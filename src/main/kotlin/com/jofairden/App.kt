package com.jofairden

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.Banner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import java.io.File

@SpringBootApplication
open class App {

    companion object {
        lateinit var appContext: ConfigurableApplicationContext
        lateinit var klapinette: Klapinette

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val job = GlobalScope.launch {
                // We need to read our token safely
                // NOBODY MUST KNOW YOUR BOT TOKEN! KEEP IT SAFE!
                // Recommended is to read it from a (local) file or grab
                // it from an environment variable
                klapinette = Klapinette("NjU0MTIxMzQ0NzExMDAwMDcw.XfDhCQ.55Z16eEVTCNhX8Qw6PMztzg2FGY")
            }
            job.join()

            appContext = runApplication<App>(*args) {
                setBannerMode(Banner.Mode.CONSOLE)
                webApplicationType = WebApplicationType.NONE
            }

            klapinette.start()
        }

        private fun getBotToken(): String {
            val classLoader = App::class.java.classLoader
            val file = File(classLoader.getResource("bot.token")!!.file)

            if (!file.canRead()) {
                throw RuntimeException("Unable to read bot.token file")
            }

            return file.readText()
        }
    }
}