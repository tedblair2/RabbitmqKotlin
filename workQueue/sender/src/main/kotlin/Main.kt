package com.github.tedblair2

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties
import mu.KotlinLogging

private val logger=KotlinLogging.logger {  }

fun main(args: Array<String>) {
    val message=args.joinToString(" ")
    val QUEUE_NAME="task_queue"
    val factory= ConnectionFactory()
    factory.host="localhost"
    factory.newConnection().use {
        it.createChannel().use {channel ->
            val durable=true //make queue durable to survive server going down
            channel.queueDeclare(QUEUE_NAME,durable,false,false,emptyMap())
            channel.basicPublish("",QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN
                ,message.toByteArray())
            println("Sent $message")
        }
    }
}