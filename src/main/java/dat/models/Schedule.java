package dat.models;

public class Schedule {
    private final Course[] courses;

    public Schedule(Course[] courses) {
        this.courses = courses;
    }

    public boolean isSchedule() {
        boolean[][] table = new boolean[16][7];
        for (byte i = 1; i < 10; i++)
            for (byte j = 0; j < 7; j++)
                table[i][j] = false;
        for (Course mh : courses) {
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
}
