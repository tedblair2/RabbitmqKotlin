package com.github.tedblair2

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import mu.KotlinLogging

private val logger=KotlinLogging.logger {  }
fun main() {
    val QUEUE_NAME="task_queue"
    val connectionFactory= ConnectionFactory()
    connectionFactory.host="localhost"

    try {
        val connection=connectionFactory.newConnection()
        val channel=connection.createChannel()
        val durable=true //mark queue as durable
        channel.queueDeclare(QUEUE_NAME,durable,false,false, emptyMap())
        channel.basicQos(1) //wait until process finishes b4 allocating another
        val deliveryCallback= DeliverCallback{_,message->
            println("Received ${message.body.decodeToString()}")
            try {
                doWork(message.body.decodeToString())
            }finally {
                println("Work done")
                channel.basicAck(message.envelope.deliveryTag,false) //acknowledge after work is done
            }
        }
        val cancelCallback= CancelCallback{ _->}
        val autoAck=false //remove auto acknowledge
        channel.basicConsume(QUEUE_NAME,autoAck,deliveryCallback,cancelCallback)
    }catch (e:Exception){
        e.printStackTrace()
    }
}

fun doWork(message:String){
    for (c in message.toCharArray()){
        if (c=='.'){
            Thread.sleep(1000)
        }
    }
}