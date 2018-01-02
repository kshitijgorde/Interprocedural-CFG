// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JSplitPane;
import java.awt.Cursor;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.Color;
import javax.swing.plaf.basic.BasicSplitPaneDivider;

class TinySplitPaneDivider extends BasicSplitPaneDivider
{
    private int inset;
    private Color controlColor;
    private Color primaryControlColor;
    
    public TinySplitPaneDivider(final BasicSplitPaneUI basicSplitPaneUI) {
        super(basicSplitPaneUI);
        this.inset = 2;
        this.controlColor = MetalLookAndFeel.getControl();
        this.primaryControlColor = MetalLookAndFeel.getPrimaryControl();
        this.setLayout(new MetalDividerLayout());
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.controlColor);
        final Rectangle clipBounds = graphics.getClipBounds();
        final Insets insets = this.getInsets();
        graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        final Dimension size;
        final Dimension dimension = size = this.getSize();
        size.width -= this.inset * 2;
        final Dimension dimension2 = dimension;
        dimension2.height -= this.inset * 2;
        final int inset = this.inset;
        final int inset2 = this.inset;
        if (insets != null) {
            final Dimension dimension3 = dimension;
            dimension3.width -= insets.left + insets.right;
            final Dimension dimension4 = dimension;
            dimension4.height -= insets.top + insets.bottom;
            final int n = inset + insets.left;
            final int n2 = inset2 + insets.top;
        }
        super.paint(graphics);
    }
    
    protected JButton createLeftOneTouchButton() {
        final JButton button = new JButton() {
            public void setBorder(final Border border) {
            }
            
            public void paint(final Graphics graphics) {
                if (TinySplitPaneDivider.this.getSplitPaneFromSuper() != null) {
                    TinySplitPaneDivider.this.getOneTouchSizeFromSuper();
                    final int orientationFromSuper = TinySplitPaneDivider.this.getOrientationFromSuper();
                    graphics.setColor(Theme.backColor[Theme.style].getColor());
                    graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                    graphics.setColor(Theme.scrollArrowColor[Theme.style].getColor());
                    if (orientationFromSuper == 0) {
                        graphics.drawLine(2, 1, 3, 1);
                        graphics.drawLine(1, 2, 4, 2);
                        graphics.drawLine(0, 3, 5, 3);
                    }
                    else {
                        graphics.drawLine(1, 2, 1, 3);
                        graphics.drawLine(2, 1, 2, 4);
                        graphics.drawLine(3, 0, 3, 5);
                    }
                }
            }
            
            public boolean isFocusTraversable() {
                return false;
            }
        };
        button.setRequestFocusEnabled(false);
        button.setCursor(Cursor.getPredefinedCursor(0));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
    
    protected JButton createRightOneTouchButton() {
        final JButton button = new JButton() {
            public void setBorder(final Border border) {
            }
            
            public void paint(final Graphics graphics) {
                if (TinySplitPaneDivider.this.getSplitPaneFromSuper() != null) {
                    TinySplitPaneDivider.this.getOneTouchSizeFromSuper();
                    final int orientationFromSuper = TinySplitPaneDivider.this.getOrientationFromSuper();
                    graphics.setColor(Theme.backColor[Theme.style].getColor());
                    graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                    graphics.setColor(Theme.scrollArrowColor[Theme.style].getColor());
                    if (orientationFromSuper == 0) {
                        graphics.drawLine(2, 3, 3, 3);
                        graphics.drawLine(1, 2, 4, 2);
                        graphics.drawLine(0, 1, 5, 1);
                    }
                    else {
                        graphics.drawLine(3, 2, 3, 3);
                        graphics.drawLine(2, 1, 2, 4);
                        graphics.drawLine(1, 0, 1, 5);
                    }
                }
            }
            
            public boolean isFocusTraversable() {
                return false;
            }
        };
        button.setCursor(Cursor.getPredefinedCursor(0));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setRequestFocusEnabled(false);
        return button;
    }
    
    int getOneTouchSizeFromSuper() {
        return 6;
    }
    
    int getOneTouchOffsetFromSuper() {
        return 2;
    }
    
    int getOrientationFromSuper() {
        return super.orientation;
    }
    
    JSplitPane getSplitPaneFromSuper() {
        return super.splitPane;
    }
    
    JButton getLeftButtonFromSuper() {
        return super.leftButton;
    }
    
    JButton getRightButtonFromSuper() {
        return super.rightButton;
    }
    
    public class MetalDividerLayout implements LayoutManager
    {
        public void layoutContainer(final Container container) {
            final JButton leftButtonFromSuper = TinySplitPaneDivider.this.getLeftButtonFromSuper();
            final JButton rightButtonFromSuper = TinySplitPaneDivider.this.getRightButtonFromSuper();
            final JSplitPane splitPaneFromSuper = TinySplitPaneDivider.this.getSplitPaneFromSuper();
            final int orientationFromSuper = TinySplitPaneDivider.this.getOrientationFromSuper();
            final int oneTouchSizeFromSuper = TinySplitPaneDivider.this.getOneTouchSizeFromSuper();
            final int oneTouchOffsetFromSuper = TinySplitPaneDivider.this.getOneTouchOffsetFromSuper();
            final Insets insets = TinySplitPaneDivider.this.getInsets();
            if (leftButtonFromSuper != null && rightButtonFromSuper != null && container == TinySplitPaneDivider.this) {
                if (splitPaneFromSuper.isOneTouchExpandable()) {
                    if (orientationFromSuper == 0) {
                        final int n = (insets != null) ? insets.top : 0;
                        int dividerSize = TinySplitPaneDivider.this.getDividerSize();
                        if (insets != null) {
                            dividerSize -= insets.top + insets.bottom;
                        }
                        final int min = Math.min(dividerSize, oneTouchSizeFromSuper);
                        leftButtonFromSuper.setBounds(oneTouchOffsetFromSuper, n, min * 2, min);
                        rightButtonFromSuper.setBounds(oneTouchOffsetFromSuper + oneTouchSizeFromSuper * 2, n, min * 2, min);
                    }
                    else {
                        int dividerSize2 = TinySplitPaneDivider.this.getDividerSize();
                        final int n2 = (insets != null) ? insets.left : 0;
                        if (insets != null) {
                            dividerSize2 -= insets.left + insets.right;
                        }
                        final int min2 = Math.min(dividerSize2, oneTouchSizeFromSuper);
                        leftButtonFromSuper.setBounds(n2, oneTouchOffsetFromSuper, min2, min2 * 2);
                        rightButtonFromSuper.setBounds(n2, oneTouchOffsetFromSuper + oneTouchSizeFromSuper * 2, min2, min2 * 2);
                    }
                }
                else {
                    leftButtonFromSuper.setBounds(-5, -5, 1, 1);
                    rightButtonFromSuper.setBounds(-5, -5, 1, 1);
                }
            }
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            return new Dimension(0, 0);
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            return new Dimension(0, 0);
        }
        
        public void removeLayoutComponent(final Component component) {
        }
        
        public void addLayoutComponent(final String s, final Component component) {
        }
    }
}
