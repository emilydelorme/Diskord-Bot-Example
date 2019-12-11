package com.jofairden.config

import com.jofairden.Klapinette
import com.jofairden.App
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanConfig {

    @Bean
    open fun getBot(): Klapinette {
        return App.klapinette
    }
}