package ru.gravitana.homework.notebook

sealed interface Command {
    fun isValid(): Boolean
}

class Error(val error: String) : Command {
    override fun isValid(): Boolean {
        return true
    }
}

class Exit() : Command {
    override fun isValid(): Boolean {
        return true
    }
}

class Help() : Command {
    override fun isValid(): Boolean {
        return true
    }
}

class Show(val name: String) : Command {
    override fun isValid(): Boolean {
        return notebook[name] != null
    }
}

class All() : Command {
    override fun isValid(): Boolean {
        return true
    }
}

class AddPhone(val name: String, val phone: String) : Command {
    override fun isValid(): Boolean {
        return phone.matches(Regex("""\+?[0-9]+"""))
    }
}

class AddEmail(val name: String, val email: String) : Command {
    override fun isValid(): Boolean {
        return email.contains("@") && email.contains(".")
    }
}

fun readCommand(): Command {
    println("Введите команду или 'help' для подсказки")
    val userInput: String = readlnOrNull().toString()

    if (userInput.isBlank()) {
        return Error("Command is empty")
    }

    when (userInput) {
        "exit", "q" -> return Exit()
        "help" -> return Help()
//        "show" -> return Show()
        "all" -> return All()
    }

    if (!userInput.startsWith("add ") && !userInput.startsWith("show ")) {
        return Error("This line is not a Command")
    }

    val inputLines = userInput.split(" ")
    val name = inputLines[1]

    if (userInput.startsWith("show ")) {
        return Show(name)
    }

    val currentCommand = inputLines[2]
    val value = inputLines[3]

    return when (currentCommand) {
        "phone" -> AddPhone(name, value)
        "email" -> AddEmail(name, value)
        else -> Error("Unknown Command")
    }
}