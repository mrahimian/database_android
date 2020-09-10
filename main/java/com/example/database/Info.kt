package com.example.database

class Info (id : String , phone : String , phone2 : String ,phone3 : String ,region : String , isStar : String , star : String , description : String , have : String , notHave : String , isExist :Boolean ) {
    var id : String = ""
    var phone : String = ""
    var phone2 : String = ""
    var phone3 : String = ""
    var region : String = ""
    var is_star : String = ""
    var star : String = ""
    var description : String = ""
    var have : String = ""
    var notHave : String = ""
    var isExist : Boolean = false
    init {
        this.id = id
        this.phone = phone
        this.phone2 =  phone2
        this.phone3 =  phone3
        this.region = region
        this.is_star = isStar
        this.star = star
        this.description = description
        this.have = have
        this.notHave = notHave
        this.isExist = isExist
    }

}