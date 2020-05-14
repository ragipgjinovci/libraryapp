public class Testing {

  public static void main(String[] args) {
    Database library = new Database(50000);
    PersonsDatabase persons = new PersonsDatabase(500);
    // Starter books
    Book book = new Book(
      new Key("QA", 76.8),
      "Charles Dickens",
      "Great Expectations",
      1860
    );
    library.insert(book);

    Book book2 = new Book(new Key("AD", 66.8), "Charles Dick", "Great", 2020);
    library.insert(book2);
    Book book3 = new Book(new Key("AQ", 66.8), "Charles Dick", "Great", 2020);
    library.insert(book3);
    Book book4 = new Book(new Key("AR", 66.8), "Charles Dick", "Great", 2020);
    library.insert(book4);
    Book book5 = new Book(new Key("AE", 66.8), "Charles Dick", "Great", 2020);
    library.insert(book5);
    Book book6 = new Book(new Key("AV", 66.8), "Charles Dick", "Great", 2020);
    library.insert(book6);

    // Starter people
    Borrower ragip = new Borrower("Ragip", "City of angels");
    persons.insert(ragip);
    Borrower diti = new Borrower("Ardit", "Leshi i lesherave");
    persons.insert(diti);
    FinalVersion view = new FinalVersion(library, persons);
  }
}
