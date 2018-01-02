// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.plaf.metal.MetalComboBoxIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.ComboBoxEditor;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TinyComboBoxUI extends BasicComboBoxUI
{
    protected boolean isDisplaySizeDirty;
    protected Dimension cachedDisplaySize;
    
    public TinyComboBoxUI() {
        this.isDisplaySizeDirty = true;
        this.cachedDisplaySize = new Dimension(0, 0);
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyComboBoxUI();
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
    }
    
    protected ComboBoxEditor createEditor() {
        return new TinyComboBoxEditor.UIResource();
    }
    
    protected ComboPopup createPopup() {
        return new MetalComboPopup(this.comboBox);
    }
    
    protected JButton createArrowButton() {
        final TinyComboBoxButton tinyComboBoxButton = new TinyComboBoxButton(this.comboBox, new MetalComboBoxIcon(), this.comboBox.isEditable(), this.currentValuePane, this.listBox);
        tinyComboBoxButton.setMargin(new Insets(0, 0, 0, 0));
        tinyComboBoxButton.putClientProperty("isComboBoxButton", Boolean.TRUE);
        return tinyComboBoxButton;
    }
    
    protected void installComponents() {
        super.installComponents();
        if (this.arrowButton != null) {
            this.arrowButton.setFocusable(false);
        }
    }
    
    public PropertyChangeListener createPropertyChangeListener() {
        return new TinyPropertyChangeListener();
    }
    
    protected void editablePropertyChanged(final PropertyChangeEvent propertyChangeEvent) {
    }
    
    protected LayoutManager createLayoutManager() {
        return new TinyComboBoxLayoutManager();
    }
    
    protected Rectangle rectangleForCurrentValue2() {
        final int width = this.comboBox.getWidth();
        final int height = this.comboBox.getHeight();
        final Insets insets = this.getInsets();
        int n = height - (insets.top + insets.bottom);
        if (this.arrowButton != null) {
            n = Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]];
        }
        if (this.comboBox.getComponentOrientation().isLeftToRight()) {
            return new Rectangle(insets.left, insets.top, width - (insets.left + insets.right + n), height - (insets.top + insets.bottom));
        }
        return new Rectangle(insets.left + n, insets.top, width - (insets.left + insets.right + n), height - (insets.top + insets.bottom));
    }
    
    protected void removeListeners() {
        if (this.propertyChangeListener != null) {
            this.comboBox.removePropertyChangeListener(this.propertyChangeListener);
        }
    }
    
    public void configureEditor() {
        super.configureEditor();
    }
    
    public void unconfigureEditor() {
        super.unconfigureEditor();
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        if (!this.isMinimumSizeDirty) {
            this.isDisplaySizeDirty = true;
            return new Dimension(this.cachedMinimumSize);
        }
        final Insets insets = Theme.comboInsets[Theme.style];
        final Dimension displaySize;
        final Dimension dimension = displaySize = this.getDisplaySize();
        displaySize.width += Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]];
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        this.cachedMinimumSize.setSize(dimension.width, dimension.height);
        this.isMinimumSizeDirty = false;
        return new Dimension(this.cachedMinimumSize);
    }
    
    protected Dimension getDisplaySize() {
        if (!this.isDisplaySizeDirty) {
            return new Dimension(this.cachedDisplaySize);
        }
        Dimension dimension = new Dimension();
        ListCellRenderer renderer = this.comboBox.getRenderer();
        if (renderer == null) {
            renderer = new DefaultListCellRenderer();
        }
        final Object prototypeDisplayValue = this.comboBox.getPrototypeDisplayValue();
        if (prototypeDisplayValue != null) {
            dimension = this.getSizeForComponent(renderer.getListCellRendererComponent(this.listBox, prototypeDisplayValue, -1, false, false));
        }
        else {
            final ComboBoxModel model = this.comboBox.getModel();
            final int size = model.getSize();
            if (size > 0) {
                for (int i = 0; i < size; ++i) {
                    final Dimension sizeForComponent = this.getSizeForComponent(renderer.getListCellRendererComponent(this.listBox, model.getElementAt(i), -1, false, false));
                    dimension.width = Math.max(dimension.width, sizeForComponent.width);
                    dimension.height = Math.max(dimension.height, sizeForComponent.height);
                }
            }
            else {
                dimension = this.getDefaultSize();
                if (this.comboBox.isEditable()) {
                    dimension.width = 100;
                }
            }
        }
        if (this.comboBox.isEditable()) {
            final Dimension preferredSize = this.editor.getPreferredSize();
            dimension.width = Math.max(dimension.width, preferredSize.width);
            dimension.height = Math.max(dimension.height, preferredSize.height);
        }
        this.cachedDisplaySize.setSize(dimension.width, dimension.height);
        this.isDisplaySizeDirty = false;
        return dimension;
    }
    
    private Dimension getSizeForComponent(final Component component) {
        this.currentValuePane.add(component);
        component.setFont(this.comboBox.getFont());
        final Dimension preferredSize = component.getPreferredSize();
        this.currentValuePane.remove(component);
        return preferredSize;
    }
    
    public class MetalComboPopup extends BasicComboPopup
    {
        public MetalComboPopup(final JComboBox comboBox) {
            super(comboBox);
        }
        
        public void delegateFocus(final MouseEvent mouseEvent) {
            super.delegateFocus(mouseEvent);
        }
    }
    
    public class TinyComboBoxLayoutManager implements LayoutManager
    {
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public void removeLayoutComponent(final Component component) {
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            final JComboBox comboBox = (JComboBox)container;
            return container.getPreferredSize();
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            final JComboBox comboBox = (JComboBox)container;
            return container.getMinimumSize();
        }
        
        public void layoutContainer(final Container container) {
            final JComboBox comboBox = (JComboBox)container;
            final int width = comboBox.getWidth();
            final int height = comboBox.getHeight();
            if (TinyComboBoxUI.this.comboBox.isEditable()) {
                if (TinyComboBoxUI.this.arrowButton != null) {
                    TinyComboBoxUI.this.arrowButton.setBounds(width - Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]], 0, Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]], height);
                }
                if (TinyComboBoxUI.this.editor != null) {
                    TinyComboBoxUI.this.editor.setBounds(TinyComboBoxUI.this.rectangleForCurrentValue2());
                }
            }
            else {
                TinyComboBoxUI.this.arrowButton.setBounds(0, 0, width, height);
            }
        }
    }
    
    public class TinyPropertyChangeListener extends PropertyChangeHandler
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            super.propertyChange(propertyChangeEvent);
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName.equals("editable")) {
                ((TinyComboBoxButton)TinyComboBoxUI.this.arrowButton).setIconOnly(TinyComboBoxUI.this.comboBox.isEditable());
                TinyComboBoxUI.this.isMinimumSizeDirty = true;
                TinyComboBoxUI.this.isDisplaySizeDirty = true;
                TinyComboBoxUI.this.comboBox.revalidate();
            }
            else if (propertyName.equals("background")) {
                TinyComboBoxUI.this.listBox.setBackground((Color)propertyChangeEvent.getNewValue());
            }
            else if (propertyName.equals("foreground")) {
                TinyComboBoxUI.this.listBox.setForeground((Color)propertyChangeEvent.getNewValue());
            }
        }
    }
}
