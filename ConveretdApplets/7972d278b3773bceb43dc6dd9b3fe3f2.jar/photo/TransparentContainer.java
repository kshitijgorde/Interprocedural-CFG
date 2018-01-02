// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class TransparentContainer extends HashMap
{
    String mkl;
    HashSet y;
    public static String ssize;
    final Zoom m;
    
    public static String trim(final String s, final String s2, final String s3) {
        final short[] array = { 2, -9 };
        array[0] = 0;
        String string = "";
        while (array[0] < s3.length()) {
            if ((array[1] = (short)s.indexOf(s3.substring(array[0], array[0] + 1))) > -1) {
                string += s2.substring(array[1], array[1] + 1);
            }
            final short[] array2 = array;
            final int n = 0;
            ++array2[n];
        }
        return string;
    }
    
    public TransparentContainer(final Zoom m, final HashSet y) {
        this.mkl = "kj89";
        this.m = m;
        this.y = y;
    }
    
    @Override
    public Set entrySet() {
        return this.y;
    }
    
    static {
        TransparentContainer.ssize = "nhhspo309.e".concat("xe").substring(9);
    }
}
