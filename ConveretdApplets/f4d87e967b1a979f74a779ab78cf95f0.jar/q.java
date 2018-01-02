import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends t implements Serializable
{
    public f p;
    public f d;
    public d a;
    public i n;
    private int[] v;
    private int[] i;
    private int[] l;
    static int b;
    
    public q() {
        this.p = new f("radius", this, 1, false);
        this.d = new f("light position", this, 1, false);
        this.a = new d("alpha", this, 1, true);
        this.n = new i("image", this, 3, true);
        this.v = new int[1024];
        this.i = new int[256];
        this.l = new int[256];
        this.p.p(0.05f);
        this.p.d(0.05f);
        this.a.p(0.85f);
        this.d.p(1.0f);
        this.d.d(1.0f);
    }
    
    public q(final float n, final float n2, final float n3) {
        this.p = new f("radius", this, 1, false);
        this.d = new f("light position", this, 1, false);
        this.a = new d("alpha", this, 1, true);
        this.n = new i("image", this, 3, true);
        this.v = new int[1024];
        this.i = new int[256];
        this.l = new int[256];
        this.a.p(n);
        this.p.p(n2);
        this.p.d(n3);
    }
    
    public final boolean initialize() {
        for (int i = 0; i < this.n.d; ++i) {
            this.v[i] = i * this.n.p;
        }
        for (int j = 0; j < 256; ++j) {
            this.i[j] = (int)(j * this.a.p());
            this.l[j] = (int)(j * (1 - this.a.p()));
        }
        return true;
    }
    
    public final boolean execute() {
        this.initialize();
        this.p(this.n.a, this.p.p(), this.p.d(), (int)this.d.p(), (int)this.d.d(), this.n.p, this.n.d);
        return true;
    }
    
    private final void p(final int[] array, final float n, final float n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = (int)((1 - n) * 65536);
        final int n8 = (int)((1 - n2) * 65536);
        final int n9 = (int)(n3 * n * 65536);
        final int n10 = (int)(n4 * n2 * 65536);
        int n11 = n4;
        for (int i = n10 + n4 * n8; i >= n10; i -= n8) {
            int n12 = n11 * n5 + n3;
            for (int j = n9 + n3 * n7; j >= n9; j -= n7) {
                final int n13 = this.v[i >> 16] + (j >> 16);
                final int n14 = j >> 8 & 0xFF;
                final int n15 = i >> 8 & 0xFF;
                final int n16 = array[n13];
                final int n17 = array[n13 + 1];
                final int n18 = array[n13 + n5];
                final int n19 = array[n13 + n5 + 1];
                if (n16 + n17 + n18 + n19 != 0) {
                    final int n20 = n16 >> 16 & 0xFF;
                    final int n21 = n16 >> 8 & 0xFF;
                    final int n22 = n16 & 0xFF;
                    final int n23 = n17 >> 16 & 0xFF;
                    final int n24 = n17 >> 8 & 0xFF;
                    final int n25 = n17 & 0xFF;
                    final int n26 = n18 >> 16 & 0xFF;
                    final int n27 = n18 >> 8 & 0xFF;
                    final int n28 = n18 & 0xFF;
                    final int n29 = n19 >> 16 & 0xFF;
                    final int n30 = n19 >> 8 & 0xFF;
                    final int n31 = n19 & 0xFF;
                    final int n32 = (n20 << 8) + (n23 - n20) * n14;
                    final int n33 = (n32 << 8) + ((n26 << 8) + (n29 - n26) * n14 - n32) * n15;
                    final int n34 = (n21 << 8) + (n24 - n21) * n14;
                    final int n35 = (n34 << 8) + ((n27 << 8) + (n30 - n27) * n14 - n34) * n15;
                    final int n36 = (n22 << 8) + (n25 - n22) * n14;
                    array[n12] = (this.l[array[n12] >> 16 & 0xFF] + this.i[n33 >> 16] << 16 | this.l[array[n12] >> 8 & 0xFF] + this.i[n35 >> 16] << 8 | this.l[array[n12] & 0xFF] + this.i[(n36 << 8) + ((n28 << 8) + (n31 - n28) * n14 - n36) * n15 >> 16]);
                }
                else {
                    array[n12] = ((this.l[array[n12] >> 16 & 0xFF] & 0xFF) << 16 | (this.l[array[n12] >> 8 & 0xFF] & 0xFF) << 8 | (this.l[array[n12] & 0xFF] & 0xFF));
                }
                --n12;
            }
            int n37 = n11 * n5 + n3 + 1;
            for (int k = n9 + (n3 + 1) * n7; k < n9 + n7 * n5; k += n7) {
                final int n38 = this.v[i >> 16] + (k >> 16);
                final int n39 = k >> 8 & 0xFF;
                final int n40 = i >> 8 & 0xFF;
                final int n41 = array[n38];
                final int n42 = array[n38 + 1];
                final int n43 = array[n38 + n5];
                final int n44 = array[n38 + n5 + 1];
                if (n41 + n42 + n43 + n44 != 0) {
                    final int n45 = n41 >> 16 & 0xFF;
                    final int n46 = n41 >> 8 & 0xFF;
                    final int n47 = n41 & 0xFF;
                    final int n48 = n42 >> 16 & 0xFF;
                    final int n49 = n42 >> 8 & 0xFF;
                    final int n50 = n42 & 0xFF;
                    final int n51 = n43 >> 16 & 0xFF;
                    final int n52 = n43 >> 8 & 0xFF;
                    final int n53 = n43 & 0xFF;
                    final int n54 = n44 >> 16 & 0xFF;
                    final int n55 = n44 >> 8 & 0xFF;
                    final int n56 = n44 & 0xFF;
                    final int n57 = (n45 << 8) + (n48 - n45) * n39;
                    final int n58 = (n57 << 8) + ((n51 << 8) + (n54 - n51) * n39 - n57) * n40;
                    final int n59 = (n46 << 8) + (n49 - n46) * n39;
                    final int n60 = (n59 << 8) + ((n52 << 8) + (n55 - n52) * n39 - n59) * n40;
                    final int n61 = (n47 << 8) + (n50 - n47) * n39;
                    array[n37] = (this.l[array[n37] >> 16 & 0xFF] + this.i[n58 >> 16] << 16 | this.l[array[n37] >> 8 & 0xFF] + this.i[n60 >> 16] << 8 | this.l[array[n37] & 0xFF] + this.i[(n61 << 8) + ((n53 << 8) + (n56 - n53) * n39 - n61) * n40 >> 16]);
                }
                else {
                    array[n37] = ((this.l[array[n37] >> 16 & 0xFF] & 0xFF) << 16 | (this.l[array[n37] >> 8 & 0xFF] & 0xFF) << 8 | (this.l[array[n37] & 0xFF] & 0xFF));
                }
                ++n37;
            }
            --n11;
        }
        int n62 = n4 + 1;
        for (int l = n10 + (n4 + 1) * n8; l < n10 + n6 * n8; l += n8) {
            int n63 = n62 * n5 + n3;
            for (int n64 = n9 + n3 * n7; n64 >= n9; n64 -= n7) {
                final int n65 = this.v[l >> 16] + (n64 >> 16);
                final int n66 = n64 >> 8 & 0xFF;
                final int n67 = l >> 8 & 0xFF;
                final int n68 = array[n65];
                final int n69 = array[n65 + 1];
                final int n70 = array[n65 + n5];
                final int n71 = array[n65 + n5 + 1];
                if (n68 + n69 + n70 + n71 != 0) {
                    final int n72 = n68 >> 16 & 0xFF;
                    final int n73 = n68 >> 8 & 0xFF;
                    final int n74 = n68 & 0xFF;
                    final int n75 = n69 >> 16 & 0xFF;
                    final int n76 = n69 >> 8 & 0xFF;
                    final int n77 = n69 & 0xFF;
                    final int n78 = n70 >> 16 & 0xFF;
                    final int n79 = n70 >> 8 & 0xFF;
                    final int n80 = n70 & 0xFF;
                    final int n81 = n71 >> 16 & 0xFF;
                    final int n82 = n71 >> 8 & 0xFF;
                    final int n83 = n71 & 0xFF;
                    final int n84 = (n72 << 8) + (n75 - n72) * n66;
                    final int n85 = (n84 << 8) + ((n78 << 8) + (n81 - n78) * n66 - n84) * n67;
                    final int n86 = (n73 << 8) + (n76 - n73) * n66;
                    final int n87 = (n86 << 8) + ((n79 << 8) + (n82 - n79) * n66 - n86) * n67;
                    final int n88 = (n74 << 8) + (n77 - n74) * n66;
                    array[n63] = (this.l[array[n63] >> 16 & 0xFF] + this.i[n85 >> 16] << 16 | this.l[array[n63] >> 8 & 0xFF] + this.i[n87 >> 16] << 8 | this.l[array[n63] & 0xFF] + this.i[(n88 << 8) + ((n80 << 8) + (n83 - n80) * n66 - n88) * n67 >> 16]);
                }
                else {
                    array[n63] = ((this.l[array[n63] >> 16 & 0xFF] & 0xFF) << 16 | (this.l[array[n63] >> 8 & 0xFF] & 0xFF) << 8 | (this.l[array[n63] & 0xFF] & 0xFF));
                }
                --n63;
            }
            int n89 = n62 * n5 + n3 + 1;
            for (int n90 = n9 + (n3 + 1) * n7; n90 < n9 + n7 * n5; n90 += n7) {
                final int n91 = this.v[l >> 16] + (n90 >> 16);
                final int n92 = n90 >> 8 & 0xFF;
                final int n93 = l >> 8 & 0xFF;
                final int n94 = array[n91];
                final int n95 = array[n91 + 1];
                final int n96 = array[n91 + n5];
                final int n97 = array[n91 + n5 + 1];
                if (n94 + n95 + n96 + n97 != 0) {
                    final int n98 = n94 >> 16 & 0xFF;
                    final int n99 = n94 >> 8 & 0xFF;
                    final int n100 = n94 & 0xFF;
                    final int n101 = n95 >> 16 & 0xFF;
                    final int n102 = n95 >> 8 & 0xFF;
                    final int n103 = n95 & 0xFF;
                    final int n104 = n96 >> 16 & 0xFF;
                    final int n105 = n96 >> 8 & 0xFF;
                    final int n106 = n96 & 0xFF;
                    final int n107 = n97 >> 16 & 0xFF;
                    final int n108 = n97 >> 8 & 0xFF;
                    final int n109 = n97 & 0xFF;
                    final int n110 = (n98 << 8) + (n101 - n98) * n92;
                    final int n111 = (n110 << 8) + ((n104 << 8) + (n107 - n104) * n92 - n110) * n93;
                    final int n112 = (n99 << 8) + (n102 - n99) * n92;
                    final int n113 = (n112 << 8) + ((n105 << 8) + (n108 - n105) * n92 - n112) * n93;
                    final int n114 = (n100 << 8) + (n103 - n100) * n92;
                    array[n89] = (this.l[array[n89] >> 16 & 0xFF] + this.i[n111 >> 16] << 16 | this.l[array[n89] >> 8 & 0xFF] + this.i[n113 >> 16] << 8 | this.l[array[n89] & 0xFF] + this.i[(n114 << 8) + ((n106 << 8) + (n109 - n106) * n92 - n114) * n93 >> 16]);
                }
                else {
                    array[n89] = ((this.l[array[n89] >> 16 & 0xFF] & 0xFF) << 16 | (this.l[array[n89] >> 8 & 0xFF] & 0xFF) << 8 | (this.l[array[n89] & 0xFF] & 0xFF));
                }
                ++n89;
            }
            ++n62;
        }
    }
    
    static {
        q.b = 0;
    }
}
