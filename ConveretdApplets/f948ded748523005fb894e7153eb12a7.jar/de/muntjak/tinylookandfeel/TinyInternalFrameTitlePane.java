// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.plaf.ButtonUI;
import java.awt.FontMetrics;
import javax.swing.Icon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.Graphics;
import javax.swing.JInternalFrame;
import java.beans.PropertyChangeListener;
import javax.swing.UIManager;
import java.awt.LayoutManager;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class TinyInternalFrameTitlePane extends BasicInternalFrameTitlePane implements LayoutManager
{
    protected boolean isPalette;
    private int buttonsWidth;
    static TinyWindowButtonUI iconButtonUI;
    static TinyWindowButtonUI maxButtonUI;
    static TinyWindowButtonUI closeButtonUI;
    
    protected void installDefaults() {
        super.installDefaults();
        this.frame.setFrameIcon(UIManager.getDefaults().getIcon("InternalFrame.icon"));
    }
    
    protected PropertyChangeListener createPropertyChangeListener() {
        return new TinyPropertyChangeHandler();
    }
    
    public TinyInternalFrameTitlePane(final JInternalFrame internalFrame) {
        super(internalFrame);
        this.isPalette = false;
    }
    
    protected void paintTitleBackground(final Graphics graphics) {
    }
    
    public boolean isFrameSelected() {
        return this.frame.isSelected();
    }
    
    public boolean isFrameMaximized() {
        return this.frame.isMaximum();
    }
    
    public void paintComponent(final Graphics graphics) {
        if (Theme.frameIsTransparent[Theme.derivedStyle[Theme.style]]) {
            this.frame.setOpaque(false);
        }
        final boolean leftToRight = this.frame.getComponentOrientation().isLeftToRight();
        final boolean selected = this.frame.isSelected();
        final int width = this.getWidth();
        final int height = this.getHeight();
        MetalLookAndFeel.getWindowTitleInactiveForeground();
        int n = leftToRight ? 2 : (width - 2);
        final String title = this.frame.getTitle();
        final Icon frameIcon = this.frame.getFrameIcon();
        if (frameIcon != null) {
            int round = Math.round((height - frameIcon.getIconHeight()) / 2.0f);
            if (!leftToRight) {
                n -= frameIcon.getIconWidth();
            }
            if (Theme.derivedStyle[Theme.style] == 1) {
                ++round;
            }
            frameIcon.paintIcon(this.frame, graphics, n, round);
            n += (leftToRight ? (frameIcon.getIconWidth() + 2) : -2);
        }
        if (title != null) {
            graphics.setFont(this.getFont());
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int stringWidth = fontMetrics.stringWidth(title);
            final int n2 = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent() + 1;
            if (!leftToRight) {
                n -= stringWidth;
            }
            if (selected) {
                graphics.setColor(Theme.frameTitleColor[Theme.style].getColor());
                graphics.drawString(title, n, n2);
                final int n3 = n + (leftToRight ? (stringWidth + 2) : -2);
            }
            else {
                graphics.setColor(Theme.frameTitleDisabledColor[Theme.style].getColor());
                graphics.drawString(title, n, n2);
                final int n4 = n + (leftToRight ? (stringWidth + 2) : -2);
            }
        }
    }
    
    protected LayoutManager createLayout() {
        return this;
    }
    
    protected void addSubComponents() {
        super.addSubComponents();
    }
    
    protected void setButtonIcons() {
    }
    
    protected void createButtons() {
        if (TinyInternalFrameTitlePane.iconButtonUI == null) {
            TinyInternalFrameTitlePane.iconButtonUI = TinyWindowButtonUI.createButtonUIForType(2);
            TinyInternalFrameTitlePane.maxButtonUI = TinyWindowButtonUI.createButtonUIForType(1);
            TinyInternalFrameTitlePane.closeButtonUI = TinyWindowButtonUI.createButtonUIForType(0);
        }
        (this.iconButton = new SpecialUIButton(TinyInternalFrameTitlePane.iconButtonUI)).addActionListener(this.iconifyAction);
        this.iconButton.setRolloverEnabled(true);
        this.iconButton.addMouseListener(new RolloverListener(this.iconButton, this.iconifyAction));
        (this.maxButton = new SpecialUIButton(TinyInternalFrameTitlePane.maxButtonUI)).addActionListener(this.maximizeAction);
        this.maxButton.setRolloverEnabled(true);
        this.maxButton.addMouseListener(new RolloverListener(this.maxButton, this.maximizeAction));
        (this.closeButton = new SpecialUIButton(TinyInternalFrameTitlePane.closeButtonUI)).addActionListener(this.closeAction);
        this.closeButton.setRolloverEnabled(true);
        this.closeButton.addMouseListener(new RolloverListener(this.closeButton, this.closeAction));
        this.iconButton.putClientProperty("externalFrameButton", Boolean.FALSE);
        this.maxButton.putClientProperty("externalFrameButton", Boolean.FALSE);
        this.closeButton.putClientProperty("externalFrameButton", Boolean.FALSE);
        this.iconButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.iconifyButtonAccessibleName"));
        this.maxButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.maximizeButtonAccessibleName"));
        this.closeButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.closeButtonAccessibleName"));
        if (this.frame.isSelected()) {
            this.activate();
        }
        else {
            this.deactivate();
        }
    }
    
    public void paintPalette(final Graphics graphics) {
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.getPreferredSize(container);
    }
    
    public Dimension getPreferredSize(final Container container) {
        this.isPalette = (this.frame.getClientProperty("isPalette") == Boolean.TRUE);
        int n = 22;
        if (this.frame.isClosable()) {
            n += 19;
        }
        if (this.frame.isMaximizable()) {
            n += 19;
        }
        if (this.frame.isIconifiable()) {
            n += 19;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final String title = this.frame.getTitle();
        final int n2 = (title != null) ? fontMetrics.stringWidth(title) : 0;
        int n3;
        if (((title != null) ? title.length() : 0) > 3) {
            final int stringWidth = fontMetrics.stringWidth(title.substring(0, 3) + "...");
            n3 = n + ((n2 < stringWidth) ? n2 : stringWidth);
        }
        else {
            n3 = n + n2;
        }
        final Dimension dimension = new Dimension(n3, this.isPalette ? Theme.framePaletteTitleHeight[Theme.derivedStyle[Theme.style]] : Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]]);
        if (this.getBorder() != null) {
            final Insets borderInsets = this.getBorder().getBorderInsets(container);
            final Dimension dimension2 = dimension;
            dimension2.height += borderInsets.top + borderInsets.bottom;
            final Dimension dimension3 = dimension;
            dimension3.width += borderInsets.left + borderInsets.right;
        }
        return dimension;
    }
    
    public Dimension getMinimumSize() {
        this.isPalette = (this.frame.getClientProperty("isPalette") == Boolean.TRUE);
        return new Dimension(32, this.isPalette ? Theme.framePaletteTitleHeight[Theme.derivedStyle[Theme.style]] : Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]]);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void setPalette(final boolean isPalette) {
        this.isPalette = isPalette;
    }
    
    public boolean isPalette() {
        return this.isPalette;
    }
    
    public void layoutContainer(final Container container) {
        this.isPalette = (this.frame.getClientProperty("isPalette") == Boolean.TRUE);
        final boolean leftToRight = this.frame.getComponentOrientation().isLeftToRight();
        final int height = this.closeButton.getPreferredSize().height;
        final int width = this.getWidth();
        int n = leftToRight ? width : 0;
        int n2 = (this.getHeight() - height) / 2 + 1;
        if (Theme.derivedStyle[Theme.style] == 1) {
            ++n2;
        }
        int n3;
        if (this.isPalette) {
            n3 = Theme.framePaletteButtonSize[Theme.derivedStyle[Theme.style]].width;
        }
        else {
            n3 = Theme.frameInternalButtonSize[Theme.derivedStyle[Theme.style]].width;
        }
        if (this.frame.isClosable()) {
            final int n4 = 2;
            n += (leftToRight ? (-n4 - n3) : n4);
            this.closeButton.setBounds(n, n2, n3, height);
            if (!leftToRight) {
                n += n3;
            }
        }
        if (this.frame.isMaximizable()) {
            final int n5 = 2;
            n += (leftToRight ? (-n5 - n3) : n5);
            this.maxButton.setBounds(n, n2, n3, height);
            if (!leftToRight) {
                n += n3;
            }
        }
        if (this.frame.isIconifiable()) {
            final int n6 = (this.frame.isMaximizable() && Theme.style == 1) ? 0 : 2;
            n += (leftToRight ? (-n6 - n3) : n6);
            this.iconButton.setBounds(n, n2, n3, height);
            if (!leftToRight) {
                n += n3;
            }
        }
        this.buttonsWidth = (leftToRight ? (width - n) : n);
    }
    
    public void activate() {
        this.closeButton.setEnabled(true);
        this.iconButton.setEnabled(true);
        this.maxButton.setEnabled(true);
    }
    
    public void deactivate() {
        this.closeButton.setEnabled(false);
        this.iconButton.setEnabled(false);
        this.maxButton.setEnabled(false);
    }
    
    public Font getFont() {
        Font font;
        if (this.isPalette) {
            font = UIManager.getFont("InternalFrame.paletteTitleFont");
        }
        else {
            font = UIManager.getFont("InternalFrame.normalTitleFont");
        }
        if (font == null) {
            font = new Font("SansSerife", 1, 12);
        }
        return font;
    }
    
    class TinyPropertyChangeHandler extends PropertyChangeHandler
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getPropertyName().equals("selected")) {
                final Boolean b = (Boolean)propertyChangeEvent.getNewValue();
                TinyInternalFrameTitlePane.this.iconButton.putClientProperty("paintActive", b);
                TinyInternalFrameTitlePane.this.closeButton.putClientProperty("paintActive", b);
                TinyInternalFrameTitlePane.this.maxButton.putClientProperty("paintActive", b);
            }
            super.propertyChange(propertyChangeEvent);
        }
    }
    
    class RolloverListener implements MouseListener
    {
        JButton button;
        Action action;
        
        public RolloverListener(final JButton button, final Action action) {
            this.button = button;
            this.action = action;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            this.action.actionPerformed(new ActionEvent(this, 1001, this.button.getText()));
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            this.button.getModel().setRollover(true);
            if (!this.button.isEnabled()) {
                this.button.setEnabled(true);
            }
            this.button.repaint();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            this.button.getModel().setRollover(false);
            if (!TinyInternalFrameTitlePane.this.frame.isSelected()) {
                this.button.setEnabled(false);
            }
            this.button.repaint();
        }
    }
}
