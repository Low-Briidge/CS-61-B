import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obj = new OffByOne();

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("12365456321"));
        assertTrue(palindrome.isPalindrome("abcba"));
    }

    @Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome("flake", obj));
    }

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
//    Uncomment this class once you've created your Palindrome class.
}
