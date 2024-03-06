package ArbreBinaire;

import java.util.Iterator;

public class StringTreeSet implements Comparable<StringTreeSet> {

    Noeud racine;
    int cpt;

    public class Noeud {
        String data;
        Noeud gauche;
        Noeud droite;
        Noeud parent;


        public Noeud(String data, Noeud gauche, Noeud droite) {
            this.data = data;
            this.gauche = gauche;
            this.droite = droite;
        }

        public void setParent(Noeud n) {
            this.parent = n;
        }

        public Noeud getParent() {
            return this.parent;
        }

        public void setDroite(Noeud n) {
            this.droite = n;
        }

        public Noeud getDroite() {
            return this.droite;
        }

        public void setGauche(Noeud n) {
            this.gauche = n;
        }

        public Noeud getGauche() {
            return this.gauche;
        }

        public void setData(String s) {
            this.data = s;
        }

        public String getData() {
            return this.data;
        }
        
        @Override
        public String toString() {
            String result = "";
            if (this.gauche != null) {
                result += this.gauche.toString() + " ";
            }
            result += this.data;
            if (this.droite != null) {
                result += " " + this.droite.toString();
            }
            return result;
        }
    }
    /* Méthode toString() itérative
        public String toString () {
            Stack<Noeud> p = new Stack<Noeud>();
            p.push(his.racine);
            StringBuilder s = new StringBuilder();
            while(!p.isEmpty()) {
                Noeud courant ) p.pop();
                s.append(courant.getData())
            }
            if(courant.getDroite == null) {
                p.push(courant.getDroite())
                p.push(courant.getGauche())
            }
            return this.racine.toString();
        } 
        
    */

    @Override
    public String toString() {
        if (this.racine != null) {
            return this. racine.toString();
        } else {
            return "Arbre vide";
        }
    }    


    @Override
    public int compareTo(StringTreeSet other) {
        if (this.racine == null && other.racine == null) {
            return 0;
        } else if (this.racine == null) {
            return -1;
        } else if (other.racine == null) {
            return 1;
        } else {
            return this.racine.getData().compareTo(other.racine.getData());
        }
    }

    boolean add(String s) {
        if (this.racine == null) {
            this.racine = new Noeud(s, null, null);
            this.cpt++;
            return true;
        }
        Noeud parent = null;
        Noeud courant = this.racine;
        int c = 0;
        while (courant != null) {
            c = s.compareTo(courant.getData());
            if (c == 0) {
                return false; // L'élément est déjà présent
            }
            parent = courant;
            if (c < 0) {
                courant = courant.getGauche();
            } else {
                courant = courant.getDroite();
            }
        }
        if (c < 0) {
            parent.setGauche(new Noeud(s, null, null));
        } else {
            parent.setDroite(new Noeud(s, null, null));
        }
        this.cpt++;
        return true;
    }

    void clear() {
        this.racine = null;
        this.cpt=0;
    }

    boolean contains(String s) {
        Noeud courant = this.racine;

        while (courant != null) {
            int c = s.compareTo(courant.getData());
            if (c == 0)
                return true;
            else if (c < 0) {
                courant = courant.getGauche();
            } else {
                courant = courant.getDroite();
            }
        }
        return false;
    }

    String first() {
        Noeud courant = this.racine;
        if(courant == null) {
            return "Il n'y a pas d'élément";
        }

        while (courant.getGauche() != null) {
                courant = courant.getGauche();
        }
        return courant.getData();
    }

    boolean isEmpty() {
        return this.racine == null;
    }

    public Iterator<String> iterator() {
        // Retourne une nouvelle instance de la classe interne StringTreeSetIterator
        return new StringTreeSetIterator(this.racine);
    }

    boolean remove(String s) {
        Noeud courant = this.racine; 
        Noeud parent = null;
        boolean estGauche = true; 
    
        // recherche du noeud à supprimer
        while (courant != null && !courant.getData().equals(s)) {
            parent = courant; 
            int c = s.compareTo(courant.getData()); 
            if (c < 0) { // aller à gauche si la valeur est inf
                estGauche = true;
                courant = courant.getGauche();
            } else { // aller à droite si la valeur est sup
                estGauche = false;
                courant = courant.getDroite();
            }
        }
        if (courant == null) { 
            return false;
        }
    
        // Cas sans enfant
        if (courant.getGauche() == null && courant.getDroite() == null) { 
            if (courant == racine) racine = null; // si c'est la racine, l'arbre devient null
            else if (estGauche) parent.setGauche(null); // si c'est un enfant gauche, déconnecter du parent
            else parent.setDroite(null); // si c'est un enfant droit, déconnecter du parent
        }

        // Cas avec un seul enfant à droite
        else if (courant.getGauche() == null) { 
            if (courant == racine) racine = courant.getDroite(); 
            else if (estGauche) parent.setGauche(courant.getDroite()); 
            else parent.setDroite(courant.getDroite());
        }

        // Cas avec un seul enfant à gauche
        else if (courant.getDroite() == null) { 
            if (courant == racine) racine = courant.getGauche(); 
            else if (estGauche) parent.setGauche(courant.getGauche());
            else parent.setDroite(courant.getGauche());
        }

        // Cas avec deux enfants
        else { 
            Noeud successeur = getSuccesseur(courant);
            if (courant == racine) racine = successeur; 
            else if (estGauche) parent.setGauche(successeur);
            else parent.setDroite(successeur);
            successeur.setGauche(courant.getGauche());
        }
    
        return true; 
    }
    
