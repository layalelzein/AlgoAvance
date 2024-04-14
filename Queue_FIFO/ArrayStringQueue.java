package Queue_FIFO;

public class ArrayStringQueue {
    private String[] data;
    private int nb;

    public ArrayStringQueue() {
        this.data = new String[10];
        this.nb = 0;
    }

    void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            String[] tab2 = new String[2 * data.length];
            for (int i = 0; i < data.length; i++) {
                tab2[i] = data[i];
            }
            data = tab2;
        }
    }

    public boolean isEmptyTab() {
        return nb == 0;
    }

    public int size() {
        return nb;
    }

    public void enqueue(String item) {
        ensureCapacity(nb + 1);
        data[nb++] = item;
    }

    public String dequeue() {
        if (isEmptyTab()) {
            return null;
        }
        String removedElement = data[0];
        for (int i = 0; i < nb - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--nb] = null;
        return removedElement;
    }

    public String peek() {
        if (isEmptyTab()) {
            return null;
        }
        return data[0];
    }

    public void clear() {
        while(nb!=0){
            data[--nb] = null;
        }
    }
}
