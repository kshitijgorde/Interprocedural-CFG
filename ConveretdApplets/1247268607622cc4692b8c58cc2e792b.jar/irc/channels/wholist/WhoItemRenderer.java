// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

import java.awt.Font;
import javax.swing.ImageIcon;
import irc.managers.Resources;
import irc.com.nick.NickInfos;
import javax.swing.Icon;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class WhoItemRenderer extends JPanel implements ListCellRenderer
{
    JLabel Name;
    JLabel Nb;
    JLabel Home;
    JLabel image;
    BorderLayout gridLayout;
    BorderLayout borderlayout;
    JPanel panel;
    String _Nom;
    String _Nb;
    String _Home;
    private final Color colorselect;
    
    public WhoItemRenderer() {
        this.Name = new JLabel();
        this.Nb = new JLabel();
        this.Home = new JLabel();
        this.image = new JLabel();
        this.gridLayout = new BorderLayout();
        this.borderlayout = new BorderLayout();
        this.panel = new JPanel();
        this.colorselect = new Color(206, 206, 206);
        this.setOpaque(false);
        this.panel.setOpaque(false);
        this.Name.setOpaque(false);
        this.image.setOpaque(false);
        this.Nb.setOpaque(false);
        this.Home.setOpaque(false);
        this.setLayout(this.borderlayout);
        this.panel.setLayout(this.gridLayout);
        this.add(this.image, "West");
        this.add(this.Name, "Center");
        this.panel.add(this.Nb, "West");
        this.panel.add(this.Home, "Center");
        this.add(this.panel, "East");
        this.Name.setPreferredSize(new Dimension(95, 35));
        this.Nb.setPreferredSize(new Dimension(41, 35));
        this.Home.setPreferredSize(new Dimension(400, 35));
        this.image.setPreferredSize(new Dimension(35, 35));
    }
    
    @Override
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final WhoItem whoItem = (WhoItem)o;
        this._Nom = whoItem.nom;
        this._Nb = "" + whoItem.age;
        this._Home = whoItem.region;
        if (this._Nom.equals("")) {
            this.setOpaque(true);
            this.panel.setOpaque(true);
            this.Name.setOpaque(true);
            this.Nb.setOpaque(true);
            this.Home.setOpaque(true);
            this.image.setOpaque(true);
            this.Name.setBackground(Color.GRAY);
            this.Nb.setBackground(Color.GRAY);
            this.Home.setBackground(Color.GRAY);
            this.image.setBackground(Color.GRAY);
            this.Name.setText("");
            this.Nb.setText("");
            this.Home.setText("");
            this.Name.setIcon(null);
            this.Nb.setIcon(null);
            this.image.setIcon(null);
            return this;
        }
        if (NickInfos.isRegister(this._Nom)) {
            if (NickInfos.isTof(this._Nom)) {
                final ImageIcon small_avatar = NickInfos.getSmall_avatar(this._Nom);
                if (small_avatar != null && small_avatar.getImage() != null) {
                    this.image.setIcon(small_avatar);
                }
                else {
                    this.image.setIcon(null);
                }
            }
            else {
                this.image.setIcon(null);
            }
        }
        else {
            this.image.setIcon(null);
        }
        String locationCode = NickInfos.getLocationCode(whoItem.getNom());
        if (locationCode == null) {
            locationCode = "";
        }
        String s;
        if (locationCode.startsWith("1")) {
            try {
                s = "" + Integer.parseInt(locationCode.substring(1));
                if (s.length() == 1) {
                    s = "0" + s;
                }
            }
            catch (Exception ex) {
                s = "";
            }
        }
        else {
            s = "";
        }
        if (s.length() == 2) {
            s = "(" + s + ") ";
        }
        if (whoItem.dist != null) {
            this._Home = "<html><font color=\"#000099\">" + s + "</font>" + whoItem.dist + "Km : " + whoItem.region + "</html>";
        }
        else {
            this._Home = "<html><font color=\"#000099\">" + s + "</font>" + whoItem.region + "</html>";
        }
        this.Name.setText(this._Nom);
        if (NickInfos.getAway(this._Nom) != null) {
            this.Nb.setIcon(new ImageIcon(Resources.getImages("off-line")));
        }
        else {
            this.Nb.setIcon(new ImageIcon(Resources.getImages("nonstar")));
        }
        if (NickInfos.isTalk(this._Nom)) {
            this.Name.setIcon(new ImageIcon(Resources.getImages("tique")));
        }
        else {
            this.Name.setIcon(null);
        }
        this.Name.repaint();
        this.Nb.setText(this._Nb + "a");
        this.Home.setText(this._Home);
        this.Nb.setForeground(Color.decode("0x007F00"));
        this.Home.setForeground(Color.BLUE);
        if (b) {
            this.setOpaque(true);
            this.panel.setOpaque(true);
            this.Name.setOpaque(true);
            this.Nb.setOpaque(true);
            this.Home.setOpaque(true);
            this.image.setOpaque(true);
            this.Name.setBackground(this.colorselect);
            this.Nb.setBackground(this.colorselect);
            this.Home.setBackground(this.colorselect);
            this.image.setBackground(this.colorselect);
        }
        else {
            this.panel.setOpaque(false);
            this.setOpaque(false);
            this.Name.setOpaque(false);
            this.Nb.setOpaque(false);
            this.Home.setOpaque(false);
            this.image.setOpaque(false);
        }
        if (whoItem.sexe == 1) {
            this.Name.setForeground(Color.decode("0x0033CC"));
            this.Name.setFont(new Font("Dialog", 1, 12));
        }
        else {
            this.Name.setForeground(Color.decode("0xB02DB9"));
            this.Name.setFont(new Font("Dialog", 1, 12));
        }
        return this;
    }
}
