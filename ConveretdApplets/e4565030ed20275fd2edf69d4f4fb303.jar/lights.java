import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class lights extends Applet
{
    boolean axa;
    boolean axb;
    boolean axc;
    boolean axd;
    boolean axe;
    boolean bxa;
    boolean bxb;
    boolean bxc;
    boolean bxd;
    boolean bxe;
    boolean cxa;
    boolean cxb;
    boolean cxc;
    boolean cxd;
    boolean cxe;
    boolean dxa;
    boolean dxb;
    boolean dxc;
    boolean dxd;
    boolean dxe;
    boolean exa;
    boolean exb;
    boolean exc;
    boolean exd;
    boolean exe;
    boolean uwin;
    boolean c1;
    boolean c1a;
    boolean c2;
    boolean c2a;
    boolean c3;
    boolean c3a;
    boolean c4;
    boolean c4a;
    boolean c5;
    boolean c5a;
    boolean c6;
    boolean c6a;
    boolean c7;
    boolean c7a;
    boolean c8;
    boolean c8a;
    boolean c9;
    boolean c9a;
    boolean c10;
    boolean c10a;
    boolean c11;
    boolean c11a;
    boolean c12;
    boolean c12a;
    boolean c13;
    boolean c13a;
    boolean c14;
    boolean c14a;
    boolean c15;
    boolean c15a;
    boolean c16;
    boolean c16a;
    boolean c17;
    boolean c17a;
    boolean c18;
    boolean c18a;
    boolean c19;
    boolean c19a;
    boolean c20;
    boolean c20a;
    boolean c21;
    boolean c21a;
    boolean c22;
    boolean c22a;
    boolean c23;
    boolean c23a;
    boolean c24;
    boolean c24a;
    boolean c25;
    boolean c25a;
    int board;
    boolean started;
    Font f;
    Font db;
    Font db1;
    Color clr1;
    int help;
    boolean bhelp;
    boolean reset;
    boolean sboard;
    int cboard;
    boolean selboard;
    boolean maxmove;
    int move;
    int mvrmn;
    int justwon;
    int boardover;
    int gameover;
    
    public lights() {
        this.f = new Font("TimesRoman", 1, 20);
        this.db = new Font("TimesRoman", 1, 25);
        this.db1 = new Font("TimesRoman", 2, 10);
        this.clr1 = new Color(0.807f, 1.0f, 0.0f);
    }
    
    public String getAppletInfo() {
        return "LightzOut.java by Donnie Burgess (aka Shadowtwin)";
    }
    
    public void init() {
        this.selboard = false;
        this.bhelp = true;
        this.uwin = false;
        this.axa = false;
        this.axb = false;
        this.axc = false;
        this.axd = false;
        this.axe = false;
        this.bxa = false;
        this.bxb = false;
        this.bxc = false;
        this.bxd = false;
        this.bxe = false;
        this.cxa = false;
        this.cxb = false;
        this.cxc = false;
        this.cxd = false;
        this.cxe = false;
        this.dxa = false;
        this.dxb = false;
        this.dxc = false;
        this.dxd = false;
        this.dxe = false;
        this.exa = false;
        this.exb = false;
        this.exc = false;
        this.exd = false;
        this.exe = false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 0 && this.board < 6 && this.move == 16) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 5 && this.board < 11 && this.move == 17) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 10 && this.board < 16 && this.move == 18) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 15 && this.board < 21 && this.move == 19) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 20 && this.board < 26 && this.move == 20) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 25 && this.board < 31 && this.move == 21) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 30 && this.board < 36 && this.move == 22) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 35 && this.board < 41 && this.move == 23) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 40 && this.board < 46 && this.move == 24) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 3 && n < 271 && n2 > 3 && n2 < 271 && this.board > 45 && this.board < 51 && this.move == 25) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.maxmove = true;
        }
        if (n > 209 && n < 271 && n2 > 274 && n2 < 306 && this.board == 0) {
            this.play(this.getCodeBase(), "brdstrt.au");
            this.board = 1;
            this.started = true;
        }
        if (n > 209 && n < 271 && n2 > 308 && n2 < 340) {
            if (this.bhelp && this.help < 2) {
                this.play(this.getCodeBase(), "bpsh.au");
                if (this.help == 0 && this.board == 1) {
                    this.c3 = true;
                    this.c4a = true;
                    this.c5a = true;
                    this.c9a = true;
                }
                if (this.help == 1 && this.board == 1) {
                    this.c13 = true;
                    this.c9 = true;
                    this.c14a = true;
                    this.c19a = true;
                    this.c15a = true;
                }
                if (this.help == 0 && this.board == 2) {
                    this.c11 = true;
                    this.c12 = true;
                    this.c7a = true;
                    this.c17a = true;
                    this.c13a = true;
                }
                if (this.help == 1 && this.board == 2) {
                    this.c21 = true;
                    this.c22 = true;
                    this.c17 = true;
                    this.c23a = true;
                }
                if (this.help == 0 && this.board == 3) {
                    this.c2 = true;
                    this.c3 = true;
                    this.c4 = true;
                    this.c8 = true;
                }
                if (this.help == 1 && this.board == 3) {
                    this.c22 = true;
                    this.c23 = true;
                    this.c24 = true;
                    this.c18 = true;
                }
                if (this.help == 0 && this.board == 4) {
                    this.c21a = true;
                    this.c16a = true;
                    this.c22 = true;
                }
                if (this.help == 1 && this.board == 4) {
                    this.c4 = true;
                    this.c5 = true;
                    this.c10 = true;
                }
                if (this.help == 0 && this.board == 5) {
                    this.c5 = true;
                    this.c10 = true;
                    this.c4a = true;
                }
                if (this.help == 1 && this.board == 5) {
                    this.c20 = true;
                    this.c24 = true;
                    this.c25 = true;
                }
                if (this.help == 0 && this.board == 6) {
                    this.c22a = true;
                    this.c23 = true;
                    this.c24 = true;
                    this.c18a = true;
                }
                if (this.help == 1 && this.board == 6) {
                    this.c10 = true;
                    this.c14 = true;
                    this.c15 = true;
                    this.c20 = true;
                }
                if (this.help == 0 && this.board == 7) {
                    this.c14a = true;
                    this.c10 = true;
                    this.c15 = true;
                    this.c20 = true;
                }
                if (this.help == 1 && this.board == 7) {
                    this.c6 = true;
                    this.c11 = true;
                    this.c16 = true;
                    this.c12a = true;
                }
                if (this.help == 0 && this.board == 8) {
                    this.c13a = true;
                    this.c12 = true;
                    this.c8 = true;
                    this.c18 = true;
                    this.c14 = true;
                }
                if (this.help == 1 && this.board == 8) {
                    this.c13 = true;
                    this.c9a = true;
                    this.c14a = true;
                    this.c15a = true;
                    this.c19a = true;
                }
                if (this.help == 0 && this.board == 9) {
                    this.c16 = true;
                    this.c12 = true;
                    this.c17 = true;
                    this.c22 = true;
                    this.c18 = true;
                }
                if (this.help == 1 && this.board == 9) {
                    this.c12a = true;
                    this.c8 = true;
                    this.c13 = true;
                    this.c18a = true;
                    this.c14a = true;
                }
                if (this.help == 0 && this.board == 10) {
                    this.c11 = true;
                    this.c16 = true;
                    this.c21a = true;
                    this.c17 = true;
                }
                if (this.help == 1 && this.board == 10) {
                    this.c21 = true;
                    this.c16a = true;
                    this.c22a = true;
                }
                if (this.help == 0 && this.board == 11) {
                    this.c2 = true;
                    this.c7a = true;
                    this.c6a = true;
                    this.c8a = true;
                    this.c12 = true;
                }
                if (this.help == 1 && this.board == 11) {
                    this.c12a = true;
                    this.c7 = true;
                    this.c11 = true;
                    this.c13 = true;
                    this.c17a = true;
                }
                if (this.help == 0 && this.board == 12) {
                    this.c3 = true;
                    this.c2a = true;
                    this.c4a = true;
                    this.c8 = true;
                }
                if (this.help == 1 && this.board == 12) {
                    this.c13a = true;
                    this.c8a = true;
                    this.c12a = true;
                    this.c14 = true;
                    this.c18 = true;
                }
                if (this.help == 0 && this.board == 13) {
                    this.c18 = true;
                    this.c17a = true;
                    this.c13a = true;
                    this.c23a = true;
                    this.c19a = true;
                }
                if (this.help == 1 && this.board == 13) {
                    this.c4 = true;
                    this.c3a = true;
                    this.c5a = true;
                    this.c9a = true;
                }
                if (this.help == 0 && this.board == 14) {
                    this.c9 = true;
                    this.c13a = true;
                    this.c14a = true;
                    this.c15a = true;
                    this.c19a = true;
                }
                if (this.help == 1 && this.board == 14) {
                    this.c14 = true;
                    this.c18a = true;
                    this.c19 = true;
                    this.c20a = true;
                    this.c24a = true;
                }
                if (this.help == 0 && this.board == 15) {
                    this.c19a = true;
                    this.c23a = true;
                    this.c24a = true;
                    this.c25a = true;
                }
                if (this.help == 1 && this.board == 15) {
                    this.c20a = true;
                    this.c15a = true;
                    this.c25 = true;
                    this.c19 = true;
                }
                if (this.help == 0 && this.board == 16) {
                    this.c18a = true;
                    this.c19a = true;
                    this.c20 = true;
                    this.c14a = true;
                    this.c24a = true;
                }
                if (this.help == 1 && this.board == 16) {
                    this.c23a = true;
                    this.c24 = true;
                    this.c25 = true;
                    this.c19 = true;
                }
                if (this.help == 0 && this.board == 17) {
                    this.c6a = true;
                    this.c11a = true;
                    this.c16a = true;
                    this.c12a = true;
                }
                if (this.help == 1 && this.board == 17) {
                    this.c6 = true;
                    this.c2a = true;
                    this.c7a = true;
                    this.c12 = true;
                    this.c8a = true;
                }
                if (this.help == 0 && this.board == 18) {
                    this.c2a = true;
                    this.c6a = true;
                    this.c7 = true;
                    this.c8a = true;
                    this.c12a = true;
                }
                if (this.help == 1 && this.board == 18) {
                    this.c12 = true;
                    this.c16a = true;
                    this.c17 = true;
                    this.c18a = true;
                    this.c22a = true;
                }
                if (this.help == 0 && this.board == 19) {
                    this.c17a = true;
                    this.c21 = true;
                    this.c22a = true;
                    this.c23 = true;
                }
                if (this.help == 1 && this.board == 19) {
                    this.c2a = true;
                    this.c3 = true;
                    this.c4a = true;
                    this.c8a = true;
                }
                if (this.help == 0 && this.board == 20) {
                    this.c2a = true;
                    this.c3 = true;
                    this.c4a = true;
                    this.c8a = true;
                }
                if (this.help == 1 && this.board == 20) {
                    this.c8 = true;
                    this.c12a = true;
                    this.c13a = true;
                    this.c14a = true;
                    this.c18a = true;
                }
                if (this.help == 0 && this.board == 21) {
                    this.c17a = true;
                    this.c21 = true;
                    this.c22a = true;
                    this.c23a = true;
                }
                if (this.help == 1 && this.board == 21) {
                    this.c13 = true;
                    this.c17 = true;
                    this.c18 = true;
                    this.c19a = true;
                    this.c23 = true;
                }
                if (this.help == 0 && this.board == 22) {
                    this.c18a = true;
                    this.c22 = true;
                    this.c23 = true;
                    this.c24 = true;
                }
                if (this.help == 1 && this.board == 22) {
                    this.c9a = true;
                    this.c13a = true;
                    this.c14a = true;
                    this.c15 = true;
                    this.c19a = true;
                }
                if (this.help == 0 && this.board == 23) {
                    this.c3a = true;
                    this.c4a = true;
                    this.c5a = true;
                    this.c9a = true;
                }
                if (this.help == 1 && this.board == 23) {
                    this.c14 = true;
                    this.c18 = true;
                    this.c19 = true;
                    this.c20a = true;
                    this.c24a = true;
                }
                if (this.help == 0 && this.board == 24) {
                    this.c4 = true;
                    this.c8a = true;
                    this.c9 = true;
                    this.c10 = true;
                    this.c14 = true;
                }
                if (this.help == 1 && this.board == 24) {
                    this.c14a = true;
                    this.c18a = true;
                    this.c19 = true;
                    this.c20a = true;
                    this.c24 = true;
                }
                if (this.help == 0 && this.board == 25) {
                    this.c9 = true;
                    this.c13 = true;
                    this.c14 = true;
                    this.c15 = true;
                    this.c19 = true;
                }
                if (this.help == 1 && this.board == 25) {
                    this.c20 = true;
                    this.c24a = true;
                    this.c25 = true;
                }
            }
            ++this.help;
            ++this.move;
        }
        if (n > 209 && n < 271 && n2 > 342 && n2 < 374 && this.board > 0 && this.board < 51) {
            this.c1 = true;
            this.c2 = true;
            this.c3 = true;
            this.c4 = true;
            this.c5 = true;
            this.c6 = true;
            this.c7 = true;
            this.c8 = true;
            this.c9 = true;
            this.c10 = true;
            this.c11 = true;
            this.c12 = true;
            this.c13 = true;
            this.c14 = true;
            this.c15 = true;
            this.c16 = true;
            this.c17 = true;
            this.c18 = true;
            this.c19 = true;
            this.c20 = true;
            this.c21 = true;
            this.c22 = true;
            this.c23 = true;
            this.c24 = true;
            this.c25 = true;
            this.reset = true;
        }
        if (n > 44 && n < 171 && n2 > 308 && n2 < 340) {
            if (this.board == 0) {
                this.board = 51;
            }
            if (this.board == 52) {
                this.board = 53;
            }
        }
        if (this.board > 0) {
            if (n > 3 && n < 55 && n2 > 3 && n2 < 55 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                if (this.board < 51) {
                    ++this.move;
                    if (this.axa) {
                        this.c1 = true;
                    }
                    if (!this.axa) {
                        this.c1a = true;
                    }
                    if (this.axb) {
                        this.c2 = true;
                    }
                    if (!this.axb) {
                        this.c2a = true;
                    }
                    if (this.bxa) {
                        this.c6 = true;
                    }
                    if (!this.bxa) {
                        this.c6a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 1;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 10;
                    this.board = 26;
                    this.started = true;
                }
            }
            if (n > 57 && n < 109 && n2 > 3 && n2 < 55 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                if (this.board < 51) {
                    ++this.move;
                    if (this.axa) {
                        this.c1 = true;
                    }
                    if (!this.axa) {
                        this.c1a = true;
                    }
                    if (this.bxa) {
                        this.c6 = true;
                    }
                    if (!this.bxa) {
                        this.c6a = true;
                    }
                    if (this.cxa) {
                        this.c11 = true;
                    }
                    if (!this.cxa) {
                        this.c11a = true;
                    }
                    if (this.bxb) {
                        this.c7 = true;
                    }
                    if (!this.bxb) {
                        this.c7a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 2;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 11;
                    this.board = 27;
                    this.started = true;
                }
            }
            if (n > 111 && n < 163 && n2 > 3 && n2 < 55 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.bxa) {
                        this.c6 = true;
                    }
                    if (!this.bxa) {
                        this.c6a = true;
                    }
                    if (this.cxa) {
                        this.c11 = true;
                    }
                    if (!this.cxa) {
                        this.c11a = true;
                    }
                    if (this.dxa) {
                        this.c16 = true;
                    }
                    if (!this.dxa) {
                        this.c16a = true;
                    }
                    if (this.cxb) {
                        this.c12 = true;
                    }
                    if (!this.cxb) {
                        this.c12a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 3;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 11;
                    this.board = 28;
                    this.started = true;
                }
            }
            if (n > 165 && n < 217 && n2 > 3 && n2 < 55 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.cxa) {
                        this.c11 = true;
                    }
                    if (!this.cxa) {
                        this.c11a = true;
                    }
                    if (this.dxa) {
                        this.c16 = true;
                    }
                    if (!this.dxa) {
                        this.c16a = true;
                    }
                    if (this.exa) {
                        this.c21 = true;
                    }
                    if (!this.exa) {
                        this.c21a = true;
                    }
                    if (this.dxb) {
                        this.c17 = true;
                    }
                    if (!this.dxb) {
                        this.c17a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 4;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 11;
                    this.board = 29;
                    this.started = true;
                }
            }
            if (n > 219 && n < 271 && n2 > 3 && n2 < 55 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.dxa) {
                        this.c16 = true;
                    }
                    if (!this.dxa) {
                        this.c16a = true;
                    }
                    if (this.exa) {
                        this.c21 = true;
                    }
                    if (!this.exa) {
                        this.c21a = true;
                    }
                    if (this.exb) {
                        this.c22 = true;
                    }
                    if (!this.exb) {
                        this.c22a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 5;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 11;
                    this.board = 30;
                    this.started = true;
                }
            }
            if (n > 3 && n < 55 && n2 > 57 && n2 < 109 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.axa) {
                        this.c1 = true;
                    }
                    if (!this.axa) {
                        this.c1a = true;
                    }
                    if (this.axb) {
                        this.c2 = true;
                    }
                    if (!this.axb) {
                        this.c2a = true;
                    }
                    if (this.axc) {
                        this.c3 = true;
                    }
                    if (!this.axc) {
                        this.c3a = true;
                    }
                    if (this.bxb) {
                        this.c7 = true;
                    }
                    if (!this.bxb) {
                        this.c7a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 6;
                    this.board = 6;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 11;
                    this.board = 31;
                    this.started = true;
                }
            }
            if (n > 57 && n < 109 && n2 > 57 && n2 < 109 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.bxa) {
                        this.c6 = true;
                    }
                    if (!this.bxa) {
                        this.c6a = true;
                    }
                    if (this.axb) {
                        this.c2 = true;
                    }
                    if (!this.axb) {
                        this.c2a = true;
                    }
                    if (this.bxb) {
                        this.c7 = true;
                    }
                    if (!this.bxb) {
                        this.c7a = true;
                    }
                    if (this.cxb) {
                        this.c12 = true;
                    }
                    if (!this.cxb) {
                        this.c12a = true;
                    }
                    if (this.bxc) {
                        this.c8 = true;
                    }
                    if (!this.bxc) {
                        this.c8a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 7;
                    this.board = 7;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 12;
                    this.board = 32;
                    this.started = true;
                }
            }
            if (n > 111 && n < 163 && n2 > 57 && n2 < 109 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.cxa) {
                        this.c11 = true;
                    }
                    if (!this.cxa) {
                        this.c11a = true;
                    }
                    if (this.bxb) {
                        this.c7 = true;
                    }
                    if (!this.bxb) {
                        this.c7a = true;
                    }
                    if (this.cxb) {
                        this.c12 = true;
                    }
                    if (!this.cxb) {
                        this.c12a = true;
                    }
                    if (this.dxb) {
                        this.c17 = true;
                    }
                    if (!this.dxb) {
                        this.c17a = true;
                    }
                    if (this.cxc) {
                        this.c13 = true;
                    }
                    if (!this.cxc) {
                        this.c13a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 7;
                    this.board = 8;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 12;
                    this.board = 33;
                    this.started = true;
                }
            }
            if (n > 165 && n < 217 && n2 > 57 && n2 < 109 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.dxa) {
                        this.c16 = true;
                    }
                    if (!this.dxa) {
                        this.c16a = true;
                    }
                    if (this.cxb) {
                        this.c12 = true;
                    }
                    if (!this.cxb) {
                        this.c12a = true;
                    }
                    if (this.dxb) {
                        this.c17 = true;
                    }
                    if (!this.dxb) {
                        this.c17a = true;
                    }
                    if (this.exb) {
                        this.c22 = true;
                    }
                    if (!this.exb) {
                        this.c22a = true;
                    }
                    if (this.dxc) {
                        this.c18 = true;
                    }
                    if (!this.dxc) {
                        this.c18a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 7;
                    this.board = 9;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 12;
                    this.board = 34;
                    this.started = true;
                }
            }
            if (n > 219 && n < 271 && n2 > 57 && n2 < 109 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.exa) {
                        this.c21 = true;
                    }
                    if (!this.exa) {
                        this.c21a = true;
                    }
                    if (this.dxb) {
                        this.c17 = true;
                    }
                    if (!this.dxb) {
                        this.c17a = true;
                    }
                    if (this.exb) {
                        this.c22 = true;
                    }
                    if (!this.exb) {
                        this.c22a = true;
                    }
                    if (this.exc) {
                        this.c23 = true;
                    }
                    if (!this.exc) {
                        this.c23a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 7;
                    this.board = 10;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 12;
                    this.board = 35;
                    this.started = true;
                }
            }
            if (n > 3 && n < 55 && n2 > 111 && n2 < 163 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.axb) {
                        this.c2 = true;
                    }
                    if (!this.axb) {
                        this.c2a = true;
                    }
                    if (this.axc) {
                        this.c3 = true;
                    }
                    if (!this.axc) {
                        this.c3a = true;
                    }
                    if (this.axd) {
                        this.c4 = true;
                    }
                    if (!this.axd) {
                        this.c4a = true;
                    }
                    if (this.bxc) {
                        this.c8 = true;
                    }
                    if (!this.bxc) {
                        this.c8a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 7;
                    this.board = 11;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 12;
                    this.board = 36;
                    this.started = true;
                }
            }
            if (n > 57 && n < 109 && n2 > 111 && n2 < 163 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.bxb) {
                        this.c7 = true;
                    }
                    if (!this.bxb) {
                        this.c7a = true;
                    }
                    if (this.axc) {
                        this.c3 = true;
                    }
                    if (!this.axc) {
                        this.c3a = true;
                    }
                    if (this.bxc) {
                        this.c8 = true;
                    }
                    if (!this.bxc) {
                        this.c8a = true;
                    }
                    if (this.cxc) {
                        this.c13 = true;
                    }
                    if (!this.cxc) {
                        this.c13a = true;
                    }
                    if (this.bxd) {
                        this.c9 = true;
                    }
                    if (!this.bxd) {
                        this.c9a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 8;
                    this.board = 12;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 13;
                    this.board = 37;
                    this.started = true;
                }
            }
            if (n > 111 && n < 163 && n2 > 111 && n2 < 163 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.cxb) {
                        this.c12 = true;
                    }
                    if (!this.cxb) {
                        this.c12a = true;
                    }
                    if (this.bxc) {
                        this.c8 = true;
                    }
                    if (!this.bxc) {
                        this.c8a = true;
                    }
                    if (this.cxc) {
                        this.c13 = true;
                    }
                    if (!this.cxc) {
                        this.c13a = true;
                    }
                    if (this.dxc) {
                        this.c18 = true;
                    }
                    if (!this.dxc) {
                        this.c18a = true;
                    }
                    if (this.cxd) {
                        this.c14 = true;
                    }
                    if (!this.cxd) {
                        this.c14a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 8;
                    this.board = 13;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 13;
                    this.board = 38;
                    this.started = true;
                }
            }
            if (n > 165 && n < 217 && n2 > 111 && n2 < 163 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.dxb) {
                        this.c17 = true;
                    }
                    if (!this.dxb) {
                        this.c17a = true;
                    }
                    if (this.cxc) {
                        this.c13 = true;
                    }
                    if (!this.cxc) {
                        this.c13a = true;
                    }
                    if (this.dxc) {
                        this.c18 = true;
                    }
                    if (!this.dxc) {
                        this.c18a = true;
                    }
                    if (this.exc) {
                        this.c23 = true;
                    }
                    if (!this.exc) {
                        this.c23a = true;
                    }
                    if (this.dxd) {
                        this.c19 = true;
                    }
                    if (!this.dxd) {
                        this.c19a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 8;
                    this.board = 14;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 13;
                    this.board = 39;
                    this.started = true;
                }
            }
            if (n > 219 && n < 271 && n2 > 111 && n2 < 163 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.exb) {
                        this.c22 = true;
                    }
                    if (!this.exb) {
                        this.c22a = true;
                    }
                    if (this.dxc) {
                        this.c18 = true;
                    }
                    if (!this.dxc) {
                        this.c18a = true;
                    }
                    if (this.exc) {
                        this.c23 = true;
                    }
                    if (!this.exc) {
                        this.c23a = true;
                    }
                    if (this.exd) {
                        this.c24 = true;
                    }
                    if (!this.exd) {
                        this.c24a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 8;
                    this.board = 15;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 13;
                    this.board = 40;
                    this.started = true;
                }
            }
            if (n > 3 && n < 55 && n2 > 165 && n2 < 217 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.axc) {
                        this.c3 = true;
                    }
                    if (!this.axc) {
                        this.c3a = true;
                    }
                    if (this.axd) {
                        this.c4 = true;
                    }
                    if (!this.axd) {
                        this.c4a = true;
                    }
                    if (this.axe) {
                        this.c5 = true;
                    }
                    if (!this.axe) {
                        this.c5a = true;
                    }
                    if (this.bxd) {
                        this.c9 = true;
                    }
                    if (!this.bxd) {
                        this.c9a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 8;
                    this.board = 16;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 13;
                    this.board = 41;
                    this.started = true;
                }
            }
            if (n > 57 && n < 109 && n2 > 165 && n2 < 217 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.bxc) {
                        this.c8 = true;
                    }
                    if (!this.bxc) {
                        this.c8a = true;
                    }
                    if (this.axd) {
                        this.c4 = true;
                    }
                    if (!this.axd) {
                        this.c4a = true;
                    }
                    if (this.bxd) {
                        this.c9 = true;
                    }
                    if (!this.bxd) {
                        this.c9a = true;
                    }
                    if (this.cxd) {
                        this.c14 = true;
                    }
                    if (!this.cxd) {
                        this.c14a = true;
                    }
                    if (this.bxe) {
                        this.c10 = true;
                    }
                    if (!this.bxe) {
                        this.c10a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 9;
                    this.board = 17;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 14;
                    this.board = 42;
                    this.started = true;
                }
            }
            if (n > 111 && n < 163 && n2 > 165 && n2 < 217 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.cxc) {
                        this.c13 = true;
                    }
                    if (!this.cxc) {
                        this.c13a = true;
                    }
                    if (this.bxd) {
                        this.c9 = true;
                    }
                    if (!this.bxd) {
                        this.c9a = true;
                    }
                    if (this.cxd) {
                        this.c14 = true;
                    }
                    if (!this.cxd) {
                        this.c14a = true;
                    }
                    if (this.dxd) {
                        this.c19 = true;
                    }
                    if (!this.dxd) {
                        this.c19a = true;
                    }
                    if (this.cxe) {
                        this.c15 = true;
                    }
                    if (!this.cxe) {
                        this.c15a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 9;
                    this.board = 18;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 14;
                    this.board = 43;
                    this.started = true;
                }
            }
            if (n > 165 && n < 217 && n2 > 165 && n2 < 217 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.dxc) {
                        this.c18 = true;
                    }
                    if (!this.dxc) {
                        this.c18a = true;
                    }
                    if (this.cxd) {
                        this.c14 = true;
                    }
                    if (!this.cxd) {
                        this.c14a = true;
                    }
                    if (this.dxd) {
                        this.c19 = true;
                    }
                    if (!this.dxd) {
                        this.c19a = true;
                    }
                    if (this.exd) {
                        this.c24 = true;
                    }
                    if (!this.exd) {
                        this.c24a = true;
                    }
                    if (this.dxe) {
                        this.c20 = true;
                    }
                    if (!this.dxe) {
                        this.c20a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 9;
                    this.board = 19;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 14;
                    this.board = 44;
                    this.started = true;
                }
            }
            if (n > 219 && n < 271 && n2 > 165 && n2 < 217 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.exc) {
                        this.c23 = true;
                    }
                    if (!this.exc) {
                        this.c23a = true;
                    }
                    if (this.dxd) {
                        this.c19 = true;
                    }
                    if (!this.dxd) {
                        this.c19a = true;
                    }
                    if (this.exd) {
                        this.c24 = true;
                    }
                    if (!this.exd) {
                        this.c24a = true;
                    }
                    if (this.exe) {
                        this.c25 = true;
                    }
                    if (!this.exe) {
                        this.c25a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 9;
                    this.board = 20;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 14;
                    this.board = 45;
                    this.started = true;
                }
            }
            if (n > 3 && n < 55 && n2 > 219 && n2 < 271 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.axd) {
                        this.c4 = true;
                    }
                    if (!this.axd) {
                        this.c4a = true;
                    }
                    if (this.axe) {
                        this.c5 = true;
                    }
                    if (!this.axe) {
                        this.c5a = true;
                    }
                    if (this.bxe) {
                        this.c10 = true;
                    }
                    if (!this.bxe) {
                        this.c10a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 9;
                    this.board = 21;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 14;
                    this.board = 46;
                    this.started = true;
                }
            }
            if (n > 57 && n < 109 && n2 > 219 && n2 < 271 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.bxd) {
                        this.c9 = true;
                    }
                    if (!this.bxd) {
                        this.c9a = true;
                    }
                    if (this.axe) {
                        this.c5 = true;
                    }
                    if (!this.axe) {
                        this.c5a = true;
                    }
                    if (this.bxe) {
                        this.c10 = true;
                    }
                    if (!this.bxe) {
                        this.c10a = true;
                    }
                    if (this.cxe) {
                        this.c15 = true;
                    }
                    if (!this.cxe) {
                        this.c15a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 10;
                    this.board = 22;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 15;
                    this.board = 47;
                    this.started = true;
                }
            }
            if (n > 111 && n < 163 && n2 > 219 && n2 < 271 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.cxd) {
                        this.c14 = true;
                    }
                    if (!this.cxd) {
                        this.c14a = true;
                    }
                    if (this.bxe) {
                        this.c10 = true;
                    }
                    if (!this.bxe) {
                        this.c10a = true;
                    }
                    if (this.cxe) {
                        this.c15 = true;
                    }
                    if (!this.cxe) {
                        this.c15a = true;
                    }
                    if (this.dxe) {
                        this.c20 = true;
                    }
                    if (!this.dxe) {
                        this.c20a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 10;
                    this.board = 23;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25 = true;
                    this.move = 15;
                    this.board = 48;
                    this.started = true;
                }
            }
            if (n > 165 && n < 217 && n2 > 219 && n2 < 271 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.dxd) {
                        this.c19 = true;
                    }
                    if (!this.dxd) {
                        this.c19a = true;
                    }
                    if (this.cxe) {
                        this.c15 = true;
                    }
                    if (!this.cxe) {
                        this.c15a = true;
                    }
                    if (this.dxe) {
                        this.c20 = true;
                    }
                    if (!this.dxe) {
                        this.c20a = true;
                    }
                    if (this.exe) {
                        this.c25 = true;
                    }
                    if (!this.exe) {
                        this.c25a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 10;
                    this.board = 24;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 15;
                    this.board = 49;
                    this.started = true;
                }
            }
            if (n > 219 && n < 271 && n2 > 219 && n2 < 271 && !this.maxmove) {
                this.play(this.getCodeBase(), "bpsh.au");
                this.bhelp = false;
                if (this.board < 51) {
                    ++this.move;
                    if (this.exd) {
                        this.c24 = true;
                    }
                    if (!this.exd) {
                        this.c24a = true;
                    }
                    if (this.dxe) {
                        this.c20 = true;
                    }
                    if (!this.dxe) {
                        this.c20a = true;
                    }
                    if (this.exe) {
                        this.c25 = true;
                    }
                    if (!this.exe) {
                        this.c25a = true;
                    }
                }
                if (this.board == 52) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.move = 10;
                    this.board = 25;
                    this.started = true;
                }
                if (this.board == 54) {
                    this.play(this.getCodeBase(), "brdstrt.au");
                    this.c25a = true;
                    this.move = 15;
                    this.board = 50;
                    this.started = true;
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (n > 0 && n < 274 && n2 > 0 && n2 < 600) {
            this.showStatus("Lightz Out by Donnie Burgess (aka Shadowtwin)");
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.move != 0 && this.move != 2) {
            graphics.setColor(Color.black);
            graphics.fillRect(5, 375, 264, 20);
        }
        if (this.justwon == 6 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 2) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 1 Perfect!", 65, 391);
            }
            if (this.board == 3) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 2 Perfect!", 65, 391);
            }
            if (this.board == 4) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 3 Perfect!", 65, 391);
            }
            if (this.board == 5) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 4 Perfect!", 65, 391);
            }
            if (this.board == 6) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 5 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 7 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 7) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 6 Perfect!", 65, 391);
            }
            if (this.board == 8) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 7 Perfect!", 65, 391);
            }
            if (this.board == 9) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 8 Perfect!", 65, 391);
            }
            if (this.board == 10) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 9 Perfect!", 65, 391);
            }
            if (this.board == 11) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 10 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 8 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 12) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 11 Perfect!", 65, 391);
            }
            if (this.board == 13) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 12 Perfect!", 65, 391);
            }
            if (this.board == 14) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 13 Perfect!", 65, 391);
            }
            if (this.board == 15) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 14 Perfect!", 65, 391);
            }
            if (this.board == 16) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 15 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 9 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 17) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 16 Perfect!", 65, 391);
            }
            if (this.board == 18) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 17 Perfect!", 65, 391);
            }
            if (this.board == 19) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 18 Perfect!", 65, 391);
            }
            if (this.board == 20) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 19 Perfect!", 65, 391);
            }
            if (this.board == 21) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 20 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 10 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 22) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 21 Perfect!", 65, 391);
            }
            if (this.board == 23) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 22 Perfect!", 65, 391);
            }
            if (this.board == 24) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 23 Perfect!", 65, 391);
            }
            if (this.board == 25) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 24 Perfect!", 65, 391);
            }
            if (this.board == 26) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 25 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 11 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 27) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 26 Perfect!", 65, 391);
            }
            if (this.board == 28) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 27 Perfect!", 65, 391);
            }
            if (this.board == 29) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 28 Perfect!", 65, 391);
            }
            if (this.board == 30) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 29 Perfect!", 65, 391);
            }
            if (this.board == 31) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 30 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 12 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 32) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 31 Perfect!", 65, 391);
            }
            if (this.board == 33) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 32 Perfect!", 65, 391);
            }
            if (this.board == 34) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 33 Perfect!", 65, 391);
            }
            if (this.board == 35) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 34 Perfect!", 65, 391);
            }
            if (this.board == 36) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 35 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 13 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 37) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 36 Perfect!", 65, 391);
            }
            if (this.board == 38) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 37 Perfect!", 65, 391);
            }
            if (this.board == 39) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 38 Perfect!", 65, 391);
            }
            if (this.board == 40) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 39 Perfect!", 65, 391);
            }
            if (this.board == 41) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 40 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 14 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 42) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 41 Perfect!", 65, 391);
            }
            if (this.board == 43) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 42 Perfect!", 65, 391);
            }
            if (this.board == 44) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 43 Perfect!", 65, 391);
            }
            if (this.board == 45) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 44 Perfect!", 65, 391);
            }
            if (this.board == 46) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 45 Perfect!", 65, 391);
            }
        }
        if (this.justwon == 15 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 47) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 46 Perfect!", 65, 391);
            }
            if (this.board == 48) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 47 Perfect!", 65, 391);
            }
            if (this.board == 49) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 48 Perfect!", 65, 391);
            }
            if (this.board == 50) {
                graphics.setColor(Color.red);
                graphics.drawString("Board 49 Perfect!", 65, 391);
            }
        }
        if (this.justwon > 6 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 2) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 6) + " moves over on board 1", 35, 391);
            }
            if (this.board == 3) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 6) + " moves over on board 2", 35, 391);
            }
            if (this.board == 4) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 6) + " moves over on board 3", 35, 391);
            }
            if (this.board == 5) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 6) + " moves over on board 4", 35, 391);
            }
            if (this.board == 6) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 6) + " moves over on board 5", 35, 391);
            }
        }
        if (this.justwon > 7 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 7) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 7) + " moves over on board 6", 35, 391);
            }
            if (this.board == 8) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 7) + " moves over on board 7", 35, 391);
            }
            if (this.board == 9) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 7) + " moves over on board 8", 35, 391);
            }
            if (this.board == 10) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 7) + " moves over on board 9", 35, 391);
            }
            if (this.board == 11) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 7) + " moves over on board 10", 35, 391);
            }
        }
        if (this.justwon > 8 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 12) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 8) + " moves over on board 11", 35, 391);
            }
            if (this.board == 13) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 8) + " moves over on board 12", 35, 391);
            }
            if (this.board == 14) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 8) + " moves over on board 13", 35, 391);
            }
            if (this.board == 15) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 8) + " moves over on board 14", 35, 391);
            }
            if (this.board == 16) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 8) + " moves over on board 15", 35, 391);
            }
        }
        if (this.justwon > 9 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 17) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 9) + " moves over on board 16", 35, 391);
            }
            if (this.board == 18) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 9) + " moves over on board 17", 35, 391);
            }
            if (this.board == 19) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 9) + " moves over on board 18", 35, 391);
            }
            if (this.board == 20) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 9) + " moves over on board 19", 35, 391);
            }
            if (this.board == 21) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 9) + " moves over on board 20", 35, 391);
            }
        }
        if (this.justwon > 10 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 22) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 10) + " moves over on board 21", 35, 391);
            }
            if (this.board == 23) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 10) + " moves over on board 22", 35, 391);
            }
            if (this.board == 24) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 10) + " moves over on board 23", 35, 391);
            }
            if (this.board == 25) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 10) + " moves over on board 24", 35, 391);
            }
            if (this.board == 26) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 10) + " moves over on board 25", 35, 391);
            }
        }
        if (this.justwon > 11 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 27) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 11) + " moves over on board 26", 35, 391);
            }
            if (this.board == 28) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 11) + " moves over on board 27", 35, 391);
            }
            if (this.board == 29) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 11) + " moves over on board 28", 35, 391);
            }
            if (this.board == 30) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 11) + " moves over on board 29", 35, 391);
            }
            if (this.board == 31) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 11) + " moves over on board 30", 35, 391);
            }
        }
        if (this.justwon > 12 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 32) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 12) + " moves over on board 31", 35, 391);
            }
            if (this.board == 33) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 12) + " moves over on board 32", 35, 391);
            }
            if (this.board == 34) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 12) + " moves over on board 33", 35, 391);
            }
            if (this.board == 35) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 12) + " moves over on board 34", 35, 391);
            }
            if (this.board == 36) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 12) + " moves over on board 35", 35, 391);
            }
        }
        if (this.justwon > 13 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 37) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 13) + " moves over on board 36", 35, 391);
            }
            if (this.board == 38) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 13) + " moves over on board 37", 35, 391);
            }
            if (this.board == 39) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 13) + " moves over on board 38", 35, 391);
            }
            if (this.board == 40) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 13) + " moves over on board 39", 35, 391);
            }
            if (this.board == 41) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 13) + " moves over on board 40", 35, 391);
            }
        }
        if (this.justwon > 14 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 42) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 14) + " moves over on board 41", 35, 391);
            }
            if (this.board == 43) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 14) + " moves over on board 42", 35, 391);
            }
            if (this.board == 44) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 14) + " moves over on board 43", 35, 391);
            }
            if (this.board == 45) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 14) + " moves over on board 44", 35, 391);
            }
            if (this.board == 46) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 14) + " moves over on board 45", 35, 391);
            }
        }
        if (this.justwon > 15 && this.move == 0) {
            graphics.setFont(this.f);
            if (this.board == 47) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 15) + " moves over on board 46", 35, 391);
            }
            if (this.board == 48) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 15) + " moves over on board 47", 35, 391);
            }
            if (this.board == 49) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 15) + " moves over on board 48", 35, 391);
            }
            if (this.board == 50) {
                graphics.setColor(Color.red);
                graphics.drawString(String.valueOf(this.justwon - 15) + " moves over on board 49", 35, 391);
            }
        }
        if (this.justwon > 6 && this.move == 2 && this.board > 1) {
            graphics.setFont(this.f);
            graphics.setColor(Color.red);
            graphics.drawString(String.valueOf(this.gameover) + " moves over for game", 35, 391);
        }
        if (this.board > 0 && this.board < 6) {
            this.mvrmn = 16 - this.move;
        }
        if (this.board > 5 && this.board < 11) {
            this.mvrmn = 17 - this.move;
        }
        if (this.board > 10 && this.board < 16) {
            this.mvrmn = 18 - this.move;
        }
        if (this.board > 15 && this.board < 21) {
            this.mvrmn = 19 - this.move;
        }
        if (this.board > 20 && this.board < 26) {
            this.mvrmn = 20 - this.move;
        }
        if (this.board > 25 && this.board < 31) {
            this.mvrmn = 21 - this.move;
        }
        if (this.board > 30 && this.board < 36) {
            this.mvrmn = 22 - this.move;
        }
        if (this.board > 35 && this.board < 41) {
            this.mvrmn = 23 - this.move;
        }
        if (this.board > 40 && this.board < 46) {
            this.mvrmn = 24 - this.move;
        }
        if (this.board > 45 && this.board < 51) {
            this.mvrmn = 25 - this.move;
        }
        if (this.move == 0) {
            if (this.board > 0 && this.board < 6) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in six moves, sixteen is the maximum.");
            }
            if (this.board > 5 && this.board < 11) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in seven moves, seventeen is the maximum.");
            }
            if (this.board > 10 && this.board < 16) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in eight moves, eighteen is the maximum.");
            }
            if (this.board > 15 && this.board < 21) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in nine moves, nineteen is the maximum.");
            }
            if (this.board > 20 && this.board < 26) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in ten moves, twenty is the maximum.");
            }
            if (this.board > 25 && this.board < 31) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in eleven moves, twenty-one is the maximum.");
            }
            if (this.board > 30 && this.board < 36) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in twelve moves, twenty-two is the maximum.");
            }
            if (this.board > 35 && this.board < 41) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in thirteen moves, twenty-three is the maximum.");
            }
            if (this.board > 40 && this.board < 46) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in fourteen moves, twenty-four is the maximum.");
            }
            if (this.board > 45 && this.board < 51) {
                this.showStatus("You are on board # " + this.board + "     This board can be solved in fifteen moves, twenty-five is the maximum.");
            }
        }
        else {
            if (this.mvrmn == 0) {
                this.showStatus("Too many moves, click anywhere to reset.");
            }
            if (this.mvrmn == 1) {
                this.showStatus("This is your final move.");
            }
            if (this.mvrmn > 1) {
                this.showStatus("You have " + this.mvrmn + " moves remaining.");
            }
        }
        graphics.setColor(Color.blue);
        graphics.setFont(this.db1);
        graphics.drawString("Applet by Donnie Burgess", 52, 353);
        graphics.drawString("email: DonnieBurgess@hotmail.com", 36, 363);
        graphics.drawString("www.geocities.com/donnieburgess", 38, 373);
        graphics.setFont(this.db);
        graphics.drawString("LIGHTZ OUT", 30, 300);
        graphics.setColor(Color.gray);
        graphics.drawRect(1, 1, 271, 271);
        graphics.drawRect(210, 275, 60, 30);
        graphics.drawRect(210, 309, 60, 30);
        graphics.drawRect(210, 343, 60, 30);
        graphics.drawRect(45, 309, 125, 30);
        graphics.setColor(Color.black);
        graphics.setFont(this.f);
        graphics.drawString("Help", 220, 330);
        graphics.drawString("Start", 219, 296);
        graphics.drawString("Reset", 217, 364);
        graphics.drawString("Select Board", 53, 330);
        if (this.board != 0) {
            graphics.drawString("Help", 220, 330);
            graphics.drawString("Start", 219, 296);
            graphics.drawString("Reset", 217, 364);
        }
        if (this.board == 51) {
            this.axa = false;
            this.axb = false;
            this.axc = false;
            this.axd = false;
            this.axe = false;
            this.bxa = false;
            this.bxb = false;
            this.bxc = false;
            this.bxd = false;
            this.bxe = false;
            this.cxa = false;
            this.cxb = false;
            this.cxc = false;
            this.cxd = false;
            this.cxe = false;
            this.dxa = false;
            this.dxb = false;
            this.dxc = false;
            this.dxd = false;
            this.dxe = false;
            this.exa = false;
            this.exb = false;
            this.exc = false;
            this.exd = false;
            this.exe = false;
            this.board = 52;
        }
        if (this.board == 53) {
            this.axa = false;
            this.axb = false;
            this.axc = false;
            this.axd = false;
            this.axe = false;
            this.bxa = false;
            this.bxb = false;
            this.bxc = false;
            this.bxd = false;
            this.bxe = false;
            this.cxa = false;
            this.cxb = false;
            this.cxc = false;
            this.cxd = false;
            this.cxe = false;
            this.dxa = false;
            this.dxb = false;
            this.dxc = false;
            this.dxd = false;
            this.dxe = false;
            this.exa = false;
            this.exb = false;
            this.exc = false;
            this.exd = false;
            this.exe = true;
            this.board = 54;
        }
        if (this.board == 0) {
            this.bxc = true;
            this.cxc = true;
            this.dxc = true;
            this.cxb = true;
            this.cxd = true;
        }
        if (this.board == 1 && this.started) {
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 1;
            this.move = 0;
            this.axc = true;
            this.cxc = true;
            this.exc = true;
            this.bxc = false;
            this.dxc = false;
            this.cxb = false;
            this.cxb = false;
            this.cxd = false;
        }
        if (this.board == 2 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 6;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 2;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axd = true;
            this.axe = true;
            this.cxa = true;
            this.cxb = true;
            this.cxd = true;
            this.cxe = true;
            this.exa = true;
            this.exb = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 3 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 6;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 3;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.bxa = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
            this.dxa = true;
            this.dxb = true;
            this.dxc = true;
            this.dxd = true;
            this.dxe = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
        }
        if (this.board == 4 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 6;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 4;
            this.move = 0;
            this.axb = true;
            this.bxb = true;
            this.dxb = true;
            this.exb = true;
            this.axd = true;
            this.exd = true;
            this.axe = true;
            this.bxe = true;
            this.dxe = true;
            this.exe = true;
        }
        if (this.board == 5 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 6;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 5;
            this.move = 0;
            this.axa = true;
            this.bxa = true;
            this.cxa = true;
            this.dxa = true;
            this.axb = true;
            this.bxb = true;
            this.cxb = true;
            this.exb = true;
            this.axc = true;
            this.bxc = true;
            this.cxc = true;
            this.exc = true;
            this.dxd = true;
            this.exd = true;
            this.axe = true;
            this.bxe = true;
            this.dxe = true;
            this.exe = true;
        }
        if (this.board == 6 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 6;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 6;
            this.move = 0;
            this.axc = true;
            this.axd = true;
            this.bxe = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxe = true;
            this.exc = true;
            this.exd = true;
        }
        if (this.board == 7 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 7;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 7;
            this.move = 0;
            this.axa = true;
            this.bxa = true;
            this.cxa = true;
            this.dxa = true;
            this.axb = true;
            this.exb = true;
            this.axc = true;
            this.exc = true;
            this.axd = true;
            this.exd = true;
            this.axe = true;
            this.bxe = true;
            this.cxe = true;
            this.dxe = true;
        }
        if (this.board == 8 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 7;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 8;
            this.move = 0;
            this.cxb = true;
            this.bxc = true;
            this.dxc = true;
            this.axd = true;
            this.cxd = true;
            this.exd = true;
            this.bxe = true;
            this.dxe = true;
        }
        if (this.board == 9 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 7;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 9;
            this.move = 0;
            this.bxa = true;
            this.dxa = true;
            this.axb = true;
            this.bxb = true;
            this.cxb = true;
            this.dxb = true;
            this.exb = true;
            this.bxc = true;
            this.cxc = true;
            this.dxc = true;
            this.bxd = true;
            this.dxd = true;
            this.exd = true;
            this.axe = true;
            this.bxe = true;
            this.cxe = true;
        }
        if (this.board == 10 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 7;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 10;
            this.move = 0;
            this.bxa = true;
            this.cxa = true;
            this.dxa = true;
            this.bxb = true;
            this.cxb = true;
            this.dxb = true;
            this.bxc = true;
            this.cxc = true;
            this.dxc = true;
        }
        if (this.board == 11 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 7;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 11;
            this.move = 0;
            this.axa = true;
            this.cxa = true;
            this.exa = true;
            this.axb = true;
            this.cxb = true;
            this.exb = true;
            this.axc = true;
            this.cxc = true;
            this.exc = true;
            this.axd = true;
            this.cxd = true;
            this.exd = true;
            this.bxe = true;
            this.cxe = true;
            this.dxe = true;
        }
        if (this.board == 12 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 8;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 12;
            this.move = 0;
            this.axa = true;
            this.bxa = true;
            this.cxa = true;
            this.dxa = true;
            this.exa = true;
            this.bxb = true;
            this.dxb = true;
            this.axc = true;
            this.bxc = true;
            this.dxc = true;
            this.exc = true;
            this.bxd = true;
            this.cxd = true;
            this.dxd = true;
            this.bxe = true;
            this.dxe = true;
        }
        if (this.board == 13 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 8;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 13;
            this.move = 0;
            this.dxa = true;
            this.cxb = true;
            this.exb = true;
            this.bxc = true;
            this.dxc = true;
            this.axd = true;
            this.cxd = true;
            this.bxe = true;
        }
        if (this.board == 14 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 8;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 14;
            this.move = 0;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
        }
        if (this.board == 15 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 8;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 15;
            this.move = 0;
            this.bxb = true;
            this.bxd = true;
        }
        if (this.board == 16 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 8;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 16;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxe = true;
            this.cxe = true;
            this.dxe = true;
            this.exe = true;
        }
        if (this.board == 17 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 9;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 17;
            this.move = 0;
            this.cxc = true;
            this.bxd = true;
            this.cxd = true;
            this.dxd = true;
            this.axe = true;
            this.bxe = true;
            this.cxe = true;
            this.dxe = true;
            this.exe = true;
        }
        if (this.board == 18 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 9;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 18;
            this.move = 0;
            this.cxa = true;
            this.bxb = true;
            this.dxb = true;
            this.axc = true;
            this.cxc = true;
            this.exc = true;
            this.bxd = true;
            this.dxd = true;
            this.cxe = true;
        }
        if (this.board == 19 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 9;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 19;
            this.move = 0;
            this.axa = true;
            this.cxa = true;
            this.exa = true;
            this.axc = true;
            this.cxc = true;
            this.exc = true;
            this.axe = true;
            this.cxe = true;
            this.exe = true;
        }
        if (this.board == 20 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 9;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 20;
            this.move = 0;
            this.axc = true;
            this.exc = true;
        }
        if (this.board == 21 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 9;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 21;
            this.move = 0;
            this.bxa = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
            this.cxa = true;
            this.cxc = true;
            this.dxa = true;
            this.dxc = true;
            this.exa = true;
        }
        if (this.board == 22 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 10;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 22;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.bxa = true;
            this.bxe = true;
            this.cxa = true;
            this.cxe = true;
            this.dxa = true;
            this.dxe = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
        }
        if (this.board == 23 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 10;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 23;
            this.move = 0;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxc = true;
            this.dxd = true;
            this.exc = true;
        }
        if (this.board == 24 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 10;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 24;
            this.move = 0;
            this.axc = true;
            this.axd = true;
            this.bxd = true;
            this.bxe = true;
            this.cxd = true;
            this.dxd = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 25 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 10;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 25;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxd = true;
            this.dxe = true;
            this.exe = true;
        }
        if (this.board == 26 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 10;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 26;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxc = true;
            this.cxc = true;
            this.dxc = true;
            this.exa = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 27 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 11;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 27;
            this.move = 0;
            this.bxb = true;
            this.cxa = true;
            this.cxb = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxb = true;
        }
        if (this.board == 28 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 11;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 28;
            this.move = 0;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxc = true;
            this.dxd = true;
            this.dxe = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 29 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 11;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 29;
            this.move = 0;
            this.bxb = true;
        }
        if (this.board == 30 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 11;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 30;
            this.move = 0;
            this.cxc = true;
        }
        if (this.board == 31 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 11;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 31;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxb = true;
            this.cxc = true;
            this.dxd = true;
            this.exa = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 32 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 12;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 32;
            this.move = 0;
            this.axa = true;
            this.axe = true;
            this.bxa = true;
            this.bxd = true;
            this.bxe = true;
            this.cxa = true;
            this.cxc = true;
            this.cxe = true;
            this.dxa = true;
            this.dxb = true;
            this.dxe = true;
            this.exa = true;
            this.exe = true;
        }
        if (this.board == 33 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 12;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 33;
            this.move = 0;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.cxc = true;
            this.dxa = true;
            this.dxb = true;
            this.dxe = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 34 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 12;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 34;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.bxd = true;
            this.bxe = true;
            this.cxa = true;
            this.cxd = true;
            this.cxe = true;
            this.dxe = true;
            this.exa = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 35 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 12;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 35;
            this.move = 0;
            this.axc = true;
            this.axd = true;
            this.bxb = true;
            this.cxd = true;
            this.dxa = true;
            this.dxb = true;
            this.exa = true;
            this.exc = true;
            this.exd = true;
        }
        if (this.board == 36 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 12;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 36;
            this.move = 0;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxb = true;
            this.bxd = true;
            this.cxa = true;
            this.cxd = true;
            this.dxb = true;
            this.dxd = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 37 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 13;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 37;
            this.move = 0;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.cxb = true;
            this.cxc = true;
            this.cxd = true;
            this.dxb = true;
            this.dxc = true;
            this.dxd = true;
        }
        if (this.board == 38 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 13;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 38;
            this.move = 0;
            this.axa = true;
            this.axc = true;
            this.axe = true;
            this.bxb = true;
            this.bxd = true;
            this.cxa = true;
            this.cxc = true;
            this.cxe = true;
            this.dxb = true;
            this.dxd = true;
            this.exa = true;
            this.exc = true;
            this.exe = true;
        }
        if (this.board == 39 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 13;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 39;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.bxa = true;
            this.bxc = true;
            this.bxe = true;
            this.cxd = true;
            this.dxa = true;
            this.dxd = true;
            this.dxe = true;
        }
        if (this.board == 40 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 13;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 40;
            this.move = 0;
            this.bxc = true;
            this.dxc = true;
        }
        if (this.board == 41 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 13;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 41;
            this.move = 0;
            this.axa = true;
            this.bxb = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxb = true;
            this.exa = true;
        }
        if (this.board == 42 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 14;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 42;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxa = true;
            this.bxc = true;
            this.bxe = true;
            this.cxa = true;
            this.cxc = true;
            this.cxe = true;
            this.dxb = true;
            this.dxd = true;
        }
        if (this.board == 43 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 14;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 43;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
            this.cxc = true;
            this.cxe = true;
            this.dxb = true;
            this.dxe = true;
            this.exa = true;
        }
        if (this.board == 44 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 14;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 44;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.bxb = true;
            this.bxc = true;
            this.bxe = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxb = true;
            this.dxc = true;
            this.dxe = true;
            this.exb = true;
            this.exc = true;
        }
        if (this.board == 45 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 14;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 45;
            this.move = 0;
            this.axb = true;
            this.axd = true;
            this.axe = true;
            this.bxa = true;
            this.bxd = true;
            this.dxa = true;
            this.cxa = true;
            this.cxb = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxa = true;
            this.dxc = true;
            this.dxd = true;
            this.exc = true;
            this.exe = true;
        }
        if (this.board == 46 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 14;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 46;
            this.move = 0;
            this.axc = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.cxa = true;
            this.cxb = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxb = true;
            this.dxc = true;
            this.dxd = true;
            this.exc = true;
        }
        if (this.board == 47 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 15;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 47;
            this.move = 0;
            this.axb = true;
            this.axc = true;
            this.bxb = true;
            this.bxd = true;
            this.cxa = true;
            this.cxb = true;
            this.cxc = true;
            this.dxb = true;
            this.exb = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.board == 48 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 15;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 48;
            this.move = 0;
            this.axb = true;
            this.axd = true;
            this.cxc = true;
            this.exb = true;
            this.exd = true;
        }
        if (this.board == 49 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 15;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 49;
            this.move = 0;
            this.axa = true;
            this.axe = true;
            this.bxb = true;
            this.bxd = true;
            this.cxc = true;
            this.dxb = true;
            this.dxd = true;
            this.exa = true;
            this.exe = true;
        }
        if (this.board == 50 && this.started) {
            this.justwon = this.move;
            this.boardover = this.justwon - 15;
            this.gameover += this.boardover;
            this.started = false;
            this.uwin = false;
            this.help = 0;
            this.bhelp = true;
            this.cboard = 50;
            this.move = 0;
            this.axa = true;
            this.axb = true;
            this.axc = true;
            this.axd = true;
            this.axe = true;
            this.bxa = true;
            this.bxb = true;
            this.bxc = true;
            this.bxd = true;
            this.bxe = true;
            this.cxa = true;
            this.cxb = true;
            this.cxc = true;
            this.cxd = true;
            this.cxe = true;
            this.dxa = true;
            this.dxb = true;
            this.dxc = true;
            this.dxd = true;
            this.dxe = true;
            this.exa = true;
            this.exb = true;
            this.exc = true;
            this.exd = true;
            this.exe = true;
        }
        if (this.c1) {
            this.axa = false;
            this.c1 = false;
        }
        if (this.c1a) {
            this.axa = true;
            this.c1a = false;
        }
        if (this.c2) {
            this.axb = false;
            this.c2 = false;
        }
        if (this.c2a) {
            this.axb = true;
            this.c2a = false;
        }
        if (this.c3) {
            this.axc = false;
            this.c3 = false;
        }
        if (this.c3a) {
            this.axc = true;
            this.c3a = false;
        }
        if (this.c4) {
            this.axd = false;
            this.c4 = false;
        }
        if (this.c4a) {
            this.axd = true;
            this.c4a = false;
        }
        if (this.c5) {
            this.axe = false;
            this.c5 = false;
        }
        if (this.c5a) {
            this.axe = true;
            this.c5a = false;
        }
        if (this.c6) {
            this.bxa = false;
            this.c6 = false;
        }
        if (this.c6a) {
            this.bxa = true;
            this.c6a = false;
        }
        if (this.c7) {
            this.bxb = false;
            this.c7 = false;
        }
        if (this.c7a) {
            this.bxb = true;
            this.c7a = false;
        }
        if (this.c8) {
            this.bxc = false;
            this.c8 = false;
        }
        if (this.c8a) {
            this.bxc = true;
            this.c8a = false;
        }
        if (this.c9) {
            this.bxd = false;
            this.c9 = false;
        }
        if (this.c9a) {
            this.bxd = true;
            this.c9a = false;
        }
        if (this.c10) {
            this.bxe = false;
            this.c10 = false;
        }
        if (this.c10a) {
            this.bxe = true;
            this.c10a = false;
        }
        if (this.c11) {
            this.cxa = false;
            this.c11 = false;
        }
        if (this.c11a) {
            this.cxa = true;
            this.c11a = false;
        }
        if (this.c12) {
            this.cxb = false;
            this.c12 = false;
        }
        if (this.c12a) {
            this.cxb = true;
            this.c12a = false;
        }
        if (this.c13) {
            this.cxc = false;
            this.c13 = false;
        }
        if (this.c13a) {
            this.cxc = true;
            this.c13a = false;
        }
        if (this.c14) {
            this.cxd = false;
            this.c14 = false;
        }
        if (this.c14a) {
            this.cxd = true;
            this.c14a = false;
        }
        if (this.c15) {
            this.cxe = false;
            this.c15 = false;
        }
        if (this.c15a) {
            this.cxe = true;
            this.c15a = false;
        }
        if (this.c16) {
            this.dxa = false;
            this.c16 = false;
        }
        if (this.c16a) {
            this.dxa = true;
            this.c16a = false;
        }
        if (this.c17) {
            this.dxb = false;
            this.c17 = false;
        }
        if (this.c17a) {
            this.dxb = true;
            this.c17a = false;
        }
        if (this.c18) {
            this.dxc = false;
            this.c18 = false;
        }
        if (this.c18a) {
            this.dxc = true;
            this.c18a = false;
        }
        if (this.c19) {
            this.dxd = false;
            this.c19 = false;
        }
        if (this.c19a) {
            this.dxd = true;
            this.c19a = false;
        }
        if (this.c20) {
            this.dxe = false;
            this.c20 = false;
        }
        if (this.c20a) {
            this.dxe = true;
            this.c20a = false;
        }
        if (this.c21) {
            this.exa = false;
            this.c21 = false;
        }
        if (this.c21a) {
            this.exa = true;
            this.c21a = false;
        }
        if (this.c22) {
            this.exb = false;
            this.c22 = false;
        }
        if (this.c22a) {
            this.exb = true;
            this.c22a = false;
        }
        if (this.c23) {
            this.exc = false;
            this.c23 = false;
        }
        if (this.c23a) {
            this.exc = true;
            this.c23a = false;
        }
        if (this.c24) {
            this.exd = false;
            this.c24 = false;
        }
        if (this.c24a) {
            this.exd = true;
            this.c24a = false;
        }
        if (this.c25) {
            this.exe = false;
            this.c25 = false;
        }
        if (this.c25a) {
            this.exe = true;
            this.c25a = false;
        }
        if (this.axa) {
            graphics.setColor(Color.red);
            graphics.fillRect(4, 4, 50, 50);
        }
        if (!this.axa) {
            graphics.setColor(Color.gray);
            graphics.fillRect(4, 4, 50, 50);
        }
        if (this.axb) {
            graphics.setColor(Color.red);
            graphics.fillRect(4, 58, 50, 50);
        }
        if (!this.axb) {
            graphics.setColor(Color.gray);
            graphics.fillRect(4, 58, 50, 50);
        }
        if (this.axc) {
            graphics.setColor(Color.red);
            graphics.fillRect(4, 112, 50, 50);
        }
        if (!this.axc) {
            graphics.setColor(Color.gray);
            graphics.fillRect(4, 112, 50, 50);
        }
        if (this.axd) {
            graphics.setColor(Color.red);
            graphics.fillRect(4, 166, 50, 50);
        }
        if (!this.axd) {
            graphics.setColor(Color.gray);
            graphics.fillRect(4, 166, 50, 50);
        }
        if (this.axe) {
            graphics.setColor(Color.red);
            graphics.fillRect(4, 220, 50, 50);
        }
        if (!this.axe) {
            graphics.setColor(Color.gray);
            graphics.fillRect(4, 220, 50, 50);
        }
        if (this.bxa) {
            graphics.setColor(Color.red);
            graphics.fillRect(58, 4, 50, 50);
        }
        if (!this.bxa) {
            graphics.setColor(Color.gray);
            graphics.fillRect(58, 4, 50, 50);
        }
        if (this.bxb) {
            graphics.setColor(Color.red);
            graphics.fillRect(58, 58, 50, 50);
        }
        if (!this.bxb) {
            graphics.setColor(Color.gray);
            graphics.fillRect(58, 58, 50, 50);
        }
        if (this.bxc) {
            graphics.setColor(Color.red);
            graphics.fillRect(58, 112, 50, 50);
        }
        if (!this.bxc) {
            graphics.setColor(Color.gray);
            graphics.fillRect(58, 112, 50, 50);
        }
        if (this.bxd) {
            graphics.setColor(Color.red);
            graphics.fillRect(58, 166, 50, 50);
        }
        if (!this.bxd) {
            graphics.setColor(Color.gray);
            graphics.fillRect(58, 166, 50, 50);
        }
        if (this.bxe) {
            graphics.setColor(Color.red);
            graphics.fillRect(58, 220, 50, 50);
        }
        if (!this.bxe) {
            graphics.setColor(Color.gray);
            graphics.fillRect(58, 220, 50, 50);
        }
        if (this.cxa) {
            graphics.setColor(Color.red);
            graphics.fillRect(112, 4, 50, 50);
        }
        if (!this.cxa) {
            graphics.setColor(Color.gray);
            graphics.fillRect(112, 4, 50, 50);
        }
        if (this.cxb) {
            graphics.setColor(Color.red);
            graphics.fillRect(112, 58, 50, 50);
        }
        if (!this.cxb) {
            graphics.setColor(Color.gray);
            graphics.fillRect(112, 58, 50, 50);
        }
        if (this.cxc) {
            graphics.setColor(Color.red);
            graphics.fillRect(112, 112, 50, 50);
        }
        if (!this.cxc) {
            graphics.setColor(Color.gray);
            graphics.fillRect(112, 112, 50, 50);
        }
        if (this.cxd) {
            graphics.setColor(Color.red);
            graphics.fillRect(112, 166, 50, 50);
        }
        if (!this.cxd) {
            graphics.setColor(Color.gray);
            graphics.fillRect(112, 166, 50, 50);
        }
        if (this.cxe) {
            graphics.setColor(Color.red);
            graphics.fillRect(112, 220, 50, 50);
        }
        if (!this.cxe) {
            graphics.setColor(Color.gray);
            graphics.fillRect(112, 220, 50, 50);
        }
        if (this.dxa) {
            graphics.setColor(Color.red);
            graphics.fillRect(166, 4, 50, 50);
        }
        if (!this.dxa) {
            graphics.setColor(Color.gray);
            graphics.fillRect(166, 4, 50, 50);
        }
        if (this.dxb) {
            graphics.setColor(Color.red);
            graphics.fillRect(166, 58, 50, 50);
        }
        if (!this.dxb) {
            graphics.setColor(Color.gray);
            graphics.fillRect(166, 58, 50, 50);
        }
        if (this.dxc) {
            graphics.setColor(Color.red);
            graphics.fillRect(166, 112, 50, 50);
        }
        if (!this.dxc) {
            graphics.setColor(Color.gray);
            graphics.fillRect(166, 112, 50, 50);
        }
        if (this.dxd) {
            graphics.setColor(Color.red);
            graphics.fillRect(166, 166, 50, 50);
        }
        if (!this.dxd) {
            graphics.setColor(Color.gray);
            graphics.fillRect(166, 166, 50, 50);
        }
        if (this.dxe) {
            graphics.setColor(Color.red);
            graphics.fillRect(166, 220, 50, 50);
        }
        if (!this.dxe) {
            graphics.setColor(Color.gray);
            graphics.fillRect(166, 220, 50, 50);
        }
        if (this.exa) {
            graphics.setColor(Color.red);
            graphics.fillRect(220, 4, 50, 50);
        }
        if (!this.exa) {
            graphics.setColor(Color.gray);
            graphics.fillRect(220, 4, 50, 50);
        }
        if (this.exb) {
            graphics.setColor(Color.red);
            graphics.fillRect(220, 58, 50, 50);
        }
        if (!this.exb) {
            graphics.setColor(Color.gray);
            graphics.fillRect(220, 58, 50, 50);
        }
        if (this.exc) {
            graphics.setColor(Color.red);
            graphics.fillRect(220, 112, 50, 50);
        }
        if (!this.exc) {
            graphics.setColor(Color.gray);
            graphics.fillRect(220, 112, 50, 50);
        }
        if (this.exd) {
            graphics.setColor(Color.red);
            graphics.fillRect(220, 166, 50, 50);
        }
        if (!this.exd) {
            graphics.setColor(Color.gray);
            graphics.fillRect(220, 166, 50, 50);
        }
        if (this.exe) {
            graphics.setColor(Color.red);
            graphics.fillRect(220, 220, 50, 50);
        }
        if (!this.exe) {
            graphics.setColor(Color.gray);
            graphics.fillRect(220, 220, 50, 50);
        }
        if (!this.axa && !this.axb && !this.axc && !this.axd && !this.axe && !this.bxa && !this.bxb && !this.bxc && !this.bxd && !this.bxe && !this.cxa && !this.cxb && !this.cxc && !this.cxd && !this.cxe && !this.dxa && !this.dxb && !this.dxc && !this.dxd && !this.dxe && !this.exa && !this.exb && !this.exc && !this.exd && !this.exe && this.board < 51) {
            this.uwin = true;
        }
        if (this.uwin && this.board < 50 && this.board > 0 && !this.maxmove) {
            this.play(this.getCodeBase(), "brdstrt.au");
            this.started = true;
            ++this.board;
            this.repaint();
        }
        if (this.uwin && this.board == 50) {
            this.play(this.getCodeBase(), "brdstrt.au");
            this.started = true;
            this.board = 0;
            this.repaint();
        }
        if (this.reset) {
            this.play(this.getCodeBase(), "bpsh.au");
            if (this.cboard == 1) {
                this.board = 1;
                this.started = true;
                this.reset = false;
            }
            if (this.cboard == 2) {
                this.board = 2;
                this.started = true;
                this.reset = false;
                this.gameover += 6;
            }
            if (this.cboard == 3) {
                this.board = 3;
                this.started = true;
                this.reset = false;
                this.gameover += 6;
            }
            if (this.cboard == 4) {
                this.board = 4;
                this.started = true;
                this.reset = false;
                this.gameover += 6;
            }
            if (this.cboard == 5) {
                this.board = 5;
                this.started = true;
                this.reset = false;
                this.gameover += 6;
            }
            if (this.cboard == 6) {
                this.board = 6;
                this.started = true;
                this.reset = false;
                this.gameover += 6;
            }
            if (this.cboard == 7) {
                this.board = 7;
                this.started = true;
                this.reset = false;
                this.gameover += 7;
            }
            if (this.cboard == 8) {
                this.board = 8;
                this.started = true;
                this.reset = false;
                this.gameover += 7;
            }
            if (this.cboard == 9) {
                this.board = 9;
                this.started = true;
                this.reset = false;
                this.gameover += 7;
            }
            if (this.cboard == 10) {
                this.board = 10;
                this.started = true;
                this.reset = false;
                this.gameover += 7;
            }
            if (this.cboard == 11) {
                this.board = 11;
                this.started = true;
                this.reset = false;
                this.gameover += 7;
            }
            if (this.cboard == 12) {
                this.board = 12;
                this.started = true;
                this.reset = false;
                this.gameover += 8;
            }
            if (this.cboard == 13) {
                this.board = 13;
                this.started = true;
                this.reset = false;
                this.gameover += 8;
            }
            if (this.cboard == 14) {
                this.board = 14;
                this.started = true;
                this.reset = false;
                this.gameover += 8;
            }
            if (this.cboard == 15) {
                this.board = 15;
                this.started = true;
                this.reset = false;
                this.gameover += 8;
            }
            if (this.cboard == 16) {
                this.board = 16;
                this.started = true;
                this.reset = false;
                this.gameover += 8;
            }
            if (this.cboard == 17) {
                this.board = 17;
                this.started = true;
                this.reset = false;
                this.gameover += 9;
            }
            if (this.cboard == 18) {
                this.board = 18;
                this.started = true;
                this.reset = false;
                this.gameover += 9;
            }
            if (this.cboard == 19) {
                this.board = 19;
                this.started = true;
                this.reset = false;
                this.gameover += 9;
            }
            if (this.cboard == 20) {
                this.board = 20;
                this.started = true;
                this.reset = false;
                this.gameover += 9;
            }
            if (this.cboard == 21) {
                this.board = 21;
                this.started = true;
                this.reset = false;
                this.gameover += 9;
            }
            if (this.cboard == 22) {
                this.board = 22;
                this.started = true;
                this.reset = false;
                this.gameover += 10;
            }
            if (this.cboard == 23) {
                this.board = 23;
                this.started = true;
                this.reset = false;
                this.gameover += 10;
            }
            if (this.cboard == 24) {
                this.board = 24;
                this.started = true;
                this.reset = false;
                this.gameover += 10;
            }
            if (this.cboard == 25) {
                this.board = 25;
                this.started = true;
                this.reset = false;
                this.gameover += 10;
            }
            if (this.cboard == 26) {
                this.board = 26;
                this.started = true;
                this.reset = false;
                this.gameover += 10;
            }
            if (this.cboard == 27) {
                this.board = 27;
                this.started = true;
                this.reset = false;
                this.gameover += 11;
            }
            if (this.cboard == 28) {
                this.board = 28;
                this.started = true;
                this.reset = false;
                this.gameover += 11;
            }
            if (this.cboard == 29) {
                this.board = 29;
                this.started = true;
                this.reset = false;
                this.gameover += 11;
            }
            if (this.cboard == 30) {
                this.board = 30;
                this.started = true;
                this.reset = false;
                this.gameover += 11;
            }
            if (this.cboard == 31) {
                this.board = 31;
                this.started = true;
                this.reset = false;
                this.gameover += 11;
            }
            if (this.cboard == 32) {
                this.board = 32;
                this.started = true;
                this.reset = false;
                this.gameover += 12;
            }
            if (this.cboard == 33) {
                this.board = 33;
                this.started = true;
                this.reset = false;
                this.gameover += 12;
            }
            if (this.cboard == 34) {
                this.board = 34;
                this.started = true;
                this.reset = false;
                this.gameover += 12;
            }
            if (this.cboard == 35) {
                this.board = 35;
                this.started = true;
                this.reset = false;
                this.gameover += 12;
            }
            if (this.cboard == 36) {
                this.board = 36;
                this.started = true;
                this.reset = false;
                this.gameover += 12;
            }
            if (this.cboard == 37) {
                this.board = 37;
                this.started = true;
                this.reset = false;
                this.gameover += 13;
            }
            if (this.cboard == 38) {
                this.board = 38;
                this.started = true;
                this.reset = false;
                this.gameover += 13;
            }
            if (this.cboard == 39) {
                this.board = 39;
                this.started = true;
                this.reset = false;
                this.gameover += 13;
            }
            if (this.cboard == 40) {
                this.board = 40;
                this.started = true;
                this.reset = false;
                this.gameover += 13;
            }
            if (this.cboard == 41) {
                this.board = 41;
                this.started = true;
                this.reset = false;
                this.gameover += 13;
            }
            if (this.cboard == 42) {
                this.board = 42;
                this.started = true;
                this.reset = false;
                this.gameover += 14;
            }
            if (this.cboard == 43) {
                this.board = 43;
                this.started = true;
                this.reset = false;
                this.gameover += 14;
            }
            if (this.cboard == 44) {
                this.board = 44;
                this.started = true;
                this.reset = false;
                this.gameover += 14;
            }
            if (this.cboard == 45) {
                this.board = 45;
                this.started = true;
                this.reset = false;
                this.gameover += 14;
            }
            if (this.cboard == 46) {
                this.board = 46;
                this.started = true;
                this.reset = false;
                this.gameover += 14;
            }
            if (this.cboard == 47) {
                this.board = 47;
                this.started = true;
                this.reset = false;
                this.gameover += 15;
            }
            if (this.cboard == 48) {
                this.board = 48;
                this.started = true;
                this.reset = false;
                this.gameover += 15;
            }
            if (this.cboard == 49) {
                this.board = 49;
                this.started = true;
                this.reset = false;
                this.gameover += 15;
            }
            if (this.cboard == 50) {
                this.board = 50;
                this.started = true;
                this.reset = false;
                this.gameover += 15;
            }
            this.move = 0;
        }
        if (this.maxmove) {
            this.play(this.getCodeBase(), "brdstrt.au");
            if (this.cboard == 1) {
                this.board = 1;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
            }
            if (this.cboard == 2) {
                this.board = 2;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 6;
            }
            if (this.cboard == 3) {
                this.board = 3;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 6;
            }
            if (this.cboard == 4) {
                this.board = 4;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 6;
            }
            if (this.cboard == 5) {
                this.board = 5;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 6;
            }
            if (this.cboard == 6) {
                this.board = 6;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 6;
            }
            if (this.cboard == 7) {
                this.board = 7;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 7;
            }
            if (this.cboard == 8) {
                this.board = 8;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 7;
            }
            if (this.cboard == 9) {
                this.board = 9;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 7;
            }
            if (this.cboard == 10) {
                this.board = 10;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 7;
            }
            if (this.cboard == 11) {
                this.board = 11;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 7;
            }
            if (this.cboard == 12) {
                this.board = 12;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 8;
            }
            if (this.cboard == 13) {
                this.board = 13;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 8;
            }
            if (this.cboard == 14) {
                this.board = 14;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 8;
            }
            if (this.cboard == 15) {
                this.board = 15;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 8;
            }
            if (this.cboard == 16) {
                this.board = 16;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 8;
            }
            if (this.cboard == 17) {
                this.board = 17;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 9;
            }
            if (this.cboard == 18) {
                this.board = 18;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 9;
            }
            if (this.cboard == 19) {
                this.board = 19;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 9;
            }
            if (this.cboard == 20) {
                this.board = 20;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 9;
            }
            if (this.cboard == 21) {
                this.board = 21;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 9;
            }
            if (this.cboard == 22) {
                this.board = 22;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 10;
            }
            if (this.cboard == 23) {
                this.board = 23;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 10;
            }
            if (this.cboard == 24) {
                this.board = 24;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 10;
            }
            if (this.cboard == 25) {
                this.board = 25;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 10;
            }
            if (this.cboard == 26) {
                this.board = 26;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 10;
            }
            if (this.cboard == 27) {
                this.board = 27;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 11;
            }
            if (this.cboard == 28) {
                this.board = 28;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 11;
            }
            if (this.cboard == 29) {
                this.board = 29;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 11;
            }
            if (this.cboard == 30) {
                this.board = 30;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 11;
            }
            if (this.cboard == 31) {
                this.board = 31;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 11;
            }
            if (this.cboard == 32) {
                this.board = 32;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 12;
            }
            if (this.cboard == 33) {
                this.board = 33;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 12;
            }
            if (this.cboard == 34) {
                this.board = 34;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 12;
            }
            if (this.cboard == 35) {
                this.board = 35;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 12;
            }
            if (this.cboard == 36) {
                this.board = 36;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 12;
            }
            if (this.cboard == 37) {
                this.board = 37;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 13;
            }
            if (this.cboard == 38) {
                this.board = 38;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 13;
            }
            if (this.cboard == 39) {
                this.board = 39;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 13;
            }
            if (this.cboard == 40) {
                this.board = 40;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 13;
            }
            if (this.cboard == 41) {
                this.board = 41;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 13;
            }
            if (this.cboard == 42) {
                this.board = 42;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 14;
            }
            if (this.cboard == 43) {
                this.board = 43;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 14;
            }
            if (this.cboard == 44) {
                this.board = 44;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 14;
            }
            if (this.cboard == 45) {
                this.board = 45;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 14;
            }
            if (this.cboard == 46) {
                this.board = 46;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 14;
            }
            if (this.cboard == 47) {
                this.board = 47;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 15;
            }
            if (this.cboard == 48) {
                this.board = 48;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 15;
            }
            if (this.cboard == 49) {
                this.board = 49;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 15;
            }
            if (this.cboard == 50) {
                this.board = 50;
                this.started = true;
                this.move = 0;
                this.maxmove = false;
                this.gameover += 15;
            }
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
