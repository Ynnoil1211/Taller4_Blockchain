/*
Modificado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006
 */
package co.edu.unicartagena.list;

public class Bloque {
	String dato;
	int id;
	String hashPrev;
	String hashActual
	Bloque sig;
	static int cnt=0;

	public String calcularHash (){
		int hashInt = Objects.hash(dato, id, hashPrev, hashActual);
		return Integer.toHexString(hashInt);
	}
	//Constructor para primer elemento del blockchain
	public Bloque (String dato){
		this.dato=dato;
		this.id=cnt++;
		this.hashPrev=null;
		this.hashActual=calcularHash();
		this.sig=null;
	}
	//Constructor para insercion de bloques
	public Bloque(String dato, String hashPrev){
		this.dato=dato;
		this.id=cnt++;
		this.hashPrev=hashPrev;
		this.hashActual=calcularHash();
		this.sig=null;
	}
	//Constructor para delete & update
	//Se usa el mismo id del bloque a actualizar/eliminar
	public Bloque(String dato, int id, String hashPrev){
		this.dato=dato;
		this.id=id;
		this.hashPrev=hashPrev;
		this.hashActual=calcularHash();
		this.sig=null;
	}

	public String getDato(){
		return this.dato;
	}
	public String getHashPrev(){
		return this.hashPrev;
	}
	public String getHashActual(){
		return this.hashActual;
	}
}