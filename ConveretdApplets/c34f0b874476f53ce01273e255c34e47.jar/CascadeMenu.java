import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CascadeMenu extends Applet implements MouseListener
{
    private MenuBar mb;
    private Image logo;
    private int aw;
    private int ah;
    private int lw;
    private int lh;
    private int menuBarHeight;
    private String debug;
    private MenuSet ms;
    private int itemh;
    private boolean showAd;
    private int offset;
    private boolean registered;
    private boolean useAnim;
    private String dicon;
    private String dtarget;
    protected boolean useShadow;
    
    public CascadeMenu() {
        this.lh = -2;
        this.debug = "MenuApplet";
        this.itemh = 15;
        this.useAnim = false;
    }
    
    public Component add(final Component component) {
        super.add(component, 0);
        return component;
    }
    
    private boolean checkRegistrationKey() {
        final int n = 13900;
        final String parameter = this.getParameter("RegistrationKey");
        if (parameter == null || parameter.equals("") || parameter.equalsIgnoreCase("Unregistered")) {
            return false;
        }
        final String protocol = this.getDocumentBase().getProtocol();
        final String upperCase = this.getDocumentBase().getHost().toUpperCase();
        if (protocol.equalsIgnoreCase("file") || (upperCase.equals("WWW.REALAPPLETS.COM") && parameter.equalsIgnoreCase("Demo"))) {
            return true;
        }
        final byte[] array = new byte[12];
        final byte[] array2 = new byte[4];
        try {
            for (int i = 0; i < 6; i = (byte)(i + 1)) {
                array[i] = (byte)Integer.parseInt(parameter.substring(i, i + 1), 16);
                array[i + 6] = (byte)Integer.parseInt(parameter.substring(i + 7, i + 8), 16);
            }
        }
        catch (Exception ex) {
            return false;
        }
        for (int j = 0; j < 4; j = (byte)(j + 1)) {
            array2[j] = (byte)(array[j * 3] / 4 * 4 + array[j * 3 + 1] / 4);
        }
        if (n != (array2[0] * 2 + array2[1] * 3) % 16 + (array2[0] * 4 + array2[1] * 6) % 15 * 16 + (array2[2] * 2 + array2[3] * 3) % 14 * 240 + (array2[2] * 4 + array2[3] * 6) % 13 * 3360) {
            return false;
        }
        for (int k = 0; k < 4; ++k) {
            array2[k] = 0;
        }
        for (int n2 = 0, n3 = upperCase.length() - 1; n3 >= 0 && n2 < 2; --n3) {
            if (upperCase.charAt(n3) == '.') {
                n2 = (byte)(n2 + 1);
            }
            int n4 = -1;
            if (upperCase.charAt(n3) >= 'A' && upperCase.charAt(n3) <= 'Z') {
                n4 = upperCase.charAt(n3) - 'A' + '\u0001';
            }
            if (upperCase.charAt(n3) >= '0' && upperCase.charAt(n3) <= '9') {
                n4 = upperCase.charAt(n3) - '0' + '\u0001';
            }
            if (n4 >= 0) {
                if ((upperCase.length() - n3) % 2 >= 1) {
                    array2[0] = (byte)((array2[0] + n4) % 16);
                }
                if ((upperCase.length() - n3) % 4 >= 2) {
                    array2[1] = (byte)((array2[1] + n4) % 16);
                }
                if ((upperCase.length() - n3) % 8 >= 4) {
                    array2[2] = (byte)((array2[2] + n4) % 16);
                }
                if ((upperCase.length() - n3) % 16 >= 8) {
                    array2[3] = (byte)((array2[3] + n4) % 16);
                }
            }
        }
        for (int l = 0; l < 4; l = (byte)(l + 1)) {
            if (array2[l] != array[l * 3 + 1] % 4 * 4 + array[l * 3 + 2] % 4) {
                return false;
            }
        }
        for (int n5 = 0; n5 < 4; n5 = (byte)(n5 + 1)) {
            if (array[n5 * 3] % 4 * 4 + array[n5 * 3 + 2] / 4 != array[n5 * 3 + 1]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean exists(final String s) {
        return this.getParameter(String.valueOf(s) + "_Text") != null;
    }
    
    public Color getColor(String s, final int n, final int n2) {
        String s2;
        if (n == 0) {
            s2 = "Out";
        }
        else {
            s2 = "Over";
        }
        if (s.equals("Bar")) {
            s = "Menubar";
        }
        Color color = new Color(0, 0, 0);
        try {
            if (n2 == 0) {
                color = new Color(Integer.parseInt(this.getParameter(String.valueOf(s) + "_bgcolor" + s2), 16));
            }
            else if (n2 == 1) {
                color = new Color(Integer.parseInt(this.getParameter(String.valueOf(s) + "_fgcolor" + s2), 16));
            }
        }
        catch (Exception ex) {}
        return color;
    }
    
    public Font getFont(String s, final int n) {
        String s2;
        if (n == 0) {
            s2 = "Out";
        }
        else {
            s2 = "Over";
        }
        if (s.equals("Bar")) {
            s = "Menubar";
        }
        Font font = new Font("TimesRoman", 0, 12);
        try {
            font = new Font(this.getParameter(String.valueOf(s) + "_fontstyle" + s2), Integer.parseInt(this.getParameter(String.valueOf(s) + "_fonttype" + s2)), Integer.parseInt(this.getParameter(String.valueOf(s) + "_fontsize" + s2)));
        }
        catch (Exception ex) {}
        return font;
    }
    
    public int getHeight() {
        return this.ah;
    }
    
    public int getItemHeight() {
        return this.itemh;
    }
    
    public int getOffset() {
        return this.offset;
    }
    
    private MenuSet getTree(final String s, final boolean b) {
        int n = 1;
        final MenuSet set = new MenuSet();
        Image image = null;
        while (this.exists(String.valueOf(s) + n)) {
            final String string = String.valueOf(s) + n;
            String s2 = this.getParameter(String.valueOf(string) + "_Icon");
            if (s2 == null) {
                s2 = this.dicon;
            }
            String s3 = this.getParameter(String.valueOf(string) + "_Target");
            if (s3 == null) {
                s3 = this.dtarget;
            }
            if (s2 != null) {
                image = this.getImage(this.getCodeBase(), s2);
            }
            final Item item = new Item(this.getParameter(String.valueOf(string) + "_Text"), this.makeURL(string), s3, b, image);
            if (this.exists(String.valueOf(s) + n + "_1")) {
                item.giveSub(this.getTree(String.valueOf(s) + n + "_", false));
            }
            ++n;
            set.addItem(item);
        }
        return set;
    }
    
    public int getWidth() {
        return this.aw;
    }
    
    public void init() {
        this.registered = this.checkRegistrationKey();
        this.aw = this.getSize().width;
        this.ah = this.getSize().height;
        this.setLayout(null);
        this.itemh = Integer.parseInt(this.getParameter("Item_Height"));
        this.offset = Integer.parseInt(this.getParameter("Item_Offset"));
        if (this.getParameter("Use_animation").toUpperCase().equals("YES")) {
            this.useAnim = true;
        }
        if (this.getParameter("Use_shadow").toUpperCase().equals("YES")) {
            this.useShadow = true;
        }
        this.dicon = this.getParameter("Default_icon");
        this.dtarget = this.getParameter("Default_target");
        this.ms = this.getTree("Item_", true);
        this.logo = this.getImage(this.getCodeBase(), this.getParameter("bgimage"));
        this.setBackground(new Color(Integer.parseInt(this.getParameter("bgcolor"), 16)));
        this.menuBarHeight = Integer.parseInt(this.getParameter("Menubar_Height"));
        (this.mb = new MenuBar(this.getWidth(), this.menuBarHeight, this.ms, this, this.registered)).setBounds(0, 0, this.getWidth(), this.menuBarHeight);
        super.add(this.mb);
        this.addMouseListener(this);
    }
    
    private URL makeURL(final String s) {
        URL url = null;
        try {
            final String parameter = this.getParameter(String.valueOf(s) + "_Action");
            if (parameter != null) {
                if (parameter.toUpperCase().indexOf("http://") != -1 || s.toUpperCase().indexOf("https://") != -1) {
                    url = new URL(parameter);
                }
                else {
                    url = new URL(this.getDocumentBase(), parameter);
                }
            }
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > this.getWidth() - 10 || x < 10 || y > this.getHeight() - 10 || y < 10) {
            this.mb.removeAll();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mb.removeAll();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.logo.getWidth(this) > 0 && this.logo != null) {
            this.lw = this.getWidth() / 2 - this.logo.getWidth(this) / 2;
            this.lh = this.getHeight() / 2 - this.logo.getHeight(this) / 2;
            graphics.drawImage(this.logo, this.lw, this.lh, this);
        }
        else if (this.logo != null) {
            graphics.drawImage(this.logo, this.lw, this.lh, this);
        }
    }
    
    public void performAction(final Item item) {
        this.getAppletContext().showDocument(item.getAction(), item.getTarget());
    }
    
    public void show(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public boolean useAnim() {
        return this.useAnim;
    }
}
