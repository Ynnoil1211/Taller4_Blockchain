/*
Copyright (c) 2019 - 2026, Juan Carlos Garcia Ojeda, Universidad de Cartagena
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.
   
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/*
Modificado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006
 */
package co.edu.unicartagena.list;
public class Blockchain {
    Bloque primerBloque;
    Bloque ultimoBloque;
    int tamaño = 0;
    public Blockchain(){
    	limpiar();
    }
    private void limpiar(){
    	primerBloque = null;
        tamaño = 0;
    }
    private boolean estaVacia(){
    	if (tamaño == 0)
            return true;
	else
            return false;
    }
	//Para un nuevo blockchain
    public boolean adicionar(String dato){
        //ADICIONAR AL COMIENZO DE LA Blockchain
        Bloque nuevoBloque = Bloque(dato);
        if(estaVacia()) {
            primerBloque = nuevoBloque;
            ultimoBloque = nuevoBloque;
        }
        else{
           adicionarFinal(dato);
	    }
        tamaño=tamaño+1;
        return true;
    }

    /*
     * adicionarFinal
     *
     * adiciona un Bloque al final de la Blockchain
     *
     */
    public boolean buscarPorHash(Bloque bloque){
        return true;
    }
    public boolean buscarPorId(Bloque bloque){
        return true;
    }

    public boolean adicionarFinal(String dato){
        String hashPrev = this.ultimoBloque.getHashActual();
        Bloque nuevoBloque = Bloque(dato, hashPrev);
        ultimoBloque.sig = nuevoBloque;
        ultimoBloque = nuevoBloque;
        return true;
    }

    public boolean eliminar(int id, String dato){
        String hashPrev = this.ultimoBloque.getHashActual();
        Bloque nuevoBloque = Bloque(dato, id, hashPrev);
        ultimoBloque.sig = nuevoBloque;
        ultimoBloque = nuevoBloque;
        return true;
    }

    public boolean update(int id, String dato){
        Bloque temp = Bloque
        String hashPrev = this.ultimoBloque.getHashActual();
        Bloque nuevoBloque = Bloque(dato, hashPrev);
        ultimoBloque.sig = nuevoBloque;
        ultimoBloque = nuevoBloque;
        return true;
    }



    /*
     * adicionarEntreBloques
     * 
     * adiciona un Bloque entre Bloques en order no decreciente
     * 
     */
        
//    public boolean adicionarEntreBloques(Bloque Bloque){
//        Bloque nuevoBloque = Bloque;
//	Bloque temp = null;
//	Bloque anterior = null;
//	if(estaVacia())
//            primerBloque = nuevoBloque;
//	else{
//            temp = primerBloque;
//            anterior = temp;
//            boolean band=false;
//            //CASO 1 : Blockchain tiene un elemento
//            if(tamaño==1){
//                if(Integer.parseInt(temp.getDato().toString()) < Integer.parseInt(nuevoBloque.getDato().toString())){
//                    //if(temp.getDato() < Bloque.getDato()){
//                    primerBloque.sig=nuevoBloque;
//                }
//		else{
//                    nuevoBloque.sig = primerBloque;
//                    primerBloque=nuevoBloque;
//		}
//            }
//            else{//Blockchain TIENE MAS DE UN ELEMENTO
//                while(temp!=null && Integer.parseInt(temp.getDato().toString()) < Integer.parseInt(nuevoBloque.getDato().toString())){
//                    anterior=temp;
//                    temp = temp.sig;
//                    band=true;
//		}
//		if(band){//
//                    nuevoBloque.sig =anterior.sig;
//                    anterior.sig = nuevoBloque;
//		}
//		else{//El valor del nuevo Bloque es menor
//                    nuevoBloque.sig=temp;
//                    primerBloque=nuevoBloque;
//		}
//            }
//	}
//	tamaño=tamaño+1;
//	return true;
//    }
	
    public void imprimir(){
        if(!estaVacia()){
            Bloque temp = primerBloque;
            while(temp!=null){
                System.out.println(temp.getDato());
                System.out.println(temp.getHashPrev());
                System.out.println(temp.getHashActual());
                temp=temp.sig;
            }
	}
        else{
            System.out.println("La Blockchain esta vacia");
        }
    }
}