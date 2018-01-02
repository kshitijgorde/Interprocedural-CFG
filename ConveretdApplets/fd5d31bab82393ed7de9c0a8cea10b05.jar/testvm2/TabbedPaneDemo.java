// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class TabbedPaneDemo extends JPanel
{
    static /* synthetic */ Class class$testvm2$TabbedPaneDemo;
    
    public TabbedPaneDemo() {
        super(new GridLayout(1, 1));
        final JTabbedPane tabbedPane = new JTabbedPane();
        final ImageIcon icon = createImageIcon("images/middle.gif");
        final JComponent panel1 = this.makeTextPanel("Panel #1");
        tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
        tabbedPane.setMnemonicAt(0, 49);
        final JComponent panel2 = this.makeTextPanel("Panel #2");
        tabbedPane.addTab("Tab 2", icon, panel2, "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, 50);
        final JComponent panel3 = this.makeTextPanel("Panel #3");
        tabbedPane.addTab("Tab 3", icon, panel3, "Still does nothing");
        tabbedPane.setMnemonicAt(2, 51);
        final JComponent panel4 = this.makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", icon, panel4, "Does nothing at all");
        tabbedPane.setMnemonicAt(3, 52);
        this.add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(1);
    }
    
    protected JComponent makeTextPanel(final String text) {
        final JPanel panel = new JPanel(false);
        final JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(0);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
    protected static ImageIcon createImageIcon(final String path) {
        final URL imgURL = ((TabbedPaneDemo.class$testvm2$TabbedPaneDemo == null) ? (TabbedPaneDemo.class$testvm2$TabbedPaneDemo = class$("testvm2.TabbedPaneDemo")) : TabbedPaneDemo.class$testvm2$TabbedPaneDemo).getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(3);
        frame.add(new TabbedPaneDemo(), "Center");
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
}
