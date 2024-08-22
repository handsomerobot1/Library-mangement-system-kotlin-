data class Member(
val memberId: Int,
val name: String,
val contactInfo: String,
val borrowedBooks: MutableSet<Int> = mutableSetOf()
)
fun borrowBook(books: MutableList<Book>, members: MutableList<Member>, bookId: Int, memberId: Int): Boolean {
    val book = books.find { it.bookId == bookId }
    val member = members.find { it.memberId == memberId }
    return if (book != null && member != null) {
        if (book.availability) {
            book.availability = false
            member.borrowedBooks.add(bookId)
            println("Member '${member.name}' has successfully borrowed '${book.title}'.")
            true
        } else {
            println("Sorry, '${book.title}' is currently unavailable.")
            false
        }
    } else {
        println("Invalid book ID or member ID.")
        false
    }
}
fun returnBook(books: MutableList<Book>, members: MutableList<Member>, bookId: Int, memberId: Int): Boolean {
    val book = books.find { it.bookId == bookId }
    val member = members.find { it.memberId == memberId }
    return if (book != null && member != null) {
        if (member.borrowedBooks.contains(bookId)) {
            book.availability = true
            member.borrowedBooks.remove(bookId)
            println("Member '${member.name}' has successfully returned '${book.title}'.")
            true
        } else {
            println("Book with ID $bookId was not borrowed by member '${member.name}'.")
            false
        }
    } else {
        println("Invalid book ID or member ID.")
        false
    }
}
fun viewBorrowedBooks(members: List<Member>, books: List<Book>, memberId: Int) {
    val member = members.find { it.memberId == memberId }
    if (member != null) {
        println("Books borrowed by '${member.name}':")
        member.borrowedBooks.forEach { bookId ->
            val book = books.find { it.bookId == bookId }
            if (book != null) {
                println("ID: ${book.bookId}, Title: ${book.title}, Author: ${book.author}, Genre: ${book.genre}")
            }
        }
    } else {
        println("Member with ID $memberId not found.")
    }
}
fun viewAvailableBooks(books: List<Book>) {
    val availableBooks = books.filter { it.availability }
    if (availableBooks.isNotEmpty()) {
        println("Available Books:")
        availableBooks.forEach { book ->
            println("ID: ${book.bookId}, Title: ${book.title}, Author: ${book.author}, Genre: ${book.genre}")
        }
    } else {
        println("No books are currently available.")
    }
}
fun viewMembers(members: List<Member>) {
    if (members.isNotEmpty()) {
        println("List of Members:")
        members.forEach { member ->
            println("ID: ${member.memberId}, Name: ${member.name}, Contact Info: ${member.contactInfo}")
        }
    } else {
        println("No members found.")
    }
}
