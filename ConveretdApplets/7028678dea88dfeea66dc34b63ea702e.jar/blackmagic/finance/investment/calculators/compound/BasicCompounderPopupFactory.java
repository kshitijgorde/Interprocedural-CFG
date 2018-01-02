// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.event.MouseListener;
import blackmagic.swing.JPopupMenuListener;
import javax.swing.JPopupMenu;

class BasicCompounderPopupFactory
{
    public static JPopupMenu getBasicCompounderPopup(final BasicCompoundForm basicCompoundForm) {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(getResetItem(basicCompoundForm));
        popupMenu.add(getConfigureItem(basicCompoundForm));
        basicCompoundForm.addMouseListener(new JPopupMenuListener(popupMenu));
        return popupMenu;
    }
    
    private static JMenuItem getResetItem(final BasicCompoundForm basicCompoundForm) {
        final JMenuItem menuItem = new JMenuItem("Reset " + basicCompoundForm.getTitle(), 82);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                basicCompoundForm.reset();
            }
        });
        basicCompoundForm.subscribeState(new ResetListener(menuItem));
        return menuItem;
    }
    
    private static JMenuItem getConfigureItem(final BasicCompoundForm basicCompoundForm) {
        final JMenuItem menuItem = new JMenuItem("Configure " + basicCompoundForm.getTitle() + "...", 67);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                basicCompoundForm.configure();
            }
        });
        return menuItem;
    }
}
