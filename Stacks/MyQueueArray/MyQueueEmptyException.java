package fr.pantheonsorbonne.cri.MyQueueArray;

public class MyQueueEmptyException extends Exception{

    public MyQueueEmptyException() {
        super("Queue is empty");
    }
}
