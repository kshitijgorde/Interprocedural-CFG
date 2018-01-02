// 
// Decompiled by Procyon v0.5.30
// 

package ji.burn;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class jiBurner extends ji.server.burner.jiBurner
{
    public jiBurner(final String s, final String s2, final jiBurnerListener jiBurnerListener, final PrintWriter printWriter) throws FileNotFoundException {
        this(s, s2, null, jiBurnerListener, printWriter);
    }
    
    public jiBurner(final String s, final String s2, final String s3, final jiBurnerListener jiBurnerListener, final PrintWriter printWriter) throws FileNotFoundException {
        super(s, s2, s3, jiBurnerListener, printWriter);
    }
    
    public jiBurner(final String s, final String s2, final String s3) throws FileNotFoundException {
        this(s, s2, s3, null);
    }
    
    public jiBurner(final String s, final String s2, final String s3, final String s4) throws FileNotFoundException {
        super(s, s2, s3, s4);
    }
}
