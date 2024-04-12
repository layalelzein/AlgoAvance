package fr.pantheonsorbonne.cri.MyQueueChain;

import java.util.Iterator;

public class MyQueueIterator implements Iterator<String> {

    MyQueueChain.Couche currentCouche;
    public MyQueueIterator(MyQueueChain.Couche top){
        this.currentCouche = top;
    }
    @Override
    public boolean hasNext() {
        return this.currentCouche != null;
    }

    @Override
    public String next() {
        String answer = this.currentCouche.getData();
        this.currentCouche = this.currentCouche.getUnder();
        return answer;
    }
}
