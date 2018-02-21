import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

import scala.concurrent.Await
import scala.concurrent.duration._

class MyActor extends Actor {
  var counter = 0

  def receive = {
    case _ => {
      counter += 1
      println(counter)
    }
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("actorStudy")

  val myActor = system.actorOf(Props[HeabyActor], "myActor")

  for (i <- 1 to 10000)
    myActor ! "send"

//  system.terminate()
  Await.ready(system.terminate(), Duration.Inf)
}
