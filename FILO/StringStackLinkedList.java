package FILO;

import java.util.Iterator;
import java.util.NoSuchElementException;

import StringArrayList.StringArrayListIterator;
import StringLinkedList.StringLinkedList;
import StringLinkedList.StringLinkedList.StringLinkedListIterator;

public class StringStackLinkedList {

    //Partie chainée
    Noeud debut;
    Noeud fin;

    public String toString() {
        String result = "";
        Noeud courant = this.debut;
        while (courant != null) {
            result += courant.getData() + " ";
            courant = courant.getSuivant();
        }
        return result;
    }

    //empiler un elem sur la pile
    void push(String s) {
        Noeud nv = new Noeud(s,null);
        if(this.fin == null){
            this.debut = nv;
            this.fin = nv;
            return;
        }
        Noeud c = this.fin;
        c.setSuivant(nv);
        this.fin = nv;
    }

    String peek() {
        if(this.fin == null){
            throw new NoSuchElementException("La pile est vide");
        }
        return this.fin.getData();
    }

    String poll() {
        String value = this.fin.getData();
        Noeud courant = this.debut;
        if(courant == null){
            throw new NoSuchElementException("La pile est vide");
        }
        if(courant.getSuivant()==null){
            this.debut = null;
            this.fin = null;
            return value;
        }
        while(courant.getSuivant().getSuivant() != null) {
            courant = courant.getSuivant();
        }
        courant.setSuivant(null);
        this.fin = courant;
        return value;
    }

    boolean isEmpty() {
        return this.fin == null;
    }

    public Iterator<String> iterator() {
        return new StringStackdLinkedListIterator(this.debut);
    }

    

    public static void main(String[] args) {
        StringStackLinkedList sS = new StringStackLinkedList();

        sS.push("lala");
        sS.push("keke");
        sS.push("nino");

        System.out.println("1 ère implémentation avec Liste Chaînée : ");

        // Utilisation de l'itérateur pour parcourir les éléments
        Iterator<String> iterator = sS.iterator();
        while (iterator.hasNext()) {
            String nextElement = iterator.next();
            System.out.println("Il y a cet élément : " + nextElement);
        }

        System.out.println("La pile est vide ? : " + sS.isEmpty());
        System.out.println("Contenu du tableau : " + sS.toString());
        System.out.println("Dernier élément sans l'enlever : " + sS.peek());
        System.out.println("Contenu du tableau : " + sS.toString());
        System.out.println("Dernier élément en enlevant : " + sS.poll());
        System.out.println("Contenu du tableau : " + sS.toString());

        System.out.println("La pile est vide ? : " + sS.isEmpty());

        for(int i=0; i<2; i++) {
            sS.poll();
        }
        
        System.out.println("Contenu du tableau : " + sS.toString());
        System.out.println("La pile est vide ? : " + sS.isEmpty());

        }

    public static class StringStackdLinkedListIterator implements Iterator<String> {
        private Noeud courant;
    
        public StringStackdLinkedListIterator(Noeud debut) {
            this.courant = debut;
        }
    
        @Override
        public boolean hasNext() {
            return courant != null;
        }
    
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String data = courant.getData();
            courant = courant.getSuivant();
            return data;
        }
    }
}