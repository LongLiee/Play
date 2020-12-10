package controllers
import services.HttpClientExample
import services.HttpClient

import javax.inject._
import play.api.mvc._
import play.api.libs._
import play.api.libs.json.{JsNull, JsString, JsValue}
import services.HttpClient

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action {

    val chartDataTest = new HttpClient
    val firstQuery:JsValue = null
    val newData = chartDataTest.getData(firstQuery)
    println("---------------Controller------------------")
    println(newData)
//    val data = chartData1.getData(nameMainAggs,fieldMainAggs,nameSubAggs,fieldSubAggs,formatDate)
    Ok(views.html.index( "Welcome", newData))
  }

    def postData2() = Action { implicit request: Request[AnyContent] =>
      val body: AnyContent          = request.body
      val jsonBody: Option[JsValue] = body.asJson

      // Expecting json body
      val chartData = new HttpClient
      val updateData = chartData.getData(jsonBody.get)
      jsonBody
        .map { json =>
          println("TESTING")
          val chartData = new HttpClient
          val updateData = chartData.getData(json)
          Ok(views.html.index("Welcome",updateData))
        }
        .getOrElse {

          BadRequest("Expecting application/json request body")
        }
    }

  def postData() = Action { implicit request: Request[AnyContent] =>
    val body = request.body.asJson.getOrElse(JsNull)

    val chartData = new HttpClient
    val updateData = chartData.getData(body)
    println(updateData)

//    Ok(views.html.index("Testing", updateData))
    Ok(updateData)
  }






}
