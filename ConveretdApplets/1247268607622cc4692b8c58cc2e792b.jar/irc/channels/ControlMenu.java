// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import javax.swing.event.PopupMenuEvent;
import irc.AwayPopup;
import java.awt.event.MouseEvent;
import java.awt.Color;
import irc.com.utils.MD5Nick;
import java.util.Enumeration;
import irc.managers.CMD;
import irc.com.utils.MyVector;
import irc.com.nick.NickInfos;
import irc.suggestion;
import irc.managers.CHANNELS;
import irc.desinstaller;
import irc.apropo;
import irc.com.utils.IEAutoStart;
import java.io.IOException;
import java.io.File;
import irc.main;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import irc.managers.Resources;
import java.awt.Font;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import irc.EIRC;
import java.awt.event.MouseListener;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;
import irc.channels.components.MyJToolBar;

public class ControlMenu extends MyJToolBar implements ActionListener, PopupMenuListener, MouseListener
{
    private EIRC eirc;
    private JLabel fichier;
    private JLabel absent;
    private JLabel outils;
    private JLabel Messalons;
    private JLabel ChatLand;
    private JLabel point;
    private JPopupMenu popupoutil;
    private JMenuItem option;
    private JMenuItem style;
    private JMenuItem afficherpv;
    private JMenuItem affichersalon;
    private JMenuItem reduirepv;
    private JMenuItem reduiresalon;
    private JMenuItem suggestions1;
    private JPopupMenu menufichier;
    private JMenuItem content;
    private JMenuItem charmeur;
    private JMenuItem triste;
    private JMenuItem malade;
    private JMenuItem fatigu\u00e9;
    private JMenuItem cool;
    private JMenuItem amoureux;
    private JMenuItem fumer;
    private JMenuItem surpris;
    private JMenuItem diabolik;
    private JMenuItem hanteux;
    private JMenuItem indiff\u00e9rent;
    private JMenuItem m\u00e9fiant;
    private JMenuItem joueur;
    private JMenuItem pascontent;
    private JMenuItem desactiver;
    private JMenuItem quitter;
    private JMenuItem d\u00e9connecter;
    private JMenuItem testeur;
    private JPopupMenu menu;
    private JPopupMenu popupsalonpv;
    private JMenu humeur1;
    private JMenu DivertissementS;
    private JMenuItem[] favs;
    private JMenuItem dellfav;
    public Vector favoris;
    private JPopupMenu popupchatland;
    private JPopupMenu popuppoint;
    private JMenuItem accuiel;
    private JMenuItem mail;
    private JMenuItem photos;
    private JMenuItem Videos;
    private JMenuItem fondsecran;
    private JMenuItem jeux1;
    private JMenuItem ticket;
    private JMenuItem blagues;
    private JMenuItem paroles;
    private JMenuItem modifer;
    private JMenuItem aide1;
    private JMenuItem Apropos1;
    private JMenuItem uninstall;
    private JMenuItem assistance;
    private JMenu Mabsent;
    private boolean menushow;
    
