// 
// Decompiled by Procyon v0.5.30
// 

public class BTriangle implements BConstants
{
    static final int R_GOURAUD = 1;
    static final int R_TEXTURE8 = 2;
    static final int R_TEXTURE24 = 4;
    static final int R_TEXTURE32 = 8;
    static final int R_ALPHA = 16;
    private int[] m_pixels;
    private int[] m_stencil;
    private int[] m_texture;
    private float[] m_zbuffer;
    private BImage m_tImage;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int SCREEN_WIDTH1;
    private int SCREEN_HEIGHT1;
    private int TEX_WIDTH;
    private int TEX_HEIGHT;
    private float F_TEX_WIDTH;
    private float F_TEX_HEIGHT;
    public boolean INTERPOLATE_UV;
    public boolean INTERPOLATE_RGB;
    public boolean INTERPOLATE_ALPHA;
    private float[] x_array;
    private float[] y_array;
    private float[] z_array;
    private float[] u_array;
    private float[] v_array;
    private float[] r_array;
    private float[] g_array;
    private float[] b_array;
    private float[] a_array;
    private int o0;
    private int o1;
    private int o2;
    private float r0;
    private float r1;
    private float r2;
    private float g0;
    private float g1;
    private float g2;
    private float b0;
    private float b1;
    private float b2;
    private float a0;
    private float a1;
    private float a2;
    private float u0;
    private float u1;
    private float u2;
    private float v0;
    private float v1;
    private float v2;
    private float dx0;
    private float dx1;
    private float dx2;
    private float dy0;
    private float dy1;
    private float dy2;
    private float dz0;
    private float dz1;
    private float dz2;
    private float du0;
    private float du1;
    private float du2;
    private float dv0;
    private float dv1;
    private float dv2;
    private float dr0;
    private float dr1;
    private float dr2;
    private float dg0;
    private float dg1;
    private float dg2;
    private float db0;
    private float db1;
    private float db2;
    private float da0;
    private float da1;
    private float da2;
    private float uleft;
    private float vleft;
    private float uleftadd;
    private float vleftadd;
    private float xleft;
    private float xrght;
    private float xadd1;
    private float xadd2;
    private float zleft;
    private float zleftadd;
    private float rleft;
    private float gleft;
    private float bleft;
    private float aleft;
    private float rleftadd;
    private float gleftadd;
    private float bleftadd;
    private float aleftadd;
    private float dta;
    private float dta2;
    private float temp;
    private float width;
    private int iuadd;
    private int ivadd;
    private int iradd;
    private int igadd;
    private int ibadd;
    private int iaadd;
    private float izadd;
    private int m_fill;
    public int m_drawFlags;
    private int m_index;
    private BGraphics parent;
    private boolean m_culling;
    private boolean m_singleRight;
    private boolean m_bilinear;
    
    public void reset() {
        this.SCREEN_WIDTH = this.parent.width;
        this.SCREEN_HEIGHT = this.parent.height;
        this.SCREEN_WIDTH1 = this.SCREEN_WIDTH - 1;
        this.SCREEN_HEIGHT1 = this.SCREEN_HEIGHT - 1;
        this.m_pixels = this.parent.pixels;
        this.m_stencil = this.parent.stencil;
        this.m_zbuffer = this.parent.zbuffer;
        this.INTERPOLATE_UV = false;
        this.INTERPOLATE_RGB = false;
        this.INTERPOLATE_ALPHA = false;
        this.m_tImage = null;
        this.m_texture = null;
        this.m_drawFlags = 0;
    }
    
    public void setCulling(final boolean culling) {
        this.m_culling = culling;
    }
    
    public void setVertices(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.x_array[0] = n;
        this.x_array[1] = n4;
        this.x_array[2] = n7;
        this.y_array[0] = n2;
        this.y_array[1] = n5;
        this.y_array[2] = n8;
        this.z_array[0] = n3;
        this.z_array[1] = n6;
        this.z_array[2] = n9;
    }
    
    public void setUV(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.u_array[0] = (n * this.F_TEX_WIDTH + 0.5f) * 65536.0f;
        this.u_array[1] = (n3 * this.F_TEX_WIDTH + 0.5f) * 65536.0f;
        this.u_array[2] = (n5 * this.F_TEX_WIDTH + 0.5f) * 65536.0f;
        this.v_array[0] = (n2 * this.F_TEX_HEIGHT + 0.5f) * 65536.0f;
        this.v_array[1] = (n4 * this.F_TEX_HEIGHT + 0.5f) * 65536.0f;
        this.v_array[2] = (n6 * this.F_TEX_HEIGHT + 0.5f) * 65536.0f;
    }
    
    public void setIntensities(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        if (n4 != 1.0f || n8 != 1.0f || n12 != 1.0f) {
            this.INTERPOLATE_ALPHA = true;
            this.a_array[0] = (n4 * 253.0f + 1.0f) * 65536.0f;
            this.a_array[1] = (n8 * 253.0f + 1.0f) * 65536.0f;
            this.a_array[2] = (n12 * 253.0f + 1.0f) * 65536.0f;
            this.m_drawFlags |= 0x10;
        }
        else {
            this.INTERPOLATE_ALPHA = false;
            this.m_drawFlags &= 0xFFFFFFEF;
        }
        if (n != n5 || n5 != n9) {
            this.INTERPOLATE_RGB = true;
            this.m_drawFlags |= 0x1;
        }
        else if (n2 != n6 || n6 != n10) {
            this.INTERPOLATE_RGB = true;
            this.m_drawFlags |= 0x1;
        }
        else if (n3 != n7 || n7 != n11) {
            this.INTERPOLATE_RGB = true;
            this.m_drawFlags |= 0x1;
        }
        else {
            this.m_drawFlags &= 0xFFFFFFFE;
        }
        this.r_array[0] = (n * 253.0f + 1.0f) * 65536.0f;
        this.r_array[1] = (n5 * 253.0f + 1.0f) * 65536.0f;
        this.r_array[2] = (n9 * 253.0f + 1.0f) * 65536.0f;
        this.g_array[0] = (n2 * 253.0f + 1.0f) * 65536.0f;
        this.g_array[1] = (n6 * 253.0f + 1.0f) * 65536.0f;
        this.g_array[2] = (n10 * 253.0f + 1.0f) * 65536.0f;
        this.b_array[0] = (n3 * 253.0f + 1.0f) * 65536.0f;
        this.b_array[1] = (n7 * 253.0f + 1.0f) * 65536.0f;
        this.b_array[2] = (n11 * 253.0f + 1.0f) * 65536.0f;
        this.m_fill = ((int)(255.0f * n) << 16 | (int)(255.0f * n2) << 8 | (int)(255.0f * n3));
    }
    
    public void setTexture(final BImage tImage) {
        this.m_tImage = tImage;
        this.m_texture = tImage.pixels;
        this.TEX_WIDTH = tImage.width;
        this.TEX_HEIGHT = tImage.height;
        this.F_TEX_WIDTH = this.TEX_WIDTH - 1;
        this.F_TEX_HEIGHT = this.TEX_HEIGHT - 1;
        this.INTERPOLATE_UV = true;
        if (tImage.format == 2) {
            this.m_drawFlags |= 0x8;
        }
        else if (tImage.format == 1) {
            this.m_drawFlags |= 0x4;
        }
        else if (tImage.format == 4) {
            this.m_drawFlags |= 0x2;
        }
        if (this.parent.smooth) {
            this.m_bilinear = true;
        }
        else {
            this.m_bilinear = false;
        }
    }
    
    public void setUV(final float[] array, final float[] array2) {
        if (this.m_bilinear) {
            this.u_array[0] = array[0] * this.F_TEX_WIDTH * 65500.0f;
            this.u_array[1] = array[1] * this.F_TEX_WIDTH * 65500.0f;
            this.u_array[2] = array[2] * this.F_TEX_WIDTH * 65500.0f;
            this.v_array[0] = array2[0] * this.F_TEX_HEIGHT * 65500.0f;
            this.v_array[1] = array2[1] * this.F_TEX_HEIGHT * 65500.0f;
            this.v_array[2] = array2[2] * this.F_TEX_HEIGHT * 65500.0f;
        }
        else {
            this.u_array[0] = array[0] * this.TEX_WIDTH * 65500.0f;
            this.u_array[1] = array[1] * this.TEX_WIDTH * 65500.0f;
            this.u_array[2] = array[2] * this.TEX_WIDTH * 65500.0f;
            this.v_array[0] = array2[0] * this.TEX_HEIGHT * 65500.0f;
            this.v_array[1] = array2[1] * this.TEX_HEIGHT * 65500.0f;
            this.v_array[2] = array2[2] * this.TEX_HEIGHT * 65500.0f;
        }
    }
    
