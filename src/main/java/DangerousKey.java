public class DangerousKey extends AbstractKey {
    public static final String OUTPUT = "I am a DangerousKey";

    public DangerousKey() {
        keyName = "dangerouskey";
    }

    @Override
    public void method() {
        System.out.println(OUTPUT);
        System.out.flush();
    }

}
