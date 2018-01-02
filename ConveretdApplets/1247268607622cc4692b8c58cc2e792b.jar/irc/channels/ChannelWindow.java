// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import com.eaio.nativecall.IntCall;
import com.eaio.util.lang.NativeLoader;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import irc.com.nick.NickInfos;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Cursor;
import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import irc.main;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import irc.channels.textarea.NewTextMotionListener;
import irc.channels.textarea.NewTextClickListener;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import irc.managers.avatar;
import javax.swing.ImageIcon;
import javax.swing.text.StyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JTabbedPane;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.text.Collator;
import irc.com.utils.RFC1459;
import java.awt.event.AdjustmentEvent;
import irc.com.User;
import irc.com.Modes;
import java.util.Enumeration;
import irc.managers.CHANNELS;
import irc.com.utils.IEAutoStart;
import javax.swing.Icon;
import irc.managers.Resources;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Vector;
import javax.swing.text.Document;
import java.awt.Dimension;
import javax.swing.text.SimpleAttributeSet;
import irc.com.messages.ChannelMessage;
import java.awt.Point;
import java.util.Hashtable;
import irc.channels.textarea.NewTextDocument;
import irc.channels.nicklist.NickListVector;
import irc.channels.nicklist.NickItem;
import irc.com.channelmodes.ChannelAdmin;
import javax.swing.text.MutableAttributeSet;
import irc.channels.nicklist.NickList;
import javax.swing.JLabel;
import java.awt.Container;
import irc.channels.components.PopupInfos;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import irc.channels.components.TextFieldHistory;
import irc.channels.components.MyJButton;
import irc.channels.components.smileys.SmileysMenu;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import irc.EIRC;
import java.awt.event.MouseMotionListener;
import irc.channels.textarea.TextAreaEvent;
import java.awt.event.FocusListener;
import java.awt.event.AdjustmentListener;
import irc.channels.textarea.HyperlinkTextAreaEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import irc.channels.components.MyPvPanel;

public class ChannelWindow extends MyPvPanel implements ActionListener, KeyListener, MouseListener, HyperlinkTextAreaEvent, AdjustmentListener, FocusListener, TextAreaEvent, MouseMotionListener
{
    private EIRC eirc;
    private GridBagLayout gb;
    private GridBagConstraints gbc;
    private JToolBar tools;
    private JButton police;
    private JButton grouper;
    private JToggleButton sound;
    private JButton smileys;
    private SmileysMenu smileymenu;
    private JButton gomme;
    private JButton favorit;
    private JToggleButton interlignes;
    private MyJButton close;
    private TextFieldHistory entry;
    private MyJButton send;
    private MyJButton murmurer;
    private JButton sajoin;
    private JPopupMenu p_menu;
    private JMenu p_sort;
    private JMenu p_op;
    private JMenuItem p_openprivate;
    private JMenuItem p_openchan;
    private JMenuItem p_addfriend;
    private JMenuItem p_show_profile;
    private JMenuItem p_ignore;
    private JMenuItem p_send_kiss;
    private JMenuItem p_send_hi;
    private JMenuItem p_send_baffe;
    private JMenuItem p_sort_sexe;
    private JMenuItem p_sort_age;
    private JMenuItem p_sort_dep;
    private JMenuItem p_sort_nick;
    private JMenuItem p_op_kick;
    private JMenuItem p_op_kickban;
    private JMenuItem p_op_ban;
    private JMenuItem p_op_unban;
    private JMenuItem p_op_admin;
    private JMenuItem p_op_unadmin;
    private JMenuItem p_op_protect;
    private JMenuItem p_op_voice;
    private JMenuItem p_op_unvoice;
    private JMenuItem p_op_op;
    private JMenuItem p_op_deop;
    private JMenuItem p_op_hop;
    private JMenuItem p_op_dehop;
    private JScrollPane scrollpanel;
    private JTextPane textzone;
    private PopupInfos nickinfos;
    private Container channelpane;
    private JLabel titre;
    private NickList nick_list;
    private MutableAttributeSet attributSet;
    private int fgColor;
    private int bgColor;
    private ChannelAdmin chanadmin;
    private String complete_partial;
    private String complete_current;
    private NickItem nickpopap;
    private NickListVector nick_list_vect;
    private String topic;
    private String tag;
    private boolean is_op;
    private boolean is_hop;
    private boolean is_adm;
    private boolean is_own;
    private NewTextDocument doc;
    private boolean findechargement;
    private Hashtable users;
    private int modes_mask;
    private String key;
    private int limit;
    private boolean flote;
    private static final int TOPIC_MASK = 1;
    private static final int MODERATED_MASK = 2;
    private static final int SECRET_MASK = 4;
    private static final int INVITATION_MASK = 8;
    private static final int REGISTERED_MASK = 16;
    private static final int NONICK_MASK = 32;
    private static final int NONE = 6;
    private static final int TOPIC = 7;
    private static final int JOIN = 8;
    private static final int REFRESH = 9;
    private String title;
    Point loct;
    int oldvalue;
    ChannelMessage chanelmessage;
    int i;
    private JLabel listeausaj;
    
