import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Event;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PetQuotes extends Applet
{
    String fontType;
    Color font_color;
    Color backGroundColor;
    Font f;
    int font_size;
    String quote;
    String author;
    String[] line;
    int l;
    String bakQuote;
    String bakAuthor;
    String strFile;
    String urlString;
    
    public PetQuotes() {
        this.quote = "Like a graceful vase, a cat, even when motionless, seems to flow.";
        this.author = "George F. Will";
        this.line = new String[30];
        this.l = 0;
        this.bakQuote = "Whoever said you can\u2019t buy happiness forgot about little puppies.";
        this.bakAuthor = "Gene Hill";
        this.strFile = "petQuotes.txt";
        this.urlString = "http://www.simplypets.com/redirects/petQuotes.html";
    }
    
    public void bgColor() {
        final String parameter = this.getParameter("bgcolor");
        if (parameter == null || parameter.equalsIgnoreCase("white")) {
            this.backGroundColor = Color.white;
        }
        else if (parameter.equalsIgnoreCase("black")) {
            this.backGroundColor = Color.black;
        }
        else if (parameter.equalsIgnoreCase("lightGray")) {
            this.backGroundColor = Color.lightGray;
        }
        else if (parameter.equalsIgnoreCase("gray")) {
            this.backGroundColor = Color.gray;
        }
        else if (parameter.equalsIgnoreCase("darkGray")) {
            this.backGroundColor = Color.darkGray;
        }
        else if (parameter.equalsIgnoreCase("red")) {
            this.backGroundColor = Color.red;
        }
        else if (parameter.equalsIgnoreCase("pink")) {
            this.backGroundColor = Color.pink;
        }
        else if (parameter.equalsIgnoreCase("orange")) {
            this.backGroundColor = Color.orange;
        }
        else if (parameter.equalsIgnoreCase("yellow")) {
            this.backGroundColor = Color.yellow;
        }
        else if (parameter.equalsIgnoreCase("green")) {
            this.backGroundColor = Color.green;
        }
        else if (parameter.equalsIgnoreCase("magenta")) {
            this.backGroundColor = Color.magenta;
        }
        else if (parameter.equalsIgnoreCase("cyan")) {
            this.backGroundColor = Color.cyan;
        }
        else if (parameter.equalsIgnoreCase("blue")) {
            this.backGroundColor = Color.blue;
        }
        else {
            this.backGroundColor = Color.white;
        }
    }
    
    public void color() {
        final String parameter = this.getParameter("color");
        if (parameter == null || parameter.equalsIgnoreCase("black")) {
            this.font_color = Color.black;
        }
        else if (parameter.equalsIgnoreCase("white")) {
            this.font_color = Color.white;
        }
        else if (parameter.equalsIgnoreCase("lightGray")) {
            this.font_color = Color.lightGray;
        }
        else if (parameter.equalsIgnoreCase("gray")) {
            this.font_color = Color.gray;
        }
        else if (parameter.equalsIgnoreCase("darkGray")) {
            this.font_color = Color.darkGray;
        }
        else if (parameter.equalsIgnoreCase("red")) {
            this.font_color = Color.red;
        }
        else if (parameter.equalsIgnoreCase("pink")) {
            this.font_color = Color.pink;
        }
        else if (parameter.equalsIgnoreCase("orange")) {
            this.font_color = Color.orange;
        }
        else if (parameter.equalsIgnoreCase("yellow")) {
            this.font_color = Color.yellow;
        }
        else if (parameter.equalsIgnoreCase("green")) {
            this.font_color = Color.green;
        }
        else if (parameter.equalsIgnoreCase("magenta")) {
            this.font_color = Color.magenta;
        }
        else if (parameter.equalsIgnoreCase("cyan")) {
            this.font_color = Color.cyan;
        }
        else if (parameter.equalsIgnoreCase("blue")) {
            this.font_color = Color.blue;
        }
        else {
            this.font_color = Color.black;
        }
    }
    
    public void font() {
        if (this.getParameter("font_size") != null) {
            this.font_size = Integer.parseInt(this.getParameter("font_size"));
        }
        else {
            this.font_size = 18;
        }
        final String parameter = this.getParameter("font");
        if (parameter == null) {
            this.fontType = "TimesRoman";
        }
        else {
            final String[] fontList = this.getToolkit().getFontList();
            for (int i = 0; i < fontList.length; ++i) {
                if (parameter.equalsIgnoreCase(fontList[i])) {
                    this.fontType = fontList[i];
                    break;
                }
            }
            this.fontType = "TimesRoman";
        }
    }
    
    public String getAppletInfo() {
        return "Pet Quote Applet by SimplyPets http://www.simplypets.com/";
    }
    
    public void init() {
        try {
            this.readQuotes();
        }
        catch (IOException ex) {
            System.exit(1);
        }
        catch (InterruptedException ex2) {
            System.exit(1);
        }
        this.color();
        this.bgColor();
        this.font();
    }
    
    public static void main(final String[] array) throws IOException, InterruptedException {
        new PetQuotes().init();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.getAppletContext() != null) {
                this.getAppletContext().showDocument(new URL(this.urlString));
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public void paint(final Graphics graphics) {
        Font font = new Font(this.fontType, 1, this.font_size);
        final Dimension size = this.getSize();
        final Insets insets = this.getInsets();
        final int n = size.width - insets.right - insets.left;
        final int n2 = size.height - insets.top - insets.bottom;
        graphics.setColor(this.backGroundColor);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setFont(font);
        graphics.setColor(this.font_color);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.quote == "" || this.quote == null) {
            this.quote = this.bakQuote;
            this.author = this.bakAuthor;
        }
        this.tokenize(graphics, n, n2);
        while (this.l * fontMetrics.getHeight() > n2) {
            if (this.font_size > 8) {
                --this.font_size;
            }
            else {
                try {
                    this.readQuotes();
                }
                catch (Exception ex) {}
                this.font();
                this.update(graphics);
            }
            font = new Font(this.fontType, 1, this.font_size);
            graphics.setFont(font);
            fontMetrics = graphics.getFontMetrics();
            this.tokenize(graphics, n, n2);
        }
        int n3 = (n2 - this.l * fontMetrics.getHeight()) / 2 + fontMetrics.getHeight() / 2;
        for (int i = 0; i <= this.l; ++i) {
            graphics.drawString(this.line[i], (n - fontMetrics.stringWidth(this.line[i])) / 2, n3);
            n3 += font.getSize();
        }
    }
    
    private void readQuotes() throws IOException, InterruptedException, NoSuchElementException {
        try {
            final URL url = new URL(String.valueOf(String.valueOf(this.getCodeBase())) + this.strFile);
            int n = 0;
            String s = "";
            final InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            if (bufferedReader.ready()) {
                s = bufferedReader.readLine();
            }
            else {
                for (boolean b = false; !b; b = true) {
                    Thread.sleep(1000L);
                    int n2 = 0;
                    if (bufferedReader.ready()) {
                        s = bufferedReader.readLine();
                        b = true;
                    }
                    else {
                        ++n2;
                    }
                    if (n2 == 100) {}
                }
            }
            final int n3 = (int)(Math.random() * 1.0E7 % Integer.parseInt(s.trim())) + 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (++n == n3) {
                    bufferedReader.close();
                    this.quote = line.substring(0, line.indexOf("||"));
                    this.author = line.substring(line.indexOf("||") + 2);
                    bufferedReader.close();
                    inputStreamReader.close();
                    return;
                }
            }
            bufferedReader.close();
            inputStreamReader.close();
        }
        catch (IOException ex) {
            System.exit(1);
        }
    }
    
    public void tokenize(final Graphics graphics, final int n, final int n2) {
        String s = "";
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(" ");
        this.l = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.quote);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int n3 = fontMetrics.stringWidth(nextToken) + stringWidth;
            final int stringWidth2 = fontMetrics.stringWidth(s);
            if (stringTokenizer.hasMoreTokens()) {
                if (stringWidth2 + n3 > n - 10) {
                    this.line[this.l] = s;
                    ++this.l;
                    s = String.valueOf(nextToken) + " ";
                }
                else {
                    s = String.valueOf(s) + " " + nextToken;
                }
            }
            else if (stringWidth2 + n3 > n - 10) {
                this.line[this.l] = s;
                ++this.l;
                this.line[this.l] = String.valueOf(nextToken);
            }
            else {
                s = String.valueOf(s) + " " + nextToken;
                this.line[this.l] = s;
            }
        }
        ++this.l;
        String s2 = "--";
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.author);
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            final int n4 = fontMetrics.stringWidth(nextToken2) + stringWidth;
            final int stringWidth3 = fontMetrics.stringWidth(s2);
            if (stringTokenizer2.hasMoreTokens()) {
                if (stringWidth3 + n4 > n - 10) {
                    this.line[this.l] = s2;
                    ++this.l;
                    s2 = String.valueOf(nextToken2) + " ";
                }
                else {
                    s2 = String.valueOf(s2) + nextToken2 + " ";
                }
            }
            else if (stringWidth3 + n4 > n - 10) {
                this.line[this.l] = s2;
                ++this.l;
                this.line[this.l] = String.valueOf(nextToken2);
            }
            else {
                s2 = String.valueOf(s2) + " " + nextToken2;
                this.line[this.l] = s2;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
