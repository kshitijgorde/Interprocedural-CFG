import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Event;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Button;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tchart3 extends Applet
{
    private Vector dFile;
    private int iMax;
    private int iAct;
    private boolean bReg;
    static String sR;
    private boolean bLoc;
    private char cSep;
    cchart[] cv;
    Button b1;
    
    private void initChart() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < this.iAct; ++i) {
            this.remove(this.cv[i]);
        }
        this.iAct = this.iMax;
        this.cv = new cchart[this.iMax];
        final Image[] array = new Image[this.iMax];
        for (int j = 0; j < this.iMax; ++j) {
            int n = 0;
            array[j] = null;
            (this.cv[j] = new cchart()).clearData();
            this.cv[j].setFont(((rd)this.dFile.elementAt(j)).sFont, 0, (int)((rd)this.dFile.elementAt(j)).iD[3]);
            this.cv[j].setBkColor((int)((rd)this.dFile.elementAt(j)).iD[4]);
            final int col = (int)this.dFile.elementAt(j).iD[0];
            if (((rd)this.dFile.elementAt(j)).sBg != null) {
                mediaTracker.addImage(array[j] = this.getImage(this.getCodeBase(), ((rd)this.dFile.elementAt(j)).sBg), 0);
            }
            this.cv[j].setBkImage(array[j]);
            this.cv[j].setCol(col);
            int n2 = 0;
            final String sUrl = this.dFile.elementAt(j).sUrl;
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(new URL(this.getCodeBase(), sUrl).openStream());
                this.bLoc = false;
            }
            catch (Exception ex) {
                dataInputStream = null;
                this.bLoc = true;
            }
            while (true) {
                String s;
                if (this.bLoc) {
                    s = this.getParameter(sUrl + n2);
                    ++n2;
                }
                else {
                    try {
                        s = dataInputStream.readLine();
                    }
                    catch (Exception ex2) {
                        break;
                    }
                }
                int char1;
                if (s != null) {
                    char1 = s.charAt(0);
                }
                else {
                    char1 = 32;
                }
                if (char1 != 59 && char1 != 47) {
                    final rd rd = new rd();
                    if (!rd.get(rd, s, this.cSep, col)) {
                        break;
                    }
                    if (rd.sIt.equals("col")) {
                        this.cv[j].addCol(n++, (int)rd.iD[0], rd.sUrl);
                    }
                    else if (rd.sIt.equals("title")) {
                        this.cv[j].setTitle(rd.sUrl);
                    }
                    else {
                        this.cv[j].addRow(rd.iD, rd.sIt);
                    }
                }
            }
            this.cv[j].setPress((int)((rd)this.dFile.elementAt(j)).iD[1], (int)((rd)this.dFile.elementAt(j)).iD[2]);
            if (!this.bReg && ((rd)this.dFile.elementAt(j)).iD[6] < 10.0) {
                final double[] id = this.dFile.elementAt(j).iD;
                final int n3 = 6;
                id[n3] += 20.0;
            }
            this.add(this.cv[j]);
            this.cv[j].reshape((int)((rd)this.dFile.elementAt(j)).iD[5], (int)((rd)this.dFile.elementAt(j)).iD[6], (int)((rd)this.dFile.elementAt(j)).iD[7], (int)((rd)this.dFile.elementAt(j)).iD[8]);
            if (((rd)this.dFile.elementAt(j)).iD[9] != rd.iNan && ((rd)this.dFile.elementAt(j)).iD[9] > 0.0) {
                this.cv[j].setVal((int)((rd)this.dFile.elementAt(j)).iD[9]);
            }
            if (((rd)this.dFile.elementAt(j)).iD[10] != ((rd)this.dFile.elementAt(j)).iD[11]) {
                this.cv[j].setMinMax(((rd)this.dFile.elementAt(j)).iD[10], ((rd)this.dFile.elementAt(j)).iD[11]);
            }
            if (((rd)this.dFile.elementAt(j)).iD[12] != rd.iNan) {
                this.cv[j].setDec((int)((rd)this.dFile.elementAt(j)).iD[12]);
            }
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex3) {}
    }
    
    public void readFile(final String s) {
        this.dFile.removeAllElements();
        this.iMax = 0;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new URL(this.getCodeBase(), s).openStream());
            this.bLoc = false;
        }
        catch (Exception ex) {
            dataInputStream = null;
            this.bLoc = true;
        }
        int n = 0;
        while (true) {
            final rd rd = new rd();
            String s2;
            if (this.bLoc) {
                s2 = this.getParameter(s + n);
                ++n;
            }
            else {
                try {
                    s2 = dataInputStream.readLine();
                }
                catch (Exception ex2) {
                    return;
                }
            }
            int char1;
            if (s2 != null) {
                char1 = s2.charAt(0);
            }
            else {
                char1 = 32;
            }
            if (char1 != 59 && char1 != 47) {
                if (!rd.get(rd, s2, this.cSep, 13)) {
                    break;
                }
                this.dFile.addElement(rd);
                ++this.iMax;
            }
        }
    }
    
    public tchart3() {
        this.dFile = new Vector(5, 5);
        this.iMax = 1;
        this.cSep = '§';
    }
    
    static {
        tchart3.sR = "http://www.javaside.com";
    }
    
    public String getAppletInfo() {
        return "Name: tchart V 4.30\r\n" + "Author: R. BERTHOU\r\n" + "E-Mail : rbl@berthou.comr\n" + "URL : " + tchart3.sR;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            try {
                this.getAppletContext().showDocument(new URL(tchart3.sR), "r");
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 14));
        this.setLayout(null);
        final String parameter = this.getParameter("csep");
        if (parameter != null) {
            this.cSep = parameter.charAt(0);
        }
        String parameter2 = this.getParameter("file");
        if (parameter2 == null) {
            parameter2 = "file3.txt";
        }
        if (!this.bReg) {
            this.add(this.b1 = new Button("About"));
            this.b1.reshape(1, 1, 100, 22);
        }
        this.setFile(parameter2);
    }
    
    public void setFile(final String s) {
        this.readFile(s);
        this.initChart();
        this.repaint();
    }
}
