package Queue_FIFO;

public class Node {
    String data;
    Node next;

    public Node(String data, Node courant) {
        this.data=data;
        this.next=courant;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String s) {
        this.data = s;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node n) {
        this.next = n;
    }
}