package dat.models;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class CoursesController {
    private final CourseDAO dao = new CourseDAO();

    private ArrayList subjects = new ArrayList<>();

    private static ArrayList<ArrayList<Course[]>> listCompleted;

    public CoursesController() {
    }

    public ArrayList getData() {
        return subjects;
    }

    public boolean addSubject(String subjectID) {
        var subject = dao.listCourse(subjectID);
        if (subject != null) {
            if (subject.size() > 0) {
                subjects.add(0, subject);
                return true;
            }
        }
        return false;
    }

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

    public static void test(ArrayList listCourses) {
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
                    show(j, tkb, listInfo);
                }
            } else numDay++;
        }
        System.out.println(rs);
        WorkWithFile file = new WorkWithFile();
        file.overwrite("TKB.txt", rs.toString());
    }

    private static ArrayList<JFrame> schedules = new ArrayList<>();

    public static void show(Course[] list, String name, String[][] tableInfo) {
        int r = 10, c = 7;
        String[][] table = new String[r][c];
        for (int i = 0; i < r; i++)
            table[i][0] = 1 + i + "";
        for (Course mh : list) {
            byte[] days = mh.getDays();
            for (byte i = 0; i < days.length; i++) {
                byte lessonStart = mh.getLessonStart()[i];
                byte lessonAmount = mh.getLessonAmount()[i];
                byte day = days[i];
                for (byte j = lessonStart; j < lessonStart + lessonAmount; j++) {
                    table[j - 1][day + 1] = mh.getName();
                }
            }
        }
        TableModel model = new DefaultTableModel(table, new String[]{"Tiết", "Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy"});
        JTable tableTKB = new JTable(model);
        TableModel tableModelInfo = new DefaultTableModel(tableInfo, new String[]{"Mã MH", "Tên MH", "Nhóm MH", "Số TC", "SL mở", "SL còn", "Ngày học", "Tiết BĐ", "Số tiết", "Giảng viên", "Phòng học"});
        JTable jTable = new JTable(tableModelInfo);
        JFrame frame = new JFrame(name);
        frame.setLayout(new BorderLayout(10, 10));
        JScrollPane boxTKB = new JScrollPane(tableTKB);
        boxTKB.setPreferredSize(new Dimension(boxTKB.getWidth(), 200));
        frame.add(boxTKB, BorderLayout.NORTH);
        JScrollPane boxInfoTable = new JScrollPane(jTable);
        frame.add(boxInfoTable, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        schedules.add(frame);
    }

    public static void closeSchedules() {
        for (var i : schedules) {
            i.dispose();
        }
        schedules = new ArrayList<>();
    }

    public void arrangeCourse() {
        test(this.subjects);
    }
}
