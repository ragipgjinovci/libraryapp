import java.text.SimpleDateFormat; // Date formation package
import java.util.Calendar; // Calendar to help with the date
import java.util.GregorianCalendar; // Another calendar to help with the date

public class Book {
  private Key catalog_number; // Catalog number of the book
  private String title; // Title of the book
  private String author; // Author of the book
  private int publication_date; // Publication date of the book
  private String owned_by; // Owner id of the book
  private String return_date; // Return date for the book when it is borrowed

  /** Constructor Book initializes the book class
  * @param num - catalog number for the book
  * @param a - sets the author of the book
  * @param t - sets the title of the book
  * @param date - sets the publication date of the book
  */
  public Book(Key num, String a, String t, int date) {
    catalog_number = num;
    title = t;
    author = a;
    publication_date = date;
  }

  /** getKey gets the catalog number of the book
   * @return returns the catalog number
   */
  public Key getKey() {
    return catalog_number;
  }

  /** getTitle gets the title of the book
   * @return returns the title
   */
  public String getTitle() {
    return title;
  }

  /** getAuthor gets the author of the book
   * @return returns the author
   */
  public String getAuthor() {
    return author;
  }

  /** getDate gets the publication date of the book
   * @return returns the publication date
   */
  public int getDate() {
    return publication_date;
  }

  /** setBorrowed sets the owner of this book and the return date
   * @param k the id of the owner that is borrowing this book
   */
  public void setBorrowed(String k) {
    owned_by = k;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_MONTH, +10);
    return_date = sdf.format(calendar.getTime());
  }

  /** getOwner gets the owner of this book
   * @return returns the owner
   */
  public String getOwner() {
    return owned_by;
  }

  /** getReturn gets the return date for this book
   * @return returns the return date
   */
  public String getReturn() {
    return return_date;
  }
}
