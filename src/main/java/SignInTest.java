import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing()  throws Exception{

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        waitFor(5000);
        driver.switchTo().frame("modal_window");
        WebElement s3= driver.findElement(By.xpath("//button[text()='Sign in']"));
        s3.click();
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        WebElement email= driver.findElement(By.id("email"));
        email.sendKeys("nitin@sunfra.com");
        waitFor(2000);
        WebElement pasword= driver.findElement(By.id("password"));
        pasword.sendKeys("123456");
        waitFor(2000);
        s3.click();
        driver.switchTo().defaultContent();
        waitFor(2000);
        String SearchflightsText = driver.findElement(By.xpath("//h1[text()='Search flights']")).getText();
        Assert.assertTrue(SearchflightsText.contains("Search flights"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
