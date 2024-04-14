package Queue_FIFO;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStringQueue {
    private Node first;
    private Node last;

    public LinkedStringQueue() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public int size() {
        int cpt = 0;
        Node courant = this.first;
        if(isEmpty())
            return 0;
        while (courant != null) {
            cpt++;
            courant = courant.getNext();
        }
        return cpt;
    }

    // Méthode pour ajouter un élément à la fin de la file
    public void enqueue(String item) {
        Node newNode = new Node(item, null);
        if (isEmpty()) {
            this.last = newNode;
            this.first = this.last;
            return;
        }
        this.last.setNext(newNode);
        this.last = newNode;
    }

    // Méthode pour retirer et retourner l'élément en tête de la file
    public String dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("La file est vide");
        }
        String removedElement = this.first.getData();
        this.first = this.first.getNext();
        return removedElement;
    }

    // Méthode pour retourner l'élément en tête de la file sans le retirer
    public String peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La file est vide");
        }
        return this.first.getData();
    }
    
    // Méthode pour vider la file
    public void clear() {
        this.first = null;
        this.last = this.first;
    }

    public Iterator<String> iterator() {
        return new StringQueueLinkedListIterator(this.first);
    }

public class StringQueueLinkedListIterator implements Iterator<String> {
    Node courant;

    public StringQueueLinkedListIterator(Node first) {
        this.courant = first;
    }

    @Override
    public boolean hasNext() {
        return this.courant != null;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Il n'y a plus d'éléments");
        }
        String res = this.courant.getData();
        this.courant = this.courant.getNext();
        return res;
    }
    }
}
