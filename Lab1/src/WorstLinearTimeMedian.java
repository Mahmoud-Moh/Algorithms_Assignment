import java.io.File;
import java.util.Arrays;
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
        for (int j = p; j < n - n%5; j+=5) {
            int[] arr = new int[5];
            System.arraycopy(A, j, arr, 0, 5);
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

        int median1 = NaiveMedian(new int[]{-59, -40, -58, -42, 20, 63, -57, 98, -96, -26});
        wm.setArr(new int[]{-59, -40, -58, -42, 20, 63, -57, 98, -96, -26});
        int x = wm.getMedian();
        System.out.println(median1 + " " + x);
    }
}
