import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Image;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends Panel
{
    private d a;
    private Scrollbar b;
    
    public void a(final Color foreground) {
        this.b.setForeground(foreground);
    }
    
    public v(final int n, final AppletContext appletContext, final Image image, final y y, final br br, final w w) {
        this.a(n, appletContext, image, y, br, w);
    }
    
    public void b(final Color background) {
        this.b.setBackground(background);
    }
    
    public void a(final Font font, final int n) {
        this.a.a(font, n);
    }
    
    public void c(final Color background) {
        this.a.setBackground(background);
    }
    
    public void a(final String s, final Color color, final boolean b) {
        this.a.a(s, color, b);
    }
    
    private void a(final int n, final AppletContext appletContext, final Image image, final y y, final br br, final w w) {
        this.a = new d(n, appletContext, image, y, br, w);
        this.b = new Scrollbar(1, 0, 1, 0, n);
        this.a.a(this.b);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 90.0;
        gridBagConstraints.weighty = 30.0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
    }
    
    public void a() {
        this.a.b();
    }
    
    public String[] b() {
        return this.a.c();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id >= 601 && event.id <= 605) {
            this.a.a(this.b.getValue() + 1);
        }
        return false;
    }
    
    void a(final boolean b) {
        this.a.a(b);
    }
}
