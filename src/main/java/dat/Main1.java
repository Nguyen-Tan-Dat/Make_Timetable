package dat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main1 {
    public static WebDriver driver;

    public static void main(String[] args) throws AWTException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com/");

        driver.findElement(By.id("email")).sendKeys("0775820223");
        driver.findElement(By.id("pass")).sendKeys("D@t16/05/2000");
        driver.findElement(By.name("login")).click();
        sleep(5000);
//        var urls = getURLS("https://www.facebook.com/profile.php?id=100074613751858");
//        int start;
//        int n = 0;
//        for (int loop = 0; loop < 2; loop++) {
//            start = n;
//            n = urls.size();
//            try {
//                for (int i = start; i < n; i++) {
//                    var list = getURLS(urls.get(i));
//                    for (var j : list) {
//                        urls.add(j);
//                    }
//                }
//            } catch (Exception e) {
//            }
//        }
//
//        for (var i : urls) {
//            System.out.println(i);
//        }
//        driver.close();
        (new Scanner(System.in)).next();
        removeFriend("https://www.facebook.com/profile.php?id=100074613751858");
//        addFriend("https://www.facebook.com/profile.php?id=100088012743442");
//        for (int i=0;i<10000;i++){
//            confirm();}
    }

    public static ArrayList<String> getURLS(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "&sk=friends','_parent');");
        ArrayList<String> urls = new ArrayList<>();
        var list = driver.findElements(By.cssSelector("div[class='x78zum5 x1q0g3np x1a02dak x1qughib']"));
        for (var i : list) {
            var a = i.findElements(By.tagName("a"));
            int index = 0;
            for (var l : a) {
                if (index % 3 == 0) {
                    try {
                        urls.add(l.getAttribute("href"));
                    } catch (Exception e) {
                    }
                }
                index++;
            }
        }
        return urls;
    }

    public static void addFriend(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_parent');");
        ((JavascriptExecutor) driver).executeScript("""
                document.querySelector('[aria-label="Thêm bạn bè"]').click();
                """);

    }

    public static void removeFriend(String url) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "','_parent');");
        ((JavascriptExecutor) driver).executeScript("""
             let bb=document.querySelectorAll('[aria-label="Bạn bè"]');
             bb[bb.length-1].click();
             """);
        sleep(5000);
        ((JavascriptExecutor) driver).executeScript("""
             let cc=document.querySelectorAll('[role="menuitem"]');
             cc[cc.length-1].click();
             """);
        sleep(500);
        ((JavascriptExecutor) driver).executeScript("""
                document.querySelector('[aria-label="Xác nhận"]').click();
                """);
    }

    public static void confirm() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open('" + "https://www.facebook.com/friends" + "','_parent');");
        ((JavascriptExecutor) driver).executeScript("""
                const nodeList = document.querySelectorAll('[aria-label="Xác nhận"]');
                for (let i = 0; i < nodeList.length; i++) {
                  nodeList[i].click();
                }
                """);
        sleep(10000);
        ((JavascriptExecutor) driver).executeScript("""
                const nodeList = document.querySelectorAll('[aria-label="Thêm bạn bè"]');
                for (let i = 0; i < nodeList.length; i++) {
                  nodeList[i].click();
                }
                """);
    }
}
