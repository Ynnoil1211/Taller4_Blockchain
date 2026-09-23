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

        System.out.println(" 1. REGISTRO DE TRANSACCIONES EN LA BLOCKCHAIN");
        blockchain.agregar("Juan envía 1 BTC a Pacho");
        blockchain.actualizar(0, "Juan envía 0.5 BTC a Pacho");
        blockchain.agregar("Daniel envia 5 USDT a Lionny");
        blockchain.eliminar(1);

        System.out.println();
        System.out.println(" 2. ESTADO ACTUAL DE LA BLOCKCHAIN");
        blockchain.imprimirTodo();

        System.out.println(" 3. PRUEBAS DE BÚSQUEDA ");

        // Imprimir por hash
        System.out.println("Búsqueda por Hash existente (Hash: 35805770) ");
        blockchain.imprimir("35805770");

        // Imprimir por id

        System.out.println("Búsqueda por ID de bloque (ID: 3)");
        blockchain.imprimir(3);

        // Buscar un bloque por hash
        System.out.println("Búsqueda del bloque con hash 35805770");
        Bloque b = blockchain.buscarPorHash("35805770");
        System.out.println("Bloque #" + b.getIdBloque() + " | Transacción: " + b.mostrar());
        System.out.println();

        // Buscar un bloque por id
        System.out.println("Búsqueda del bloque con id 3");
        Bloque c = blockchain.buscarPorId(3);
        System.out.println("Bloque #" + c.getIdBloque() + " | Hash actual: " + c.getHash() + " | Transacción: " + c.mostrar());
        System.out.println();

        System.out.println(" 4. CASOS INVÁLIDOS Y NO ENCONTRADOS");
        // Buscar bloques inexistentes
        System.out.println("Búsqueda de bloque inexistente por ID (ID: 12):");
        blockchain.imprimir(12);
        System.out.println();

        System.out.println("Búsqueda de bloque inexistente por Hash (Hash: inq1003): ");
        blockchain.imprimir("inq1003");
        System.out.println();

        // Insertar un bloque invalido
        System.out.println("Inserción de bloques inválidos:");
        System.out.println("Eliminar bloque con ID 12:");
        blockchain.eliminar(12);
        System.out.println("Actualizar bloque con ID 12:");
        blockchain.actualizar(12, "Lionny envía 50 BTC a Daniel");

    }
}
