import java.util.LinkedList;

public class LinkRandomQueue<E> {

    private LinkedList<E> queue;

    public LinkRandomQueue() {
        this.queue = new LinkedList<>();
    }

    public void add(E e) {
        if (Math.random() < 0.5) {
            queue.addFirst(e);
        } else {
            queue.addLast(e);
        }
    }

    public E remove() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("There's no element");
        }

        if (Math.random() < 0.5) {
            return queue.removeFirst();
        } else {
            return queue.removeLast();
        }
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
}
