// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Insets;
import anon.util.JAPMessages;
import java.awt.Cursor;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlippingPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static final String MSG_CLICK_TO_SHOW;
    private JPanel m_panelContainer;
    private JPanel m_panelSmall;
    private JPanel m_panelFull;
    private JLabel m_labelBttn;
    private CardLayout m_Layout;
    private Window m_Parent;
    private boolean m_bIsFlipped;
    private static final String IMG_UP = "arrow.gif";
    private static final String IMG_DOWN = "arrow90.gif";
    static /* synthetic */ Class class$gui$FlippingPanel;
    
    public FlippingPanel(final Window parent) {
        this.m_bIsFlipped = false;
        this.m_Parent = parent;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        (this.m_labelBttn = new JLabel(GUIUtils.loadImageIcon("arrow.gif", true))).setCursor(Cursor.getPredefinedCursor(12));
        this.m_labelBttn.setToolTipText(JAPMessages.getString(FlippingPanel.MSG_CLICK_TO_SHOW));
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(3, 0, 0, 0);
        gridBagConstraints.anchor = 18;
        this.m_labelBttn.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                FlippingPanel.this.m_bIsFlipped = !FlippingPanel.this.m_bIsFlipped;
                FlippingPanel.this.m_Layout.next(FlippingPanel.this.m_panelContainer);
                if (FlippingPanel.this.m_bIsFlipped) {
                    FlippingPanel.this.m_labelBttn.setIcon(GUIUtils.loadImageIcon("arrow90.gif", true));
                }
                else {
                    FlippingPanel.this.m_labelBttn.setIcon(GUIUtils.loadImageIcon("arrow.gif", true));
                }
                FlippingPanel.this.m_Parent.pack();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
            }
        });
        this.add(this.m_labelBttn, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.m_panelContainer = new JPanel();
        this.m_Layout = new CardLayout();
        this.m_panelContainer.setLayout(this.m_Layout);
        this.add(this.m_panelContainer, gridBagConstraints);
        this.m_panelSmall = new JPanel(new GridLayout(1, 1));
        this.m_panelContainer.add(this.m_panelSmall, "SMALL", 0);
        this.m_panelFull = new JPanel(new GridLayout(1, 1));
        this.m_Layout.addLayoutComponent(this.m_panelFull, "FULL");
        this.m_panelContainer.add(this.m_panelFull, "FULL", 1);
    }
    
    public void setFullPanel(final JPanel panel) {
        this.m_panelFull.removeAll();
        this.m_panelFull.add(panel);
    }
    
    public JPanel getFullPanel() {
        return (JPanel)this.m_panelFull.getComponent(0);
    }
    
    public void setSmallPanel(final JPanel panel) {
        this.m_panelSmall.removeAll();
        this.m_panelSmall.add(panel);
    }
    
    public JPanel getSmallPanel() {
        return (JPanel)this.m_panelSmall.getComponent(0);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.m_panelFull.getPreferredSize();
        final Dimension preferredSize2 = this.m_panelSmall.getPreferredSize();
        preferredSize.width = Math.max(preferredSize.width, preferredSize2.width);
        final Dimension dimension = preferredSize;
        dimension.width += GUIUtils.loadImageIcon("arrow.gif", true).getIconWidth();
        if (!this.m_bIsFlipped) {
            preferredSize.height = preferredSize2.height;
        }
        return preferredSize;
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = this.m_panelFull.getMinimumSize();
        final Dimension minimumSize2 = this.m_panelSmall.getMinimumSize();
        minimumSize.width = Math.max(minimumSize.width, minimumSize2.width);
        final Dimension dimension = minimumSize;
        dimension.width += GUIUtils.loadImageIcon("arrow.gif", true).getIconWidth();
        if (!this.m_bIsFlipped) {
            minimumSize.height = minimumSize2.height;
        }
        minimumSize.height = Math.max(minimumSize.height, GUIUtils.loadImageIcon("arrow90.gif", true).getIconHeight());
        return minimumSize;
    }
    
    public Dimension getMaximumSize() {
        final Dimension maximumSize = this.m_panelFull.getMaximumSize();
        final Dimension maximumSize2 = this.m_panelSmall.getMaximumSize();
        maximumSize.width = Math.max(maximumSize.width, maximumSize2.width);
        final Dimension dimension = maximumSize;
        dimension.width += GUIUtils.loadImageIcon("arrow.gif", true).getIconWidth();
        if (!this.m_bIsFlipped) {
            maximumSize.height = maximumSize2.height;
        }
        return maximumSize;
    }
    
    public void setFlipped(final boolean b) {
        if (b == this.m_bIsFlipped) {
            return;
        }
        this.m_labelBttn.dispatchEvent(new MouseEvent(this.m_labelBttn, 500, 0L, 0, 0, 0, 1, false));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_CLICK_TO_SHOW = ((FlippingPanel.class$gui$FlippingPanel == null) ? (FlippingPanel.class$gui$FlippingPanel = class$("gui.FlippingPanel")) : FlippingPanel.class$gui$FlippingPanel).getName() + "_clickToShow";
    }
}
