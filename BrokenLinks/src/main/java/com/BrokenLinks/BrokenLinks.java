package com.BrokenLinks;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BrokenLinks 
{

	public void checkBrokenLinks() throws MalformedURLException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C://Users//pratik//Documents//drivers//chromedriver.exe");
    	driver = new ChromeDriver();
		//WebDriver driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");

		List<String> hrefs = new ArrayList<String>();
		List<WebElement> anchors = driver.findElements(By.tagName("a"));

		for (WebElement anchor : anchors) {

			if ( anchor.getAttribute("href") != null ) 
				hrefs.add(anchor.getAttribute("href"));

		}

		for (String href : hrefs) {

				int responseCode = returnStatusCode(new URL(href));
				if ( responseCode != 200 ) {
					System.out.println("The broken Link is " + href);

				}
				else {

					System.out.println("The working Link is " + href);

				}
			}

		}



	public int returnStatusCode(URL url) {
		Response resp = given().when().get(url);

		return resp.getStatusCode();
	}

}


