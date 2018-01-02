import java.io.IOException;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Label;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Iruvar extends Applet
{
    Choice cState;
    Choice cCap;
    Label lCap;
    Hashtable hCap;
    
    public void init() {
        String s = "TimesRoman";
        String s2 = "capitol.txt";
        String text = " is the Capitol of ";
        int int1 = 100;
        int int2 = 20;
        final String parameter = this.getParameter("TOTAL");
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("FILENAME");
        if (parameter2 != null) {
            s2 = parameter2;
        }
        final String parameter3 = this.getParameter("LABELTEXT");
        if (parameter3 != null) {
            text = parameter3;
        }
        final String parameter4 = this.getParameter("FONT");
        if (parameter4 != null) {
            s = parameter4;
        }
        final String parameter5 = this.getParameter("FONTSIZE");
        if (parameter5 != null) {
            int2 = Integer.parseInt(parameter5);
        }
        this.hCap = new Hashtable(int1);
        this.cState = new Choice();
        this.cCap = new Choice();
        (this.lCap = new Label()).setText(text);
        this.cCap.setBackground(Color.yellow);
        this.cCap.setFont(new Font(s, 1, int2));
        this.cState.setBackground(Color.green);
        this.cState.setFont(new Font(s, 2, int2));
        this.setLayout(new BorderLayout());
        this.add("West", this.cCap);
        this.add("Center", this.lCap);
        this.add("East", this.cState);
        if (this.readFile(s2)) {
            final Enumeration<String> keys = (Enumeration<String>)this.hCap.keys();
            while (keys.hasMoreElements()) {
                final String s3 = keys.nextElement();
                this.cCap.addItem(s3);
                this.cState.addItem((String)this.hCap.get(s3));
            }
        }
        this.validate();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cState) {
            final int selectedIndex = this.cState.getSelectedIndex();
            final String s = this.hCap.get(this.cState.getSelectedItem());
            this.cCap.select(selectedIndex);
            return true;
        }
        if (event.target == this.cCap) {
            this.cCap.getSelectedIndex();
            this.cState.select(this.hCap.get(this.cCap.getSelectedItem()));
            return true;
        }
        return false;
    }
    
    public boolean readFile(final String s) {
        URL url;
        try {
            if (s.indexOf("http://") >= 0) {
                url = new URL(s);
            }
            else {
                url = new URL(this.getDocumentBase(), s);
            }
        }
        catch (MalformedURLException ex) {
            return false;
        }
        try {
            String line;
            while ((line = new DataInputStream(new BufferedInputStream(url.openStream())).readLine()) != null) {
                final String trim = line.trim();
                if (!trim.startsWith("#")) {
                    if (trim.length() == 0) {
                        continue;
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(trim, "-");
                    this.hCap.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                }
            }
        }
        catch (IOException ex2) {
            return false;
        }
        return true;
    }
}
