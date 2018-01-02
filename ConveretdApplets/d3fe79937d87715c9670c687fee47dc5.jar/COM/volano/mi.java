// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

public class mi
{
    private static final String[] a;
    private static mi b;
    
    public static synchronized mi b() {
        if (mi.b == null) {
            try {
                for (int i = 0; i < mi.a.length; ++i) {
                    Class.forName(mi.a[i]);
                }
                mi.b = (mi)Class.forName("COM/volano/mh".replace('/', '.')).newInstance();
            }
            catch (ClassNotFoundException ex2) {}
            catch (Exception ex) {
                System.err.println(ex);
            }
            finally {
                if (mi.b == null) {
                    mi.b = new mi();
                }
            }
        }
        return mi.b;
    }
    
    public void a() {
    }
    
    public byte[] a(final byte[] array) {
        return new byte[0];
    }
    
    static {
        a = new String[] { "java.security.SecureRandom" };
        mi.b = null;
    }
}
