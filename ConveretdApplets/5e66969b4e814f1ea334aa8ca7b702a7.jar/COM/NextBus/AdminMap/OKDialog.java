// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class OKDialog extends Frame implements ActionListener, KeyListener, WindowListener
{
    private static final long serialVersionUID = -2215112042630973211L;
    protected GridBagLayout _gridbag;
    protected Component _textComponent;
    protected Button _okButton;
    
    public static void a(final String s, final String s2, final Component component, Color background) {
        final OKDialog okDialog;
        (okDialog = new OKDialog(s, s2)).add(okDialog._textComponent);
        okDialog.add(okDialog._okButton);
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).gridwidth = 0;
        okDialog._gridbag.setConstraints(okDialog._textComponent, gridBagConstraints);
        gridBagConstraints.insets.right = 4;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.insets.top = 5;
        gridBagConstraints.insets.bottom = 5;
        gridBagConstraints.insets.left = 24;
        gridBagConstraints.insets.right = 4;
        gridBagConstraints.weightx = 1.0;
        okDialog._gridbag.setConstraints(okDialog._okButton, gridBagConstraints);
        okDialog.setSize(400, 200);
        okDialog.pack();
        final OKDialog okDialog2 = okDialog;
        background = background;
        final OKDialog okDialog3 = okDialog2;
        okDialog2._textComponent.setBackground(background);
        okDialog3._okButton.setBackground(background);
        okDialog3.setBackground(background);
        if (component.isVisible()) {
            final Point locationOnScreen = component.getLocationOnScreen();
            final Dimension size = component.getSize();
            final Dimension size2 = okDialog.getSize();
            okDialog.setLocation(locationOnScreen.x + size.width / 2 - size2.width / 2, locationOnScreen.y + (int)(size.height / 2.6) - (int)(size2.height / 2.6));
        }
        okDialog.setVisible(true);
    }
    
    private OKDialog(final String title, final String s) {
        this.setTitle(title);
        this.setLayout(this._gridbag = new GridBagLayout());
        (this._okButton = new Button("OK")).setFont(O.k);
        this._okButton.addActionListener(this);
        this._okButton.setActionCommand("OK_BUTTON");
        this._okButton.addKeyListener(this);
        this.addWindowListener(this);
        if (s.length() > 80) {
            final TextArea textComponent;
            (textComponent = new TextArea("", 8, 60, 1)).setFont(O.k);
            textComponent.setEditable(false);
            textComponent.append(s);
            this._textComponent = textComponent;
            return;
        }
        final Label textComponent2;
        (textComponent2 = new Label(s, 0)).setFont(O.k);
        this._textComponent = textComponent2;
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.toFront();
        this._okButton.requestFocus();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("OK_BUTTON")) {
            this.dispose();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.dispose();
            return;
        }
        if (keyEvent.getKeyCode() == 27) {
            this.dispose();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
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
