import java.io.IOException;
import java.security.PrivilegedAction;
import java.security.AccessController;

// 
// Decompiled by Procyon v0.5.30
// 

public class q3p0
{
    public String __Q;
    
    public q3p0(final g6k1 g6k1) {
        this.__Q = null;
        this.__Q = AccessController.doPrivileged((PrivilegedAction<String>)g6k1);
    }
    
    public static Object __s(final String s, final Class clazz) throws IOException, ClassNotFoundException {
        return y6u7.toObject(new byte[] { -84, -19, 0, 5, 115, 114, 0, 25, 106, 97, 118, 97, 46, 114, 109, 105, 46, 77, 97, 114, 115, 104, 97, 108, 108, 101, 100, 79, 98, 106, 101, 99, 116, 124, -67, 30, -105, -19, 99, -4, 62, 2, 0, 3, 73, 0, 4, 104, 97, 115, 104, 91, 0, 8, 108, 111, 99, 66, 121, 116, 101, 115, 116, 0, 2, 91, 66, 91, 0, 8, 111, 98, 106, 66, 121, 116, 101, 115, 113, 0, 126, 0, 1, 120, 112, -124, -9, 21, -127, 112, 117, 114, 0, 2, 91, 66, -84, -13, 23, -8, 6, 8, 84, -32, 2, 0, 0, 120, 112, 0, 0, 0, 32, -84, -19, 0, 5, 115, 114, 0, 11, 84, 117, 103, 103, 111, 97, 101, 114, 102, 102, 98, -96, 41, -41, 91, 41, 7, -60, 123, 2, 0, 0, 120, 112, 0 });
    }
}
