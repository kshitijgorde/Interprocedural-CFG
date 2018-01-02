// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Dialog;

public abstract class AppinfoDialog extends Dialog
{
    Dimension d;
    Font f;
    FontMetrics fm;
    AppinfoDialogCanvas aidC;
    int y;
    Color color;
    
    public AppinfoDialog(final String s, final int n, final int n2, final Color color) {
        super(new Frame(), s, true);
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        this.aidC = new AppinfoDialogCanvas();
        this.addWindowListener(new WindowCloser());
        this.color = color;
        this.aidC.resize(n, n2);
        this.add("Center", this.aidC);
        final Panel panel = new Panel();
        panel.setBackground(this.color);
        panel.setLayout(new FlowLayout(2));
        panel.add(new Button("OK"));
        this.add("South", panel);
        this.pack();
        this.move((this.d.width - this.bounds().width) / 2, (this.d.height - this.bounds().height) / 2);
    }
    
    public abstract void printing(final Graphics p0);
    
    void printText(final String s, final Color color, final int n, final Graphics graphics) {
        graphics.setFont(this.f = new Font("Dialog", 0, n));
        this.fm = graphics.getFontMetrics();
        graphics.setColor(color);
        this.y += this.fm.getHeight();
        graphics.drawString(s, (this.size().width - this.fm.stringWidth(s)) / 2, this.y);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.dispose();
            return true;
        }
        return false;
    }
    
    public class AppinfoDialogCanvas extends Canvas
    {
        public void paint(final Graphics graphics) {
            AppinfoDialog.this.printing(graphics);
        }
    }
    
    class WindowCloser extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            AppinfoDialog.this.dispose();
        }
    }
}
