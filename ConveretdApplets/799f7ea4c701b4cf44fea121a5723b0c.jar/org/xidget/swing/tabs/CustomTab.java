// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import org.xidget.Creator;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import org.xidget.IXidget;
import javax.swing.JPanel;

public class CustomTab extends JPanel
{
    private static final int[] closeButtonXs;
    private static final int[] closeButtonYs;
    private IXidget xidget;
    private boolean hovering;
    private JLabel label;
    private CloseButton closeButton;
    
    static {
        closeButtonXs = new int[] { 0, 2, 4, 5, 7, 9, 9, 7, 7, 9, 9, 7, 5, 4, 2, 0, 0, 2, 2, 0, 0 };
        closeButtonYs = new int[] { 0, 0, 2, 2, 0, 0, 2, 4, 5, 7, 9, 9, 7, 7, 9, 9, 7, 5, 4, 2, 0 };
    }
    
    public CustomTab(final IXidget xidget) {
        this.xidget = xidget;
        this.setLayout(new FlowLayout(0, 5, 0));
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        this.add(this.label = new JLabel());
        this.add(this.closeButton = new CloseButton());
    }
    
    public void setImage(final Image image) {
        this.label.setIcon(new ImageIcon(image));
    }
    
    public void setTitle(final String text) {
        this.label.setText(text);
    }
    
    public void setCloseButton(final boolean visible) {
        this.closeButton.setVisible(visible);
    }
    
    static /* synthetic */ void access$0(final CustomTab customTab, final boolean hovering) {
        customTab.hovering = hovering;
    }
    
    private class CloseButton extends JButton implements ActionListener
    {
        private final MouseListener mouseListener;
        
        public CloseButton() {
            this.mouseListener = new MouseAdapter() {
                @Override
                public void mouseEntered(final MouseEvent mouseEvent) {
                    super.mouseEntered(mouseEvent);
                    CustomTab.access$0(CustomTab.this, true);
                    CloseButton.this.repaint();
                }
                
                @Override
                public void mouseExited(final MouseEvent mouseEvent) {
                    super.mouseExited(mouseEvent);
                    CustomTab.access$0(CustomTab.this, false);
                    CloseButton.this.repaint();
                }
            };
            this.setBorder(BorderFactory.createEmptyBorder());
            this.addActionListener(this);
            this.addMouseListener(this.mouseListener);
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(10, 10);
        }
        
        @Override
        protected void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            final Color color = graphics.getColor();
            graphics.setColor(CustomTab.this.hovering ? Color.pink : Color.white);
            graphics.fillPolygon(CustomTab.closeButtonXs, CustomTab.closeButtonYs, CustomTab.closeButtonXs.length);
            graphics.setColor(color);
            graphics.drawPolygon(CustomTab.closeButtonXs, CustomTab.closeButtonYs, CustomTab.closeButtonXs.length);
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            Creator.getInstance().destroy(CustomTab.this.xidget);
        }
    }
}
