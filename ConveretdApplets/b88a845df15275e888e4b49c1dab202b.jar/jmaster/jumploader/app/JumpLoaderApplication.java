// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.app;

import jmaster.util.swing.GUIHelper;
import java.awt.Component;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import jmaster.util.C.B;
import java.awt.event.WindowListener;

public class JumpLoaderApplication implements WindowListener
{
    private static final String B = "frame";
    private JumpLoaderMain A;
    
    public JumpLoaderApplication() {
        (this.A = new JumpLoaderMain()).createModel();
        this.A.injectSystemProperties();
        this.A.initModel();
        jmaster.util.C.B.C(this.A, "createView");
        try {
            while (this.A.getView() == null) {
                Thread.sleep(10L);
            }
        }
        catch (InterruptedException ex2) {}
        jmaster.util.C.B.C(this, "createFrame");
        this.A.createController();
        this.A.startController();
        try {
            this.A.getModel().D().getAttributeSet().createStringAttribute("xxx", "\u6c49\u8bed/\u6f22\u8a9e, \u534e\u8bed/\u83ef\u8a9e or \u4e2d\u6587").setSendToServer(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        jmaster.util.C.B.B(this.A, "destroy", new Class[] { Boolean.TYPE }, new Object[] { new Boolean(true) });
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public static void main(final String[] array) {
        try {
            new JumpLoaderApplication();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void createFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        final JFrame frame = new JFrame();
        frame.getContentPane().add((Component)this.A.getView());
        frame.setDefaultCloseOperation(0);
        GUIHelper.getInstance().injectProperties(frame, "frame");
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.addWindowListener(this);
    }
}
