// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.glines;

import java.awt.FlowLayout;
import irc.managers.Resources;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import irc.EIRC;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GlineList extends JFrame implements ActionListener
{
    private JScrollPane scroll;
    private JList list;
    private Vector glines;
    private JPanel footer;
    private JButton degline;
    private JButton deglineall;
    private EIRC eirc;
    private boolean canload;
    
    public GlineList(final EIRC eirc) {
        this.jbInit();
        this.eirc = eirc;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getSource().equals(this.degline)) {}
        if (actionEvent.getSource().equals(this.deglineall)) {}
    }
    
    public void addglines(final String s) {
        this.glines.add(s);
    }
    
    public boolean isCanload() {
        return this.canload;
    }
    
    public void jbInit() {
        this.getContentPane().setLayout(new BorderLayout());
        this.scroll = new JScrollPane();
        this.list = new JList();
        this.scroll.getViewport().add(this.list);
        this.setIconImage(Resources.getImages("minlogoa"));
        (this.footer = new JPanel()).setLayout(new FlowLayout(1, 20, 0));
        (this.degline = new JButton("Degline selected")).addActionListener(this);
        (this.deglineall = new JButton("Degline All")).addActionListener(this);
        this.footer.add(this.degline);
        this.footer.add(this.deglineall);
        this.getContentPane().add(this.scroll, "Center");
        this.getContentPane().add(this.footer, "South");
        this.glines = new Vector();
    }
    
    public void ListData() {
        this.list.setListData(this.glines);
    }
    
    public void setCanload(final boolean canload) {
        this.canload = canload;
    }
}
