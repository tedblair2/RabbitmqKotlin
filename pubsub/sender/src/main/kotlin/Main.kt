package com.github.tedblair2

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties

fun main(args: Array<String>) {
    val exchangeName="firstpub"
    val message=args.joinToString(" ")
    val connectionFactory=ConnectionFactory()
    connectionFactory.host="localhost"
    connectionFactory.newConnection().use {
        it.createChannel().use { channel ->
            channel.exchangeDeclare(exchangeName,"fanout")
            channel.basicPublish(exchangeName,"",
                null,
                message.toByteArray())
            println("Sent $message")
        }
    }
}