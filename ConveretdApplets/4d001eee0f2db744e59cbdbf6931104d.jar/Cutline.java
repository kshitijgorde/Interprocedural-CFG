import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cutline
{
    private String[] _$2343;
    private FontMetrics _$2448;
    private String _$1892;
    private int _$2348;
    private int _$4384;
    private Font _$1919;
    private Color _$3929;
    private Color _$4078;
    private Color _$4388;
    int maxwidth;
    private int _$4398;
    private int _$4401;
    
    public Cutline(final String s, final String s2, final int n, final String s3, final Color $3929, final Color $3930, final int $3931, final int $3932, final Color $3933, final int n2, final int $3934, final Graphics graphics) {
        this._$2343 = new String[20];
        this._$4384 = 0;
        this._$3929 = Color.black;
        this._$4078 = Color.white;
        this._$4388 = Color.gray;
        this.maxwidth = 0;
        int n3 = 0;
        if (s3.indexOf("BOLD") >= 0) {
            ++n3;
        }
        if (s3.indexOf("ITALIC") >= 0) {
            n3 += 2;
        }
        this._$1919 = new Font(s2, n3, n);
        this._$2448 = graphics.getFontMetrics(this._$1919);
        this._$4398 = $3931;
        this._$4401 = $3932;
        this._$3929 = $3929;
        this._$4078 = $3930;
        this._$4388 = $3933;
        this._$2440(s);
        this._$4384 = $3934;
    }
    
    private void _$2440(final String s) {
        this._$1892 = s.trim();
        String substring = "";
        this._$2348 = 0;
        this._$2343[0] = "";
        for (int i = 0; i < s.length(); ++i) {
            try {
                substring = s.substring(i, i + 1);
            }
            catch (StringIndexOutOfBoundsException ex) {}
            if (substring.charAt(0) == '|') {
                if (this.maxwidth < this._$2448.stringWidth(this._$2343[this._$2348])) {
                    this.maxwidth = this._$2448.stringWidth(this._$2343[this._$2348]);
                }
                ++this._$2348;
                this._$2343[this._$2348] = "";
            }
            else {
                final String[] $2343 = this._$2343;
                final int $2344 = this._$2348;
                $2343[$2344] = String.valueOf($2343[$2344]).concat(String.valueOf(substring));
            }
        }
        if (this.maxwidth < this._$2448.stringWidth(this._$2343[this._$2348])) {
            this.maxwidth = this._$2448.stringWidth(this._$2343[this._$2348]);
        }
        ++this._$2348;
    }
    
    public void setWidth(final int n) {
        this.maxwidth += this.maxwidth;
    }
    
    public void BuildImage(final Graphics graphics, final int n) {
        final int $4384 = this._$4384;
        graphics.setColor(this._$3929);
        graphics.fillRect(0, 0, this.maxwidth + 30, n);
        graphics.setFont(this._$1919);
        graphics.setColor(this._$4078);
        for (int i = 0; i < this._$2348; ++i) {
            if (this._$4398 != 0 && this._$4401 != 0) {
                graphics.setColor(this._$4388);
                graphics.drawString(this._$2343[i], this._$4398 + 15 + (this.maxwidth - this._$2448.stringWidth(this._$2343[i])) / 2, this._$4401 + $4384 + (i + 1) * this._$2448.getHeight());
                graphics.setColor(this._$4078);
            }
            graphics.drawString(this._$2343[i], 15 + (this.maxwidth - this._$2448.stringWidth(this._$2343[i])) / 2, $4384 + (i + 1) * this._$2448.getHeight());
        }
    }
}
