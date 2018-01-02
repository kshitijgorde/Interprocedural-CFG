// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Comparator;
import irc.com.utils.MyVector;
import java.util.StringTokenizer;
import irc.managers.Resources;
import irc.com.nick.NickInfos;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.util.Hashtable;
import java.awt.Container;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class ASV extends JDialog implements ActionListener
{
    String asv;
    JComboBox comboDepartement;
    JComboBox comboregion;
    JComboBox comboage;
    Container container;
    private Hashtable dept_code;
    private Hashtable dept_hash;
    EIRC eirc;
    ButtonGroup groupsexe;
    JRadioButton homme;
    JRadioButton femme;
    GridBagLayout layout;
    JLabel sexe;
    JLabel region;
    JLabel Departement;
    JLabel age;
    JLabel message;
    JLabel pseudo;
    JTextField pseudo_text;
    JButton valider;
    private String RFCpseudo;
    
    public ASV(final JFrame frame, final EIRC eirc) {
        super(frame, true);
        this.asv = "";
        this.RFCpseudo = "-_0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.eirc = eirc;
        this.setTitle("Chat-land");
        this.layout = new GridBagLayout();
        this.getContentPane().setLayout(this.layout);
        this.container = this.getContentPane();
        this.region = new JLabel("R\u00e9gion : ");
        this.comboregion = new JComboBox();
        this.Departement = new JLabel("D\u00e9partement : ");
        this.comboDepartement = new JComboBox();
        this.sexe = new JLabel("Sexe :");
        this.homme = new JRadioButton("Gar\u00e7on ");
        this.femme = new JRadioButton("Fille  ");
        this.comboage = new JComboBox();
        this.age = new JLabel(" Age : ");
        this.message = new JLabel("<html><body><center>Veuillez r\u00e9introduire vos coordonn\u00e9s</center></body></html>");
        (this.groupsexe = new ButtonGroup()).add(this.femme);
        this.groupsexe.add(this.homme);
        this.valider = new JButton("  Valider  ");
        this.pseudo = new JLabel("Pseudo : ");
        this.pseudo_text = new JTextField();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(0, 40, 40, 0);
        this.container.add(this.message, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.pseudo, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.pseudo_text, gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.region, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.comboregion, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.Departement, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.comboDepartement, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.sexe, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        this.container.add(this.homme, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 1;
        this.container.add(this.femme, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.age, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        this.container.add(this.comboage, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(30, 0, 0, 0);
        this.container.add(this.valider, gridBagConstraints);
        this.femme.setSelected(true);
        this.setDefaultCloseOperation(0);
        this.init();
        this.setSize(250, 450);
        this.comboregion.setSelectedItem(eirc.getAccueil().comboregion.getSelectedItem());
        if (this.comboregion.getSelectedIndex() != 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.comboregion.getSelectedItem()) + ".list"), "/");
            final MyVector myVector = new MyVector();
            if (this.dept_code != null) {
                this.dept_code.clear();
            }
            this.dept_code = new Hashtable();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final String location = Resources.getLocation("dep." + nextToken);
                myVector.addElement(location);
                this.dept_code.put(location, nextToken);
            }
            this.comboDepartement.removeAllItems();
            if (myVector != null) {
                myVector.sort(new SortString());
                final Enumeration elements = myVector.elements();
                while (elements.hasMoreElements()) {
                    this.comboDepartement.addItem(elements.nextElement());
                }
            }
            this.comboDepartement.setSelectedItem(eirc.getAccueil().comboDepartement.getSelectedItem());
        }
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.comboregion)) {
            if (this.comboregion.getSelectedIndex() != 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.comboregion.getSelectedItem()) + ".list"), "/");
                final MyVector myVector = new MyVector();
                if (this.dept_code != null) {
                    this.dept_code.clear();
                }
                this.dept_code = new Hashtable();
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final String location = Resources.getLocation("dep." + nextToken);
                    myVector.addElement(location);
                    this.dept_code.put(location, nextToken);
                }
                this.comboDepartement.removeAllItems();
                if (myVector != null) {
                    myVector.sort(new SortString());
                    final Enumeration elements = myVector.elements();
                    while (elements.hasMoreElements()) {
                        this.comboDepartement.addItem(elements.nextElement());
                    }
                }
                return;
            }
            this.comboDepartement.removeAllItems();
            this.comboDepartement.addItem("Choisir d\u00e9partement");
        }
        if (actionEvent.getSource().equals(this.valider)) {
            if (this.comboregion.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Veuillez s\u00e9lectionnez votre r\u00e9gion");
                return;
            }
            if (this.pseudo_text.getText().trim().length() < 2) {
                JOptionPane.showMessageDialog(this, "Vous devez indiquer votre pseudo pour vous connecter");
                return;
            }
            this.asv = "";
            if (this.comboage.getSelectedIndex() == 0) {
                this.asv += "18";
            }
            else {
                this.asv += ((String)this.comboage.getSelectedItem()).substring(0, 2);
                Integer.parseInt(((String)this.comboage.getSelectedItem()).substring(0, 2));
            }
            if (this.homme.isSelected()) {
                this.asv += "H";
            }
            else {
                this.asv += "F";
            }
            this.asv += this.dept_hash.get(this.comboDepartement.getSelectedItem());
            this.asv += "1";
            final EIRC eirc = this.eirc;
            EIRC.chat_adult = 1;
            this.asv += "00i";
            final String verfpseudo = this.verfpseudo(this.pseudo_text.getText());
            this.eirc.setRealnick(verfpseudo);
            NickInfos.addUserInfos(verfpseudo, this.asv, "");
            this.eirc.sendMessage("nick", new String[] { verfpseudo });
            this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", this.asv });
            this.eirc.setUsername(this.asv);
            this.eirc.getAccueil().setregion((String)this.comboregion.getSelectedItem(), this.dept_hash.get(this.comboDepartement.getSelectedItem()));
            this.eirc.saveinfo();
            this.eirc.getAccueil().getTextName().setText(verfpseudo);
            this.dispose();
        }
    }
    
    public String getAsv() {
        return this.asv;
    }
    
    public void init() {
        this.dept_hash = new Hashtable();
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep.list"), "/");
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(Resources.getLocation("dep." + stringTokenizer.nextToken() + ".list"), "/");
            while (stringTokenizer2.hasMoreTokens()) {
                final String nextToken = stringTokenizer2.nextToken();
                this.dept_hash.put(Resources.getLocation("dep." + nextToken), nextToken);
            }
        }
        for (int i = 18; i < 100; ++i) {
            this.comboage.addItem(Integer.toString(i) + " ans");
        }
        this.dept_code = new Hashtable();
        final MyVector myVector = new MyVector();
        final Enumeration<Object> keys = NickInfos.regions.keys();
        while (keys.hasMoreElements()) {
            myVector.addElement(keys.nextElement());
        }
        myVector.sort(new SortString());
        this.comboregion.addItem("Choisir r\u00e9gion");
        final Enumeration elements = myVector.elements();
        while (elements.hasMoreElements()) {
            this.comboregion.addItem(elements.nextElement());
        }
        this.comboage.setSelectedIndex(6);
        this.comboregion.addActionListener(this);
        this.valider.addActionListener(this);
        this.comboage.addActionListener(this);
        this.homme.addActionListener(this);
        this.femme.addActionListener(this);
    }
    
    private String verfpseudo(final String s) {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[charArray.length];
        int i;
        for (int n = i = 0; i < charArray.length; ++i) {
            if (this.RFCpseudo.indexOf(charArray[i]) != -1) {
                array[n++] = charArray[i];
            }
        }
        return String.valueOf(array);
    }
}
