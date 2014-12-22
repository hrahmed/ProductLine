package com.productline.rest;


import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.axis2.AxisFault;


import com.productline.inventory.InventoryServiceStub;
import com.productline.inventory.InventoryServiceStub.CheckItem;
import com.productline.inventory.InventoryServiceStub.CheckItemResponse;
import com.productline.inventory.InventoryServiceStub.ReserveItem;
import com.productline.inventory.InventoryServiceStub.ReserveItemResponse;


@Path("/inventory")
public class InventoryRestService {
	
	private String port;
	private String host;
	private InventoryServiceStub inventoryStub;

	public InventoryRestService() {
		// TODO Auto-generated constructor stub
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("inventoryservice.properties"));
			port = props.getProperty("port");
			host = props.getProperty("host");		
		} catch (Exception e) {
			//swallow
		}
		
		try {
			inventoryStub = new InventoryServiceStub( "http://" + host + ":" +
					port	+
			"/InventoryService/services/InventoryService.InventoryServiceHttpSoap12Endpoint/");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/check")
	public Response checkItem(@QueryParam("itemid") String itemID){
		
		//InventoryServiceStub service;
		// default is false
		String response = "unknown";
		
		try {
			//service = new InventoryServiceStub();
			
			CheckItem checkItem = new CheckItem();
			checkItem.setItemId(itemID);
			CheckItemResponse checkResponse = inventoryStub.checkItem(checkItem);
			response = checkResponse.get_return();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		String output = "Item to be Checked : " + itemID + " with response of: " + response; 
		 
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/reserve")
	public Response reserveItem(@QueryParam("itemid") String itemID){
		
		//InventoryServiceStub service;
		// default is false
		String response = "unknown";
		
		try {
			//service = new InventoryServiceStub();
			
			ReserveItem item = new ReserveItem();
			item.setItemId(itemID);
			ReserveItemResponse reserveResponse = inventoryStub.reserveItem(item);
			response = reserveResponse.get_return();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		String output = "Item to be Reserved : " + itemID + " with response of: " + response; 
		 
		return Response.status(200).entity(output).build();	}
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
}
