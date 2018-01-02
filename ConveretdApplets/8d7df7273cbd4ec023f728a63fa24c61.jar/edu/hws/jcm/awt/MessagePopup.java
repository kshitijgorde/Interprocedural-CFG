// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Component;
import java.awt.event.ActionListener;

public class MessagePopup implements ActionListener, ErrorReporter
{
    private String errorMessage;
    private Controller errorSource;
    private Component source;
    private Window popup;
    
    public MessagePopup(final Component source) {
        this.source = source;
    }
    
    public void setErrorMessage(final Controller errorSource, final String errorMessage) {
        if (this.popup != null) {
            this.clearErrorMessage();
        }
        if (errorMessage == null) {
            return;
        }
        this.errorSource = errorSource;
        this.errorMessage = errorMessage;
        Component component;
        for (component = this.source; component != null && !(component instanceof Frame); component = component.getParent()) {}
        if (component != null) {
            this.popup = new Dialog((Frame)component, "Error Message", true);
        }
        else {
            this.popup = new Frame("Error Message");
        }
        this.popup.setBackground(Color.white);
        this.popup.add(new MC(errorMessage), "Center");
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(2, 10, 10));
        final Button button = new Button("    OK    ");
        button.addActionListener(this);
        panel.add(button);
        this.popup.add(panel, "South");
        this.popup.pack();
        if (component == null) {
            this.popup.setLocation(100, 80);
        }
        else {
            this.popup.setLocation(component.getLocation().x + 50, component.getLocation().y + 30);
        }
        this.popup.addWindowListener(new SimpleWindowCloser(false));
        this.popup.show();
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public synchronized void clearErrorMessage() {
        if (this.popup == null) {
            return;
        }
        this.popup.dispose();
        this.errorMessage = null;
        if (this.errorSource != null) {
            this.errorSource.errorCleared();
        }
        this.errorSource = null;
        this.popup = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.clearErrorMessage();
    }
    
    private static class MC extends Canvas
    {
        private String message;
        private Vector messageStrings;
        private int messageWidth;
        private int messageHeight;
        private Font font;
        private int lineHeight;
        private int fontAscent;
        
        MC(final String message) {
            if (message == null) {
                this.message = "";
            }
            else {
                this.message = message;
            }
        }
        
        public Dimension getPreferredSize() {
            if (this.messageStrings == null) {
                this.makeStringList();
            }
            return new Dimension(this.messageWidth + 20, this.messageHeight + 20);
        }
        
        public void paint(final Graphics graphics) {
            if (this.messageStrings == null) {
                this.makeStringList();
            }
            int fontAscent = (this.getSize().height - this.messageHeight) / 2 + this.fontAscent;
            if (fontAscent < this.fontAscent) {
                fontAscent = this.fontAscent;
            }
            int n = (this.getSize().width - this.messageWidth) / 2;
            if (n < 0) {
                n = 0;
            }
            graphics.setFont(this.font);
            for (int i = 0; i < this.messageStrings.size(); ++i) {
                graphics.drawString((String)this.messageStrings.elementAt(i), n, fontAscent);
                fontAscent += this.lineHeight;
            }
        }
        
        private void makeStringList() {
            this.messageStrings = new Vector();
            this.font = new Font("Dialog", 0, 12);
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            this.lineHeight = fontMetrics.getHeight() + 3;
            this.fontAscent = fontMetrics.getAscent();
            final int stringWidth = fontMetrics.stringWidth(this.message);
            if (stringWidth <= 280) {
                this.messageStrings.addElement(this.message);
                this.messageWidth = 280;
                this.messageHeight = this.lineHeight;
            }
            else {
                if (stringWidth > 1800) {
                    this.messageWidth = Math.min(500, stringWidth / 6);
                }
                else {
                    this.messageWidth = 300;
                }
                int n = 0;
                String s = "    ";
                String string = "";
                this.message = String.valueOf(this.message) + " ";
                for (int i = 0; i < this.message.length(); ++i) {
                    if (this.message.charAt(i) == ' ') {
                        if (fontMetrics.stringWidth(String.valueOf(s) + string) > this.messageWidth + 8) {
                            this.messageStrings.addElement(s);
                            n = Math.max(n, fontMetrics.stringWidth(s));
                            s = "";
                        }
                        s = String.valueOf(s) + string;
                        if (s.length() > 0) {
                            s = String.valueOf(s) + ' ';
                        }
                        string = "";
                    }
                    else {
                        string = String.valueOf(string) + this.message.charAt(i);
                    }
                }
                if (s.length() > 0) {
                    this.messageStrings.addElement(s);
                    n = Math.max(n, fontMetrics.stringWidth(s));
                }
                this.messageHeight = this.lineHeight * this.messageStrings.size() - fontMetrics.getLeading();
                this.messageWidth = Math.max(280, n);
            }
        }
    }
}
