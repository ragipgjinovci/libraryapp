import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book {
  private Key catalog_number;
  private String title;
  private String author;
  private int publication_date;
  private String owned_by;
  private String return_date;
  private int no_borrowed;

  public Book(Key num, String a, String t, int date) {
    catalog_number = num;
    title = t;
    author = a;
    publication_date = date;
  }

  public Key getKey() {
    return catalog_number;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getDate() {
    return publication_date;
  }

  public void setBorrowed(String k) {
    owned_by = k;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_MONTH, +10);
    return_date = sdf.format(calendar.getTime());
  }

  public String getOwner() {
    return owned_by;
  }

  public String getReturn() {
    return return_date;
  }
}
