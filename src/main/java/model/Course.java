package model;

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

    public static boolean isSchedule(Course[] list) {
        boolean[][] table = new boolean[16][7];
        for (byte i = 1; i < 10; i++)
            for (byte j = 0; j < 7; j++)
                table[i][j] = false;
        for(byte i=0;i<7;i++)
            table[0][i]=true;
        for(int i=10;i<16;i++)
            for (byte j = 0; j < 7; j++)
                table[i][j] = true;
        for (Course mh : list) {
            byte[] days = mh.getDays();
            for (byte i = 0; i < days.length; i++) {
                byte lessonStart = mh.getLessonStart()[i];
                byte lessonAmount = mh.getLessonAmount()[i];
                byte day = days[i];
                for (byte j = lessonStart; j < lessonStart + lessonAmount; j++)
                    if (table[j][day]) return false;
                    else table[j][day] = true;
            }
        }
        return true;
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
