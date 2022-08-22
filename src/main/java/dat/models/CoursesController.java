package dat.models;


import dat.controls.MainFrame;

import java.util.ArrayList;

public class CoursesController {
    public CoursesController() {
    }


    private static ArrayList<ArrayList<Course[]>> listCompleted;

    private static void arrange(ArrayList<ArrayList<Course>> listCourses, Course[] listAction, int indexList) {
        if (indexList == listAction.length) {
            Schedule s = new Schedule(listAction);
            if (s.isSchedule()) {
                Course[] newTableTime = new Course[listAction.length];
                System.arraycopy(listAction, 0, newTableTime, 0, listAction.length);
                listCompleted.get(Course.getNumberDays(newTableTime) - 1).add(newTableTime);
            }
        } else for (int i = 0; i < listCourses.get(indexList).size(); i++) {
            listAction[indexList] = listCourses.get(indexList).get(i);
            arrange(listCourses, listAction, indexList + 1);
        }
    }

    public static void searchValidTimetableOnline() {
        var listCourses = MainFrame.existingList;
        listCompleted = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            listCompleted.add(new ArrayList<>());
        arrange(listCourses, new Course[listCourses.size()], 0);
        StringBuilder rs = new StringBuilder();
        int numDay = 1;
        int number = 1;
        for (var i : listCompleted) {
            if (i.size() > 0) {
                int stc = 0;
                for (int o = 0; o < listCourses.size(); o++)
                    stc += i.get(0)[o].getSTC();
                rs.append("Số tín chỉ " + stc + "\n");
                rs.append(" Số ngày " + numDay + "\n");
                for (var j : i) {
                    String tkb = "TKB-" + number++;
                    rs.append(tkb + "\n");
                    String[][] listInfo = new String[j.length][15];
                    int s = 0;
                    for (var k : j) {
                        rs.append(k + "\n");
                        for (int o = 0; o < k.getData().length; o++)
                            listInfo[s][o] = k.getData()[o];
                        s++;
                    }
                    Course.show(j, tkb, listInfo);
                }
            } else numDay++;
        }
        System.out.println(rs);
        WorkWithFile work = new WorkWithFile();
        work.overwrite("TKB.txt", rs.toString());
    }

}
