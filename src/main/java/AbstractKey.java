import java.io.Serializable;

public abstract class AbstractKey implements Serializable {

    Manufactor manufactor;

    String keyName;

    public AbstractKey() {
        manufactor = new Manufactor();
        keyName = "abstractkey";
    }

    public abstract void method();

}