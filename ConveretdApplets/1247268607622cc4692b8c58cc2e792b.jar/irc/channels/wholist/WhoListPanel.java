// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import java.awt.Cursor;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import javax.swing.border.Border;
import javax.swing.ListCellRenderer;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.Icon;
import irc.managers.avatar;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringTokenizer;
import irc.managers.Resources;
import irc.com.nick.NickInfos;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.util.Timer;
import irc.com.messages.ChannelMessage;
import java.util.Hashtable;
import java.util.Vector;
import irc.com.utils.MyVector;
import java.text.MessageFormat;
import java.text.Collator;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import irc.EIRC;
import javax.swing.JList;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import irc.channels.components.MyOpacity;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class WhoListPanel extends JPanel implements ActionListener, Comparator, MouseListener, KeyListener, MouseMotionListener, ChangeListener
{
    JPanel findpanel;
    private MyOpacity opacity;
    JTextField nick_pattern;
    JComboBox age_from;
    JComboBox age_to;
    public JComboBox region;
    JComboBox departement;
    JCheckBox femmes;
    JCheckBox hommes;
    JComboBox sort;
    JCheckBox only_vip;
    JLabel loading;
    JLabel notfound;
    private ButtonGroup chatgroup;
    JScrollPane jScrollPane1;
    GridBagLayout gridBagLayout1;
    JList list;
    ButtonGroup gpb;
    EIRC eirc;
    JLabel km;
    JSpinner jspinner;
    JSlider jslider;
    private static final ImageIcon find_arrow_right;
    public static final int SORT_BYNICK = 0;
    public static final int SORT_BYAGE = 1;
    public static final int SORT_BYLOCATION = 2;
    public static final int SORT_BYSEX = 3;
    public static final int SORT_BYDDISC = 4;
    static int sort_criteria;
    static Collator collator;
    protected static final String age_string;
    protected static final MessageFormat MALE_FORMAT;
    protected static final MessageFormat FEMALE_FORMAT;
    int temps;
    int _age_to;
    int _age_from;
    private boolean _use_depart;
    private String _departement;
    private String _region;
    private static boolean FILTER_MALE;
    private static boolean FILTER_FEMALE;
    MyVector male;
    MyVector female;
    Vector visiblevect;
    private Hashtable dept_code;
    public WhoListVector nick_list_vect;
    ChannelMessage chanelmessage;
    public boolean findechargement;
    public AutoRefresh autorefresh;
    public Vector Km_vect;
    PopupUserInfos userinfos;
    Timer timer;
    private boolean km_lancer;
    public Vector noubi;
    private JToolBar tools;
    int xnoubi;
    public JRadioButton chatadult;
    public JRadioButton chatnormale;
    public JCheckBox chatadultcheck;
    
    private static int subComp(final String s, final String s2) {
        if (s == null && s2 != null) {
            return 1;
        }
        if (s != null && s2 == null) {
            return -1;
        }
        if (s == null && s2 == null) {
            return 0;
        }
        return WhoListPanel.collator.compare(s, s2);
    }
    
    public WhoListPanel(final EIRC eirc) {
        this.findpanel = new JPanel();
        this.nick_pattern = new JTextField();
        this.loading = new JLabel();
        this.notfound = new JLabel("<html>Aucun r\u00e9sultat ne correspond <br/> \u00e0 votre recherche</html>");
        this.chatgroup = new ButtonGroup();
        this.jScrollPane1 = new JScrollPane();
        this.gridBagLayout1 = new GridBagLayout();
        this.gpb = new ButtonGroup();
        this.km = new JLabel("  Km");
        this.jspinner = new JSpinner();
        this.jslider = new JSlider(0, 400, 180);
        this.temps = 120;
        this._age_to = 18;
        this._age_from = 100;
        this._use_depart = false;
        this._departement = "";
        this._region = "";
        this.male = new MyVector();
        this.female = new MyVector();
        this.visiblevect = new Vector();
        this.findechargement = false;
        this.autorefresh = null;
        this.Km_vect = new Vector();
        this.timer = new Timer();
        this.km_lancer = false;
        this.noubi = new Vector();
        this.xnoubi = 0;
        this.eirc = eirc;
        this.nick_list_vect = new WhoListVector();
        this.age_from = new JComboBox();
        this.age_to = new JComboBox();
        this.region = new JComboBox();
        this.departement = new JComboBox();
        this.sort = new JComboBox();
        this.femmes = new JCheckBox("Femmes");
        this.hommes = new JCheckBox("Hommes");
        this.only_vip = new JCheckBox("Photo");
        this.noubi.addElement("a");
        this.noubi.addElement("b");
        this.noubi.addElement("c");
        this.noubi.addElement("d");
        this.noubi.addElement("e");
        this.noubi.addElement("f");
        this.noubi.addElement("g");
        this.noubi.addElement("h");
        this.noubi.addElement("i");
        this.noubi.addElement("j");
        this.noubi.addElement("k");
        this.noubi.addElement("l");
        this.noubi.addElement("m");
        this.noubi.addElement("o");
        this.noubi.addElement("p");
        this.xnoubi = (int)(System.currentTimeMillis() % 15L);
        try {
            this.jbInit();
            this.initVariables();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.nick_pattern.setBackground(Color.white);
        this.jspinner.setBackground(Color.white);
        this.hommes.setForeground(Color.white);
        this.femmes.setForeground(Color.white);
        this.only_vip.setForeground(Color.white);
        this.km.setForeground(Color.white);
        this.chatnormale = new JRadioButton("Tchat public");
        this.chatadult = new JRadioButton("Tchat adulte");
        this.chatadultcheck = new JCheckBox("Tchat adulte");
        this.chatnormale.setOpaque(false);
        this.chatadult.setOpaque(false);
        this.chatadultcheck.setOpaque(false);
        this.chatadult.setForeground(Color.white);
        this.chatnormale.setForeground(Color.white);
        this.chatadultcheck.setForeground(Color.white);
        this.chatadult.addActionListener(this);
        this.chatadultcheck.addActionListener(this);
        this.chatnormale.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getSource().equals(this.chatadultcheck)) {
            this.eirc.getRightpanel().chanListPanel.chatadultcheck.setSelected(this.chatadultcheck.isSelected());
            this.eirc.getRightpanel().chanListPanel.chatadultcheckaction();
        }
        if (actionEvent.getSource().equals(this.chatadult)) {
            this.eirc.getRightpanel().chanListPanel.chatadult.setSelected(true);
            this.eirc.getRightpanel().chanListPanel.chatadultaction();
        }
        if (actionEvent.getSource().equals(this.chatnormale)) {
            this.eirc.getRightpanel().chanListPanel.chatnormale.setSelected(true);
            this.eirc.getRightpanel().chanListPanel.chatnormaleaction();
        }
        if (actionEvent.getSource().equals(this.age_from)) {
            this.age_to.removeActionListener(this);
            final String string = this.age_to.getSelectedItem().toString();
            this.age_to.removeAllItems();
            for (int i = this.age_from.getSelectedIndex() + 18; i < 101; ++i) {
                this.age_to.addItem(i + " ans");
            }
            try {
                this.age_to.setSelectedItem(string);
            }
            catch (Exception ex) {
                this.age_to.setSelectedIndex(this.age_to.getItemCount() - 1);
            }
            this.loadUsers();
            this.age_to.addActionListener(this);
        }
        if (actionEvent.getSource().equals(this.age_to)) {
            this.loadUsers();
        }
        if (actionEvent.getSource().equals(this.sort)) {
            WhoListPanel.sort_criteria = this.sort.getSelectedIndex();
            this.display();
        }
        if (actionEvent.getSource().equals(this.only_vip)) {
            this.display();
        }
        if (actionEvent.getSource().equals(this.hommes)) {
            this.display();
        }
        if (actionEvent.getSource().equals(this.femmes)) {
            this.display();
        }
        if (actionEvent.getSource() == this.region) {
            this.departement.removeActionListener(this);
            final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.region.getSelectedItem()) + ".list"), "/");
            final MyVector myVector = new MyVector();
            if (this.dept_code != null) {
                this.dept_code.clear();
                (this.dept_code = new Hashtable()).put("Tous", "*");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final String location = Resources.getLocation("dep." + nextToken);
                    myVector.addElement(location);
                    this.dept_code.put(location, nextToken);
                }
                this.departement.removeAllItems();
                this.departement.addItem("Tous");
                this.departement.setSelectedIndex(0);
                if (myVector != null) {
                    myVector.sort(new SortString());
                    final Enumeration elements = myVector.elements();
                    while (elements.hasMoreElements()) {
                        this.departement.addItem(elements.nextElement());
                    }
                }
                this.nick_list_vect.removeAllElements();
                this.findechargement = false;
                this.autorefresh.setCanReorder(false);
                this.eirc.nameListRegion(this.region.getSelectedItem().toString().trim());
            }
            this.departement.addActionListener(this);
            return;
        }
        if (actionEvent.getSource().equals(this.departement)) {
            this.loadUsers();
        }
    }
    
    public void add(final String s) {
        this.updatechan(new ChannelMessage(1, s));
    }
    
    public void colorForeground(final Color color) {
        this.setForeground(color);
        this.findpanel.setForeground(color);
        this.loading.setForeground(color);
        this.femmes.setForeground(color);
        this.hommes.setForeground(color);
        this.only_vip.setForeground(color);
        this.km.setForeground(color);
        this.notfound.setForeground(color);
        this.age_from.setForeground(color);
        this.age_to.setForeground(color);
        this.region.setForeground(color);
        this.departement.setForeground(color);
        this.sort.setForeground(color);
        this.chatadult.setForeground(color);
        this.chatnormale.setForeground(color);
        this.chatadultcheck.setForeground(color);
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        if (o instanceof WhoItem) {
            final WhoItem whoItem = (WhoItem)o;
            final WhoItem whoItem2 = (WhoItem)o2;
            final String nom = whoItem.getNom();
            final String nom2 = whoItem2.getNom();
            try {
                switch (WhoListPanel.sort_criteria) {
                    case 0: {
                        return subComp(nom, nom2);
                    }
                    case 1: {
                        return NickInfos.getAge(nom) - NickInfos.getAge(nom2);
                    }
                    case 2: {
                        return subComp(NickInfos.getLocation(nom), NickInfos.getLocation(nom2));
                    }
                    case 3: {
                        return NickInfos.getSex(nom2) - NickInfos.getSex(nom);
                    }
                    case 4: {
                        int n = 1;
                        int n2 = 1;
                        if (NickInfos.isTalk(nom)) {
                            n = 0;
                        }
                        if (NickInfos.isTalk(nom2)) {
                            n2 = 0;
                        }
                        return n - n2;
                    }
                }
            }
            catch (Exception ex) {}
            return 0;
        }
        return Integer.parseInt(((String)o).substring(((String)o).indexOf(" ") + 1, ((String)o).indexOf(";"))) - Integer.parseInt(((String)o2).substring(((String)o2).indexOf(" ") + 1, ((String)o2).indexOf(";")));
    }
    
    public void display() {
        this.visiblevect.removeAllElements();
        if (this.nick_list_vect != null && this.nick_list_vect.size() > 0) {
            if (WhoListPanel.sort_criteria == 3) {
                this.SortMaleFemale();
            }
            else {
                Collections.sort((List<Object>)this.nick_list_vect, this);
            }
            boolean b = true;
            if (NickInfos.getAge(this.eirc.getNick()) <= 17 && NickInfos.getAge(this.eirc.getNick()) != -1) {
                b = false;
            }
            int n = 0;
            WhoListPanel.FILTER_MALE = this.hommes.isSelected();
            WhoListPanel.FILTER_FEMALE = this.femmes.isSelected();
            final Vector<Object> vector = new Vector<Object>();
            for (int i = 0; i < this.Km_vect.size(); ++i) {
                vector.add(this.Km_vect.elementAt(i));
            }
            Collections.sort(vector, this);
            final Enumeration<WhoItem> elements = (Enumeration<WhoItem>)this.nick_list_vect.elements();
            while (elements.hasMoreElements()) {
                final WhoItem whoItem = elements.nextElement();
                boolean b2 = true;
                if (this.eirc.isRegister()) {
                    if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                        if (b) {
                            if (EIRC.chat_adult == 0) {
                                if (NickInfos.isNosex(whoItem.getNom()) == 1) {
                                    b2 = false;
                                }
                            }
                            else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                                b2 = false;
                            }
                        }
                        else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                            b2 = false;
                        }
                    }
                    else if (b) {
                        if (EIRC.chat_adult == 0) {
                            if (NickInfos.isNosex(whoItem.getNom()) == 1) {
                                b2 = false;
                            }
                        }
                        else if (EIRC.chat_adult == 1 && NickInfos.isNosex(whoItem.getNom()) == 0) {
                            b2 = false;
                        }
                    }
                    else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                        b2 = false;
                    }
                }
                else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                    if (b) {
                        if (EIRC.chat_adult == 0) {
                            if (NickInfos.isNosex(whoItem.getNom()) == 1) {
                                b2 = false;
                            }
                        }
                        else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                            b2 = false;
                        }
                    }
                    else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                        b2 = false;
                    }
                }
                else if (b) {
                    if (EIRC.chat_adult == 0) {
                        if (NickInfos.isNosex(whoItem.getNom()) == 1) {
                            b2 = false;
                        }
                    }
                    else if (EIRC.chat_adult == 1 && NickInfos.isNosex(whoItem.getNom()) == 0) {
                        b2 = false;
                    }
                }
                else if (NickInfos.isNosex(whoItem.getNom()) == 0) {
                    b2 = false;
                }
                int age = whoItem.getAge();
                if (age < 0) {
                    age = 0;
                }
                final String region = whoItem.getRegion();
                whoItem.getChan();
                switch (whoItem.getSexe()) {
                    case 1: {
                        if (!WhoListPanel.FILTER_MALE) {
                            b2 = false;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (!WhoListPanel.FILTER_FEMALE) {
                            b2 = false;
                            break;
                        }
                        break;
                    }
                }
                if (!NickInfos.isRegister(whoItem.getNom()) && this.only_vip.isSelected()) {
                    b2 = false;
                }
                if (NickInfos.getHumeur(whoItem.getNom()) != null) {
                    if ((NickInfos.getHumeur(whoItem.getNom()).equals("62") || NickInfos.getHumeur(whoItem.getNom()).equals("60")) && !NickInfos.getLocationCode(whoItem.getNom()).equals(NickInfos.getLocationCode(this.eirc.getNick()))) {
                        b2 = false;
                    }
                    if ((NickInfos.getHumeur(whoItem.getNom()).equals("62") || NickInfos.getHumeur(whoItem.getNom()).equals("61")) && !this.eirc.get_friends_list().contains(whoItem.getNom() + ":1") && !this.eirc.get_friends_list().contains(whoItem.getNom() + ":0")) {
                        b2 = false;
                    }
                }
                if (b2 && this.match_age(age) && this.match_region(region) && this.match_nick(whoItem.getNom())) {
                    final Enumeration<String> elements2 = vector.elements();
                    while (elements2.hasMoreElements()) {
                        final String s = elements2.nextElement();
                        if (whoItem.getNom().equalsIgnoreCase(s.substring(0, s.indexOf(" ")))) {
                            whoItem.setDist(s.substring(s.indexOf(" ") + 1, s.indexOf(";")));
                            whoItem.region = s.substring(s.indexOf(";") + 1);
                            vector.remove(s);
                        }
                    }
                    if (whoItem.getNom().equalsIgnoreCase(this.eirc.getNick())) {
                        continue;
                    }
                    this.visiblevect.addElement(whoItem);
                    ++n;
                }
            }
            if (vector.size() > 0) {
                this.visiblevect.addElement(new WhoItem("", 0, 0, "", "", ""));
            }
            final Enumeration<String> elements3 = vector.elements();
            while (elements3.hasMoreElements()) {
                final String s2 = elements3.nextElement();
                final WhoItem whoItem2 = new WhoItem(s2.substring(0, s2.indexOf(" ")), NickInfos.getSex(s2.substring(0, s2.indexOf(" "))), NickInfos.getAge(s2.substring(0, s2.indexOf(" "))), s2.substring(s2.indexOf(";") + 1), NickInfos.getChan(s2.substring(0, s2.indexOf(" "))), s2.substring(s2.indexOf(" ") + 1, s2.indexOf(";")));
                boolean b3 = true;
                if (this.eirc.isRegister()) {
                    if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                        if (b) {
                            if (EIRC.chat_adult == 0) {
                                if (NickInfos.isNosex(whoItem2.getNom()) == 1) {
                                    b3 = false;
                                }
                            }
                            else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                                b3 = false;
                            }
                        }
                        else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                            b3 = false;
                        }
                    }
                    else if (b) {
                        if (EIRC.chat_adult == 0) {
                            if (NickInfos.isNosex(whoItem2.getNom()) == 1) {
                                b3 = false;
                            }
                        }
                        else if (EIRC.chat_adult == 1 && NickInfos.isNosex(whoItem2.getNom()) == 0) {
                            b3 = false;
                        }
                    }
                    else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                        b3 = false;
                    }
                }
                else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                    if (b) {
                        if (EIRC.chat_adult == 0) {
                            if (NickInfos.isNosex(whoItem2.getNom()) == 1) {
                                b3 = false;
                            }
                        }
                        else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                            b3 = false;
                        }
                    }
                    else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                        b3 = false;
                    }
                }
                else if (b) {
                    if (EIRC.chat_adult == 0) {
                        if (NickInfos.isNosex(whoItem2.getNom()) == 1) {
                            b3 = false;
                        }
                    }
                    else if (EIRC.chat_adult == 1 && NickInfos.isNosex(whoItem2.getNom()) == 0) {
                        b3 = false;
                    }
                }
                else if (NickInfos.isNosex(whoItem2.getNom()) == 0) {
                    b3 = false;
                }
                int age2 = whoItem2.getAge();
                if (age2 < 0) {
                    age2 = 0;
                }
                final String region2 = whoItem2.getRegion();
                switch (whoItem2.getSexe()) {
                    case 1: {
                        if (!WhoListPanel.FILTER_MALE) {
                            b3 = false;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (!WhoListPanel.FILTER_FEMALE) {
                            b3 = false;
                            break;
                        }
                        break;
                    }
                }
                if (!NickInfos.isRegister(whoItem2.getNom()) && this.only_vip.isSelected()) {
                    b3 = false;
                }
                if (region2.equals("nulle part")) {
                    b3 = false;
                }
                if (NickInfos.getHumeur(whoItem2.getNom()) != null) {
                    if ((NickInfos.getHumeur(whoItem2.getNom()).equals("62") || NickInfos.getHumeur(whoItem2.getNom()).equals("60")) && !NickInfos.getLocationCode(whoItem2.getNom()).equals(NickInfos.getLocationCode(this.eirc.getNick()))) {
                        b3 = false;
                    }
                    if ((NickInfos.getHumeur(whoItem2.getNom()).equals("62") || NickInfos.getHumeur(whoItem2.getNom()).equals("61")) && !this.eirc.get_friends_list().contains(whoItem2.getNom() + ":1") && !this.eirc.get_friends_list().contains(whoItem2.getNom() + ":0")) {
                        b3 = false;
                    }
                }
                if (b3 && this.match_age(age2) && this.match_nick(whoItem2.getNom()) && !whoItem2.getNom().equalsIgnoreCase(this.eirc.getNick())) {
                    this.visiblevect.addElement(whoItem2);
                    ++n;
                }
            }
            if (n == 0) {
                this.notfound.setVisible(true);
                this.visiblevect.removeAllElements();
            }
            else {
                this.notfound.setVisible(false);
            }
            this.list.setListData(this.visiblevect);
            this.loading.setVisible(false);
        }
        else {
            this.loading.setVisible(false);
            this.notfound.setVisible(true);
        }
    }
    
    public void enabled(final boolean enabled) {
        this.nick_pattern.setEnabled(enabled);
        this.age_from.setEnabled(enabled);
        this.age_to.setEnabled(enabled);
        this.region.setEnabled(enabled);
        this.departement.setEnabled(enabled);
        this.hommes.setEnabled(enabled);
        this.femmes.setEnabled(enabled);
        this.sort.setEnabled(enabled);
        this.only_vip.setEnabled(enabled);
        this.list.setEnabled(enabled);
        this.jScrollPane1.setEnabled(enabled);
    }
    
    public void enableReorder() {
        if (this.autorefresh == null) {
            (this.autorefresh = new AutoRefresh()).start();
        }
        else {
            this.autorefresh.setCanReorder(true);
        }
        this.findechargement = true;
        this.reorder();
    }
    
    public void handleNick(String lowerCase) {
        if (lowerCase.equals("") || lowerCase == null) {
            return;
        }
        lowerCase = lowerCase.toLowerCase();
        this.eirc.getPrivates().openPrivate(lowerCase, 1);
        this.eirc.getChat_panel().ShowPrivate(lowerCase.toLowerCase());
    }
    
    public void initage() {
        this.age_to.removeActionListener(this);
        this.age_from.removeActionListener(this);
        this.age_to.removeAllItems();
        this.age_from.removeAllItems();
        for (int i = 18; i < 101; ++i) {
            this.age_to.addItem(i + " ans");
        }
        for (int j = 18; j < 101; ++j) {
            this.age_from.addItem(j + " ans");
        }
        this.age_to.setSelectedIndex(this.age_to.getItemCount() - 1);
        this.age_to.addActionListener(this);
        this.age_from.addActionListener(this);
    }
    
    private void initVariables() {
        WhoListPanel.collator = Collator.getInstance();
        for (int i = 18; i < 101; ++i) {
            this.age_to.addItem(Integer.toString(i) + " ans");
        }
        for (int j = 18; j < 91; ++j) {
            this.age_from.addItem(Integer.toString(j) + " ans");
        }
        this.hommes.setSelected(true);
        this.femmes.setSelected(true);
        this.sort.addItem("Trier par pseudo");
        this.sort.addItem("Trier par \u00e2ge");
        this.sort.addItem("Trier par d\u00e9partement");
        this.sort.addItem("Trier par sexe");
        this.sort.addItem("Trier par d\u00e9j\u00e0 parler");
        this.sort.setSelectedIndex(3);
        this.age_from.setSelectedIndex(0);
        this.age_to.setSelectedIndex(this.age_to.getItemCount() - 1);
        this.departement.addItem("Tous");
        (this.dept_code = new Hashtable()).put("Tous", "*");
        final MyVector myVector = new MyVector();
        final Enumeration<Object> keys = NickInfos.regions.keys();
        while (keys.hasMoreElements()) {
            myVector.addElement(keys.nextElement());
        }
        myVector.sort(new SortString());
        final Enumeration elements = myVector.elements();
        while (elements.hasMoreElements()) {
            this.region.addItem(elements.nextElement());
        }
        this.age_from.setToolTipText("S\u00e9lectionnez l'\u00e2ge pour afficher le r\u00e9sultat");
        this.age_to.setToolTipText("S\u00e9lectionnez l'\u00e2ge pour afficher le r\u00e9sultat");
        this.departement.setToolTipText("S\u00e9lectionnez le d\u00e9partement pour afficher le r\u00e9sultat");
        this.region.setToolTipText("S\u00e9lectionnez la r\u00e9gion pour afficher le r\u00e9sultat");
        this.femmes.setToolTipText("Cochez pour afficher le r\u00e9sultat");
        this.hommes.setToolTipText("Cochez pour afficher le r\u00e9sultat");
        this.only_vip.setToolTipText("Cochez pour afficher le r\u00e9sultat");
        this.sort.setToolTipText("S\u00e9lectionnez le crit\u00e8re de tri pour afficher le r\u00e9sultat");
        this.hommes.setOpaque(false);
        this.femmes.setOpaque(false);
        this.only_vip.setOpaque(false);
        this.age_to.addActionListener(this);
        this.age_to.setActionCommand("age_to");
        this.age_from.addActionListener(this);
        this.age_from.setActionCommand("age_from");
        this.region.addActionListener(this);
        this.region.setActionCommand("region");
        this.departement.addActionListener(this);
        this.departement.setActionCommand("departement");
        this.nick_pattern.addMouseListener(this);
        this.nick_pattern.addKeyListener(this);
        this.list.addMouseListener(this);
        this.list.addMouseMotionListener(this);
        this.sort.addActionListener(this);
        this.only_vip.addActionListener(this);
        this.hommes.addActionListener(this);
        this.femmes.addActionListener(this);
    }
    
    private void jbInit() throws Exception {
        this.jspinner.setValue(new Integer(180));
        this.jspinner.setToolTipText("Indiquez le rayon kilom\u00e9trique dans lequel vous souhaitez effectuer votre recherche");
        (this.list = new JList()).setOpaque(false);
        this.findpanel.setLayout(this.gridBagLayout1);
        this.setLayout(new BorderLayout());
        this.nick_pattern.setText("Pseudo");
        this.nick_pattern.setForeground(Color.lightGray);
        this.nick_pattern.setToolTipText("Entrez un pseudo / une partie d'un pseudo ");
        this.findpanel.setOpaque(false);
        this.setOpaque(false);
        this.jScrollPane1.getViewport().add(this.list);
        this.jScrollPane1.getViewport().setOpaque(false);
        this.jScrollPane1.setOpaque(false);
        this.list.setOpaque(false);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(this.sort);
        panel.add(this.age_from);
        panel.add(this.age_to);
        panel.setOpaque(false);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new Insets(1, 20, 1, 20);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        this.findpanel.add(panel, gridBagConstraints);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(this.region);
        panel2.add(this.departement);
        panel2.setOpaque(false);
        gridBagConstraints.insets = new Insets(1, 20, 1, 20);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.findpanel.add(panel2, gridBagConstraints);
        final JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setLayout(new GridLayout(1, 3));
        panel3.add(this.only_vip);
        panel3.add(this.hommes);
        panel3.add(this.femmes);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.findpanel.add(panel3, gridBagConstraints);
        this.loading.setIcon(new ImageIcon(avatar.avatarloading));
        this.loading.setIcon(new ImageIcon(avatar.avatarloading));
        (this.tools = new JToolBar()).setFloatable(false);
        this.tools.setOpaque(false);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        this.findpanel.add(this.tools, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        this.findpanel.add(this.jslider, gridBagConstraints);
        this.jspinner.setPreferredSize(new Dimension(45, 20));
        final JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(this.jslider, "Center");
        final JPanel panel5 = new JPanel(new GridLayout(1, 2));
        panel5.add(this.jspinner);
        panel5.add(this.km);
        panel4.add(panel5, "East");
        panel4.setOpaque(false);
        this.jslider.setOpaque(false);
        panel5.setOpaque(false);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        this.findpanel.add(panel4, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        this.findpanel.add(this.nick_pattern, gridBagConstraints);
        final JScrollPane jScrollPane1 = this.jScrollPane1;
        final JScrollPane jScrollPane2 = this.jScrollPane1;
        jScrollPane1.setHorizontalScrollBarPolicy(31);
        (this.opacity = new MyOpacity()).add(this.jScrollPane1);
        this.opacity.setLayout(new GridLayout(1, 1));
        this.add(this.findpanel, "South");
        this.add(this.opacity, "Center");
        this.list.setLayout(new FlowLayout());
        this.list.add(this.loading);
        this.list.add(this.notfound);
        this.list.setToolTipText("Un tick apparait \u00e0 gauche du pseudo de la personne avec qui vous avez d\u00e9j\u00e0 discut\u00e9");
        this.notfound.setVisible(false);
        this.loading.setVisible(false);
        this.list.setCellRenderer(new WhoItemRenderer());
        this.jslider.setMajorTickSpacing(200);
        this.jslider.setPaintTicks(true);
        this.jslider.setInverted(false);
        this.jslider.setMinorTickSpacing(20);
        this.jslider.setMaximum(400);
        this.jslider.addChangeListener(this);
        this.jspinner.addChangeListener(this);
        this.jslider.addMouseListener(this);
        this.jScrollPane1.setBackground(Color.white);
        this.jScrollPane1.getViewport().setBackground(Color.white);
        this.jScrollPane1.setBorder(null);
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource().equals(this.nick_pattern)) {
            this.loadUsers();
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void lancer_km() {
        if (!this.km_lancer) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000L);
                    }
                    catch (InterruptedException ex) {}
                    WhoListPanel.this.timer.schedule(new Detecte(WhoListPanel.this.eirc), 0L, 300000L);
                }
            }.start();
            this.km_lancer = true;
        }
    }
    
    public void loadUsers() {
        if (this.nick_list_vect.size() > 0) {
            if (this.age_to.getSelectedIndex() != -1) {
                this._age_to = Integer.parseInt(this.age_to.getSelectedItem().toString().substring(0, this.age_to.getSelectedItem().toString().indexOf(" ")));
            }
            else {
                this._age_to = -1;
            }
            if (this.age_from.getSelectedIndex() != -1) {
                this._age_from = Integer.parseInt(this.age_from.getSelectedItem().toString().substring(0, this.age_from.getSelectedItem().toString().indexOf(" ")));
            }
            else {
                this._age_from = -1;
            }
            if (this.region.getSelectedIndex() == -1) {
                this._region = "";
            }
            else {
                this._region = this.region.getSelectedItem().toString();
            }
            if (this.departement.getSelectedIndex() == -1 || this.departement.getSelectedIndex() == 0) {
                this._use_depart = false;
                this._departement = "";
            }
            else {
                this._use_depart = true;
                this._departement = this.departement.getSelectedItem().toString();
                final Enumeration<String> keys = NickInfos.locations.keys();
                while (keys.hasMoreElements()) {
                    final String trim = keys.nextElement().trim();
                    if (((String)NickInfos.locations.get(trim)).toUpperCase().compareTo(this._departement.toUpperCase()) == 0) {
                        this._departement = trim;
                        break;
                    }
                }
            }
        }
        this.display();
    }
    
    public void makeupdate(final ChannelMessage channelMessage) {
        if (channelMessage == null) {
            return;
        }
        switch (channelMessage.getAction()) {
            case 1: {
                this.nick_list_vect.add(channelMessage.getMessagePart1());
                break;
            }
            case 2: {
                this.nick_list_vect.remove(channelMessage.getMessagePart1());
                break;
            }
            case 3: {
                this.nick_list_vect.rename(channelMessage.getMessagePart1(), channelMessage.getMessagePart2());
                break;
            }
        }
    }
    
    private boolean match_age(final int n) {
        return this._age_to == -1 || this._age_from == -1 || (this._age_from <= n && n <= this._age_to);
    }
    
    public boolean match_nick(final String s) {
        return this.nick_pattern.getText().trim().equals("") || this.nick_pattern.getText().trim().equals("Pseudo") || s.toLowerCase().indexOf(this.nick_pattern.getText().trim().toLowerCase()) != -1;
    }
    
    public boolean match_region(final String s) {
        return this.departement.getSelectedIndex() == 0 || this.departement.getSelectedItem().toString().equalsIgnoreCase(s);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.nick_pattern)) {
            if (this.nick_pattern.getText().indexOf("Pseudo") != -1) {
                this.nick_pattern.setText("");
                this.nick_pattern.setForeground(Color.black);
            }
            if (mouseEvent.getButton() == 3 || mouseEvent.getModifiers() == 18) {
                this.eirc.getAccueil().copypaste.setParent(this.nick_pattern);
                this.eirc.getAccueil().copypaste.refershItemsState();
                this.eirc.getAccueil().copypaste.show(this.nick_pattern, mouseEvent.getX(), mouseEvent.getY());
            }
        }
        else if (mouseEvent.getSource().equals(this.list) && mouseEvent.getClickCount() == 2) {
            this.userinfos.setVisible(false);
            if (this.list.getSelectedIndex() != -1) {
                this.handleNick(this.list.getModel().getElementAt(this.list.getSelectedIndex()).nom);
            }
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.list)) {
            final int locationToIndex = this.list.locationToIndex(mouseEvent.getPoint());
            if (locationToIndex != -1) {
                this.list.setSelectedIndex(locationToIndex);
            }
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.jslider) || mouseEvent.getSource().equals(this.jspinner)) {
            this.Km_vect.removeAllElements();
            this.eirc.sendMessage("PRIVMSG", new String[] { this.noubi.elementAt(this.xnoubi), "dst " + (int)this.jspinner.getValue() });
            ++this.xnoubi;
            if (this.xnoubi >= 15) {
                this.xnoubi = 0;
            }
        }
        if (mouseEvent.getSource().equals(this.list)) {
            final int selectedIndex = this.list.getSelectedIndex();
            if (selectedIndex != -1) {
                final WhoItem whoItem = this.list.getModel().getElementAt(selectedIndex);
                if (whoItem != null) {
                    if (whoItem.getNom().equals("")) {
                        return;
                    }
                    (this.userinfos = new PopupUserInfos(this, selectedIndex)).setNick(whoItem.getNom());
                    final PopupUserInfos userinfos = this.userinfos;
                    final EIRC eirc = this.eirc;
                    userinfos.colorForeground(EIRC.mainfg);
                    this.userinfos.show(this.jScrollPane1, this.jScrollPane1.getWidth(), 0);
                }
            }
        }
    }
    
    public void remove(final String s) {
        this.updatechan(new ChannelMessage(2, s));
    }
    
    public void rename(final String s, final String s2) {
        this.updatechan(new ChannelMessage(3, s, s2));
    }
    
    public void reorder() {
        this.loadUsers();
        this.list.repaint();
    }
    
    public void selectMyRegion() {
        this.region.removeActionListener(this);
        this.departement.removeActionListener(this);
        this.region.setSelectedItem(this.eirc.getCurrentregion());
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep." + NickInfos.regions.get(this.region.getSelectedItem()) + ".list"), "/");
        final MyVector myVector = new MyVector();
        if (this.dept_code != null) {
            this.dept_code.clear();
        }
        (this.dept_code = new Hashtable()).put("Tous", "*");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final String location = Resources.getLocation("dep." + nextToken);
            myVector.addElement(location);
            this.dept_code.put(location, nextToken);
        }
        this.departement.removeAllItems();
        this.departement.addItem("Tous");
        this.departement.setSelectedIndex(0);
        if (myVector != null) {
            myVector.sort(new SortString());
            final Enumeration elements = myVector.elements();
            while (elements.hasMoreElements()) {
                this.departement.addItem(elements.nextElement());
            }
        }
        this.region.addActionListener(this);
        this.departement.addActionListener(this);
    }
    
    public void setConnected(final boolean enabled) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setEnabled(enabled);
        }
    }
    
    public void SortMaleFemale() {
        this.male.removeAllElements();
        this.female.removeAllElements();
        final Enumeration<WhoItem> elements = this.nick_list_vect.elements();
        while (elements.hasMoreElements()) {
            final WhoItem whoItem = elements.nextElement();
            if (whoItem.getSexe() == 1) {
                this.male.addElement(whoItem);
            }
            else {
                this.female.addElement(whoItem);
            }
        }
        WhoListPanel.sort_criteria = 0;
        this.male.sort(this);
        this.female.sort(this);
        WhoListPanel.sort_criteria = 3;
        this.nick_list_vect.removeAllElements();
        if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
            final Enumeration elements2 = this.female.elements();
            while (elements2.hasMoreElements()) {
                this.nick_list_vect.addElement(elements2.nextElement());
            }
            final Enumeration elements3 = this.male.elements();
            while (elements3.hasMoreElements()) {
                this.nick_list_vect.addElement(elements3.nextElement());
            }
        }
        else {
            final Enumeration elements4 = this.male.elements();
            while (elements4.hasMoreElements()) {
                this.nick_list_vect.addElement(elements4.nextElement());
            }
            final Enumeration elements5 = this.female.elements();
            while (elements5.hasMoreElements()) {
                this.nick_list_vect.addElement(elements5.nextElement());
            }
        }
    }
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource().equals(this.jslider)) {
            this.jspinner.setValue(new Integer(this.jslider.getValue()));
        }
        if (changeEvent.getSource().equals(this.jspinner)) {
            this.jslider.setValue((int)this.jspinner.getValue());
            if ((int)this.jspinner.getValue() > 400) {
                this.jspinner.setValue("400");
            }
            if ((int)this.jspinner.getValue() < 0) {
                this.jspinner.setValue("0");
            }
        }
    }
    
    public void updatechan(final ChannelMessage chanelmessage) {
        this.chanelmessage = chanelmessage;
        if (!this.findechargement) {
            this.makeupdate(this.chanelmessage);
        }
    }
    
    public void actualise() {
        this.tools.removeAll();
        this.chatadult.setVisible(true);
        this.chatnormale.setVisible(true);
        this.chatadultcheck.setVisible(true);
        boolean b = true;
        if (NickInfos.getAge(this.eirc.getNick()) <= 17 && NickInfos.getAge(this.eirc.getNick()) != -1) {
            b = false;
            this.chatadult.setVisible(false);
            this.chatnormale.setVisible(false);
            this.chatadultcheck.setVisible(false);
        }
        if (b) {
            if (this.eirc.getSex(this.eirc.getNick()) != 1) {
                this.tools.add(this.chatadultcheck);
            }
            else {
                this.tools.add(this.chatadult);
                this.tools.add(this.chatnormale);
                this.chatgroup.add(this.chatadult);
                this.chatgroup.add(this.chatnormale);
            }
        }
        else {
            this.tools.add(this.chatadult);
            this.tools.add(this.chatnormale);
            this.chatgroup.add(this.chatadult);
            this.chatgroup.add(this.chatnormale);
        }
        this.tools.setOpaque(false);
        this.chatadult.setSelected(EIRC.chat_adult == 0);
        this.chatnormale.setSelected(EIRC.chat_adult == 1);
        this.chatadultcheck.setSelected(EIRC.chat_adult == 2);
        if (EIRC.chat_adult == 2) {
            this.chatadult.setVisible(true);
            this.chatadult.setSelected(true);
            this.chatnormale.setVisible(false);
        }
        else if (b) {
            if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                this.chatadult.setVisible(true);
                this.chatnormale.setVisible(true);
            }
            else {
                this.chatadult.setVisible(true);
                this.chatadult.setSelected(false);
                this.chatnormale.setVisible(false);
            }
        }
        else {
            this.chatadult.setVisible(false);
            this.chatnormale.setVisible(false);
        }
        this.tools.repaint();
    }
    
    static {
        find_arrow_right = new ImageIcon(Resources.getImages("find_arrow_rech"));
        WhoListPanel.sort_criteria = 3;
        age_string = Resources.getStringEirc("who_list.age");
        MALE_FORMAT = new MessageFormat(Resources.getStringEirc("msg.male"));
        FEMALE_FORMAT = new MessageFormat(Resources.getStringEirc("msg.female"));
        WhoListPanel.FILTER_MALE = false;
        WhoListPanel.FILTER_FEMALE = false;
    }
    
    class SortString implements Comparator
    {
        @Override
        public int compare(final Object o, final Object o2) {
            return ((String)o).compareTo((String)o2);
        }
    }
    
    public class Detecte extends TimerTask
    {
        EIRC eirc;
        
        public Detecte(final EIRC eirc) {
            this.eirc = eirc;
        }
        
        @Override
        public void run() {
            WhoListPanel.this.Km_vect.removeAllElements();
            this.eirc.sendMessage("PRIVMSG", new String[] { WhoListPanel.this.noubi.elementAt(WhoListPanel.this.xnoubi), "dst " + (int)WhoListPanel.this.jspinner.getValue() });
            final WhoListPanel this$0 = WhoListPanel.this;
            ++this$0.xnoubi;
            if (WhoListPanel.this.xnoubi >= 15) {
                WhoListPanel.this.xnoubi = 0;
            }
        }
    }
    
    public class AutoRefresh extends Thread
    {
        private boolean canreorder;
        
        AutoRefresh() {
            this.canreorder = true;
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60000L);
                }
                catch (InterruptedException ex) {}
                if (this.canreorder) {
                    WhoListPanel.this.nick_list_vect.removeAllElements();
                    WhoListPanel.this.findechargement = false;
                    WhoListPanel.this.eirc.nameListRegion(WhoListPanel.this.eirc.getCurrentregion());
                }
            }
        }
        
        public void setCanReorder(final boolean canreorder) {
            this.canreorder = canreorder;
        }
    }
}
