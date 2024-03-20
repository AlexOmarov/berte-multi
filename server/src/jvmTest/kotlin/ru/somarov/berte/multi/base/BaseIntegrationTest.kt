package ru.somarov.berte.multi.base

import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseIntegrationTest {
    companion object {
        private var postgresql = PostgreSQLContainer<Nothing>("postgres:15.2").apply {
            withReuse(true)
            start()
        }
        private var redis = RedisContainer(DockerImageName.parse("redis:6.2.6")).apply {
            withReuse(true)
            start()
        }
        private var kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3")).apply {
            withReuse(true)
            start()
        }

        init {
            System.setProperty("kafka.brokers", kafka.bootstrapServers)

            System.setProperty("contour.cache.host", redis.host)
            System.setProperty("contour.cache.port", redis.firstMappedPort.toString())

            System.setProperty("spring.flyway.url", postgresql.jdbcUrl)
            System.setProperty("spring.flyway.user", postgresql.username)
            System.setProperty("spring.flyway.password", postgresql.password)
            System.setProperty(
                "spring.r2dbc.url",
                "r2dbc:postgresql://${postgresql.host}:${postgresql.firstMappedPort}/${postgresql.databaseName}"
            )
            System.setProperty("spring.r2dbc.username", postgresql.username)
            System.setProperty("spring.r2dbc.password", postgresql.password)
        }
    }
}
