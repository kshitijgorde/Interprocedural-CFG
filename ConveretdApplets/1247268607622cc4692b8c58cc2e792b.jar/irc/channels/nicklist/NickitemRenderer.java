// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.nicklist;

import irc.managers.Resources;
import javax.swing.Icon;
import irc.com.nick.NickInfos;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class NickitemRenderer extends JPanel implements ListCellRenderer
{
    private static final Color HIGHLIGHT_COLOR;
    protected boolean selected;
    protected int modes;
    protected String nick;
    private Color textbg;
    private Color textfg;
    private Color selbg;
    private Color selfg;
    private static final int OP = 0;
    private static final int HALFOP = 1;
    private static final int VOICE = 2;
    private static final int ADMIN = 3;
    private static final int OWNER = 4;
    private static final int AWAY = 5;
    private static final int NORMAL = 6;
    private static final int BULLETS = 7;
    private static int BULLET_DIAMETER;
    private static boolean bullets_loaded;
    private static final int BULLET_GAP = 2;
    private static ImageIcon[] bullets;
    private static ImageIcon star;
    private ImageIcon current_bullet;
    private boolean item_bullet;
    private JLabel lblnick;
    private JLabel lblvip;
    private JLabel lblaway;
    private JLabel image;
    private static final Color awayTxtColor;
    private boolean _simple;
    private int index;
    private int mode;
    private String position;
    FlowLayout layout;
    
    public NickitemRenderer() {
        this.textbg = SystemColor.text;
        this.textfg = SystemColor.textText;
        this.selbg = Color.white;
        this.selfg = SystemColor.textHighlightText;
        this.layout = new FlowLayout(0, 0, 0);
        this.setOpaque(true);
        this.lblnick = new JLabel();
        this.lblvip = new JLabel();
        this.lblaway = new JLabel();
        this.image = new JLabel();
        this.modes = 1;
        this._simple = true;
        this.setBackground(Color.white);
        this.layout.setHgap(2);
        this.layout.setVgap(2);
        this.setLayout(this.layout);
        this.add(this.image);
        this.add(this.lblnick);
        this.add(this.lblaway);
        this.add(this.lblvip);
        this.lblnick.setFont(new Font("Dialog", 0, 13));
        this.lblvip.setFont(new Font("Dialog", 0, 13));
        this.lblaway.setFont(new Font("Dialog", 0, 13));
        this.lblnick.setOpaque(true);
        this.lblvip.setOpaque(true);
        this.lblaway.setOpaque(true);
        this.image.setPreferredSize(new Dimension(35, 35));
        this.setPreferredSize(new Dimension(500, 35));
        this.lblvip.setPreferredSize(new Dimension(250, 35));
    }
    
    public int getIndex() {
        return this.index;
    }
    
    @Override
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final NickItem nickItem = (NickItem)o;
        nickItem.setAge(NickInfos.getAge(nickItem.getNick()));
        nickItem.setLoc(NickInfos.getLocation(nickItem.getNick()));
        this.setNick(nickItem.getNick());
        this.setSelectedBackground(Color.black);
        this.setSelectedForeground(Color.white);
        this.setTextBackground(nickItem.getTextBackground());
        if (NickInfos.getSex(nickItem.getNick()) == 1) {
            this.setTextForeground(Color.BLUE);
            nickItem.setSexe(NickInfos.getSex(nickItem.getNick()));
        }
        else {
            this.setTextForeground(nickItem.getTextForeground());
        }
        this.setForeground(nickItem.getForeground());
        this.setBackground(nickItem.getBackground());
        this.setBullet(nickItem.item_bullet);
        this.setIndex(nickItem.getIndex());
        this.setModes(nickItem.getModes());
        if (b) {
            this.lblnick.setBackground(Color.decode("0xADD8E6"));
            this.setBackground(Color.decode("0xADD8E6"));
            this.lblvip.setBackground(Color.decode("0xADD8E6"));
            this.lblaway.setBackground(Color.decode("0xADD8E6"));
            this.image.setBackground(Color.decode("0xADD8E6"));
        }
        else {
            this.lblnick.setBackground(Color.white);
            this.setBackground(Color.white);
            this.lblvip.setBackground(Color.white);
            this.lblaway.setBackground(Color.white);
            this.image.setBackground(Color.white);
        }
        if (NickInfos.isRegister(this.nick)) {
            if (NickInfos.isTof(this.nick)) {
                final ImageIcon small_avatar = NickInfos.getSmall_avatar(this.nick);
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
        if (NickInfos.isTalk(this.nick)) {
            this.lblvip.setIcon(new ImageIcon(Resources.getImages("tique")));
        }
        else {
            this.lblvip.setIcon(null);
        }
        if (NickInfos.getAway(nickItem.getNick()) != null) {
            this.lblaway.setIcon(new ImageIcon(Resources.getImages("off-line")));
        }
        else {
            this.lblaway.setIcon(null);
            if (NickInfos.getHumeur(nickItem.getNick()) != null && Resources.getStringEirc("humeur.text." + NickInfos.getHumeur(nickItem.getNick())) != null) {
                this.lblvip.setText("  (" + Resources.getStringEirc("humeur.text." + NickInfos.getHumeur(nickItem.getNick())) + ")");
            }
            else {
                this.lblvip.setText("");
            }
        }
        return this;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public int getModes() {
        return this.modes;
    }
    
    public String getNick() {
        return this.nick;
    }
    
    public Color getSelectedBackground() {
        return this.selbg;
    }
    
    public Color getSelectedForeground() {
        return this.selfg;
    }
    
    public Color getTextBackground() {
        return this.textbg;
    }
    
    public Color getTextForeground() {
        return this.textfg;
    }
    
    public void resetMode(final int n) {
        this.modes &= ~n;
        this.setBullet(this.item_bullet);
    }
    
    public void setBullet(final boolean item_bullet) {
        this.current_bullet = null;
        this.item_bullet = item_bullet;
        if (item_bullet) {
            this._simple = false;
            if ((0x20 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[4];
            }
            else if ((0x10 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[3];
            }
            else if ((0x2 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[0];
            }
            else if ((0x4 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[1];
            }
            else if ((0x8 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[2];
            }
            else {
                this.current_bullet = NickitemRenderer.bullets[6];
                this._simple = true;
            }
            if ((0x40 & this.modes) != 0x0) {
                this.current_bullet = NickitemRenderer.bullets[5];
            }
        }
        else {
            this.current_bullet = null;
        }
        if (this.current_bullet != null) {
            this.lblnick.setText(this.lblnick.getText().trim());
            this.lblnick.setIcon(this.current_bullet);
        }
        else {
            this.lblnick.setIcon(null);
        }
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public void setMode(final int n) {
        this.modes |= n;
        this.setBullet(this.item_bullet);
    }
    
    public void setModes(final int modes) {
        this.modes = modes;
        this.setBullet(this.item_bullet);
    }
    
    public void setNick(final String s) {
        this.nick = s;
        this.lblnick.setText(s);
    }
    
    public void setSelectedBackground(final Color selbg) {
        this.selbg = selbg;
    }
    
    public void setSelectedForeground(final Color selfg) {
        this.selfg = selfg;
    }
    
    public void setTextBackground(final Color textbg) {
        this.textbg = textbg;
    }
    
    public void setTextForeground(final Color color) {
        this.textfg = color;
        this.lblnick.setForeground(color);
    }
    
    static {
        HIGHLIGHT_COLOR = new Color(0, 0, 128);
        NickitemRenderer.bullets_loaded = false;
        NickitemRenderer.bullets_loaded = true;
        (NickitemRenderer.bullets = new ImageIcon[7])[0] = new ImageIcon(Resources.getImages("op"));
        NickitemRenderer.bullets[1] = new ImageIcon(Resources.getImages("hop"));
        NickitemRenderer.bullets[2] = new ImageIcon(Resources.getImages("voiced"));
        NickitemRenderer.bullets[3] = new ImageIcon(Resources.getImages("admin"));
        NickitemRenderer.bullets[4] = new ImageIcon(Resources.getImages("owner"));
        NickitemRenderer.bullets[5] = new ImageIcon(Resources.getImages("away"));
        NickitemRenderer.bullets[6] = new ImageIcon(Resources.getImages("nonstar"));
        NickitemRenderer.star = new ImageIcon(Resources.getImages("star"));
        awayTxtColor = new Color(85, 85, 85);
    }
}
