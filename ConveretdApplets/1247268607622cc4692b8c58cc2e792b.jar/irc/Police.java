// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.Rectangle;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.util.StringTokenizer;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import java.util.Enumeration;
import irc.com.nick.NickInfos;
import irc.channels.ChannelWindow;
import irc.managers.CHANNELS;
import irc.channels.PrivateWindow;
import java.awt.event.ActionEvent;
import irc.managers.Resources;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Police extends JFrame implements WindowListener, ListSelectionListener, ActionListener, ItemListener
{
    private JLabel Police;
    private JLabel Styles;
    private JLabel Taille;
    private JButton Valider;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JPanel Effets;
    private JPanel Apercu;
    private JLabel Couleur_text;
    private JLabel Couleur_fond;
    private JComboBox Combo_couleur_text;
    private JComboBox Combo_couleur_fond;
    private JLabel Apercu_text;
    private JList List_police;
    private JList List_style;
    private JList List_taille;
    private Font fonte;
    private int couleur1;
    private int couleur2;
    private boolean bold;
    public static int fontsize;
    public static String fonttype;
    public static int Style;
    private EIRC eirc;
    static String[] envfonts;
    
    public Police(final EIRC eirc) {
        super("Police");
        this.couleur1 = 1;
        this.couleur2 = 0;
        this.setAlwaysOnTop(true);
        this.eirc = eirc;
        this.setIconImage(Resources.getImages("minlogoa"));
        this.jbInit();
        this.init();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (this.List_style.getSelectedIndex() == 1) {
            this.setBold(true);
        }
        else {
            this.setBold(false);
        }
        this.setFonte(new Font(this.List_police.getSelectedValue(), this.List_style.getSelectedIndex(), Integer.parseInt(this.List_taille.getSelectedValue())));
        this.setCouleur1(this.Combo_couleur_text.getSelectedIndex());
        this.setCouleur2(this.Combo_couleur_fond.getSelectedIndex());
        EIRC.fontsize = this.getFonte().getSize();
        EIRC.fontfamily = this.getFonte().getFamily();
        final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().couleur(this.getCouleur1(), this.getCouleur2(), this.getFonte());
        }
        synchronized (CHANNELS.channels) {
            final Enumeration<ChannelWindow> elements2 = CHANNELS.channels.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().couleur(this.getCouleur1(), this.getCouleur2(), this.getFonte());
            }
        }
        this.eirc.setBooleangras(this.isBold());
        this.setVisible(false);
        if (NickInfos.isRegister(this.eirc.getNick())) {
            this.eirc.getAppletoption().updateMessage();
        }
        this.eirc.getAppletoption().update();
    }
    
    private void apercu() {
        if (this.List_police.getSelectedValue() != null && this.List_taille.getSelectedValue() != null && this.Combo_couleur_text.getSelectedIndex() != -1 && this.Combo_couleur_fond.getSelectedIndex() != -1) {
            final String text = "AaBbCcEeZz";
            this.Apercu_text.setFont(new Font(this.List_police.getSelectedValue(), this.List_style.getSelectedIndex(), Integer.parseInt(this.List_taille.getSelectedValue())));
            this.Apercu_text.setText(text);
            this.Apercu_text.setForeground(EIRC.fixedColors[this.Combo_couleur_text.getSelectedIndex()]);
            this.Apercu_text.setBackground(EIRC.fixedColors[this.Combo_couleur_fond.getSelectedIndex()]);
        }
    }
    
    public int getCouleur1() {
        return this.couleur1;
    }
    
    public int getCouleur2() {
        return this.couleur2;
    }
    
    public Font getFonte() {
        return this.fonte;
    }
    
    public String getNameFont(final int n) {
        return this.List_police.getModel().getElementAt(n);
    }
    
    public int getSelectpolice() {
        return this.List_police.getSelectedIndex();
    }
    
    public void init() {
        final DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < irc.Police.envfonts.length; ++i) {
            model.addElement(irc.Police.envfonts[i]);
        }
        this.List_police.setModel(model);
        final DefaultListModel<String> model2 = new DefaultListModel<String>();
        model2.addElement("Standard");
        model2.addElement("Gras");
        this.List_style.setModel(model2);
        final DefaultListModel<String> model3 = new DefaultListModel<String>();
        for (int j = 8; j < 24; j += 2) {
            model3.addElement(String.valueOf(j));
        }
        this.List_taille.setModel(model3);
        this.List_police.setSelectedValue(EIRC.fontfamily, true);
        this.List_style.setSelectedIndex(0);
        this.List_taille.setSelectedValue("" + EIRC.fontsize, true);
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getStringEirc("conf.write_col_list"), ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            this.Combo_couleur_text.addItem(nextToken);
            this.Combo_couleur_fond.addItem(nextToken);
        }
        this.Combo_couleur_text.setSelectedItem("Noir");
        this.Combo_couleur_fond.setSelectedItem("Blanc");
        this.List_police.addListSelectionListener(this);
        this.List_style.addListSelectionListener(this);
        this.List_taille.addListSelectionListener(this);
        this.Valider.addActionListener(this);
        this.Combo_couleur_text.addItemListener(this);
        this.Combo_couleur_fond.addItemListener(this);
        this.Apercu_text.setFont(new Font(this.List_police.getSelectedValue(), this.List_style.getSelectedIndex(), Integer.parseInt(this.List_taille.getSelectedValue())));
    }
    
    public boolean isBold() {
        return this.bold;
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.apercu();
    }
    
    private void jbInit() {
        this.Police = new JLabel();
        this.Styles = new JLabel();
        this.Taille = new JLabel();
        this.Valider = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jScrollPane2 = new JScrollPane();
        this.jScrollPane3 = new JScrollPane();
        this.Effets = new JPanel();
        this.Apercu = new JPanel();
        this.Couleur_text = new JLabel();
        this.Couleur_fond = new JLabel();
        this.Combo_couleur_text = new JComboBox();
        this.Combo_couleur_fond = new JComboBox();
        this.Apercu_text = new JLabel();
        this.List_police = new JList();
        this.List_style = new JList();
        this.List_taille = new JList();
        this.Apercu_text.setOpaque(true);
        this.getContentPane().setLayout(null);
        this.Effets.setLayout(null);
        this.Apercu.setLayout(null);
        this.getContentPane().add(this.Police);
        this.Police.setText("Police");
        this.Couleur_text.setText("Couleur de texte : ");
        this.Couleur_fond.setText("Couleur de fond : ");
        this.Apercu_text.setText("AaBbCcEeZz");
        this.Effets.setBorder(new TitledBorder("Effets"));
        this.Apercu.setBorder(new TitledBorder("Aper\u00e7u"));
        this.Apercu.setOpaque(true);
        this.Styles.setText("Style");
        this.Valider.setText(" OK ");
        this.Taille.setText("Taille");
        this.Apercu_text.setBounds(new Rectangle(15, 18, 171, 47));
        this.Police.setBounds(new Rectangle(10, 0, 150, 25));
        this.jScrollPane1.setBounds(new Rectangle(10, 25, 150, 150));
        this.Styles.setBounds(new Rectangle(170, 0, 100, 25));
        this.jScrollPane2.setBounds(new Rectangle(170, 25, 100, 150));
        this.Taille.setBounds(new Rectangle(280, 0, 100, 25));
        this.jScrollPane3.setBounds(new Rectangle(280, 25, 100, 150));
        this.Effets.setBounds(new Rectangle(0, 180, 240, 80));
        this.Apercu.setBounds(new Rectangle(270, 180, 200, 80));
        this.Valider.setBounds(new Rectangle(400, 80, 70, 30));
        this.Couleur_text.setBounds(new Rectangle(10, 20, 100, 17));
        this.Couleur_fond.setBounds(new Rectangle(10, 40, 100, 17));
        this.Combo_couleur_text.setBounds(new Rectangle(110, 20, 100, 17));
        this.Combo_couleur_fond.setBounds(new Rectangle(110, 40, 100, 17));
        this.getContentPane().add(this.Styles);
        this.getContentPane().add(this.Taille);
        this.getContentPane().add(this.Valider);
        this.getContentPane().add(this.jScrollPane1);
        this.getContentPane().add(this.jScrollPane2);
        this.getContentPane().add(this.jScrollPane3);
        this.getContentPane().add(this.Effets);
        this.getContentPane().add(this.Apercu);
        this.Effets.add(this.Couleur_text);
        this.Effets.add(this.Couleur_fond);
        this.Effets.add(this.Combo_couleur_text);
        this.Effets.add(this.Combo_couleur_fond);
        this.Apercu.add(this.Apercu_text);
        this.jScrollPane1.getViewport().add(this.List_police);
        this.jScrollPane2.getViewport().add(this.List_style);
        this.jScrollPane3.getViewport().add(this.List_taille);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(2);
        this.setTitle("Modifier police.");
        this.setResizable(false);
    }
    
    public void select_style(final int selectedIndex) {
        this.List_style.setSelectedIndex(selectedIndex);
    }
    
    public void selectList_police() {
        this.List_police.setSelectedValue(EIRC.fontfamily, true);
    }
    
    public void selectList_taille() {
        this.List_taille.setSelectedValue("" + EIRC.fontsize, true);
    }
    
    public void setBold(final boolean bold) {
        this.bold = bold;
    }
    
    public void setCouleur1(final int couleur1) {
        this.couleur1 = couleur1;
    }
    
    public void setCouleur2(final int couleur2) {
        this.couleur2 = couleur2;
    }
    
    public void setFonte(final Font fonte) {
        this.fonte = fonte;
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        this.apercu();
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.eirc.revenir();
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    static {
        Police.fontsize = 14;
        Police.fonttype = "Times New Roman";
        Police.Style = 0;
        Police.envfonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
}
