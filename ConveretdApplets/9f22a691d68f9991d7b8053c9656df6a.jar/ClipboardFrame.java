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
    
    ClipboardFrame(final VncViewer viewer) {
        super("TightVNC Clipboard");
        this.viewer = viewer;
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.textArea = new TextArea(5, 40), gridBagConstraints);
        this.add(this.textArea);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.clearButton = new Button("Clear"), gridBagConstraints);
        this.add(this.clearButton);
        this.clearButton.addActionListener(this);
        layout.setConstraints(this.closeButton = new Button("Close"), gridBagConstraints);
        this.add(this.closeButton);
        this.closeButton.addActionListener(this);
        this.pack();
        this.addWindowListener(this);
    }
    
    void setCutText(final String s) {
        this.selection = s;
        this.textArea.setText(s);
        if (this.isVisible()) {
            this.textArea.selectAll();
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        if (this.selection != null && !this.selection.equals(this.textArea.getText())) {
            this.selection = this.textArea.getText();
            this.viewer.setCutText(this.selection);
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.clearButton) {
            this.textArea.setText("");
        }
        else if (actionEvent.getSource() == this.closeButton) {
            this.setVisible(false);
        }
    }
}
