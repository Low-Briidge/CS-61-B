public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new Deque.ArrayDeque<>();
        for (int i = 0; i < word.length(); i++){
            res.addLast(word.charAt(i));
        }
        return res;
    }

    private boolean helper(Deque<Character> w) {
        return w.size() == 1 || w.removeFirst() == w.removeLast() && helper(w);
    }
    public boolean isPalindrome(String word) {
        Deque<Character> w = wordToDeque(word);

        return helper(w);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> w = wordToDeque(word);
        while (w.size() > 1) {
            if (!cc.equalChars(w.removeFirst(), w.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
