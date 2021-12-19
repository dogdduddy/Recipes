package com.example.recipes

class Find {
    var id: String? = null // 프로퍼티의 getter, setter
    var title: String? = null
    var ingre: ArrayList<String>? = null
    var content: String? = null
    var time: String? = null
    var meet:String? = null
    var veget: String? = null
    var source:String? = null

    constructor() {} // 디폴트 생성자가 존재해야 deserialization 가
    constructor(id: String, title: String, ingre:ArrayList<String>, content: String, time:String, veget:String) {
        this.id = id
        this.title = title
        this.ingre = ingre
        this.content = content
        this.time = time

    }

    constructor(time:String, source: String, ingre:ArrayList<String>, meet: String, veget:String) {
        this.time = time
        this.source = source
        this.ingre = ingre
        this.meet = meet
        this.veget = veget
    }
    constructor(title: String, time:String, t:String, content: String, a:String ) {
        this.title = title
        this.meet = a
        this.content = content
        this.time = time
    }

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("ingre", ingre!!)
        result.put("content", content!!)
        result.put("time", time!!)

        return result
    }
}