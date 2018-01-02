import java.util.Date;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZeBanner extends Applet implements Runnable
{
    private String _$3783;
    private String[] _$1106;
    private int _$1115;
    private String _$2464;
    private Thread _$1172;
    private int _$1876;
    private int _$3792;
    private int _$3797;
    private int _$3802;
    private int _$3811;
    private int _$961;
    private int _$3816;
    private int _$3820;
    private int _$3828;
    private int _$917;
    private int _$1240;
    private int _$3837;
    private int[] _$3842;
    private int[] _$3848;
    private Image[] _$1938;
    private Image[] _$3851;
    private String[] _$1896;
    private String[] _$1905;
    private String[] _$3855;
    private URL[] _$3858;
    private int[] _$3864;
    private int[] _$3871;
    private Cutline[] _$3878;
    private Cutline[] _$3880;
    private int _$3883;
    private String _$3893;
    private int _$3900;
    private int[] _$3905;
    private int[] _$3910;
    private Color[] _$3915;
    private Color[] _$3919;
    private Color[] _$2549;
    private Color[] _$3924;
    private Color[] _$3929;
    private Color[] _$3938;
    private int _$3948;
    private String[] _$1919;
    private String[] _$3555;
    private int[] _$3954;
    private boolean _$3962;
    private Image _$1178;
    private Graphics _$1215;
    private Graphics _$3968;
    private String _$966;
    
    public ZeBanner() {
        this._$1106 = new String[50];
        this._$3842 = new int[200];
        this._$3848 = new int[200];
        this._$1938 = new Image[200];
        this._$3851 = new Image[200];
        this._$1896 = new String[200];
        this._$1905 = new String[200];
        this._$3855 = new String[200];
        this._$3858 = new URL[200];
        this._$3864 = new int[200];
        this._$3871 = new int[200];
        this._$3878 = new Cutline[200];
        this._$3880 = new Cutline[200];
        this._$3905 = new int[200];
        this._$3910 = new int[200];
        this._$3915 = new Color[200];
        this._$3919 = new Color[200];
        this._$2549 = new Color[200];
        this._$3924 = new Color[200];
        this._$3929 = new Color[200];
        this._$3938 = new Color[200];
        this._$1919 = new String[200];
        this._$3555 = new String[200];
        this._$3954 = new int[200];
        this._$3962 = false;
    }
    
    protected String getParam(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    private void _$3972() {
        for (int i = 0; i < this._$3837; ++i) {
            this._$3880[i] = new Cutline(this._$3855[i], this._$1919[this._$3871[i]], this._$3954[this._$3871[i]], this._$3555[this._$3871[i]], this._$3938[this._$3864[i]], this._$3924[this._$3864[i]], this._$3905[this._$3864[i]], this._$3910[this._$3864[i]], this._$3919[this._$3864[i]], this.size().height, this._$3848[i], this._$1215);
            this._$3878[i] = new Cutline(this._$3855[i], this._$1919[this._$3871[i]], this._$3954[this._$3871[i]], this._$3555[this._$3871[i]], this._$3929[this._$3864[i]], this._$2549[this._$3864[i]], this._$3905[this._$3864[i]], this._$3910[this._$3864[i]], this._$3915[this._$3864[i]], this.size().height, this._$3848[i], this._$1215);
        }
        while (this._$3883 < this.size().width) {
            this._$3883 = 0;
            this._$3842[0] = 0;
            for (int j = 0; j < this._$3837; ++j) {
                this._$3883 += this._$3878[j].maxwidth + 30;
                if (j > 0) {
                    this._$3842[j] = this._$3842[j - 1] + this._$3878[j - 1].maxwidth + 30;
                }
            }
            if (this._$3883 < this.size().width) {
                for (int k = 0; k < this._$3837; ++k) {
                    this._$3878[k].setWidth((this._$3883 - this.size().width) / this._$3837);
                    this._$3880[k].setWidth((this._$3883 - this.size().width) / this._$3837);
                }
            }
        }
        for (int l = 0; l < this._$3837; ++l) {
            this._$1938[l] = this.createImage(this._$3878[l].maxwidth + 30, this.size().height);
            this._$3968 = this._$1938[l].getGraphics();
            this._$3878[l].BuildImage(this._$3968, this.size().height);
            this._$3851[l] = this.createImage(this._$3880[l].maxwidth + 30, this.size().height);
            this._$3968 = this._$3851[l].getGraphics();
            this._$3880[l].BuildImage(this._$3968, this.size().height);
        }
        this._$3962 = true;
    }
    
    public void ScrollIt() {
        this._$1876 += this._$917;
        if (this._$1876 > this._$3883) {
            this._$1876 = 0;
        }
        int $3828;
        for ($3828 = 0; this._$1876 > this._$3842[$3828 + 1] && $3828 != this._$3837 - 1; ++$3828) {}
        int i = this._$3842[$3828] - this._$1876;
        int n = 0;
        while (i < this._$3883) {
            if (this._$3828 != -2 && n == 0 && i + this._$3878[$3828].maxwidth + 30 > this._$3811) {
                if (this._$3828 != $3828) {
                    this._$3828 = $3828;
                    this.showStatus(" ".concat(String.valueOf(this._$1905[$3828])));
                }
                n = 1;
            }
            if (this._$3828 == $3828) {
                this._$1215.drawImage(this._$3851[$3828], i, 0, this);
            }
            else {
                this._$1215.drawImage(this._$1938[$3828], i, 0, this);
            }
            i += this._$3878[$3828].maxwidth + 30;
            if (++$3828 == this._$3837) {
                $3828 = 0;
            }
        }
    }
    
    public void run() {
        if (!this._$3962) {
            this.repaint();
            this._$966 = this.getParameter("file");
            this._$1517();
            this._$3972();
        }
        this._$1876 = 0 - this._$917;
        long n = System.currentTimeMillis() + this._$1240;
        this._$1215.clipRect(0, 0, this.size().width, this.size().height);
        this._$1172.setPriority(1);
        while (true) {
            this.ScrollIt();
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < n) {
                try {
                    Thread.currentThread();
                    Thread.sleep((int)(n - currentTimeMillis));
                }
                catch (InterruptedException ex) {}
            }
            n = System.currentTimeMillis() + this._$1240;
            this.repaint();
        }
    }
    
    public void start() {
        if (this._$1172 == null) {
            (this._$1172 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this._$1172 != null) {
            this._$1172.stop();
            this._$1172 = null;
        }
    }
    
    public void init() {
        this._$966 = this.getParameter("file");
        this._$3802 = 0;
        this._$3811 = 0;
        this._$961 = 0;
        this._$3820 = 0;
        this._$3828 = -2;
        this._$917 = 5;
        this._$1240 = 100;
        this._$3837 = 0;
        this._$1178 = this.createImage(this.size().width, this.size().height);
        this._$1215 = this._$1178.getGraphics();
        try {
            this._$917 = Integer.parseInt(this.getParameter("step"));
        }
        catch (Exception ex) {}
        try {
            this._$1240 = Integer.parseInt(this.getParameter("delay"));
        }
        catch (Exception ex2) {}
        this._$3900 = 0;
        for (String s = this.getParam(String.valueOf(new StringBuffer("color").append(this._$3900 + 1)), "none"); s != "none" && this._$3900 < 30; s = this.getParam(String.valueOf(new StringBuffer("color").append(this._$3900 + 1)), "none")) {
            this.SplitTxt(s, ",");
            try {
                this._$2549[this._$3900] = new Color(Integer.parseInt(this._$1106[0], 16));
            }
            catch (Exception ex3) {}
            try {
                this._$3924[this._$3900] = new Color(Integer.parseInt(this._$1106[1], 16));
            }
            catch (Exception ex4) {}
            try {
                this._$3929[this._$3900] = new Color(Integer.parseInt(this._$1106[2], 16));
            }
            catch (Exception ex5) {}
            try {
                this._$3938[this._$3900] = new Color(Integer.parseInt(this._$1106[3], 16));
            }
            catch (Exception ex6) {}
            final String param = this.getParam(String.valueOf(new StringBuffer("shadow").append(this._$3900 + 1)), "none");
            if (param == "none") {
                this._$3905[this._$3900] = 0;
                this._$3910[this._$3900] = 0;
            }
            else {
                this.SplitTxt(param, ",");
                this._$3905[this._$3900] = Integer.parseInt(this._$1106[0]);
                this._$3910[this._$3900] = Integer.parseInt(this._$1106[1]);
                this._$3915[this._$3900] = new Color(Integer.parseInt(this._$1106[2], 16));
                this._$3919[this._$3900] = new Color(Integer.parseInt(this._$1106[3], 16));
            }
            ++this._$3900;
        }
        this._$3948 = 0;
        for (String s2 = this.getParam(String.valueOf(new StringBuffer("font").append(this._$3948 + 1)), "none"); s2 != "none" && this._$3948 < 30; s2 = this.getParam(String.valueOf(new StringBuffer("font").append(this._$3948 + 1)), "none")) {
            this.SplitTxt(s2, ",");
            this._$1919[this._$3948] = this._$1106[0];
            this._$3954[this._$3948] = Integer.parseInt(this._$1106[1]);
            this._$3555[this._$3948] = this._$1106[2];
            ++this._$3948;
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this._$3962) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.getFontMetrics(new Font("Helvetica", 0, 8));
            graphics.setColor(Color.black);
            graphics.drawString("Applet is loading...", 5, 15);
        }
        else {
            graphics.drawImage(this._$1178, 0, 0, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public boolean mouseMove(final Event event, final int $3811, final int $3812) {
        if (this._$3828 < -1) {
            this._$3828 = -1;
        }
        this._$3811 = $3811;
        this._$961 = $3812;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this._$3828 != -1 && this._$3858[this._$3828] != null) {
            this.getAppletContext().showDocument(this._$3858[this._$3828], this._$1896[this._$3828]);
        }
        return true;
    }
    
    private void _$1517() {
        int n = 1;
        int n2 = 0;
        String s = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        int int1 = 1;
        int int2 = 1;
        int int3 = 0;
        this._$3837 = 0;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), this._$966).openStream());
            do {
                final String trim = dataInputStream.readLine().trim();
                if (trim != null) {
                    if (trim.startsWith("BEGIN")) {
                        n = 0;
                        s = "";
                        s2 = "";
                        s3 = "";
                        s4 = "";
                        int1 = 1;
                        int2 = 1;
                        int3 = 0;
                        n2 = 0;
                        s5 = "";
                        s6 = "01/01/1900";
                    }
                    else {
                        if (n != 0) {
                            continue;
                        }
                        if (trim.startsWith("END")) {
                            n = 1;
                            if (this.CheckDate(s5) && !this.CheckDate(s6)) {
                                this._$3848[this._$3837] = int3;
                                this._$1896[this._$3837] = s2;
                                this._$1905[this._$3837] = s4;
                                this._$3855[this._$3837] = s;
                                this._$3864[this._$3837] = int1 - 1;
                                this._$3871[this._$3837] = int2 - 1;
                                try {
                                    this._$3858[this._$3837] = null;
                                    if (s3.length() > 0) {
                                        this._$3858[this._$3837] = new URL(this.getDocumentBase(), s3);
                                    }
                                }
                                catch (Exception ex) {
                                    System.out.println(ex);
                                }
                                ++this._$3837;
                            }
                        }
                        else {
                            this.SplitTxt(trim, "=");
                        }
                        if (this._$1106[0].startsWith("TIP")) {
                            s4 = this._$1106[1];
                        }
                        if (this._$1106[0].startsWith("DEC")) {
                            int3 = Integer.parseInt(this._$1106[1]);
                        }
                        if (this._$1106[0].startsWith("COLOR")) {
                            int1 = Integer.parseInt(this._$1106[1]);
                        }
                        if (this._$1106[0].startsWith("FONT")) {
                            int2 = Integer.parseInt(this._$1106[1]);
                        }
                        if (this._$1106[0].startsWith("TEXT")) {
                            if (n2 > 0) {
                                s = String.valueOf(new StringBuffer(String.valueOf(s)).append("|").append(this._$1106[1]));
                            }
                            else {
                                s = String.valueOf(s).concat(String.valueOf(this._$1106[1]));
                            }
                            for (int i = 2; i < this._$1115; ++i) {
                                s = String.valueOf(s).concat(String.valueOf("=".concat(String.valueOf(this._$1106[i]))));
                            }
                            ++n2;
                        }
                        if (this._$1106[0].startsWith("TARGET")) {
                            s2 = this._$1106[1];
                        }
                        if (this._$1106[0].startsWith("DATE")) {
                            s5 = this._$1106[1];
                        }
                        if (this._$1106[0].startsWith("SDATE")) {
                            s6 = this._$1106[1];
                        }
                        if (!this._$1106[0].startsWith("URL")) {
                            continue;
                        }
                        s3 = this._$1106[1];
                    }
                }
            } while (dataInputStream.available() > 0);
            dataInputStream.close();
        }
        catch (IOException ex2) {
            System.out.println(ex2);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this._$3828 = -2;
        this.showStatus("");
        return true;
    }
    
    protected void SplitTxt(final String s, final String s2) {
        this._$1115 = 0;
        int index;
        for (int length = s.length(), i = 0; i < length; i = index + 1, ++this._$1115) {
            index = s.indexOf(s2, i);
            if (index < 0) {
                index = length;
            }
            this._$1106[this._$1115] = s.substring(i, index);
        }
    }
    
    public boolean CheckDate(final String s) {
        if (s == "") {
            return true;
        }
        final Date date = new Date();
        final int year = date.getYear();
        final int n = date.getMonth() + 1;
        final int date2 = date.getDate();
        this.SplitTxt(s, "/");
        return year * 10000 + n * 100 + date2 <= (Integer.parseInt(this._$1106[2]) - 1900) * 10000 + Integer.parseInt(this._$1106[1]) * 100 + Integer.parseInt(this._$1106[0]);
    }
}
