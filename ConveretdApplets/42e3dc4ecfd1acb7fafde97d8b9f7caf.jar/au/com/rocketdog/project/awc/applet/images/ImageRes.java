// 
// Decompiled by Procyon v0.5.30
// 

package au.com.rocketdog.project.awc.applet.images;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.awt.MediaTracker;
import java.awt.Image;

public final class ImageRes
{
    private static final byte[] a;
    public static Image b;
    public static Image c;
    public static Image d;
    public static Image e;
    public static Image f;
    public static Image g;
    public static Image h;
    public static Image i;
    public static Image j;
    public static Image k;
    public static Image l;
    public static Image m;
    public static Image n;
    public static Image o;
    public static Image p;
    public static Image q;
    public static Image r;
    public static Image s;
    public static Image t;
    public static Image u;
    public static Image v;
    public static Image w;
    public static Image x;
    public static Image y;
    public static Image z;
    public static Image aa;
    public static Image ab;
    public static Image ac;
    public static Image ad;
    public static Image ae;
    public static Image af;
    public static Image ag;
    public static Image ah;
    public static Image ai;
    public static Image aj;
    public static Image ak;
    public static Image al;
    public static Image am;
    public static Image an;
    public static Image ao;
    public static Image ap;
    public static Image aq;
    public static Image ar;
    public static Image as;
    public static Image at;
    public static Image au;
    public static Image av;
    public static Image aw;
    public static Image ax;
    public static Image ay;
    public static Image az;
    public static Image a0;
    public static Image a1;
    public static Image a2;
    public static Image a3;
    public static Image a4;
    public static Image a5;
    public static Image a6;
    public static Image a7;
    public static Image a8;
    public static Image a9;
    public static Image ba;
    public static Image bb;
    public static Image bc;
    public static Image bd;
    public static Image be;
    public static Image bf;
    public static Image bg;
    public static Image bh;
    public static Image bi;
    public static Image bj;
    public static Image bk;
    public static Image bl;
    public static Image bm;
    public static Image bn;
    public static Image bo;
    public static Image bp;
    public static /* synthetic */ Class bq;
    
