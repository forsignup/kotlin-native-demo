package sample.helloworld

import kotlinx.cinterop.*
import platform.posix.getenv
import platform.windows.*

fun messageBox() {
    val message = StringBuilder()
    memScoped {
        val buffer = allocArray<UShortVar>(MAX_PATH)
        GetModuleFileNameW(null, buffer, MAX_PATH)
        val path = buffer.toKString().split("\\").dropLast(1).joinToString("\\")
        message.append(path)
    }

    val path = getenv("PATH")?.toKString() ?:""

    MessageBoxW(
            null,
            "小标题: \n$message\n$path",
            "标题栏",
            (MB_YESNOCANCEL or MB_ICONINFORMATION).convert()
    )
}

fun hello(): String = "Hello, Kotlin/Native!"

fun main(args: Array<String>) {
    println(hello())
}
