import java.awt.Dimension;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ezbirthday extends Applet
{
    private boolean isStandalone;
    Color cFgcolor;
    Color cBgcolor;
    int minWidth;
    int minHeight;
    String fileNaam;
    String stringOne;
    String stringNoMatch;
    String stringTwo;
    String stringDelimeter;
    String font;
    String alignment;
    int daysInFuture;
    int daysInPast;
    private GregorianCalendar now;
    private GregorianCalendar rollDate;
    private DateFormat formatter;
    private int dag;
    private int maand;
    private String bold;
    int fontMaat;
    boolean displayDates;
    boolean multiLine;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public ezbirthday() {
        this.isStandalone = false;
        this.minWidth = 1;
        this.minHeight = 1;
        this.fileNaam = "";
        this.stringOne = "";
        this.stringNoMatch = "";
        this.stringTwo = "";
        this.stringDelimeter = "";
        this.font = "";
        this.alignment = "";
        this.daysInFuture = 0;
        this.daysInPast = 0;
        this.bold = "false";
    }
    
    public void init() {
        try {
            this.bold = this.getParameter("bold", "false");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.fontMaat = Integer.parseInt(this.getParameter("size", "10"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.font = this.getParameter("font", "SansSerif");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.fileNaam = this.getParameter("filename", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.cFgcolor = Color.decode(this.getParameter("fgcolor", "#000000"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.cBgcolor = Color.decode(this.getParameter("bgcolor", "#FFFFFF"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.alignment = this.getParameter("alignment", "center");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.stringOne = this.getParameter("stringone", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.stringNoMatch = this.getParameter("stringnomatch", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.stringTwo = this.getParameter("stringtwo", "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.stringDelimeter = this.getParameter("stringdelimiter", " -- ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.daysInFuture = Integer.parseInt(this.getParameter("daysinfuture", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.daysInPast = Integer.parseInt(this.getParameter("daysinpast", "0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.displayDates = new Boolean(this.getParameter("displaydates", "true"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.multiLine = new Boolean(this.getParameter("multiline", "true"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.now = new GregorianCalendar();
        this.rollDate = new GregorianCalendar();
        if (this.multiLine) {
            this.stringDelimeter = "\t";
        }
    }
    
    private void printToApplet(final Graphics g, final String message) {
        g.setColor(this.cFgcolor);
        if (this.bold == "true") {
            g.setFont(new Font(this.font, 1, this.fontMaat));
        }
        else {
            g.setFont(new Font(this.font, 0, this.fontMaat));
        }
        final FontMetrics ft = g.getFontMetrics();
        int w = ft.stringWidth(message);
        final int h = ft.getAscent();
        final int hoogte = this.getSize().height;
        final int breedte = this.getSize().width;
        if (!this.multiLine) {
            if (this.alignment.equalsIgnoreCase("left")) {
                g.drawString(message, 2, hoogte / 2 + h / 2);
            }
            else if (this.alignment.equalsIgnoreCase("right")) {
                g.drawString(message, breedte - (w + 2), hoogte / 2 + h / 2);
            }
            else {
                g.drawString(message, breedte / 2 - w / 2, hoogte / 2 + h / 2);
            }
        }
        else {
            final StringTokenizer tok = new StringTokenizer(message, "\t");
            int i = 1;
            while (tok.hasMoreTokens()) {
                final String tmpNaam = tok.nextToken();
                if (this.alignment.equalsIgnoreCase("left")) {
                    g.drawString(tmpNaam, h / 2, (int)(i * 1.5 * h));
                }
                else if (this.alignment.equalsIgnoreCase("right")) {
                    w = ft.stringWidth(tmpNaam);
                    final int tmpI = breedte - w - h / 2;
                    g.drawString(tmpNaam, tmpI, (int)(i * 1.5 * h));
                }
                else {
                    w = ft.stringWidth(tmpNaam);
                    final int tmpI = (breedte - w) / 2;
                    g.drawString(tmpNaam, tmpI, (int)(i * 1.5 * h));
                }
                ++i;
            }
        }
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.cBgcolor);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (!this.isCorrectSize(this.getSize())) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);
            g.setColor(Color.black);
            g.drawString(String.valueOf(String.valueOf(new StringBuffer("minimum size WxH ").append(String.valueOf(this.minWidth)).append("x").append(String.valueOf(this.minHeight)).append("not met"))), 20, this.getSize().height - 5);
            return;
        }
        this.printToApplet(g, this.buildString());
    }
    
    private String buildString() {
        final BufferedReader in = this.getFile();
        String strOut = "";
        String line = "";
        String tmpStr = "";
        try {
            do {
                line = in.readLine();
                if (line != null) {
                    tmpStr = this.isVandaagJarig(line);
                    if (tmpStr == "") {
                        continue;
                    }
                    if (strOut == "") {
                        if (this.multiLine) {
                            strOut = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.stringOne))).append(this.stringDelimeter).append(this.getNaam(line))));
                        }
                        else {
                            strOut = String.valueOf(String.valueOf(this.stringOne)).concat(String.valueOf(String.valueOf(this.getNaam(line))));
                        }
                        if (!this.displayDates) {
                            continue;
                        }
                        strOut = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(strOut))).append(" (").append(tmpStr).append(") ")));
                    }
                    else {
                        strOut = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(strOut))).append(this.stringDelimeter).append(this.getNaam(line))));
                        if (!this.displayDates) {
                            continue;
                        }
                        strOut = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(strOut))).append(" (").append(tmpStr).append(") ")));
                    }
                }
            } while (line != null);
            if (strOut == "") {
                strOut = this.stringNoMatch;
            }
            else if (this.multiLine) {
                strOut = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(strOut))).append(this.stringDelimeter).append(this.stringTwo)));
            }
            else {
                strOut = String.valueOf(String.valueOf(strOut)).concat(String.valueOf(String.valueOf(this.stringTwo)));
            }
            return strOut;
        }
        catch (Exception e) {
            return "File- or Stringhandle error! Empty lines in textfile?";
        }
    }
    
    private BufferedReader getFile() {
        try {
            final URL url = new URL(this.getDocumentBase(), this.fileNaam);
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            return in;
        }
        catch (Exception e) {
            this.getGraphics().setColor(this.cFgcolor);
            this.getGraphics().drawString("Fileread error, can not open or read from file", 1, this.getSize().height);
            return null;
        }
    }
    
    public String isVandaagJarig(final String input) {
        final String tmp = "";
        String dag = "";
        String maand = "";
        int i = 0;
        do {
            dag = String.valueOf(String.valueOf(dag)).concat(String.valueOf(String.valueOf(input.substring(i, i + 1))));
            ++i;
        } while (input.substring(i, i + 1).compareTo("-") != 0);
        ++i;
        do {
            maand = String.valueOf(String.valueOf(maand)).concat(String.valueOf(String.valueOf(input.substring(i, i + 1))));
            ++i;
        } while (input.substring(i, i + 1).compareTo("\t") != 0);
        final int iDag = Integer.parseInt(dag);
        final int iMaand = Integer.parseInt(maand);
        this.now = new GregorianCalendar();
        int iDagVandaag = this.now.get(5);
        int iMaandVandaag = this.now.get(2) + 1;
        if (iDag == iDagVandaag && iMaand == iMaandVandaag) {
            this.formatter = DateFormat.getDateInstance(3);
            return this.formatter.format(this.now.getTime());
        }
        this.rollDate = new GregorianCalendar();
        for (i = 1; i <= this.daysInPast; ++i) {
            if (this.rollDate.get(5) > 1) {
                this.rollDate.roll(5, false);
                iDagVandaag = this.rollDate.get(5);
                iMaandVandaag = this.rollDate.get(2) + 1;
                if (iDag == iDagVandaag && iMaand == iMaandVandaag) {
                    this.formatter = DateFormat.getDateInstance(3);
                    return this.formatter.format(this.rollDate.getTime());
                }
            }
            else {
                this.rollDate.roll(2, false);
                this.rollDate.set(5, this.getCurrentMaximum(this.rollDate.get(2)));
                iDagVandaag = this.rollDate.get(5);
                iMaandVandaag = this.rollDate.get(2) + 1;
                if (iDag == iDagVandaag && iMaand == iMaandVandaag) {
                    this.formatter = DateFormat.getDateInstance(3);
                    return this.formatter.format(this.rollDate.getTime());
                }
            }
        }
        this.rollDate = new GregorianCalendar();
        for (i = 1; i <= this.daysInFuture; ++i) {
            if (this.rollDate.get(5) < this.getCurrentMaximum(this.rollDate.get(2))) {
                this.rollDate.roll(5, true);
                iDagVandaag = this.rollDate.get(5);
                iMaandVandaag = this.rollDate.get(2) + 1;
                if (iDag == iDagVandaag && iMaand == iMaandVandaag) {
                    this.formatter = DateFormat.getDateInstance(3);
                    return this.formatter.format(this.rollDate.getTime());
                }
            }
            else {
                this.rollDate.roll(2, true);
                this.rollDate.set(5, 1);
                iDagVandaag = this.rollDate.get(5);
                iMaandVandaag = this.rollDate.get(2) + 1;
                if (iDag == iDagVandaag && iMaand == iMaandVandaag) {
                    this.formatter = DateFormat.getDateInstance(3);
                    return this.formatter.format(this.rollDate.getTime());
                }
            }
        }
        return "";
    }
    
    private String getNaam(final String heleString) {
        final StringTokenizer del = new StringTokenizer(heleString, "\t");
        String getNaam = del.nextToken();
        getNaam = del.nextToken();
        return getNaam;
    }
    
    private boolean isCorrectSize(final Dimension size) {
        return size.height >= this.minHeight && size.width >= this.minWidth;
    }
    
    public String getAppletInfo() {
        return "Ezit BirthdayAnnouncer 2.0  - Joris Bots 2004 ";
    }
    
    public String[][] getParameterInfo() {
        final String[][] pinfo = { { "width", "String", "" }, { "height", "String", "" }, { "fgcolor", "String", "" }, { "bgcolor", "String", "" } };
        return pinfo;
    }
    
    private int getCurrentMaximum(final int gregMonth) {
        (this.now = new GregorianCalendar()).set(2, gregMonth);
        this.now.set(5, 15);
        int i;
        int j;
        for (i = this.now.get(5), j = 0; i > j; i = this.now.get(5)) {
            j = i;
            this.now.roll(5, true);
        }
        return j;
    }
}
