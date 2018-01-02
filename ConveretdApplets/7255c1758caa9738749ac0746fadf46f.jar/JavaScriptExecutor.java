import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaScriptExecutor
{
    private Applet applet;
    
    public JavaScriptExecutor(final Applet applet) {
        this.applet = applet;
    }
    
    public Object execute(final String s) {
        Object invoke = null;
        try {
            Method method = null;
            Method method2 = null;
            final Class<?> forName = Class.forName("netscape.javascript.JSObject");
            final Method[] methods = forName.getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().compareTo("getWindow") == 0) {
                    method = methods[i];
                }
                else if (methods[i].getName().compareTo("eval") == 0) {
                    method2 = methods[i];
                }
            }
            final Object[] array = { this.applet };
            final Object invoke2 = method.invoke(forName, array);
            array[0] = s;
            invoke = method2.invoke(invoke2, array);
        }
        catch (InvocationTargetException ex) {
            ex.getTargetException().printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return invoke;
    }
}
