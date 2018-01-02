// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Properties;
import buddysoft.SlideShow;
import java.awt.Image;
import java.awt.Color;

public class a extends d
{
    public c[] b;
    public Color byte;
    public Color g;
    public Color case;
    public Color d;
    public String new;
    public Image int;
    public String e;
    public String c;
    public String long;
    public String else;
    public String goto;
    public String try;
    public int do;
    public int void;
    public String null;
    public static boolean f;
    String for;
    String char;
    
    public a(final SlideShow slideShow) {
        this.e = "Replay";
        this.c = "Play";
        this.long = "Stop";
        this.else = "Skip back";
        this.goto = "Skip forward";
        this.try = "Pause";
        this.do = 6;
        this.void = 6;
        this.null = "Unregistreted 30-day Evaluation Copy!";
        String parameter = slideShow.getParameter("configFile");
        if (parameter == null || parameter.equals("")) {
            parameter = "slide_images/slide_show.cfg";
        }
        final Properties properties = new Properties();
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(slideShow.getCodeBase(), parameter).openStream());
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
        }
        catch (SecurityException ex2) {
            slideShow.alert("Invalid folder (path) name! \\n\\nCannot find " + parameter);
        }
        catch (FileNotFoundException ex3) {
            slideShow.alert("Invalid configuration file name! \\n\\nCannot find " + parameter);
        }
        catch (Exception ex) {
            slideShow.alert("Configuration file Error! \\n\\n" + ex);
        }
        int n;
        for (n = 0; properties.getProperty("img" + (n + 1) + ".src") != null; ++n) {}
        this.b = new c[n];
        final String substring = parameter.substring(0, parameter.indexOf(47) + 1);
        for (int i = 0; i < this.b.length; ++i) {
            final String string = "img" + (i + 1);
            this.b[i] = new c();
            this.b[i].try = properties.getProperty(string + ".src");
            final String property = properties.getProperty(string + ".url");
            if (property != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(property, "*");
                final String trim = stringTokenizer.nextToken().trim();
                try {
                    this.b[i].a = new URL(slideShow.getDocumentBase(), trim);
                }
                catch (MalformedURLException ex4) {
                    slideShow.alert("Bad URL! \\n\\n<" + trim + "> is invalid URL!");
                }
                this.b[i].new = (stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "_self");
            }
            this.b[i].for = slideShow.getImage(slideShow.getCodeBase(), substring + this.b[i].try);
            this.b[i].do = Integer.parseInt(properties.getProperty(string + ".delay", "1000"));
            this.b[i].if = properties.getProperty(string + ".dir", "fl").toLowerCase();
        }
        this.int = slideShow.getImage(slideShow.getCodeBase(), substring + properties.getProperty("logo", "logo.gif"));
        this.byte = this.if(properties.getProperty("bgColor", "#2055bb"));
        this.g = this.if(properties.getProperty("fgColor", "white"));
        this.case = this.if(properties.getProperty("buttonBG", "orange"));
        this.d = this.if(properties.getProperty("buttonFG", "black"));
        this.new = properties.getProperty("autoStart", "true");
        final e e = new e();
        this.for = properties.getProperty("regID");
        this.char = slideShow.getCodeBase().getHost();
        if (this.char.startsWith("www.")) {
            this.char = this.char.substring(4);
        }
        a.f = e.a(this.for, this.char);
    }
}
