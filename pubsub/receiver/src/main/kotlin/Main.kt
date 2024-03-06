package com.github.tedblair2

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main() {
    val exchangeName="firstpub"
    val connectionFactory=ConnectionFactory()
    connectionFactory.host="localhost"
    try {
        val connection=connectionFactory.newConnection()
        val channel=connection.createChannel()
        val queueName=channel.queueDeclare().queue
        channel.queueBind(queueName,exchangeName,"")
        val deliverCallback=DeliverCallback{_,delivery->
            println(delivery.body.decodeToString())
        }
        val cancelCallback=CancelCallback{_->}
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback)
    }catch (e:Exception){
        e.printStackTrace()
    }
}