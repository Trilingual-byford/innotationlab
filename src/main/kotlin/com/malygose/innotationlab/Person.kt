package com.malygose.innotationlab

import com.malygose.innotationlab.annotation.Init
import com.malygose.innotationlab.annotation.JsonElement
import com.malygose.innotationlab.annotation.JsonSerializable

@JsonSerializable
class Person( _firstName: String,
              _lastName: String) {
    @JsonElement
    var firstName:String
    @JsonElement
    var lastName:String
    init {
        firstName=_firstName
        lastName=_lastName
    }
    @Init
    fun initName() {
        this.lastName=this.lastName.substring(0,1).toUpperCase()+this.lastName.substring(1)
        this.firstName=this.firstName.substring(0,1).toUpperCase()+this.firstName.substring(1)
    }

}

