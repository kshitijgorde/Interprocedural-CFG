// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import blackmagic.swing.HidingDialogListener;
import java.awt.Window;
import blackmagic.swing.JUtilities;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Container;
import javax.swing.JDialog;

final class CompounderMenuFactory
{
    private static final JDialog vUsageDialog;
    
    public static JMenuBar getCompounderMenuBar(final Container container, final CompounderPanel compounderPanel) {
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(getScenarioMenu(compounderPanel));
        menuBar.add(getViewMenu(compounderPanel));
        menuBar.add(getHelpMenu(container));
        return menuBar;
    }
    
    private static JMenu getHelpMenu(final Container container) {
        final JMenu menu = new JMenu("Help");
        menu.setMnemonic(72);
        final JMenuItem menuItem = new JMenuItem("Usage Guide", 85);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(112, 0));
        menu.add(menuItem);
        JUtilities.centerWindow(CompounderMenuFactory.vUsageDialog);
        menuItem.addActionListener(new HidingDialogListener(CompounderMenuFactory.vUsageDialog));
        return menu;
    }
    
    private static JMenu getScenarioMenu(final CompounderPanel compounderPanel) {
        final JMenu menu = new JMenu("Scenario");
        menu.setMnemonic(83);
        final JMenuItem menuItem = new JMenuItem("Configure Base Scenario...", 66);
        final JMenuItem menuItem2 = new JMenuItem("Configure Alternate Scenario...", 65);
        menu.add(menuItem);
        menu.add(menuItem2);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                compounderPanel.configureBaseScenario();
            }
        });
        menuItem.setAccelerator(KeyStroke.getKeyStroke(49, 2));
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                compounderPanel.configureAlternateScenario();
            }
        });
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(50, 2));
        return menu;
    }
    
    private static JMenu getViewMenu(final CompounderPanel compounderPanel) {
        final JMenu menu = new JMenu("View");
        menu.setMnemonic(86);
        final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("Basic Mode", true);
        final JRadioButtonMenuItem radioButtonMenuItem2 = new JRadioButtonMenuItem("Comparison Mode", false);
        radioButtonMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                compounderPanel.setMode("Basic");
            }
        });
        radioButtonMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                compounderPanel.setMode("Comparison");
            }
        });
        radioButtonMenuItem.setMnemonic(66);
        radioButtonMenuItem.setAccelerator(KeyStroke.getKeyStroke(66, 2));
        radioButtonMenuItem2.setMnemonic(77);
        radioButtonMenuItem2.setAccelerator(KeyStroke.getKeyStroke(77, 2));
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonMenuItem);
        buttonGroup.add(radioButtonMenuItem2);
        menu.add(radioButtonMenuItem);
        menu.add(radioButtonMenuItem2);
        return menu;
    }
    
    static {
        vUsageDialog = CompounderDialogFactory.getUsageDialog();
    }
}
