package com.jofairden

import com.jessecorbett.diskord.api.model.Message
import com.jessecorbett.diskord.dsl.Bot
import com.jessecorbett.diskord.dsl.DiskordDsl
import com.jessecorbett.diskord.dsl.bot
import com.jessecorbett.diskord.util.EnhancedEventListener
import com.jofairden.services.CommandService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired


class Klapinette(
    token: String
) : EnhancedEventListener(token) {

    companion object {
        lateinit var bot : Bot
    }

    init {
        createBot(token)
    }

    @Autowired
    private lateinit var commandService: CommandService

    suspend fun start() {
        val factory = App.appContext.autowireCapableBeanFactory
        factory.autowireBean(this)
        factory.initializeBean(this, Klapinette::class.java.name)

        bot.start()
    }

    private suspend fun createBot(token: String) {
        val job = GlobalScope.launch {
            bot = bot(token) {}
        }
        job.join()
    }

    override suspend fun onMessageCreate(message: Message) {
        commandService.handleMessage(this, message)
    }
}

