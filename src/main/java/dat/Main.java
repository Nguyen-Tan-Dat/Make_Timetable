package dat;

import dat.models.CourseDAO;
import dat.models.CoursesController;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		CourseDAO dao = new CourseDAO();
		ArrayList<String> list = new ArrayList<>();
		list.add("841111");
		ArrayList listLoad = new ArrayList<>();
		for (String i : list)
			listLoad.add(dao.listCourse(i));
		CoursesController.searchValidTimetableOnline(listLoad);
	}
}
