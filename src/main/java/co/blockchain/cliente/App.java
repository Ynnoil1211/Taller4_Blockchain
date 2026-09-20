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

package co.blockchain.cliente;
import co.blockchain.app.Blockchain;
import co.blockchain.app.Bloque;
/*
Elaborado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006
 */
public class App {
    public static void main(String args[]){
        Blockchain blockchain = new Blockchain();
        blockchain.agregar("Juan envía 1 BTC a Pacho");
        blockchain.actualizar(0, "Juan envía 0.5 BTC a Pacho");
        blockchain.agregar("Daniel envia 5 USDT a Lionny");
        blockchain.eliminar(1);
        blockchain.imprimirTodo();

        // Imprimir por hash
        System.out.println("Busco el hash: 35805770");
        blockchain.imprimir("35805770");

        // Imprimir por id
        System.out.println("Busco la id: 3");
        blockchain.imprimir(3);

        // Buscar un bloque por hash
        System.out.println("Bloque con hash 35805770");
        Bloque b = blockchain.buscarPorHash("35805770");
        System.out.println("Bloque #" + b.getIdBloque());
        System.out.println();

        // Buscar un bloque por id
        System.out.println("Bloque con id 3");
        Bloque c = blockchain.buscarPorId(3);
        System.out.println("Hash: " + c.getHash());
        System.out.println();

        // Buscar bloques inexistentes
        blockchain.imprimir(12);
        blockchain.imprimir("ahjsfiuohasfiouhjsfaiouhasf");
        System.out.println();

        // Insertar un bloque invalido
        System.out.println("Inserto dos bloques invalidos");
        blockchain.eliminar(12);
        blockchain.actualizar(12, "Lionny le manda 50 BTC a Daniel");

    }
}
