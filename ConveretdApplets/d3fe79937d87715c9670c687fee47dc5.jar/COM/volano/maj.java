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

public class maj implements Cloneable
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
    
    public static void a(final Component component, final maj maj) {
        b(component, maj);
        a(component, maj.m);
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
    
    public static void a(final TextComponent textComponent, final maj maj) {
        textComponent.setForeground(maj.j);
    }
    
    public static void b(final TextComponent textComponent, final maj maj) {
        textComponent.setForeground(maj.k);
    }
    
    private static void b(final Component component, final maj maj) {
        if (component instanceof Container) {
            final Container container = (Container)component;
            container.setBackground(maj.a);
            container.setForeground(maj.f);
            final Component[] components = container.getComponents();
            for (int i = 0; i < components.length; ++i) {
                b(components[i], maj);
            }
        }
        else if (component instanceof TextComponent) {
            final TextComponent textComponent = (TextComponent)component;
            if (textComponent.isEditable()) {
                textComponent.setBackground(maj.e);
                textComponent.setForeground(maj.j);
            }
            else {
                textComponent.setBackground(maj.d);
                textComponent.setForeground(maj.i);
            }
        }
        else if (component instanceof List) {
            component.setBackground(maj.c);
            component.setForeground(maj.h);
        }
        else if (component instanceof Button) {
            component.setBackground(maj.b);
            component.setForeground(maj.g);
        }
        else if (component instanceof mai) {
            final mai mai = (mai)component;
            mai.setBackground(maj.a);
            mai.setForeground(maj.f);
            mai.j = maj.l;
        }
        else {
            component.setBackground(maj.a);
            component.setForeground(maj.f);
        }
        component.repaint();
    }
    
    public maj(final int n) {
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
        this.n = 0;
        this.n = n;
    }
    
    public maj() {
        this(0);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
