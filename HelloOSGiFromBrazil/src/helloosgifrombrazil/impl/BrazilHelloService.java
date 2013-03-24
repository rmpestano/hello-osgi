package helloosgifrombrazil.impl;

import helloosgi.main.api.HelloService;

public class BrazilHelloService implements HelloService{

	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello() {
		 
		return "Olá Mundo!";
	}

	@Override
	public String getLanguage() {
		return "Brasileiro";
	}

}
