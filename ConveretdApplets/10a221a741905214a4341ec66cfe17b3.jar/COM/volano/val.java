// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Button;
import java.awt.List;
import java.awt.TextComponent;
import java.awt.Window;
import java.awt.Container;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

public class val implements Cloneable
{
    public Color a;
    public Color b;
    public Color c;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    public Font m;
    public int n;
    
    public static void a(final Component component, final val val) {
        b(component, val);
        a(component, val.m);
    }
    
    public static void a(final Component component, final Font font) {
        if (component instanceof Container) {
            final Container container = (Container)component;
            final Component[] components = container.getComponents();
            for (int i = 0; i < components.length; ++i) {
                a(components[i], font);
            }
            container.invalidate();
            if (container instanceof Window) {
                ((Window)container).pack();
            }
        }
        else {
            component.setFont(font);
            component.invalidate();
        }
    }
    
    public static void a(final TextComponent textComponent, final val val) {
        textComponent.setForeground(val.j);
    }
    
    public static void b(final TextComponent textComponent, final val val) {
        textComponent.setForeground(val.k);
    }
    
    private static void b(final Component component, final val val) {
        if (component instanceof Container) {
            final Container container = (Container)component;
            container.setBackground(val.a);
            container.setForeground(val.f);
            final Component[] components = container.getComponents();
            for (int i = 0; i < components.length; ++i) {
                b(components[i], val);
            }
        }
        else if (component instanceof TextComponent) {
            final TextComponent textComponent = (TextComponent)component;
            if (textComponent.isEditable()) {
                textComponent.setBackground(val.e);
                textComponent.setForeground(val.j);
            }
            else {
                textComponent.setBackground(val.d);
                textComponent.setForeground(val.i);
            }
        }
        else if (component instanceof List) {
            component.setBackground(val.c);
            component.setForeground(val.h);
        }
        else if (component instanceof Button) {
            component.setBackground(val.b);
            component.setForeground(val.g);
        }
        else if (component instanceof vad) {
            final vad vad = (vad)component;
            vad.setBackground(val.a);
            vad.setForeground(val.f);
            vad.j = val.l;
        }
        else {
            component.setBackground(val.a);
            component.setForeground(val.f);
        }
        component.repaint();
    }
    
    public val(final int n) {
        this.a = Color.white;
        this.b = Color.white;
        this.c = Color.white;
        this.d = Color.white;
        this.e = Color.white;
        this.f = Color.black;
        this.g = Color.black;
        this.h = Color.black;
        this.i = Color.black;
        this.j = Color.black;
        this.k = Color.gray;
        this.l = Color.blue;
        this.m = new Font("Dialog", 0, 12);
        this.n = n;
    }
    
    public val() {
        this(0);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
