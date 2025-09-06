import jdk.dynalink.Operation
import java.lang.Exception

/////////////////////////////////////////////
//
// Практическая №1. Основы
// Выполнили Турчанинов А.Е. и Мамбетов Н.А.
// Политехнический колледж городского хозяйства
// Группа: ИП-23-3
// 3 Курс 1 семестр, 1 неделя 6 день, солнечная погода, настроение отличное
// Задание: Создать приложение, которое подсчитывает количество подряд идущих одинаковых символов во введенной строке.
// На вход подается, например, строка AAADSSSRRTTHAAAA. На выходе получаем A3DS3R2T2HA4.
// То есть, если количество подряд идущих символов меньше двух, то мы не пишем единицу
//
/////////////////////////////////////////////

fun main() {
    println("Программа для задач")

    startMenuCycle()
}

fun startMenuCycle() {
    println("Выберите задачу: ")
    println("1 - Сокращение строк")
    println("2 - Подсчёт символов")
    println("3 - Перевод из 10-ичной системы в двоичную")
    println("4 - Калькулятор")
    println("5 - ")
    println("6 - ")
    print("Ваш выбор - ")

    var chosenTask: Int = 1

    try {
        chosenTask = readln().toInt()
    } catch (_: Exception) {
        print("Вводить только числа - ")
        return
    }

    when (chosenTask) {
        1 -> task1()
        2 -> task2()
        3 -> task3()
        4 -> task4()
        5 -> task1()
        6 -> task1()
        else -> println("Неверный ввод данных")
    }
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

    println(reduceString(response))
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
        print("Вводить только числа - ")
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