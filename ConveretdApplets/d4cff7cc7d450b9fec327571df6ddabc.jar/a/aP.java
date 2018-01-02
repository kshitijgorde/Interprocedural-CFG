// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;

public final class aP
{
    private static Class q;
    private static Class w;
    
    public static aQ q(final Container container, final bI bi) {
        aQ aq;
        if (ar.q()) {
            if (a.w()) {
                aq = new aU(container, bi);
            }
            else {
                try {
                    aq = (aU)Class.forName("com.spilka.client.awt.menu.standart.context.CMChatMainPanelStandartMaster").getConstructor((aP.q == null) ? (aP.q = q("java.awt.Container")) : aP.q, (aP.w == null) ? (aP.w = q("a.bI")) : aP.w).newInstance(container, bi);
                }
                catch (Exception ex) {
                    System.out.println("Failed to initialize context menu");
                    ex.printStackTrace();
                    aq = new aU(container, bi);
                }
            }
        }
        else if (a.w()) {
            aq = new aK(container, bi);
        }
        else {
            try {
                aq = (aK)Class.forName("com.spilka.client.awt.menu.context.CMChatMainPanelMaster").getConstructor((aP.q == null) ? (aP.q = q("java.awt.Container")) : aP.q, (aP.w == null) ? (aP.w = q("a.bI")) : aP.w).newInstance(container, bi);
            }
            catch (Exception ex2) {
                System.out.println("Failed to initialize context menu");
                ex2.printStackTrace();
                aq = new aK(container, bi);
            }
        }
        return aq;
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
