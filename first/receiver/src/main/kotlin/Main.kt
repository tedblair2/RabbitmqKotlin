package com.github.tedblair2

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import mu.KotlinLogging

private val logger=KotlinLogging.logger {  }
fun main() {
    val QUEUE_NAME="hello"

    try {
        val connectionFactory=ConnectionFactory()
        connectionFactory.host="localhost"
        val connection=connectionFactory.newConnection()
        val channel=connection.createChannel()
        channel.queueDeclare(QUEUE_NAME,false,false,false, emptyMap())
        val deliveryCallback=DeliverCallback{consumerTag:String?,message:Delivery?->
            println("Message is ${message!!.body.decodeToString()}")
        }
        val cancelCallback=CancelCallback{_->}
        channel.basicConsume(QUEUE_NAME,true,deliveryCallback,cancelCallback)
    }catch (e:Exception){
        e.printStackTrace()
    }
}