import java.awt.Choice;
import java.awt.Button;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Container;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class throws extends continue
{
    throws(final FnChartsApplet fnChartsApplet, final switch switch1, final Color color, final Color color2) {
        super(3, new Insets(4, 4, 4, 4));
        this.setLayout(new FlowLayout(1));
        final Label label = new Label(switch1.a("msgFnChartsMaximized"));
        label.setFont(new Font("SansSerif", 1, 16));
        this.add(label);
        this.b(this, color);
        this._(this, color2);
    }
    
    void b(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.b(components[i], background);
                }
            }
        }
    }
    
    void _(final Component component, final Color foreground) {
        component.setForeground(foreground);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this._(components[i], foreground);
                }
            }
        }
    }
}