    public ControlMenu() {
        this.favoris = new Vector();
        this.menushow = false;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ControlMenu(final EIRC eirc) {
        this.favoris = new Vector();
        this.menushow = false;
        this.eirc = eirc;
        this.add(this.fichier = new JLabel(" Fichier "));
        this.add(this.absent = new JLabel(" S'absenter "));
        this.add(this.outils = new JLabel(" Outils "));
        this.add(this.Messalons = new JLabel(" Salons & PV "));
        this.add(this.ChatLand = new JLabel(" Extra "));
        this.point = new JLabel("?");
        this.fichier.setFont(new Font("Dialog", 0, 12));
        this.absent.setFont(new Font("Dialog", 0, 12));
        this.outils.setFont(new Font("Dialog", 0, 12));
        this.Messalons.setFont(new Font("Dialog", 0, 12));
        this.ChatLand.setFont(new Font("Dialog", 0, 12));
        this.point.setFont(new Font("Dialog", 0, 12));
        this.point.addMouseListener(this);
        this.add(this.point);
        this.fichier.addMouseListener(this);
        this.absent.addMouseListener(this);
        this.outils.addMouseListener(this);
        this.Messalons.addMouseListener(this);
        this.ChatLand.addMouseListener(this);
        this.point.addMouseListener(this);
        (this.menufichier = new JPopupMenu()).addPopupMenuListener(this);
        (this.popupsalonpv = new JPopupMenu()).addPopupMenuListener(this);
        (this.Mabsent = new JMenu("Mes salons favoris")).setIcon(new ImageIcon(Resources.getImages("iconmenu_salon_fav")));
        this.popupsalonpv.add(this.Mabsent);
        this.popupsalonpv.addSeparator();
        (this.afficherpv = new JMenuItem("Restaurer toutes les discussions priv\u00e9es")).setIcon(new ImageIcon(Resources.getImages("iconmenu_rest_pv")));
        this.popupsalonpv.add(this.afficherpv);
        this.afficherpv.addActionListener(this);
        (this.reduirepv = new JMenuItem("R\u00e9duire toutes les discussions priv\u00e9es")).setIcon(new ImageIcon(Resources.getImages("iconmenu_reduire_pv")));
        this.popupsalonpv.add(this.reduirepv);
        this.reduirepv.addActionListener(this);
        this.popupsalonpv.addSeparator();
        (this.affichersalon = new JMenuItem("Restaurer tous les salons")).setIcon(new ImageIcon(Resources.getImages("iconmenu_affichersalon")));
        this.popupsalonpv.add(this.affichersalon);
        this.affichersalon.addActionListener(this);
        (this.reduiresalon = new JMenuItem("R\u00e9duire tous les salons")).setIcon(new ImageIcon(Resources.getImages("iconmenu_reduire_salon")));
        this.popupsalonpv.add(this.reduiresalon);
        this.reduiresalon.addActionListener(this);
        this.fichier.setFont(new Font("Time New Roman", 0, 12));
        this.absent.setFont(new Font("Time New Roman", 0, 12));
        this.outils.setFont(new Font("Time New Roman", 0, 12));
        this.Messalons.setFont(new Font("Time New Roman", 0, 12));
        this.ChatLand.setFont(new Font("Time New Roman", 0, 12));
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getSource().equals(this.testeur)) {
            final String s = (String)JOptionPane.showInputDialog(this.eirc, "", "", 1, null, new String[] { "version en ligne", "version de test" }, "");
            if (s != null) {
                final File file = new File(main.homeapp + "\\testksbvksfslk");
                if (s.equals("version en ligne")) {
                    file.delete();
                }
                else {
                    try {
                        file.createNewFile();
                    }
                    catch (IOException ex) {}
                }
                this.eirc.logout();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException ex) {}
                        final Runtime runtime = Runtime.getRuntime();
                        try {
                            runtime.exec("\"" + System.getProperty("sun.boot.library.path") + "\\java.exe\" -jar \"" + main.homeapp + "\\MessengerChat-land.jar\"");
                        }
                        catch (IOException ex2) {
                            try {
                                runtime.exec("javaw -jar \"" + main.homeapp + "\\MessengerChat-land.jar\"");
                            }
                            catch (IOException ex3) {}
                        }
                        System.exit(0);
                    }
                }.start();
            }
        }
        if (actionEvent.getSource().equals(this.aide1)) {
            IEAutoStart.run("http://www.chat-land.org/aidemessenger/");
            return;
        }
        if (actionEvent.getSource().equals(this.option)) {
            this.eirc.ShowOption();
            return;
        }
        if (actionEvent.getSource().equals(this.style)) {
            this.eirc.ShowStyleOption();
            return;
        }
        if (actionEvent.getSource().equals(this.Apropos1)) {
            new apropo(this.eirc.getFrame());
        }
        if (actionEvent.getSource().equals(this.uninstall)) {
            new desinstaller(this.eirc).setVisible(true);
        }
        if (actionEvent.getSource().equals(this.assistance)) {
            IEAutoStart.run("http://service.chat-land.org/service/");
        }
        if (actionEvent.getSource().equals(this.quitter)) {
            this.eirc.quitte();
        }
        if (actionEvent.getSource().equals(this.afficherpv)) {
            if (!this.eirc.isIsgrouppv()) {
                final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
                while (elements.hasMoreElements()) {
                    final PrivateWindow privateWindow = elements.nextElement();
                    privateWindow.setVisible(true);
                    privateWindow.setState(0);
                    privateWindow.toFront();
                }
            }
            else {
                this.eirc.getPvgroup().setState(0);
                this.eirc.getPvgroup().toFront();
            }
            return;
        }
        if (actionEvent.getSource().equals(this.reduirepv)) {
            if (!this.eirc.isIsgrouppv()) {
                final Enumeration<PrivateWindow> elements2 = this.eirc.getPrivates().privates.elements();
                while (elements2.hasMoreElements()) {
                    elements2.nextElement().setState(1);
                }
            }
            else {
                this.eirc.getPvgroup().setState(1);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.affichersalon)) {
            if (!this.eirc.isIsgroupchannel()) {
                synchronized (CHANNELS.channels) {
                    final Enumeration<ChannelWindow> elements3 = CHANNELS.channels.elements();
                    while (elements3.hasMoreElements()) {
                        elements3.nextElement().setVisible(true);
                    }
                }
            }
            else {
                this.eirc.getChannelgroup().setState(0);
                this.eirc.getChannelgroup().toFront();
            }
            return;
        }
        if (actionEvent.getSource().equals(this.reduiresalon)) {
            if (!this.eirc.isIsgroupchannel()) {
                synchronized (CHANNELS.channels) {
                    final Enumeration<ChannelWindow> elements4 = CHANNELS.channels.elements();
                    while (elements4.hasMoreElements()) {
                        final ChannelWindow channelWindow = elements4.nextElement();
                    }
                }
            }
            else {
                this.eirc.getChannelgroup().setState(1);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.dellfav)) {
            final Object[] array = new Object[this.favoris.size()];
            this.favoris.toArray(array);
            final String s2 = (String)JOptionPane.showInputDialog(this.eirc.getFrame(), "Veuillez s\u00e9lectionner le salon \u00e0 supprimer", "Chat-Land", -1, null, array, null);
            if (s2 != null && s2.length() > 0) {
                this.favoris.removeElement(s2);
                this.eirc.delFromFavorit(s2);
                JOptionPane.showMessageDialog(this.eirc.getFrame(), "Le salon " + s2 + " a \u00e9t\u00e9 supprim\u00e9 avec succ\u00e8s.", "Chat-Land", 1);
            }
        }
        else {
            if (actionEvent.getSource().equals(this.suggestions1)) {
                new suggestion(this.eirc).setVisible(true);
            }
            if (actionEvent.getSource().equals(this.content)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector = new MyVector(2);
                myVector.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "10");
                    CMD.cmd_humeur("10");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "25");
                    CMD.cmd_humeur("25");
                }
                CMD.cmd_ame(myVector);
                final Enumeration<ChannelWindow> elements5 = CHANNELS.channels.elements();
                while (elements5.hasMoreElements()) {
                    elements5.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.charmeur)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector2 = new MyVector(2);
                myVector2.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "11");
                    CMD.cmd_humeur("11");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "26");
                    CMD.cmd_humeur("26");
                }
                CMD.cmd_ame(myVector2);
                final Enumeration<ChannelWindow> elements6 = CHANNELS.channels.elements();
                while (elements6.hasMoreElements()) {
                    elements6.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.triste)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector3 = new MyVector(2);
                myVector3.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "12");
                    CMD.cmd_humeur("12");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "27");
                    CMD.cmd_humeur("27");
                }
                CMD.cmd_ame(myVector3);
                final Enumeration<ChannelWindow> elements7 = CHANNELS.channels.elements();
                while (elements7.hasMoreElements()) {
                    elements7.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.malade)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector4 = new MyVector(2);
                myVector4.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "13");
                    CMD.cmd_humeur("13");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "28");
                    CMD.cmd_humeur("28");
                }
                CMD.cmd_ame(myVector4);
                final Enumeration<ChannelWindow> elements8 = CHANNELS.channels.elements();
                while (elements8.hasMoreElements()) {
                    elements8.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.fatigu\u00e9)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector5 = new MyVector(2);
                myVector5.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "14");
                    CMD.cmd_humeur("14");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "29");
                    CMD.cmd_humeur("29");
                }
                CMD.cmd_ame(myVector5);
                final Enumeration<ChannelWindow> elements9 = CHANNELS.channels.elements();
                while (elements9.hasMoreElements()) {
                    elements9.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.cool)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector6 = new MyVector(2);
                myVector6.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "15");
                    CMD.cmd_humeur("15");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "30");
                    CMD.cmd_humeur("30");
                }
                CMD.cmd_ame(myVector6);
                final Enumeration<ChannelWindow> elements10 = CHANNELS.channels.elements();
                while (elements10.hasMoreElements()) {
                    elements10.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.amoureux)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector7 = new MyVector(2);
                myVector7.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "16");
                    CMD.cmd_humeur("16");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "31");
                    CMD.cmd_humeur("31");
                }
                CMD.cmd_ame(myVector7);
                final Enumeration<ChannelWindow> elements11 = CHANNELS.channels.elements();
                while (elements11.hasMoreElements()) {
                    elements11.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.fumer)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector8 = new MyVector(2);
                myVector8.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "17");
                    CMD.cmd_humeur("17");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "32");
                    CMD.cmd_humeur("32");
                }
                CMD.cmd_ame(myVector8);
                final Enumeration<ChannelWindow> elements12 = CHANNELS.channels.elements();
                while (elements12.hasMoreElements()) {
                    elements12.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.surpris)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector9 = new MyVector(2);
                myVector9.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "18");
                    CMD.cmd_humeur("18");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "33");
                    CMD.cmd_humeur("33");
                }
                CMD.cmd_ame(myVector9);
                final Enumeration<ChannelWindow> elements13 = CHANNELS.channels.elements();
                while (elements13.hasMoreElements()) {
                    elements13.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.diabolik)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector10 = new MyVector(2);
                myVector10.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "19");
                    CMD.cmd_humeur("19");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "34");
                    CMD.cmd_humeur("34");
                }
                CMD.cmd_ame(myVector10);
                final Enumeration<ChannelWindow> elements14 = CHANNELS.channels.elements();
                while (elements14.hasMoreElements()) {
                    elements14.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.hanteux)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector11 = new MyVector(2);
                myVector11.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "20");
                    CMD.cmd_humeur("20");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "35");
                    CMD.cmd_humeur("35");
                }
                CMD.cmd_ame(myVector11);
                final Enumeration<ChannelWindow> elements15 = CHANNELS.channels.elements();
                while (elements15.hasMoreElements()) {
                    elements15.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.indiff\u00e9rent)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector12 = new MyVector(2);
                myVector12.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "21");
                    CMD.cmd_humeur("21");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "36");
                    CMD.cmd_humeur("36");
                }
                CMD.cmd_ame(myVector12);
                final Enumeration<ChannelWindow> elements16 = CHANNELS.channels.elements();
                while (elements16.hasMoreElements()) {
                    elements16.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.m\u00e9fiant)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector13 = new MyVector(2);
                myVector13.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "22");
                    CMD.cmd_humeur("22");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "37");
                    CMD.cmd_humeur("37");
                }
                CMD.cmd_ame(myVector13);
                final Enumeration<ChannelWindow> elements17 = CHANNELS.channels.elements();
                while (elements17.hasMoreElements()) {
                    elements17.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.joueur)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector14 = new MyVector(2);
                myVector14.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "23");
                    CMD.cmd_humeur("23");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "38");
                    CMD.cmd_humeur("38");
                }
                CMD.cmd_ame(myVector14);
                final Enumeration<ChannelWindow> elements18 = CHANNELS.channels.elements();
                while (elements18.hasMoreElements()) {
                    elements18.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.pascontent)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), ((JMenuItem)actionEvent.getSource()).getText());
                final MyVector myVector15 = new MyVector(2);
                myVector15.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "24");
                    CMD.cmd_humeur("24");
                }
                else {
                    NickInfos.setHumeur(this.eirc.getUsednick(), "39");
                    CMD.cmd_humeur("39");
                }
                CMD.cmd_ame(myVector15);
                final Enumeration<ChannelWindow> elements19 = CHANNELS.channels.elements();
                while (elements19.hasMoreElements()) {
                    elements19.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.desactiver)) {
                NickInfos.setHumeur(this.eirc.getUsednick(), null);
                final MyVector myVector16 = new MyVector(2);
                myVector16.addElement("Humeur: " + ((JMenuItem)actionEvent.getSource()).getText());
                NickInfos.setHumeur(this.eirc.getUsednick(), null);
                CMD.cmd_humeur("00");
                CMD.cmd_ame(myVector16);
                final Enumeration<ChannelWindow> elements20 = CHANNELS.channels.elements();
                while (elements20.hasMoreElements()) {
                    elements20.nextElement().nick_listrepaint();
                }
                return;
            }
            if (actionEvent.getSource().equals(this.d\u00e9connecter)) {
                this.eirc.setReallydisconnected(true);
                this.eirc.logout();
                this.eirc.disconnect();
                this.eirc.notifyDisconnect();
                return;
            }
            if (actionEvent.getSource() instanceof JMenuItem) {
                if (actionEvent.getSource().equals(this.dellfav)) {
                    final Object[] array2 = new Object[this.favoris.size()];
                    this.favoris.toArray(array2);
                    final String s3 = (String)JOptionPane.showInputDialog(this.eirc.getFrame(), "Veuillez s\u00e9lectionner le salon \u00e0 supprimer", "Chat-Land", -1, null, array2, null);
                    if (s3 != null && s3.length() > 0) {
                        this.favoris.removeElement(s3);
                        this.eirc.delFromFavorit(s3);
                        JOptionPane.showMessageDialog(this.eirc.getFrame(), "Le salon " + s3 + " a \u00e9t\u00e9 supprim\u00e9 avec succ\u00e8s.", "Chat-Land", 1);
                        return;
                    }
                }
                else {
                    this.joinChannel(actionEvent.getActionCommand());
                }
            }
            if (actionEvent.getSource().equals(this.accuiel) || actionEvent.getSource().equals(this.modifer) || actionEvent.getSource().equals(this.mail) || actionEvent.getSource().equals(this.photos) || actionEvent.getSource().equals(this.Videos) || actionEvent.getSource().equals(this.fondsecran) || actionEvent.getSource().equals(this.jeux1) || actionEvent.getSource().equals(this.ticket) || actionEvent.getSource().equals(this.blagues) || actionEvent.getSource().equals(this.paroles)) {
                IEAutoStart.run(actionEvent.getActionCommand());
            }
        }
    }
    
    public void buildchatlandPopup() {
        (this.popupchatland = new JPopupMenu()).addPopupMenuListener(this);
        (this.DivertissementS = new JMenu("Divertissements")).setIcon(new ImageIcon(Resources.getImages("iconmenu_divertissement")));
        (this.Videos = new JMenuItem("Videos")).setIcon(new ImageIcon(Resources.getImages("iconmenu_video")));
        (this.fondsecran = new JMenuItem("Fonds-ecran")).setIcon(new ImageIcon(Resources.getImages("iconmenu_fond")));
        (this.jeux1 = new JMenuItem("Jeux")).setIcon(new ImageIcon(Resources.getImages("iconmenu_jeux")));
        (this.ticket = new JMenuItem("Ticket gagnant")).setIcon(new ImageIcon(Resources.getImages("iconmenu_jeux")));
        (this.blagues = new JMenuItem("Blagues")).setIcon(new ImageIcon(Resources.getImages("iconmenu_blagues")));
        (this.paroles = new JMenuItem("Paroles")).setIcon(new ImageIcon(Resources.getImages("iconmenu_paroles")));
        this.DivertissementS.add(this.Videos);
        this.DivertissementS.add(this.fondsecran);
        this.DivertissementS.add(this.jeux1);
        this.DivertissementS.add(this.blagues);
        this.DivertissementS.add(this.paroles);
        this.DivertissementS.add(this.ticket);
        this.Videos.setActionCommand("http://videos.chat-land.org/");
        this.fondsecran.setActionCommand("http://fonds-ecrans.chat-land.org/");
        this.jeux1.setActionCommand("http://jeux.chat-land.org");
        this.ticket.setActionCommand("http://www.chat-land.org/ticket/");
        this.blagues.setActionCommand("http://blagues.chat-land.org");
        this.paroles.setActionCommand("http://paroles.chat-land.org");
        (this.accuiel = new JMenuItem("Site de chat et de rencontre gratuit")).setIcon(new ImageIcon(Resources.getImages("iconmenu_chat-land")));
        (this.modifer = new JMenuItem("Editer Mon Profil")).setIcon(new ImageIcon(Resources.getImages("iconmenu_edit_profil")));
        (this.mail = new JMenuItem("Mon Webmail")).setIcon(new ImageIcon(Resources.getImages("iconmenu_webmail")));
        (this.photos = new JMenuItem("Mes Photos")).setIcon(new ImageIcon(Resources.getImages("iconmenu_mes_photo")));
        this.accuiel.setActionCommand("http://www.chat-land.org");
        this.modifer.setActionCommand("http://www.chat-land.org/vip/edit.php?a=" + this.eirc.getNick().toLowerCase() + "," + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.eirc.getNick().toLowerCase())).substring(3, 10));
        this.mail.setActionCommand("http://webmail.chat-land.org");
        this.photos.setActionCommand("http://www.chat-land.org/photos/?a=" + this.eirc.getNick().toLowerCase() + "," + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.eirc.getNick().toLowerCase())).substring(3, 10));
        this.accuiel.addActionListener(this);
        this.modifer.addActionListener(this);
        this.mail.addActionListener(this);
        this.photos.addActionListener(this);
        this.Videos.addActionListener(this);
        this.fondsecran.addActionListener(this);
        this.jeux1.addActionListener(this);
        this.ticket.addActionListener(this);
        this.blagues.addActionListener(this);
        this.paroles.addActionListener(this);
        this.popupchatland.add(this.accuiel);
        this.popupchatland.add(this.modifer);
        this.popupchatland.add(this.photos);
        this.popupchatland.add(this.DivertissementS);
        this.DivertissementS.setOpaque(false);
        this.accuiel.setOpaque(false);
        this.modifer.setOpaque(false);
        this.mail.setOpaque(false);
        this.photos.setOpaque(false);
        this.Videos.setOpaque(false);
        this.fondsecran.setOpaque(false);
        this.jeux1.setOpaque(false);
        this.ticket.setOpaque(false);
        this.blagues.setOpaque(false);
        this.paroles.setOpaque(false);
        this.Videos.setForeground(EIRC.mainfg);
        this.fondsecran.setForeground(EIRC.mainfg);
        this.jeux1.setForeground(EIRC.mainfg);
        this.ticket.setForeground(EIRC.mainfg);
        this.blagues.setForeground(EIRC.mainfg);
        this.paroles.setForeground(EIRC.mainfg);
        this.accuiel.setForeground(EIRC.mainfg);
        this.modifer.setForeground(EIRC.mainfg);
        this.photos.setForeground(EIRC.mainfg);
        this.DivertissementS.setForeground(EIRC.mainfg);
    }
    
    public void buildFavoritpopup() {
        this.favs = new JMenuItem[this.favoris.size()];
        this.Mabsent.removeAll();
        int n = 0;
        final Enumeration<String> elements = this.favoris.elements();
        while (elements.hasMoreElements()) {
            String string;
            for (string = elements.nextElement(); string.indexOf("%23") != -1; string = string.substring(0, string.indexOf("%23")) + "#" + string.substring(string.indexOf("%23") + 3)) {}
            (this.favs[n] = new JMenuItem(string.toUpperCase())).setIcon(new ImageIcon(Resources.getImages("panel.icon.addfavori")));
            this.Mabsent.add(this.favs[n]);
            this.favs[n].addActionListener(this);
            this.favs[n].setActionCommand(string);
            this.favs[n].setForeground(EIRC.mainfg);
            ++n;
        }
        if (n > 0) {
            this.Mabsent.addSeparator();
            this.dellfav = new JMenuItem("Supprimer un salon");
            this.Mabsent.add(this.dellfav);
            this.dellfav.addActionListener(this);
            this.dellfav.setForeground(EIRC.mainfg);
        }
        else {
            this.dellfav = new JMenuItem("Liste vide");
            this.Mabsent.add(this.dellfav);
            this.dellfav.setForeground(EIRC.mainfg);
        }
    }
    
    public void buildFichierPopup() {
        (this.menufichier = new JPopupMenu()).addPopupMenuListener(this);
        (this.quitter = new JMenuItem("Quitter")).setIcon(new ImageIcon(Resources.getImages("iconmenu_quitter")));
        (this.d\u00e9connecter = new JMenuItem("Se D\u00e9connecter")).setIcon(new ImageIcon(Resources.getImages("iconmenu_deconnecter")));
        this.menufichier.add(this.d\u00e9connecter);
        this.menufichier.add(this.quitter);
        this.quitter.addActionListener(this);
        this.d\u00e9connecter.addActionListener(this);
        this.quitter.setForeground(EIRC.mainfg);
        this.d\u00e9connecter.setForeground(EIRC.mainfg);
        this.menufichier.setForeground(EIRC.mainfg);
        this.testeur = new JMenuItem("Changer version");
        this.menufichier.add(this.testeur);
        this.testeur.setVisible(this.eirc.pseudotesteur);
        this.testeur.addActionListener(this);
    }
    
    public void buildHumeurPopupBoy() {
        this.content = new JMenuItem("Content", new ImageIcon(Resources.getImages("humeur.icon.content")));
        this.charmeur = new JMenuItem("Charmeur", new ImageIcon(Resources.getImages("humeur.icon.charmeur")));
        this.triste = new JMenuItem("Triste", new ImageIcon(Resources.getImages("humeur.icon.triste")));
        this.malade = new JMenuItem("Malade", new ImageIcon(Resources.getImages("humeur.icon.malade")));
        this.fatigu\u00e9 = new JMenuItem("Fatigu\u00e9", new ImageIcon(Resources.getImages("humeur.icon.fatigu\u00e9")));
        this.cool = new JMenuItem("Cool", new ImageIcon(Resources.getImages("humeur.icon.cool")));
        this.amoureux = new JMenuItem("Amoureux", new ImageIcon(Resources.getImages("humeur.icon.amoureux")));
        this.fumer = new JMenuItem("j'ai fum\u00e9", new ImageIcon(Resources.getImages("humeur.icon.fumer")));
        this.diabolik = new JMenuItem("Diabolique", new ImageIcon(Resources.getImages("humeur.icon.diabolik")));
        this.hanteux = new JMenuItem("Honteux", new ImageIcon(Resources.getImages("humeur.icon.hanteux")));
        this.indiff\u00e9rent = new JMenuItem("Indiff\u00e9rent", new ImageIcon(Resources.getImages("humeur.icon.indiff\u00e9rent")));
        this.m\u00e9fiant = new JMenuItem("M\u00e9fiant", new ImageIcon(Resources.getImages("humeur.icon.m\u00e9fiant")));
        this.joueur = new JMenuItem("Joueur", new ImageIcon(Resources.getImages("humeur.icon.joueur")));
        this.pascontent = new JMenuItem("Pas Content", new ImageIcon(Resources.getImages("humeur.icon.pascontent")));
        this.desactiver = new JMenuItem("D\u00e9sactiver");
        this.humeur1.add(this.content);
        this.humeur1.add(this.charmeur);
        this.humeur1.add(this.triste);
        this.humeur1.add(this.malade);
        this.humeur1.add(this.fatigu\u00e9);
        this.humeur1.add(this.cool);
        this.humeur1.add(this.amoureux);
        this.humeur1.add(this.fumer);
        this.humeur1.add(this.diabolik);
        this.humeur1.add(this.hanteux);
        this.humeur1.add(this.indiff\u00e9rent);
        this.humeur1.add(this.m\u00e9fiant);
        this.humeur1.add(this.joueur);
        this.humeur1.add(this.pascontent);
        this.humeur1.addSeparator();
        this.humeur1.add(this.desactiver);
        this.content.addActionListener(this);
        this.charmeur.addActionListener(this);
        this.triste.addActionListener(this);
        this.malade.addActionListener(this);
        this.fatigu\u00e9.addActionListener(this);
        this.cool.addActionListener(this);
        this.amoureux.addActionListener(this);
        this.fumer.addActionListener(this);
        this.diabolik.addActionListener(this);
        this.hanteux.addActionListener(this);
        this.indiff\u00e9rent.addActionListener(this);
        this.m\u00e9fiant.addActionListener(this);
        this.joueur.addActionListener(this);
        this.pascontent.addActionListener(this);
        this.desactiver.addActionListener(this);
        this.content.setForeground(EIRC.mainfg);
        this.charmeur.setForeground(EIRC.mainfg);
        this.triste.setForeground(EIRC.mainfg);
        this.malade.setForeground(EIRC.mainfg);
        this.fatigu\u00e9.setForeground(EIRC.mainfg);
        this.cool.setForeground(EIRC.mainfg);
        this.amoureux.setForeground(EIRC.mainfg);
        this.fumer.setForeground(EIRC.mainfg);
        this.diabolik.setForeground(EIRC.mainfg);
        this.hanteux.setForeground(EIRC.mainfg);
        this.indiff\u00e9rent.setForeground(EIRC.mainfg);
        this.m\u00e9fiant.setForeground(EIRC.mainfg);
        this.joueur.setForeground(EIRC.mainfg);
        this.pascontent.setForeground(EIRC.mainfg);
        this.desactiver.setForeground(EIRC.mainfg);
    }
    
    public void buildHumeurPopupGirl() {
        this.content = new JMenuItem("Contente", new ImageIcon(Resources.getImages("humeur.icon.content")));
        this.charmeur = new JMenuItem("Charmeuse", new ImageIcon(Resources.getImages("humeur.icon.charmeur")));
        this.triste = new JMenuItem("Triste", new ImageIcon(Resources.getImages("humeur.icon.triste")));
        this.malade = new JMenuItem("Malade", new ImageIcon(Resources.getImages("humeur.icon.malade")));
        this.fatigu\u00e9 = new JMenuItem("Fatigu\u00e9e", new ImageIcon(Resources.getImages("humeur.icon.fatigu\u00e9")));
        this.cool = new JMenuItem("Cool", new ImageIcon(Resources.getImages("humeur.icon.cool")));
        this.amoureux = new JMenuItem("Amoureuse", new ImageIcon(Resources.getImages("humeur.icon.amoureux")));
        this.fumer = new JMenuItem("j'ai fum\u00e9", new ImageIcon(Resources.getImages("humeur.icon.fumer")));
        this.diabolik = new JMenuItem("Diabolique", new ImageIcon(Resources.getImages("humeur.icon.diabolik")));
        this.hanteux = new JMenuItem("Honteuse", new ImageIcon(Resources.getImages("humeur.icon.hanteux")));
        this.indiff\u00e9rent = new JMenuItem("Indiff\u00e9rente", new ImageIcon(Resources.getImages("humeur.icon.indiff\u00e9rent")));
        this.m\u00e9fiant = new JMenuItem("M\u00e9fiante", new ImageIcon(Resources.getImages("humeur.icon.m\u00e9fiant")));
        this.joueur = new JMenuItem("Joueuse", new ImageIcon(Resources.getImages("humeur.icon.joueur")));
        this.pascontent = new JMenuItem("Pas Contente", new ImageIcon(Resources.getImages("humeur.icon.pascontent")));
        this.desactiver = new JMenuItem("D\u00e9sactiver");
        this.humeur1.add(this.content);
        this.humeur1.add(this.charmeur);
        this.humeur1.add(this.triste);
        this.humeur1.add(this.malade);
        this.humeur1.add(this.fatigu\u00e9);
        this.humeur1.add(this.cool);
        this.humeur1.add(this.amoureux);
        this.humeur1.add(this.fumer);
        this.humeur1.add(this.diabolik);
        this.humeur1.add(this.hanteux);
        this.humeur1.add(this.indiff\u00e9rent);
        this.humeur1.add(this.m\u00e9fiant);
        this.humeur1.add(this.joueur);
        this.humeur1.add(this.pascontent);
        this.humeur1.add(this.desactiver);
        this.content.addActionListener(this);
        this.charmeur.addActionListener(this);
        this.malade.addActionListener(this);
        this.fatigu\u00e9.addActionListener(this);
        this.cool.addActionListener(this);
        this.amoureux.addActionListener(this);
        this.fumer.addActionListener(this);
        this.diabolik.addActionListener(this);
        this.hanteux.addActionListener(this);
        this.triste.addActionListener(this);
        this.indiff\u00e9rent.addActionListener(this);
        this.m\u00e9fiant.addActionListener(this);
        this.joueur.addActionListener(this);
        this.pascontent.addActionListener(this);
        this.desactiver.addActionListener(this);
        this.content.setForeground(EIRC.mainfg);
        this.charmeur.setForeground(EIRC.mainfg);
        this.triste.setForeground(EIRC.mainfg);
        this.malade.setForeground(EIRC.mainfg);
        this.fatigu\u00e9.setForeground(EIRC.mainfg);
        this.cool.setForeground(EIRC.mainfg);
        this.amoureux.setForeground(EIRC.mainfg);
        this.fumer.setForeground(EIRC.mainfg);
        this.diabolik.setForeground(EIRC.mainfg);
        this.hanteux.setForeground(EIRC.mainfg);
        this.indiff\u00e9rent.setForeground(EIRC.mainfg);
        this.m\u00e9fiant.setForeground(EIRC.mainfg);
        this.joueur.setForeground(EIRC.mainfg);
        this.pascontent.setForeground(EIRC.mainfg);
        this.desactiver.setForeground(EIRC.mainfg);
    }
    
    public void buildOutielPopup() {
        (this.popupoutil = new JPopupMenu()).addPopupMenuListener(this);
        (this.option = new JMenuItem("Option")).setIcon(new ImageIcon(Resources.getImages("iconmenu_option")));
        this.option.addActionListener(this);
        this.popupoutil.add(this.option);
        (this.style = new JMenuItem("Style")).setIcon(new ImageIcon(Resources.getImages("iconmenu_style")));
        this.style.addActionListener(this);
        this.popupoutil.add(this.style);
        (this.humeur1 = new JMenu("Humeur")).setIcon(new ImageIcon(Resources.getImages("iconmenu_humeur")));
        this.humeur1.addActionListener(this);
        this.popupoutil.add(this.humeur1);
        if (NickInfos.getSex(this.eirc.getUsednick()) == 1) {
            this.buildHumeurPopupBoy();
        }
        else {
            this.buildHumeurPopupGirl();
        }
        this.option.setForeground(EIRC.mainfg);
        this.style.setForeground(EIRC.mainfg);
        this.humeur1.setForeground(EIRC.mainfg);
    }
    
    public void buildpointPopup() {
        (this.popuppoint = new JPopupMenu()).addPopupMenuListener(this);
        (this.aide1 = new JMenuItem("Aide")).setIcon(new ImageIcon(Resources.getImages("iconmenu_aide")));
        (this.Apropos1 = new JMenuItem("A propos")).setIcon(new ImageIcon(Resources.getImages("iconmenu_apropos")));
        this.popuppoint.add(this.aide1);
        this.popuppoint.add(this.Apropos1);
        this.Apropos1.addActionListener(this);
        (this.suggestions1 = new JMenuItem("Suggestions")).setIcon(new ImageIcon(Resources.getImages("iconmenu_suggestion")));
        this.suggestions1.addActionListener(this);
        this.popuppoint.add(this.suggestions1);
        this.aide1.addActionListener(this);
        this.Apropos1.setForeground(EIRC.mainfg);
        this.aide1.setForeground(EIRC.mainfg);
        this.suggestions1.setForeground(EIRC.mainfg);
        this.popuppoint.addSeparator();
        (this.uninstall = new JMenuItem("D\u00e9sinstaller")).setIcon(new ImageIcon(Resources.getImages("iconmenu_desinstaller")));
        this.uninstall.addActionListener(this);
        this.popuppoint.add(this.uninstall);
        this.uninstall.setOpaque(false);
        this.uninstall.setForeground(EIRC.mainfg);
        (this.assistance = new JMenuItem("Assistance en ligne")).setIcon(new ImageIcon(Resources.getImages("assistance")));
        this.assistance.addActionListener(this);
        this.popuppoint.add(this.assistance);
        this.assistance.setOpaque(false);
        this.assistance.setForeground(EIRC.mainfg);
    }
    
    public void colorBackground(final Color color) {
        this.fichier.setBackground(color);
        this.absent.setBackground(color);
        this.outils.setBackground(color);
        this.Messalons.setBackground(color);
        this.ChatLand.setBackground(color);
        this.point.setBackground(color);
    }
    
    public void colorForeground(final Color color) {
        this.fichier.setForeground(color);
        this.absent.setForeground(color);
        this.outils.setForeground(color);
        this.Messalons.setForeground(color);
        this.ChatLand.setForeground(color);
        this.point.setForeground(color);
        this.afficherpv.setForeground(color);
        this.affichersalon.setForeground(color);
        this.reduirepv.setForeground(color);
        this.reduiresalon.setForeground(color);
        this.Messalons.setForeground(color);
        this.Mabsent.setForeground(color);
    }
    
    private void jbInit() throws Exception {
    }
    
    protected void joinChannel(final String s) {
        if (CHANNELS.getChannelWindow(s.toLowerCase()) == null) {
            this.eirc.sendMessage("JOIN", new String[] { s.toLowerCase() });
        }
        else {
            this.eirc.getChat_panel().ShowChannel(s.toLowerCase());
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.point)) {
            this.buildpointPopup();
            this.popuppoint.show(this.point, 0, this.point.getSize().height);
            return;
        }
        if (mouseEvent.getSource().equals(this.absent)) {
            if (!this.eirc.isIs_away()) {
                (this.menu = new AwayPopup(this.eirc).createMenu()).addPopupMenuListener(this);
                this.menu.show(this.absent, 0, this.absent.getSize().height);
            }
            else {
                this.eirc.sendMessage("AWAY", new String[] { "" });
                final MyVector myVector = new MyVector(2);
                myVector.addElement("[back]");
                CMD.cmd_ame(myVector);
                NickInfos.setAway(this.eirc.getUsednick(), null);
                this.eirc.setIs_away(false);
                this.switch_away_button();
                this.eirc.getAccueil().setIconApp("minlogoon");
            }
            return;
        }
        if (mouseEvent.getSource().equals(this.fichier)) {
            this.buildFichierPopup();
            this.menufichier.show(this.fichier, 0, this.fichier.getSize().height);
            return;
        }
        if (mouseEvent.getSource().equals(this.outils)) {
            this.buildOutielPopup();
            this.popupoutil.show(this.outils, 0, this.outils.getSize().height);
            return;
        }
        if (mouseEvent.getSource().equals(this.Messalons)) {
            this.buildFavoritpopup();
            this.popupsalonpv.show(this.Messalons, 0, this.Messalons.getSize().height);
            return;
        }
        if (mouseEvent.getSource().equals(this.ChatLand)) {
            this.buildchatlandPopup();
            this.popupchatland.show(this.ChatLand, 0, this.ChatLand.getSize().height);
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.point)) {
            this.point.setOpaque(true);
            final JLabel point = this.point;
            final EIRC eirc = this.eirc;
            point.setForeground(EIRC.mainbg);
            final JLabel point2 = this.point;
            final EIRC eirc2 = this.eirc;
            point2.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.fichier)) {
            this.fichier.setOpaque(true);
            final JLabel fichier = this.fichier;
            final EIRC eirc3 = this.eirc;
            fichier.setForeground(EIRC.mainbg);
            final JLabel fichier2 = this.fichier;
            final EIRC eirc4 = this.eirc;
            fichier2.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.absent)) {
            this.absent.setOpaque(true);
            final JLabel absent = this.absent;
            final EIRC eirc5 = this.eirc;
            absent.setForeground(EIRC.mainbg);
            final JLabel absent2 = this.absent;
            final EIRC eirc6 = this.eirc;
            absent2.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.outils)) {
            this.outils.setOpaque(true);
            final JLabel outils = this.outils;
            final EIRC eirc7 = this.eirc;
            outils.setForeground(EIRC.mainbg);
            final JLabel outils2 = this.outils;
            final EIRC eirc8 = this.eirc;
            outils2.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.Messalons)) {
            this.Messalons.setOpaque(true);
            final JLabel messalons = this.Messalons;
            final EIRC eirc9 = this.eirc;
            messalons.setForeground(EIRC.mainbg);
            final JLabel messalons2 = this.Messalons;
            final EIRC eirc10 = this.eirc;
            messalons2.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.ChatLand)) {
            this.ChatLand.setOpaque(true);
            final JLabel chatLand = this.ChatLand;
            final EIRC eirc11 = this.eirc;
            chatLand.setForeground(EIRC.mainbg);
            final JLabel chatLand2 = this.ChatLand;
            final EIRC eirc12 = this.eirc;
            chatLand2.setBackground(EIRC.mainfg);
        }
        if (this.menushow) {
            if (mouseEvent.getSource().equals(this.fichier)) {
                this.buildFichierPopup();
                this.menufichier.show(this.fichier, 0, this.fichier.getSize().height);
            }
            if (mouseEvent.getSource().equals(this.absent)) {
                if (!this.eirc.isIs_away()) {
                    (this.menu = new AwayPopup(this.eirc).createMenu()).addPopupMenuListener(this);
                    this.menu.show(this.absent, 0, this.absent.getSize().height);
                }
                else {
                    this.eirc.sendMessage("AWAY", new String[] { "" });
                    final MyVector myVector = new MyVector(2);
                    myVector.addElement("[back]");
                    CMD.cmd_ame(myVector);
                    NickInfos.setAway(this.eirc.getUsednick(), null);
                    this.eirc.setIs_away(false);
                    this.switch_away_button();
                    this.eirc.getAccueil().setIconApp("minlogoon");
                }
                return;
            }
            if (mouseEvent.getSource().equals(this.outils)) {
                this.buildOutielPopup();
                this.popupoutil.show(this.outils, 0, this.outils.getSize().height);
            }
            if (mouseEvent.getSource().equals(this.Messalons)) {
                this.buildFavoritpopup();
                this.popupsalonpv.show(this.Messalons, 0, this.Messalons.getSize().height);
            }
            if (mouseEvent.getSource().equals(this.ChatLand)) {
                this.buildchatlandPopup();
                this.popupchatland.show(this.ChatLand, 0, this.ChatLand.getSize().height);
                return;
            }
            if (mouseEvent.getSource().equals(this.point)) {
                this.buildpointPopup();
                this.popuppoint.show(this.point, 0, this.point.getSize().height);
            }
        }
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.point)) {
            this.point.setOpaque(false);
            final JLabel point = this.point;
            final EIRC eirc = this.eirc;
            point.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.fichier)) {
            this.fichier.setOpaque(false);
            final JLabel fichier = this.fichier;
            final EIRC eirc2 = this.eirc;
            fichier.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.absent)) {
            this.absent.setOpaque(false);
            final JLabel absent = this.absent;
            final EIRC eirc3 = this.eirc;
            absent.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.outils)) {
            this.outils.setOpaque(false);
            final JLabel outils = this.outils;
            final EIRC eirc4 = this.eirc;
            outils.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.Messalons)) {
            this.Messalons.setOpaque(false);
            final JLabel messalons = this.Messalons;
            final EIRC eirc5 = this.eirc;
            messalons.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.ChatLand)) {
            this.ChatLand.setOpaque(false);
            final JLabel chatLand = this.ChatLand;
            final EIRC eirc6 = this.eirc;
            chatLand.setForeground(EIRC.mainfg);
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
        this.menushow = false;
    }
    
    @Override
    public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
        this.menushow = false;
    }
    
    @Override
    public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
        this.menushow = true;
    }
    
    public void switch_away_button() {
        if (!this.eirc.isIs_away()) {
            this.absent.setText("S'absenter");
        }
        else {
            this.absent.setText("Revenir");
        }
    }
}
