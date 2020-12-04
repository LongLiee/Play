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
//import play.mvc.BodyParser.Json
//import org.json4s.jackson.JsonMethods._
//import org.json4s.{DefaultFormats, _}
//import org.json4s.JsonInput


class HttpClientExample extends App {
  def ParseSearchResponse(searchResponse: SearchResponse) = {
    "THIS IS PARSED DATA FROM RAW DATA"
    //(elastic4s OR scala play framework) parse Elastic SeachResponse into Json
    val rawJson = searchResponse.aggregationsAsString
    val jsonObject:JsValue = Json.parse(rawJson)
    val menClothing = (jsonObject \ "category_types" \ "buckets" \ 0 \ "each_day" \ "buckets").get
    val womenClothing = (jsonObject \ "category_types" \ "buckets" \ 1 \ "each_day" \ "buckets").get
//    val womenClothing = (jsonObject \ "category_types" \ "buckets" \ 1).get
//    val menClothing = (jsonObject \ "category_types" \ "buckets" \ 0).get

//    val timeLine = List((menClothing \ 0 \ "key_as_string"),(menClothing \ 1 \ "key_as_string"),(menClothing \ 2 \ "key_as_string"),(menClothing \ 3 \ "key_as_string")
//                        ,(menClothing \ 4 \ "key_as_string"),(menClothing \ 5 \ "key_as_string"),(menClothing \ 6 \ "key_as_string"))
//    val menDoc =  (menClothing \ 0 \ "doc_count")


    val jsonValue = Json.toJson(List(menClothing,womenClothing))
    jsonValue


  }

  def getData = {
      val domain = ConfigFactory.load().getString("MY_DOMAIN")
      val props = ElasticProperties(domain)
      val client = ElasticClient(JavaClient(props))

     val rawData = client.execute {
          search("kibana_sample_data_ecommerce")
            .size(0)
            .aggs{
              termsAgg("category_types","category.keyword").subAggregations(
                dateHistogramAgg("each_day","order_date").format("yyyy-MM-dd").calendarInterval(DateHistogramInterval.Day)
              )
            }
          .sourceInclude("category","order_date")
        }.await


    println("---------------Result------------")
//    println(rawData.result)
    ParseSearchResponse(rawData.result)


//    val result = Await.result(dataExp, Duration.Inf)
//    val prettyPrint = Json.stringify(result)

//    dataExp.result.hits.total.value

  }

}
