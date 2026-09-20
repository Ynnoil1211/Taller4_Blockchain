/*
Modificado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006
 */
package co.blockchain.app;

public abstract class Bloque {
	int id;
	String data;
	String hashPrev;
	Bloque sig;
	static int cnt=0;

	public abstract String calcularHash();
	//Constructor para primer elemento del blockchain
	public Bloque (){
		this.hashPrev=null;
		this.sig=null;
	}
	public abstract boolean esValido(Blockchain blockchain);

	public String getData(){
	    return data;
	}
	public int getId(){
	    return this.id;
	}

	public String getHashPrev(){
		return this.hashPrev;
	}
	public String getHash(){
		return this.calcularHash();
	}
}
