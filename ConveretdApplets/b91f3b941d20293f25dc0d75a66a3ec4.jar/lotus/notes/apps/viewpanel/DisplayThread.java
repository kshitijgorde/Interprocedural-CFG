// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

class DisplayThread extends Thread
{
    int delay;
    String docUNID;
    ViewPanel viewPanel;
    
    public DisplayThread(final ViewPanel viewPanel, final String docUNID, final int delay) {
        super("DocumentDisplayThread");
        this.delay = 0;
        this.delay = delay;
        this.docUNID = docUNID;
        this.viewPanel = viewPanel;
    }
    
    public void run() {
        try {
            Thread.sleep(this.delay);
            this.viewPanel.showDocumentByUNID(this.docUNID);
        }
        catch (InterruptedException ex) {}
    }
}
