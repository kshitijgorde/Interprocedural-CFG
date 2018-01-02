// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import com.objectbox.loader.JBURLClassloader;
import com.objectbox.loader.StateChangeEvent;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.gui.JBProcessButton;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.objectbox.runner.gui.BeanRunner;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import com.objectbox.loader.StateChangeListener;
import java.util.Observable;

public class JBProcessModel extends Observable implements StateChangeListener, ActionListener
{
    private Hashtable holderobjects;
    private BeanRunner activeRunner;
    private BeanRunner lastRemovedRunner;
    private Component lastRemovedComponent;
    
    public JBProcessModel() {
        this.holderobjects = new Hashtable();
        this.activeRunner = null;
        this.lastRemovedRunner = null;
        this.lastRemovedComponent = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void addBeanRunner(final BeanRunner activeRunner, final Component component) {
        try {
            ((JBProcessButton)component).addActionListener(this);
            this.holderobjects.put(component, activeRunner);
            this.holderobjects.put(activeRunner, component);
            this.activeRunner = activeRunner;
            activeRunner.getLoader().addStateChangeListener(this);
            this.setChanged();
            this.notifyObservers("add");
        }
        catch (Exception ex) {
            JBLogger.log(ex.toString());
        }
    }
    
    public BeanRunner getActiveRunner() {
        return this.activeRunner;
    }
    
    public BeanRunner getBeanRunner(final Component component) {
        return this.holderobjects.get(component);
    }
    
    public Component getComponent(final BeanRunner beanRunner) {
        return this.holderobjects.get(beanRunner);
    }
    
    public Component getLastRemovedComponent() {
        return this.lastRemovedComponent;
    }
    
    public BeanRunner getLastRemovedRunner() {
        return this.lastRemovedRunner;
    }
    
    public void onChange(final StateChangeEvent stateChangeEvent) {
        final JBURLClassloader jburlClassloader = (JBURLClassloader)stateChangeEvent.getSource();
        if (jburlClassloader.getOwner() instanceof BeanRunner) {
            JBLogger.log("Loader event " + stateChangeEvent.thecurrentstate);
            switch (stateChangeEvent.thecurrentstate) {
                case 6: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(3);
                    this.removeBeanRunner((BeanRunner)jburlClassloader.getOwner());
                    break;
                }
                case 3: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(2);
                    break;
                }
                case 2: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(1);
                    break;
                }
                case 5: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(5);
                    break;
                }
                case 4: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(4);
                    break;
                }
                default: {
                    ((JBProcessButton)this.holderobjects.get(jburlClassloader.getOwner())).setState(2);
                    break;
                }
            }
        }
    }
    
    public void removeBeanRunner(final BeanRunner lastRemovedRunner) {
        if (lastRemovedRunner != null) {
            final Component lastRemovedComponent = this.holderobjects.get(lastRemovedRunner);
            this.holderobjects.remove(lastRemovedRunner);
            this.holderobjects.remove(lastRemovedComponent);
            this.lastRemovedRunner = lastRemovedRunner;
            this.lastRemovedComponent = lastRemovedComponent;
            this.setChanged();
            this.notifyObservers("remove");
        }
    }
    
    public void setActiveBeanRunner(final BeanRunner activeRunner) {
        this.activeRunner = activeRunner;
        this.setChanged();
        this.notifyObservers("active");
    }
}
