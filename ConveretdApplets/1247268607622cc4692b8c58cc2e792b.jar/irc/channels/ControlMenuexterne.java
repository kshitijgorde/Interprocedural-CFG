// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import javax.swing.event.PopupMenuEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import irc.EIRC;
import javax.swing.ImageIcon;
import irc.managers.Resources;
import irc.suggestion;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import irc.desinstaller;
import irc.apropo;
import irc.com.utils.IEAutoStart;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import irc.main;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;
import irc.channels.components.MyJToolBar;

public class ControlMenuexterne extends MyJToolBar implements ActionListener, PopupMenuListener, MouseListener
{
    private JLabel fichier;
    private JLabel ChatLand;
    private JLabel point;
    private JPopupMenu menufichier;
    private JMenuItem quitter;
    private JMenuItem suggestions1;
    private JMenu DivertissementS;
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
    private boolean menushow;
    private main m;
    
    public ControlMenuexterne(final main m) {
        this.menushow = false;
        this.m = m;
        this.add(this.fichier = new JLabel(" Fichier "));
        this.add(this.ChatLand = new JLabel(" Extra "));
        (this.point = new JLabel("?")).addMouseListener(this);
        this.add(this.point);
        this.fichier.addMouseListener(this);
        this.ChatLand.addMouseListener(this);
        this.point.addMouseListener(this);
        (this.menufichier = new JPopupMenu()).addPopupMenuListener(this);
        this.fichier.setFont(new Font("Time New Roman", 0, 13));
        this.ChatLand.setFont(new Font("Time New Roman", 0, 13));
        this.point.setFont(new Font("Time New Roman", 0, 13));
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.aide1)) {
            IEAutoStart.run("http://www.chat-land.org/aidemessenger/");
            return;
        }
        if (actionEvent.getSource().equals(this.Apropos1)) {
            new apropo(this.m.Application);
        }
        if (actionEvent.getSource().equals(this.uninstall)) {
            new desinstaller(this.m).setVisible(true);
        }
        if (actionEvent.getSource().equals(this.assistance)) {
            IEAutoStart.run("http://service.chat-land.org/service/");
        }
        if (actionEvent.getSource().equals(this.quitter)) {
            this.m.savedim();
            final Object[] array = { "Oui", "Non" };
            if (JOptionPane.showOptionDialog(this.m.Application, "Voulez vous vraiment quitter Messenger chat-land ?", "Chat-land.org", 0, 3, null, array, array[0]) == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        if (ControlMenuexterne.this.m.eirc != null) {
                            ControlMenuexterne.this.m.savedim();
                            ControlMenuexterne.this.m.eirc.logout();
                            ControlMenuexterne.this.m.eirc.end();
                        }
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (InterruptedException ex) {}
                        System.exit(0);
                    }
                }.start();
            }
        }
        if (actionEvent.getSource().equals(this.accuiel) || actionEvent.getSource().equals(this.modifer) || actionEvent.getSource().equals(this.mail) || actionEvent.getSource().equals(this.photos) || actionEvent.getSource().equals(this.Videos) || actionEvent.getSource().equals(this.fondsecran) || actionEvent.getSource().equals(this.jeux1) || actionEvent.getSource().equals(this.ticket) || actionEvent.getSource().equals(this.blagues) || actionEvent.getSource().equals(this.paroles)) {
            IEAutoStart.run(actionEvent.getActionCommand());
        }
        if (actionEvent.getSource().equals(this.suggestions1)) {
            new suggestion(this.m).setVisible(true);
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
        this.modifer.setActionCommand("http://www.chat-land.org/vip/edit.php");
        this.mail.setActionCommand("http://webmail.chat-land.org");
        this.photos.setActionCommand("http://www.chat-land.org/photos/");
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
    }
    
    public void buildFichierPopup() {
        (this.menufichier = new JPopupMenu()).addPopupMenuListener(this);
        (this.quitter = new JMenuItem("Quitter")).setIcon(new ImageIcon(Resources.getImages("iconmenu_quitter")));
        this.menufichier.add(this.quitter);
        this.quitter.addActionListener(this);
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
        this.popuppoint.addSeparator();
        (this.uninstall = new JMenuItem("D\u00e9sinstaller")).setIcon(new ImageIcon(Resources.getImages("iconmenu_desinstaller")));
        this.uninstall.addActionListener(this);
        this.popuppoint.add(this.uninstall);
        this.uninstall.setOpaque(false);
        (this.assistance = new JMenuItem("Assistance en ligne")).setIcon(new ImageIcon(Resources.getImages("assistance")));
        this.assistance.addActionListener(this);
        this.popuppoint.add(this.assistance);
        this.assistance.setOpaque(false);
        this.assistance.setForeground(EIRC.mainfg);
    }
    
    public void colorBackground(final Color background) {
        this.fichier.setBackground(background);
        this.ChatLand.setBackground(background);
        this.point.setBackground(background);
    }
    
    public void colorForeground(final Color foreground) {
        this.fichier.setForeground(foreground);
        this.ChatLand.setForeground(foreground);
        this.point.setForeground(foreground);
    }
    
    private void jbInit() throws Exception {
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.point)) {
            this.buildpointPopup();
            this.popuppoint.show(this.point, 0, this.point.getSize().height);
            return;
        }
        if (mouseEvent.getSource().equals(this.fichier)) {
            this.buildFichierPopup();
            this.menufichier.show(this.fichier, 0, this.fichier.getSize().height);
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
            this.point.setForeground(EIRC.mainbg);
            this.point.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.fichier)) {
            this.fichier.setOpaque(true);
            this.fichier.setForeground(EIRC.mainbg);
            this.fichier.setBackground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.ChatLand)) {
            this.ChatLand.setOpaque(true);
            this.ChatLand.setForeground(EIRC.mainbg);
            this.ChatLand.setBackground(EIRC.mainfg);
        }
        if (this.menushow) {
            if (mouseEvent.getSource().equals(this.fichier)) {
                this.buildFichierPopup();
                this.menufichier.show(this.fichier, 0, this.fichier.getSize().height);
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
            this.point.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.fichier)) {
            this.fichier.setOpaque(false);
            this.fichier.setForeground(EIRC.mainfg);
        }
        else if (mouseEvent.getSource().equals(this.ChatLand)) {
            this.ChatLand.setOpaque(false);
            this.ChatLand.setForeground(EIRC.mainfg);
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
}
