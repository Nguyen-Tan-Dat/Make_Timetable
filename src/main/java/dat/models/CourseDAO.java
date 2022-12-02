package dat.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class CourseDAO {
    WebDriver driver;
    public CourseDAO(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("http://thongtindaotao.sgu.edu.vn");
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtTaiKhoa")).sendKeys("3118410076");
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtMatKhau")).sendKeys("D@t16/05/2000");
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_btnDangNhap")).click();
        driver.findElement(By.id("ctl00_menu_lblDangKyMonHoc")).click();
    }
    public ArrayList<Course> listCourse(String courseID){
        driver.findElement(By.id("txtMaMH1")).sendKeys(courseID);
        driver.findElement(By.id("btnLocTheoMaMH1")).click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
                String[] filterData = {data[1], data[2], data[3], data[5], data[8], data[9], data[11], data[12], data[13], data[15],data[14]};
                if (!data[9].equals("H?t"))
                    result.add(new Course(filterData));
            } catch (Exception ignored) {
                ignored.printStackTrace();
                return null;
            }
        }
        return result;
    }

}
