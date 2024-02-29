package ArbreBinaire;

import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class StringTreeSetIterator implements Iterator<String> {
    private StringTreeSet.Noeud suivant;
    private Deque<StringTreeSet.Noeud> stack;

    public StringTreeSetIterator(StringTreeSet.Noeud racine) {
        stack = new ArrayDeque<>();
        this.suivant = racine; // Initialisez la variable suivant avec le nœud racine
        pushLeftNodes(suivant);
    }

    private void pushLeftNodes(StringTreeSet.Noeud node) {
        while (node != null) {
            stack.push(node);
            node = node.getGauche();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException(); // Lancer une exception s'il n'y a pas d'élément suivant
        }
        StringTreeSet.Noeud courant = stack.pop();
        pushLeftNodes(courant.getDroite());
        return courant.getData();
    }
}