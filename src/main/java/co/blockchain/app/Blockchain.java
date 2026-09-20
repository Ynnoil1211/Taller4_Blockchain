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
package co.blockchain.app;

import co.blockchain.app.Bloque.Tipo;

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
        bloque.idBloque = size;
        if (cola == null) cabeza = bloque;
        else {
            bloque.hashPrev = cola.getHash();
            cola.sig = bloque;
        }
        cola = bloque;
        size = size + 1;
        System.out.println(
            "Bloque #" + bloque.idBloque + " añadido (Tx ID: " + bloque.transaccionId + ") con hash " + bloque.getHash()
        );
        System.out.println();
        return true;
    }

    public Bloque buscarPorHash(String hash) {
        Bloque actual = cabeza;
        while (actual != null) {
            if (actual.getHash().equals(hash)) return actual;
            actual = actual.sig;
        }
        return null;
    }

    public Bloque buscarPorId(int id) {
        Bloque resultado = null;
        Bloque actual = cabeza;
        while (actual != null) {
            if (actual.transaccionId == id) {
                if (actual.tipo == Tipo.DELETE) return null;
                else resultado = actual;
            }
            actual = actual.sig;
        }
        return resultado;
    }

    public boolean agregar(String data) {
        Bloque nuevoBloque = Bloque.add(data);
        return push(nuevoBloque);
    }

    public boolean eliminar(int id) {
        Bloque nuevoBloque = Bloque.delete(id);
        return push(nuevoBloque);
    }

    public boolean actualizar(int id, String data) {
        Bloque nuevoBloque = Bloque.update(id, data);
        return push(nuevoBloque);
    }

    private boolean validarBloque(Bloque bloque) {
        return switch (bloque.tipo) {
            case ADD -> true;
            case UPDATE -> buscarPorId(bloque.transaccionId) != null;
            case DELETE -> {
                Bloque orig = buscarPorId(bloque.transaccionId);
                if (orig != null) {
                    bloque.data = orig.data;
                    yield true;
                }
                yield false;
            }
        };
    }

    public void imprimir(String hash) {
        Bloque busqueda = buscarPorHash(hash);
        if(busqueda != null) {
            System.out.println("Bloque #" + busqueda.idBloque);
            System.out.println("Transacción: " + busqueda.mostrar());
            System.out.println("Hash anterior: " + busqueda.getHashPrev());
            System.out.println("Hash actual: " + busqueda.getHash());
            System.out.println();
        }
        else System.out.println("El bloque con el hash " + hash + " no existe o no se encuentra en la blockchain");
    }
    public void imprimirTodo() {
        if (size != 0) {
            Bloque actual = cabeza;
            while (actual != null) {
                System.out.println("Bloque #" + actual.idBloque);
                System.out.println("Transacción: " + actual.mostrar());
                System.out.println("Hash anterior: " + actual.getHashPrev() );
                System.out.println("Hash actual: " + actual.getHash());
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
