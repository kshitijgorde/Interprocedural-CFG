// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class UserNameablePopupMenuFactory
{
    public static JPopupMenu getMenu(final JUserNameableFrame userNameableFrame) {
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(getSetTitleItem(userNameableFrame));
        return popupMenu;
    }
    
    private static JMenuItem getSetTitleItem(final JUserNameableFrame userNameableFrame) {
        final JMenuItem menuItem = new JMenuItem("Change Title...", 67);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                userNameableFrame.showTitleDialog();
            }
        });
        return menuItem;
    }
}
