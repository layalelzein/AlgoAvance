package fr.pantheonsorbonne.cri.MyQueueArray;

import java.util.Iterator;

import fr.pantheonsorbonne.cri.MyArrayList.MyIterator;

public class MyQueueArray implements Iterable<String>{
    private int flag;
    private String[] data;

    public MyQueueArray() {
        this.data = new String[1];
        this.flag = 0;
    }

    private void ensureCapacity(int n) { 
        if (n <= data.length) {
            return;
        } 
        else {
            String[] tab2 = new String[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                tab2[i] = data[i];
            }
            data = tab2;
        }
    }
    
    public String poll() throws MyQueueEmptyException{ 
        if (flag == 0){
            throw new MyQueueEmptyException();
        }
        String res = data[--flag];
        return res;
    }

    public void push(String s) throws MyQueueEmptyException{
        ensureCapacity(flag + 1);
        data[flag] = s;
        flag++;
    }

    public String peek() throws MyQueueEmptyException{
        if (flag == 0){
            throw new MyQueueEmptyException();
        }
        return data[flag-1];
    }
    public String toString() { //O(n)
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.flag; i++) {
            sb.append(this.data[i]);
            if (i!= flag-1){
                sb.append(", ");
            }
            
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new MyQueueIterator(this.data, this.flag);
    }
}
