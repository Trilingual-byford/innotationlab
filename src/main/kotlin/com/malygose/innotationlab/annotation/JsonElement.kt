package com.malygose.innotationlab.annotation
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonElement(val key:String="")