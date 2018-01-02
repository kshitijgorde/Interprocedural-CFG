import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Event;
import java.io.DataInputStream;
import java.net.URLConnection;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class hm35 extends Applet implements Runnable
{
    public Dimension adw;
    public Color adv;
    public Image adu;
    public boolean HMt2;
    public int InitialSize;
    public URL adt;
    public String ads;
    public long hy;
    public byte[] adr;
    public String adq;
    public URL adp;
    public Image ado;
    public boolean adn;
    public int adm;
    public int adl;
    public double adk;
    public int adj;
    public boolean adi;
    public int adh;
    public Image dl;
    public Graphics adg;
    public boolean adf;
    public boolean ade;
    public boolean add;
    public boolean adc;
    public boolean adb;
    public boolean ada;
    public boolean ac9;
    public boolean ac8;
    public boolean ac7;
    public short ac6;
    public int ac5;
    public int ac4;
    public Frame ac3;
    public URL ac2;
    public String ie;
    public String ac1;
    public Thread ac0;
    public boolean io;
    public long ac_;
    public boolean acz;
    public Applet acy;
    public Applet acx;
    public boolean acw;
    public int acv;
    public boolean acu;
    public String act;
    
    public hm35() {
        this.adv = Color.white;
        this.ac9 = true;
        this.ac5 = 4000;
        this.ac4 = 1;
        this.ac1 = "_default";
        this.acz = true;
    }
    
    private final boolean l1(final long n) {
        for (long n2 = n; n2 >= 0L && !this.io; n2 -= 20L) {
            try {
                Thread.sleep((n2 > 20L) ? 20L : n2);
            }
            catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
    
    public final Color getBackground() {
        return this.adv;
    }
    
    public final Dimension size() {
        Dimension dimension = super.size();
        if ((dimension.width == 0 || dimension.height == 0) && this.adw != null) {
            dimension = this.adw;
            super.reshape(0, 0, dimension.width, dimension.height);
        }
        return dimension;
    }
    
    public final int set(final String s, final String s2) {
        if (s == null || s2 == null) {
            return -1;
        }
        if (this.acy != null) {
            final Object[] array = { new Integer(2), s, s2 };
            this.acy.equals(array);
            if (array[1] instanceof Boolean) {
                if (array[1]) {
                    return 0;
                }
                return -1;
            }
        }
        return -2;
    }
    
    public final int set(final short n, final String s, final String s2) {
        String string = s;
        if (n == -1) {
            if (s == null) {
                return -1;
            }
        }
        else {
            string = Integer.toString(n);
        }
        return this.set(string, s2);
    }
    
    public final int load(final String act) {
        this.act = act;
        return 1;
    }
    
    public final int activate() {
        this.adf = true;
        return 1;
    }
    
    public final void paint(final Graphics graphics) {
        final Graphics adg = this.adg;
        if (adg == null) {
            return;
        }
        if (this.ac_ != 0L) {
            return;
        }
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.adh == 0) {
            adg.setColor(this.adv);
            adg.fillRect(0, 0, width, height);
            if (this.adu != null) {
                adg.drawImage(this.adu, (width - this.adu.getWidth(null)) / 2, (height - this.adu.getHeight(null)) / 2, null);
            }
        }
        else if (this.adh == 1 || this.adh == 3) {
            adg.setColor(this.adv);
            adg.fillRect(0, 0, width, height);
            if (this.ac7 && this.adh == 1) {
                ++this.adh;
            }
        }
        if (this.adn && this.adh == 2) {
            final int n = (int)this.adk;
            if (this.adb && n + this.adm > 0 && n + this.adm < width) {
                this.adn &= adg.drawImage(this.ado, n + this.adm, this.adj, this.adm, this.adl, this);
            }
            this.adn &= adg.drawImage(this.ado, n, this.adj, this.adm, this.adl, this);
            this.HMt2 = this.adn;
        }
        graphics.drawImage(this.dl, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void l2() {
        final int width = this.ado.getWidth(this);
        final int height = this.ado.getHeight(this);
        final int width2 = this.size().width;
        final int height2 = this.size().height;
        if (width > 0) {
            final double n = width / width2;
            final double n2 = height / height2;
            double n3;
            if (n > n2) {
                n3 = 1.0 / n;
            }
            else {
                n3 = 1.0 / n2;
            }
            if (this.ade) {
                n3 = 1.0 / n2;
            }
            this.adm = (int)(n3 * width);
            this.adl = (int)(n3 * height);
            if (this.adm - width2 >= -1 && this.adm - width2 <= 1 && this.adl - height2 >= -1 && this.adl - height2 <= 1) {
                this.adm = width2;
                this.adl = height2;
            }
            if (n < n2 || !this.ade) {
                this.adb = false;
                this.adk = (width2 - this.adm) / 2.0;
                this.adj = (int)(0.5 + (height2 - this.adl) / 2.0);
            }
        }
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.ado == null) {
            return false;
        }
        if ((n & 0x30) != 0x0) {
            this.l1(100L);
            this.l2();
            this.adn = true;
            this.repaint();
        }
        return (n & 0xA0) == 0x0;
    }
    
    public final boolean equals(final Object o) {
        if (!(o instanceof Component)) {
            try {
                final Object[] array = (Object[])o;
                switch ((int)array[0]) {
                    case 5: {
                        final String s = (String)array[1];
                        array[1] = null;
                        if (s != null) {
                            array[1] = Class.forName(s);
                            break;
                        }
                        break;
                    }
                    case 0: {
                        final int intValue = (int)array[1];
                        final byte[] array2 = (byte[])array[2];
                        array[1] = null;
                        try {
                            array[1] = this.getToolkit().createImage(array2);
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            final URLConnection urlConnection = (URLConnection)Class.forName("hm35decimg").newInstance();
                            final Object[] array3 = { new Integer(intValue), array2, this.adt, this };
                            urlConnection.equals(array3);
                            array[1] = array3[0];
                        }
                        break;
                    }
                    case 1: {
                        this.l5((Applet)array[1], (URL)array[2]);
                        break;
                    }
                    case 2: {
                        int intValue2 = (int)array[1];
                        if (intValue2 >= 99) {
                            if (intValue2 >= 111) {
                                this.adh = 3;
                                intValue2 -= 10;
                            }
                            if (intValue2 > 101) {
                                this.acv = intValue2 - 101;
                            }
                            final boolean acu = intValue2 == 99;
                            intValue2 = (acu ? 3 : 0);
                            if (this.acu ^ acu) {
                                this.acu = acu;
                            }
                            if (!acu && this.acx != null) {
                                this.acw = true;
                            }
                        }
                        this.ac3.setCursor(intValue2);
                        break;
                    }
                    case 3: {
                        array[1] = new Integer(this.ac3.getCursorType());
                        break;
                    }
                    case 4: {
                        final URL url = (URL)array[1];
                        array[1] = null;
                        final URLConnection openConnection = url.openConnection();
                        openConnection.setUseCaches(false);
                        array[1] = openConnection.getInputStream();
                        break;
                    }
                }
            }
            catch (Exception ex) {}
            return false;
        }
        return super.equals(o);
    }
    
    private final void l3(final URL url) {
        DataInputStream dataInputStream = null;
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            dataInputStream = new DataInputStream(openConnection.getInputStream());
            boolean b = false;
            short n = dataInputStream.readShort();
            if (n == 0) {
                dataInputStream.readShort();
                n = dataInputStream.readShort();
                b = true;
            }
            int n2;
            if (n == 10) {
                n2 = 1;
            }
            else {
                n2 = (short)(n >> 8);
            }
            if (n2 > 3) {
                System.out.println("Hm: detected MVR file version (" + n2 + ".x). Update player.");
                return;
            }
            dataInputStream.readShort();
            final int int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            this.adf = ((int2 & 0x1) != 0x0);
            this.ac7 = ((int2 & 0x4) != 0x0);
            this.ac8 = ((int2 & 0x20) != 0x0);
            final int int3 = dataInputStream.readInt();
            this.adw = new Dimension(dataInputStream.readShort(), dataInputStream.readShort());
            dataInputStream.readFully(new byte[int3 - ((n2 >= 2) ? 12 : 8)]);
            int n3 = int1 - int3;
            if (n2 >= 2) {
                n3 -= (b ? 12 : 8);
            }
            else {
                n3 -= 16;
            }
            if (n3 > 0) {
                (this.adr = new byte[n3 + 2])[0] = (byte)(int2 & 0x7F);
                if (n2 >= 2) {
                    final byte[] adr = this.adr;
                    final int n4 = 0;
                    adr[n4] |= (byte)128;
                }
                this.adr[1] = (byte)(int2 >>> 8);
                dataInputStream.readFully(this.adr, 2, n3);
            }
            if (this.ac7) {
                if (b) {
                    dataInputStream.readInt();
                }
                final byte byte1 = dataInputStream.readByte();
                final byte byte2 = dataInputStream.readByte();
                this.ade = ((byte2 & 0x1) != 0x0);
                this.add = ((byte2 & 0x2) != 0x0);
                this.adc = ((byte2 & 0x10) != 0x0);
                this.adf = ((byte2 & 0x20) != 0x0);
                this.ada = ((byte2 & 0x40) != 0x0);
                if (this.ade) {
                    this.adb = ((byte2 & 0x4) != 0x0);
                }
                this.ac4 = (dataInputStream.readByte() & 0xFF);
                dataInputStream.readByte();
                final int int4 = dataInputStream.readInt();
                this.ac6 = dataInputStream.readShort();
                this.ac5 = dataInputStream.readChar() * 'd';
                if (!this.adf) {
                    this.ac5 = 4000;
                }
                final int int5 = dataInputStream.readInt();
                if (int5 <= 0) {
                    return;
                }
                final byte[] array = new byte[int5];
                dataInputStream.readFully(array);
                this.InitialSize = int1 + int4;
                final Object[] array2 = { new Integer(0), new Integer(byte1 & 0xF), array };
                this.equals(array2);
                this.ado = (Image)array2[1];
                if (this.prepareImage(this.ado, this)) {
                    this.l2();
                    this.adn = true;
                }
                final int cursor = this.ac8 ? 12 : 0;
                if (this.ac3.getCursorType() != cursor) {
                    this.ac3.setCursor(cursor);
                }
            }
        }
        catch (Exception ex) {
            this.adr = null;
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    private final synchronized void l4() {
        if (this.acy == null) {
            try {
                this.equals(new Object[] { new Integer(2), new Integer(99) });
                (this.acy = (Applet)Class.forName("hm35master").newInstance()).equals(new Object[] { new Integer(0), this, this.adt, this.ads, new Long(this.hy), this.adr });
                this.acy.start();
            }
            catch (Exception ex) {
                this.ac3.setCursor(0);
            }
        }
    }
    
    public final void run() {
        int n = 1;
        long currentTimeMillis = System.currentTimeMillis();
        final long n2 = currentTimeMillis + 1000L;
        final long n3 = currentTimeMillis + this.ac5;
        final long n4 = 1L << this.ac4 + (this.adc ? 0 : 4);
        int n5 = 1;
        final int width = this.size().width;
        this.size();
        Graphics graphics = this.getGraphics();
        this.hy = currentTimeMillis;
        while (!this.io) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            long n6 = (this.adh == 2 && n4 < 64L) ? n4 : 64L;
            if (this.acx != null && this.acw) {
                this.acw = false;
                this.acx.destroy();
                this.acx = null;
                this.enable();
            }
            if (this.io || this.l6()) {
                break;
            }
            if (this.act != null && this.acx == null) {
                if (this.acy != null) {
                    final Object[] array = { new Integer(1), this.act };
                    this.act = null;
                    this.acy.equals(array);
                }
                else {
                    this.act = null;
                }
            }
            if (this.adh == 0 && (this.adn || !this.ac7)) {
                if (currentTimeMillis2 > n2) {
                    this.adh = 1;
                }
                this.repaint();
            }
            final long n7 = currentTimeMillis - currentTimeMillis2;
            if (n7 <= 8L) {
                n5 = 1;
                currentTimeMillis += n4;
            }
            else if (n7 < n6 << 1) {
                n6 = n7;
            }
            if (!this.adi && this.adm > 0 && width <= this.adm && this.adh == 2 && n5 != 0) {
                n5 = 0;
                double n8 = 1.0;
                if (this.add && this.ac6 > 0) {
                    if (this.ac6 == 1) {
                        n8 = 0.0;
                    }
                    else {
                        n8 = (this.adm - width) / (this.ac6 - 1);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else if (this.adb) {
                    this.adk -= n8;
                    if (this.adk - 1.0 < -this.adm) {
                        this.adk = 0.0;
                    }
                }
                else if (!this.ac9) {
                    this.adk += n8;
                    if (this.adk + n8 / 2.0 >= 0.0) {
                        this.adk = 0.0;
                        this.ac9 = true;
                    }
                }
                else {
                    this.adk -= n8;
                    if (this.adk - n8 / 2.0 <= width - this.adm) {
                        this.adk = width - this.adm;
                        this.ac9 = false;
                    }
                }
                if (graphics != null) {
                    this.paint(graphics);
                }
                else {
                    graphics = this.getGraphics();
                }
            }
            if (!this.ac7) {
                this.l4();
            }
            if (this.adf && this.ac7 && currentTimeMillis2 > n3) {
                this.l4();
            }
            if (this.io) {
                break;
            }
            this.l1(n6);
        }
        if (graphics != null) {
            graphics.dispose();
        }
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.ac8 && this.ada) {
            this.l4();
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.acz) {
            if (this.ac2 != null && (this.ie == null || !this.ac8)) {
                this.ac3.setCursor(3);
                this.getAppletContext().showDocument(this.ac2, this.ac1);
                return true;
            }
            if (this.ac8 && this.ada) {
                return true;
            }
            if (this.ac8 && !this.ada && !event.controlDown() && !event.metaDown()) {
                this.l4();
            }
            else {
                this.adi = !this.adi;
            }
        }
        return true;
    }
    
    private final synchronized void l5(final Applet applet, final URL adt) {
        if (applet == this.acy && !this.acz) {
            this.disable();
            this.acy.stop();
            if (this.acx != null) {
                this.l1(4000L);
                if (this.acx != null) {
                    if (this.acw) {
                        this.acw = false;
                        this.acx.destroy();
                    }
                    this.enable();
                }
            }
            this.acx = this.acy;
            this.acy = null;
            this.adt = adt;
            this.ads = this.adt.toString();
            if (this.ie != null) {
                this.ads = this.ads.concat("%%%");
                this.ads = this.ads.concat(this.ie);
            }
            this.l4();
        }
    }
    
    public final synchronized void destroy() {
        this.ac_ = 0L;
        this.io = true;
        if (this.ac0 != null) {
            try {
                this.ac0.join(250L);
            }
            catch (Exception ex) {}
            this.ac0 = null;
        }
        if (this.acy != null) {
            if (!this.acz) {
                this.acy.stop();
            }
            this.acy.destroy();
            this.acy = null;
        }
        if (this.acx != null) {
            this.acx.destroy();
            this.acx = null;
        }
        if (this.adg != null) {
            this.adg.dispose();
            this.adg = null;
        }
        this.ado = null;
        this.dl = null;
        this.ads = null;
        this.adt = null;
        this.ac2 = null;
        this.ie = null;
        this.ac1 = null;
        this.adq = null;
        this.adp = null;
        System.gc();
    }
    
    private final synchronized boolean l6() {
        if (this.ac_ != 0L && System.currentTimeMillis() >= this.ac_) {
            if (this.acy != null) {
                this.acy.stop();
                this.acy.destroy();
                this.acy = null;
                System.gc();
                this.adt = this.adp;
                this.ads = this.adq;
            }
            if (this.acx != null) {
                this.acx.destroy();
                this.acx = null;
                this.acw = false;
                this.enable();
            }
            this.acz = true;
            this.ac0 = null;
            return true;
        }
        return this.acz || this.io;
    }
    
    public final void stop() {
        this.ac_ = System.currentTimeMillis() + 250L;
        if (this.acv == 2) {
            while (this.ac0 != null) {
                Thread.yield();
            }
        }
    }
    
    private final void l7() {
        if (this.adu == null) {
            final byte[] array = new byte["\u4947\u3846\u6139\u0087\u0013³\uff00\uffff\ud5d5\ubff7\ubfbf\u7f7f\uff7f\u1010\u4f4f\u1b4f\ud11b\u0909\ufe09\u0201\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u2100\u0bff\u454e\u5354\u4143\u4550\u2e32\u0330\u0001\u0000\uf921\u0404\u0019\u00ff,\u0000\u8700\u1300\u0000\uff04\uc870\uab49\u38bd\ucdeb\uffbb\u2020\uc202\u9e68\uaa68\u6cae\ubeeb\udc70\u264a\uc841\uae78\ue2ef\u1fe1\u163c\u646d\u892b\u0404\u7063\u7cc9\u4701\ud8cf\uea6f\u0d33\uc58f\ued80\uecd6\u4f7a\u5fc0\u156f\u9808\ua8a2\u95e8\uddb4\u948e\ub882\u2cc0\ucc0a\u714d\ue9fc\ub2de\u8fd3\u2d4a\u8146i\u614f\u012a\u7f03\u6087c\u4857\u496d\u2550\u0702\u9905\u2298\u9b03\u0750\ua04a\u0526\u8903\u9c05\u3d9e\u82a2\u9740\u612c\u8655\u2380\u0601\u8c06\u9faa\u128f\u9392\u6736\u6896\u9da2\u9751\u86ab\ua481\uae2a\ub190\u3e24\u2984\u2dcc\ub288\u024f\u73b6\ub603\ub9b8\u4fce\ubebc\ua15b\ua3c1\u03a5\u4699\uc712\u00c7\ucdca\u059e\ue89b\uea05\u469d\u07f2\ua8a5\uad98\u24a7\ufef9\u1a3d\ucb58\uc196\u505b\u38ce\u0593\u1f69\u5f1b\u9e95\ue9c9\u0664\u90dd\uec7a\uc806\ua011\u9d0c\u4c99\uffcd\u584a\u87dc\u04b1\u7748\u2ea5\u6c99\u2405\u2880\u0e97\u890d\u5131\u60f0\u84b7\u3867\u14c5\u45c7\ua5d4\uf24c\u79fe\u196c\u1426\u4d50\u56a5\u8cb9\uae03\u4b80\u4f00\u2d41\ud38d\ue509\u9611\u4847\u8a5c\ue2c9\uc1a0\u4f45\ue95f\uf1ac\u61eb\u9f09\u4068\u73a2\u8027\ubb06\u4964\u31c9\u5b5b\u2dcf\ua3d3\udea7\u62b9\u5016\u8bdf\u5e9a\u4c0d\u0153\u276e\u3797\ucb67\u588d\u18cb\ue25d\u2388\u2994\u3505\u94f7\u7a84\u2ec8\u11fd\u7271\u902f\u9caa\ua6b9\u7c00\u6350\u15d8\u6b85\ueb44\u18a1\udfaa\u1cb5\u910f\u0733\ua9c9\u53bb\u0195\ubeca\u19cd\u28b7\ucf79\ua19a\u7d12\u7482\ucdb6\u1c68\u9d8d\u3845\u94d8\u6b7d\ufd0d\u4b7b\u9e1b\u7940\ua68a\uc74f\u43e7\u3120\u7ce0\u9fbb\u3324\u0ca7\u8436\ua860\u5ccc\ua5fe\u7bbd\u051e\uc737\uf358\ubebe\u417d\u71e8\ubfd0\ubfcf\u543f\ufa3e\u2081\u09e0\u0480\u6816\u81e0\u4412\u0000\uf921\u0504\u0019\b\u7e2c\u0500\u0400\u0500\u0000\u0704\uc810\u2b49\uf838D\uf921\u0504\u0019\b\u7e2c\u0900\u0400\u0400\u0000\u0704\u80f0\u2479\u40b5F\uf921\u0504\u0019\b\u7d2c\u0900\u0400\u0400\u0000\u0704\u8110\u2679\ud4a8F\uf921\u0504\u0019\b\u7a2c\u0900\u0400\u0400\u0000\u0804\u8010\u6673\ub2a5\u08ca\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0004\u0400\uf007\u03c8\ubdaa\u0235\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0001\u0400\u1004\u1048\u0001\uf921\u0504\u0019\b\u7d2c\u0500\u0100\u0300\u0000\u0304\uc8f0\b;".length() * 2];
            for (int i = "\u4947\u3846\u6139\u0087\u0013³\uff00\uffff\ud5d5\ubff7\ubfbf\u7f7f\uff7f\u1010\u4f4f\u1b4f\ud11b\u0909\ufe09\u0201\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u2100\u0bff\u454e\u5354\u4143\u4550\u2e32\u0330\u0001\u0000\uf921\u0404\u0019\u00ff,\u0000\u8700\u1300\u0000\uff04\uc870\uab49\u38bd\ucdeb\uffbb\u2020\uc202\u9e68\uaa68\u6cae\ubeeb\udc70\u264a\uc841\uae78\ue2ef\u1fe1\u163c\u646d\u892b\u0404\u7063\u7cc9\u4701\ud8cf\uea6f\u0d33\uc58f\ued80\uecd6\u4f7a\u5fc0\u156f\u9808\ua8a2\u95e8\uddb4\u948e\ub882\u2cc0\ucc0a\u714d\ue9fc\ub2de\u8fd3\u2d4a\u8146i\u614f\u012a\u7f03\u6087c\u4857\u496d\u2550\u0702\u9905\u2298\u9b03\u0750\ua04a\u0526\u8903\u9c05\u3d9e\u82a2\u9740\u612c\u8655\u2380\u0601\u8c06\u9faa\u128f\u9392\u6736\u6896\u9da2\u9751\u86ab\ua481\uae2a\ub190\u3e24\u2984\u2dcc\ub288\u024f\u73b6\ub603\ub9b8\u4fce\ubebc\ua15b\ua3c1\u03a5\u4699\uc712\u00c7\ucdca\u059e\ue89b\uea05\u469d\u07f2\ua8a5\uad98\u24a7\ufef9\u1a3d\ucb58\uc196\u505b\u38ce\u0593\u1f69\u5f1b\u9e95\ue9c9\u0664\u90dd\uec7a\uc806\ua011\u9d0c\u4c99\uffcd\u584a\u87dc\u04b1\u7748\u2ea5\u6c99\u2405\u2880\u0e97\u890d\u5131\u60f0\u84b7\u3867\u14c5\u45c7\ua5d4\uf24c\u79fe\u196c\u1426\u4d50\u56a5\u8cb9\uae03\u4b80\u4f00\u2d41\ud38d\ue509\u9611\u4847\u8a5c\ue2c9\uc1a0\u4f45\ue95f\uf1ac\u61eb\u9f09\u4068\u73a2\u8027\ubb06\u4964\u31c9\u5b5b\u2dcf\ua3d3\udea7\u62b9\u5016\u8bdf\u5e9a\u4c0d\u0153\u276e\u3797\ucb67\u588d\u18cb\ue25d\u2388\u2994\u3505\u94f7\u7a84\u2ec8\u11fd\u7271\u902f\u9caa\ua6b9\u7c00\u6350\u15d8\u6b85\ueb44\u18a1\udfaa\u1cb5\u910f\u0733\ua9c9\u53bb\u0195\ubeca\u19cd\u28b7\ucf79\ua19a\u7d12\u7482\ucdb6\u1c68\u9d8d\u3845\u94d8\u6b7d\ufd0d\u4b7b\u9e1b\u7940\ua68a\uc74f\u43e7\u3120\u7ce0\u9fbb\u3324\u0ca7\u8436\ua860\u5ccc\ua5fe\u7bbd\u051e\uc737\uf358\ubebe\u417d\u71e8\ubfd0\ubfcf\u543f\ufa3e\u2081\u09e0\u0480\u6816\u81e0\u4412\u0000\uf921\u0504\u0019\b\u7e2c\u0500\u0400\u0500\u0000\u0704\uc810\u2b49\uf838D\uf921\u0504\u0019\b\u7e2c\u0900\u0400\u0400\u0000\u0704\u80f0\u2479\u40b5F\uf921\u0504\u0019\b\u7d2c\u0900\u0400\u0400\u0000\u0704\u8110\u2679\ud4a8F\uf921\u0504\u0019\b\u7a2c\u0900\u0400\u0400\u0000\u0804\u8010\u6673\ub2a5\u08ca\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0004\u0400\uf007\u03c8\ubdaa\u0235\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0001\u0400\u1004\u1048\u0001\uf921\u0504\u0019\b\u7d2c\u0500\u0100\u0300\u0000\u0304\uc8f0\b;".length() - 1; i >= 0; --i) {
                final char char1 = "\u4947\u3846\u6139\u0087\u0013³\uff00\uffff\ud5d5\ubff7\ubfbf\u7f7f\uff7f\u1010\u4f4f\u1b4f\ud11b\u0909\ufe09\u0201\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u2100\u0bff\u454e\u5354\u4143\u4550\u2e32\u0330\u0001\u0000\uf921\u0404\u0019\u00ff,\u0000\u8700\u1300\u0000\uff04\uc870\uab49\u38bd\ucdeb\uffbb\u2020\uc202\u9e68\uaa68\u6cae\ubeeb\udc70\u264a\uc841\uae78\ue2ef\u1fe1\u163c\u646d\u892b\u0404\u7063\u7cc9\u4701\ud8cf\uea6f\u0d33\uc58f\ued80\uecd6\u4f7a\u5fc0\u156f\u9808\ua8a2\u95e8\uddb4\u948e\ub882\u2cc0\ucc0a\u714d\ue9fc\ub2de\u8fd3\u2d4a\u8146i\u614f\u012a\u7f03\u6087c\u4857\u496d\u2550\u0702\u9905\u2298\u9b03\u0750\ua04a\u0526\u8903\u9c05\u3d9e\u82a2\u9740\u612c\u8655\u2380\u0601\u8c06\u9faa\u128f\u9392\u6736\u6896\u9da2\u9751\u86ab\ua481\uae2a\ub190\u3e24\u2984\u2dcc\ub288\u024f\u73b6\ub603\ub9b8\u4fce\ubebc\ua15b\ua3c1\u03a5\u4699\uc712\u00c7\ucdca\u059e\ue89b\uea05\u469d\u07f2\ua8a5\uad98\u24a7\ufef9\u1a3d\ucb58\uc196\u505b\u38ce\u0593\u1f69\u5f1b\u9e95\ue9c9\u0664\u90dd\uec7a\uc806\ua011\u9d0c\u4c99\uffcd\u584a\u87dc\u04b1\u7748\u2ea5\u6c99\u2405\u2880\u0e97\u890d\u5131\u60f0\u84b7\u3867\u14c5\u45c7\ua5d4\uf24c\u79fe\u196c\u1426\u4d50\u56a5\u8cb9\uae03\u4b80\u4f00\u2d41\ud38d\ue509\u9611\u4847\u8a5c\ue2c9\uc1a0\u4f45\ue95f\uf1ac\u61eb\u9f09\u4068\u73a2\u8027\ubb06\u4964\u31c9\u5b5b\u2dcf\ua3d3\udea7\u62b9\u5016\u8bdf\u5e9a\u4c0d\u0153\u276e\u3797\ucb67\u588d\u18cb\ue25d\u2388\u2994\u3505\u94f7\u7a84\u2ec8\u11fd\u7271\u902f\u9caa\ua6b9\u7c00\u6350\u15d8\u6b85\ueb44\u18a1\udfaa\u1cb5\u910f\u0733\ua9c9\u53bb\u0195\ubeca\u19cd\u28b7\ucf79\ua19a\u7d12\u7482\ucdb6\u1c68\u9d8d\u3845\u94d8\u6b7d\ufd0d\u4b7b\u9e1b\u7940\ua68a\uc74f\u43e7\u3120\u7ce0\u9fbb\u3324\u0ca7\u8436\ua860\u5ccc\ua5fe\u7bbd\u051e\uc737\uf358\ubebe\u417d\u71e8\ubfd0\ubfcf\u543f\ufa3e\u2081\u09e0\u0480\u6816\u81e0\u4412\u0000\uf921\u0504\u0019\b\u7e2c\u0500\u0400\u0500\u0000\u0704\uc810\u2b49\uf838D\uf921\u0504\u0019\b\u7e2c\u0900\u0400\u0400\u0000\u0704\u80f0\u2479\u40b5F\uf921\u0504\u0019\b\u7d2c\u0900\u0400\u0400\u0000\u0704\u8110\u2679\ud4a8F\uf921\u0504\u0019\b\u7a2c\u0900\u0400\u0400\u0000\u0804\u8010\u6673\ub2a5\u08ca\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0004\u0400\uf007\u03c8\ubdaa\u0235\u2100\u04f9\u1905\u0800\u2c00y\t\u0004\u0001\u0400\u1004\u1048\u0001\uf921\u0504\u0019\b\u7d2c\u0500\u0100\u0300\u0000\u0304\uc8f0\b;".charAt(i);
                array[i * 2] = (byte)(char1 & '\u00ff');
                array[i * 2 + 1] = (byte)(char1 >> 8 & '\u00ff');
            }
            final Object[] array2 = { new Integer(0), new Integer(0), array };
            this.equals(array2);
            this.adu = (Image)array2[1];
            while (this.adu.getHeight(this) < 15) {
                this.l1(100L);
            }
        }
    }
    
    public final synchronized void start() {
        this.ac_ = 0L;
        if (this.acz) {
            if (this.ac0 == null) {
                (this.ac0 = new Thread(this)).start();
            }
            this.acz = false;
            if (this.adh == 3) {
                this.adh = 2;
                if (this.acv == 1) {
                    this.ac3.remove(this);
                    this.ac3.add(this, 0);
                }
            }
        }
        this.repaint();
    }
    
    public final void init() {
        this.setLayout(null);
        String s = this.getParameter("BGCOLOR");
        if (s != null) {
            final int index = s.indexOf(35);
            if (index != -1) {
                s = s.substring(index + 1);
            }
            if (s.length() == 6) {
                try {
                    this.adv = new Color(Integer.parseInt(s, 16));
                }
                catch (NumberFormatException ex) {}
            }
        }
        try {
            String s2;
            if ((s2 = this.getParameter("T1URL")) != null) {
                final int lastIndex;
                if ((lastIndex = s2.lastIndexOf("###")) != -1) {
                    this.ac1 = s2.substring(lastIndex + 3);
                    s2 = s2.substring(0, lastIndex);
                }
                if (s2.lastIndexOf("http://") == -1) {
                    this.ac2 = new URL(this.getCodeBase().getProtocol(), this.getCodeBase().getHost(), this.getCodeBase().getPort(), s2);
                }
                else {
                    this.ac2 = new URL(s2);
                }
            }
            this.ie = this.getParameter("T2URL");
            final String parameter = this.getParameter("useDocumentBase");
            final String parameter2 = this.getParameter("mvrfile");
            final URL url = (parameter != null && parameter.equalsIgnoreCase("y")) ? this.getDocumentBase() : this.getCodeBase();
            this.adt = ((parameter2 == null) ? url : new URL(url, parameter2));
            this.ads = this.adt.toString();
            if (this.ie != null) {
                this.ads = this.ads.concat("%%%");
                this.ads = this.ads.concat(this.ie);
            }
            this.adp = this.adt;
            this.adq = this.ads;
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
            this.ac3 = (Frame)container;
            this.dl = this.createImage(this.size().width, this.size().height);
            if (this.dl != null) {
                this.adg = this.dl.getGraphics();
            }
            this.l7();
            this.l3(this.adt);
        }
        catch (Exception ex2) {}
    }
    
    public final String getAppletInfo() {
        return "IBM HotMedia\n(c) Copyright IBM Corp. 1998, 2001.  All rights reserved.";
    }
}
