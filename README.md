# SeleniumTests_with_Website
I have created selenium test cases for blog web-application(Attached in code)

--------------------------
Selenium version
--------------------------
Selenium: 3.3.1
I have used my cutom framework based on Page Object Modeling:
	Here locators and data are separated from Test Cases
	Driver instance is Thread-safe.
	Parallalism can be achieved.

I have used TestNG as Test Framework


---------------------------
Set Environment variables:
---------------------------
Make sure Latest Browsers of Chrome and Firefox have been installed
Add latest chromedriver and geckodriver from here:
https://sites.google.com/a/chromium.org/chromedriver/downloads
https://github.com/mozilla/geckodriver/releases

Windows:

Add path to - 
Advanced System Settings > Environment Variables > System Variables > Path 

Linux:

You need to add path to your ~/.profile file.
export PATH=$PATH:/path/to/dir

--------------------------
Gradle Setup + Run
--------------------------
1) Move to project directory and open command promp/terminal
2) Make sure all dependencies properly downloaded
3) type below commands:
   gradlew clean
   gradlew eclipse
   gradlew builld [This would run the test cases]

--------------------------
Eclipse Setup + Run Project(Single Thread Execution)
--------------------------
1) Open Eclipse(Neon recommanded)
2) Make sure TestNG plugin installed
3) Import Project
4) Add 'src/test/java' and 'src/test/resources' into build path
5) Build the project
6) Test Case file name is: SmokeTest.java
7) To Run individual test case, please select any test case method(e.g: verifyNewCreatedPostWithImageCanBePublished)
   Right click on selected method name
   Run As > TestNG Test
8) To Run all test cases
   Right click on anywhere(in opened SmokeTest.java file)
   Run As > TestNG Test


--------------------------
Run Project(Multiple thread execution, 3 test cases will run in parallel)
--------------------------
1) Locate tesng.xml (src\test\resources\testng.xml)
2) Right click testng.xml file
3) Run As > TestNG Suite


--------------------------
How to Change Browser
--------------------------
Open consoleInput.properties (src\test\resources\consoleInput.properties) 

for Firefox browser 
automation.browser=FIREFOX

for Chrome browser
automation.browser=CHROME


--------------------------
SCREENSHOT
--------------------------
For Failed test cases user can also see the screenshots at: test-output\screenshots


--------------------------
CAUTION:
--------------------------
If Machine(Server) speed is low or Application response time is too slow then some test case can fail due to Timeout issues.
