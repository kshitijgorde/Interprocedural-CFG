// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

import java.io.IOException;

final class SynthesisFilter
{
    private float[] v1;
    private float[] v2;
    private float[] actual_v;
    private int actual_write_pos;
    private float[] samples;
    private int channel;
    private float scalefactor;
    private float[] eq;
    private float[] _tmpOut;
    private static final double MY_PI = 3.141592653589793;
    private static final float cos1_64;
    private static final float cos3_64;
    private static final float cos5_64;
    private static final float cos7_64;
    private static final float cos9_64;
    private static final float cos11_64;
    private static final float cos13_64;
    private static final float cos15_64;
    private static final float cos17_64;
    private static final float cos19_64;
    private static final float cos21_64;
    private static final float cos23_64;
    private static final float cos25_64;
    private static final float cos27_64;
    private static final float cos29_64;
    private static final float cos31_64;
    private static final float cos1_32;
    private static final float cos3_32;
    private static final float cos5_32;
    private static final float cos7_32;
    private static final float cos9_32;
    private static final float cos11_32;
    private static final float cos13_32;
    private static final float cos15_32;
    private static final float cos1_16;
    private static final float cos3_16;
    private static final float cos5_16;
    private static final float cos7_16;
    private static final float cos1_8;
    private static final float cos3_8;
    private static final float cos1_4;
    private static float[] d;
    private static float[][] d16;
    
    public SynthesisFilter(final int channel, final float scalefactor, final float[] array) {
        this._tmpOut = new float[32];
        if (SynthesisFilter.d == null) {
            SynthesisFilter.d = load_d();
            SynthesisFilter.d16 = splitArray(SynthesisFilter.d, 16);
        }
        this.v1 = new float[512];
        this.v2 = new float[512];
        this.samples = new float[32];
        this.channel = channel;
        this.scalefactor = scalefactor;
        this.setEQ(this.eq);
        this.reset();
    }
    
    public void setEQ(final float[] eq) {
        this.eq = eq;
        if (this.eq == null) {
            this.eq = new float[32];
            for (int i = 0; i < 32; ++i) {
                this.eq[i] = 1.0f;
            }
        }
        if (this.eq.length < 32) {
            throw new IllegalArgumentException("eq0");
        }
    }
    
    public void reset() {
        for (int i = 0; i < 512; ++i) {
            this.v1[i] = (this.v2[i] = 0.0f);
        }
        for (int j = 0; j < 32; ++j) {
            this.samples[j] = 0.0f;
        }
        this.actual_v = this.v1;
        this.actual_write_pos = 15;
    }
    
    public void input_sample(final float n, final int n2) {
        this.samples[n2] = this.eq[n2] * n;
    }
    
    public void input_samples(final float[] array) {
        for (int i = 31; i >= 0; --i) {
            this.samples[i] = array[i] * this.eq[i];
        }
    }
    
