package com.example.recipes

class Find {
    var id: String? = null // 프로퍼티의 getter, setter
    var title: String? = null
    var ingre: Array<String>? = null
    var content: String? = null
    var time: String? = null

    constructor() {} // 디폴트 생성자가 존재해야 deserialization 가
    constructor(id: String, title: String, ingre:Array<String>, content: String, time:String) {
        this.id = id
        this.title = title
        this.ingre = ingre
        this.content = content
        this.time = time

    }

    constructor(title: String, ingre:Array<String>, content: String, time:String) {
        this.title = title
        this.ingre = ingre
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