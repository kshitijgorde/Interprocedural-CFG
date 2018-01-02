import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class VScrollerBox extends Rectangle
{
    Vector lines;
    private FontMetrics _$3200;
    private String _$3202;
    private int _$3207;
    int decy;
    private Font _$1940;
    private Color _$3223;
    private Color _$3229;
    private Color _$3235;
    private Applet _$3244;
    int delay;
    int spacing;
    int padx;
    int pady;
    int rpadx;
    int rpady;
    int margin;
    int theight;
    int txheight;
    private int _$3265;
    private int _$3268;
    private String _$1907;
    private String _$1901;
    private Image[] _$1959;
    private int[] _$3271;
    private MediaTracker _$1268;
    private Color[] _$3275;
    private int _$3281;
    private int _$3283;
    private int _$928;
    private int _$3285;
    private int _$3298;
    private Image _$3300;
    
    public VScrollerBox(final Image image, final Image image2, final int $3265, final int $3266, final int n, final int n2, final int margin, final int padx, final int pady, final String s, final int spacing, final String $3267, final String $3268, final int delay, final int width, final String s2, final int n3, final String s3, final Color $3269, final Color $3270, final Color $3271, final String s4, final Graphics graphics, final int $3272, final Applet $3273) {
        this.lines = new Vector();
        this.decy = 0;
        this._$3223 = Color.white;
        this._$3229 = Color.white;
        this._$1959 = new Image[4];
        this._$3271 = new int[2];
        this._$3275 = new Color[2];
        super.width = width;
        this._$3244 = $3273;
        this._$928 = $3272;
        this._$3265 = $3265;
        this._$3268 = $3266;
        this._$3275[0] = Color.white;
        this._$3275[1] = Color.white;
        this._$3281 = 0;
        this._$3283 = 0;
        if (s4 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s4, ",");
            this._$3275[0] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            this._$3275[1] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
            this._$3281 = Integer.parseInt(stringTokenizer.nextToken());
            this._$3283 = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (this._$3265 == -1) {
            this._$3265 = 0;
        }
        if (this._$3268 == -1) {
            this._$3268 = 0;
        }
        this._$3271[0] = n;
        this._$3271[1] = n2;
        this._$1907 = $3267;
        this._$1901 = $3268;
        this.spacing = spacing;
        this.delay = delay;
        this.padx = padx;
        this.margin = margin;
        if (this.padx < 0) {
            this.rpadx = -this.padx;
        }
        else {
            this.rpadx = this.padx;
        }
        if (this._$3281 < 0) {
            this.rpadx -= this._$3281;
        }
        if (this._$3281 > 0) {
            this.rpadx += this._$3281;
        }
        this.pady = pady;
        if (this.pady < 0) {
            this.rpady = -this.pady;
        }
        else {
            this.rpady = this.pady;
        }
        if (this._$3283 < 0) {
            this.rpady -= this._$3283;
        }
        if (this._$3283 > 0) {
            this.rpady += this._$3283;
        }
        this._$3223 = $3269;
        this._$3229 = $3270;
        this._$3235 = $3271;
        int n4 = 0;
        if (s3.indexOf("BOLD") != -1) {
            ++n4;
        }
        if (s3.indexOf("ITALIC") != -1) {
            n4 += 2;
        }
        this._$1940 = new Font(s2, n4, n3);
        this._$3200 = graphics.getFontMetrics(this._$1940);
        Toolkit.getDefaultToolkit().getFontList();
        this._$3450(s);
        this._$1959[2] = image;
        this._$1959[3] = image2;
        this._$3458();
    }
    
    private void _$3458() {
        this.txheight = this._$3200.getHeight() * this._$3207;
        this.theight = this._$3200.getHeight() * this._$3207 + this.rpady;
        if (this._$1959[2] != null && this.txheight > 0) {
            if (this.theight < this._$1959[2].getHeight(this._$3244) + this._$3268) {
                this.theight = this._$1959[2].getHeight(this._$3244) + this._$3268;
            }
        }
        else if (this._$1959[2] != null) {
            this.theight = this._$1959[2].getHeight(this._$3244) + this._$3268;
        }
        super.height = this.theight + this.spacing + 1;
        if (super.height % this._$928 != 0) {
            super.height = (super.height / this._$928 + 1) * this._$928;
        }
        this._$1959[0] = this._$3244.createImage(super.width, super.height);
        this._$1959[1] = this._$3244.createImage(super.width, super.height);
        this.BuildTmpImage(this._$1959[0].getGraphics(), this._$3223, 2, 0);
        this.BuildTmpImage(this._$1959[1].getGraphics(), this._$3229, 3, 0);
        (this._$1268 = new MediaTracker(this._$3244)).addImage(this._$1959[0], 2);
        this._$1268.addImage(this._$1959[1], 12);
        try {
            this._$1268.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load");
        }
        this._$1268 = null;
    }
    
    private void _$3450(final String s) {
        this._$3202 = s.trim();
        this._$3207 = 0;
        String s2 = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(this._$3202, "|");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            for (int i = 0; i <= nextToken.length(); ++i) {
                String substring = "";
                try {
                    substring = nextToken.substring(i, i + 1);
                }
                catch (StringIndexOutOfBoundsException ex) {}
                s2 = String.valueOf(s2).concat(String.valueOf(substring));
                if (this._$3200.stringWidth(s2) >= super.width - this.rpadx - this.margin * 2) {
                    final int lastIndex = s2.lastIndexOf(" ");
                    String substring2 = "";
                    if (lastIndex != -1) {
                        try {
                            substring2 = s2.substring(lastIndex + 1);
                            s2 = s2.substring(0, lastIndex);
                            substring2.trim();
                        }
                        catch (StringIndexOutOfBoundsException ex2) {}
                    }
                    ++this._$3207;
                    this.lines.addElement(s2);
                    s2 = substring2;
                }
            }
            ++this._$3207;
            this.lines.addElement(s2);
            s2 = "";
        }
    }
    
    public void BuildTmpImage(final Graphics graphics, final Color color, final int n, final int n2) {
        graphics.setFont(this._$1940);
        graphics.setColor(color);
        if (this._$3298 != 0) {
            return;
        }
        if (this._$3271[n - 2] == 0 && this._$1959[n] != null) {
            graphics.drawImage(this._$1959[n], this._$3265, n2 + this._$3268, this._$3244);
        }
        for (int i = 0; i < this._$3207; ++i) {
            int n3 = ((this.padx < 0) ? 0 : this.padx) + this.margin;
            if (this._$1907.startsWith("CENTER")) {
                n3 = ((this.padx < 0) ? 0 : this.rpadx) + (super.width - this.rpadx - this._$3200.stringWidth(this.lines.elementAt(i))) / 2;
            }
            if (this._$1907.startsWith("RIGHT")) {
                n3 = super.width - ((this.padx < 0) ? this.rpadx : 0) - this.margin - this._$3200.stringWidth(this.lines.elementAt(i));
            }
            int n4 = ((this.pady < 0) ? 0 : this.pady) + (i + 1) * this._$3200.getHeight();
            if (this._$1901.startsWith("BOTTOM")) {
                n4 = this.theight - this.txheight - ((this.pady < 0) ? 0 : this.rpady) + (i + 1) * this._$3200.getHeight();
            }
            if (this._$1901.startsWith("CENTER")) {
                n4 = ((this.pady < 0) ? 0 : this.rpady) + (this.theight - this.txheight - this.rpady) / 2 + (i + 1) * this._$3200.getHeight();
            }
            if (this._$3281 != 0 && this._$3283 != 0) {
                graphics.setColor(this._$3275[n - 2]);
                graphics.drawString(this.lines.elementAt(i), n3 + this._$3281, n2 + n4 + this._$3283);
                graphics.setColor(color);
            }
            graphics.drawString(this.lines.elementAt(i), n3, n2 + n4);
        }
        if (this._$3271[n - 2] == 1 && this._$1959[n] != null) {
            graphics.drawImage(this._$1959[n], this._$3265, n2 + this._$3268, this._$3244);
        }
    }
    
    public void BuildImage(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this._$1959[n2], 0, n, this._$3244);
    }
    
    public void RebuildImage(final Graphics graphics, final int n, final int n2) {
        if (n + super.height > 0 && n < this._$3244.size().height) {
            this.BuildTmpImage(graphics, (n2 == 0) ? this._$3223 : this._$3229, n2 + 2, n);
        }
    }
}
