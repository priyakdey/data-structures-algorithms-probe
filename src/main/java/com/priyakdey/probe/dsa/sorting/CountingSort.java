package com.priyakdey.probe.dsa.sorting;

import java.util.Objects;

/**
 * @author Priyak Dey
 */
public class CountingSort implements Sort<Integer> {

    @Override
    public void sort(Integer[] arr) {
        Objects.requireNonNull(arr);
        int length = arr.length;
        if (length < 2) return;

        // Step 1. Find max element
        int maxElement = arr[0];
        int minElement = arr[0];
        for (int element : arr) {
            maxElement = Math.max(maxElement, element);
            minElement = Math.min(minElement, element);
        }

        if (minElement < 0) {
            throw new IllegalArgumentException("can only sort [0...k]");
        }

        // Step 2. Create a freq table with maxElement + 1 buckets
        int bucketSize = maxElement + 1;
        int[] freqArr = new int[bucketSize];

        // Step 3. Count the freq of all elements
        for (int element: arr) {
            freqArr[element]++;
        }

        // Step 4: Transform freqArr into prefix sum to determine positions
        for (int i = 1; i < bucketSize; i++) {
            freqArr[i] += freqArr[i - 1];
        }

        // Step 5. Iterate over original array and place them in correct position
        int[] output = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            int element = arr[i];
            output[freqArr[element] - 1] = element;
            freqArr[element]--;
        }

        System.arraycopy(output, 0, arr, 0, length);
    }
}
