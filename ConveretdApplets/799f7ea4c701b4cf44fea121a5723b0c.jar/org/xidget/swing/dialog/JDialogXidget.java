// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.dialog;

import javax.swing.JDialog;
import java.awt.Window;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.feature.ToplevelWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.dialog.IDialogFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JDialogXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IDialogFeature dialogFeature;
    private IWidgetFeature widgetFeature;
    private ITitleFeature titleFeature;
    private JDialogWidgetCreationFeature creationFeature;
    private IWidgetContainerFeature containerFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.dialogFeature = new JDialogFeature(this);
        this.widgetFeature = new ToplevelWidgetFeature(this);
        this.titleFeature = new JDialogTitleFeature(this);
        this.creationFeature = new JDialogWidgetCreationFeature(this);
        this.containerFeature = new JDialogContainerFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == IDialogFeature.class) {
            return (T)this.dialogFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == ITitleFeature.class) {
            return (T)this.titleFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJDialog();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJDialog();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJDialog();
        }
        if (clazz == Window.class) {
            return (T)this.creationFeature.getJDialog();
        }
        if (clazz == JDialog.class) {
            return (T)this.creationFeature.getJDialog();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
