package ru.gravitana.homework.notebook

val notebook = mutableMapOf<String, Person>()

fun printError(error: String) {
    println("Error! $error")
}

fun printHelp() {
    println("Список доступных команд:\n" +
            "exit - выход из программы\n" +
            "help - список доступных команд\n" +
            "show - поиск по имени\n" +
            "all - вывести весь список персон\n" +
            "add <Имя> phone <Номер телефона>\n" +
            "add <Имя> email <Адрес электронной почты>" +
            "\n"
    )
}

fun printPerson(person: Person?) {
    if (person == null) {
        println("Not initialized")
    } else {
        println(person)
    }
}

fun printAll() {
    for ((name, person) in notebook ) {
        printPerson(person)
    }
}

fun makePerson(name: String): Person {
    return notebook[name] ?: Person(name)
}

fun main() {
    var programGo = true
    var command: Command

    var currentPerson: Person? = null

    while (programGo) {
        command = readCommand()

        if (command.isValid()) {
            when (command) {
                is Error -> printError(command.error)
                is AddEmail -> {
                    currentPerson = makePerson(command.name)
                    currentPerson.addEmail(command.email)
                    notebook.put(command.name, currentPerson)
                }
                is AddPhone -> {
                    currentPerson = makePerson(command.name)
                    currentPerson.addPhone(command.phone)
                    notebook.put(command.name, currentPerson)
                }
                is Exit -> programGo = false
                is Show -> printPerson(currentPerson)
                is All -> printAll()
                else -> printHelp()
            }
        } else {
            printError("Argument is not correct")
        }
    }

    println("Программа завершена")
}
