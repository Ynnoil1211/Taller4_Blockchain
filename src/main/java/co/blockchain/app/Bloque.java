/*
Modificado por:
DANIEL ESTEBAN BORRE CARO - 0222510016
LIONNY LIN LI - 0222510050
MARIA ALEJANDRA RAMOS NAIZIR - 0222510006
 */
package co.blockchain.app;
import java.util.Objects;
public class Bloque {

    enum Tipo {
        ADD,
        UPDATE,
        DELETE,
    }

    Tipo tipo;
    int id;
    String data;
    String hashPrev;
    Bloque sig;
    static int cnt = 0;

    private String calcularHash() {
        int hashInt = Objects.hash(id, data, hashPrev);
        return Integer.toHexString(hashInt);
    }

    private Bloque(){
        this.hashPrev = null;
        this.sig = null;
    }
    //Constructor para bloque add
    static Bloque add(String data) {
        Bloque bloque = new Bloque();
        bloque.tipo = Tipo.ADD;
        bloque.id = cnt++;
        bloque.data= data;
        return bloque;
    }
    // Constructor para bloque update
    static Bloque update(int id, String data) {
        Bloque bloque = new Bloque();
        bloque.tipo = Tipo.UPDATE;
        bloque.id = id;
        bloque.data= data;
        return bloque;
    }
    // Constructor para bloque delete
    static Bloque delete(int id){
        Bloque bloque = new Bloque();
        bloque.tipo = Tipo.DELETE;
        bloque.id = id;
        bloque.data = null; // Se resuelve al validar
        return bloque;
    }
    public String getData() {
        return data;
    }
    public String mostrar() {
        return switch (this.tipo){
            case ADD -> "[Añadir] id: " + this.id + " - " + this.data;
            case UPDATE -> "[Actualizar] id: " + this.id + " - " + this.data;
            case DELETE -> "[Borrar] id: " + this.id + " - " + this.data;
        };

    }

    public int getId() {
        return this.id;
    }

    public String getHashPrev() {
        return this.hashPrev;
    }

    public String getHash() {
        return this.calcularHash();
    }
}
