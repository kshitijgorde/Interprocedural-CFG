// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CalculatorWindow extends JFrame implements ActionListener
{
    private boolean bShow;
    private JLabel tfTitle;
    private JButton bButton;
    private CalculatorApplet CA;
    private String sTitle;
    private boolean bError;
    
    public CalculatorWindow(final String sTitle, String s, final JPanel panel, final CalculatorApplet ca, final int n, final int n2) {
        this.bShow = false;
        this.sTitle = "";
        this.bError = false;
        this.CA = ca;
        if (s == null) {
            s = " ";
        }
        this.tfTitle = this.CA.JOutputLabel(sTitle, null, this.CA.getForeground(), this.CA.getBackground(), 0, this.CA.getTitleFont());
        this.sTitle = sTitle;
        this.tfTitle.setBackground(Color.white);
        this.setTitle();
        final JPanel jPanel = this.CA.JPanel(this.CA.getForeground(), this.CA.getBackground(), new FlowLayout());
        (this.bButton = new JButton(this.CA.getParameter("MSG_OK_BUTTON", "   OK   "))).setBackground(this.CA.getBackground());
        this.bButton.setFont(this.CA.getPlainFont());
        this.bButton.addActionListener(this);
        jPanel.add(this.bButton);
        this.getContentPane().setBackground(this.CA.getBackground());
        this.getContentPane().add("North", this.CA.JBorderPanel(this.CA.JOutputLabel(" ", null, this.CA.getForeground(), this.CA.getBackground(), 0, this.CA.getTinyFont()), this.CA.JOutputLabel(s, null, this.CA.getForeground(), this.CA.getBackground(), 0, s.equals(" ") ? this.CA.getTinyFont() : this.CA.getBoldFont()), null, null, this.tfTitle, this.CA.getBackground()));
        this.getContentPane().add("South", jPanel);
        this.getContentPane().add("Center", panel);
        this.hide();
        this.setSize(n, n2);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - n) / 2, (screenSize.height - n2) / 2);
    }
    
    public CalculatorWindow(final String s, final JPanel panel, final CalculatorApplet calculatorApplet, final int n, final int n2) {
        this(s, null, panel, calculatorApplet, n, n2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.CA.calculate();
        if (!this.bError) {
            this.hide();
        }
    }
    
    public boolean getShown() {
        return this.bShow;
    }
    
    public void hide() {
        if (this.getShown()) {
            super.hide();
            this.CA.calculate();
            this.bShow = false;
        }
    }
    
    public void setTitle() {
        this.tfTitle.setForeground(this.CA.cTitle);
        this.tfTitle.setText(this.sTitle);
    }
    
    public void setTitle(final String text) {
        this.tfTitle.setForeground(this.CA.cTitle);
        this.tfTitle.setText(text);
    }
    
    public void setTitle(final String text, final Color foreground) {
        this.tfTitle.setForeground(foreground);
        this.tfTitle.setText(text);
    }
    
    public void setTitle(final String text, final Color foreground, final Color background, final boolean bError) {
        this.tfTitle.setBackground(background);
        this.tfTitle.setForeground(foreground);
        this.tfTitle.setText(text);
        this.bError = bError;
    }
    
    public void show() {
        super.show();
        this.toFront();
        this.bShow = true;
    }
}
