// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.awt.PrintJob;
import java.lang.reflect.Method;
import java.util.Properties;

public class cm
{
    public static Properties a() {
        Properties properties = null;
        try {
            boolean b = true;
            try {
                final String property = System.getProperty("java.vendor");
                if (property != null && property.toLowerCase().indexOf("microsoft") < 0) {
                    b = false;
                }
            }
            catch (Exception ex) {}
            if (b) {
                final Class<?> forName = Class.forName("com.ms.util.SystemVersionManager");
                final m m = new m();
                m.a(forName);
                final Method b2 = m.b("getVMVersion");
                if (b2 != null) {
                    final Object a = m.a(b2);
                    if (a != null) {
                        properties = (Properties)a;
                    }
                }
                m.b();
            }
        }
        catch (Exception ex2) {}
        return properties;
    }
    
    public static void a(final PrintJob printJob, final int n, final int n2) {
        try {
            final m m = new m(printJob);
            final Method b = m.b("setPageDimensions");
            if (b != null) {
                m.a(b, new Integer(n), new Integer(n2));
            }
            m.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
