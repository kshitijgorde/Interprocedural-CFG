import java.io.IOException;
import java.awt.event.KeyEvent;
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

class ButtonPanel extends Panel implements ActionListener
{
    VncViewer viewer;
    Button disconnectButton;
    Button optionsButton;
    Button recordButton;
    Button clipboardButton;
    Button ctrlAltDelButton;
    Button refreshButton;
    
    ButtonPanel(final VncViewer viewer) {
        this.viewer = viewer;
        this.setLayout(new FlowLayout(0, 0, 0));
        (this.disconnectButton = new Button("Disconnect")).setEnabled(false);
        this.add(this.disconnectButton);
        this.disconnectButton.addActionListener(this);
        this.add(this.optionsButton = new Button("Options"));
        this.optionsButton.addActionListener(this);
        (this.clipboardButton = new Button("Clipboard")).setEnabled(false);
        this.add(this.clipboardButton);
        this.clipboardButton.addActionListener(this);
        if (this.viewer.rec != null) {
            this.add(this.recordButton = new Button("Record"));
            this.recordButton.addActionListener(this);
        }
        (this.ctrlAltDelButton = new Button("Send Ctrl-Alt-Del")).setEnabled(false);
        this.ctrlAltDelButton.addActionListener(this);
        (this.refreshButton = new Button("Refresh")).setEnabled(false);
        this.add(this.refreshButton);
        this.refreshButton.addActionListener(this);
    }
    
    public void enableButtons() {
        this.disconnectButton.setEnabled(true);
        this.clipboardButton.setEnabled(true);
        this.refreshButton.setEnabled(true);
    }
    
    public void disableButtonsOnDisconnect() {
        this.remove(this.disconnectButton);
        (this.disconnectButton = new Button("Hide desktop")).setEnabled(true);
        this.add(this.disconnectButton, 0);
        this.disconnectButton.addActionListener(this);
        this.optionsButton.setEnabled(false);
        this.clipboardButton.setEnabled(false);
        this.ctrlAltDelButton.setEnabled(false);
        this.refreshButton.setEnabled(false);
        this.validate();
    }
    
    public void enableRemoteAccessControls(final boolean enabled) {
        this.ctrlAltDelButton.setEnabled(enabled);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.viewer.moveFocusToDesktop();
        if (actionEvent.getSource() == this.disconnectButton) {
            this.viewer.disconnect();
        }
        else if (actionEvent.getSource() == this.optionsButton) {
            this.viewer.options.setVisible(!this.viewer.options.isVisible());
        }
        else if (actionEvent.getSource() == this.recordButton) {
            this.viewer.rec.setVisible(!this.viewer.rec.isVisible());
        }
        else if (actionEvent.getSource() == this.clipboardButton) {
            this.viewer.clipboard.setVisible(!this.viewer.clipboard.isVisible());
        }
        else if (actionEvent.getSource() == this.ctrlAltDelButton) {
            try {
                this.viewer.rfb.writeKeyEvent(new KeyEvent(this, 401, 0L, 10, 127));
                this.viewer.rfb.writeKeyEvent(new KeyEvent(this, 402, 0L, 10, 127));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (actionEvent.getSource() == this.refreshButton) {
            try {
                final RfbProto rfb = this.viewer.rfb;
                rfb.writeFramebufferUpdateRequest(0, 0, rfb.framebufferWidth, rfb.framebufferHeight, false);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
