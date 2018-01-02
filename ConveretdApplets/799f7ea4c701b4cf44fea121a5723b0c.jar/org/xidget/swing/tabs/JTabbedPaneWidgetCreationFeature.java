// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.ifeature.IWidgetContainerFeature;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.List;
import java.util.Collections;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.IBindFeature;
import javax.swing.event.ChangeEvent;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Canvas;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.JTabbedPane;
import org.xidget.IXidget;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import org.xidget.ifeature.IWidgetCreationFeature;

public class JTabbedPaneWidgetCreationFeature implements IWidgetCreationFeature
{
    private ComponentListener componentListener;
    private ChangeListener selectionListener;
    private IXidget xidget;
    private JTabbedPane jtabbedPane;
    
    public JTabbedPaneWidgetCreationFeature(final IXidget xidget) {
        this.componentListener = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent componentEvent) {
                Dimension size = null;
                Component[] components;
                for (int length = (components = JTabbedPaneWidgetCreationFeature.this.jtabbedPane.getComponents()).length, i = 0; i < length; ++i) {
                    final Component component = components[i];
                    if (component instanceof Canvas) {
                        size = ((Container)component).getSize();
                        break;
                    }
                }
                if (size != null) {
                    final Iterator<IXidget> iterator = JTabbedPaneWidgetCreationFeature.this.xidget.getChildren().iterator();
                    while (iterator.hasNext()) {
                        final IWidgetCreationFeature widgetCreationFeature = iterator.next().getFeature(IWidgetCreationFeature.class);
                        if (widgetCreationFeature != null) {
                            final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
                            if (lastWidgets.length <= 0) {
                                continue;
                            }
                            ((Component)lastWidgets[0]).setSize(size);
                        }
                    }
                }
            }
        };
        this.selectionListener = new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                final List<IXidget> children = JTabbedPaneWidgetCreationFeature.this.xidget.getChildren();
                final int selectedIndex = JTabbedPaneWidgetCreationFeature.this.jtabbedPane.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < children.size()) {
                    final StatefulContext boundContext = children.get(selectedIndex).getFeature(IBindFeature.class).getBoundContext();
                    if (boundContext != null) {
                        JTabbedPaneWidgetCreationFeature.this.jtabbedPane.removeChangeListener(this);
                        try {
                            JTabbedPaneWidgetCreationFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).modelSelect(Collections.singletonList(boundContext.getObject()));
                        }
                        finally {
                            JTabbedPaneWidgetCreationFeature.this.jtabbedPane.addChangeListener(this);
                        }
                        JTabbedPaneWidgetCreationFeature.this.jtabbedPane.addChangeListener(this);
                    }
                }
            }
        };
        this.xidget = xidget;
    }
    
    @Override
    public void createWidgets() {
        (this.jtabbedPane = new JTabbedPane()).addChangeListener(this.selectionListener);
        this.jtabbedPane.addComponentListener(this.componentListener);
        final IXidget parent = this.xidget.getParent();
        final String title = this.getTitle();
        if (title != null && title.length() > 0 && parent != null && !parent.getConfig().isType("tabs")) {
            this.jtabbedPane.setBorder(new TitledBorder(title));
        }
        final IWidgetContainerFeature widgetContainerFeature = this.xidget.getParent().getFeature(IWidgetContainerFeature.class);
        if (widgetContainerFeature != null) {
            widgetContainerFeature.addWidget(this.xidget);
        }
    }
    
    private String getTitle() {
        final IModelObject config = this.xidget.getConfig();
        final IExpression childGet = Xlate.childGet(config, "title", Xlate.get(config, "title", (IExpression)null));
        if (childGet != null) {
            return childGet.evaluateString();
        }
        return null;
    }
    
    @Override
    public void destroyWidgets() {
        this.jtabbedPane.getParent().remove(this.jtabbedPane);
        this.jtabbedPane.getParent().validate();
        this.jtabbedPane = null;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jtabbedPane };
    }
    
    public JTabbedPane getJTabbedPane() {
        return this.jtabbedPane;
    }
}