    private void compute_new_v() {
        final float[] samples = this.samples;
        final float n = samples[0];
        final float n2 = samples[1];
        final float n3 = samples[2];
        final float n4 = samples[3];
        final float n5 = samples[4];
        final float n6 = samples[5];
        final float n7 = samples[6];
        final float n8 = samples[7];
        final float n9 = samples[8];
        final float n10 = samples[9];
        final float n11 = samples[10];
        final float n12 = samples[11];
        final float n13 = samples[12];
        final float n14 = samples[13];
        final float n15 = samples[14];
        final float n16 = samples[15];
        final float n17 = samples[16];
        final float n18 = samples[17];
        final float n19 = samples[18];
        final float n20 = samples[19];
        final float n21 = samples[20];
        final float n22 = samples[21];
        final float n23 = samples[22];
        final float n24 = samples[23];
        final float n25 = samples[24];
        final float n26 = samples[25];
        final float n27 = samples[26];
        final float n28 = samples[27];
        final float n29 = samples[28];
        final float n30 = samples[29];
        final float n31 = samples[30];
        final float n32 = samples[31];
        final float n33 = n + n32;
        final float n34 = n2 + n31;
        final float n35 = n3 + n30;
        final float n36 = n4 + n29;
        final float n37 = n5 + n28;
        final float n38 = n6 + n27;
        final float n39 = n7 + n26;
        final float n40 = n8 + n25;
        final float n41 = n9 + n24;
        final float n42 = n10 + n23;
        final float n43 = n11 + n22;
        final float n44 = n12 + n21;
        final float n45 = n13 + n20;
        final float n46 = n14 + n19;
        final float n47 = n15 + n18;
        final float n48 = n16 + n17;
        final float n49 = n33 + n48;
        final float n50 = n34 + n47;
        final float n51 = n35 + n46;
        final float n52 = n36 + n45;
        final float n53 = n37 + n44;
        final float n54 = n38 + n43;
        final float n55 = n39 + n42;
        final float n56 = n40 + n41;
        final float n57 = (n33 - n48) * SynthesisFilter.cos1_32;
        final float n58 = (n34 - n47) * SynthesisFilter.cos3_32;
        final float n59 = (n35 - n46) * SynthesisFilter.cos5_32;
        final float n60 = (n36 - n45) * SynthesisFilter.cos7_32;
        final float n61 = (n37 - n44) * SynthesisFilter.cos9_32;
        final float n62 = (n38 - n43) * SynthesisFilter.cos11_32;
        final float n63 = (n39 - n42) * SynthesisFilter.cos13_32;
        final float n64 = (n40 - n41) * SynthesisFilter.cos15_32;
        final float n65 = n49 + n56;
        final float n66 = n50 + n55;
        final float n67 = n51 + n54;
        final float n68 = n52 + n53;
        final float n69 = (n49 - n56) * SynthesisFilter.cos1_16;
        final float n70 = (n50 - n55) * SynthesisFilter.cos3_16;
        final float n71 = (n51 - n54) * SynthesisFilter.cos5_16;
        final float n72 = (n52 - n53) * SynthesisFilter.cos7_16;
        final float n73 = n57 + n64;
        final float n74 = n58 + n63;
        final float n75 = n59 + n62;
        final float n76 = n60 + n61;
        final float n77 = (n57 - n64) * SynthesisFilter.cos1_16;
        final float n78 = (n58 - n63) * SynthesisFilter.cos3_16;
        final float n79 = (n59 - n62) * SynthesisFilter.cos5_16;
        final float n80 = (n60 - n61) * SynthesisFilter.cos7_16;
        final float n81 = n65 + n68;
        final float n82 = n66 + n67;
        final float n83 = (n65 - n68) * SynthesisFilter.cos1_8;
        final float n84 = (n66 - n67) * SynthesisFilter.cos3_8;
        final float n85 = n69 + n72;
        final float n86 = n70 + n71;
        final float n87 = (n69 - n72) * SynthesisFilter.cos1_8;
        final float n88 = (n70 - n71) * SynthesisFilter.cos3_8;
        final float n89 = n73 + n76;
        final float n90 = n74 + n75;
        final float n91 = (n73 - n76) * SynthesisFilter.cos1_8;
        final float n92 = (n74 - n75) * SynthesisFilter.cos3_8;
        final float n93 = n77 + n80;
        final float n94 = n78 + n79;
        final float n95 = (n77 - n80) * SynthesisFilter.cos1_8;
        final float n96 = (n78 - n79) * SynthesisFilter.cos3_8;
        final float n97 = n81 + n82;
        final float n98 = (n81 - n82) * SynthesisFilter.cos1_4;
        final float n99 = n83 + n84;
        final float n100 = (n83 - n84) * SynthesisFilter.cos1_4;
        final float n101 = n85 + n86;
        final float n102 = (n85 - n86) * SynthesisFilter.cos1_4;
        final float n103 = n87 + n88;
        final float n104 = (n87 - n88) * SynthesisFilter.cos1_4;
        final float n105 = n89 + n90;
        final float n106 = (n89 - n90) * SynthesisFilter.cos1_4;
        final float n107 = n91 + n92;
        final float n108 = (n91 - n92) * SynthesisFilter.cos1_4;
        final float n109 = n93 + n94;
        final float n110 = (n93 - n94) * SynthesisFilter.cos1_4;
        final float n111 = n95 + n96;
        final float n112 = (n95 - n96) * SynthesisFilter.cos1_4;
        final float n115;
        final float n114;
        final float n113 = -(n114 = (n115 = n104) + n102) - n103;
        final float n116 = -n103 - n104 - n101;
        final float n119;
        final float n118;
        final float n117 = (n118 = (n119 = n112) + n108) + n110;
        final float n121;
        final float n120 = -(n121 = n112 + n110 + n106) - n111;
        final float n123;
        final float n122 = (n123 = -n111 - n112 - n107 - n108) - n110;
        final float n124 = -n111 - n112 - n109 - n105;
        final float n125 = n123 - n109;
        final float n126 = -n97;
        final float n127 = n98;
        final float n129;
        final float n128 = -(n129 = n100) - n99;
        final float n130 = (n - n32) * SynthesisFilter.cos1_64;
        final float n131 = (n2 - n31) * SynthesisFilter.cos3_64;
        final float n132 = (n3 - n30) * SynthesisFilter.cos5_64;
        final float n133 = (n4 - n29) * SynthesisFilter.cos7_64;
        final float n134 = (n5 - n28) * SynthesisFilter.cos9_64;
        final float n135 = (n6 - n27) * SynthesisFilter.cos11_64;
        final float n136 = (n7 - n26) * SynthesisFilter.cos13_64;
        final float n137 = (n8 - n25) * SynthesisFilter.cos15_64;
        final float n138 = (n9 - n24) * SynthesisFilter.cos17_64;
        final float n139 = (n10 - n23) * SynthesisFilter.cos19_64;
        final float n140 = (n11 - n22) * SynthesisFilter.cos21_64;
        final float n141 = (n12 - n21) * SynthesisFilter.cos23_64;
        final float n142 = (n13 - n20) * SynthesisFilter.cos25_64;
        final float n143 = (n14 - n19) * SynthesisFilter.cos27_64;
        final float n144 = (n15 - n18) * SynthesisFilter.cos29_64;
        final float n145 = (n16 - n17) * SynthesisFilter.cos31_64;
        final float n146 = n130 + n145;
        final float n147 = n131 + n144;
        final float n148 = n132 + n143;
        final float n149 = n133 + n142;
        final float n150 = n134 + n141;
        final float n151 = n135 + n140;
        final float n152 = n136 + n139;
        final float n153 = n137 + n138;
        final float n154 = (n130 - n145) * SynthesisFilter.cos1_32;
        final float n155 = (n131 - n144) * SynthesisFilter.cos3_32;
        final float n156 = (n132 - n143) * SynthesisFilter.cos5_32;
        final float n157 = (n133 - n142) * SynthesisFilter.cos7_32;
        final float n158 = (n134 - n141) * SynthesisFilter.cos9_32;
        final float n159 = (n135 - n140) * SynthesisFilter.cos11_32;
        final float n160 = (n136 - n139) * SynthesisFilter.cos13_32;
        final float n161 = (n137 - n138) * SynthesisFilter.cos15_32;
        final float n162 = n146 + n153;
        final float n163 = n147 + n152;
        final float n164 = n148 + n151;
        final float n165 = n149 + n150;
        final float n166 = (n146 - n153) * SynthesisFilter.cos1_16;
        final float n167 = (n147 - n152) * SynthesisFilter.cos3_16;
        final float n168 = (n148 - n151) * SynthesisFilter.cos5_16;
        final float n169 = (n149 - n150) * SynthesisFilter.cos7_16;
        final float n170 = n154 + n161;
        final float n171 = n155 + n160;
        final float n172 = n156 + n159;
        final float n173 = n157 + n158;
        final float n174 = (n154 - n161) * SynthesisFilter.cos1_16;
        final float n175 = (n155 - n160) * SynthesisFilter.cos3_16;
        final float n176 = (n156 - n159) * SynthesisFilter.cos5_16;
        final float n177 = (n157 - n158) * SynthesisFilter.cos7_16;
        final float n178 = n162 + n165;
        final float n179 = n163 + n164;
        final float n180 = (n162 - n165) * SynthesisFilter.cos1_8;
        final float n181 = (n163 - n164) * SynthesisFilter.cos3_8;
        final float n182 = n166 + n169;
        final float n183 = n167 + n168;
        final float n184 = (n166 - n169) * SynthesisFilter.cos1_8;
        final float n185 = (n167 - n168) * SynthesisFilter.cos3_8;
        final float n186 = n170 + n173;
        final float n187 = n171 + n172;
        final float n188 = (n170 - n173) * SynthesisFilter.cos1_8;
        final float n189 = (n171 - n172) * SynthesisFilter.cos3_8;
        final float n190 = n174 + n177;
        final float n191 = n175 + n176;
        final float n192 = (n174 - n177) * SynthesisFilter.cos1_8;
        final float n193 = (n175 - n176) * SynthesisFilter.cos3_8;
        final float n194 = n178 + n179;
        final float n195 = (n178 - n179) * SynthesisFilter.cos1_4;
        final float n196 = n180 + n181;
        final float n197 = (n180 - n181) * SynthesisFilter.cos1_4;
        final float n198 = n182 + n183;
        final float n199 = (n182 - n183) * SynthesisFilter.cos1_4;
        final float n200 = n184 + n185;
        final float n201 = (n184 - n185) * SynthesisFilter.cos1_4;
        final float n202 = n186 + n187;
        final float n203 = (n186 - n187) * SynthesisFilter.cos1_4;
        final float n204 = n188 + n189;
        final float n205 = (n188 - n189) * SynthesisFilter.cos1_4;
        final float n206 = n190 + n191;
        final float n207 = (n190 - n191) * SynthesisFilter.cos1_4;
        final float n208 = n192 + n193;
        final float n209 = (n192 - n193) * SynthesisFilter.cos1_4;
        final float n213;
        final float n212;
        final float n211;
        final float n210 = (n211 = (n212 = (n213 = n209) + n201) + n205) + n199 + n207;
        final float n215;
        final float n214 = (n215 = n209 + n205 + n197) + n207;
        final float n218;
        final float n217;
        final float n216 = -(n217 = (n218 = n207 + n209 + n203) + n195) - n208;
        final float n220;
        final float n219 = -(n220 = n218 + n199 + n201) - n200 - n208;
        final float n222;
        final float n221 = (n222 = -n204 - n205 - n208 - n209) - n207 - n196 - n197;
        final float n223 = n222 - n207 - n199 - n200 - n201;
        final float n224 = n222 - n206 - n196 - n197;
        final float n226;
        final float n225 = n222 - n206 - (n226 = n198 + n200 + n201);
        final float n228;
        final float n227 = (n228 = -n202 - n206 - n208 - n209) - n194;
        final float n229 = n228 - n226;
        final float[] actual_v = this.actual_v;
        final int actual_write_pos = this.actual_write_pos;
        actual_v[0 + actual_write_pos] = n127;
        actual_v[16 + actual_write_pos] = n217;
        actual_v[32 + actual_write_pos] = n121;
        actual_v[48 + actual_write_pos] = n220;
        actual_v[64 + actual_write_pos] = n114;
        actual_v[80 + actual_write_pos] = n210;
        actual_v[96 + actual_write_pos] = n117;
        actual_v[112 + actual_write_pos] = n214;
        actual_v[128 + actual_write_pos] = n129;
        actual_v[144 + actual_write_pos] = n215;
        actual_v[160 + actual_write_pos] = n118;
        actual_v[176 + actual_write_pos] = n211;
        actual_v[192 + actual_write_pos] = n115;
        actual_v[208 + actual_write_pos] = n212;
        actual_v[224 + actual_write_pos] = n119;
        actual_v[240 + actual_write_pos] = n213;
        actual_v[256 + actual_write_pos] = 0.0f;
        actual_v[272 + actual_write_pos] = -n213;
        actual_v[288 + actual_write_pos] = -n119;
        actual_v[304 + actual_write_pos] = -n212;
        actual_v[320 + actual_write_pos] = -n115;
        actual_v[336 + actual_write_pos] = -n211;
        actual_v[352 + actual_write_pos] = -n118;
        actual_v[368 + actual_write_pos] = -n215;
        actual_v[384 + actual_write_pos] = -n129;
        actual_v[400 + actual_write_pos] = -n214;
        actual_v[416 + actual_write_pos] = -n117;
        actual_v[432 + actual_write_pos] = -n210;
        actual_v[448 + actual_write_pos] = -n114;
        actual_v[464 + actual_write_pos] = -n220;
        actual_v[480 + actual_write_pos] = -n121;
        actual_v[496 + actual_write_pos] = -n217;
        final float[] array = (this.actual_v == this.v1) ? this.v2 : this.v1;
        array[0 + actual_write_pos] = -n127;
        array[16 + actual_write_pos] = n216;
        array[32 + actual_write_pos] = n120;
        array[48 + actual_write_pos] = n219;
        array[64 + actual_write_pos] = n113;
        array[80 + actual_write_pos] = n223;
        array[96 + actual_write_pos] = n122;
        array[112 + actual_write_pos] = n221;
        array[128 + actual_write_pos] = n128;
        array[144 + actual_write_pos] = n224;
        array[160 + actual_write_pos] = n125;
        array[176 + actual_write_pos] = n225;
        array[192 + actual_write_pos] = n116;
        array[208 + actual_write_pos] = n229;
        array[224 + actual_write_pos] = n124;
        array[240 + actual_write_pos] = n227;
        array[256 + actual_write_pos] = n126;
        array[272 + actual_write_pos] = n227;
        array[288 + actual_write_pos] = n124;
        array[304 + actual_write_pos] = n229;
        array[320 + actual_write_pos] = n116;
        array[336 + actual_write_pos] = n225;
        array[352 + actual_write_pos] = n125;
        array[368 + actual_write_pos] = n224;
        array[384 + actual_write_pos] = n128;
        array[400 + actual_write_pos] = n221;
        array[416 + actual_write_pos] = n122;
        array[432 + actual_write_pos] = n223;
        array[448 + actual_write_pos] = n113;
        array[464 + actual_write_pos] = n219;
        array[480 + actual_write_pos] = n120;
        array[496 + actual_write_pos] = n216;
    }
    
