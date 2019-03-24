import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This is NOT unit test. It is for demo how an attack succeed to run unwanted code.
 */
public class AttackDemo {


    @Test
    public void demo() {
        SomeObjectsSerializer.serialize_objects();

        String safeKeyFile = "key1.data";
        String dangerousKeyFile = "key2.data";
        String randomObjectFile = "randomobject.data";

        try(FileInputStream fis = new FileInputStream(safeKeyFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(FileInputStream fis = new FileInputStream(safeKeyFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // this shows that RandomObject code has been executed if "I am a RandomObject" is printed
        try(FileInputStream fis = new FileInputStream(randomObjectFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // this shows that RandomObject code has been executed if "I am a RandomObject" is printed
        try(FileInputStream fis = new FileInputStream(randomObjectFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("####### NOTE the exception showning is intentional. See demo files comment for detail!!!! ###");
    }
}
