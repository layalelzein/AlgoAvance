package StringHashSet;

import java.util.LinkedList;

public class StringHashSet {

    LinkedList <Couple>[] data = new LinkedList[10];
    private int size = 0;
    static final double T = 0.7;    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    void grow() {
        if(size/data.length > T) {
            LinkedList <Couple>[] dataDouble = new LinkedList [2*data.length];
            for (int i = 0; i < data.length; i++) {
                dataDouble[i] = data[i];
            }
            data = dataDouble;
            System.out.println(data.length);
        } else {
            return;
        }
    }

    public static void main(String[] args) {
        StringHashSet sHS = new StringHashSet();
        
        sHS.grow();
    
    }
}