    private void compute_new_v_old() {
        final float[] array = new float[32];
        final float[] array2 = new float[16];
        final float[] array3 = new float[16];
        for (int i = 31; i >= 0; --i) {
            array[i] = 0.0f;
        }
        final float[] samples = this.samples;
        array2[0] = samples[0] + samples[31];
        array2[1] = samples[1] + samples[30];
        array2[2] = samples[2] + samples[29];
        array2[3] = samples[3] + samples[28];
        array2[4] = samples[4] + samples[27];
        array2[5] = samples[5] + samples[26];
        array2[6] = samples[6] + samples[25];
        array2[7] = samples[7] + samples[24];
        array2[8] = samples[8] + samples[23];
        array2[9] = samples[9] + samples[22];
        array2[10] = samples[10] + samples[21];
        array2[11] = samples[11] + samples[20];
        array2[12] = samples[12] + samples[19];
        array2[13] = samples[13] + samples[18];
        array2[14] = samples[14] + samples[17];
        array2[15] = samples[15] + samples[16];
        array3[0] = array2[0] + array2[15];
        array3[1] = array2[1] + array2[14];
        array3[2] = array2[2] + array2[13];
        array3[3] = array2[3] + array2[12];
        array3[4] = array2[4] + array2[11];
        array3[5] = array2[5] + array2[10];
        array3[6] = array2[6] + array2[9];
        array3[7] = array2[7] + array2[8];
        array3[8] = (array2[0] - array2[15]) * SynthesisFilter.cos1_32;
        array3[9] = (array2[1] - array2[14]) * SynthesisFilter.cos3_32;
        array3[10] = (array2[2] - array2[13]) * SynthesisFilter.cos5_32;
        array3[11] = (array2[3] - array2[12]) * SynthesisFilter.cos7_32;
        array3[12] = (array2[4] - array2[11]) * SynthesisFilter.cos9_32;
        array3[13] = (array2[5] - array2[10]) * SynthesisFilter.cos11_32;
        array3[14] = (array2[6] - array2[9]) * SynthesisFilter.cos13_32;
        array3[15] = (array2[7] - array2[8]) * SynthesisFilter.cos15_32;
        array2[0] = array3[0] + array3[7];
        array2[1] = array3[1] + array3[6];
        array2[2] = array3[2] + array3[5];
        array2[3] = array3[3] + array3[4];
        array2[4] = (array3[0] - array3[7]) * SynthesisFilter.cos1_16;
        array2[5] = (array3[1] - array3[6]) * SynthesisFilter.cos3_16;
        array2[6] = (array3[2] - array3[5]) * SynthesisFilter.cos5_16;
        array2[7] = (array3[3] - array3[4]) * SynthesisFilter.cos7_16;
        array2[8] = array3[8] + array3[15];
        array2[9] = array3[9] + array3[14];
        array2[10] = array3[10] + array3[13];
        array2[11] = array3[11] + array3[12];
        array2[12] = (array3[8] - array3[15]) * SynthesisFilter.cos1_16;
        array2[13] = (array3[9] - array3[14]) * SynthesisFilter.cos3_16;
        array2[14] = (array3[10] - array3[13]) * SynthesisFilter.cos5_16;
        array2[15] = (array3[11] - array3[12]) * SynthesisFilter.cos7_16;
        array3[0] = array2[0] + array2[3];
        array3[1] = array2[1] + array2[2];
        array3[2] = (array2[0] - array2[3]) * SynthesisFilter.cos1_8;
        array3[3] = (array2[1] - array2[2]) * SynthesisFilter.cos3_8;
        array3[4] = array2[4] + array2[7];
        array3[5] = array2[5] + array2[6];
        array3[6] = (array2[4] - array2[7]) * SynthesisFilter.cos1_8;
        array3[7] = (array2[5] - array2[6]) * SynthesisFilter.cos3_8;
        array3[8] = array2[8] + array2[11];
        array3[9] = array2[9] + array2[10];
        array3[10] = (array2[8] - array2[11]) * SynthesisFilter.cos1_8;
        array3[11] = (array2[9] - array2[10]) * SynthesisFilter.cos3_8;
        array3[12] = array2[12] + array2[15];
        array3[13] = array2[13] + array2[14];
        array3[14] = (array2[12] - array2[15]) * SynthesisFilter.cos1_8;
        array3[15] = (array2[13] - array2[14]) * SynthesisFilter.cos3_8;
        array2[0] = array3[0] + array3[1];
        array2[1] = (array3[0] - array3[1]) * SynthesisFilter.cos1_4;
        array2[2] = array3[2] + array3[3];
        array2[3] = (array3[2] - array3[3]) * SynthesisFilter.cos1_4;
        array2[4] = array3[4] + array3[5];
        array2[5] = (array3[4] - array3[5]) * SynthesisFilter.cos1_4;
        array2[6] = array3[6] + array3[7];
        array2[7] = (array3[6] - array3[7]) * SynthesisFilter.cos1_4;
        array2[8] = array3[8] + array3[9];
        array2[9] = (array3[8] - array3[9]) * SynthesisFilter.cos1_4;
        array2[10] = array3[10] + array3[11];
        array2[11] = (array3[10] - array3[11]) * SynthesisFilter.cos1_4;
        array2[12] = array3[12] + array3[13];
        array2[13] = (array3[12] - array3[13]) * SynthesisFilter.cos1_4;
        array2[14] = array3[14] + array3[15];
        array2[15] = (array3[14] - array3[15]) * SynthesisFilter.cos1_4;
        final float[] array4 = array;
        final int n = 19;
        final float[] array5 = array;
        final int n2 = 4;
        final float[] array6 = array;
        final int n3 = 12;
        final float n4 = array2[7];
        array6[n3] = n4;
        final float n5 = n4 + array2[5];
        array5[n2] = n5;
        array4[n] = -n5 - array2[6];
        array[27] = -array2[6] - array2[7] - array2[4];
        final float[] array7 = array;
        final int n6 = 6;
        final float[] array8 = array;
        final int n7 = 10;
        final float[] array9 = array;
        final int n8 = 14;
        final float n9 = array2[15];
        array9[n8] = n9;
        final float n10 = n9 + array2[11];
        array8[n7] = n10;
        array7[n6] = n10 + array2[13];
        final float[] array10 = array;
        final int n11 = 17;
        final float[] array11 = array;
        final int n12 = 2;
        final float n13 = array2[15] + array2[13] + array2[9];
        array11[n12] = n13;
        array10[n11] = -n13 - array2[14];
        final float n14;
        array[21] = (n14 = -array2[14] - array2[15] - array2[10] - array2[11]) - array2[13];
        array[29] = -array2[14] - array2[15] - array2[12] - array2[8];
        array[25] = n14 - array2[12];
        array[31] = -array2[0];
        array[0] = array2[1];
        final float[] array12 = array;
        final int n15 = 23;
        final float[] array13 = array;
        final int n16 = 8;
        final float n17 = array2[3];
        array13[n16] = n17;
        array12[n15] = -n17 - array2[2];
        array2[0] = (samples[0] - samples[31]) * SynthesisFilter.cos1_64;
        array2[1] = (samples[1] - samples[30]) * SynthesisFilter.cos3_64;
        array2[2] = (samples[2] - samples[29]) * SynthesisFilter.cos5_64;
        array2[3] = (samples[3] - samples[28]) * SynthesisFilter.cos7_64;
        array2[4] = (samples[4] - samples[27]) * SynthesisFilter.cos9_64;
        array2[5] = (samples[5] - samples[26]) * SynthesisFilter.cos11_64;
        array2[6] = (samples[6] - samples[25]) * SynthesisFilter.cos13_64;
        array2[7] = (samples[7] - samples[24]) * SynthesisFilter.cos15_64;
        array2[8] = (samples[8] - samples[23]) * SynthesisFilter.cos17_64;
        array2[9] = (samples[9] - samples[22]) * SynthesisFilter.cos19_64;
        array2[10] = (samples[10] - samples[21]) * SynthesisFilter.cos21_64;
        array2[11] = (samples[11] - samples[20]) * SynthesisFilter.cos23_64;
        array2[12] = (samples[12] - samples[19]) * SynthesisFilter.cos25_64;
        array2[13] = (samples[13] - samples[18]) * SynthesisFilter.cos27_64;
        array2[14] = (samples[14] - samples[17]) * SynthesisFilter.cos29_64;
        array2[15] = (samples[15] - samples[16]) * SynthesisFilter.cos31_64;
        array3[0] = array2[0] + array2[15];
        array3[1] = array2[1] + array2[14];
        array3[2] = array2[2] + array2[13];
        array3[3] = array2[3] + array2[12];
        array3[4] = array2[4] + array2[11];
        array3[5] = array2[5] + array2[10];
        array3[6] = array2[6] + array2[9];
        array3[7] = array2[7] + array2[8];
        array3[8] = (array2[0] - array2[15]) * SynthesisFilter.cos1_32;
        array3[9] = (array2[1] - array2[14]) * SynthesisFilter.cos3_32;
        array3[10] = (array2[2] - array2[13]) * SynthesisFilter.cos5_32;
        array3[11] = (array2[3] - array2[12]) * SynthesisFilter.cos7_32;
        array3[12] = (array2[4] - array2[11]) * SynthesisFilter.cos9_32;
        array3[13] = (array2[5] - array2[10]) * SynthesisFilter.cos11_32;
        array3[14] = (array2[6] - array2[9]) * SynthesisFilter.cos13_32;
        array3[15] = (array2[7] - array2[8]) * SynthesisFilter.cos15_32;
        array2[0] = array3[0] + array3[7];
        array2[1] = array3[1] + array3[6];
        array2[2] = array3[2] + array3[5];
        array2[3] = array3[3] + array3[4];
        array2[4] = (array3[0] - array3[7]) * SynthesisFilter.cos1_16;
        array2[5] = (array3[1] - array3[6]) * SynthesisFilter.cos3_16;
        array2[6] = (array3[2] - array3[5]) * SynthesisFilter.cos5_16;
        array2[7] = (array3[3] - array3[4]) * SynthesisFilter.cos7_16;
        array2[8] = array3[8] + array3[15];
        array2[9] = array3[9] + array3[14];
        array2[10] = array3[10] + array3[13];
        array2[11] = array3[11] + array3[12];
        array2[12] = (array3[8] - array3[15]) * SynthesisFilter.cos1_16;
        array2[13] = (array3[9] - array3[14]) * SynthesisFilter.cos3_16;
        array2[14] = (array3[10] - array3[13]) * SynthesisFilter.cos5_16;
        array2[15] = (array3[11] - array3[12]) * SynthesisFilter.cos7_16;
        array3[0] = array2[0] + array2[3];
        array3[1] = array2[1] + array2[2];
        array3[2] = (array2[0] - array2[3]) * SynthesisFilter.cos1_8;
        array3[3] = (array2[1] - array2[2]) * SynthesisFilter.cos3_8;
        array3[4] = array2[4] + array2[7];
        array3[5] = array2[5] + array2[6];
        array3[6] = (array2[4] - array2[7]) * SynthesisFilter.cos1_8;
        array3[7] = (array2[5] - array2[6]) * SynthesisFilter.cos3_8;
        array3[8] = array2[8] + array2[11];
        array3[9] = array2[9] + array2[10];
        array3[10] = (array2[8] - array2[11]) * SynthesisFilter.cos1_8;
        array3[11] = (array2[9] - array2[10]) * SynthesisFilter.cos3_8;
        array3[12] = array2[12] + array2[15];
        array3[13] = array2[13] + array2[14];
        array3[14] = (array2[12] - array2[15]) * SynthesisFilter.cos1_8;
        array3[15] = (array2[13] - array2[14]) * SynthesisFilter.cos3_8;
        array2[0] = array3[0] + array3[1];
        array2[1] = (array3[0] - array3[1]) * SynthesisFilter.cos1_4;
        array2[2] = array3[2] + array3[3];
        array2[3] = (array3[2] - array3[3]) * SynthesisFilter.cos1_4;
        array2[4] = array3[4] + array3[5];
        array2[5] = (array3[4] - array3[5]) * SynthesisFilter.cos1_4;
        array2[6] = array3[6] + array3[7];
        array2[7] = (array3[6] - array3[7]) * SynthesisFilter.cos1_4;
        array2[8] = array3[8] + array3[9];
        array2[9] = (array3[8] - array3[9]) * SynthesisFilter.cos1_4;
        array2[10] = array3[10] + array3[11];
        array2[11] = (array3[10] - array3[11]) * SynthesisFilter.cos1_4;
        array2[12] = array3[12] + array3[13];
        array2[13] = (array3[12] - array3[13]) * SynthesisFilter.cos1_4;
        array2[14] = array3[14] + array3[15];
        array2[15] = (array3[14] - array3[15]) * SynthesisFilter.cos1_4;
        final float[] array14 = array;
        final int n18 = 5;
        final float[] array15 = array;
        final int n19 = 11;
        final float[] array16 = array;
        final int n20 = 13;
        final float[] array17 = array;
        final int n21 = 15;
        final float n22 = array2[15];
        array17[n21] = n22;
        final float n23 = n22 + array2[7];
        array16[n20] = n23;
        final float n24 = n23 + array2[11];
        array15[n19] = n24;
        array14[n18] = n24 + array2[5] + array2[13];
        final float[] array18 = array;
        final int n25 = 7;
        final float[] array19 = array;
        final int n26 = 9;
        final float n27 = array2[15] + array2[11] + array2[3];
        array19[n26] = n27;
        array18[n25] = n27 + array2[13];
        final float[] array20 = array;
        final int n28 = 16;
        final float[] array21 = array;
        final int n29 = 1;
        final float n31;
        final float n30 = (n31 = array2[13] + array2[15] + array2[9]) + array2[1];
        array21[n29] = n30;
        array20[n28] = -n30 - array2[14];
        final float[] array22 = array;
        final int n32 = 18;
        final float[] array23 = array;
        final int n33 = 3;
        final float n34 = n31 + array2[5] + array2[7];
        array23[n33] = n34;
        array22[n32] = -n34 - array2[6] - array2[14];
        final float n35;
        array[22] = (n35 = -array2[10] - array2[11] - array2[14] - array2[15]) - array2[13] - array2[2] - array2[3];
        array[20] = n35 - array2[13] - array2[5] - array2[6] - array2[7];
        array[24] = n35 - array2[12] - array2[2] - array2[3];
        final float n36;
        array[26] = n35 - array2[12] - (n36 = array2[4] + array2[6] + array2[7]);
        final float n37;
        array[30] = (n37 = -array2[8] - array2[12] - array2[14] - array2[15]) - array2[0];
        array[28] = n37 - n36;
        final float[] array24 = array;
        final float[] actual_v = this.actual_v;
        actual_v[0 + this.actual_write_pos] = array24[0];
        actual_v[16 + this.actual_write_pos] = array24[1];
        actual_v[32 + this.actual_write_pos] = array24[2];
        actual_v[48 + this.actual_write_pos] = array24[3];
        actual_v[64 + this.actual_write_pos] = array24[4];
        actual_v[80 + this.actual_write_pos] = array24[5];
        actual_v[96 + this.actual_write_pos] = array24[6];
        actual_v[112 + this.actual_write_pos] = array24[7];
        actual_v[128 + this.actual_write_pos] = array24[8];
        actual_v[144 + this.actual_write_pos] = array24[9];
        actual_v[160 + this.actual_write_pos] = array24[10];
        actual_v[176 + this.actual_write_pos] = array24[11];
        actual_v[192 + this.actual_write_pos] = array24[12];
        actual_v[208 + this.actual_write_pos] = array24[13];
        actual_v[224 + this.actual_write_pos] = array24[14];
        actual_v[240 + this.actual_write_pos] = array24[15];
        actual_v[256 + this.actual_write_pos] = 0.0f;
        actual_v[272 + this.actual_write_pos] = -array24[15];
        actual_v[288 + this.actual_write_pos] = -array24[14];
        actual_v[304 + this.actual_write_pos] = -array24[13];
        actual_v[320 + this.actual_write_pos] = -array24[12];
        actual_v[336 + this.actual_write_pos] = -array24[11];
        actual_v[352 + this.actual_write_pos] = -array24[10];
        actual_v[368 + this.actual_write_pos] = -array24[9];
        actual_v[384 + this.actual_write_pos] = -array24[8];
        actual_v[400 + this.actual_write_pos] = -array24[7];
        actual_v[416 + this.actual_write_pos] = -array24[6];
        actual_v[432 + this.actual_write_pos] = -array24[5];
        actual_v[448 + this.actual_write_pos] = -array24[4];
        actual_v[464 + this.actual_write_pos] = -array24[3];
        actual_v[480 + this.actual_write_pos] = -array24[2];
        actual_v[496 + this.actual_write_pos] = -array24[1];
    }
    
