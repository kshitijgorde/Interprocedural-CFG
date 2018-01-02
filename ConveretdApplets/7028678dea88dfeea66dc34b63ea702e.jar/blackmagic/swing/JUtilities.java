// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JWindow;
import javax.swing.JComponent;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class JUtilities
{
    static /* synthetic */ Class class$blackmagic$swing$JUtilities;
    
    public static void centerWindow(final Window window) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = window.getSize();
        window.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
    }
    
    public static void centerWindowRandomly(final Window window, final double n) {
        assert n >= 0.0 && n <= 1.0 : "vPercentageArea not between 0 & 100%";
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int n2 = screenSize.width / 2;
        final int n3 = screenSize.height / 2;
        final double n4 = screenSize.width * n;
        final double n5 = screenSize.height * n;
        final Dimension size = window.getSize();
        if (n4 < size.width || n5 < size.height) {
            centerWindow(window);
            return;
        }
        window.setLocation((int)(Math.random() * (n4 - size.width) + (screenSize.width / 2 - n4 / 2.0)), (int)(Math.random() * (n5 - size.height) + (screenSize.height / 2 - n5 / 2.0)));
    }
    
    public static void equalizeComponentSizes(final List list) {
        final Dimension dimension = new Dimension(0, 0);
        for (int i = 0; i < list.size(); ++i) {
            final Dimension preferredSize = list.get(i).getPreferredSize();
            dimension.width = Math.max(dimension.width, (int)preferredSize.getWidth());
            dimension.height = Math.max(dimension.height, (int)preferredSize.getHeight());
        }
        for (int j = 0; j < list.size(); ++j) {
            final JComponent component = list.get(j);
            component.setPreferredSize((Dimension)dimension.clone());
            component.setMaximumSize((Dimension)dimension.clone());
        }
    }
    
    public static void showSplashScreen(final String s, final String s2) {
        final JWindow window = new JWindow();
        window.getContentPane().add(getContents(s, s2), "Center");
        window.pack();
        centerWindow(window);
        window.setVisible(true);
        killSplashScreenLater(window);
    }
    
    private static void killSplashScreenLater(final JWindow window) {
        final Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                window.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private static JComponent getContents(final String s, final String s2) {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(gridBagLayout);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 2), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        int n = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = n++;
        panel.add(getImageAsJLabel(s), gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.gridy = n++;
        panel.add(new JLabel(s2), gridBagConstraints);
        return panel;
    }
    
    private static JLabel getImageAsJLabel(final String s) {
        return new JLabel(new ImageIcon(ClassLoader.getSystemClassLoader().getResource(s)));
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
        $assertionsDisabled = !((JUtilities.class$blackmagic$swing$JUtilities == null) ? (JUtilities.class$blackmagic$swing$JUtilities = class$("blackmagic.swing.JUtilities")) : JUtilities.class$blackmagic$swing$JUtilities).desiredAssertionStatus();
    }
}
