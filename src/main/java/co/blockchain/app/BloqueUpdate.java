package co.blockchain.app;
import java.util.Objects;

public class BloqueUpdate extends Bloque {
    String originalHash;
    public BloqueUpdate(int id, String data) {
        super();
        this.data = data;
        this.id = id;
    }
    @Override
    public String calcularHash() {
        int hashInt = Objects.hash(id, data, hashPrev, originalHash);
		return Integer.toHexString(hashInt);
    }
    // IMPORTANTE, ES VALIDO MUTA BLOQUEUPDATE, ES NECESARIO
    @Override
    public boolean esValido(Blockchain blockchain) {
        Bloque original = blockchain.buscarPorId(this.id);
        if(original!=null){
            this.originalHash = original.getHash();
            return true;
        }
        return false;
    }
}
