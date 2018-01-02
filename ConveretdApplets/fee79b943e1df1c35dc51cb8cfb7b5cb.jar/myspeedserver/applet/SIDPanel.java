// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SIDPanel extends JPanel implements ActionListener
{
    private myspeed TU;
    private JTextField EV;
    private JButton FV;
    private boolean DV;
    
    public SIDPanel(final Applet applet) {
        this.DV = false;
        this.TU = (myspeed)applet;
    }
    
    private void init() {
        this.EV = new JTextField(25);
        this.FV = new JButton(this.TU.RC("sidok"));
        this.setLayout(null);
        this.add(this.EV);
        this.add(this.FV);
        this.EV.setSize(this.EV.getPreferredSize());
        this.FV.setSize(this.FV.getPreferredSize());
        this.EV.setVisible(true);
        this.FV.setVisible(true);
        this.EV.invalidate();
        this.FV.invalidate();
        this.EV.addActionListener(this);
        this.FV.addActionListener(this);
        this.invalidate();
        this.validate();
        this.EV.requestFocus();
        this.setOpaque(false);
    }
    
    public void focus() {
        this.requestFocus();
        if (this.EV != null) {
            this.EV.requestFocus();
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (!this.DV) {
            this.DV = true;
            this.init();
            this.repaint();
        }
        else {
            this.doPaint(graphics);
        }
    }
    
    private void doPaint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(new Color(15658751));
        graphics.fillRect(1, 1, width - 2, height - 2);
        graphics.setColor(Color.black);
        final Vector vector = new Vector<String>();
        final String rc = this.TU.RC("sid");
        final int n = width - 60;
        String s = "";
        int n2 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(rc, " \t");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int stringWidth = fontMetrics.stringWidth(nextToken);
            if (s.length() > 0) {
                if (n2 + stringWidth > n) {
                    vector.addElement(s);
                    n2 = 0;
                    s = "";
                }
                else {
                    s = String.valueOf(s) + " ";
                    n2 += fontMetrics.stringWidth(" ");
                }
            }
            s = String.valueOf(s) + nextToken;
            n2 += stringWidth;
        }
        if (s.length() > 0) {
            vector.addElement(s);
        }
        int n3 = (height - 80) / 2 - vector.size() * fontMetrics.getHeight() / 2 + 30;
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            graphics.drawString(s2, width / 2 - fontMetrics.stringWidth(s2) / 2, n3);
            n3 += fontMetrics.getHeight();
        }
        final int n4 = n3 + fontMetrics.getHeight();
        final int n5 = this.EV.getSize().width + 5 + this.FV.getSize().width;
        final int n6 = height - 30 - this.EV.getSize().height;
        this.EV.setSize(this.EV.getPreferredSize());
        this.FV.setSize(this.FV.getPreferredSize());
        this.EV.setLocation(width / 2 - n5 / 2, n6);
        this.FV.setLocation(width / 2 - n5 / 2 + 5 + this.EV.getSize().width, n6);
    }
    
    public String getSID() {
        final String s = (this.EV == null) ? null : this.EV.getText().trim();
        return (s != null && s.length() > 0) ? s : null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.FV || actionEvent.getSource() == this.EV) {
            this.TU.doStartMySpeed(true);
        }
    }
}
