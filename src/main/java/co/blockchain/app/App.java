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

package co.blockchain.app;
import co.blockchain.linkedlist.Blockchain;
import co.blockchain.linkedlist.Bloque;
/*
Elaborado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006

--Repo en Github del taller https://github.com/Ynnoil1211/Taller4_Blockchain
 */
public class App {
    public static void main(String args[]){
        Blockchain blockchain = new Blockchain();

        System.out.println(" 1. REGISTRO DE TRANSACCIONES EN LA BLOCKCHAIN");
        blockchain.agregar("Juan envía 1 BTC a Pacho");
        blockchain.actualizar(0, "Juan envía 0.5 BTC a Pacho");
        blockchain.agregar("Daniel envia 5 USDT a Lionny");
        blockchain.eliminar(1);

        System.out.println(" 2. ESTADO ACTUAL DE LA BLOCKCHAIN");
        blockchain.imprimirTodo();

        System.out.println(" 3. PRUEBAS DE BUSQUEDA ");

        // Imprimir por hash
        System.out.println("Busqueda por Hash existente (Metodo: imprimirPorHash()): ");
        String hash1 = blockchain.buscarPorId(1).getHashActual();
        System.out.println("Hash: " + hash1);
        System.out.println("Informacion del Bloque Encontrado: ");
        blockchain.imprimirPorHash(hash1);
        System.out.println();

        // Imprimir por id
        System.out.println("Busqueda por ID de bloque (ID: 3)");
        System.out.println("Informacion del Bloque Encontrado: ");
        blockchain.imprimirPorIDBloque(3);
        System.out.println();

        // Buscar un bloque por hash
        System.out.println("Busqueda del bloque con hash (Metodo: buscarPorHash()): " + hash1);
        Bloque b = blockchain.buscarPorHash(hash1);
        if (b != null) {
            System.out.println("Informacion del Bloque Encontrado: ");
            b.mostrarDatosBloque();
        }
        System.out.println();

        // Buscar un bloque por id
        System.out.println("Busqueda del bloque con id 3");
        Bloque c = blockchain.buscarPorId(3);
        if (c != null) {
            System.out.println("Informacion del Bloque Encontrado: ");
            c.mostrarDatosBloque();
        }
        System.out.println();

        //Mostrar el historia de una Transaccion por su ID
        System.out.println("Imprimir el historial completo de una transaccion: ");
        blockchain.imprimirPorIDTransaccion(0);
        System.out.println();

        System.out.println(" 4. CASOS INVALIDOS Y NO ENCONTRADOS");
        // Buscar bloques inexistentes
        System.out.println("Búsqueda de bloque inexistente por ID (ID: 12):");
        blockchain.imprimirPorIDBloque(12);
        System.out.println();

        System.out.println("Busqueda de bloque inexistente por Hash (Hash: inq1003): ");
        blockchain.imprimirPorHash("inq1003");
        System.out.println();

        // Insertar un bloque invalido
        System.out.println("Insercion de bloques invalidos:");
        System.out.println("Eliminar Transaccion con ID 12:");
        blockchain.eliminar(12);
        System.out.println("Actualizar Transaccion con ID 12:");
        blockchain.actualizar(12, "Lionny envía 50 BTC a Daniel");

        // Buscar una transaccion inexistente
        System.out.println("Búsqueda de una transacción inexistente por ID:");
        blockchain.imprimirPorIDTransaccion(213);
        System.out.println();

        System.out.println(" 5. VALIDACION DE INTEGRIDAD DE LA BLOCKCHAIN");
        System.out.println("Verificando la continuidad de la Blockchain:");
        blockchain.validarCadena();
    }
}
