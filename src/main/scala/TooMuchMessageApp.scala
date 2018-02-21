import java.util.Date
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

import scala.concurrent.Await
import scala.concurrent.duration._

class HeabyActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case message:String => {
      log.info(message)
      Thread.sleep(1000)
    }
  }
}
object TooMuchMessageApp extends App {
  val system = ActorSystem("actorStudy")

  val heabyActor = system.actorOf(Props[HeabyActor], "heabyActor")
while(true)
  heabyActor ! new Date().toString

  Await.ready(system.terminate(), 5000.millisecond)
}
