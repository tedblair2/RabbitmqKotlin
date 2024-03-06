package com.github.tedblair2

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

fun main(args: Array<String>) {
    val exchangeName="direct-exchange"
    val key=args[0]
    val connectionFactory=ConnectionFactory()
    connectionFactory.host="localhost"
    try {
        val connection=connectionFactory.newConnection()
        val channel=connection.createChannel()
        channel.exchangeDeclare(exchangeName,"direct")
        val queueName=channel.queueDeclare().queue
        channel.queueBind(queueName,exchangeName,key)
        val deliverCallback=DeliverCallback{_,message->
            println("Received ${message.envelope.routingKey}:${message.body.decodeToString()}")
        }
        val cancelCallback=CancelCallback{_->}
        channel.basicConsume(queueName,true,deliverCallback,cancelCallback)
    }catch (e:Exception){
        e.printStackTrace()
    }
}