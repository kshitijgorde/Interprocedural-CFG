// 
// Decompiled by Procyon v0.5.30
// 

package asdasd;

import java.io.IOException;

public class Pleme
{
    public static void main(final String[] array) {
    }
    
    public static void lererenet(final String s) {
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(s);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