    public static void a(final MediaTracker mediaTracker) {
        try {
            ImageRes.ak = a("thumb_error_160x120.gif");
            ImageRes.c = a("imlive.jpg");
            ImageRes.bg = a("sort_name_down_on.gif");
            ImageRes.bh = a("sort_name_up_on.gif");
            ImageRes.bi = a("sort_name_down_off.gif");
            ImageRes.bj = a("sort_name_up_off.gif");
            ImageRes.bk = a("sort_number_down_on.gif");
            ImageRes.bl = a("sort_number_up_on.gif");
            ImageRes.bm = a("sort_number_down_off.gif");
            ImageRes.bn = a("sort_number_up_off.gif");
            ImageRes.b = a("broadcaster_logo_spin.gif");
            ImageRes.a2 = a("thumb_160x120.gif");
            ImageRes.a3 = a("private_160x120.gif");
            ImageRes.a6 = a("cam_ad.gif");
            ImageRes.e = a("icon_-.gif");
            ImageRes.d = a("icon_+.gif");
            ImageRes.f = a("button_send_down.gif");
            ImageRes.g = a("button_send.gif");
            ImageRes.j = a("icon_sex_m_standard.gif");
            ImageRes.k = a("icon_sex_f_standard.gif");
            ImageRes.l = a("icon_sex_other_standard.gif");
            ImageRes.m = a("icon_sex_group_standard.gif");
            ImageRes.n = a("icon_sex_m_green.gif");
            ImageRes.o = a("icon_sex_f_green.gif");
            ImageRes.p = a("icon_sex_other_green.gif");
            ImageRes.q = a("icon_sex_group_green.gif");
            ImageRes.v = a("icon_sex_m_silver.gif");
            ImageRes.w = a("icon_sex_f_silver.gif");
            ImageRes.x = a("icon_sex_other_silver.gif");
            ImageRes.y = a("icon_sex_group_silver.gif");
            ImageRes.r = a("icon_sex_m_gold.gif");
            ImageRes.s = a("icon_sex_f_gold.gif");
            ImageRes.t = a("icon_sex_other_gold.gif");
            ImageRes.u = a("icon_sex_group_gold.gif");
            ImageRes.z = a("icon_sex_m_staff.gif");
            ImageRes.aa = a("icon_sex_f_staff.gif");
            ImageRes.ab = a("icon_sex_other_staff.gif");
            ImageRes.ac = a("icon_sex_group_staff.gif");
            ImageRes.aj = a("button_webcam_thumbnails.gif");
            ImageRes.ao = a("button_webcam_thumbnail_disabled.gif");
            ImageRes.bf = a("button_webcam_thumbnail_act.gif");
            ImageRes.al = a("button_webcam_thumbnails_down.gif");
            ImageRes.av = a("directory_bc_off.gif");
            ImageRes.aw = a("directory_bc_on.gif");
            ImageRes.ax = a("directory_bc_hover.gif");
            ImageRes.ay = a("directory_cam_off.gif");
            ImageRes.az = a("directory_cam_on.gif");
            ImageRes.a0 = a("directory_cam_hover.gif");
            ImageRes.as = a("directory_chat_off.gif");
            ImageRes.at = a("directory_chat_on.gif");
            ImageRes.au = a("directory_chat_hover.gif");
            ImageRes.a9 = a("arrow_green_right_up.gif");
            ImageRes.ba = a("arrow_green_right_over.gif");
            ImageRes.ap = a("directory_settings_off.gif");
            ImageRes.aq = a("directory_settings_on.gif");
            ImageRes.ar = a("directory_settings_hover.gif");
            ImageRes.a1 = a("find_in_chat.gif");
            ImageRes.a4 = a("icon_awc.gif");
            ImageRes.a7 = a("bc_ad.gif");
            ImageRes.a8 = a("bc.gif");
            ImageRes.am = a("icon_thumb_on.gif");
            ImageRes.an = a("icon_thumb_off.gif");
            ImageRes.bp = a("btn_right.gif");
            ImageRes.bo = a("btn_left.gif");
            ImageRes.a5 = a("icon_info.gif");
            ImageRes.h = a("icon_chat_cam.gif");
            ImageRes.i = a("icon_chat_cam_private.gif");
            ImageRes.ad = a("icon_sex_m.gif");
            ImageRes.ae = a("icon_sex_f.gif");
            ImageRes.ah = a("broadcaster_placeholder.gif");
            ImageRes.af = a("icon_sex_group.gif");
            ImageRes.ag = a("icon_sex_other.gif");
            ImageRes.bb = a("play.gif");
            ImageRes.bc = a("stop.gif");
            ImageRes.bd = a("play_over.gif");
            ImageRes.be = a("stop_over.gif");
            mediaTracker.addImage(ImageRes.av, 1);
            mediaTracker.addImage(ImageRes.aw, 2);
            mediaTracker.addImage(ImageRes.ax, 3);
            mediaTracker.addImage(ImageRes.bg, 4);
            mediaTracker.addImage(ImageRes.bh, 5);
            mediaTracker.addImage(ImageRes.bi, 6);
            mediaTracker.addImage(ImageRes.bj, 7);
            mediaTracker.addImage(ImageRes.bk, 8);
            mediaTracker.addImage(ImageRes.bl, 9);
            mediaTracker.addImage(ImageRes.bm, 10);
            mediaTracker.addImage(ImageRes.bn, 11);
            mediaTracker.addImage(ImageRes.b, 12);
            mediaTracker.addImage(ImageRes.a6, 13);
            mediaTracker.addImage(ImageRes.a7, 14);
            mediaTracker.addImage(ImageRes.a8, 15);
            mediaTracker.addImage(ImageRes.aj, 16);
            mediaTracker.addImage(ImageRes.a2, 17);
            mediaTracker.addImage(ImageRes.a3, 18);
            mediaTracker.addImage(ImageRes.ak, 19);
            mediaTracker.addImage(ImageRes.e, 20);
            mediaTracker.addImage(ImageRes.d, 21);
            mediaTracker.addImage(ImageRes.f, 22);
            mediaTracker.addImage(ImageRes.g, 23);
            mediaTracker.addImage(ImageRes.j, 27);
            mediaTracker.addImage(ImageRes.k, 28);
            mediaTracker.addImage(ImageRes.l, 29);
            mediaTracker.addImage(ImageRes.m, 30);
            mediaTracker.addImage(ImageRes.c, 31);
            mediaTracker.addImage(ImageRes.v, 0);
            mediaTracker.addImage(ImageRes.w, 0);
            mediaTracker.addImage(ImageRes.x, 0);
            mediaTracker.addImage(ImageRes.y, 0);
            mediaTracker.addImage(ImageRes.r, 0);
            mediaTracker.addImage(ImageRes.s, 0);
            mediaTracker.addImage(ImageRes.t, 0);
            mediaTracker.addImage(ImageRes.u, 0);
            mediaTracker.addImage(ImageRes.z, 0);
            mediaTracker.addImage(ImageRes.aa, 0);
            mediaTracker.addImage(ImageRes.ab, 0);
            mediaTracker.addImage(ImageRes.ac, 0);
            mediaTracker.addImage(ImageRes.n, 0);
            mediaTracker.addImage(ImageRes.o, 0);
            mediaTracker.addImage(ImageRes.p, 0);
            mediaTracker.addImage(ImageRes.q, 0);
            mediaTracker.addImage(ImageRes.ah, 0);
            mediaTracker.addImage(ImageRes.ad, 0);
            mediaTracker.addImage(ImageRes.ae, 0);
            mediaTracker.addImage(ImageRes.af, 0);
            mediaTracker.addImage(ImageRes.ag, 0);
            mediaTracker.addImage(ImageRes.an, 0);
            mediaTracker.addImage(ImageRes.am, 0);
            mediaTracker.addImage(ImageRes.aj, 0);
            mediaTracker.addImage(ImageRes.al, 0);
            mediaTracker.addImage(ImageRes.ao, 0);
            mediaTracker.addImage(ImageRes.bf, 0);
            mediaTracker.addImage(ImageRes.ah, 0);
            mediaTracker.addImage(ImageRes.ai, 0);
            mediaTracker.addImage(ImageRes.ap, 0);
            mediaTracker.addImage(ImageRes.aq, 0);
            mediaTracker.addImage(ImageRes.ar, 0);
            mediaTracker.addImage(ImageRes.as, 0);
            mediaTracker.addImage(ImageRes.at, 0);
            mediaTracker.addImage(ImageRes.au, 0);
            mediaTracker.addImage(ImageRes.ay, 0);
            mediaTracker.addImage(ImageRes.az, 0);
            mediaTracker.addImage(ImageRes.a0, 0);
            mediaTracker.addImage(ImageRes.a1, 0);
            mediaTracker.addImage(ImageRes.i, 0);
            mediaTracker.addImage(ImageRes.h, 0);
            mediaTracker.addImage(ImageRes.a4, 0);
            mediaTracker.addImage(ImageRes.a9, 0);
            mediaTracker.addImage(ImageRes.ba, 0);
            mediaTracker.addImage(ImageRes.bo, 0);
            mediaTracker.addImage(ImageRes.bp, 0);
            mediaTracker.addImage(ImageRes.a5, 0);
            mediaTracker.addImage(ImageRes.bb, 0);
            mediaTracker.addImage(ImageRes.bc, 0);
            mediaTracker.addImage(ImageRes.bd, 0);
            mediaTracker.addImage(ImageRes.be, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    private static byte[] b(final String s) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(((ImageRes.bq == null) ? (ImageRes.bq = class$("au.com.rocketdog.project.awc.applet.images.ImageRes")) : ImageRes.bq).getResourceAsStream(s));
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 0;
        int read;
        while (-1 != (read = bufferedInputStream.read(ImageRes.a, 0, 32096))) {
            byteArrayOutputStream.write(ImageRes.a, n, read);
            n += read;
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static Image a(final String s) {
        try {
            return Toolkit.getDefaultToolkit().createImage(b(s));
        }
        catch (Exception ex) {
            b.a("Cant find: " + s, 3);
            return null;
        }
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        a = new byte[32096];
    }
}
