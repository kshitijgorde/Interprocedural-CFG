import java.awt.event.KeyEvent;
import java.net.URL;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public final class an extends Thread
{
    public int a;
    private int[] a;
    private au a;
    private String a;
    private int i;
    private int j;
    private int k;
    private O a;
    public int b;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    public boolean a;
    public int c;
    private ArrayList a;
    private ArrayList b;
    private ArrayList c;
    private String b;
    private boolean e;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean f;
    private String g;
    private String h;
    private String i;
    private String j;
    public int d;
    public int e;
    private boolean[] a;
    private String k;
    private Image a;
    private boolean g;
    private Image b;
    private Font a;
    private Font b;
    private Font c;
    private Font d;
    private boolean h;
    public boolean b;
    private int q;
    public int f;
    public Cursor a;
    public Cursor b;
    public Cursor c;
    public boolean c;
    private Image c;
    public boolean d;
    private boolean i;
    private boolean j;
    private int r;
    public int g;
    private int s;
    public int h;
    
    public final void a() {
        this.i = 300;
        this.j = -1;
        this.k = -1;
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        graphics.setColor(color);
        graphics.drawRect(n, n2, n3, n4);
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final String s) {
        this.a(graphics, n, n2, n3, n4, s, this.a.a());
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, int n3, int n4, final String s, final boolean b) {
        if (n3 < 50) {
            n3 = 50;
        }
        if (n4 < 50) {
            n4 = 50;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        if (n4 > 255) {
            n4 = 255;
        }
        graphics.setColor(Color.black);
        graphics.fillRoundRect(n - 2, n2 - 2, n3 + 4, n4 + 4, 24, 24);
        final Color color = new Color(0, 0, 180);
        final Color color2 = new Color(64, 64, 255);
        graphics.setColor(color);
        graphics.fillRoundRect(n, n2, n3, n4, 24, 24);
        graphics.setColor(new Color(128, 128, 255));
        graphics.fillRoundRect(n + 3, n2 + 3, n3 - 6, n4 - 6, 21, 21);
        graphics.setColor(color);
        graphics.fillRoundRect(n, n2, n3, 30, 24, 24);
        graphics.fillRect(n, n2 + 10, n3, 20);
        graphics.setColor(color2);
        graphics.fillRoundRect(n + 3, n2 + 3, n3 - 6, 24, 21, 21);
        graphics.fillRect(n + 3, n2 + 10, n3 - 6, 20);
        graphics.setColor(color);
        graphics.fillRect(n, n2 + 28, n3, 2);
        graphics.setColor(Color.black);
        graphics.setFont(this.a);
        graphics.drawString(s, n + 10 + 1, n2 + 22 - 2 + 1);
        graphics.setColor(new Color(220, 220, 255));
        graphics.drawString(s, n + 10 + 0, n2 + 22 - 2 + 0);
        if (n4 > 100) {
            if (this.d >= 0 && this.b != 11) {
                graphics.setColor(Color.white);
                final int n5 = n + n3 - 6 - 16 - 4;
                final int n6 = n2 + 3 + 4;
                graphics.fillRoundRect(n5, n6, 16, 16, 4, 4);
                graphics.setColor(new Color(255, 110, 110));
                graphics.fillRoundRect(n5 + 1, n6 + 1, 14, 14, 4, 4);
                graphics.setColor(Color.white);
                graphics.drawLine(n5 + 6, n6 + 6, n5 + 16 - 6, n6 + 16 - 6);
                graphics.setColor(Color.white);
                graphics.drawLine(n5 + 6, n6 + 16 - 6, n5 + 16 - 6, n6 + 6);
                if (!this.h) {
                    this.c.add(new Object(this, n5, n6, 16, 16, 27) {
                        public int a;
                        public int b;
                        public int c;
                        public int d;
                        public int e;
                        
                        {
                            this.a = 0;
                            this.b = 0;
                            this.c = 0;
                            this.d = 0;
                            this.e = 0;
                            this.c = c;
                            this.d = d;
                            this.b = b;
                            this.a = a;
                            this.e = e;
                        }
                        
                        public final boolean a(final int n, final int n2) {
                            return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                        }
                    });
                    this.h = true;
                }
            }
            if (b) {
                if (this.i > 0) {
                    --this.i;
                }
                else {
                    if (this.j < 0) {
                        this.j = n * 2 + 32;
                    }
                    else {
                        ++this.j;
                        if (this.j > 512) {
                            this.j = -1;
                            this.k = -1;
                            this.i = 300;
                        }
                    }
                    if (this.k < 0) {
                        this.k = n2 - 12;
                    }
                    else {
                        if (this.j > (n + n3 - 10) * 2) {
                            this.k += 2;
                        }
                        if (this.k > 240) {
                            this.j = -1;
                            this.k = -1;
                            this.i = 300;
                        }
                    }
                    if (this.i == 0 && this.a.a.a.c != null) {
                        graphics.drawImage(this.a.a.a.c, this.j / 2, this.k, new Color(255, 255, 255, 0), null);
                    }
                }
            }
        }
        if (this.a.a.a.e != null) {
            graphics.drawImage(this.a.a.a.e, n + 10, n2 - 25, new Color(255, 255, 255, 0), null);
        }
    }
    
    public final void a(final String c) {
        this.c = c;
        this.d = "";
        this.e = "";
        this.f = "";
    }
    
    public final void a(final String c, final String d) {
        this.c = c;
        this.d = d;
        this.e = "";
        this.f = "";
    }
    
    public final void a(final String c, final String d, final String e) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = "";
    }
    
    public final void a(final String c, final String d, final String e, final String f) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    private Image a(final int n) {
        this.b = true;
        this.g = false;
        try {
            String s;
            for (s = "" + (n + 1); s.length() < 3; s = "0" + s) {}
            this.a.a("Attempting to load State from Slot " + s);
            final DataInputStream appletLoadSaveState;
            if ((appletLoadSaveState = this.a.a.AppletLoadSaveState(s)) == null) {
                this.a.a("No State Connection for LoadState " + s);
                return null;
            }
            this.a.a("Inputstream Found");
            int n2 = 0;
            final int[] array = new int[1048576];
            while (true) {
                final String line = appletLoadSaveState.readLine();
                this.a.a("<: " + line);
                if (line == null) {
                    appletLoadSaveState.close();
                    final byte[] array2 = new byte[n2];
                    for (int i = 0; i < n2; ++i) {
                        array2[i] = (byte)array[i];
                    }
                    if (array2.length < 16) {
                        return null;
                    }
                    if (!new String(array2, 0, 16).startsWith("NSAVESTE")) {
                        return null;
                    }
                    if (array2[8] * 256 + array2[9] < 2) {
                        return null;
                    }
                    final int n3 = array2[10] * 256 + array2[11];
                    final long n4 = 0L + ((array2[12] + 256 & 0xFF) << 24) + ((array2[13] + 256 & 0xFF) << 16) + ((array2[14] + 256 & 0xFF) << 8) + ((array2[15] + 256 & 0xFF) << 0);
                    if (n3 != 1 && n3 != 2) {
                        return null;
                    }
                    if (n4 > 10485760L) {
                        return null;
                    }
                    final byte[] array3 = new byte[(int)n4];
                    for (int j = 0; j < (int)n4; ++j) {
                        array3[j] = array2[j + 16];
                    }
                    byte[] array4;
                    if (n3 == 1) {
                        array4 = aK.c(array3);
                    }
                    else {
                        array4 = aK.b(array3);
                    }
                    this.b = false;
                    return this.a(array4);
                }
                else {
                    String substring;
                    int n5;
                    if (line.indexOf(":") > 1) {
                        substring = line.substring(line.indexOf(":") + 1);
                        n5 = Integer.parseInt(line.substring(0, line.indexOf(":")));
                    }
                    else {
                        n5 = (substring = line).length();
                    }
                    if (substring.length() % 2 != 0 || n5 != substring.length()) {
                        return null;
                    }
                    for (int k = 0; k < substring.length() / 2; ++k) {
                        array[n2++] = aK.a(substring.substring(k * 2, k * 2 + 2));
                    }
                }
            }
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Image a(final byte[] array) throws Exception {
        Image image = null;
        if (array != null) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final byte[] array2 = new byte[3];
            while (byteArrayInputStream.read(array2) == 3) {
                final String s = new String(array2);
                final int b = aK.b(byteArrayInputStream);
                if (s.equals("CPU") || s.equals("MEM") || s.equals("PPU") || s.equals("JY1") || s.equals("JY2") || s.equals("MAP") || s.equals("APU") || s.equals("SND")) {
                    byteArrayInputStream.skip(b);
                }
                else if (s.equals("MOV")) {
                    this.g = true;
                    byteArrayInputStream.skip(b);
                }
                else {
                    if (!s.equals("IMG")) {
                        byteArrayInputStream.close();
                        return null;
                    }
                    final byte[] array3 = new byte[b];
                    byteArrayInputStream.read(array3);
                    image = Toolkit.getDefaultToolkit().createImage(array3);
                }
            }
            byteArrayInputStream.close();
            return image;
        }
        return null;
    }
    
    private boolean a() {
        return this.a.a.f || this.a.a.e;
    }
    
    private void b() {
        this.d = 0;
        this.e = 6;
        final boolean[] a = { false, !this.a.a(), !this.a.a(), false, false, false };
        final int n = 3;
        this.a.a();
        a[n] = true;
        a[5] = (a[4] = false);
        this.a = a;
    }
    
    private final void c() {
        if (!this.e) {
            this.e = true;
            final Thread thread;
            (thread = new Thread(this)).setPriority(1);
            thread.start();
        }
    }
    
    public final void run() {
        try {
            final al al = new al(this.a.a);
            if (al.a(new URL("http", this.a.b, this.a.d))) {
                for (String s = al.b; s.indexOf("\n") > -1; s = s.substring(s.indexOf("\n") + 1)) {
                    final String substring;
                    if ((substring = s.substring(0, s.indexOf("\n"))).indexOf(";") > -1) {
                        final String trim = substring.substring(substring.indexOf(";") + 1).replace('\n', ' ').replace('\r', ' ').trim();
                        final String trim2 = substring.substring(0, substring.indexOf(";")).replace('\n', ' ').replace('\r', ' ').trim();
                        this.a.add(trim);
                        this.b.add(trim2);
                    }
                }
                this.b = null;
                return;
            }
            switch (al.a) {
                case 0: {
                    this.b = "Online NESCafe Game Server is unavailable.";
                }
                case 1: {
                    this.b = "The Online NESCafe Game List was not readable.";
                }
                case 2: {
                    this.b = "Fetching of Online NESCafe Game List was manually cancelled.";
                }
                default: {
                    this.b = al.a;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private boolean a(final int n) {
        switch (n) {
            case 8:
            case 18:
            case 44:
            case 46:
            case 65:
            case 67:
            case 71:
            case 73:
            case 76:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 112:
            case 113:
            case 114:
            case 127:
            case 520: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private String a(final int n) {
        if ((n >= 64 && n <= 90) || (n >= 48 && n <= 58)) {
            return "" + (char)n;
        }
        switch (n) {
            case 520: {
                return "#";
            }
            case 44: {
                return "<";
            }
            case 45: {
                return "-";
            }
            case 46: {
                return ">";
            }
            case 47: {
                return "/";
            }
            case 59: {
                return ";";
            }
            case 61: {
                return "=";
            }
            case 91: {
                return "[";
            }
            case 92: {
                return "\\";
            }
            case 93: {
                return "]";
            }
            case 112: {
                return "F1";
            }
            case 113: {
                return "F2";
            }
            case 114: {
                return "F3";
            }
            case 115: {
                return "F4";
            }
            case 116: {
                return "F5";
            }
            case 117: {
                return "F6";
            }
            case 118: {
                return "F7";
            }
            case 119: {
                return "F8";
            }
            case 120: {
                return "F9";
            }
            case 121: {
                return "F10";
            }
            case 122: {
                return "F11";
            }
            case 123: {
                return "F12";
            }
            case 222: {
                return "@";
            }
            case 37: {
                return "LEFT";
            }
            case 38: {
                return "UP";
            }
            case 39: {
                return "RIGHT";
            }
            case 40: {
                return "DOWN";
            }
            case 32: {
                return "SPACE";
            }
            case 10: {
                return "ENTER";
            }
            case 17: {
                return "CTRL";
            }
            case 16: {
                return "SHIFT";
            }
            case 8: {
                return "BACKSPACE";
            }
            case 155: {
                return "INSERT";
            }
            case 36: {
                return "HOME";
            }
            case 33: {
                return "PGUP";
            }
            case 34: {
                return "PGDOWN";
            }
            case 35: {
                return "END";
            }
            case 127: {
                return "DELETE";
            }
            case 0: {
                return "[not assigned]";
            }
            default: {
                return "" + n;
            }
        }
    }
    
    public an(final int n, final au au) {
        this.a = 0;
        this.a = new int[8];
        this.a = "";
        this.i = 300;
        this.j = -1;
        this.k = -1;
        this.a = null;
        this.b = 0;
        this.l = 0;
        this.m = 0;
        this.n = 1;
        this.o = 0;
        this.p = 0;
        this.a = false;
        this.c = 0;
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.b = "Please wait while ROM Server is contacted...";
        this.e = false;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.f = false;
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.d = 0;
        this.e = 0;
        this.a = new boolean[] { false, false, false, false, false };
        this.k = "";
        this.a = null;
        this.g = false;
        this.b = null;
        this.a = new Font("Dialog", 0, 13);
        this.b = new Font("Dialog", 0, 10);
        this.c = new Font("Dialog", 1, 11);
        this.d = new Font("Dialog", 0, 11);
        this.h = false;
        this.b = true;
        this.q = 0;
        this.f = 60;
        this.a = new Cursor(12);
        this.b = new Cursor(3);
        this.c = new Cursor(0);
        this.c = false;
        this.c = null;
        this.d = false;
        this.i = false;
        this.j = false;
        this.r = 0;
        this.g = 0;
        this.s = 0;
        this.a(n, au, this.h = 0, false);
    }
    
    public an(final int n, final au au, final int n2, final boolean b) {
        this.a = 0;
        this.a = new int[8];
        this.a = "";
        this.i = 300;
        this.j = -1;
        this.k = -1;
        this.a = null;
        this.b = 0;
        this.l = 0;
        this.m = 0;
        this.n = 1;
        this.o = 0;
        this.p = 0;
        this.a = false;
        this.c = 0;
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.b = "Please wait while ROM Server is contacted...";
        this.e = false;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.f = false;
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.d = 0;
        this.e = 0;
        this.a = new boolean[] { false, false, false, false, false };
        this.k = "";
        this.a = null;
        this.g = false;
        this.b = null;
        this.a = new Font("Dialog", 0, 13);
        this.b = new Font("Dialog", 0, 10);
        this.c = new Font("Dialog", 1, 11);
        this.d = new Font("Dialog", 0, 11);
        this.h = false;
        this.b = true;
        this.q = 0;
        this.f = 60;
        this.a = new Cursor(12);
        this.b = new Cursor(3);
        this.c = new Cursor(0);
        this.c = false;
        this.c = null;
        this.d = false;
        this.i = false;
        this.j = false;
        this.r = 0;
        this.g = 0;
        this.s = 0;
        this.h = 0;
        this.a(n, au, n2, b);
    }
    
    private void a(final int b, final au a, final int e, final boolean b2) {
        if (b2) {
            this.q = 256;
        }
        this.a = a;
        this.e = e;
        if (this.e == 0) {
            this.e = 5;
        }
        if (a != null) {
            this.a = a.a;
        }
        this.a(b);
        if (this.b == 1) {
            this.b = b;
            if (this.e == 0) {
                this.b();
            }
            this.a(1);
        }
        if (a != null && a.a != null) {
            a.a.b("");
        }
    }
    
    public an(final int n, final au au, final int n2) {
        this.a = 0;
        this.a = new int[8];
        this.a = "";
        this.i = 300;
        this.j = -1;
        this.k = -1;
        this.a = null;
        this.b = 0;
        this.l = 0;
        this.m = 0;
        this.n = 1;
        this.o = 0;
        this.p = 0;
        this.a = false;
        this.c = 0;
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.b = "Please wait while ROM Server is contacted...";
        this.e = false;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.f = false;
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.d = 0;
        this.e = 0;
        this.a = new boolean[] { false, false, false, false, false };
        this.k = "";
        this.a = null;
        this.g = false;
        this.b = null;
        this.a = new Font("Dialog", 0, 13);
        this.b = new Font("Dialog", 0, 10);
        this.c = new Font("Dialog", 1, 11);
        this.d = new Font("Dialog", 0, 11);
        this.h = false;
        this.b = true;
        this.q = 0;
        this.f = 60;
        this.a = new Cursor(12);
        this.b = new Cursor(3);
        this.c = new Cursor(0);
        this.c = false;
        this.c = null;
        this.d = false;
        this.i = false;
        this.j = false;
        this.r = 0;
        this.g = 0;
        this.s = 0;
        this.h = 0;
        this.a(n, au, n2, false);
    }
    
    private void a(final int b) {
        this.h = 0;
        this.r = 4 / this.a.a.j;
        if (this.a() == this.a) {
            this.a(this.c);
        }
        this.i = 300;
        this.j = -1;
        this.k = -1;
        if ((this.b = b) == 6) {
            this.n = 0;
        }
        this.l = this.a.a.a;
        this.m = this.a.a.a;
        if (this.m < 0) {
            this.m = 0;
        }
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.b == 2) {
            if (this.l > 3) {
                this.l = 3;
            }
            this.a = null;
            this.f = 60;
            this.h = 0;
        }
        else if (this.b == 24) {
            this.o = 0;
        }
        else if (this.b == 22) {
            if (this.a.a.h == y.b) {
                this.p = 0;
            }
            else if (this.a.a.h == y.c) {
                this.p = 1;
            }
            else if (this.a.a.d == 1) {
                this.p = 0;
            }
            else {
                this.p = 1;
            }
            if (!this.a.a.canAppletSaveAnimGif() && !this.a.a.canAppletSaveState()) {
                this.p = -1;
            }
            else if (this.p == 1 && !this.a.a.canAppletSaveState()) {
                this.p = -1;
            }
            else if (this.p == 0 && !this.a.a.canAppletSaveAnimGif()) {
                this.p = -1;
            }
        }
        else if (this.b == 19) {
            this.p = 0;
        }
        else if (this.b == 20) {
            this.p = 0;
        }
        else if (this.b == 10) {
            this.a.a.d();
        }
        else if (this.b == 18) {
            this.c(0);
        }
        else if (this.b == 7) {
            this.a[0] = this.a.a.l;
            this.a[1] = this.a.a.m;
            this.a[2] = this.a.a.n;
            this.a[3] = this.a.a.o;
            this.a[4] = this.a.a.p;
            this.a[5] = this.a.a.q;
            this.a[6] = this.a.a.r;
            this.a[7] = this.a.a.s;
            this.p = 0;
        }
        else if (this.b == 13) {
            this.p = 0;
            this.f = this.a.a.r;
            this.g = this.a.a.b;
            this.h = this.a.a.d;
            this.i = this.a.a.e;
            this.j = this.a.a.c;
        }
        else if (this.b == 3) {
            this.a = null;
            this.f = 60;
            this.h = 0;
        }
        else if (this.b == 16) {
            this.s = 0;
        }
        else if (this.b == 14) {
            this.c();
        }
        this.c = new ArrayList();
        if (this.b == 6 || this.b == 4 || this.b == 5) {
            this.c.add(new Object(this, 33, 130, 90, 18, 89) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 133, 130, 90, 18, 78) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 22) {
            this.c.add(new Object(this, 33, 130, 90, 18, 65) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 133, 130, 90, 18, 75) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 20) {
            this.c.add(new Object(this, 20, 110, 214, 18, 49) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 132, 214, 18, 50) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 154, 214, 18, 51) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 13) {
            this.c.add(new Object(this, 20, 84, 214, 18, 49) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 106, 214, 18, 50) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 128, 214, 18, 51) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 150, 214, 18, 52) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 172, 214, 18, 53) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 9) {
            this.c.add(new Object(this, 20, 79, 100, 18, 49) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 101, 100, 18, 50) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 123, 100, 18, 51) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 145, 100, 18, 52) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 3 || this.b == 2) {
            this.c.add(new Object(this, 20, 79, 100, 18, 49) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 101, 100, 18, 50) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 123, 100, 18, 51) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 145, 100, 18, 52) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, 167, 100, 18, 53) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (this.b == 16) {
            this.s = 1;
        }
        else if (this.b == 1) {
            for (int i = 0; i < this.e; ++i) {
                this.c.add(new Object(this, 20, 84 + i * 22, 214, 18, (int)(49 + i)) {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public int e;
                    
                    {
                        this.a = 0;
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.c = c;
                        this.d = d;
                        this.b = b;
                        this.a = a;
                        this.e = e;
                    }
                    
                    public final boolean a(final int n, final int n2) {
                        return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                    }
                });
            }
        }
        else if (this.b != 11 || this.a.a.a == null) {
            if (this.b == 23) {
                this.c.add(new Object(this, 50, 142, 156, 15, 87) {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public int e;
                    
                    {
                        this.a = 0;
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.c = c;
                        this.d = d;
                        this.b = b;
                        this.a = a;
                        this.e = e;
                    }
                    
                    public final boolean a(final int n, final int n2) {
                        return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                    }
                });
                this.c.add(new Object(this, 100, 177, 56, 15, 10) {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public int e;
                    
                    {
                        this.a = 0;
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.c = c;
                        this.d = d;
                        this.b = b;
                        this.a = a;
                        this.e = e;
                    }
                    
                    public final boolean a(final int n, final int n2) {
                        return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                    }
                });
            }
            else if (this.b == 12) {
                this.c.add(new Object(this, 100, 162, 56, 15, 10) {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public int e;
                    
                    {
                        this.a = 0;
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.c = c;
                        this.d = d;
                        this.b = b;
                        this.a = a;
                        this.e = e;
                    }
                    
                    public final boolean a(final int n, final int n2) {
                        return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                    }
                });
            }
            else if (this.b == 17) {
                this.c.add(new Object(this, 100, 134, 56, 15, 10) {
                    public int a;
                    public int b;
                    public int c;
                    public int d;
                    public int e;
                    
                    {
                        this.a = 0;
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.c = c;
                        this.d = d;
                        this.b = b;
                        this.a = a;
                        this.e = e;
                    }
                    
                    public final boolean a(final int n, final int n2) {
                        return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                    }
                });
            }
            else if (this.b == 7) {
                for (int j = 0; j < 8; ++j) {
                    this.c.add(new Object(this, 20, 69 + j * 18, 214, 18, (int)(49 + j)) {
                        public int a;
                        public int b;
                        public int c;
                        public int d;
                        public int e;
                        
                        {
                            this.a = 0;
                            this.b = 0;
                            this.c = 0;
                            this.d = 0;
                            this.e = 0;
                            this.c = c;
                            this.d = d;
                            this.b = b;
                            this.a = a;
                            this.e = e;
                        }
                        
                        public final boolean a(final int n, final int n2) {
                            return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                        }
                    });
                }
            }
        }
        this.h = false;
    }
    
    private Cursor a() {
        return this.a.a.getCursor();
    }
    
    private void a(final Cursor cursor) {
        this.a.a.setCursor(cursor);
    }
    
    public final void a(final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < this.c.size(); ++i) {
            if (((a)this.c.get(i)).a(n, n2)) {
                b = true;
            }
        }
        if (b) {
            this.a(new Cursor(12));
            return;
        }
        this.a(new Cursor(0));
    }
    
    public final boolean a(final int n, final int n2, final boolean b) {
        for (int i = 0; i < this.c.size(); ++i) {
            if (((a)this.c.get(i)).a(n, n2)) {
                final boolean a;
                if (a = this.a(this.c.get(i).e, '\0', b)) {
                    if (this.a.a.g) {
                        this.a(new Cursor(13));
                    }
                    else {
                        this.a(new Cursor(0));
                    }
                }
                return a;
            }
        }
        return false;
    }
    
    public final boolean a(final KeyEvent keyEvent) {
        final boolean a;
        if (a = this.a(keyEvent.getKeyCode(), keyEvent.getKeyChar(), false)) {
            if (this.a.a.g) {
                this.a(new Cursor(13));
            }
            else {
                this.a(new Cursor(0));
            }
        }
        this.a.a.a.a(true);
        return a;
    }
    
    private boolean a(final int n, final char c, final boolean c2) {
        if (n == 255) {
            this.a(false);
            return false;
        }
        this.c = c2;
        switch (this.b) {
            case 21: {
                return false;
            }
            case 24: {
                return this.b(n);
            }
            case 14: {
                return this.f(n);
            }
            case 1: {
                return this.g(n);
            }
            case 13: {
                return this.a(n, c);
            }
            case 16: {
                return this.h(n);
            }
            case 9: {
                return this.i(n);
            }
            case 7: {
                return this.j(n);
            }
            case 22: {
                return this.l(n);
            }
            case 6: {
                return this.k(n);
            }
            case 2: {
                return this.o(n);
            }
            case 3: {
                return this.p(n);
            }
            case 4: {
                return this.n(n);
            }
            case 5: {
                return this.m(n);
            }
            case 15: {
                return n == 27 || n == 10;
            }
            case 10: {
                return true;
            }
            case 8: {
                return false;
            }
            case 23: {
                if (n == 87) {
                    try {
                        this.a.a.getAppletContext().showDocument(new URL("http://" + O.a + "/"), "_top");
                    }
                    catch (Exception ex) {}
                }
                return n == 10 || n == 27;
            }
            case 11:
            case 12: {
                if (this.a.a.a != null) {
                    if (n != 10) {
                        if (!c2) {
                            return this.b == 12 && (n == 10 || n == 27 || c2);
                        }
                    }
                    try {
                        this.a.a.getAppletContext().showDocument(new URL(this.a.a.a), "_top");
                    }
                    catch (Exception ex2) {}
                }
                return this.b == 12 && (n == 10 || n == 27 || c2);
            }
            case 18: {
                return this.c(n);
            }
            case 20: {
                return this.d(n);
            }
            case 19: {
                return this.e(n);
            }
            case 17: {
                return n == 10 || n == 27 || c2;
            }
            default: {
                return true;
            }
        }
    }
    
    private boolean b(final int n) {
        if (n == 27) {
            return true;
        }
        if (n == 37) {
            this.o = 0;
        }
        else if (n == 39) {
            this.o = 1;
        }
        else if (n == 1) {
            this.o = 0;
            this.c = true;
        }
        else if (n == 2) {
            this.o = 1;
            this.c = true;
        }
        if (n == 10 || this.c) {
            if (this.o == 0) {
                this.a.a.a.h();
            }
            return true;
        }
        return false;
    }
    
    private boolean c(final int n) {
        if (n != 0) {
            if (n == 37 && this.r < this.a.a.a.a - 1) {
                ++this.r;
                if (this.r >= this.a.a.a.a()) {
                    this.r = this.a.a.a.a() - 1;
                }
            }
            else if (n == 39 && this.r > 0) {
                --this.r;
                if (this.r < 0) {
                    this.r = 0;
                }
            }
            else {
                if (n == 27) {
                    return true;
                }
                if (n == 10) {
                    this.a.a.h = this.a.a.a.a - this.r - 1;
                    return this.a.a.h = true;
                }
                return false;
            }
        }
        try {
            this.c = this.a(this.a.a.a.a(this.a.a.a.a - this.r - 1));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private boolean d(int n) {
        int n2 = 1;
        final boolean b = this.a.a.h == y.a;
        final boolean b2 = this.a.a.d == 1;
        if (this.p > 0 && n == 38) {
            --this.p;
        }
        else if (b2 && this.p < 2 && n == 40) {
            ++this.p;
        }
        else if (n >= 49 && (n < 52 & b)) {
            if (n > 49 && this.a.a.d == 1) {
                this.p = n - 49;
            }
            else {
                this.c = false;
                this.p = 0;
            }
        }
        else if (n == 37) {
            if (this.p != 0 || b) {
                n2 = -1;
                n = 10;
            }
        }
        else if (n == 39 && (this.p != 0 || b)) {
            n2 = 1;
            n = 10;
        }
        if (n == 10 || this.c) {
            if (this.p == 0 && b) {
                if (this.a.a.d == 0) {
                    this.a.a.d = 1;
                }
                else {
                    this.a.a.d = 0;
                }
            }
            else if (this.p == 1) {
                this.a.a.h = !this.a.a.h;
            }
            else if (this.p == 2) {
                final y a = this.a.a;
                a.e += n2;
                if (this.a.a.e > 15) {
                    this.a.a.e = 5;
                }
                if (this.a.a.e < 5) {
                    this.a.a.e = 15;
                }
            }
        }
        else if (n == 27) {
            return true;
        }
        return false;
    }
    
    private boolean e(final int n) {
        if (this.p > 0 && n == 38) {
            --this.p;
        }
        else if (this.p < 1 && n == 40) {
            ++this.p;
        }
        else if (this.p == 0 && n == 37) {
            if (this.a.a.i > 5) {
                final y a = this.a.a;
                --a.i;
            }
            else {
                this.a.a.i = 0;
                this.a.a.k = false;
            }
            this.d = true;
        }
        else if (this.p == 0 && n == 39) {
            if (this.a.a.i < 300) {
                final y a2 = this.a.a;
                ++a2.i;
                if (this.a.a.i < 5) {
                    this.a.a.i = 5;
                }
                this.a.a.k = true;
                this.d = true;
            }
        }
        else if (this.p == 1 && n == 37) {
            if (this.a.a.j > 1) {
                final y a3 = this.a.a;
                --a3.j;
                this.d = true;
            }
        }
        else if (this.p == 1 && n == 39 && this.a.a.j < 5) {
            final y a4 = this.a.a;
            ++a4.j;
            this.d = true;
        }
        if (n == 10 || n == 27) {
            if (this.d) {
                this.d = false;
                this.a.a.a.a();
            }
            return true;
        }
        return false;
    }
    
    private boolean f(final int n) {
        if (n == 38 && this.p > 0) {
            --this.p;
            if (this.p < this.h) {
                --this.h;
            }
        }
        else if (n == 40 && this.p < this.a.size() - 1) {
            ++this.p;
            if (this.p >= this.h + 5) {
                ++this.h;
            }
        }
        if (n >= 49 && this.h + n - 49 < this.a.size()) {
            this.p = this.h + n - 49;
        }
        else {
            this.c = false;
        }
        if (n == 27) {
            return true;
        }
        if ((n == 10 || this.c) && this.p < this.b.size()) {
            try {
                this.d();
                this.a.a.a(false);
                new w(this.a, this.a.a, this.b.get(this.p), this.a.get(this.p)).start();
                this.d = 0;
                return false;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    private void d() {
        this.a.a.a();
    }
    
    private boolean g(final int n) {
        try {
            if (n == 38 || n == 40) {
                int p = this.p;
                int p2 = this.p;
                for (int i = this.p - 1; i >= 0; --i) {
                    if (!this.a[i]) {
                        p = i;
                        break;
                    }
                }
                for (int j = this.p + 1; j < this.a.length; ++j) {
                    if (!this.a[j]) {
                        p2 = j;
                        break;
                    }
                }
                if (n == 38 && p >= 0) {
                    this.p = p;
                }
                if (n == 40 && p2 < this.e) {
                    this.p = p2;
                }
            }
            if (n >= 49 && n < 49 + this.a.length && !this.a[n - 49]) {
                this.p = n - 49;
                this.c = true;
            }
            else {
                this.c = false;
            }
            if (n == 27) {
                return true;
            }
            if (n == 10 || this.c) {
                switch (this.d * 10 + this.p) {
                    case 0: {
                        final an an;
                        (an = new an(1, this.a, 6)).a = new boolean[] { false, false, this.a.a == null, false, false, this.a.a.e | !this.a.a.canAppletSaveAnimGif() | this.a.a.g < 1024 };
                        an.d = 1;
                        this.a.a.a(an);
                        break;
                    }
                    case 1: {
                        final an an2 = new an(1, this.a, 5);
                        final boolean b = !this.a.a();
                        an2.a = new boolean[] { b | this.a() | !this.a.a.canAppletSaveState() | this.a.b, b | this.a() | !this.a.a.canAppletLoadState() | this.a.b, b, b | this.a() | !this.a.a.f, b | !this.a.a.a.d | this.a() | !this.a.a.f };
                        an2.d = 2;
                        this.a.a.a(an2);
                        break;
                    }
                    case 2: {
                        final an an3 = new an(1, this.a, 6);
                        final boolean b2 = !this.a.a();
                        an3.a = new boolean[] { b2 | this.a() | this.a.a.p, b2 | this.a() | this.a.a.a == null, b2 | this.a.a.f | !this.a.a.canAppletSaveState(), b2 | !this.a.a.canAppletSaveShot(), b2 || !this.a.a.k || this.a.a.a.a < 5 || this.a() || this.a.a.n };
                        an3.d = 3;
                        this.a.a.a(an3);
                        break;
                    }
                    case 3: {
                        final an an4;
                        (an4 = new an(1, this.a, 1)).a = new boolean[] { true, true };
                        an4.d = 4;
                        this.a.a.a(an4);
                        break;
                    }
                    case 4: {
                        this.a.a.a(new an(15, this.a));
                        break;
                    }
                    case 5: {
                        return this.a(true);
                    }
                    case 10: {
                        this.a.a.a(new an(7, this.a));
                        break;
                    }
                    case 11: {
                        this.a.a.a(new an(16, this.a));
                        return false;
                    }
                    case 12: {
                        if (this.a.a == null) {
                            return true;
                        }
                        this.a.a.g = !this.a.a.g;
                        return false;
                    }
                    case 13: {
                        this.a.a.g = !this.a.a.g;
                        return false;
                    }
                    case 14: {
                        this.a.a.a(new an(19, this.a));
                        return false;
                    }
                    case 15: {
                        this.a.a.a(new an(20, this.a));
                        return false;
                    }
                    case 20: {
                        final an an5;
                        (an5 = new an(2, this.a)).a = null;
                        an5.f = 60;
                        this.a.a.a(an5);
                        break;
                    }
                    case 21: {
                        final an an6;
                        (an6 = new an(3, this.a)).a = null;
                        an6.f = 60;
                        this.a.a.a(an6);
                        break;
                    }
                    case 22: {
                        final boolean b3;
                        if (b3 = !this.a.a.b) {
                            this.a.a.a("Game paused...", 86400, 16);
                        }
                        else {
                            this.a.a.a("Game resumed...", 6, 16);
                        }
                        this.a.a.a(b3);
                        this.d();
                        return true;
                    }
                    case 23: {
                        this.a.a.a(new an(4, this.a));
                        break;
                    }
                    case 24: {
                        this.a.a.a(new an(5, this.a));
                        break;
                    }
                    case 30: {
                        this.a.a.a(new an(9, this.a));
                        this.m = 0;
                        break;
                    }
                    case 31: {
                        if (this.a.a.a != null) {
                            this.a.a.a.b = !this.a.a.a.b;
                        }
                        this.d();
                        return true;
                    }
                    case 32: {
                        if (this.a.a.f) {
                            return false;
                        }
                        if (!this.a.a.e) {
                            this.a.a.a(new an(22, this.a));
                            break;
                        }
                        this.a.a.b("Finished Recording Movie...");
                        this.a.a.b();
                        this.d();
                        return true;
                    }
                    case 33: {
                        if (!this.a.a.canAppletSaveShot()) {
                            return false;
                        }
                        this.a.a.a(new an(6, this.a));
                        this.a.a.a(true);
                        break;
                    }
                    case 34: {
                        this.a.a.a(new an(18, this.a));
                        break;
                    }
                    case 40: {
                        this.a.a.a(new an(13, this.a));
                        break;
                    }
                    case 41: {
                        final an an7;
                        (an7 = new an(14, this.a)).c();
                        this.a.a.a(an7);
                        break;
                    }
                }
                return false;
            }
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean a(final boolean b) {
        if (b) {
            final an an;
            (an = new an(12, this.a)).c = 8;
            an.a("Visit " + O.a + " for NESCafe");
        }
        try {
            this.a.a.getAppletContext().showDocument(new URL("http://" + O.a + "/"), "_new");
        }
        catch (Exception ex) {}
        return false;
    }
    
    private boolean h(final int n) {
        if (n == 38 && this.p > 0) {
            --this.p;
        }
        if (n == 40 && this.p < 6) {
            ++this.p;
        }
        if (n >= 49 && n <= 55) {
            if (n >= 49) {
                this.p = n - 49;
            }
            else {
                this.c = false;
                this.p = 0;
            }
        }
        if (n == 39 || n == 10) {
            if (this.p == 3) {
                this.a.a.a.a = !this.a.a.a.a;
            }
            else if (this.p == 4) {
                this.a.a.a.l();
            }
            else if (this.p == 5) {
                this.a.a.a.j();
            }
            else if (this.p == 6) {
                this.a.a.a.n();
            }
        }
        else if (n == 37) {
            if (this.p == 3) {
                this.a.a.a.a = !this.a.a.a.a;
            }
            else if (this.p == 4) {
                this.a.a.a.m();
            }
            else if (this.p == 5) {
                this.a.a.a.k();
            }
            else if (this.p == 6) {
                this.a.a.a.n();
            }
        }
        if (n == 10 || this.c) {
            if (this.p == 0) {
                this.a.a.a.a();
            }
            else if (this.p == 1) {
                if (this.a.a.a.c) {
                    this.a.a.a.e();
                }
                else {
                    this.a.a.a.f();
                }
            }
            else if (this.p == 2) {
                if (this.a.a.f == 1) {
                    this.a.a.f = 0;
                }
                else {
                    this.a.a.f = 1;
                }
            }
        }
        if (n == 27) {
            this.p = 0;
            return true;
        }
        return false;
    }
    
    private boolean a(final int n, final char c) {
        if (!this.a) {
            if (n == 38 && this.p > 0) {
                --this.p;
            }
            if (n == 40 && this.p < 4 && this.f) {
                ++this.p;
            }
            if (n >= 49 && n < 54) {
                if (n > 49 && this.f) {
                    this.p = n - 49;
                }
                else {
                    this.c = false;
                    this.p = 0;
                }
            }
            if (n == 10 || this.c) {
                if (this.p == 0) {
                    this.f = !this.f;
                }
                else {
                    this.a = true;
                    switch (this.p) {
                        case 1: {
                            this.k = this.g;
                            break;
                        }
                        case 2: {
                            this.k = this.j;
                            break;
                        }
                        case 3: {
                            this.k = this.h;
                            break;
                        }
                        case 4: {
                            this.k = this.i;
                            break;
                        }
                    }
                }
            }
            if (n == 27) {
                this.a.a.r = this.f;
                this.a.a.b = this.g;
                this.a.a.c = this.j;
                this.a.a.d = this.h;
                this.a.a.e = this.i;
                this.p = 0;
                return true;
            }
        }
        else {
            if (n == 27) {
                this.k = "";
                return this.a = false;
            }
            if (n == 10) {
                switch (this.p) {
                    case 1: {
                        this.g = this.k;
                        break;
                    }
                    case 2: {
                        this.j = this.k;
                        break;
                    }
                    case 3: {
                        this.h = this.k;
                        break;
                    }
                    case 4: {
                        this.i = this.k;
                        break;
                    }
                }
                return this.a = false;
            }
            if (this.p == 1) {
                if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.".indexOf(c) >= 0) {
                    this.k += c;
                }
                else if (n == 8 && this.k.length() > 0) {
                    this.k = this.k.substring(0, this.k.length() - 1);
                }
            }
            else if (this.p == 3 || this.p == 4) {
                if (" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!\"£$%^&*()-=+-_]}[{'@#~;:><,/?\\|".indexOf(c) >= 0) {
                    this.k += c;
                }
                else if (n == 8 && this.k.length() > 0) {
                    this.k = this.k.substring(0, this.k.length() - 1);
                }
            }
            else if (this.p == 2) {
                if ("0123456789".indexOf(c) >= 0) {
                    this.k += c;
                }
                else if (n == 8 && this.k.length() > 0) {
                    this.k = this.k.substring(0, this.k.length() - 1);
                }
            }
            int n2 = 0;
            switch (this.p) {
                case 1: {
                    n2 = 24;
                    break;
                }
                case 2: {
                    n2 = 4;
                    break;
                }
                case 3: {
                    n2 = 16;
                    break;
                }
                case 4: {
                    n2 = 16;
                    break;
                }
            }
            if (this.k.length() > n2) {
                this.k = this.k.substring(0, n2);
            }
        }
        return false;
    }
    
    private boolean i(final int n) {
        this.c = "";
        if (!this.a) {
            if (n == 38 && this.m > 0) {
                --this.m;
            }
            if (n == 40 && this.m < 3) {
                ++this.m;
            }
            if (n >= 49 && n <= 52) {
                this.m = n - 49;
            }
            else {
                this.c = false;
            }
            if (n == 10 || this.c) {
                this.k = this.a.a.a.a(this.m);
                this.a = true;
                return false;
            }
            if (n == 127) {
                this.a.a.b = this.a.a.a.a(this.m);
            }
            else if (n == 27) {
                return true;
            }
            return false;
        }
        else {
            if (n == 27) {
                this.k = "";
                return this.a = false;
            }
            if (n != 10) {
                switch (n) {
                    case 65:
                    case 69:
                    case 71:
                    case 73:
                    case 75:
                    case 76:
                    case 78:
                    case 79:
                    case 80:
                    case 83:
                    case 84:
                    case 85:
                    case 86:
                    case 88:
                    case 89:
                    case 90: {
                        if (this.k.length() < 8) {
                            this.k += (char)n;
                            break;
                        }
                        break;
                    }
                }
                if (n == 8 && this.k.length() > 0) {
                    this.k = this.k.substring(0, this.k.length() - 1);
                }
                return false;
            }
            if (this.k.equals("")) {
                this.a.a.a.a(this.m);
                return this.a = false;
            }
            final int a;
            if ((a = this.a.a.a.a(this.k, this.m)) >= 0) {
                this.a = false;
                this.a.a.b = (a == 1);
                return false;
            }
            this.c = "Invalid Game Genie Code '" + this.k + "'";
            return false;
        }
    }
    
    private boolean j(int n) {
        if (this.a) {
            if (n != 127) {
                this.a = "";
                for (int i = 0; i < 8; ++i) {
                    if (n == this.a[i] && i != this.p) {
                        this.a = "The key '" + this.a(n) + "' is already assigned";
                        return false;
                    }
                }
                if (n == 27) {
                    this.p = 0;
                    return this.a = false;
                }
                if (this.a(n)) {
                    final an an;
                    (an = new an(12, this.a)).c = 8;
                    an.a("You attempted to assign a reserved key.", "Please pick an alternative key on your keyboard.");
                    this.a.a.a(an);
                    return false;
                }
            }
            else {
                n = 0;
            }
            this.a[this.p] = n;
            switch (this.p) {
                case 0: {
                    this.a.a.l = this.a[0];
                    this.a.a.b = this.a[0];
                    break;
                }
                case 1: {
                    this.a.a.m = this.a[1];
                    this.a.a.c = this.a[1];
                    break;
                }
                case 2: {
                    this.a.a.n = this.a[2];
                    this.a.a.d = this.a[2];
                    break;
                }
                case 3: {
                    this.a.a.o = this.a[3];
                    this.a.a.e = this.a[3];
                    break;
                }
                case 4: {
                    this.a.a.p = this.a[4];
                    this.a.a.f = this.a[4];
                    break;
                }
                case 5: {
                    this.a.a.q = this.a[5];
                    this.a.a.g = this.a[5];
                    break;
                }
                case 6: {
                    this.a.a.r = this.a[6];
                    this.a.a.h = this.a[6];
                    break;
                }
                case 7: {
                    this.a.a.s = this.a[7];
                    this.a.a.i = this.a[7];
                    break;
                }
            }
            return this.a = false;
        }
        if (n == 38 && this.p > 0) {
            --this.p;
        }
        if (n == 40 && this.p < 7) {
            ++this.p;
        }
        if (n == 27) {
            return true;
        }
        if (n >= 49 && n < 57) {
            this.p = n - 49;
        }
        else {
            this.c = false;
        }
        if (n == 10 || this.c) {
            this.a = true;
        }
        if (n == 127) {
            this.a[this.p] = 0;
            switch (this.p) {
                case 0: {
                    this.a.a.l = this.a[0];
                    this.a.a.b = this.a[0];
                    break;
                }
                case 1: {
                    this.a.a.m = this.a[1];
                    this.a.a.c = this.a[1];
                    break;
                }
                case 2: {
                    this.a.a.n = this.a[2];
                    this.a.a.d = this.a[2];
                    break;
                }
                case 3: {
                    this.a.a.o = this.a[3];
                    this.a.a.e = this.a[3];
                    break;
                }
                case 4: {
                    this.a.a.p = this.a[4];
                    this.a.a.f = this.a[4];
                    break;
                }
                case 5: {
                    this.a.a.q = this.a[5];
                    this.a.a.g = this.a[5];
                    break;
                }
                case 6: {
                    this.a.a.r = this.a[6];
                    this.a.a.h = this.a[6];
                    break;
                }
                case 7: {
                    this.a.a.s = this.a[7];
                    this.a.a.i = this.a[7];
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean k(final int n) {
        if (n == 89) {
            this.n = 0;
            this.c = true;
        }
        if (n == 78) {
            this.n = 1;
            this.c = true;
        }
        if (n == 37 && this.n == 1) {
            this.n = 0;
        }
        else if (n == 39 && this.n == 0) {
            this.n = 1;
        }
        else {
            if (n == 27) {
                if (this.d != 0) {
                    this.d = 3;
                }
                return true;
            }
            if (n == 10 || this.c || n == 13) {
                if (this.n == 1 && n != 13) {
                    return true;
                }
                this.a.a.a.h();
                this.d();
                return true;
            }
        }
        return false;
    }
    
    private boolean l(final int n) {
        final int p = this.p;
        final boolean b = this.a.a.h == y.a;
        if (n == 65 && b) {
            this.p = 0;
        }
        if (n == 75 && b) {
            this.p = 1;
        }
        if (n == 37 && this.p == 1 && b) {
            this.p = 0;
        }
        else if (n == 39 && this.p == 0 && b) {
            this.p = 1;
        }
        if (this.p == 0) {
            if (!this.a.a.canAppletSaveAnimGif()) {
                this.p = p;
            }
        }
        else if (this.a.a.d == 0 && !this.a.a.canAppletSaveState()) {
            this.p = p;
        }
        if (n == 27) {
            if (this.d != 0) {
                this.d = 3;
            }
            return true;
        }
        if (n == 10 || this.c || n == 13) {
            if (this.p == 0) {
                this.a.a.d = 1;
            }
            else if (this.p == 1) {
                this.a.a.d = 0;
            }
            this.d();
            this.a.a.a();
            this.d = 0;
            return true;
        }
        return false;
    }
    
    private boolean m(final int n) {
        if (n == 89) {
            this.n = 0;
            this.c = true;
        }
        else if (n == 78) {
            this.n = 1;
            this.c = true;
        }
        else if (n == 37 && this.n == 1) {
            this.n = 0;
        }
        else if (n == 39 && this.n == 0) {
            this.n = 1;
        }
        else if (n == 27) {
            return true;
        }
        if (n != 10 && !this.c && n != 13) {
            return false;
        }
        if (this.n == 1 && n != 13) {
            return true;
        }
        this.d = 0;
        if (this.a.a.b) {
            this.a.a.a(false);
        }
        if (this.a.a.loadStateOnStartup != null) {
            this.a.a.a = true;
            this.a.a.b = false;
            return this.a.a.loadStateOnStartupTrigger = true;
        }
        this.a.a.a();
        this.a.b();
        return true;
    }
    
    private boolean n(final int n) {
        if (n == 89) {
            this.n = 0;
            this.c = true;
        }
        else if (n == 78) {
            this.n = 1;
            this.c = true;
        }
        else if (n == 37 && this.n == 1) {
            this.n = 0;
        }
        else if (n == 39 && this.n == 0) {
            this.n = 1;
        }
        else if (n == 27) {
            return true;
        }
        if (n != 10 && !this.c && n != 13) {
            return false;
        }
        if (this.n == 1 && n != 13) {
            return true;
        }
        if (this.a.a.b) {
            this.a.a.a(false);
        }
        this.d();
        if (this.a.a.loadStateOnStartup != null) {
            this.a.a.a = true;
            this.a.a.b = false;
            return this.a.a.loadStateOnStartupTrigger = true;
        }
        this.a.b();
        return true;
    }
    
    private boolean o(final int n) {
        if (this.j) {
            if (n == 27) {
                this.j = false;
            }
            else if (n == 37 && this.p == 1) {
                this.p = 0;
            }
            else if (n == 39 && this.p == 0) {
                this.p = 1;
            }
            else if (n == 10) {
                if (this.p == 0) {
                    this.a.a.a = this.l;
                    this.a.a.b = true;
                    this.a.a.a = false;
                    this.d();
                    this.d = 0;
                    return true;
                }
                this.j = false;
                final int l = this.l;
                this.h = false;
                this.a(this.b);
                this.l = l;
            }
            return false;
        }
        if (n == 38 && this.l > 0) {
            --this.l;
            this.a = null;
            this.f = 60;
            if (this.l < this.h) {
                --this.h;
            }
        }
        else if (n == 40 && this.l < 4) {
            ++this.l;
            this.a = null;
            this.f = 60;
            if (this.l >= this.h + 5) {
                ++this.h;
            }
        }
        else if (n >= 49 && n <= 53) {
            if (this.l != this.h + n - 49) {
                this.l = this.h + n - 49;
                this.a = null;
                this.f = 60;
            }
        }
        else {
            this.c = false;
        }
        if (n == 10 || this.c) {
            if (this.f > 0) {
                return false;
            }
            if (this.b) {
                this.a.a.a = this.l;
                this.a.a.b = true;
                this.a.a.a = false;
                this.d();
                this.d = 0;
                return true;
            }
            this.j = true;
            this.p = 1;
            (this.c = new ArrayList()).add(new Object(this, 33, 140, 90, 18, 37) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 133, 140, 90, 18, 39) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (n == 27) {
            return true;
        }
        return false;
    }
    
    private boolean p(final int n) {
        if (this.i) {
            if (n == 10 || n == 27) {
                final int m = this.m;
                this.i = false;
                this.h = false;
                this.a(this.b);
                this.m = m;
            }
            return false;
        }
        if (n == 38 && this.m > 0) {
            --this.m;
            this.a = null;
            this.f = 60;
            if (this.m < this.h) {
                --this.h;
            }
        }
        else if (n == 40 && this.m < 4) {
            ++this.m;
            this.a = null;
            this.f = 60;
            if (this.m >= this.h + 5) {
                ++this.h;
            }
        }
        else if (n >= 49 && n <= 53) {
            if (this.m != this.h + n - 49) {
                this.m = this.h + n - 49;
                this.a = null;
                this.f = 60;
            }
        }
        else {
            this.c = false;
        }
        if (n == 10 || this.c) {
            if (this.f > 0) {
                return false;
            }
            if (!this.b) {
                this.a.a.a = this.m;
                this.d = 0;
                this.a.a.b = false;
                this.a.a.a = true;
                this.d();
                return true;
            }
            this.i = true;
            (this.c = new ArrayList()).add(new Object(this, 83, 140, 90, 18, 10) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
        else if (n == 27) {
            return true;
        }
        return false;
    }
    
    public final boolean a(final Graphics graphics, final Image image) {
        if (this.a() == this.b) {
            this.a(this.c);
        }
        if (this.b == null) {
            this.b = this.a.a.createImage(256, 240);
            if (this.b == null) {
                return false;
            }
        }
        final Graphics graphics2 = this.b.getGraphics();
        switch (this.b) {
            case 17: {
                if (image != null) {
                    graphics2.drawImage(image, 0, 0, Color.black, null);
                }
                graphics2.setColor(new Color(0, 0, 0));
                graphics2.fillRect(0, 0, 256, 240);
                if (this.c.startsWith("FAILURE:")) {
                    this.a(graphics2, 0, 70, 256, 90, "Failed to Load IPS Patch");
                    graphics2.setColor(Color.black);
                    aK.a(graphics2, this.c.substring(9), this.b, 121);
                }
                else {
                    this.a(graphics2, 0, 70, 256, 90, "ROM Patched with IPS");
                    graphics2.setColor(Color.black);
                    aK.a(graphics2, this.c, this.b, 121);
                }
                graphics2.setColor(Color.black);
                this.a(graphics2, 100, 134, 56, 15, Color.white);
                graphics2.setColor(Color.black);
                aK.a(graphics2, "Ok", this.b, 145);
                break;
            }
            case 21: {
                if (image != null) {
                    graphics2.drawImage(image, 0, 0, Color.black, null);
                }
                graphics2.setColor(new Color(0, 0, 0, 128));
                graphics2.fillRect(0, 0, 256, 240);
                this.a(graphics2, 0, 70, 256, 100, "Broadcasting data to Server...");
                graphics2.setColor(Color.black);
                aK.a(graphics2, "Please wait whilst NESCafe talks with the server...", this.b, 121);
                aK.a(graphics2, this.c, this.b, 138);
                if (this.a < 100) {
                    graphics2.setColor(Color.white);
                    graphics2.drawRect(46, 148, 164, 12);
                    final int n = (int)(this.a / 100.0 * 160);
                    graphics2.setColor(new Color(0, 0, 255));
                    graphics2.fillRect(48, 150, n, 8);
                    graphics2.setColor(new Color(0, 0, 128));
                    graphics2.drawRect(48, 150, n, 8);
                    break;
                }
                break;
            }
            case 23: {
                graphics2.setColor(new Color(0, 0, 0, 128));
                graphics2.fillRect(0, 0, 256, 240);
                this.a(graphics2, 100, 147, 56, 15, Color.white);
                this.a(graphics2, 0, 55, 256, 150, "Welcome to NESCafe 1.02");
                graphics2.setColor(Color.black);
                aK.a(graphics2, "NESCafe is a free emulator for the Nintendo", this.b, 106);
                aK.a(graphics2, "written by David de Niese. Instructions on using", this.b, 120);
                aK.a(graphics2, "NESCafe can be found at the NESCafe website", this.b, 134);
                graphics2.setColor(new Color(0, 0, 128));
                aK.a(graphics2, "http://" + O.a + "/", this.b, 154);
                graphics2.setColor(Color.black);
                this.a(graphics2, 100, 177, 56, 15, Color.white);
                graphics2.setColor(Color.black);
                aK.a(graphics2, "Play", this.b, 188);
                break;
            }
            case 11:
            case 12: {
                if (image != null) {
                    graphics2.drawImage(image, 0, 0, Color.black, null);
                    graphics2.setColor(new Color(0, 0, 0, 128));
                    graphics2.fillRect(0, 0, 256, 240);
                }
                else {
                    graphics2.setColor(Color.black);
                    graphics2.fillRect(0, 0, 256, 240);
                }
                String s = "NESCafe";
                switch (this.c) {
                    case 17: {
                        s = "Time Trial Start";
                        break;
                    }
                    case 10: {
                        s = "Data Saved";
                        break;
                    }
                    case 11: {
                        s = "Message from NESCafe Server";
                        break;
                    }
                    case 7: {
                        s = "NESCafe Error Occurred";
                        break;
                    }
                    case 8:
                    case 14: {
                        s = "NESCafe";
                        break;
                    }
                    case 1: {
                        s = "Game Failed to Load";
                        break;
                    }
                    case 2: {
                        s = "State Failed to Load";
                        break;
                    }
                    case 4: {
                        s = "State Failed to Save";
                        break;
                    }
                    case 5: {
                        s = "NESCafe Initialisation Error";
                        break;
                    }
                    case 15: {
                        s = "Light Gun Used in Movie";
                        this.c = "Sorry, you cannot record Light Gun clicks in";
                        this.d = "Keystoke Movies. To record Light Gun actions";
                        this.e = "please record in Animated GIF mode instead.";
                        break;
                    }
                    case 6: {
                        s = "Authorisation Failure";
                        if (!this.c.equals("NESCafe failed to authorise with NESCafe website.")) {
                            this.c = "NESCafe failed to authorise with NESCafe website.";
                            this.d = "Ensure that the nescafeproxy.php script is installed.";
                            this.a.a("Failed to communicate with nescafeproxy.php to " + O.a);
                            break;
                        }
                        break;
                    }
                    case 13: {
                        s = "Game Crashed";
                        this.c = "This game has crashed unexpectedly and may";
                        this.d = "not be fully supported by NESCafe.";
                        this.e = "Please try playing another game instead.";
                        break;
                    }
                    case 16: {
                        s = "Game Over";
                        this.c = "The game that you were playing has ended.";
                        this.d = "Please select another game to play.";
                        break;
                    }
                }
                this.a(graphics2, 0, 70, 256, 120, s);
                graphics2.setColor(Color.black);
                aK.a(graphics2, this.c, this.b, 121);
                if (this.d != null && !this.d.trim().equals("")) {
                    aK.a(graphics2, this.d, this.b, 135);
                }
                if (this.e != null && !this.e.trim().equals("")) {
                    aK.a(graphics2, this.e, this.b, 149);
                }
                if (this.f != null && !this.f.trim().equals("")) {
                    aK.a(graphics2, this.f, this.b, 163);
                }
                if (this.a.a.a != null) {
                    this.a(graphics2, 80, 162, 96, 15, Color.white);
                    (this.c = new ArrayList()).add(new Object(this, 80, 162, 96, 15, 10) {
                        public int a;
                        public int b;
                        public int c;
                        public int d;
                        public int e;
                        
                        {
                            this.a = 0;
                            this.b = 0;
                            this.c = 0;
                            this.d = 0;
                            this.e = 0;
                            this.c = c;
                            this.d = d;
                            this.b = b;
                            this.a = a;
                            this.e = e;
                        }
                        
                        public final boolean a(final int n, final int n2) {
                            return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                        }
                    });
                    graphics2.setColor(Color.black);
                    aK.a(graphics2, "Go to Website", this.b, 173);
                }
                else if (this.b == 12) {
                    this.a(graphics2, 100, 162, 56, 15, Color.white);
                    graphics2.setColor(Color.black);
                    aK.a(graphics2, "Enter", this.b, 173);
                }
                break;
            }
            case 10: {
                graphics2.setColor(Color.black);
                graphics2.fillRect(0, 0, 256, 240);
                this.a.a.a.a(graphics2);
                break;
            }
            default: {
                graphics2.setColor(Color.black);
                graphics2.fillRect(0, 0, 256, 240);
                if (image != null) {
                    graphics2.drawImage(image, 0, 0, Color.black, null);
                }
                if (this.b != 16) {
                    graphics2.setColor(new Color(0, 0, 0, 128));
                    graphics2.fillRect(0, 0, 256, 240);
                    break;
                }
                if (this.s < 100) {
                    graphics2.setColor(new Color(32, 32, 32, 128 - this.s));
                    graphics2.fillRect(0, 0, 256, 240);
                    break;
                }
                break;
            }
        }
        if (this.b == 2 || this.b == 3) {
            if (this.f > 0) {
                --this.f;
            }
            if (this.f == 1) {
                if (this.b == 2) {
                    this.a = this.a(this.l);
                }
                else {
                    this.a = this.a(this.m);
                }
            }
        }
        switch (this.b) {
            case 24: {
                this.c(graphics2);
                break;
            }
            case 20: {
                this.b(graphics2);
                break;
            }
            case 19: {
                this.a(graphics2);
                break;
            }
            case 18: {
                this.a(graphics2, this.q);
                break;
            }
            case 16: {
                this.k(graphics2);
                break;
            }
            case 14: {
                this.f(graphics2);
                break;
            }
            case 13: {
                this.l(graphics2);
                break;
            }
            case 9: {
                this.a(graphics2, image, this.q);
                break;
            }
            case 7: {
                this.g(graphics2);
                break;
            }
            case 6: {
                this.b(graphics2, this.q);
                break;
            }
            case 22: {
                this.h(graphics2);
                break;
            }
            case 2: {
                this.c(graphics2, this.q);
                break;
            }
            case 3: {
                this.e(graphics2, this.q);
                break;
            }
            case 4: {
                this.i(graphics2);
                break;
            }
            case 5: {
                this.j(graphics2);
                break;
            }
            case 8: {
                this.d(graphics2);
                break;
            }
            case 15: {
                this.e(graphics2);
                break;
            }
            case 1: {
                this.d(graphics2, this.q);
                break;
            }
        }
        graphics2.dispose();
        if (graphics != null) {
            graphics.drawImage(this.b, 0, 0, Color.black, null);
            if (this.q > 0) {
                this.q -= 16;
            }
        }
        return false;
    }
    
    private void a(final Graphics graphics) {
        this.a(graphics, 2, 70, 251, 130, "Time Shift Buffer Configuration");
        try {
            graphics.setFont(this.d);
            for (int i = 0; i < 3; ++i) {
                String s = "";
                String s2 = "";
                switch (i) {
                    case 0: {
                        s = "TSB Length: ";
                        if (this.a.a.i == 0) {
                            s2 = "[disabled]";
                            break;
                        }
                        s2 = this.a.a.i + " units";
                        break;
                    }
                    case 1: {
                        s = "TSB Record Interval: ";
                        s2 = this.a.a.j + " seconds";
                        break;
                    }
                    case 2: {
                        s = "";
                        s2 = "";
                        break;
                    }
                }
                if (i == this.p) {
                    graphics.setColor(new Color(255, 255, 255));
                }
                else {
                    graphics.setColor(new Color(192, 192, 255));
                }
                if (i != 2) {
                    this.a(graphics, 20, 110 + i * 22, 214, 18, graphics.getColor());
                }
                if (i != 2) {
                    graphics.setColor(Color.black);
                    graphics.drawString(s, 30, 110 + i * 22 + 14);
                    graphics.drawString(s2, 150, 110 + i * 22 + 14);
                }
                else {
                    graphics.setColor(Color.black);
                    String string = "Time Shift Buffer is " + this.a.a.j * this.a.a.i + " seconds long";
                    if (this.a.a.i == 0) {
                        string = "Time Shift Buffer is disabled";
                    }
                    aK.a(graphics, string, this.d, 120 + i * 22 + 14);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void b(final Graphics graphics) {
        this.a(graphics, 2, 70, 251, 150, "Movie Recording Configuration");
        try {
            graphics.setFont(this.d);
            final int e = this.a.a.e;
            final boolean b = this.a.a.d != 0;
            for (int i = 0; i < 3; ++i) {
                String s = "";
                String string = "";
                switch (i) {
                    case 0: {
                        s = "Movie Mode: ";
                        string = ((this.a.a.d == 0) ? "Record Keystokes" : "Record Animated GIF");
                        break;
                    }
                    case 1: {
                        s = "Movie Quality: ";
                        string = (this.a.a.h ? "High (256x240)" : "Low (128x120)");
                        break;
                    }
                    case 2: {
                        s = "Frames/Sec: ";
                        string = e + " fps";
                        break;
                    }
                }
                if (!b && i >= 1) {
                    graphics.setColor(new Color(192, 192, 192));
                }
                else if (i == this.p) {
                    graphics.setColor(new Color(255, 255, 255));
                }
                else {
                    graphics.setColor(new Color(192, 192, 255));
                }
                this.a(graphics, 20, 110 + i * 22, 214, 18, graphics.getColor());
                if (!b && i >= 1) {
                    graphics.setColor(new Color(192, 192, 192));
                }
                else if (i == this.p) {
                    graphics.setColor(Color.black);
                }
                else {
                    graphics.setColor(Color.black);
                }
                graphics.drawString(s, 30, 110 + i * 22 + 14);
                graphics.drawString(string, 110, 110 + i * 22 + 14);
            }
            if (this.a.a.d == 1) {
                aK.a(graphics, "Size of Movie is limited to " + this.b(this.a.a.g), this.b, 190);
                aK.a(graphics, "Max movie length " + aK.a(this.a(this.a.a.e)) + " at these settings.", this.b, 206);
            }
        }
        catch (Exception ex) {}
    }
    
    private int a(final int n) {
        int n2;
        if (this.a.a.h) {
            n2 = 3500;
        }
        else {
            n2 = 1000;
        }
        return this.a.a.g / (n2 * n);
    }
    
    private String b(final int n) {
        String s;
        if (n > 1048576) {
            s = this.a.a.g / 1024 / 1024 + "MB";
        }
        else if (n > 1024) {
            s = this.a.a.g / 1024 + "KB";
        }
        else {
            s = this.a.a.g + " bytes";
        }
        return s;
    }
    
    private void c(final Graphics graphics) {
        this.a(graphics, 10, 60, 235, 150, "Submit Time Trial data");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        aK.a(graphics, "If you are ready to submit your screenshot", this.b, 110);
        aK.a(graphics, "then click on the Submit button. Make sure", this.b, 129);
        aK.a(graphics, "that the screenshot clearly shows your score.", this.b, 148);
        if (this.o == 0) {
            graphics.setColor(new Color(255, 255, 255));
        }
        else {
            graphics.setColor(new Color(192, 192, 255));
        }
        this.a(graphics, 68, 170, 50, 18, graphics.getColor());
        graphics.setColor(Color.black);
        graphics.drawString(" Submit", 73, 184);
        if (this.o == 1) {
            graphics.setColor(new Color(255, 255, 255));
        }
        else {
            graphics.setColor(new Color(192, 192, 255));
        }
        this.a(graphics, 138, 170, 50, 18, graphics.getColor());
        graphics.setColor(Color.black);
        graphics.drawString(" Cancel", 143, 184);
        this.c.size();
        if (this.c.size() == 1) {
            this.c.add(new Object(this, 68, 170, 50, 18, 1) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 138, 170, 50, 18, 2) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
        }
    }
    
    private void d(final Graphics graphics) {
        this.a(graphics, 10, 70, 235, 100, "NESCafe v1.02 Expired");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        aK.a(graphics, "Please upgrade your version of NESCafe,", this.b, 120);
        aK.a(graphics, "this version is too old to use.", this.b, 130);
        aK.a(graphics, "http://" + O.a + "/", this.b, 150);
    }
    
    private void a(final Graphics graphics, final int n) {
        this.a(graphics, 10 + n, 50, 235, 165, "Time Shift Buffer [Warp Zone]");
        final double n2 = 120.0 - 120.0 * (this.r / (this.a.a.a.a - 1));
        try {
            final int n3 = 20 + (int)n2 + n;
            graphics.setColor(new Color(128, 128, 128));
            graphics.fillRect(28 + n, 130, 194, 10);
            graphics.setColor(new Color(64, 64, 64));
            graphics.drawRect(28 + n, 130, 194, 10);
            if (this.c != null) {
                graphics.setColor(Color.black);
                graphics.fillRoundRect(n3 - 2 + n, 88, 100, 94, 5, 5);
                if (this.c != null) {
                    graphics.drawImage(this.c, n3 + n, 90, 96, 90, Color.black, null);
                }
                if (this.r < this.a.a.a.a - 1) {
                    aK.a(graphics, n3 + n, 124);
                }
                if (this.r > 0) {
                    aK.b(graphics, n3 + 96 + n, 124);
                }
                graphics.setColor(Color.black);
                aK.a(graphics, "Press ENTER to warp back " + this.r * this.a.a.j + " seconds", this.b, n, 200);
            }
            else {
                graphics.setColor(Color.black);
                aK.a(graphics, "Time Shift Buffer is empty, press ESC", this.b, n, 200);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void e(final Graphics graphics) {
        this.a(graphics, 10, 45, 236, 180, "About NESCafe v1.02");
        graphics.setFont(this.d);
        graphics.setColor(Color.black);
        graphics.drawString("NESCafe Applet Edition", 30, 94);
        graphics.setFont(this.b);
        graphics.setColor(Color.black);
        graphics.drawString("Written by David de Niese, May 2008", 30, 111);
        graphics.drawString("NESCafe is a Java-based NES Emulator", 30, 121);
        graphics.drawString(ac.a, 30, 131);
        if (this.a.a()) {
            graphics.setFont(this.d);
            graphics.setColor(Color.black);
            graphics.drawString("Nintendo Game Loaded", 30, 159);
            graphics.setFont(this.b);
            graphics.setColor(Color.black);
            graphics.drawString("MMC: " + this.a.a.a.a, 30, 176);
            graphics.drawString("PROM Banks: " + this.a.a.a.c + " (" + this.a.a.a.c * 8 + "Kb)", 30, 186);
            graphics.drawString("CROM Banks: " + this.a.a.a.d + " (" + this.a.a.a.d * 4 + "Kb)", 30, 196);
            graphics.drawString("Save RAM: " + (this.a.a.a.d ? "Enabled" : "Disabled"), 30, 206);
        }
    }
    
    private void f(final Graphics graphics) {
        this.a(graphics, 10, 40, 236, 165, "Download Game");
        try {
            graphics.setFont(this.b);
            graphics.setFont(this.d);
            if (this.b == null) {
                final boolean b = this.c.size() == 1;
                final int h;
                final int a = aK.a((h = this.h) + 5, this.a.size());
                for (int i = h; i < a; ++i) {
                    final int n = i - h;
                    final String s = this.a.get(i);
                    if (i == this.p) {
                        graphics.setColor(new Color(255, 255, 255));
                    }
                    else {
                        graphics.setColor(new Color(192, 192, 255));
                    }
                    this.a(graphics, 20, 79 + n * 22, 214, 18, graphics.getColor());
                    if (b) {
                        this.c.add(new Object(this, 20, 79 + n * 22, 214, 18, 49 + n) {
                            public int a;
                            public int b;
                            public int c;
                            public int d;
                            public int e;
                            
                            {
                                this.a = 0;
                                this.b = 0;
                                this.c = 0;
                                this.d = 0;
                                this.e = 0;
                                this.c = c;
                                this.d = d;
                                this.b = b;
                                this.a = a;
                                this.e = e;
                            }
                            
                            public final boolean a(final int n, final int n2) {
                                return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                            }
                        });
                    }
                    graphics.setColor(Color.black);
                    graphics.drawString(s, 30, 79 + n * 22 + 14);
                }
                if (a < this.a.size()) {
                    aK.d(graphics, 210, 180);
                }
                if (this.h > 0) {
                    aK.c(graphics, 210, 85);
                }
                return;
            }
            ++this.g;
            if (this.g <= 200) {
                graphics.setColor(Color.black);
                aK.a(graphics, "Please wait...", this.b, 93);
                return;
            }
            if (this.g > 200) {
                graphics.setColor(Color.black);
                aK.a(graphics, "An error occurred when NESCafe tried to", this.b, 93);
                aK.a(graphics, "connect to the NESCafe Game Server:", this.b, 108);
                aK.a(graphics, this.b, this.b, 123);
                aK.a(graphics, "Please press ESC to return to the menu", this.b, 138);
                this.g = 200;
            }
        }
        catch (Exception ex) {}
    }
    
    private void g(final Graphics graphics) {
        this.a(graphics, 10, 30, 236, 210, "Configure Controls");
        graphics.setFont(this.d);
        for (int i = 0; i < 9; ++i) {
            if (i != 8) {
                if (i == this.p && this.a) {
                    graphics.setColor(new Color(255, 255, 64));
                }
                else if (i == this.p) {
                    graphics.setColor(new Color(255, 255, 255));
                }
                else {
                    graphics.setColor(new Color(192, 192, 255));
                }
                this.a(graphics, 20, 69 + i * 18, 214, 16, graphics.getColor());
                graphics.setColor(Color.black);
            }
            String a = "";
            switch (i) {
                case 0: {
                    a = "Button A";
                    break;
                }
                case 1: {
                    a = "Button B";
                    break;
                }
                case 2: {
                    a = "Start Button";
                    break;
                }
                case 3: {
                    a = "Select Button";
                    break;
                }
                case 4: {
                    a = "Up Button";
                    break;
                }
                case 5: {
                    a = "Down Button";
                    break;
                }
                case 6: {
                    a = "Left Button";
                    break;
                }
                case 7: {
                    a = "Right Button";
                    break;
                }
                case 8: {
                    if (!this.a.equals("")) {
                        graphics.setColor(new Color(128, 0, 0));
                        a = this.a;
                        break;
                    }
                    break;
                }
            }
            if (i == 8) {
                aK.a(graphics, a, this.d, 69 + i * 18 + 14);
            }
            else {
                graphics.drawString(a, 30, 69 + i * 18 + 14 - 2);
            }
            graphics.setColor(Color.black);
            String s = "";
            if (this.p != i) {
                switch (i) {
                    case 0: {
                        s += this.a(this.a.a.l);
                        break;
                    }
                    case 1: {
                        s += this.a(this.a.a.m);
                        break;
                    }
                    case 2: {
                        s += this.a(this.a.a.n);
                        break;
                    }
                    case 3: {
                        s += this.a(this.a.a.o);
                        break;
                    }
                    case 4: {
                        s += this.a(this.a.a.p);
                        break;
                    }
                    case 5: {
                        s += this.a(this.a.a.q);
                        break;
                    }
                    case 6: {
                        s += this.a(this.a.a.r);
                        break;
                    }
                    case 7: {
                        s += this.a(this.a.a.s);
                        break;
                    }
                }
            }
            else if (this.a) {
                s += "[press a key]";
            }
            else {
                switch (i) {
                    case 0: {
                        s += this.a(this.a.a.l);
                        break;
                    }
                    case 1: {
                        s += this.a(this.a.a.m);
                        break;
                    }
                    case 2: {
                        s += this.a(this.a.a.n);
                        break;
                    }
                    case 3: {
                        s += this.a(this.a.a.o);
                        break;
                    }
                    case 4: {
                        s += this.a(this.a.a.p);
                        break;
                    }
                    case 5: {
                        s += this.a(this.a.a.q);
                        break;
                    }
                    case 6: {
                        s += this.a(this.a.a.r);
                        break;
                    }
                    case 7: {
                        s += this.a(this.a.a.s);
                        break;
                    }
                }
            }
            graphics.drawString(s, 150, 69 + i * 18 + 14 - 2);
        }
    }
    
    private void b(final Graphics graphics, final int n) {
        this.a(graphics, 25 + n, 70, 206, 90, "Save Image");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        graphics.drawString("Do you want to save this screen image?", 33 + n, 120);
        graphics.setFont(this.d);
        for (int i = 0; i < 2; ++i) {
            if (i == this.n) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            this.a(graphics, 33 + i * 100 + n, 130, 90, 18, graphics.getColor());
            graphics.setColor(Color.black);
            String s;
            if (i == 0) {
                s = "Yes";
            }
            else {
                s = " No";
            }
            graphics.drawString(s, 65 + i * 100 + n, 144);
        }
    }
    
    private void h(final Graphics graphics) {
        this.a(graphics, 0, 70, 256, (this.p == 0) ? 120 : 101, "Save Movie");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        graphics.drawString("Which movie format do you wish to use?", 33, 120);
        graphics.setFont(this.d);
        for (int i = 0; i < 2; ++i) {
            if (i == this.p) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else if (this.a.a.h != y.a) {
                graphics.setColor(Color.gray);
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            if (i == 0 && !this.a.a.canAppletSaveAnimGif()) {
                graphics.setColor(Color.gray);
            }
            else if (i == 1 && !this.a.a.canAppletSaveState()) {
                graphics.setColor(Color.gray);
            }
            this.a(graphics, 33 + i * 100, 130, 90, 18, graphics.getColor());
            graphics.setColor(Color.black);
            String s;
            if (i == 0) {
                s = "Animated GIF";
            }
            else {
                s = "Keystrokes";
            }
            graphics.drawString(s, 50 + i * 100, 144);
        }
        if (this.p == 0) {
            graphics.setColor(Color.black);
            aK.a(graphics, "Size of Movie is limited to " + this.b(this.a.a.g), this.b, 165);
            aK.a(graphics, "Max movie length " + aK.a(this.a(this.a.a.e)) + " using your movie settings", this.b, 175);
        }
    }
    
    private void i(final Graphics graphics) {
        this.a(graphics, 25, 70, 206, 90, "Reset Game");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        graphics.drawString("Are you sure you wish to Reset?", 33, 120);
        graphics.setFont(this.d);
        for (int i = 0; i < 2; ++i) {
            if (i == this.n) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            this.a(graphics, 33 + i * 100, 130, 90, 18, graphics.getColor());
            graphics.setColor(Color.black);
            String s;
            if (i == 0) {
                s = "Yes";
            }
            else {
                s = " No";
            }
            graphics.drawString(s, 65 + i * 100, 144);
        }
    }
    
    private void j(final Graphics graphics) {
        this.a(graphics, 25, 70, 206, 90, "Wipe Save RAM?");
        graphics.setColor(Color.black);
        graphics.setFont(this.b);
        graphics.drawString("Do you wish to clear the SaveRAM?", 33, 120);
        graphics.setFont(this.d);
        for (int i = 0; i < 2; ++i) {
            if (i == this.n) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            this.a(graphics, 33 + i * 100, 130, 90, 18, graphics.getColor());
            graphics.setColor(Color.black);
            String s;
            if (i == 0) {
                s = "Yes";
            }
            else {
                s = " No";
            }
            graphics.drawString(s, 65 + i * 100, 144);
        }
    }
    
    private void c(final Graphics graphics, final int n) {
        if (this.a.a.a != null) {
            this.a(graphics, 10 + n, 40, 236, 180, "Save NESCafe Movie");
        }
        else {
            this.a(graphics, 10 + n, 40, 236, 180, "Save NESCafe State");
        }
        graphics.setFont(this.d);
        final int h;
        final int n2 = (h = this.h) + 5;
        for (int i = h; i < n2; ++i) {
            if (i == this.l) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            final int n3 = i - this.h;
            this.a(graphics, 20 + n, 79 + n3 * 22, 100, 18, graphics.getColor());
            graphics.setColor(Color.black);
            graphics.drawString("Save Slot " + (i + 1), 30 + n, 79 + n3 * 22 + 14);
        }
        if (n2 < 5) {
            aK.d(graphics, 100 + n, 180);
        }
        if (this.h > 0) {
            aK.c(graphics, 100 + n, 85);
        }
        graphics.setColor(Color.black);
        graphics.fillRect(130 + n, 79, 106, 106);
        if (this.a != null) {
            graphics.drawImage(this.a, 130 + n, 79, 106, 106, Color.black, null);
            if (this.g) {
                aK.a(graphics, "Movie Embedded", 135 + n, 177, null);
            }
        }
        else if (this.f > 0) {
            graphics.setColor(Color.white);
            graphics.setFont(this.b);
            graphics.drawString("[please wait...]", 135 + n, 177);
        }
        else if (this.b) {
            graphics.setColor(Color.green);
            graphics.setFont(this.b);
            graphics.drawString("[no state saved]", 135 + n, 177);
        }
        else {
            graphics.setColor(Color.red);
            graphics.setFont(this.b);
            graphics.drawString("[state already here]", 135 + n, 177);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(130 + n, 79, 106, 106);
        aK.a(graphics, "Select Slot, press Enter to save or Esc to close", this.b, n, 205);
        if (this.j) {
            graphics.setColor(new Color(0, 0, 0, 120));
            graphics.fillRect(0, 0, 256, 240);
            this.a(graphics, 0 + n, 80, 256, 100, "NESCafe");
            graphics.setColor(Color.black);
            aK.a(graphics, "Do you wish to overwrite this state?", this.b, n, 130);
            for (int j = 0; j < 2; ++j) {
                if (j == this.p) {
                    graphics.setColor(new Color(240, 240, 255));
                }
                else {
                    graphics.setColor(new Color(192, 192, 255));
                }
                this.a(graphics, 33 + j * 100 + n, 140, 90, 18, graphics.getColor());
                graphics.setColor(Color.black);
                String s;
                if (j == 0) {
                    s = "Yes";
                }
                else {
                    s = " No";
                }
                graphics.drawString(s, 65 + j * 100 + n, 154);
            }
        }
    }
    
    private void k(final Graphics graphics) {
        boolean b = false;
        if (this.b == 16 && this.p > 3) {
            if (this.s < 140) {
                this.s += 4;
                b = true;
            }
        }
        else if (this.s > 0) {
            this.s -= 4;
            b = true;
        }
        final int n = 60 - this.s;
        if (b) {
            (this.c = new ArrayList()).add(new Object(this, 20, n + 10 + 0, 216, 18, 49) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 22, 216, 18, 50) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 44, 216, 18, 51) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 66, 216, 18, 52) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 88, 216, 18, 53) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 110, 216, 18, 54) {
                public int a;
                public int b;
                public int c;
                public int d;
                public int e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.c = c;
                    this.d = d;
                    this.b = b;
                    this.a = a;
                    this.e = e;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.c.add(new Object(this, 20, n + 10 + 132, 216, 18, 55) {
                public int a = a;
                public int b = b;
                public int c = c;
                public int d = d;
                public int e = e;
                
                {
                    this.a = 0;
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                }
                
                public final boolean a(final int n, final int n2) {
                    return n >= this.c && n < this.c + this.b && n2 >= this.d && n2 < this.d + this.a;
                }
            });
            this.h = false;
        }
        this.a(graphics, 10, n - 29, 236, 200, "Graphics Settings", false);
        graphics.setFont(this.b);
        graphics.setFont(this.b);
        for (int i = 0; i < 7; ++i) {
            String s = "";
            String s2 = "";
            switch (i) {
                case 0: {
                    s = "Stretch";
                    s2 = (this.a.a.a.b ? "Enabled" : "Disabled");
                    break;
                }
                case 1: {
                    s = "Frame Rate";
                    s2 = (this.a.a.a.c ? "Visible" : "Hidden");
                    break;
                }
                case 2: {
                    s = "Image Format";
                    s2 = ((this.a.a.f == 1) ? "PNG" : "GIF");
                    break;
                }
                case 3: {
                    s = "Clip Borders";
                    s2 = (this.a.a.a.a ? "Clipped" : "Not Clipped");
                    break;
                }
                case 4: {
                    s = "Hue";
                    s2 = (int)(this.a.a.a.b / 256.0f * 100.0f) + "%";
                    break;
                }
                case 5: {
                    s = "Tint";
                    s2 = (int)(this.a.a.a.a / 256.0f * 100.0f) + "%";
                    break;
                }
                case 6: {
                    s = "Colour Mode";
                    s2 = (this.a.a.a.d ? "Black n White" : "Colour");
                    break;
                }
            }
            if (i == this.p) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            this.a(graphics, 20, n + 10 + i * 22, 214, 18, graphics.getColor());
            graphics.setColor(Color.black);
            graphics.drawString(s, 25, n + 10 + i * 22 + 13);
            graphics.drawString(s2, 100, n + 10 + i * 22 + 13);
        }
    }
    
    private void l(final Graphics graphics) {
        this.a(graphics, 10, 45, 236, 165, "Online Settings");
        graphics.setFont(this.b);
        graphics.setFont(this.b);
        for (int i = 0; i < 5; ++i) {
            String s = "";
            String s2 = "";
            switch (i) {
                case 0: {
                    s = "Proxy:";
                    s2 = (this.f ? "Enabled" : "Disabled");
                    break;
                }
                case 1: {
                    s = "Server:";
                    s2 = this.g;
                    break;
                }
                case 2: {
                    s = "Port:";
                    s2 = this.j;
                    break;
                }
                case 3: {
                    s = "Username:";
                    s2 = this.h;
                    break;
                }
                case 4: {
                    s = "Password:";
                    s2 = this.i;
                    break;
                }
            }
            if (i == this.p) {
                if (this.a) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(new Color(240, 240, 255));
                }
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            if ((i != 4 || !this.a || this.p != 4) && i == 4) {
                int length;
                for (length = s2.length(), s2 = ""; s2.length() < length; s2 += "*") {}
            }
            if (i == this.p && this.a) {
                s2 = this.k + "_";
            }
            this.a(graphics, 20, 84 + i * 22, 214, 18, graphics.getColor());
            if (i > 0 && !this.f) {
                graphics.setColor(Color.gray);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(s, 25, 84 + i * 22 + 12);
            graphics.drawString(s2, 85, 84 + i * 22 + 12);
        }
    }
    
    private void a(final Graphics graphics, final Image image, final int n) {
        this.a(graphics, 10 + n, 40, 236, 184, "Game Genie Menu");
        graphics.setFont(this.d);
        String string = "";
        String string2 = "";
        String string3 = "";
        for (int i = 0; i < 4; ++i) {
            String s = this.a.a.a.a(i);
            if (i == this.m) {
                string = "";
                string2 = "";
                string3 = "";
            }
            if (s == null || s.equals("")) {
                s = "[not set]";
            }
            if (i == this.m && !s.equals("[not set]")) {
                string = "0x" + this.a.a.a.b(i);
                string3 = "0x" + aK.a(this.a.a.a.b(i), 2);
                if (this.a.a.a.c(i) != -1) {
                    string2 = "0x" + aK.a(this.a.a.a.c(i), 2);
                }
            }
            if (i == this.m) {
                if (this.a) {
                    if (!this.c.equals("")) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(Color.white);
                    }
                }
                else {
                    graphics.setColor(new Color(240, 240, 255));
                }
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            if (i == this.m && this.a) {
                s = this.k + "_";
            }
            final int n2 = 79 + i * 22 + 14;
            this.a(graphics, 20 + n, 79 + i * 22, 100, 18, graphics.getColor());
            graphics.setColor(Color.black);
            graphics.drawString(s, 30 + n, n2);
        }
        if (this.c != null) {
            if (!this.a) {
                graphics.drawString("double-click to edit", 20 + n, 184);
            }
            else {
                graphics.drawString("press Enter to save", 20 + n, 184);
            }
        }
        graphics.setColor(Color.black);
        graphics.fillRect(130 + n, 79, 106, 106);
        if (image != null) {
            graphics.drawImage(image, 130 + n, 79, 106, 106, Color.black, null);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(130 + n, 79, 106, 106);
        if (this.c != null && !this.c.trim().equals("")) {
            graphics.setColor(new Color(64, 0, 64));
            aK.a(graphics, this.c, this.c, n, 207);
            return;
        }
        if (!string.equals("") && !this.a) {
            graphics.setColor(new Color(152, 152, 255));
            graphics.fillRect(20 + n, 192, 216, 23);
            String s2 = "Address: " + string + " Val: " + string3;
            if (!string2.equals("")) {
                s2 = s2 + " Comp: " + string2;
            }
            graphics.setColor(Color.black);
            aK.a(graphics, s2, this.b, n, 212);
            aK.a(graphics, "Game Genie Technical Info", this.c, n, 202);
        }
    }
    
    private void d(final Graphics graphics, final int n) {
        try {
            if (this.p >= this.a.length) {
                this.p = 0;
            }
            while (this.a[this.p]) {
                ++this.p;
                if (this.p >= this.a.length) {
                    this.p = 0;
                    break;
                }
            }
            String s = "";
            switch (this.d) {
                case 0: {
                    s = "NESCafe v1.02";
                    break;
                }
                case 1: {
                    s = "Settings Menu";
                    break;
                }
                case 2: {
                    s = "Emulation Menu";
                    break;
                }
                case 3: {
                    s = "Extras Menu";
                    break;
                }
                case 4: {
                    s = "Online Menu";
                    break;
                }
                case 5: {
                    s = "About NESCafe";
                    break;
                }
            }
            this.a(graphics, 10 + n, 45, 236, 180, s);
            graphics.setFont(this.d);
            for (int i = 0; i < this.e; ++i) {
                if (this.a[i]) {
                    graphics.setColor(new Color(192, 192, 192));
                }
                else if (i == this.p) {
                    graphics.setColor(new Color(240, 240, 255));
                }
                else {
                    graphics.setColor(new Color(192, 192, 255));
                }
                this.a(graphics, 20 + n, 84 + i * 22, 214, 18, graphics.getColor());
                if (this.a[i]) {
                    graphics.setColor(Color.gray);
                }
                else {
                    graphics.setColor(Color.black);
                }
                String s2 = "";
                String s3 = "";
                final boolean g = this.a.a.g;
                switch (this.d * 10 + i) {
                    case 0: {
                        s2 = "Settings Menu...";
                        break;
                    }
                    case 1: {
                        s2 = "Emulation Menu...";
                        break;
                    }
                    case 2: {
                        s2 = "Extras Menu...";
                        break;
                    }
                    case 3: {
                        s2 = "NESCafe Online...";
                        break;
                    }
                    case 4: {
                        s2 = "About NESCafe...";
                        break;
                    }
                    case 5: {
                        s2 = "Download NESCafe...";
                        break;
                    }
                    case 10: {
                        s2 = "Configure Controls...";
                        s3 = "";
                        break;
                    }
                    case 11: {
                        s2 = "Graphics Options...";
                        s3 = "";
                        break;
                    }
                    case 12: {
                        s2 = (g ? "Sound Enabled (toggle)" : "Sound Disabled (toggle)");
                        s3 = "[S]";
                        break;
                    }
                    case 13: {
                        s2 = (this.a.a.g ? "Light Gun Enabled (toggle)" : "Light Gun Disabled (toggle)");
                        s3 = "";
                        break;
                    }
                    case 14: {
                        s2 = "Configure Time Shift Buffer...";
                        s3 = "";
                        break;
                    }
                    case 15: {
                        s2 = "Configure Movie Recording...";
                        s3 = "";
                        break;
                    }
                    case 20: {
                        s2 = "Save State...";
                        s3 = "[C]";
                        break;
                    }
                    case 21: {
                        s2 = "Load State...";
                        s3 = "[L]";
                        break;
                    }
                    case 22: {
                        s2 = (this.a.a.b ? "Unpause ROM" : "Pause ROM");
                        s3 = "[P]";
                        break;
                    }
                    case 23: {
                        s2 = "Reset Game...";
                        s3 = "[R]";
                        break;
                    }
                    case 24: {
                        s2 = "Wipe SaveRAM";
                        s3 = "[W]";
                        break;
                    }
                    case 30: {
                        s2 = "Enter Game Genie Code";
                        s3 = "[G]";
                        break;
                    }
                    case 31: {
                        s2 = ((this.a.a.a != null && this.a.a.a.b) ? "Hide Subtitles" : "Show Subtitles");
                        s3 = "[#]";
                        break;
                    }
                    case 32: {
                        s2 = (this.a.a.e ? "Stop Recording Movie" : "Record Movie");
                        s3 = "[F8]";
                        break;
                    }
                    case 33: {
                        s2 = "Save Screenshot";
                        s3 = "[A]";
                        break;
                    }
                    case 34: {
                        s2 = "Time Shift Buffer (Warp Zone)";
                        s3 = "[BS]";
                        break;
                    }
                    case 40: {
                        s2 = "Internet Settings...";
                        s3 = "";
                        break;
                    }
                }
                graphics.drawString(s2, 30 + n, 84 + i * 22 + 14);
                graphics.drawString(s3, 206 + n, 84 + i * 22 + 13);
            }
        }
        catch (Exception ex) {}
    }
    
    private void e(final Graphics graphics, final int n) {
        this.a(graphics, 10 + n, 40, 236, 180, "Load NESCafe State");
        graphics.setFont(this.d);
        final int h;
        final int n2 = (h = this.h) + 5;
        for (int i = h; i < n2; ++i) {
            if (i == this.m) {
                graphics.setColor(new Color(240, 240, 255));
            }
            else {
                graphics.setColor(new Color(192, 192, 255));
            }
            final int n3 = i - h;
            this.a(graphics, 20 + n, 79 + n3 * 22, 100, 18, graphics.getColor());
            graphics.setColor(Color.black);
            graphics.drawString("Save Slot " + (i + 1), 30 + n, 79 + n3 * 22 + 14);
        }
        aK.a(graphics, "Select Slot, press Enter to load or Esc to close", this.b, n, 205);
        if (n2 < 5) {
            aK.d(graphics, 100 + n, 180);
        }
        if (this.h > 0) {
            aK.c(graphics, 100 + n, 85);
        }
        graphics.setColor(Color.black);
        graphics.fillRect(130 + n, 79, 106, 106);
        if (this.a != null) {
            graphics.drawImage(this.a, 130 + n, 79, 106, 106, Color.black, null);
            if (this.g) {
                aK.a(graphics, "Movie Embedded", 135 + n, 177, null);
            }
        }
        else if (this.f > 0) {
            graphics.setColor(Color.white);
            graphics.setFont(this.b);
            graphics.drawString("[please wait...]", 135 + n, 177);
        }
        else if (this.b) {
            graphics.setColor(Color.red);
            graphics.setFont(this.b);
            graphics.drawString("[no state saved]", 135 + n, 177);
        }
        else {
            graphics.setColor(Color.white);
            graphics.setFont(this.b);
            graphics.drawString("[state available]", 135 + n, 177);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(130 + n, 79, 106, 106);
        if (this.i) {
            graphics.setColor(new Color(0, 0, 0, 120));
            graphics.fillRect(0, 0, 256, 240);
            this.a(graphics, 0 + n, 80, 256, 100, "NESCafe Error");
            graphics.setColor(Color.black);
            aK.a(graphics, "No State file available in slot", this.b, n, 130);
            graphics.setColor(new Color(240, 240, 255));
            this.a(graphics, 83 + n, 140, 90, 18, graphics.getColor());
            graphics.setColor(Color.black);
            graphics.drawString("Enter", 118 + n, 154);
        }
    }
}
