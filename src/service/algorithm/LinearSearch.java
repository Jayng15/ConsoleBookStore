package service.algorithm;

import service.adt.ArrayListADT;
import service.adt.interfaces.List;
import service.algorithm.helper.ValueExtractor;

public class LinearSearch<T> {

    public List<T> search(List<T> list, String searchCriteria, String targetValue, ValueExtractor<T> valueExtractor) {
        T[] array = list.toArray((T[]) new Object[0]);
        return linearSearch(array, searchCriteria, targetValue, valueExtractor);
    }

    private List<T> linearSearch(T[] array, String searchCriteria, String targetValue, ValueExtractor<T> valueExtractor) {
        List<T> results = new ArrayListADT<>();
    
        for (T item : array) {
            String currentValue = valueExtractor.extractValue(item, searchCriteria);
            if (currentValue.toLowerCase().contains(targetValue.toLowerCase())) {
                results.add(item);
            }
        }
    
        return results;
    }
}

