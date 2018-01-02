// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import javax.swing.JComponent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.text.StyledDocument;
import irc.channels.textarea.NewTextMotionListener;
import irc.channels.textarea.NewTextClickListener;
import java.awt.Rectangle;
import java.awt.Window;
import irc.channels.ChannelWindow;
import irc.channels.PrivateWindow;
import java.awt.GraphicsEnvironment;
import java.awt.KeyboardFocusManager;
import java.awt.event.AdjustmentEvent;
import java.util.TimerTask;
import java.util.Timer;
import irc.channels.components.Mynotice;
import irc.channels.textarea.NewTextDocument;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import irc.channels.textarea.TextAreaEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import irc.channels.textarea.HyperlinkTextAreaEvent;
import javax.swing.JDialog;

public class popnotice extends JDialog implements HyperlinkTextAreaEvent, AdjustmentListener, MouseMotionListener, MouseListener, TextAreaEvent
{
    private JLabel call1;
    private JLabel call2;
    private JLabel call3;
    private JLabel call4;
    private JScrollPane scrollpanel;
    public JTextPane textzone;
    private NewTextDocument doc;
    private EIRC eirc;
    private Mynotice Global;
    private int time;
    private String name;
    Timer timer;
    
    public popnotice(final EIRC eirc, final String name) {
        this.call1 = new JLabel();
        this.call2 = new JLabel();
        this.call3 = new JLabel();
        this.call4 = new JLabel();
        this.time = 0;
        this.setFocusable(false);
        this.eirc = eirc;
        this.name = name;
        this.init();
        this.time = 30;
        (this.timer = new Timer()).schedule(new Reduire(), 0L, 1000L);
        this.setAlwaysOnTop(true);
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    public void afficher() {
        new Thread() {
            @Override
            public void run() {
                final Window focusedWindow = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
                final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                final int n = maximumWindowBounds.height - 5;
                final int n2 = maximumWindowBounds.width - 255;
                popnotice.this.setLocation(n2, n);
                popnotice.this.setVisible(true);
                for (int i = 0; i < 160; i += 10) {
                    popnotice.this.setLocation(n2, n - i);
                    try {
                        Thread.sleep(30L);
                    }
                    catch (InterruptedException ex) {}
                }
                popnotice.this.setVisible(false);
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex2) {}
                popnotice.this.setVisible(true);
                if (focusedWindow != null) {
                    if (popnotice.this.eirc.getChat_panel().getCurrent() instanceof PrivateWindow) {
                        ((PrivateWindow)popnotice.this.eirc.getChat_panel().getCurrent()).EntryrequestFocus();
                    }
                    else if (popnotice.this.eirc.getChat_panel().getCurrent() instanceof ChannelWindow) {
                        ((ChannelWindow)popnotice.this.eirc.getChat_panel().getCurrent()).entryrequestFocus();
                    }
                }
                popnotice.this.textzone.repaint();
                popnotice.this.textzone.validate();
                popnotice.this.repaint();
                popnotice.this.validate();
            }
        }.start();
    }
    
    @Override
    public void autoAway() {
    }
    
    @Override
    public void Click() {
        this.openPrivate(this.name);
    }
    
    @Override
    public void doubleClick() {
    }
    
    public NewTextDocument getDoc() {
        return this.doc;
    }
    
    @Override
    public void handleHyperlink(final String s) {
    }
    
    @Override
    public void handleNick(final String s) {
    }
    
    @Override
    public void hidePopupInfos() {
    }
    
    public void init() {
        this.Global = new Mynotice(this.eirc);
        this.setUndecorated(true);
        this.scrollpanel = new JScrollPane();
        this.textzone = new JTextPane();
        this.doc = new NewTextDocument(this.eirc, this);
        this.textzone.setEditable(false);
        this.textzone.addMouseListener(new NewTextClickListener(this, this.textzone));
        this.textzone.addMouseMotionListener(new NewTextMotionListener(this, this.textzone));
        this.textzone.setStyledDocument(this.doc);
        this.textzone.setCaretPosition(this.textzone.getStyledDocument().getLength());
        this.scrollpanel.getViewport().add(this.textzone);
        this.scrollpanel.setAutoscrolls(true);
        this.scrollpanel.getVerticalScrollBar().addAdjustmentListener(this);
        this.textzone.addMouseMotionListener(this);
        this.textzone.setBackground(Color.white);
        this.Global.setLayout(new BorderLayout());
        this.scrollpanel.getViewport().add(this.textzone);
        this.Global.add(this.call1, "North");
        this.Global.add(this.scrollpanel, "Center");
        this.Global.add(this.call2, "South");
        this.Global.add(this.call3, "East");
        this.Global.add(this.call4, "West");
        this.scrollpanel.setVerticalScrollBarPolicy(22);
        this.call1.setPreferredSize(new Dimension(250, 25));
        this.call2.setPreferredSize(new Dimension(250, 5));
        this.call3.setPreferredSize(new Dimension(5, 150));
        this.call4.setPreferredSize(new Dimension(5, 150));
        this.setContentPane(this.Global);
        this.Global.addMouseListener(this);
        this.setSize(250, 150);
        this.getContentPane().addMouseMotionListener(this);
        this.afficher();
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.time = 30;
        if (mouseEvent.getX() > 230 && mouseEvent.getY() < 15) {
            this.dispose();
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.time = 30;
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.time = 30;
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.time = 30;
        if (mouseEvent.getX() > 230 && mouseEvent.getY() < 15) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void MouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void openPrivate(final String s) {
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
    }
    
    @Override
    public void TextAdded(final String s) {
    }
    
    public class Reduire extends TimerTask
    {
        @Override
        public void run() {
            if (popnotice.this.time <= 0) {
                popnotice.this.timer.cancel();
                popnotice.this.dispose();
            }
            else {
                popnotice.this.time--;
            }
        }
    }
}
