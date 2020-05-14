public class Key {
  private String letter_code;
  private double number_code;

  public Key(String letters, double num) {
    letter_code = letters;
    number_code = num;
  }

  public boolean equals(Key c) {
    String s = c.getLetterCode();
    double d = c.getNumberCode();
    return (s.equals(letter_code) && d == number_code);
  }

  public String getLetterCode() {
    return letter_code;
  }

  public double getNumberCode() {
    return number_code;
  }
}
