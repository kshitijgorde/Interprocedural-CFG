import java.util.StringTokenizer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.TextArea;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class iframe extends Applet implements Runnable
{
    Thread L;
    boolean pf;
    boolean pfK;
    boolean pfN;
    boolean FreeVersion;
    String hostName;
    boolean fromDisk;
    boolean FirstPass;
    TextArea D;
    Cursor C;
    String urlWhat;
    String TheURL;
    Color aBgColor;
    Color TextColor;
    Color PageColor;
    int FontSize;
    int FontStyle;
    String FontName;
    int MaxFileSize;
    int ScrollBars;
    
    public iframe() {
        this.pf = false;
        this.pfK = false;
        this.pfN = false;
        this.FreeVersion = false;
        this.hostName = "This URL";
        this.fromDisk = false;
        this.FirstPass = true;
        this.aBgColor = Color.white;
        this.TextColor = Color.black;
        this.PageColor = Color.white;
        this.FontSize = 12;
        this.FontStyle = 0;
        this.FontName = "Dialog";
        this.MaxFileSize = 64000;
        this.ScrollBars = 1;
    }
    
    public void init() {
        this.DoK();
        final String parameter = this.getParameter("TextFile");
        if (parameter != null) {
            this.TheURL = parameter;
        }
        final String parameter2 = this.getParameter("MaxFileSize");
        if (parameter2 != null) {
            this.MaxFileSize = Integer.parseInt(parameter2);
        }
        if (this.MaxFileSize < 16000) {
            this.MaxFileSize = 16000;
        }
        final String parameter3 = this.getParameter("ScrollBars");
        if (parameter3 != null) {
            this.ScrollBars = Integer.parseInt(parameter3);
        }
        if (this.ScrollBars < 0 || this.ScrollBars > 3) {
            this.ScrollBars = 1;
        }
        this.aBgColor = this.parseC(this.getParameter("AppletColor"), Color.white);
        this.TextColor = this.parseC(this.getParameter("FontColor"), Color.black);
        this.PageColor = this.parseC(this.getParameter("PageColor"), Color.white);
        final String parameter4 = this.getParameter("FontSize");
        if (parameter4 != null) {
            this.FontSize = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("FontStyle");
        if (parameter5 != null) {
            this.FontStyle = Integer.parseInt(parameter5);
        }
        if (this.FontStyle > 3 || this.FontStyle < 0) {
            this.FontStyle = 0;
        }
        final String parameter6 = this.getParameter("FontName");
        if (parameter6 != null) {
            if (parameter6.toUpperCase().startsWith("COURIER")) {
                this.FontName = "Courier";
            }
            if (parameter6.toUpperCase().startsWith("DIALOG")) {
                this.FontName = "Dialog";
            }
            if (parameter6.toUpperCase().startsWith("TIMESROMAN")) {
                this.FontName = "TimesRoman";
            }
        }
        this.setBackground(this.aBgColor);
        this.C = new Cursor(0);
        (this.D = new TextArea("", 1, 1, this.ScrollBars)).setEditable(false);
        this.D.setEnabled(false);
        this.D.setCursor(this.C);
        this.D.setBackground(this.PageColor);
        this.D.setForeground(this.TextColor);
        this.D.setFont(new Font(this.FontName, this.FontStyle, this.FontSize));
        this.setLayout(new BorderLayout());
        this.add("Center", this.D);
        String trim = null;
        if (this.TheURL != null) {
            trim = this.TheURL.trim();
        }
        if (trim == null || this.TheURL == null) {
            this.D.setText("TextFile not found");
            this.D.requestFocus();
        }
        else {
            this.doLoad(trim);
        }
    }
    
    public void doLoad(final String urlWhat) {
        this.urlWhat = urlWhat;
        (this.L = new Thread(this)).start();
    }
    
    public void destroy() {
        if (this.L != null && this.L.isAlive()) {
            this.L.stop();
        }
    }
    
    public void run() {
        if (this.FirstPass && this.pf && this.FreeVersion && !this.fromDisk) {
            this.D.setText("iFrame Applet\nBy www.CodeBrain.com\nFree Version 3.0.1\nPrivate/non-profit use only");
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex) {}
            this.D.setText("Loading...");
            try {
                Thread.sleep(777L);
            }
            catch (InterruptedException ex2) {}
            this.FirstPass = false;
        }
        else {
            this.D.setText("Loading...");
            try {
                Thread.sleep(777L);
            }
            catch (InterruptedException ex3) {}
        }
        Label_0195: {
            if (this.pf) {
                break Label_0195;
            }
            if (!this.pfN && this.pfK) {
                this.D.setText("Notice wrong\nor missing...");
            }
            if (!this.pfK && this.pfN) {
                this.D.setText(String.valueOf("Need key for:\n").concat(String.valueOf(this.hostName)));
            }
            if (!this.pfK && !this.pfN) {
                this.D.setText("Errors:\n Key & Notice");
            }
            this.L.stop();
            try {
                final Object content = new URL(this.getDocumentBase(), this.urlWhat).getContent();
                if (content instanceof String) {
                    this.D.setText((String)content);
                }
                else if (content instanceof InputStream || content instanceof Reader) {
                    BufferedReader bufferedReader;
                    if (content instanceof InputStream) {
                        bufferedReader = new BufferedReader(new InputStreamReader((InputStream)content));
                    }
                    else if (content instanceof BufferedReader) {
                        bufferedReader = (BufferedReader)content;
                    }
                    else {
                        bufferedReader = new BufferedReader((Reader)content);
                    }
                    final char[] array = new char[this.MaxFileSize];
                    int i;
                    int read;
                    for (i = 0; i < this.MaxFileSize; i += read) {
                        read = bufferedReader.read(array, i, this.MaxFileSize - i);
                        if (read == -1) {
                            break;
                        }
                    }
                    if (i == 0) {
                        this.D.setText("Error:\nCould not get data...");
                    }
                    else {
                        this.D.setText(new String(array, 0, i));
                        this.D.setEnabled(true);
                    }
                    bufferedReader.close();
                }
                else {
                    this.D.setText("Error:\nNon-string data");
                }
            }
            catch (MalformedURLException ex4) {
                this.D.setText("Error:\nBad TextFile URL syntax");
            }
            catch (SecurityException ex5) {
                this.D.setText("Security Error:\nCannot access URL...");
            }
            catch (IOException ex6) {
                this.D.setText("Input Error:\nCannot read data...");
            }
            finally {
                this.L = null;
            }
        }
    }
    
    private Color parseC(String trim, final Color color) {
        Color color2;
        try {
            trim = trim.replace('#', ' ').trim();
            color2 = new Color(Integer.valueOf(trim.substring(0, 2), 16), Integer.valueOf(trim.substring(2, 4), 16), Integer.valueOf(trim.substring(4, 6), 16));
        }
        catch (Exception ex) {
            color2 = color;
        }
        return color2;
    }
    
    private void DoK() {
        this.pf = false;
        this.pfN = false;
        this.pfK = false;
        final String s = "627542";
        final int int1 = Integer.parseInt(s.substring(0, 1));
        final int int2 = Integer.parseInt(s.substring(1, 2));
        final int int3 = Integer.parseInt(s.substring(2, 3));
        final int int4 = Integer.parseInt(s.substring(3, 4));
        final int int5 = Integer.parseInt(s.substring(4, 5));
        final int int6 = Integer.parseInt(s.substring(5, 6));
        final URL documentBase = this.getDocumentBase();
        this.hostName = documentBase.getHost();
        if (documentBase.getProtocol().toUpperCase().indexOf("FILE") > -1) {
            this.pf = true;
            this.pfK = true;
            this.fromDisk = true;
        }
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
                this.pfN = true;
            }
            else {
                this.pfN = false;
                this.pf = false;
            }
        }
        String concat = "CodeBrainRocks";
        final String parameter2 = this.getParameter("KeyCode");
        if (parameter2 == null || parameter2.startsWith("FREE")) {
            this.FreeVersion = true;
        }
        if (parameter2 != null && parameter2.length() > 10) {
            concat = parameter2;
        }
        int n = 0;
        for (int i = 0; i < concat.length(); ++i) {
            if (concat.substring(i, i + 1).indexOf("|") > -1) {
                ++n;
            }
        }
        if (n == 0) {
            concat = String.valueOf(concat).concat(String.valueOf("|CodeBrainRocks"));
            n = 1;
        }
        final String[] array = new String[13];
        final StringTokenizer stringTokenizer = new StringTokenizer(concat, "|");
        final int n2 = n + 1;
        for (int j = 0; j < n2; ++j) {
            array[j] = stringTokenizer.nextToken();
        }
        final String[] array2 = new String[13];
        for (int k = 0; k < n2; ++k) {
            final String substring = array[k].substring(3);
            final String substring2 = substring.substring(0, substring.length() - 3);
            String concat2 = "";
            int n3 = 0;
            for (int l = 0; l < substring2.length(); ++l) {
                int char1 = substring2.charAt(l);
                if (n3 == 0) {
                    char1 += int1;
                }
                if (n3 == 1) {
                    char1 += int2;
                }
                if (n3 == 2) {
                    char1 += int3;
                }
                if (n3 == 3) {
                    char1 += int4;
                }
                if (n3 == 4) {
                    char1 += int5;
                }
                if (n3 == 5) {
                    char1 += int6;
                }
                concat2 = String.valueOf(concat2).concat(String.valueOf(String.valueOf((char)char1)));
                if (++n3 > 5) {
                    n3 = 0;
                }
            }
            String concat3 = "";
            int n4 = 0;
            for (int n5 = 0; n5 < concat2.length(); ++n5) {
                int n6 = concat2.charAt(n5) - '\u0003';
                if (n6 == 118) {
                    n6 = 126;
                }
                concat3 = String.valueOf(concat3).concat(String.valueOf(String.valueOf((char)n6)));
                if (++n4 > 5) {
                    n4 = 0;
                }
            }
            array2[k] = concat3;
        }
        final String upperCase = String.valueOf(this.getDocumentBase()).toUpperCase();
        for (int n7 = 0; n7 < n2; ++n7) {
            if (upperCase.indexOf(array2[n7]) > -1) {
                this.pfK = true;
            }
        }
        if (this.FreeVersion) {
            this.pfK = true;
        }
        if (this.pfN && this.pfK) {
            this.pf = true;
        }
    }
}
