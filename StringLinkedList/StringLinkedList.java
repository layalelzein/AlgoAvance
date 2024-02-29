package StringLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringLinkedList implements Iterable<String> {

    //Référence du premier noeud de la liste
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

    boolean addElement(String data) {
        Noeud newNode = new Noeud(data, null);

        if (this.debut == null) {
            // Si la liste est vide, le nouveau nœud devient le premier nœud
            this.debut = newNode;
        } else {
            Noeud courant = this.debut;
            while (courant.getSuivant() != null) {
                courant = courant.getSuivant();
            }
            courant.setSuivant(newNode);
        }
        return true;
    }

    /*
    boolean add(String contenu) {
        Noeud nouveauNoeud = new Noeud(contenu, null);
        if (premier == null) {
            premier = nouveauNoeud;
            dernier = premier;
        } else {
            dernier.setNextNoeud(nouveauNoeud);
            dernier = nouveauNoeud;
        }
        return true;
    }
     */
    

    void add(int index, String data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }

        Noeud newNode = new Noeud(data, null);

        if (index == 0) {
            newNode.setSuivant(this.debut);
            this.debut = newNode;
            return;
        }

        Noeud courant = this.debut;
        int currentIndex = 0;

        while (courant != null && currentIndex < index - 1) {
            courant = courant.getSuivant();
            currentIndex++;
        }

        if (courant == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        newNode.setSuivant(courant.getSuivant());
        courant.setSuivant(newNode);
    }

    void addFirst(String data) {
        this.debut = new Noeud(data, this.debut);
    }

    //Je fais revenir le pointeur de référence à 0 et donc le pointeur du noeud est null
    void clear() {
        this.debut = null;
    }

    boolean contains(String data) {
        Noeud courant = this.debut;
        while (courant != null) {
            if (data == courant.getData()) {
                return true;
            } else
                courant = courant.getSuivant();
        }
        return false;
    }

    String get(int index) {
        Noeud courant = this.debut;
        int currentIndex = 0;

        if (courant == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        while (courant != null && currentIndex < index) {
            courant = courant.getSuivant();
            currentIndex++;
        }

        return courant.getData();
    }

    String getFirst() {
        Noeud courant = this.debut;
        return courant.getData();
    }

    String getLast() {
        Noeud courant = this.debut;
        while (courant.getSuivant() != null) {
            courant = courant.getSuivant();
        }

        return courant.getData();
    }

    @Override
    public Iterator<String> iterator() {
        return new StringLinkedListIterator(debut);
    }

    boolean offer(String s) {
        Noeud courant = this.debut;
        while (courant.getSuivant() != null) {
            courant = courant.getSuivant();
        }
        Noeud nv = new Noeud(s, null);
        courant.setSuivant(nv);
        return true;
    }

    //récupérer et supprimer le premier elem de la liste
    String poll() {
        if (this.debut == null) {
            return null;
        }
        String data = this.debut.getData();
        this.debut = this.debut.getSuivant(); // supprime l'élém du début en le remplaçant par son suivant
        return data;
    }

    //empiler un elem sur la pile
    void push(String s) {
        Noeud nv = new Noeud(s, this.debut);
        this.debut = nv;
    }


    //remove le 1er element et retourne sa data
    String remove() {
        String data = this.debut.getData();
        Noeud newFirst = this.debut.getSuivant();
        this.debut = newFirst;
        return data;
    }

    String removeLast() {
        Noeud courant = this.debut;
        while (courant.getSuivant().getSuivant() != null) {
            courant = courant.getSuivant();
        }

        String data = courant.getSuivant().getData();
        courant.setSuivant(null);
        return data;
    }

    String remove(int index) {
        int currentIndex = 0;
        Noeud courant = this.debut;
        if (index == 0) {
            this.debut = courant.getSuivant();
            return courant.getData();
        }
        while (currentIndex++ < index - 1) {
            courant = courant.getSuivant();
        }
        String data = courant.getSuivant().getData();
        courant.setSuivant(courant.getSuivant().getSuivant());
        return data;
    }

    //remplacer l'elem à la position spécifiée dans cette liste par l'elem spécifié
    String set(int index, String s) {
        Noeud courant = this.debut;
        int currentIndex = 0;
        while (currentIndex++ < index) {
            courant = courant.getSuivant();
        }
        String data = courant.getData();
        courant.setData(s);
        return data;
    }

    int size() {
        int size = 1;
        Noeud courant = this.debut;
        while (courant.getSuivant() != null) {
            size++;
            courant = courant.getSuivant();
        }
        return size;
    }

    public static void main(String[] args) {
        StringLinkedList sLL = new StringLinkedList();

        sLL.addElement("ok");
        sLL.addElement("fazer");
        sLL.add(1, "Troisième");
        sLL.add(3, "nino");
        System.out.println("Contenu du tableau : " + sLL.toString());
        System.out.println("Index 1 : " + sLL.get(1));
        System.out.println("Premier élément : " + sLL.getFirst());
        System.out.println("Dernier élément : " + sLL.getLast());
        System.out.println("Contenu supp par index du tableau : " + sLL.remove(0));
        System.out.println("Contenu supp du tableau : " + sLL.remove());
        System.out.println("ContenuLast supp du tableau : " + sLL.removeLast());
        System.out.println("Contenu du tableau : " + sLL.toString());

        sLL.clear();
        sLL.addElement("Nino");
        if (sLL.contains("Nino"))
            System.out.println("Oui il est contenu : " + sLL.toString());
        sLL.offer("FAZ");
        sLL.push("BON");
        System.out.println("Contenu remplacé : " + sLL.set(2, "FAZER"));
        System.out.println("Contenu du tableau : " + sLL.toString());

        // Utilisation de l'itérateur pour parcourir les éléments
        Iterator<String> iterator = sLL.iterator();
        while (iterator.hasNext()) {
            String nextElement = iterator.next();
            System.out.println("Il y a cet élément : " + nextElement);
        }

        System.out.println("Contenu supp avec poll du tableau : " + sLL.poll());
        System.out.println("Nombre d'éléments du tableau : " + sLL.size());
    }

    public class StringLinkedListIterator implements Iterator<String> {
        private Noeud courant;

        public StringLinkedListIterator(Noeud debut) {
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