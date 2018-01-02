// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

public class vi
{
    private static final String[] a;
    private static vi b;
    
    public static synchronized vi b() {
        if (vi.b == null) {
            try {
                for (int i = 0; i < vi.a.length; ++i) {
                    Class.forName(vi.a[i]);
                }
                vi.b = (vi)Class.forName("COM/volano/vh".replace('/', '.')).newInstance();
            }
            catch (ClassNotFoundException ex2) {}
            catch (Exception ex) {
                System.err.println(ex);
            }
            finally {
                if (vi.b == null) {
                    vi.b = new vi();
                }
            }
        }
        return vi.b;
    }
    
    public void a() {
    }
    
    public byte[] a(final byte[] array) {
        return new byte[0];
    }
    
    static {
        a = new String[] { "java.security.SecureRandom" };
        vi.b = null;
    }
}
