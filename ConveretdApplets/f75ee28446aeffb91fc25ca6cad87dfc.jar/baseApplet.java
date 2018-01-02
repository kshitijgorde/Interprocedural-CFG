import java.awt.Component;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class baseApplet extends Applet
{
    private String[][] basicInfo;
    public Color foregroundColor;
    public Color backgroundColor;
    public int appletFontSize;
    public String appletName;
    public int height;
    public int width;
    public String[] engineParms;
    MediaTracker mt;
    public Image[] img;
    public String[] altImage;
    public int numItems;
    
    public void init() {
        this.backgroundColor = this.getParameterColor("backgroundColor", Color.white);
        this.foregroundColor = this.getParameterColor("foregroundColor", Color.black);
        this.appletFontSize = this.getParameterInt("appletFontSize", 12);
        if (this.getParameter("engine") != null) {
            this.getEngineParms();
        }
        else {
            (this.engineParms = new String[1])[0] = new String("  ");
        }
        this.setFont(new Font("Dialog", 0, this.appletFontSize));
        this.setBackground(this.backgroundColor);
        this.height = this.getSize().height;
        this.width = this.getSize().width;
        this.checkImagePreloads();
    }
    
    public String getParameterString(final String key, final String def) {
        return (this.getParameter(key) != null) ? this.getParameter(key) : def;
    }
    
    public Color getParameterColor(final String key, final Color def) {
        if (this.getParameter(key) == null) {
            return def;
        }
        Color color = null;
        try {
            color = Color.decode(this.getParameter(key));
        }
        catch (NumberFormatException ex) {
            return ((color = this.parseColor(this.getParameter(key))) != null) ? color : def;
        }
        return color;
    }
    
    public int getParameterInt(final String key, final int def) {
        if (this.getParameter(key) == null) {
            return def;
        }
        int i = 0;
        try {
            final Integer s = new Integer(this.getParameter(key));
            i = s;
        }
        catch (NumberFormatException ex) {
            return def;
        }
        return i;
    }
    
    public Color parseColor(final String s) {
        if (s == null) {
            return null;
        }
        final StringTokenizer st = new StringTokenizer(s, ",");
        final int[] rgb = new int[3];
        int i = 0;
        while (st.hasMoreTokens() && i < rgb.length) {
            String nextToken = st.nextToken();
            nextToken = nextToken.trim();
            try {
                rgb[i++] = Integer.parseInt(nextToken);
            }
            catch (NumberFormatException ex) {
                return null;
            }
        }
        return new Color(rgb[0], rgb[1], rgb[2]);
    }
    
    public void getEngineParms() {
        if (this.getParameter("loadLocal") == null) {
            int i = 0;
            final String[] data = new String[200];
            URL url = null;
            try {
                url = new URL(this.getCodeBase(), this.getParameter("engine"));
            }
            catch (MalformedURLException ex) {
                return;
            }
            try {
                final InputStream is = url.openStream();
                final BufferedReader dis = new BufferedReader(new InputStreamReader(is));
                try {
                    for (String s = dis.readLine(); s != null; s = dis.readLine()) {
                        if (s.length() != 0) {
                            data[i++] = s;
                        }
                    }
                    is.close();
                }
                catch (IOException ex2) {}
                this.engineParms = new String[i];
                for (int j = 0; j < i; ++j) {
                    this.engineParms[j] = data[j];
                }
            }
            catch (IOException ex3) {}
        }
        else {
            this.loadFromParamList();
        }
    }
    
    public void loadFromParamList() {
        int i;
        String[] data;
        for (i = 0, data = new String[200]; this.getParameter("dataLine" + i) != null; data[i] = this.getParameter("dataLine" + i++)) {}
        this.engineParms = new String[i];
        for (int j = 0; j < i; ++j) {
            this.engineParms[j] = data[j];
        }
    }
    
    public String getAppletInfo() {
        return new String(String.valueOf(this.appletName) + "  Copyright Ken Rigoni 1999");
    }
    
    private void checkImagePreloads() {
        String str = null;
        int i = 0;
        while (this.getParameter("img" + i) != null) {
            final StringTokenizer tok = new StringTokenizer(this.getParameter("img" + i), "|");
            str = tok.nextToken();
            this.img[i] = this.getImage(this.getDocumentBase(), str);
            if (tok.hasMoreTokens()) {
                this.altImage[i] = tok.nextToken();
            }
            this.mt.addImage(this.getImage(this.getDocumentBase(), str), i++);
        }
        if ((this.numItems = i) > 0) {
            try {
                this.mt.waitForAll();
            }
            catch (InterruptedException ex) {
                System.out.println("Error Loading Images");
            }
        }
    }
    
    public baseApplet() {
        this.basicInfo = new String[][] { { "foregroundColor", "String From PARAM", "Foreground Color in (r,g,b) format or Hex" }, { "backgroundColor", "String From PARAM", "Background Color in (r,g,b) format or Hex" }, { "appletFontSize", "String From PARAM", "Selected font Size" }, { "engine", "String[] From PARAM", "Parameters read from server file" }, { "height", "Public Variable", "Applet Height" }, { "width", "Public Variable", "Applet width" }, { "appletName", "Public Variable", "Name of Applet" } };
        this.appletName = new String();
        this.mt = new MediaTracker(this);
        this.img = new Image[100];
        this.altImage = new String[100];
    }
}
