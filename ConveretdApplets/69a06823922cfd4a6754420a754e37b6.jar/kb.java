import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class kb implements LayoutManager
{
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int[] i;
    private int[] j;
    private Vector k;
    private Vector l;
    private static final String[] z;
    
    public kb(final int n, final int n2) {
        this(n, n2, 2, 2, false, kb.z[0]);
    }
    
    public kb(int d, final int e, final int b, final int c, final boolean a, final String h) {
        this.a = false;
        if (d == 0 && e == 0) {
            d = 1;
        }
        this.d = d;
        this.e = e;
        this.b = b;
        this.c = c;
        this.a = a;
        this.h = h;
        this.k = new Vector();
        this.l = new Vector();
    }
    
    public void addLayoutComponent(String s, final Component component) {
        if (s == null) {
            s = new String(kb.z[1]);
        }
        this.k.addElement(component);
        this.l.addElement(s);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final boolean e = mb.e;
        final Insets insets = container.getInsets();
        final int componentCount = container.getComponentCount();
        int n = 0;
        Label_0167: {
            if (this.d == 0) {
                this.f = (int)Math.ceil(componentCount / this.e);
                this.g = this.e;
                if (!e) {
                    break Label_0167;
                }
                int a = q.a;
                q.a = ++a;
            }
            if (this.e == 0) {
                this.g = (int)Math.ceil(componentCount / this.d);
                this.f = this.d;
                if (!e) {
                    break Label_0167;
                }
            }
            if (this.d * this.e < componentCount) {
                this.g = (int)Math.ceil(componentCount / this.d);
                this.f = this.d;
                if (!e) {
                    break Label_0167;
                }
            }
            this.g = this.e;
            this.f = this.d;
        }
        this.i = new int[this.g];
        int i = 0;
        while (i < this.g) {
            this.i[i] = 0;
            ++i;
            if (e) {
                break;
            }
        }
        this.j = new int[this.f];
        int j = 0;
        while (j < this.f) {
            this.j[j] = 0;
            ++j;
            if (e) {
                break;
            }
        }
        int k = 0;
        while (k < this.f) {
            int l = 0;
            while (l < this.g) {
                n = k * this.g + l;
                if (n >= componentCount && !e) {
                    break;
                }
                final Dimension preferredSize = container.getComponent(k * this.g + l).getPreferredSize();
                if (this.i[l] < preferredSize.width) {
                    this.i[l] = preferredSize.width;
                }
                if (this.j[k] < preferredSize.height) {
                    this.j[k] = preferredSize.height;
                }
                ++l;
                if (e) {
                    break;
                }
            }
            if (n >= componentCount && !e) {
                break;
            }
            ++k;
            if (e) {
                break;
            }
        }
        int n2 = 0;
        int n3 = 0;
        while (n3 < this.g) {
            n2 += this.i[n3];
            ++n3;
            if (e) {
                break;
            }
        }
        int n4 = 0;
        int n5 = 0;
        while (n5 < this.f) {
            n4 += this.j[n5];
            ++n5;
            if (e) {
                break;
            }
        }
        return new Dimension(insets.left + insets.right + n2 + (this.g - 1) * this.b, insets.top + insets.bottom + n4 + (this.f - 1) * this.c);
    }
    
    public void layoutContainer(final Container container) {
        final boolean e = mb.e;
        final Insets insets = container.getInsets();
        final int left = insets.left;
        final int top = insets.top;
        final int componentCount = container.getComponentCount();
        int n = 0;
        final Dimension size = container.getSize();
        final int n2 = size.width - insets.left - insets.right;
        final int n3 = size.height - insets.top - insets.bottom;
        final Dimension preferredLayoutSize = this.preferredLayoutSize(container);
        final int n4 = preferredLayoutSize.width - insets.left - insets.right;
        final int n5 = preferredLayoutSize.height - insets.top - insets.bottom;
        this.a(container, preferredLayoutSize);
        int n6 = 0;
        Label_0154: {
            if (n4 > n2) {
                n6 = n4;
                if (!e) {
                    break Label_0154;
                }
            }
            if (this.a) {
                n6 = n2;
                if (!e) {
                    break Label_0154;
                }
            }
            n6 = n4;
        }
        int n7 = 0;
        Label_0190: {
            if (n5 > n3) {
                n7 = n5;
                if (!e) {
                    break Label_0190;
                }
            }
            if (this.a) {
                n7 = n3;
                if (!e) {
                    break Label_0190;
                }
            }
            n7 = n5;
        }
        int n8 = 0;
        int n9 = 0;
        Label_0547: {
            if (this.h.equals(kb.z[4])) {
                n8 = insets.left + (n2 - n6) / 2;
                n9 = insets.top;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[10])) {
                n8 = insets.left + (n2 - n6) / 2;
                n9 = n3 - n7 - insets.bottom;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[5])) {
                n8 = n2 - n6 - insets.right;
                n9 = insets.top - (n3 - n7) / 2;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[3])) {
                n8 = insets.left;
                n9 = insets.top - (n3 - n7) / 2;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[7])) {
                n8 = n2 - n6 - insets.right;
                n9 = insets.top;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[12])) {
                n8 = insets.left;
                n9 = insets.top;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[8])) {
                n8 = n2 - n6 - insets.right;
                n9 = n3 - n7 - insets.bottom;
                if (!e) {
                    break Label_0547;
                }
            }
            if (this.h.equals(kb.z[11])) {
                n8 = insets.left;
                n9 = n3 - n7 - insets.bottom;
                if (!e) {
                    break Label_0547;
                }
            }
            n8 = insets.left + (n2 - n6) / 2;
            n9 = insets.top + (n3 - n7) / 2;
        }
        int i = 0;
        while (i < this.f) {
            int n10 = n8;
            final int n11 = this.j[i];
            int j = 0;
            while (j < this.g) {
                final int n12 = this.i[j];
                n = i * this.g + j;
                if (n >= componentCount && !e) {
                    break;
                }
                final Component component = container.getComponent(i * this.g + j);
                final Dimension preferredSize = component.getPreferredSize();
                final int index = this.k.indexOf(component);
                String s = null;
                Label_0682: {
                    if (index != -1) {
                        s = this.l.elementAt(index);
                        if (!e) {
                            break Label_0682;
                        }
                    }
                    s = kb.z[1];
                }
                Label_1385: {
                    if (s.equals(kb.z[1])) {
                        component.setBounds(n10, n9, n12, n11);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[0])) {
                        component.setBounds(n10 + (n12 - preferredSize.width) / 2, n9 + (n11 - preferredSize.height) / 2, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[4])) {
                        component.setBounds(n10 + (n12 - preferredSize.width) / 2, n9, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[10])) {
                        component.setBounds(n10 + (n12 - preferredSize.width) / 2, n9 + n11 - preferredSize.height, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[5])) {
                        component.setBounds(n10 + n12 - preferredSize.width, n9 + (n11 - preferredSize.height) / 2, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[3])) {
                        component.setBounds(n10, n9 + (n11 - preferredSize.height) / 2, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[7])) {
                        component.setBounds(n10 + n12 - preferredSize.width, n9, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[12])) {
                        component.setBounds(n10, n9, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[8])) {
                        component.setBounds(n10 + n12 - preferredSize.width, n9 + n11 - preferredSize.height, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[11])) {
                        component.setBounds(n10, n9 + n11 - preferredSize.height, preferredSize.width, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[13])) {
                        component.setBounds(n10, n9, n12, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[2])) {
                        component.setBounds(n10, n9 + n11 - preferredSize.height, n12, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[9])) {
                        component.setBounds(n10, n9 + (n11 - preferredSize.height) / 2, n12, preferredSize.height);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[6])) {
                        component.setBounds(n10 + n12 - preferredSize.width, n9, preferredSize.width, n11);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    if (s.equals(kb.z[14])) {
                        component.setBounds(n10, n9, preferredSize.width, n11);
                        if (!e) {
                            break Label_1385;
                        }
                    }
                    component.setBounds(n10 + (n12 - preferredSize.width) / 2, n9, preferredSize.width, n11);
                }
                n10 += n12 + this.b;
                ++j;
                if (e) {
                    break;
                }
            }
            n9 += n11 + this.c;
            if (n >= componentCount && !e) {
                break;
            }
            ++i;
            if (e) {
                break;
            }
        }
        if (q.a != 0) {
            mb.e = !e;
        }
    }
    
    private void a(final Container container, final Dimension dimension) {
        final boolean e = mb.e;
        final Insets insets = container.getInsets();
        final int n = container.getSize().width - insets.left - insets.right;
        final int n2 = container.getSize().height - insets.top - insets.bottom;
        final int width = dimension.width;
        final int height = dimension.height;
        final int n3 = n - (this.g - 1) * this.b;
        final int n4 = n2 - (this.f - 1) * this.c;
        final int n5 = width - (this.g - 1) * this.b;
        final int n6 = height - (this.f - 1) * this.c;
        if (!this.a) {
            return;
        }
        Label_0195: {
            if (n3 > n5) {
                int i = n3 - n5;
                while (i > 0) {
                    int j = 0;
                    while (j < this.g) {
                        final int[] k = this.i;
                        final int n7 = j;
                        ++k[n7];
                        if (--i <= 0 && !e) {
                            break;
                        }
                        ++j;
                        if (e) {
                            break Label_0195;
                        }
                    }
                }
            }
        }
        Label_0258: {
            if (n4 > n6) {
                int l = n4 - n6;
                while (l > 0) {
                    int n8 = 0;
                    while (n8 < this.f) {
                        final int[] m = this.j;
                        final int n9 = n8;
                        ++m[n9];
                        if (--l <= 0 && !e) {
                            break;
                        }
                        ++n8;
                        if (e) {
                            break Label_0258;
                        }
                    }
                }
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void removeLayoutComponent(final Component component) {
        final int index = this.k.indexOf(component);
        if (index != -1) {
            this.k.removeElementAt(index);
            this.l.removeElementAt(index);
        }
    }
    
    static {
        final String[] z2 = new String[15];
        final int n = 0;
        final char[] charArray = "w^'7\u0012F".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '4';
                    break;
                }
                case 1: {
                    c2 = ';';
                    break;
                }
                case 2: {
                    c2 = 'I';
                    break;
                }
                case 3: {
                    c2 = 'C';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "rR%/".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '4';
                    break;
                }
                case 1: {
                    c4 = ';';
                    break;
                }
                case 2: {
                    c4 = 'I';
                    break;
                }
                case 3: {
                    c4 = 'C';
                    break;
                }
                default: {
                    c4 = 'w';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "|] /\u001bGT<7\u001f".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '4';
                    break;
                }
                case 1: {
                    c6 = ';';
                    break;
                }
                case 2: {
                    c6 = 'I';
                    break;
                }
                case 3: {
                    c6 = 'C';
                    break;
                }
                default: {
                    c6 = 'w';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "c^:7".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '4';
                    break;
                }
                case 1: {
                    c8 = ';';
                    break;
                }
                case 2: {
                    c8 = 'I';
                    break;
                }
                case 3: {
                    c8 = 'C';
                    break;
                }
                default: {
                    c8 = 'w';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "zT;7\u001f".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '4';
                    break;
                }
                case 1: {
                    c10 = ';';
                    break;
                }
                case 2: {
                    c10 = 'I';
                    break;
                }
                case 3: {
                    c10 = 'C';
                    break;
                }
                default: {
                    c10 = 'w';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "qZ:7".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '4';
                    break;
                }
                case 1: {
                    c12 = ';';
                    break;
                }
                case 2: {
                    c12 = 'I';
                    break;
                }
                case 3: {
                    c12 = 'C';
                    break;
                }
                default: {
                    c12 = 'w';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "b] /\u001bQZ:7".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '4';
                    break;
                }
                case 1: {
                    c14 = ';';
                    break;
                }
                case 2: {
                    c14 = 'I';
                    break;
                }
                case 3: {
                    c14 = 'C';
                    break;
                }
                default: {
                    c14 = 'w';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "zT;7\u001fQZ:7".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '4';
                    break;
                }
                case 1: {
                    c16 = ';';
                    break;
                }
                case 2: {
                    c16 = 'I';
                    break;
                }
                case 3: {
                    c16 = 'C';
                    break;
                }
                default: {
                    c16 = 'w';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "gT<7\u001fQZ:7".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '4';
                    break;
                }
                case 1: {
                    c18 = ';';
                    break;
                }
                case 2: {
                    c18 = 'I';
                    break;
                }
                case 3: {
                    c18 = 'C';
                    break;
                }
                default: {
                    c18 = 'w';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "|] /\u001bW^'7\u0012F".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '4';
                    break;
                }
                case 1: {
                    c20 = ';';
                    break;
                }
                case 2: {
                    c20 = 'I';
                    break;
                }
                case 3: {
                    c20 = 'C';
                    break;
                }
                default: {
                    c20 = 'w';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "gT<7\u001f".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '4';
                    break;
                }
                case 1: {
                    c22 = ';';
                    break;
                }
                case 2: {
                    c22 = 'I';
                    break;
                }
                case 3: {
                    c22 = 'C';
                    break;
                }
                default: {
                    c22 = 'w';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "gT<7\u001fC^:7".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '4';
                    break;
                }
                case 1: {
                    c24 = ';';
                    break;
                }
                case 2: {
                    c24 = 'I';
                    break;
                }
                case 3: {
                    c24 = 'C';
                    break;
                }
                default: {
                    c24 = 'w';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "zT;7\u001fC^:7".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '4';
                    break;
                }
                case 1: {
                    c26 = ';';
                    break;
                }
                case 2: {
                    c26 = 'I';
                    break;
                }
                case 3: {
                    c26 = 'C';
                    break;
                }
                default: {
                    c26 = 'w';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z2[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "|] /\u001bZT;7\u001f".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '4';
                    break;
                }
                case 1: {
                    c28 = ';';
                    break;
                }
                case 2: {
                    c28 = 'I';
                    break;
                }
                case 3: {
                    c28 = 'C';
                    break;
                }
                default: {
                    c28 = 'w';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z2[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "b] /\u001bC^:7".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = '4';
                    break;
                }
                case 1: {
                    c30 = ';';
                    break;
                }
                case 2: {
                    c30 = 'I';
                    break;
                }
                case 3: {
                    c30 = 'C';
                    break;
                }
                default: {
                    c30 = 'w';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z2[n43] = new String(charArray15).intern();
        z = z2;
    }
}
