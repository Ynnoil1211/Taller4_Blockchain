package co.blockchain.app;
import java.util.Objects;

public class BloqueAdd extends Bloque {
    public BloqueAdd(String data) {
        super();
        this.data=data;
        this.id = cnt++;
    }
    @Override
    public String calcularHash() {
        int hashInt = Objects.hash(id, data, hashPrev);
		return Integer.toHexString(hashInt);
    }

    @Override
    public boolean esValido(Blockchain blockchain) {
        // El hash de un bloque nuevo siempre es unico, siempre es valido
        return true;
    }
}