    private void compute_pcm_samples0(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[0 + n] * array[0] + actual_v[15 + n] * array[1] + actual_v[14 + n] * array[2] + actual_v[13 + n] * array[3] + actual_v[12 + n] * array[4] + actual_v[11 + n] * array[5] + actual_v[10 + n] * array[6] + actual_v[9 + n] * array[7] + actual_v[8 + n] * array[8] + actual_v[7 + n] * array[9] + actual_v[6 + n] * array[10] + actual_v[5 + n] * array[11] + actual_v[4 + n] * array[12] + actual_v[3 + n] * array[13] + actual_v[2 + n] * array[14] + actual_v[1 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples1(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[1 + n] * array[0] + actual_v[0 + n] * array[1] + actual_v[15 + n] * array[2] + actual_v[14 + n] * array[3] + actual_v[13 + n] * array[4] + actual_v[12 + n] * array[5] + actual_v[11 + n] * array[6] + actual_v[10 + n] * array[7] + actual_v[9 + n] * array[8] + actual_v[8 + n] * array[9] + actual_v[7 + n] * array[10] + actual_v[6 + n] * array[11] + actual_v[5 + n] * array[12] + actual_v[4 + n] * array[13] + actual_v[3 + n] * array[14] + actual_v[2 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples2(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[2 + n] * array[0] + actual_v[1 + n] * array[1] + actual_v[0 + n] * array[2] + actual_v[15 + n] * array[3] + actual_v[14 + n] * array[4] + actual_v[13 + n] * array[5] + actual_v[12 + n] * array[6] + actual_v[11 + n] * array[7] + actual_v[10 + n] * array[8] + actual_v[9 + n] * array[9] + actual_v[8 + n] * array[10] + actual_v[7 + n] * array[11] + actual_v[6 + n] * array[12] + actual_v[5 + n] * array[13] + actual_v[4 + n] * array[14] + actual_v[3 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples3(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[3 + n] * array[0] + actual_v[2 + n] * array[1] + actual_v[1 + n] * array[2] + actual_v[0 + n] * array[3] + actual_v[15 + n] * array[4] + actual_v[14 + n] * array[5] + actual_v[13 + n] * array[6] + actual_v[12 + n] * array[7] + actual_v[11 + n] * array[8] + actual_v[10 + n] * array[9] + actual_v[9 + n] * array[10] + actual_v[8 + n] * array[11] + actual_v[7 + n] * array[12] + actual_v[6 + n] * array[13] + actual_v[5 + n] * array[14] + actual_v[4 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples4(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[4 + n] * array[0] + actual_v[3 + n] * array[1] + actual_v[2 + n] * array[2] + actual_v[1 + n] * array[3] + actual_v[0 + n] * array[4] + actual_v[15 + n] * array[5] + actual_v[14 + n] * array[6] + actual_v[13 + n] * array[7] + actual_v[12 + n] * array[8] + actual_v[11 + n] * array[9] + actual_v[10 + n] * array[10] + actual_v[9 + n] * array[11] + actual_v[8 + n] * array[12] + actual_v[7 + n] * array[13] + actual_v[6 + n] * array[14] + actual_v[5 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples5(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[5 + n] * array[0] + actual_v[4 + n] * array[1] + actual_v[3 + n] * array[2] + actual_v[2 + n] * array[3] + actual_v[1 + n] * array[4] + actual_v[0 + n] * array[5] + actual_v[15 + n] * array[6] + actual_v[14 + n] * array[7] + actual_v[13 + n] * array[8] + actual_v[12 + n] * array[9] + actual_v[11 + n] * array[10] + actual_v[10 + n] * array[11] + actual_v[9 + n] * array[12] + actual_v[8 + n] * array[13] + actual_v[7 + n] * array[14] + actual_v[6 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples6(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[6 + n] * array[0] + actual_v[5 + n] * array[1] + actual_v[4 + n] * array[2] + actual_v[3 + n] * array[3] + actual_v[2 + n] * array[4] + actual_v[1 + n] * array[5] + actual_v[0 + n] * array[6] + actual_v[15 + n] * array[7] + actual_v[14 + n] * array[8] + actual_v[13 + n] * array[9] + actual_v[12 + n] * array[10] + actual_v[11 + n] * array[11] + actual_v[10 + n] * array[12] + actual_v[9 + n] * array[13] + actual_v[8 + n] * array[14] + actual_v[7 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples7(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[7 + n] * array[0] + actual_v[6 + n] * array[1] + actual_v[5 + n] * array[2] + actual_v[4 + n] * array[3] + actual_v[3 + n] * array[4] + actual_v[2 + n] * array[5] + actual_v[1 + n] * array[6] + actual_v[0 + n] * array[7] + actual_v[15 + n] * array[8] + actual_v[14 + n] * array[9] + actual_v[13 + n] * array[10] + actual_v[12 + n] * array[11] + actual_v[11 + n] * array[12] + actual_v[10 + n] * array[13] + actual_v[9 + n] * array[14] + actual_v[8 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples8(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[8 + n] * array[0] + actual_v[7 + n] * array[1] + actual_v[6 + n] * array[2] + actual_v[5 + n] * array[3] + actual_v[4 + n] * array[4] + actual_v[3 + n] * array[5] + actual_v[2 + n] * array[6] + actual_v[1 + n] * array[7] + actual_v[0 + n] * array[8] + actual_v[15 + n] * array[9] + actual_v[14 + n] * array[10] + actual_v[13 + n] * array[11] + actual_v[12 + n] * array[12] + actual_v[11 + n] * array[13] + actual_v[10 + n] * array[14] + actual_v[9 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples9(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[9 + n] * array[0] + actual_v[8 + n] * array[1] + actual_v[7 + n] * array[2] + actual_v[6 + n] * array[3] + actual_v[5 + n] * array[4] + actual_v[4 + n] * array[5] + actual_v[3 + n] * array[6] + actual_v[2 + n] * array[7] + actual_v[1 + n] * array[8] + actual_v[0 + n] * array[9] + actual_v[15 + n] * array[10] + actual_v[14 + n] * array[11] + actual_v[13 + n] * array[12] + actual_v[12 + n] * array[13] + actual_v[11 + n] * array[14] + actual_v[10 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples10(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[10 + n] * array[0] + actual_v[9 + n] * array[1] + actual_v[8 + n] * array[2] + actual_v[7 + n] * array[3] + actual_v[6 + n] * array[4] + actual_v[5 + n] * array[5] + actual_v[4 + n] * array[6] + actual_v[3 + n] * array[7] + actual_v[2 + n] * array[8] + actual_v[1 + n] * array[9] + actual_v[0 + n] * array[10] + actual_v[15 + n] * array[11] + actual_v[14 + n] * array[12] + actual_v[13 + n] * array[13] + actual_v[12 + n] * array[14] + actual_v[11 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples11(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[11 + n] * array[0] + actual_v[10 + n] * array[1] + actual_v[9 + n] * array[2] + actual_v[8 + n] * array[3] + actual_v[7 + n] * array[4] + actual_v[6 + n] * array[5] + actual_v[5 + n] * array[6] + actual_v[4 + n] * array[7] + actual_v[3 + n] * array[8] + actual_v[2 + n] * array[9] + actual_v[1 + n] * array[10] + actual_v[0 + n] * array[11] + actual_v[15 + n] * array[12] + actual_v[14 + n] * array[13] + actual_v[13 + n] * array[14] + actual_v[12 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples12(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[12 + n] * array[0] + actual_v[11 + n] * array[1] + actual_v[10 + n] * array[2] + actual_v[9 + n] * array[3] + actual_v[8 + n] * array[4] + actual_v[7 + n] * array[5] + actual_v[6 + n] * array[6] + actual_v[5 + n] * array[7] + actual_v[4 + n] * array[8] + actual_v[3 + n] * array[9] + actual_v[2 + n] * array[10] + actual_v[1 + n] * array[11] + actual_v[0 + n] * array[12] + actual_v[15 + n] * array[13] + actual_v[14 + n] * array[14] + actual_v[13 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples13(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[13 + n] * array[0] + actual_v[12 + n] * array[1] + actual_v[11 + n] * array[2] + actual_v[10 + n] * array[3] + actual_v[9 + n] * array[4] + actual_v[8 + n] * array[5] + actual_v[7 + n] * array[6] + actual_v[6 + n] * array[7] + actual_v[5 + n] * array[8] + actual_v[4 + n] * array[9] + actual_v[3 + n] * array[10] + actual_v[2 + n] * array[11] + actual_v[1 + n] * array[12] + actual_v[0 + n] * array[13] + actual_v[15 + n] * array[14] + actual_v[14 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples14(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[14 + n] * array[0] + actual_v[13 + n] * array[1] + actual_v[12 + n] * array[2] + actual_v[11 + n] * array[3] + actual_v[10 + n] * array[4] + actual_v[9 + n] * array[5] + actual_v[8 + n] * array[6] + actual_v[7 + n] * array[7] + actual_v[6 + n] * array[8] + actual_v[5 + n] * array[9] + actual_v[4 + n] * array[10] + actual_v[3 + n] * array[11] + actual_v[2 + n] * array[12] + actual_v[1 + n] * array[13] + actual_v[0 + n] * array[14] + actual_v[15 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples15(final Obuffer obuffer) {
        final float[] actual_v = this.actual_v;
        final float[] tmpOut = this._tmpOut;
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            final float[] array = SynthesisFilter.d16[i];
            tmpOut[i] = (actual_v[15 + n] * array[0] + actual_v[14 + n] * array[1] + actual_v[13 + n] * array[2] + actual_v[12 + n] * array[3] + actual_v[11 + n] * array[4] + actual_v[10 + n] * array[5] + actual_v[9 + n] * array[6] + actual_v[8 + n] * array[7] + actual_v[7 + n] * array[8] + actual_v[6 + n] * array[9] + actual_v[5 + n] * array[10] + actual_v[4 + n] * array[11] + actual_v[3 + n] * array[12] + actual_v[2 + n] * array[13] + actual_v[1 + n] * array[14] + actual_v[0 + n] * array[15]) * this.scalefactor;
            n += 16;
        }
    }
    
    private void compute_pcm_samples(final Obuffer obuffer) {
        switch (this.actual_write_pos) {
            case 0: {
                this.compute_pcm_samples0(obuffer);
                break;
            }
            case 1: {
                this.compute_pcm_samples1(obuffer);
                break;
            }
            case 2: {
                this.compute_pcm_samples2(obuffer);
                break;
            }
            case 3: {
                this.compute_pcm_samples3(obuffer);
                break;
            }
            case 4: {
                this.compute_pcm_samples4(obuffer);
                break;
            }
            case 5: {
                this.compute_pcm_samples5(obuffer);
                break;
            }
            case 6: {
                this.compute_pcm_samples6(obuffer);
                break;
            }
            case 7: {
                this.compute_pcm_samples7(obuffer);
                break;
            }
            case 8: {
                this.compute_pcm_samples8(obuffer);
                break;
            }
            case 9: {
                this.compute_pcm_samples9(obuffer);
                break;
            }
            case 10: {
                this.compute_pcm_samples10(obuffer);
                break;
            }
            case 11: {
                this.compute_pcm_samples11(obuffer);
                break;
            }
            case 12: {
                this.compute_pcm_samples12(obuffer);
                break;
            }
            case 13: {
                this.compute_pcm_samples13(obuffer);
                break;
            }
            case 14: {
                this.compute_pcm_samples14(obuffer);
                break;
            }
            case 15: {
                this.compute_pcm_samples15(obuffer);
                break;
            }
        }
        if (obuffer != null) {
            obuffer.appendSamples(this.channel, this._tmpOut);
        }
    }
    
    public void calculate_pcm_samples(final Obuffer obuffer) {
        this.compute_new_v();
        this.compute_pcm_samples(obuffer);
        this.actual_write_pos = (this.actual_write_pos + 1 & 0xF);
        this.actual_v = ((this.actual_v == this.v1) ? this.v2 : this.v1);
        for (int i = 0; i < 32; ++i) {
            this.samples[i] = 0.0f;
        }
    }
    
    private static float[] load_d() {
        try {
            return (float[])JavaLayerUtils.deserializeArrayResource("sfd.ser", Float.TYPE, 512);
        }
        catch (IOException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private static float[][] splitArray(final float[] array, final int n) {
        final int n2 = array.length / n;
        final float[][] array2 = new float[n2][];
        for (int i = 0; i < n2; ++i) {
            array2[i] = subArray(array, i * n, n);
        }
        return array2;
    }
    
    private static float[] subArray(final float[] array, final int n, int n2) {
        if (n + n2 > array.length) {
            n2 = array.length - n;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        final float[] array2 = new float[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[n + i];
        }
        return array2;
    }
    
    static {
        cos1_64 = (float)(1.0 / (2.0 * Math.cos(0.04908738521234052)));
        cos3_64 = (float)(1.0 / (2.0 * Math.cos(0.14726215563702155)));
        cos5_64 = (float)(1.0 / (2.0 * Math.cos(0.2454369260617026)));
        cos7_64 = (float)(1.0 / (2.0 * Math.cos(0.3436116964863836)));
        cos9_64 = (float)(1.0 / (2.0 * Math.cos(0.44178646691106466)));
        cos11_64 = (float)(1.0 / (2.0 * Math.cos(0.5399612373357456)));
        cos13_64 = (float)(1.0 / (2.0 * Math.cos(0.6381360077604268)));
        cos15_64 = (float)(1.0 / (2.0 * Math.cos(0.7363107781851077)));
        cos17_64 = (float)(1.0 / (2.0 * Math.cos(0.8344855486097889)));
        cos19_64 = (float)(1.0 / (2.0 * Math.cos(0.9326603190344698)));
        cos21_64 = (float)(1.0 / (2.0 * Math.cos(1.030835089459151)));
        cos23_64 = (float)(1.0 / (2.0 * Math.cos(1.1290098598838318)));
        cos25_64 = (float)(1.0 / (2.0 * Math.cos(1.227184630308513)));
        cos27_64 = (float)(1.0 / (2.0 * Math.cos(1.325359400733194)));
        cos29_64 = (float)(1.0 / (2.0 * Math.cos(1.423534171157875)));
        cos31_64 = (float)(1.0 / (2.0 * Math.cos(1.521708941582556)));
        cos1_32 = (float)(1.0 / (2.0 * Math.cos(0.09817477042468103)));
        cos3_32 = (float)(1.0 / (2.0 * Math.cos(0.2945243112740431)));
        cos5_32 = (float)(1.0 / (2.0 * Math.cos(0.4908738521234052)));
        cos7_32 = (float)(1.0 / (2.0 * Math.cos(0.6872233929727672)));
        cos9_32 = (float)(1.0 / (2.0 * Math.cos(0.8835729338221293)));
        cos11_32 = (float)(1.0 / (2.0 * Math.cos(1.0799224746714913)));
        cos13_32 = (float)(1.0 / (2.0 * Math.cos(1.2762720155208536)));
        cos15_32 = (float)(1.0 / (2.0 * Math.cos(1.4726215563702154)));
        cos1_16 = (float)(1.0 / (2.0 * Math.cos(0.19634954084936207)));
        cos3_16 = (float)(1.0 / (2.0 * Math.cos(0.5890486225480862)));
        cos5_16 = (float)(1.0 / (2.0 * Math.cos(0.9817477042468103)));
        cos7_16 = (float)(1.0 / (2.0 * Math.cos(1.3744467859455345)));
        cos1_8 = (float)(1.0 / (2.0 * Math.cos(0.39269908169872414)));
        cos3_8 = (float)(1.0 / (2.0 * Math.cos(1.1780972450961724)));
        cos1_4 = (float)(1.0 / (2.0 * Math.cos(0.7853981633974483)));
        SynthesisFilter.d = null;
        SynthesisFilter.d16 = null;
    }
}
