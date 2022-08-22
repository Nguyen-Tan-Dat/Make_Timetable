package dat.models;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class Course {
    private final String[] data;
    private final byte[] days;
    private final byte[] lessonStart;
    private final byte[] lessonsAmount;

    public Course(String[] data) {
        this.data = data;
        this.days = convertDays(data[6]);
        this.lessonStart = convertNumbers(data[7]);
        this.lessonsAmount = convertNumbers((data[8]));
    }

    public String[] getData() {
        return data;
    }

    public String getName() {
        return data[1];
    }

    public byte getSTC() {
        return Byte.parseByte(data[3]);
    }

    public byte[] getDays() {
        return days;
    }

    public byte[] getLessonStart() {
        return lessonStart;
    }

    public byte[] getLessonAmount() {
        return lessonsAmount;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (String s : data)
            temp.append("\t").append(s);
        return temp.toString();
    }


    public static byte getNumberDays(Course[] list) {
        boolean[] dayStudy = {false, false, false, false, false, false};
        for (Course mh : list)
            for (byte day : mh.getDays())
                dayStudy[day] = true;
        byte number = 0;
        for (boolean b : dayStudy)
            if (b) number++;
        return number;
    }

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
        TableModel tableModelInfo=new DefaultTableModel(tableInfo,new String[]{"Mã MH","Tên MH","Nhóm MH","Số TC","SL mở","SL còn","Ngày học","Tiết BĐ","Số tiết","Giảng viên","Phòng học"});
        JTable jTable=new JTable(tableModelInfo);
        JFrame frame = new JFrame(name);
        frame.setLayout(new BorderLayout(10,10));
        JScrollPane boxTKB=new JScrollPane(tableTKB);
        boxTKB.setPreferredSize(new Dimension(boxTKB.getWidth(),200));
        frame.add(boxTKB,BorderLayout.NORTH);
        JScrollPane boxInfoTable=new JScrollPane(jTable);
        frame.add(boxInfoTable,BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static byte convertDay(String day) {
        String[] days = {"Hai", "Ba", "Tư", "Năm", "Sáu", "Bảy"};
        for (byte i = 0; i < days.length; i++)
            if (days[i].equals(day)) return i;
        return -1;
    }

    private byte[] convertDays(String days) {
        String[] list = days.split(" ");
        byte[] temp = new byte[list.length];
        for (byte i = 0; i < list.length; i++)
            temp[i] = convertDay(list[i]);
        return temp;
    }

    private byte[] convertNumbers(String numbers) {
        String[] list = numbers.split(" ");
        byte[] temp = new byte[list.length];
        for (byte i = 0; i < list.length; i++)
            temp[i] = Byte.parseByte(list[i]);
        return temp;
    }

}