    private Noeud getSuccesseur(Noeud delNoeud) {
        Noeud successeurParent = delNoeud;
        Noeud successeur = delNoeud;
        Noeud courant = delNoeud.getDroite(); // on commence par l'enfant droit du noeud à sup
    
        // trouver le successeur (le + petit noeud du sous-arbre droit)
        while (courant != null) {
            successeurParent = successeur;
            successeur = courant;
            courant = courant.getGauche(); // aller tjr à gauche
        }
    
        // si le successeur n'est pas l'enfant droit direct => réorganiser les liens
        if (successeur != delNoeud.getDroite()) {
            successeurParent.setGauche(successeur.getDroite()); // connecter l'enfant droit du successeur à son parent
            successeur.setDroite(delNoeud.getDroite()); // connecter l'enfant droit du noeud supprimé au successeur
        }
    
        return successeur;
    }
    

    int size() {
        return cpt;
    }

    public static void main(String[] args) {
        StringTreeSet treeSet = new StringTreeSet();

        treeSet.add("banana");
        treeSet.add("apple");
        treeSet.add("orange");
        treeSet.add("amande");
    
        // Afficher le contenu de l'arbre 
        System.out.println("Contenu de l'arbre : " + treeSet);

        // Vérifier la présence d'un élément
        System.out.println("Contient 'apple' : " + treeSet.contains("apple"));
        System.out.println("Contient 'grape' : " + treeSet.contains("grape"));
    
        // Tenter de supprimer un élément qui existe
        boolean isRemoved = treeSet.remove("apple");
        System.out.println("Suppression de 'apple' réussie ? " + isRemoved);
        System.out.println("Contenu de l'arbre après suppression de 'apple' : " + treeSet);
    
        // Tenter de supprimer un élément qui n'existe pas
        isRemoved = treeSet.remove("grape");
        System.out.println("Suppression de 'grape' réussie ? " + isRemoved);
        System.out.println("Contenu de l'arbre après tentative de suppression de 'grape' : " + treeSet);

        treeSet.clear();
        System.out.println("Contenu de l'arbre après clear : " + treeSet.toString() + " c'est " + treeSet.isEmpty());
        System.out.println("Taille d'élément : " + treeSet.size());
    
        // Ajouter de nouveaux éléments et réafficher
        treeSet.add("c");
        treeSet.add("banana");
        treeSet.add("apple");
        treeSet.add("ab");
    
        System.out.println("Contenu de l'arbre après ajout de nouveaux éléments : " + treeSet);
        System.out.println("L'élément le plus à gauche : " + treeSet.first());
        System.out.println("Arbre vide ? " + treeSet.isEmpty());
        System.out.println("Taille de l'élément : " + treeSet.size());

        // Utilisation de l'itérateur pour parcourir les éléments dans l'ordre croissant
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);
        }

        treeSet.remove("ab");
        System.out.println("Contenu de l'arbre : " + treeSet.toString());

        StringTreeSet treeSet1 = new StringTreeSet();
        StringTreeSet treeSet2 = new StringTreeSet();
        StringTreeSet treeSet3 = new StringTreeSet();

        // Ajouter des éléments à chaque ensemble
        for (int i = 0; i < 1000; i++) {
            treeSet1.add("Element" + i);
        }

        for (int i = 0; i < 100000; i++) {
            treeSet2.add("Element" + i);
        }

        for (int i = 0; i < 1000000; i++) {
            treeSet3.add("Element" + i);
        }

        // Mesurer le temps d'exécution de contains pour chaque ensemble
        long startTime1 = System.nanoTime();
        boolean contains1 = treeSet1.contains("Element500");
        long endTime1 = System.nanoTime();
        long duration1 = endTime1 - startTime1;
        System.out.println("Temps d'exécution de contains pour treeSet1 : " + duration1 + " nanosecondes.");

        long startTime2 = System.nanoTime();
        boolean contains2 = treeSet2.contains("Element50000");
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        System.out.println("Temps d'exécution de contains pour treeSet2 : " + duration2 + " nanosecondes.");

        long startTime3 = System.nanoTime();
        boolean contains3 = treeSet3.contains("Element500000");
        long endTime3 = System.nanoTime();
        long duration3 = endTime3 - startTime3;
        System.out.println("Temps d'exécution de contains pour treeSet3 : " + duration3 + " nanosecondes.");

    }
    
}