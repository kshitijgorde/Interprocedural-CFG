// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Event;

public class Finder
{
    public static Object[] findComponent(final Event event, final Container container) {
        Component component2;
        Component component;
        if (dx.c < 65800) {
            component = (component2 = (Component)event.target);
        }
        else {
            int x = event.x;
            int y = event.y;
            component = (component2 = container.getComponentAt(x, y));
            while (component2 instanceof Container && component2 != null) {
                x -= component2.getBounds().x;
                y -= component2.getBounds().y;
                final Container container2 = (Container)component2;
                if ((component2 = container2.getComponentAt(x, y)) == container2) {
                    break;
                }
            }
        }
        String s = "";
        for (Container parent = (Container)component2; parent != null; parent = parent.getParent()) {
            if (parent instanceof s) {
                final String a = ((s)parent).a(component2);
                if (a != null) {
                    s = a;
                    break;
                }
            }
        }
        return new Object[] { component, s };
    }
    
    public static Informer findInformer(final Component component) {
        if (component instanceof Informer) {
            return (Informer)component;
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                final Informer informer;
                if ((informer = findInformer(components[i])) != null) {
                    return informer;
                }
            }
        }
        return null;
    }
}
