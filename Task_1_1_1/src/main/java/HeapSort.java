package ru.example;
/**
 * Реализация пирамидальной сортировки.
 * Сортирует массив на месте за O(n log n) времени, память O(1) соответственно.
 */
public class HeapSort {

    /**
     * Просеивает элемент вниз по куче.
     *
     * @param arr массив
     * @param n   размер кучи
     * @param i   индекс элемента для просеивания
     */
    public static void heapsift(int[] arr, int n, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if (left < n && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < n && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest == i) {
                break;
            }
            swap(arr, i, largest);
            i = largest;
        }
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param arr массив
     * @param j   индекс элемента первого
     * @param i   индекс элемента второго
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Строит кучу, используя просеивание.
     *
     * @param arr массив
     */
    public static void build(int[] arr) {
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapsift(arr, len, i);
        }
    }

    /**
     * Сортирует уже сам массив пирамидальной сортировкой.
     *
     * @param arr массив
     * @return отсортированный массив
     */
    public static int[] sort(int[] arr) {
        int len = arr.length;
        build(arr);
        for (int i = len - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapsift(arr, i, 0);
        }
        return arr;
    }

    /**
     *Проверка работы алгоритма.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9, 3, 6};
        System.out.println("До сортировки: " + java.util.Arrays.toString(arr));

        HeapSort.sort(arr);

        System.out.println("После сортировки: " + java.util.Arrays.toString(arr));
    }

    }