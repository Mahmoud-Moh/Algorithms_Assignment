import java.util.Arrays;
import java.util.random.RandomGenerator;

public class RandomizedMedian {
    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private int arr[];

    public RandomizedMedian(int arr[]) {
        this.arr = arr.clone();
    }

    public int getMedian() {
        return RandomizedSelect(arr, 0, arr.length - 1, (arr.length + 1) / 2);
    }

    private int RandomizedSelect(int A[], int p, int q, int i) {
        if (p == q) return A[p];
        int r = partition(A, p, q);
        int k = r - p + 1;
        if (i == k) return A[r];
        if (i < k) return RandomizedSelect(A, p, r - 1, i);
        else return RandomizedSelect(A, r + 1, q, i - k);
    }

    static int partition(int A[], int p, int q) {
        int r = RandomGenerator.getDefault().nextInt(p, q);
        int pivot = A[r];
        swap(A, r, q);

        int i = (p - 1);

        for (int j = p; j <= q - 1; j++) {

            if (A[j] < pivot) {

                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, q);
        return (i + 1);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int NaiveMedian(int arr[]) {
        Arrays.sort(arr);
        return arr[(arr.length + 1) / 2 - 1];
    }

    public static void main(String args[]) {
        RandomizedMedian rm = new RandomizedMedian(new int[]{0});
        /*for(int i=0; i<50; i++){
            int size = RandomGenerator.getDefault().nextInt(1, 100);
            int[] arr = new int[size];
            for(int j=0; j<size; j++){
                arr[j] =  RandomGenerator.getDefault().nextInt(-100, 100);
            }

            *//*System.out.println("------------------------");
            System.out.println(Arrays.toString(arr));*//*
            int median1 = NaiveMedian(arr);
            rm.setArr(arr);
            int median2 = rm.getMedian();
            if(median2 == median1)
                System.out.println("T");
            else
                System.out.println("F");
            *//*System.out.println(Arrays.toString(arr));
            System.out.println(median1 + " " + median2);
            System.out.println("------------------------");*//*
            assert(median1 == median2);*/

        int c = 10;
        System.out.println("size    time");
        for (int i = 0; i < 7; i++) {
            var start_time = System.currentTimeMillis();
            System.out.print(c + "    ");
            int arr[] = new int[c];
            for (int j = 0; j < c; j++)
                arr[j] = RandomGenerator.getDefault().nextInt(-1000, 1000);
            rm.setArr(arr);
            rm.getMedian();
            var end_time = System.currentTimeMillis();
            System.out.println(end_time - start_time);
            if (c == 1000000)
                c *= 5;
            else c *= 10;
        }

    }


}
