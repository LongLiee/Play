package services

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.http.JavaClient

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import play.api.libs.json.JsValue

import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}
//import play.mvc.BodyParser.Json
//import org.json4s.jackson.JsonMethods._
//import org.json4s.{DefaultFormats, _}
//import org.json4s.JsonInput


object HttpClientExample extends App {
  def getData = {
      val domain = ConfigFactory.load().getString("MY_DOMAIN")
      val props = ElasticProperties(domain)
      val client = ElasticClient(JavaClient(props))

    val dataExp = client.execute {
      search("kibana_sample_data_ecommerce").query(
        boolQuery().must(
          matchQuery("category","Men's Clothing"),
          matchQuery("day_of_week","Monday")
        )
      )
    }


    val result = Await.result(dataExp, Duration.Inf)
//    val prettyPrint = Json.stringify(result)











    println("----------------------------------------------------------------------------")

    println(result)


  }




}
