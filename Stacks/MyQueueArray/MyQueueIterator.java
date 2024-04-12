package fr.pantheonsorbonne.cri.MyQueueArray;

import java.util.Iterator;

public class MyQueueIterator implements Iterator<String> {

    private String[] data;
    private int pointeur;

    public MyQueueIterator (String[] datas, int flag){
        this.data = datas;
        this.pointeur = flag;
    }

    @Override
    public boolean hasNext() {
        return this.pointeur > 0;
    }

    @Override
    public String next() {
        return this.data[--this.pointeur];
    }
}
