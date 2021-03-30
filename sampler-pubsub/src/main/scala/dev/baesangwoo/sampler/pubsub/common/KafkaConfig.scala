package dev.baesangwoo.sampler.pubsub.common

import com.typesafe.config.{Config, ConfigFactory}

object KafkaConfig {
  private val config: Config = ConfigFactory.defaultApplication()
  val kafkaConfig: Config = config.getConfig("akka.kafka")

  val producerConfig: Config = kafkaConfig.getConfig("producer")
  val consumerConfig: Config = kafkaConfig.getConfig("consumer")

  val bootStrapServers: String = kafkaConfig.getString("bootstrap.servers")
  val groupId: String = kafkaConfig.getString("group.id")
}
