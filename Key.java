/** Represents key model
 * @author Ragip Gjinovci
 * @version 1.0
*/
public class Key {
  /**
   * Letter code used for catalog number
   */
  private String letter_code;
  /**
   * Number code used for catalog number
   */
  private double number_code;

  /** Constructor Key initializes the key generator
  * @param letters - letters to be used in the catalog number
  * @param num - numbers to be used in the catalog number
  */
  public Key(String letters, double num) {
    letter_code = letters;
    number_code = num;
  }

  /** equals tests a given key if it is the correct one
  * @param c - key to be tested
  * @return true if the key is correct; return false if otherwise
  */
  public boolean equals(Key c) {
    String s = c.getLetterCode();
    double d = c.getNumberCode();
    return (s.equals(letter_code) && d == number_code);
  }

  /** getLetterCode gets the {@link Key#letter_code} of the catalog number
  * @return letter code
  */
  public String getLetterCode() {
    return letter_code;
  }

  /** getNumberCode gets the {@link Key#number_code} of the catalog number
  * @return number code
  */
  public double getNumberCode() {
    return number_code;
  }
}
