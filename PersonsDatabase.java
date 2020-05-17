/** Represents persons database
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class PersonsDatabase {
  /**
   * Collection of records
   */
  private Borrower[] base;
  /**
   * int used to denote when a record not found
   */
  private int NOT_FOUND = -1;

  /** Constructor PersonsDatabase initializes the persons database
  * @param initial_size - the size of the database */
  public PersonsDatabase(int initial_size) {
    if (initial_size > 0) {
      base = new Borrower[initial_size];
    } else {
      base = new Borrower[1];
    }
  }

  /** findLocation is a helper method that searches base for a record
  * whose key is k. If found, the index of the record is returned,
  * else NOT_FOUND is returned.
  * @param k the key that will be searched
  * @return the location if its found; -1 otherwise
  */
  private int findLocation(String k) {
    int result = NOT_FOUND;
    boolean found = false;
    int i = 0;
    while (!found && i != base.length) {
      if (base[i] != null && base[i].getId().equals(k)) {
        found = true;
        result = i;
      } else {
        i = i + 1;
      }
    }
    return result;
  }

  /** find locates a record in the database based on a key
  * @param k - the key of the desired record
  * @return (the address of) the desired record;
  * return null if record not found. */
  public Borrower find(String k) {
    Borrower answer = null;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      answer = base[index];
    }
    return answer;
  }

  /** insert inserts a new record into the database.
  * @param r - the record
  * @return true, if record added; return false if record not added because
  * another record with the same key already exists in the database */
  public boolean insert(Borrower r) {
    boolean success = false;
    if (findLocation(r.getId()) == NOT_FOUND) {
      boolean found_empty_place = false;
      int i = 0;
      while (!found_empty_place && i != base.length) {
        if (base[i] == null) {
          found_empty_place = true;
        } else {
          i = i + 1;
        }
      }
      if (found_empty_place) {
        base[i] = r;
      } else {
        Borrower[] temp = new Borrower[base.length * 2];
        for (int j = 0; j != base.length; j = j + 1) {
          temp[j] = base[j];
        }
        temp[base.length] = r;
        base = temp;
      }
      success = true;
    }
    return success;
  }

  /** borrow borrows a book for this person
   * @param k the key of the book to be borrowed
   * @param id the id of the person that is borrowing the book
   * @return true if the book is borrowed; returns false if the book couldn't be borrowed
   */
  public boolean borrow(String k, Key id) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].borrow(id);
      result = true;
    }
    return result;
  }

  /** returnBook returns a borrowed book of this person
   * @param k the key of the book to be returned
   * @param id the id of the person that is returning the book
   * @return true if returned; false otherwise
   */
  public boolean returnBook(String k, Key id) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].returnBook(id);
      result = true;
    }
    return result;
  }

  /** delete removes a record in the database based on a key
  * @param k - the record's key (identification)
  * @return true, if record is found and deleted; return false otherwise */
  public boolean delete(String k) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index] = null;
      result = true;
    }
    return result;
  }
  
  /** getBorrowers gets the {@link PersonsDatabase#base} - list of all persons
   * @return returns the borrowers(persons)
   */
  public Borrower[] getBorrowers() {
    return base;
  }
}
