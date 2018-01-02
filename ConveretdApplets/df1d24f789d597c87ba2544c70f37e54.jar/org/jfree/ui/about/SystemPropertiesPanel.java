// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ListSelectionModel;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ResourceBundle;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JPanel;

public class SystemPropertiesPanel extends JPanel
{
    private JTable table;
    private JPopupMenu copyPopupMenu;
    private JMenuItem copyMenuItem;
    private PopupListener copyPopupListener;
    
    public SystemPropertiesPanel() {
        final ResourceBundle bundle = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
        this.setLayout(new BorderLayout());
        this.table = SystemProperties.createSystemPropertiesTable();
        this.add(new JScrollPane(this.table));
        this.copyPopupMenu = new JPopupMenu();
        final String string = bundle.getString("system-properties-panel.popup-menu.copy");
        (this.copyMenuItem = new JMenuItem(string)).setAccelerator((KeyStroke)bundle.getObject("system-properties-panel.popup-menu.copy.accelerator"));
        this.copyMenuItem.getAccessibleContext().setAccessibleDescription(string);
        this.copyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SystemPropertiesPanel.this.copySystemPropertiesToClipboard();
            }
        });
        this.copyPopupMenu.add(this.copyMenuItem);
        this.copyPopupListener = new PopupListener();
        this.table.addMouseListener(this.copyPopupListener);
    }
    
    public void copySystemPropertiesToClipboard() {
        final StringBuffer sb = new StringBuffer();
        final ListSelectionModel selectionModel = this.table.getSelectionModel();
        final int minSelectionIndex = selectionModel.getMinSelectionIndex();
        final int maxSelectionIndex = selectionModel.getMaxSelectionIndex();
        if (minSelectionIndex != -1 && maxSelectionIndex != -1) {
            for (int i = minSelectionIndex; i <= maxSelectionIndex; ++i) {
                for (int j = 0; j < this.table.getColumnCount(); ++j) {
                    sb.append(this.table.getValueAt(i, j));
                    if (j != 2) {
                        sb.append("\t");
                    }
                }
                sb.append("\n");
            }
        }
        final StringSelection stringSelection = new StringSelection(sb.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
    }
    
    class PopupListener extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            this.maybeShowPopup(mouseEvent);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.maybeShowPopup(mouseEvent);
        }
        
        private void maybeShowPopup(final MouseEvent mouseEvent) {
            if (mouseEvent.isPopupTrigger()) {
                SystemPropertiesPanel.this.copyPopupMenu.show(SystemPropertiesPanel.this.table, mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
}
