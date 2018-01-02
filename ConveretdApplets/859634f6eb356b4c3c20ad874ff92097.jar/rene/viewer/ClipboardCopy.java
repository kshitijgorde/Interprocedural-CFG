// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Canvas;
import java.awt.datatransfer.ClipboardOwner;

class ClipboardCopy extends Thread
{
    String S;
    ClipboardOwner C;
    Canvas Cv;
    
    public ClipboardCopy(final ClipboardOwner c, final Canvas cv, final String s) {
        this.S = s;
        this.C = c;
        this.Cv = cv;
        this.start();
    }
    
    public void run() {
        this.Cv.getToolkit().getSystemClipboard().setContents(new StringSelection(this.S), this.C);
    }
}
