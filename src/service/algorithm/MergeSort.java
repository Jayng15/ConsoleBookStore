package service.algorithm;

import java.util.Comparator;
import service.adt.interfaces.*;

public class MergeSort<T> {

    public static <T> void sort(List<T> arr, Comparator<T> comparator) {
        mergeSort(arr, 0, arr.size() - 1, comparator);
    }

    private static <T> void mergeSort(List<T> arr, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, comparator);
            mergeSort(arr, mid + 1, right, comparator);

            merge(arr, left, mid, right, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(List<T> arr, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr.get(left + i);
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr.get(mid + 1 + j);
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare((T) leftArray[i], (T) rightArray[j]) <= 0) {
                arr.set(k, (T) leftArray[i]);
                i++;
            } else {
                arr.set(k, (T) rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr.set(k, (T) leftArray[i]);
            i++;
            k++;
        }

        while (j < n2) {
            arr.set(k, (T) rightArray[j]);
            j++;
            k++;
        }
    }
}

