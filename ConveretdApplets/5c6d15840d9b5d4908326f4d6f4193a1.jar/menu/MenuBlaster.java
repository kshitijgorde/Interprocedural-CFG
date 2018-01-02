// 
// Decompiled by Procyon v0.5.30
// 

package menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Font;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.applet.AudioClip;
import java.applet.Applet;

public class MenuBlaster extends Applet
{
    public menu.a api;
    public AudioClip pop;
    public boolean isVertical;
    public boolean rollOver;
    public Color rollBG;
    public Color rollFG;
    private static JSObject a;
    
    public void start() {
        this.requestFocus();
    }
    
    private int a(final String s) {
        if (s.equalsIgnoreCase("bold")) {
            return 1;
        }
        if (s.equalsIgnoreCase("italic")) {
            return 2;
        }
        if (s.equalsIgnoreCase("boldItalic") || s.equalsIgnoreCase("italicBold")) {
            return 3;
        }
        return 0;
    }
    
    public String getAppletInfo() {
        return "Author gertsho@yahoo.com";
    }
    
    public void init() {
        try {
            MenuBlaster.a = JSObject.getWindow((Applet)this);
        }
        catch (JSException ex3) {}
        this.setFont(new Font("Helvetica", 0, 12));
        String parameter = this.getParameter("charset");
        if (parameter == null || parameter.equals("")) {
            parameter = "8859_1";
        }
        if (this.getSize().width < this.getSize().height * 1.2) {
            this.isVertical = true;
        }
        String parameter2 = this.getParameter("configFile");
        if (parameter2 == null) {
            parameter2 = "config.txt";
        }
        try {
            this.api = new menu.a(parameter);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.getCodeBase(), parameter2).openStream());
            this.api.load(bufferedInputStream);
            bufferedInputStream.close();
        }
        catch (FileNotFoundException ex4) {
            this.alert("Invalid file name! \\n\\nCannot find - " + parameter2);
        }
        catch (SecurityException ex5) {
            this.alert("Invalid path! \\n\\nThe following path do not exist - " + parameter2);
        }
        catch (UnsupportedEncodingException ex6) {
            this.alert("Unsupported CharSet Encoding! \\n\\nThe following -" + parameter + "- charset is unsupported or incorrect.\\n" + "Make sure that the charset is spelling correct!");
        }
        catch (Exception ex) {
            this.alert("Configuration file Error! \\n\\n" + ex);
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.api.getProperty("bgColor", "#0055cc"), "*");
            this.setBackground(this.if(stringTokenizer.nextToken().trim()));
            this.rollBG = (stringTokenizer.hasMoreTokens() ? this.if(stringTokenizer.nextToken().trim()) : null);
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.api.getProperty("fgColor", "white"), "*");
            this.setForeground(this.if(stringTokenizer2.nextToken().trim()));
            this.rollFG = (stringTokenizer2.hasMoreTokens() ? this.if(stringTokenizer2.nextToken().trim()) : null);
            this.rollOver = !this.api.getProperty("rollOver", "on").trim().equalsIgnoreCase("off");
            this.setFont(new Font(this.api.getProperty("fontName", "Dialog"), this.a(this.api.getProperty("fontStyle", "PLAIN")), Integer.parseInt(this.api.getProperty("fontSize", "12"))));
            this.setLayout(new BorderLayout(0, 0));
            this.add("Center", new b(this));
            this.pop = this.getAudioClip(this.getCodeBase(), "menu/pop.au");
            this.addKeyListener(new a());
        }
        catch (Exception ex2) {
            this.alert("Configuration file problem! \\n\\n" + ex2);
        }
    }
    
    private Color if(final String s) {
        if (s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return this.do(s);
    }
    
    private Color do(final String s) {
        if (s.equals("black")) {
            return Color.black;
        }
        if (s.equals("orange")) {
            return Color.orange;
        }
        if (s.equals("red")) {
            return Color.red;
        }
        if (s.equals("green")) {
            return Color.green;
        }
        if (s.equals("blue")) {
            return Color.blue;
        }
        if (s.equals("cyan")) {
            return Color.cyan;
        }
        if (s.equals("magenta")) {
            return Color.magenta;
        }
        if (s.equals("gray")) {
            return Color.gray;
        }
        return Color.white;
    }
    
    public void alert(final String s) {
        if (MenuBlaster.a != null) {
            try {
                MenuBlaster.a.eval("alert('" + s + "');");
            }
            catch (Exception ex) {}
        }
        else {
            this.showStatus(s);
            System.out.println(s);
        }
    }
    
    class a extends KeyAdapter
    {
        a() {
            MenuBlaster.this.getClass();
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getModifiers() == 2) {
                if (keyEvent.getKeyCode() == 65) {
                    MenuBlaster.this.alert(MenuBlaster.this.getAppletInfo());
                }
                else if (keyEvent.getKeyCode() == 86) {
                    MenuBlaster.this.alert("BuddySoft MenuBlaster - Version: 3.2.0");
                }
            }
        }
    }
}
