// 
// Decompiled by Procyon v0.5.30
// 

package hydrogenic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

public class QMframe extends Frame
{
    Label label1;
    Button closebtn;
    FlowLayout flowLayout1;
    
    public QMframe() {
        this.label1 = new Label();
        this.closebtn = new Button();
        this.flowLayout1 = new FlowLayout();
        this.addWindowListener(new 1());
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(final String[] array) {
        final QMframe qMframe = new QMframe();
    }
    
    private void jbInit() throws Exception {
        this.setLayout(this.flowLayout1);
        this.setSize(new Dimension(220, 92));
        this.setTitle("Error");
        this.label1.setAlignment(1);
        this.label1.setText("Warning:  Your l value is out of range!");
        this.closebtn.setLabel("OK");
        this.closebtn.addActionListener(new 2());
        this.add(this.label1, null);
        this.add(this.closebtn, null);
    }
    
    void closebtn_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    class 1 extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            QMframe.this.dispose();
        }
    }
    
    class 2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            QMframe.this.closebtn_actionPerformed(actionEvent);
        }
    }
}
