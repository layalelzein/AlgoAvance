package StringArrayList;

import java.util.Iterator;
import java.util.Random;

public class StringArrayList implements Iterable<String> {

    //private Scanner scanner = new Scanner(System.in);
    String[] data = new String[100];
    int nb;

    String toStr() {
        String string = "";
        for (int i = 0; i < nb; i++) {
            if (data[i] == null)
                return string;
            string += data[i] + " ";
        }
        return string;
    }

    void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            String[] tab2 = new String[2 * data.length];
            for (int i = 0; i < data.length; i++) {
                tab2[i] = data[i];
            }
            data = tab2;
        }
    }

    //Une fois que le tableau il est plein, on double la taille du tableau
    boolean addElement(String element) {
        ensureCapacity(nb + 1);
        if (nb < data.length) {
            data[nb] = element;
            nb++;
            return true;
        }
        return false;
    }
    // Mieux
    // boolean addElement(String element) {
    //     ensureCapacity(nb + 1);
    //     data[nb++] = element;    
    //     return true;
    // }

    void add(int index, String element) {
        if (index < 0 || index > nb) {
            throw new IndexOutOfBoundsException("Index: " + index + ", nb: " + nb);
        }
        ensureCapacity(nb + 1);
        for (int i = nb; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
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

    String get(int index) {
        if (index < 0 || index >= nb) {
            throw new IndexOutOfBoundsException("Index: " + index + ", nb: " + nb);
        }
        return data[index];
    }

    int indexOf(String element) {
        for (int i = 0; i < nb; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    boolean isEmpty() {
        return nb == 0;
    }

    String remove(int index) {
        if (index < 0 || index > nb) {
            throw new IndexOutOfBoundsException("Index: " + index + ", nb: " + nb);
        }
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

    String set(int index, String element) {
        String oldElement = data[index];
        data[index] = element;
        return oldElement;
    }

    int size() {
        return nb;
    }

    public Iterator<String> iterator() {
        return new StringArrayListIterator(this.data);
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

        int[] iterations = { 10000, 20000, 30000, 40000, 50000, 60000,70000,80000,90000,100000};

        for (int iter : iterations) {
            // Création d'une nouvelle instance de la classe StringArrayList
            StringArrayList list = new StringArrayList();

            // Mesure du temps d'exécution moyen de la méthode add
            long addTotalDuration = 0;

            // Mesure du temps d'exécution moyen de la méthode contains
            long containsTotalDuration = 0;

            // Ajout de des éléments à la liste et mesure de la méthode contains
            for (int i = 0; i < iter; i++) {
                long addStartTime = System.nanoTime();
                list.addElement("Element " + i); // Ajout d'un élément à la liste
                long addEndTime = System.nanoTime();
                addTotalDuration += (addEndTime - addStartTime); // Durée en nanosecondes
                
                int j = new Random().nextInt(0, iter);
                long containsStartTime = System.nanoTime();
                list.contains("Element " + j); // Recherche d'un élément spécifique dans la liste
                long containsEndTime = System.nanoTime();
                containsTotalDuration += (containsEndTime - containsStartTime); // Durée en nanosecondes
            }

            // Calcul du temps d'exécution moyen pour add et contains
            long addAverageDuration = addTotalDuration / (iter != 0 ? iter : 1); // Éviter la division par zéro
            long containsAverageDuration = containsTotalDuration / (iter != 0 ? iter : 1); // Éviter la division par zéro

            // Affichage du temps d'exécution moyen pour add et contains
            System.out.println("Pour " + iter + " itérations :");
            System.out.println("Temps d'exécution moyen de la méthode add : " + addAverageDuration + " nanosecondes");
            System.out.println("Temps d'exécution moyen de la méthode contains : " + containsAverageDuration + " nanosecondes");
            System.out.println();
        }

    }
}