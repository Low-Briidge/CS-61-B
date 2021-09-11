import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
public class TestArrayDequeGold {
    StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> ans = new ArrayDequeSolution<>();

    @Test
    public void randomTest() {
        for (int i = 0; i < 100; i++) {
            int rand = StdRandom.uniform(0, 8);
            if (rand == 0) {
                actual.addFirst(i);
                ans.addFirst(i);
                assertEquals("addFirst(" + i + ")", ans.get(0), actual.get(0));
            }
            else if (rand == 1) {
                actual.addLast(i);
                ans.addLast(i);
                assertEquals("addLast(" + i + ")", ans.getLast(), actual.get(actual.size() - 1));
            }
            else if (rand == 2) {
                if (ans.size() == 0)
                    continue;
                Integer x1 = actual.removeFirst();
                Integer x2 = ans.removeFirst();
                assertEquals("removeFirst()" + i, x2, x1);
            }
            else if (rand == 3){//call removeLast()
                if (ans.size() == 0)
                    continue;
                Integer x1 = actual.removeLast();
                Integer x2 = ans.removeFirst();
                System.out.println();
                assertEquals("removeLast()", x2, x1);
            }
            else if (rand == 4) {
                continue;
            }
            else if (rand == 5) {
                assertEquals("isEmpty()", ans.isEmpty(), actual.isEmpty());
            }
            else if (rand == 6) {
                assertEquals("size()", ans.size(), actual.size());
            }
            else if (rand == 7) {
                if (ans.size() < 0)
                    continue;
                int rand2 = StdRandom.uniform(0, ans.size());
                int x1 = actual.get(rand2);
                int x2 = ans.get(rand2);
                assertEquals("get(" + rand2 + ")", x2, x1);
            }
        }
    }
}
