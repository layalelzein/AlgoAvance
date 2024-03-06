package FILO;

public class Noeud {
    
    String data;
    Noeud suivant;

    public Noeud(String data, Noeud suivant) {
        this.data=data;
        this.suivant=suivant;
    }

    public void setSuivant(Noeud n) {
        this.suivant = n;
    }

    public Noeud getSuivant() {
        return this.suivant;
    }

    public void setData(String s) {
        this.data = s;
    }

    public String getData() {
        return this.data;
    }
}