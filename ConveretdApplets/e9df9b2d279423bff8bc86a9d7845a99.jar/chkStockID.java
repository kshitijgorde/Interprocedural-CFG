import netscape.javascript.JSObject;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class chkStockID extends Applet
{
    int rtExc;
    Thread runner;
    String[] tseId;
    String[] otcId;
    int[] tickQ;
    Color BGColor;
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.BGColor);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
    }
    
    public chkStockID() {
        this.rtExc = 0;
        this.runner = null;
        this.tickQ = new int[5];
    }
    
    void chkExc() {
        if (this.rtExc == 1) {
            try {
                Thread.sleep((int)(Math.random() * 2000.0));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void inId() {
        do {
            this.tseId = this.parseP0("/f/p0.just?s=tseid", this.tickQ);
            this.chkExc();
        } while (this.rtExc == 1);
        do {
            this.otcId = this.parseP0("/f/p0.just?s=otcid", this.tickQ);
            this.chkExc();
        } while (this.rtExc == 1);
    }
    
    public int idChk(final String s) {
        if (this.idPos(s.trim().toUpperCase()) == -1) {
            return 0;
        }
        return 1;
    }
    
    private String[] parseP0(final String s, final int[] array) {
        String[] array2 = null;
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), s).openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            String string = "";
            int n = 0;
            do {
                string += (char)inputStream.read();
            } while (++n < 3);
            if (string.compareTo("RC=") == 0) {
                array2 = new String[0];
                inputStream.close();
                this.rtExc = 0;
                return array2;
            }
            if (string.compareTo("P03") != 0) {
                array2 = new String[0];
                inputStream.close();
                this.rtExc = 1;
                return array2;
            }
            final int n2 = (inputStream.read() << 8) + inputStream.read();
            array2 = new String[n2];
            array[0] = (inputStream.read() << 8) + inputStream.read();
            array[1] = inputStream.read();
            array[2] = (inputStream.read() << 8) + inputStream.read();
            array[3] = (inputStream.read() << 8) + inputStream.read();
            array[4] = (inputStream.read() << 8) + inputStream.read();
            for (int i = 0; i < n2; ++i) {
                array2[i] = "";
                int n3 = 0;
                do {
                    array2[i] += (char)inputStream.read();
                } while (++n3 < 6);
                array2[i] = array2[i].trim();
            }
            inputStream.close();
            this.rtExc = 0;
        }
        catch (Exception ex) {
            this.rtExc = 1;
        }
        return array2;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void notifyLoaded() {
        final String parameter = this.getParameter("LOAD");
        if (parameter != null) {
            try {
                JSObject.getWindow((Applet)this).eval(parameter);
            }
            catch (Exception ex) {}
        }
    }
    
    private int idPos(final String s) {
        int n = 0;
        String[] array;
        boolean b;
        if (s.compareTo("4") > 0 && s.compareTo("9") < 0) {
            array = this.otcId;
            n += 10000;
            b = false;
        }
        else {
            array = this.tseId;
            b = true;
        }
        int i = 0;
        int n2 = array.length - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final int compareTo = array[n3].compareTo(s);
            if (compareTo == 0) {
                return n3;
            }
            if (compareTo < 0) {
                i = n3 + 1;
            }
            else {
                n2 = n3 - 1;
            }
        }
        String[] array2;
        if (b) {
            array2 = this.otcId;
        }
        else {
            array2 = this.tseId;
        }
        int j = 0;
        int n4 = array2.length - 1;
        while (j <= n4) {
            final int n5 = (j + n4) / 2;
            final int compareTo2 = array2[n5].compareTo(s);
            if (compareTo2 == 0) {
                return n5;
            }
            if (compareTo2 < 0) {
                j = n5 + 1;
            }
            else {
                n4 = n5 - 1;
            }
        }
        return -1;
    }
    
    public void init() {
        if (this.getParameter("BGCOLOR") == null) {
            this.BGColor = new Color(14806004);
        }
        else {
            this.BGColor = new Color(Integer.parseInt(this.getParameter("BGCOLOR"), 16));
        }
        this.inId();
        this.notifyLoaded();
    }
}
