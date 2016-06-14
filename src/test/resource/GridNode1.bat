@echo OFF
cd E:\Study\Softwares\Jar\Selenium
java -jar selenium-server-standalone-2.53.0.jar -role node -Dwebdriver.chrome.driver="E:/Study/Softwares/chromedriver.exe" -hub http://localhost:4444/grid/register -port 5566 -browser browserName=chrome,platform=WINDOWS,maxInstances=2 -maxSession 1 -timeout 200000