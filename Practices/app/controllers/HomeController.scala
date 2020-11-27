package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs._
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
  def index = Action {
//    val chartData = services.HttpClientExample.getData()
    val chartData1 = services.HttpClientExample.getData
    Ok(views.html.index("lala", chartData1))
  }

}
