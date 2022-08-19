package model;


import java.util.ArrayList;

import static model.Course.isSchedule;

public class CoursesController {

    private static Account account = new Account("3118410076", "D@t16/05/2000");

    public CoursesController(Account account) {
        CoursesController.account = account;
    }

    public CoursesController() {
    }

    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private static ArrayList<ArrayList<Course[]>> listCompleted;

    private static void arrange(ArrayList<ArrayList<Course>> listCourses, Course[] listAction, int indexList) {
        if (indexList == listAction.length) {
            if (isSchedule(listAction)) {
                Course[] newTableTime = new Course[listAction.length];
                System.arraycopy(listAction, 0, newTableTime, 0, listAction.length);
                listCompleted.get(Course.getNumberDays(newTableTime) - 1).add(newTableTime);
            }
        } else for (int i = 0; i < listCourses.get(indexList).size(); i++) {
            listAction[indexList] = listCourses.get(indexList).get(i);
            arrange(listCourses, listAction, indexList + 1);
        }
    }

    public static void searchValidTimetableOnline(ArrayList<String> listSubject) {
        DataWeb web = new DataWeb(account.getUsername(), account.getPassword());
        var listCourses = web.getListCourses(listSubject);
        listCompleted = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            listCompleted.add(new ArrayList<>());
        arrange(listCourses, new Course[listSubject.size()], 0);

        int numDay = 1;
        StringBuilder rs=new StringBuilder();
        for (var i : listCompleted)
            if (i.size() > 0) {
                int stc = 0;
                for (int o = 0; o < listSubject.size(); o++)
                    stc += i.get(0)[o].getSTC();
                rs.append("Số tín chỉ " + stc+"\n");
                rs.append(" Số ngày " + numDay+"\n");
                int number = 1;
                for (var j : i) {
                    rs.append("TKB-" + number+++"\n");
                    for (var k : j)
                        rs.append(k+"\n");
                }
            } else numDay++;
        System.out.println(rs.toString());
        WorkWithFile work=new WorkWithFile();
        work.overwrite("TKB.txt",rs.toString());
    }

}
