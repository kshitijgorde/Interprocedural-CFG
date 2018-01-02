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

public class vao implements Cloneable
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
    
    public static void a(final Component component, final vao vao) {
        b(component, vao);
        a(component, vao.m);
    }
    
    public static void a(final Component component, final Font font) {
        if (component instanceof vk) {
            final vk vk = (vk)component;
            vk.setFont(font);
            vk.invalidate();
            return;
        }
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
    
    public static void a(final TextComponent textComponent, final vao vao) {
        textComponent.setForeground(vao.j);
    }
    
    public static void b(final TextComponent textComponent, final vao vao) {
        textComponent.setForeground(vao.k);
    }
    
    private static void b(final Component component, final vao vao) {
        if (component instanceof vk) {
            final vk vk = (vk)component;
            if (vk.a()) {
                vk.setBackground(vao.e);
                vk.setForeground(vao.j);
            }
            else {
                vk.setBackground(vao.d);
                vk.setForeground(vao.i);
            }
        }
        else if (component instanceof Container) {
            final Container container = (Container)component;
            container.setBackground(vao.a);
            container.setForeground(vao.f);
            final Component[] components = container.getComponents();
            for (int i = 0; i < components.length; ++i) {
                b(components[i], vao);
            }
        }
        else if (component instanceof TextComponent) {
            final TextComponent textComponent = (TextComponent)component;
            if (textComponent.isEditable()) {
                textComponent.setBackground(vao.e);
                textComponent.setForeground(vao.j);
            }
            else {
                textComponent.setBackground(vao.d);
                textComponent.setForeground(vao.i);
            }
        }
        else if (component instanceof List) {
            component.setBackground(vao.c);
            component.setForeground(vao.h);
        }
        else if (component instanceof Button) {
            component.setBackground(vao.b);
            component.setForeground(vao.g);
        }
        else if (component instanceof vag) {
            final vag vag = (vag)component;
            vag.setBackground(vao.a);
            vag.setForeground(vao.f);
            vag.o = vao.l;
        }
        else {
            component.setBackground(vao.a);
            component.setForeground(vao.f);
        }
        component.repaint();
    }
    
    public vao(final int n) {
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
    
    public vao() {
        this(0);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
