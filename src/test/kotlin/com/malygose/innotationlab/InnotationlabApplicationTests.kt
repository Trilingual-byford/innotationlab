package com.malygose.innotationlab

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InnotationlabApplicationTests {

    @Test
    fun contextLoads() {
        val person = Person("lufi", "Gose")
        val jsonSerializer = JsonSerializer()
        val convertToJson = jsonSerializer.convertToJson(person)
        System.out.println(convertToJson)
    }

}
