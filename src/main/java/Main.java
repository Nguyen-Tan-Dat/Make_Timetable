import model.Course;
import model.CoursesController;
import model.DataWeb;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var k=new ArrayList<String>();
        k.add("862309");
        k.add("841324");
        k.add("841110");
        k.add("841052");
        k.add("841411");
        k.add("841113");
        k.add("841434");
        double start=System.nanoTime();
        CoursesController.searchValidTimetableOnline(k);
        double time=(System.nanoTime() - start)/1000000000;
        System.out.println(time);
    }
}
