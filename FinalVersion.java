import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FinalVersion {
  static int width = 900;
  static int height = 400;
  static Book[] books;
  static Borrower[] borrowers;
  static Database library;
  static PersonsDatabase persons;
  static int heightP = 0;
  static JComboBox personList;
  static JComboBox bookList;
  static Key clearRow;
  static ComboItem currentBorrower;
  static BookCombo currentBook;
  static JButton borrowBtn;
  static ArrayList<String> haveBooks = new ArrayList<String>();
  static ArrayList<Key> booksOwned = new ArrayList<Key>();
  static JLabel returnDate;

  public FinalVersion(Database library, PersonsDatabase persons) {
    this.library = library;
    this.persons = persons;
    this.books = library.getBooks();
    this.borrowers = persons.getBorrowers();
    execute();
  }

  public static void execute() {
    JFrame frame = new JFrame("Library Application");
    Panel panel = new Panel(books, borrowers, width, height, library, persons);
    JScrollPane jsp = new JScrollPane(panel);
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel.setPreferredSize(
      new Dimension(
        width,
        height + ((books.length / 10000) * 20) + ((borrowers.length / 100) * 20)
      )
    );
    panel.setLayout(null);
    addCurrentPerson(panel);
    addCurrentBook(panel);
    addComboBox(panel);
    addBookCombo(panel);
    addButtons(panel);
    addDeleteButtons(panel);
    addDeleteButtonsPerson(panel);
    addBorrowBtn(panel);
    frame.setSize(new Dimension(width + 35, height));
    frame.getContentPane().add(jsp);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public static void addDeleteButtons(JPanel panel) {
    for (int nr = 0; nr < books.length; nr++) {
      if (books[nr] != null) {
        if (books[nr].getOwner() != null) {
          booksOwned.add(books[nr].getKey());
        }
        heightP = 80 + 125 + ((nr) * 30);
        final Book nrB = books[nr];
        JButton deleteBtn = new JButton("Delete");
        Dimension size = deleteBtn.getPreferredSize();
        deleteBtn.setBounds(600, 125 + (nr * 30), size.width + 20, size.height);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(Color.decode("#dc3545"));
        deleteBtn.setBorder(null);
        deleteBtn.setFocusPainted(false);
        panel.add(deleteBtn);
        deleteBtn.addActionListener(
          new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              if (booksOwned.size() > 0) {
                for (int bO = 0; bO < booksOwned.size(); bO++) {
                  if (nrB.getKey() != booksOwned.get(bO)) {
                    panel.remove(deleteBtn);
                    library.delete(nrB.getKey());
                    clearRow = nrB.getKey();
                    panel.remove(bookList);
                    addBookCombo(panel);
                    panel.repaint();
                  } else {
                    JOptionPane.showMessageDialog(
                      null,
                      "This book hasn't been returned yet, therefore can not be deleted"
                    );
                  }
                }
              } else {
                library.delete(nrB.getKey());
                panel.remove(deleteBtn);
                clearRow = nrB.getKey();
                panel.remove(bookList);
                addBookCombo(panel);
                panel.repaint();
              }
            }
          }
        );
      }
    }
  }

  public static void addBorrowBtn(JPanel panel) {
    borrowBtn = new JButton("Borrow");
    Dimension size = borrowBtn.getPreferredSize();
    borrowBtn.setBounds(750, 130, size.width + 35, size.height);
    borrowBtn.setForeground(Color.WHITE);
    borrowBtn.setBorder(null);
    borrowBtn.setBackground(Color.decode("#007bff"));
    borrowBtn.setFocusPainted(false);
    panel.add(borrowBtn);
    Object currentBorrower = personList.getSelectedItem();
    String name = ((ComboItem) currentBorrower).getKey();
    String personId = ((ComboItem) currentBorrower).getValue();
    Object currentBook = bookList.getSelectedItem();
    Key bookId = ((BookCombo) currentBook).getValue();
    String title = ((BookCombo) currentBook).getKey();
    Key[] thisBooks = persons.find(personId).getBooks();
    if (thisBooks.length > 0) {
      for (int l = 0; l < thisBooks.length; l++) {
        if (bookId == thisBooks[l]) {
          borrowBtn.setBackground(Color.decode("#ffc107"));
          borrowBtn.setText("Return");
          returnDate = new JLabel();
          returnDate.setText(
            "Return date: " + library.find(bookId).getReturn()
          );
          Dimension sizeR = returnDate.getPreferredSize();
          returnDate.setBounds(750, 160, sizeR.width + 20, sizeR.height);
          panel.add(returnDate);
          panel.repaint();
        }
      }
    } else {
      borrowBtn.setText("Borrow");
      borrowBtn.setBackground(Color.decode("#007bff"));
      panel.remove(returnDate);
      panel.repaint();
    }

    borrowBtn.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          boolean found = false;
          if (thisBooks.length > 0) {
            for (int l = 0; l < thisBooks.length; l++) {
              if (bookId == thisBooks[l]) {
                found = true;

                int answer = JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to return: " + title
                );
                if (answer == 0) {
                  library.removeBorrowed(bookId, personId);
                  persons.returnBook(personId, bookId);
                  borrowBtn.setBackground(Color.decode("#007bff"));
                  borrowBtn.setText("Borrow");
                  haveBooks.remove(personId);
                  booksOwned.remove(bookId);
                  panel.remove(returnDate);
                }
              }
            }
          } else {
            int answer = JOptionPane.showConfirmDialog(
              null,
              "Are you sure you want to borrow: " + title
            );
            if (answer == 0) {
              library.setBorrowed(bookId, personId);
              persons.borrow(personId, bookId);
              haveBooks.add(personId);
              booksOwned.add(bookId);
              returnDate = new JLabel();
              returnDate.setText(
                "Return date: " + library.find(bookId).getReturn()
              );
              Dimension size = returnDate.getPreferredSize();
              returnDate.setBounds(750, 160, size.width + 20, size.height);
              panel.add(returnDate);
            }
          }
          if (!found) {
            int answer = JOptionPane.showConfirmDialog(
              null,
              "Are you sure you want to borrow: " + title
            );
            if (answer == 0) {
              borrowBtn.setBackground(Color.decode("#ffc107"));
              borrowBtn.setText("Return");
              library.setBorrowed(bookId, personId);
              persons.borrow(personId, bookId);
              haveBooks.add(personId);
              booksOwned.add(bookId);
              returnDate = new JLabel();
              returnDate.setText(
                "Return date: " + library.find(bookId).getReturn()
              );
              Dimension size = returnDate.getPreferredSize();
              returnDate.setBounds(750, 160, size.width + 20, size.height);
              panel.add(returnDate);
            }
          }
          panel.repaint();
          // Until here

        }
      }
    );
  }

  public static void addDeleteButtonsPerson(JPanel panel) {
    for (int nr = 0; nr < borrowers.length; nr++) {
      if (borrowers[nr] != null) {
        final Borrower nrB = borrowers[nr];
        if (nrB.getBooks().length == 0) {
          haveBooks.add(nrB.getId());
        }
        JButton deleteBtnP = new JButton("Delete");
        Dimension size = deleteBtnP.getPreferredSize();
        deleteBtnP.setBounds(
          600,
          heightP + 45 + (nr * 30),
          size.width + 20,
          size.height
        );
        deleteBtnP.setForeground(Color.WHITE);
        deleteBtnP.setBackground(Color.decode("#dc3545"));
        deleteBtnP.setBorder(null);
        deleteBtnP.setFocusPainted(false);
        panel.add(deleteBtnP);
        deleteBtnP.addActionListener(
          new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              if (haveBooks.size() > 0) {
                for (int b = 0; b < haveBooks.size(); b++) {
                  if (nrB.getId() != haveBooks.get(b)) {
                    persons.delete(nrB.getId());
                    panel.remove(deleteBtnP);
                    panel.remove(personList);
                    addComboBox(panel);
                    panel.repaint();
                  } else {
                    JOptionPane.showMessageDialog(
                      null,
                      nrB.getName() +
                      " hasn't returned some(a) book yet, therefore can not be deleted"
                    );
                  }
                }
              } else {
                persons.delete(nrB.getId());
                panel.remove(deleteBtnP);
                panel.remove(personList);
                addComboBox(panel);
                panel.repaint();
              }
            }
          }
        );
      }
    }
  }

  public static void addCurrentPerson(JPanel panel) {
    JLabel cPText = new JLabel("Current Person:");
    Dimension size = cPText.getPreferredSize();
    cPText.setBounds(750, 20, size.width + 20, size.height);
    panel.add(cPText);
  }

  public static void addCurrentBook(JPanel panel) {
    JLabel bPText = new JLabel("Choose Book:");
    Dimension size = bPText.getPreferredSize();
    bPText.setBounds(750, 70, size.width + 20, size.height);
    panel.add(bPText);
  }

  public static void addComboBox(JPanel panel) {
    personList = new JComboBox();
    for (int nr = 0; nr < persons.getBorrowers().length; nr++) {
      if (persons.getBorrowers()[nr] != null) {
        personList.addItem(
          new ComboItem(
            persons.getBorrowers()[nr].getName(),
            persons.getBorrowers()[nr].getId()
          )
        );
      }
    }
    Dimension size = personList.getPreferredSize();
    personList.setBounds(750, 40, 110, size.height);
    panel.add(personList);
    personList.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          panel.remove(borrowBtn);
          panel.remove(returnDate);
          addBorrowBtn(panel);
        }
      }
    );
  }

  public static void addBookCombo(JPanel panel) {
    bookList = new JComboBox();
    for (int nr = 0; nr < library.getBooks().length; nr++) {
      if (library.getBooks()[nr] != null) {
        bookList.addItem(
          new BookCombo(
            library.getBooks()[nr].getTitle(),
            library.getBooks()[nr].getKey()
          )
        );
      }
    }
    Dimension size = bookList.getPreferredSize();
    bookList.setBounds(750, 90, 110, size.height);
    panel.add(bookList);
    bookList.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          panel.remove(borrowBtn);
          if(returnDate != null) {
            panel.remove(returnDate);
          }
          addBorrowBtn(panel);
        }
      }
    );
  }

  public static void addButtons(JPanel panel) {
    JButton addBook = new JButton("Add a book");
    JButton addPerson = new JButton("Add a person");
    Dimension size = addBook.getPreferredSize();
    addBook.setBounds(465, 20, size.width, size.height);
    addBook.setForeground(Color.WHITE);
    addBook.setBackground(Color.decode("#1c7430"));
    addBook.setBorder(null);
    addBook.setFocusPainted(false);
    Dimension sizeB = addPerson.getPreferredSize();
    addPerson.setBounds(485 + size.width, 20, sizeB.width, sizeB.height);
    addPerson.setForeground(Color.WHITE);
    addPerson.setBackground(Color.decode("#1c7430"));
    addPerson.setBorder(null);
    addPerson.setFocusPainted(false);
    addBook.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          try {
            String bookKeyS = JOptionPane.showInputDialog("Book String Key");
            if (bookKeyS != null && !bookKeyS.isEmpty()) {
              String bookNr = JOptionPane.showInputDialog("Book catalog number");
              if (bookNr != null && !bookNr.isEmpty()) {
                String bookTitle = JOptionPane.showInputDialog("Book title");
                if (bookTitle != null && !bookTitle.isEmpty()) {
                  String bookAuthor = JOptionPane.showInputDialog("Book author");
                  if (bookAuthor != null && !bookAuthor.isEmpty()) {
                    String bookDate = JOptionPane.showInputDialog(
                      "Book publication date"
                    );
                    if (bookDate != null && !bookDate.isEmpty()) {
                      Book newBook = new Book(
                        new Key(bookKeyS, Double.parseDouble(bookNr)),
                        bookAuthor,
                        bookTitle,
                        Integer.parseInt(bookDate)
                      );
                      library.insert(newBook);
                      books = library.getBooks();
                      panel.remove(bookList);
                      addBookCombo(panel);
                      addDeleteButtons(panel);
                      height = height + 30;
                      panel.repaint();
                    }
                  }
                }
              }
            }
          }
          catch(NumberFormatException nrError) {
            JOptionPane.showMessageDialog(null, "Book could not be added, please check for errors.");
          }
        }
      }
    );
    // Add person
    addPerson.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          String personName = JOptionPane.showInputDialog("Name"); 
          if (!personName.matches("[0-9]+") && personName.length() > 2) { 
            if (personName != null && !personName.isEmpty()) {
              String personAddress = JOptionPane.showInputDialog("Address");
              if (personAddress != null && !personAddress.isEmpty()) {
                Borrower newBorrower = new Borrower(personName, personAddress);
                persons.insert(newBorrower);
                borrowers = persons.getBorrowers();
                height = height + 30;
                addDeleteButtonsPerson(panel);
                panel.remove(personList);
                addComboBox(panel);
                panel.repaint();
              }
            }
          } else {
            JOptionPane.showMessageDialog(null, "Name contains number or it's shorter than three characters, try again!");
          }
        }
      }
    );
    panel.add(addBook);
    panel.add(addPerson);
  }
}

