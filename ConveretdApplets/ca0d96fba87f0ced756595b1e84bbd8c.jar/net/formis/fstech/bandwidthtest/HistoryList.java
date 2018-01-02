// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.bandwidthtest;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class HistoryList extends JFrame
{
    private JTextArea histList;
    private JScrollPane jScrollPane1;
    
    public HistoryList() {
        this.initComponents();
    }
    
    public void clear() {
        this.histList.setText("");
    }
    
    public void addList(final String line) {
        this.histList.setText(line + "\n");
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.histList = new JTextArea();
        this.getContentPane().setLayout(new GridBagLayout());
        this.histList.setColumns(20);
        this.histList.setRows(5);
        this.jScrollPane1.setViewportView(this.histList);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 2);
        this.getContentPane().add(this.jScrollPane1, gridBagConstraints);
        this.pack();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryList().setVisible(true);
            }
        });
    }
}
