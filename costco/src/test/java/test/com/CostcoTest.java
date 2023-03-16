/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package test.com;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author bhara
 */
public class CostcoTest {

    private WebDriver driver;
    private String baseUrl;

    public CostcoTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\softwares\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        baseUrl = "https://www.costco.com/";
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterClass
    public void tearDownClass() throws Exception {

    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "C:\\softwares\\driver\\chromedriver.exe");
        //driver = new ChromeDriver();
        //baseUrl = "https://www.costco.com/";
        //driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {

    }

    @Test(priority = 1)
    public void test_SearchTv() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);       
        driver.findElement(By.id("search-field")).click();
        driver.findElement(By.id("search-field")).clear();
        driver.findElement(By.id("search-field")).sendKeys("tv");
        driver.findElement(By.id("formcatsearch")).submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.findElement(By.xpath("//div[@id='category-name-header']/h1")).click();
        //driver.findElement(By.xpath("//div[@id='category-name-header']/h1")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='category-name-header']/h1")).getText(), "TVs");
    }

    @Test(priority =2)
    public void test_SearchTv_by_price() throws Exception {
        driver.findElement(By.xpath("//div[@id='accordion-filter_collapse-1']/div/span[3]/div/label")).click();
        //driver.findElement(By.xpath("//label[@id='refinement_1-3' and @name='$100 to $200']")).click();
        driver.findElement(By.xpath("//div[@id='accordion-filter_collapse-1']/div/span[4]/div/label")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        assertEquals(driver.findElement(By.linkText("Price")).getText(), "Price");
        //assertEquals(driver.findElement(By.xpath("//button[@id='refinement_sf_1-1']/label/span")).getText(), "$100 to $200");
        //driver.findElement(By.xpath("//div[@id='accordion-filter_collapse-5']/div/span[2]/div/label")).click();
    }

    @Test(priority =3)
    public void test_SearchTv_by_brand() throws Exception {
        //       WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));  //20 sec
//        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Brand")));
        driver.findElement(By.xpath("//a[contains(.,'Brand')]")).click();
        driver.findElement(By.xpath("//div[@id='accordion-filter_collapse-5']/div/span[2]/div/label")).click();
        driver.findElement(By.xpath("//div[@id='accordion-filter_collapse-5']/div/span[5]/div/label")).click();
        // driver.findElement(By.xpath("//*[@id=\"accordion-filter_collapse-5\"]/div/span[5]/div/label/span[1]")).click();
        assertEquals(driver.findElement(By.linkText("Brand")).getText(), "Brand");
        //assertEquals(driver.findElement(By.xpath("//button[@id='refinement_sf_2-2']/label/span")).getText(), "Samsung");
    }

    @Test(priority =4)
    public void test_select_first_product() throws Exception {
        driver.findElement(By.id("addbutton-0")).click();
        driver.findElement(By.xpath("//button[@id='plusQty']/em")).click();
    }

    @Test(priority =5)
    public void test_add_to_cart() throws Exception {
        driver.findElement(By.xpath("//input[@type='button' and @id=\"add-to-cart-btn\"]")).click();
    }

    @Test(priority =6)
    public void test_view_cart() throws Exception {
        //driver.findElement(By.xpath("//a[button(.,'viewCartButton')]"));
        // driver.findElement(By.xpath("//div[@id='costcoModalText']/div[2]/div[2]/a/button")).click();
        driver.findElement(By.partialLinkText("View")).click();

    }

    @Test(priority =7)
    public void test_adding_quantity() throws Exception {
        driver.findElement(By.xpath("//button[@id='add-1' and @name='plusQty']/img")).click();
    }

    @Test(priority =8)
    public void test_selecting_the_calender() throws Exception {

        driver.findElement(By.xpath("//input[@class='date-input date-input-field form-control']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        WebElement Schedulepicker = driver.findElement(By.xpath("//input[starts-with(@id,'scheduler_picker')]"));
        if (Schedulepicker != null && Schedulepicker.isDisplayed() && Schedulepicker.isEnabled()) {
            Schedulepicker.click();
            //driver.findElement(By.xpath("//td[starts-with(@id,'dp-')and @aria-disabled='false']")).click();
            //driver.findElement(By.xpath("//td[starts-with(@aria-label,'Tuesday')and text()='28']")).click();
            //driver.findElement(By.cssSelector("input[type='button'][value='Checkout']")).click();
        }

        driver.findElement(By.xpath("//td[starts-with(@aria-label,'Tuesday')and text()='28']")).click();
        //picking earliest date available
        // driver.findElement(By.xpath("//td[starts-with(@id,'dp-')and @aria-disabled='false']")).click();
        //locating earliest first saturday
        //driver.findElement(By.xpath("//td[starts-with(@aria-label,'Saturday')and @aria-disabled='false']")).click();
        // driver.findElement(By.cssSelector("input[type='button'][value='Checkout']")).click();
        //driver.findElement(By.id("shopCartCheckoutSubmitButton")).click();

        driver.findElement(By.cssSelector("input[type='button'][value='Checkout']")).click();
    }
     @Test(priority=9)

    public void test_signing_in() throws Exception {
        //driver.get(baseUrl);
         driver.manage().deleteAllCookies();
        WebElement email = driver.findElement(By.xpath("//input[@id='signInName']"));
        email.sendKeys("say2bharathi@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("Test123!");
        driver.manage().deleteAllCookies();
        WebElement signin = driver.findElement(By.xpath("//button[@id='next' and @type='submit' and text()='Sign In']"));
        signin.click();
       
         System.out.println("sign-in-clicked");
    }

   
}
