package com.meetstudio.pharmacy.coroutine

import kotlinx.coroutines.*

class Launch {
}

fun main(){
    testRunBlocking()
//    testLaunch()
//    testCoroutineScope()
//    testCancelAndJoin()
//    testAsync()
//    testConcurrentSum()
//    testWithContext()
}


fun testRunBlocking(){
    letUsPrintln("Hello,")
    runBlocking {               // 這個表示式阻塞了主執行緒
        letUsPrintln("World!")
        delay(2000L)  // 等待2秒
        letUsPrintln("end!")
    }
    letUsPrintln("我被阻塞了沒!")
}

fun testLaunch(){
    GlobalScope.launch { // 在後臺啟動一個新的協程並繼續;
        delay(1000L)
        letUsPrintln("World!")
    }
    letUsPrintln("Hello,") //launch 不是阻塞協程, 後面主執行緒中的程式碼會立即執行
    runBlocking {
        delay(2000L)  // 我們阻塞主執行緒 2 秒來保證 JVM 的存活
        letUsPrintln("end!")
    }
}

fun testCoroutineScope(){
    runBlocking{
        launch {
            delay(200L)
            letUsPrintln("Task from runBlocking")   // 2. 200 delay  launch 不阻塞
        }

        coroutineScope {	// 建立一個協程作用域
            launch {
                delay(500L)
                letUsPrintln("Task from nested launch")
            }
            delay(100L)
            letUsPrintln("Task from coroutine scope") // 1. 100 delay  launch 不阻塞
        }

        letUsPrintln("Coroutine scope is over") // 4. 500 delay  coroutineScope
    }
}

fun testCancelAndJoin(){
    runBlocking{
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) { // this: CoroutineScope
            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // 一個執行計算的迴圈，只是為了佔用 CPU
                // 每秒列印訊息兩次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段時間
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一個作業並且等待它結束
        println("main: Now I can quit.")
    }
}

fun testAsync(){
    runBlocking {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
}

suspend fun testConcurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假設我們在這裡做了一些有用的事
    return 13
}
suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假設我們在這裡也做了一些有用的事
    return 29
}

fun testWithContext(){
    runBlocking {
        letUsPrintln("start!-主執行緒")
        withContext(Dispatchers.IO) { // 啟動一個新協程, 這是 this.launch
            delay(1000L)
            letUsPrintln("111-子執行緒!")
        }
        letUsPrintln("end!-主執行緒")
    }
}

//列印程式碼
fun letUsPrintln(title: String){
    println("$title Thread_name：${Thread.currentThread().name}")
}