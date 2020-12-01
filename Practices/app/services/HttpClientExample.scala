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


    rawData.result.aggregationsAsString

//    val result = Await.result(dataExp, Duration.Inf)
//    val prettyPrint = Json.stringify(result)

//    dataExp.result.hits.total.value

  }

}
