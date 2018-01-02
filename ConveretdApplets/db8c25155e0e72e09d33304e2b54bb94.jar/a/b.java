// 
// Decompiled by Procyon v0.5.30
// 

package a;

import org.kim.cadclick.common.utils.a;
import org.kim.cadclick.common.utils.c;
import java.applet.Applet;
import java.net.URL;

public class b
{
    private static URL a;
    
    public static void a(final Applet applet) {
        b.a = c.a(applet.getDocumentBase());
        org.kim.cadclick.common.utils.a.a("Applet code base: " + applet.getCodeBase());
        org.kim.cadclick.common.utils.a.a("Applet document base: " + applet.getDocumentBase());
        org.kim.cadclick.common.utils.a.a("using URL: " + b.a);
    }
    
    public static URL a() {
        return b.a;
    }
}
