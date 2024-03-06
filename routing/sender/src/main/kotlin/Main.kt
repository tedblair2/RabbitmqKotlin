package com.github.tedblair2

import com.rabbitmq.client.ConnectionFactory

fun main(args: Array<String>) {
    val key=args[0]
    val message= joinArgsFromIndex(args,1)
    val exchangeName="direct-exchange"
    val connectionFactory= ConnectionFactory()
    connectionFactory.host="localhost"
    connectionFactory.newConnection().use {
        it.createChannel().use { channel ->
            channel.exchangeDeclare(exchangeName,"direct")
            channel.basicPublish(exchangeName,key,null,message.toByteArray())
            println("Sent $message")
        }
    }
}

fun joinArgsFromIndex(args: Array<String>, startIndex:Int):String{
    return if (startIndex<args.size){
        args.sliceArray(startIndex until args.size).joinToString(" ")
    }else ""
}