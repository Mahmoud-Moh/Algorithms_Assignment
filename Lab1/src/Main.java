import java.util.random.RandomGenerator;

public class Main {
    public static void main(String args[]){
       /* RandomizedMedian rm = new RandomizedMedian(new int[]{9,7,8,4,8,9,9,2,5});
        WorstLinearTimeMedian wm = new WorstLinearTimeMedian(new int[]{9,7,8,4,8,9,9,2,5});
        int median = rm.getMedian();
        int median2 = wm.getMedian();
        System.out.println(median);
        System.out.println(median2);*/
        for(int i=0; i<10; i++){
            int x = RandomGenerator.getDefault().nextInt(-5, 5);
            int y = RandomGenerator.getDefault().nextInt(-5, 5);
            System.out.println(x + " " + y);
        }
    }
}
