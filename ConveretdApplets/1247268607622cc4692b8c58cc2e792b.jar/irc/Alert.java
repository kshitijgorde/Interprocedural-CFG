// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.event.MouseEvent;
import irc.com.utils.IEAutoStart;
import java.awt.event.ActionEvent;
import javax.swing.text.StyledDocument;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import irc.channels.textarea.NewTextMotionListener;
import java.awt.event.MouseListener;
import irc.channels.textarea.NewTextClickListener;
import java.awt.Color;
import java.awt.Frame;
import irc.channels.textarea.NewTextDocument;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import irc.channels.textarea.TextAreaEvent;
import irc.channels.textarea.HyperlinkTextAreaEvent;
import javax.swing.JDialog;

public class Alert extends JDialog implements HyperlinkTextAreaEvent, TextAreaEvent, ActionListener
{
    private JScrollPane scrollpanel;
    private JTextPane textzone;
    private JButton fermer;
    private NewTextDocument doc;
    
    public Alert(final EIRC eirc, final String s) {
        super(eirc.getAccueil().Application, "Chat-land.org", true);
        this.setResizable(false);
        this.setSize(500, 150);
        (this.textzone = new JTextPane()).setBackground(Color.white);
        this.textzone.setEditable(false);
        this.textzone.addMouseListener(new NewTextClickListener(this, this.textzone));
        this.textzone.addMouseMotionListener(new NewTextMotionListener(this, this.textzone));
        this.textzone.setToolTipText("Double clique sur cette zone permet d'afficher plus d'infos sur la personne.");
        this.scrollpanel = new JScrollPane();
        this.scrollpanel.getViewport().add(this.textzone);
        this.scrollpanel.setAutoscrolls(true);
        this.scrollpanel.setBounds(20, 10, 450, 60);
        this.add(this.scrollpanel);
        this.setLayout(null);
        (this.fermer = new JButton("Fermer")).setBounds(190, 80, 100, 30);
        this.fermer.addActionListener(this);
        this.add(this.fermer);
        this.doc = new NewTextDocument(eirc, this);
        this.textzone.setStyledDocument(this.doc);
        final boolean showtime = eirc.isShowtime();
        eirc.setShowtime(false);
        this.doc.printAction(s, "");
        eirc.setShowtime(showtime);
        this.setLocation(eirc.getAccueil().Application.getLocation());
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
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
    
    @Override
    public void handleHyperlink(String string) {
        if (string.startsWith("http://") || string.startsWith("www")) {
            if (string.startsWith("www")) {
                string = "http://" + string;
            }
            IEAutoStart.run(string);
        }
    }
    
    @Override
    public void handleNick(final String s) {
    }
    
    @Override
    public void hidePopupInfos() {
    }
    
    @Override
    public void MouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void openPrivate(final String s) {
    }
    
    @Override
    public void popupCopyPaste(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void popupInfos(final String s, final String s2, final MouseEvent mouseEvent) {
    }
    
    @Override
    public void TextAdded(final String s) {
    }
}
