// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.nicklist;

import java.awt.SystemColor;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import irc.EIRC;
import irc.com.User;
import irc.com.nick.NickInfos;
import java.util.Hashtable;
import java.awt.Color;
import java.util.Vector;

public class NickListVector extends Vector
{
    public static final int SYMBOL_RENDERER = 0;
    public static final int BULLET_RENDERER = 1;
    public static final int SORT_ALPHA = 0;
    public static final int SORT_ALPHA_FAVOR_MODE = 1;
    private int item_renderer;
    private static Color textbg;
    private static Color textfg;
    private static Color selbg;
    private static Color selfg;
    Hashtable items;
    int botsize;
    
    public NickListVector() {
        this.item_renderer = 1;
        this.botsize = 0;
        this.items = new Hashtable();
    }
    
    public void add(final String s, final int modes) {
        if (this.items.containsKey(s)) {
            return;
        }
        if (NickInfos.getLocation(s).equalsIgnoreCase("m")) {
            ++this.botsize;
            return;
        }
        final NickItem nickItem = new NickItem(s, NickInfos.getSex(s), NickInfos.getLocation(s), NickInfos.getAge(s));
        switch (this.item_renderer) {
            case 1: {
                nickItem.item_bullet = true;
                break;
            }
            case 0: {
                nickItem.item_bullet = false;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid NickItem renderer selected");
            }
        }
        nickItem.setForeground(NickListVector.selfg);
        nickItem.setBackground(NickListVector.selbg);
        nickItem.setSelectedForeground(NickListVector.selfg);
        nickItem.setSelectedBackground(NickListVector.selbg);
        nickItem.setModes(modes);
        nickItem.setTextBackground(NickListVector.textbg);
        if ((modes & 0x20) != 0x0) {
            final String inetAddr = NickInfos.getInetAddr(nickItem.getNick());
            if (inetAddr != null) {
                if (inetAddr.startsWith("CoChefZone")) {
                    nickItem.setTextForeground(Color.RED);
                }
                else if (inetAddr.startsWith("ChefAdmin") || inetAddr.startsWith("ChefZone")) {
                    nickItem.setTextForeground(Color.CYAN);
                }
            }
            else {
                nickItem.setTextForeground(User.COLOR_OWNER);
            }
        }
        else if ((modes & 0x10) != 0x0) {
            nickItem.setTextForeground(User.COLOR_ADMIN);
        }
        else if ((modes & 0x2) != 0x0) {
            nickItem.setTextForeground(User.COLOR_OP);
        }
        else if ((modes & 0x4) != 0x0) {
            nickItem.setTextForeground(User.COLOR_HOP);
        }
        else if ((modes & 0x8) != 0x0) {
            if (NickInfos.getSex(s) == 1) {
                nickItem.setTextForeground(Color.BLUE);
            }
            else {
                nickItem.setTextForeground(new Color(170, 0, 170));
            }
        }
        else {
            nickItem.setTextForeground(NickInfos.nickToColor(s));
        }
        this.add(nickItem);
        this.items.put(s, nickItem);
    }
    
    public void clearAllNicks() {
        this.items.clear();
        this.clear();
    }
    
    public String[] getList() {
        synchronized (this) {
            final String[] array = new String[this.size()];
            for (int i = 0; i < this.size(); ++i) {
                array[i] = ((NickItem)this.elementAt(i)).getNick();
            }
            return array;
        }
    }
    
    public void mode(final String s, final int modes) {
        if (this.items == null) {
            return;
        }
        final NickItem nickItem = this.items.get(s);
        this.remove(nickItem);
        if (nickItem == null) {
            return;
        }
        nickItem.setModes(modes);
        if ((modes & 0x20) != 0x0) {
            final String inetAddr = NickInfos.getInetAddr(nickItem.getNick());
            if (inetAddr != null) {
                if (inetAddr.startsWith("CoChefZone")) {
                    nickItem.setTextForeground(Color.RED);
                }
                else if (inetAddr.startsWith("ChefAdmin") || inetAddr.startsWith("ChefZone")) {
                    nickItem.setTextForeground(Color.CYAN);
                }
            }
            else {
                nickItem.setTextForeground(User.COLOR_OWNER);
            }
        }
        else if ((modes & 0x10) != 0x0) {
            nickItem.setTextForeground(User.COLOR_ADMIN);
        }
        else if ((modes & 0x2) != 0x0) {
            nickItem.setTextForeground(User.COLOR_OP);
        }
        else if ((modes & 0x4) != 0x0) {
            nickItem.setTextForeground(User.COLOR_HOP);
        }
        else if ((modes & 0x8) != 0x0) {
            if (NickInfos.getSex(s) == 1) {
                nickItem.setTextForeground(Color.BLUE);
            }
            else {
                nickItem.setTextForeground(new Color(170, 0, 170));
            }
        }
        else {
            nickItem.setTextForeground(NickInfos.nickToColor(s));
        }
        this.add(nickItem);
    }
    
    public int mySize() {
        return this.size() + this.botsize;
    }
    
    public void remove(final String s) {
        synchronized (this) {
            this.remove(this.items.remove(s));
            this.items.remove(s);
        }
    }
    
    public void rename(final String s, final String nick) {
        final NickItem nickItem = this.items.remove(s);
        if (nickItem == null) {
            return;
        }
        synchronized (this) {
            this.remove(nickItem);
            nickItem.setNick(nick);
            this.add(nickItem);
            this.items.put(nick, nickItem);
        }
    }
    
    public void sort() {
        synchronized (this) {
            Collections.sort((List<Object>)this, new NickComparator(EIRC.SORTING_METHOD, 1));
        }
    }
    
    static {
        NickListVector.textbg = SystemColor.text;
        NickListVector.textfg = SystemColor.textText;
        NickListVector.selbg = SystemColor.textHighlight;
        NickListVector.selfg = SystemColor.textHighlightText;
    }
}
