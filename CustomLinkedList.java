import java.util.Iterator;

public class CustomLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    // Constructor
    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    private void setHead(Node<T> head) {
        this.head = head;
    }

    // Add to end
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            setHead(newNode);
        } else {
            Node<T> current = getHead();
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    // Remove first occurrence
    public boolean remove(T data) {
        if (isEmpty()) return false;

        if (getHead().getData().equals(data)) {
            setHead(getHead().getNext());
            size--;
            return true;
        }

        Node<T> current = getHead();
        while (current.getNext() != null &&
               !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }

        if (current.getNext() == null) return false;

        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        Node<T> current = getHead();
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> current = getHead();
        for (int i = 0; i < index; i++)
            current = current.getNext();

        return current.getData();
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomLinkeListIterator<T>(this.getHead());
    }

    // ============================================================
    //         LAB 9 METHODS (insertAt, addFirst, contains...)
    // ============================================================

    // 3.1 insertAt
    public void insertAt(int index, T data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++)
                current = current.getNext();

            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        size++;
    }

    // 3.2 addFirst
    public void addFirst(T data) {
        insertAt(0, data);
    }

    // 3.3 contains
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data))
                return true;
            current = current.getNext();
        }
        return false;
    }

    // 3.4 indexOf
    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(data))
                return index;
            index++;
            current = current.getNext();
        }

        return -1;
    }

    // 3.5 lastIndexOf
    public int lastIndexOf(T data) {
        Node<T> current = head;
        int index = 0;
        int lastFound = -1;

        while (current != null) {
            if (current.getData().equals(data))
                lastFound = index;
            index++;
            current = current.getNext();
        }

        return lastFound;
    }

    // 3.6 toArray
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            arr[index++] = current.getData();
            current = current.getNext();
        }

        return arr;
    }
}
