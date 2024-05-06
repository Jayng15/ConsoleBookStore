package service.algorithm.helper;

public interface ValueExtractor<T> {
    String extractValue(T object, String searchCriteria);
}
