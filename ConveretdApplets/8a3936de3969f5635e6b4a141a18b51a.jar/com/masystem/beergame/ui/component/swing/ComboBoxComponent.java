// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.UIResource;
import java.awt.Component;
import javax.swing.JList;
import com.masystem.beergame.resource.ResourceManager;
import javax.swing.ListCellRenderer;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.ComboBoxUI;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.scene.PaintProperties;
import javax.swing.JComboBox;

public final class ComboBoxComponent extends JComboBox implements HasPaintProperties, NeedsInitialization
{
    private static final long serialVersionUID = 1L;
    private PaintProperties paintProperties;
    private StretchableImage tempImage;
    private CustomComboBoxUI ui;
    
    public ComboBoxComponent(final String[] array, final StretchableImage tempImage) {
        super(array);
        this.paintProperties = new PaintProperties();
        this.tempImage = tempImage;
        this.setOpaque(tempImage != null && !tempImage.getSourceImage().getColorModel().hasAlpha());
    }
    
    @Override
    public final void initialize() {
        this.ui = new CustomComboBoxUI(this.tempImage);
        this.tempImage = null;
        this.setUI(this.ui);
    }
    
    @Override
    public final void setFont(final Font font) {
        super.setFont(font);
        if (this.ui != null && this.ui.getMainRenderer() != null && this.ui.getPopupRenderer() != null) {
            this.ui.getMainRenderer().setFont(font);
            this.ui.getPopupRenderer().setFont(font);
        }
    }
    
    public final void setTextColor(final Color textColor) {
        this.setForeground(textColor);
        this.ui.getMainRenderer().setTextColor(textColor);
        this.ui.getPopupRenderer().setTextColor(textColor);
    }
    
    public final void setInsets(final int n, final int n2, final int n3, final int n4) {
        this.ui.getMainRenderer().setBorder(new EmptyBorder(new Insets(n2, n, n4, n3)));
    }
    
    @Override
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
    }
    
    @Override
    public final void paint(final Graphics graphics) {
        final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
        super.paint(graphics2);
        graphics2.dispose();
    }
    
    static class PopupComboBoxRenderer extends TextLabelComponent implements ListCellRenderer
    {
        private static final long serialVersionUID = 1L;
        
        public PopupComboBoxRenderer() {
            this.setFont((Font)ResourceManager.getResource("DefaultFont"));
            this.setOpaque(true);
        }
        
        @Override
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            if (b) {
                this.setBackground(list.getSelectionBackground());
            }
            else {
                this.setBackground(list.getBackground());
            }
            this.setText((o == null) ? "" : o.toString());
            return this;
        }
        
        public static final class UIResource extends PopupComboBoxRenderer implements javax.swing.plaf.UIResource
        {
            private static final long serialVersionUID = 1L;
        }
    }
    
    static class MainComboBoxRenderer extends StretchableTextFieldComponent implements ListCellRenderer
    {
        private static final long serialVersionUID = 1L;
        
        public MainComboBoxRenderer() {
            super(new StretchableImage(ResourceManager.getOptimizedImage("textfield.png")));
            this.setInsets(14, 14, 14, 14);
            this.setFont((Font)ResourceManager.getResource("DefaultFont"));
        }
        
        @Override
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            this.setText((o == null) ? "" : o.toString());
            return this;
        }
        
        public static final class UIResource extends MainComboBoxRenderer implements javax.swing.plaf.UIResource
        {
            private static final long serialVersionUID = 1L;
            
            public UIResource(final StretchableImage stretchableImage) {
            }
        }
    }
    
    static final class CustomComboBoxUI extends BasicComboBoxUI
    {
        private final MainComboBoxRenderer mainRenderer;
        private final PopupComboBoxRenderer popupRenderer;
        
        public CustomComboBoxUI(final StretchableImage stretchableImage) {
            this.mainRenderer = new MainComboBoxRenderer.UIResource(stretchableImage);
            this.popupRenderer = new PopupComboBoxRenderer.UIResource();
        }
        
        @Override
        public final void installUI(final JComponent component) {
            super.installUI(component);
            component.setOpaque(false);
            component.setBackground(null);
        }
        
        @Override
        public final void paintCurrentValueBackground(final Graphics graphics, final Rectangle rectangle, final boolean b) {
        }
        
        @Override
        public final void paintCurrentValue(final Graphics graphics, final Rectangle rectangle, final boolean b) {
            this.currentValuePane.paintComponent(graphics, this.mainRenderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, true, false), this.comboBox, rectangle.x, rectangle.y, rectangle.width, rectangle.height, false);
        }
        
        @Override
        protected final Rectangle rectangleForCurrentValue() {
            final int width = this.comboBox.getWidth();
            final int height = this.comboBox.getHeight();
            final Insets insets = this.getInsets();
            return new Rectangle(insets.left, insets.top, width - (insets.left + insets.right), height - (insets.top + insets.bottom));
        }
        
        @Override
        protected final JButton createArrowButton() {
            final BasicArrowButton basicArrowButton;
            (basicArrowButton = new BasicArrowButton(this, 5) {
                private static final long serialVersionUID = 1L;
                
                @Override
                public final void paint(final Graphics graphics) {
                    final Color color = graphics.getColor();
                    final int width = this.getSize().width;
                    final int height;
                    final int max = Math.max(Math.min(((height = this.getSize().height) - 4) / 5, (width - 4) / 5), 2);
                    this.paintTriangle(graphics, (width - max) / 2, (height - max) / 2, max, this.direction, this.isEnabled());
                    graphics.setColor(color);
                }
            }).setBackground(null);
            basicArrowButton.setOpaque(false);
            return basicArrowButton;
        }
        
        @Override
        protected final ListCellRenderer createRenderer() {
            return this.popupRenderer;
        }
        
        public final MainComboBoxRenderer getMainRenderer() {
            return this.mainRenderer;
        }
        
        public final PopupComboBoxRenderer getPopupRenderer() {
            return this.popupRenderer;
        }
    }
}
