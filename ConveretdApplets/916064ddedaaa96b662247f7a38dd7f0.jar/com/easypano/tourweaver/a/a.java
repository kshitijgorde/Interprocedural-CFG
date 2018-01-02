// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.a;

import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class a
{
    Vector a;
    StringBuffer b;
    StringBuffer c;
    StringBuffer d;
    boolean e;
    boolean f;
    private static String[] z;
    
    public a() {
        this.a = new Vector();
        this.b = new StringBuffer();
        this.c = new StringBuffer();
        this.d = null;
        this.e = false;
        this.f = false;
    }
    
    private void a(final InputStream inputStream) throws IOException {
        final int l = com.easypano.tourweaver.a.e.l;
        int i = inputStream.read();
        while (i != 10) {
            i = inputStream.read();
            if (l != 0) {
                break;
            }
        }
    }
    
    private String a(final char[] array, int n, final int n2, char[] array2) {
        final int l = com.easypano.tourweaver.a.e.l;
        final char[] array3 = array2;
        if (l != 0 || array3.length >= n2) {
            final char[] array4 = array3;
            int n3 = 0;
            final int n4 = n + n2;
        Label_0425:
            while (true) {
                int i = n;
            Label_0055:
                while (i < n4) {
                    char c = array[n++];
                    if (l == 0) {
                        if (c == '\\') {
                            c = array[n++];
                            char c5;
                            char c4;
                            char c3;
                            final char c2 = c3 = (c4 = (c5 = c));
                            char c9;
                            char c8;
                            char c7;
                            final char c6 = c7 = (c8 = (c9 = 'u'));
                            if (l == 0) {
                                if (c2 == c6) {
                                    int n5 = 0;
                                    int j = 0;
                                    while (j < 4) {
                                        c = array[n++];
                                        int n7;
                                        final int n6 = i = (n7 = c);
                                        if (l != 0) {
                                            continue Label_0055;
                                        }
                                        Label_0438: {
                                            Label_0405: {
                                                Label_0385: {
                                                    if (l == 0) {
                                                        switch (n6) {
                                                            case 48:
                                                            case 49:
                                                            case 50:
                                                            case 51:
                                                            case 52:
                                                            case 53:
                                                            case 54:
                                                            case 55:
                                                            case 56:
                                                            case 57: {
                                                                n7 = (n5 << 4) + c - 48;
                                                                break;
                                                            }
                                                            case 97:
                                                            case 98:
                                                            case 99:
                                                            case 100:
                                                            case 101:
                                                            case 102: {
                                                                break Label_0385;
                                                            }
                                                            case 65:
                                                            case 66:
                                                            case 67:
                                                            case 68:
                                                            case 69:
                                                            case 70: {
                                                                break Label_0405;
                                                            }
                                                            default: {
                                                                break Label_0425;
                                                            }
                                                        }
                                                    }
                                                    n5 = n7;
                                                    if (l == 0) {
                                                        break Label_0438;
                                                    }
                                                }
                                                n5 = (n5 << 4) + 10 + c - 97;
                                                if (l == 0) {
                                                    break Label_0438;
                                                }
                                            }
                                            n5 = (n5 << 4) + 10 + c - 65;
                                            if (l != 0) {
                                                break Label_0425;
                                            }
                                        }
                                        ++j;
                                        if (l != 0) {
                                            break;
                                        }
                                    }
                                    array4[n3++] = (char)n5;
                                    if (l == 0) {
                                        continue Label_0425;
                                    }
                                }
                                final char c10;
                                c3 = (c10 = (c4 = (c5 = c)));
                                final char c11;
                                c7 = (c11 = (c8 = (c9 = 't')));
                            }
                            Label_0541: {
                                if (l == 0) {
                                    if (c2 == c6) {
                                        c = '\t';
                                        if (l == 0) {
                                            break Label_0541;
                                        }
                                    }
                                    c4 = (c3 = (c5 = c));
                                    c8 = (c7 = (c9 = 'r'));
                                }
                                if (l == 0) {
                                    if (c3 == c7) {
                                        c = '\r';
                                        if (l == 0) {
                                            break Label_0541;
                                        }
                                    }
                                    c5 = (c4 = c);
                                    c9 = (c8 = 'n');
                                }
                                char c12 = '\0';
                                Label_0539: {
                                    if (l == 0) {
                                        if (c4 == c8) {
                                            c = '\n';
                                            if (l == 0) {
                                                break Label_0541;
                                            }
                                        }
                                        c12 = (c5 = c);
                                        if (l != 0) {
                                            break Label_0539;
                                        }
                                        c9 = 'f';
                                    }
                                    if (c5 != c9) {
                                        break Label_0541;
                                    }
                                    c12 = '\f';
                                }
                                c = c12;
                            }
                            array4[n3++] = c;
                            if (l == 0) {
                                continue Label_0425;
                            }
                        }
                        array4[n3++] = c;
                    }
                    if (l != 0) {
                        break;
                    }
                    continue Label_0425;
                }
                return new String(array4, 0, n3);
            }
            throw new IllegalArgumentException(com.easypano.tourweaver.a.a.z[1]);
        }
        final int n8 = n2 * 2;
        if (l == 0 && n8 < 0) {
            goto Label_0036;
        }
        array2 = new char[n8];
        goto Label_0042;
    }
    
    private void b(final InputStream inputStream) throws IOException {
        final int l = com.easypano.tourweaver.a.e.l;
        int read = inputStream.read();
        int n = inputStream.read();
        while (true) {
            while (true) {
                int n3 = 0;
                final int n2;
                int n5 = 0;
                final int n4;
                Label_0041: {
                    if (read == 42) {
                        n2 = (n3 = n);
                        n4 = (n5 = 47);
                        if (l != 0) {
                            break Label_0041;
                        }
                        if (n2 == n4) {
                            break;
                        }
                    }
                    read = n;
                    n = inputStream.read();
                    final int n6;
                    n3 = (n6 = read);
                    final int n7;
                    n5 = (n7 = -1);
                }
                if (l == 0) {
                    if (n2 == n4) {
                        break;
                    }
                    n3 = n;
                    n5 = -1;
                }
                if (n3 == n5) {
                    if (l == 0) {
                        break;
                    }
                    continue;
                }
                break;
            }
        }
    }
    
    public b a() {
        Object o;
        final Vector vector = (Vector)(o = this.a);
        if (com.easypano.tourweaver.a.e.l == 0) {
            if (vector.size() == 0) {
                return null;
            }
            o = this.a.elementAt(0);
        }
        return (b)o;
    }
    
    public void c(final InputStream inputStream) throws IOException {
        final int l = com.easypano.tourweaver.a.e.l;
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] array = new byte[40960];
        int n = 0;
        while (true) {
            int read;
            while ((read = bufferedInputStream.read(array, n, 10240)) != -1) {
                n += read;
                if (n + 10240 > array.length) {
                    final byte[] array2 = new byte[n + 10240];
                    System.arraycopy(array, 0, array2, 0, n);
                    array = array2;
                    if (l != 0) {
                        this.d(new ByteArrayInputStream(array));
                        return;
                    }
                    if (l != 0) {
                        int o = com.easypano.tourweaver.b.a.o;
                        com.easypano.tourweaver.b.a.o = ++o;
                        break;
                    }
                    continue;
                }
            }
            final int n2 = n;
            if (l == 0 && n2 >= array.length) {
                continue;
            }
            final byte[] array3 = new byte[n2];
            System.arraycopy(array, 0, array3, 0, n);
            array = array3;
            continue;
        }
    }
    
    public void d(final InputStream inputStream) throws IOException {
        final int l = com.easypano.tourweaver.a.e.l;
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int i = bufferedInputStream.read();
        while (i != -1) {
            int f;
            int n3;
            int n2;
            final int n = n2 = (n3 = (f = (this.f ? 1 : 0)));
            if (l == 0) {
                if (n != 0) {
                    break;
                }
                n3 = (n2 = (f = i));
            }
            int n5;
            final int n4 = n5 = 35;
            int n7 = 0;
            Label_0203: {
                Label_0199: {
                    if (l == 0) {
                        if (n2 == n4) {
                            this.a(bufferedInputStream);
                            if (l == 0) {
                                break Label_0199;
                            }
                        }
                        f = (n3 = i);
                        final int n6;
                        n5 = (n6 = 92);
                    }
                    Label_0193: {
                        int read = 0;
                        Label_0122: {
                            if (l == 0) {
                                if (n3 == n4) {
                                    final boolean b = (n7 = (this.e ? 1 : 0)) != 0;
                                    if (l != 0) {
                                        break Label_0203;
                                    }
                                    if (!b) {
                                        break Label_0199;
                                    }
                                    final StringBuffer d = this.d;
                                    if (l == 0) {
                                        if (d == null) {
                                            break Label_0199;
                                        }
                                        this.d.append((char)i);
                                    }
                                    if (l == 0) {
                                        break Label_0199;
                                    }
                                }
                                read = (f = i);
                                if (l != 0) {
                                    break Label_0122;
                                }
                                n5 = 47;
                            }
                            if (f != n5) {
                                break Label_0193;
                            }
                            read = bufferedInputStream.read();
                        }
                        final int n9;
                        final int n8 = n9 = read;
                        Label_0188: {
                            Label_0183: {
                                a a = null;
                                Label_0147: {
                                    if (l == 0) {
                                        if (n9 == 42) {
                                            break Label_0183;
                                        }
                                        a = this;
                                        if (l != 0) {
                                            break Label_0147;
                                        }
                                        final boolean e = this.e;
                                    }
                                    if (n9 == 0) {
                                        break Label_0188;
                                    }
                                    a = this;
                                }
                                final StringBuffer d2 = a.d;
                                if (l == 0) {
                                    if (d2 == null) {
                                        break Label_0188;
                                    }
                                    this.d.append((char)i);
                                    this.d.append((char)n8);
                                }
                                if (l == 0) {
                                    break Label_0188;
                                }
                            }
                            this.b(bufferedInputStream);
                        }
                        if (l == 0) {
                            break Label_0199;
                        }
                    }
                    this.a((char)i);
                }
                n7 = bufferedInputStream.read();
            }
            i = n7;
            if (l != 0) {
                break;
            }
        }
    }
    
    public void a(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        String s2 = s;
        if (l == 0) {
            if (s == null) {
                return;
            }
            s2 = s;
        }
        final char[] charArray = s2.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            this.a(charArray[i]);
            ++i;
            if (l != 0) {
                break;
            }
        }
    }
    
    private void a(final char c) {
        final int l = com.easypano.tourweaver.a.e.l;
        char e = c;
        a a = null;
        Label_0085: {
            if (l == 0) {
                Label_0057: {
                    switch (c) {
                        case '<': {
                            this.e = true;
                            this.b();
                            this.d = this.b;
                            if (l != 0) {
                                break Label_0057;
                            }
                            return;
                        }
                        case '>': {
                            this.c();
                            this.d = this.c;
                            if (l != 0) {
                                break;
                            }
                            return;
                        }
                    }
                }
                a = this;
                if (l != 0) {
                    break Label_0085;
                }
                e = (char)(this.e ? 1 : 0);
            }
            if (e == '\0') {
                return;
            }
            a = this;
        }
        a.d.append(c);
    }
    
    private void b() {
        final int l = com.easypano.tourweaver.a.e.l;
        final StringBuffer d = this.d;
        if (l == 0) {
            if (d == null) {
                return;
            }
            final StringBuffer d2 = this.d;
        }
        int n2;
        final int n = n2 = (d.toString().trim().equals("") ? 1 : 0);
        if (l == 0) {
            if (n != 0) {
                return;
            }
            n2 = this.a.size();
        }
        int i = n2 - 1;
        while (i >= 0) {
            final b b = this.a.elementAt(i);
            if (l != 0) {
                return;
            }
            if (l == 0) {
                if (!b.a()) {
                    final char[] array = new char[this.d.length()];
                    this.d.getChars(0, array.length, array, 0);
                    b.b(this.a(array, 0, array.length, new char[array.length]).trim());
                    if (l == 0) {
                        break;
                    }
                }
                --i;
            }
            if (l != 0) {
                break;
            }
        }
        this.a(this.d);
    }
    
    private void c() {
        final int l = com.easypano.tourweaver.a.e.l;
        final int length = this.d.length();
        Label_0053: {
            if (l == 0) {
                if (length <= 0) {
                    break Label_0053;
                }
                this.d.charAt(0);
            }
            final int n = length;
            Label_0049: {
                Label_0045: {
                    if (l == 0) {
                        if (n != 47) {
                            break Label_0045;
                        }
                        this.e();
                    }
                    if (l == 0) {
                        break Label_0049;
                    }
                }
                this.d();
            }
            if (l == 0) {
                return;
            }
        }
        System.out.println(com.easypano.tourweaver.a.a.z[0]);
    }
    
    private b a(b b) {
        final int l = com.easypano.tourweaver.a.e.l;
        while (b != null) {
            final b b2 = b;
            if (l == 0) {
                if (!b2.a()) {
                    return b;
                }
                b.d();
            }
            b = b2;
            if (l != 0) {
                break;
            }
        }
        return null;
    }
    
    private void d() {
        final int l = com.easypano.tourweaver.a.e.l;
        final b b = new b(this.d.toString().trim());
        final int size = this.a.size();
        if (l == 0) {
            if (size != 0) {
                final b a = this.a(this.a.elementAt(size - 1));
                if (l != 0) {
                    return;
                }
                if (a != null) {
                    a.b(b);
                }
            }
            this.a.addElement(b);
            this.a(this.d);
        }
    }
    
    private b b(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        if (s == null) {
            return null;
        }
        int i = this.a.size() - 1;
        while (i >= 0) {
            final b b = this.a.elementAt(i);
            final String b2 = b.b();
            if (l == 0) {
                if (b2 != null && b2.equals(s)) {
                    return b;
                }
                --i;
            }
            if (l != 0) {
                break;
            }
        }
        return null;
    }
    
    private void e() {
        final int l = com.easypano.tourweaver.a.e.l;
        final b b = this.b(this.d.toString().trim().substring(1));
        if (l == 0) {
            if (b != null) {
                b.a(true);
                if (l != 0) {
                    return;
                }
                if (b.b() != null && b.b().toLowerCase().equals(com.easypano.tourweaver.a.a.z[2])) {
                    this.f = true;
                }
            }
            this.a(this.d);
        }
    }
    
    public void a(final StringBuffer sb) {
        final int l = com.easypano.tourweaver.a.e.l;
        StringBuffer sb2 = sb;
        final StringBuffer b = this.b;
        if (l == 0) {
            if (sb == b) {
                this.b = new StringBuffer();
                if (l == 0) {
                    return;
                }
            }
            sb2 = sb;
            final StringBuffer c = this.c;
        }
        if (sb2 == b) {
            this.c = new StringBuffer();
        }
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "r~$\u00005".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0005';
                            break;
                        }
                        case 1: {
                            c2 = '\f';
                            break;
                        }
                        case 2: {
                            c2 = 'K';
                            break;
                        }
                        case 3: {
                            c2 = 'n';
                            break;
                        }
                        default: {
                            c2 = 'R';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Hm'\b=wa.\nrYy3\u0016*},.\u00001jh\"\u00005+".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0005';
                            break;
                        }
                        case 1: {
                            c4 = '\f';
                            break;
                        }
                        case 2: {
                            c4 = 'K';
                            break;
                        }
                        case 3: {
                            c4 = 'n';
                            break;
                        }
                        default: {
                            c4 = 'R';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "wc$\u001a".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0005';
                            break;
                        }
                        case 1: {
                            c6 = '\f';
                            break;
                        }
                        case 2: {
                            c6 = 'K';
                            break;
                        }
                        case 3: {
                            c6 = 'n';
                            break;
                        }
                        default: {
                            c6 = 'R';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                a.z = z;
                return;
            }
            continue;
        }
    }
}
