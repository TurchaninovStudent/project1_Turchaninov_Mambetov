/////////////////////////////////////////////
//
// Практическая №1. Основы
// Выполнили Турчанинов А.Е. и Мамбетов Н.А.
// Политехнический колледж городского хозяйства
// Группа: ИП-23-3
// 3 Курс 1 семестр
//
//
//
/////////////////////////////////////////////

import java.lang.Exception
import java.security.InvalidParameterException
import kotlin.math.log

fun main() {
    println("Программа для задач")
    var continueState = true

    do {
        continueState = startMenuCycle()
    } while (continueState)

    println("Увидимся в следующий раз")
}

fun startMenuCycle(): Boolean {
    println("Выберите задачу: ")
    println("1 - Сокращение строк")
    println("2 - Подсчёт символов")
    println("3 - Перевод из 10-ичной системы в двоичную")
    println("4 - Калькулятор")
    println("5 - Нахождение целочисленного показателя")
    println("6 - Существует ли нечётное число из 2 цифр")
    println("7 - Выход из программы")
    print("Ваш выбор - ")

    var chosenTask = 1

    try {
        chosenTask = readln().toInt()
    } catch (_: Exception) {
        print("Вводить только числа - ")
        return true
    }

    when (chosenTask) {
        1 -> task1()
        2 -> task2()
        3 -> task3()
        4 -> task4()
        5 -> task5()
        6 -> task6()
        7 -> return false
        else -> println("Неверный ввод данных")
    }

    return true
}

fun task1() {
    print("Введите строку пожалуйста: ")
    val response: String = readln()

    fun reduceString(target: String): String {
        var result: String = ""
        var count: Int = 1

        for (i in 1..target.length - 1) {
            if (target[i] == target[i - 1]) {
                count++

            } else {
                result += target[i - 1]
                if (count > 1) {
                    result += count.toString()
                }
                count = 1
            }
        }

        result += target[target.length - 1]
        if (count > 1) {
            result += count.toString()
        }

        return result
    }

    println("результат: ${reduceString(response)}")
}

fun task2() {
    print("Введите строку пожалуйста: ")
    val response: String = readln()

    fun countLetters(target: String) {
        val result = mutableMapOf<Char, Int>()

        for (character in target) {
            if (result.containsKey(character)) {
                result[character] = result[character]!! + 1
            } else {
                result[character] = 1
            }
        }

        val sortedResult = result.keys.sorted()

        for (key in sortedResult) {
            println("$key - ${result[key]}")
        }
    }

    countLetters(response)
}

fun task3() {
    print("Введите натуральное число пожалуйста: ")
    var response: Int = 0

    try {
        response = readln().toInt()
    } catch (_: Exception) {
        print("Вводить только числа")
        return
    }

    fun translateToBinary(target: Int): String {
        var targetCopy = target
        var result: String = ""

        while (targetCopy > 0) {
            result += targetCopy % 2
            targetCopy /= 2
        }

        return result.reversed()
    }

    val result = translateToBinary(response)
    println("Результат: ${result}")
}

fun task4() {
    print("Введите 2 числа и знак операции пожалуйста: ")
    val response: String = readLine() ?: ""
    val responseParts = response.split(" ")

    if (responseParts.size != 3) {
        println("Ввод должен быть в формате ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ")
        return
    }

    val firstNumber: Double = responseParts[0].toDouble()
    val secondNumber: Double = responseParts[1].toDouble()
    val operation: String = responseParts[2]

    var result: Double = when(operation) {
        "+" -> firstNumber + secondNumber
        "-" -> firstNumber - secondNumber
        "*" -> firstNumber * secondNumber
        "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else {
            println("Делить на ноль нельзя")
            return
        }
        else -> {
            println("Неправильный ввод данных")
            return
        }
    }

    println("Результат: ${result}")
}

fun task5() {
    var base = 0.0
    var x = 0.0

    print("Введите целое число n и основание степени пожалуйста: \n")

    try {
        base = readln().toDouble()
        x = readln().toDouble()
    } catch (_: Exception) {
        print("Вводить только числа - ")
        return
    }

    fun isInteger(number: Double): Boolean {
        return number == number.toInt().toDouble()
    }

    val result = log(x, base)

    if (isInteger(result)) {
        println("Целочисленный показатель существует: ${result.toInt()}")
    } else {
        println("Целочисленный показатель не существует")
    }
}

fun task6() {
    var firstDigit = 0
    var secondDigit = 0

    print("Введите две цифры: \n")

    try {
        firstDigit = readln().toInt()
        secondDigit = readln().toInt()

        if (firstDigit < 0 || firstDigit > 9 || secondDigit < 0 || secondDigit > 9) {
            throw InvalidParameterException()
        }
    } catch (_: Exception) {
        print("Вводить только цифры - ")
        return
    }

    if (firstDigit % 2 != 0) {
        println("Возможно нечётное число: ${secondDigit}${firstDigit}")
    } else if(secondDigit % 2 != 0) {
        println("Возможно нечётное число: ${firstDigit}${secondDigit}")
    } else {
        println("нечётное число не возможно получить из этих цифр")
    }
}