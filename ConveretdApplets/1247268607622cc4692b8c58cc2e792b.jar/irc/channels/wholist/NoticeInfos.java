// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.wholist;

import javax.swing.JComponent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import irc.managers.Resources;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.text.StyledDocument;
import irc.channels.textarea.NewTextMotionListener;
import irc.channels.textarea.NewTextClickListener;
import javax.swing.JLabel;
import javax.swing.JDialog;
import irc.EIRC;
import irc.channels.textarea.NewTextDocument;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import irc.channels.textarea.TextAreaEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import irc.channels.textarea.HyperlinkTextAreaEvent;
import irc.channels.components.Myjpanel;

public class NoticeInfos extends Myjpanel implements HyperlinkTextAreaEvent, AdjustmentListener, MouseMotionListener, MouseListener, TextAreaEvent
{
    private JScrollPane scrollpanel;
    public JTextPane textzone;
    private NewTextDocument doc;
    EIRC eirc;
    JDialog tempsDialog;
    JLabel l4;
    
    public NoticeInfos(final EIRC eirc) {
        this.l4 = new JLabel();
        this.scrollpanel = new JScrollPane(22, 31);
        this.textzone = new JTextPane();
        this.eirc = eirc;
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
        this.setLayout(new BorderLayout());
        this.add(this.scrollpanel, "Center");
        final JLabel label = new JLabel();
        final JLabel label2 = new JLabel();
        label.setPreferredSize(new Dimension(250, 5));
        label2.setPreferredSize(new Dimension(250, 5));
        final JLabel label3 = new JLabel();
        label3.setPreferredSize(new Dimension(5, 0));
        this.l4.setPreferredSize(new Dimension(25, 0));
        this.l4.setIcon(new ImageIcon(Resources.getImages("closenotice")));
        this.l4.addMouseListener(this);
        this.add(label, "North");
        this.add(label2, "South");
        this.add(label3, "West");
        this.add(this.l4, "East");
        this.textzone.setBackground(Color.white);
        this.setBackground(Color.decode("0xADD8E6"));
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    @Override
    public void autoAway() {
    }
    
    @Override
    public void Click() {
    }
    
    @Override
    public void doubleClick() {
    }
    
    public NewTextDocument getDoc() {
        return this.doc;
    }
    
    public JDialog getTempsDialog() {
        return this.tempsDialog;
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
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(this.l4)) {
            this.eirc.getFramemanager().masquer();
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.eirc.getFramemanager().setTime(90);
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.eirc.getFramemanager().setTime(30);
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
    
    public void printAction(final String s, final String s2, final boolean b) {
        this.doc.printAction(s, s2);
    }
    
    public void printPrivmsg(final String s, final String s2) {
        this.doc.printPrivmsg(s, s2);
    }
    
    public void scrollDown1() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex) {}
                NoticeInfos.this.scrollpanel.getVerticalScrollBar().setValue(NoticeInfos.this.scrollpanel.getVerticalScrollBar().getMaximum());
            }
        }.start();
    }
    
    public void setTempsDialog(final JDialog tempsDialog) {
        this.tempsDialog = tempsDialog;
    }
    
    @Override
    public void TextAdded(final String s) {
        this.scrollDown1();
    }
    
    public void vider() {
        this.textzone.setText("");
    }
}
