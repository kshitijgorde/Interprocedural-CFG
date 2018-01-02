import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.image.RGBImageFilter;
import sexy.gui.SexyApplet;
import java.awt.Rectangle;
import java.util.Vector;
import java.util.Hashtable;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class r extends e
{
    public String[] a;
    public String[] b;
    public String[] c;
    public WordFallApplet d;
    public Hashtable e;
    public Vector f;
    public Vector[] g;
    public u h;
    public u i;
    public u j;
    public u k;
    public u l;
    public u m;
    public j[] n;
    public j o;
    public j p;
    public j q;
    public j r;
    public j s;
    public j t;
    public j u;
    public j v;
    public j w;
    public j x;
    public j y;
    public j z;
    public j aa;
    public j ab;
    public j ac;
    public j ad;
    public j ae;
    public j af;
    public j ag;
    public j ah;
    public j ai;
    public j aj;
    public j ak;
    public j al;
    public j am;
    public j an;
    public j ao;
    public j ap;
    public j aq;
    public j ar;
    public j as;
    public j at;
    public j[] au;
    public j[] av;
    public j[] aw;
    public j[] ax;
    public j[] ay;
    public j[] az;
    public String a0;
    public int[] a1;
    public int[] a2;
    public int a3;
    public int a4;
    public j a5;
    public ae a6;
    
    public j a(final j j, final Rectangle rectangle, final j i, final Rectangle rectangle2) {
        if (rectangle.width != rectangle2.width || rectangle.height != rectangle2.height) {
            return null;
        }
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int[] b = j.b();
        final int h = j.h();
        final int[] b2 = i.b();
        final int h2 = i.h();
        final j k = new j();
        k.a(width, height);
        final int[] b3 = k.b();
        int n = 0;
        for (int l = 0; l < height; ++l) {
            int n2 = (rectangle.y + l) * h + rectangle.x;
            int n3 = (rectangle2.y + l) * h2 + rectangle2.x;
            for (int n4 = 0; n4 < width; ++n4) {
                b3[n++] = (b2[n3++] << 24 | (b[n2++] & 0xFFFFFF));
            }
        }
        return k;
    }
    
    public j b(final j j) {
        final int h = j.h();
        final int n = j.c() / 2;
        return this.a(j, new Rectangle(0, n, h, n), j, new Rectangle(0, 0, h, n));
    }
    
    public j[] a(final j j, final int n) {
        final int h = j.h();
        final int n2 = j.c() / (n + 1);
        final j[] array = new j[n];
        for (int i = 0; i < n; ++i) {
            array[i] = this.a(j, new Rectangle(0, n2 + i * n2, h, n2), j, new Rectangle(0, 0, h, n2));
        }
        return array;
    }
    
    public r(final WordFallApplet d) {
        super(d);
        this.a = new String[] { "background.jpg", "tile.gif", "tile_green_glow.gif", "tile_gold.gif", "tile_gold_glow.jpg", "tile_fire.gif", "tile_fire_glow.gif", "?font_tile_letters.gif", "?font_digits_small.gif", "arrows.gif", "worm_still.gif", "worm_eat.gif", "worm_squat.gif", "button_submit.jpg", "button_new_game.jpg", "progress_books.gif", "tile_fire_fx1.gif", "tile_fire_fx2.gif", "?logo_bookworm.gif", "msg_level_piece.gif", "?balloon_scramble.gif", "tile_scorched.gif", "?font_level.gif", "_font_score.gif", "ember_small.gif", "tile_spin_fx.gif", "_smoke.gif", "worm_still_blink.gif", "ember_medium.gif", "ember_large.gif", "button_sound_on.jpg", "button_sound_off.jpg", "button_help.jpg" };
        this.b = new String[] { "blank.au", "nextselect.au", "wordcomplete.au", "bonuscomplete.au", "buzzer.au", "piece_hit.au", "chomp.au", "flametile.au", "warning.au", "up1.au", "up2.au", "up3.au", "up4.au", "up5.au", "up6.au", "up7.au", "up8.au", "temp_tile_break.au", "tileburn.au", "appear2.au", "goldtile.au", "buttonclick.au", "redtilecleared.au", "spinbonus.au", "spinsuperbonus.au", "bonusword.au", "burp.au", "levelup.au", "screenburn.au", "gameover.au" };
        this.c = new String[] { "intertitle.gif", "intertext.gif", "interscreen1.gif", "interscreen2.gif", "interscreen3.gif", "interstar.gif" };
        this.e = new Hashtable();
        this.f = new Vector();
        this.g = new Vector[] { new Vector(), new Vector(), new Vector(), new Vector(), new Vector() };
        this.az = new j[9];
        this.a0 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !?+";
        this.a1 = new int[255];
        this.a2 = new int[255];
        this.d = d;
    }
    
    public int g() {
        return 23;
    }
    
    public String[] b() {
        return this.b;
    }
    
    public void j() {
        this.a6 = new ae(this.d);
        this.m = new u(super.a[22], this.b("images/levelfont.txt"));
        this.l = new u(super.a[23], this.b("images/scorefont.txt"));
        this.au = this.a(super.a[13], 2);
        this.av = this.a(super.a[14], 2);
        this.aw = this.a(super.a[30], 2);
        this.ax = this.a(super.a[31], 2);
        this.ay = this.a(super.a[32], 2);
        this.ai = this.b(super.a[9]);
        this.ak = this.b(super.a[10]);
        this.al = this.b(super.a[11]);
        this.aq = this.b(super.a[12]);
        this.aj = this.b(super.a[15]);
        final String[] c = this.c;
        this.n = new j[c.length];
        for (int i = 0; i < c.length; ++i) {
            this.n[i] = this.a("images/" + c[i]);
        }
        this.ar = this.b(super.a[4]);
        this.k = this.d.b("SansSerif", 1, 10);
        this.j = this.d.b("SansSerif", 1, 12);
        this.i = this.d.b("SansSerif", 1, 20);
        this.h = this.d.b("SansSerif", 1, 24);
        final j j = super.a[7];
        final j k = super.a[8];
        final j b = this.b(super.a[1]);
        final j a = this.a(b, new af(1.2));
        final j l = super.a[3];
        final j a2 = this.a(l, new af(1.2));
        final j m = super.a[5];
        final j a3 = this.a(m, new af(1.2));
        final j b2 = this.b(super.a[21]);
        final j j2 = new j();
        j2.a(b.h(), b.c());
        final n n = new n(j2);
        n.d = true;
        n.a(new Color(0, 255, 0));
        n.a(b, 0, 0);
        final j a4 = this.a(j2, new af(1.2));
        n.d = false;
        int n2 = 0;
        do {
            j j3 = null;
            int n3 = 0;
            switch (n2) {
                case 0: {
                    j3 = b;
                    n3 = 0;
                    break;
                }
                case 1: {
                    j3 = a;
                    n3 = 255;
                    break;
                }
                case 4: {
                    j3 = j2;
                    n3 = 0;
                    break;
                }
                case 5: {
                    j3 = a4;
                    n3 = 255;
                    break;
                }
                case 6: {
                    j3 = l;
                    n3 = 0;
                    break;
                }
                case 7: {
                    j3 = a2;
                    n3 = 255;
                    break;
                }
                case 2: {
                    j3 = m;
                    n3 = 0;
                    break;
                }
                case 3: {
                    j3 = a3;
                    n3 = 255;
                    break;
                }
                case 8: {
                    j3 = b2;
                    n3 = 176;
                    break;
                }
                default: {
                    j3 = b;
                    n3 = 0;
                    break;
                }
            }
            (this.az[n2] = new j()).a(j3.h() * 26, j3.c());
            final n n4 = new n(this.az[n2]);
            int n5 = 0;
            do {
                final int n6 = n5 * j3.h();
                n4.a(j3, n6, 0);
                n4.d = true;
                final int n7 = j.c() / 26;
                final Rectangle rectangle = new Rectangle(0, n5 * n7, j.h(), n7);
                n4.e = new f(n3, n3, n3);
                n4.b(j, n6, 0, rectangle);
                if (this.d.ad && this.d.ac == 1) {
                    int n8 = w.c[n5];
                    if (81 == n5 + 65) {
                        n8 = w.c[25];
                    }
                    final int n9 = k.c() / 10;
                    final Rectangle rectangle2 = new Rectangle(0, (n8 - 1) * n9, k.h(), n9);
                    n4.e = new f(255, 255, 255, 255);
                    n4.b(k, n6 + j3.h() - k.h() - 2, j3.c() - n9 - 3, rectangle2);
                }
                n4.d = false;
            } while (++n5 < 26);
        } while (++n2 < 9);
        final long currentTimeMillis = System.currentTimeMillis();
        InputStream inputStream = null;
        try {
            inputStream = new URL(this.d.getCodeBase(), "wordlist.zip").openConnection().getInputStream();
            final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                final String name = zipEntry.getName();
                if (name.equals("wordlist.txt")) {
                    final int u = (int)zipEntry.getSize();
                    super.u = u;
                    final char[] array = new char[256];
                    int n10 = 0;
                    final char[] array2 = new char[256];
                    int n11 = 0;
                    int n12 = 0;
                    for (int v = 0; v < u; ++v) {
                        super.v = v;
                        final char c2 = (char)zipInputStream.read();
                        if (c2 >= '0' && c2 <= '9') {
                            if (n12 == 0) {
                                n11 = 0;
                                n12 = 1;
                            }
                            n11 = n11 * 10 + (c2 - '0');
                        }
                        else if (c2 == '\r' || c2 == '\n') {
                            if (n10 > 0) {
                                final String s = new String(array, 0, n10);
                                this.e.put(s, s);
                                if (s.length() == 5) {
                                    this.f.addElement(s);
                                }
                                System.arraycopy(array, 0, array2, 0, n10);
                                n10 = 0;
                            }
                            n12 = 0;
                        }
                        else {
                            if (n11 > 0 && n10 == 0) {
                                System.arraycopy(array2, 0, array, 0, n11);
                                n10 = n11;
                            }
                            array[n10++] = c2;
                        }
                    }
                }
                else if (name.equals("bonus_word_dictionary.txt")) {
                    final int n13 = (int)zipEntry.getSize();
                    final byte[] array3 = new byte[n13];
                    for (int n14 = 0; n14 < n13; ++n14) {
                        array3[n14] = (byte)(char)zipInputStream.read();
                    }
                    if (n13 > 0) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(new String(array3).replace('\r', ' ').replace('\n', ' '), " ");
                        if (stringTokenizer != null) {
                            while (stringTokenizer.hasMoreTokens()) {
                                final String nextToken = stringTokenizer.nextToken();
                                if (nextToken != null) {
                                    final String trim = nextToken.trim();
                                    if (trim.length() < 3 || trim.length() > 7) {
                                        continue;
                                    }
                                    final int n15 = trim.length() - 3;
                                    if (this.g[n15] == null || this.g[n15].contains(trim)) {
                                        continue;
                                    }
                                    this.g[n15].addElement(trim);
                                }
                            }
                        }
                    }
                }
                else {
                    System.out.print("\nUnknown zip file entry: " + zipEntry.getName() + "\n");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.d.a("dictfail", "Dictionary loading failed");
        }
        try {
            inputStream.close();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            this.d.a("dictfail", "Dictionary stream close failed");
        }
        System.out.println("Dictionary loading time: " + (int)(System.currentTimeMillis() - currentTimeMillis) + "ms");
        this.o = this.a("images/?page.gif");
        this.p = this.a("images/?yes.gif");
        this.q = this.a("images/?no.gif");
        this.r = this.a("images/?msg_level_up.gif");
        this.s = this.a("images/?msg_new_game.gif");
        this.t = this.a("images/?msg_game_over.gif");
        this.u = this.a("images/?msg_continue.gif");
        this.v = this.a("images/?msg_replay.gif");
        this.w = this.a("images/bonusword_background.gif");
        this.x = this.a("images/?balloon_bonusword.gif");
        this.y = this.a("images/?bonusarrow_left.gif");
        this.z = this.a("images/?bonusarrow_right.gif");
        this.aa = this.a("images/?bonusarrow_top.gif");
        this.ab = this.a("images/worm_sweat.gif");
        this.ac = this.a("images/worm_sweat_seq.gif");
        this.ad = this.a("images/worm_scorched.gif");
        this.ae = this.a("images/worm_scorched_blink.gif");
        this.af = this.a("images/background_scorched.jpg");
        this.ag = this.a("images/tile_flames_fx.gif");
        this.ah = this.a("images/?fireline.gif");
    }
    
    public double k() {
        return 0.25;
    }
    
    public String[] d() {
        return this.a;
    }
}
