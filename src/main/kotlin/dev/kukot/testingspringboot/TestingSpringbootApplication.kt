package dev.kukot.testingspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestingSpringbootApplication

fun main(args: Array<String>) {
    runApplication<TestingSpringbootApplication>(*args)
}
