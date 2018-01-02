import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VScroller extends Applet implements Runnable
{
    private int _$895;
    private int _$906;
    private int _$918;
    private int _$928;
    private int _$932;
    private int _$938;
    private int _$947;
    private int _$951;
    private int _$956;
    private int _$963;
    private int _$972;
    private String _$977;
    private String[] _$985;
    private String[] _$994;
    private int[] _$1004;
    private String[] _$1013;
    private VScrollerBox[] _$1033;
    private int[] _$1036;
    private String[] _$1042;
    private String[] _$1050;
    private URL[] _$1055;
    private int _$1060;
    private Image[] _$1073;
    private Image[] _$1080;
    private Image _$1088;
    private Image _$1095;
    private int _$1102;
    private Color[] _$1115;
    private Color[] _$1121;
    private Color _$1127;
    private String[] _$1131;
    private int _$1140;
    private int _$1152;
    private int _$1159;
    private int _$1166;
    private int[] _$1171;
    private Thread _$1183;
    private Image _$1189;
    private Image _$1198;
    private Image _$1207;
    private Graphics _$1226;
    private Graphics _$1230;
    private Graphics _$1234;
    private int _$1236;
    private int _$1243;
    private int _$1251;
    private MediaTracker _$1268;
    boolean isDragging;
    
    public VScroller() {
        this._$985 = new String[20];
        this._$994 = new String[20];
        this._$1004 = new int[20];
        this._$1013 = new String[20];
        this._$1033 = new VScrollerBox[200];
        this._$1036 = new int[200];
        this._$1042 = new String[200];
        this._$1050 = new String[200];
        this._$1055 = new URL[200];
        this._$1073 = new Image[50];
        this._$1080 = new Image[50];
        this._$1115 = new Color[20];
        this._$1121 = new Color[20];
        this._$1131 = new String[250];
        this._$1243 = 0;
        this.isDragging = false;
    }
    
    protected String getParam(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        return (parameter == null) ? s2 : parameter;
    }
    
    public void start() {
        if (this._$1183 == null) {
            (this._$1183 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this._$1183 != null) {
            this._$1183.stop();
            this._$1183 = null;
        }
    }
    
    public void init() {
    }
    
    private void _$1288() {
        this._$906 = 0;
        this._$918 = 0;
        this._$1236 = 0;
        this._$956 = 0;
        this._$1243 = 0;
        this._$956 = 0;
        this._$963 = 0;
        this._$1189 = this.createImage(this.size().width, this.size().height);
        this._$1226 = this._$1189.getGraphics();
        for (String s = this.getParam(String.valueOf(new StringBuffer("font").append(1 + this._$956)), "none"); s != "none"; s = this.getParam(String.valueOf(new StringBuffer("font").append(1 + this._$956)), "none")) {
            this._$985[this._$956] = s;
            this._$1004[this._$956] = Integer.parseInt(this.getParam(String.valueOf(new StringBuffer("fontSize").append(1 + this._$956)), "12"));
            this._$994[this._$956] = this.getParam(String.valueOf(new StringBuffer("fontstyle").append(1 + this._$956)), "none");
            ++this._$956;
        }
        this._$963 = 0;
        for (String s2 = this.getParam(String.valueOf(new StringBuffer("shadow").append(1 + this._$963)), "none"); s2 != "none"; s2 = this.getParam(String.valueOf(new StringBuffer("shadow").append(1 + this._$963)), "none")) {
            this._$1013[this._$963] = s2;
            ++this._$963;
        }
        this._$1102 = 0;
        for (String s3 = this.getParam(String.valueOf(new StringBuffer("Color").append(1 + this._$1102)), "none"); s3 != "none"; s3 = this.getParam(String.valueOf(new StringBuffer("Color").append(1 + this._$1102)), "none")) {
            this._$1354(s3, ",");
            this._$1115[this._$1102] = new Color(Integer.parseInt(this._$1131[0], 16));
            this._$1121[this._$1102] = new Color(Integer.parseInt(this._$1131[1], 16));
            ++this._$1102;
        }
        this._$1251 = Integer.parseInt(this.getParam("delay", "50"));
        this._$1152 = Integer.parseInt(this.getParam("defpadx", "0"));
        this._$1159 = Integer.parseInt(this.getParam("defpady", "0"));
        this._$1166 = Integer.parseInt(this.getParam("defpadsp", "0"));
        this._$1127 = Color.white;
        try {
            this._$1127 = new Color(Integer.parseInt(this.getParameter("bgcolor"), 16));
        }
        catch (Exception ex) {}
        this.showStatus("Loading Images...");
        final String param = this.getParam("BGIMAGE", "none");
        this._$1088 = null;
        if (param != "none" && this._$1131[0].length() > 0) {
            try {
                this._$1088 = this.getImage(this.getDocumentBase(), param);
            }
            catch (Exception ex2) {
                this._$1088 = null;
            }
        }
        final String param2 = this.getParam("FGIMAGE", "none");
        this._$1095 = null;
        if (param2 != "none" && this._$1131[0].length() > 0) {
            try {
                this._$1095 = this.getImage(this.getDocumentBase(), param2);
            }
            catch (Exception ex3) {
                this._$1095 = null;
            }
        }
        this._$1060 = 0;
        for (String s4 = this.getParam(String.valueOf(new StringBuffer("Image").append(1 + this._$1060)), "none"); s4 != "none"; s4 = this.getParam(String.valueOf(new StringBuffer("Image").append(1 + this._$1060)), "none")) {
            this._$1354(s4, ",");
            if (this._$1131[0].length() > 0) {
                try {
                    this._$1073[this._$1060] = this.getImage(this.getDocumentBase(), this._$1131[0]);
                }
                catch (Exception ex4) {}
            }
            if (this._$1131[1].length() > 0 && this._$1140 == 2) {
                try {
                    this._$1080[this._$1060] = this.getImage(this.getDocumentBase(), this._$1131[1]);
                }
                catch (Exception ex5) {}
            }
            ++this._$1060;
        }
        this._$918 = Integer.parseInt(this.getParam("STOPSCROLL", "0"));
        this._$928 = Integer.parseInt(this.getParam("STEP", "1"));
        this._$895 = Integer.parseInt(this.getParam("PAUSEHEIGHT", "0"));
        this._$1234 = this.getGraphics();
        this._$977 = this.getParameter("file");
    }
    
    public void paint(final Graphics graphics) {
        if (this._$1236 < 2 || this._$1236 == 4) {
            graphics.drawImage(this._$1189, 0, 0, this);
        }
        if (this._$1236 == 1) {
            this._$1226.setColor(Color.white);
            this._$1226.drawString("Applet is Loading...", 6, 16);
            this._$1226.setColor(Color.black);
            this._$1226.drawString("Applet is Loading...", 5, 15);
        }
        if (this._$1236 == 2) {
            graphics.drawImage(this._$1189, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        this._$1288();
        this._$1268 = new MediaTracker(this);
        if (this._$1088 != null) {
            this._$1268.addImage(this._$1088, 1);
        }
        if (this._$1095 != null) {
            this._$1268.addImage(this._$1095, 2);
        }
        for (int i = 0; i < this._$1060; ++i) {
            if (this._$1073[i] != null) {
                this._$1268.addImage(this._$1073[i], i * 2 + 3);
            }
            if (this._$1080[i] != null) {
                this._$1268.addImage(this._$1080[i], i * 2 + 5);
            }
        }
        try {
            this._$1268.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load");
        }
        this._$1520();
        this._$932 = -1;
        this._$1236 = 1;
        this.repaint();
        this._$972 = -1;
        long n = 0L;
        this._$938 = 0;
        for (int j = 0; j < this._$1243; ++j) {
            this._$1036[j] = this._$938;
            this._$938 += this._$1033[j].height;
        }
        this._$947 = 0;
        this._$1198 = this.createImage(this.size().width, this._$938);
        (this._$1230 = this._$1198.getGraphics()).setColor(this._$1127);
        this._$1230.fillRect(0, 0, this.size().width, this._$938);
        (this._$1268 = new MediaTracker(this)).addImage(this._$1198, 1);
        try {
            this._$1268.waitForID(1);
        }
        catch (InterruptedException ex2) {
            System.out.println("Error waiting for image to load");
        }
        this._$1236 = 4;
        this.repaint();
        this._$1236 = 2;
        this._$947 = 0;
        try {
            this._$1268.waitForID(1);
        }
        catch (InterruptedException ex3) {
            System.out.println("Error waiting for image to load");
        }
        this._$947 = this.size().height;
        this._$1183.setPriority(1);
        for (int k = 0; k < this._$1060; ++k) {
            this._$1073[k] = null;
            this._$1080[k] = null;
        }
        while (true) {
            this._$1606(this._$972);
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < n) {
                try {
                    Thread.currentThread();
                    Thread.sleep((int)(n - currentTimeMillis));
                }
                catch (InterruptedException ex4) {}
            }
            n = System.currentTimeMillis() + this._$1251;
            this._$1654(this._$947, this._$932);
            this.update(this._$1234);
            if (this._$906 == 0) {
                this._$1668();
            }
            if (this._$947 < -this._$938) {
                this._$947 = this.size().height;
            }
        }
    }
    
    private void _$1654(final int n, final int n2) {
        this._$1226.setColor(this._$1127);
        this._$1226.fillRect(0, 0, this.size().width, this.size().height);
        if (this._$1088 != null && !this.isDragging) {
            this._$1226.drawImage(this._$1088, 0, 0, this);
        }
        int n3 = n;
        for (int i = 0; i < this._$1243; ++i) {
            if (i == n2) {
                this._$1033[i].RebuildImage(this._$1226, n3, 1);
            }
            else {
                this._$1033[i].RebuildImage(this._$1226, n3, 0);
            }
            n3 += this._$1033[i].height;
        }
        if (this._$1095 != null && !this.isDragging) {
            this._$1226.drawImage(this._$1095, 0, 0, this);
        }
    }
    
    private void _$1668() {
        System.out.println("cury ".concat(String.valueOf(this._$947)));
        for (int i = 0; i < this._$1243; ++i) {
            if (this._$1036[i] == -this._$947 + this._$895 && this._$1033[i].delay > 0) {
                int n = this._$972;
                while (System.currentTimeMillis() < System.currentTimeMillis() + this._$1033[i].delay) {
                    try {
                        Thread.sleep(20L);
                    }
                    catch (InterruptedException ex) {}
                    if (this._$972 != n) {
                        this._$1606(this._$972);
                        this._$1654(this._$947, this._$932);
                        this.repaint();
                        n = this._$972;
                    }
                }
                break;
            }
        }
        this._$947 -= this._$928;
    }
    
    private void _$1606(final int n) {
        if (n < 0 || n - this._$947 > this._$938) {
            if (this._$932 != -1) {
                this.showStatus("");
                this._$932 = -1;
            }
        }
        else {
            int $932;
            for ($932 = 0; $932 < this._$1243 && this._$1036[$932] < n - this._$947; ++$932) {}
            if (--$932 != this._$1243 && $932 != -1 && n - this._$947 > this._$1036[$932] + this._$1033[$932].theight) {
                $932 = -1;
            }
            if ($932 == this._$1243 || $932 == -1) {
                if (this._$932 != -1) {
                    this.showStatus("");
                    this._$932 = -1;
                }
            }
            else if ($932 != this._$932) {
                if (this._$932 != -1) {}
                this._$932 = $932;
                this.showStatus(this._$1050[$932]);
            }
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this._$918 == 1) {
            this._$906 = 1;
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this._$906 = 0;
        this._$972 = -1;
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int $972) {
        if (this.isDragging) {
            this._$947 += (this._$972 - $972) / this._$928 * this._$928;
            this.showStatus(" ".concat(String.valueOf(this._$947)));
            this._$1654(this._$947, this._$932);
            this.repaint();
        }
        this._$972 = $972;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int $972) {
        this._$972 = $972;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this._$972 == n2 && this._$932 != -1 && this._$1055[this._$932] != null) {
            this.getAppletContext().showDocument(this._$1055[this._$932], this._$1042[this._$932]);
            return true;
        }
        this.isDragging = false;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int $972) {
        this.isDragging = true;
        this._$972 = $972;
        return true;
    }
    
    private void _$1520() {
        int n = 1;
        String s = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        int int1 = 0;
        int int2 = 0;
        int int3 = 1;
        int int4 = 1;
        int n2 = this._$1166;
        int n3 = this._$1152;
        int n4 = this._$1159;
        int int5 = -1;
        int int6 = -1;
        int int7 = -1;
        int int8 = 0;
        int n5 = 0;
        int int9 = 0;
        int int10 = 0;
        this._$1243 = 0;
        String s6 = "This is a trial version.|To order a full version : visit my site.";
        try {
            this._$1055[this._$1243] = null;
            this._$1055[this._$1243] = new URL(this.getDocumentBase(), "http://cmwalolo.multimania.com");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this._$1042[this._$1243] = "";
        this._$1050[this._$1243] = "Click here for Registering";
        this._$1033[this._$1243] = new VScrollerBox(null, null, 0, 0, 0, 0, 10, 0, 0, s6, 10, "CENTER", "", 3000, this.size().width, this._$985[0], 12, this._$994[0], this._$1115[0], this._$1121[0], this._$1127, null, this._$1226, this._$928, this);
        ++this._$1243;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), this._$977).openStream());
            do {
                final String trim = dataInputStream.readLine().trim();
                if (trim != null) {
                    if (trim.startsWith("BEGIN")) {
                        n = 0;
                        int8 = 0;
                        int2 = 0;
                        int1 = 0;
                        int3 = 1;
                        int4 = 1;
                        n4 = this._$1159;
                        n3 = this._$1152;
                        int6 = -1;
                        int7 = -1;
                        int5 = -1;
                        n2 = this._$1166;
                        s5 = "";
                        s = "";
                        s2 = "";
                        s6 = "";
                        s3 = "";
                        s4 = "";
                        n5 = 0;
                        int9 = 0;
                        int10 = 0;
                    }
                    else {
                        if (n != 0) {
                            continue;
                        }
                        if (trim.startsWith("END")) {
                            n = 1;
                            this._$1050[this._$1243] = s5;
                            if (int5 >= 0) {
                                this._$1033[this._$1243] = new VScrollerBox(this._$1073[int5 - 1], this._$1080[int5 - 1], int6, int7, int9, int10, int8, n3, n4, s6, n2, s2, s, int2, this.size().width, this._$985[int4 - 1], this._$1004[int4 - 1], this._$994[int4 - 1], this._$1115[int3 - 1], this._$1121[int3 - 1], this._$1127, (int1 == 0) ? null : this._$1013[int1 - 1], this._$1226, this._$928, this);
                            }
                            else {
                                this._$1033[this._$1243] = new VScrollerBox(null, null, int6, int7, int9, int10, int8, n3, n4, s6, n2, s2, s, int2, this.size().width, this._$985[int4 - 1], this._$1004[int4 - 1], this._$994[int4 - 1], this._$1115[int3 - 1], this._$1121[int3 - 1], this._$1127, (int1 == 0) ? null : this._$1013[int1 - 1], this._$1226, this._$928, this);
                            }
                            this._$1042[this._$1243] = s3;
                            try {
                                this._$1055[this._$1243] = null;
                                if (s4.length() > 0) {
                                    this._$1055[this._$1243] = new URL(this.getDocumentBase(), s4);
                                }
                            }
                            catch (Exception ex2) {
                                System.out.println(ex2);
                            }
                            ++this._$1243;
                        }
                        else {
                            this._$1354(trim, "=");
                        }
                        if (this._$1131[0].startsWith("TIP")) {
                            s5 = this._$1131[1];
                        }
                        if (this._$1131[0].startsWith("XIPOS")) {
                            int6 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("YIPOS")) {
                            int7 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("PADX")) {
                            n3 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("MARGIN")) {
                            int8 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("PADY")) {
                            n4 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("IMAGE")) {
                            int5 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("SPACING")) {
                            n2 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("DELAY")) {
                            int2 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("COLOR")) {
                            int3 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("FONT")) {
                            int4 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("SHADOW")) {
                            int1 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("HALIGN")) {
                            s2 = this._$1131[1];
                        }
                        if (this._$1131[0].startsWith("IINDEX")) {
                            this._$1354(this._$1131[1], ",");
                            int9 = Integer.parseInt(this._$1131[0]);
                            int10 = Integer.parseInt(this._$1131[1]);
                        }
                        if (this._$1131[0].startsWith("VALIGN")) {
                            s = this._$1131[1];
                        }
                        if (this._$1131[0].startsWith("TEXT")) {
                            if (n5 > 0) {
                                s6 = String.valueOf(new StringBuffer(String.valueOf(s6)).append("|").append(this._$1131[1]));
                            }
                            else {
                                s6 = String.valueOf(s6).concat(String.valueOf(this._$1131[1]));
                            }
                            for (int i = 2; i < this._$1140; ++i) {
                                s6 = String.valueOf(s6).concat(String.valueOf("=".concat(String.valueOf(this._$1131[i]))));
                            }
                            ++n5;
                        }
                        if (this._$1131[0].startsWith("TARGET")) {
                            s3 = this._$1131[1];
                        }
                        if (!this._$1131[0].startsWith("URL")) {
                            continue;
                        }
                        s4 = this._$1131[1];
                    }
                }
            } while (dataInputStream.available() > 0);
            dataInputStream.close();
        }
        catch (IOException ex3) {
            System.out.println(ex3);
        }
    }
    
    private void _$1354(final String s, final String s2) {
        this._$1140 = 0;
        int index;
        for (int length = s.length(), i = 0; i < length; i = index + 1, ++this._$1140) {
            index = s.indexOf(s2, i);
            if (index < 0) {
                index = length;
            }
            this._$1131[this._$1140] = s.substring(i, index);
        }
    }
}
