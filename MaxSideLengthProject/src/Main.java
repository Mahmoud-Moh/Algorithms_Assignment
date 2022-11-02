import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[]){
        String ip_path = args[0];
        ip_path = ip_path.substring(1, ip_path.length());
        System.out.println(ip_path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(ip_path));
            String line;
            ArrayList<Point> points = new ArrayList<>();
            while((line = br.readLine()) != null) {
                String[] xy = line.split(" ");
                points.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
            }
            Point[] points_arr = new Point[points.size()];
            for(int i=0; i<points.size(); i++){
                points_arr[i] = points.get(i);
            }
            while((line = br.readLine()) != null) {
                String[] xy = line.split(" ");
                points.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
            }
            br.close();
            MaxSideLength maxSideLength = new MaxSideLength(points_arr);
            File theFile = new File(ip_path);
            String parent = theFile.getParent();
            parent += "\\output.txt";
            BufferedWriter bw = new BufferedWriter(new FileWriter(parent));
            bw.write(Double.toString(maxSideLength.calcMaxSideLength()));
            bw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
