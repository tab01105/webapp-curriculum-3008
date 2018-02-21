import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

import scala.concurrent.Await
import scala.concurrent.duration._

case class Greet(receiver: ActorRef)

case class Greeting(message: String)

class ReplyActor extends Actor {
  def receive = {
    case Greet(receiver) => receiver ! Greeting("msg")
    case Greeting(message) => {
      sender ! Greeting(message)
      println(sender + ":" + message)
    }
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("actorStudy")

  val replyActor1 = system.actorOf(Props[ReplyActor], "replyActor1")
  val replyActor2 = system.actorOf(Props[ReplyActor], "replyActor2")

  replyActor1 ! Greet(replyActor2)

  Await.ready(system.terminate(), 5000.millisecond)
}
