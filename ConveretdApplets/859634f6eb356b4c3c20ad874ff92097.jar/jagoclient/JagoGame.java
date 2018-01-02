// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Button;
import java.awt.List;
import jagoclient.board.LocalGoFrame;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class JagoGame extends Applet implements ActionListener, Runnable
{
    LocalGoFrame GF;
    List L;
    Button B;
    String Game;
    String Games;
    Vector Urls;
    
    public synchronized void init() {
        this.Game = this.getParameter("Game");
        this.Games = this.getParameter("Games");
        this.setLayout(new BorderLayout());
        if (this.Games != null && !this.Games.equals("")) {
            this.add("Center", this.L = new List());
            this.Urls = new Vector();
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(new URL(this.Games).openStream())));
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    final String line2 = bufferedReader.readLine();
                    if (line2 == null) {
                        break;
                    }
                    this.L.add(line);
                    this.Urls.addElement(line2);
                }
            }
            catch (Exception ex) {}
            final Panel panel = new Panel();
            panel.add(this.B = new Button(Global.resourceString("Load")));
            this.add("South", panel);
        }
        else {
            this.add("Center", this.B = new Button(Global.resourceString("Load")));
        }
        this.B.addActionListener(this);
        Global.url(this.getCodeBase());
        Global.readparameter("jago/go.cfg");
        Global.createfonts();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        Global.setcomponent(this.GF = new LocalGoFrame(new Frame(), Global.resourceString("Jago_Viewer")));
        if (this.L != null) {
            this.Game = this.Urls.elementAt(this.L.getSelectedIndex());
        }
        else {
            this.Game = this.getParameter("Game");
        }
        new Thread(this).start();
    }
    
    public void run() {
        this.GF.setVisible(false);
        try {
            if (this.Game != null) {
                this.GF.load(new URL(this.Game));
            }
        }
        catch (Exception ex) {}
        this.GF.setVisible(true);
    }
    
    public void load(final String game) {
        this.Game = game;
        this.GF = new LocalGoFrame(new Frame(), Global.resourceString("Jago_Viewer"));
        new Thread(this).start();
    }
}
