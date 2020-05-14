import java.util.ArrayList;

public class PersonsDatabase {
  private Borrower[] base;
  private int NOT_FOUND = -1;
  private ArrayList persons;

  public PersonsDatabase(int initial_size) {
    if (initial_size > 0) {
      base = new Borrower[initial_size];
    } else {
      base = new Borrower[1];
    }
  }

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

  public Borrower find(String k) {
    Borrower answer = null;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      answer = base[index];
    }
    return answer;
  }

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

  public boolean borrow(String k, Key id) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].borrow(id);
      result = true;
    }
    return result;
  }

  public boolean returnBook(String k, Key id) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index].returnBook(id);
      result = true;
    }
    return result;
  }

  public boolean delete(String k) {
    boolean result = false;
    int index = findLocation(k);
    if (index != NOT_FOUND) {
      base[index] = null;
      result = true;
    }
    return result;
  }

  public Borrower[] getBorrowers() {
    return base;
  }
}
