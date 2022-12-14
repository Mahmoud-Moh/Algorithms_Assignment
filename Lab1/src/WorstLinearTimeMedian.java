import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.random.RandomGenerator;

public class WorstLinearTimeMedian {
    private int arr[];

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public WorstLinearTimeMedian(int arr[]) {
        this.arr = arr.clone();
    }

    public int getMedian() {
        return Select(arr, 0, arr.length - 1, (arr.length + 1) / 2);
    }

    private int Select(int A[], int p, int q, int i) {
        int n = q - p + 1;
        if (n <= 5)
            return MedianOf5(A);
        int[] medians = new int[n / 5];
        int c = 0;
        for (int j = p; j+4 <= q; j+=5) {
            int[] arr = new int[5];
            for(int d=0; d<5; d++)
                arr[d] = A[j+d];
           // System.arraycopy(A, j, arr, 0, 5);
            //System.out.println(Arrays.toString(arr));
            int median_of_5 = MedianOf5(arr);
            //System.out.println(median_of_5);
            medians[c++] = median_of_5;
        }
        //This is going to be changed
        int median_of_medians = Select(medians, 0, medians.length - 1, (medians.length + 1) / 2);
        int r = partition(A, p, q, median_of_medians);
        int k = r - p + 1;
        if (i == k) return A[r];
        if (i < k) return Select(A, p, r - 1, i);
        else return Select(A, r + 1, q, i - k);
    }

    int MedianOf5(int arr[]) {
        Arrays.sort(arr);
        return arr[(arr.length + 1) / 2 - 1];
    }


