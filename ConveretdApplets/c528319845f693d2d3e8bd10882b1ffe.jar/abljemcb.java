import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemcb
{
    static boolean a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        try {
            graphics.setClip(n, n2, n3, n4);
        }
        catch (Throwable t) {
            return false;
        }
        return true;
    }
    
    static String a(final String s, final Object o) {
        if (s == null) {
            return null;
        }
        try {
            final Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(o);
            if (contents == null) {
                return null;
            }
            return (String)contents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Throwable t) {
            abljem.d(t.toString());
            return null;
        }
    }
    
    static void a(final String s, final String s2) {
        if (s == null) {
            return;
        }
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s2), null);
        }
        catch (Throwable t) {
            abljem.d(t.toString());
        }
    }
}
