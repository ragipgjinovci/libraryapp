import java.util.UUID; // Package that helps with the unique id's

/** Represents borrower model
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class Borrower {
  /**
   * Id of the borrower
   */
  private String id;
  /**
   * Name of the borrower
   */
  private String name;
  /**
   * Address of the borrower
   */
  private String address; 
  /**
   * List of books that this person borrowed
   */
  private Key[] borrowed;

  /** Constructor Borrower initializes the borrower class
  * @param name - sets the name for the borrower
  * @param address - sets the address for the borrower
  */
  public Borrower(String name, String address) {
    id = UUID.randomUUID().toString();
    this.name = name;
    this.address = address;
    borrowed = new Key[6];
  }

  /** getId gets the {@link Borrower#id} of the borrower
  * @return id of the borrower */
  public String getId() {
    return id;
  }

  /** getName gets the {@link Borrower#name} of the borrower
  * @return name of the borrower */
  public String getName() {
    return name;
  }

  /** getAddress gets the {@link Borrower#address} of the borrower
  * @return address of the borrower */
  public String getAddress() {
    return address;
  }

  /** borrow borrows the specific book for this person
  * @param k key of the book to be borrowed */
  public void borrow(Key k) {
    for (int i = 0; i < borrowed.length; i++) {
      if (borrowed[i] == null) {
        borrowed[i] = k;
        break;
      }
    }
  }

  /** returnBook returns the specific book for this person
  * @param k key of the book to be returned */
  public void returnBook(Key k) {
    for (int i = 0; i < borrowed.length; i++) {
      if (borrowed[i] == k) {
        borrowed[i] = null;
        break;
      }
    }
  }
  /** getBooks gets all the books {@link Borrower#borrowed} of this person
  * @return borrowed books of this person */
  public Key[] getBooks() {
    return borrowed;
  }
}
