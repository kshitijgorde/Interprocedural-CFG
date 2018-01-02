// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;

public class MessageBox extends Dialog
{
    public static final int NONE = 0;
    public static final int OK = 1;
    public static final int YESNO = 2;
    public static final int OKCANCEL = 3;
    public static final int YES = 4;
    public static final int NO = 5;
    public static final int CANCEL = 6;
    private static final int hGap = 20;
    private static final int vGap = 15;
    private Label msg;
    private Button okButton;
    private Button cancelButton;
    private Button yesButton;
    private Button noButton;
    private Panel buttonPanel;
    private Panel infoPanel;
    private Panel textPanel;
    private int returnVal;
    
    public static int showMessage(final Frame frame, final String s, final String s2, final int n) {
        final MessageBox messageBox = new MessageBox(frame, s, s2, n);
        messageBox.show(true);
        return messageBox.getResponse();
    }
    
    private MessageBox(final Frame frame, final String s) {
        super(frame, s, true);
        this.returnVal = 0;
    }
    
    private MessageBox(final Frame frame, final String s, final String s2, final int n) {
        this(frame, s);
        this.setResizable(false);
        this.infoPanel = new Panel();
        this.buttonPanel = new Panel();
        this.textPanel = new Panel();
        this.infoPanel.setLayout(new FlowLayout(1, 20, 15));
        this.buttonPanel.setLayout(new FlowLayout());
        Font font = frame.getFont();
        if (font == null) {
            font = new Font("Serif", 0, 12);
        }
        final int n2 = (int)Math.ceil(Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(s2) / this.getMaxWidth());
        if (n2 > 1) {
            this.textPanel.setLayout(new GridLayout(n2, 1, 1, 1));
        }
        else {
            this.textPanel.setLayout(new FlowLayout(0));
        }
        final String[] formatString = this.formatString(s2, n2);
        for (int i = 0; i < formatString.length; ++i) {
            this.textPanel.add(new Label(formatString[i]));
        }
        this.infoPanel.add(this.textPanel);
        if (n == 1) {
            this.okButton = new Button("OK");
            this.buttonPanel.add(this.okButton);
        }
        else if (n == 3) {
            this.okButton = new Button("OK");
            this.cancelButton = new Button("Cancel");
            this.buttonPanel.add(this.okButton);
            this.buttonPanel.add(this.cancelButton);
        }
        else if (n == 2) {
            this.yesButton = new Button("Yes");
            this.noButton = new Button("No");
            this.buttonPanel.add(this.yesButton);
            this.buttonPanel.add(this.noButton);
        }
        this.add("Center", this.infoPanel);
        this.add("South", this.buttonPanel);
        this.pack();
        this.center();
    }
    
    public int getResponse() {
        return this.returnVal;
    }
    
    private void close() {
        this.show(false);
        this.dispose();
    }
    
    public void setBackground(final Color color) {
        this.infoPanel.setBackground(color);
        this.buttonPanel.setBackground(color);
        this.repaint();
    }
    
    private void center() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = this.size().width;
        final int height = this.size().height;
        this.reshape((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.okButton) {
            this.returnVal = 1;
        }
        else if (event.target == this.yesButton) {
            this.returnVal = 4;
        }
        else if (event.target == this.noButton) {
            this.returnVal = 5;
        }
        else if (event.target == this.cancelButton) {
            this.returnVal = 6;
        }
        if (this.returnVal != 0) {
            this.close();
            return true;
        }
        return false;
    }
    
    private int getMaxWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width / 3;
    }
    
    private String[] formatString(final String s, final int n) {
        final String[] array = new String[n];
        final int n2 = s.length() / n;
        for (int i = 0; i < n; ++i) {
            final int index = s.indexOf(" ", n2 * (i + 1));
            if (index == -1) {
                array[i] = s.substring(n2 * i, s.length());
            }
            else {
                array[i] = s.substring(n2 * i, index);
            }
        }
        return array;
    }
}
