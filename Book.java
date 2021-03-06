import java.text.SimpleDateFormat; // Date formation package
import java.util.Calendar; // Calendar to help with the date
import java.util.GregorianCalendar; // Another calendar to help with the date

/** Represents book model
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class Book {
  /**
   * Catalog number of the book
   */
  private Key catalog_number; 
  /**
   * Title of the book
   */
  private String title; 
  /**
   * Author of the book
   */
  private String author; 
  /**
   * Publication date of the book
   */
  private int publication_date; 
  /**
   * Owner id of the book
   */
  private String owned_by; 
  /**
   * Return date for the book when it is borrowed
   */
  private String return_date; 

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

  /** getKey gets the {@link Book#catalog_number} of the book
   * @return returns the catalog number
   */
  public Key getKey() {
    return catalog_number;
  }

  /** getTitle gets the {@link Book#title} of the book
   * @return returns the title
   */
  public String getTitle() {
    return title;
  }

  /** getAuthor gets the {@link Book#author} of the book
   * @return returns the author
   */
  public String getAuthor() {
    return author;
  }

  /** getDate gets the {@link Book#publication_date} of the book
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

  /** getOwner gets the {@link Book#owned_by} - owner of this book
   * @return returns the owner
   */
  public String getOwner() {
    return owned_by;
  }

  /** getReturn gets the {@link Book#return_date} for this book
   * @return returns the return date
   */
  public String getReturn() {
    return return_date;
  }
}
