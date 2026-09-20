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
    int idBloque; // identificador unico del  bloque
    int transaccionId; // id de la transaccion/registro
    String data;
    String hashPrev;
    Bloque sig;
    static int cnt = 0;

    private String calcularHash() {
        int hashInt = Objects.hash(transaccionId, data, hashPrev);
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
        bloque.transaccionId = cnt++;
        bloque.data= data;
        return bloque;
    }
    // Constructor para bloque update
    static Bloque update(int id, String data) {
        Bloque bloque = new Bloque();
        bloque.tipo = Tipo.UPDATE;
        bloque.transaccionId = id;
        bloque.data= data;
        return bloque;
    }
    // Constructor para bloque delete
    static Bloque delete(int id){
        Bloque bloque = new Bloque();
        bloque.tipo = Tipo.DELETE;
        bloque.transaccionId = id;
        bloque.data = null; // Se resuelve al validar
        return bloque;
    }
    public String getData() {
        return data;
    }
    public String mostrar() {
        return switch (this.tipo){
            case ADD -> "[Añadir] id: " + this.transaccionId + " - " + this.data;
            case UPDATE -> "[Actualizar] id: " + this.transaccionId + " - " + this.data;
            case DELETE -> "[Borrar] id: " + this.transaccionId + " - " + this.data;
        };

    }

    public int getTransaccionId() {
        return this.transaccionId;
    }

    public String getHashPrev() {
        if(this.hashPrev == null) return "None";
        return this.hashPrev;
    }

    public String getHash() {
        return this.calcularHash();
    }

    public int getIdBloque(){
        return this.idBloque;
    }
}
