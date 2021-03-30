package dev.baesangwoo.sampler.pubsub.producer

import akka.NotUsed
import akka.actor.ActorSystem
import akka.kafka.{ProducerMessage, ProducerSettings}
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.{Flow, Source}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.MapHasAsJava

class EventProducer(implicit system: ActorSystem, ec: ExecutionContext) {

  private val producerSettings =
    ProducerSettings(system, new StringSerializer, new StringSerializer)
      .withBootstrapServers("localhost:9092")

  private val stringProducerParams: Map[String, AnyRef] = Map(
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092",
    ProducerConfig.ACKS_CONFIG -> "1",
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG -> classOf[StringSerializer],
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG -> classOf[StringSerializer]
  )

//  private val producer: Producer[String, String] = producerSettings.createKafkaProducer()
//  private val settingsWithProducer = producerSettings.withProducer(producer)

  private val topic = "sangwoo"

  def sendMessage(): Unit = {
//    Source(1 to 10)
//      .map(_.toString)
//      .map(value => new ProducerRecord[String, String](topic, "sangwoo-key", s"this is a data!!_ $value"))
//      .runWith(plainSink(settingsWithProducer))

//    producer.close()

    val producer = new KafkaProducer[String, String](stringProducerParams.asJava)
    val record = new ProducerRecord[String, String](topic, "sangwoo-key", s"this is a data!!")
    producer.send(record)
//    producer.close()

    val source: Source[Int, NotUsed] = Source(1 to 100)
    val flow: Flow[ProducerMessage.Envelope[String, String, Nothing], ProducerMessage.Results[String, String, Nothing], NotUsed] = Producer.flexiFlow(producerSettings)


    Source.single("")
      .map(_ => record)
      .runWith(Producer.plainSink(producerSettings))
  }
}
