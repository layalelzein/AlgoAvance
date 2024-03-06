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
        System.out.println("Contains 'hey': " + shs.contains("hey"));
        System.out.println("Contains 'hi': " + shs.contains("hi"));        

        StringHashSet shs1 = new StringHashSet();

        for(int i = 0; i < 1000; i++) {
            shs1.add("Element" + i);
        }

        StringHashSet shs2 = new StringHashSet();

        for(int i = 0; i < 100000; i++) {
            shs2.add("Element" + i);
        }

        StringHashSet shs3 = new StringHashSet();

        for(int i = 0; i < 1000000; i++) {
            shs3.add("Element" + i);
        }
    
        // Mesurer le temps d'exécution de contains
        long startTime = System.nanoTime(); 
        boolean contient1 = shs1.contains("Element500");
        long endTime = System.nanoTime(); 
        long duration = endTime - startTime;

        System.out.println("Temps d'exécution de contains: " + duration + " nanosecondes.");

        // Mesurer le temps d'exécution de contains
        long startTime2 = System.nanoTime(); 
        boolean contient2 = shs2.contains("Element50000");
        long endTime2 = System.nanoTime(); 
        long duration2 = endTime2 - startTime2;

        System.out.println("Temps d'exécution de contains2: " + duration2 + " nanosecondes.");

        // Mesurer le temps d'exécution de contains
        long startTime3 = System.nanoTime(); 
        boolean contient3 = shs3.contains("Element500000");
        long endTime3 = System.nanoTime(); 
        long duration3 = endTime3 - startTime3;

        System.out.println("Temps d'exécution de contains3: " + duration3 + " nanosecondes.");   
        
    }
    
}