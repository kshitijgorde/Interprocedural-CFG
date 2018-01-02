import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ReloginPanel extends Panel implements ActionListener
{
    Button reloginButton;
    Button closeButton;
    VncViewer viewer;
    
    public ReloginPanel(final VncViewer v) {
        this.viewer = v;
        this.setLayout(new FlowLayout(1));
        this.add(this.reloginButton = new Button("Login again"));
        this.reloginButton.addActionListener(this);
        if (this.viewer.inSeparateFrame) {
            this.add(this.closeButton = new Button("Close window"));
            this.closeButton.addActionListener(this);
        }
    }
    
    public synchronized void actionPerformed(final ActionEvent evt) {
        if (this.viewer.inSeparateFrame) {
            this.viewer.vncFrame.dispose();
        }
        if (evt.getSource() == this.reloginButton) {
            this.viewer.getAppletContext().showDocument(this.viewer.getDocumentBase());
        }
    }
}
