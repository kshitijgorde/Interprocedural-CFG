// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.event.WindowFocusListener;
import matt.GUI;
import javax.swing.JFrame;

public class CheckList extends JFrame
{
    public wfl wf;
    protected GUI gui;
    private CheckListItem[] cli;
    
    public void setVisible(final boolean visible) {
        if (visible) {}
        if (!visible || !this.isVisible()) {
            super.setVisible(visible);
        }
        if (visible) {
            int state = super.getExtendedState();
            state &= 0xFFFFFFFE;
            super.setExtendedState(state);
            super.setAlwaysOnTop(true);
            super.toFront();
            super.requestFocus();
            super.setAlwaysOnTop(false);
        }
    }
    
    public void toFront() {
        super.setVisible(true);
        int state = super.getExtendedState();
        state &= 0xFFFFFFFE;
        super.setExtendedState(state);
        super.setAlwaysOnTop(true);
        super.toFront();
        super.requestFocus();
        super.setAlwaysOnTop(false);
    }
    
    public void setGui(final GUI mattGui) {
        this.gui = mattGui;
    }
    
    CheckList(final String[] obs) {
        this.addWindowFocusListener(this.wf = new wfl());
        this.cli = new CheckListItem[obs.length];
        for (int i = 0; i < obs.length; ++i) {
            this.cli[i] = new CheckListItem(obs[i].toString());
        }
        final JList list = new JList((E[])this.cli);
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(0);
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e2) {}
        catch (InstantiationException e3) {}
        catch (IllegalAccessException ex) {}
        this.cli[0].setSelected(true);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent event) {
                final JList list = (JList)event.getSource();
                final int index = list.locationToIndex(event.getPoint());
                final CheckListItem item = list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected());
                Label_0161: {
                    Label_0138: {
                        if (item.label == null) {
                            if ("All" != null) {
                                break Label_0138;
                            }
                        }
                        else if (!item.label.equals("All")) {
                            break Label_0138;
                        }
                        if (item.isSelected()) {
                            for (int i = 1; i < CheckList.this.cli.length; ++i) {
                                CheckList.this.cli[i].setSelected(false);
                                list.repaint(list.getCellBounds(i, i));
                            }
                        }
                        break Label_0161;
                    }
                    CheckList.this.cli[0].setSelected(false);
                    list.repaint(list.getCellBounds(0, 0));
                }
                list.repaint(list.getCellBounds(index, index));
            }
        });
        this.add(new JScrollPane(list));
        this.pack();
    }
    
    public String getVals() {
        String selected = "";
        for (int i = 0; i < this.cli.length; ++i) {
            if (this.cli[i].isSelected()) {
                selected += i;
                selected += ",";
            }
        }
        selected = selected.substring(0, selected.length() - 1);
        System.out.println(selected);
        return selected;
    }
    
    public String getWhat() {
        String selected = "";
        int selectedCount = 0;
        for (int i = 0; i < this.cli.length; ++i) {
            if (this.cli[i].isSelected()) {
                selected = this.cli[i].toString();
                ++selectedCount;
            }
        }
        if (this.cli[0].isSelected()) {
            return "All";
        }
        if (selectedCount > 1) {
            return "Many";
        }
        if (selectedCount == 1) {
            return selected;
        }
        return "None";
    }
    
    public class WindowFocusListener
    {
    }
    
    public class wfl implements WindowFocusListener
    {
        public void wfl() {
        }
        
        public void windowGainedFocus(final WindowEvent e) {
        }
        
        public void windowLostFocus(final WindowEvent e) {
            CheckList.this.gui.setBns();
        }
    }
}
