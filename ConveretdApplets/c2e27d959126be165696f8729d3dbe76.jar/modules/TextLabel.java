// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.awt.Component;
import java.awt.Label;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public class TextLabel extends Panel implements Module
{
    telnet applet;
    
    public void connect(final String host, final int port) {
    }
    
    public void disconnect() {
    }
    
    public String receive(final String s) {
        return null;
    }
    
    public void setLoader(final Object o) {
        this.applet = (telnet)o;
        int rows = 1;
        int cols = 1;
        String tmp = this.applet.getParameter("labelRows");
        if (tmp != null) {
            rows = Integer.parseInt(tmp);
        }
        if ((tmp = this.applet.getParameter("labelCols")) != null) {
            cols = Integer.parseInt(tmp);
        }
        this.setLayout(new GridLayout(rows, cols));
        Font labelFont = null;
        if ((tmp = this.applet.getParameter("labelFont")) != null) {
            final int idx = tmp.indexOf(",");
            int size = 14;
            if (idx != -1) {
                size = Integer.parseInt(tmp.substring(idx + 1));
            }
            labelFont = new Font(tmp, 0, size);
        }
        int no = 1;
        while ((tmp = this.applet.getParameter("label#" + no++)) != null) {
            final Label text = new Label(tmp);
            if (labelFont != null) {
                text.setFont(labelFont);
            }
            this.add(text);
        }
    }
}
