// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

public class vn
{
    public vp a(final int n) throws ClassNotFoundException {
        switch (n) {
            case 101: {
                return new vp();
            }
            case 102: {
                return new vas();
            }
            default: {
                throw new ClassNotFoundException(Integer.toString(n));
            }
        }
    }
}
