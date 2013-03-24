package helloosgi.main.api;

import java.io.Serializable;

public interface HelloService extends Serializable{
	

	/**
	 * 
	 * @return "hello world" using the current language
	 */
	public String sayHello();
	
	/**
	 * 
	 * @return the language name
	 */
	public  String getLanguage();

}
