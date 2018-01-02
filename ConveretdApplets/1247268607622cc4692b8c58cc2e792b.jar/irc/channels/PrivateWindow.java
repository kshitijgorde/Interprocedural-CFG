// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import com.eaio.nativecall.IntCall;
import com.eaio.util.lang.NativeLoader;
import java.net.URL;
import java.awt.event.WindowEvent;
import irc.managers.CONF;
import javax.swing.BorderFactory;
import irc.com.utils.MD5Nick;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Cursor;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import irc.managers.CHANNELS;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import irc.channels.textarea.NewTextMotionListener;
import irc.channels.textarea.NewTextClickListener;
import javax.swing.text.StyledDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.AdjustmentEvent;
import javax.swing.ImageIcon;
import java.util.StringTokenizer;
import irc.files.FileTransferManager;
import javax.swing.JFileChooser;
import irc.com.utils.IEAutoStart;
import java.awt.Component;
import javax.swing.JOptionPane;
import irc.Wizz;
import javax.swing.Icon;
import irc.ASV;
import irc.main;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.util.Enumeration;
import irc.managers.avatar;
import java.awt.Color;
import irc.com.nick.NickInfos;
import javax.swing.text.SimpleAttributeSet;
import irc.managers.Resources;
import java.awt.Point;
import java.awt.Dimension;
import irc.channels.components.MyPvPanel;
import irc.channels.textarea.NewTextDocument;
import java.awt.Image;
import javax.swing.text.MutableAttributeSet;
import java.text.MessageFormat;
import javax.swing.JPopupMenu;
import irc.channels.components.PopupInfos;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import irc.channels.components.MyJButton;
import irc.channels.components.TextFieldHistory;
import irc.channels.components.smileys.SmileysMenu;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import irc.EIRC;
import java.awt.event.WindowStateListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import irc.channels.textarea.TextAreaEvent;
import java.awt.event.FocusListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseListener;
import irc.channels.textarea.HyperlinkTextAreaEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PrivateWindow extends JFrame implements ActionListener, KeyListener, HyperlinkTextAreaEvent, MouseListener, AdjustmentListener, FocusListener, TextAreaEvent, WindowListener, MouseMotionListener, WindowStateListener
{
    private EIRC eirc;
    private BorderLayout layout_zonetext;
    private BorderLayout bottomtools_layout;
    private GridLayout footerlayout;
    private JToolBar toptools1;
    private JPanel zone_text;
    private Container Global;
    private JLabel titre;
    private JPanel bottomtools;
    private JPanel footer;
    private JButton police;
    private JButton grouper;
    private JButton close;
    private JToggleButton sound;
    private JButton smileys;
    private SmileysMenu smileymenu;
    private JToggleButton interlignes;
    private JButton wizz;
    private JButton profile;
    private JButton myavatars;
    private JButton add_freind;
    private JButton block;
    private JButton info1;
    private JButton plainte;
    private JButton sendfile;
    private JButton historique;
    private TextFieldHistory entry;
    private MyJButton send;
    private JScrollPane scrollpanel;
    private JTextPane textzone;
    private PopupInfos nickinfos;
    private JPanel Gpanel;
    private JLabel mper;
    private AvatarPanel avatarpanel;
    private MesAvatars mesavatars;
    private JPopupMenu avatarmenu;
    private MessageFormat private_title1;
    private MessageFormat private_title2;
    private MutableAttributeSet attributSet;
    private int fgColor;
    private int bgColor;
    private boolean sendWizz;
    private long EnvTyping;
    private String user;
    private String user_md5;
    private String user_md5_md5;
    private Image avatar1;
    private boolean waiting;
    private NewTextDocument doc;
    private boolean connecter;
    private String messageperso;
    private int nbwizz;
    private MyPvPanel myjmainpanel;
    private Dimension dim;
    private JLabel message_lu;
    private boolean mess;
    private boolean MessActive;
    boolean cc;
    int oldvalue;
    Point loct;
    int i;
    boolean haut;
    boolean movex;
    boolean movey;
    boolean coint1;
    boolean coint2;
    boolean coint3;
    boolean coint4;
    boolean moveframe;
    boolean resizeframe;
    int moveframx;
    int moveframy;
    int resizeframx;
    int resizeframy;
    private boolean typingencour;
    
    public PrivateWindow(final EIRC eirc, final String text) {
        this.avatarmenu = new JPopupMenu();
        this.private_title1 = new MessageFormat(Resources.getStringEirc("top_panel.private"));
        this.private_title2 = new MessageFormat(Resources.getStringEirc("top_panel.private1"));
        this.attributSet = new SimpleAttributeSet();
        this.fgColor = 1;
        this.bgColor = 0;
        this.sendWizz = true;
        this.EnvTyping = 0L;
        this.connecter = true;
        this.messageperso = "";
        this.nbwizz = 0;
        this.myjmainpanel = new MyPvPanel();
        this.dim = null;
        this.mess = true;
        this.MessActive = false;
        this.cc = true;
        this.oldvalue = 0;
        this.loct = null;
        this.i = 0;
        this.haut = false;
        this.movex = false;
        this.movey = false;
        this.coint1 = false;
        this.coint2 = false;
        this.coint3 = false;
        this.coint4 = false;
        this.moveframe = false;
        this.resizeframe = false;
        this.moveframx = 0;
        this.moveframy = 0;
        this.resizeframx = 0;
        this.resizeframy = 0;
        this.typingencour = false;
        this.setTitle(text);
        (this.eirc = eirc).sendCommand("watch +" + text, null);
        if (text.equalsIgnoreCase("news")) {
            this.setIconImage(Resources.getImages("news"));
        }
        else if (text.equalsIgnoreCase("horoscope")) {
            this.setIconImage(Resources.getImages("horoscope"));
        }
        else if (NickInfos.getSex(text) == 1) {
            this.setIconImage(Resources.getImages("minlogoph"));
        }
        else {
            this.setIconImage(Resources.getImages("minlogopf"));
        }
        this.setMaximumSize(new Dimension(1500, 1500));
        this.user = text;
        this.setMinimumSize(new Dimension(580, 400));
        this.setDefaultCloseOperation(0);
        this.addWindowListener(this);
        this.setContentPane(this.myjmainpanel);
        (this.Global = new Container()).addMouseListener(this);
        this.Global.addMouseMotionListener(this);
        this.setBackground(Color.decode("0xADD8E6"));
        this.titre = new JLabel();
        final JLabel titre = this.titre;
        final EIRC eirc2 = this.eirc;
        titre.setForeground(EIRC.mainfg);
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.titre.setText(text);
        this.doc = new NewTextDocument(this.eirc, this);
        if (this.eirc.isRegister()) {
            final Image avatar = NickInfos.getAvatar(this.user);
            if (avatar == null) {
                this.setAvatar(irc.managers.avatar.avatarloading);
                new GetPvPhotos();
            }
            else {
                new GetMessageperso();
                this.setAvatar(avatar);
            }
        }
        else {
            this.setAvatar(avatar.avatarnonvip);
        }
        if (!NickInfos.hasInfos(this.user.toLowerCase())) {
            this.eirc.sendMessage("MYWHO", new String[] { this.user });
        }
        this.LoadPv();
        this.couleur(this.eirc.getPolice().getCouleur1(), this.eirc.getPolice().getCouleur2(), this.eirc.getPolice().getFonte());
        this.setVisible(false);
        if (!this.eirc.isIsgrouppv() && this.eirc.getPrivates().privates.size() >= 6) {
            final Enumeration<PrivateWindow> elements = (Enumeration<PrivateWindow>)this.eirc.getPrivates().privates.elements();
            while (elements.hasMoreElements()) {
                final PrivateWindow privateWindow = elements.nextElement();
                privateWindow.groupersetIcon("icondegrouperclipv");
                privateWindow.repaint();
            }
            this.groupersetIcon("icondegrouperclipv");
            this.repaint();
        }
        this.addWindowStateListener(this);
        if (!this.getUser().equalsIgnoreCase("news") && !this.getUser().equalsIgnoreCase("Horoscope") && !this.getUser().equalsIgnoreCase("blague") && this.eirc.getCreation().contains_name(this.getUser()) && this.eirc.actif_historique) {
            this.historique.setVisible(true);
            final Vector extraire = this.eirc.getCreation().extraire(this.user);
            if (extraire.size() > 10) {
                for (int i = extraire.size() - 10; i < extraire.size(); ++i) {
                    final String replaceFirst = extraire.get(i).replaceFirst(":", "(").replaceFirst(";", ")");
                    this.printPrivmsg(replaceFirst.substring(replaceFirst.indexOf("(")), replaceFirst.substring(0, replaceFirst.indexOf("(")).trim());
                }
            }
            else {
                for (int j = 0; j < extraire.size(); ++j) {
                    final String replaceFirst2 = extraire.get(j).replaceFirst(":", "(").replaceFirst(";", ")");
                    this.printPrivmsg(replaceFirst2.substring(replaceFirst2.indexOf("(")), replaceFirst2.substring(0, replaceFirst2.indexOf("(")).trim());
                }
            }
        }
        else {
            this.historique.setVisible(false);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        this.LoadInfoBarre(this.getUser());
        final Object source = actionEvent.getSource();
        if (source.equals(this.send) || source.equals(this.entry)) {
            if (main.isnotasv) {
                main.isnotasv = false;
                new ASV(this, this.eirc);
            }
            String s;
            for (s = this.entry.getText(); s.startsWith(" "); s = s.substring(1)) {}
            String string = "";
            if (s.length() <= 0) {
                return;
            }
            if (s.trim().toLowerCase().startsWith("/nick ")) {
                this.entry.setText("");
                return;
            }
            if (s.trim().toLowerCase().startsWith("/q ")) {
                this.entry.setText("");
                return;
            }
            if (s.charAt(0) == '/') {
                final String substring = s.substring(1);
                if (substring.trim().length() > 0) {
                    this.eirc.sendCommand(substring, this.getDoc());
                }
            }
            else {
                if (s.length() > 450) {
                    s = s.substring(0, 449);
                }
                if (this.eirc.isBooleangras()) {
                    string += '\u0002';
                }
                final String string2 = string + "\u0003" + this.fgColor + "," + this.bgColor + " " + s;
                if (this.eirc.checkUrl(s)) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getUser(), string2 });
                }
                this.setMess(false);
                this.printPrivmsg(string2, this.eirc.getNick());
                this.zone_text.repaint();
                if (!NickInfos.getLocation(this.getUser()).equals("m") && this.eirc.actif_historique) {
                    new ActionHis(this.getUser(), s, this.eirc.getNick());
                }
            }
            this.entry.setText("");
            this.EntryrequestFocus();
        }
        if (source.equals(this.info1)) {
            this.eirc.sendMessage("WHOIS", new String[] { this.getUser() });
            return;
        }
        if (source.equals(this.police)) {
            this.eirc.showPolice();
            this.EntryrequestFocus();
            return;
        }
        if (source.equals(this.close)) {
            this.eirc.getPrivates().ClosePrivate(this.getUser().toLowerCase());
        }
        if (source.equals(this.grouper)) {
            if (!this.eirc.isIsgrouppv()) {
                this.eirc.getPvgroup().action();
            }
            else {
                this.eirc.getPvgroup().degroup();
            }
            return;
        }
        if (source.equals(this.sound)) {
            if (this.sound.isSelected()) {
                this.sound.setBackground(this.eirc.getMainbg());
                this.sound.setIcon(EIRC.soundoff);
                this.eirc.setSoundOff();
            }
            else {
                this.sound.setIcon(EIRC.soundon);
                this.eirc.setSoundOn();
            }
            return;
        }
        if (source.equals(this.interlignes)) {
            if (this.interlignes.isSelected()) {
                final Enumeration<PrivateWindow> elements = (Enumeration<PrivateWindow>)this.eirc.getPrivates().privates.elements();
                while (elements.hasMoreElements()) {
                    final PrivateWindow privateWindow = elements.nextElement();
                    privateWindow.interlignes(1.0f);
                    privateWindow.interlignes.setSelected(true);
                }
                EIRC.interlignes = 1.0f;
            }
            else {
                final Enumeration<PrivateWindow> elements2 = (Enumeration<PrivateWindow>)this.eirc.getPrivates().privates.elements();
                while (elements2.hasMoreElements()) {
                    final PrivateWindow privateWindow2 = elements2.nextElement();
                    privateWindow2.interlignes(0.2f);
                    privateWindow2.interlignes.setSelected(false);
                }
                EIRC.interlignes = 0.2f;
            }
            return;
        }
        if (source.equals(this.wizz)) {
            if (this.eirc.isRegister()) {
                if (this.isSendWizz()) {
                    this.eirc.sendMessage("PRIVMSG", new String[] { this.getUser(), "\u0001ACTION [wizz]\u0001" });
                    this.setSendWizz(false);
                    this.eirc.playSound(18);
                    if (!this.eirc.isIsgrouppv()) {
                        Wizz.vibre(this, this.eirc);
                    }
                    else {
                        this.eirc.getPvgroup().Showpv(this.getUser().toLowerCase());
                        Wizz.vibre(this.eirc.getPvgroup(), this.eirc);
                    }
                    new Waitingwizz();
                    this.eirc.sendMessage("PRIVMSG", new String[] { "#applet-wizz", "envoi un wizz \u00e0 " + this.getUser() });
                }
                else {
                    this.getDoc().printInfo("N'abusez pas du Wizz!");
                }
            }
            else {
                final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                if (JOptionPane.showOptionDialog(this.eirc.getFrame(), "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    IEAutoStart.run("http://vip.chat-land.org/");
                }
            }
            return;
        }
        if (source.equals(this.myavatars)) {}
        if (source.equals(this.sendfile)) {
            if (this.getUser().equals(this.eirc.getNick())) {
                JOptionPane.showMessageDialog(null, "Vous ne pouvez pas envoyer un fichier \u00e0 vous m\u00eame");
            }
            else {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Envoi d'un fichier ou une photo \u00e0 " + this.getUser());
                if (fileChooser.showOpenDialog(this) == 1) {
                    return;
                }
                FileTransferManager.addSender(fileChooser, this.getUser());
            }
        }
        if (source.equals(this.profile)) {
            this.eirc.showProfile(this.getUser(), this.getDoc());
        }
        if (source.equals(this.plainte)) {
            final String showInputDialog = JOptionPane.showInputDialog(this.eirc.getFrame(), "Merci de bien vouloir indiquer la raison de votre plainte:", "Signaler une plainte contre " + this.getUser());
            if (showInputDialog.equals("")) {
                JOptionPane.showMessageDialog(this.eirc.getFrame(), "Vous devez indiquer la raison de votre plainte.", "Chat-Land", 1);
                return;
            }
            if (showInputDialog == null) {
                return;
            }
            final String[] array2 = { "#plaintes", null };
            final String string3 = "***************** Plainte de " + this.eirc.getNick() + " sur " + this.getUser() + " Raison:" + showInputDialog + " ******************";
            array2[1] = string3;
            this.eirc.sendMessage("PRIVMSG", array2);
            final StringTokenizer stringTokenizer = new StringTokenizer(this.textzone.getText(), "\n");
            if (stringTokenizer.countTokens() <= 10) {
                while (stringTokenizer.hasMoreTokens()) {
                    String s2 = stringTokenizer.nextToken();
                    if (s2.indexOf(">") != -1) {
                        array2[1] = s2.substring(0, s2.indexOf(">"));
                        s2 = s2.substring(s2.indexOf(">"));
                    }
                    else {
                        array2[1] = "";
                    }
                    for (int i = 0; i < s2.length(); ++i) {
                        if (i % 5 == 4) {
                            array2[1] = array2[1] + '\u0003' + "0 *" + '\u000f';
                        }
                        array2[1] += s2.charAt(i);
                    }
                    if (array2[1].indexOf(">") != -1) {
                        this.eirc.sendMessage("PRIVMSG", array2);
                    }
                }
            }
            else {
                for (int j = 0; j < stringTokenizer.countTokens() - 6; ++j) {
                    stringTokenizer.nextToken();
                }
                while (stringTokenizer.hasMoreTokens()) {
                    String s3 = stringTokenizer.nextToken();
                    if (s3.indexOf(">") != -1) {
                        array2[1] = s3.substring(0, s3.indexOf(">"));
                        s3 = s3.substring(s3.indexOf(">"));
                    }
                    else {
                        array2[1] = "";
                    }
                    for (int k = 0; k < s3.length(); ++k) {
                        if (k % 5 == 4) {
                            array2[1] += "*";
                        }
                        array2[1] += s3.charAt(k);
                    }
                    if (array2[1].indexOf(">") != -1) {
                        this.eirc.sendMessage("PRIVMSG", array2);
                    }
                }
            }
            final int length = string3.length();
            String string4 = "";
            for (int l = 0; l < length; ++l) {
                string4 += "-";
            }
            array2[1] = string4;
            this.eirc.sendMessage("PRIVMSG", array2);
        }
        if (source.equals(this.add_freind)) {
            this.eirc.add_friend(this.getUser());
            return;
        }
        if (source.equals(this.block)) {
            if (this.eirc.cmd_ignore(this.getUser().toLowerCase(), this.getDoc()).equals("true")) {
                if (this.block.getText().equals("Bloquer")) {
                    this.block.setText("D\u00e9bloquer");
                    this.block.setIcon(new ImageIcon(Resources.getImages("pv.icon.dein")));
                }
                else {
                    this.block.setText("Bloquer");
                    this.block.setIcon(new ImageIcon(Resources.getImages("pv.icon.kill")));
                }
            }
            else if (this.eirc.cmd_ignore(this.getUser().toLowerCase(), this.getDoc()).equals("false")) {
                this.eirc.getCurrentPanel().printInfo("N'abusez pas du syst\u00e9me bloquer/d\u00e9bloquer s.v.p !");
            }
            return;
        }
        if (source.equals(this.historique)) {
            this.historique();
        }
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.LoadInfoBarre(this.getUser());
        try {
            if (100 * adjustmentEvent.getValue() / (this.scrollpanel.getVerticalScrollBar().getMaximum() - this.scrollpanel.getVerticalScrollBar().getVisibleAmount() - this.scrollpanel.getVerticalScrollBar().getMinimum()) != 100 && this.scrollpanel.getVerticalScrollBar().getValue() < this.oldvalue) {
                return;
            }
        }
        catch (Exception ex) {}
        this.oldvalue = this.scrollpanel.getVerticalScrollBar().getValue();
        this.scrollpanel.getVerticalScrollBar().setValue(this.scrollpanel.getVerticalScrollBar().getMaximum());
    }
    
    @Override
    public void autoAway() {
        this.eirc.revenir();
    }
    
    private void buildCommandMenu() {
    }
    
    private void buildFooterPanel() {
        (this.toptools1 = new JToolBar()).setFloatable(false);
        (this.police = new JButton(new ImageIcon(Resources.getImages("frame.police")))).setToolTipText("Modifier police");
        this.grouper = new JButton();
        (this.message_lu = new JLabel("")).addMouseListener(this);
        this.message_lu.setIcon(new ImageIcon(Resources.getImages("nonvipnontique")));
        if (this.eirc.isIsgrouppv()) {
            this.grouper.setIcon(new ImageIcon(Resources.getImages("icondegrouperpv")));
            this.grouper.setToolTipText("Degrouper toutes les discussions priv\u00e9es ");
        }
        else {
            this.grouper.setIcon(new ImageIcon(Resources.getImages("iconregrouperpv")));
            this.grouper.setToolTipText("Regrouper toutes les discussions priv\u00e9es dans une seule fen\u00eatre ");
        }
        (this.close = new JButton(new ImageIcon(Resources.getImages("pv.icon.close")))).setText("Fermer");
        this.close.setToolTipText("Cliquez ici pour fermer la discussion en cours");
        if (!this.eirc.isSilent()) {
            this.sound = new JToggleButton(EIRC.soundon);
        }
        else {
            this.sound = new JToggleButton(EIRC.soundoff);
        }
        this.sound.setToolTipText("Activer/D\u00e9sactiver le son");
        (this.smileys = new JButton(new ImageIcon(Resources.getImages("button.smileys")))).setToolTipText("Ins\u00e9rer une \u00e9motic\u00f4ne");
        (this.interlignes = new JToggleButton(new ImageIcon(EIRC.class.getResource("gui/interligne.png")))).setToolTipText("Mettre un espace entre chaque phrase");
        (this.wizz = new JButton(new ImageIcon(EIRC.class.getResource("gui/wizz.png")))).setToolTipText("Envoyer un Wizz");
        (this.profile = new JButton(new ImageIcon(Resources.getImages("pv.icon.show_profile")))).setToolTipText("Cliquez ici pour afficher le profil");
        (this.myavatars = new JButton(new ImageIcon(Resources.getImages("button.avatar")))).setToolTipText("Cliquez ici pour changer votre photo.");
        this.block = new JButton(new ImageIcon(Resources.getImages("pv.icon.kill")));
        (this.info1 = new JButton(new ImageIcon(Resources.getImages("pv.icon.command")))).setToolTipText("Plus d'infos concernant votre correspondant");
        this.block.setToolTipText("Cliquez ici pour bloquer/d\u00e9bloquer ce pseudo.");
        (this.add_freind = new JButton(new ImageIcon(Resources.getImages("pv.icon.add_friend")))).setToolTipText("Ajouter \u00e0 ma liste d'amis.");
        (this.plainte = new JButton(new ImageIcon(Resources.getImages("pv.icon.signaler")))).setToolTipText("Cliquez ici pour signaler une plainte.");
        (this.sendfile = new JButton(new ImageIcon(Resources.getImages("button.sendfile")))).setToolTipText("Cliquez sur ce bouton pour envoyer un fichier");
        (this.historique = new JButton(new ImageIcon(Resources.getImages("icon_historique")))).setToolTipText("Cliquez ici pour consulter l'historique de conversation");
        this.historique.addActionListener(this);
        this.historique.setVisible(false);
        this.police.addActionListener(this);
        this.grouper.addActionListener(this);
        this.sound.addActionListener(this);
        this.interlignes.addActionListener(this);
        this.wizz.addActionListener(this);
        this.profile.addActionListener(this);
        this.info1.addActionListener(this);
        this.add_freind.addActionListener(this);
        this.plainte.addActionListener(this);
        this.sendfile.addActionListener(this);
        this.block.addActionListener(this);
        this.close.addActionListener(this);
        this.bottomtools_layout = new BorderLayout();
        this.bottomtools = new JPanel(this.bottomtools_layout);
        this.entry = new TextFieldHistory(this.eirc);
        this.send = new MyJButton(avatar.MybackGround, "Envoyer", null, 0);
        final JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setOpaque(false);
        this.send.setOpaque(false);
        toolBar.add(this.send);
        this.bottomtools.add(this.entry, "Center");
        this.bottomtools.add(toolBar, "East");
        this.send.addActionListener(this);
        this.entry.addKeyListener(this);
        this.entry.addActionListener(this);
        this.entry.addMouseListener(this);
        if (!this.user.equalsIgnoreCase("news") && !this.user.equalsIgnoreCase("horoscope")) {
            this.footerlayout = new GridLayout(2, 1);
            (this.footer = new JPanel(this.footerlayout)).add(this.toptools1);
            this.footer.add(this.bottomtools);
        }
        else {
            this.footerlayout = new GridLayout(1, 1);
            (this.footer = new JPanel(this.footerlayout)).add(this.toptools1);
        }
        (this.nickinfos = new PopupInfos("")).setVisible(false);
        this.sound.setSelected(this.eirc.isSilent());
    }
    
    @Override
    public void Click() {
    }
    
    public void colorBackground(final Color background) {
        this.close.setBackground(background);
        this.historique.setBackground(background);
        this.toptools1.setBackground(background);
        this.zone_text.setBackground(background);
        this.bottomtools.setBackground(background);
        this.footer.setBackground(background);
        this.police.setBackground(background);
        this.sound.setBackground(background);
        this.smileys.setBackground(background);
        this.smileymenu.setBackground(background);
        this.smileymenu.setBackground(background);
        this.interlignes.setBackground(background);
        this.wizz.setBackground(background);
        this.profile.setBackground(background);
        this.myavatars.setBackground(background);
        this.block.setBackground(background);
        this.add_freind.setBackground(background);
        this.plainte.setBackground(background);
        this.sendfile.setBackground(background);
        this.send.setBackground(background);
        this.info1.setBackground(background);
        this.repaint();
    }
    
    public void colorForeground(final Color foreground) {
        this.close.setForeground(foreground);
        this.toptools1.setForeground(foreground);
        this.zone_text.setForeground(foreground);
        this.bottomtools.setForeground(foreground);
        this.footer.setForeground(foreground);
        this.police.setForeground(foreground);
        this.sound.setForeground(foreground);
        this.smileys.setForeground(foreground);
        this.smileymenu.setForeground(foreground);
        this.smileymenu.colorForeground(foreground);
        this.interlignes.setForeground(foreground);
        this.wizz.setForeground(foreground);
        this.profile.setForeground(foreground);
        this.myavatars.setForeground(foreground);
        this.block.setForeground(foreground);
        this.add_freind.setForeground(foreground);
        this.plainte.setForeground(foreground);
        this.sendfile.setForeground(foreground);
        this.send.setForeground(foreground);
        this.info1.setForeground(foreground);
    }
    
    public void couleur(final int fgColor, final int bgColor, final Font font) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.textzone.moveCaretPosition(this.textzone.getDocument().getLength());
        this.entry.setForeground(EIRC.fixedColors[fgColor]);
        this.entry.setBackground(EIRC.fixedColors[bgColor]);
        this.textzone.select(this.textzone.getDocument().getLength(), this.textzone.getDocument().getLength());
    }
    
    public void current() {
        this.eirc.getChat_panel().setCurrent(this);
        this.eirc.getChat_panel().setCurrentname(this.getUser());
    }
    
    public void disposePrivate() {
        try {
            this.doc.remove(0, this.doc.getLength());
        }
        catch (BadLocationException ex) {}
        this.doc = null;
        this.user = null;
        this.user_md5 = null;
        this.user_md5_md5 = null;
        this.avatar1 = null;
    }
    
    @Override
    public void doubleClick() {
        this.eirc.revenir();
        this.eirc.sendMessage("WHOIS", new String[] { this.getUser() });
    }
    
    public void EntryrequestFocus() {
        if (this.entry != null) {
            this.entry.requestFocus();
        }
    }
    
    public void flash() {
        new flash();
    }
    
    @Override
    public void focusGained(final FocusEvent focusEvent) {
        if (this.eirc != null) {
            this.eirc.sendMessage("PRIVMSG", new String[] { this.getUser(), "\u0001ACTION [m]\u0001" });
        }
    }
    
    @Override
    public void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getOppositeComponent() != null && this.eirc != null) {
            if (focusEvent.getOppositeComponent().equals(this.eirc.getFramemanager())) {
                this.EntryrequestFocus();
            }
            else if (focusEvent.getOppositeComponent().getName() != null && focusEvent.getOppositeComponent().getName().equals("PrivateWindow") && !focusEvent.getOppositeComponent().equals(this.textzone)) {
                this.EntryrequestFocus();
            }
        }
    }
    
    public void free() {
        this.layout_zonetext = null;
        this.bottomtools_layout = null;
        this.footerlayout = null;
        this.toptools1 = null;
        this.zone_text = null;
        this.bottomtools = null;
        this.footer = null;
        this.police = null;
        this.sound = null;
        this.smileys = null;
        this.smileymenu.free();
        this.smileymenu = null;
        this.interlignes = null;
        this.wizz = null;
        this.myavatars = null;
        this.block = null;
        this.sendfile = null;
        this.entry = null;
        this.send = null;
        this.profile = null;
        this.plainte = null;
        this.add_freind = null;
        this.attributSet = null;
        this.nickinfos.removeAll();
        this.nickinfos = null;
        this.avatarmenu.removeAll();
        this.avatarmenu = null;
        if (this.mesavatars != null) {
            this.mesavatars.removeAll();
            this.mesavatars = null;
        }
        this.avatarpanel.removeAll();
        this.avatarpanel = null;
        this.scrollpanel.removeAll();
        this.scrollpanel = null;
        this.textzone.removeAll();
        this.textzone = null;
        this.Global.removeAll();
        this.Global = null;
        this.myjmainpanel.removeAll();
        this.myjmainpanel = null;
        this.getContentPane().removeAll();
        this.removeAll();
        this.eirc = null;
    }
    
    public Image getAvatar() {
        return this.avatar1;
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
    
    public Container getGlobal() {
        return this.Global;
    }
    
    public String getMessageperso() {
        return this.messageperso;
    }
    
    public int getNbwizz() {
        return this.nbwizz;
    }
    
    public JToggleButton getSound() {
        return this.sound;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String getUser_md5() {
        return this.user_md5;
    }
    
    public String getUser_md5_md5() {
        return this.user_md5_md5;
    }
    
    public JPanel getZone_text() {
        return this.zone_text;
    }
    
    public void groupersetIcon(final String s) {
        this.grouper.setIcon(new ImageIcon(Resources.getImages(s)));
    }
    
    public void groupertool(final String toolTipText) {
        this.grouper.setToolTipText(toolTipText);
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
    
    public void historique() {
        if (this.eirc.actif_historique) {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = this.eirc.historique.getSize();
            if (size.height > screenSize.height) {
                size.height = screenSize.height;
            }
            if (size.width > screenSize.width) {
                size.width = screenSize.width;
            }
            this.eirc.historique.setLocation((screenSize.width - size.width) / 3 + 50, (screenSize.height - size.height) / 3);
            this.eirc.historique.init(this.getUser(), this.eirc.getCreation());
        }
    }
    
    public void insertSmiley(final String s) {
        this.entry.setText(this.entry.getText() + s);
        this.entry.setCaretPosition(this.entry.getText().length());
        this.EntryrequestFocus();
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
        this.EntryrequestFocus();
    }
    
    public boolean isConnecter() {
        return this.connecter;
    }
    
    public boolean isMess() {
        return this.mess;
    }
    
    public boolean isMessActive() {
        return this.MessActive;
    }
    
    public boolean isSendWizz() {
        return this.sendWizz;
    }
    
    public boolean isWaiting() {
        return this.waiting;
    }
    
    private void jbInit() throws Exception {
        this.layout_zonetext = new BorderLayout();
        (this.zone_text = new JPanel()).setLayout(this.layout_zonetext);
        (this.scrollpanel = new JScrollPane()).setOpaque(true);
        this.textzone = new JTextPane();
        if (!this.user.equalsIgnoreCase("news") && !this.user.equalsIgnoreCase("horoscope")) {
            this.textzone.setName("PrivateWindow");
        }
        this.textzone.setBackground(Color.white);
        this.textzone.setEditable(false);
        this.textzone.addMouseListener(new NewTextClickListener(this, this.textzone));
        this.textzone.addMouseMotionListener(new NewTextMotionListener(this, this.textzone));
        this.textzone.setToolTipText("Double clique sur cette zone permet d'afficher plus d'infos sur la personne.");
        this.scrollpanel.getViewport().add(this.textzone);
        this.scrollpanel.setAutoscrolls(true);
        this.scrollpanel.getVerticalScrollBar().addAdjustmentListener(this);
        this.scrollpanel.getVerticalScrollBar().addMouseListener(this);
        this.smileymenu = new SmileysMenu(this);
        this.avatarpanel = new AvatarPanel();
        this.buildCommandMenu();
        this.buildFooterPanel();
        (this.Gpanel = new JPanel(new BorderLayout())).setOpaque(false);
        this.zone_text.setOpaque(false);
        this.scrollpanel.setOpaque(true);
        (this.mper = new JLabel()).setFont(new Font("Dialog", 0, 16));
        this.mper.setForeground(Color.BLACK);
        this.Gpanel.add(this.mper, "North");
        this.Gpanel.add(this.scrollpanel, "Center");
        this.zone_text.add(this.Gpanel, "Center");
        this.footer.setOpaque(false);
        this.avatarpanel.setOpaque(false);
        this.toptools1.setOpaque(false);
        this.bottomtools.setOpaque(false);
        this.titre.setPreferredSize(new Dimension(0, 30));
        this.titre.setFont(new Font("Calibri", 0, 18));
        this.titre.setForeground(Color.BLACK);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.Global.setLayout(layout);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(this.titre, gridBagConstraints);
        this.Global.add(this.titre);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        layout.setConstraints(this.zone_text, gridBagConstraints);
        this.Global.add(this.zone_text);
        this.avatarpanel.setPreferredSize(new Dimension(130, 0));
        this.avatarpanel.setMinimumSize(new Dimension(130, 0));
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        if (!this.user.equalsIgnoreCase("news") && !this.user.equalsIgnoreCase("horoscope")) {
            layout.setConstraints(this.avatarpanel, gridBagConstraints);
            this.Global.add(this.avatarpanel);
        }
        else {
            final JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(10, 0));
            layout.setConstraints(label, gridBagConstraints);
            this.Global.add(label);
        }
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        if (this.user.equalsIgnoreCase("news") || this.user.equalsIgnoreCase("horoscope")) {
            this.toptools1.add(this.police);
            this.toptools1.add(this.sound);
            this.toptools1.add(this.interlignes);
            this.toptools1.add(this.close);
        }
        else {
            this.toptools1.add(this.message_lu);
            this.toptools1.add(this.police);
            this.toptools1.add(this.sound);
            this.toptools1.add(this.smileys);
            this.toptools1.add(this.interlignes);
            this.toptools1.add(this.wizz);
            this.toptools1.add(this.profile);
            this.toptools1.add(this.myavatars);
            this.toptools1.add(this.add_freind);
            this.toptools1.add(this.sendfile);
            this.toptools1.add(this.info1);
            this.toptools1.add(this.plainte);
            this.toptools1.add(this.block);
            this.toptools1.add(this.historique);
            this.toptools1.add(this.close);
        }
        layout.setConstraints(this.footer, gridBagConstraints);
        this.Global.add(this.footer);
        this.myavatars.addMouseListener(this);
        this.smileys.addMouseListener(this);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        this.setSize(500, 450);
        if (this.eirc.getXprivateswindows() > 0 && this.eirc.getYprivateswindows() > 0) {
            this.setSize(this.eirc.getXprivateswindows(), this.eirc.getYprivateswindows());
        }
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        if (this.eirc.getXprivateswindows() > 0) {
            if (maximumWindowBounds.width == this.eirc.getXprivateswindows() && maximumWindowBounds.height == this.eirc.getYprivateswindows()) {
                this.loct = this.getLocation();
                this.dim = this.getSize();
                if (maximumWindowBounds.width > 0 && maximumWindowBounds.height > 0) {
                    this.setSize(maximumWindowBounds.width, maximumWindowBounds.height);
                }
                this.setLocation(0, 0);
                this.myjmainpanel.repaint();
                this.eirc.setXprivateswindows((int)this.getSize().getWidth());
                this.eirc.setYprivateswindows((int)this.getSize().getHeight());
            }
            else if (this.eirc.getXprivateswindows() > 0 && this.eirc.getYprivateswindows() > 0) {
                this.setSize(this.eirc.getXprivateswindows(), this.eirc.getYprivateswindows());
            }
        }
        final main accueil = this.eirc.getAccueil();
        accueil.frampos += 20;
        if (this.eirc.getAccueil().frampos > 100) {
            this.eirc.getAccueil().frampos = 0;
        }
        this.setLocation(this.eirc.getAccueil().frampos, this.eirc.getAccueil().frampos);
        this.myjmainpanel.setLayout(new BorderLayout());
        this.myjmainpanel.add(this.Global, "Center");
        this.message_lu.setOpaque(false);
        this.police.setOpaque(false);
        this.grouper.setOpaque(false);
        this.sound.setOpaque(false);
        this.smileys.setOpaque(false);
        this.interlignes.setOpaque(false);
        this.wizz.setOpaque(false);
        this.profile.setOpaque(false);
        this.myavatars.setOpaque(false);
        this.add_freind.setOpaque(false);
        this.sendfile.setOpaque(false);
        this.info1.setOpaque(false);
        this.plainte.setOpaque(false);
        this.block.setOpaque(false);
        this.historique.setOpaque(false);
        this.close.setOpaque(false);
        this.toptools1.setOpaque(false);
    }
    
    protected void joinChannel(final String s) {
        if (null == CHANNELS.getChannelWindow(s)) {
            this.eirc.sendMessage("JOIN", new String[] { s });
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        this.eirc.revenir();
        if (keyEvent.getKeyChar() == '\uffff') {
            return;
        }
        if (System.currentTimeMillis() - this.EnvTyping > 8000L) {
            this.EnvTyping = System.currentTimeMillis();
            this.eirc.sendMessage("PRIVMSG", new String[] { this.getUser(), "\u0001ACTION [y]\u0001" });
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    public void LoadInfoBarre(final String s) {
        if (NickInfos.getAge(s) != 0) {
            String s2;
            if (NickInfos.getSex(s) == 1) {
                s2 = "homme";
            }
            else {
                s2 = "femme";
            }
            String text = this.private_title1.format(new Object[] { s, s2, new Integer(NickInfos.getAge(s)), NickInfos.getLocation(s) });
            if (NickInfos.isNosex(s) == 1) {
                text += " (parler de sexe me d\u00e9range)";
            }
            if (NickInfos.getAway(s) != null) {
                text = text + "  [" + NickInfos.getAway(s) + "]";
            }
            this.titre.setText(text);
        }
        else {
            this.titre.setText(this.private_title2.format(new Object[] { s }));
        }
    }
    
    public void LoadPv() {
        this.textzone.setStyledDocument(this.getDoc());
        this.textzone.setCaretPosition(this.textzone.getStyledDocument().getLength());
        this.LoadInfoBarre(this.getUser());
        this.setUserAvatar(this.getAvatar());
        this.setMyAvatar();
        this.setMessagePerso(this.getMessageperso());
        if (this.eirc.getIgnore_list().contains(this.getUser().toLowerCase())) {
            this.block.setText("D\u00e9bloquer");
        }
        else {
            this.block.setText("Bloquer");
        }
        if (EIRC.interlignes == 1.0f) {
            this.interlignes.setSelected(true);
        }
        else {
            this.interlignes.setSelected(false);
        }
        this.interlignes(EIRC.interlignes);
        this.entry.addFocusListener(this);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.LoadInfoBarre(this.getUser());
        if (mouseEvent.getSource().equals(this.entry) && (mouseEvent.getButton() == 3 || mouseEvent.getModifiers() == 18)) {
            this.eirc.getAccueil().copypaste.setParent(this.entry);
            this.eirc.getAccueil().copypaste.refershItemsState();
            this.eirc.getAccueil().copypaste.show(this.entry, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.LoadInfoBarre(this.getUser());
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.LoadInfoBarre(this.getUser());
        this.eirc.revenir();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.LoadInfoBarre(this.getUser());
        this.eirc.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.LoadInfoBarre(this.getUser());
        this.eirc.revenir();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.LoadInfoBarre(this.getUser());
        if (mouseEvent.getSource().equals(this.smileys)) {
            if (this.smileymenu.isVisible()) {
                this.smileymenu.setVisible(false);
            }
            else {
                this.smileymenu.show(this.smileys, mouseEvent.getX() + 5, mouseEvent.getY() - 230);
            }
        }
        if (mouseEvent.getSource().equals(this.Global)) {
            this.EntryrequestFocus();
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.LoadInfoBarre(this.getUser());
        if (mouseEvent.getSource().equals(this.myavatars)) {
            if (this.eirc.isRegister()) {
                if (this.eirc.getMyphotos().size() > 0) {
                    if (this.mesavatars == null) {
                        this.mesavatars = new MesAvatars(this);
                        this.avatarmenu.removeAll();
                        this.avatarmenu.add(this.mesavatars);
                    }
                    this.avatarmenu.show(this.myavatars, mouseEvent.getX() + 10, mouseEvent.getY() - this.mesavatars.getHeight());
                }
                else {
                    JOptionPane.showMessageDialog(this, "Votre profil ne contient pas d'images\nPour ajouter des images \u00e0 votre profil allez sur http://www.chat-land.org/photos/", "Chat-Land.org", 1);
                }
            }
            else {
                final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                if (JOptionPane.showOptionDialog(this.eirc.getFrame(), "Vous devez \u00eatre vip pour avoir une liste d'avatars.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    IEAutoStart.run("http://vip.chat-land.org/");
                }
            }
        }
        if (mouseEvent.getSource().equals(this.Global)) {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.moveframe = false;
            this.resizeframe = false;
        }
    }
    
    @Override
    public void MouseReleased(final MouseEvent mouseEvent) {
        this.LoadInfoBarre(this.getUser());
        this.eirc.revenir();
        if (this.textzone.getSelectedText() != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.textzone.getSelectedText()), null);
        }
        else {
            this.EntryrequestFocus();
        }
    }
    
    public void NotifyPrivate(final String s) {
        if (this.eirc == null) {
            return;
        }
        this.eirc.getChat_panel().Notify(s);
    }
    
    @Override
    public void openPrivate(final String s) {
    }
    
    @Override
    public void popupCopyPaste(final MouseEvent mouseEvent) {
        this.eirc.getAccueil().copypaste.setParent(this.textzone);
        this.eirc.getAccueil().copypaste.refershItemsState();
        this.eirc.getAccueil().copypaste.show(this.textzone, mouseEvent.getX(), mouseEvent.getY());
    }
    
    @Override
    public void popupInfos(final String s, final String s2, final MouseEvent mouseEvent) {
        this.nickinfos.setText(NickInfos.getAge(s) + s2 + NickInfos.getLocation(s));
        if (NickInfos.getSex(s) == 1) {
            this.nickinfos.setBackground(Color.decode("0x0099FF"));
        }
        else {
            this.nickinfos.setBackground(Color.decode("0xFF00FF"));
        }
        this.nickinfos.setLocation(mouseEvent.getX() + 10, mouseEvent.getY() + 5);
        this.nickinfos.setVisible(true);
        this.textzone.add(this.nickinfos);
        this.textzone.repaint();
    }
    
    public void printAction(final String s, final String s2, final boolean b) {
        this.doc.printAction(s, s2);
    }
    
    public void printNotice(final String s, final String s2, final boolean b) {
        this.doc.printNotice(s, s2);
    }
    
    public void printPrivmsg(final String s, final String s2) {
        this.doc.printPrivmsg(s, s2);
    }
    
    public void refreshpvsize() {
        if (this.eirc.getXprivateswindows() > 0 && this.eirc.getYprivateswindows() > 0) {
            this.setSize(this.eirc.getXprivateswindows(), this.eirc.getYprivateswindows());
        }
    }
    
    public void scrollDown() {
        this.scrollpanel.getVerticalScrollBar().setValue(this.scrollpanel.getVerticalScrollBar().getMaximum());
    }
    
    public void setactive(final boolean visible) {
        if (!this.eirc.isIsgrouppv()) {
            this.setVisible(visible);
        }
    }
    
    public void setAvatar(final Image avatar1) {
        this.avatar1 = avatar1;
    }
    
    public void setchannelpane(final Container global) {
        if (this.Global != null) {
            this.myjmainpanel.remove(this.Global);
        }
        this.Global = global;
        this.myjmainpanel.setLayout(new BorderLayout());
        this.myjmainpanel.add(this.Global, "Center");
    }
    
    public void setConnecter(final boolean connecter) {
        this.connecter = connecter;
    }
    
    public void setDoc(final NewTextDocument doc) {
        this.doc = doc;
    }
    
    public void setMD5() {
        this.user_md5 = MD5Nick.getEncodedNick(this.user.toLowerCase());
        this.user_md5_md5 = MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.user.toLowerCase()));
    }
    
    public void setMess(final boolean mess) {
        this.mess = mess;
        if (mess) {
            this.SetMessV();
        }
        else {
            this.SetMessR();
        }
    }
    
    public void setMessActive(final boolean messActive) {
        this.MessActive = messActive;
    }
    
    public void setMessageperso(final String messageperso) {
        this.messageperso = messageperso;
    }
    
    public void setMessagePerso(final String s) {
        if (!s.equals("") && s.trim().length() > 1) {
            this.mper.setText(" " + s);
            this.mper.setBorder(BorderFactory.createLineBorder(Color.white));
            this.repaint();
        }
        else {
            this.mper.setBorder(BorderFactory.createEmptyBorder());
            this.mper.setText("");
        }
    }
    
    public void SetMessR() {
        this.message_lu.setToolTipText("Message envoy\u00e9 pas encore lu");
        this.message_lu.setOpaque(true);
        this.message_lu.setBackground(Color.red);
    }
    
    public void SetMessV() {
        this.message_lu.setToolTipText("Message envoy\u00e9 lu");
        this.message_lu.setOpaque(true);
        this.message_lu.setBackground(Color.green);
    }
    
    public void setminscroll() {
        this.scrollpanel.getVerticalScrollBar().setValue(0);
    }
    
    public void setMyAvatar() {
        if (this.eirc.getMyimage() == null) {
            if (NickInfos.getSex(this.eirc.getNick()) == 1) {
                this.eirc.setMyimage(avatar.avatarInconnuH);
            }
            else {
                this.eirc.setMyimage(avatar.avatarInconnuF);
            }
        }
        final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
        while (elements.hasMoreElements()) {
            final PrivateWindow privateWindow = elements.nextElement();
            privateWindow.avatarpanel.setBottomPhoto(this.eirc.getMyimage());
            privateWindow.avatarpanel.repaint();
        }
        this.avatarpanel.setBottomPhoto(this.eirc.getMyimage());
        this.avatarpanel.repaint();
    }
    
    public void setNbwizz(final int nbwizz) {
        this.nbwizz = nbwizz;
    }
    
    public void setSendWizz(final boolean sendWizz) {
        this.sendWizz = sendWizz;
    }
    
    public void setUser(final String user) {
        this.setTitle(this.user = user);
    }
    
    public void setUser_md5(final String user_md5) {
        this.user_md5 = user_md5;
    }
    
    public void setUser_md5_md5(final String user_md5_md5) {
        this.user_md5_md5 = user_md5_md5;
    }
    
    public void setUserAvatar(final Image topPhoto) {
        this.avatarpanel.setTopPhoto(topPhoto);
        this.avatarpanel.repaint();
    }
    
    public void setWaiting(final boolean waiting) {
        this.waiting = waiting;
    }
    
    @Override
    public void TextAdded(final String s) {
        if (s.trim().indexOf(">") != -1 && s.trim().substring(0, s.trim().indexOf(">")).indexOf(this.eirc.getNick()) != -1) {
            return;
        }
        this.NotifyPrivate(this.getUser());
        this.LoadInfoBarre(this.getUser());
        if (this.user.equalsIgnoreCase("news")) {
            this.typingPV("news");
        }
        else if (this.user.equalsIgnoreCase("horoscope")) {
            this.typingPV("horoscope");
        }
        else if (NickInfos.getSex(this.user) == 1) {
            this.typingPV("minlogoph");
        }
        else {
            this.typingPV("minlogopf");
        }
    }
    
    public void typing() {
        if (!this.typingencour) {
            new typing();
        }
    }
    
    public void updateAvatar() {
        new GetPvPhotos();
    }
    
    public void updateMperso() {
        this.setMessageperso(CONF.GetMperso(this.user));
        this.setMessagePerso(this.getMessageperso());
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
        if (this.eirc == null) {
            return;
        }
        this.eirc.getChat_panel().setCurrent(this);
        this.eirc.getChat_panel().setCurrentname(this.getUser());
        this.validate();
        this.repaint();
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        this.eirc.getPrivates().ClosePrivate(this.getUser().toLowerCase());
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.EntryrequestFocus();
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowStateChanged(final WindowEvent windowEvent) {
    }
    
    public void typingPV(final String s) {
        this.setIconImage(Resources.getImages(s));
    }
    
    class Waitingwizz extends Thread
    {
        Waitingwizz() {
            super("waiting wizz");
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(60000L);
                PrivateWindow.this.setSendWizz(true);
                try {
                    this.finalize();
                }
                catch (Throwable t) {}
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class typing extends Thread
    {
        public typing() {
            this.start();
        }
        
        @Override
        public void run() {
            PrivateWindow.this.typingencour = true;
            if (PrivateWindow.this.eirc.isIsgrouppv()) {
                PrivateWindow.this.eirc.getPvgroup().grouping(PrivateWindow.this.user);
            }
            else {
                PrivateWindow.this.typingPV("writingp");
                try {
                    Thread.sleep(7000L);
                }
                catch (InterruptedException ex) {}
                if (PrivateWindow.this.user.equalsIgnoreCase("news")) {
                    PrivateWindow.this.typingPV("news");
                }
                else if (PrivateWindow.this.user.equalsIgnoreCase("horoscope")) {
                    PrivateWindow.this.typingPV("horoscope");
                }
                else if (NickInfos.getSex(PrivateWindow.this.user) == 1) {
                    PrivateWindow.this.typingPV("minlogoph");
                }
                else {
                    PrivateWindow.this.typingPV("minlogopf");
                }
            }
            PrivateWindow.this.typingencour = false;
        }
    }
    
    class MesAvatars extends JToolBar implements ActionListener
    {
        PrivateWindow pws;
        
        public MesAvatars(final PrivateWindow pws) {
            this.pws = pws;
            if (this.pws.eirc.getMyphotos().size() > 1) {
                final Dimension optimalSize = this.getOptimalSize(this.pws.eirc.getMyphotos().size());
                this.setLayout(new GridLayout(optimalSize.width, optimalSize.height, 0, 0));
            }
            else {
                this.setLayout(new GridLayout(1, 1, 0, 0));
            }
            this.setFloatable(false);
            final Enumeration<Image> elements = (Enumeration<Image>)this.pws.eirc.getMyphotos().elements();
            final Enumeration<String> keys = this.pws.eirc.getMyphotos().keys();
            while (elements.hasMoreElements()) {
                final Image image = elements.nextElement();
                final String actionCommand = keys.nextElement();
                if (image != null) {
                    final JButton button = new JButton(new ImageIcon(image));
                    button.setActionCommand(actionCommand);
                    button.addActionListener(this);
                    button.setToolTipText("D\u00e9finir comme avatar");
                    this.add(button);
                }
            }
        }
        
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            PrivateWindow.this.eirc.revenir();
            if (actionEvent.getSource() instanceof JButton) {
                avatar.setMyDefaultAvatar(actionEvent.getActionCommand());
                this.pws.setMyAvatar();
                this.pws.avatarmenu.setVisible(false);
            }
        }
        
        private Dimension getOptimalSize(final int n) {
            final int n2 = (int)Math.ceil(Math.sqrt(n));
            int n3 = n2 - 1;
            int n4 = (int)Math.ceil(n / n3);
            if (n3 * n4 >= n2 * n2) {
                n3 = n2;
                n4 = n2;
            }
            return new Dimension(n3, n4);
        }
    }
    
    class GetPvPhotos extends Thread
    {
        public GetPvPhotos() {
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
            if (PrivateWindow.this.eirc.isRegister()) {
                if (NickInfos.isRegister(PrivateWindow.this.user)) {
                    PrivateWindow.this.setMessageperso(CONF.GetMperso(PrivateWindow.this.user));
                    PrivateWindow.this.setAvatar(avatar.getAvatar(PrivateWindow.this.user));
                }
                else {
                    if (NickInfos.getSex(PrivateWindow.this.user) == 1) {
                        PrivateWindow.this.setAvatar(avatar.avatarInconnuH);
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ex3) {}
                        PrivateWindow.this.setUserAvatar(avatar.avatarInconnuH);
                        return;
                    }
                    PrivateWindow.this.setAvatar(avatar.avatarInconnuF);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex4) {}
                    PrivateWindow.this.setUserAvatar(avatar.avatarInconnuF);
                    return;
                }
            }
            else {
                PrivateWindow.this.setAvatar(avatar.avatarnonvip);
            }
            try {
                PrivateWindow.this.setUserAvatar(PrivateWindow.this.getAvatar());
                PrivateWindow.this.setMessagePerso(PrivateWindow.this.getMessageperso());
            }
            catch (Exception ex) {
                System.out.println("pws.getCurrentdiscution().getUser():" + PrivateWindow.this.getUser());
                ex.printStackTrace();
            }
        }
    }
    
    class GetMessageperso extends Thread
    {
        URL url;
        
        public GetMessageperso() {
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            if (NickInfos.isRegister(PrivateWindow.this.user)) {
                PrivateWindow.this.setMessageperso(CONF.GetMperso(PrivateWindow.this.user));
                PrivateWindow.this.setMessagePerso(PrivateWindow.this.getMessageperso());
            }
        }
    }
    
    class flash extends Thread
    {
        public flash() {
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            if (!NativeLoader.isload()) {
                return;
            }
            try {
                final IntCall intCall = new IntCall("user32", "FindWindowA");
                final int executeCall = intCall.executeCall(new Object[] { null, PrivateWindow.this.getTitle() });
                intCall.destroy();
                final IntCall intCall2 = new IntCall("user32", "FlashWindow");
                intCall2.executeCall(new Object[] { new Integer(executeCall), new Integer(3) });
                intCall2.destroy();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    class ActionHis extends Thread
    {
        String user;
        String text;
        String nick;
        
        public ActionHis(final String user, final String text, final String nick) {
            this.user = user;
            this.text = text;
            this.nick = nick;
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            PrivateWindow.this.eirc.getCreation().principale(this.user, this.text, this.nick);
        }
    }
}
