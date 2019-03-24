import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashMap;

public class ProtectedObjectInputStream extends ObjectInputStream {

    /** table mapping primitive type names to corresponding class objects */
    private static final HashMap<String, Class<?>> primClasses
            = new HashMap<>(8, 1.0F);
    static {
        primClasses.put("boolean", boolean.class);
        primClasses.put("byte", byte.class);
        primClasses.put("char", char.class);
        primClasses.put("short", short.class);
        primClasses.put("int", int.class);
        primClasses.put("long", long.class);
        primClasses.put("float", float.class);
        primClasses.put("double", double.class);
        primClasses.put("void", void.class);
    }

    private final String classname;
    private boolean checked = false;
    private boolean allowSubclass = false;

    public ProtectedObjectInputStream(InputStream in, String classname) throws IOException {
        super(in);
        this.classname = classname;
    }

    public ProtectedObjectInputStream(InputStream in, String classname, boolean allowSubclass) throws IOException {
        this(in, classname);
        this.allowSubclass = allowSubclass;
    }

    protected Class<?> resolveClass(ObjectStreamClass desc)
            throws IOException, ClassNotFoundException {
        String name = desc.getName();
        try {
            Class<?> loadedClass = Class.forName(name, false, latestUserDefinedLoader());

            if (!checked) {
                Class<?> expectedClass = Class.forName(classname, false, latestUserDefinedLoader());
                if (allowSubclass) {
                    if (!expectedClass.isAssignableFrom(loadedClass)) {
                        throw new IllegalStateException("allowedSubclass is enable. " + loadedClass + " is NOT same as or subclass of " + expectedClass);
                    }
                } else {
                    if (!loadedClass.equals(expectedClass)) {
                        throw new IllegalStateException("allowedSubClass is disabled. " + loadedClass + " is NOT same as " + expectedClass);
                    }
                }
                checked = true;
            }

            return loadedClass;
        } catch (ClassNotFoundException ex) {
            Class<?> cl = primClasses.get(name);
            if (cl != null) {
                return cl;
            } else {
                throw ex;
            }
        }
    }

    /**
     * Returns first non-privileged class loader on the stack (excluding
     * reflection generated frames) or the extension class loader if only
     * class loaded by the boot class loader and extension class loader are
     * found on the stack. This method is also called via reflection by the
     * following RMI-IIOP class:
     *
     *     com.sun.corba.se.internal.util.JDKClassLoader
     *
     * This method should not be removed or its signature changed without
     * corresponding modifications to the above class.
     */
    private static ClassLoader latestUserDefinedLoader() {
        return sun.misc.VM.latestUserDefinedLoader();
    }
}
