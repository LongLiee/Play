package controllers
import services.HttpClientExample
import services.HttpClient
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
    val chartData1 = new HttpClientExample
    val chartDataTest = new HttpClient
    val nameMainAggs = "category_types"
    val fieldMainAggs = "category.keyword"
    val nameSubAggs = "each_day"
    val fieldSubAggs = "order_date"
    val formatDate = "yyyy-MM-dd"
    val newData =chartDataTest.getData()
    val data = chartData1.getData(nameMainAggs,fieldMainAggs,nameSubAggs,fieldSubAggs,formatDate)
    Ok(views.html.index( "Welcome", newData))
  }

}
