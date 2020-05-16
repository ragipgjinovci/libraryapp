/** Represents library database
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class Database {
  /**
   * Collection of records
   */
  private Book[] base; 
  /**
   * int used to denote when a record not found
   */
  private int NOT_FOUND = -1;

  /** Constructor Database initializes the database
  * @param initial_size - the size of the database */
  public Database(int initial_size) {
    if (initial_size > 0) {
      base = new Book[initial_size];
    } else {
      base = new Book[1];
    }
  }
  /** findLocation is a helper method that searches base for a record
  * whose key is k. If found, the index of the record is returned,
  * else NOT_FOUND is returned. */
  private int findLocation(Key k) {
    int result = NOT_FOUND;
    boolean found = false;
    int i = 0;
    while (!found && i != base.length) {
      if (base[i] != null && base[i].getKey().equals(k)) {
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
  public Book find(Key k) {
    Book answer = null;
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
  public boolean insert(Book r) {
    boolean success = false;
    if (findLocation(r.getKey()) == NOT_FOUND) {
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
        Book[] temp = new Book[base.length * 2];
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

  /** setBorrowed borrows a specific book for the given owner
  * @param k - the key of the book to be borrowed
  * @param owner - the id of the owner that is borrowing the book
  * @return true, if the person borrows the book */
  public boolean setBorrowed(Key k, String owner) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].setBorrowed(owner);
      result = true;
    }
    return result;
  }

  /** removeBorrowed returns a specific book for the given owner
  * @param k - the key of the book to be returned
  * @param owner - the id of the owner that is returning the book
  * @return true, if the person returns the book */
  public boolean removeBorrowed(Key k, String owner) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].setBorrowed("");
      result = true;
    }
    return result;
  }

  /** delete removes a record in the database based on a key
  * @param k - the record's key (identification)
  * @return true, if record is found and deleted; return false otherwise */
  public boolean delete(Key k) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index] = null;
      result = true;
    }
    return result;
  }

  /** getBooks returns a list of all the books on the database
  * @return books */
  public Book[] getBooks() {
    return base;
  }
}
