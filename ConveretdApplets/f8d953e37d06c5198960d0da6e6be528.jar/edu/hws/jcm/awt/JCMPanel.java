// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Panel;

public class JCMPanel extends Panel
{
    private int insetGap;
    private Controller controller;
    
    public JCMPanel(final LayoutManager layout) {
        this.enableEvents(2L);
        this.setLayout(layout);
    }
    
    public JCMPanel() {
        this(3);
    }
    
    public JCMPanel(final int n) {
        this(new BorderLayout(n, n));
    }
    
    public JCMPanel(final int n, final int n2) {
        this(n, n2, 3);
    }
    
    public JCMPanel(final int n, final int n2, final int n3) {
        this(new GridLayout(n, n2, n3, n3));
    }
    
    public void setInsetGap(final int insetGap) {
        this.insetGap = insetGap;
    }
    
    public Insets getInsets() {
        return new Insets(this.insetGap, this.insetGap, this.insetGap, this.insetGap);
    }
    
    public Controller getController() {
        if (this.controller == null) {
            this.controller = new Controller();
        }
        return this.controller;
    }
    
    public void gatherInputs() {
        final Controller controller = this.getController();
        controller.notifyControllerOnChange(controller);
    }
    
    public void processContainerEvent(final ContainerEvent containerEvent) {
        final Component child = containerEvent.getChild();
        if (child instanceof JCMPanel) {
            if (containerEvent.getID() == 300) {
                this.getController().add(((JCMPanel)child).getController());
            }
            else if (containerEvent.getID() == 301) {
                this.getController().remove(((JCMPanel)child).getController());
            }
        }
        else if (child instanceof Computable || child instanceof InputObject) {
            if (containerEvent.getID() == 300) {
                this.getController().add(child);
            }
            else if (containerEvent.getID() == 301) {
                this.getController().remove(child);
            }
        }
    }
}
