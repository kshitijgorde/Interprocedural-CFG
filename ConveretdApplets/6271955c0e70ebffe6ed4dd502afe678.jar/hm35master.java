import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Image;
import java.util.Hashtable;
import java.net.URL;
import java.io.DataInputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class hm35master extends Applet implements Runnable
{
    public static boolean iq;
    public Thread ip;
    public boolean io;
    public DataInputStream im;
    public int il;
    public hm35player ik;
    public hm35player ij;
    public Applet ii;
    public boolean ih;
    public URL ig;
    public String if;
    public String ie;
    public char ic;
    public String ib;
    public boolean ia;
    public boolean h9;
    public boolean h8;
    public hm35master h7;
    public URL h6;
    public boolean h5;
    public boolean h4;
    public int h3;
    public boolean h2;
    public hm35player h1;
    public boolean h0;
    public boolean h_;
    public boolean hz;
    public long hy;
    public boolean hx;
    public long hw;
    public int hv;
    public boolean hu;
    public short ht;
    public boolean hs;
    public hm35player[] hr;
    public Hashtable hq;
    
    public final String ac(final byte[] array) {
        if ((array[0] == -1 && array[1] == -2) || (array[0] == -2 && array[1] == -1)) {
            final short n = (short)(array.length / 2 - 2);
            final char[] array2 = new char[n];
            for (short n2 = 0; n2 < n; ++n2) {
                array2[n2] = (char)(((array[2 * n2 + 2] & 0xFF) << 8) + (array[2 * n2 + 3] & 0xFF));
            }
            return new String(array2);
        }
        return new String(array, 0, 0, array.length - 1);
    }
    
    public final int a6() {
        final Object[] array = { new Integer(3), null };
        this.ii.equals(array);
        return (int)array[1];
    }
    
    public final void a7(final int n) {
        this.ii.equals(new Object[] { new Integer(2), new Integer(n) });
    }
    
    public final Image a8(final int n, final byte[] array) {
        final Object[] array2 = { new Integer(0), new Integer(n), array };
        this.ii.equals(array2);
        return (Image)array2[1];
    }
    
    public final void a9(final String s) {
        try {
            this.ii.equals(new Object[] { new Integer(4), new URL(this.ii.getCodeBase().getProtocol(), this.ii.getCodeBase().getHost(), this.ii.getCodeBase().getPort(), s) });
        }
        catch (Exception ex) {}
    }
    
    public final void ba(final hm35player hm35player, final int n, final int n2) {
        if ((n & 0x400) != 0x0) {
            this.h2 = ((n2 & 0x400) == 0x0);
            if (this.h2 && this.h7 != null && this.h7.h1 != null) {
                this.h7.h1.play(0);
            }
        }
        if (this.ij != null) {
            this.ij.setSensitive(hm35player, n, n2);
        }
    }
    
    public final void bb(final hm35player hm35player, int n, final int n2) {
        if (this.ij != null) {
            int n3 = (this.il > 0) ? 1 : 0;
            if ((this.h3 & 0x2F800) != 0x0) {
                n |= (this.h3 & 0x2F800);
                n3 |= 0x1;
            }
            if (hm35player == this.h1 && n == 0 && n2 == 0) {
                n3 |= 0x1;
            }
            if (n3 != 0) {
                this.ij.setGUI(hm35player, n, n2);
            }
        }
    }
    
    private final void bc(final int n) {
        if (n >= 99) {
            this.ik = null;
            this.ij = null;
            this.h1 = null;
        }
        final Enumeration<hm35player> elements = this.hq.elements();
        while (elements.hasMoreElements()) {
            final hm35player hm35player = elements.nextElement();
            switch (n) {
                case 0: {
                    hm35player.init(this);
                    continue;
                }
                case 1: {
                    hm35player.start();
                    continue;
                }
                case 2: {
                    hm35player.stop();
                    continue;
                }
                case 3: {
                    hm35player.play(2);
                    continue;
                }
                default: {
                    hm35player.destroy();
                    this.ii.remove(hm35player);
                    continue;
                }
            }
        }
        if (n >= 99) {
            this.hq.clear();
            this.ih = false;
            this.ia = false;
            this.h9 = true;
            this.ii.validate();
        }
    }
    
    private final hm35player bd(final int n, final String s) {
        hm35player hm35player = null;
        if (s != null) {
            final String string = String.valueOf(s) + String.valueOf(n);
            hm35player = (hm35player)this.hq.get(string);
            if (hm35player == null) {
                try {
                    hm35player = this.be(s).newInstance();
                    final int init = hm35player.init(this);
                    if (init < 0) {
                        hm35player = null;
                    }
                    else {
                        if (this.ij != null && !this.ih) {
                            final int height = this.ii.size().height;
                            this.ii.add(this.ij, 0);
                            this.ij.reshape(0, height - 1, 1, 1);
                            this.ih = true;
                            this.bb(null, this.h3, 0);
                        }
                        if (init == 0 && n != 99) {
                            this.bg(hm35player);
                        }
                        hm35player.start();
                        this.hq.put(string, hm35player);
                    }
                }
                catch (Exception ex) {}
            }
        }
        return hm35player;
    }
    
    private final Class be(final String s) {
        final Object[] array = { new Integer(5), s };
        this.ii.equals(array);
        final Class clazz = (Class)array[1];
        if (clazz == null) {
            throw new Exception();
        }
        return clazz;
    }
    
    private final String bf(final int n, final int n2) {
        String concat = null;
        switch (n) {
            case 3: {
                concat = "animator";
                break;
            }
            case 4: {
                if (n2 == 1) {
                    concat = "gsm";
                }
                if (n2 == 4) {
                    concat = "g723";
                }
                if (n2 == 8) {
                    concat = "g723vr";
                    break;
                }
                break;
            }
            case 5: {
                concat = "video";
                break;
            }
            case 6: {
                concat = "3d";
                break;
            }
            case 7: {
                concat = "slide";
                break;
            }
            case 8: {
                concat = "pan";
                break;
            }
            case 9: {
                concat = "syncaudio";
                break;
            }
            case 10: {
                concat = "syncvideo";
                break;
            }
            case 11: {
                concat = "mpeg4";
                break;
            }
            case 12: {
                if (n2 == 4) {
                    concat = "g723";
                }
                if (n2 == 8) {
                    concat = "g723vr";
                    break;
                }
                break;
            }
            case 13: {
                concat = "slide";
                break;
            }
            case 15: {
                concat = "mp3";
                break;
            }
            case 100: {
                concat = "ipix";
                break;
            }
            case -1: {
                concat = "player";
                break;
            }
            case -2: {
                concat = "action";
                break;
            }
            case -3: {
                concat = "gui";
                break;
            }
            default: {
                concat = "_".concat(String.valueOf(n & 0xFF));
                break;
            }
        }
        return "hm35".concat(concat);
    }
    
    public final void bg(hm35player hm35player) {
        if (this.h5) {
            hm35player = null;
        }
        String property = System.getProperty("java.version");
        if (property.startsWith("JDK")) {
            property = new String(property.substring(4));
        }
        final String property2 = System.getProperty("java.vendor");
        final boolean b = property2.indexOf("Netscape") != -1;
        final boolean b2 = property2.indexOf("Microsoft") != -1;
        final String property3 = System.getProperty("os.name");
        final boolean startsWith = property3.startsWith("Win");
        final boolean startsWith2 = property3.startsWith("Mac");
        System.getProperty("os.version");
        final int height = this.ii.size().height;
        final int width = this.ii.size().width;
        if (hm35player != null) {
            if (startsWith2 && !this.h5) {
                this.ii.removeAll();
                if (this.ih) {
                    this.ii.add(this.ij, 0);
                }
            }
            this.ii.add(hm35player);
            hm35player.reshape(0, 0, width, height);
        }
        int n = (hm35player == null) ? 101 : 111;
        if (startsWith2 && b) {
            ++n;
        }
        else if (startsWith2 && b2) {
            n += 2;
        }
        this.a7(n);
        if (hm35player != null) {
            if (property.compareTo("1.1.2") <= 0 && b && startsWith && this.ij != null && this.ij != this.ii.locate(1, height)) {
                this.ii.remove(this.ij);
                this.ii.add(this.ij, 0);
            }
            this.ii.validate();
        }
    }
    
    private final int bh(final int n) {
        int n2 = n - this.hv;
        if (!hm35master.iq) {
            for (int i = 0; i < this.hr.length; ++i) {
                if (this.hr[i] != null) {
                    final boolean endOfData = this.hr[i].endOfData((byte)i);
                    this.h9 = (this.h9 && endOfData);
                    this.hr[i] = null;
                }
            }
            if (this.ik != null) {
                this.h9 = (this.h9 && this.ik.endOfData((byte)0));
            }
        }
        this.ia = true;
        this.h0 = true;
        if (n2 > 0) {
            this.im.readFully(new byte[n2]);
            n2 = n2;
        }
        return n2;
    }
    
    private final int bi(final int n, final byte b, final int n2, final short n3, final int n4) {
        final int n5 = n2 - this.hv;
        if (n5 == 0) {
            throw new IOException("Master; Media frame has no data");
        }
        final hm35player hm35player = this.hr[n4];
        final int int1 = this.im.readInt();
        final int int2 = this.im.readInt();
        final int n6 = n5 - 8;
        if (hm35player == this.h1 && b == 0) {
            this.h0 = true;
        }
        switch (b) {
            case 0:
            case 1:
            case 2: {
                if (hm35player == null && !hm35master.iq) {
                    this.im.readFully(new byte[n6]);
                    break;
                }
                int n7;
                if (hm35master.iq) {
                    n7 = this.im.skipBytes(n6);
                }
                else {
                    n7 = hm35player.consumeFrame((byte)n4, n, b, n3, n6, int1, this.im);
                }
                if (n7 < 0) {
                    throw new IOException();
                }
                if (n7 < n6) {
                    if (this.io) {
                        throw new IOException();
                    }
                    this.im.readFully(new byte[n6 - n7]);
                }
                if (int2 == -1 && !hm35master.iq) {
                    final boolean endOfData = hm35player.endOfData((byte)n4);
                    this.h9 = (this.h9 && endOfData);
                    this.hr[n4] = null;
                    break;
                }
                break;
            }
            case 3: {
                if (hm35master.iq) {
                    this.im.readFully(new byte[n6]);
                    break;
                }
                this.im.readFully(new byte[n6]);
                break;
            }
            default: {
                this.im.readFully(new byte[n6]);
                break;
            }
        }
        return n5;
    }
    
    private final int bj(final int n, final byte b, final int n2, final short n3, final int n4) {
        final int n5 = n2 - this.hv;
        if (n5 > 0) {
            if (hm35master.iq) {
                this.im.readFully(new byte[n5]);
            }
            else {
                if (this.ik == null) {
                    this.ik = this.bd(99, this.bf(-2, 0));
                }
                int consumeFrame = 0;
                if (this.ik != null) {
                    if (!this.hs) {
                        this.ik.startOfData((byte)0, (byte)0, (byte)0, (byte)0, 0, 0, 0);
                        this.hs = true;
                    }
                    consumeFrame = this.ik.consumeFrame((byte)n4, n, b, n3, n5, 0, this.im);
                }
                if (consumeFrame < 0) {
                    throw new IOException();
                }
                if (consumeFrame < n5) {
                    if (this.io) {
                        throw new IOException();
                    }
                    this.im.readFully(new byte[n5 - consumeFrame]);
                }
            }
        }
        return n5;
    }
    
    private final void bk() {
        final boolean b = this.ht == 10;
        final int hv = b ? 24 : 26;
        final int int1 = this.im.readInt();
        byte byte1 = 0;
        byte byte2 = 0;
        if (b) {
            final byte byte3 = this.im.readByte();
            byte2 = (byte)(byte3 >> 4);
            byte1 = (byte)(byte3 & 0xF);
        }
        final int n = this.im.readByte() & 0xFF;
        int n2 = 0;
        short short1;
        if (b) {
            short1 = (short)(this.im.readByte() & 0xFF);
            this.im.readByte();
        }
        else {
            n2 = (this.im.readByte() & 0xFF);
            short1 = this.im.readShort();
        }
        final boolean b2 = (short1 & 0x1) != 0x0;
        if (!b) {
            byte1 = this.im.readByte();
            byte2 = this.im.readByte();
        }
        final int int2 = this.im.readInt();
        final int int3 = this.im.readInt();
        this.im.readInt();
        final int int4 = this.im.readInt();
        this.hv = hv;
        String s = null;
        if (b) {
            if (byte2 == 3 && (byte1 == 0 || byte1 == 1)) {
                s = this.bf(byte2, byte1);
            }
        }
        else {
            if (b2) {
                final short short2 = this.im.readShort();
                if (short2 != 0) {
                    final byte[] array = new byte[short2 - 1];
                    this.im.readFully(array);
                    this.ac(array);
                    this.im.readByte();
                }
                this.hv += short2 + 2;
            }
            s = this.bf(byte2 & 0xFF, byte1 & 0xFF);
        }
        if (s != null) {
            if (s.equals("hm35_127")) {
                return;
            }
            if (!hm35master.iq) {
                final hm35player bd = this.bd(n2, s);
                if (bd != null) {
                    if (byte2 == 4 || byte2 == 12) {
                        if (!this.h4) {
                            this.bg(null);
                        }
                        this.h1 = bd;
                    }
                    else {
                        this.h4 = true;
                    }
                    (this.hr[n] = bd).startOfData((byte)n, (byte)n2, byte2, byte1, int2, int3, int4);
                }
            }
        }
        if (int1 > this.hv) {
            this.im.readFully(new byte[int1 - this.hv]);
            this.hv += int1 - this.hv;
        }
    }
    
    private final void bl() {
        final int int1 = this.im.readInt();
        final boolean b = (int1 & 0x1) != 0x0;
        this.hx = ((int1 & 0x2) != 0x0);
        final boolean b2 = (int1 & 0x4) != 0x0;
        final boolean b3 = (int1 & 0x8) != 0x0;
        final boolean b4 = (int1 & 0x10) != 0x0;
        if ((int1 & 0x20) == 0x0) {}
        final boolean b5 = (int1 & 0x40) != 0x0;
        this.hz = ((int1 & 0x400) == 0x0);
        this.h3 = (int1 & 0x2F800);
        if ((((this.h3 & 0x2F800) != 0x0) ? 1 : 0) + this.il > 0 && !this.h5 && !hm35master.iq) {
            this.ij = this.bd(99, this.bf(-3, 0));
        }
        int int2 = this.im.readInt();
        if (this.ht == 10) {
            int2 += 4;
        }
        this.im.readShort();
        this.im.readShort();
        this.im.readByte();
        final short n = (short)(this.im.readByte() & 0xFF);
        final short short1 = this.im.readShort();
        final short short2 = this.im.readShort();
        final short short3 = this.im.readShort();
        this.im.readInt();
        final short n2 = (short)(n + short1 + short2 + short3);
        this.im.readFully(new byte[4 + n2]);
        this.hv = 28 + n2;
        this.hu = !b5;
        if (int2 > this.hv) {
            this.im.readFully(new byte[int2 - this.hv]);
            this.hv += int2 - this.hv;
        }
    }
    
    private final int bm(final byte b, final int n) {
        int i = n - this.hv;
        int n2 = 0;
        if (b == 0) {
            this.bl();
            n2 += this.hv;
            i -= this.hv;
        }
        this.hw = 0L;
        while (i > 0) {
            this.bk();
            n2 += this.hv;
            i -= this.hv;
            ++this.hw;
        }
        return n2;
    }
    
    private final void bn() {
        int n = 0;
        int n2 = 0;
        this.hs = false;
        while (!this.io) {
            int int1 = -1;
            this.hv = 0;
            int n3;
            if (n2 == 0 || this.hu) {
                int1 = this.im.readInt();
                this.hv += 4;
                if (n2 == 0 && int1 != 0) {
                    n3 = int1;
                }
                else {
                    n3 = this.im.readInt();
                    this.hv += 4;
                    this.hu = true;
                }
            }
            else {
                n3 = this.im.readInt();
                this.hv += 4;
            }
            int n4 = (byte)(n3 & 0xF);
            byte b = (byte)(n3 >>> 4 & 0xF);
            final int int2 = this.im.readInt();
            this.hv += 4;
            n += this.hv;
            if (this.ht == 10) {
                switch (n4) {
                    case 1: {
                        b = (byte)(n3 >>> 8);
                        break;
                    }
                    case 2: {
                        n4 = 4;
                        b = -1;
                        break;
                    }
                    case 3: {
                        n4 = 4;
                        b = 1;
                        break;
                    }
                    case 4: {
                        n4 = 4;
                        b = 0;
                        break;
                    }
                    case 5: {
                        n4 = 4;
                        b = -1;
                        break;
                    }
                }
            }
            switch (n4) {
                case 0: {
                    this.ht = (short)(n3 >>> 16);
                    n += this.bm(b, int2);
                    break;
                }
                case 6: {
                    this.im.readFully(new byte[int2 - this.hv]);
                    n += int2 - this.hv;
                    break;
                }
                case 4: {
                    n += this.bj(int1, (byte)((b == 0) ? 4 : 3), int2, (short)(n3 >>> 16), n3 >>> 8 & 0xFF);
                    break;
                }
                case 1: {
                    short n5 = 0;
                    int n6;
                    if (this.ht == 10) {
                        n6 = (n3 >>> 16 & 0xFF);
                    }
                    else {
                        n5 = (short)(n3 >>> 16);
                        n6 = (n3 >>> 8 & 0xFF);
                    }
                    n += this.bi(int1, b, int2, n5, n6);
                    break;
                }
                case 7: {
                    final int n7 = n + this.bh(int2);
                    return;
                }
                default: {
                    this.im.readFully(new byte[int2 - this.hv]);
                    break;
                }
            }
            ++n2;
        }
    }
    
    public final void bo(final int n, final URL url, String s) {
        switch (n) {
            case 0: {
                if (!this.h5) {
                    this.h_ = true;
                    if (this.ij != null) {
                        this.ii.remove(this.ij);
                    }
                    this.ii.equals(new Object[] { new Integer(1), this, url });
                    return;
                }
                break;
            }
            case 1: {
                if (!this.h5) {
                    this.bp(url);
                    return;
                }
                break;
            }
            case 2: {
                if (s == null) {
                    s = "_self";
                }
                this.ii.getAppletContext().showDocument(url, s);
            }
        }
    }
    
    private final synchronized void bp(final URL h6) {
        if (this.h0) {
            if (this.h1 != null) {
                this.h1.play(0);
            }
            this.h2 = false;
            if (this.h7 != null) {
                if (this.h6.toString().equals(h6.toString())) {
                    this.h7.bc(3);
                }
                else {
                    this.h7.stop();
                    this.h7.bc(99);
                    this.h7 = null;
                }
            }
            if (this.h7 == null) {
                this.h6 = h6;
                this.h7 = new hm35master(h6, this);
                final hm35master h7 = this.h7;
                h7.h8 = true;
                h7.bc(1);
                h7.bq(1);
            }
        }
    }
    
    public final void run() {
        this.a7(99);
        try {
            this.bn();
        }
        catch (IOException ex) {
            this.a7(100);
        }
        finally {
            try {
                this.im.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public final synchronized void bq(final int n) {
        switch (n) {
            case 0: {
                if (this.ip != null) {
                    if (this.im != null) {
                        try {
                            this.im.close();
                        }
                        catch (Exception ex) {}
                    }
                    this.io = true;
                    return;
                }
                break;
            }
            case 2: {
                if (this.io) {
                    while (this.ip.isAlive()) {
                        try {
                            this.ip.join(50L);
                        }
                        catch (Exception ex2) {}
                    }
                    this.ip = null;
                    this.im = null;
                    return;
                }
                break;
            }
            case 1: {
                if (this.h8) {
                    if (!this.ia || !this.h9) {
                        this.bq(2);
                        if (this.ip != null) {
                            break;
                        }
                        for (int i = 0; i < this.hr.length; ++i) {
                            this.hr[i] = null;
                        }
                        this.io = false;
                        final Object[] array = { new Integer(4), this.ig };
                        this.ii.equals(array);
                        final InputStream inputStream = (InputStream)array[1];
                        if (inputStream != null) {
                            this.im = new DataInputStream(new BufferedInputStream(inputStream, 5000));
                            (this.ip = new Thread(this)).start();
                            return;
                        }
                        this.a7(100);
                    }
                    return;
                }
                break;
            }
            case 3: {
                this.bq(0);
                this.bq(2);
                break;
            }
        }
    }
    
    public final void destroy() {
        this.bc(99);
    }
    
    public final synchronized void stop() {
        this.h8 = false;
        this.bq(3);
        this.bc(2);
        if (this.h7 != null) {
            this.h7.stop();
            this.h7.bc(99);
            this.h7 = null;
        }
        if (this.hz && !this.h_) {
            final String parameter = this.ii.getParameter("TOTALTIME");
            if (parameter != null) {
                this.a9(parameter.concat("=").concat(String.valueOf((System.currentTimeMillis() - this.hy) / 1000L)));
            }
            final String parameter2 = this.ii.getParameter("RICHMEDIA");
            if (parameter2 != null) {
                this.a9(parameter2);
            }
        }
    }
    
    public final void start() {
        this.h8 = true;
        this.bc(1);
        this.bq(1);
    }
    
    public final boolean equals(final Object o) {
        if (!(o instanceof Component)) {
            try {
                final Object[] array = (Object[])o;
                switch ((int)array[0]) {
                    case 1: {
                        try {
                            this.bo(0, new URL(this.ig, this.if.concat((String)array[1])), "");
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    case 2: {
                        boolean mediaControl = false;
                        final Enumeration<hm35player> elements = this.hq.elements();
                        while (elements.hasMoreElements()) {
                            mediaControl = elements.nextElement().mediaControl(array);
                            if (!mediaControl) {
                                continue;
                            }
                            break;
                        }
                        array[1] = new Boolean(mediaControl);
                        break;
                    }
                    case 0: {
                        this.ii = (Applet)array[1];
                        this.ig = (URL)array[2];
                        String substring = (String)array[3];
                        this.hy = (long)array[4];
                        final int lastIndex;
                        if ((lastIndex = substring.lastIndexOf("%%%")) != -1) {
                            this.ie = substring.substring(lastIndex + 3);
                            substring = substring.substring(0, lastIndex);
                            final int index;
                            if ((index = this.ie.indexOf("***")) != -1) {
                                this.ie = this.ie.substring(0, index);
                            }
                            final int lastIndex2;
                            if ((lastIndex2 = this.ie.lastIndexOf("###")) != -1) {
                                this.ib = this.ie.substring(lastIndex2 + 3);
                                this.ie = this.ie.substring(0, lastIndex2);
                            }
                            final int lastIndex3;
                            if ((lastIndex3 = this.ie.lastIndexOf("$$$")) != -1) {
                                this.ic = this.ie.charAt(0);
                                this.ie = this.ie.substring(lastIndex3 + 3);
                            }
                        }
                        this.if = substring.substring(0, substring.lastIndexOf("/") + 1);
                        final String parameter = this.ii.getParameter("GUI");
                        if (parameter != null) {
                            if (parameter.equalsIgnoreCase("Y")) {
                                this.il = 1;
                            }
                            if (parameter.equalsIgnoreCase("N")) {
                                this.il = -1;
                            }
                        }
                        final Class[] array2 = { null, null, null, null, null };
                        array2[0] = this.be(this.bf(-1, 0));
                        final byte[] array3 = (byte[])array[5];
                        final byte b = array3[0];
                        if ((((array3[1] & 0x38) != 0x0) ? 1 : 0) + this.il > 0) {
                            array2[2] = this.be(this.bf(-3, 0));
                        }
                        if ((b & 0x80) != 0x0) {
                            if ((array3[11] & 0xFF) == 0xD) {
                                final int n = (array3[2] << 24) + (array3[3] << 16) + (array3[4] << 8) + array3[5];
                                if (array3.length > n) {
                                    final String bf = this.bf(array3[n + 11] & 0xFF, array3[n + 10] & 0xFF);
                                    if (bf != null) {
                                        array2[4] = this.be(bf);
                                    }
                                }
                            }
                            final String bf2 = this.bf(array3[11] & 0xFF, array3[10] & 0xFF);
                            if (bf2 != null) {
                                array2[3] = this.be(bf2);
                            }
                        }
                        else {
                            array2[3] = this.be(this.bf(3, 0));
                            System.out.println("Master " + this.hashCode() + " pre-loading class - KMV-DEBU" + this.bf(3, 0));
                        }
                        if ((b & 0x2) != 0x0) {
                            array2[1] = this.be(this.bf(-2, 0));
                            break;
                        }
                        break;
                    }
                }
                return false;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        return super.equals(o);
    }
    
    public hm35master(final URL ig, final hm35master hm35master) {
        this.ic = 'r';
        this.ib = "_default";
        this.h9 = true;
        this.hx = false;
        this.hr = new hm35player[256];
        this.hq = new Hashtable(4);
        this.h_ = true;
        this.h5 = true;
        this.ii = hm35master.ii;
        this.ig = ig;
        this.if = hm35master.if;
        this.ie = hm35master.ie;
        this.ic = hm35master.ic;
        this.ib = hm35master.ib;
    }
    
    public hm35master() {
        this.ic = 'r';
        this.ib = "_default";
        this.h9 = true;
        this.hx = false;
        this.hr = new hm35player[256];
        this.hq = new Hashtable(4);
    }
    
    public final String getAppletInfo() {
        return "IBM HotMedia\n(c) Copyright IBM Corp. 1998, 2001.  All rights reserved.";
    }
}
