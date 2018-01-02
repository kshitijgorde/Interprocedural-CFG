// 
// Decompiled by Procyon v0.5.30
// 

package tmcm;

import java.awt.Event;
import java.awt.Component;
import tmcm.xSortLab.xSortLabPanel;
import java.awt.Frame;

class xSortLabFrame extends Frame
{
    private xSortLabPanel sortPanel;
    private boolean closed;
    boolean exitOnClose;
    
    public static void main(final String[] array) {
        final xSortLabFrame xSortLabFrame = new xSortLabFrame();
    }
    
    public static void runNoExit() {
        new xSortLabFrame().exitOnClose = false;
    }
    
    xSortLabFrame() {
        super("xSortLab");
        this.closed = false;
        this.exitOnClose = true;
        this.add("Center", this.sortPanel = new xSortLabPanel());
        this.reshape(20, 30, 640, 480);
        this.setResizable(false);
        this.show();
    }
    
    boolean isClosed() {
        return this.closed;
    }
    
    void close() {
        this.sortPanel.stop();
        this.sortPanel.destroy();
        this.closed = true;
        this.dispose();
        if (this.exitOnClose) {
            System.exit(0);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.close();
            return true;
        }
        return super.handleEvent(event);
    }
}
