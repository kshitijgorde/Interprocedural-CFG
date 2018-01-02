// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Point;
import java.util.Collections;
import java.util.ArrayList;
import java.io.EOFException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import com.screencastomatic.play.q;
import javax.sound.sampled.AudioFormat;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.awt.Dimension;
import com.screencastomatic.play.b.b;
import java.util.Map;

public class e
{
    private static final Map a;
    private String b;
    private long c;
    private long d;
    private u e;
    private long f;
    private long g;
    private long h;
    private b i;
    private int j;
    private String k;
    private v l;
    private int m;
    private Dimension n;
    private Dimension o;
    private d p;
    private GZIPInputStream q;
    private List r;
    private com.screencastomatic.play.stream.b s;
    
    public e(final String b, final long d, final int j) {
        this.c = 0L;
        this.d = 0L;
        this.e = null;
        this.f = 0L;
        this.g = 0L;
        this.h = 200L;
        this.k = "jpeg";
        this.m = -1;
        this.n = null;
        this.o = null;
        this.b = b;
        this.d = d;
        this.j = j;
        this.l = com.screencastomatic.play.stream.e.a.get(this.b);
        if (this.l == null) {
            this.e();
        }
        for (final Long n : this.l.h.keySet()) {
            if (n > d) {
                break;
            }
            this.c = (long)this.l.h.get(n);
        }
    }
    
    public AudioFormat a() {
        return this.l.a;
    }
    
    public long b() {
        return this.l.b;
    }
    
    public void a(final r r) {
        this.b(r);
    }
    
    public void c() {
        try {
            if (this.e != null) {
                this.e.close();
            }
        }
        catch (Exception ex) {
            com.screencastomatic.play.q.a(ex);
        }
    }
    
