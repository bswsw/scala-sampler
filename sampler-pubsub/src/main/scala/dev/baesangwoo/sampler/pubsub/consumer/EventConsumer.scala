package dev.baesangwoo.sampler.pubsub.consumer

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.scaladsl.{Committer, Consumer}
import akka.kafka.{CommitterSettings, ConsumerSettings, Subscriptions}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.{ExecutionContext, Future}

class EventConsumer(implicit system: ActorSystem, ec: ExecutionContext) {
  private val config = system.settings.config.getConfig("akka.kafka.consumer")
  private val kafkaClientsConfig = config.getConfig("kafka-clients")
  private val bootstrapServers = kafkaClientsConfig.getString("bootstrap.servers")

  private val consumerSettings = ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
    .withBootstrapServers(bootstrapServers)
    .withGroupId("alan-test-group")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
    .withProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "50")
    .withProperty(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000")

  private val committerSettings = CommitterSettings(system)

  def consume(): Unit = {
    val drainingControl: DrainingControl[Done] =
      Consumer
        .committableSource(
          consumerSettings,
          Subscriptions.topics("local.jarvis-dead-letter")
        )
        .mapAsync(1) { msg =>
          business(msg.record.key(), msg.record.value()).map(_ => msg.committableOffset)
        }
        .toMat(Committer.sink(committerSettings))(DrainingControl.apply)
        .run()

//    println("consume complete")
//
//    drainingControl.drainAndShutdown()
  }

  private def business(key: String, value: String): Future[Unit] = {
    Future.successful {
      println(s"key : $key, value: $value")
    }
  }
}
