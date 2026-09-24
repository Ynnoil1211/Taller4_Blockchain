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
package co.blockchain.linkedlist;

import co.blockchain.linkedlist.Bloque.Tipo;

public class Blockchain {

    private Bloque cabeza;
    private Bloque cola;
    private int size;

    public Blockchain() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    private boolean push(Bloque bloque) {
        if (bloque == cola || bloque.sig != null) {
            System.out.println("El bloque ya esta encadenado");
            return false;
        }
        if (!validarBloque(bloque)) {
            System.out.println("Bloque rechazado");
            return false;
        }
        if (cola == null) {
            cabeza = bloque;
            bloque.procesarBloque(size, null);
        }
        else {
            String hashPrev = cola.getHashActual();
            bloque.procesarBloque(size, hashPrev);
            cola.sig = bloque;
        }
        cola = bloque;
        size = size + 1;
        System.out.println(
            "Bloque #" + bloque.idBloque + " añadido (Tx ID: " + bloque.transaccionId + ") con hash " + bloque.getHashActual()
        );
        System.out.println();
        return true;
    }

    public boolean agregar(String data) {
        Bloque nuevoBloque = Bloque.add(data);
        return push(nuevoBloque);
    }

    public boolean eliminar(int transaccionId) {
        Bloque orig = buscarPorTransaccion(transaccionId);
        if (orig == null) {
            System.out.println("Transaccion no encontrada");
            return false;
        }
        Bloque nuevoBloque = Bloque.delete(transaccionId, orig.getData());
        return push(nuevoBloque);
    }

    public boolean actualizar(int transaccionId, String data) {
        Bloque orig = buscarPorTransaccion(transaccionId);
        if (orig == null) {
            System.out.println("Transaccion no encontrada");
            return false;
        }
        Bloque nuevoBloque = Bloque.update(transaccionId, data);
        return push(nuevoBloque);
    }

    ///Metodos de Validacion interna
    //
    private boolean validarBloque(Bloque bloque) {
        return switch (bloque.tipo) {
            case ADD -> true;
            case UPDATE, DELETE -> buscarPorTransaccion(bloque.transaccionId) != null;
        };
    }

    public boolean validarCadena() {
        if(cabeza==null){
            System.out.println("Blockchain vacia. ");
        } else {
            //Para validar una cadena, comparamos tanto el hashPrev de actual.sig con el hashActual del bloque actual
            //como el mismo hashActual guardado en memoria con el hash Calculado
            Bloque actual = cabeza;
            while (actual != null) {
                if (!actual.getHashActual().equals(actual.calcularHash())) {
                    System.out.println("Blockchain corrompida. Bloque dañado: ");
                    actual.mostrarDatosBloque();
                    return false;
                }
                if(actual.sig != null && !actual.sig.getHashPrev().equals(actual.getHashActual())){
                    System.out.println("Blockchain corrompida. Bloque dañado: ");
                    actual.sig.mostrarDatosBloque();
                    return false;
                }
                actual = actual.sig;
            }
            System.out.println("Blockchain Valida. ");
        }
        return true;
    }

    /// Metodos para busqueda:
    //
    public Bloque buscarPorHash(String hash) {
        Bloque actual = cabeza;
        while (actual != null) {
            if (actual.getHashActual().equals(hash)) return actual;
            actual = actual.sig;
        }
        return null;
    }

    public Bloque buscarPorTransaccion(int transaccionId) {
        Bloque resultado = null;
        Bloque actual = cabeza;
        while (actual != null) {
            if (actual.transaccionId == transaccionId) {
                if (actual.tipo == Tipo.DELETE) return null;
                else resultado = actual;
            }
            actual = actual.sig;
        }
        return resultado;
    }

    public Bloque buscarPorId(int idBloque){
        Bloque actual = cabeza;
        while (actual != null) {
            if (actual.idBloque == idBloque) return actual;
            actual = actual.sig;
        }
        return null;
    }

    ///Metodos para Lectura:
    //
    public void imprimirPorIDBloque(int idBloque){
        Bloque busqueda = buscarPorId(idBloque);
        if(busqueda != null) {
            busqueda.mostrarDatosBloque();
            System.out.println();
        }
        else System.out.println("El bloque #" + idBloque + " no existe o no se encuentra en la blockchain");
    }
    public void imprimirPorHash(String hash) {
        Bloque busqueda = buscarPorHash(hash);
        if(busqueda != null) {
            busqueda.mostrarDatosBloque();
            System.out.println();
        }
        else System.out.println("El bloque con el hash " + hash + " no existe o no se encuentra en la blockchain");
    }

    public void imprimirPorIDTransaccion(int transaccionId) {
        System.out.println("ID Transaccion a consultar: " + transaccionId);
        boolean found = false;
        Bloque actual = cabeza;
        while(actual!=null){
            if(actual.getTransaccionId()==transaccionId){
                if(!found) found = true;
                actual.mostrarDatosBloque();
                System.out.println("------------------------------------------");
            }
            actual = actual.sig;
        }
        if(!found) System.out.println("Transaccion ID Invalida. ");
    }

    public void imprimirTodo() {
        if (size != 0) {
            Bloque actual = cabeza;
            while (actual != null) {
                actual.mostrarDatosBloque();
                System.out.println();
                actual = actual.sig;
            }
        } else {
            System.out.println("La Blockchain esta vacia");
        }
    }

    public int getSize() {
        return this.size;
    }
}
