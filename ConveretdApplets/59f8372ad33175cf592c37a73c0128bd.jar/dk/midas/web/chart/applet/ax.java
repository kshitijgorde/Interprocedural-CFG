// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Frame;

public class ax extends Frame
{
    static final int int = 8;
    int byte;
    Label new;
    Label try;
    Label if;
    Label a;
    Label for;
    aa do;
    
    ax(final String title, final aa do1) {
        super(title);
        this.byte = -1;
        this.do = do1;
        this.setTitle(title);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setLayout(new GridLayout(4, 2));
        this.add(new Label("LineID :"));
        this.add(this.new = new Label("Unknown"));
        this.add(new Label("Actual :"));
        this.add(this.try = new Label("Unknown"));
        this.add(new Label("Actual+1 :"));
        this.add(this.if = new Label("Unknown"));
        this.add(this.a = new Label("Increase :"));
        this.add(this.for = new Label("Unknown"));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                ax.this.setVisible(false);
                ax.this.dispose();
            }
        });
    }
    
    public void a(final aj aj, final int n, final float n2, final float n3) {
        if (this.byte == n) {
            if (!this.isVisible()) {
                this.setVisible(true);
            }
            return;
        }
        this.new.setText("T" + n);
        this.try.setText(this.do.a(n2));
        this.if.setText(this.do.a(n3));
        if (n3 > n2) {
            this.a.setText("Increase :");
        }
        else {
            this.a.setText("Decrease :");
        }
        this.for.setText(this.do.a(n3 - n2));
    }
    
    public void a() {
        this.new.setText("Unknown");
        this.try.setText("Unknown");
        this.if.setText("Unknown");
        this.for.setText("Unknown");
        this.byte = -1;
        this.setVisible(true);
        this.toFront();
    }
}
