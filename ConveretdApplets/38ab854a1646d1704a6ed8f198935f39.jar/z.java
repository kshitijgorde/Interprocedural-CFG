import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends JPanel
{
    int if;
    int a;
    int do;
    
    public z() {
        this.if = 0;
        this.a = 50;
        this.do = 50;
    }
    
    public Component add(final Component component) {
        ++this.if;
        final int n = (int)Math.sqrt(this.if + 1);
        final int n2 = this.if / n + 1;
        this.setLayout(new GridLayout(n, n2));
        this.setPreferredSize(new Dimension(n * this.do, n2 * this.a));
        return super.add(component);
    }
    
    public void if(final o o) {
        this.add(new b(new m(100, o, Color.red)));
    }
    
    public void a(final o o) {
        this.add(new b(new y(o, Color.red)));
    }
}
