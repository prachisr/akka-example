import akka.actor.{Actor, ActorSystem, Props}

/*
The Counter Actor has a global state as well. The example here demonstrates how Actors
could be used easily even with state to provide concurrency without the unintended
results
*/

object Main extends App {

  case class Count(value: Int)

  implicit val system: ActorSystem = ActorSystem("counting-system")

  class Counter(var value: Int) extends Actor {
    override def receive : Receive = {
      case "next" => value += 1
      case "result" => println(value)
    }
  }

  val countingActor = system.actorOf(Props(new Counter(0)))

  class CountingThread extends Thread {
    override def run(): Unit = {
      for (_ <- 0 until 10000) {
        countingActor ! "next"
      }
    }
  }

  val countingThread1 = new CountingThread
  val countingThread2 = new CountingThread

  countingThread1.start()
  countingThread2.start()

  countingThread1.join()
  countingThread2.join()

  countingActor ! "result"
}
