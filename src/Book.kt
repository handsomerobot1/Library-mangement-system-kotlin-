data class Book(
    val bookId: Int,
    val title: String,
    val author: String,
    val genre: String,
    var availability: Boolean
)
fun searchByTitle(books: List<Book>, title: String): List<Book> {
    return books.filter { it.title.contains(title, ignoreCase = true) }
}
fun searchByAuthor(books: List<Book>, author: String): List<Book> {
    return books.filter { it.author.contains(author, ignoreCase = true) }
}
fun searchByGenre(books: List<Book>, genre: String): List<Book> {
    return books.filter { it.genre.contains(genre, ignoreCase = true) }
}
fun borrowBook(books: MutableList<Book>, bookId: Int): Boolean {
    val book = books.find { it.bookId == bookId }
    return if (book != null && book.availability) {
        book.availability = false
        println("You have successfully borrowed '${book.title}'.")
        true
    } else if (book != null) {
        println("Sorry, '${book.title}' is currently unavailable.")
        false
    } else {
        println("Book with ID $bookId not found.")
        false
    }
}
fun returnBook(books: MutableList<Book>, bookId: Int): Boolean {
    val book = books.find { it.bookId == bookId }
    return if (book != null && !book.availability) {
        book.availability = true
        println("You have successfully returned '${book.title}'.")
        true
    } else if (book != null) {
        println("Book with ID $bookId was not borrowed.")
        false
    } else {
        println("Book with ID $bookId not found.")
        false
    }
}