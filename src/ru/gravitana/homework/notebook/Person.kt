package ru.gravitana.homework.notebook

class Person(val name: String) {
    private val phones = mutableListOf<String>()
    private val emails = mutableListOf<String>()

    fun addPhone(phone: String) {
        if (phone.isNotBlank()) {
            phones.add(phone)
        }
    }

    fun addEmail(email: String) {
        if (email.isNotBlank()) {
            emails.add(email)
        }
    }

    fun getPhones(): MutableList<String> {
        return phones
    }

    fun getEmails(): MutableList<String> {
        return emails
    }

    override fun toString(): String {
        return "Person(name='$name', phones=$phones, emails=$emails)"
    }

}
