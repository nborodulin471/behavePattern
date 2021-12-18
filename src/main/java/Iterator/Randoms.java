package Iterator;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {

    protected Random random;
    private int max;
    private int min;

    public Randoms(int min, int max) {
        random = new Random();
        this.max = max;
        this.min = min;
    }

    public int getRandomInts() {
        return random.nextInt(max + 1 - min) + min;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                // т.к мы генерируем случайное число, то оно будет всегда доступно
                return true;
            }

            @Override
            public Integer next() {
                return getRandomInts();
            }
        };
    }

    //...
}
