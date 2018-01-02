// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.Icon;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import org.xmodel.Xlate;
import javax.swing.JComponent;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.ifeature.IScriptFeature;
import java.awt.event.ActionEvent;
import org.xidget.IXidget;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.ifeature.IIconFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JMenuItemWidgetCreationFeature extends SwingWidgetCreationFeature implements IIconFeature, ILabelFeature
{
    private ActionListener actionListener;
    private JMenuItem jMenuItem;
    
    public JMenuItemWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.actionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final IScriptFeature scriptFeature = JMenuItemWidgetCreationFeature.this.xidget.getFeature(IScriptFeature.class);
                if (scriptFeature != null) {
                    final StatefulContext context = JMenuItemWidgetCreationFeature.this.xidget.getFeature(IWidgetContextFeature.class).getContext(actionEvent.getSource());
                    if (context != null) {
                        scriptFeature.runScript("onPress", context);
                    }
                }
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        if (!Xlate.get(this.xidget.getConfig(), "type", "push").equals("push")) {
            throw new UnsupportedOperationException("Only push menu items are currently supported.");
        }
        if (this.xidget.getConfig().isType("separator")) {
            final IXidget parent = this.xidget.getParent();
            final JMenu menu = parent.getFeature(JMenu.class);
            if (menu != null) {
                menu.addSeparator();
            }
            final JPopupMenu popupMenu = parent.getFeature(JPopupMenu.class);
            if (popupMenu != null) {
                popupMenu.addSeparator();
            }
        }
        else {
            (this.jMenuItem = new JMenuItem()).addActionListener(this.actionListener);
        }
        return this.jMenuItem;
    }
    
    @Override
    public Object[] getLastWidgets() {
        if (this.jMenuItem == null) {
            return new Object[0];
        }
        return new Object[] { this.jMenuItem };
    }
    
    public JMenuItem getJMenuItem() {
        return this.jMenuItem;
    }
    
    @Override
    public void setIcon(final Object o) {
        this.jMenuItem.setIcon((Icon)o);
    }
    
    @Override
    public int getLabelWidth() {
        return 0;
    }
    
    @Override
    public void setLabelWidth(final int n) {
    }
    
    @Override
    public void setLabelText(final String text) {
        this.jMenuItem.setText(text);
    }
}
