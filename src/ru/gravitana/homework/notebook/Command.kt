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

class Find(val value: String) : Command {
    override fun isValid(): Boolean {
        return value.isNotBlank()
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
        "all" -> return All()
    }

    if (userInput.startsWith("show ")) {
        val inputLines = userInput.split(" ")
        return if (inputLines.size != 2) {
            Error("This line is not a Command")
        } else {
            val name = inputLines[1]
            Show(name)
        }
    }

    if (userInput.startsWith("find ")) {
        val inputLines = userInput.split(" ")
        val findValue = inputLines[1]
        return Find(findValue)
    }

    if (!userInput.startsWith("add ")) {
        return Error("This line is not a Command")
    }

    val inputLines = userInput.split(" ")

    if (inputLines.size != 4) {
        return Error("This line is not a Command")
    }

    val name = inputLines[1]

    val currentCommand = inputLines[2]
    val value = inputLines[3]

    return when (currentCommand) {
        "phone" -> AddPhone(name, value)
        "email" -> AddEmail(name, value)
        else -> Error("Unknown Command")
    }
}