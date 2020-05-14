import java.util.UUID;

public class Borrower {
  private String id;
  private String name;
  private String address;
  private Key[] borrowed;

  public Borrower(String name, String address) {
    id = UUID.randomUUID().toString();
    this.name = name;
    this.address = address;
    borrowed = new Key[6];
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public void borrow(Key k) {
    for (int i = 0; i < borrowed.length; i++) {
      if (borrowed[i] == null) {
        borrowed[i] = k;
        break;
      }
    }
  }

  public void returnBook(Key k) {
    for (int i = 0; i < borrowed.length; i++) {
      if (borrowed[i] == k) {
        borrowed[i] = null;
        break;
      }
    }
  }

  public Key[] getBooks() {
    return borrowed;
  }
}
