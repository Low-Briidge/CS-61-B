import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> ans = new ArrayDequeSolution<>();

    @Test
    public void randomTest() {
        //addFirst
        for (int i = 0; i < 10; i++) {
            int rand = StdRandom.uniform(0, 100);
            ans.addFirst(rand);
            actual.addFirst(rand);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals("addFirst(" + ans.get(i) + ")", ans.get(i), actual.get(i));
        }

        //addLast
        for (int i = 0; i < 10; i++) {
            int rand = StdRandom.uniform(0, 100);
            ans.addLast(rand);
            actual.addLast(rand);
        }
        for (int i = 10; i < 20; i++) {
            assertEquals("addLast(" + ans.get(i) + ")", ans.get(i), actual.get(i));
        }

        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();
        //removeFirst
        for (int i = 0; i < 10; i++) {
            lst1.add(ans.removeFirst());
            lst2.add(actual.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            assertEquals("removeFirst()", lst1.get(i), lst2.get(i));
        }
        lst1.clear();
        lst2.clear();
        //removeLast
        for (int i = 0; i < 10; i++) {
            lst1.add(ans.removeLast());
            lst2.add(actual.removeLast());
            assertEquals("removeLast()", lst1.get(i), lst2.get(i));
        }
    }
}
