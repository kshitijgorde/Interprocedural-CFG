// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;

public final class bg
{
    private static Class q;
    private static Class w;
    
    public static bh q(final Container container, final cV cv) {
        bh bh;
        if (aG.q()) {
            if (a.w()) {
                bh = new bl(container, cv);
            }
            else {
                try {
                    bh = (bl)Class.forName("a.bm").getConstructor((bg.q == null) ? (bg.q = q("java.awt.Container")) : bg.q, (bg.w == null) ? (bg.w = q("a.cV")) : bg.w).newInstance(container, cv);
                }
                catch (Exception ex) {
                    System.out.println("Failed to initialize context menu");
                    ex.printStackTrace();
                    bh = new bl(container, cv);
                }
            }
        }
        else if (a.w()) {
            bh = new aZ(container, cv);
        }
        else {
            try {
                bh = (aZ)Class.forName("a.ba").getConstructor((bg.q == null) ? (bg.q = q("java.awt.Container")) : bg.q, (bg.w == null) ? (bg.w = q("a.cV")) : bg.w).newInstance(container, cv);
            }
            catch (Exception ex2) {
                System.out.println("Failed to initialize context menu");
                ex2.printStackTrace();
                bh = new aZ(container, cv);
            }
        }
        return bh;
    }
    
    private static Class q(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
