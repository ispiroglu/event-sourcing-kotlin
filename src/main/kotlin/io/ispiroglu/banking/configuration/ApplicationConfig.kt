package io.ispiroglu.banking.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ApplicationConfig {

    @Bean
    @Primary
    fun serializer(): Serializer {
        return ObjectMapper()
            .registerModules(JavaTimeModule())
            .registerKotlinModule()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .let {

                JacksonSerializer.builder()
                    .objectMapper(it)
                    .defaultTyping()
                    .lenientDeserialization()
                    .build()
            }
    }

    //@Bean
    //fun getTokenStore(client: MongoClient, serializer: Serializer): TokenStore {
    //    return MongoTokenStore.builder()
    //        .mongoTemplate(
    //            DefaultMongoTemplate.builder()
    //            .mongoDatabase(client)
    //            .build()
    //        )
    //        .serializer(serializer)
    //        .build()
//
    //}
}