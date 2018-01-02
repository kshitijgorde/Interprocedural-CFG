import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class ClipboardFrame extends Frame implements WindowListener, ActionListener
{
    TextArea textArea;
    Button clearButton;
    Button closeButton;
    String selection;
    VncViewer viewer;
    
    ClipboardFrame(final VncViewer v) {
        super("TightVNC Clipboard");
        this.viewer = v;
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 0;
        gbc.fill = 1;
        gbc.weighty = 1.0;
        gridbag.setConstraints(this.textArea = new TextArea(5, 40), gbc);
        this.add(this.textArea);
        gbc.fill = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        gridbag.setConstraints(this.clearButton = new Button("Clear"), gbc);
        this.add(this.clearButton);
        this.clearButton.addActionListener(this);
        gridbag.setConstraints(this.closeButton = new Button("Close"), gbc);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.pack();
        this.addWindowListener(this);
    }
    
    void setCutText(final String text) {
        this.selection = text;
        this.textArea.setText(text);
        if (this.isVisible()) {
            this.textArea.selectAll();
        }
    }
    
    public void windowDeactivated(final WindowEvent evt) {
        if (this.selection != null && !this.selection.equals(this.textArea.getText())) {
            this.selection = this.textArea.getText();
            this.viewer.setCutText(this.selection);
        }
    }
    
    public void windowClosing(final WindowEvent evt) {
        this.setVisible(false);
    }
    
    public void windowActivated(final WindowEvent evt) {
    }
    
    public void windowOpened(final WindowEvent evt) {
    }
    
    public void windowClosed(final WindowEvent evt) {
    }
    
    public void windowIconified(final WindowEvent evt) {
    }
    
    public void windowDeiconified(final WindowEvent evt) {
    }
    
    public void actionPerformed(final ActionEvent evt) {
        if (evt.getSource() == this.clearButton) {
            this.textArea.setText("");
        }
        else if (evt.getSource() == this.closeButton) {
            this.setVisible(false);
        }
    }
}
