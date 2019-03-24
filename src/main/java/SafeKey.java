public class SafeKey extends AbstractKey {
    private static final String OUTPUT = "I am a SafeKey";

    public  SafeKey() {
        keyName = "safekey";
    }

    @Override
    public void method() {
        System.out.println(OUTPUT);
        System.out.flush();
    }

}
