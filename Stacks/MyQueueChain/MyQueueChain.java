package fr.pantheonsorbonne.cri.MyQueueChain;

import java.util.Iterator;

public class MyQueueChain implements Iterable<String>{

    Couche currentTop;

    public static class Couche {
        private String data;
        private Couche under;

        public Couche(String data, Couche under){
            this.data = data;
            this.under = under;
        }

        public void setData(String data) {
            this.data = data;
        }

        public void setUnder(Couche under){
            this.under = under;
        }

        public String getData(){
            return this.data;
        }

        public Couche getUnder(){
            return this.under;
        }
    }
    public boolean isEmpty(){
        return this.currentTop == null;
    }

    public void push(String ajout){
        this.currentTop = new Couche(ajout, this.currentTop);
    }

    public String peek(){
        return this.currentTop.getData();
    }

    public String poll(){
        Couche extract = this.currentTop;
        this.currentTop = extract.getUnder();

        return extract.getData();
    }

    @Override
    public Iterator<String> iterator() {
        return new MyQueueIterator(this.currentTop);
    }

}
