// 
// Decompiled by Procyon v0.5.30
// 

package ji.clip;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class jiJ2Clipboard implements gz
{
    public final void setClipboardText(final String s) {
        try {
            final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final StringSelection stringSelection = new StringSelection(s);
            systemClipboard.setContents(stringSelection, stringSelection);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String getClipboardText() {
        String s = "";
        try {
            final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            final Transferable contents = systemClipboard.getContents(systemClipboard);
            if (contents != null) {
                s = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }
}
