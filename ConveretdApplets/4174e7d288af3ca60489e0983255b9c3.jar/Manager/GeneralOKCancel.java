// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;

public abstract class GeneralOKCancel extends Frame
{
    Panel panel2;
    Button btnOK;
    Button btnClose;
    
    public GeneralOKCancel() {
        this.panel2 = new Panel();
        this.btnOK = new Button();
        this.btnClose = new Button();
        try {
            this.jbInit();
            this.loadData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void jbInit() throws Exception {
        this.setSize(new Dimension(361, 305));
        this.btnOK.setLabel("OK");
        this.btnOK.addActionListener(new 1());
        this.btnClose.setLabel("Close");
        this.btnClose.addActionListener(new 2());
        this.add(this.panel2, "South");
        this.panel2.add(this.btnOK, null);
        this.panel2.add(this.btnClose, null);
        this.addWindowListener(new 3());
    }
    
    protected abstract void loadData();
    
    protected abstract void saveChanges();
    
    void btnClose_actionPerformed(final ActionEvent e) {
        this.formClose();
    }
    
    void btnOK_actionPerformed(final ActionEvent e) {
        this.saveChanges();
        this.formClose();
    }
    
    private void formClose() {
        this.dispose();
    }
    
    class 1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GeneralOKCancel.this.btnOK_actionPerformed(e);
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            GeneralOKCancel.this.btnClose_actionPerformed(e);
        }
    }
    
    class 3 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent e) {
            e.getWindow().dispose();
        }
    }
}
