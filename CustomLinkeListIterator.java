import java.util.Iterator;

public class CustomLinkeListIterator <T> implements Iterator<T>{
    // private field controlling the current node in our linked list
    private Node<T> currentNode;

    // constructor
    // assign head
    public CustomLinkeListIterator(Node<T> head) {
        this.currentNode = head;
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public T next() {
        // if there is not next.
        // so calling next before hasNext will cause an exception
        // if there is no next
        if(!hasNext()) 
            throw new java.util.NoSuchElementException();
        T temp = currentNode.getData(); // get the current
        currentNode = currentNode.getNext(); // make the current next anymore, move to next 
        return temp; // return the current 
    }
    
}