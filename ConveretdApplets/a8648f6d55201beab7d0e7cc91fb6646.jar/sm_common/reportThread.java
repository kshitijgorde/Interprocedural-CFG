// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.Cursor;

public class reportThread extends Thread
{
    private ReportDesktop desktop;
    private Cursor oldCursor;
    
    public reportThread(final ReportDesktop rd) {
        super("doSearch");
        this.desktop = rd;
    }
    
    public void run() {
        this.oldCursor = this.desktop.getCursor();
        this.desktop.setCursor(new Cursor(3));
        this.desktop.doSearch();
        this.desktop.validate();
        this.desktop.setCursor(this.oldCursor);
    }
}
