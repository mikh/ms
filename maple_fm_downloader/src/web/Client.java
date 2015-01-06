package web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Client {
	WebDriver driver;
	
	public Client(String baseurl, String profile, String firefox_executable){
		if(profile != null){
			ProfilesIni fprofile = new ProfilesIni();
			FirefoxProfile ffprofile = fprofile.getProfile(profile);
			driver = new FirefoxDriver(ffprofile);
		} else{
			FirefoxProfile ffprofile = new FirefoxProfile();
			FirefoxBinary ffbinary = new FirefoxBinary(new File(firefox_executable));
			ffprofile.setPreference("webdriver.firefox.bin", firefox_executable);
			driver = new FirefoxDriver(ffbinary, ffprofile);
		}
		driver.get(baseurl);
	}
	
	public void getPage(String URL){
		driver.get(URL);
	}
	
	public String getPageSource(){
		return driver.getPageSource();
	}
	
	public void close(){
		driver.close();
	}
	
	public void printPage(){
		System.out.println(driver.getPageSource());
	}
	public void printPage(String filename){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
			bw.write(driver.getPageSource());
			bw.close();
		} catch(IOException e){
			System.out.println("Error! Could not write to file " + filename);
		}
	}
	
	public String getPageTitle(){
		return driver.getTitle();
	}
	
	public void sendKeys(String element, String value, boolean name){
		WebElement el = null;
		if(name){
			el = driver.findElement(By.name(element));
		} else{
			el = driver.findElement(By.id(element));
		}
		el.sendKeys(value);
	}
	
	public void submit(String element, boolean name){
		WebElement el = null;
		if(name){
			el = driver.findElement(By.name(element));
		} else{
			el = driver.findElement(By.id(element));
		}
		el.submit();
	}
	
	public void click(String element, boolean name){
		WebElement el = null;
		if(name){
			el = driver.findElement(By.name(element));
		} else{
			el = driver.findElement(By.id(element));
		}
		el.click();
	}
	
	public void clickLink(String findFormat, String field){
		if(findFormat.equals("cssSelector")){
			field = "a[href*=\'" + field + "\']";
			driver.findElement(By.cssSelector(field)).click();
		}
	}
	
	public void acceptAlert(){
		driver.switchTo().alert().accept();
	}
	
	public boolean checkForAlert(int time_wait){
		try{
			WebDriverWait wait = new WebDriverWait(driver, time_wait);
			if(wait.until(ExpectedConditions.alertIsPresent()) == null)
				return false;
			else
				return true;
		} catch(TimeoutException e){
			//alert wait timeout
			return false;
		}
	}
	
	public void setSelectOption(String path_to_select, String value_to_set){
		Select select = new Select(driver.findElement(By.xpath(path_to_select)));
		//select.deselectAll();
		select.selectByValue(value_to_set);
	}
}
