package services

import java.io.File
import play.api.libs.json.Json
import com.typesafe.config.{Config, ConfigFactory}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.http.JavaClient

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import play.api.libs.json._
import com.sksamuel.elastic4s.requests.searches.{DateHistogramInterval, SearchResponse}
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}

class HttpClient extends App {


  def getData(json: JsValue) = {
    val domain = ConfigFactory.load().getString("MY_DOMAIN")
    val props = ElasticProperties(domain)
    val client = ElasticClient(JavaClient(props))
    var queryString = ""
    var queryNum = 86400
    if (json == null){
      queryString = "(Men's Clothing) OR (Women's Clothing)"
    } else {
          val typeCategory = (json \ "category").as[String]
          val typeDate = (json \ "day").as[String]
      println(typeCategory)
      println(typeDate)
      if ( typeCategory == "MenClo" && typeDate == "oneDay"){
            queryString = "(Men's Clothing)"
      }
      else if (typeCategory == "MenClo" && typeDate == "twoDays"){
          queryString = "(Men's Clothing)"
          queryNum = 172800
      } else if (typeCategory == "MenClo" && typeDate == "threeDays"){
        queryString = "(Men's Clothing)"
        queryNum = 259200
      } else if ( typeCategory == "WomenClo" && typeDate == "oneDay"){
        queryString = "(Women's Clothing)"
      } else if (typeCategory == "WomenClo" && typeDate == "twoDays"){
        queryString = "(Women's Clothing)"
        queryNum = 172800
      } else if (typeCategory == "WomenClo" && typeDate == "threeDays"){
        queryString = "(Women's Clothing)"
        queryNum = 259200
      }else if ( typeCategory == "All" && typeDate == "oneDay"){
        queryString = "(Men's Clothing) OR (Women's Clothing)"
      } else if (typeCategory == "All" && typeDate == "twoDays"){
        queryString = "(Men's Clothing) OR (Women's Clothing)"
        queryNum = 172800
      } else if (typeCategory == "All" && typeDate == "threeDays"){
        queryString = "(Men's Clothing) OR (Women's Clothing)"
        queryNum = 259200
      }
    }



    val rawData = client.execute {
      search("kibana_sample_data_ecommerce")
        .size(0)
        .query(queryStringQuery(queryString).defaultField("category.keyword"))
        .aggs{
          dateHistogramAgg("each_day","order_date").format("yyyy-MM-dd").fixedInterval(queryNum)
        }
        .sourceInclude("category","order_date")
    }.await


    println("---------------Result------------")
    println(rawData.result.aggregationsAsString)
    handleData(rawData.result.aggregationsAsString)


  }
  def handleData (data:String) = {
      val jsData = Json.parse(data)
      val count = List(( jsData \ "each_day" \ "buckets" \ 0 \ "doc_count").get,( jsData \ "each_day" \ "buckets" \ 1 \ "doc_count").get,( jsData \ "each_day" \ "buckets" \ 2 \ "doc_count").get,
        ( jsData \ "each_day" \ "buckets" \ 3 \ "doc_count").get,( jsData \ "each_day" \ "buckets" \ 4 \ "doc_count").get,( jsData \ "each_day" \ "buckets" \ 5 \ "doc_count").get,( jsData \ "each_day" \ "buckets" \ 6 \ "doc_count").get)
      val jsArr: JsValue = Json.toJson(count)
    jsArr
  }
}
