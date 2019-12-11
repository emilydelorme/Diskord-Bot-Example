package com.jofairden.commands

import com.jessecorbett.diskord.api.model.Message
import com.jofairden.BotCommand
import com.jofairden.Klapinette
import org.springframework.stereotype.Component

@Component
class PingCommand : BotCommand("ping") {

    override suspend fun action(listener: Klapinette, message: Message) {
        with(listener) {
            logger.info("I got a message :: ${message.content}")
            message.reply("pong")
        }
    }

}