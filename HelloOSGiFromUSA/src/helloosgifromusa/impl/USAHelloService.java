package helloosgifromusa.impl;

import helloosgi.main.api.HelloService;

public class USAHelloService implements HelloService{

	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello() {
		 
		return "Hello World!";
	}

	@Override
	public String getLanguage() {
		return "English";
	}

}