    public ChannelWindow(final EIRC eirc, final String tag) {
        this.attributSet = new SimpleAttributeSet();
        this.fgColor = 1;
        this.bgColor = 0;
        this.complete_current = "";
        this.nickpopap = null;
        this.findechargement = false;
        this.users = new Hashtable();
        this.key = "";
        this.limit = -1;
        this.flote = false;
        this.loct = null;
        this.oldvalue = 0;
        this.i = 0;
        this.eirc = eirc;
        this.tag = tag;
        this.setMinimumSize(new Dimension(500, 350));
        this.setMaximumSize(new Dimension(1500, 1500));
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.nick_list_vect = new NickListVector();
        (this.doc = new NewTextDocument(this.eirc, this)).setChanneltag(this.tag);
        this.textzone.setDocument(this.getDoc());
        this.textzone.setCaretPosition(this.textzone.getStyledDocument().getLength());
        this.getNickList().sort();
        this.nick_list.setNickList(this.getNickList());
        this.setTitle();
        if (EIRC.interlignes == 1.0f) {
            this.interlignes.setSelected(true);
        }
        else {
            this.interlignes.setSelected(false);
        }
        this.interlignes(EIRC.interlignes);
        this.favorit.setToolTipText("Ajouter " + this.getTag() + " aux favoris");
        this.textzone.setBackground(Color.white);
        this.couleur(this.eirc.getPolice().getCouleur1(), this.eirc.getPolice().getCouleur2(), this.eirc.getPolice().getFonte());
        this.entry.addFocusListener(this);
        this.titre.setForeground(Color.black);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        final Object source = actionEvent.getSource();
        if (actionEvent.getSource().equals(this.sajoin)) {
            final EIRC eirc = this.eirc;
            if (!EIRC.sajoin.contains(this.getTag().toLowerCase())) {
                final EIRC eirc2 = this.eirc;
                if (EIRC.sajoin.size() >= 5) {
                    JOptionPane.showMessageDialog(this.eirc.getFrame(), "Vous avez atteint 5 salons ouverts auto, veuillez supprimer un pour pouvoir ajouter ce salon.", "Chat-Land", 1);
                }
                else {
                    final EIRC eirc3 = this.eirc;
                    EIRC.sajoin.addElement(this.getTag().toLowerCase());
                    this.eirc.getRightpanel().chanListPanel.savesaj();
                    this.eirc.getRightpanel().chanListPanel.afficher();
                    JOptionPane.showMessageDialog(this.channelpane, "L'ajout de " + this.getTag() + " \u00e0 la liste des salons ouverts auto a \u00e9t\u00e9 effectu\u00e9 avec succ\u00e8s!", "Chat-Land", 1);
                }
            }
            else {
                final EIRC eirc4 = this.eirc;
                EIRC.sajoin.removeElement(this.getTag().toLowerCase());
                this.eirc.getRightpanel().chanListPanel.savesaj();
            }
            final EIRC eirc5 = this.eirc;
            if (EIRC.sajoin.contains(this.getTag().toLowerCase())) {
                this.sajoin.setText("Enlever le salon " + this.getTag() + " au d\u00e9marrage de messenger");
            }
            else {
                this.sajoin.setText("Ouvrir le salon " + this.getTag() + " au d\u00e9marrage de messenger");
            }
        }
        if (source.equals(this.murmurer)) {
            String s = this.entry.getText().trim();
            if (s.indexOf(">") == -1) {
                JOptionPane.showMessageDialog(this, "Pour murmurer \u00e0 une personne , vous devez cliquer sur son pseudo en premier lieu \n puis tapez votre message en suite cliquez sur le bouton murmurer .\nVotre message sera vue seulement a qui vous avez murmurer");
                return;
            }
            if (s.charAt(0) != '/') {
                final String substring = s.substring(0, s.indexOf(">"));
                String string = "";
                if (s.length() > 450) {
                    s = s.substring(0, 449);
                }
                if (this.eirc.isBooleangras()) {
                    string += '\u0002';
                }
                final String string2 = string + "\u0003" + this.fgColor + "," + this.bgColor + " ";
                while (s.startsWith(" ")) {
                    s = s.substring(1);
                }
                final String string3 = string2 + s;
                final String substring2 = string3.substring(string3.indexOf(">") + 1);
                if (this.eirc.checkUrl(s)) {
                    this.eirc.sendMessage("NOTICE", new String[] { substring, this.getTag() + " murmure \u00e0 " + substring + " : " + '\u000f' + substring2 });
                }
                this.printAction(" murmure \u00e0 " + substring + " : " + '\u000f' + substring2, this.eirc.getNick());
            }
            this.entry.setText("");
            this.entry.requestFocus();
        }
        else if (source.equals(this.entry) || source.equals(this.send)) {
            String s2 = this.entry.getText().trim();
            if (s2.length() <= 0) {
                return;
            }
            if (s2.charAt(0) == '/') {
                if (s2.trim().toLowerCase().startsWith("/nick ")) {
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().toLowerCase().startsWith("/q ")) {
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().startsWith("/poli ") && EIRC._is_anim) {
                    final String string4 = s2.trim().substring(s2.trim().indexOf(32) + 1) + "> " + Resources.getStringEirc("kick.poli");
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string4 });
                    this.getDoc().printPrivmsg(string4, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().startsWith("/flood ") && EIRC._is_anim) {
                    final String string5 = s2.trim().substring(s2.trim().indexOf(32) + 1) + "> " + Resources.getStringEirc("kick.flood");
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string5 });
                    this.getDoc().printPrivmsg(string5, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().startsWith("/comp ") && EIRC._is_anim) {
                    final String string6 = s2.trim().substring(s2.trim().indexOf(32) + 1) + "> " + Resources.getStringEirc("kick.comp");
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string6 });
                    this.getDoc().printPrivmsg(string6, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().startsWith("/sexe ") && EIRC._is_anim) {
                    final String string7 = s2.trim().substring(s2.trim().indexOf(32) + 1) + "> " + Resources.getStringEirc("kick.sex");
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string7 });
                    this.getDoc().printPrivmsg(string7, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                if (s2.trim().startsWith("/pub ") && EIRC._is_anim) {
                    final String string8 = s2.trim().substring(s2.trim().indexOf(32) + 1) + "> " + Resources.getStringEirc("kick.pub");
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string8 });
                    this.getDoc().printPrivmsg(string8, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                final String substring3 = s2.substring(1);
                if (substring3.trim().length() > 0) {
                    this.eirc.sendCommand(substring3, this.getDoc());
                }
            }
            else {
                if (s2.trim().startsWith("!")) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), s2.trim() });
                    this.getDoc().printPrivmsg(s2, this.eirc.getNick());
                    this.entry.setText("");
                    return;
                }
                String string9 = "";
                if (s2.length() > 450) {
                    s2 = s2.substring(0, 449);
                }
                if (this.eirc.isBooleangras()) {
                    string9 += '\u0002';
                }
                final String string10 = string9 + "\u0003" + this.fgColor + "," + this.bgColor + " ";
                while (s2.startsWith(" ")) {
                    s2 = s2.substring(1);
                }
                final String string11 = string10 + s2;
                if (this.eirc.checkUrl(s2)) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), string11 });
                }
                this.printPrivmsg(string11, this.eirc.getNick());
            }
            this.entry.setText("");
            this.entry.requestFocus();
        }
        else {
            if (source.equals(this.police)) {
                this.eirc.showPolice();
                this.entry.requestFocus();
                return;
            }
            if (source.equals(this.grouper)) {
                if (!this.eirc.isIsgroupchannel()) {
                    this.eirc.getChannelgroup().action();
                }
                else {
                    this.eirc.getChannelgroup().degroup();
                }
                return;
            }
            if (source.equals(this.sound)) {
                if (this.sound.isSelected()) {
                    this.sound.setIcon(EIRC.soundoff);
                    this.eirc.setSoundOff();
                }
                else {
                    this.sound.setIcon(EIRC.soundon);
                    this.eirc.setSoundOn();
                }
                return;
            }
            if (source.equals(this.gomme)) {
                this.textzone.setText("");
                return;
            }
            if (source.equals(this.favorit)) {
                if (this.eirc.isRegister()) {
                    if (this.eirc.addToFavorit(this.getTag())) {
                        JOptionPane.showMessageDialog(this.channelpane, "L'ajout de " + this.getTag() + " \u00e0 la liste des favoris a \u00e9t\u00e9 effectu\u00e9 avec succ\u00e8s!", "Chat-Land", 2);
                    }
                    else {
                        JOptionPane.showMessageDialog(this.channelpane, this.getTag() + " existe d\u00e9j\u00e0 dans votre liste", "Chat-Land", 2);
                    }
                }
                else {
                    final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                    if (JOptionPane.showOptionDialog(this.channelpane, "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                        IEAutoStart.run("http://vip.chat-land.org/");
                    }
                }
                return;
            }
            if (source.equals(this.interlignes)) {
                if (this.interlignes.isSelected()) {
                    synchronized (CHANNELS.channels) {
                        final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                        while (elements.hasMoreElements()) {
                            final ChannelWindow channelWindow = elements.nextElement();
                            channelWindow.interlignes.setSelected(true);
                            channelWindow.interlignes(1.0f);
                        }
                    }
                    EIRC.interlignes = 1.0f;
                }
                else {
                    EIRC.interlignes = 0.2f;
                    synchronized (CHANNELS.channels) {
                        final Enumeration<ChannelWindow> elements2 = CHANNELS.channels.elements();
                        while (elements2.hasMoreElements()) {
                            final ChannelWindow channelWindow2 = elements2.nextElement();
                            channelWindow2.interlignes.setSelected(false);
                            channelWindow2.interlignes(0.2f);
                        }
                    }
                }
                return;
            }
            if (source.equals(this.close)) {
                CHANNELS.CloseChannel(this.getTag());
                return;
            }
            if (source instanceof JMenuItem) {
                if (source.equals(this.p_openprivate)) {
                    this.eirc.getPrivates().openPrivate(this.nickpopap.getNick(), 1);
                    this.eirc.getChat_panel().ShowPrivate(this.nickpopap.getNick().toLowerCase());
                    return;
                }
                if (source.equals(this.p_openchan)) {
                    this.entry.setText(this.nickpopap.getNick() + " >");
                    this.entry.requestFocus();
                    return;
                }
                if (source.equals(this.p_addfriend)) {
                    this.eirc.add_friend(this.nickpopap.getNick());
                    return;
                }
                if (source.equals(this.p_show_profile)) {
                    this.eirc.showProfile(this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_ignore)) {
                    if (this.eirc.cmd_ignore(this.nickpopap.getNick(), this.getDoc()).equals("false")) {
                        this.eirc.getCurrentPanel().printInfo("N'abusez pas du syst\u00e9me bloquer/d\u00e9bloquer s.v.p !");
                    }
                    return;
                }
                if (source.equals(this.p_send_kiss)) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), "\u0001ACTION fait un gros bisou \u00e0 " + this.nickpopap.getNick() + "\u0001" });
                    this.printAction("fait un gros bisou \u00e0 " + this.nickpopap.getNick(), this.eirc.getNick());
                    return;
                }
                if (source.equals(this.p_send_hi)) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), "\u0001ACTION serre la main \u00e0 son pote " + this.nickpopap.getNick() + "\u0001" });
                    this.printAction("serre la main \u00e0 son pote " + this.nickpopap.getNick(), this.eirc.getNick());
                    return;
                }
                if (source.equals(this.p_send_baffe)) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getTag(), "\u0001ACTION met une grooosse baffe \u00e0 " + this.nickpopap.getNick() + "\u0001" });
                    this.printAction("met une grooosse baffe \u00e0 " + this.nickpopap.getNick(), this.eirc.getNick());
                    return;
                }
                if (source.equals(this.p_sort_sexe)) {
                    if (this.eirc.isRegister()) {
                        EIRC.SORTING_METHOD = 3;
                        this.getNickList().sort();
                        this.nick_list.refresh(this.getNickList());
                    }
                    else {
                        final Object[] array2 = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                        if (JOptionPane.showOptionDialog(this.channelpane, "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array2, array2[1]) == 0) {
                            IEAutoStart.run("http://vip.chat-land.org/");
                        }
                    }
                    return;
                }
                if (source.equals(this.p_sort_age)) {
                    if (this.eirc.isRegister()) {
                        EIRC.SORTING_METHOD = 1;
                        this.getNickList().sort();
                        this.nick_list.refresh(this.getNickList());
                    }
                    else {
                        final Object[] array3 = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                        if (JOptionPane.showOptionDialog(this.channelpane, "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array3, array3[1]) == 0) {
                            IEAutoStart.run("http://vip.chat-land.org/");
                        }
                    }
                    return;
                }
                if (source.equals(this.p_sort_dep)) {
                    if (this.eirc.isRegister()) {
                        EIRC.SORTING_METHOD = 2;
                        this.getNickList().sort();
                        this.nick_list.refresh(this.getNickList());
                    }
                    else {
                        final Object[] array4 = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                        if (JOptionPane.showOptionDialog(this.channelpane, "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array4, array4[1]) == 0) {
                            IEAutoStart.run("http://vip.chat-land.org/");
                        }
                    }
                    return;
                }
                if (source.equals(this.p_sort_nick)) {
                    if (this.eirc.isRegister()) {
                        EIRC.SORTING_METHOD = 0;
                        this.getNickList().sort();
                        this.nick_list.refresh(this.getNickList());
                    }
                    else {
                        final Object[] array5 = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                        if (JOptionPane.showOptionDialog(this.channelpane, "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array5, array5[1]) == 0) {
                            IEAutoStart.run("http://vip.chat-land.org/");
                        }
                    }
                    return;
                }
                if (source.equals(this.p_op_kick)) {
                    this.eirc.openKick(this.getTag(), this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_kickban)) {
                    this.eirc.openKickBan(this.getTag(), this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_ban)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +b " + this.nickpopap.getNick() + "!*@*", this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_unban)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " -b " + this.nickpopap.getNick() + "!*@*", this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_admin)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +a " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_unadmin)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " -a " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_protect)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +e " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_voice)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +v " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_unvoice)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " -v " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_op)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +o " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_deop)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " -o " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_hop)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " +h " + this.nickpopap.getNick(), this.getDoc());
                    return;
                }
                if (source.equals(this.p_op_dehop)) {
                    this.eirc.sendCommand("mode " + this.getTag() + " -h " + this.nickpopap.getNick(), this.getDoc());
                }
            }
        }
    }
    
    public void add(String canonicalizeNick) {
        final int symbolicToMask = Modes.symbolicToMask(canonicalizeNick.charAt(0));
        canonicalizeNick = Modes.canonicalizeNick(canonicalizeNick);
        final User user = new User(canonicalizeNick);
        user.setModes(symbolicToMask);
        this.users.put(canonicalizeNick.toLowerCase(), user);
        this.updatechan(new ChannelMessage(1, canonicalizeNick, Integer.toString(symbolicToMask)));
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        try {
            if (this.scrollpanel.getVerticalScrollBar().getMaximum() - this.scrollpanel.getVerticalScrollBar().getVisibleAmount() - this.scrollpanel.getVerticalScrollBar().getMinimum() != 0 && 100 * adjustmentEvent.getValue() / (this.scrollpanel.getVerticalScrollBar().getMaximum() - this.scrollpanel.getVerticalScrollBar().getVisibleAmount() - this.scrollpanel.getVerticalScrollBar().getMinimum()) != 100 && this.scrollpanel.getVerticalScrollBar().getValue() < this.oldvalue) {
                return;
            }
        }
        catch (Exception ex) {}
        this.oldvalue = this.scrollpanel.getVerticalScrollBar().getValue();
        this.scrollpanel.getVerticalScrollBar().setValue(this.scrollpanel.getVerticalScrollBar().getMaximum());
    }
    
    @Override
    public void autoAway() {
        if (this.eirc != null) {
            this.eirc.revenir();
        }
    }
    
    public void buildPopup() {
        this.p_sort = new JMenu("Trier la liste par");
        this.p_sort_sexe = new JMenuItem("Sexe");
        this.p_sort_age = new JMenuItem("\u00c2ge");
        this.p_sort_dep = new JMenuItem("D\u00e9partement");
        this.p_sort_nick = new JMenuItem("Pseudo");
        this.p_sort.add(this.p_sort_sexe);
        this.p_sort.add(this.p_sort_age);
        this.p_sort.add(this.p_sort_dep);
        this.p_sort.add(this.p_sort_nick);
        this.p_op = new JMenu("Animateur du salon");
        this.p_op_kick = new JMenuItem("Ejecter");
        this.p_op_kickban = new JMenuItem("Ejecter & bannir");
        this.p_op_ban = new JMenuItem("Bannir");
        this.p_op_unban = new JMenuItem("D\u00e9bannir");
        this.p_op_admin = new JMenuItem("Donner le statut administrateur");
        this.p_op_unadmin = new JMenuItem("Retirer le statut administrateur");
        this.p_op_protect = new JMenuItem("Prot\u00e9ger du bannissement");
        this.p_op_voice = new JMenuItem("Donner la parole");
        this.p_op_unvoice = new JMenuItem("Retirer la parole");
        this.p_op_op = new JMenuItem("Donner le statut op\u00e9rateur");
        this.p_op_deop = new JMenuItem("Retirer le statut op\u00e9rateur");
        this.p_op_hop = new JMenuItem("Donner le statut animateur");
        this.p_op_dehop = new JMenuItem("Retirer le statut animateur");
        this.p_op.add(this.p_op_kick);
        this.p_op.add(this.p_op_kickban);
        this.p_op.addSeparator();
        this.p_op.add(this.p_op_ban);
        this.p_op.add(this.p_op_unban);
        this.p_op.addSeparator();
        this.p_op.add(this.p_op_admin);
        this.p_op.add(this.p_op_unadmin);
        this.p_op.add(this.p_op_protect);
        this.p_op.addSeparator();
        this.p_op.add(this.p_op_voice);
        this.p_op.add(this.p_op_unvoice);
        this.p_op.addSeparator();
        this.p_op.add(this.p_op_op);
        this.p_op.add(this.p_op_deop);
        this.p_op.addSeparator();
        this.p_op.add(this.p_op_hop);
        this.p_op.add(this.p_op_dehop);
        this.p_menu = new JPopupMenu();
        this.p_openprivate = new JMenuItem("Ouvrir une discussion priv\u00e9e");
        this.p_openchan = new JMenuItem("Parler sur le salon");
        this.p_addfriend = new JMenuItem("Ajouter \u00e0 ma liste d'amis");
        this.p_show_profile = new JMenuItem("Voir son profil");
        this.p_ignore = new JMenuItem("Bloquer");
        this.p_send_kiss = new JMenuItem("Faire une bise!");
        this.p_send_hi = new JMenuItem("Saluer");
        this.p_send_baffe = new JMenuItem("Mettre une baffe");
        this.p_menu.add(this.p_openprivate);
        this.p_menu.add(this.p_openchan);
        this.p_menu.addSeparator();
        this.p_menu.add(this.p_sort);
        this.p_menu.add(this.p_addfriend);
        this.p_menu.add(this.p_show_profile);
        this.p_menu.add(this.p_ignore);
        this.p_menu.addSeparator();
        this.p_menu.add(this.p_send_kiss);
        this.p_menu.add(this.p_send_hi);
        this.p_menu.add(this.p_send_baffe);
        this.p_menu.addSeparator();
        this.p_menu.add(this.p_op);
        this.p_openprivate.addActionListener(this);
        this.p_openchan.addActionListener(this);
        this.p_addfriend.addActionListener(this);
        this.p_show_profile.addActionListener(this);
        this.p_ignore.addActionListener(this);
        this.p_send_kiss.addActionListener(this);
        this.p_send_hi.addActionListener(this);
        this.p_send_baffe.addActionListener(this);
        this.p_sort_sexe.addActionListener(this);
        this.p_sort_age.addActionListener(this);
        this.p_sort_dep.addActionListener(this);
        this.p_sort_nick.addActionListener(this);
        this.p_op_kick.addActionListener(this);
        this.p_op_kickban.addActionListener(this);
        this.p_op_ban.addActionListener(this);
        this.p_op_unban.addActionListener(this);
        this.p_op_admin.addActionListener(this);
        this.p_op_unadmin.addActionListener(this);
        this.p_op_protect.addActionListener(this);
        this.p_op_voice.addActionListener(this);
        this.p_op_unvoice.addActionListener(this);
        this.p_op_op.addActionListener(this);
        this.p_op_deop.addActionListener(this);
        this.p_op_hop.addActionListener(this);
        this.p_op_dehop.addActionListener(this);
        this.p_menu.addMouseMotionListener(this);
    }
    
    public boolean canNick() {
        return (this.modes_mask & 0x20) == 0x0;
    }
    
    @Override
    public void Click() {
    }
    
    public void colorBackground(final Color color) {
        this.police.setBackground(color);
        this.grouper.setBackground(color);
        this.sound.setBackground(color);
        this.smileymenu.colorBackground(color);
        this.smileymenu.setBackground(color);
        this.sajoin.setBackground(color);
        this.gomme.setBackground(color);
        this.favorit.setBackground(color);
        this.interlignes.setBackground(color);
        this.smileys.setBackground(color);
        this.send.setBackground(color);
        this.murmurer.setBackground(color);
        this.close.setBackground(color);
    }
    
    public void colorForeground(final Color foreground) {
        this.police.setForeground(foreground);
        this.grouper.setForeground(foreground);
        this.sound.setForeground(foreground);
        this.smileymenu.colorForeground(foreground);
        this.smileymenu.setForeground(foreground);
        this.gomme.setForeground(foreground);
        this.favorit.setForeground(foreground);
        this.interlignes.setForeground(foreground);
        this.smileys.setForeground(foreground);
        this.send.setForeground(foreground);
        this.murmurer.setForeground(foreground);
        this.close.setForeground(foreground);
    }
    
    public void completeNick() {
        final char[] charArray = this.entry.getText().toCharArray();
        final int caretPosition = this.entry.getCaretPosition();
        int n = caretPosition - 1;
        int n2 = caretPosition;
        final Collator collator = RFC1459.getCollator();
        synchronized (this.getNickList()) {
            final String[] list = this.getNickList().getList();
            for (int i = 0; i < this.getNickList().size(); ++i) {
                for (int j = list.length - 1; j > i; --j) {
                    if (collator.compare(list[i], list[j]) > 0) {
                        final String s = list[i];
                        list[i] = list[j];
                        list[j] = s;
                    }
                }
            }
            while (n >= 0 && RFC1459.isDeclaredChar(charArray[n])) {
                --n;
            }
            while (n2 < charArray.length && RFC1459.isDeclaredChar(charArray[n2])) {
                ++n2;
            }
            ++n;
            if (this.complete_current.length() == 0) {
                this.complete_partial = String.valueOf(charArray, n, n2 - n);
            }
            final int length = this.complete_partial.length();
            int n3 = -1;
            int n4 = 0;
            for (int k = 0; k < list.length; ++k) {
                if (list[k].length() >= length) {
                    if (collator.equals(list[k].substring(0, length), this.complete_partial)) {
                        if (n3 == -1) {
                            n3 = k;
                        }
                        if (collator.compare(list[k], this.complete_current) > 0) {
                            this.complete_current = list[k];
                            n4 = 1;
                            break;
                        }
                    }
                }
            }
            if (n3 != -1 && n4 == 0) {
                this.complete_current = list[n3];
                n4 = 1;
            }
            if (n4 != 0) {
                this.entry.setText(String.valueOf(charArray, 0, n).concat(this.complete_current).concat(String.valueOf(charArray, n2, charArray.length - n2)));
                this.entry.setCaretPosition(n + this.complete_current.length());
            }
        }
    }
    
    public boolean contains(final String s) {
        return this.users.contains(s.toLowerCase());
    }
    
    public void couleur(final int fgColor, final int bgColor, final Font font) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.textzone.moveCaretPosition(this.textzone.getDocument().getLength());
        this.entry.setForeground(EIRC.fixedColors[fgColor]);
        this.entry.setBackground(EIRC.fixedColors[bgColor]);
    }
    
    public void current() {
        this.eirc.getChat_panel().setCurrent(this);
        this.eirc.getChat_panel().setCurrentname(this.getTag());
    }
    
    public void disposeChannel() {
        if (this.doc != null) {
            try {
                this.doc.remove(0, this.doc.getLength());
            }
            catch (BadLocationException ex) {}
        }
        if (this.users != null) {
            this.users.clear();
        }
        if (this.nick_list_vect != null) {
            this.nick_list_vect.clearAllNicks();
        }
        this.eirc = null;
        this.nick_list_vect = null;
        this.topic = null;
        this.tag = null;
        this.doc = null;
        this.key = null;
    }
    
    @Override
    public void doubleClick() {
        this.eirc.revenir();
        (this.chanadmin = new ChannelAdmin(this)).setSize(380, 400);
        this.chanadmin.setIconImage(Resources.getImages("minlogoa"));
        this.chanadmin.updateModes(this.getTag());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.chanadmin.getSize();
        if (size.height > screenSize.height) {
            size.height = screenSize.height;
        }
        if (size.width > screenSize.width) {
            size.width = screenSize.width;
        }
        this.chanadmin.setLocation((screenSize.width - size.width) / 3, (screenSize.height - size.height) / 3);
        this.chanadmin.setVisible(true);
        this.chanadmin.pack();
    }
    
    public void enableReorder() {
        this.findechargement = true;
        this.nick_list_vect.sort();
        this.setTitle();
        this.nick_list.refresh(this.nick_list_vect);
    }
    
    public void entryrequestFocus() {
        this.entry.requestFocus();
    }
    
    public void flash() {
        new flash();
    }
    
    @Override
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    @Override
    public void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getOppositeComponent() != null) {
            if (focusEvent.getOppositeComponent().getName() != null) {
                if (focusEvent.getOppositeComponent().getName().equalsIgnoreCase("privatewindow")) {
                    this.entryrequestFocus();
                }
            }
            else if (focusEvent.getOppositeComponent() instanceof JTabbedPane) {
                this.entryrequestFocus();
            }
        }
    }
    
    public void free() {
        this.eirc = null;
        this.gb = null;
        this.gbc = null;
        this.tools = null;
        this.police = null;
        this.sound = null;
        this.smileys = null;
        this.smileymenu.free();
        this.smileymenu = null;
        this.gomme = null;
        this.favorit = null;
        this.interlignes = null;
        this.close = null;
        this.entry = null;
        this.send = null;
        this.murmurer = null;
        this.p_menu = null;
        this.p_sort = null;
        this.p_op = null;
        this.p_openprivate = null;
        this.p_addfriend = null;
        this.p_show_profile = null;
        this.p_ignore = null;
        this.p_send_kiss = null;
        this.p_send_hi = null;
        this.p_send_baffe = null;
        this.p_sort_sexe = null;
        this.p_sort_age = null;
        this.p_sort_dep = null;
        this.p_sort_nick = null;
        this.p_op_kick = null;
        this.p_op_kickban = null;
        this.p_op_ban = null;
        this.p_op_unban = null;
        this.p_op_admin = null;
        this.p_op_unadmin = null;
        this.p_op_protect = null;
        this.p_op_voice = null;
        this.p_op_unvoice = null;
        this.p_op_op = null;
        this.p_op_deop = null;
        this.p_op_hop = null;
        this.p_op_dehop = null;
        this.scrollpanel = null;
        this.textzone = null;
        this.nickinfos = null;
        this.nick_list.free();
        this.nick_list = null;
        this.attributSet = null;
        if (this.chanadmin != null) {
            this.chanadmin.free();
        }
        this.chanadmin = null;
        this.complete_partial = null;
        this.complete_current = null;
        this.removeAll();
    }
    
    public User get(final String s) {
        return this.users.get(s.toLowerCase());
    }
    
    public Container getchannelpane() {
        this.remove(this.channelpane);
        return this.channelpane;
    }
    
    public NewTextDocument getDoc() {
        return this.doc;
    }
    
    public EIRC getEirc() {
        return this.eirc;
    }
    
    public TextFieldHistory getEntry() {
        return this.entry;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public NickListVector getNickList() {
        return this.nick_list_vect;
    }
    
    public JToggleButton getSound() {
        return this.sound;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public JTextPane getTextzone() {
        return this.textzone;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getTopic() {
        return this.topic;
    }
    
    @Override
    public void handleHyperlink(String string) {
        if (string.startsWith("http://") || string.startsWith("www")) {
            if (string.startsWith("www")) {
                string = "http://" + string;
            }
            IEAutoStart.run(string);
        }
        else if (string.startsWith("#")) {
            this.joinChannel(string);
        }
        else {
            this.entry.setText(this.entry.getText() + string + ">");
            this.entry.requestFocus();
        }
    }
    
    @Override
    public void handleNick(final String s) {
    }
    
    @Override
    public void hidePopupInfos() {
        this.nickinfos.setVisible(false);
        this.textzone.remove(this.nickinfos);
        this.textzone.repaint();
    }
    
    public void initcomplete_current() {
        this.complete_current = "";
    }
    
    public void insertSmiley(final String s) {
        this.entry.setText(this.entry.getText() + s);
        this.entry.setCaretPosition(this.entry.getText().length());
        this.entry.requestFocus();
    }
    
    public void interlignes(final float n) {
        final StyledDocument styledDocument = this.textzone.getStyledDocument();
        StyleConstants.setLineSpacing(this.attributSet, n);
        styledDocument.setParagraphAttributes(0, styledDocument.getLength(), this.attributSet, false);
        try {
            this.textzone.getDocument().insertString(0, "", null);
            this.textzone.moveCaretPosition(this.textzone.getDocument().getLength());
        }
        catch (BadLocationException ex) {}
        this.textzone.select(this.textzone.getDocument().getLength(), this.textzone.getDocument().getLength());
    }
    
    public boolean isInvitOnly() {
        return (this.modes_mask & 0x8) != 0x0;
    }
    
    public boolean isIs_adm() {
        return this.is_adm;
    }
    
    public boolean isIs_hop() {
        return this.is_hop;
    }
    
    public boolean isIs_op() {
        return this.is_op;
    }
    
    public boolean isIs_own() {
        return this.is_own;
    }
    
    public boolean isModerated() {
        return (this.modes_mask & 0x2) != 0x0;
    }
    
    public boolean isRegistered() {
        return (this.modes_mask & 0x10) != 0x0;
    }
    
    public boolean isSecret() {
        return (this.modes_mask & 0x4) != 0x0;
    }
    
    public boolean isTopicSettable() {
        return (this.modes_mask & 0x1) == 0x0;
    }
    
    private void jbInit() throws Exception {
        this.scrollpanel = new JScrollPane();
        this.textzone = new JTextPane();
        this.scrollpanel.getViewport().add(this.textzone);
        this.textzone.setEditable(false);
        this.scrollpanel.setAutoscrolls(true);
        this.scrollpanel.getVerticalScrollBar().addAdjustmentListener(this);
        this.scrollpanel.getVerticalScrollBar().addMouseListener(this);
        this.smileymenu = new SmileysMenu(this);
        (this.tools = new JToolBar()).setFloatable(false);
        this.nick_list = new NickList(this);
        this.police = new JButton(new ImageIcon(Resources.getImages("frame.police")));
        this.grouper = new JButton();
        if (this.eirc.isIsgroupchannel()) {
            this.grouper.setIcon(new ImageIcon(Resources.getImages("icondegrouper")));
        }
        else {
            this.grouper.setIcon(new ImageIcon(Resources.getImages("iconregrouper")));
        }
        this.grouper.setToolTipText("Degrouper tous les salons ");
        this.police.setToolTipText("Modifier police");
        (this.sound = new JToggleButton(EIRC.soundon)).setToolTipText("Activer/d\u00e9sactiver le son");
        (this.smileys = new JButton(new ImageIcon(Resources.getImages("button.smileys")))).setToolTipText("Ins\u00e9rer une \u00e9motic\u00f4ne");
        (this.gomme = new JButton(new ImageIcon(Resources.getImages("panel.icon.gomme")))).setToolTipText("Effacer tout le texte");
        this.favorit = new JButton(new ImageIcon(Resources.getImages("panel.icon.addfavori")));
        (this.interlignes = new JToggleButton(new ImageIcon(EIRC.class.getResource("gui/interligne.png")))).setToolTipText("Mettre un espace entre chaque phrase");
        (this.close = new MyJButton(avatar.MybackGround, "Quitter ce salon", new ImageIcon(Resources.getImages("chan.icon.close")), 0)).setToolTipText("Cliquez ici pour quitter le salon");
        this.entry = new TextFieldHistory(this.eirc);
        this.send = new MyJButton(avatar.MybackGround, "Envoyer", null, 0);
        this.murmurer = new MyJButton(avatar.MybackGround, "Murmurer", null, 0);
        this.sajoin = new JButton(new ImageIcon(Resources.getImages("icon_salon_coeur")));
        final EIRC eirc = this.eirc;
        if (EIRC.sajoin.contains(this.getTag().toLowerCase())) {
            this.sajoin.setText("enlever le salon " + this.getTag() + " au d\u00e9marrage de messenger");
        }
        else {
            this.sajoin.setText("Ouvrir le salon " + this.getTag() + " au d\u00e9marrage de messenger");
        }
        (this.nickinfos = new PopupInfos("")).setVisible(false);
        this.tools.setOpaque(false);
        this.tools.add(this.police);
        this.tools.add(this.sound);
        this.tools.add(this.smileys);
        this.tools.add(this.interlignes);
        this.tools.add(this.favorit);
        this.tools.add(this.gomme);
        this.channelpane = new Container();
        this.gb = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.channelpane.setLayout(this.gb);
        (this.titre = new JLabel(this.getTag())).setFont(new Font("Time New Roman", 0, 18));
        (this.listeausaj = new JLabel("<html><u>Voir la liste auto </u></html>", 4)).setForeground(Color.BLUE);
        this.listeausaj.setFont(new Font("Times New Roman", 1, 14));
        this.gbc.anchor = 10;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 4;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 4.0;
        this.gbc.weighty = 0.0;
        this.gbc.fill = 10;
        this.gbc.insets = new Insets(5, 0, 3, 0);
        this.gb.setConstraints(this.titre, this.gbc);
        this.channelpane.add(this.titre);
        this.gbc.anchor = 10;
        this.gbc.gridx = 4;
        this.gbc.gridy = 0;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 0.0;
        this.gbc.weighty = 0.0;
        this.gbc.fill = 10;
        this.gbc.insets = new Insets(5, 0, 3, 0);
        this.gb.setConstraints(this.sajoin, this.gbc);
        this.channelpane.add(this.sajoin);
        this.gbc.anchor = 20;
        this.gbc.gridx = 4;
        this.gbc.gridy = 1;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 0.0;
        this.gbc.weighty = 0.0;
        this.gbc.fill = 2;
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.gb.setConstraints(this.listeausaj, this.gbc);
        this.channelpane.add(this.listeausaj);
        this.listeausaj.setPreferredSize(new Dimension(0, 15));
        this.gbc.anchor = 10;
        this.gbc.insets = new Insets(0, 3, 0, 0);
        this.gbc.weighty = 0.0;
        this.gbc.anchor = 10;
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbc.gridwidth = 4;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 2.0;
        this.gbc.weighty = 1.0;
        this.gbc.fill = 1;
        this.gb.setConstraints(this.scrollpanel, this.gbc);
        this.channelpane.add(this.scrollpanel);
        this.gbc.insets = new Insets(0, 0, 0, 3);
        this.nick_list.setPreferredSize(new Dimension(180, 0));
        this.gbc.gridx = 4;
        this.gbc.gridy = 2;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.fill = 1;
        this.gb.setConstraints(this.nick_list, this.gbc);
        this.channelpane.add(this.nick_list);
        this.gbc.insets = new Insets(0, 10, 0, 0);
        this.gbc.gridx = 0;
        this.gbc.gridy = 3;
        this.gbc.gridwidth = 3;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 2.0;
        this.gbc.weighty = 0.0;
        this.gb.setConstraints(this.tools, this.gbc);
        this.channelpane.add(this.tools);
        this.gbc.insets = new Insets(0, 5, 5, 5);
        final JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOpaque(false);
        toolBar.add(this.close);
        this.close.setOpaque(false);
        toolBar.setOpaque(false);
        this.gbc.gridx = 4;
        this.gbc.gridy = 3;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 0.0;
        this.gbc.weighty = 0.0;
        this.gb.setConstraints(toolBar, this.gbc);
        this.channelpane.add(toolBar);
        this.gbc.gridx = 0;
        this.gbc.gridy = 4;
        this.gbc.gridwidth = 3;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 0.0;
        this.gbc.weighty = 0.0;
        this.gbc.anchor = 10;
        this.gb.setConstraints(this.entry, this.gbc);
        this.channelpane.add(this.entry);
        this.gbc.gridx = 3;
        this.gbc.gridy = 4;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.weightx = 0.0;
        this.gbc.weighty = 0.0;
        this.gbc.anchor = 10;
        this.gbc.fill = 2;
        final JToolBar toolBar2 = new JToolBar();
        toolBar2.setFloatable(false);
        toolBar2.setOpaque(false);
        toolBar2.add(this.send);
        toolBar2.add(this.murmurer);
        toolBar2.setOpaque(false);
        this.send.setOpaque(false);
        this.murmurer.setOpaque(false);
        this.gb.setConstraints(toolBar2, this.gbc);
        this.channelpane.add(toolBar2);
        this.setLayout(new BorderLayout());
        this.add(this.channelpane, "Center");
        this.police.addActionListener(this);
        this.grouper.addActionListener(this);
        this.sound.addActionListener(this);
        this.smileys.addMouseListener(this);
        this.gomme.addActionListener(this);
        this.favorit.addActionListener(this);
        this.interlignes.addActionListener(this);
        this.close.addActionListener(this);
        this.listeausaj.addMouseListener(this);
        this.send.addActionListener(this);
        this.murmurer.addActionListener(this);
        this.entry.addKeyListener(this);
        this.entry.addActionListener(this);
        this.entry.addMouseListener(this);
        this.sajoin.addActionListener(this);
        this.textzone.addMouseListener(new NewTextClickListener(this, this.textzone));
        this.textzone.addMouseMotionListener(new NewTextMotionListener(this, this.textzone));
        this.nick_list.getNicklist().addMouseListener(this);
        this.buildPopup();
        this.tools.repaint();
        this.sound.setSelected(this.eirc.isSilent());
        if (this.eirc.isSilent()) {
            final JToggleButton sound = this.sound;
            final EIRC eirc2 = this.eirc;
            sound.setIcon(EIRC.soundoff);
        }
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        if (this.eirc.getXchannelswindows() > 0) {
            if (maximumWindowBounds.width == this.eirc.getXchannelswindows() && maximumWindowBounds.height == this.eirc.getYchannelwindows()) {
                this.loct = this.getLocation();
                if (maximumWindowBounds.width > 0 && maximumWindowBounds.height > 0) {
                    this.setSize(maximumWindowBounds.width, maximumWindowBounds.height);
                }
                this.setLocation(0, 0);
                this.eirc.setXchannelswindows((int)this.getSize().getWidth());
                this.eirc.setYchannelswindows((int)this.getSize().getHeight());
            }
            else if (this.eirc.getXchannelswindows() > 0 && this.eirc.getYchannelwindows() > 0) {
                this.setSize(this.eirc.getXchannelswindows(), this.eirc.getYchannelwindows());
            }
        }
        this.setLocation(this.eirc.getAccueil().frampos, this.eirc.getAccueil().frampos);
        final main accueil = this.eirc.getAccueil();
        accueil.frampos += 20;
        if (this.eirc.getAccueil().frampos > 100) {
            this.eirc.getAccueil().frampos = 0;
        }
        this.tools.setOpaque(false);
        this.police.setOpaque(false);
        this.grouper.setOpaque(false);
        this.sound.setOpaque(false);
        this.smileys.setOpaque(false);
        this.interlignes.setOpaque(false);
        this.favorit.setOpaque(false);
        this.gomme.setOpaque(false);
        this.sajoin.setOpaque(false);
    }
    
    protected void joinChannel(final String s) {
        if (null == CHANNELS.getChannelWindow(s)) {
            this.eirc.sendMessage("JOIN", new String[] { s });
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    public void LoadBanned(final Vector vector) {
        this.chanadmin.LoadBanned(vector);
    }
    
    public void makeupdate(final ChannelMessage channelMessage) {
        if (channelMessage == null) {
            return;
        }
        switch (channelMessage.getAction()) {
            case 1: {
                this.nick_list_vect.add(channelMessage.getMessagePart1(), Integer.parseInt(channelMessage.getMessagePart2()));
                if (this.findechargement) {
                    this.nick_list_vect.sort();
                    this.setTitle();
                    this.nick_list.refresh(this.nick_list_vect);
                    break;
                }
                break;
            }
            case 2: {
                this.nick_list_vect.remove(channelMessage.getMessagePart1());
                if (this.findechargement) {
                    this.nick_list_vect.sort();
                    this.setTitle();
                    this.nick_list.refresh(this.nick_list_vect);
                    break;
                }
                break;
            }
            case 3: {
                this.nick_list_vect.rename(channelMessage.getMessagePart1(), channelMessage.getMessagePart2());
                if (this.findechargement) {
                    this.nick_list_vect.sort();
                    this.setTitle();
                    this.nick_list.refresh(this.nick_list_vect);
                    break;
                }
                break;
            }
            case 4: {
                this.nick_list_vect.mode(channelMessage.getMessagePart1(), Integer.parseInt(channelMessage.getMessagePart2()));
                if (this.findechargement) {
                    this.nick_list_vect.sort();
                    this.setTitle();
                    this.nick_list.refresh(this.nick_list_vect);
                    break;
                }
                break;
            }
            case 8: {
                final User user = this.users.get(this.eirc.getNick());
                if (user != null) {
                    this.is_op = user.isOp();
                    this.is_hop = user.isHalfOp();
                    this.is_adm = user.isAdmin();
                    this.is_own = user.isOwner();
                }
                if (this.findechargement) {
                    this.setTitle();
                    this.nick_list.refresh(this.nick_list_vect);
                    break;
                }
                break;
            }
            case 9: {}
            case 7: {}
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.listeausaj)) {
            final JDialog dialog = new JDialog(this.eirc.getChannelgroup(), true);
            dialog.setTitle("liste auto");
            dialog.setLocationRelativeTo(this.eirc.getChannelgroup());
            final JList list = new JList();
            final EIRC eirc = this.eirc;
            list.setListData(EIRC.sajoin);
            dialog.add(new JScrollPane(list));
            dialog.setSize(100, 200);
            dialog.setVisible(true);
            return;
        }
        if (mouseEvent.getSource().equals(this.nick_list.nicklist)) {
            if (this.nick_list.nicklist.getSelectedIndex() != -1 && this.nick_list.nicklist.getModel().getSize() > this.nick_list.nicklist.getSelectedIndex()) {
                final NickItem nickpopap = this.nick_list.nicklist.getModel().getElementAt(this.nick_list.nicklist.getSelectedIndex());
                if (nickpopap != null && (this.nickpopap = nickpopap) != null && mouseEvent.getClickCount() == 2 && this.entry.getText().endsWith(nickpopap.getNick())) {
                    this.entry.setText(this.entry.getText().substring(0, this.entry.getText().length() - (nickpopap.getNick().length() + 1)));
                }
            }
            if (mouseEvent.getClickCount() == 2 && this.nick_list.nicklist.getSelectedIndex() != -1) {
                this.eirc.getPrivates().openPrivate(this.nickpopap.getNick(), 1);
                this.eirc.getChat_panel().ShowPrivate(this.nickpopap.getNick().toLowerCase());
                this.p_menu.setVisible(false);
            }
        }
        if (mouseEvent.getSource().equals(this.entry) && (mouseEvent.getButton() == 3 || mouseEvent.getModifiers() == 18)) {
            this.eirc.getAccueil().copypaste.setParent(this.entry);
            this.eirc.getAccueil().copypaste.refershItemsState();
            this.eirc.getAccueil().copypaste.show(this.entry, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getX() > this.getWidth() - 54 && mouseEvent.getX() < this.getWidth() - 13 && mouseEvent.getY() < 30) {
            this.setCursor(Cursor.getPredefinedCursor(0));
            return;
        }
        if (mouseEvent.getX() > this.getWidth() - 80 && mouseEvent.getX() < this.getWidth() - 55 && mouseEvent.getY() < 30) {
            this.setCursor(Cursor.getPredefinedCursor(0));
            return;
        }
        if (mouseEvent.getX() > this.getWidth() - 103 && mouseEvent.getX() < this.getWidth() - 81 && mouseEvent.getY() < 30) {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.eirc != null) {
            this.eirc.revenir();
        }
        if (mouseEvent.getSource().equals(this.listeausaj)) {
            this.eirc.getChannelgroup().setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.eirc != null) {
            this.eirc.revenir();
        }
        this.eirc.getChannelgroup().setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.smileys)) {
            this.smileymenu.show(this.smileys, mouseEvent.getX() + 5, mouseEvent.getY() - 230);
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.nick_list.nicklist) && this.nick_list.nicklist.getSelectedIndex() != -1) {
            if (this.nickpopap != null) {
                if (this.eirc.getIgnore_list().contains(this.nickpopap.getNick().toLowerCase())) {
                    this.p_ignore.setText("D\u00e9bloquer");
                }
                else {
                    this.p_ignore.setText("Bloquer");
                }
            }
            final Point point = mouseEvent.getPoint();
            this.p_menu.show(this.nick_list.getNicklist(), point.x - 220, point.y + 5);
        }
    }
    
    @Override
    public void MouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (this.textzone.getSelectedText() != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.textzone.getSelectedText()), null);
        }
        else if (mouseEvent.getButton() == 1) {
            this.entry.requestFocus();
        }
    }
    
    public void nick_listrepaint() {
        this.nick_list.nicklist.repaint();
        this.nick_list.repaint();
    }
    
    public void NotifyChannel(final String s, final String s2) {
        if (s2.equalsIgnoreCase("#plaintes")) {
            this.eirc.getChat_panel().Notify(s2);
            return;
        }
        if (s.substring(0, s.indexOf(" ")).toLowerCase().indexOf(this.eirc.getNick().toLowerCase()) != -1) {
            return;
        }
        if (s.toLowerCase().indexOf(this.eirc.getNick().toLowerCase()) != -1) {
            this.eirc.getChat_panel().Notify(s2);
            this.eirc.playSound(this.eirc.getSound_chan());
        }
    }
    
    public int number() {
        int size = this.users.size();
        if (size > 50 && size < 100) {
            size += size - 50;
        }
        else if (size > 100) {
            size += 50;
        }
        return size;
    }
    
    @Override
    public void openPrivate(final String s) {
        if (s.startsWith("http") || s.startsWith("www") || s.startsWith("#")) {
            this.handleHyperlink(s);
            return;
        }
        this.eirc.getPrivates().openPrivate(s, 1);
        this.eirc.getChat_panel().ShowPrivate(s.toLowerCase());
    }
    
    @Override
    public void popupCopyPaste(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.eirc.getAccueil().copypaste.setParent(this.textzone);
        this.eirc.getAccueil().copypaste.refershItemsState();
        this.eirc.getAccueil().copypaste.show(this.textzone, mouseEvent.getX(), mouseEvent.getY());
    }
    
    @Override
    public void popupInfos(final String s, final String s2, final MouseEvent mouseEvent) {
        if (NickInfos.hasInfos(s)) {
            this.nickinfos.setText(NickInfos.getAge(s) + s2 + NickInfos.getLocation(s));
            if (NickInfos.getSex(s) == 1) {
                this.nickinfos.setBackground(Color.decode("0x0099FF"));
            }
            else {
                this.nickinfos.setBackground(Color.decode("0xFF00FF"));
            }
        }
        else {
            this.nickinfos.setText("D\u00e9connect\u00e9");
            this.nickinfos.setBackground(Color.red);
        }
        this.nickinfos.setLocation(mouseEvent.getX() + 10, mouseEvent.getY() + 5);
        this.nickinfos.setVisible(true);
        this.textzone.add(this.nickinfos);
        this.textzone.repaint();
    }
    
    public void printAction(final String s, final String s2) {
        this.doc.printAction(s, s2);
    }
    
    public void printInfo(final String s) {
        this.doc.printInfo(s);
    }
    
    public void printNotice(final String s, final String s2) {
        this.doc.printNotice(s, s2);
    }
    
    public void printPrivmsg(final String s, final String s2) {
        this.doc.printPrivmsg(s, s2);
    }
    
    public void printPrivmsg(final String s, final String s2, final User user) {
        this.doc.printPrivmsg(s, s2, user);
    }
    
    public void remove(final String s) {
        final User user = this.users.get(s.toLowerCase());
        this.users.remove(s.toLowerCase());
        if (user != null) {
            this.updatechan(new ChannelMessage(2, s));
        }
    }
    
    public void rename(final String s, final String tag) {
        final User user = this.users.get(s.toLowerCase());
        if (user != null) {
            this.users.remove(user);
            user.setTag(tag);
            this.users.put(tag.toLowerCase(), user);
            this.updatechan(new ChannelMessage(3, s, tag));
        }
    }
    
    public void revenir() {
        if (this.eirc != null) {
            this.eirc.revenir();
        }
    }
    
    public void scrollDown() {
        this.scrollpanel.getVerticalScrollBar().setValue(this.scrollpanel.getVerticalScrollBar().getMaximum());
    }
    
    public void setactive(final boolean visible) {
        if (!visible) {
            this.nick_list.setNickList(new Vector());
        }
        if (!this.eirc.isIsgroupchannel()) {
            this.setVisible(visible);
        }
    }
    
    private void setChannelModes(final boolean b, final int n) {
        if (b) {
            this.modes_mask |= n;
        }
        else {
            this.modes_mask &= ~n;
        }
    }
    
    public void setchannelpane(final Container channelpane) {
        this.channelpane = channelpane;
        this.setLayout(new BorderLayout());
        this.add(this.channelpane, "Center");
    }
    
    public void setIconBgrouper(final String s) {
        this.grouper.setIcon(new ImageIcon(Resources.getImages(s)));
    }
    
    public void setIs_adm(final boolean is_adm) {
        this.is_adm = is_adm;
    }
    
    public void setIs_hop(final boolean is_hop) {
        this.is_hop = is_hop;
    }
    
    public void setIs_op(final boolean is_op) {
        this.is_op = is_op;
    }
    
    public void setIs_own(final boolean is_own) {
        this.is_own = is_own;
    }
    
    public void setKey(final boolean b, final String key) {
        if (b) {
            this.key = key;
        }
        else {
            this.key = "";
        }
    }
    
    public void setLimit(final String s) {
        try {
            this.limit = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            this.limit = -1;
        }
    }
    
    public void setModes(final String s, final String[] array) {
        final char[] charArray = s.toCharArray();
        boolean b = false;
        int n = 0;
        for (int i = 0; i < charArray.length; ++i) {
            switch (charArray[i]) {
                case '+': {
                    b = true;
                    break;
                }
                case '-': {
                    b = false;
                    break;
                }
                case 'v': {
                    this.setUserModes(array[n++], b, 8);
                    break;
                }
                case 'o': {
                    this.setUserModes(array[n++], b, 2);
                    break;
                }
                case 'h': {
                    this.setUserModes(array[n++], b, 4);
                    break;
                }
                case 'a': {
                    this.setUserModes(array[n++], b, 16);
                    break;
                }
                case 'q': {
                    this.setUserModes(array[n++], b, 32);
                    break;
                }
                case 't': {
                    this.setChannelModes(b, 1);
                    break;
                }
                case 'm': {
                    this.setChannelModes(b, 2);
                    break;
                }
                case 's': {
                    this.setChannelModes(b, 4);
                    break;
                }
                case 'i': {
                    this.setChannelModes(b, 8);
                    break;
                }
                case 'k': {
                    this.setKey(b, array[n++]);
                    break;
                }
                case 'l': {
                    this.setLimit(b ? array[n++] : "");
                    break;
                }
                case 'r': {
                    this.setChannelModes(b, 16);
                    break;
                }
                case 'N': {
                    this.setChannelModes(b, 32);
                    break;
                }
                case 'I':
                case 'b':
                case 'e': {
                    ++n;
                    break;
                }
            }
        }
        this.updatechan(new ChannelMessage(8));
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public void setTitle() {
        this.titre.setText("Salon  " + this.getTag() + "  " + this.getNickList().mySize() + "  chateurs");
        this.repaint();
    }
    
    public void settoolBgrouper(final String toolTipText) {
        this.grouper.setToolTipText(toolTipText);
    }
    
    public void setTopic(final String topic) {
        this.topic = topic;
    }
    
    private void setUserModes(final String s, final boolean b, final int n) {
        final User user = this.users.get(s.toLowerCase());
        if (user == null) {
            return;
        }
        final int modes = user.getModes();
        int modes2;
        if (b) {
            modes2 = (modes | n);
        }
        else {
            modes2 = (modes & ~n);
        }
        user.setModes(modes2);
        this.updatechan(new ChannelMessage(4, s, Integer.toString(modes2)));
    }
    
    @Override
    public void TextAdded(final String s) {
        this.NotifyChannel(s, this.getTag());
    }
    
    public void updatechan(final ChannelMessage chanelmessage) {
        this.chanelmessage = chanelmessage;
        if (this.findechargement) {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        ChannelWindow.this.makeupdate(ChannelWindow.this.chanelmessage);
                    }
                });
            }
            catch (InvocationTargetException ex) {}
            catch (InterruptedException ex2) {}
        }
        else {
            this.makeupdate(this.chanelmessage);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        if (this.eirc != null) {
            this.eirc.getChat_panel().setCurrent(this);
            this.eirc.getChat_panel().setCurrentname(this.getTag());
            this.validate();
            this.repaint();
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        CHANNELS.CloseChannel(this.getTag());
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowStateChanged(final WindowEvent windowEvent) {
    }
    
    class flash extends Thread
    {
        public flash() {
            this.start();
        }
        
        @Override
        public void run() {
            if (!NativeLoader.isload()) {
                return;
            }
            try {
                final IntCall intCall = new IntCall("user32", "FindWindowA");
                final int executeCall = intCall.executeCall(new Object[] { null, ChannelWindow.this.getTitle() });
                intCall.destroy();
                final IntCall intCall2 = new IntCall("user32", "FlashWindow");
                intCall2.executeCall(new Object[] { new Integer(executeCall), new Integer(1) });
                intCall2.destroy();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
