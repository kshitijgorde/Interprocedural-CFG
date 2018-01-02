import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Choice;
import java.awt.Color;
import java.util.StringTokenizer;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class link extends Applet
{
    public int number;
    public Vector urls;
    public Vector names;
    public URL theURL;
    String at;
    StringTokenizer st;
    StringTokenizer str;
    String targetFrame;
    Color bgColor;
    String s;
    String rgbDelimiter;
    
    public void init() {
        this.s = this.getParameter("bgColor");
        if (this.s != null) {
            this.st = new StringTokenizer(this.s, this.rgbDelimiter);
        }
        if (this.s == null) {
            this.bgColor = Color.lightGray;
        }
        else if (this.s.equalsIgnoreCase("red")) {
            this.bgColor = Color.red;
        }
        else if (this.s.equalsIgnoreCase("blue")) {
            this.bgColor = Color.blue;
        }
        else if (this.s.equalsIgnoreCase("green")) {
            this.bgColor = Color.green;
        }
        else if (this.s.equalsIgnoreCase("yellow")) {
            this.bgColor = Color.yellow;
        }
        else if (this.s.equalsIgnoreCase("white")) {
            this.bgColor = Color.white;
        }
        else if (this.s.equalsIgnoreCase("orange")) {
            this.bgColor = Color.orange;
        }
        else if (this.s.equalsIgnoreCase("cyan")) {
            this.bgColor = Color.cyan;
        }
        else if (this.s.equalsIgnoreCase("magenta")) {
            this.bgColor = Color.magenta;
        }
        else if (this.s.equalsIgnoreCase("black")) {
            this.bgColor = Color.black;
        }
        else if (this.st.countTokens() == 3) {
            this.bgColor = new Color(new Integer(this.st.nextToken()), new Integer(this.st.nextToken()), new Integer(this.st.nextToken()));
        }
        else {
            this.bgColor = Color.lightGray;
        }
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            this.targetFrame = parameter;
        }
        else {
            this.targetFrame = "_parent";
        }
        this.number = Integer.parseInt(this.getParameter("number"));
        final Choice choice = new Choice();
        for (int i = 0; i <= this.number; ++i) {
            this.at = this.getParameter("link" + i);
            this.str = new StringTokenizer(this.at, "\\");
            this.names.addElement(new String(this.str.nextToken()));
            this.urls.addElement(new String(this.str.nextToken()));
            choice.addItem((String)this.names.elementAt(i));
        }
        this.add(choice);
        this.setBackground(this.bgColor);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final String s = (String)o;
            for (int i = 0; i <= this.number; ++i) {
                if (s.equals(this.names.elementAt(i))) {
                    try {
                        this.theURL = new URL((String)this.urls.elementAt(i));
                    }
                    catch (MalformedURLException ex) {
                        System.out.println("Bad URL");
                    }
                    this.getAppletContext().showDocument(this.theURL, this.targetFrame);
                }
            }
        }
        return true;
    }
    
    public link() {
        this.urls = new Vector();
        this.names = new Vector();
        this.rgbDelimiter = ":,.";
    }
}
