package dat;

import dat.models.CourseDAO;
import dat.models.CoursesController;

import java.util.ArrayList;

public class RunNoGUI {

	public static void main(String[] args) {
		CourseDAO dao = new CourseDAO();
		ArrayList<String> list = new ArrayList<>();
		list.add("832057");
		list.add("832117");
		ArrayList listLoad = new ArrayList<>();
		for (String i : list)
			listLoad.add(dao.listCourse(i));
		CoursesController.test(listLoad);
	}
}
