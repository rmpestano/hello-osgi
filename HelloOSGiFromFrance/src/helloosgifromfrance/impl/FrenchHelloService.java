package helloosgifromfrance.impl;

import helloosgi.main.api.HelloService;

public class FrenchHelloService implements HelloService{

	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello() {
		 
		return "Bonjour tout le monde!";
	}

	@Override
	public String getLanguage() {
		return "French";
	}

}
