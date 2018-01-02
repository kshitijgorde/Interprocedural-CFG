// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier;

import javax.swing.JOptionPane;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.Repository;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class VerifierAppFrame extends JFrame
{
    JPanel contentPane;
    JSplitPane jSplitPane1;
    JPanel jPanel1;
    JPanel jPanel2;
    JSplitPane jSplitPane2;
    JPanel jPanel3;
    JList classNamesJList;
    GridLayout gridLayout1;
    JPanel messagesPanel;
    GridLayout gridLayout2;
    JMenuBar jMenuBar1;
    JMenu jMenu1;
    JScrollPane jScrollPane1;
    JScrollPane messagesScrollPane;
    JScrollPane jScrollPane3;
    GridLayout gridLayout4;
    JScrollPane jScrollPane4;
    CardLayout cardLayout1;
    private String JUSTICE_VERSION;
    private String current_class;
    GridLayout gridLayout3;
    JTextPane pass1TextPane;
    JTextPane pass2TextPane;
    JTextPane messagesTextPane;
    JMenuItem newFileMenuItem;
    JSplitPane jSplitPane3;
    JSplitPane jSplitPane4;
    JScrollPane jScrollPane2;
    JScrollPane jScrollPane5;
    JScrollPane jScrollPane6;
    JScrollPane jScrollPane7;
    JList pass3aJList;
    JList pass3bJList;
    JTextPane pass3aTextPane;
    JTextPane pass3bTextPane;
    JMenu jMenu2;
    JMenuItem whatisMenuItem;
    JMenuItem aboutMenuItem;
    
    public VerifierAppFrame() {
        this.jSplitPane1 = new JSplitPane();
        this.jPanel1 = new JPanel();
        this.jPanel2 = new JPanel();
        this.jSplitPane2 = new JSplitPane();
        this.jPanel3 = new JPanel();
        this.classNamesJList = new JList();
        this.gridLayout1 = new GridLayout();
        this.messagesPanel = new JPanel();
        this.gridLayout2 = new GridLayout();
        this.jMenuBar1 = new JMenuBar();
        this.jMenu1 = new JMenu();
        this.jScrollPane1 = new JScrollPane();
        this.messagesScrollPane = new JScrollPane();
        this.jScrollPane3 = new JScrollPane();
        this.gridLayout4 = new GridLayout();
        this.jScrollPane4 = new JScrollPane();
        this.cardLayout1 = new CardLayout();
        this.JUSTICE_VERSION = "JustIce by Enver Haase";
        this.gridLayout3 = new GridLayout();
        this.pass1TextPane = new JTextPane();
        this.pass2TextPane = new JTextPane();
        this.messagesTextPane = new JTextPane();
        this.newFileMenuItem = new JMenuItem();
        this.jSplitPane3 = new JSplitPane();
        this.jSplitPane4 = new JSplitPane();
        this.jScrollPane2 = new JScrollPane();
        this.jScrollPane5 = new JScrollPane();
        this.jScrollPane6 = new JScrollPane();
        this.jScrollPane7 = new JScrollPane();
        this.pass3aJList = new JList();
        this.pass3bJList = new JList();
        this.pass3aTextPane = new JTextPane();
        this.pass3bTextPane = new JTextPane();
        this.jMenu2 = new JMenu();
        this.whatisMenuItem = new JMenuItem();
        this.aboutMenuItem = new JMenuItem();
        this.enableEvents(64L);
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        (this.contentPane = (JPanel)this.getContentPane()).setLayout(this.cardLayout1);
        this.setJMenuBar(this.jMenuBar1);
        this.setSize(new Dimension(708, 451));
        this.setTitle("JustIce");
        this.jPanel1.setMinimumSize(new Dimension(100, 100));
        this.jPanel1.setPreferredSize(new Dimension(100, 100));
        this.jPanel1.setLayout(this.gridLayout1);
        this.jSplitPane2.setOrientation(0);
        this.jPanel2.setLayout(this.gridLayout2);
        this.jPanel3.setMinimumSize(new Dimension(200, 100));
        this.jPanel3.setPreferredSize(new Dimension(400, 400));
        this.jPanel3.setLayout(this.gridLayout4);
        this.messagesPanel.setMinimumSize(new Dimension(100, 100));
        this.messagesPanel.setLayout(this.gridLayout3);
        this.jPanel2.setMinimumSize(new Dimension(200, 100));
        this.jMenu1.setText("File");
        this.jScrollPane1.getViewport().setBackground(Color.red);
        this.messagesScrollPane.getViewport().setBackground(Color.red);
        this.messagesScrollPane.setPreferredSize(new Dimension(10, 10));
        this.classNamesJList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                VerifierAppFrame.this.classNamesJList_valueChanged(e);
            }
        });
        this.classNamesJList.setSelectionMode(0);
        this.jScrollPane3.setBorder(BorderFactory.createLineBorder(Color.black));
        this.jScrollPane3.setPreferredSize(new Dimension(100, 100));
        this.gridLayout4.setRows(4);
        this.gridLayout4.setColumns(1);
        this.gridLayout4.setHgap(1);
        this.jScrollPane4.setBorder(BorderFactory.createLineBorder(Color.black));
        this.jScrollPane4.setPreferredSize(new Dimension(100, 100));
        this.pass1TextPane.setBorder(BorderFactory.createRaisedBevelBorder());
        this.pass1TextPane.setToolTipText("");
        this.pass1TextPane.setEditable(false);
        this.pass2TextPane.setBorder(BorderFactory.createRaisedBevelBorder());
        this.pass2TextPane.setEditable(false);
        this.messagesTextPane.setBorder(BorderFactory.createRaisedBevelBorder());
        this.messagesTextPane.setEditable(false);
        this.newFileMenuItem.setText("New...");
        this.newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(78, 2, true));
        this.newFileMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                VerifierAppFrame.this.newFileMenuItem_actionPerformed(e);
            }
        });
        this.pass3aTextPane.setEditable(false);
        this.pass3bTextPane.setEditable(false);
        this.pass3aJList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                VerifierAppFrame.this.pass3aJList_valueChanged(e);
            }
        });
        this.pass3bJList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(final ListSelectionEvent e) {
                VerifierAppFrame.this.pass3bJList_valueChanged(e);
            }
        });
        this.jMenu2.setText("Help");
        this.whatisMenuItem.setText("What is...");
        this.whatisMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                VerifierAppFrame.this.whatisMenuItem_actionPerformed(e);
            }
        });
        this.aboutMenuItem.setText("About");
        this.aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                VerifierAppFrame.this.aboutMenuItem_actionPerformed(e);
            }
        });
        this.jSplitPane2.add(this.messagesPanel, "bottom");
        this.messagesPanel.add(this.messagesScrollPane, null);
        this.messagesScrollPane.getViewport().add(this.messagesTextPane, null);
        this.jSplitPane2.add(this.jPanel3, "top");
        this.jPanel3.add(this.jScrollPane3, null);
        this.jScrollPane3.getViewport().add(this.pass1TextPane, null);
        this.jPanel3.add(this.jScrollPane4, null);
        this.jPanel3.add(this.jSplitPane3, null);
        this.jSplitPane3.add(this.jScrollPane2, "left");
        this.jScrollPane2.getViewport().add(this.pass3aJList, null);
        this.jSplitPane3.add(this.jScrollPane5, "right");
        this.jScrollPane5.getViewport().add(this.pass3aTextPane, null);
        this.jPanel3.add(this.jSplitPane4, null);
        this.jSplitPane4.add(this.jScrollPane6, "left");
        this.jScrollPane6.getViewport().add(this.pass3bJList, null);
        this.jSplitPane4.add(this.jScrollPane7, "right");
        this.jScrollPane7.getViewport().add(this.pass3bTextPane, null);
        this.jScrollPane4.getViewport().add(this.pass2TextPane, null);
        this.jSplitPane1.add(this.jPanel2, "top");
        this.jPanel2.add(this.jScrollPane1, null);
        this.jSplitPane1.add(this.jPanel1, "bottom");
        this.jPanel1.add(this.jSplitPane2, null);
        this.jScrollPane1.getViewport().add(this.classNamesJList, null);
        this.jMenuBar1.add(this.jMenu1);
        this.jMenuBar1.add(this.jMenu2);
        this.contentPane.add(this.jSplitPane1, "jSplitPane1");
        this.jMenu1.add(this.newFileMenuItem);
        this.jMenu2.add(this.whatisMenuItem);
        this.jMenu2.add(this.aboutMenuItem);
        this.jSplitPane2.setDividerLocation(300);
        this.jSplitPane3.setDividerLocation(150);
        this.jSplitPane4.setDividerLocation(150);
    }
    
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == 201) {
            System.exit(0);
        }
    }
    
    synchronized void classNamesJList_valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        this.current_class = this.classNamesJList.getSelectedValue().toString();
        this.verify();
        this.classNamesJList.setSelectedValue(this.current_class, true);
    }
    
    private void verify() {
        this.setTitle("PLEASE WAIT");
        final Verifier v = VerifierFactory.getVerifier(this.current_class);
        v.flush();
        VerificationResult vr = v.doPass1();
        if (vr.getStatus() == 2) {
            this.pass1TextPane.setText(vr.getMessage());
            this.pass1TextPane.setBackground(Color.red);
            this.pass2TextPane.setText("");
            this.pass2TextPane.setBackground(Color.yellow);
            this.pass3aTextPane.setText("");
            this.pass3aJList.setListData(new Object[0]);
            this.pass3aTextPane.setBackground(Color.yellow);
            this.pass3bTextPane.setText("");
            this.pass3bJList.setListData(new Object[0]);
            this.pass3bTextPane.setBackground(Color.yellow);
        }
        else {
            this.pass1TextPane.setBackground(Color.green);
            this.pass1TextPane.setText(vr.getMessage());
            vr = v.doPass2();
            if (vr.getStatus() == 2) {
                this.pass2TextPane.setText(vr.getMessage());
                this.pass2TextPane.setBackground(Color.red);
                this.pass3aTextPane.setText("");
                this.pass3aTextPane.setBackground(Color.yellow);
                this.pass3aJList.setListData(new Object[0]);
                this.pass3bTextPane.setText("");
                this.pass3bTextPane.setBackground(Color.yellow);
                this.pass3bJList.setListData(new Object[0]);
            }
            else {
                this.pass2TextPane.setText(vr.getMessage());
                this.pass2TextPane.setBackground(Color.green);
                final JavaClass jc = Repository.lookupClass(this.current_class);
                final boolean all3aok = true;
                final boolean all3bok = true;
                final String all3amsg = "";
                final String all3bmsg = "";
                final String[] methodnames = new String[jc.getMethods().length];
                for (int i = 0; i < jc.getMethods().length; ++i) {
                    methodnames[i] = jc.getMethods()[i].toString().replace('\n', ' ').replace('\t', ' ');
                }
                this.pass3aJList.setListData(methodnames);
                this.pass3aJList.setSelectionInterval(0, jc.getMethods().length - 1);
                this.pass3bJList.setListData(methodnames);
                this.pass3bJList.setSelectionInterval(0, jc.getMethods().length - 1);
            }
        }
        final String[] msgs = v.getMessages();
        this.messagesTextPane.setBackground((msgs.length == 0) ? Color.green : Color.yellow);
        String allmsgs = "";
        for (int j = 0; j < msgs.length; ++j) {
            msgs[j] = msgs[j].replace('\n', ' ');
            allmsgs = String.valueOf(allmsgs) + msgs[j] + "\n\n";
        }
        this.messagesTextPane.setText(allmsgs);
        this.setTitle(String.valueOf(this.current_class) + " - " + this.JUSTICE_VERSION);
    }
    
    void newFileMenuItem_actionPerformed(final ActionEvent e) {
        final String classname = JOptionPane.showInputDialog("Please enter the fully qualified name of a class or interface to verify:");
        if (classname == null || classname.equals("")) {
            return;
        }
        VerifierFactory.getVerifier(classname);
        this.classNamesJList.setSelectedValue(classname, true);
    }
    
    synchronized void pass3aJList_valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        final Verifier v = VerifierFactory.getVerifier(this.current_class);
        String all3amsg = "";
        boolean all3aok = true;
        boolean rejected = false;
        for (int i = 0; i < this.pass3aJList.getModel().getSize(); ++i) {
            if (this.pass3aJList.isSelectedIndex(i)) {
                final VerificationResult vr = v.doPass3a(i);
                if (vr.getStatus() == 2) {
                    all3aok = false;
                    rejected = true;
                }
                all3amsg = String.valueOf(all3amsg) + "Method '" + Repository.lookupClass(v.getClassName()).getMethods()[i] + "': " + vr.getMessage().replace('\n', ' ') + "\n\n";
            }
        }
        this.pass3aTextPane.setText(all3amsg);
        this.pass3aTextPane.setBackground(all3aok ? Color.green : (rejected ? Color.red : Color.yellow));
    }
    
    synchronized void pass3bJList_valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        final Verifier v = VerifierFactory.getVerifier(this.current_class);
        String all3bmsg = "";
        boolean all3bok = true;
        boolean rejected = false;
        for (int i = 0; i < this.pass3bJList.getModel().getSize(); ++i) {
            if (this.pass3bJList.isSelectedIndex(i)) {
                final VerificationResult vr = v.doPass3b(i);
                if (vr.getStatus() == 2) {
                    all3bok = false;
                    rejected = true;
                }
                all3bmsg = String.valueOf(all3bmsg) + "Method '" + Repository.lookupClass(v.getClassName()).getMethods()[i] + "': " + vr.getMessage().replace('\n', ' ') + "\n\n";
            }
        }
        this.pass3bTextPane.setText(all3bmsg);
        this.pass3bTextPane.setBackground(all3bok ? Color.green : (rejected ? Color.red : Color.yellow));
    }
    
    void aboutMenuItem_actionPerformed(final ActionEvent e) {
        JOptionPane.showMessageDialog(this, "JustIce is a Java class file verifier.\nIt was implemented by Enver Haase in 2001.\nhttp://bcel.sourceforge.net", this.JUSTICE_VERSION, 1);
    }
    
    void whatisMenuItem_actionPerformed(final ActionEvent e) {
        JOptionPane.showMessageDialog(this, "The upper four boxes to the right reflect verification passes according to The Java Virtual Machine Specification.\nThese are (in that order): Pass one, Pass two, Pass three (before data flow analysis), Pass three (data flow analysis).\nThe bottom box to the right shows (warning) messages; warnings do not cause a class to be rejected.", this.JUSTICE_VERSION, 1);
    }
}
