// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.JDialog;

public class SearchDialog extends JDialog implements ListSelectionListener, KeyListener
{
    Object[] dataBase;
    JPanel inputPanel;
    JTextField textField;
    JList list;
    SearchListModel listModel;
    boolean searching;
    
    public SearchDialog(final Object[] dataBase) {
        this.searching = false;
        this.dataBase = dataBase;
        this.setModal(true);
        this.addKeyListener(this);
        (this.inputPanel = new JPanel()).setLayout(new BorderLayout());
        this.inputPanel.add(new JLabel("Text : "), "West");
        (this.textField = new JTextField(30)).addKeyListener(this);
        this.textField.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SearchDialog.this.setVisible(false);
            }
        });
        this.inputPanel.add(this.textField, "Center");
        (this.list = new JList((E[])dataBase)).setSelectionMode(0);
        this.list.addListSelectionListener(this);
        final JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SearchDialog.this.setVisible(false);
            }
        });
        final JButton button2 = new JButton("Cancel");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SearchDialog.this.list.clearSelection();
                SearchDialog.this.setVisible(false);
            }
        });
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);
        panel.add(button2);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.inputPanel, "North");
        this.getContentPane().add(new JScrollPane(this.list), "Center");
        this.getContentPane().add(panel, "South");
        this.pack();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.textField && !keyEvent.isActionKey()) {
            this.searching = true;
            this.searchText(this.textField.getText());
            this.searching = false;
        }
        if (keyEvent.getKeyCode() == 27) {
            this.list.clearSelection();
            this.setVisible(false);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (!this.searching && this.list.getSelectedIndex() > 0) {
            this.textField.setText((String)this.dataBase[this.list.getSelectedIndex()]);
        }
    }
    
    public Object getSelectedData() {
        final int selectedIndex = this.list.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex > this.dataBase.length) {
            return null;
        }
        return this.dataBase[this.list.getSelectedIndex()];
    }
    
    void searchText(final String s) {
        int selectedIndex = this.list.getSelectedIndex();
        if (selectedIndex < 0) {
            selectedIndex = 0;
        }
        final boolean b = s.compareTo((String)this.dataBase[selectedIndex]) > 0;
        while (selectedIndex >= 0 && selectedIndex < this.dataBase.length) {
            final int compareTo = s.compareTo((String)this.dataBase[selectedIndex]);
            if (compareTo == 0) {
                break;
            }
            if (b && compareTo > 0) {
                ++selectedIndex;
            }
            if (b && compareTo < 0) {
                break;
            }
            if (!b && compareTo > 0) {
                ++selectedIndex;
                break;
            }
            if (b || compareTo >= 0) {
                continue;
            }
            --selectedIndex;
        }
        if (selectedIndex < 0) {
            selectedIndex = 0;
        }
        if (selectedIndex == this.dataBase.length) {
            --selectedIndex;
        }
        this.list.setSelectedIndex(selectedIndex);
    }
}
