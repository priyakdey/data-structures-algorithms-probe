package com.priyakdey.probe.dsa.sorting;

import java.util.Objects;

/**
 * @author Priyak Dey
 */
public class RadixSort implements Sort<Integer> {

    @Override
    public void sort(Integer[] arr) {
        Objects.requireNonNull(arr);
        if (arr.length < 2) return;

        int maxElement = arr[0];
        int minElement = arr[0];
        for (int num : arr) {
            maxElement = Math.max(maxElement, num);
            minElement = Math.min(minElement, num);
        }

        if (minElement < 0) {
            throw new IllegalArgumentException("can only sort [0...k]");
        }

        for (int place = 1; maxElement / place > 0; place *= 10) {
            countingSort(arr, place);
        }

    }

    private void countingSort(Integer[] arr, int place) {
        int[] freqArr = new int[10];    // [0..9]

        for (int num : arr) {
            int digit = (num / place) % 10;
            freqArr[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            freqArr[i] += freqArr[i - 1];
        }

        Integer[] output = new Integer[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            int digit = (num / place) % 10;
            output[freqArr[digit] - 1] = num;
            freqArr[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
