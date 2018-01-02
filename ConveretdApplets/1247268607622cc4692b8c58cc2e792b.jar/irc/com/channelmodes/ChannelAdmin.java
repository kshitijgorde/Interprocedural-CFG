// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.channelmodes;

import javax.swing.event.ListSelectionEvent;
import irc.com.User;
import irc.managers.CHANNELS;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.ListModel;
import irc.managers.Resources;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import irc.channels.ChannelWindow;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class ChannelAdmin extends JDialog implements ActionListener, MouseListener, ListSelectionListener
{
    String ChannelName;
    JPanel ListePseudoPanel;
    JPanel Interface;
    GridBagLayout gridbaglayout;
    GridBagConstraints gridconstraints;
    GridLayout GridInterface;
    JPanel Global;
    DefaultListModel ListPseudo;
    JList jListPseudo;
    JScrollPane ScrollListPseudo;
    JButton Bannis;
    JButton Proteges;
    JCheckBox ModeModere;
    JCheckBox ModeSecret;
    JCheckBox ModeInvitation;
    JCheckBox PasChanPseudo;
    JCheckBox Limite;
    JCheckBox MotDePasse;
    JLabel typeaction;
    JButton action;
    JPanel passe;
    GridLayout gridpasse;
    ChannelWindow cw;
    JSpinner limitechatteur;
    JLabel personnes;
    JTextField textdepasse;
    String Action;
    GridLayout grid;
    
    public ChannelAdmin(final ChannelWindow cw) {
        super(cw.getEirc().getPvgroup(), true);
        this.ListePseudoPanel = new JPanel();
        this.Interface = new JPanel();
        this.gridbaglayout = new GridBagLayout();
        this.gridconstraints = new GridBagConstraints();
        this.GridInterface = new GridLayout(1, 2);
        this.Global = new JPanel();
        this.ListPseudo = new DefaultListModel();
        this.ScrollListPseudo = new JScrollPane();
        this.Bannis = new JButton("List des Bannis");
        this.Proteges = new JButton("List des Prot\u00e9g\u00e9s");
        this.ModeModere = new JCheckBox("Mode Mod\u00e9r\u00e9");
        this.ModeSecret = new JCheckBox("Mode Secret");
        this.ModeInvitation = new JCheckBox("Mode invitation");
        this.PasChanPseudo = new JCheckBox("Pas de changement de pseudo");
        this.Limite = new JCheckBox("Limite");
        this.MotDePasse = new JCheckBox("Mot de passe");
        this.typeaction = new JLabel();
        this.action = new JButton();
        this.passe = new JPanel();
        this.gridpasse = new GridLayout(1, 2);
        this.limitechatteur = new JSpinner();
        this.personnes = new JLabel("  Personnes");
        this.textdepasse = new JTextField();
        this.grid = new GridLayout(1, 1);
        this.cw = cw;
        this.setIconImage(Resources.getImages("minlogoa"));
        this.setTitle("Propri\u00e9t\u00e9 du salon " + this.cw.getTag());
        this.ChannelName = this.cw.getTag();
        final Container contentPane = this.getContentPane();
        this.jListPseudo = new JList(this.ListPseudo);
        this.Global.setLayout(this.gridbaglayout);
        this.ListePseudoPanel.setLayout(this.grid);
        final GridBagConstraints gridconstraints = this.gridconstraints;
        final GridBagConstraints gridconstraints2 = this.gridconstraints;
        gridconstraints.anchor = 18;
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 0;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 1;
        this.gridbaglayout.setConstraints(this.Bannis, this.gridconstraints);
        this.Global.add(this.Bannis);
        this.gridconstraints.gridx = 1;
        this.gridconstraints.gridy = 0;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 1;
        this.gridconstraints.insets = new Insets(0, 10, 2, 0);
        this.gridbaglayout.setConstraints(this.Proteges, this.gridconstraints);
        this.Global.add(this.Proteges);
        this.ScrollListPseudo.getViewport().add(this.jListPseudo);
        this.ListePseudoPanel.add(this.ScrollListPseudo);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 1;
        this.gridconstraints.gridheight = 3;
        this.gridconstraints.gridwidth = 4;
        final GridBagConstraints gridconstraints3 = this.gridconstraints;
        final GridBagConstraints gridconstraints4 = this.gridconstraints;
        gridconstraints3.fill = 2;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.ListePseudoPanel, this.gridconstraints);
        this.Global.add(this.ListePseudoPanel);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 4;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 4;
        this.gridconstraints.insets = new Insets(0, 10, 0, 0);
        this.gridbaglayout.setConstraints(this.action, this.gridconstraints);
        this.Global.add(this.action);
        this.action.setText("D\u00e9bannir");
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 5;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 4;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.ModeModere, this.gridconstraints);
        this.Global.add(this.ModeModere);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 7;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 4;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.ModeSecret, this.gridconstraints);
        this.Global.add(this.ModeSecret);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 8;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 4;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.ModeInvitation, this.gridconstraints);
        this.Global.add(this.ModeInvitation);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 9;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 4;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.PasChanPseudo, this.gridconstraints);
        this.Global.add(this.PasChanPseudo);
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 10;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 1;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.Limite, this.gridconstraints);
        this.Global.add(this.Limite);
        this.passe.setLayout(this.gridpasse);
        this.passe.add(this.limitechatteur);
        this.passe.add(this.personnes);
        final GridBagConstraints gridconstraints5 = this.gridconstraints;
        final GridBagConstraints gridconstraints6 = this.gridconstraints;
        gridconstraints5.anchor = 10;
        this.gridconstraints.gridx = 1;
        this.gridconstraints.gridy = 10;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 1;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.passe, this.gridconstraints);
        this.Global.add(this.passe);
        this.limitechatteur.setPreferredSize(new Dimension(40, 20));
        this.gridconstraints.gridx = 0;
        this.gridconstraints.gridy = 11;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 1;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.MotDePasse, this.gridconstraints);
        this.Global.add(this.MotDePasse);
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridconstraints.gridx = 1;
        this.gridconstraints.gridy = 11;
        this.gridconstraints.gridheight = 1;
        this.gridconstraints.gridwidth = 2;
        this.gridconstraints.insets = new Insets(0, 0, 0, 0);
        this.gridbaglayout.setConstraints(this.textdepasse, this.gridconstraints);
        this.Global.add(this.textdepasse);
        this.textdepasse.setPreferredSize(new Dimension(70, 25));
        contentPane.add(this.Global);
        this.ModeModere.setEnabled(false);
        this.ModeSecret.setEnabled(false);
        this.ModeInvitation.setEnabled(false);
        this.PasChanPseudo.setEnabled(false);
        this.Limite.setEnabled(false);
        this.MotDePasse.setEnabled(false);
        this.updateModes(this.ChannelName);
        this.ModeModere.setName("m");
        this.ModeInvitation.setName("i");
        this.ModeSecret.setName("s");
        this.PasChanPseudo.setName("N");
        this.MotDePasse.setName("k");
        this.Limite.setName("l");
        this.ModeModere.addActionListener(this);
        this.ModeSecret.addActionListener(this);
        this.ModeInvitation.addActionListener(this);
        this.PasChanPseudo.addActionListener(this);
        this.Limite.addActionListener(this);
        this.MotDePasse.addActionListener(this);
        this.Bannis.addActionListener(this);
        this.Proteges.addActionListener(this);
        this.action.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.cw.getEirc().revenir();
        final Object source = actionEvent.getSource();
        if (actionEvent.getSource().equals(this.ModeModere)) {
            this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (this.ModeModere.isSelected() ? '+' : '-') + ((Component)source).getName() });
        }
        if (actionEvent.getSource().equals(this.ModeSecret)) {
            this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (this.ModeSecret.isSelected() ? '+' : '-') + ((Component)source).getName() });
        }
        if (actionEvent.getSource().equals(this.ModeInvitation)) {
            this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (this.ModeInvitation.isSelected() ? '+' : '-') + ((Component)source).getName() });
        }
        if (actionEvent.getSource().equals(this.PasChanPseudo)) {
            if (this.PasChanPseudo.isSelected()) {
                if (this.PasChanPseudo.isSelected()) {
                    if (this.textdepasse.getText().equals("")) {
                        JOptionPane.showMessageDialog(this.cw.getEirc().getFrame(), "Vous devez choisir un mot de passe.", "Information", 0);
                        this.PasChanPseudo.setSelected(false);
                        this.textdepasse.requestFocus();
                    }
                    else {
                        final String text = this.textdepasse.getText();
                        this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (char)((!text.equals("") && this.PasChanPseudo.isSelected()) ? 43 : 45) + ((Component)source).getName(), text });
                    }
                }
            }
        }
        if (actionEvent.getSource().equals(this.MotDePasse)) {
            if (!this.MotDePasse.isSelected()) {
                this.textdepasse.setText("");
            }
            else if (this.MotDePasse.isSelected()) {
                if (this.textdepasse.getText().equals("")) {
                    JOptionPane.showMessageDialog(this.cw.getEirc().getFrame(), "Vous devez choisir un mot de passe.", "Information", 0);
                    this.MotDePasse.setSelected(false);
                    this.textdepasse.requestFocus();
                }
                else {
                    final String text2 = this.textdepasse.getText();
                    this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (char)((!text2.equals("") && this.MotDePasse.isSelected()) ? 43 : 45) + ((Component)source).getName(), text2 });
                }
            }
        }
        if (actionEvent.getSource().equals(this.Limite)) {
            if (!this.Limite.isSelected()) {
                this.limitechatteur.setValue(new Integer(0));
            }
            else if (this.Limite.isSelected()) {
                if (Integer.parseInt(this.limitechatteur.getValue().toString()) < 1) {
                    JOptionPane.showMessageDialog(this.cw.getEirc().getFrame(), "Vous devez limiter le salon \u00e0 une personne au moins.", "Information", 0);
                    this.Limite.setSelected(false);
                }
                else {
                    final String string = this.limitechatteur.getValue().toString();
                    this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, (char)(string.equals("") ? 45 : 43) + ((Component)source).getName(), string });
                }
            }
        }
        if (actionEvent.getSource().equals(this.Bannis) && this.ChannelName != null) {
            this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, "+b" });
            this.Action = "ban";
            this.ListPseudo.clear();
        }
        if (actionEvent.getSource().equals(this.Proteges) && this.ChannelName != null) {
            this.cw.getEirc().sendMessage("MODE", new String[] { this.ChannelName, "+e" });
            this.Action = "pro";
        }
        if (actionEvent.getSource().equals(this.action) && this.jListPseudo.getSelectedValue() != null && this.action.getText().equals("D\u00e9bannir")) {
            if (this.jListPseudo.getSelectedValue() != null) {
                if (JOptionPane.showConfirmDialog(this.cw.getEirc().getFrame(), "Vous \u00e9tes sur le point de d\u00e9bannir\n" + this.jListPseudo.getSelectedValue(), "Confirmation...", 0, 1) == 0) {
                    final String[] array = { this.ChannelName, "-b", this.jListPseudo.getSelectedValue() };
                    this.ListPseudo.removeElementAt(this.jListPseudo.getSelectedIndex());
                    this.cw.getEirc().sendMessage("MODE", array);
                }
            }
            else if (JOptionPane.showConfirmDialog(this.cw.getEirc().getFrame(), "Vous \u00e9tes sur le point de d\u00e9prot\u00e9ger\n" + this.ChannelName, "Confirmation...", 0, 1) == 0) {
                final String[] array2 = { this.ChannelName, "-e", this.jListPseudo.getSelectedValue() };
                this.ListPseudo.removeElementAt(this.jListPseudo.getSelectedIndex());
                this.cw.getEirc().sendMessage("MODE", array2);
            }
        }
    }
    
    public void free() {
        this.grid = null;
        this.Action = null;
        this.textdepasse.setText("");
        this.textdepasse = null;
        this.personnes = null;
        this.limitechatteur = null;
        this.cw = null;
        this.gridpasse = null;
        this.passe = null;
        this.action = null;
        this.typeaction = null;
        this.MotDePasse = null;
        this.Limite = null;
        this.PasChanPseudo = null;
        this.ModeInvitation = null;
        this.ModeSecret = null;
        this.ModeModere = null;
        this.Proteges = null;
        this.Bannis = null;
        this.ScrollListPseudo = null;
        this.jListPseudo = null;
        this.Global = null;
        this.GridInterface = null;
        this.gridbaglayout = null;
        this.Interface = null;
        this.ListePseudoPanel = null;
        this.ChannelName = null;
    }
    
    public void LoadBanned(final Vector vector) {
        this.ListPseudo.clear();
        if (this.Action.equals("ban")) {
            this.typeaction.setText("Bannis:");
            this.action.setText("D\u00e9bannir");
        }
        else {
            this.typeaction.setText("Prot\u00e9g\u00e9s:");
            this.action.setText("D\u00e9prot\u00e9ger");
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.ListPseudo.addElement(vector.elementAt(i));
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.cw.getEirc().revenir();
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.cw.getEirc().revenir();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.cw.getEirc().revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.cw.getEirc().revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.cw.getEirc().revenir();
    }
    
    public void updateModes(final String s) {
        final ChannelWindow channelWindow = CHANNELS.getChannelWindow(s);
        if (channelWindow != null) {
            final User value = channelWindow.get(this.cw.getEirc().getNick());
            final boolean enabled = value.isHalfOp() || value.isOp() || this.cw.getEirc().canOverride();
            final boolean enabled2 = value.isOp() || this.cw.getEirc().canOverride();
            this.ModeModere.setEnabled(enabled);
            this.ModeSecret.setEnabled(enabled);
            this.ModeInvitation.setEnabled(enabled2);
            this.PasChanPseudo.setEnabled(enabled2);
            this.Limite.setEnabled(enabled2);
            this.MotDePasse.setEnabled(enabled);
            this.Bannis.setEnabled(enabled);
            this.Proteges.setEnabled(enabled);
            this.ModeModere.setSelected(channelWindow.isModerated());
            this.ModeSecret.setSelected(channelWindow.isSecret());
            this.ModeInvitation.setSelected(channelWindow.isInvitOnly());
            this.PasChanPseudo.setSelected(!channelWindow.canNick());
            this.textdepasse.setText(channelWindow.getKey());
            this.limitechatteur.setEnabled(enabled2);
            this.personnes.setEnabled(enabled2);
            this.limitechatteur.setValue(new Integer((channelWindow.getLimit() < 0) ? 0 : channelWindow.getLimit()));
        }
    }
    
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
    }
}
