package com.bolsadeideas.springboot.backend.apirest;

public class princi {

	public static void main(String[] args) {

		String cods = "10;10;20";
		String[] lcods = cods.split(";");
		String productos = "x";
		for (int i = 0; i < lcods.length; i++) {
			 productos = productos + ";" + lcods[i];
		}
		
		System.out.println(productos.replace("x;",""));
	}

}
