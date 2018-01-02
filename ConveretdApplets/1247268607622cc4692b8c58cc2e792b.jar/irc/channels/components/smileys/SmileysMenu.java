// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components.smileys;

import java.awt.Cursor;
import irc.channels.ChannelWindow;
import irc.channels.PrivateWindow;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import irc.managers.Resources;
import javax.swing.JButton;
import java.util.StringTokenizer;
import javax.swing.JToolBar;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

public class SmileysMenu extends JPopupMenu implements ActionListener, MouseListener
{
    private Object chat;
    private JToolBar tb;
    StringTokenizer st;
    JButton[] smiley;
    
    public SmileysMenu(final Object chat) {
        this.st = new StringTokenizer(Resources.getStringEirc("smiley.toolbar.list"), "/");
        this.smiley = new JButton[this.st.countTokens()];
        this.setOpaque(false);
        this.setToolTipText("Smileys");
        this.chat = chat;
        (this.tb = new JToolBar()).setBackground(this.tb.getBackground());
        this.loadSmileys();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void colorBackground(final Color color) {
        this.tb.setBackground(color);
        for (int i = 0; i < this.smiley.length; ++i) {
            this.smiley[i].setBackground(color);
        }
    }
    
    public void colorForeground(final Color foreground) {
        for (int i = 0; i < this.smiley.length; ++i) {
            this.smiley[i].setForeground(foreground);
        }
    }
    
    public void free() {
        this.tb.removeAll();
        this.removeAll();
        for (int i = 0; i < this.smiley.length; ++i) {
            this.smiley[i] = null;
        }
        this.chat = null;
        this.tb = null;
    }
    
    @Override
    public Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    @Override
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
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
    
    private void loadSmileys() {
        int n = 0;
        final Dimension optimalSize = this.getOptimalSize(58);
        this.tb.setLayout(new GridLayout(optimalSize.width, optimalSize.height, 0, 0));
        this.tb.setFloatable(false);
        this.st = new StringTokenizer(Resources.getStringEirc("smiley.toolbar.list"), "/");
        while (this.st.hasMoreTokens()) {
            final String nextToken = this.st.nextToken();
            this.smiley[n] = new JButton(new ImageIcon(Resources.getSmileyImage("symbol." + nextToken + ".image")));
            this.tb.add(this.smiley[n]);
            final String nextToken2 = new StringTokenizer(Resources.getStringSmiley("symbol." + nextToken + ".emoticons")).nextToken();
            this.smiley[n].setPreferredSize(new Dimension(40, 30));
            this.smiley[n].setToolTipText(nextToken2);
            this.smiley[n].addMouseListener(this);
            this.smiley[n].setName(nextToken2);
            ++n;
        }
        this.add(this.tb);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.chat instanceof PrivateWindow) {
            ((PrivateWindow)this.chat).insertSmiley(((JButton)mouseEvent.getComponent()).getName());
            this.setVisible(false);
            ((PrivateWindow)this.chat).getEntry().requestFocus();
            ((PrivateWindow)this.chat).getEirc().revenir();
        }
        else if (this.chat instanceof ChannelWindow) {
            ((ChannelWindow)this.chat).insertSmiley(((JButton)mouseEvent.getComponent()).getName());
            this.setVisible(false);
            ((ChannelWindow)this.chat).getEntry().requestFocus();
            ((ChannelWindow)this.chat).getEirc().revenir();
        }
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void setConnected(final boolean enabled) {
        this.tb.setEnabled(enabled);
    }
}
