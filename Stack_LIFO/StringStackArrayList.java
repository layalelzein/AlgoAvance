package Stack_LIFO;

import java.util.Iterator;

public class StringStackArrayList {
    //Partie tableau redimensionnée
    //private Scanner scanner = new Scanner(System.in);
    String[] data = new String[100];
    int nb;

    String toStrTab() {
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

    boolean isEmptyTab() {
        return nb == 0;
    }

    public String peekTab() {
        if (data[nb-1] == null) {
            return null;
        }
        return data[nb-1];
    }

    public String popTab() {
        if (nb == 0) {
            return null; // La pile est vide
        }
        String removedElement = data[--nb];
        data[nb] = null;
        return removedElement;
    }

    public boolean pushTab(String item) {
        ensureCapacity(nb + 1);
            data[nb++] = item;
        return true;
    }
    
    public Iterator<String> iteratorTab() {
        return new StringStackdArrayListIterator(this.data);
    }

    public static void main(String[] args) {
        StringStackArrayList sSTab = new StringStackArrayList();

        sSTab.pushTab("lala");
        sSTab.pushTab("keke");
        sSTab.pushTab("nino");

        System.out.println("2 ème implémentation avec Tab Redimensionné : ");

        //  Utilisation de l'itérateur pour parcourir les éléments
        Iterator<String> iteratorTab = sSTab.iteratorTab();
        while (iteratorTab.hasNext()) {
            String nextElement = iteratorTab.next();
            System.out.println("Il y a cet élément : " + nextElement);
        }
        

        System.out.println("La pile est vide ? : " + sSTab.isEmptyTab());
        System.out.println("Contenu du tableau : " + sSTab.toStrTab());
        System.out.println("Dernier élément sans l'enlever : " + sSTab.peekTab());
        System.out.println("Contenu du tableau : " + sSTab.toStrTab());
        System.out.println("Dernier élément en enlevant : " + sSTab.popTab());
        System.out.println("Contenu du tableau : " + sSTab.toStrTab());

        System.out.println("La pile est vide ? : " + sSTab.isEmptyTab());

        for(int i=0; i<2; i++) {
            sSTab.popTab();
        }
        
        System.out.println("Contenu du tableau : " + sSTab.toStrTab());
        System.out.println("La pile est vide ? : " + sSTab.isEmptyTab());
    
    }
    public class StringStackdArrayListIterator implements Iterator<String> {
        String[] data = new String[100];
    
        int cpt = 0;
    
        public StringStackdArrayListIterator(String[] data) {
            this.data = data;
            //this.cpt = cpt;
        }
    
        @Override
        public String next() {
            return this.data[cpt++];
            // return this.data[--cpt];

        }
    
        @Override
        public boolean hasNext() {
            if (cpt < data.length)
                return this.data[cpt] != null;
            return false;
        }        
    }
}