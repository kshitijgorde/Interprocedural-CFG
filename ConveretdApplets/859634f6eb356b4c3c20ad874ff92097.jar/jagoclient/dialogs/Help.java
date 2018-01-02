// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import jagoclient.gui.Panel3D;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.MyPanel;
import java.awt.Component;
import java.awt.Window;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.net.URL;
import java.io.BufferedReader;
import rene.viewer.SystemViewer;
import jagoclient.Global;
import rene.viewer.Viewer;
import jagoclient.gui.CloseFrame;

public class Help extends CloseFrame implements Runnable
{
    Viewer V;
    
    public Help(final String s) {
        super(Global.resourceString("Help"));
        this.seticon("ihelp.gif");
        (this.V = (rene.gui.Global.getParameter("systemviewer", false) ? new SystemViewer() : new Viewer())).setFont(Global.Monospaced);
        try {
            final BufferedReader stream = Global.getStream("jago/" + s + ".txt");
            for (String s2 = stream.readLine(); s2 != null; s2 = stream.readLine()) {
                this.V.appendLine(s2);
            }
            stream.close();
        }
        catch (Exception ex) {
            new Message(Global.frame(), Global.resourceString("Could_not_find_the_help_file_"));
            this.doclose();
            return;
        }
        this.display();
    }
    
    public Help() {
        super(Global.resourceString("Help"));
        this.seticon("ihelp.gif");
        (this.V = (rene.gui.Global.getParameter("systemviewer", false) ? new SystemViewer() : new Viewer())).setFont(Global.Monospaced);
        new Thread(this).start();
    }
    
    public void run() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(new URL("http://mathsrv.ku-eichstaett.de/MGF/homes/grothmann/jago/about.txt").openStream())));
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                this.V.appendLine(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            new Message(Global.frame(), Global.resourceString("Could_not_find_the_help_file_"));
            this.doclose();
            return;
        }
        this.display();
    }
    
    void display() {
        Global.setwindow(this, "help", 500, 400);
        this.add("Center", this.V);
        final MyPanel myPanel = new MyPanel();
        myPanel.add(new ButtonAction(this, Global.resourceString("Close")));
        this.add("South", new Panel3D(myPanel));
        this.show();
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "help");
        super.doAction(s);
    }
}
