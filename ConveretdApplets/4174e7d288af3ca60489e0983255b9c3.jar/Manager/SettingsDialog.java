// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.LayoutManager;
import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.TextField;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Dialog;

public class SettingsDialog extends Dialog
{
    Panel panel1;
    Panel panel2;
    Panel panel3;
    Panel panel4;
    Label label1;
    Label label2;
    Choice choiceBlackPlayerType;
    Choice choiceWhitePlayerType;
    GridLayout gridLayout1;
    Panel panel5;
    Button btnCancel;
    Button btnOK;
    Label label3;
    TextField edtBoardSize;
    protected ActionListener listeners;
    private ActionListener thisListener;
    
    public SettingsDialog(final Frame frame, final String title, final boolean modal) {
        super(frame, title, modal);
        this.panel1 = new Panel();
        this.panel2 = new Panel();
        this.panel3 = new Panel();
        this.panel4 = new Panel();
        this.label1 = new Label();
        this.label2 = new Label();
        this.choiceBlackPlayerType = new Choice();
        this.choiceWhitePlayerType = new Choice();
        this.gridLayout1 = new GridLayout();
        this.panel5 = new Panel();
        this.btnCancel = new Button();
        this.btnOK = new Button();
        this.label3 = new Label();
        this.edtBoardSize = new TextField();
        this.listeners = null;
        this.thisListener = new 1();
        try {
            this.jbInit();
            this.add(this.panel1);
            this.pack();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public SettingsDialog(final Frame frame) {
        this(frame, "", false);
    }
    
    public SettingsDialog(final Frame frame, final boolean modal) {
        this(frame, "", modal);
    }
    
    public SettingsDialog(final Frame frame, final String title) {
        this(frame, title, false);
    }
    
    public String getBoardSize() {
        return this.edtBoardSize.getText();
    }
    
    public String getBlackPlayerType() {
        return this.choiceBlackPlayerType.getSelectedItem();
    }
    
    public String getWhitePlayerType() {
        return this.choiceWhitePlayerType.getSelectedItem();
    }
    
    public void addActionListener(final ActionListener l) {
        this.listeners = AWTEventMulticaster.add(this.listeners, l);
    }
    
    public void removeActionListener(final ActionListener l) {
        this.listeners = AWTEventMulticaster.remove(this.listeners, l);
    }
    
    void jbInit() throws Exception {
        this.panel1.setLayout(this.gridLayout1);
        this.label1.setText("BLACK");
        this.label2.setText("WHITE");
        this.choiceWhitePlayerType.addItemListener(new 2());
        this.gridLayout1.setRows(4);
        this.gridLayout1.setColumns(1);
        this.btnCancel.setLabel("Cancel");
        this.btnCancel.addActionListener(new 3());
        this.label3.setText("Board size");
        this.btnOK.setLabel("  OK  ");
        this.btnOK.setActionCommand("ok");
        this.btnOK.addActionListener(this.thisListener);
        this.btnOK.addActionListener(new 4());
        this.choiceBlackPlayerType.addItem("Human player");
        this.choiceBlackPlayerType.addItem("Greedy Computer player");
        this.choiceBlackPlayerType.addItem("MinMax Computer player");
        this.choiceBlackPlayerType.addItem("Alpha-Beta Computer player");
        this.choiceBlackPlayerType.select(-1);
        this.choiceWhitePlayerType.addItem("Human player");
        this.choiceWhitePlayerType.addItem("Greedy Computer player");
        this.choiceWhitePlayerType.addItem("MinMax Computer player");
        this.choiceWhitePlayerType.addItem("Alpha-Beta Computer player");
        this.choiceWhitePlayerType.select(-1);
        this.panel1.add(this.panel4, null);
        this.panel4.add(this.label3, null);
        this.panel4.add(this.edtBoardSize, null);
        this.panel1.add(this.panel2, null);
        this.panel1.add(this.panel3, null);
        this.panel1.add(this.panel5, null);
        this.panel2.add(this.label1, null);
        this.panel2.add(this.choiceBlackPlayerType, null);
        this.panel3.add(this.label2, null);
        this.panel3.add(this.choiceWhitePlayerType, null);
        this.panel5.add(this.btnOK, null);
        this.panel5.add(this.btnCancel, null);
    }
    
    void cancel() {
        this.setVisible(false);
    }
    
    void switchColors(final ActionEvent e) {
    }
    
    void btnOK_actionPerformed(final ActionEvent e) {
        this.cancel();
    }
    
    void btnCancel_actionPerformed(final ActionEvent e) {
        this.cancel();
    }
    
    void choiceWhitePlayerType_itemStateChanged(final ItemEvent e) {
        this.setTitle(this.choiceWhitePlayerType.getSelectedItem());
    }
    
    public static void main(final String[] args) {
        final Frame f = new Frame("InfoDialog Test");
        f.setSize(100, 100);
        f.show();
        final SettingsDialog d = new SettingsDialog(f, "Test", true);
        d.addActionListener(new 5());
        d.show();
    }
    
    class 1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            SettingsDialog.this.setVisible(false);
            if (SettingsDialog.this.listeners != null) {
                System.out.println(String.valueOf("SettingsDialog: listeners notified - ").concat(String.valueOf(e.getActionCommand())));
                SettingsDialog.this.listeners.actionPerformed(new ActionEvent(SettingsDialog.this, e.getID(), e.getActionCommand()));
            }
        }
    }
    
    class 2 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent e) {
            SettingsDialog.this.choiceWhitePlayerType_itemStateChanged(e);
        }
    }
    
    class 3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            SettingsDialog.this.btnCancel_actionPerformed(e);
        }
    }
    
    class 4 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            SettingsDialog.this.btnOK_actionPerformed(e);
        }
    }
    
    static class 5 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            System.out.println(String.valueOf("The user pressed: ").concat(String.valueOf(e.getActionCommand())));
        }
    }
}