class Panel extends JPanel {
  private int width;
  private int height;
  private Book[] books;
  private Borrower[] borrowers;
  private int heightP;
  private Database library;
  private PersonsDatabase persons;

  public Panel(
    Book[] books,
    Borrower[] borrowers,
    int width,
    int height,
    Database library,
    PersonsDatabase persons
  ) {
    this.books = books;
    this.width = width;
    this.height = height;
    this.borrowers = borrowers;
    this.library = library;
    this.persons = persons;
  }

  public void paintComponent(Graphics g) {
    Map<?, ?> desktopHints = (Map<?, ?>) Toolkit
      .getDefaultToolkit()
      .getDesktopProperty("awt.font.desktophints");
    Graphics2D g2d = (Graphics2D) g;
    if (desktopHints != null) {
      g2d.setRenderingHints(desktopHints);
    }
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height + 1000);
    g.setColor(Color.BLACK);
    g.setFont(new Font("Calibri", Font.BOLD, 24));
    g.drawString("Library applicaton", 20, 40);
    g.setFont(new Font("Calibri", Font.PLAIN, 18));
    g.drawString("List of books:", 20, 80);
    g.setFont(new Font("Calibri", Font.BOLD, 16));
    g.drawString("Title", 20, 110);
    g.drawString("Author", 300, 110);
    g.drawString("Publication Date", 450, 110);
    g.drawString("Action", 600, 110);
    g.drawLine(20, 120, 680, 120);
    g.setFont(new Font("Calibri", Font.PLAIN, 16));
    int bookNumber = 0;
    for (int nr = 0; nr < books.length; nr++) {
      if (books[nr] != null) {
        bookNumber++;
        addBook(
          g,
          books[nr].getTitle(),
          books[nr].getAuthor(),
          books[nr].getDate(),
          125 + (nr * 30)
        );
      }
    }
    // Persons
    heightP = 80 + 125 + ((bookNumber - 1) * 30);
    g.setFont(new Font("Calibri", Font.PLAIN, 18));
    g.drawString("List of persons:", 20, heightP);
    g.setFont(new Font("Calibri", Font.BOLD, 16));
    g.drawString("Name", 20, heightP + 30);
    g.drawString("Address", 120, heightP + 30);
    g.drawString("Borrowed Books", 300, heightP + 30);
    g.drawString("Action", 600, heightP + 30);
    g.drawLine(20, heightP + 40, 680, heightP + 40);
    g.setFont(new Font("Calibri", Font.PLAIN, 16));
    int personsNumber = 0;
    for (int nr = 0; nr < borrowers.length; nr++) {
      if (borrowers[nr] != null) {
        personsNumber++;
        addPerson(
          g,
          borrowers[nr].getName(),
          borrowers[nr].getAddress(),
          borrowers[nr].getBooks(),
          heightP + 40 + (nr * 30)
        );
      }
    }
  }

  public void addBook(
    Graphics pen,
    String title,
    String author,
    int date,
    int y
  ) {
    pen.drawString(title, 20, y + 20);
    pen.drawString(author, 300, y + 20);
    pen.drawString(date + "", 450, y + 20);
  }

  public void addPerson(
    Graphics pen,
    String name,
    String address,
    Key[] booksBorrowed,
    int y
  ) {
    pen.drawString(name, 20, y + 20);
    pen.drawString(address, 120, y + 20);
    for (int i = 0; i < booksBorrowed.length; i++) {
      if (booksBorrowed[i] != null) {
        String title = "";
        try {
          title = library.find(booksBorrowed[i]).getTitle();
        } catch (NullPointerException e) {}
        pen.drawString(title, 300 + (i * 50), y + 20);
      }
    }
  }
}

class ComboItem {
  private String key;
  private String value;

  public ComboItem(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return key;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}

class BookCombo {
  private String key;
  private Key value;

  public BookCombo(String key, Key value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return key;
  }

  public String getKey() {
    return key;
  }

  public Key getValue() {
    return value;
  }
}
