ProductLine
===========

ReadMe for ProductLine Application

## Build ##
Import project into eclipse
Create tomcat server in eclipse

## Installation: ##
Deploy InventoryService.war and ProductLineInterface.war files to the tomcat webapp directory
Restart Tomcat

## Quick Test: ##
Issue the following URL to ensure that the webapp is up and running
http://localhost:8080/ProductLineInterface/index.jsp

Issue following URL to ensure that the Rest WS calls are working
http://localhost:8080/ProductLineInterface/rest/hello/hi

Issue the following URL to ensure checkItem and receiveItem Rest WS calls are working
http://localhost:8080/ProductLineInterface/rest/inventory/check?itemid=111
http://localhost:8080/ProductLineInterface/rest/inventory/reserve?itemid=111

## Extend App to use ProductLine: ##

Update inventory.properties (found under ~/InventoryService/WEB-INF/classes) file to add item id's and associated messages. If item id's match the associated message will be returned. 
	Sample content:
		itemList=111,222,333
		itemStatus=Back Order,Not Available,Back Order

## How to use Apache httpclient libraries to call ProductLine application: ##
Add httpclient-4.0.3.jar to your applications lib directory
Add ProductLineRemoteApi.java to your application and and use the checkItem (..) and reserveItem(...) API's.
		ProductLineRemoteApi pl = new ProductLineRemoteApi();
		String response = pl.checkItem("111");
