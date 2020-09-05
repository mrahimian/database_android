package com.example.database

class Info (phone : String ,region : String , isStar : String , star : String , description : String , have : String , notHave : String ) {
    var phone : String = ""
    var region : String = ""
    var is_star : String = ""
    var star : String = ""
    var description : String = ""
    var have : String = ""
    var notHave : String = ""
    init {
        this.phone = phone
        this.region = region
        this.is_star = isStar
        this.star = star
        this.description = description
        this.have = have
        this.notHave = notHave
    }

}