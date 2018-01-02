import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Hashtable;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class CMFrame extends Frame implements WindowListener
{
    CMEditor cme;
    Hashtable ht;
    WorkPanel wp;
    AppletPanel ap;
    
    public CMFrame(final CMEditor cme) {
        super("CascadeMenu Editor");
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.cme = cme;
        this.wp = new WorkPanel(this);
        this.ap = new AppletPanel(this);
        this.wp.setBounds(20, 20, 660, 350);
        this.ap.setBounds(20, 380, 660, 200);
        this.add(this.wp);
        this.add(this.ap);
        this.addWindowListener(this);
    }
    
    public void updateNow(final MenuSet set) {
        System.out.println("updateNow");
        set.reset();
        this.ht = this.wp.extractData();
        this.ap.updateYourself(this.ht, set);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
