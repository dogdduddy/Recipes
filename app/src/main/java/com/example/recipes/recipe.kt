package com.example.recipes

class recipe {
    lateinit var id: Array<Any> // 프로퍼티의 getter, setter

    constructor() {} // 디폴트 생성자가 존재해야 deserialization 가
    constructor(id: Array<Any>) {
        this.id = id
    }

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        //result.put("title", title!!)

        return result
    }
}