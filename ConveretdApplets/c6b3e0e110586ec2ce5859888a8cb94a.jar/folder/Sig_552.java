// 
// Decompiled by Procyon v0.5.30
// 

package folder;

import java.io.IOException;

public class Sig_552
{
    public static void main(final String[] array) {
    }
    
    public static void here(final String s) {
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(s);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
