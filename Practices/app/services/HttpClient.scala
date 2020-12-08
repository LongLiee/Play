package services

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.http.JavaClient

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import play.api.libs.json._
import com.sksamuel.elastic4s.requests.searches.{DateHistogramInterval, SearchResponse}
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}

class HttpClient extends App {


  def getData() = {
    val domain = ConfigFactory.load().getString("MY_DOMAIN")
    val props = ElasticProperties(domain)
    val client = ElasticClient(JavaClient(props))
//    var query = ""
//    if(column == "") {
//      query = "(Men's Clothing) OR (Women's Clothing)"
//    } else if (column == "Man"){
//      query = "(Men's Clothing)"
//    } else if (column == "Woman"){
//      query = "(Women's Clothing)"
//    }

    val rawData = client.execute {
      search("kibana_sample_data_ecommerce")
        .size(0)
        .query(queryStringQuery("(Men's Clothing) OR (Women's Clothing)").defaultField("category.keyword"))
        .aggs{
          dateHistogramAgg("each_day","order_date").format("yyyy-MM-dd").fixedInterval(86400)
        }
        .sourceInclude("category","order_date")
    }.await


    println("---------------Result------------")
        println(rawData.result.aggregationsAsString)
//    rawData.result


    //    val result = Await.result(dataExp, Duration.Inf)
    //    val prettyPrint = Json.stringify(result)

    //    dataExp.result.hits.total.value

  }

}
