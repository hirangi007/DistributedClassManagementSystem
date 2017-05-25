package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import com.rmi.Center;
import com.users.Manager;

public class ManagerClient {	
	
	public HashMap<String, ArrayList<Manager>> managerHashMap;
	public ArrayList<Manager> mtl,lvl,ddo;
	
	public ManagerClient() {
		Manager manager1 = new Manager("MTL0001","Gaurav","Parvadiya");
		Manager manager2 = new Manager("LVL0002","Hirangi","Naik");
		Manager manager3 = new Manager("DDO0003","Jack","Reacher");
		
		mtl = new ArrayList<Manager>();
		mtl.add(manager1);
		
		lvl = new ArrayList<Manager>();
		lvl.add(manager2);
		
		ddo = new ArrayList<Manager>();
		ddo.add(manager3);
		
		managerHashMap = new HashMap<String, ArrayList<Manager>>();
		managerHashMap.put("MTL", mtl);
		managerHashMap.put("LVL", lvl);
		managerHashMap.put("DDO", ddo);
		
	}

	public static void main(String[] args) throws IOException, NotBoundException {
		
		ManagerClient managerClient=new ManagerClient();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Manager ID : ");
		String managerID=reader.readLine();
		if(managerID.substring(0, 3).equals("MTL") && managerID.equals(managerClient.managerHashMap.get("MTL").get(0).getManagerID())){
			Registry registry = LocateRegistry.getRegistry(2964);
			Center stub=(Center)registry.lookup("MTLServer");
		    stub.createSRecord("Hirangi", "Naik", "DSD", "ready", "4/5/17");
		}
		else if(managerID.substring(0, 3).equals("LVL") && managerID.equals(managerClient.managerHashMap.get("LVL").get(0).getManagerID())){
			Registry registry = LocateRegistry.getRegistry(1212);
			Center stub=(Center)registry.lookup("LVLServer");
		    stub.createSRecord("Hirangi", "Naik", "DSD", "ready", "4/5/17");
		}
		else if(managerID.substring(0, 3).equals("DDO") && managerID.equals(managerClient.managerHashMap.get("DDO").get(0).getManagerID())){
			Registry registry = LocateRegistry.getRegistry(1111);
			Center stub=(Center)registry.lookup("DDOServer");
		    stub.createSRecord("Hirangi", "Naik", "DSD", "ready", "4/5/17");
		}
		else
		{
			System.out.println("Manager doesn't exists");
		}
		
	}

}