    static int partition(int A[], int p, int q, int medianOfmedians) {

        int r = findIndex(A, medianOfmedians);
        int pivot = medianOfmedians;
        swap(A, r, q);

        int i = (p - 1);

        for (int j = p; j <= q - 1; j++) {

            if (A[j] <= pivot) {

                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, q);
        return (i + 1);
    }

    public static int findIndex(int arr[], int t) {

        if (arr == null) {
            return -1;
        }


        int len = arr.length;
        int i = 0;

        while (i < len) {

            if (arr[i] == t) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int NaiveMedian(int arr[]){
        Arrays.sort(arr);
        return arr[(arr.length + 1) / 2 - 1];
    }
    public static void main(String args[]) {
        WorstLinearTimeMedian wm = new WorstLinearTimeMedian(new int[]{0}) ;

        ArrayList<int[]> a = new ArrayList();
/*
        a.add(new int[]{13, 48, 60, 33, 52, 36, 66, 94, 87, 97, 1, 32, 36, 24, 90, 98, 92, 77, 57, 47, 46, 77, 31, 43, 2, 57, 17});
        a.add(new int[]{75, 86, 27, 68, 90, 85, 52, 34, 3, 81, 92, 78, 71, 55, 66, 72, 21, 55, 51, 86});
        a.add(new int[]{59, 55, 66, 42, 63, 89, 59, 13, 64, 49, 74, 91, 48, 95, 56, 9, 76, 96, 52, 78, 74, 88, 90, 48, 45, 57, 88, 8, 37, 73, 17, 51, 16, 27, 72, 52, 80, 96, 82, 38, 65, 16, 93, 68, 29, 47, 29, 22, 86, 18, 92, 17, 51, 50, 62, 10, 89, 42, 37, 76, 45, 72, 24, 29, 5, 23, 16, 73, 42, 44, 56});
        a.add(new int[]{80, 86, 28, 75, 20, 30, 12, 54, 34, 67, 32, 11, 1, 86, 38, 38, 92, 21, 36, 53, 6, 45, 10, 68, 38, 42, 44, 68, 84, 18, 80, 41, 80, 38, 24, 18, 87, 88, 39, 17, 24, 74, 79, 35, 77, 32, 87, 55, 28, 9, 6, 48, 79, 99, 66, 75, 60, 66, 41, 70, 28, 50, 69, 78, 3, 13, 31, 80, 52, 1});
        a.add(new int[]{91, 38, 72, 21, 84, 10, 25, 72, 43, 18, 75, 32, 94, 50, 20, 76, 27, 36, 37, 81, 96, 92, 93, 22, 5, 78, 93, 20, 89, 90, 99, 77, 86, 11, 63, 22, 2, 93, 57, 95, 80, 9, 90, 22, 4, 74, 94, 45, 55, 51, 89, 49, 81, 64, 92, 33, 10, 2, 7, 58, 1, 50, 37, 29, 39, 90, 1, 19, 53, 64, 28, 62, 24, 72, 94, 3, 48, 62, 4, 24, 92, 11});
        a.add(new int[]{18, 14, 87, 98, 93, 30, 38, 46, 81, 98, 41, 60, 49, 13, 66, 86, 39, 70, 58, 66, 97, 26, 23, 92, 59, 40, 23, 96, 81, 33, 5, 92, 90, 8, 62, 86, 90, 47, 9, 29, 20, 96, 74, 3, 46});
        a.add(new int[]{85, 94, 65, 16, 98, 21, 13, 30, 30, 90, 56, 45, 35, 26, 78, 45, 23, 26, 85, 2, 22, 20, 63, 66, 3, 88, 85, 90, 25, 34, 0, 14, 20, 78, 29, 64, 36, 75, 18, 21, 67, 63, 52, 19, 55});
        a.add(new int[]{0, 14, 39, 53, 48, 40, 57, 78, 93, 18});
        a.add(new int[]{3, 74, 33, 32, 24, 80, 5, 67, 24, 1, 21, 55, 10, 29, 98, 48, 8, 86, 48, 11, 77, 95, 35, 84, 89, 24, 90, 87, 86, 42, 72, 49, 5, 70, 32, 39, 30, 2, 80, 82, 99, 77, 10, 92, 32, 40, 96, 75, 35, 42, 28, 68, 64, 4, 40, 38, 51, 84, 9, 71, 10, 7, 45, 66, 33, 25, 22, 39, 38, 45, 92, 29, 98, 40, 90, 78, 34, 43});
        a.add(new int[]{30, 9, 15, 47, 85, 21, 94, 71, 71, 9, 3, 99, 48, 36, 70, 20, 15, 37, 40, 41, 95, 66, 70, 86, 63, 99, 73, 21, 74, 0, 13, 38, 48, 88, 93, 70, 23, 34, 69, 28, 29, 3, 92, 86, 56, 91, 87, 68, 19, 95, 52, 86, 0, 4, 20, 31, 86, 14, 90, 45, 72, 91, 82, 36, 95, 10, 19, 65, 13, 83, 55, 1, 30, 67, 86, 45, 64, 15, 51, 3, 74, 57, 91, 83, 61, 51, 31, 15, 92, 77});
        a.add(new int[]{98, 16, 40, 76, 48, 82, 80, 59, 18, 72, 9, 9, 71, 54, 63, 83, 92, 30, 13, 12, 33, 8, 89, 96, 83, 44, 12, 13, 15, 30, 61, 97, 82, 10, 83, 96, 94, 97, 92, 72, 79, 23, 51, 96, 40, 57, 65, 48, 40, 9, 93, 0, 0, 12, 89, 91, 48, 38, 5, 78, 23, 39, 43, 44, 52, 93, 30, 5, 71, 87, 25, 36, 10, 3, 98, 36});
        a.add(new int[]{25, 57, 66, 23, 73, 37, 47, 42, 39, 84, 31, 12, 50, 52, 49, 82, 62, 40, 30, 24, 26, 61, 6, 24, 92, 53, 2, 91, 77, 24, 50, 72, 39});
        a.add(new int[]{42, 96, 34, 53, 97, 12, 49, 64, 40, 63, 48, 36, 48, 75, 95, 57, 40, 23, 41, 62, 36, 13, 20, 11, 33, 75, 50, 29, 31, 44, 41, 72, 82, 91, 98, 21, 32, 34, 88, 36, 45, 11, 0, 46, 23, 22, 85, 53, 97, 70, 8, 5, 14, 40, 82, 5, 87, 87, 0, 44, 34, 35, 12, 51, 51});
        a.add(new int[]{63, 65, 63, 30, 26, 19, 39, 88, 58, 8, 61, 46, 27, 45, 91, 25, 56, 42, 13, 62, 3, 30, 15, 5, 4, 60, 83, 60, 37, 71, 68, 5, 31, 14, 17, 77, 91, 34, 12, 62, 74, 16, 63, 34, 26, 38, 44, 43, 55, 30, 16, 50, 98, 28, 43, 20, 7, 64, 19, 84, 4, 1, 72, 58, 74, 16, 89, 40, 40, 45, 75, 98, 95, 64, 51, 24});
        a.add(new int[]{80, 25, 8, 80, 30, 7, 49, 42, 12, 15, 99, 81, 80, 53, 1, 89, 30, 9, 89, 16, 35, 63, 97, 17, 31, 65, 1});
        a.add(new int[]{29, 19, 20, 6, 2, 18, 27, 50, 43, 47, 43, 94, 97, 53, 94, 89, 47, 95, 10, 15, 46, 98, 39, 44, 86, 34, 55, 14, 78, 20, 61, 6, 86, 2, 80, 48, 81, 74, 63, 35, 39, 84, 52, 89, 37, 82, 35, 43, 57, 35, 24, 27, 42, 92, 39, 86, 24, 50, 39, 99, 83, 30});
        a.add(new int[]{45, 95, 15, 75, 42, 70, 38, 95, 32, 31, 78, 53, 92, 12, 43, 83, 34, 74, 0, 5, 78, 42, 14, 18, 20, 43, 6, 7, 13, 62, 11, 13, 42, 62, 80, 36, 70, 1, 70, 37, 68, 30, 55, 7, 9, 37, 22, 99, 24, 75, 8, 57, 57, 34, 22, 40, 91, 45, 11, 99, 87, 31, 65, 38, 94, 16, 29, 35, 36, 78, 58, 0, 80, 30, 26, 26, 1, 22, 96, 77, 91, 73, 85, 35, 50, 22, 23, 33, 82, 81, 8, 0, 30, 71, 61, 66, 85});
        a.add(new int[]{56, 12, 9, 12, 4, 91, 81, 92, 52, 14, 57, 3, 41, 71, 96, 29, 87, 56, 99, 51, 28, 93, 77, 25, 11, 85, 37, 32, 10, 39, 42, 87, 55, 54, 18, 25, 11, 46, 44, 35, 42, 34, 65, 86, 51, 79, 8, 39, 18, 32, 76, 41, 90, 2, 87, 56, 45, 69, 52, 97, 19, 97, 98, 17, 75, 86, 84, 3, 27, 27, 96, 99, 43, 96, 7, 20, 52, 3, 40, 26, 29, 62, 25, 60, 12, 78, 69, 6, 45, 84, 16, 34, 26, 46});
        a.add(new int[]{15, 94, 15, 23, 18, 12, 53, 28, 34, 50, 34, 20, 36, 79, 24, 67, 85, 3, 75, 14, 93, 16, 77, 26, 99, 47, 25, 38, 80, 18, 19, 70, 54, 36, 0, 40, 4, 93, 31, 95, 98, 63, 27, 84, 25, 98, 19, 76, 18, 29, 74, 15, 47, 75, 89, 89, 60, 17, 91, 52, 6, 93, 58, 29, 69, 27, 94, 67, 3});
        a.add(new int[]{3, 19, 18, 60, 14, 61, 67, 89, 51});
        a.add(new int[]{40, 12, 51, 59, 43, 44, 2, 51, 5, 30, 14, 21, 28, 93, 24, 98, 3, 68, 44, 76, 4, 85, 61});
        a.add(new int[]{18, 67, 42});
        a.add(new int[]{31, 0, 11, 0, 39, 20, 7, 67, 11, 66, 98, 24, 2, 27, 42, 95, 8, 42, 27, 90, 44, 51, 32, 77, 73, 8, 24, 72, 40, 97, 39, 60, 69, 57, 77, 62, 32, 33, 85, 54, 12, 52, 20, 93, 65, 92, 89, 78, 10, 82, 47, 21, 71, 55, 28, 56, 50});
        a.add(new int[]{89, 63, 74, 62, 60, 88, 34, 27, 34, 61, 20, 74, 63, 48, 48, 60, 4, 87, 22, 10, 10, 90, 35, 52, 95, 94, 96, 15, 78, 70, 33, 11, 36, 72, 83, 25, 13, 24, 94, 75, 88, 38, 76, 26, 82, 1, 38, 83, 38, 90, 29, 7, 23, 56, 26, 20, 85, 30, 98});
        a.add(new int[]{4, 39, 39, 78, 96, 87, 47, 4, 91, 26, 36, 67, 96, 85, 93, 15, 92, 27, 10, 78, 81, 67, 78, 72, 31, 62, 75, 92, 71, 15, 28, 97, 0, 3, 40, 37, 38, 22, 12, 98, 11, 42, 61, 78, 28, 52, 46, 4, 79, 51, 85, 86, 32, 67, 48, 96, 77, 22, 22, 73, 66, 28, 60, 45, 22, 58, 80, 81, 58, 75, 77, 72, 12, 44, 45, 32, 3, 4, 68, 80, 84, 86, 19, 23, 87, 96, 35, 35, 75});
        a.add(new int[]{80, 49, 85, 17, 15, 43, 94, 98, 91, 65, 41, 14, 26, 46, 10, 84, 60, 91, 61, 27, 39, 63, 36, 45, 60, 11, 32, 37, 33, 16, 52, 64, 6, 81, 18, 8, 84, 10, 97, 84, 29, 12});
        a.add(new int[]{80, 94, 39, 81, 19, 47, 69, 91, 88, 67, 90, 28, 88, 57, 48, 34, 31, 64, 98});
        a.add(new int[]{92, 19, 18, 77, 73, 84, 25, 99, 97, 71, 82, 28, 16, 7, 68, 30, 23, 81, 87, 66, 42, 38, 19, 73, 44, 22, 93});
        a.add(new int[]{79, 20, 3, 10, 91, 24, 13, 30, 95, 68, 56, 45, 38, 8, 45, 67, 27, 64, 83, 2, 19, 61, 34, 74, 14, 84, 33, 77, 11, 32, 81, 90, 25, 97, 43, 19, 77, 40, 48, 3, 74, 28, 64, 4, 28, 23, 98, 4, 38, 62, 77, 22, 66, 11, 37, 92});
        a.add(new int[]{2, 31, 82, 27, 32, 5, 50, 20, 10, 19, 53, 56, 38, 66, 58, 7, 35, 37, 64, 78, 10, 32, 42, 50, 19, 84, 5, 40, 92, 69, 85, 16, 35, 52, 79, 63, 15, 74, 66, 69, 2, 74, 77, 98, 91, 47, 22, 4, 8, 37, 69, 94, 21, 0, 96, 96, 49});
        a.add(new int[]{61, 27, 69, 77, 19, 22, 12, 63, 5, 1, 68, 12, 44, 97, 69, 3, 9, 75, 62, 81, 13, 62, 55, 13, 60, 33, 66, 22, 59, 63, 16, 42, 35, 5, 39, 84});
        a.add(new int[]{79, 52, 34, 49, 48, 29, 87, 82, 64, 2, 44, 83, 31, 53, 57, 45, 80, 92, 64, 62, 23, 38, 65});
        a.add(new int[]{53, 96, 34, 46, 85, 13, 71, 80, 22, 55, 47, 65, 2, 62, 96, 25, 34, 39, 70, 58, 48, 25, 10, 82, 2, 99, 14, 62, 39, 83, 46, 1, 62, 79, 39, 68, 32, 36, 74, 72, 92, 19, 39, 16, 73, 81, 85, 69, 25, 10, 14, 27, 21, 1, 20, 22, 41});
        a.add(new int[]{74, 41, 26, 25, 95, 16, 24, 37, 14, 99, 19, 98, 96, 62, 83, 16, 65, 56, 33, 97, 44, 88, 34, 43, 30, 28, 84, 84, 61, 86, 81, 61, 18, 1, 77, 70, 88, 18, 80, 63, 86, 57, 55, 8, 11, 0, 79, 80, 69, 82, 13});
        a.add(new int[]{4, 77, 68, 63, 67, 0, 34, 42, 70, 8, 94, 35, 80, 63, 34, 34, 45, 33, 84, 88, 99, 42, 38, 69, 60, 87, 56, 12, 72, 86, 4, 17, 8, 42, 40, 14, 17, 93, 12, 61, 85, 23, 45, 13, 6, 8, 73, 90, 56, 2, 59, 45, 24, 93, 62, 99, 64});
        a.add(new int[]{21, 55, 38, 77, 15, 63, 65, 48, 43, 38, 78, 39, 19, 98, 97, 85, 23, 8, 72, 58, 64, 17, 56, 70, 78, 72, 11, 74, 43, 91, 20, 62, 89, 32, 1, 1, 5, 26, 35, 55, 36, 9, 91, 29, 83, 41, 23, 17, 93, 42, 2, 26, 75, 4, 77, 76, 53, 22, 0, 0, 35, 9, 41, 59, 56, 76, 68, 20, 30, 4, 11, 12, 2, 5, 28, 35, 99, 37, 77, 77, 6, 2, 21, 15, 15, 66, 35, 55, 64, 20, 26});
        a.add(new int[]{37, 25, 1, 57, 21, 17, 55, 91, 73, 44, 31, 45, 72, 84, 23, 18, 40, 25, 99, 87, 1, 80, 47, 43, 18, 63, 35, 30, 54, 11, 92, 99, 54, 32, 78, 5, 66, 92, 92, 10, 0, 24, 22, 14, 79});
        a.add(new int[]{0, 40, 32, 38, 87, 81, 18, 14, 21, 55, 61, 99, 70, 55, 88, 51, 35, 28, 14, 24, 33, 71, 30, 77, 13, 76, 44, 88, 17, 47, 74, 31, 67, 23, 0, 22, 57, 96, 10, 80, 83, 33, 27, 57, 22, 2, 35, 36, 49, 40, 43, 55, 67, 41, 42, 77, 49, 55, 42, 8, 57, 94, 99, 25, 60, 8, 12, 37, 55, 43, 11, 71, 51, 31, 83, 68, 25, 35, 70, 87, 94, 10, 35, 23, 15, 15, 99, 35, 80, 65, 34, 50, 57, 49, 27, 3});
        a.add(new int[]{55, 7, 89, 23, 25});
        a.add(new int[]{98, 93, 68, 51, 75, 41, 96, 56, 8, 27, 39, 33, 91, 67, 59, 52, 37, 86, 97, 82, 2, 2, 33, 98, 58, 81, 12, 43, 37, 68, 17, 22, 29});
        a.add(new int[]{26});
        a.add(new int[]{49, 5, 35, 50, 83, 84, 95, 18, 26, 56, 27});
        a.add(new int[]{95, 65, 41, 99, 51, 57, 40, 57, 13, 62, 54, 21, 50, 1, 24, 31, 91, 29, 52, 9, 7, 33, 92, 75, 46, 61});
        a.add(new int[]{10, 87, 74, 19, 44, 86, 99, 60, 38});
        a.add(new int[]{93, 19, 27});
        a.add(new int[]{84, 2, 20, 93, 25, 81, 27, 26, 78, 32, 95, 84, 74, 95, 86, 76, 90, 90, 76, 58, 9, 55, 57, 78, 70, 76, 89, 1, 65, 28, 44, 85, 26, 61, 58, 26, 90, 31, 22, 89, 48, 35, 92, 57, 2, 61, 98, 59, 64, 18, 23, 13, 10, 26, 91, 18, 93, 65, 88, 9, 92, 56, 95, 53, 96, 48, 58, 93, 64, 55, 68, 30, 43, 18, 48, 15, 9, 33, 69, 86, 19, 59, 1});
        a.add(new int[]{96, 2, 52, 29});
        a.add(new int[]{2, 34, 51, 43, 41, 89, 87, 93, 89, 11, 16, 85, 78, 77, 34, 83, 64, 48, 86, 21, 73, 64, 67, 56, 9, 33, 26, 11, 44, 68, 74, 27, 31, 41, 22, 82, 53, 11, 31, 74, 52, 52});
        a.add(new int[]{3, 62});
        a.add(new int[]{27, 89, 41, 24, 17, 3, 24, 52, 89, 23, 59, 22, 88, 38, 89, 41, 40, 55, 39, 56, 51, 76, 81, 44, 65, 4, 25, 19, 59, 50, 90, 68, 87, 63, 67, 26, 17, 14, 60, 0, 41, 81, 88, 99, 1, 20, 51, 80, 75, 65, 67, 23, 85, 68, 16, 85, 26, 23, 99, 79, 84, 23, 15, 9, 22, 52, 16, 25, 85, 45, 75, 45, 61, 82, 20, 10, 72, 71, 6, 99, 15, 96, 95});
*/

        int c = 10;
        System.out.println("size    time");
        for(int i=0; i<7; i++){
            var start_time = System.currentTimeMillis();
            System.out.print(c +"    ");
            int arr[] = new int[c];
            for(int j=0; j<c; j++)
                arr[j] = RandomGenerator.getDefault().nextInt(-1000, 1000);
            wm.setArr(arr);
            wm.getMedian();
            var end_time = System.currentTimeMillis();
            System.out.println(end_time - start_time);
            if(c==1000000)
                c*=5;
            else c*=10;
        }

        /*for(int[] arr: a){
            wm.setArr(arr);
            int m2 = wm.getMedian();
            System.out.println(m2);
        }*/

    }
}
