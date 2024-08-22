fun login(): Boolean {
    val username = "librarian"
    val password = "lib123"
    println("Please log in.")
    print("Username: ")
    val enteredUsername = readLine()?.trim()
    print("Password: ")
    val enteredPassword = readLine()?.trim()
    return enteredUsername == username && enteredPassword == password
}