// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$L;

import java.util.Enumeration;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerEvent;
import java.awt.Container;
import java.awt.Component;
import java.util.Vector;
import java.awt.event.ContainerListener;
import java.awt.event.KeyListener;

public class $I9 implements KeyListener, ContainerListener
{
    private Vector v_l;
    
    public void $L2B(final $G9 $g9) {
        this.v_l.removeElement($g9);
    }
    
    public void $W9(final $G9 $g9) {
        this.v_l.addElement($g9);
    }
    
    public synchronized void $X9(final Component component) {
        component.addKeyListener(this);
        if (component instanceof Container) {
            final Container container = (Container)component;
            container.addContainerListener(this);
            final Component[] components = container.getComponents();
            for (int n = 0; components != null && n < components.length; ++n) {
                this.$X9(components[n]);
            }
        }
    }
    
    public $I9() {
        this(null);
    }
    
    public $I9(final Component component) {
        this.v_l = new Vector();
        if (component != null) {
            this.$X9(component);
        }
    }
    
    public void componentAdded(final ContainerEvent containerEvent) {
        this.$X9(containerEvent.getChild());
    }
    
    public void componentRemoved(final ContainerEvent containerEvent) {
        this.removeComponent(containerEvent.getChild());
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final Enumeration<$G9> elements = this.v_l.elements();
        while (elements.hasMoreElements()) {
            final $G9 $g9 = elements.nextElement();
            if (keyEvent.getSource() != $g9) {
                $g9.$Z9(keyEvent);
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final Enumeration<$G9> elements = this.v_l.elements();
        while (elements.hasMoreElements()) {
            final $G9 $g9 = elements.nextElement();
            if (keyEvent.getSource() != $g9) {
                $g9.$B0(keyEvent);
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final Enumeration<$G9> elements = this.v_l.elements();
        while (elements.hasMoreElements()) {
            final $G9 $g9 = elements.nextElement();
            if (keyEvent.getSource() != $g9) {
                $g9.$A0(keyEvent);
            }
        }
    }
    
    public synchronized void removeComponent(final Component component) {
        component.removeKeyListener(this);
        if (component instanceof Container) {
            final Container container = (Container)component;
            container.removeContainerListener(this);
            final Component[] components = container.getComponents();
            for (int i = 0; i < components.length; ++i) {
                this.removeComponent(components[i]);
            }
        }
    }
}
