import java.awt.Event;
import java.awt.Graphics;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01b extends Panel
{
    private tabnav01 z5;
    private tabnav01d z39;
    private tabnav01c z16;
    private Rectangle r;
    private char[] z9;
    private String z37;
    private String z36;
    private tabnav01e[] z38;
    private boolean[] z7;
    private int z10;
    private int z13;
    private int z12;
    private int z11;
    private boolean z8;
    private boolean z40;
    private boolean z6;
    private Image z14;
    private Image z15;
    private int z17;
    private int z19;
    private int z18;
    Image[] q47;
    int q37;
    int q39;
    int q50;
    int q36;
    int q38;
    static final int q49 = 0;
    static final int q48 = 1;
    Font[] q46;
    FontMetrics[] q45;
    int[] q44;
    static final int q43 = 0;
    static final int q42 = 1;
    static final int q41 = 2;
    static final int q40 = 3;
    private static final String[] z26;
    Vector q54;
    Vector q53;
    final int q52 = 0;
    final int q51 = 1;
    private final String z20 = "appletdata";
    private final String z24 = "delimiters";
    private final String z23 = "deftarget";
    private final String z21 = "backgraphic";
    private final String z32 = "icongraphic";
    private final String z31 = "iconcount";
    private final String z33 = "scrollbars";
    private final String z34 = "scrollsize";
    private final String z30 = "hintheight";
    private final String z35 = "tabbackground";
    private final String z27 = "hintbackground";
    private final String z29 = "hintcontent";
    private final String z28 = "hintborder";
    private final String z25 = "menusbold";
    private final String z22 = "defhint";
    
    tabnav01b(final tabnav01 z5, final Rectangle r) {
        this.z9 = new char[] { '{', '}' };
        this.z38 = new tabnav01e[] { null, null };
        this.z7 = new boolean[] { false, false };
        this.z8 = false;
        this.z40 = false;
        this.z6 = false;
        this.q47 = new Image[] { null, null };
        this.q46 = new Font[] { null, null, null, null };
        this.q45 = new FontMetrics[] { null, null, null, null };
        this.q44 = new int[] { 0, 0, 0 };
        this.z5 = z5;
        this.r = r;
        this.setLayout(null);
        this.setBackground(z5.q16[5]);
        this.reshape(r.x, r.y, r.width, r.height);
        this.q54 = new Vector(8, 4);
        this.q53 = new Vector(8, 4);
        for (int i = 0; i < 2; ++i) {
            (this.z38[i] = new tabnav01e((i != 0) ? 1 : 0, 0, 1, 1)).setBackground(z5.q16[2]);
        }
    }
    
    private int z1(final Font font, final FontMetrics fontMetrics) {
        int ascent = fontMetrics.getAscent();
        int descent = fontMetrics.getDescent();
        final int size = font.getSize();
        if (ascent > size) {
            ascent = size;
        }
        if (ascent / 4 < descent) {
            descent = ascent / 4;
        }
        return (descent + ascent << 8) + ascent;
    }
    
    private Font z0(final String s) {
        if (s != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                if (stringTokenizer.countTokens() == 3) {
                    return new Font(stringTokenizer.nextToken(), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                }
            }
            catch (Exception ex) {}
        }
        return new Font("Helvetica", 0, 12);
    }
    
    boolean q33() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            final String q1 = this.z5.q1((i == 0) ? "icongraphic" : "backgraphic");
            if (q1 != null && q1.length() > 1) {
                mediaTracker.addImage(this.q47[i] = this.z5.getImage(this.z5.getDocumentBase(), q1), i);
            }
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0)) {
            this.q47[0] = null;
        }
        if (mediaTracker.isErrorID(1)) {
            this.q47[1] = null;
        }
        if (this.q47[1] != null) {
            this.q37 = this.q47[1].getWidth(null);
            this.q39 = this.q47[1].getHeight(null);
        }
        if (this.q47[0] != null) {
            final int q2 = this.z5.q0(this.z5.q1("iconcount"), 0, 99, 0);
            if (q2 > 0) {
                this.q50 = q2;
                this.q36 = this.q47[0].getWidth(null) / q2;
                this.q38 = this.q47[0].getHeight(null);
            }
        }
        final String q3 = this.z5.q1("deftarget");
        this.z37 = ((q3 == null) ? "_blank" : q3);
        for (int j = 0; j <= 3; ++j) {
            if (j == 3) {
                this.q46[j] = this.q46[1];
                this.q45[j] = this.q45[1];
            }
            else {
                this.q46[j] = this.z0(this.z5.q1(tabnav01b.z26[j] + "font"));
                this.q45[j] = this.getFontMetrics(this.q46[j]);
                this.q44[j] = this.z1(this.q46[j], this.q45[j]);
            }
        }
        if (this.z5.q0(this.z5.q1("menusbold"), 0, 1, 0) > 0 && !this.q46[1].isBold()) {
            this.q46[3] = new Font(this.q46[1].getName(), this.q46[1].getStyle() + 1, this.q46[1].getSize());
            this.q45[3] = this.getFontMetrics(this.q46[3]);
        }
        final String q4 = this.z5.q1("delimiters");
        if (q4 != null && q4.length() > 1) {
            this.z9[0] = q4.charAt(0);
            this.z9[1] = q4.charAt(1);
        }
        if (this.z2(this.z5.q1("appletdata"))) {
            this.z17 = this.z5.q0(this.z5.q1("hintbackground"), 0, 1, 0);
            this.z19 = this.z5.q0(this.z5.q1("hintcontent"), 0, 1, 0);
            this.z18 = this.z5.q0(this.z5.q1("hintborder"), 0, 1, 0);
            if (this.z8) {
                this.z13 = (this.q44[0] >> 8) + 6;
                if (this.z40 && this.q38 > 0 && this.z13 < this.q38 + 4) {
                    this.z13 = this.q38 + 4;
                }
            }
            this.z39 = new tabnav01d(this.z5, this, this.r.width, this.z13, this.z5.q0(this.z5.q1("tabbackground"), 0, 1, 0));
            this.z11 = this.z5.q0(this.z5.q1("hintheight"), 0, this.r.height - (this.z13 + 24), 0);
            this.z6 = (this.z11 > 0);
            final String q5;
            if ((q5 = this.z5.q1("defhint")) != null) {
                this.z36 = q5;
            }
            else {
                this.z36 = "";
            }
            this.z12 = this.r.height - (this.z13 + this.z11 + 1);
            int n = this.r.width - 2;
            final int q6 = this.z5.q0(this.z5.q1("scrollbars"), 0, 3, 0);
            this.z10 = this.z5.q0(this.z5.q1("scrollsize"), 8, 24, 15);
            this.z7[0] = (q6 % 2 > 0);
            this.z7[1] = (q6 > 1);
            if (this.z7[0]) {
                n -= this.z10;
                this.z38[0].reshape(n + 1, this.z13, this.z10, (q6 > 2) ? (this.z12 - this.z10) : this.z12);
                this.add(this.z38[0]);
                this.z38[0].sbSetValues(0, 1, 1);
            }
            if (this.z7[1]) {
                this.z12 -= this.z10;
                this.z38[1].reshape(1, this.z13 + this.z12, n, this.z10);
                this.add(this.z38[1]);
                this.z38[1].sbSetValues(0, 1, 1);
            }
            this.z16 = new tabnav01c(this.z5, this, this.z13, n, this.z12);
            if (this.z8) {
                this.add(this.z39);
            }
            this.add(this.z16);
            return true;
        }
        return false;
    }
    
    private boolean z2(final String s) {
        tabnav01g tabnav01g = null;
        final StringBuffer sb = new StringBuffer(s.length() + 64);
        Label_0164: {
            if (s.indexOf(this.z9[0]) == -1) {
                URL url;
                try {
                    url = new URL(this.z5.getDocumentBase(), s);
                }
                catch (MalformedURLException ex) {
                    return false;
                }
                try {
                    final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                    String line;
                    while ((line = dataInputStream.readLine()) != null) {
                        sb.append(line + " ");
                    }
                    dataInputStream.close();
                    break Label_0164;
                }
                catch (IOException ex2) {
                    return false;
                }
            }
            int i = 0;
            while (i < s.length()) {
                final char char1;
                if ((char1 = s.charAt(i++)) > '\u001f') {
                    sb.append(char1);
                }
            }
        }
        final String string = sb.toString();
        final String[] array = new String[3];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int index = 0;
        FontMetrics fontMetrics = null;
        boolean b = true;
        while (b) {
            final int index2;
            if ((index2 = string.indexOf(this.z9[0], index)) != -1 && (index = string.indexOf(this.z9[1], index2)) != -1 && index - index2 > 3) {
                final String trim = string.substring(index2 + 1, index).trim();
                final String upperCase = trim.substring(0, 4).toUpperCase();
                boolean b2 = true;
                int n6;
                if ((n6 = upperCase.indexOf("/TA")) != -1) {
                    ++n;
                    n2 = 0;
                    b2 = false;
                }
                else if ((n6 = upperCase.indexOf("TAB")) == 0) {
                    n6 += 3;
                    n5 = 0;
                    fontMetrics = this.q45[0];
                }
                else if ((n6 = upperCase.indexOf("/ME")) == 0) {
                    if (tabnav01g != null) {
                        tabnav01g.q73(n3);
                    }
                    n3 = 0;
                    tabnav01g = null;
                    if (n2 > 0) {
                        --n2;
                    }
                    b2 = false;
                }
                else if ((n6 = upperCase.indexOf("MEN")) == 0) {
                    n6 += 4;
                    ++n2;
                    n5 = 1;
                    fontMetrics = this.q45[3];
                }
                else if ((n6 = upperCase.indexOf("ITE")) == 0) {
                    n6 += 4;
                    n5 = 2;
                    fontMetrics = this.q45[1];
                }
                else {
                    b2 = false;
                }
                if (!b2) {
                    continue;
                }
                for (int j = 0; j < 3; ++j) {
                    array[j] = "";
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(trim.substring(n6), "|");
                String trim2;
                for (int n7 = 0; stringTokenizer.hasMoreTokens() && n7 < 3; array[n7++] = trim2) {
                    trim2 = stringTokenizer.nextToken().trim();
                    if (trim2.length() > 0) {}
                }
                if (array[0].length() <= 0) {
                    continue;
                }
                if (this.q53.size() < n + 1) {
                    this.q53.addElement(new Vector<Vector>(16, 8));
                    n4 = 0;
                }
                final tabnav01g tabnav01g2 = new tabnav01g(this.z5, array, n, n5, (n5 == 1) ? (n2 - 1) : n2, fontMetrics, n4);
                if (n5 == 0) {
                    this.q54.addElement(tabnav01g2);
                    if (this.z40) {
                        continue;
                    }
                    this.z40 = (tabnav01g2.q64() > -1);
                }
                else {
                    ++n4;
                    this.q53.elementAt(n).addElement(tabnav01g2);
                    if (n5 == 1) {
                        tabnav01g = tabnav01g2;
                    }
                    else {
                        if (n5 != 2) {
                            continue;
                        }
                        ++n3;
                    }
                }
            }
            else {
                b = false;
            }
        }
        this.z8 = (this.q54.size() > 0);
        return this.q53.size() > 0;
    }
    
    public void paint(final Graphics graphics) {
        if (this.z6 && this.z14 == null) {
            this.z14 = this.createImage(this.r.width, this.z11);
        }
        if (this.q50 > 0 && this.z15 == null) {
            this.z15 = this.createImage(this.q36, this.q38);
        }
        if (this.z7[0] && this.z7[1]) {
            graphics.setColor(this.z5.q16[2]);
            final int n = this.r.width - (this.z10 + 1);
            final int n2 = this.r.height - (this.z11 + this.z10 + 1);
            final int n3 = n + this.z10 - 1;
            final int n4 = n2 + this.z10 - 1;
            graphics.fillRect(n, n2, this.z10, this.z10);
            graphics.setColor(this.z5.q16[2].darker());
            graphics.drawLine(n, n4, this.r.width, n4);
            graphics.drawLine(n3, n2, n3, n4);
        }
        final int n5 = this.r.height - this.z11;
        graphics.setColor(this.z5.q16[1]);
        graphics.drawLine(0, this.z13, 0, n5);
        graphics.drawLine(this.r.width - 1, this.z13, this.r.width - 1, n5);
        graphics.drawLine(0, n5 - 1, this.r.width, n5 - 1);
        this.z4(graphics, this.z36);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void q28() {
        this.z39.q28();
    }
    
    void q34() {
        this.z16.q56(true);
        this.z16.repaint();
    }
    
    int q31(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.z15 != null && n3 >= 0 && n3 < this.q50) {
            final Graphics graphics2 = this.z15.getGraphics();
            graphics2.setColor(graphics.getColor());
            graphics2.fillRect(0, 0, this.q36, this.q38);
            graphics2.drawImage(this.q47[0], -(n3 * this.q36), 0, null);
            graphics2.dispose();
            graphics.drawImage(this.z15, n, n2, null);
            return this.q36 + n4;
        }
        return 0;
    }
    
    private void z4(final Graphics graphics, final String s) {
        if (this.z6) {
            final Graphics graphics2 = this.z14.getGraphics();
            graphics2.setColor(this.z5.q16[7]);
            graphics2.fillRect(0, 0, this.r.width, this.z11);
            if (this.z17 == 0) {
                this.z5.q3(graphics2, this.q47[1], new Rectangle(-this.z5.q19, 0, this.r.width, this.z11));
            }
            int n = 0;
            int n3;
            final int n2 = n3 = (this.q44[2] & 0xFF);
            graphics2.setFont(this.q46[2]);
            graphics2.setColor(this.z5.q16[8]);
            if (this.z19 > 0) {
                graphics2.drawString(s, 3, n3);
            }
            else {
                int z3;
                while ((z3 = this.z3(n, this.r.width - 6, s)) != -1) {
                    graphics2.drawString(s.substring(n, z3).trim(), 3, n3);
                    n3 += n2;
                    n = ++z3;
                }
            }
            if (this.z18 > 0) {
                graphics2.setColor(this.z5.q16[1]);
                graphics2.drawRect(0, -1, this.r.width - 1, this.z11);
            }
            graphics2.dispose();
            graphics.drawImage(this.z14, 0, this.r.height - this.z11, null);
        }
    }
    
    private int z3(final int n, final int n2, final String s) {
        int i = n;
        final int n3 = s.length() - 1;
        if (i > n3) {
            return -1;
        }
        final StringBuffer sb = new StringBuffer(64);
        while (i <= n3) {
            final char char1 = s.charAt(i);
            sb.append(char1);
            if ((char1 == ' ' || i == n3) && this.q45[2].stringWidth(sb.toString()) > n2) {
                while (s.charAt(i - 1) != ' ' && i > n + 2) {
                    --i;
                }
                return i - 1;
            }
            ++i;
        }
        return i;
    }
    
    void q30(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        if (n == -1) {
            this.z4(graphics, this.z36);
            this.z5.showStatus("");
        }
        else {
            tabnav01g tabnav01g;
            if (n2 == 0) {
                tabnav01g = this.q54.elementAt(n);
            }
            else {
                tabnav01g = this.q53.elementAt(this.q75()).elementAt(n);
            }
            final String q63 = tabnav01g.q63();
            final String q64 = tabnav01g.q71();
            this.z4(graphics, (this.z19 == 0) ? q63 : q64);
            this.z5.showStatus((this.z19 == 0) ? q64 : q63);
        }
        graphics.dispose();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof tabnav01e) {
            this.z16.q55(this.z7[0] ? this.z38[0].sbGetValue() : 0, this.z7[1] ? this.z38[1].sbGetValue() : 0);
            return true;
        }
        return super.handleEvent(event);
    }
    
    int q75() {
        if (this.z8) {
            return this.z39.q57();
        }
        return 0;
    }
    
    tabnav01e q32(final int n) {
        if (this.z7[n]) {
            return this.z38[n];
        }
        return null;
    }
    
    void q35(final tabnav01g tabnav01g) {
        final String q71 = tabnav01g.q71();
        if (q71.length() > 1) {
            String s = tabnav01g.getTarget();
            if (s.length() < 2) {
                s = this.z37;
            }
            try {
                URL url;
                if (q71.indexOf("://") > 0) {
                    url = new URL(q71);
                }
                else if (q71.charAt(3) == '.') {
                    url = new URL("http://" + q71);
                }
                else {
                    url = new URL(this.z5.getDocumentBase(), q71);
                }
                this.z5.getAppletContext().showDocument(url, s);
            }
            catch (Exception ex) {
                this.z5.showStatus("URL error");
            }
        }
    }
    
    static {
        z26 = new String[] { "tab", "menu", "hint" };
    }
}
