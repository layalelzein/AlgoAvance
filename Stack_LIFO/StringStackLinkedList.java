package Stack_LIFO;

import java.util.Iterator;
import java.util.NoSuchElementException;

import StringArrayList.StringArrayListIterator;
import StringLinkedList.StringLinkedList;
import StringLinkedList.StringLinkedList.StringLinkedListIterator;

public class StringStackLinkedList {

    //Partie chainée
    Couche top;

    public String toString() {
        String result = "";
        Couche courant = this.top;
        while (courant != null) {
            result += courant.getData() + " ";
            courant = courant.getBottom();
        }
        return result;
    }

    boolean isEmpty() {
        return this.top == null;
    }

    // retourne l'élément au sommet de la pile sans l'enlever
    String peek() {
        if(this.top == null){
            throw new NoSuchElementException("La pile est vide");
        }
        return this.top.getData();
    }
    
    //retourne l'élément au sommet de la pile en l'enlevant
    String poll() {
        if(this.top == null){
            throw new NoSuchElementException("La pile est vide");
        }
        String removedElement = this.top.getData();
        this.top = this.top.getBottom();
        return removedElement;
    }

    //empiler un elem sur la pile
    void push(String s) {
        if(this.top == null){
            this.top = new Couche(s, null);
        } else {
            this.top = new Couche(s, this.top);        
        }
    }

    public Iterator<String> iterator() {
        return new StringStackdLinkedListIterator(this.top);
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
        private Couche courant;
    
        public StringStackdLinkedListIterator(Couche top) {
            this.courant = top;
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
            courant = courant.getBottom();
            return data;
        }
    }
}