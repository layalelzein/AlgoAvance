package StringArrayList;

import java.util.Iterator;

public class StringArrayList implements Iterable<String> {

    //private Scanner scanner = new Scanner(System.in);
    String[] data = new String[100];
    int nb;

    void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            String[] tab2 = new String[2 * data.length];
            for (int i = 0; i < data.length; i++) {
                tab2[i] = data[i];
            }
            data = tab2;
        }
    }

    boolean addElement(String element) {
        ensureCapacity(nb + 1);
        if (nb < data.length) {
            data[nb] = element;
            nb++;
            return true;
        }
        return false;
    }

    void add(int index, String s) {
        ensureCapacity(nb + 1);
        for (int i = nb; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = s;
        nb++;
    }

    boolean contains(String element) {
        for (int i = 0; i < nb; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    int indexOf(String element) {
        for (int i = 0; i < nb; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    int size() {
        return nb;
    }

    void get(int i) {
        System.out.println(data[i]);
    }

    String toStr() {
        String string = "";
        for (int i = 0; i < nb; i++) {
                string += data[i] + " ";
        }
        return string;
    }

    boolean isEmpty() {
        return nb == 0;
    }

    String set(int index, String element) {
        String oldElement = data[index];
        data[index] = element;
        return oldElement;
    }

    String remove(int index) {
        String element = data[index];
        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[data.length - 1] = "";


        nb--;
        return element;

        /* mieux
        String removedElement = data[index];
    for (int i = index; i < size - 1; i++) {
        data[i] = data[i + 1];
    }
    data[--size] = null;
    return removedElement;
         */
    }

    public static void main(String[] args) {
        StringArrayList sAL = new StringArrayList();

        // Test de la méthode add
        sAL.add(0,"Premier");
        sAL.add(1,"Deuxième");
        sAL.add(2,"Troisième");

        // Test de la méthode toStr
        System.out.println("Contenu du tableau : " + sAL.toStr());

        // Test de la méthode get
        sAL.get(0); // Affiche "Premier"

        // Test de la méthode addElement
        boolean added = sAL.addElement("Quatrième");
        if (added) {
            System.out.println("Élément ajouté avec succès.");
        } else {
            System.out.println("Impossible d'ajouter l'élément.");
        }

        // Test de la méthode contains
        boolean contains = sAL.contains("Quatrième");
        if (contains) {
            System.out.println("L'élément 'Quatrième' est présent.");
        } else {
            System.out.println("L'élément 'Quatrième' n'est pas présent.");
        }

        // Test de la méthode indexOf
        int index = sAL.indexOf("Troisième");
        if (index != -1) {
            System.out.println("L'indice de 'Troisième' est : " + index);
        } else {
            System.out.println("L'élément 'Troisième' n'a pas été trouvé.");
        }

        // Test de la méthode isEmpty
        boolean empty = sAL.isEmpty();
        if (empty) {
            System.out.println("Le tableau est vide.");
        } else {
            System.out.println("Le tableau n'est pas vide.");
        }

        // Test de la méthode remove
        String removedElement = sAL.remove(1);
        System.out.println("Élément supprimé : " + removedElement);
        System.out.println("Contenu du tableau après suppression : " + sAL.toStr());

        // Test de la méthode set
        String oldElement = sAL.set(0, "NouveauPremier");
        System.out.println("Ancien élément à l'indice 0 : " + oldElement);
        System.out.println("Contenu du tableau après modification : " + sAL.toStr());

        // Test de la méthode size
        int size = sAL.size();
        System.out.println("Taille du tableau : " + size);

        // Utilisation de l'itérateur pour parcourir les éléments
        Iterator<String> iterator = sAL.iterator();

        System.out.println("Test de la classe StringArrayListIterator !");
        System.out.println("Contenu de l'objet l'un après l'autre : ");

        while (iterator.hasNext()) {
            String nextElement = iterator.next();
            System.out.println(nextElement);
        }
    }

    public Iterator<String> iterator() {
        return new StringArrayListIterator(this.data);
    }
}