import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Manufactor implements Serializable {
    private final String string = "I am a Manufactor";

    public Manufactor() {
        System.out.println("Manufactor constructor");
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println(string);
        System.out.flush();
    }

    public String toString() {
        return string;
    }
}
