/** Represents main class
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class LibraryApp {

  public static void main(String[] args) { // Main method that executes
    Database library = new Database(50000); // Adding a database library for testing purposes
    PersonsDatabase persons = new PersonsDatabase(500); // Adding the borrowers database for testing purposes
    // Adding some books to be able to test the application
    Book book = new Book(
      new Key("QA", 76.8),
      "Charles Dickens",
      "Great Expectations",
      1860
    );
    library.insert(book);

    Book book2 = new Book(new Key("AD", 66.8), "John Steinbeck", "The Grapes of Wrath", 1920);
    library.insert(book2);

    Book book3 = new Book(new Key("AQ", 66.8), "George Orwell", "Nineteen Eighty-Four", 1984);
    library.insert(book3);

    Book book4 = new Book(new Key("AR", 66.8), "James Joyce", "Ulysses", 1960);
    library.insert(book4);

    Book book5 = new Book(new Key("AE", 66.8), "Vladimir Nabokov", "Lolita", 1978);
    library.insert(book5);

    // Adding some books to be able to test the application
    Borrower borrower = new Borrower("Jasmine", "4391 Lorem Street");
    persons.insert(borrower);

    Borrower borrower1 = new Borrower("Susan", "9428 Proin Rd.");
    persons.insert(borrower1);

    Borrower borrower2 = new Borrower("Barry", "2099 Lectus Street");
    persons.insert(borrower2);

    // Executing the view and the logic class the contains most of the code.
    Logic view = new Logic(library, persons);
  }
}
