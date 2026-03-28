// Node class defined as a separate public class
public class Node<T> {
    private T data;
    private Node<T> next;

    // Constructor
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    // Getter for data
    public T getData() {
        return data;
    }

    // Setter for data
    public void setData(T data) {
        this.data = data;
    }

    // Getter for next
    public Node<T> getNext() {
        return next;
    }

    // Setter for next
    public void setNext(Node<T> next) {
        this.next = next;
    }
}