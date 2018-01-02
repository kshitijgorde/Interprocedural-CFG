// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft;

import buddysoft.a.c;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import buddysoft.a.d;
import java.awt.Color;
import java.applet.Applet;

public class QuickMail extends Applet
{
    String try;
    Color for;
    Color case;
    Color else;
    Color if;
    String goto;
    String null;
    String long;
    String byte;
    boolean new;
    boolean do;
    boolean char;
    private b int;
    private d a;
    
    public QuickMail() {
        this.try = "BuddySoft Quick Mail 1.7";
    }
    
    public void destroy() {
        if (this.int != null) {
            if (this.int.isShowing()) {
                this.int.hide();
            }
            this.int.dispose();
        }
    }
    
    public String getAppletInfo() {
        return this.try + " Author: info@buddysoft.net";
    }
    
    public void init() {
        this.setBackground(Color.white);
        String parameter = this.getParameter("colors");
        if (parameter == null) {
            parameter = "#0077cc";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        this.else = this.a(stringTokenizer.nextToken().trim());
        this.if = this.a(stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "white");
        this.for = this.a(stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "orange");
        this.case = this.a(stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "black");
        final String parameter2 = this.getParameter("regID");
        this.goto = ((parameter2 != null) ? parameter2 : "orange");
        final String parameter3 = this.getParameter("TO");
        this.long = ((parameter3 != null) ? parameter3 : "missing, recipient, email");
        final String parameter4 = this.getParameter("flag");
        this.byte = ((parameter4 != null) ? parameter4 : "");
        this.new = (this.getParameter("CC") == null);
        this.setLayout(new BorderLayout());
        if (this.size().width < 200) {
            final String parameter5 = this.getParameter("icon");
            if (parameter5 != null) {
                this.a = new d(this.getImage(this.getCodeBase(), parameter5));
            }
            else {
                final String parameter6 = this.getParameter("label");
                this.a = new d((parameter6 != null) ? parameter6 : "Quick Mail");
            }
            this.a.a("Launch BS Quick Mailer");
            this.a.setBackground(this.for);
            this.a.setForeground(this.case);
            this.add("Center", this.a);
            this.int = new b(this);
        }
        else {
            this.add("Center", new a(this, 6));
        }
        this.char = (this.getParameter("sendUrl") != null);
        this.do = (this.getParameter("debug") != null);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target != this.a) {
                    break;
                }
                if (!this.int.isShowing()) {
                    this.int.show();
                    break;
                }
                this.int.hide();
                break;
            }
            case 504: {
                if (event.target instanceof c) {
                    this.showStatus(((c)event.target).a());
                    break;
                }
                break;
            }
            case 505: {
                this.showStatus("Done");
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private Color a(final String s) {
        if (s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1), 16));
        }
        return this.if(s);
    }
    
    private Color if(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals("silver")) {
            return Color.gray;
        }
        if (lowerCase.equals("gray")) {
            return Color.darkGray;
        }
        if (lowerCase.equals("orange")) {
            return Color.orange;
        }
        if (lowerCase.equals("red")) {
            return Color.red;
        }
        if (lowerCase.equals("green")) {
            return Color.green;
        }
        if (lowerCase.equals("blue")) {
            return Color.blue;
        }
        if (lowerCase.equals("cyan")) {
            return Color.cyan;
        }
        if (lowerCase.equals("magenta")) {
            return Color.magenta;
        }
        if (lowerCase.equals("yellow")) {
            return Color.yellow;
        }
        if (lowerCase.equals("black")) {
            return Color.black;
        }
        return Color.white;
    }
}
