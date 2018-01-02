// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.awt.EventQueue;
import java.awt.Component;
import java.awt.LayoutManager;
import org.jdesktop.layout.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class About extends JFrame
{
    private static About _instance;
    private JButton btnOk;
    private JLabel lblText;
    
    public static About instance() {
        if (About._instance == null) {
            About._instance = new About();
        }
        return About._instance;
    }
    
    public About() {
        this.initComponents();
        MattGuiNB.center(this, 300, 250);
    }
    
    private void initComponents() {
        this.btnOk = new JButton();
        this.lblText = new JLabel();
        this.setDefaultCloseOperation(3);
        this.setBounds(new Rectangle(0, 0, 300, 200));
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent evt) {
                About.this.load(evt);
            }
        });
        this.btnOk.setText("Ok");
        this.btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                About.this.btnOkActionPerformed(evt);
            }
        });
        this.lblText.setHorizontalAlignment(0);
        this.lblText.setText("MATT2");
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(121, 121, 121).add((Component)this.btnOk).addContainerGap(154, 32767)).add((Component)this.lblText, -1, 320, 32767));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add(2, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.lblText, -1, 203, 32767).addPreferredGap(0).add((Component)this.btnOk).addContainerGap()));
        this.pack();
    }
    
    private void load(final WindowEvent evt) {
        final StringBuffer text = new StringBuffer();
        text.append("<html><center><h3>MATT2 - Machine Annotation of Traditional Tunes</h3><br\\>");
        text.append("By Bryan Duggan<br\\>");
        text.append("School of Computing, DIT<br\\>");
        text.append("<a href=\"mailto:bryan.duggan@dit.ie\">bryan.duggan@dit.ie</a><br\\>");
        text.append("Using ABC files transcribed by Henrik Norbeck<br/><a href=\"http://www.norbeck.nu/abc/\">http://www.norbeck.nu/abc/</a><br\\>");
        text.append("Uses ABC4J by Lionel Gueganton<br\\>");
        text.append("<a href=\"http://code.google.com/p/abc4j\">http://code.google.com/p/abc4j</a><br/>");
        text.append("</center></html>");
        this.lblText.setText("" + (Object)text);
    }
    
    private void btnOkActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About().setVisible(true);
            }
        });
    }
    
    static {
        About._instance = null;
    }
}
