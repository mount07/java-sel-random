package testPkg.nip;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImgDwnUIPageant {
	static File dir;
	static WebDriver driver;

	public static void main(String[] args) throws IOException, InterruptedException {
		getImgLinks();

		driver.close();
	}

	public static void getImgLinks() throws InterruptedException, IOException {
		driver = new ChromeDriver();
		driver.get(
				"https://madlad2590:Madmax@2021@members.purenudism.com/members_only/access/event-pictures.cgi?p=3&event=21");
		Thread.sleep(2000);

		driver.get("https://members.purenudism.com/members_only/access/pageant-details.cgi");
		Thread.sleep(2000);

		for (int i = 1; i <= 132; i++) {
			WebElement numBox = driver.findElement(By.cssSelector("input[onclick='this.select();']"));
			WebElement goBtn = driver.findElement(By.cssSelector("input[type='image']"));

			numBox.clear();
			numBox.sendKeys("" + i);
			Thread.sleep(1000);

			goBtn.click();
			Thread.sleep(2000);
			WebElement link = driver.findElement(By
					.cssSelector("[href*='https://www.nudist-theater.com/members/videos/mp4/pageants']:nth-child(3)"));

			System.out.println(link.getAttribute("href"));
		}

		Thread.sleep(2000);
	}

}
