package ru.example;

public class HeapSort {
    public static void heapsift(int[] arr, int n, int i){
        while (true){
            int left = 2*i+1;
            int right = 2*i+2;
            int largest = i;
            if (left < n && arr[left] > arr[largest]){
                largest = left;
            }
            if (right < n && arr[right] > arr[largest]){
                largest = right;
            }
            if (largest == i) {
                break;
            }
            swap(arr,i,largest);
            i=largest;
        }
    }
    public static void swap(int[] arr, int i, int n){
        int temp = arr[i];
        arr[i]=arr[n];
        arr[n]=temp;
    }
    public static void build(int[] arr){
        int len = arr.length;
        for (int i=len/2 - 1;i>=0;i--){
            heapsift(arr,len,i);
        }
    }
    public static int[] sort(int[] arr){
        int len = arr.length;
        build(arr);
        for (int i=len-1;i>=0;i--){
            swap(arr,0,i);
            heapsift(arr,i,0);
        }
        return arr;
    }
    }