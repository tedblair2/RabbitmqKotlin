package com.github.tedblair2

import com.rabbitmq.client.ConnectionFactory
import mu.KotlinLogging

private val logger=KotlinLogging.logger {  }
fun main() {
    val QUEUE_NAME="hello"
    val message="Another message comes now"
    val factory=ConnectionFactory()
    factory.host="localhost"
    factory.newConnection().use {
        it.createChannel().use {channel ->
            channel.queueDeclare(QUEUE_NAME,false,false,false,emptyMap())
            channel.basicPublish("",QUEUE_NAME,null,message.toByteArray())
            println("[x] Sent $message")
        }
    }
}
