// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.KeyEvent;
import irc.managers.CONF;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.AbstractButton;
import java.io.File;
import irc.main;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import irc.com.utils.IEAutoStart;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import irc.managers.Resources;
import java.awt.Frame;
import irc.EIRC;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import irc.channels.components.JLimitedTextField.JLimitedStringField;
import javax.swing.JLabel;
import irc.channels.components.JLimitedTextField.JLimitedIntField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.PopupMenuListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class Configurator extends JDialog implements ActionListener, WindowListener, KeyListener, FocusListener, PopupMenuListener
{
    private JTabbedPane centerpanel;
    private JPanel footer;
    private JButton close;
    private JButton validate;
    private JPanel generale;
    private GridBagLayout generale_layout;
    private JCheckBox wizz;
    private JCheckBox demarrage;
    private JCheckBox auto_away;
    private JLimitedIntField away_delay;
    private JLabel lbl_mperso;
    private JLabel lbl_away;
    private JLimitedStringField mperso;
    private String[] sounds;
    private JPanel Sound;
    private GridBagLayout sound_layout;
    private ButtonGroup SoundGroup;
    private JRadioButton Soundon;
    private JRadioButton Soundoff;
    private JRadioButton Soundoffaway;
    private JComboBox soundentree;
    private JComboBox soundsortie;
    private JComboBox soundpromotion;
    private JComboBox sounddechu;
    private JComboBox soundejecte;
    private JComboBox soundbanni;
    private JComboBox sounddeconnexion;
    private JComboBox soundmessageprive;
    private JComboBox soundmessagesalon;
    private JLabel lbl_entree;
    private JLabel lbl_sortie;
    private JLabel lbl_promotion;
    private JLabel lbl_decheance;
    private JLabel lbl_ejecte;
    private JLabel lbl_banni;
    private JLabel lbl_deconnexion;
    private JLabel lbl_messageprive;
    private JLabel lbl_messagesalon;
    private JPanel messages;
    private GridBagLayout message_layout;
    private JCheckBox no_privates;
    private JCheckBox see_join;
    private JCheckBox see_time;
    private JCheckBox Notice;
    private JCheckBox historique;
    private EIRC eirc;
    private boolean playsound;
    
    public Configurator(final EIRC eirc) {
        super(eirc.getFrame(), "Options", true);
        this.sounds = new String[] { "(Silence)", "Boooh.au", "Boop.au", "Click.au", "Clock.au", "Drip.au", "Hit.au", "Magic.au", "Pipe.au", "Pouet.au", "Rifle.au", "oiseau.au", "Squish.au", "oiseaux.au", "Warning.au", "Whistle.au", "Whoosh.au", "Yoo.au" };
        this.SoundGroup = new ButtonGroup();
        this.playsound = false;
        this.eirc = eirc;
        this.addWindowListener(this);
        this.setIconImage(Resources.getImages("minlogoa"));
        this.buildGeneralePanel();
        this.buildFooterPanel();
        this.buildSoundsPanel();
        this.buildMessagesPanel();
        this.centerpanel = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.centerpanel, "Center");
        this.getContentPane().add(this.footer, "South");
        this.centerpanel.addTab("G\u00e9n\u00e9ral", new ImageIcon(Resources.getImages("panel.icon.friends")), this.generale);
        this.centerpanel.addTab("Sons", new ImageIcon(Resources.getImages("conf.icon.snds")), this.Sound);
        this.centerpanel.addTab("Messages", new ImageIcon(Resources.getImages("option.message")), this.messages);
        this.setSize(500, 300);
        this.refresh();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getSource().equals(this.demarrage)) {
            this.validate.setEnabled(true);
            return;
        }
        if (actionEvent.getSource().equals(this.historique)) {
            this.eirc.actif_historique = this.historique.isSelected();
        }
        this.validate.setEnabled(true);
        if (this.away_delay.getText().equals("")) {
            this.away_delay.setText("5");
        }
        if (actionEvent.getSource().equals(this.auto_away)) {
            this.eirc.setAuto_away(this.auto_away.isSelected());
            if (this.auto_away.isSelected()) {
                this.away_delay.setEnabled(true);
            }
            else {
                this.away_delay.setEnabled(false);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.away_delay)) {
            this.eirc.setAway_delay(Integer.parseInt(this.away_delay.getText()));
            return;
        }
        if (actionEvent.getSource().equals(this.wizz)) {
            this.eirc.setAccept_wizz(this.wizz.isSelected());
            return;
        }
        if (actionEvent.getSource().equals(this.mperso)) {
            this.eirc.setM_perso(this.mperso.getText());
            return;
        }
        if (actionEvent.getSource().equals(this.Soundon)) {
            this.eirc.setSoundOn();
            return;
        }
        if (actionEvent.getSource().equals(this.Soundoff)) {
            this.eirc.setSoundOff();
            return;
        }
        if (actionEvent.getSource().equals(this.Soundoffaway)) {
            this.eirc.setSoundOffAway();
            return;
        }
        if (actionEvent.getSource().equals(this.soundentree)) {
            this.eirc.setSound_join(this.soundentree.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_join());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundsortie)) {
            this.eirc.setSound_part(this.soundsortie.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_part());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundpromotion)) {
            this.eirc.setSound_op(this.soundpromotion.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_op());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.sounddechu)) {
            this.eirc.setSound_deop(this.sounddechu.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_deop());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundejecte)) {
            this.eirc.setSound_kick(this.soundejecte.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_kick());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundbanni)) {
            this.eirc.setSound_ban(this.soundbanni.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_ban());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.sounddeconnexion)) {
            this.eirc.setSound_quit(this.sounddeconnexion.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_quit());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundmessageprive)) {
            this.eirc.setSound_prvmsg(this.soundmessageprive.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_prvmsg());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.soundmessagesalon)) {
            this.eirc.setSound_chan(this.soundmessagesalon.getSelectedIndex());
            if (this.playsound) {
                this.eirc.playSound(this.eirc.getSound_chan());
            }
            return;
        }
        if (actionEvent.getSource().equals(this.no_privates)) {
            this.eirc.setNo_privates(this.no_privates.isSelected());
            return;
        }
        if (actionEvent.getSource().equals(this.see_join)) {
            this.eirc.setSee_join(this.see_join.isSelected());
            return;
        }
        if (actionEvent.getSource().equals(this.see_time)) {
            this.eirc.setShowtime(this.see_time.isSelected());
            return;
        }
        if (actionEvent.getSource().equals(this.Notice)) {
            this.eirc.setAffichernotice(this.Notice.isSelected());
            return;
        }
        if (actionEvent.getSource().equals(this.close)) {
            this.setVisible(false);
            return;
        }
        if (actionEvent.getSource().equals(this.validate)) {
            if (this.eirc.isRegister()) {
                this.update();
                this.saveexcute();
                this.validate.setEnabled(false);
            }
            else {
                this.setVisible(false);
                final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                if (JOptionPane.showOptionDialog(this.eirc.getFrame(), "Vous devez \u00eatre VIP pour pouvoir enregistrer vos param\u00e8tres\nInscription sur:http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    IEAutoStart.run("http://vip.chat-land.org/");
                }
            }
        }
    }
    
    public void buildFooterPanel() {
        this.footer = new JPanel();
        (this.close = new JButton("Fermer")).addActionListener(this);
        (this.validate = new JButton("Enregistrer")).addActionListener(this);
        this.footer.add(this.validate);
        this.footer.add(this.close);
    }
    
    public void buildGeneralePanel() {
        this.generale = new JPanel();
        this.generale_layout = new GridBagLayout();
        this.generale.setLayout(this.generale_layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.lbl_mperso = new JLabel("Tapez le message perso sous lequel vous souhaitez appara\u00eetre:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        this.generale.add(this.lbl_mperso, gridBagConstraints);
        (this.mperso = new JLimitedStringField(128)).addActionListener(this);
        this.mperso.addKeyListener(this);
        this.mperso.addFocusListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        this.generale.add(this.mperso, gridBagConstraints);
        (this.auto_away = new JCheckBox("Afficher le statut absent apr\u00e9s")).addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        this.generale.add(this.auto_away, gridBagConstraints);
        (this.away_delay = new JLimitedIntField(2)).addActionListener(this);
        this.away_delay.addKeyListener(this);
        this.away_delay.addFocusListener(this);
        this.away_delay.setPreferredSize(new Dimension(45, 25));
        this.away_delay.setString("5");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        this.generale.add(this.away_delay, gridBagConstraints);
        this.lbl_away = new JLabel("minutes d'inactivit\u00e9");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        this.generale.add(this.lbl_away, gridBagConstraints);
        (this.wizz = new JCheckBox("autoriser les autres \u00e0 m'envoyer des WIZZ.")).addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        this.generale.add(this.wizz, gridBagConstraints);
        (this.demarrage = new JCheckBox("Ex\u00e9cuter Chat-land messenger automatiquement \u00e0 l'ouverture de windows")).addActionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        this.generale.add(this.demarrage, gridBagConstraints);
        final StringBuilder sb = new StringBuilder();
        this.eirc.getAccueil();
        if (!new File(sb.append(main.homeapp).append("d125c").toString()).exists()) {
            this.demarrage.setSelected(true);
        }
    }
    
    public void buildMessagesPanel() {
        this.messages = new JPanel();
        this.message_layout = new GridBagLayout();
        this.messages.setLayout(this.message_layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.no_privates = new JCheckBox("Pas de nouveaux messages priv\u00e9s.");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.no_privates.addActionListener(this);
        this.messages.add(this.no_privates, gridBagConstraints);
        this.see_join = new JCheckBox("Voir les arriv\u00e9es et les sorties dans un salon.");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.see_join.addActionListener(this);
        this.messages.add(this.see_join, gridBagConstraints);
        this.see_time = new JCheckBox("Afficher l'heure dans les dialogues");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.see_time.addActionListener(this);
        this.messages.add(this.see_time, gridBagConstraints);
        (this.Notice = new JCheckBox("Notices")).setToolTipText("Afficher une notice lorsqu'un ami se connecte ou se d\u00e9connecte");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.Notice.addActionListener(this);
        this.messages.add(this.Notice, gridBagConstraints);
        this.historique = new JCheckBox("Activer l'historique de conversation");
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        this.historique.addActionListener(this);
        this.messages.add(this.historique, gridBagConstraints);
    }
    
    public void buildSoundsPanel() {
        this.Sound = new JPanel();
        this.sound_layout = new GridBagLayout();
        this.Sound.setLayout(this.sound_layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridy = 0;
        this.Soundon = new JRadioButton("Activ\u00e9s");
        gridBagConstraints.gridx = 0;
        this.Soundon.addActionListener(this);
        this.Sound.add(this.Soundon, gridBagConstraints);
        this.Soundoff = new JRadioButton("D\u00e9sactiv\u00e9s");
        gridBagConstraints.gridx = 1;
        this.Soundoff.addActionListener(this);
        this.Sound.add(this.Soundoff, gridBagConstraints);
        this.Soundoffaway = new JRadioButton("D\u00e9sactiv\u00e9s si absent");
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 2;
        this.Soundoffaway.addActionListener(this);
        this.Sound.add(this.Soundoffaway, gridBagConstraints);
        this.SoundGroup.add(this.Soundon);
        this.SoundGroup.add(this.Soundoff);
        this.SoundGroup.add(this.Soundoffaway);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridy = 1;
        this.lbl_entree = new JLabel("Entr\u00e9e:");
        gridBagConstraints.gridx = 0;
        this.Sound.add(this.lbl_entree, gridBagConstraints);
        this.soundentree = new JComboBox();
        this.soundbanni = new JComboBox();
        this.soundsortie = new JComboBox();
        this.sounddeconnexion = new JComboBox();
        this.soundpromotion = new JComboBox();
        this.soundmessageprive = new JComboBox();
        this.sounddechu = new JComboBox();
        this.soundmessagesalon = new JComboBox();
        this.soundejecte = new JComboBox();
        this.soundentree.addPopupMenuListener(this);
        for (int i = 0; i < this.sounds.length; ++i) {
            this.soundentree.addItem(this.sounds[i]);
        }
        gridBagConstraints.gridx = 1;
        this.soundentree.addActionListener(this);
        this.Sound.add(this.soundentree, gridBagConstraints);
        this.lbl_banni = new JLabel("Banni:");
        gridBagConstraints.gridx = 2;
        this.Sound.add(this.lbl_banni, gridBagConstraints);
        for (int j = 0; j < this.sounds.length; ++j) {
            this.soundbanni.addItem(this.sounds[j]);
        }
        gridBagConstraints.gridx = 3;
        this.soundbanni.addActionListener(this);
        this.Sound.add(this.soundbanni, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        this.lbl_sortie = new JLabel("Sortie:");
        gridBagConstraints.gridx = 0;
        this.Sound.add(this.lbl_sortie, gridBagConstraints);
        for (int k = 0; k < this.sounds.length; ++k) {
            this.soundsortie.addItem(this.sounds[k]);
        }
        gridBagConstraints.gridx = 1;
        this.soundsortie.addActionListener(this);
        this.Sound.add(this.soundsortie, gridBagConstraints);
        this.lbl_deconnexion = new JLabel("D\u00e9connexion:");
        gridBagConstraints.gridx = 2;
        this.Sound.add(this.lbl_deconnexion, gridBagConstraints);
        for (int l = 0; l < this.sounds.length; ++l) {
            this.sounddeconnexion.addItem(this.sounds[l]);
        }
        gridBagConstraints.gridx = 3;
        this.sounddeconnexion.addActionListener(this);
        this.Sound.add(this.sounddeconnexion, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        this.lbl_promotion = new JLabel("Promotion:");
        gridBagConstraints.gridx = 0;
        this.Sound.add(this.lbl_promotion, gridBagConstraints);
        for (int n = 0; n < this.sounds.length; ++n) {
            this.soundpromotion.addItem(this.sounds[n]);
        }
        gridBagConstraints.gridx = 1;
        this.soundpromotion.addActionListener(this);
        this.Sound.add(this.soundpromotion, gridBagConstraints);
        this.lbl_messageprive = new JLabel("Message priv\u00e9:");
        gridBagConstraints.gridx = 2;
        this.Sound.add(this.lbl_messageprive, gridBagConstraints);
        for (int n2 = 0; n2 < this.sounds.length; ++n2) {
            this.soundmessageprive.addItem(this.sounds[n2]);
        }
        gridBagConstraints.gridx = 3;
        this.soundmessageprive.addActionListener(this);
        this.Sound.add(this.soundmessageprive, gridBagConstraints);
        gridBagConstraints.gridy = 4;
        this.lbl_decheance = new JLabel("D\u00e9ch\u00e9ance:");
        gridBagConstraints.gridx = 0;
        this.Sound.add(this.lbl_decheance, gridBagConstraints);
        for (int n3 = 0; n3 < this.sounds.length; ++n3) {
            this.sounddechu.addItem(this.sounds[n3]);
        }
        gridBagConstraints.gridx = 1;
        this.sounddechu.addActionListener(this);
        this.Sound.add(this.sounddechu, gridBagConstraints);
        this.lbl_messagesalon = new JLabel("Message Salon:");
        gridBagConstraints.gridx = 2;
        this.Sound.add(this.lbl_messagesalon, gridBagConstraints);
        for (int n4 = 0; n4 < this.sounds.length; ++n4) {
            this.soundmessagesalon.addItem(this.sounds[n4]);
        }
        gridBagConstraints.gridx = 3;
        this.soundmessagesalon.addActionListener(this);
        this.Sound.add(this.soundmessagesalon, gridBagConstraints);
        gridBagConstraints.gridy = 5;
        this.lbl_ejecte = new JLabel("Eject\u00e9:");
        gridBagConstraints.gridx = 0;
        this.Sound.add(this.lbl_ejecte, gridBagConstraints);
        for (int n5 = 0; n5 < this.sounds.length; ++n5) {
            this.soundejecte.addItem(this.sounds[n5]);
        }
        gridBagConstraints.gridx = 1;
        this.soundejecte.addActionListener(this);
        this.Sound.add(this.soundejecte, gridBagConstraints);
    }
    
    public void colorForeground(Color color) {
        if (color.equals(new Color(255, 255, 255))) {
            color = new Color(0, 0, 0);
        }
        this.demarrage.setForeground(color);
        this.wizz.setForeground(color);
        this.auto_away.setForeground(color);
        this.lbl_mperso.setForeground(color);
        this.lbl_away.setForeground(color);
        this.Sound.setForeground(color);
        this.Soundon.setForeground(color);
        this.Soundoff.setForeground(color);
        this.Soundoffaway.setForeground(color);
        this.lbl_entree.setForeground(color);
        this.lbl_sortie.setForeground(color);
        this.lbl_promotion.setForeground(color);
        this.lbl_decheance.setForeground(color);
        this.lbl_ejecte.setForeground(color);
        this.lbl_banni.setForeground(color);
        this.lbl_deconnexion.setForeground(color);
        this.lbl_messageprive.setForeground(color);
        this.lbl_messagesalon.setForeground(color);
        this.messages.setForeground(color);
        this.no_privates.setForeground(color);
        this.see_join.setForeground(color);
        this.see_time.setForeground(color);
        this.Notice.setForeground(color);
        this.historique.setForeground(color);
    }
    
    @Override
    public void focusGained(final FocusEvent focusEvent) {
        if (focusEvent.getSource().equals(this.mperso)) {
            if (!this.eirc.isRegister()) {
                this.mperso.transferFocus();
                final Object[] array = { "Oui, s'inscrire maintenant", "Non, une autre fois" };
                if (JOptionPane.showOptionDialog(this.eirc.getFrame(), "Vous devez \u00eatre vip pour acc\u00e9der \u00e0 cette commande.\nInscription gratuite sur http://vip.chat-land.org/", "Chat-Land.org", 0, 1, null, array, array[1]) == 0) {
                    IEAutoStart.run("http://vip.chat-land.org/");
                }
                return;
            }
            if (this.mperso.getText().trim().equals("Quoi de neuf ? Tapez votre message perso ici")) {
                this.mperso.setFont(new Font("Time New Roman", 0, 13));
                this.mperso.setForeground(Color.black);
                this.mperso.setString("");
            }
            else {
                this.mperso.setFont(new Font("Time New Roman", 0, 13));
                this.mperso.setForeground(Color.black);
            }
        }
    }
    
    @Override
    public void focusLost(final FocusEvent focusEvent) {
        if (focusEvent.getSource().equals(this.away_delay) && this.away_delay.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vous devez choisir le d\u00e9lai d'inactivit\u00e9 pour l'option\nAfficher le statut absent apr\u00e9s...", "Chat-Land.Org", 0);
        }
        if (focusEvent.getSource().equals(this.mperso)) {
            if (this.mperso.getText().trim().equals("")) {
                this.mperso.setFont(new Font("Time New Roman", 2, 13));
                this.mperso.setForeground(Color.darkGray);
                this.mperso.setString("Quoi de neuf ? Tapez votre message perso ici");
                CONF.SetMperso(this.eirc.getNick(), "");
            }
            else {
                this.mperso.setFont(new Font("Time New Roman", 2, 13));
                this.mperso.setForeground(Color.darkGray);
                CONF.SetMperso(this.eirc.getNick(), this.mperso.getText());
            }
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        this.validate.setEnabled(true);
        if (keyEvent.getSource().equals(this.away_delay) && !this.away_delay.getText().equals("") && keyEvent.getKeyCode() != 8) {
            this.eirc.setAway_delay(Integer.parseInt(this.away_delay.getText()));
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    @Override
    public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
    }
    
    @Override
    public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
    }
    
    @Override
    public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
        ((JComboBox)popupMenuEvent.getSource()).validate();
        ((JComboBox)popupMenuEvent.getSource()).repaint();
    }
    
    public void refresh() {
        this.playsound = false;
        this.Soundon.setSelected(!this.eirc.isSilent());
        this.Soundoff.setSelected(this.eirc.isSilent());
        this.Soundoffaway.setSelected(this.eirc.isSound_off_away());
        this.soundentree.setSelectedIndex(this.eirc.getSound_join());
        this.soundsortie.setSelectedIndex(this.eirc.getSound_part());
        this.soundpromotion.setSelectedIndex(this.eirc.getSound_op());
        this.sounddechu.setSelectedIndex(this.eirc.getSound_deop());
        this.soundbanni.setSelectedIndex(this.eirc.getSound_ban());
        this.soundejecte.setSelectedIndex(this.eirc.getSound_kick());
        this.sounddeconnexion.setSelectedIndex(this.eirc.getSound_quit());
        this.soundmessageprive.setSelectedIndex(this.eirc.getSound_prvmsg());
        this.soundmessagesalon.setSelectedIndex(this.eirc.getSound_chan());
        this.no_privates.setSelected(this.eirc.isNo_privates());
        this.see_join.setSelected(this.eirc.isSee_join());
        this.see_time.setSelected(this.eirc.isShowtime());
        this.Notice.setSelected(this.eirc.isAffichernotice());
        this.historique.setSelected(this.eirc.actif_historique);
        this.playsound = true;
        this.wizz.setSelected(this.eirc.isAccept_wizz());
        this.auto_away.setSelected(this.eirc.isAuto_away());
        this.away_delay.setString("" + this.eirc.getAway_delay());
    }
    
    public void saveexcute() {
        final StringBuilder sb = new StringBuilder();
        this.eirc.getAccueil();
        final File file = new File(sb.append(main.homeapp).append("d125c").toString());
        if (this.demarrage.isSelected()) {
            file.delete();
            final String[] array = new String[27];
            array[0] = "Dim Shell, DesktopPath, URL,URL1";
            array[1] = "Set Shell = CreateObject(\"WScript.Shell\")";
            array[2] = "DesktopPath = Shell.SpecialFolders(\"Desktop\")";
            array[3] = "Set URL = Shell.CreateShortcut(DesktopPath & \"\\Chat-Land messenger.lnk\")";
            array[4] = "CreateObject(\"Scripting.FileSystemObject\")";
            final int n = 5;
            final StringBuilder append = new StringBuilder().append("URL.IconLocation = \"");
            this.eirc.getAccueil();
            array[n] = append.append(main.homeapp).append("\\Chat-land.ico\"").toString();
            final int n2 = 6;
            final StringBuilder append2 = new StringBuilder().append("URL.TargetPath = \"");
            this.eirc.getAccueil();
            array[n2] = append2.append(main.homeapp).append("\\Chat-Land messenger.exe\"").toString();
            array[7] = "URL.Save";
            array[8] = "Set Shell = WScript.CreateObject(\"WScript.Shell\")";
            array[9] = "       DesktopPath = shell.SpecialFolders(\"Startup\")";
            array[10] = "       Set URL1 = Shell.CreateShortcut(DesktopPath & \"\\Chat-Land messenger.lnk\")";
            final int n3 = 11;
            final StringBuilder append3 = new StringBuilder().append("URL1.IconLocation = \"");
            this.eirc.getAccueil();
            array[n3] = append3.append(main.homeapp).append("\\Chat-land.ico\"").toString();
            final int n4 = 12;
            final StringBuilder append4 = new StringBuilder().append("URL1.TargetPath = \"");
            this.eirc.getAccueil();
            array[n4] = append4.append(main.homeapp).append("\\Chat-Land messenger.exe\"").toString();
            array[13] = "URL1.Save";
            array[14] = "Set Shell = WScript.CreateObject(\"WScript.Shell\")";
            array[15] = "       DesktopPath = shell.SpecialFolders(\"StartMenu\")";
            array[16] = "       Set URL1 = Shell.CreateShortcut(DesktopPath & \"\\Chat-Land messenger.lnk\")";
            final int n5 = 17;
            final StringBuilder append5 = new StringBuilder().append("URL1.IconLocation = \"");
            this.eirc.getAccueil();
            array[n5] = append5.append(main.homeapp).append("\\Chat-land.ico\"").toString();
            final int n6 = 18;
            final StringBuilder append6 = new StringBuilder().append("URL1.TargetPath = \"");
            this.eirc.getAccueil();
            array[n6] = append6.append(main.homeapp).append("\\Chat-Land messenger.exe\"").toString();
            array[19] = "URL1.Save";
            array[20] = "SET oFS = nothing";
            array[21] = "dim cheminvbs";
            array[22] = "WScript.Sleep 1000";
            array[23] = "cheminvbs = WScript.ScriptFullName";
            array[24] = "Dim oFSOvbs";
            array[25] = "Set oFSOvbs = CreateObject(\"Scripting.FileSystemObject\")";
            array[26] = "oFSOvbs.DeleteFile cheminvbs,True";
            final String[] array2 = array;
            try {
                final StringBuilder sb2 = new StringBuilder();
                this.eirc.getAccueil();
                final PrintWriter printWriter = new PrintWriter(new FileWriter(sb2.append(main.homeapp).append("\\Install-chat-land.vbs").toString()), true);
                for (int i = 0; i < array2.length; ++i) {
                    printWriter.println(array2[i]);
                }
                printWriter.close();
                final Runtime runtime = Runtime.getRuntime();
                final StringBuilder append7 = new StringBuilder().append("cmd /c \"");
                this.eirc.getAccueil();
                runtime.exec(append7.append(main.homeapp).append("\\Install-chat-land.vbs\"").toString());
            }
            catch (Exception ex) {}
        }
        else {
            try {
                file.createNewFile();
            }
            catch (IOException ex2) {}
            final String[] array3 = { "On Error Resume Next", "Dim Shell, DesktopPath", "dim cheminvbs", "Dim oFSOvbs,WshShell", "Set Shell = WScript.CreateObject(\"WScript.Shell\")", "DesktopPath = shell.SpecialFolders(\"Startup\")", "cheminvbs = DesktopPath & \"\\Chat-Land messenger.lnk\"", "Set oFSOvbs = CreateObject(\"Scripting.FileSystemObject\")", "oFSOvbs.DeleteFile cheminvbs,True", "cheminvbs = WScript.ScriptFullName", "Set oFSOvbs = CreateObject(\"Scripting.FileSystemObject\")", "oFSOvbs.DeleteFile cheminvbs,True", "Set WshShell = Wscript.CreateObject(\"Wscript.Shell\")", "WshShell.RegDelete \"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Run\\Chat-Landmessenger\"" };
            try {
                final StringBuilder sb3 = new StringBuilder();
                this.eirc.getAccueil();
                final PrintWriter printWriter2 = new PrintWriter(new FileWriter(sb3.append(main.homeapp).append("\\Install-chat-land.vbs").toString()), true);
                for (int j = 0; j < array3.length; ++j) {
                    printWriter2.println(array3[j]);
                }
                printWriter2.close();
                final Runtime runtime2 = Runtime.getRuntime();
                try {
                    final Runtime runtime3 = runtime2;
                    final StringBuilder append8 = new StringBuilder().append("cmd /c \"");
                    this.eirc.getAccueil();
                    runtime3.exec(append8.append(main.homeapp).append("\\Install-chat-land.vbs\"").toString());
                }
                catch (IOException ex3) {}
            }
            catch (Exception ex4) {}
        }
    }
    
    public void setmperso(final String string) {
        this.mperso.setString(string);
    }
    
    public void setSelectauto_away(final boolean selected) {
        this.auto_away.setSelected(selected);
    }
    
    public void setSelectaway_delay(final String string) {
        this.away_delay.setString(string);
    }
    
    public void setSelectno_privates(final boolean selected) {
        this.no_privates.setSelected(selected);
    }
    
    public void setSelectsee_join(final boolean selected) {
        this.see_join.setSelected(selected);
    }
    
    public void setSelectsee_Notice(final boolean selected) {
        this.Notice.setSelected(selected);
    }
    
    public void setSelectsee_time(final boolean selected) {
        this.see_time.setSelected(selected);
    }
    
    public void setSelectwizz(final boolean selected) {
        this.wizz.setSelected(selected);
    }
    
    public void Show() {
        this.validate.setEnabled(false);
        this.setLocation(this.eirc.getFrame().getLocation().x - 50, this.eirc.getFrame().getLocation().y + 100);
        this.setVisible(true);
    }
    
    public void update() {
        final String string = "" + this.soundentree.getSelectedIndex() + ":" + this.soundsortie.getSelectedIndex() + ":" + this.soundpromotion.getSelectedIndex() + ":" + this.sounddechu.getSelectedIndex() + ":" + this.soundejecte.getSelectedIndex() + ":" + this.soundbanni.getSelectedIndex() + ":" + this.sounddeconnexion.getSelectedIndex() + ":" + this.soundmessageprive.getSelectedIndex() + ":" + this.soundmessagesalon.getSelectedIndex();
        String s;
        if (this.eirc.isSound_off_away()) {
            s = "2:" + string;
        }
        else if (this.eirc.isSilent()) {
            s = "0:" + string;
        }
        else {
            s = "1:" + string;
        }
        final String s2 = "";
        final String string2 = (this.no_privates.isSelected() ? (s2 + "1:") : (s2 + "0:")) + "0:";
        final String s3 = this.see_join.isSelected() ? (string2 + "1:") : (string2 + "0:");
        final String s4 = this.see_time.isSelected() ? (s3 + "1:") : (s3 + "0:");
        final String s5 = this.auto_away.isSelected() ? (s4 + "1:") : (s4 + "0:");
        String s6;
        if (!this.away_delay.getText().equals("") && !this.away_delay.equals("0") && !this.away_delay.equals("00")) {
            s6 = s5 + this.away_delay.getText() + ":";
        }
        else {
            s6 = s5 + "5:";
        }
        final String string3 = (this.wizz.isSelected() ? (s6 + "1:") : (s6 + "0:")) + "1:" + this.eirc.parseColor(EIRC.mainbg) + ":" + this.eirc.parseColor(EIRC.mainfg) + ":" + this.eirc.getPolice().getSelectpolice() + ":" + EIRC.fontsize + ":";
        final String s7 = this.eirc.isBooleangras() ? (string3 + "1:") : (string3 + "0:");
        final String string4 = (this.Notice.isSelected() ? (s7 + "1:") : (s7 + "0:")) + "0:";
        final String s8 = this.historique.isSelected() ? (string4 + "1:") : (string4 + "0:");
        CONF.SetSound(s);
        CONF.SetMessage(s8);
        CONF.SetMperso(this.eirc.getNick(), this.mperso.getText());
    }
    
    public void updateMessage() {
        final String s = "";
        final String string = (this.no_privates.isSelected() ? (s + "1:") : (s + "0:")) + "0:";
        final String s2 = this.see_join.isSelected() ? (string + "1:") : (string + "0:");
        final String s3 = this.see_time.isSelected() ? (s2 + "1:") : (s2 + "0:");
        final String s4 = this.auto_away.isSelected() ? (s3 + "1:") : (s3 + "0:");
        String s5;
        if (!this.away_delay.getText().equals("") && !this.away_delay.equals("0") && !this.away_delay.equals("00")) {
            s5 = s4 + this.away_delay.getText() + ":";
        }
        else {
            s5 = s4 + "5:";
        }
        final String string2 = (this.wizz.isSelected() ? (s5 + "1:") : (s5 + "0:")) + "1:" + this.eirc.parseColor(EIRC.mainbg) + ":" + this.eirc.parseColor(EIRC.mainfg) + ":" + this.eirc.getPolice().getSelectpolice() + ":" + EIRC.fontsize + ":";
        final String s6 = this.eirc.isBooleangras() ? (string2 + "1") : (string2 + "0");
        final String string3 = (this.Notice.isSelected() ? (s6 + "1:") : (s6 + "0:")) + "0:";
        CONF.SetMessage(this.historique.isSelected() ? (string3 + "1:") : (string3 + "0:"));
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.away_delay.getText().equals("")) {
            this.away_delay.setString("" + this.eirc.getAway_delay());
        }
        this.eirc.revenir();
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
        this.eirc.revenir();
    }
}
