package helloosgi.main.impl;

import helloosgi.main.api.HelloService;

import java.io.Serializable;
import java.util.Scanner;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;


public class HelloManager extends Thread implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private volatile boolean active = true;
	
	private BundleContext context;
	
	private Scanner scanner;

	
	public HelloManager(BundleContext context) {
		this.context = context;
	}
	
	/**
	 * manager will run in a separated thread waiting for user input
	 */
	@Override
	public void run() {
		 scanner = new Scanner(System.in);
		 while(active){
			 String input = scanner.nextLine();
			 if(input.equals("l")){
				 listAvailableLanguages();
			 }
			 else if(input.equals("h")){
				 help();
			 }
			 else{
				 try{
					int opt = Integer.parseInt(input);
					System.out.println(this.getHello(opt-1)); 
				 }catch (NumberFormatException nfe) {
					
				}
			 }
		 }
	}
	
	private String getHello(int opt) {
		ServiceReference<HelloService>[] serviceReferences;
		try {
			serviceReferences = (ServiceReference<HelloService>[]) context.getServiceReferences(HelloService.class.getName(), null);
			if(serviceReferences != null && opt >=0 && serviceReferences.length > opt){
				 return ((HelloService)context.getService(serviceReferences[opt])).sayHello();
			}
			else{ 	
				System.out.println("No languages found with given option.");
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void help(){
		 System.out.println("Hello Manager started, type 'l' to list available languages.\n Type 'h' for help. \nType the number of the language to say hello.\n");
	}
	
	@SuppressWarnings("unchecked")
	public void listAvailableLanguages(){
		ServiceReference<HelloService>[] serviceReferences;
		try {
			System.out.println("Listing Available languages:\n");
			serviceReferences = (ServiceReference<HelloService>[]) context.getServiceReferences(HelloService.class.getName(), null);
			if(serviceReferences != null){
				StringBuilder sb = new StringBuilder();
				for (int i = 0;i<serviceReferences.length;i++) {
					sb.append(i+1).append(" - ").append(((HelloService)context.getService(serviceReferences[i])).getLanguage()).append("\n");
				}
				System.out.println(sb.toString());
			}
			else{ 	
				System.out.println("No languages found.");
			}
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void stopThread(){
		active = false;
		scanner.close();
	}

}
