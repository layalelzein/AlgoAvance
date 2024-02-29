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
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (Couple cpl : data[i]) {
                    sb.append(cpl.toString()).append(", ");
                }
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Supprimer la virgule et l'espace en trop à la fin
        }
        sb.append("]");
        return sb.toString();
    }

    boolean add(String s){

        int hash = s.hashCode();
        int l = data.length;
        Couple cpl = new Couple(s,hash);
        int index = hash%l;

        if (data[index] == null) {
            data[index] = new LinkedList<>();
        }

        data[index].add(cpl);
        size++;
        return true;
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
