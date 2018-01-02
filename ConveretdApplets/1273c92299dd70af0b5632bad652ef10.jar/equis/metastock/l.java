// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Panel;

class l extends Panel
{
    private Image a;
    private int b;
    private final int c = 20;
    private m d;
    
    public l() {
        this.d = new m(20);
        URL url = null;
        InputStream openStream = null;
        this.setBackground(MS4Java.ab);
        this.setLayout(null);
        this.reshape(0, 0, 300, 65);
        try {
            url = new URL(MS4Java.k());
            MS4Java.a("Ad File URL: <" + url.toString() + ">");
            openStream = url.openStream();
        }
        catch (Exception ex2) {
            MS4Java.a("Ad File Error: opening <" + url.toString() + ">.");
            System.exit(1);
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream), 4000);
        try {
            this.d.d = 0;
            this.d.a[this.d.d] = "instructions.gif";
            this.d.b[this.d.d] = new URL(MS4Java.b().toString());
            final m d = this.d;
            ++d.d;
            int n = 1;
            while (this.d.d < 20) {
                final String line = bufferedReader.readLine();
                MS4Java.a("Line #" + n + ": " + line);
                if (line == null) {
                    break;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                boolean b = stringTokenizer.countTokens() == 2;
                int n2 = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    final String trim = stringTokenizer.nextToken().trim();
                    if (b) {
                        b = (b & trim.charAt(0) == '\"' & trim.charAt(trim.length() - 1) == '\"');
                    }
                    if (b) {
                        if (n2 == 0) {
                            this.d.a[this.d.d] = trim.substring(1, trim.length() - 1);
                        }
                        else {
                            this.d.b[this.d.d] = new URL(trim.substring(1, trim.length() - 1));
                        }
                    }
                    ++n2;
                }
                if (!b) {
                    MS4Java.a("Invalid line (#" + n + ": " + line + ")");
                }
                else {
                    MS4Java.a("Image: " + this.d.a[this.d.d] + ", Jump: " + this.d.b[this.d.d]);
                    final m d2 = this.d;
                    ++d2.d;
                }
                ++n;
            }
        }
        catch (Exception ex) {
            MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Exception in ad panel: " + ex.toString());
            System.exit(1);
        }
        this.a();
        try {
            openStream.close();
        }
        catch (Exception ex3) {
            MS4Java.a("Error closing <" + url.toString() + ">.");
        }
    }
    
    public void resize(final int n, final int n2) {
        if (this.a != null) {
            this.b = (n - ((this.a.getWidth(this) <= 0) ? 480 : this.a.getWidth(this))) / 2;
            return;
        }
        this.b = 0;
    }
    
    public void paint(final Graphics graphics) {
        if (this.a != null) {
            graphics.drawImage(this.a, this.b, 5, this);
            return;
        }
        MS4Java.a("AdPanel::paint: pImage is null");
    }
    
    public void a() {
        try {
            final m d = this.d;
            ++d.e;
            if (this.d.e > this.d.d - 1) {
                this.d.e = 1;
            }
            final String string = String.valueOf(MS4Java.j().toString()) + "/" + this.d.a[this.d.e];
            MS4Java.a("AdPanel::ChangeAd: " + string);
            final URL url = new URL(string);
            url.openStream();
            this.a = MS4Java.k.getImage(url);
            this.repaint();
        }
        catch (Exception ex) {
            MS4Java.a(String.valueOf(MS4Java.bf[43]) + "Exception changing ad: " + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.d.e != 0 && MS4Java.i() != null) {
                MS4Java.a("AdPanel::mouseDown(): Jumping to " + MS4Java.i().toString());
                MS4Java.k.getAppletContext().showDocument(MS4Java.i());
            }
            else {
                MS4Java.a("AdPanel::mouseDown(): Jumping to " + this.d.b[this.d.e]);
                MS4Java.k.getAppletContext().showDocument(this.d.b[this.d.e]);
            }
        }
        catch (Exception ex) {
            MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Exception jumping to advertisement: " + ex.toString());
            ex.printStackTrace();
        }
        return true;
    }
}
