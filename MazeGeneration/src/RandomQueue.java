import java.util.ArrayList;

// 随机队列
public class RandomQueue<E> {

    private ArrayList<E> queue;

    public RandomQueue() {
        this.queue = new ArrayList<>();
    }

    public void add(E e) {
        queue.add(e);
    }

    public E remove() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("There's no element");
        }
        int randomIndex = (int) (Math.random() * queue.size());

        E randElement = queue.get(randomIndex);
        // queue中最后一个元素与随机元素交换位置
        queue.set(randomIndex, queue.get(queue.size() - 1));
        // 删除交换后的随机元素
        queue.remove(queue.size() - 1);
        return randElement;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