    public void setIndex(final int index) {
        this.m_index = index;
    }
    
    public void render() {
        this.draw();
    }
    
    private final void draw() {
        final float n = this.y_array[0];
        final float n2 = this.y_array[1];
        final float n3 = this.y_array[2];
        if (this.m_culling) {
            final float n4 = this.x_array[0];
            if ((this.x_array[2] - n4) * (n2 - n) < (this.x_array[1] - n4) * (n3 - n)) {
                return;
            }
        }
        if (n < n2) {
            if (n3 < n2) {
                if (n3 < n) {
                    this.o0 = 2;
                    this.o1 = 0;
                    this.o2 = 1;
                }
                else {
                    this.o0 = 0;
                    this.o1 = 2;
                    this.o2 = 1;
                }
            }
            else {
                this.o0 = 0;
                this.o1 = 1;
                this.o2 = 2;
            }
        }
        else if (n3 > n2) {
            if (n3 < n) {
                this.o0 = 1;
                this.o1 = 2;
                this.o2 = 0;
            }
            else {
                this.o0 = 1;
                this.o1 = 0;
                this.o2 = 2;
            }
        }
        else {
            this.o0 = 2;
            this.o1 = 1;
            this.o2 = 0;
        }
        final float n5 = this.y_array[this.o0];
        int n6 = (int)(n5 + 0.5f);
        if (n6 > this.SCREEN_HEIGHT) {
            return;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        final float n7 = this.y_array[this.o2];
        int screen_HEIGHT = (int)(n7 + 0.5f);
        if (screen_HEIGHT < 0) {
            return;
        }
        if (screen_HEIGHT > this.SCREEN_HEIGHT) {
            screen_HEIGHT = this.SCREEN_HEIGHT;
        }
        if (screen_HEIGHT > n6) {
            final float n8 = this.x_array[this.o0];
            final float n9 = this.x_array[this.o1];
            final float n10 = this.x_array[this.o2];
            final float n11 = this.y_array[this.o1];
            int screen_HEIGHT2 = (int)(n11 + 0.5f);
            if (screen_HEIGHT2 < 0) {
                screen_HEIGHT2 = 0;
            }
            if (screen_HEIGHT2 > this.SCREEN_HEIGHT) {
                screen_HEIGHT2 = this.SCREEN_HEIGHT;
            }
            this.dx2 = n10 - n8;
            this.dy0 = n11 - n5;
            this.dy2 = n7 - n5;
            this.xadd2 = this.dx2 / this.dy2;
            this.temp = this.dy0 / this.dy2;
            this.width = this.temp * this.dx2 + n8 - n9;
            if (this.INTERPOLATE_ALPHA) {
                this.a0 = this.a_array[this.o0];
                this.a1 = this.a_array[this.o1];
                this.a2 = this.a_array[this.o2];
                this.da0 = this.a1 - this.a0;
                this.da2 = this.a2 - this.a0;
                this.iaadd = (int)((this.temp * this.da2 - this.da0) / this.width);
            }
            if (this.INTERPOLATE_RGB) {
                this.r0 = this.r_array[this.o0];
                this.r1 = this.r_array[this.o1];
                this.r2 = this.r_array[this.o2];
                this.g0 = this.g_array[this.o0];
                this.g1 = this.g_array[this.o1];
                this.g2 = this.g_array[this.o2];
                this.b0 = this.b_array[this.o0];
                this.b1 = this.b_array[this.o1];
                this.b2 = this.b_array[this.o2];
                this.dr0 = this.r1 - this.r0;
                this.dg0 = this.g1 - this.g0;
                this.db0 = this.b1 - this.b0;
                this.dr2 = this.r2 - this.r0;
                this.dg2 = this.g2 - this.g0;
                this.db2 = this.b2 - this.b0;
                this.iradd = (int)((this.temp * this.dr2 - this.dr0) / this.width);
                this.igadd = (int)((this.temp * this.dg2 - this.dg0) / this.width);
                this.ibadd = (int)((this.temp * this.db2 - this.db0) / this.width);
            }
            if (this.INTERPOLATE_UV) {
                this.u0 = this.u_array[this.o0];
                this.u1 = this.u_array[this.o1];
                this.u2 = this.u_array[this.o2];
                this.v0 = this.v_array[this.o0];
                this.v1 = this.v_array[this.o1];
                this.v2 = this.v_array[this.o2];
                this.du0 = this.u1 - this.u0;
                this.dv0 = this.v1 - this.v0;
                this.du2 = this.u2 - this.u0;
                this.dv2 = this.v2 - this.v0;
                this.iuadd = (int)((this.temp * this.du2 - this.du0) / this.width);
                this.ivadd = (int)((this.temp * this.dv2 - this.dv0) / this.width);
            }
            final float n12 = this.z_array[this.o0];
            final float n13 = this.z_array[this.o1];
            final float n14 = this.z_array[this.o2];
            this.dz0 = n13 - n12;
            this.dz2 = n14 - n12;
            this.izadd = (this.temp * this.dz2 - this.dz0) / this.width;
            if (screen_HEIGHT2 > n6) {
                this.dta = n6 + 0.5f - n5;
                this.xadd1 = (n9 - n8) / this.dy0;
                if (this.xadd2 > this.xadd1) {
                    this.xleft = n8 + this.dta * this.xadd1;
                    this.xrght = n8 + this.dta * this.xadd2;
                    this.zleftadd = this.dz0 / this.dy0;
                    this.zleft = this.dta * this.zleftadd + n12;
                    if (this.INTERPOLATE_UV) {
                        this.uleftadd = this.du0 / this.dy0;
                        this.vleftadd = this.dv0 / this.dy0;
                        this.uleft = this.dta * this.uleftadd + this.u0;
                        this.vleft = this.dta * this.vleftadd + this.v0;
                    }
                    if (this.INTERPOLATE_RGB) {
                        this.rleftadd = this.dr0 / this.dy0;
                        this.gleftadd = this.dg0 / this.dy0;
                        this.bleftadd = this.db0 / this.dy0;
                        this.rleft = this.dta * this.rleftadd + this.r0;
                        this.gleft = this.dta * this.gleftadd + this.g0;
                        this.bleft = this.dta * this.bleftadd + this.b0;
                    }
                    if (this.INTERPOLATE_ALPHA) {
                        this.aleftadd = this.da0 / this.dy0;
                        this.aleft = this.dta * this.aleftadd + this.a0;
                        if (this.m_drawFlags == 16) {
                            this.drawsegment_plain_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 17) {
                            this.drawsegment_gouraud_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 18) {
                            this.drawsegment_texture8_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 20) {
                            this.drawsegment_texture24_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 24) {
                            this.drawsegment_texture32_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 19) {
                            this.drawsegment_gouraud_texture8_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 21) {
                            this.drawsegment_gouraud_texture24_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 25) {
                            this.drawsegment_gouraud_texture32_alpha(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                        }
                    }
                    else if (this.m_drawFlags == 0) {
                        this.drawsegment_plain(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 1) {
                        this.drawsegment_gouraud(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 2) {
                        this.drawsegment_texture8(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 4) {
                        this.drawsegment_texture24(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 8) {
                        this.drawsegment_texture32(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 3) {
                        this.drawsegment_gouraud_texture8(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 5) {
                        this.drawsegment_gouraud_texture24(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 9) {
                        this.drawsegment_gouraud_texture32(this.xadd1, this.xadd2, n6, screen_HEIGHT2);
                    }
                    this.m_singleRight = true;
                }
                else {
                    this.xleft = n8 + this.dta * this.xadd2;
                    this.xrght = n8 + this.dta * this.xadd1;
                    this.zleftadd = this.dz2 / this.dy2;
                    this.zleft = this.dta * this.zleftadd + n12;
                    if (this.INTERPOLATE_UV) {
                        this.uleftadd = this.du2 / this.dy2;
                        this.vleftadd = this.dv2 / this.dy2;
                        this.uleft = this.dta * this.uleftadd + this.u0;
                        this.vleft = this.dta * this.vleftadd + this.v0;
                    }
                    if (this.INTERPOLATE_RGB) {
                        this.rleftadd = this.dr2 / this.dy2;
                        this.gleftadd = this.dg2 / this.dy2;
                        this.bleftadd = this.db2 / this.dy2;
                        this.rleft = this.dta * this.rleftadd + this.r0;
                        this.gleft = this.dta * this.gleftadd + this.g0;
                        this.bleft = this.dta * this.bleftadd + this.b0;
                    }
                    if (this.INTERPOLATE_ALPHA) {
                        this.aleftadd = this.da2 / this.dy2;
                        this.aleft = this.dta * this.aleftadd + this.a0;
                        if (this.m_drawFlags == 16) {
                            this.drawsegment_plain_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 17) {
                            this.drawsegment_gouraud_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 18) {
                            this.drawsegment_texture8_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 20) {
                            this.drawsegment_texture24_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 24) {
                            this.drawsegment_texture32_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 19) {
                            this.drawsegment_gouraud_texture8_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 21) {
                            this.drawsegment_gouraud_texture24_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                        else if (this.m_drawFlags == 25) {
                            this.drawsegment_gouraud_texture32_alpha(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                        }
                    }
                    else if (this.m_drawFlags == 0) {
                        this.drawsegment_plain(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 1) {
                        this.drawsegment_gouraud(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 2) {
                        this.drawsegment_texture8(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 4) {
                        this.drawsegment_texture24(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 8) {
                        this.drawsegment_texture32(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 3) {
                        this.drawsegment_gouraud_texture8(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 5) {
                        this.drawsegment_gouraud_texture24(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    else if (this.m_drawFlags == 9) {
                        this.drawsegment_gouraud_texture32(this.xadd2, this.xadd1, n6, screen_HEIGHT2);
                    }
                    this.m_singleRight = false;
                }
                if (screen_HEIGHT == screen_HEIGHT2) {
                    return;
                }
                this.dy1 = n7 - n11;
                this.xadd1 = (n10 - n9) / this.dy1;
            }
            else {
                this.dy1 = n7 - n11;
                this.xadd1 = (n10 - n9) / this.dy1;
                if (this.xadd2 < this.xadd1) {
                    this.xrght = (screen_HEIGHT2 + 0.5f - n5) * this.xadd2 + n8;
                    this.m_singleRight = true;
                }
                else {
                    this.dta = screen_HEIGHT2 + 0.5f - n5;
                    this.xleft = this.dta * this.xadd2 + n8;
                    this.zleftadd = this.dz2 / this.dy2;
                    this.zleft = this.dta * this.zleftadd + n12;
                    if (this.INTERPOLATE_UV) {
                        this.uleftadd = this.du2 / this.dy2;
                        this.vleftadd = this.dv2 / this.dy2;
                        this.uleft = this.dta * this.uleftadd + this.u0;
                        this.vleft = this.dta * this.vleftadd + this.v0;
                    }
                    if (this.INTERPOLATE_RGB) {
                        this.rleftadd = this.dr2 / this.dy2;
                        this.gleftadd = this.dg2 / this.dy2;
                        this.bleftadd = this.db2 / this.dy2;
                        this.rleft = this.dta * this.rleftadd + this.r0;
                        this.gleft = this.dta * this.gleftadd + this.g0;
                        this.bleft = this.dta * this.bleftadd + this.b0;
                    }
                    if (this.INTERPOLATE_ALPHA) {
                        this.aleftadd = this.da2 / this.dy2;
                        this.aleft = this.dta * this.aleftadd + this.a0;
                    }
                    this.m_singleRight = false;
                }
            }
            if (this.m_singleRight) {
                this.dta = screen_HEIGHT2 + 0.5f - n11;
                this.xleft = this.dta * this.xadd1 + n9;
                this.zleftadd = (n14 - n13) / this.dy1;
                this.zleft = this.dta * this.zleftadd + n13;
                if (this.INTERPOLATE_UV) {
                    this.uleftadd = (this.u2 - this.u1) / this.dy1;
                    this.vleftadd = (this.v2 - this.v1) / this.dy1;
                    this.uleft = this.dta * this.uleftadd + this.u1;
                    this.vleft = this.dta * this.vleftadd + this.v1;
                }
                if (this.INTERPOLATE_RGB) {
                    this.rleftadd = (this.r2 - this.r1) / this.dy1;
                    this.gleftadd = (this.g2 - this.g1) / this.dy1;
                    this.bleftadd = (this.b2 - this.b1) / this.dy1;
                    this.rleft = this.dta * this.rleftadd + this.r1;
                    this.gleft = this.dta * this.gleftadd + this.g1;
                    this.bleft = this.dta * this.bleftadd + this.b1;
                }
                if (this.INTERPOLATE_ALPHA) {
                    this.aleftadd = (this.a2 - this.a1) / this.dy1;
                    this.aleft = this.dta * this.aleftadd + this.a1;
                    if (this.m_drawFlags == 16) {
                        this.drawsegment_plain_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 17) {
                        this.drawsegment_gouraud_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 18) {
                        this.drawsegment_texture8_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 20) {
                        this.drawsegment_texture24_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 24) {
                        this.drawsegment_texture32_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 19) {
                        this.drawsegment_gouraud_texture8_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 21) {
                        this.drawsegment_gouraud_texture24_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 25) {
                        this.drawsegment_gouraud_texture32_alpha(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                    }
                }
                else if (this.m_drawFlags == 0) {
                    this.drawsegment_plain(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 1) {
                    this.drawsegment_gouraud(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 2) {
                    this.drawsegment_texture8(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 4) {
                    this.drawsegment_texture24(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 8) {
                    this.drawsegment_texture32(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 3) {
                    this.drawsegment_gouraud_texture8(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 5) {
                    this.drawsegment_gouraud_texture24(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 9) {
                    this.drawsegment_gouraud_texture32(this.xadd1, this.xadd2, screen_HEIGHT2, screen_HEIGHT);
                }
            }
            else {
                this.xrght = (screen_HEIGHT2 + 0.5f - n11) * this.xadd1 + n9;
                if (this.INTERPOLATE_ALPHA) {
                    if (this.m_drawFlags == 16) {
                        this.drawsegment_plain_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 17) {
                        this.drawsegment_gouraud_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 18) {
                        this.drawsegment_texture8_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 20) {
                        this.drawsegment_texture24_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 24) {
                        this.drawsegment_texture32_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 19) {
                        this.drawsegment_gouraud_texture8_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 21) {
                        this.drawsegment_gouraud_texture24_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                    else if (this.m_drawFlags == 25) {
                        this.drawsegment_gouraud_texture32_alpha(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                    }
                }
                else if (this.m_drawFlags == 0) {
                    this.drawsegment_plain(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 1) {
                    this.drawsegment_gouraud(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 2) {
                    this.drawsegment_texture8(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 4) {
                    this.drawsegment_texture24(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 8) {
                    this.drawsegment_texture32(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 3) {
                    this.drawsegment_gouraud_texture8(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 5) {
                    this.drawsegment_gouraud_texture24(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
                else if (this.m_drawFlags == 9) {
                    this.drawsegment_gouraud_texture32(this.xadd2, this.xadd1, screen_HEIGHT2, screen_HEIGHT);
                }
            }
        }
    }
    
    private final void drawsegment_plain(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int fill = this.m_fill;
        final int index = this.m_index;
        while (i < n3) {
            int n4 = (int)(this.xleft + 0.5f);
            if (n4 < 0) {
                n4 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            float n5 = this.izadd * (n4 + 0.5f - this.xleft) + this.zleft;
            for (int j = n4 + i; j < screen_WIDTH + i; ++j) {
                if (n5 <= this.m_zbuffer[j]) {
                    this.m_zbuffer[j] = n5;
                    this.m_pixels[j] = fill;
                    this.m_stencil[j] = index;
                }
                n5 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_plain_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int n4 = this.m_fill & 0xFF0000;
        final int n5 = this.m_fill & 0xFF00;
        final int n6 = this.m_fill & 0xFF;
        final int index = this.m_index;
        final float n7 = this.iaadd;
        while (i < n3) {
            int n8 = (int)(this.xleft + 0.5f);
            if (n8 < 0) {
                n8 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n9 = n8 + 0.5f - this.xleft;
            float n10 = this.izadd * n9 + this.zleft;
            int n11 = (int)(n7 * n9 + this.aleft);
            for (int j = n8 + i; j < screen_WIDTH + i; ++j) {
                if (n10 <= this.m_zbuffer[j]) {
                    final int n12 = n11 >> 16;
                    final int n13 = this.m_pixels[j];
                    final int n14 = n13 & 0xFF00;
                    final int n15 = n13 & 0xFF;
                    final int n16 = n13 & 0xFF0000;
                    this.m_pixels[j] = ((n16 + ((n4 - n16) * n12 >> 8) & 0xFF0000) | (n14 + ((n5 - n14) * n12 >> 8) & 0xFF00) | (n15 + ((n6 - n15) * n12 >> 8) & 0xFF));
                    this.m_stencil[j] = index;
                }
                n10 += this.izadd;
                n11 += this.iaadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud(final float n, final float n2, int i, int n3) {
        final float n4 = this.iradd;
        final float n5 = this.igadd;
        final float n6 = this.ibadd;
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        while (i < n3) {
            int n7 = (int)(this.xleft + 0.5f);
            if (n7 < 0) {
                n7 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n8 = n7 + 0.5f - this.xleft;
            int n9 = (int)(n4 * n8 + this.rleft);
            int n10 = (int)(n5 * n8 + this.gleft);
            int n11 = (int)(n6 * n8 + this.bleft);
            float n12 = this.izadd * n8 + this.zleft;
            for (int j = n7 + i; j < screen_WIDTH + i; ++j) {
                if (n12 <= this.m_zbuffer[j]) {
                    this.m_zbuffer[j] = n12;
                    this.m_pixels[j] = ((n9 & 0xFF0000) | (n10 >> 8 & 0xFF00) | n11 >> 16);
                    this.m_stencil[j] = index;
                }
                n9 += this.iradd;
                n10 += this.igadd;
                n11 += this.ibadd;
                n12 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iradd;
        final float n5 = this.igadd;
        final float n6 = this.ibadd;
        final float n7 = this.iaadd;
        while (i < n3) {
            int n8 = (int)(this.xleft + 0.5f);
            if (n8 < 0) {
                n8 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n9 = n8 + 0.5f - this.xleft;
            int n10 = (int)(n4 * n9 + this.rleft);
            int n11 = (int)(n5 * n9 + this.gleft);
            int n12 = (int)(n6 * n9 + this.bleft);
            int n13 = (int)(n7 * n9 + this.aleft);
            float n14 = this.izadd * n9 + this.zleft;
            for (int j = n8 + i; j < screen_WIDTH + i; ++j) {
                if (n14 <= this.m_zbuffer[j]) {
                    final int n15 = n10 & 0xFF0000;
                    final int n16 = n11 >> 8 & 0xFF00;
                    final int n17 = n12 >> 16;
                    final int n18 = this.m_pixels[j];
                    final int n19 = n18 & 0xFF0000;
                    final int n20 = n18 & 0xFF00;
                    final int n21 = n18 & 0xFF;
                    final int n22 = n13 >> 16;
                    this.m_pixels[j] = ((n19 + ((n15 - n19) * n22 >> 8) & 0xFF0000) | (n20 + ((n16 - n20) * n22 >> 8) & 0xFF00) | (n21 + ((n17 - n21) * n22 >> 8) & 0xFF));
                    this.m_stencil[j] = index;
                }
                n10 += this.iradd;
                n11 += this.igadd;
                n12 += this.ibadd;
                n13 += this.iaadd;
                n14 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.aleft += this.aleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_texture8(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final int n6 = this.m_fill & 0xFF0000;
        final int n7 = this.m_fill & 0xFF00;
        final int n8 = this.m_fill & 0xFF;
        while (i < n3) {
            int n9 = (int)(this.xleft + 0.5f);
            if (n9 < 0) {
                n9 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n10 = n9 + 0.5f - this.xleft;
            int n11 = (int)(n4 * n10 + this.uleft);
            int n12 = (int)(n5 * n10 + this.vleft);
            float n13 = this.izadd * n10 + this.zleft;
            for (int j = n9 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n13 <= this.m_zbuffer[j]) {
                        int n21;
                        if (this.m_bilinear) {
                            final int n14 = (n12 >> 16) * this.TEX_WIDTH + (n11 >> 16);
                            final char c = (char)(n11 & (char)(-1));
                            final int n15 = this.m_texture[n14] & 0xFF;
                            final int n16 = this.m_texture[n14 + 1] & 0xFF;
                            final int n17 = n14 + this.TEX_WIDTH;
                            final int n18 = this.m_texture[n17] & 0xFF;
                            final int n19 = this.m_texture[n17 + 1] & 0xFF;
                            final int n20 = n15 + ((n16 - n15) * c >> 16);
                            n21 = n20 + ((n18 + ((n19 - n18) * c >> 16) - n20) * (n12 & (char)(-1)) >> 16);
                        }
                        else {
                            n21 = (this.m_texture[(n12 >> 16) * this.TEX_WIDTH + (n11 >> 16)] & 0xFF);
                        }
                        final int n22 = this.m_pixels[j];
                        final int n23 = n22 & 0xFF00;
                        final int n24 = n22 & 0xFF;
                        final int n25 = n22 & 0xFF0000;
                        this.m_pixels[j] = ((n25 + ((n6 - n25) * n21 >> 8) & 0xFF0000) | (n23 + ((n7 - n23) * n21 >> 8) & 0xFF00) | (n24 + ((n8 - n24) * n21 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n11 += this.iuadd;
                n12 += this.ivadd;
                n13 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_texture8_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iaadd;
        final int n7 = this.m_fill & 0xFF0000;
        final int n8 = this.m_fill & 0xFF00;
        final int n9 = this.m_fill & 0xFF;
        while (i < n3) {
            int n10 = (int)(this.xleft + 0.5f);
            if (n10 < 0) {
                n10 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n11 = n10 + 0.5f - this.xleft;
            int n12 = (int)(n4 * n11 + this.uleft);
            int n13 = (int)(n5 * n11 + this.vleft);
            int n14 = (int)(n6 * n11 + this.aleft);
            float n15 = this.izadd * n11 + this.zleft;
            for (int j = n10 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n15 <= this.m_zbuffer[j]) {
                        int n23;
                        if (this.m_bilinear) {
                            final int n16 = (n13 >> 16) * this.TEX_WIDTH + (n12 >> 16);
                            final char c = (char)(n12 & (char)(-1));
                            final int n17 = this.m_texture[n16] & 0xFF;
                            final int n18 = this.m_texture[n16 + 1] & 0xFF;
                            final int n19 = n16 + this.TEX_WIDTH;
                            final int n20 = this.m_texture[n19] & 0xFF;
                            final int n21 = this.m_texture[n19 + 1] & 0xFF;
                            final int n22 = n17 + ((n18 - n17) * c >> 16);
                            n23 = n22 + ((n20 + ((n21 - n20) * c >> 16) - n22) * (n13 & (char)(-1)) >> 16);
                        }
                        else {
                            n23 = (this.m_texture[(n13 >> 16) * this.TEX_WIDTH + (n12 >> 16)] & 0xFF);
                        }
                        final int n24 = n23 * (n14 >> 16) >> 8;
                        final int n25 = this.m_pixels[j];
                        final int n26 = n25 & 0xFF00;
                        final int n27 = n25 & 0xFF;
                        final int n28 = n25 & 0xFF0000;
                        this.m_pixels[j] = ((n28 + ((n7 - n28) * n24 >> 8) & 0xFF0000) | (n26 + ((n8 - n26) * n24 >> 8) & 0xFF00) | (n27 + ((n9 - n27) * n24 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n12 += this.iuadd;
                n13 += this.ivadd;
                n15 += this.izadd;
                n14 += this.iaadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
            this.aleft += this.aleftadd;
        }
    }
    
    private final void drawsegment_texture24(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        while (i < n3) {
            int n6 = (int)(this.xleft + 0.5f);
            if (n6 < 0) {
                n6 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n7 = n6 + 0.5f - this.xleft;
            int n8 = (int)(n4 * n7 + this.uleft);
            int n9 = (int)(n5 * n7 + this.vleft);
            float n10 = this.izadd * n7 + this.zleft;
            for (int j = n6 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n10 <= this.m_zbuffer[j]) {
                        this.m_zbuffer[j] = n10;
                        if (this.m_bilinear) {
                            final int n11 = (n9 >> 16) * this.TEX_WIDTH + (n8 >> 16);
                            final char c = (char)((n8 & (char)(-1)) >> 9);
                            final char c2 = (char)((n9 & (char)(-1)) >> 9);
                            final int n12 = this.m_texture[n11];
                            final int n13 = this.m_texture[n11 + 1];
                            final int n14 = n11 + this.TEX_WIDTH;
                            final int n15 = this.m_texture[n14];
                            final int n16 = this.m_texture[n14 + 1];
                            final int n17 = n12 & 0xFF0000;
                            final int n18 = n15 & 0xFF0000;
                            final int n19 = n17 + (((n13 & 0xFF0000) - n17) * c >> 7);
                            final int n20 = n19 + ((n18 + (((n16 & 0xFF0000) - n18) * c >> 7) - n19) * c2 >> 7);
                            final int n21 = n12 & 0xFF00;
                            final int n22 = n15 & 0xFF00;
                            final int n23 = n21 + (((n13 & 0xFF00) - n21) * c >> 7);
                            final int n24 = n23 + ((n22 + (((n16 & 0xFF00) - n22) * c >> 7) - n23) * c2 >> 7);
                            final int n25 = n12 & 0xFF;
                            final int n26 = n15 & 0xFF;
                            final int n27 = n25 + (((n13 & 0xFF) - n25) * c >> 7);
                            this.m_pixels[j] = ((n20 & 0xFF0000) | (n24 & 0xFF00) | (n27 + ((n26 + (((n16 & 0xFF) - n26) * c >> 7) - n27) * c2 >> 7) & 0xFF));
                        }
                        else {
                            this.m_pixels[j] = this.m_texture[(n9 >> 16) * this.TEX_WIDTH + (n8 >> 16)];
                        }
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n8 += this.iuadd;
                n9 += this.ivadd;
                n10 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_texture24_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iaadd;
        while (i < n3) {
            int n7 = (int)(this.xleft + 0.5f);
            if (n7 < 0) {
                n7 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n8 = n7 + 0.5f - this.xleft;
            int n9 = (int)(n4 * n8 + this.uleft);
            int n10 = (int)(n5 * n8 + this.vleft);
            int n11 = (int)(n6 * n8 + this.aleft);
            float n12 = this.izadd * n8 + this.zleft;
            for (int j = n7 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n12 <= this.m_zbuffer[j]) {
                        final int n13 = n11 >> 16;
                        if (this.m_bilinear) {
                            final int n14 = (n10 >> 16) * this.TEX_WIDTH + (n9 >> 16);
                            final char c = (char)((n9 & (char)(-1)) >> 9);
                            final char c2 = (char)((n10 & (char)(-1)) >> 9);
                            final int n15 = this.m_texture[n14];
                            final int n16 = this.m_texture[n14 + 1];
                            final int n17 = n14 + this.TEX_WIDTH;
                            final int n18 = this.m_texture[n17];
                            final int n19 = this.m_texture[n17 + 1];
                            final int n20 = n15 & 0xFF0000;
                            final int n21 = n18 & 0xFF0000;
                            final int n22 = n20 + (((n16 & 0xFF0000) - n20) * c >> 7);
                            final int n23 = n22 + ((n21 + (((n19 & 0xFF0000) - n21) * c >> 7) - n22) * c2 >> 7);
                            final int n24 = n15 & 0xFF00;
                            final int n25 = n18 & 0xFF00;
                            final int n26 = n24 + (((n16 & 0xFF00) - n24) * c >> 7);
                            final int n27 = n26 + ((n25 + (((n19 & 0xFF00) - n25) * c >> 7) - n26) * c2 >> 7);
                            final int n28 = n15 & 0xFF;
                            final int n29 = n18 & 0xFF;
                            final int n30 = n28 + (((n16 & 0xFF) - n28) * c >> 7);
                            final int n31 = n30 + ((n29 + (((n19 & 0xFF) - n29) * c >> 7) - n30) * c2 >> 7);
                            final int n32 = this.m_pixels[j];
                            final int n33 = n32 & 0xFF0000;
                            final int n34 = n32 & 0xFF00;
                            final int n35 = n32 & 0xFF;
                            this.m_pixels[j] = ((n33 + ((n23 - n33) * n13 >> 8) & 0xFF0000) | (n34 + ((n27 - n34) * n13 >> 8) & 0xFF00) | (n35 + ((n31 - n35) * n13 >> 8) & 0xFF));
                        }
                        else {
                            final int n36 = this.m_texture[(n10 >> 16) * this.TEX_WIDTH + (n9 >> 16)];
                            final int n37 = n36 & 0xFF00;
                            final int n38 = n36 & 0xFF;
                            final int n39 = n36 & 0xFF0000;
                            final int n40 = this.m_pixels[j];
                            final int n41 = n40 & 0xFF0000;
                            final int n42 = n40 & 0xFF00;
                            final int n43 = n40 & 0xFF;
                            this.m_pixels[j] = ((n41 + ((n39 - n41) * n13 >> 8) & 0xFF0000) | (n42 + ((n37 - n42) * n13 >> 8) & 0xFF00) | (n43 + ((n38 - n43) * n13 >> 8) & 0xFF));
                        }
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n9 += this.iuadd;
                n10 += this.ivadd;
                n11 += this.iaadd;
                n12 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
            this.aleft += this.aleftadd;
        }
    }
    
    private final void drawsegment_texture32(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        while (i < n3) {
            int n6 = (int)(this.xleft + 0.5f);
            if (n6 < 0) {
                n6 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n7 = n6 + 0.5f - this.xleft;
            int n8 = (int)(n4 * n7 + this.uleft);
            int n9 = (int)(n5 * n7 + this.vleft);
            float n10 = this.izadd * n7 + this.zleft;
            for (int j = n6 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n10 <= this.m_zbuffer[j]) {
                        if (this.m_bilinear) {
                            final int n11 = (n9 >> 16) * this.TEX_WIDTH + (n8 >> 16);
                            final char c = (char)((n8 & (char)(-1)) >> 9);
                            final char c2 = (char)((n9 & (char)(-1)) >> 9);
                            final int n12 = this.m_texture[n11];
                            final int n13 = this.m_texture[n11 + 1];
                            final int n14 = n11 + this.TEX_WIDTH;
                            final int n15 = this.m_texture[n14];
                            final int n16 = this.m_texture[n14 + 1];
                            final int n17 = n12 & 0xFF0000;
                            final int n18 = n15 & 0xFF0000;
                            final int n19 = n17 + (((n13 & 0xFF0000) - n17) * c >> 7);
                            final int n20 = n19 + ((n18 + (((n16 & 0xFF0000) - n18) * c >> 7) - n19) * c2 >> 7);
                            final int n21 = n12 & 0xFF00;
                            final int n22 = n15 & 0xFF00;
                            final int n23 = n21 + (((n13 & 0xFF00) - n21) * c >> 7);
                            final int n24 = n23 + ((n22 + (((n16 & 0xFF00) - n22) * c >> 7) - n23) * c2 >> 7);
                            final int n25 = n12 & 0xFF;
                            final int n26 = n15 & 0xFF;
                            final int n27 = n25 + (((n13 & 0xFF) - n25) * c >> 7);
                            final int n28 = n27 + ((n26 + (((n16 & 0xFF) - n26) * c >> 7) - n27) * c2 >> 7);
                            final int n29 = n12 >>> 24;
                            final int n30 = n15 >>> 24;
                            final int n31 = n29 + (((n13 >>> 24) - n29) * c >> 7);
                            final int n32 = n31 + ((n30 + (((n16 >>> 24) - n30) * c >> 7) - n31) * c2 >> 7);
                            final int n33 = this.m_pixels[j];
                            final int n34 = n33 & 0xFF0000;
                            final int n35 = n33 & 0xFF00;
                            final int n36 = n33 & 0xFF;
                            this.m_pixels[j] = ((n34 + ((n20 - n34) * n32 >> 8) & 0xFF0000) | (n35 + ((n24 - n35) * n32 >> 8) & 0xFF00) | (n36 + ((n28 - n36) * n32 >> 8) & 0xFF));
                        }
                        else {
                            final int n37 = this.m_texture[(n9 >> 16) * this.TEX_WIDTH + (n8 >> 16)];
                            final int n38 = n37 >>> 24;
                            final int n39 = n37 & 0xFF00;
                            final int n40 = n37 & 0xFF;
                            final int n41 = n37 & 0xFF0000;
                            final int n42 = this.m_pixels[j];
                            final int n43 = n42 & 0xFF0000;
                            final int n44 = n42 & 0xFF00;
                            final int n45 = n42 & 0xFF;
                            this.m_pixels[j] = ((n43 + ((n41 - n43) * n38 >> 8) & 0xFF0000) | (n44 + ((n39 - n44) * n38 >> 8) & 0xFF00) | (n45 + ((n40 - n45) * n38 >> 8) & 0xFF));
                        }
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n8 += this.iuadd;
                n9 += this.ivadd;
                n10 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
            this.aleft += this.aleftadd;
        }
    }
    
    private final void drawsegment_texture32_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iaadd;
        while (i < n3) {
            int n7 = (int)(this.xleft + 0.5f);
            if (n7 < 0) {
                n7 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n8 = n7 + 0.5f - this.xleft;
            int n9 = (int)(n4 * n8 + this.uleft);
            int n10 = (int)(n5 * n8 + this.vleft);
            int n11 = (int)(n6 * n8 + this.aleft);
            float n12 = this.izadd * n8 + this.zleft;
            for (int j = n7 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n12 <= this.m_zbuffer[j]) {
                        final int n13 = n11 >> 16;
                        if (this.m_bilinear) {
                            final int n14 = (n10 >> 16) * this.TEX_WIDTH + (n9 >> 16);
                            final char c = (char)((n9 & (char)(-1)) >> 9);
                            final char c2 = (char)((n10 & (char)(-1)) >> 9);
                            final int n15 = this.m_texture[n14];
                            final int n16 = this.m_texture[n14 + 1];
                            final int n17 = n14 + this.TEX_WIDTH;
                            final int n18 = this.m_texture[n17];
                            final int n19 = this.m_texture[n17 + 1];
                            final int n20 = n15 & 0xFF0000;
                            final int n21 = n18 & 0xFF0000;
                            final int n22 = n20 + (((n16 & 0xFF0000) - n20) * c >> 7);
                            final int n23 = n22 + ((n21 + (((n19 & 0xFF0000) - n21) * c >> 7) - n22) * c2 >> 7);
                            final int n24 = n15 & 0xFF00;
                            final int n25 = n18 & 0xFF00;
                            final int n26 = n24 + (((n16 & 0xFF00) - n24) * c >> 7);
                            final int n27 = n26 + ((n25 + (((n19 & 0xFF00) - n25) * c >> 7) - n26) * c2 >> 7);
                            final int n28 = n15 & 0xFF;
                            final int n29 = n18 & 0xFF;
                            final int n30 = n28 + (((n16 & 0xFF) - n28) * c >> 7);
                            final int n31 = n30 + ((n29 + (((n19 & 0xFF) - n29) * c >> 7) - n30) * c2 >> 7);
                            final int n32 = n15 >>> 24;
                            final int n33 = n18 >>> 24;
                            final int n34 = n32 + (((n16 >>> 24) - n32) * c >> 7);
                            final int n35 = n13 * (n34 + ((n33 + (((n19 >>> 24) - n33) * c >> 7) - n34) * c2 >> 7)) >> 8;
                            final int n36 = this.m_pixels[j];
                            final int n37 = n36 & 0xFF0000;
                            final int n38 = n36 & 0xFF00;
                            final int n39 = n36 & 0xFF;
                            this.m_pixels[j] = ((n37 + ((n23 - n37) * n35 >> 8) & 0xFF0000) | (n38 + ((n27 - n38) * n35 >> 8) & 0xFF00) | (n39 + ((n31 - n39) * n35 >> 8) & 0xFF));
                        }
                        else {
                            final int n40 = this.m_texture[(n10 >> 16) * this.TEX_WIDTH + (n9 >> 16)];
                            final int n41 = n13 * (n40 >>> 24) >> 8;
                            final int n42 = n40 & 0xFF00;
                            final int n43 = n40 & 0xFF;
                            final int n44 = n40 & 0xFF0000;
                            final int n45 = this.m_pixels[j];
                            final int n46 = n45 & 0xFF0000;
                            final int n47 = n45 & 0xFF00;
                            final int n48 = n45 & 0xFF;
                            this.m_pixels[j] = ((n46 + ((n44 - n46) * n41 >> 8) & 0xFF0000) | (n47 + ((n42 - n47) * n41 >> 8) & 0xFF00) | (n48 + ((n43 - n48) * n41 >> 8) & 0xFF));
                        }
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n9 += this.iuadd;
                n10 += this.ivadd;
                n11 += this.iaadd;
                n12 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.zleft += this.zleftadd;
            this.aleft += this.aleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture8(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        while (i < n3) {
            int n9 = (int)(this.xleft + 0.5f);
            if (n9 < 0) {
                n9 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n10 = n9 + 0.5f - this.xleft;
            int n11 = (int)(n4 * n10 + this.uleft);
            int n12 = (int)(n5 * n10 + this.vleft);
            int n13 = (int)(n6 * n10 + this.rleft);
            int n14 = (int)(n7 * n10 + this.gleft);
            int n15 = (int)(n8 * n10 + this.bleft);
            float n16 = this.izadd * n10 + this.zleft;
            for (int j = n9 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n16 <= this.m_zbuffer[j]) {
                        int n24;
                        if (this.m_bilinear) {
                            final int n17 = (n12 >> 16) * this.TEX_WIDTH + (n11 >> 16);
                            final char c = (char)(n11 & (char)(-1));
                            final int n18 = this.m_texture[n17] & 0xFF;
                            final int n19 = this.m_texture[n17 + 1] & 0xFF;
                            final int n20 = n17 + this.TEX_WIDTH;
                            final int n21 = this.m_texture[n20] & 0xFF;
                            final int n22 = this.m_texture[n20 + 1] & 0xFF;
                            final int n23 = n18 + ((n19 - n18) * c >> 16);
                            n24 = n23 + ((n21 + ((n22 - n21) * c >> 16) - n23) * (n12 & (char)(-1)) >> 16);
                        }
                        else {
                            n24 = (this.m_texture[(n12 >> 16) * this.TEX_WIDTH + (n11 >> 16)] & 0xFF);
                        }
                        final int n25 = n13 & 0xFF0000;
                        final int n26 = n14 >> 8 & 0xFF00;
                        final int n27 = n15 >> 16;
                        final int n28 = this.m_pixels[j];
                        final int n29 = n28 & 0xFF0000;
                        final int n30 = n28 & 0xFF00;
                        final int n31 = n28 & 0xFF;
                        this.m_pixels[j] = ((n29 + ((n25 - n29) * n24 >> 8) & 0xFF0000) | (n30 + ((n26 - n30) * n24 >> 8) & 0xFF00) | (n31 + ((n27 - n31) * n24 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n11 += this.iuadd;
                n12 += this.ivadd;
                n13 += this.iradd;
                n14 += this.igadd;
                n15 += this.ibadd;
                n16 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture8_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        final float n9 = this.iaadd;
        while (i < n3) {
            int n10 = (int)(this.xleft + 0.5f);
            if (n10 < 0) {
                n10 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n11 = n10 + 0.5f - this.xleft;
            int n12 = (int)(n4 * n11 + this.uleft);
            int n13 = (int)(n5 * n11 + this.vleft);
            int n14 = (int)(n6 * n11 + this.rleft);
            int n15 = (int)(n7 * n11 + this.gleft);
            int n16 = (int)(n8 * n11 + this.bleft);
            int n17 = (int)(n9 * n11 + this.aleft);
            float n18 = this.izadd * n11 + this.zleft;
            for (int j = n10 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n18 <= this.m_zbuffer[j]) {
                        int n26;
                        if (this.m_bilinear) {
                            final int n19 = (n13 >> 16) * this.TEX_WIDTH + (n12 >> 16);
                            final char c = (char)(n12 & (char)(-1));
                            final int n20 = this.m_texture[n19] & 0xFF;
                            final int n21 = this.m_texture[n19 + 1] & 0xFF;
                            final int n22 = n19 + this.TEX_WIDTH;
                            final int n23 = this.m_texture[n22] & 0xFF;
                            final int n24 = this.m_texture[n22 + 1] & 0xFF;
                            final int n25 = n20 + ((n21 - n20) * c >> 16);
                            n26 = n25 + ((n23 + ((n24 - n23) * c >> 16) - n25) * (n13 & (char)(-1)) >> 16);
                        }
                        else {
                            n26 = (this.m_texture[(n13 >> 16) * this.TEX_WIDTH + (n12 >> 16)] & 0xFF);
                        }
                        final int n27 = n26 * (n17 >> 16) >> 8;
                        final int n28 = n14 & 0xFF0000;
                        final int n29 = n15 >> 8 & 0xFF00;
                        final int n30 = n16 >> 16;
                        final int n31 = this.m_pixels[j];
                        final int n32 = n31 & 0xFF0000;
                        final int n33 = n31 & 0xFF00;
                        final int n34 = n31 & 0xFF;
                        this.m_pixels[j] = ((n32 + ((n28 - n32) * n27 >> 8) & 0xFF0000) | (n33 + ((n29 - n33) * n27 >> 8) & 0xFF00) | (n34 + ((n30 - n34) * n27 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n12 += this.iuadd;
                n13 += this.ivadd;
                n14 += this.iradd;
                n15 += this.igadd;
                n16 += this.ibadd;
                n17 += this.iaadd;
                n18 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.aleft += this.aleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture24(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        while (i < n3) {
            int n9 = (int)(this.xleft + 0.5f);
            if (n9 < 0) {
                n9 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n10 = n9 + 0.5f - this.xleft;
            int n11 = (int)(n4 * n10 + this.uleft);
            int n12 = (int)(n5 * n10 + this.vleft);
            int n13 = (int)(n6 * n10 + this.rleft);
            int n14 = (int)(n7 * n10 + this.gleft);
            int n15 = (int)(n8 * n10 + this.bleft);
            float n16 = this.izadd * n10 + this.zleft;
            for (int j = n9 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n16 <= this.m_zbuffer[j]) {
                        this.m_zbuffer[j] = n16;
                        int n26;
                        int n30;
                        int n34;
                        if (this.m_bilinear) {
                            final int n17 = (n12 >> 16) * this.TEX_WIDTH + (n11 >> 16);
                            final char c = (char)((n11 & (char)(-1)) >> 9);
                            final char c2 = (char)((n12 & (char)(-1)) >> 9);
                            final int n18 = this.m_texture[n17];
                            final int n19 = this.m_texture[n17 + 1];
                            final int n20 = n17 + this.TEX_WIDTH;
                            final int n21 = this.m_texture[n20];
                            final int n22 = this.m_texture[n20 + 1];
                            final int n23 = n18 & 0xFF0000;
                            final int n24 = n21 & 0xFF0000;
                            final int n25 = n23 + (((n19 & 0xFF0000) - n23) * c >> 7);
                            n26 = n25 + ((n24 + (((n22 & 0xFF0000) - n24) * c >> 7) - n25) * c2 >> 7);
                            final int n27 = n18 & 0xFF00;
                            final int n28 = n21 & 0xFF00;
                            final int n29 = n27 + (((n19 & 0xFF00) - n27) * c >> 7);
                            n30 = n29 + ((n28 + (((n22 & 0xFF00) - n28) * c >> 7) - n29) * c2 >> 7);
                            final int n31 = n18 & 0xFF;
                            final int n32 = n21 & 0xFF;
                            final int n33 = n31 + (((n19 & 0xFF) - n31) * c >> 7);
                            n34 = n33 + ((n32 + (((n22 & 0xFF) - n32) * c >> 7) - n33) * c2 >> 7);
                        }
                        else {
                            final int n35 = this.m_texture[(n12 >> 16) * this.TEX_WIDTH + (n11 >> 16)];
                            n26 = (n35 & 0xFF0000);
                            n30 = (n35 & 0xFF00);
                            n34 = (n35 & 0xFF);
                        }
                        this.m_pixels[j] = ((n26 * (n13 >> 16) & 0xFF000000) | (n30 * (n14 >> 16) & 0xFF0000) | n34 * (n15 >> 16)) >> 8;
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n11 += this.iuadd;
                n12 += this.ivadd;
                n13 += this.iradd;
                n14 += this.igadd;
                n15 += this.ibadd;
                n16 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture24_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        final float n9 = this.iaadd;
        while (i < n3) {
            int n10 = (int)(this.xleft + 0.5f);
            if (n10 < 0) {
                n10 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n11 = n10 + 0.5f - this.xleft;
            int n12 = (int)(n4 * n11 + this.uleft);
            int n13 = (int)(n5 * n11 + this.vleft);
            int n14 = (int)(n6 * n11 + this.rleft);
            int n15 = (int)(n7 * n11 + this.gleft);
            int n16 = (int)(n8 * n11 + this.bleft);
            int n17 = (int)(n9 * n11 + this.aleft);
            float n18 = this.izadd * n11 + this.zleft;
            for (int j = n10 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n18 < this.m_zbuffer[j]) {
                        final int n19 = n17 >> 16;
                        int n29;
                        int n33;
                        int n37;
                        if (this.m_bilinear) {
                            final int n20 = (n13 >> 16) * this.TEX_WIDTH + (n12 >> 16);
                            final char c = (char)((n12 & (char)(-1)) >> 9);
                            final char c2 = (char)((n13 & (char)(-1)) >> 9);
                            final int n21 = this.m_texture[n20];
                            final int n22 = this.m_texture[n20 + 1];
                            final int n23 = n20 + this.TEX_WIDTH;
                            final int n24 = this.m_texture[n23];
                            final int n25 = this.m_texture[n23 + 1];
                            final int n26 = n21 & 0xFF0000;
                            final int n27 = n24 & 0xFF0000;
                            final int n28 = n26 + (((n22 & 0xFF0000) - n26) * c >> 7);
                            n29 = n28 + ((n27 + (((n25 & 0xFF0000) - n27) * c >> 7) - n28) * c2 >> 7) >> 16;
                            final int n30 = n21 & 0xFF00;
                            final int n31 = n24 & 0xFF00;
                            final int n32 = n30 + (((n22 & 0xFF00) - n30) * c >> 7);
                            n33 = n32 + ((n31 + (((n25 & 0xFF00) - n31) * c >> 7) - n32) * c2 >> 7) >> 8;
                            final int n34 = n21 & 0xFF;
                            final int n35 = n24 & 0xFF;
                            final int n36 = n34 + (((n22 & 0xFF) - n34) * c >> 7);
                            n37 = n36 + ((n35 + (((n25 & 0xFF) - n35) * c >> 7) - n36) * c2 >> 7);
                        }
                        else {
                            final int n38 = this.m_texture[(n13 >> 16) * this.TEX_WIDTH + (n12 >> 16)];
                            n29 = (n38 & 0xFF0000) >> 16;
                            n33 = (n38 & 0xFF00) >> 8;
                            n37 = (n38 & 0xFF);
                        }
                        final int n39 = n29 * n14 >>> 8;
                        final int n40 = n33 * n15 >>> 16;
                        final int n41 = n37 * n16 >>> 24;
                        final int n42 = this.m_pixels[j];
                        final int n43 = n42 & 0xFF0000;
                        final int n44 = n42 & 0xFF00;
                        final int n45 = n42 & 0xFF;
                        this.m_pixels[j] = ((n43 + ((n39 - n43) * n19 >> 8) & 0xFF0000) | (n44 + ((n40 - n44) * n19 >> 8) & 0xFF00) | (n45 + ((n41 - n45) * n19 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n12 += this.iuadd;
                n13 += this.ivadd;
                n14 += this.iradd;
                n15 += this.igadd;
                n16 += this.ibadd;
                n17 += this.iaadd;
                n18 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.aleft += this.aleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture32(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        while (i < n3) {
            int n9 = (int)(this.xleft + 0.5f);
            if (n9 < 0) {
                n9 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n10 = n9 + 0.5f - this.xleft;
            int n11 = (int)(n4 * n10 + this.uleft);
            int n12 = (int)(n5 * n10 + this.vleft);
            int n13 = (int)(n6 * n10 + this.rleft);
            int n14 = (int)(n7 * n10 + this.gleft);
            int n15 = (int)(n8 * n10 + this.bleft);
            float n16 = this.izadd * n10 + this.zleft;
            for (int j = n9 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n16 <= this.m_zbuffer[j]) {
                        int n26;
                        int n30;
                        int n34;
                        int n38;
                        if (this.m_bilinear) {
                            final int n17 = (n12 >> 16) * this.TEX_WIDTH + (n11 >> 16);
                            final char c = (char)((n11 & (char)(-1)) >> 9);
                            final char c2 = (char)((n12 & (char)(-1)) >> 9);
                            final int n18 = this.m_texture[n17];
                            final int n19 = this.m_texture[n17 + 1];
                            final int n20 = n17 + this.TEX_WIDTH;
                            final int n21 = this.m_texture[n20];
                            final int n22 = this.m_texture[n20 + 1];
                            final int n23 = n18 & 0xFF0000;
                            final int n24 = n21 & 0xFF0000;
                            final int n25 = n23 + (((n19 & 0xFF0000) - n23) * c >> 7);
                            n26 = n25 + ((n24 + (((n22 & 0xFF0000) - n24) * c >> 7) - n25) * c2 >> 7) >> 16;
                            final int n27 = n18 & 0xFF00;
                            final int n28 = n21 & 0xFF00;
                            final int n29 = n27 + (((n19 & 0xFF00) - n27) * c >> 7);
                            n30 = n29 + ((n28 + (((n22 & 0xFF00) - n28) * c >> 7) - n29) * c2 >> 7) >> 8;
                            final int n31 = n18 & 0xFF;
                            final int n32 = n21 & 0xFF;
                            final int n33 = n31 + (((n19 & 0xFF) - n31) * c >> 7);
                            n34 = n33 + ((n32 + (((n22 & 0xFF) - n32) * c >> 7) - n33) * c2 >> 7);
                            final int n35 = n18 >>> 24;
                            final int n36 = n21 >>> 24;
                            final int n37 = n35 + (((n19 >>> 24) - n35) * c >> 7);
                            n38 = n37 + ((n36 + (((n22 >>> 24) - n36) * c >> 7) - n37) * c2 >> 7);
                        }
                        else {
                            final int n39 = this.m_texture[(n12 >> 16) * this.TEX_WIDTH + (n11 >> 16)];
                            n38 = n39 >>> 24;
                            n26 = (n39 & 0xFF0000) >> 16;
                            n30 = (n39 & 0xFF00) >> 8;
                            n34 = (n39 & 0xFF);
                        }
                        final int n40 = n26 * n13 >>> 8;
                        final int n41 = n30 * n14 >>> 16;
                        final int n42 = n34 * n15 >>> 24;
                        final int n43 = this.m_pixels[j];
                        final int n44 = n43 & 0xFF0000;
                        final int n45 = n43 & 0xFF00;
                        final int n46 = n43 & 0xFF;
                        this.m_pixels[j] = ((n44 + ((n40 - n44) * n38 >> 8) & 0xFF0000) | (n45 + ((n41 - n45) * n38 >> 8) & 0xFF00) | (n46 + ((n42 - n46) * n38 >> 8) & 0xFF));
                    }
                }
                catch (Exception ex) {}
                n11 += this.iuadd;
                n12 += this.ivadd;
                n13 += this.iradd;
                n14 += this.igadd;
                n15 += this.ibadd;
                n16 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    private final void drawsegment_gouraud_texture32_alpha(final float n, final float n2, int i, int n3) {
        i *= this.SCREEN_WIDTH;
        n3 *= this.SCREEN_WIDTH;
        final int index = this.m_index;
        final float n4 = this.iuadd;
        final float n5 = this.ivadd;
        final float n6 = this.iradd;
        final float n7 = this.igadd;
        final float n8 = this.ibadd;
        final float n9 = this.iaadd;
        while (i < n3) {
            int n10 = (int)(this.xleft + 0.5f);
            if (n10 < 0) {
                n10 = 0;
            }
            int screen_WIDTH = (int)(this.xrght + 0.5f);
            if (screen_WIDTH > this.SCREEN_WIDTH) {
                screen_WIDTH = this.SCREEN_WIDTH;
            }
            final float n11 = n10 + 0.5f - this.xleft;
            int n12 = (int)(n4 * n11 + this.uleft);
            int n13 = (int)(n5 * n11 + this.vleft);
            int n14 = (int)(n6 * n11 + this.rleft);
            int n15 = (int)(n7 * n11 + this.gleft);
            int n16 = (int)(n8 * n11 + this.bleft);
            int n17 = (int)(n9 * n11 + this.aleft);
            float n18 = this.izadd * n11 + this.zleft;
            for (int j = n10 + i; j < screen_WIDTH + i; ++j) {
                try {
                    if (n18 < this.m_zbuffer[j]) {
                        final int n19 = n17 >> 16;
                        int n29;
                        int n33;
                        int n37;
                        int n41;
                        if (this.m_bilinear) {
                            final int n20 = (n13 >> 16) * this.TEX_WIDTH + (n12 >> 16);
                            final char c = (char)((n12 & (char)(-1)) >> 9);
                            final char c2 = (char)((n13 & (char)(-1)) >> 9);
                            final int n21 = this.m_texture[n20];
                            final int n22 = this.m_texture[n20 + 1];
                            final int n23 = n20 + this.TEX_WIDTH;
                            final int n24 = this.m_texture[n23];
                            final int n25 = this.m_texture[n23 + 1];
                            final int n26 = n21 & 0xFF0000;
                            final int n27 = n24 & 0xFF0000;
                            final int n28 = n26 + (((n22 & 0xFF0000) - n26) * c >> 7);
                            n29 = n28 + ((n27 + (((n25 & 0xFF0000) - n27) * c >> 7) - n28) * c2 >> 7) >> 16;
                            final int n30 = n21 & 0xFF00;
                            final int n31 = n24 & 0xFF00;
                            final int n32 = n30 + (((n22 & 0xFF00) - n30) * c >> 7);
                            n33 = n32 + ((n31 + (((n25 & 0xFF00) - n31) * c >> 7) - n32) * c2 >> 7) >> 8;
                            final int n34 = n21 & 0xFF;
                            final int n35 = n24 & 0xFF;
                            final int n36 = n34 + (((n22 & 0xFF) - n34) * c >> 7);
                            n37 = n36 + ((n35 + (((n25 & 0xFF) - n35) * c >> 7) - n36) * c2 >> 7);
                            final int n38 = n21 >>> 24;
                            final int n39 = n24 >>> 24;
                            final int n40 = n38 + (((n22 >>> 24) - n38) * c >> 7);
                            n41 = n19 * (n40 + ((n39 + (((n25 >>> 24) - n39) * c >> 7) - n40) * c2 >> 7)) >> 8;
                        }
                        else {
                            final int n42 = this.m_texture[(n13 >> 16) * this.TEX_WIDTH + (n12 >> 16)];
                            n41 = n19 * (n42 >>> 24) >> 8;
                            n29 = (n42 & 0xFF0000) >> 16;
                            n33 = (n42 & 0xFF00) >> 8;
                            n37 = (n42 & 0xFF);
                        }
                        final int n43 = n29 * n14 >>> 8;
                        final int n44 = n33 * n15 >>> 16;
                        final int n45 = n37 * n16 >>> 24;
                        final int n46 = this.m_pixels[j];
                        final int n47 = n46 & 0xFF0000;
                        final int n48 = n46 & 0xFF00;
                        final int n49 = n46 & 0xFF;
                        this.m_pixels[j] = ((n47 + ((n43 - n47) * n41 >> 8) & 0xFF0000) | (n48 + ((n44 - n48) * n41 >> 8) & 0xFF00) | (n49 + ((n45 - n49) * n41 >> 8) & 0xFF));
                        this.m_stencil[j] = index;
                    }
                }
                catch (Exception ex) {}
                n12 += this.iuadd;
                n13 += this.ivadd;
                n14 += this.iradd;
                n15 += this.igadd;
                n16 += this.ibadd;
                n17 += this.iaadd;
                n18 += this.izadd;
            }
            i += this.SCREEN_WIDTH;
            this.xleft += n;
            this.xrght += n2;
            this.uleft += this.uleftadd;
            this.vleft += this.vleftadd;
            this.rleft += this.rleftadd;
            this.gleft += this.gleftadd;
            this.bleft += this.bleftadd;
            this.aleft += this.aleftadd;
            this.zleft += this.zleftadd;
        }
    }
    
    public BTriangle(final BGraphics parent) {
        this.x_array = new float[3];
        this.y_array = new float[3];
        this.z_array = new float[3];
        this.u_array = new float[3];
        this.v_array = new float[3];
        this.r_array = new float[3];
        this.g_array = new float[3];
        this.b_array = new float[3];
        this.a_array = new float[3];
        this.parent = parent;
        this.reset();
    }
}
