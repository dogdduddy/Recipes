package com.example.recipes

import io.grpc.NameResolver

fun main() {
    var b = " 가,나,다 "
    var a = b.split(",")
    var c = arrayOf(1,2,3,4,5)
    c.set(1,6)
    print(c.get(1))
}
