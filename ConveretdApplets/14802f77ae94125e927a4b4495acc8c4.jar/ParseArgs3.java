import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ParseArgs3 extends Banner3
{
    public ParseArgs3(final Banner3 banner3) {
        final String parameter = banner3.getParameter("MessageNo");
        try {
            Banner3.totalmessages = Integer.parseInt(parameter);
        }
        catch (Exception ex) {
            Banner3.totalmessages = 0;
        }
        Banner3.catbgcolor = new Color[Banner3.totalmessages];
        Banner3.cattextcolor = new Color[Banner3.totalmessages];
        Banner3.msgtext = new String[Banner3.totalmessages];
        Banner3.cattext = new String[Banner3.totalmessages];
        Banner3.textURL = new String[Banner3.totalmessages];
        super.j = 0;
        while (super.j < Banner3.totalmessages) {
            Banner3.msgtext[super.j] = banner3.getParameter("msgtext" + super.j);
            Banner3.cattext[super.j] = banner3.getParameter("cattext" + super.j);
            Banner3.textURL[super.j] = banner3.getParameter("TextURL" + super.j);
            ++super.j;
        }
        Banner3.fontname = banner3.getParameter("font");
        if (Banner3.fontname == null) {
            Banner3.fontname = new String("Arial");
        }
        final String parameter2 = banner3.getParameter("fontsize");
        try {
            Banner3.fontsize = Integer.parseInt(parameter2);
        }
        catch (Exception ex2) {
            Banner3.fontsize = 36;
        }
        final String parameter3 = banner3.getParameter("catwidth");
        try {
            Banner3.catwidth = Integer.parseInt(parameter3);
        }
        catch (Exception ex3) {
            Banner3.catwidth = 50;
        }
        final String parameter4 = banner3.getParameter("fontstyle");
        try {
            if (parameter4.equalsIgnoreCase("Plain")) {
                Banner3.fontstyle = 0;
            }
            else if (parameter4.equalsIgnoreCase("Italic")) {
                Banner3.fontstyle = 2;
            }
            else if (parameter4.equalsIgnoreCase("BoldItalic") || parameter4.equalsIgnoreCase("ItalicBold")) {
                Banner3.fontstyle = 3;
            }
            else {
                Banner3.fontstyle = 1;
            }
        }
        catch (Exception ex4) {
            Banner3.fontstyle = 1;
        }
        final String parameter5 = banner3.getParameter("delay");
        try {
            Banner3.delay = Integer.parseInt(parameter5);
        }
        catch (Exception ex5) {
            Banner3.delay = 50;
        }
        final String parameter6 = banner3.getParameter("pauseDelay");
        try {
            Banner3.pauseDelay = Integer.parseInt(parameter6);
        }
        catch (Exception ex6) {
            Banner3.pauseDelay = 1000;
        }
        final String parameter7 = banner3.getParameter("dist");
        try {
            Banner3.ydelta = (Banner3.xdelta = Integer.parseInt(parameter7));
        }
        catch (Exception ex7) {
            Banner3.ydelta = (Banner3.xdelta = 5);
        }
        final String parameter8 = banner3.getParameter("speed");
        try {
            super.speed = Integer.parseInt(parameter8);
        }
        catch (Exception ex8) {
            super.speed = 10;
        }
        super.j = 0;
        while (super.j < Banner3.totalmessages) {
            Banner3.catbgcolor[super.j] = this.parseColor(banner3.getParameter("catbgcolor" + super.j));
            if (Banner3.catbgcolor[super.j] == null) {
                Banner3.catbgcolor[super.j] = Color.black;
            }
            Banner3.cattextcolor[super.j] = this.parseColor(banner3.getParameter("cattextcolor" + super.j));
            if (Banner3.cattextcolor[super.j] == null) {
                Banner3.cattextcolor[super.j] = Color.black;
            }
            ++super.j;
        }
        Banner3.msgbgcolor = this.parseColor(banner3.getParameter("msgbgcolor"));
        if (Banner3.msgbgcolor == null) {
            Banner3.msgbgcolor = Color.yellow;
        }
        Banner3.msgtextcolor = this.parseColor(banner3.getParameter("msgtextcolor"));
        if (Banner3.msgtextcolor == null) {
            Banner3.msgtextcolor = Color.white;
        }
        Banner3.linkColor = this.parseColor(banner3.getParameter("linkcolor"));
        if (Banner3.linkColor == null) {
            Banner3.linkColor = Color.red;
        }
        Banner3.valign = banner3.getParameter("valign");
        Banner3.align = banner3.getParameter("align");
        Banner3.direction = banner3.getParameter("direction");
    }
    
    private Color parseColor(final String s) {
        try {
            if (s.equalsIgnoreCase("Yellow")) {
                return Color.yellow;
            }
            if (s.equalsIgnoreCase("Blue")) {
                return Color.blue;
            }
            if (s.equalsIgnoreCase("Black")) {
                return Color.black;
            }
            if (s.equalsIgnoreCase("Red")) {
                return Color.red;
            }
            if (s.equalsIgnoreCase("White")) {
                return Color.white;
            }
            if (s.equalsIgnoreCase("Green")) {
                return Color.green;
            }
            if (s.equalsIgnoreCase("Pink")) {
                return Color.pink;
            }
            if (s.equalsIgnoreCase("Orange")) {
                return Color.orange;
            }
            final int index = s.indexOf(44);
            final int int1 = Integer.parseInt(s.substring(0, index));
            final int index2 = s.indexOf(44, index + 1);
            return new Color(int1, Integer.parseInt(s.substring(index + 1, index2)), Integer.parseInt(s.substring(index2 + 1)));
        }
        catch (Exception ex) {
            return null;
        }
    }
}
