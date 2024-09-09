import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Tyler Lu
 * @version 3.0
 * @userid tlu302
 * @GTID 903870636
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * Iterates through the Doubly Linked List. Once the index is reached,
     * that node is added and new pointers are established.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is too small or too large");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data is null and could not be added to the List");
        }
        if (index == 0) {
            this.addToFront(data);
        } else if (index == this.size()) {
            this.addToBack(data);
        } else {
            DoublyLinkedListNode<T> currentHead = head;
            for (int i = 0; i < index; i++) {
                currentHead = currentHead.getNext();
            }
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data, currentHead.getPrevious(), currentHead);
            currentHead.getPrevious().setNext(newNode);
            currentHead.setPrevious(newNode);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and could not be added to the List");
        }
        DoublyLinkedListNode<T> addedFront = new DoublyLinkedListNode<T>(data);

        if (this.isEmpty()) {
            head = addedFront;
            tail = addedFront;
        } else {
            head.setPrevious(addedFront);
            addedFront.setNext(head);
            head = addedFront;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and could not be added to the List");
        }
        DoublyLinkedListNode<T> addedBack = new DoublyLinkedListNode<T>(data);

        if (this.isEmpty()) {
            head = addedBack;
            tail = addedBack;
        } else {
            tail.setNext(addedBack);
            addedBack.setPrevious(tail);
            tail = addedBack;
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * Iterates through the Doubly Linked List. Once the index is reached,
     * that node is removed and new pointers are established.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        T outputNode = null;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is too small or too large");
        }
        if (index == 0) {
            outputNode = this.removeFromFront();
        } else if (index == size - 1) {
            outputNode = this.removeFromBack();
        } else {
            DoublyLinkedListNode<T> nodePlaceholder = head;
            for (int i = 0; i < index; i++) {
                nodePlaceholder = nodePlaceholder.getNext();
            }
            outputNode = nodePlaceholder.getData();
            DoublyLinkedListNode<T> next = nodePlaceholder.getNext();
            DoublyLinkedListNode<T> previous = nodePlaceholder.getPrevious();
            next.setPrevious(previous);
            previous.setNext(next);
            size--;
        }
        return outputNode;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        T outputNode = head.getData();
        if (this.isEmpty()) {
            throw new NoSuchElementException("The List is empty and the front node cannot be removed");
        } else if (this.size() == 1) {
            this.clear();
            return outputNode;
        } else {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }
        return outputNode;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T outputNode = tail.getData();
        if (this.isEmpty()) {
            throw new NoSuchElementException("The List is empty and the tail node cannot be removed");
        } else if (this.size() == 1) {
            this.clear();
            return outputNode;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }
        return outputNode;
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is too small or too large");
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            DoublyLinkedListNode<T> nodeIterator = head;
            for (int i = 0; i < index; i++) {
                nodeIterator = nodeIterator.getNext();
            }
            return nodeIterator.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.size == 0); //O(1),this checks the boolean value and returns true or false given "this.size == 0"
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * resets the size and sets head and tail to null
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * Line 269: To reduce repetitive and abundant code, the previous method
     * removeAtIndex is called which allows the speed of the program to be O(n).
     * If removeAtIndex was coded again here the bigO would be less efficient.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and could not be added to the List");
        }
        if (data == tail.getData()) {
            removeFromBack();
            return data;
        }
        DoublyLinkedListNode<T> nodeComparison = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (nodeComparison.getData().equals(data)) {
                return removeAtIndex(i);
            }
            nodeComparison = nodeComparison.getPrevious();
        }
        throw new NoSuchElementException("Data could not be found and is not in the List");
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * Iterates through the Doubly Linked List and the Array assigning the currentNode
     * to the current index in the array
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] returnArray;
        DoublyLinkedListNode<T> outputList = head;
        if (this.isEmpty()) {
            returnArray = new Object[0];
            return returnArray;
        } else {
            returnArray = new Object[size];
            for (int i = 0; i < size; i++) {
                returnArray[i] = outputList.getData();
                outputList = outputList.getNext();
            }
        }
        return returnArray;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
