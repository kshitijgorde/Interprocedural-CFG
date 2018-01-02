import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class y
{
    public static int a;
    public static int b;
    public static int c;
    public double a;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int d;
    public int e;
    public boolean h;
    public int f;
    public boolean i;
    public int g;
    public boolean j;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public String a;
    public boolean l;
    public boolean m;
    public int k;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public String b;
    public String c;
    public String d;
    public String e;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean s;
    public String f;
    public boolean t;
    public boolean u;
    private au a;
    
    public y() {
        this.a = 0.0;
        this.a = true;
        this.b = true;
        this.c = true;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = false;
        this.d = 0;
        this.e = 7;
        this.h = true;
        this.f = 1;
        this.i = false;
        this.g = 2097152;
        this.j = false;
        this.h = y.a;
        this.i = 120;
        this.j = 2;
        this.k = true;
        this.a = null;
        this.l = false;
        this.m = false;
        this.k = 0;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.l = 88;
        this.m = 90;
        this.n = 10;
        this.o = 32;
        this.p = 38;
        this.q = 40;
        this.r = 37;
        this.s = 39;
        this.s = false;
        this.f = "";
        this.t = false;
        this.u = false;
    }
    
    private String a() {
        return this.a.a.settingsfile;
    }
    
    public final void a(final au a) {
        if (a.a.forceDebugMode) {
            this.u = true;
        }
        URL url = null;
        try {
            this.a = a;
            final URLConnection openConnection;
            (openConnection = (url = new URL(a.a.getCodeBase() + this.a())).openConnection()).setDoOutput(true);
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (line.trim().startsWith("//")) {
                    continue;
                }
                final int index;
                if ((index = line.indexOf("=")) <= 0) {
                    continue;
                }
                final String trim = line.substring(0, index).toUpperCase().trim();
                final String trim2 = line.substring(index + 1).trim();
                if (trim.equals("USEPROXY")) {
                    this.r = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("HIGHRESTHUMBNAILS")) {
                    this.i = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DEBUGMODE")) {
                    this.u = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLEMENU")) {
                    this.q = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLEDOWNLOADING")) {
                    this.o = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLEFRAMESKIP")) {
                    this.t = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("STARTDOWNLOADER")) {
                    this.l = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLERESET")) {
                    this.f = !trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLESAVERAM")) {
                    this.b = !trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLESAVESTATE")) {
                    this.c = !trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLESAVEIMAGE")) {
                    this.d = !trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLESAVEMOVIE")) {
                    this.e = !trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLETIMESHIFTBUFFER")) {
                    this.n = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("CLIPBORDERS")) {
                    this.a = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("DISABLEGAMEGENIECHANGES")) {
                    this.p = trim2.equalsIgnoreCase("TRUE");
                }
                else if (trim.equals("MOVIEMODE")) {
                    if (trim2.equalsIgnoreCase("ANY")) {
                        this.h = y.a;
                    }
                    else if (trim2.equalsIgnoreCase("FORCE_ANIMATED_GIF")) {
                        this.d = 1;
                        this.h = y.b;
                    }
                    else {
                        if (!trim2.equalsIgnoreCase("FORCE_KEYSTROKES")) {
                            continue;
                        }
                        this.h = y.c;
                        this.d = 0;
                    }
                }
                else if (trim.equals("MOVIEFORMAT") && this.h == y.a) {
                    if (trim2.equalsIgnoreCase("KEYSTROKES")) {
                        this.d = 0;
                    }
                    else {
                        if (!trim2.equalsIgnoreCase("ANIM_GIF")) {
                            continue;
                        }
                        this.d = 1;
                    }
                }
                else if (trim.equals("PROXYSERVER")) {
                    this.b = trim2;
                }
                else if (trim.equals("PROXYSERVERPORT")) {
                    this.c = trim2;
                }
                else if (trim.equals("PROXYSERVERUSERNAME")) {
                    this.d = trim2;
                }
                else if (trim.equals("PROXYSERVERPASSWORD")) {
                    this.e = trim2;
                }
                else if (trim.equals("JOYPAD_A")) {
                    try {
                        this.l = this.a(trim2);
                    }
                    catch (Exception ex2) {}
                }
                else if (trim.equals("JOYPAD_B")) {
                    try {
                        this.m = this.a(trim2);
                    }
                    catch (Exception ex3) {}
                }
                else if (trim.equals("JOYPAD_START")) {
                    try {
                        this.n = this.a(trim2);
                    }
                    catch (Exception ex4) {}
                }
                else if (trim.equals("JOYPAD_SELECT")) {
                    try {
                        this.o = this.a(trim2);
                    }
                    catch (Exception ex5) {}
                }
                else if (trim.equals("JOYPAD_UP")) {
                    try {
                        this.p = this.a(trim2);
                    }
                    catch (Exception ex6) {}
                }
                else if (trim.equals("JOYPAD_DOWN")) {
                    try {
                        this.q = this.a(trim2);
                    }
                    catch (Exception ex7) {}
                }
                else if (trim.equals("JOYPAD_LEFT")) {
                    try {
                        this.r = this.a(trim2);
                    }
                    catch (Exception ex8) {}
                }
                else if (trim.equals("JOYPAD_RIGHT")) {
                    try {
                        this.s = this.a(trim2);
                    }
                    catch (Exception ex9) {}
                }
                else if (trim.equals("SCREENSIZEFACTOR")) {
                    try {
                        this.a = Double.parseDouble(trim2);
                    }
                    catch (Exception ex10) {}
                    if (this.a < 1.0) {
                        this.a = 1.0;
                    }
                    if (this.a <= 3.0) {
                        continue;
                    }
                    this.a = 3.0;
                }
                else if (trim.equals("TIMESHIFTBUFFERINTERVAL")) {
                    try {
                        this.j = Integer.parseInt(trim2);
                    }
                    catch (Exception ex11) {}
                    if (this.j > 5) {
                        this.j = 5;
                    }
                    if (this.j >= 1) {
                        continue;
                    }
                    this.j = 1;
                }
                else if (trim.equals("IMAGEFORMAT")) {
                    if (trim2.trim().equalsIgnoreCase("PNG")) {
                        this.f = 1;
                    }
                    else {
                        if (!trim2.trim().equalsIgnoreCase("GIF")) {
                            continue;
                        }
                        this.f = 0;
                    }
                }
                else if (trim.equals("MOVIEMAXSIZE")) {
                    try {
                        this.g = Integer.parseInt(trim2);
                        this.j = true;
                    }
                    catch (Exception ex12) {}
                    if (this.g > 10485760) {
                        this.g = 10485760;
                    }
                    if (this.g >= 0) {
                        continue;
                    }
                    this.g = 0;
                }
                else {
                    if (!trim.equals("TIMESHIFTBUFFERLENGTH")) {
                        continue;
                    }
                    try {
                        this.i = Integer.parseInt(trim2);
                    }
                    catch (Exception ex13) {}
                    if (this.i > 300) {
                        this.i = 300;
                    }
                    if (this.i == 0) {
                        this.k = false;
                    }
                    else {
                        if (this.i < 10) {
                            this.i = 10;
                        }
                        this.k = true;
                    }
                }
            }
            dataInputStream.close();
        }
        catch (Exception ex) {
            a.a("Error occurred opening Settings file " + this.a() + ": " + ex.getMessage());
            final an an;
            (an = new an(11, a)).c = 8;
            an.a("The nescafe-settings.txt file couldnt be loaded,", "please ensure it is in the location below:", url.toString());
            a.a.a(an);
        }
    }
    
    private int a(final String s) throws Exception {
        if (s == null) {
            throw new Exception("bad");
        }
        try {
            final int int1;
            switch (int1 = Integer.parseInt(s.toUpperCase())) {
                case 8:
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
                case 192:
                case 520: {
                    this.s = true;
                    if (int1 >= 64 && int1 <= 128) {
                        this.f = "" + (char)int1;
                        break;
                    }
                    if (int1 == 8) {
                        this.f = "BACKSPACE";
                        break;
                    }
                    if (int1 == 520) {
                        this.f = "#";
                        break;
                    }
                    this.f = "" + int1;
                    break;
                }
            }
            return int1;
        }
        catch (Exception ex) {
            if (s.length() == 1) {
                return s.toUpperCase().charAt(0);
            }
            if (s.equalsIgnoreCase("space")) {
                return 32;
            }
            if (s.equalsIgnoreCase("enter")) {
                return 10;
            }
            if (s.equalsIgnoreCase("ctrl")) {
                return 17;
            }
            if (s.equalsIgnoreCase("shift")) {
                return 16;
            }
            if (s.equalsIgnoreCase("backspace")) {
                return 8;
            }
            if (s.equalsIgnoreCase("insert")) {
                return 155;
            }
            if (s.equalsIgnoreCase("home")) {
                return 36;
            }
            if (s.equalsIgnoreCase("pgup")) {
                return 33;
            }
            if (s.equalsIgnoreCase("pgdown")) {
                return 34;
            }
            if (s.equalsIgnoreCase("end")) {
                return 35;
            }
            if (s.equalsIgnoreCase("delete")) {
                return 127;
            }
            if (s.equalsIgnoreCase("/")) {
                return 47;
            }
            throw new Exception("Unknown Key Type Specified");
        }
    }
    
    public final void a() {
        try {
            final String property = System.getProperty("line.separator");
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String s = "ANY";
            if (this.h == y.b) {
                s = "FORCE_ANIMATED_GIF";
            }
            else if (this.h == y.c) {
                s = "FORCE_KEYSTROKES";
            }
            this.a(byteArrayOutputStream, "[Proxy]" + property);
            this.a(byteArrayOutputStream, "useproxy=" + (this.r ? "true" : "false") + property);
            this.a(byteArrayOutputStream, "proxyserver=" + this.b + property);
            this.a(byteArrayOutputStream, "proxyserverport=" + this.c + property);
            this.a(byteArrayOutputStream, "proxyserverusername=" + this.d + property);
            this.a(byteArrayOutputStream, "proxyserverpassword=" + this.e + property);
            if (this.u) {
                this.a(byteArrayOutputStream, "debugmode=" + this.u + property);
            }
            this.a(byteArrayOutputStream, property + "[TimeShiftBuffer]" + property);
            this.a(byteArrayOutputStream, "timeshiftbufferlength=" + this.i + property);
            this.a(byteArrayOutputStream, "timeshiftbufferinterval=" + this.j + property);
            this.a(byteArrayOutputStream, property + "[Graphics]" + property);
            this.a(byteArrayOutputStream, "HighResThumbnails=" + (this.i ? "true" : "false") + property);
            if (this.a >= 1.0) {
                this.a(byteArrayOutputStream, "ScreenSizeFactor=" + this.a + property);
            }
            this.a(byteArrayOutputStream, "MovieMaxSize=" + this.g + property);
            this.a(byteArrayOutputStream, "MovieMode=" + s + property);
            this.a(byteArrayOutputStream, "MovieFormat=" + ((this.d == 1) ? "ANIM_GIF" : "KEYSTROKES") + property);
            this.a(byteArrayOutputStream, "ImageFormat=" + ((this.f == 1) ? "PNG" : "GIF") + property);
            this.a(byteArrayOutputStream, "ClipBorders=" + this.a.a.a.a + property);
            this.a(byteArrayOutputStream, property + "[Controls]" + property);
            this.a(byteArrayOutputStream, "Joypad_A=" + this.l + property);
            this.a(byteArrayOutputStream, "Joypad_B=" + this.m + property);
            this.a(byteArrayOutputStream, "Joypad_Start=" + this.n + property);
            this.a(byteArrayOutputStream, "Joypad_Select=" + this.o + property);
            this.a(byteArrayOutputStream, "Joypad_Up=" + this.p + property);
            this.a(byteArrayOutputStream, "Joypad_Down=" + this.q + property);
            this.a(byteArrayOutputStream, "Joypad_Left=" + this.r + property);
            this.a(byteArrayOutputStream, "Joypad_Right=" + this.s + property);
            this.a(byteArrayOutputStream, property + "[Startup]" + property);
            this.a(byteArrayOutputStream, "StartDownloader=" + (this.l ? "true" : "false") + property);
            this.a(byteArrayOutputStream, property + "[Security]" + property);
            this.a(byteArrayOutputStream, "DisableDownloading=" + (this.o ? "true" : "false") + property);
            this.a(byteArrayOutputStream, "DisableFrameSkip=" + (this.t ? "true" : "false") + property);
            this.a(byteArrayOutputStream, "DisableReset=" + (this.f ? "false" : "true") + property);
            this.a(byteArrayOutputStream, "DisableSaveRAM=" + (this.b ? "false" : "true") + property);
            this.a(byteArrayOutputStream, "DisableSaveState=" + (this.c ? "false" : "true") + property);
            this.a(byteArrayOutputStream, "DisableSaveImage=" + (this.d ? "false" : "true") + property);
            this.a(byteArrayOutputStream, "DisableSaveMovie=" + (this.e ? "false" : "true") + property);
            this.a(byteArrayOutputStream, "DisableTimeShiftBuffer=" + (this.n ? "true" : "false") + property);
            this.a(byteArrayOutputStream, "DisableGameGenieChanges=" + (this.p ? "true" : "false") + property);
            this.a(byteArrayOutputStream, "DisableMenu=" + (this.q ? "true" : "false") + property);
            byteArrayOutputStream.close();
            this.a.a.saveSettings(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {}
    }
    
    private void a(final OutputStream outputStream, final String s) throws Exception {
        outputStream.write(s.getBytes());
    }
    
    static {
        y.a = 0;
        y.b = 1;
        y.c = 2;
    }
}
