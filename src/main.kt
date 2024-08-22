fun main() {
    if (!login()) {
        println("Invalid username or password. Exiting...")
        return
    }
    val books = mutableListOf(
        Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", true),
        Book(2, "To Kill a Mockingbird", "Harper Lee", "Fiction", false),
        Book(3, "1984", "George Orwell", "Dystopian", true),
        Book(4, "Pride and Prejudice", "Jane Austen", "Romance", true),
        Book(5, "The Catcher in the Rye", "J.D. Salinger", "Fiction", false),
        Book(6, "The Hobbit", "J.R.R. Tolkien", "Fantasy", true),
        Book(7, "Fahrenheit 451", "Ray Bradbury", "Science Fiction", true),
        Book(8, "Moby-Dick", "Herman Melville", "Adventure", false),
        Book(9, "War and Peace", "Leo Tolstoy", "Historical", true),
        Book(10, "The Odyssey", "Homer", "Epic", true),
        Book(11, "The Da Vinci Code", "Dan Brown", "Thriller", true),
        Book(12, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", false),
        Book(13, "Jane Eyre", "Charlotte Brontë", "Classic", true),
        Book(14, "The Alchemist", "Paulo Coelho", "Adventure", true),
        Book(15, "Brave New World", "Aldous Huxley", "Dystopian", true),
        Book(16, "The Shining", "Stephen King", "Horror", false),
        Book(17, "The Chronicles of Narnia", "C.S. Lewis", "Fantasy", true),
        Book(18, "Gone with the Wind", "Margaret Mitchell", "Historical Romance", true),
        Book(19, "Little Women", "Louisa May Alcott", "Fiction", false),
        Book(20, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science Fiction", true)
    )
    val members = mutableListOf(
        Member(1, "Alice Johnson", "alice@example.com"),
        Member(2, "Bob Smith", "bob@example.com"),
        Member(3, "Charlie Brown", "charlie@example.com"),
        Member(4, "Diana Prince", "diana@example.com"),
        Member(5, "Eve Adams", "eve@example.com"),
        Member(6, "Frank Castle", "frank@example.com"),
        Member(7, "Grace Kelly", "grace@example.com"),
        Member(8, "Henry Ford", "henry@example.com"),
        Member(9, "Ivy League", "ivy@example.com"),
        Member(10, "Jack Daniels", "jack@example.com")
    )
    while (true) {
        println("\nChoose an action: \n(1) Search \n(2) Borrow \n(3) Return \n(4) View Borrowed \n(5) View Available Books \n(6) View Members \n(7) Exit")
        when (readLine()?.trim()) {
            "1" -> {
                println("Search by (title/author/genre): ")
                val searchType = readLine()?.trim()?.toLowerCase()
                if (searchType in listOf("title", "author", "genre")) {
                    println("Enter search term: ")
                    val searchTerm = readLine()?.trim() ?: ""
                    val results = when (searchType) {
                        "title" -> searchByTitle(books, searchTerm)
                        "author" -> searchByAuthor(books, searchTerm)
                        "genre" -> searchByGenre(books, searchTerm)
                        else -> emptyList()
                    }
                    if (results.isNotEmpty()) {
                        println("Search Results:")
                        for (book in results) {
                            println("ID: ${book.bookId}, Title: ${book.title}, Author: ${book.author}, Genre: ${book.genre}, Available: ${book.availability}")
                        }
                    } else {
                        println("No books found matching the search criteria.")
                    }
                } else {
                    println("Invalid search type. Please enter 'title', 'author', or 'genre'.")
                }
            }
            "2" -> {
                println("Enter the ID of the book to borrow: ")
                val bookId = readLine()?.trim()?.toIntOrNull()
                println("Enter your member ID: ")
                val memberId = readLine()?.trim()?.toIntOrNull()
                if (bookId != null && memberId != null) {
                    borrowBook(books, members, bookId, memberId)
                } else {
                    println("Invalid book ID or member ID.")
                }
            }
            "3" -> {
                println("Enter the ID of the book to return: ")
                val bookId = readLine()?.trim()?.toIntOrNull()
                println("Enter your member ID: ")
                val memberId = readLine()?.trim()?.toIntOrNull()
                if (bookId != null && memberId != null) {
                    returnBook(books, members, bookId, memberId)
                } else {
                    println("Invalid book ID or member ID.")
                }
            }
            "4" -> {
                println("Enter your member ID to view borrowed books: ")
                val memberId = readLine()?.trim()?.toIntOrNull()
                if (memberId != null) {
                    viewBorrowedBooks(members, books, memberId)
                } else {
                    println("Invalid member ID.")
                }
            }
            "5" -> {
                viewAvailableBooks(books)
            }
            "6" -> {
                viewMembers(members)
            }
            "7", "0" -> {
                println("Exiting...")
                return
            }
            else -> {
                println("Invalid choice. Please select a valid option.")
            }
        }
    }
}