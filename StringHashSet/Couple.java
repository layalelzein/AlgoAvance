package StringHashSet;

public class Couple {

    public Couple(String s, int hash) {
        this.s = s;
        this.hash = hash;
    }

    String s;
    int hash;

    @Override
    public String toString() {
        return "(" + s + ", " + hash + ")";
    }
     
 }