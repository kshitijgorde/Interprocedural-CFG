// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.ui;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import ytdfriends.action.FriendsXRayColorFunction;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import ytdfriends.util.ColorAction;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import edu.berkeley.guir.prefuse.graph.Entity;
import ytdfriends.FriendsPanel;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiPanel extends JPanel
{
    private JPanel buttonsPanel;
    private JLabel[] labels;
    private JToggleButton[] colorers;
    private FriendsPanel fPanel;
    private Entity curProfile;
    public static final String[] ATTR;
    public static final String[] LABEL;
    
    static {
        ATTR = new String[] { "nfriends", "gender", "network" };
        LABEL = new String[] { "Friends", "Gender", "Network" };
    }
    
    public GuiPanel(final FriendsPanel fPanel) {
        this.setLayout(new BorderLayout());
        this.fPanel = fPanel;
        this.setBackground(Color.WHITE);
        (this.buttonsPanel = new JPanel()).setBackground(Color.WHITE);
        this.initUI();
        this.add(this.buttonsPanel, "West");
    }
    
    private void initUI() {
        final Font f = new Font("SansSerif", 1, 12);
        final ColorAction colorAction = new ColorAction(this.fPanel);
        final ButtonGroup buttG = new ButtonGroup();
        final JToggleButton inv = new JCheckBox();
        buttG.add(inv);
        final Dimension d = new Dimension(22, 17);
        this.colorers = new JToggleButton[GuiPanel.LABEL.length];
        for (int i = 0; i < GuiPanel.LABEL.length; ++i) {
            final int attr = FriendsXRayColorFunction.getAttributeIndex(GuiPanel.ATTR[i]);
            if (attr != -1) {
                (this.colorers[i] = new JCheckBox()).putClientProperty("attr", new Integer(attr));
                this.colorers[i].putClientProperty("inv", inv);
                this.colorers[i].addActionListener(colorAction);
                buttG.add(this.colorers[i]);
            }
            else {
                (this.colorers[i] = new JToggleButton() {
                    public void paintComponent(final Graphics g) {
                    }
                }).setEnabled(false);
            }
            this.colorers[i].setMaximumSize(d);
            this.colorers[i].setPreferredSize(d);
            this.colorers[i].setBackground(Color.WHITE);
        }
        final MouseListener mL = new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (!SwingUtilities.isLeftMouseButton(e)) {
                    return;
                }
                final Object src = e.getSource();
                for (int i = 0; i < GuiPanel.this.labels.length; ++i) {
                    if (src == GuiPanel.this.labels[i]) {
                        GuiPanel.this.colorers[i].doClick();
                    }
                }
            }
        };
        this.labels = new JLabel[GuiPanel.LABEL.length];
        for (int j = 0; j < GuiPanel.LABEL.length; ++j) {
            (this.labels[j] = new JLabel(GuiPanel.LABEL[j])).setHorizontalAlignment(4);
            this.labels[j].setVerticalAlignment(1);
            this.labels[j].setFont(f);
            this.labels[j].addMouseListener(mL);
        }
        for (int j = 0; j < GuiPanel.LABEL.length; ++j) {
            this.addAttribute(j);
        }
    }
    
    private void addAttribute(final int i) {
        this.labels[i].setCursor(Cursor.getPredefinedCursor(12));
        this.colorers[i].setCursor(Cursor.getPredefinedCursor(12));
        this.buttonsPanel.add(this.labels[i]);
        this.buttonsPanel.add(this.colorers[i]);
    }
}
