package controllers
import javax.inject._
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class TaskList1 @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def TaskList = Action {
    Ok("This works!!")
  }

}
