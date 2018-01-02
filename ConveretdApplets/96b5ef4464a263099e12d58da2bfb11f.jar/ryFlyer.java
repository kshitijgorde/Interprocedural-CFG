import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import netscape.javascript.JSObject;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryFlyer extends Applet implements ActionListener
{
    protected Color \u012c;
    private Color \u012b;
    private Color \u012a;
    private Color \u0129;
    private String \u0128;
    private Font \u0127;
    private int \u0126;
    private int \u0125;
    private int \u0124;
    private int \u0123;
    private Panel \u0122;
    private boolean \u0121;
    private int \u0120;
    private Image[] \u011f;
    private Image \u011e;
    protected boolean \u011d;
    private boolean \u011c;
    private boolean \u011b;
    private boolean \u011a;
    private boolean \u0119;
    private Color \u0118;
    private Color \u0117;
    private String \u0116;
    private String \u0115;
    
    private void \u013b() {
        final String s = "Flyer © 2000 Cool Focus [www.coolfocus.com]";
        final String s2 = "Flyer (c) 2000 Cool Focus [www.coolfocus.com]";
        if (this.getParameter("Copyright") == null || (!this.getParameter("Copyright").equals(s) && !this.getParameter("Copyright").equals(s2))) {
            throw new SecurityException(" Copyright parameter missing or incorrect ");
        }
    }
    
    private void \u013a() {
        final String lowerCase = this.getDocumentBase().toString().toLowerCase();
        boolean b = false;
        if (lowerCase.startsWith("file:") && lowerCase.indexOf("flyer/docs/") >= 0) {
            this.\u011c = true;
            return;
        }
        final String parameter = this.getParameter("Base");
        if (parameter == null) {
            this.\u011c = false;
            return;
        }
        String s = "";
        if (parameter.indexOf("|") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "|");
            final String[] array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                if (!nextToken.startsWith("http")) {
                    array[i] = "http://" + nextToken;
                }
                else {
                    array[i] = nextToken;
                }
                s = String.valueOf(s) + array[i];
                if (lowerCase.indexOf(nextToken) >= 0 || lowerCase.startsWith("file:")) {
                    b = true;
                }
            }
        }
        else {
            final String s2 = parameter;
            if (!parameter.startsWith("http")) {
                s = "http://" + parameter;
            }
            else {
                s = parameter;
            }
            if (lowerCase.indexOf(s2) >= 0 || lowerCase.startsWith("file:")) {
                b = true;
            }
        }
        if (!b) {
            this.\u011c = false;
            return;
        }
        try {
            this.\u011c = this.\u0139(s);
        }
        catch (NoSuchElementException ex) {
            this.\u011c = false;
        }
    }
    
    private boolean \u0139(final String s) throws NoSuchElementException {
        final String parameter = this.getParameter("Key");
        if (parameter == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "-");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final String nextToken3 = stringTokenizer.nextToken();
        final String nextToken4 = stringTokenizer.nextToken();
        return nextToken.equals(Integer.toString((s.length() * 8239 + 54 - 23703) * 21)) && nextToken2.equals(Integer.toString(this.\u0138(s, 'e') * 144)) && nextToken3.equals(Integer.toString(this.\u0138(s, 'w') * 523)) && nextToken4.equals(Integer.toString(this.\u0138(s, 's') * 622));
    }
    
    private int \u0138(final String s, final char c) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++n;
            }
        }
        return n;
    }
    
    private int \u0137(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
    
    private String \u0136(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public String getAppletInfo() {
        if (!this.\u011c) {
            return "\n\nUNREGISTERED Flyer v1.7 by Rob Young\nCopyright © 2000-2001 Cool Focus [www.coolfocus.com]\n\n";
        }
        return "\n\nFlyer v1.7 by Rob Young\nCopyright © 2000-2001 Cool Focus [www.coolfocus.com]\n\n";
    }
    
    public void init() {
        this.\u013a();
        this.\u013b();
        System.out.println(this.getAppletInfo());
        this.\u012c = new Color(this.\u0137("BgColor", Color.white));
        this.\u012b = new Color(this.\u0137("ButtonColor", new Color(192, 192, 192)));
        this.\u012a = new Color(this.\u0137("ButtonTextColor", Color.black));
        this.\u0129 = new Color(this.\u0137("ButtonTextFocusColor", Color.blue));
        this.setBackground(this.\u012c);
        this.\u0124 = this.size().width;
        this.\u0123 = this.size().height;
        if (!this.\u011c) {
            this.\u011b = true;
            this.repaint();
        }
        this.\u011a = false;
        if (!this.\u011c) {
            this.\u011b = true;
            return;
        }
        this.\u011b = false;
    }
    
    public void start() {
        this.\u0119 = (this.getParameter("ShowLoad") == null);
        this.\u0118 = new Color(this.\u0137("LoadBgColor", Color.black));
        this.\u0117 = new Color(this.\u0137("LoadTextColor", Color.green));
        this.\u0116 = this.\u0136("LoadText1", "Loading Flyer...");
        this.\u0115 = this.\u0136("LoadText2", "");
        if (this.\u0119) {
            this.repaint();
            return;
        }
        this.\u0135();
    }
    
    private void \u0135() {
        this.\u011a = true;
        this.\u0128 = this.\u0136("DefaultTarget", "_top");
        String parameter = this.getParameter("Font");
        if (parameter == null) {
            parameter = "Dialog,plain,12";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        int n = 0;
        if (nextToken2.equalsIgnoreCase("plain")) {
            n = 0;
        }
        if (nextToken2.equalsIgnoreCase("bold")) {
            n = 1;
        }
        if (nextToken2.equalsIgnoreCase("italic")) {
            n = 2;
        }
        if (nextToken2.equalsIgnoreCase("bolditalic")) {
            n = 3;
        }
        this.\u0127 = new Font(nextToken, n, Integer.parseInt(stringTokenizer.nextToken()));
        final String \u0137 = this.\u0136("Spacing", "0");
        try {
            this.\u0126 = Integer.parseInt(\u0137);
        }
        catch (Exception ex) {
            this.\u0126 = 0;
        }
        if (this.\u0126 < 0) {
            this.\u0126 = 0;
        }
        final String \u01372 = this.\u0136("FlatButtons", "no");
        if (\u01372.equalsIgnoreCase("yes")) {
            this.\u0120 = 1;
        }
        if (\u01372.equalsIgnoreCase("border")) {
            this.\u0120 = 2;
        }
        else {
            this.\u0125 = 0;
        }
        if (this.\u0136("Location", "top").equalsIgnoreCase("left")) {
            this.\u0125 = 1;
        }
        else {
            this.\u0125 = 0;
        }
        if (this.\u0136("DoubleBevel", "no").equalsIgnoreCase("yes")) {
            this.\u0121 = true;
        }
        else {
            this.\u0121 = false;
        }
        if (this.\u0136("UseCustomColors", "no").equalsIgnoreCase("yes")) {
            this.\u011d = true;
        }
        else {
            this.\u011d = false;
        }
        int n2 = 0;
        int n3 = 0;
        while (this.getParameter("Menu" + (n2 + 1)) != null) {
            ++n3;
            ++n2;
        }
        this.setLayout(new BorderLayout());
        (this.\u0122 = new Panel()).setBackground(this.\u012c);
        this.add("Center", this.\u0122);
        if (this.\u0125 == 0) {
            this.\u0122.setLayout(new GridLayout(1, n3, this.\u0126, 0));
        }
        else {
            this.\u0122.setLayout(new GridLayout(n3, 1, 0, this.\u0126));
        }
        final String parameter2 = this.getParameter("ButtonImages");
        if (parameter2 != null) {
            try {
                this.\u011e = this.getImage(new URL(this.getDocumentBase(), parameter2));
            }
            catch (Exception ex2) {
                this.\u011e = null;
            }
            if (this.\u011e != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u011e, 0);
                try {
                    if (!mediaTracker.waitForID(0, 1000L)) {
                        this.\u011e = null;
                    }
                }
                catch (Exception ex3) {
                    this.\u011e = null;
                }
            }
        }
        if (this.\u011e != null) {
            this.\u011f = new Image[n3];
            final int n4 = this.\u011e.getWidth(this) / n3;
            final int height = this.\u011e.getHeight(this);
            for (int i = 0; i < n3; ++i) {
                this.\u011f[i] = this.createImage(new FilteredImageSource(this.\u011e.getSource(), new CropImageFilter(n4 * i, 0, n4, height)));
            }
        }
        for (int j = 0; j < n3; ++j) {
            Image image = null;
            if (this.\u011e != null) {
                image = this.\u011f[j];
            }
            final ryFlyButton \u0131 = this.\u0131(this.getParameter("Menu" + (j + 1)), this.\u0136("URL" + (j + 1), ""), this.\u0136("Target" + (j + 1), this.\u0128), true, image);
            this.\u0134(\u0131, \u0131.\u0110, j);
        }
        this.validate();
        this.\u0119 = false;
        this.repaint();
    }
    
    private synchronized void \u0134(final ryFlyButton ryFlyButton, final Menu menu, final int n) {
        int n2 = 0;
        Menu \u012f = menu;
        Menu \u012f2 = menu;
        Menu \u012f3 = menu;
        Menu \u012f4 = menu;
        Menu \u012f5 = menu;
        Menu \u012f6 = menu;
        Menu menu2 = menu;
        while (true) {
            final String string = Integer.toString(n + 1);
            final String parameter = this.getParameter(String.valueOf(string) + "-Entry" + (n2 + 1));
            if (parameter == null) {
                break;
            }
            final int \u0133 = this.\u0133(parameter);
            if (\u0133 == 0) {
                menu2 = menu;
            }
            else if (\u0133 == 1) {
                menu2 = \u012f;
            }
            else if (\u0133 == 2) {
                menu2 = \u012f2;
            }
            else if (\u0133 == 3) {
                menu2 = \u012f3;
            }
            else if (\u0133 == 4) {
                menu2 = \u012f4;
            }
            else if (\u0133 == 5) {
                menu2 = \u012f5;
            }
            else if (\u0133 == 6) {
                menu2 = \u012f6;
            }
            final int n3 = \u0133;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.\u0132(parameter), "|");
            String nextToken;
            try {
                nextToken = stringTokenizer.nextToken();
            }
            catch (Exception ex) {
                nextToken = "NO LABEL";
            }
            if (nextToken.equalsIgnoreCase("sub")) {
                String nextToken2;
                try {
                    nextToken2 = stringTokenizer.nextToken();
                }
                catch (Exception ex2) {
                    nextToken2 = "NO LABEL";
                }
                final String \u0137 = this.\u0136(String.valueOf(string) + "-Disabled" + (n2 + 1), "no");
                if (n3 == 0) {
                    menu2 = (\u012f = this.\u012f(menu, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
                else if (n3 == 1) {
                    menu2 = (\u012f2 = this.\u012f(menu2, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
                else if (n3 == 2) {
                    menu2 = (\u012f3 = this.\u012f(menu2, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
                else if (n3 == 3) {
                    menu2 = (\u012f4 = this.\u012f(menu2, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
                else if (n3 == 4) {
                    menu2 = (\u012f5 = this.\u012f(menu2, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
                else if (n3 == 5) {
                    menu2 = (\u012f6 = this.\u012f(menu2, nextToken2, \u0137.equalsIgnoreCase("no")));
                }
            }
            else if (nextToken.equals("-")) {
                menu2.addSeparator();
            }
            else {
                this.\u0130(menu2, nextToken, this.\u0136(String.valueOf(string) + "-URL" + (n2 + 1), ""), this.\u0136(String.valueOf(string) + "-Target" + (n2 + 1), this.\u0128), this.\u0136(String.valueOf(string) + "-Disabled" + (n2 + 1), "no").equalsIgnoreCase("no"));
            }
            ++n2;
        }
    }
    
    private synchronized int \u0133(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '*') {
                ++n;
            }
        }
        return n;
    }
    
    private synchronized String \u0132(String s) {
        s = s.replace('*', ' ');
        s = s.trim();
        return s;
    }
    
    private ryFlyButton \u0131(final String s, final String s2, final String s3, final boolean enabled, final Image image) {
        final PopupMenu popupMenu = new PopupMenu("");
        popupMenu.setEnabled(enabled);
        popupMenu.setFont(this.\u0127);
        popupMenu.addActionListener(this);
        final ryFlyButton ryFlyButton = new ryFlyButton(this, s, this.\u0125, popupMenu, this.\u011c, this.\u0127, this.\u012b, this.\u012a, this.\u0129, this.\u0121, this.\u0120, image, s2, s3);
        ryFlyButton.setFont(this.\u0127);
        this.\u0122.add(ryFlyButton);
        return ryFlyButton;
    }
    
    private void \u0130(final Menu menu, final String label, final String s, final String s2, final boolean enabled) {
        final ryFlyItem ryFlyItem = new ryFlyItem(this, label, s, s2);
        ryFlyItem.setLabel(label);
        ryFlyItem.setFont(this.\u0127);
        ryFlyItem.setEnabled(enabled);
        ryFlyItem.addActionListener(this);
        menu.add(ryFlyItem);
    }
    
    private Menu \u012f(final Menu menu, final String s, final boolean enabled) {
        final Menu menu2 = new Menu(String.valueOf(s) + "  ");
        menu2.setFont(this.\u0127);
        menu2.setEnabled(enabled);
        menu.add(menu2);
        return menu2;
    }
    
    protected void \u012e(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        if (s.length() > 10 && s.substring(0, 10).equalsIgnoreCase("javascript")) {
            this.\u012d(s);
            return;
        }
        URL url;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (Exception ex) {
            url = null;
        }
        if (url != null) {
            this.getAppletContext().showDocument(url, s2);
        }
    }
    
    private void \u012d(String substring) {
        if (!this.\u011c && !this.getDocumentBase().toString().toLowerCase().startsWith("file:")) {
            return;
        }
        if (substring == null || substring == "$" || substring.startsWith("$")) {
            return;
        }
        final JSObject window = JSObject.getWindow((Applet)this);
        substring = substring.substring(substring.indexOf(":") + 1);
        window.eval(substring);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.\u011b && !this.\u0119) {
            graphics.setColor(this.\u012c);
            graphics.fillRect(0, 0, this.\u0124, this.\u0123);
            return;
        }
        if (this.\u0119 && !this.\u011b) {
            graphics.setColor(this.\u0118);
            graphics.fillRect(0, 0, this.\u0124, this.\u0123);
            graphics.setColor(this.\u0117);
            graphics.setFont(new Font("Dialog", 0, 12));
            graphics.drawString(this.\u0116, 10, 20);
            graphics.drawString(this.\u0115, 10, 35);
            this.\u0135();
            return;
        }
        final Image image = this.createImage(this.\u0124, this.\u0123);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.\u0124, this.\u0123);
        graphics2.setColor(Color.white);
        graphics2.setFont(new Font("Dialog", 0, 12));
        final FontMetrics fontMetrics = this.getFontMetrics(new Font("Dialog", 0, 12));
        final String[] array = { "UNREGISTERED", "Flyer", "by Cool Focus", "www.coolfocus.com" };
        graphics2.drawString(array[0], (this.\u0124 - fontMetrics.stringWidth(array[0])) / 2, fontMetrics.getAscent() + (this.\u0123 - fontMetrics.getHeight()) / 2);
        graphics.drawImage(image, 0, 0, this);
        Thread.currentThread();
        try {
            Thread.sleep(1200L);
        }
        catch (InterruptedException ex) {}
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.\u0124, this.\u0123);
        graphics2.setColor(Color.white);
        graphics2.drawString(array[1], (this.\u0124 - fontMetrics.stringWidth(array[1])) / 2, fontMetrics.getAscent() + (this.\u0123 - fontMetrics.getHeight()) / 2);
        graphics.drawImage(image, 0, 0, this);
        try {
            Thread.sleep(1200L);
        }
        catch (InterruptedException ex2) {}
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.\u0124, this.\u0123);
        graphics2.setColor(Color.white);
        graphics2.drawString(array[2], (this.\u0124 - fontMetrics.stringWidth(array[2])) / 2, fontMetrics.getAscent() + (this.\u0123 - fontMetrics.getHeight()) / 2);
        graphics.drawImage(image, 0, 0, this);
        try {
            Thread.sleep(1200L);
        }
        catch (InterruptedException ex3) {}
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.\u0124, this.\u0123);
        graphics2.setColor(Color.white);
        graphics2.drawString(array[3], (this.\u0124 - fontMetrics.stringWidth(array[3])) / 2, fontMetrics.getAscent() + (this.\u0123 - fontMetrics.getHeight()) / 2);
        graphics.drawImage(image, 0, 0, this);
        try {
            Thread.sleep(1200L);
        }
        catch (InterruptedException ex4) {}
        graphics.setColor(this.\u012c);
        graphics.fillRect(0, 0, this.\u0124, this.\u0123);
        this.\u011b = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.\u011c) {
            this.showStatus("UNREGISTERED Flyer by Cool Focus [www.coolfocus.com]");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.\u011c) {
            this.showStatus("");
        }
        return true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final ryFlyItem ryFlyItem = (ryFlyItem)actionEvent.getSource();
        this.\u012e(ryFlyItem.\u013c, ryFlyItem.\u013d);
    }
    
    public ryFlyer() {
        this.\u0121 = false;
        this.\u011c = false;
        this.\u011b = false;
        this.\u011a = false;
    }
}
