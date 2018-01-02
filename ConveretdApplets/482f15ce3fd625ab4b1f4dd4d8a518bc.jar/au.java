import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class au
{
    public ae a;
    public y a;
    public aa a;
    public aa b;
    public ab a;
    public NESCafeApplet a;
    public aE a;
    public at a;
    public ap a;
    public ag a;
    public O a;
    public v a;
    public boolean a;
    public String a;
    public String b;
    public int a;
    public int b;
    public boolean b;
    
    public au() {
        this.a = null;
        this.a = new y();
        this.a = null;
        this.a = new v();
        this.a = true;
        this.a = "";
        this.b = "";
        this.a = 255;
        this.b = 0;
        this.b = false;
        this.a = true;
        this.a = "";
    }
    
    public final void a(final aE a) {
        this.a = a;
        if (this.a.forceDebugMode && this.a != null) {
            this.a.u = true;
        }
        this.a.a(this);
        this.a("======================================================");
        this.a("Debug Mode Enabled in Settings File");
        this.a("Disable Debug Mode in Live Systems");
        this.a("======================================================");
        this.a = new aa(1, this);
        this.b = new aa(2, this);
        this.a = new ab(this, a);
        this.a = new ag(this, a);
        this.a = new ap(this, a);
        this.a = new ae(this);
        for (int i = 0; i < 20; ++i) {
            if (i == 16) {
                this.a.a(16400, (short)16);
            }
            else {
                this.a.a(16384 + i, (short)0);
            }
        }
        this.a.a();
        this.a = new O(this);
    }
    
    public final boolean a(final String s) {
        if (this.a.s) {
            this.a.a("You are using reserved key '" + this.a.f + "' for your controls", true);
        }
        if (this.a.e) {
            final an an = new an(12, this);
            this.a.a(true);
            an.c = 8;
            an.a("Cannot load a new ROM whilst recording a movie.");
            this.a.a(an);
            return false;
        }
        if (this.a.f) {
            final an an2 = new an(12, this);
            this.a.a(true);
            an2.c = 8;
            an2.a("Cannot close a ROM whilst playing a movie.");
            this.a.a(an2);
            return false;
        }
        this.a.a(false);
        final boolean g = this.a.g;
        if (this.a != null) {
            this.a.g = false;
        }
        this.a.a(s);
        if (this.a != null) {
            this.a.g = g;
        }
        return true;
    }
    
    public final boolean b(final String s) {
        this.a.a.d();
        try {
            this.a.a(s);
        }
        catch (aO ao) {
            this.a.a.a(-1.0f);
            this.a(this.b = ao.a);
            an an;
            if (this.a.a()) {
                an = new an(12, this);
            }
            else {
                an = new an(12, this);
            }
            an.c = 1;
            if (ao.c != null) {
                an.a(this.b, ao.b, ao.c);
            }
            else if (ao.b != null) {
                an.a(this.b, ao.b);
            }
            else {
                an.a(this.b);
            }
            this.a.a(an);
            return false;
        }
        this.a.d();
        ((ae)(this.a = true)).g();
        this.a.a(false);
        this.a.c();
        this.a = false;
        this.a.a.a(-1.0f);
        if (this.a.equals("")) {
            this.a = "nescafe.nes";
        }
        return true;
    }
    
    public final void a() {
        if (this.a != null && this.a.a != null) {
            this.a.a.b = false;
            this.a.a = null;
        }
        if (this.a) {
            return;
        }
        if (this.a != null) {
            this.a.d();
        }
        if (this.a != null) {
            this.a.b();
        }
        this.a = true;
        this.a = "";
        System.gc();
    }
    
    public final boolean a() {
        return !this.a;
    }
    
    public final void b() {
        this.a.i();
        this.a.g();
    }
    
    public final void c() {
        this.a.a = false;
        String s;
        for (s = "" + (this.a.a + 1); s.length() < 3; s = "0" + s) {}
        if (this.a.loadStateOnStartup != null && this.a.loadStateOnStartupTrigger) {
            s = "00";
        }
        try {
            final DataInputStream appletLoadSaveState;
            if ((appletLoadSaveState = this.a.AppletLoadSaveState(s)) == null) {
                throw new Exception("Failed to load the NESCafe State file");
            }
            try {
                final GZIPInputStream gzipInputStream = new GZIPInputStream(appletLoadSaveState);
                try {
                    this.b(gzipInputStream);
                    appletLoadSaveState.close();
                }
                catch (Exception ex2) {}
            }
            catch (EOFException ex3) {
                appletLoadSaveState.close();
                throw new Exception("Could not read the State file");
            }
            catch (Exception ex4) {
                appletLoadSaveState.close();
                DataInputStream appletLoadSaveState2;
                try {
                    appletLoadSaveState2 = this.a.AppletLoadSaveState(s);
                }
                catch (Exception ex5) {
                    throw new Exception("Could not read the State file");
                }
                if (appletLoadSaveState2 == null) {
                    throw new Exception(this.a.getParameter("LOADSTATE"));
                }
                int n = 0;
                final int[] array = new int[2097152];
                String line;
                while ((line = appletLoadSaveState2.readLine()) != null) {
                    String substring;
                    int n2;
                    if (line.indexOf(":") > 1) {
                        substring = line.substring(line.indexOf(":") + 1);
                        n2 = Integer.parseInt(line.substring(0, line.indexOf(":")));
                    }
                    else {
                        n2 = (substring = line).length();
                    }
                    if (substring.length() % 2 == 0 && n2 == substring.length()) {
                        for (int i = 0; i < substring.length() / 2; ++i) {
                            array[n++] = aK.a(substring.substring(i * 2, i * 2 + 2));
                        }
                    }
                    else {
                        this.a.a("Invalid File Format", true);
                    }
                }
                appletLoadSaveState2.close();
                final byte[] array2 = new byte[n];
                for (int j = 0; j < n; ++j) {
                    array2[j] = (byte)array[j];
                }
                if (array2.length < 16) {
                    throw new Exception("Invalid Signature in State File");
                }
                if (!new String(array2, 0, 16).startsWith("NSAVESTE")) {
                    throw new Exception("Invalid State File");
                }
                final int n3 = array2[8] * 256 + array2[9];
                final int n4 = array2[10] * 256 + array2[11];
                final long n5 = 0L + ((array2[12] + 256 & 0xFF) << 24) + ((array2[13] + 256 & 0xFF) << 16) + ((array2[14] + 256 & 0xFF) << 8) + ((array2[15] + 256 & 0xFF) << 0);
                if (n3 > 2) {
                    throw new Exception("This version of NESCafe is too old to read these State Files");
                }
                if (n4 != 1 && n4 != 2) {
                    throw new Exception("Unknown of Unsupported Compression Format");
                }
                if (n5 > 10485760L) {
                    throw new Exception("State File too large to load into NESCafe");
                }
                final byte[] array3 = new byte[(int)n5];
                for (int k = 0; k < (int)n5; ++k) {
                    array3[k] = array2[k + 16];
                }
                byte[] array4;
                if (n4 == 1) {
                    array4 = aK.c(array3);
                }
                else {
                    array4 = aK.b(array3);
                }
                if (array4 != null) {
                    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array4);
                    if (n3 == 1) {
                        this.a.a(byteArrayInputStream);
                        this.a.a(byteArrayInputStream);
                        this.a.a(byteArrayInputStream);
                        byteArrayInputStream.read();
                        this.a.a(byteArrayInputStream);
                        this.a.a(byteArrayInputStream);
                        this.b.a(byteArrayInputStream);
                    }
                    else if (n3 == 2) {
                        this.a(byteArrayInputStream);
                    }
                    byteArrayInputStream.close();
                    if (!this.a.f) {
                        this.a.b("State Successfully Loaded...");
                    }
                    return;
                }
                throw new Exception("Invalid Compression Data in State File");
            }
        }
        catch (Exception ex) {
            final an an = new an(12, this);
            this.a.a(true);
            an.c = 2;
            an.a(ex.getMessage());
            this.a.a(an);
        }
    }
    
    public final void a(final InputStream inputStream) throws Exception {
        final byte[] array = new byte[3];
        while (inputStream.read(array) == 3) {
            final String s = new String(array);
            final int b = aK.b(inputStream);
            if (s.equals("CPU")) {
                this.a.a(inputStream);
            }
            else if (s.equals("MEM")) {
                this.a.a(inputStream);
            }
            else if (s.equals("PPU")) {
                this.a.a(inputStream);
            }
            else if (s.equals("JY1")) {
                this.a.a(inputStream);
            }
            else if (s.equals("JY2")) {
                this.b.a(inputStream);
            }
            else if (s.equals("MAP")) {
                inputStream.read();
                this.a.a(inputStream);
            }
            else if (s.equals("APU")) {
                inputStream.skip(b);
            }
            else if (s.equals("SND")) {
                this.a.a(inputStream);
            }
            else if (s.equals("IMG")) {
                inputStream.skip(b);
            }
            else {
                if (!s.equals("MOV")) {
                    inputStream.close();
                    throw new Exception("The State file is corrupt");
                }
                final byte[] array2 = new byte[b];
                int n = 0;
                int n2 = b;
                for (int n3 = inputStream.read(array2, 0, n2); n3 > 0 && n2 > 0; n2 -= n3, n3 = inputStream.read(array2, n, n2)) {
                    n += n3;
                }
                if (n2 > 0) {
                    throw new Exception("The Movie stored in the State is corrupt");
                }
                this.a.a(array2);
            }
        }
    }
    
    private final void b(final InputStream inputStream) {
        try {
            final byte[] array = new byte[7];
            if (inputStream.read(array, 0, 7) != 7 || !new String(array).equals("NESCafe")) {
                throw new aO("Invalid Save-State File Format.");
            }
            if (inputStream.read() > 1) {
                throw new aO("File Format is newer than supported by this version of NESCafe.");
            }
            this.a.a(inputStream);
            this.a.a(inputStream);
            this.a.a(inputStream);
            inputStream.read();
            this.a.a(inputStream);
            this.a.a(inputStream);
            this.b.a(inputStream);
            inputStream.close();
        }
        catch (aO ao) {
            final an an = new an(12, this);
            this.a.a(true);
            an.c = 2;
            an.a(ao.a);
            this.a.a(an);
            return;
        }
        catch (IOException ex) {
            final an an2 = new an(12, this);
            this.a.a(true);
            an2.c = 2;
            an2.a("The NESCafe State could not be read");
            this.a.a(an2);
            return;
        }
        this.a.b("State Successfully Loaded...");
    }
    
    public final byte[] a() {
        return this.a(false, true, false);
    }
    
    public final byte[] a(final boolean b, final boolean b2, final boolean b3) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final boolean b4 = this.a.a != null;
            this.a.a(byteArrayOutputStream);
            if (!b4 || b) {
                if (this.a == null) {
                    throw new Exception("NES.stateSaveData() Failed : Memory Not Available");
                }
                this.a.a(byteArrayOutputStream);
                if (this.a == null) {
                    throw new Exception("NES.stateSaveData() Failed : PPU Not Available");
                }
                this.a.a(byteArrayOutputStream);
                if (this.a == null) {
                    throw new Exception("NES.stateSaveData() Failed : Mapper Not Available");
                }
                this.a.a(byteArrayOutputStream);
                if (this.a == null) {
                    throw new Exception("NES.stateSaveData() Failed : JoyPad1 Not Available");
                }
                this.a.a(byteArrayOutputStream);
                if (this.b == null) {
                    throw new Exception("NES.stateSaveData() Failed : JoyPad2 Not Available");
                }
                this.b.a(byteArrayOutputStream);
                this.a.a(byteArrayOutputStream);
                if (b2) {
                    byte[] a = null;
                    try {
                        a = this.a.a.a(b3);
                    }
                    catch (Exception ex2) {
                        this.a("NES.stateSaveData() failed : getScreenThumbnailImage() crashed");
                    }
                    if (a != null) {
                        byteArrayOutputStream.write("IMG".getBytes());
                        aK.a(byteArrayOutputStream, a.length);
                        byteArrayOutputStream.write(a);
                    }
                    else {
                        this.a("Failed to create Thumbnail Image : NULL returned");
                    }
                }
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception ex) {
            this.a("Error occurred in NES.stateSaveData()");
            this.a(ex.toString());
            return null;
        }
    }
    
    public final void d() {
        try {
            this.a(this.a());
        }
        catch (Exception ex) {
            final an an = new an(12, this);
            this.a.a(true);
            an.c = 4;
            an.a("State Failed to Save");
            this.a.a(an);
        }
    }
    
    public final void a(final String s) {
        if (this.a.u) {
            System.out.println("DEBUG: " + s);
        }
    }
    
    public final void a(final byte[] array) throws Exception {
        this.a.b = false;
        final byte[] a;
        if ((a = aK.a(array)) == null) {
            this.a("Failed to compresss Data");
            throw new Exception("Failed");
        }
        final byte[] array2 = new byte[16 + a.length];
        final byte[] bytes = "NSAVESTE".getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            array2[i] = bytes[i];
        }
        array2[8] = 0;
        array2[9] = 2;
        array2[10] = 0;
        array2[11] = 2;
        array2[12] = (byte)((a.length & 0xFF000000) >> 24 & 0xFF);
        array2[13] = (byte)((a.length & 0xFF0000) >> 16 & 0xFF);
        array2[14] = (byte)((a.length & 0xFF00) >> 8 & 0xFF);
        array2[15] = (byte)((a.length & 0xFF) >> 0 & 0xFF);
        for (int j = 0; j < a.length; ++j) {
            array2[j + 16] = a[j];
        }
        String s;
        if (this.a.e && this.a.g) {
            s = "005";
        }
        else {
            for (s = "" + (this.a.a + 1); s.length() < 3; s = "0" + s) {}
        }
        if (!this.a.AppletSaveState(array2, s)) {
            this.a.a("State/Movie Failed to be Saved...", true);
            final an an = new an(12, this);
            this.a.a(true);
            an.c = 4;
            an.a("State/Movie Failed to Save", this.a.lastBroadcastError);
            this.a.a(an);
            return;
        }
        this.a.b("State/Movie Successfully Saved into Slot " + s);
        final an an2 = new an(12, this);
        this.a.a(true);
        an2.c = 10;
        an2.a("State/Movie has been saved into Slot " + s);
        this.a.a(an2);
    }
}
