package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

public class DataWeb {
    private final WebDriver driver;

    public DataWeb(String name, String password) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nguye\\IntelliJIDEAProjects\\CourseSchedule\\src\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("http://thongtindaotao.sgu.edu.vn");
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtTaiKhoa")).sendKeys(name);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtMatKhau")).sendKeys(password);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_btnDangNhap")).click();
        driver.findElement(By.id("ctl00_menu_lblDangKyMonHoc")).click();
    }

    public ArrayList<Course> listCourse(String courseID){
        driver.findElement(By.id("txtMaMH1")).sendKeys(courseID);
        driver.findElement(By.id("btnLocTheoMaMH1")).click();
        driver.getPageSource();
        var listCourse = driver.findElement(By.id("divTDK"));
        var tds = listCourse.findElements(By.tagName("td"));
        ArrayList<Course> result = new ArrayList<>();
        for (int i = 0; i < tds.size() / 17; i++) {
            String[] data = new String[17];
            try {
                for (int j = 0; j < data.length; j++) {
                    String cell = tds.get(i * 17 + j).getText();
                    if (cell.contains("\n")) data[j] = cell.replaceAll("\n", "");
                    else data[j] = cell;
                }
                String[] filterData = {data[1], data[2], data[3], data[5], data[8], data[9], data[11], data[12], data[13], data[15]};
                if (!data[9].equals("H?t"))
                    result.add(new Course(filterData));
            } catch (Exception ignored) {
                ignored.printStackTrace();
                System.out.println(courseID);
            }
        }
        return result;
    }
    public ArrayList<ArrayList<Course>> getListCourses(ArrayList<String> listSubject) {
        ArrayList<ArrayList<Course>> listCourses = new ArrayList<>();
        for (String s : listSubject)
            listCourses.add(listCourse(s));
        driver.quit();
        return listCourses;
    }
}
