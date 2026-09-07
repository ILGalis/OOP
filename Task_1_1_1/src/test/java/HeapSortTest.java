package ru.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    void testSortWithPositiveNumbers() {
        int[] arr = {5, 2, 8, 1, 9, 3, 6};
        int[] expected = {1, 2, 3, 5, 6, 8, 9};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortWithNegativeNumbers() {
        int[] arr = {-5, -2, -8, -1, -9};
        int[] expected = {-9, -8, -5, -2, -1};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortWithDuplicates() {
        int[] arr = {4, 1, 3, 4, 2, 3, 1};
        int[] expected = {1, 1, 2, 3, 3, 4, 4};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortWithEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortWithSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortReverseOrder() {
        int[] arr = {9, 7, 5, 3, 1};
        int[] expected = {1, 3, 5, 7, 9};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortWithZeros() {
        int[] arr = {0, 0, 0, 0, 0};
        int[] expected = {0, 0, 0, 0, 0};
        HeapSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}