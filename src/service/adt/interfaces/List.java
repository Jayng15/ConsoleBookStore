package service.adt.interfaces;

public interface List<T>{

    // Adds the specified element to the end of this list.
    void add(T element);

    // Inserts the specified element at the specified position in this list.
    void add(int index, T element);

    // Returns the element at the specified position in this list.
    T get(int index);

    // Replaces the element at the specified position in this list with the specified element.
    void set(int index, T element);

    // Removes the element at the specified position in this list.
    T remove(T element);

    // Returns true if this list contains no elements.
    boolean isEmpty();

    // Returns the number of elements in this list.
    int size();

    // Removes all of the elements from this list.
    void clear();

    T[] toArray(T[] ts);

}

