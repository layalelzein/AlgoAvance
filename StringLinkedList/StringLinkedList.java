package StringLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringLinkedList implements Iterable<String> {

    //Référence du premier noeud de la liste
    Noeud debut;

    public String toString() {
        String result = "";
        Noeud courant = this.debut;
        while (courant != null) {
            result += courant.getData() + " ";
            courant = courant.getSuivant();
        }
        return result;
    }

    boolean addElement(String s) {
        Noeud newNode = new Noeud(s, null);

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
            // this.debut = new Noeud(data, this.debut);
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

        if (courant == null || courant.getSuivant() == null){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        newNode.setSuivant(courant.getSuivant());
        courant.setSuivant(newNode);
    }

    //     if (this.debut == null) {
        //  Si la liste est vide, créer un nouveau nœud et le définir comme le début de la liste
        // if (index != 0) {
        //     throw new IndexOutOfBoundsException("Index is out of range");
        // }
        // this.debut = new Noeud(data, null);
        // return;
    // }


    //     Noeud courant = this.debut;
    //     int currentIndex = 1;

    //     while (courant.getSuivant() != null && currentIndex < index) {
    //         courant = courant.getSuivant();
    //         currentIndex++;
    //     }

    //     courant.setSuivant(new Noeud(data, courant.getSuivant()));


    void addFirst(String s) {
        this.debut = new Noeud(s, this.debut);
    }

    //Je fais revenir le pointeur de référence à 0 et donc le pointeur du noeud est null
    void clear() {
        this.debut = null;
    }

    boolean contains(String s) {
        Noeud courant = this.debut;
        while (courant != null) {
            if (courant.getData().equals(s)) {
                return true;
            } else
                courant = courant.getSuivant();
        }
        return false;
    }

    String get(int index) {
        Noeud courant = this.debut;
        int currentIndex = 0;

        if (courant == null || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        while (courant != null && currentIndex < index) {
            courant = courant.getSuivant();
            currentIndex++;
        }
        
        if(courant == null)
            return courant.getData();
        else
            throw new IndexOutOfBoundsException("Index out of bounds");
    }

    String getFirst() {
        if (debut == null) {
            throw new NoSuchElementException("List is empty");
        }
        
        return this.debut.getData();
    }

    String getLast() {
        Noeud courant = this.debut;
        if (courant == null) {
            throw new NoSuchElementException("List is empty");
        }
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
        Noeud nv = new Noeud(s, null);

        if(courant == null) {
            this.debut = nv;
            return true;
        }
        while (courant.getSuivant() != null) {
            courant = courant.getSuivant();
        }
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
        if(this.debut == null) {
            this.debut = new Noeud(s, null);
            return;
        }
        
        this.debut = new Noeud(s, this.debut);
    }


    //remove le 1er element et retourne sa data
    String remove() {
        if (debut == null) {
            return null;
        }
        String data = this.debut.getData();
        
        this.debut = this.debut.getSuivant();
        return data;
    }

    String removeLast() {
        if (this.debut == null) {
            return null;
        }
        if (this.debut.getSuivant() == null) {
            String data = this.debut.getData();
            this.debut = null;
            return data;
        }
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
        if(index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds");
        if(courant == null) {
            throw new NoSuchElementException("List is empty");
        }
        
        if (index == 0) {
            this.debut = courant.getSuivant();
            return courant.getData();
        }
        while (currentIndex++ < index - 1) {
            courant = courant.getSuivant();
        }
        if (courant == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        String data = courant.getSuivant().getData();
        courant.setSuivant(courant.getSuivant().getSuivant());
        return data;
    }

    //remplacer l'elem à la position spécifiée dans cette liste par l'elem spécifié
    String set(int index, String s) {
        Noeud courant = this.debut;
        int currentIndex = 0;
        if (index < 0 || debut == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        while (currentIndex++ < index && courant != null) {
            courant = courant.getSuivant();
        }
        if (courant == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
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
    //     int size = 0;
    // Noeud courant = debut;
    
    // while (courant != null) {
    //     size++;
    //     courant = courant.getSuivant();
    // }
    
    // return size;
    }

    // public String removeDLL(int index) {
    //     Noeud courant = this.debut;
    //     int cpt = 0;
    //     String removedData = null;
    //     if (index < 0 ) {
    //         throw new IndexOutOfBoundsException("Index is out of bounds");
    //     }
    //     if(debut == null) {
    //         throw new NoSuchElementException("List is empty");
    //     }

    //     // Si on supprime le premier élément
    //     if (index == 0) {
    //         removedData = courant.getData();
            
    //         if (courant.getSuivant() != null) {
    //             this.debut = courant.getSuivant();
    //             this.debut.setPrecedent(null);
    //         } else {
    //             this.debut = null; // La liste est maintenant vide
    //         }
    //         return removedData;
    //     }
    
    //     while (cpt < index - 1 && courant != null) {
    //         courant = courant.suivant;
    //         cpt++;
    //     }
    
    //     // Vérifier si l'index est valide
    //     if (courant.getSuivant() == null || courant == null) {
    //         throw new IndexOutOfBoundsException("Index is out of bounds");
    //     }
    
    //     // Supprimer le nœud à l'index spécifié
    //     removedData = courant.getSuivant().getData();
    //     Noeud suivant = courant.getSuivant().getSuivant();
    //     courant.setSuivant(suivant);
    //     if (suivant != null) {
    //         suivant.setPrecedent(courant);
    //     } else {
    //         fin = courant; // Mise à jour de fin si le dernier nœud est supprimé
    //     }
        
    //     return removedData;
    // }
    
    

    public static void main(String[] args) {
        StringLinkedList sLL = new StringLinkedList();

        sLL.addElement("ok");
        sLL.addElement("fazer");
        sLL.add(1, "Troisième");
        sLL.add(3, "nino");
        System.out.println("Contenu du tableau : " + sLL.toString());
        System.out.println("Index 1 : " + sLL.get(3));
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