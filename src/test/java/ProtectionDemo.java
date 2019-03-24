import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * It is NOT unit test. It is for a demo to show protection is achieved by blocking an attack.
 */
public class ProtectionDemo {

    @Test
    public void demo() {
        SomeObjectsSerializer.serialize_objects();

        String safeKeyFile = "key1.data";
        String dangerousKeyFile = "key2.data";
        String randomObjectFile = "randomobject.data";

        try(FileInputStream fis = new FileInputStream(safeKeyFile);
                     ObjectInputStream ois = new ProtectedObjectInputStream(fis, "SafeKey")) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(FileInputStream fis = new FileInputStream(safeKeyFile);
            ObjectInputStream ois = new ProtectedObjectInputStream(fis, AbstractKey.class.getName(), true)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // note that the exception will be thrown when an attack is detected.
        // note that "I am a RandomObject" is not printed here
        try(FileInputStream fis = new FileInputStream(randomObjectFile);
            ObjectInputStream ois = new ProtectedObjectInputStream(fis, "SafeKey")) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // note that the exception will be thrown when an attack is detected
        // note that "I am a RandomObject" is not printed here
        try(FileInputStream fis = new FileInputStream(randomObjectFile);
            ObjectInputStream ois = new ProtectedObjectInputStream(fis, AbstractKey.class.getName(), true)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("###### the exception showning is intentional. See demo files comment for detail!!!! ###");
    }
}
