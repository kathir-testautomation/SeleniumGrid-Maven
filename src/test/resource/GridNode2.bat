@echo OFF
cd E:\Study\Softwares\Jar\Selenium
java -jar selenium-server-standalone-2.53.0.jar -role node -hub http://localhost:4444/grid/register -port 5577 -browser browserName=firefox,platform=WINDOWS,maxInstances=3 -maxSession 1 -timeout 200000