import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class RandomObject implements Serializable {
    public static final String OUTPUT = "I am a RandomObject";

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println(OUTPUT);
        System.out.flush();
    }

}
