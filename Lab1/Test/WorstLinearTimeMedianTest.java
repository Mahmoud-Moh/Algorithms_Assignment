import org.junit.Test;

import java.util.Arrays;
import java.util.random.RandomGenerator ;

import static org.junit.Assert.*;

public class WorstLinearTimeMedianTest {

    int NaiveMedian(int arr[]){
        Arrays.sort(arr);
        return arr[(arr.length + 1) / 2 - 1];
    }

    @Test
    public void test(){
        WorstLinearTimeMedian wm = new WorstLinearTimeMedian(new int[]{0}) ;
        for(int i=0; i<50; i++){
            int size = RandomGenerator.getDefault().nextInt(1, 100);
            int[] arr = new int[size];
            for(int j=0; j<size; j++){
                arr[j] =  RandomGenerator.getDefault().nextInt(0, 100);
            }

            System.out.println("------------------------");
            System.out.println(Arrays.toString(arr));
            int median1 = NaiveMedian(arr);
            wm.setArr(arr);
            int median2 = wm.getMedian();
            System.out.println(Arrays.toString(arr));
            System.out.println(median1 + " " + median2);
            System.out.println("------------------------");
            assert(median1 == median2);
        }
    }

}