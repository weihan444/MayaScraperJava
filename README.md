# MayaScraper (Java)
Maya Scraper is a java program that scrape the [UM Maya sites](https://maya.um.edu.my/sitsvision/wrd/siw_lgn) for the timetable database and output them into .csv file.

## Getting Started
You can get the latest release build by downloading from the [release](https://github.com/weihan444/MayaScraperJava/releases) tab or you can clone the project and build it yourself.

### Prerequisite
Download the chrome driver based on the current chrome version installed on your computer from [here](https://chromedriver.chromium.org/downloads).

Then put it into a folder named `\Driver\` in the same directory of your `JAR` file

For Example, if your `JAR` file is in a folder called `MayaScraper` on your desktop:
```
C:\User\<Your Username>\Desktop\MayaScraper\Driver\chromedriver.exe
```
### Installing
After cloning the project, you can do
```
mvn package
```
to install all the dependencies and build them into a JAR file.

## Running
To run the project on Windows, open up a command prompt or powershell and run
```
java -jar MayaScraping-1.0.jar
```
A prompt asking for your maya login username should show up
```
Enter Username:
```

## Built With
* [Selenium Java WebDriver](https://www.selenium.dev/documentation/webdriver/) - A WebDriver for browser automation
* [Maven](https://maven.apache.org/) - Software Project Management tool
* [Jsoup](https://jsoup.org/) - For parsing html source returned by the Selenium WebDriver

## Contributing
Feel free to submit a pull request if you wish to contribute to this project.

