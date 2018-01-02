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

class s extends a
{
    s(final FnChartsApplet fnChartsApplet, final o o, final Color color, final Color color2) {
        super(3, new Insets(4, 4, 4, 4));
        this.setLayout(new FlowLayout(1));
        final Label label = new Label(o.b("msgFnChartsMaximized"));
        label.setFont(new Font("SansSerif", 1, 16));
        this.add(label);
        this._(this, color);
        this.a(this, color2);
    }
    
    void _(final Component component, final Color background) {
        component.setBackground(background);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this._(components[i], background);
                }
            }
        }
    }
    
    void a(final Component component, final Color foreground) {
        component.setForeground(foreground);
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof TextField || components[i] instanceof Panel || components[i] instanceof List || components[i] instanceof Button || components[i] instanceof Choice) {
                    this.a(components[i], foreground);
                }
            }
        }
    }
}
