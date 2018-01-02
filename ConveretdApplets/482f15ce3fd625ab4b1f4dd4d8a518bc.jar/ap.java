import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ap
{
    public au a;
    public ag a;
    private aE a;
    public int[] a;
    public int[] b;
    public int[] c;
    public int[] d;
    public boolean a;
    private int[] e;
    private boolean d;
    public aH a;
    public boolean b;
    public int a;
    public int b;
    public int c;
    public boolean c;
    public am a;
    public int d;
    
    public final void a() {
        this.a.a("SaveRAM wiped");
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = 0;
        }
    }
    
    public ap(final au a, final aE a2) {
        this.a = new int[2048];
        this.b = null;
        this.c = new int[65536];
        this.d = new int[65536];
        this.a = true;
        this.e = new int[16];
        this.d = false;
        this.a = new aH();
        this.b = false;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.c = false;
        this.a = null;
        this.d = 1;
        this.a = a2;
        this.a = a;
        this.a = a.a;
        for (int i = 0; i < this.e.length; ++i) {
            this.e[i] = -1;
        }
    }
    
    public final void a(final aC ac, final String s) {
        this.a.a("MemoryManager.init with file '" + s + "'");
        this.b = ac.a;
        this.a.a.a(ac.b, ac.a, ac.b);
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = 0;
        }
        if (ac.c && ac.c != null) {
            this.a.a("Game has Trainer ROM");
            for (int j = 0; j < 512; ++j) {
                this.c[4096 + j] = ac.c[j];
            }
        }
        else {
            for (int k = 0; k < 512; ++k) {
                this.c[4096 + k] = 0;
            }
        }
        this.a.a = 255;
        this.a.b = 0;
        if (ac.d) {
            this.a.a("Game has SaveRAM");
            try {
                this.d = false;
                if (s != null) {
                    this.d = this.a(s);
                }
            }
            catch (Exception ex) {}
        }
        else {
            this.d = false;
        }
        if (this.d) {
            this.a.a("SaveRam Loaded Successfully");
        }
        else {
            this.a.a("SaveRam was not Loaded, so will not be Saved");
        }
        this.a.a();
        this.b = false;
        final StringBuffer sb = new StringBuffer();
        for (int l = 0; l < 8; ++l) {
            sb.append((char)this.b[l]);
        }
        this.c = sb.toString().equals("NESCafe ");
        this.a = new am(this.a, this.a.a.a.a);
        if (!this.a.a) {
            this.a = null;
        }
    }
    
    public final boolean a(final String s) throws Exception {
        try {
            for (int i = 0; i < this.c.length; ++i) {
                this.c[i] = 0;
            }
            final DataInputStream appletLoadSaveRAM;
            if ((appletLoadSaveRAM = this.a.a.AppletLoadSaveRAM()) == null) {
                this.a.a("InputStream to SaveRAM is not available");
                return false;
            }
            int n = 0;
            int n2 = 0;
            final int[] array = new int[1048576];
            String line;
            while ((line = appletLoadSaveRAM.readLine()) != null) {
                this.a.a("Response: " + line);
                if (line.trim().equals("")) {
                    continue;
                }
                if (line.indexOf("<") >= 0) {
                    final an an;
                    (an = new an(12, this.a)).c = 14;
                    an.a("NESCafe attempted to load SaveRAM from the", "server but the website inserted HTML into it.", "Please let an administrator know.");
                    this.a.a(an);
                    appletLoadSaveRAM.close();
                    return false;
                }
                if (line.equals("NOSAVERAM")) {
                    this.a.a("No SaveRAM found on Server");
                    appletLoadSaveRAM.close();
                    return true;
                }
                String substring;
                int n3;
                if (line.indexOf(":") > 1) {
                    substring = line.substring(line.indexOf(":") + 1);
                    n3 = Integer.parseInt(line.substring(0, line.indexOf(":")));
                }
                else {
                    n3 = (substring = line).length();
                }
                if (substring.length() % 2 != 0 || n3 != substring.length()) {
                    this.a.a("Invalid SaveRam File on Server, first line:");
                    this.a.a(line);
                    appletLoadSaveRAM.close();
                    throw new aO("Line " + (n + 1) + " of the SaveRAM file is corrupt.");
                }
                for (int j = 0; j < substring.length() / 2; ++j) {
                    array[n2++] = aK.a(substring.substring(j * 2, j * 2 + 2));
                }
                ++n;
            }
            appletLoadSaveRAM.close();
            final byte[] array2 = new byte[n2];
            for (int k = 0; k < n2; ++k) {
                array2[k] = (byte)array[k];
            }
            if (array2.length < 16) {
                this.a.a("Length of Uncompressed SaveRAM data = " + array2.length);
                throw new aO("SaveRAM file is corrupt, please delete this file.");
            }
            final String s2;
            if (!(s2 = new String(array2, 0, 16)).startsWith("NSAVERAM")) {
                this.a.a("Invalid signature to SaveRAM data '" + s2 + "'");
                throw new aO("SaveRAM file is invalid, likely to be the wrong file.");
            }
            final int n4 = array2[8] * 256 + array2[9];
            final int n5 = array2[10] * 256 + array2[11];
            final long n6 = 0L + ((array2[12] + 256 & 0xFF) << 24) + ((array2[13] + 256 & 0xFF) << 16) + ((array2[14] + 256 & 0xFF) << 8) + ((array2[15] + 256 & 0xFF) << 0);
            if (n4 > 2) {
                this.a.a("Cannot Load Future Save State File Format, version = " + n4);
                throw new aO("SaveRAM file is not compatible with NESCafe v1.02");
            }
            if (n5 != 1 && n5 != 2) {
                this.a.a("Unknown or unsupported Compression Format, compression = " + n5);
                throw new aO("SaveRAM file contains unknown compression format.");
            }
            if (n6 > 1048576L) {
                this.a.a("Size of data too large to uncompress, size = " + n6);
                throw new aO("SaveRAM file is corrupted, compression unreadable.");
            }
            final byte[] array3 = new byte[(int)n6];
            for (int l = 0; l < (int)n6; ++l) {
                array3[l] = array2[l + 16];
            }
            byte[] array4;
            if (n5 == 1) {
                array4 = aK.c(array3);
            }
            else if (n5 == 2) {
                array4 = aK.b(array3);
            }
            else {
                array4 = null;
            }
            if (array4 == null) {
                this.a.a("Failed to uncompress SaveRAM data");
                throw new aO("SaveRAM file is corrupt, this data may be lost");
            }
            if (n4 == 1) {
                if (array4.length == 65536) {
                    for (int n7 = 0; n7 < array4.length; ++n7) {
                        this.c[n7] = (array4[n7] & 0xFF);
                    }
                    this.a.a("SaveRAM data successfully uncompressed and loaded");
                    return true;
                }
            }
            else {
                if (n4 == 2) {
                    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array4);
                    final byte[] array5 = new byte[3];
                    while (byteArrayInputStream.read(array5) == 3) {
                        final String s3 = new String(array5);
                        final int b = aK.b(byteArrayInputStream);
                        if (s3.equals("SAV")) {
                            if (b != 65536) {
                                throw new aO("SaveRAM file contains only partial content.");
                            }
                            for (int n8 = 0; n8 < this.c.length; ++n8) {
                                this.c[n8] = aK.c(byteArrayInputStream);
                            }
                            this.a.a("SaveRAM Loaded");
                            byteArrayInputStream.close();
                            return true;
                        }
                        else if (s3.equals("IMG")) {
                            this.a.a("IMG Block Found in SaveRAM");
                            byteArrayInputStream.skip(b);
                        }
                        else {
                            this.a.a("Unknown Block Found in SaveRAM");
                            byteArrayInputStream.skip(b);
                        }
                    }
                    byteArrayInputStream.close();
                    throw new aO("SaveRAM block missing from SaveRAM file");
                }
                throw new aO("SaveRAM file version is unknown");
            }
        }
        catch (aO ao) {
            this.a.a("Error occurred when processing SaveRAM data - " + ao.a);
            final an an2;
            (an2 = new an(12, this.a)).c = 14;
            an2.a("An error occurred whilst trying to load SaveRAM.", ao.a, "SaveRAM will not be saved during this session.");
            this.a.a(an2);
        }
        catch (Exception ex) {
            this.a.a("Error occurred when processing SaveRAM data - " + ex.getMessage());
            final an an3;
            (an3 = new an(12, this.a)).c = 14;
            an3.a("An error occurred whilst trying to load SaveRAM.", "SaveRAM will not be saved during this session.");
            this.a.a(an3);
        }
        return false;
    }
    
    public final void b() {
        this.a.a("SaveRAM Loaded:" + this.d);
        if (!this.d) {
            return;
        }
        if (!this.a.a.b) {
            this.a.a("Not allowed to Save SaveRAM");
            return;
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream;
            (byteArrayOutputStream = new ByteArrayOutputStream()).write("SAV".getBytes());
            aK.a(byteArrayOutputStream, this.a.a.c.length);
            for (int i = 0; i < this.c.length; ++i) {
                byteArrayOutputStream.write(this.c[i] & 0xFF);
            }
            final byte[] a = this.a.a.a(false);
            byteArrayOutputStream.write("IMG".getBytes());
            aK.a(byteArrayOutputStream, a.length);
            byteArrayOutputStream.write(a);
            final byte[] a2 = aK.a(byteArrayOutputStream.toByteArray());
            final byte[] array = new byte[16 + a2.length];
            final byte[] bytes = "NSAVERAM".getBytes();
            for (int j = 0; j < bytes.length; ++j) {
                array[j] = bytes[j];
            }
            array[8] = 0;
            array[9] = 2;
            array[10] = 0;
            array[11] = 2;
            array[12] = (byte)((a2.length & 0xFF000000) >> 24 & 0xFF);
            array[13] = (byte)((a2.length & 0xFF0000) >> 16 & 0xFF);
            array[14] = (byte)((a2.length & 0xFF00) >> 8 & 0xFF);
            array[15] = (byte)((a2.length & 0xFF) >> 0 & 0xFF);
            for (int k = 0; k < a2.length; ++k) {
                array[k + 16] = a2[k];
            }
            this.a.a.AppletSaveSaveRAM(array);
        }
        catch (Exception ex) {
            this.a.a("An error occurred whilst attempted to save the SaveRAM - " + ex.getMessage());
        }
    }
    
    public final void a(final int n, final int n2) {
        this.e[n % this.e.length] = n2 % this.b.length;
    }
    
    public final int a(final int n) {
        if (n >= 32768) {
            final int a;
            if (this.b && (a = this.a.a(n)) >= 0) {
                final int c;
                if ((c = this.a.c(a)) == -1) {
                    return this.a.b(a);
                }
                final int n2;
                if ((n2 = this.b[this.e[(n & 0xF000) >> 12] + (n & 0xFFF)]) == c) {
                    return this.a.b(a);
                }
                return n2;
            }
            else {
                try {
                    return this.b[this.e[(n & 0xF000) >> 12] + (n & 0xFFF)];
                }
                catch (Exception ex) {
                    return 0;
                }
            }
        }
        if (n < 8192) {
            return this.a[n & 0x7FF];
        }
        if (n < 16384) {
            return this.a.a.a(n & 0xE007);
        }
        if (n < 16408) {
            if (n == 16405 && (this.a.a & 0xC0) == 0x0) {
                return this.a.a.a(n);
            }
            if (n == 16406) {
                return this.a.a.a();
            }
            if (n == 16407) {
                return this.a.b.a() | this.c(2);
            }
            return 0;
        }
        else {
            if (n < 24576) {
                return this.a.a.b(n);
            }
            if (n < 32768) {
                return this.c[n - 24576];
            }
            return 0;
        }
    }
    
    public final void b(final int n, final int a) {
        if (n < 8192) {
            if (this.a != null) {
                this.a.a(n, a);
            }
            this.a[n & 0x7FF] = a;
            return;
        }
        if (n < 16384) {
            this.a.a.a(n & 0xE007, a);
            return;
        }
        if (n < 16408) {
            this.a.a.b(n, a);
            switch (n) {
                case 16384:
                case 16385:
                case 16386:
                case 16387:
                case 16388:
                case 16389:
                case 16390:
                case 16391:
                case 16392:
                case 16393:
                case 16394:
                case 16395:
                case 16396:
                case 16397:
                case 16398:
                case 16399:
                case 16400:
                case 16401:
                case 16402:
                case 16403: {
                    this.a.a.a(n, (short)a);
                }
                case 16404: {
                    final int n2;
                    int[] array = null;
                    int n3 = 0;
                    switch ((n2 = a << 8) & 0xF000) {
                        case 0:
                        case 4096: {
                            array = this.a;
                            n3 = (n2 & 0x7FF);
                            break;
                        }
                        case 32768: {
                            array = this.b;
                            n3 = this.e[(n2 >> 12) + (n2 & 0xFFF)];
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                    for (int i = 0; i < 256; ++i) {
                        this.a.a.b[i] = (array[n3] & 0xFF);
                        ++n3;
                    }
                    this.a.a.a(514);
                }
                case 16405: {
                    this.a.a.a(n, (short)a);
                }
                case 16406: {
                    if ((a & 0x1) == 0x0) {
                        this.a.a.a();
                    }
                }
                case 16407: {
                    if ((a & 0x1) == 0x0) {
                        this.a.b.a();
                    }
                    if (this.a.b == 0) {
                        this.a.a = a;
                    }
                    this.a.a.a(n, (short)a);
                }
                default: {}
            }
        }
        else {
            if (n < 24576) {
                this.a.a.b(n, a);
                return;
            }
            if (n < 32768) {
                this.a.a.a(n, a);
                if (this.a) {
                    this.c[n - 24576] = a;
                }
                return;
            }
            this.a.a.a(n, a);
        }
    }
    
    public final int b(final int n) {
        return this.a(n) | this.a(n + 1) << 8;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = (inputStream.read() & 0xFF);
        }
        for (int j = 0; j < this.c.length; ++j) {
            this.c[j] = (inputStream.read() & 0xFF);
        }
        for (int k = 0; k < this.e.length; ++k) {
            this.e[k] = (inputStream.read() & 0xFF) << 0;
            final int[] e = this.e;
            final int n = k;
            e[n] |= (inputStream.read() & 0xFF) << 8;
            final int[] e2 = this.e;
            final int n2 = k;
            e2[n2] |= (inputStream.read() & 0xFF) << 16;
            final int[] e3 = this.e;
            final int n3 = k;
            e3[n3] |= (inputStream.read() & 0xFF) << 24;
        }
        this.a = (inputStream.read() == 255);
        this.d = (inputStream.read() == 255);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("MEM".getBytes());
        aK.a(outputStream, this.a.length + this.c.length + this.e.length * 4 + 2);
        for (int i = 0; i < this.a.length; ++i) {
            outputStream.write(this.a[i] & 0xFF);
        }
        for (int j = 0; j < this.c.length; ++j) {
            outputStream.write(this.c[j] & 0xFF);
        }
        for (int k = 0; k < this.e.length; ++k) {
            outputStream.write(this.e[k] >> 0 & 0xFF);
            outputStream.write(this.e[k] >> 8 & 0xFF);
            outputStream.write(this.e[k] >> 16 & 0xFF);
            outputStream.write(this.e[k] >> 24 & 0xFF);
        }
        outputStream.write(this.a ? 255 : 0);
        outputStream.write(this.d ? 255 : 0);
    }
    
    private int c(final int n) {
        if (!this.a.a.g) {
            return 0;
        }
        final int n2 = this.a.a.c[this.c * 256 + this.b];
        final int n3 = this.a + 8;
        if (n2 == 32 || n2 == 48) {
            return n3 & 0xF7;
        }
        return n3;
    }
}
