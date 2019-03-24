import java.io.*;

public class SomeObjectsSerializer {

    public static  void serialize_objects() {
        AbstractKey key = new SafeKey();
        key.method();

        String filename1 = "key1.data";
        try (FileOutputStream fos = new FileOutputStream(filename1);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename2 = "key2.data";
        try (FileOutputStream fos = new FileOutputStream(filename2);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new DangerousKey());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try( FileInputStream fis = new FileInputStream(filename1);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            AbstractKey returnedKey = (AbstractKey) ois.readObject();
            returnedKey.method();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try( FileInputStream fis = new FileInputStream(filename1);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object returnedKey = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String randomObjectFile = "randomobject.data";
        try (FileOutputStream fos = new FileOutputStream(randomObjectFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            RandomObject obj = new RandomObject();
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try( FileInputStream fis = new FileInputStream(randomObjectFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
