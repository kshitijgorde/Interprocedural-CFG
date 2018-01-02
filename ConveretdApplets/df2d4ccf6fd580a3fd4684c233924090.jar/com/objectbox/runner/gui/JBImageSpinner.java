// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.Image;
import java.beans.PropertyChangeSupport;
import com.objectbox.runner.image.ImageComponent;
import java.awt.Panel;

public class JBImageSpinner extends Panel
{
    private ImageComponent ivjImageComponentAnimation;
    private ImageComponent ivjImageComponentStillLogo;
    protected transient PropertyChangeSupport propertyChange;
    private Image fieldAnimatedImage;
    private Image fieldStillImage;
    private CardLayout ivjCardLayout;
    
    public JBImageSpinner() {
        this.ivjImageComponentAnimation = null;
        this.ivjImageComponentStillLogo = null;
        this.fieldAnimatedImage = null;
        this.fieldStillImage = null;
        this.ivjCardLayout = null;
        this.initialize();
    }
    
    public JBImageSpinner(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjImageComponentAnimation = null;
        this.ivjImageComponentStillLogo = null;
        this.fieldAnimatedImage = null;
        this.fieldStillImage = null;
        this.ivjCardLayout = null;
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    private void connPtoP1SetTarget() {
        try {
            this.setLayout(this.getCardLayout());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    private CardLayout getCardLayout() {
        if (this.ivjCardLayout == null) {
            try {
                (this.ivjCardLayout = new CardLayout()).addLayoutComponent(this.getImageComponentAnimation(), "ANI");
                this.ivjCardLayout.addLayoutComponent(this.getImageComponentStillLogo(), "STILL");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCardLayout;
    }
    
    private ImageComponent getImageComponentAnimation() {
        if (this.ivjImageComponentAnimation == null) {
            try {
                (this.ivjImageComponentAnimation = new ImageComponent()).setName("ImageComponentAnimation");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjImageComponentAnimation;
    }
    
    private ImageComponent getImageComponentStillLogo() {
        if (this.ivjImageComponentStillLogo == null) {
            try {
                (this.ivjImageComponentStillLogo = new ImageComponent()).setName("ImageComponentStillLogo");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjImageComponentStillLogo;
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.connPtoP1SetTarget();
    }
    
    private void initialize() {
        this.setName("JBImageSpinner");
        this.setLayout(new CardLayout());
        this.setSize(70, 49);
        this.add(this.getImageComponentStillLogo(), this.getImageComponentStillLogo().getName());
        this.add(this.getImageComponentAnimation(), this.getImageComponentAnimation().getName());
        this.initConnections();
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t2) {
                frame = new Frame();
            }
            final JBImageSpinner jbImageSpinner = new JBImageSpinner();
            frame.add("Center", jbImageSpinner);
            frame.setSize(jbImageSpinner.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t) {
            System.err.println("Exception occurred in main() of java.awt.Panel");
            t.printStackTrace(System.out);
        }
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setAnimatedImage(final Image image) {
        final Image fieldAnimatedImage = this.fieldAnimatedImage;
        this.fieldAnimatedImage = image;
        this.ivjImageComponentAnimation.setImage(image);
        this.firePropertyChange("animatedImage", fieldAnimatedImage, image);
    }
    
    public void setStillImage(final Image image) {
        final Image fieldStillImage = this.fieldStillImage;
        this.fieldStillImage = image;
        this.ivjImageComponentStillLogo.setImage(image);
        this.firePropertyChange("stillImage", fieldStillImage, image);
    }
    
    public void toggle(final boolean b) {
        if (b) {
            this.getCardLayout().show(this, "ANI");
        }
        else {
            this.getCardLayout().show(this, "STILL");
        }
    }
}