    private void e() {
        this.l = new v(this, null);
        final InputStream a = new b(this.b).a(0L);
        final DataInputStream dataInputStream = new DataInputStream(new u(a));
        final byte byte1 = dataInputStream.readByte();
        if (byte1 > 8) {
            throw new IOException("Version in stream isn't right: " + byte1);
        }
        if (byte1 >= 5) {
            this.l.d = true;
            com.screencastomatic.play.q.a("Marking that version contains mouse: " + byte1);
        }
        if (byte1 >= 6) {
            this.l.e = true;
            com.screencastomatic.play.q.a("Marking that version contains combined parts: " + byte1);
        }
        if (byte1 >= 7) {
            this.l.f = true;
            com.screencastomatic.play.q.a("Marking that version contains jpeg edge fix: " + byte1);
        }
        if (byte1 >= 8) {
            this.l.g = true;
            com.screencastomatic.play.q.a("Marking that version contains web cam: " + byte1);
        }
        com.screencastomatic.play.q.a("Using img type: " + this.k);
        if (dataInputStream.readBoolean()) {
            this.l.a = new AudioFormat(dataInputStream.readFloat(), dataInputStream.readInt(), dataInputStream.readByte(), dataInputStream.readBoolean(), dataInputStream.readBoolean());
        }
        this.l.b = dataInputStream.readLong();
        if (byte1 >= 6) {
            this.o = new Dimension(dataInputStream.readInt(), dataInputStream.readInt());
        }
        this.l.c = dataInputStream.readByte();
        if (!this.l.e && this.l.c > 20) {
            this.l.e = true;
            com.screencastomatic.play.q.a("Marking that version contains combined parts since num frame parts is more than 20.");
        }
        for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
            final long long1 = dataInputStream.readLong();
            final long long2 = dataInputStream.readLong();
            this.l.h.put(long1, long2);
            com.screencastomatic.play.q.a("pp: " + long1 + " - " + long2);
        }
        a.close();
        com.screencastomatic.play.stream.e.a.put(this.b, this.l);
    }
    
    private void f() {
        this.i = new b(this.b);
        this.e = new u(this.i.a(this.c));
    }
    
    private InputStream g() {
        if (this.e == null) {
            this.f();
        }
        if (this.p == null || this.q.available() == 0) {
            if (this.p != null) {
                final long n = this.p.available();
                if (n > 0L) {
                    com.screencastomatic.play.q.a("Skipping left over bytes: " + n);
                    final long skip;
                    if (n != (skip = this.p.skip(n))) {
                        com.screencastomatic.play.q.a("Skip less bytes: " + n + " < " + skip);
                    }
                }
            }
            this.r = this.a(this.e);
            this.p = new d(this.e, new DataInputStream(this.e).readLong());
            this.q = new GZIPInputStream(this.p);
            this.n = null;
        }
        return this.q;
    }
    
    public int d() {
        if (this.e == null) {
            return 0;
        }
        if (this.i != null && this.i.a()) {
            return 10000;
        }
        if (this.i != null && !this.i.b() && this.m == -1) {
            return 10000;
        }
        int m = (int)(this.e.available() / this.h / 1000L);
        if (this.m >= 0 && this.m < m) {
            m = this.m;
        }
        return m;
    }
    
    public void b(final r r) {
        final DataInputStream dataInputStream = new DataInputStream(this.g());
        final n n = new n(this.g(), this.k);
        boolean b = false;
        k a = null;
        if (this.n == null) {
            if (this.d > 0L) {
                a = this.a(new u(this.g()), this.r, null);
                this.d = 0L;
                b = true;
            }
            else {
                a = new k(0L, n.a());
            }
            this.n = new Dimension(a.d().getWidth(), a.d().getHeight());
        }
        if (!b) {
            long long1;
            try {
                long1 = dataInputStream.readLong();
            }
            catch (EOFException ex) {
                this.b(r);
                return;
            }
            if (a == null) {
                a = new k(long1);
            }
            else {
                a = new k(long1, a.d());
            }
            this.a(a.a(), dataInputStream, n, false);
        }
        int int1 = dataInputStream.readInt();
        byte[] array = null;
        if (int1 > 0) {
            int read;
            for (array = new byte[int1]; int1 > 0 && (read = dataInputStream.read(array, array.length - int1, Math.min(int1, array.length))) != -1; int1 -= read) {}
        }
        List<com.screencastomatic.play.stream.b> singletonList = new ArrayList<com.screencastomatic.play.stream.b>();
        if (this.l.d) {
            final byte byte1 = dataInputStream.readByte();
            if (byte1 > 0) {
                for (byte b2 = 0; b2 < byte1; ++b2) {
                    singletonList.add(new com.screencastomatic.play.stream.b(dataInputStream));
                }
            }
            for (final com.screencastomatic.play.stream.b s : singletonList) {
                if (s.d == CapturedMouse$ButtonAction.a) {
                    s.f = 1.0f;
                }
                this.s = s;
            }
            if (singletonList.isEmpty() && this.s != null) {
                final com.screencastomatic.play.stream.b s2 = this.s;
                s2.f -= 0.35f;
                singletonList = Collections.singletonList(this.s);
                if (this.s.f <= 0.0f) {
                    this.s = null;
                }
            }
        }
        final ArrayList<i> list = new ArrayList<i>();
        if (this.l.g) {
            int byte2 = dataInputStream.readByte();
            if (byte2 == -1) {
                list.add(new i(null, null, -1L));
            }
            while (byte2-- > 0) {
                list.add(new i(this.a(n), new Point(dataInputStream.readShort(), dataInputStream.readShort()), dataInputStream.readLong()));
            }
        }
        if (this.g != 0L && this.j > 0) {
            final long n2 = this.e.a() - this.g;
            if (n2 > 0L) {
                final long n3 = a.b() - this.f;
                if (n3 > 0L) {
                    this.h = Math.max(n2 / n3, 30L);
                    this.i.b(this.h * 1000L * this.j);
                }
            }
        }
        if (this.g == 0L) {
            this.g = this.e.a();
            this.f = a.b();
        }
        r.a(array, this.a(a), singletonList, list);
        this.m = r.a();
    }
    
    private BufferedImage a(final n n) {
        return n.a();
    }
    
    private k a(final k k) {
        if (k.c() && this.o != null && !this.o.equals(this.n)) {
            final BufferedImage c = new BufferedImage(this.o.width, this.o.height, 1);
            c.getGraphics().drawImage(k.d(), 0, 0, null);
            k.a().get(0).c = c;
        }
        return k;
    }
    
    private k a(final u u, final List list, final BufferedImage bufferedImage) {
        int n = 0;
        for (final o o : list) {
            if (o.a >= this.d || ++n == list.size()) {
                final n n2 = new n(u, this.k);
                BufferedImage a = bufferedImage;
                if (a == null) {
                    com.screencastomatic.play.q.a("Seek is getting new full image.");
                    final long n3 = o.c - u.a();
                    if (u.skip(n3) != n3) {
                        throw new RuntimeException("Failed to skip to full image at position: " + n3);
                    }
                    a = n2.a();
                }
                final HashMap<Long, Object> hashMap = new HashMap<Long, Object>();
                for (final Map.Entry<K, Long> entry : o.d.entrySet()) {
                    final Long n4 = entry.getValue();
                    final h h = (h)entry.getKey();
                    if (n4 <= u.a()) {
                        continue;
                    }
                    if (this.l.e) {
                        List<?> list2 = hashMap.get(n4);
                        if (list2 == null) {
                            list2 = new ArrayList<Object>();
                        }
                        list2.add(h);
                        hashMap.put(n4, list2);
                    }
                    else {
                        hashMap.put(n4, h);
                    }
                }
                final Long[] array = hashMap.keySet().toArray(new Long[0]);
                Arrays.sort(array);
                final byte b = (byte)(this.l.c / 2);
                final int n5 = a.getWidth() / b;
                final int n6 = a.getHeight() / b;
                for (final Long n7 : array) {
                    final long n8 = n7 - u.a();
                    if (u.skip(n8) != n8) {
                        throw new RuntimeException("Failed to skip ahead: " + n8);
                    }
                    if (!this.l.e) {
                        final BufferedImage a2 = n2.a();
                        final h h2 = hashMap.get(n7);
                        a.getGraphics().drawImage(a2, h2.a * n5, h2.b * n6, null);
                    }
                    else {
                        final List<List> list3 = hashMap.get(n7);
                        final DataInputStream dataInputStream = new DataInputStream(u);
                        int a3 = this.a(dataInputStream);
                        final ArrayList list4 = new ArrayList<h>(a3);
                        while (a3-- > 0) {
                            final byte byte1 = dataInputStream.readByte();
                            final byte byte2 = dataInputStream.readByte();
                            if (byte1 < 0 || byte1 >= b || byte2 < 0 || byte2 >= b) {
                                com.screencastomatic.play.q.a("Bad x or y: " + byte1 + "," + byte2);
                            }
                            else {
                                list4.add(new h(byte1, byte2));
                            }
                        }
                        final BufferedImage a4 = n2.a();
                        h h3 = null;
                        int n9 = 0;
                        for (final h h4 : list4) {
                            n9 += this.a(h3, h4, n5);
                            if (list3.contains(h4)) {
                                a.getGraphics().drawImage(a4.getSubimage(n9, 0, n5, n6), h4.a * n5, h4.b * n6, null);
                            }
                            h3 = h4;
                        }
                    }
                }
                final long n10 = o.b - u.a();
                if (n10 >= 0L) {
                    if (u.skip(n10) != n10) {
                        throw new RuntimeException("Failed to skip to MS position: " + n10);
                    }
                    final DataInputStream dataInputStream2 = new DataInputStream(u);
                    if (o.a != dataInputStream2.readLong()) {
                        throw new RuntimeException("Failed to read MS we expected.");
                    }
                    if (0 != this.a(dataInputStream2)) {
                        throw new RuntimeException("Failed to read 0 for num parts");
                    }
                }
                return new k(o.a, a);
            }
        }
        throw new RuntimeException("Failed to seek for msec: " + this.d);
    }
    
    private List a(final InputStream inputStream) {
        final d d = new d(inputStream, new DataInputStream(inputStream).readLong());
        final DataInputStream dataInputStream = new DataInputStream(new GZIPInputStream(d));
        final int int1 = dataInputStream.readInt();
        final ArrayList list = new ArrayList<o>(int1);
        for (int i = 0; i < int1; ++i) {
            list.add(com.screencastomatic.play.stream.o.a(dataInputStream));
        }
        final long n = d.available();
        if (n > 0L) {
            com.screencastomatic.play.q.a("Skipping left over PP bytes: " + n);
            final long skip;
            if (n != (skip = inputStream.skip(d.available()))) {
                com.screencastomatic.play.q.a("Skip less bytes: " + n + " < " + skip);
            }
        }
        return list;
    }
    
    private int a(final List list, final DataInputStream dataInputStream, final n n, final boolean b) {
        final byte b2 = (byte)(this.l.c / 2);
        final int n2 = this.n.width / b2;
        final int n3 = this.n.height / b2;
        final int a = this.a(dataInputStream);
        if (a > 0) {
            final ArrayList list2 = new ArrayList<Object>(a);
            for (int i = 0; i < a; ++i) {
                final byte byte1 = dataInputStream.readByte();
                final byte byte2 = dataInputStream.readByte();
                if (byte1 < 0 || byte1 >= b2 || byte2 < 0 || byte2 >= b2) {
                    com.screencastomatic.play.q.a("Bad x or y: " + byte1 + "," + byte2);
                }
                else if (!this.l.e) {
                    if (!b) {
                        list.add(new s(byte1 * n2, byte2 * n3, n.a()));
                    }
                    else {
                        n.b();
                    }
                }
                else {
                    list2.add(new h(byte1, byte2));
                }
            }
            if (this.l.e) {
                if (!b) {
                    final BufferedImage a2 = n.a();
                    h h = null;
                    int n4 = 0;
                    for (final h h2 : list2) {
                        n4 += this.a(h, h2, n2);
                        list.add(new s(h2.a * n2, h2.b * n3, a2.getSubimage(n4, 0, n2, n3)));
                        h = h2;
                    }
                }
                else {
                    n.b();
                }
            }
        }
        else if (a == -1) {
            if (!b) {
                list.add(new s(n.a()));
            }
            else {
                n.b();
            }
        }
        else if (a < 0) {
            throw new RuntimeException("Read bad num parts value: " + a);
        }
        return a;
    }
    
    private int a(final h h, final h h2, int n) {
        if (!this.l.f) {
            return (h == null) ? 0 : n;
        }
        if (h == null && h2.a != 0) {
            return 2;
        }
        if (h == null) {
            return 0;
        }
        if (h.b != h2.b || h.a != h2.a - 1) {
            if (h.a != this.l.c / 2 - 1) {
                n += 2;
            }
            if (h2.a != 0) {
                n += 2;
            }
        }
        return n;
    }
    
    private int a(final DataInputStream dataInputStream) {
        return (this.l.c > 20) ? dataInputStream.readShort() : dataInputStream.readByte();
    }
    
    static {
        a = new ConcurrentHashMap();
    }
}
