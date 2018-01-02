// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.table.TableColumn;
import java.util.Vector;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

public class PopupMenu extends JPopupMenu implements ActionListener
{
    private JTable m_table;
    private Vector m_columns;
    private Vector m_buttons;
    
    public PopupMenu(final JTable table) {
        this.m_table = null;
        this.m_columns = null;
        this.m_buttons = null;
        this.m_table = table;
        this.m_columns = new Vector();
        this.m_buttons = new Vector();
        for (int i = 0; i < table.getColumnModel().getColumnCount(); ++i) {
            this.m_columns.addElement(table.getColumnModel().getColumn(i));
        }
        for (int i = 0; i < this.m_columns.size(); ++i) {
            final JCheckBoxMenuItem button = new JCheckBoxMenuItem(this.m_columns.elementAt(i).getHeaderValue().toString(), true);
            this.m_buttons.addElement(button);
            this.add(button);
            button.addActionListener(this);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        for (int i = 0; i < this.m_buttons.size(); ++i) {
            final JCheckBoxMenuItem button = this.m_buttons.elementAt(i);
            if (e.getSource().equals(button)) {
                if (button.isSelected()) {
                    this.m_table.addColumn(this.m_columns.elementAt(i));
                }
                else {
                    this.m_table.removeColumn(this.m_columns.elementAt(i));
                }
            }
        }
    }
}
