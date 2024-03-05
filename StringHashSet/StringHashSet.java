package StringHashSet;

import java.util.LinkedList;

public class StringHashSet {

    LinkedList <Couple>[] data;
    private int size = 0;
    static final double T = 0.7;

    public StringHashSet() {
        data = new LinkedList[4];
        // Initialiser chaque élément avec une nouvelle LinkedList
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }
    }

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

    void grow() {
        if ((double)size / data.length > T) {
            LinkedList<Couple>[] newData = new LinkedList[data.length * 2];
            for (int i = 0; i < newData.length; i++) {
                newData[i] = new LinkedList<>();
            }
            for (LinkedList<Couple> list : data) {
                if (list != null) {
                    for (Couple c : list) {
                        int newIndex = (c.hash % newData.length + newData.length) % newData.length;
                        newData[newIndex].add(c);
                    }
                }
            }
            data = newData;
        }
    }

    boolean add(String s){
        grow();
        int hash = s.hashCode();
        int l = data.length;
        Couple cpl = new Couple(s,hash);
        int index = (hash % l + l) % l; // Correction pour éviter l'indice négatif
        if (data[index] == null) {
            data[index] = new LinkedList<>();
        }
        data[index].add(cpl);
        size++;
        return true;
    }
    

    /*boolean contains(String S) {
        int hash = S.hashCode();
        int index = hash % data.length;
        if (data[index] != null) {
            LinkedList<Couple> couples = data[index];
            for (int i = 0; i < couples.size(); i++) {
                Couple cpl = couples.get(i);
                if (cpl.s.equals(S)) {
                    return true;
                }
            }
        }
        return false;
    }*/

    boolean contains(String S) {
        int hash = S.hashCode();
        int index = (hash % data.length + data.length) % data.length;
        LinkedList<Couple> bucket = data[index];
        for (Couple c : bucket) {
            if (c.s.equals(S)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StringHashSet shs = new StringHashSet();
        shs.add("hey");
        shs.add("hello");
        shs.add("world");
    
        for(int i = 0; i < 1000; i++) {
            shs.add("Element" + i);
        }
    
        // Mesurer le temps d'exécution de contains
        long startTime = System.nanoTime(); 
        boolean contient = shs.contains("Element500"); // Exemple de recherche
        long endTime = System.nanoTime(); 
        long duration = endTime - startTime;

        System.out.println("Temps d'exécution de contains: " + duration + " nanosecondes.");
    
        System.out.println("Contains 'hey': " + shs.contains("hey"));
        System.out.println("Contains 'hi': " + shs.contains("hi"));
    }
    
}