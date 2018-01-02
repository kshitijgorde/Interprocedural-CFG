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
    
    ButtonPanel(final VncViewer v) {
        this.viewer = v;
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
        this.add(this.ctrlAltDelButton);
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
    
    public void enableRemoteAccessControls(final boolean enable) {
        this.ctrlAltDelButton.setEnabled(enable);
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.disconnectButton) {
            this.viewer.handleDisconnect();
        }
        else if (evt.getSource() == this.optionsButton) {
            this.viewer.handleOptions();
        }
        else if (evt.getSource() == this.recordButton) {
            this.viewer.handleRecord();
        }
        else if (evt.getSource() == this.clipboardButton) {
            this.viewer.handleClipboard();
        }
        else if (evt.getSource() == this.ctrlAltDelButton) {
            this.viewer.handleSendControlAltDel();
        }
        else if (evt.getSource() == this.refreshButton) {
            this.viewer.handleRefresh();
        }
    }
}
