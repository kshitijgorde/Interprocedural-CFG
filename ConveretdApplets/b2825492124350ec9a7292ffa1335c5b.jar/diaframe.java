import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class diaframe extends Frame
{
    int width;
    int height;
    diaImage image;
    
    diaframe(final String s, final diaImage image) {
        super(s);
        this.width = image.width + 60;
        this.height = image.height + 60;
        this.image = image;
        this.resize(this.width, this.height);
        this.setLayout(new BorderLayout());
        this.add("Center", image);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.width) / 2, (screenSize.height - this.height) / 2);
        this.pack();
        this.show();
        this.repaint();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                diaframe.this.dispose();
            }
        });
    }
    
    public Insets getInsets() {
        return new Insets(30, 30, 30, 30);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.dispose();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.width, this.height);
        this.image.repaint();
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
}
