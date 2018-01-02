// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.chanlist;

import irc.channels.ChannelWindow;
import irc.com.utils.MD5Nick;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import irc.managers.CHANNELS;
import javax.swing.ListCellRenderer;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import irc.main;
import java.util.Enumeration;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.AbstractButton;
import irc.com.nick.NickInfos;
import irc.com.utils.IEAutoStart;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.LayoutManager;
import irc.managers.Resources;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import irc.channels.components.MyOpacity;
import javax.swing.JLabel;
import irc.com.utils.MyVector;
import java.text.Collator;
import java.awt.BorderLayout;
import irc.EIRC;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.ButtonGroup;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ChanListPanel extends JPanel implements ActionListener, ItemListener, Comparator, MouseListener, MouseMotionListener
{
    private Vector vect;
    private ButtonGroup groupe;
    private GridBagLayout gridbaglayout;
    protected final String newchan_str;
    private JButton new_chan_ok;
    private JComboBox users_nbr;
    private static final int SORT_BYNAME = 0;
    private static final int SORT_BYNUM = 1;
    private static int sort_criteria;
    private JList listchan;
    private EIRC eirc;
    private BorderLayout borderLayout;
    private JPanel pourlecomp;
    private static Collator collator;
    private MyVector listsalon;
    private JButton trouver;
    private JLabel labchatteur;
    private JComboBox sortby;
    private MyOpacity opacity;
    private JScrollPane jScrollPane1;
    public JRadioButton chatadult;
    public JRadioButton chatnormale;
    public JCheckBox chatadultcheck;
    private JToolBar tools;
    private ButtonGroup chatgroup;
    public boolean chat_adulte;
    private String jchannel;
    private Vector tchatnormal;
    private Vector tchatsajoin;
    private Vector tchatadult;
    private JPopupMenu p_menu;
    private JMenuItem p_saj;
    private JMenuItem p_ouvrir;
    String salonselection;
    
    public ChanListPanel(final EIRC eirc) {
        this.newchan_str = Resources.getStringEirc("channel_list.new");
        this.listsalon = new MyVector();
        this.jScrollPane1 = new JScrollPane(20, 31);
        this.chatgroup = new ButtonGroup();
        this.jchannel = "";
        this.tchatnormal = new Vector();
        this.tchatsajoin = new Vector();
        this.tchatadult = new Vector();
        this.salonselection = "";
        this.eirc = eirc;
        this.gridbaglayout = new GridBagLayout();
        this.setLayout(this.borderLayout = new BorderLayout());
        ChanListPanel.collator = Collator.getInstance();
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.chatadult.setForeground(Color.white);
        this.chatnormale.setForeground(Color.white);
        this.chatadultcheck.setForeground(Color.white);
        this.labchatteur.setForeground(Color.white);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getSource().equals(this.p_saj)) {
            if (this.p_saj.getText().startsWith("Supprimer")) {
                final EIRC eirc = this.eirc;
                EIRC.sajoin.removeElement(this.salonselection.toLowerCase());
                this.savesaj();
                JOptionPane.showMessageDialog(this.eirc.getFrame(), "Le salon " + this.salonselection + " a \u00e9t\u00e9 supprim\u00e9 de la liste des salons ouverts auto avec succ\u00e8s.", "Chat-Land", 1);
                this.afficher();
                return;
            }
            final EIRC eirc2 = this.eirc;
            if (EIRC.sajoin.size() >= 5) {
                JOptionPane.showMessageDialog(this.eirc.getFrame(), "Vous avez atteint 5 salons ouverts auto, veuillez supprimer un pour pouvoir ajouter ce salon.", "Chat-Land", 1);
            }
            else {
                final EIRC eirc3 = this.eirc;
                EIRC.sajoin.addElement(this.salonselection.toLowerCase());
                this.eirc.getRightpanel().chanListPanel.savesaj();
                JOptionPane.showMessageDialog(this.eirc.getFrame(), "L'ajout de " + this.salonselection + " \u00e0 la liste des salons ouverts auto a \u00e9t\u00e9 \u00e9ffectu\u00e9 avec succ\u00e8s.", "Chat-Land", 1);
                this.afficher();
            }
        }
        else if (actionEvent.getSource().equals(this.p_ouvrir)) {
            this.joinChannel(this.salonselection);
        }
        if (actionEvent.getSource().equals(this.trouver)) {
            this.loadChan();
        }
        else if (actionEvent.getSource().equals(this.users_nbr)) {
            this.afficher();
        }
        if (actionEvent.getSource().equals(this.new_chan_ok)) {
            if (this.eirc.isRegister()) {
                String s = JOptionPane.showInputDialog(this.eirc.getFrame(), "Veuillez indiquer le nom du salon:");
                if (s != null && !s.equals("")) {
                    if (!s.startsWith("#")) {
                        s = "#" + s;
                    }
                    if (s.length() > 1) {
                        this.joinChannel(s);
                        this.afficher();
                    }
                }
            }
            else {
                this.eirc.getCurrentPanel().printInfo("Vous devez \u00eatre VIP pour acc\u00e9der \u00e0 cette commande\nInscription sur:http://vip.chat-land.org/");
                final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                if (JOptionPane.showOptionDialog(this.eirc.getFrame(), "Vous devez \u00eatre VIP pour acc\u00e9der \u00e0 cette commande\nInscription gratuite sur:http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    IEAutoStart.run("http://vip.chat-land.org/");
                }
            }
        }
        if (actionEvent.getSource().equals(this.chatadultcheck)) {
            this.chatadultcheckaction();
        }
        if (actionEvent.getSource().equals(this.chatadult)) {
            this.chatadultaction();
        }
        if (actionEvent.getSource().equals(this.chatnormale)) {
            this.chatnormaleaction();
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
        this.chat_adulte = (EIRC.chat_adult == 0);
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
        this.eirc.getRightpanel().whoListPanel.actualise();
    }
    
    public void afficher() {
        if (this.sortby.getSelectedIndex() == 2) {
            this.users_nbr.setEnabled(false);
            this.afficherthem();
            return;
        }
        this.users_nbr.setEnabled(true);
        boolean b = true;
        this.vect = new Vector();
        if (NickInfos.getAge(this.eirc.getNick()) <= 17 && NickInfos.getAge(this.eirc.getNick()) != -1) {
            b = false;
        }
        this.listsalon.sort(this);
        final int int1 = Integer.parseInt(this.users_nbr.getSelectedItem().toString());
        synchronized (this.listsalon) {
            final Enumeration elements = this.listsalon.elements();
            while (elements.hasMoreElements()) {
                final ChannelItem channelItem = elements.nextElement();
                final String tag = channelItem.getTag();
                boolean b2 = true;
                if (this.eirc.isRegister()) {
                    if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                        if (b) {
                            if (EIRC.chat_adult == 0) {
                                b2 = channelItem.isAdulte();
                            }
                            else {
                                b2 = !channelItem.isAdulte();
                            }
                        }
                        else {
                            b2 = !channelItem.isAdulte();
                        }
                    }
                    else if (b) {
                        if (EIRC.chat_adult == 0) {
                            b2 = channelItem.isAdulte();
                        }
                        else if (EIRC.chat_adult == 1) {
                            b2 = !channelItem.isAdulte();
                        }
                    }
                    else {
                        b2 = !channelItem.isAdulte();
                    }
                }
                else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                    if (b) {
                        if (EIRC.chat_adult == 0) {
                            b2 = channelItem.isAdulte();
                        }
                        else {
                            b2 = !channelItem.isAdulte();
                        }
                    }
                    else {
                        b2 = !channelItem.isAdulte();
                    }
                }
                else if (b) {
                    if (EIRC.chat_adult == 0) {
                        b2 = channelItem.isAdulte();
                    }
                    else if (EIRC.chat_adult == 1) {
                        b2 = !channelItem.isAdulte();
                    }
                }
                else {
                    b2 = !channelItem.isAdulte();
                }
                if (tag.equalsIgnoreCase("#aide")) {
                    b2 = true;
                }
                if (channelItem.getUsers() >= int1 && b2) {
                    this.vect.addElement(channelItem);
                }
            }
            this.listchan.setModel(new DefaultListModel());
            this.listchan.setListData(this.vect);
        }
    }
    
    public void afficherthem() {
        this.vect = new Vector();
        this.listsalon.sort(this);
        this.initvectadult();
        for (int i = 0; i < this.tchatsajoin.size(); ++i) {
            if (((String)this.tchatsajoin.elementAt(i)).startsWith("#")) {
                final Enumeration elements = this.listsalon.elements();
                while (elements.hasMoreElements()) {
                    final ChannelItem channelItem = elements.nextElement();
                    if (channelItem.getTag().equalsIgnoreCase(this.tchatsajoin.elementAt(i))) {
                        this.vect.addElement(channelItem);
                    }
                }
            }
            else {
                this.vect.addElement(new ChannelItem((String)this.tchatsajoin.elementAt(i), 100, false, false));
            }
        }
        if (EIRC.chat_adult == 1 || EIRC.chat_adult == 2) {
            for (int j = 0; j < this.tchatnormal.size(); ++j) {
                if (((String)this.tchatnormal.elementAt(j)).startsWith("#")) {
                    final Enumeration elements2 = this.listsalon.elements();
                    while (elements2.hasMoreElements()) {
                        final ChannelItem channelItem2 = elements2.nextElement();
                        if (channelItem2.getTag().equalsIgnoreCase(this.tchatnormal.elementAt(j))) {
                            this.vect.addElement(channelItem2);
                        }
                    }
                }
                else {
                    this.vect.addElement(new ChannelItem((String)this.tchatnormal.elementAt(j), 100, false, false));
                }
            }
        }
        if (EIRC.chat_adult == 0 || EIRC.chat_adult == 2) {
            for (int k = 0; k < this.tchatadult.size(); ++k) {
                if (((String)this.tchatadult.elementAt(k)).startsWith("#")) {
                    final Enumeration elements3 = this.listsalon.elements();
                    while (elements3.hasMoreElements()) {
                        final ChannelItem channelItem3 = elements3.nextElement();
                        if (channelItem3.getTag().equalsIgnoreCase(this.tchatadult.elementAt(k))) {
                            this.vect.addElement(channelItem3);
                        }
                    }
                }
                else {
                    this.vect.addElement(new ChannelItem((String)this.tchatadult.elementAt(k), 100, false, false));
                }
            }
        }
        this.vect.addElement(new ChannelItem("Non class\u00e9", 100, false, false));
        boolean b = true;
        if (NickInfos.getAge(this.eirc.getNick()) <= 17 && NickInfos.getAge(this.eirc.getNick()) != -1) {
            b = false;
        }
        this.listsalon.sort(this);
        final int int1 = Integer.parseInt(this.users_nbr.getSelectedItem().toString());
        synchronized (this.listsalon) {
            final Enumeration elements4 = this.listsalon.elements();
            while (elements4.hasMoreElements()) {
                final ChannelItem channelItem4 = elements4.nextElement();
                final String tag = channelItem4.getTag();
                boolean b2 = true;
                if (this.eirc.isRegister()) {
                    if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                        if (b) {
                            if (EIRC.chat_adult == 0) {
                                b2 = channelItem4.isAdulte();
                            }
                            else {
                                b2 = !channelItem4.isAdulte();
                            }
                        }
                        else {
                            b2 = !channelItem4.isAdulte();
                        }
                    }
                    else if (b) {
                        if (EIRC.chat_adult == 0) {
                            b2 = channelItem4.isAdulte();
                        }
                        else if (EIRC.chat_adult == 1) {
                            b2 = !channelItem4.isAdulte();
                        }
                    }
                    else {
                        b2 = !channelItem4.isAdulte();
                    }
                }
                else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                    if (b) {
                        if (EIRC.chat_adult == 0) {
                            b2 = channelItem4.isAdulte();
                        }
                        else {
                            b2 = !channelItem4.isAdulte();
                        }
                    }
                    else {
                        b2 = !channelItem4.isAdulte();
                    }
                }
                else if (b) {
                    if (EIRC.chat_adult == 0) {
                        b2 = channelItem4.isAdulte();
                    }
                    else if (EIRC.chat_adult == 1) {
                        b2 = !channelItem4.isAdulte();
                    }
                }
                else {
                    b2 = !channelItem4.isAdulte();
                }
                if (tag.equalsIgnoreCase("#aide")) {
                    b2 = true;
                }
                if (channelItem4.getUsers() >= int1 && b2 && !this.vect.contains(channelItem4)) {
                    this.vect.addElement(channelItem4);
                }
            }
        }
        this.listchan.setModel(new DefaultListModel());
        this.listchan.setListData(this.vect);
    }
    
    public void colorForeground(final Color color) {
        this.setForeground(color);
        this.labchatteur.setForeground(color);
        this.pourlecomp.setForeground(color);
        this.chatadult.setForeground(color);
        this.chatnormale.setForeground(color);
        this.chatadultcheck.setForeground(color);
        this.new_chan_ok.setForeground(color);
        this.trouver.setForeground(color);
        this.sortby.setForeground(color);
        this.users_nbr.setForeground(color);
    }
    
    @Override
    public int compare(final Object o, final Object o2) {
        final ChannelItem channelItem = (ChannelItem)o;
        final ChannelItem channelItem2 = (ChannelItem)o2;
        switch (ChanListPanel.sort_criteria) {
            case 0: {
                return ChanListPanel.collator.compare(channelItem.getTag(), channelItem2.getTag());
            }
            case 1: {
                return channelItem2.getUsers() - channelItem.getUsers();
            }
            default: {
                return channelItem2.getUsers() - channelItem.getUsers();
            }
        }
    }
    
    public void creerlistsaj() {
        final StringBuilder sb = new StringBuilder();
        this.eirc.getAccueil();
        if (!new File(sb.append(main.homeapp).append("\\s0125").toString()).exists()) {
            String s;
            if (NickInfos.getAge(this.eirc.getNick()) >= 18 && NickInfos.getAge(this.eirc.getNick()) < 20) {
                s = "#18-25ans";
            }
            else if (NickInfos.getAge(this.eirc.getNick()) >= 20 && NickInfos.getAge(this.eirc.getNick()) < 30) {
                s = "#25-30ans";
            }
            else if (NickInfos.getAge(this.eirc.getNick()) >= 30 && NickInfos.getAge(this.eirc.getNick()) < 40) {
                s = "#30-40ans";
            }
            else if (NickInfos.getAge(this.eirc.getNick()) >= 40 && NickInfos.getAge(this.eirc.getNick()) < 50) {
                s = "#40-50ans";
            }
            else {
                s = "#+50ans";
            }
            final EIRC eirc = this.eirc;
            EIRC.sajoin.addElement(s.toLowerCase());
            try {
                final StringBuilder sb2 = new StringBuilder();
                this.eirc.getAccueil();
                final PrintWriter printWriter = new PrintWriter(new FileWriter(sb2.append(main.homeapp).append("\\s0125").toString()), true);
                printWriter.println(s);
                final String s2 = this.eirc.getAccueil().regiontab.get(NickInfos.getLocation(this.eirc.getNick()).toLowerCase());
                if (s2 != null) {
                    final String[] split = s2.split(",");
                    for (int i = 0; i < split.length; ++i) {
                        printWriter.println(split[i]);
                        final EIRC eirc2 = this.eirc;
                        EIRC.sajoin.addElement(split[i].toLowerCase());
                    }
                }
                final Enumeration elements = this.listsalon.elements();
                while (elements.hasMoreElements()) {
                    final String tag = elements.nextElement().getTag();
                    if (tag.equalsIgnoreCase("#" + NickInfos.getLocation(this.eirc.getNick()))) {
                        printWriter.println(tag);
                        final EIRC eirc3 = this.eirc;
                        EIRC.sajoin.addElement(tag.toLowerCase());
                    }
                }
                printWriter.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public void enabled(final boolean enabled) {
        this.trouver.setEnabled(enabled);
        this.listchan.setEnabled(enabled);
        this.users_nbr.setEnabled(enabled);
        this.new_chan_ok.setEnabled(enabled);
        this.labchatteur.setEnabled(enabled);
        this.sortby.setEnabled(enabled);
        this.jScrollPane1.setEnabled(enabled);
    }
    
    public void handleHyperlink(final String s) {
        if (s.startsWith("#")) {
            this.joinChannel(s);
        }
        else {
            try {
                this.eirc.visitURL(new URL(s));
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public void handleNick(final String s) {
    }
    
    public void initvectadult() {
        this.tchatnormal.removeAllElements();
        this.tchatadult.removeAllElements();
        this.tchatsajoin.removeAllElements();
        final EIRC eirc = this.eirc;
        if (EIRC.sajoin.size() > 0) {
            this.tchatsajoin.addElement("Salons ouverts auto");
            int n = 0;
            while (true) {
                final int n2 = n;
                final EIRC eirc2 = this.eirc;
                if (n2 >= EIRC.sajoin.size()) {
                    break;
                }
                final Vector tchatsajoin = this.tchatsajoin;
                final EIRC eirc3 = this.eirc;
                tchatsajoin.addElement(EIRC.sajoin.elementAt(n));
                ++n;
            }
        }
        this.tchatnormal.addElement("Tchat par ville/d\u00e9partement");
        this.tchatnormal.addElement("#Amiens");
        this.tchatnormal.addElement("#Angers");
        this.tchatnormal.addElement("#Bayonne");
        this.tchatnormal.addElement("#Besan\u00e7on");
        this.tchatnormal.addElement("#Bordeaux");
        this.tchatnormal.addElement("#Brest");
        this.tchatnormal.addElement("#Bruxelles");
        this.tchatnormal.addElement("#Caen");
        this.tchatnormal.addElement("#Charleroi");
        this.tchatnormal.addElement("#Clermont-ferrand");
        this.tchatnormal.addElement("#Dijon");
        this.tchatnormal.addElement("#Gen\u00e8ve");
        this.tchatnormal.addElement("#Grenoble");
        this.tchatnormal.addElement("#Le-havre");
        this.tchatnormal.addElement("#Le-mans");
        this.tchatnormal.addElement("#Lens");
        this.tchatnormal.addElement("#Lille");
        this.tchatnormal.addElement("#Limoges");
        this.tchatnormal.addElement("#Li\u00e8ge");
        this.tchatnormal.addElement("#Lyon");
        this.tchatnormal.addElement("#Marseille");
        this.tchatnormal.addElement("#Montr\u00e9al");
        this.tchatnormal.addElement("#Mulhouse");
        this.tchatnormal.addElement("#Namur");
        this.tchatnormal.addElement("#Nancy");
        this.tchatnormal.addElement("#Nantes");
        this.tchatnormal.addElement("#Narbonne");
        this.tchatnormal.addElement("#Nice");
        this.tchatnormal.addElement("#N\u00eemes");
        this.tchatnormal.addElement("#Orl\u00e9ans");
        this.tchatnormal.addElement("#Perpignan");
        this.tchatnormal.addElement("#Poitiers");
        this.tchatnormal.addElement("#Reims");
        this.tchatnormal.addElement("#Rennes");
        this.tchatnormal.addElement("#Rouen");
        this.tchatnormal.addElement("#Saint-etienne");
        this.tchatnormal.addElement("#Toulon");
        this.tchatnormal.addElement("#Toulouse");
        this.tchatnormal.addElement("#Tours");
        this.tchatnormal.addElement("#Alpes-de-haute-provence");
        this.tchatnormal.addElement("#Alpes-maritimes");
        this.tchatnormal.addElement("#Ardeche");
        this.tchatnormal.addElement("#Bouche-du-rhone");
        this.tchatnormal.addElement("#Dordogne");
        this.tchatnormal.addElement("#Gironde");
        this.tchatnormal.addElement("#Haute-savoie");
        this.tchatnormal.addElement("#Hautes-alpes");
        this.tchatnormal.addElement("#Isere");
        this.tchatnormal.addElement("#Landes");
        this.tchatnormal.addElement("#Loire");
        this.tchatnormal.addElement("#Lot-et-garonne");
        this.tchatnormal.addElement("#Nord");
        this.tchatnormal.addElement("#Pyr\u00e9n\u00e9es-atlantique");
        this.tchatnormal.addElement("#Savoie");
        this.tchatnormal.addElement("#Vaucluse");
        this.tchatnormal.addElement("#Drome");
        this.tchatnormal.addElement("Tchat r\u00e9gional");
        this.tchatnormal.addElement("#Regions-nord-est");
        this.tchatnormal.addElement("#Regions-nord");
        this.tchatnormal.addElement("#Regions-nord-ouest");
        this.tchatnormal.addElement("#Regions-centre");
        this.tchatnormal.addElement("#Regions-sud-ouest");
        this.tchatnormal.addElement("#Regions-sud-est");
        this.tchatnormal.addElement("#Dom-tom");
        this.tchatnormal.addElement("Tchat international");
        this.tchatnormal.addElement("#Cote-d-ivoire");
        this.tchatnormal.addElement("#Afrique");
        this.tchatnormal.addElement("#Alg\u00e9rie");
        this.tchatnormal.addElement("#Belgique");
        this.tchatnormal.addElement("#Ile-maurice");
        this.tchatnormal.addElement("#Italy");
        this.tchatnormal.addElement("#Germany");
        this.tchatnormal.addElement("#Madagascar");
        this.tchatnormal.addElement("#Maroc");
        this.tchatnormal.addElement("#Qu\u00e9bec");
        this.tchatnormal.addElement("#S\u00e9n\u00e9gal");
        this.tchatnormal.addElement("#Spain");
        this.tchatnormal.addElement("#Suisse");
        this.tchatnormal.addElement("#Tunisie");
        this.tchatnormal.addElement("#Tunis");
        this.tchatnormal.addElement("#United-states");
        this.tchatnormal.addElement("#Portugal");
        this.tchatnormal.addElement("#Luxembourg");
        this.tchatnormal.addElement("#united-kingdom");
        this.tchatnormal.addElement("Tchat th\u00e9matique");
        this.tchatnormal.addElement("#Aide");
        this.tchatnormal.addElement("#Blackjack");
        this.tchatnormal.addElement("#Charme");
        this.tchatnormal.addElement("#Cin\u00e9ma");
        this.tchatnormal.addElement("#Cuisine-arabe");
        this.tchatnormal.addElement("#Informatique");
        this.tchatnormal.addElement("#Insultes");
        this.tchatnormal.addElement("#Jeu-motus");
        this.tchatnormal.addElement("#jeu-echec");
        this.tchatnormal.addElement("#Jeu-scramble");
        this.tchatnormal.addElement("#Jeux-videos");
        this.tchatnormal.addElement("#Langue-arabe");
        this.tchatnormal.addElement("#Voyance");
        this.tchatnormal.addElement("#Litterature");
        this.tchatnormal.addElement("#Musique");
        this.tchatnormal.addElement("#Politique");
        this.tchatnormal.addElement("#Quizz");
        this.tchatnormal.addElement("#Quizz2");
        this.tchatnormal.addElement("#Radio-oriental");
        this.tchatnormal.addElement("#Psychologie");
        this.tchatnormal.addElement("#Sant\u00e9");
        this.tchatnormal.addElement("#Sports");
        this.tchatnormal.addElement("#Webcam");
        this.tchatnormal.addElement("#Uno");
        this.tchatnormal.addElement("#Vips");
        this.tchatnormal.addElement("Tchat Par tranche d'\u00e2ge");
        this.tchatnormal.addElement("#18-25ans");
        this.tchatnormal.addElement("#25-30ans");
        this.tchatnormal.addElement("#30-40ans");
        this.tchatnormal.addElement("#40-50ans");
        this.tchatnormal.addElement("#+50ans");
        this.tchatadult.addElement("Tchat Adulte");
        this.tchatadult.addElement("#Quizz-chaud");
        this.tchatadult.addElement("#Sexe-voyeuse");
        this.tchatadult.addElement("#Sexe");
        this.tchatadult.addElement("#Suceuse");
        this.tchatadult.addElement("#Gros-seins");
        this.tchatadult.addElement("#String");
        this.tchatadult.addElement("#Cochonne");
        this.tchatadult.addElement("#Sexe-ronde");
        this.tchatadult.addElement("#Bisexuel-hot");
        this.tchatadult.addElement("#Bisexuel");
        this.tchatadult.addElement("#Lesbienne-hot");
        this.tchatadult.addElement("#Lesbienne");
        this.tchatadult.addElement("#Gay-sexe");
        this.tchatadult.addElement("#Gay");
        this.tchatadult.addElement("#Masturbation");
        this.tchatadult.addElement("#Sexe-cam");
        this.tchatadult.addElement("#Soumise");
        this.tchatadult.addElement("#Mini-jupe");
        this.tchatadult.addElement("#Femme-m\u00fbre");
        this.tchatadult.addElement("#Fantasmes");
        this.tchatadult.addElement("#Nu-chez-moi");
        this.tchatadult.addElement("#sc\u00e9narios");
    }
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.sortby) && itemEvent.getStateChange() == 1) {
            if (this.sortby.getSelectedIndex() == 0) {
                ChanListPanel.sort_criteria = 0;
            }
            else {
                ChanListPanel.sort_criteria = 1;
            }
            this.afficher();
        }
    }
    
    private void jbInit() throws Exception {
        (this.tools = new JToolBar()).setFloatable(false);
        this.borderLayout = new BorderLayout();
        (this.pourlecomp = new JPanel()).setLayout(this.gridbaglayout);
        this.chatnormale = new JRadioButton("Tchat public");
        this.chatadult = new JRadioButton("Tchat adulte");
        this.chatadultcheck = new JCheckBox("Tchat adulte");
        this.chatnormale.setOpaque(false);
        this.chatadult.setOpaque(false);
        this.chatadultcheck.setOpaque(false);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 19;
        (this.new_chan_ok = new JButton(this.newchan_str)).addActionListener(this);
        this.new_chan_ok.setToolTipText("Cliquez ici pour cr\u00e9er ou rejoindre un salon");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        this.pourlecomp.add(this.new_chan_ok, gridBagConstraints);
        this.users_nbr = new JComboBox();
        this.sortby = new JComboBox();
        for (int i = 1; i < 101; ++i) {
            this.users_nbr.addItem("" + i);
        }
        this.users_nbr.setSelectedIndex(0);
        this.users_nbr.setToolTipText("Cliquez ici pour choisir le nombre minimum de tchateurs par salon.");
        this.labchatteur = new JLabel("chateur(s) min.");
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(this.users_nbr);
        panel.add(this.labchatteur);
        panel.setOpaque(false);
        this.sortby.addItem("Trier par nom");
        this.sortby.addItem("Trier par nombre");
        this.sortby.addItem("Trier par th\u00e9matique");
        this.sortby.setSelectedIndex(2);
        this.sortby.addItemListener(this);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.pourlecomp.add(this.sortby, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.pourlecomp.add(panel, gridBagConstraints);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.pourlecomp.add(this.tools, gridBagConstraints);
        (this.trouver = new JButton("Actualiser")).setToolTipText("Cliquez ici pour actualiser la liste des salons");
        this.trouver.addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.pourlecomp.add(this.trouver, gridBagConstraints);
        (this.listchan = new JList()).updateUI();
        this.jScrollPane1.getViewport().add(this.listchan);
        this.jScrollPane1.setOpaque(false);
        this.jScrollPane1.getViewport().setOpaque(false);
        this.listchan.setOpaque(false);
        (this.opacity = new MyOpacity()).add(this.jScrollPane1);
        this.opacity.setLayout(new GridLayout(1, 1));
        this.add("South", this.pourlecomp);
        this.add("Center", this.opacity);
        this.users_nbr.addActionListener(this);
        this.listchan.setCellRenderer(new ChannelItemRenderer());
        this.listchan.addMouseListener(this);
        this.listchan.addMouseMotionListener(this);
        this.listchan.setToolTipText("Cliquez sur le nom du salon que vous voulez rejoindre");
        this.pourlecomp.setOpaque(false);
        this.setOpaque(false);
        this.chatadult.addActionListener(this);
        this.chatadultcheck.addActionListener(this);
        this.chatnormale.addActionListener(this);
        this.p_menu = new JPopupMenu();
        this.p_saj = new JMenuItem("Ajouter aux salons ouverts auto");
        this.p_ouvrir = new JMenuItem("Ouvrir le salon");
        this.p_menu.add(this.p_ouvrir);
        this.p_menu.add(this.p_saj);
        this.p_ouvrir.addActionListener(this);
        this.p_saj.addActionListener(this);
    }
    
    protected void joinChannel(final String s) {
        if (CHANNELS.getChannelWindow(s) == null) {
            this.eirc.sendMessage("JOIN", new String[] { s });
        }
        else {
            this.eirc.getChat_panel().ShowChannel(s);
        }
    }
    
    public void listAdd(final String s, final int n, final boolean b) {
        synchronized (this.listsalon) {
            this.listsalon.addElement(new ChannelItem(s, n, b, false));
        }
    }
    
    public void listEnd() {
        this.trouver.setEnabled(true);
        this.users_nbr.setEnabled(true);
        this.sortby.setEnabled(true);
        this.afficher();
    }
    
    public void listStart() {
        if (this.listsalon != null) {
            this.listsalon.removeAllElements();
        }
        this.listsalon = new MyVector();
    }
    
    public void loadChan() {
        this.eirc.sendMessage("LIST", new String[] { "*" });
        this.trouver.setEnabled(false);
        this.users_nbr.setEnabled(false);
        this.sortby.setEnabled(false);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.listchan)) {
            if (mouseEvent.getClickCount() == 2) {
                this.setCursor(Cursor.getPredefinedCursor(12));
                if (this.listchan.getSelectedIndex() != -1) {
                    this.joinChannel(this.vect.elementAt(this.listchan.getSelectedIndex()).getTag());
                }
            }
            else {
                final ChannelItem channelItem = this.listchan.getSelectedValue();
                if (channelItem == null) {
                    return;
                }
                if (!channelItem.getTag().startsWith("#")) {
                    return;
                }
                this.salonselection = channelItem.getTag();
                final EIRC eirc = this.eirc;
                if (EIRC.sajoin.contains(this.salonselection.toLowerCase())) {
                    this.p_saj.setText("Supprimer des salons ouverts auto");
                }
                else {
                    this.p_saj.setText("Ajouter aux salons ouverts auto");
                }
                this.p_menu.show(this.listchan, mouseEvent.getX() + 15, mouseEvent.getY());
            }
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.listchan.clearSelection();
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.listchan)) {
            final int locationToIndex = this.listchan.locationToIndex(mouseEvent.getPoint());
            if (locationToIndex != -1) {
                this.listchan.setSelectedIndex(locationToIndex);
            }
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void savesaj() {
        try {
            final StringBuilder sb = new StringBuilder();
            this.eirc.getAccueil();
            final PrintWriter printWriter = new PrintWriter(new FileWriter(sb.append(main.homeapp).append("\\s0125").toString()), true);
            int n = 0;
            while (true) {
                final int n2 = n;
                final EIRC eirc = this.eirc;
                if (n2 >= EIRC.sajoin.size()) {
                    break;
                }
                final PrintWriter printWriter2 = printWriter;
                final EIRC eirc2 = this.eirc;
                printWriter2.println(EIRC.sajoin.elementAt(n).toLowerCase());
                ++n;
            }
            printWriter.close();
        }
        catch (Exception ex) {}
    }
    
    public void setConnected(final boolean enabled) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setEnabled(enabled);
        }
    }
    
    public void Vvect() {
        this.listsalon.removeAllElements();
    }
    
    public void chatadultcheckaction() {
        if (this.chatadultcheck.isSelected()) {
            final Object[] array = { "Continuer", "Annuler" };
            if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point d'acc\u00e9der au \"tchat adulte chaud\"", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                this.chat_adulte = true;
                MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                    final String user = NickInfos.getUser(this.eirc.getUsednick());
                    if (user.length() >= 10) {
                        final String string = user.substring(0, 6) + 2 + user.substring(7);
                        if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string)) {
                            this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string });
                            NickInfos.updateInfos(this.eirc.getUsednick(), string, NickInfos.getInetAddr(this.eirc.getUsednick()));
                            this.eirc.setUsername(string);
                        }
                    }
                }
                NickInfos.setNoSex(this.eirc.getNick(), 2);
                EIRC.chat_adult = 2;
                this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                this.chatadult.setVisible(true);
                this.chatnormale.setVisible(false);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000L);
                        }
                        catch (InterruptedException ex) {}
                        ChanListPanel.this.eirc.sendMessage("JOIN", new String[] { "#sexe" });
                    }
                }.start();
                this.eirc.getRightpanel().whoListPanel.loadUsers();
                this.eirc.getRightpanel().chanListPanel.loadChan();
                this.eirc.getRightpanel().whoListPanel.chatadultcheck.setSelected(this.chatadultcheck.isSelected());
            }
            else {
                this.chatadultcheck.setSelected(false);
                this.eirc.getRightpanel().whoListPanel.chatadultcheck.setSelected(false);
            }
        }
        else {
            this.chat_adulte = false;
            final Object[] array2 = { "Continuer", "Annuler" };
            if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point de quitter \"tchat adulte chaud\"", "Chat-Land.org", 0, 1, null, array2, array2[1]) == 0) {
                this.chat_adulte = false;
                MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                    final String user2 = NickInfos.getUser(this.eirc.getUsednick());
                    if (user2.length() >= 10) {
                        final String string2 = user2.substring(0, 6) + 1 + user2.substring(7);
                        if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string2)) {
                            this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string2 });
                            NickInfos.updateInfos(this.eirc.getUsednick(), string2, NickInfos.getInetAddr(this.eirc.getUsednick()));
                            this.eirc.setUsername(string2);
                        }
                    }
                }
                NickInfos.setNoSex(this.eirc.getNick(), 1);
                EIRC.chat_adult = 1;
                this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                this.eirc.getRightpanel().whoListPanel.loadUsers();
                this.eirc.getRightpanel().chanListPanel.loadChan();
                this.chatadult.setSelected(false);
                this.chatnormale.setSelected(true);
                this.eirc.getRightpanel().whoListPanel.chatadultcheck.setSelected(this.chatadultcheck.isSelected());
            }
            else {
                this.chatadultcheck.setSelected(true);
                this.eirc.getRightpanel().whoListPanel.chatadultcheck.setSelected(true);
            }
        }
    }
    
    public void chatadultaction() {
        this.eirc.sendMessage("MYWHO", new String[] { this.eirc.getUsednick() });
        if (!this.chat_adulte) {
            if (NickInfos.getAge(this.eirc.getNick()) <= 17 && NickInfos.getAge(this.eirc.getNick()) != -1) {
                JOptionPane.showMessageDialog(this.eirc.getAccueil().Application, "Chat adult r\u00e9serv\u00e9 aux adultes");
                this.chatadult.setSelected(false);
                this.chatnormale.setSelected(true);
            }
            else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                final Object[] array = { "Continuer", "Annuler" };
                if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point de quitter \"tchat tout public\"\nTous les salons en cours vont \u00eatre ferm\u00e9s", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    this.chat_adulte = true;
                    MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                    if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                        final String user = NickInfos.getUser(this.eirc.getUsednick());
                        if (user.length() >= 10) {
                            final String string = user.substring(0, 6) + 0 + user.substring(7);
                            if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string)) {
                                this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string });
                                NickInfos.updateInfos(this.eirc.getUsednick(), string, NickInfos.getInetAddr(this.eirc.getUsednick()));
                                this.eirc.setUsername(string);
                            }
                        }
                    }
                    NickInfos.setNoSex(this.eirc.getNick(), 0);
                    EIRC.chat_adult = 0;
                    this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                    this.eirc.getRightpanel().whoListPanel.loadUsers();
                    this.eirc.getRightpanel().chanListPanel.loadChan();
                    this.eirc.getChannelgroup().setVisible(false);
                    synchronized (CHANNELS.channels) {
                        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                        while (elements.hasMoreElements()) {
                            CHANNELS.CloseChannel(elements.nextElement().getTag().toLowerCase());
                        }
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000L);
                            }
                            catch (InterruptedException ex) {}
                            ChanListPanel.this.eirc.sendMessage("JOIN", new String[] { "#sexe" });
                        }
                    }.start();
                    this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(true);
                }
                else {
                    this.chatadult.setSelected(false);
                    this.chatnormale.setSelected(true);
                    this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(false);
                    this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(true);
                }
            }
        }
    }
    
    public void chatnormaleaction() {
        this.eirc.sendMessage("MYWHO", new String[] { this.eirc.getUsednick() });
        if (this.chat_adulte) {
            if (this.eirc.isRegister()) {
                if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                    final Object[] array = { "Continuer", "Annuler" };
                    if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point de quitter \"tchat adulte chaud\"\nTous les salons en cours vont \u00eatre ferm\u00e9s", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                        this.chat_adulte = false;
                        MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                        if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                            final String user = NickInfos.getUser(this.eirc.getUsednick());
                            if (user.length() >= 10) {
                                final String string = user.substring(0, 6) + 1 + user.substring(7);
                                if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string)) {
                                    this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string });
                                    NickInfos.updateInfos(this.eirc.getUsednick(), string, NickInfos.getInetAddr(this.eirc.getUsednick()));
                                    this.eirc.setUsername(string);
                                }
                            }
                        }
                        NickInfos.setNoSex(this.eirc.getUsednick(), 1);
                        EIRC.chat_adult = 1;
                        this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                        if (NickInfos.getAge(this.eirc.getNick()) >= 18 && NickInfos.getAge(this.eirc.getNick()) < 25) {
                            this.jchannel = "#18-25ans";
                        }
                        else if (NickInfos.getAge(this.eirc.getNick()) >= 25 && NickInfos.getAge(this.eirc.getNick()) < 30) {
                            this.jchannel = "#25-30ans";
                        }
                        else if (NickInfos.getAge(this.eirc.getNick()) >= 30 && NickInfos.getAge(this.eirc.getNick()) < 40) {
                            this.jchannel = "#30-40ans";
                        }
                        else if (NickInfos.getAge(this.eirc.getNick()) >= 40 && NickInfos.getAge(this.eirc.getNick()) < 50) {
                            this.jchannel = "#40-50ans";
                        }
                        else {
                            this.jchannel = "#+50ans";
                        }
                        this.eirc.getRightpanel().whoListPanel.loadUsers();
                        this.eirc.getRightpanel().chanListPanel.loadChan();
                        this.eirc.getChannelgroup().setVisible(false);
                        synchronized (CHANNELS.channels) {
                            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                            while (elements.hasMoreElements()) {
                                CHANNELS.CloseChannel(elements.nextElement().getTag().toLowerCase());
                            }
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000L);
                                }
                                catch (InterruptedException ex) {}
                                ChanListPanel.this.eirc.sendMessage("JOIN", new String[] { ChanListPanel.this.jchannel });
                            }
                        }.start();
                        this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(true);
                    }
                    else {
                        this.chatadult.setSelected(true);
                        this.chatnormale.setSelected(false);
                        this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(true);
                        this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(false);
                    }
                }
                else {
                    final Object[] array2 = { "Continuer", "Annuler" };
                    if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point d'acc\u00e9der au \"tchat tout public\"", "Chat-Land.org", 0, 1, null, array2, array2[1]) == 0) {
                        this.chat_adulte = false;
                        MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                        if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                            final String user2 = NickInfos.getUser(this.eirc.getUsednick());
                            if (user2.length() >= 10) {
                                final String string2 = user2.substring(0, 6) + 2 + user2.substring(7);
                                if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string2)) {
                                    this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string2 });
                                    NickInfos.updateInfos(this.eirc.getUsednick(), string2, NickInfos.getInetAddr(this.eirc.getUsednick()));
                                    this.eirc.setUsername(string2);
                                }
                            }
                        }
                        NickInfos.setNoSex(this.eirc.getNick(), 2);
                        EIRC.chat_adult = 2;
                        this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                        this.eirc.getRightpanel().whoListPanel.loadUsers();
                        this.eirc.getRightpanel().chanListPanel.loadChan();
                        this.chatadult.setVisible(false);
                        this.chatnormale.setVisible(false);
                        this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(true);
                    }
                    else {
                        this.chatadult.setSelected(true);
                        this.chatnormale.setSelected(false);
                        this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(true);
                        this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(false);
                    }
                }
            }
            else if (this.eirc.getSex(this.eirc.getNick()) == 1) {
                final Object[] array3 = { "Continuer", "Annuler" };
                if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point de quitter \"tchat adulte chaud\"\nTous les salons en cours vont \u00eatre ferm\u00e9s", "Chat-Land.org", 0, 1, null, array3, array3[1]) == 0) {
                    this.chat_adulte = false;
                    MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                    if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                        final String user3 = NickInfos.getUser(this.eirc.getUsednick());
                        if (user3.length() >= 10) {
                            final String string3 = user3.substring(0, 6) + 1 + user3.substring(7);
                            if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string3)) {
                                this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string3 });
                                NickInfos.updateInfos(this.eirc.getUsednick(), string3, NickInfos.getInetAddr(this.eirc.getUsednick()));
                                this.eirc.setUsername(string3);
                            }
                        }
                    }
                    NickInfos.setNoSex(this.eirc.getUsednick(), 1);
                    EIRC.chat_adult = 1;
                    this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                    if (NickInfos.getAge(this.eirc.getNick()) >= 18 && NickInfos.getAge(this.eirc.getNick()) < 20) {
                        this.jchannel = "#18-25ans";
                    }
                    else if (NickInfos.getAge(this.eirc.getNick()) >= 20 && NickInfos.getAge(this.eirc.getNick()) < 30) {
                        this.jchannel = "#25-30ans";
                    }
                    else if (NickInfos.getAge(this.eirc.getNick()) >= 30 && NickInfos.getAge(this.eirc.getNick()) < 40) {
                        this.jchannel = "#30-40ans";
                    }
                    else if (NickInfos.getAge(this.eirc.getNick()) >= 40 && NickInfos.getAge(this.eirc.getNick()) < 50) {
                        this.jchannel = "#40-50ans";
                    }
                    else {
                        this.jchannel = "#+50ans";
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000L);
                            }
                            catch (InterruptedException ex) {}
                            synchronized (CHANNELS.channels) {
                                final Enumeration<String> keys = CHANNELS.channels.keys();
                                while (keys.hasMoreElements()) {
                                    CHANNELS.CloseChannel(keys.nextElement());
                                }
                            }
                            ChanListPanel.this.eirc.sendMessage("JOIN", new String[] { ChanListPanel.this.jchannel });
                        }
                    }.start();
                    this.eirc.getRightpanel().whoListPanel.loadUsers();
                    this.eirc.getRightpanel().chanListPanel.loadChan();
                    this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(true);
                }
                else {
                    this.chatadult.setSelected(true);
                    this.chatnormale.setSelected(false);
                    this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(true);
                    this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(false);
                }
            }
            else {
                final Object[] array4 = { "Continuer", "Annuler" };
                if (JOptionPane.showOptionDialog(this.eirc.getAccueil().Application, "Vous \u00eates sur le point d'acc\u00e9der au \"tchat tout public\"", "Chat-Land.org", 0, 1, null, array4, array4[1]) == 0) {
                    this.chat_adulte = false;
                    MD5Nick.getMD5_3("picolo" + this.eirc.getUsednick().toLowerCase());
                    if (NickInfos.getUser(this.eirc.getUsednick()) != null && NickInfos.getUser(this.eirc.getUsednick()).length() > 9) {
                        final String user4 = NickInfos.getUser(this.eirc.getUsednick());
                        if (user4.length() >= 10) {
                            final String string4 = user4.substring(0, 6) + 2 + user4.substring(7);
                            if (!NickInfos.getUser(this.eirc.getUsednick()).equals(string4)) {
                                this.eirc.sendMessage("PRIVMSG", new String[] { "#irc-change-ident", string4 });
                                NickInfos.updateInfos(this.eirc.getUsednick(), string4, NickInfos.getInetAddr(this.eirc.getUsednick()));
                                this.eirc.setUsername(string4);
                            }
                        }
                    }
                    NickInfos.setNoSex(this.eirc.getNick(), 2);
                    EIRC.chat_adult = 2;
                    this.eirc.setUsername(NickInfos.getUser(this.eirc.getUsednick()).substring(0, 6) + EIRC.chat_adult + NickInfos.getUser(this.eirc.getUsednick()).substring(7, 10));
                    this.eirc.getRightpanel().whoListPanel.loadUsers();
                    this.eirc.getRightpanel().chanListPanel.loadChan();
                    this.chatadult.setVisible(false);
                    this.chatnormale.setVisible(false);
                    this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(true);
                }
                else {
                    this.chatadult.setSelected(true);
                    this.chatnormale.setSelected(false);
                    this.eirc.getRightpanel().whoListPanel.chatadult.setSelected(true);
                    this.eirc.getRightpanel().whoListPanel.chatnormale.setSelected(false);
                }
            }
        }
    }
    
    static {
        ChanListPanel.sort_criteria = 1;
    }
}
