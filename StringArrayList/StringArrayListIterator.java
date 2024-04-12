package StringArrayList;

import java.util.Iterator;

public class StringArrayListIterator implements Iterator<String> {
    String[] data = new String[100];

    int cpt = 0;

    public StringArrayListIterator(String[] data) {
        this.data = data;
    }

    @Override
    public String next() {
        return this.data[cpt++];
    }

    @Override
    public boolean hasNext() {
        if (cpt < data.length)
            return this.data[cpt] != null;
        return false;
    }    
}
