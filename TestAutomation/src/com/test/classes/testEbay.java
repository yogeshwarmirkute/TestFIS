package com.test.classes;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.WindowType;
	import org.openqa.selenium.chrome.ChromeDriver;

	import java.util.Iterator;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	public class testEbay {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub

			System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			// Step 1: Open browser
			driver.manage().window().maximize();

			// Step 2: Navigate to ebay.com
			driver.get("https://www.ebay.com");

			Thread.sleep(4000);
			
			// Step 3: Search for 'book'
			WebElement searchBox = driver.findElement(By.id("gh-ac"));
			searchBox.sendKeys("book");
			driver.findElement(By.id("gh-search-btn")).click();

			// Step 4: Click on the first book in the list
			WebElement firstBook = driver.findElement(By.xpath("(//span[@role='heading'])[3]"));
			firstBook.click();

			Thread.sleep(4000);

			Set<String> s = driver.getWindowHandles();
			Iterator<String> i = s.iterator();

			while (i.hasNext()) {
				String childWindow = i.next();
				driver.switchTo().window(childWindow);
				System.out.println(driver.switchTo().window(childWindow).getTitle());
			}
			
			Thread.sleep(4000);
			
			// Step 5: Click on ‘Add to cart’
			WebElement addToCartButton = driver.findElement(By.id("atcBtn_btn_1"));
			addToCartButton.click();
			Thread.sleep(8000);
			
			// Step 6: Verify the cart has been updated
			WebElement cartCount = driver.findElement(By.xpath("//span[@class='gh-cart__icon']"));
			String itemCount = cartCount.getText();
			System.out.println("Item Count is  " + itemCount);

			if (!itemCount.equals("0")) {
				System.out.println("Test Passed: Item successfully added to cart.");
			} else {
				System.out.println("Test Failed: Cart is empty.");
			}

			driver.quit();
		}
	}

