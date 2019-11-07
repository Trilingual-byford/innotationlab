package com.malygose.innotationlab

import com.malygose.innotationlab.annotation.Init
import com.malygose.innotationlab.annotation.JsonElement
import com.malygose.innotationlab.annotation.JsonSerializable
import java.lang.reflect.Field
import java.lang.reflect.Method

class JsonSerializer {
    fun checkIfSerializable(objects: Any) {
        if (objects == null) {
            throw JsonSerializationException("The object to serialize is null")
        }
        val javaClass = objects.javaClass
        if (!javaClass.isAnnotationPresent(JsonSerializable::class.java)) {
            throw JsonSerializationException("The class \"\n" + javaClass.getSimpleName() +
                    "          + \" is not annotated with JsonSerializable")
        }

    }

    fun initializeObject(objects: Any) {
        val javaClass = objects.javaClass
        for (method: Method in javaClass.declaredMethods) {
            if (method.isAnnotationPresent(Init::class.java)) {
                method.isAccessible = true
                method.invoke(objects)
            }
        }

    }

    fun getJsonString(objects: Any): String {
        val javaClass = objects.javaClass
        var jsonElementMap = mutableMapOf<String, String>()
        for (field: Field in javaClass.declaredFields) {
            field.isAccessible = true
            if (field.isAnnotationPresent(JsonElement::class.java)) {
                jsonElementMap.put(field.name, field.get(objects) as String)
            }
        }
        var jsonString: String = ""
        jsonElementMap.forEach { entry -> jsonString = jsonString + "\"" + entry.key + "\":\"" + entry.value + "\"," }
        return "{$jsonElementMap}"
    }

    @Throws(JsonSerializationException::class)
    fun convertToJson(`object`: Any): String {
        try {
            checkIfSerializable(`object`)
            initializeObject(`object`)
            return getJsonString(`object`)
        } catch (e: Exception) {
            throw JsonSerializationException(e.message)
        }

    }
}