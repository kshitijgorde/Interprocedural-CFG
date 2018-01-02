import java.awt.Event;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Date;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet3 extends Applet
{
    public int gi;
    public int[] Fx;
    public int[] Fy;
    public int[] Fa;
    public int[] Fb;
    public int[] Le;
    public int[] Lf;
    public int GlCount1;
    public int iiee;
    public int eeii;
    public int RndE;
    int xFa;
    int yFa;
    int StepC;
    String StepCS;
    int hours;
    int[] BoxSym;
    int[] Elem14;
    int[] Elem13;
    int[] Elem12;
    int[] Elem11;
    int[] Elem10;
    int[] Elem9;
    int[] Elem8;
    int[] Elem7;
    int[] Elem6;
    int[] Elem5;
    int[] Elem4;
    int[] Elem3;
    int[] Elem2;
    int[] Elem1;
    int Mesto;
    int OldMesto;
    int NumLevel;
    int NewP;
    int NewP1;
    int NewP2;
    int NewP3;
    int NewP4;
    int NewP5;
    public int RefreshTg;
    public int PaintTrig;
    public int LocI;
    public int iSP;
    public int jSP;
    public int Pubci;
    public int NCounter;
    Color Gray1;
    Color Gray2;
    Color Gray3;
    Color Gray4;
    Color Red1;
    Color Green1;
    Color Blue1;
    Color Blue2;
    Color Lime;
    
    public void PrintPosition() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setFont(new Font("Arial", 1, 10));
        String string = "" + this.Elem14[this.Pubci];
        if (this.iSP > 577) {
            this.iSP = 95;
        }
        graphics.setColor(Color.black);
        if (this.Elem14[this.Pubci] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Pubci] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Pubci] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Pubci] == 9) {
            string = "T";
        }
        graphics.drawString(string, this.iSP + 1, this.jSP);
        graphics.setColor(Color.yellow);
        if (this.Elem14[this.Pubci] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Pubci] == 7) {
            graphics.setColor(Color.white);
        }
        if (this.Elem14[this.Pubci] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Pubci] == 8) {
            graphics.setColor(Color.orange);
        }
        if (this.Elem14[this.Pubci] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Pubci] == 0) {
            graphics.setColor(Color.red);
        }
        if (this.Elem14[this.Pubci] == 9) {
            string = "T";
        }
        if (this.Elem14[this.Pubci] == 9) {
            graphics.setColor(Color.magenta);
        }
        graphics.drawString(string, this.iSP, this.jSP - 1);
        this.iSP += 20;
    }
    
    public void CounterLeft() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        this.Fa[0] = 600;
        this.Fb[0] = 192;
        this.Fa[1] = this.Fa[0];
        this.Fb[1] = this.Fb[0] + 15;
        this.Fa[2] = this.Fa[0] + 60;
        this.Fb[2] = this.Fb[0] + 15;
        this.Fa[3] = this.Fa[0] + 60;
        this.Fb[3] = this.Fb[0];
        graphics.setColor(this.Gray4);
        graphics.fillPolygon(this.Fa, this.Fb, 4);
        this.RndPay();
        final String string = "" + this.NCounter;
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.drawString(string, 608, 204);
        graphics.setColor(Color.yellow);
        graphics.drawString(string, 607, 203);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setColor(Color.yellow);
        this.ClearDn();
        final String string = "" + n;
        final String string2 = "" + n2;
        graphics.setFont(new Font("Arial", 1, 9));
        graphics.drawString("Coord x=" + string + " y=" + string2, 35, 16);
        if (n2 > 311) {
            if (n2 < 329) {
                if (n > 591) {
                    if (n < 660) {
                        this.Mesto = 26;
                        graphics.drawString("Marker", 200, 365);
                    }
                    else {
                        this.Mesto = 0;
                    }
                }
                else {
                    this.Mesto = 0;
                }
            }
            if (n2 > 331) {
                if (n2 < 349) {
                    if (n > 90 && n < 110) {
                        this.Mesto = 1;
                        graphics.drawString("Position #1", 200, 365);
                    }
                    if (n > 110 && n < 130) {
                        this.Mesto = 2;
                        graphics.drawString("Position #2", 200, 365);
                    }
                    if (n > 130 && n < 150) {
                        this.Mesto = 3;
                        graphics.drawString("Position #3", 200, 365);
                    }
                    if (n > 150 && n < 170) {
                        this.Mesto = 4;
                        graphics.drawString("Position #4", 200, 365);
                    }
                    if (n > 170 && n < 190) {
                        this.Mesto = 5;
                        graphics.drawString("Position #5", 200, 365);
                    }
                    if (n > 190 && n < 210) {
                        this.Mesto = 6;
                        graphics.drawString("Position #6", 200, 365);
                    }
                    if (n > 210 && n < 230) {
                        this.Mesto = 7;
                        graphics.drawString("Position #7", 200, 365);
                    }
                    if (n > 230 && n < 250) {
                        this.Mesto = 8;
                        graphics.drawString("Position #8", 200, 365);
                    }
                    if (n > 250 && n < 270) {
                        this.Mesto = 9;
                        graphics.drawString("Position #9", 200, 365);
                    }
                    if (n > 270 && n < 290) {
                        this.Mesto = 10;
                        graphics.drawString("Position #10", 200, 365);
                    }
                    if (n > 290 && n < 310) {
                        this.Mesto = 11;
                        graphics.drawString("Position #11", 200, 365);
                    }
                    if (n > 310 && n < 330) {
                        this.Mesto = 12;
                        graphics.drawString("Position #12", 200, 365);
                    }
                    if (n > 330 && n < 350) {
                        this.Mesto = 13;
                        graphics.drawString("Position #13", 200, 365);
                    }
                    if (n > 350 && n < 370) {
                        this.Mesto = 14;
                        graphics.drawString("Position #14", 200, 365);
                    }
                    if (n > 370 && n < 390) {
                        this.Mesto = 15;
                        graphics.drawString("Position #15", 200, 365);
                    }
                    if (n > 390 && n < 410) {
                        this.Mesto = 16;
                        graphics.drawString("Position #16", 200, 365);
                    }
                    if (n > 410 && n < 430) {
                        this.Mesto = 17;
                        graphics.drawString("Position #17", 200, 365);
                    }
                    if (n > 430 && n < 450) {
                        this.Mesto = 18;
                        graphics.drawString("Position #18", 200, 365);
                    }
                    if (n > 450 && n < 470) {
                        this.Mesto = 19;
                        graphics.drawString("Position #19", 200, 365);
                    }
                    if (n > 470 && n < 490) {
                        this.Mesto = 20;
                        graphics.drawString("Position #20", 200, 365);
                    }
                    if (n > 490 && n < 510) {
                        this.Mesto = 21;
                        graphics.drawString("Position #21", 200, 365);
                    }
                    if (n > 510 && n < 530) {
                        this.Mesto = 22;
                        graphics.drawString("Position #22", 200, 365);
                    }
                    if (n > 530 && n < 550) {
                        this.Mesto = 23;
                        graphics.drawString("Position #23", 200, 365);
                    }
                    if (n > 550 && n < 570) {
                        this.Mesto = 24;
                        graphics.drawString("Position #24", 200, 365);
                    }
                    if (n > 570 && n < 590) {
                        this.Mesto = 25;
                        graphics.drawString("Position #25", 200, 365);
                    }
                }
                else {
                    this.Mesto = 0;
                }
            }
        }
        if (n > 592 && n < 662 && n2 > 330 && n2 < 349) {
            graphics.drawString("Refresh Window", 200, 365);
        }
        return true;
    }
    
    public void ClearFullScreen() {
        this.iSP = 95;
        this.jSP = 87;
        int n = 0;
        do {
            int n2 = 0;
            do {
                this.xFa = this.iSP;
                this.yFa = this.jSP;
                this.ClearPosition();
                this.jSP += 20;
                if (this.jSP > 360) {
                    this.jSP = 87;
                }
            } while (++n2 < 14);
            this.iSP += 20;
            if (this.iSP > 577) {
                this.iSP = 95;
            }
        } while (++n < 25);
    }
    
    public void NewLevels() {
        int n = 0;
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setFont(new Font("Arial", 1, 10));
        final int numLevel = this.NumLevel;
        int n2 = 0;
        do {
            ++this.NumLevel;
            final String string = "" + this.NumLevel;
            this.Fa[0] = 50;
            this.Fb[0] = 332 - n;
            this.Fa[1] = this.Fa[0];
            this.Fb[1] = this.Fb[0] + 18;
            this.Fa[2] = this.Fa[0] + 36;
            this.Fb[2] = this.Fb[0] + 18;
            this.Fa[3] = this.Fa[0] + 36;
            this.Fb[3] = this.Fb[0];
            graphics.setColor(this.Gray4);
            graphics.fillPolygon(this.Fa, this.Fb, 4);
            graphics.setColor(Color.black);
            graphics.drawString(string, 61, 345 - n);
            graphics.setColor(this.Blue2);
            graphics.drawString(string, 60, 344 - n);
            n += 20;
        } while (++n2 < 14);
        this.NumLevel = numLevel;
        ++this.NumLevel;
    }
    
    public void MarkerCount() {
        if (this.Mesto == this.OldMesto + 1) {
            this.StepCounter();
            if (this.StepC > 0) {
                this.PrintMarkerPravo();
            }
        }
        if (this.Mesto == this.OldMesto - 1) {
            this.StepCounter();
            if (this.StepC > 0) {
                this.PrintMarkerLevo();
            }
        }
        if (this.OldMesto == 1000) {
            this.PrintMarker();
        }
    }
    
    public void SetWinWhite() {
        final Graphics graphics = this.getGraphics();
        this.Le[0] = this.xFa - 5 - 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
    }
    
    public void PrintMarkerPravo() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setColor(Color.yellow);
        if (this.Mesto == 2) {
            this.LocI = 2;
            this.xFa = 95;
            this.yFa = 347;
            this.ClearPosition();
            this.xFa = 115;
            this.yFa = 347;
            this.SetWinBlack();
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 117, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 3) {
            this.LocI = 3;
            this.xFa = 115;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 135;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 137, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 4) {
            this.LocI = 4;
            this.xFa = 135;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 155;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 157, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 5) {
            this.LocI = 5;
            this.xFa = 155;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 175;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 177, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 6) {
            this.LocI = 6;
            this.xFa = 175;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 195;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 197, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 7) {
            this.LocI = 7;
            this.xFa = 195;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 215;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 217, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 8) {
            this.LocI = 8;
            this.xFa = 215;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 235;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 237, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 9) {
            this.LocI = 9;
            this.xFa = 235;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 255;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 257, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 10) {
            this.LocI = 10;
            this.xFa = 255;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 275;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 277, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 11) {
            this.LocI = 11;
            this.xFa = 275;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 295;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 297, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 12) {
            this.LocI = 12;
            this.xFa = 295;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 315;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 317, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 13) {
            this.LocI = 13;
            this.xFa = 315;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 335;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 337, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 14) {
            this.LocI = 14;
            this.xFa = 335;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 355;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 357, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 15) {
            this.LocI = 15;
            this.xFa = 355;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 375;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 377, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 16) {
            this.LocI = 16;
            this.xFa = 375;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 395;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 397, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 17) {
            this.LocI = 17;
            this.xFa = 395;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 415;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 417, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 18) {
            this.LocI = 18;
            this.xFa = 415;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 435;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 437, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 19) {
            this.LocI = 19;
            this.xFa = 435;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 455;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 457, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 20) {
            this.LocI = 20;
            this.xFa = 455;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 475;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 477, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 21) {
            this.LocI = 21;
            this.xFa = 475;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 495;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 497, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 22) {
            this.LocI = 22;
            this.xFa = 495;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 515;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 517, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 23) {
            this.LocI = 23;
            this.xFa = 515;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 535;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 537, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 24) {
            this.LocI = 24;
            this.xFa = 535;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 555;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 557, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
        if (this.Mesto == 25) {
            this.LocI = 25;
            this.xFa = 555;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 575;
            this.yFa = 347;
            this.SetWinWhiteRight();
            this.ClearPosition();
            graphics.drawString("X", 577, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1Position();
        }
    }
    
    public void PrintSet() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setColor(Color.yellow);
        this.MarkerCount();
    }
    
    public void WriteElem14() {
        this.getGraphics();
        int pubci = 0;
        do {
            this.Pubci = pubci;
            this.RndE = (int)(40.0 * Math.random());
            if (this.RndE == 39) {
                this.Elem14[pubci] = 9;
            }
            if (this.RndE == 32) {
                this.Elem14[pubci] = 0;
            }
            if (this.RndE == 28) {
                this.Elem14[pubci] = 0;
            }
            if (this.RndE == 23) {
                this.Elem14[pubci] = 0;
            }
            if (this.RndE == 17) {
                this.Elem14[pubci] = 0;
            }
            if (this.RndE == 11) {
                this.Elem14[pubci] = 0;
            }
            if (this.RndE == 36) {
                this.Elem14[pubci] = 8;
            }
            if (this.RndE == 14) {
                this.Elem14[pubci] = 8;
            }
            if (this.RndE == 34) {
                this.Elem14[pubci] = 8;
            }
            if (this.RndE == 21) {
                this.Elem14[pubci] = 8;
            }
            if (this.RndE == 7) {
                this.Elem14[pubci] = 8;
            }
            if (this.RndE == 37) {
                this.Elem14[pubci] = 25;
            }
            if (this.RndE == 19) {
                this.Elem14[pubci] = 25;
            }
            if (this.RndE == 5) {
                this.Elem14[pubci] = 25;
            }
            if (this.RndE == 35) {
                this.Elem14[pubci] = -25;
            }
            if (this.RndE == 30) {
                this.Elem14[pubci] = -25;
            }
            if (this.RndE == 3) {
                this.Elem14[pubci] = -25;
            }
            if (this.RndE == 38) {
                this.Elem14[pubci] = 10;
            }
            if (this.RndE == 29) {
                this.Elem14[pubci] = 10;
            }
            if (this.RndE == 15) {
                this.Elem14[pubci] = 10;
            }
            if (this.RndE == 33) {
                this.Elem14[pubci] = -10;
            }
            if (this.RndE == 20) {
                this.Elem14[pubci] = -10;
            }
            if (this.RndE == 4) {
                this.Elem14[pubci] = -10;
            }
            if (this.RndE == 40) {
                this.Elem14[pubci] = 5;
            }
            if (this.RndE == 26) {
                this.Elem14[pubci] = 5;
            }
            if (this.RndE == 16) {
                this.Elem14[pubci] = 5;
            }
            if (this.RndE == 10) {
                this.Elem14[pubci] = 5;
            }
            if (this.RndE == 31) {
                this.Elem14[pubci] = -5;
            }
            if (this.RndE == 25) {
                this.Elem14[pubci] = -5;
            }
            if (this.RndE == 12) {
                this.Elem14[pubci] = -5;
            }
            if (this.RndE == 18) {
                this.Elem14[pubci] = -5;
            }
            if (this.RndE == 24) {
                this.Elem14[pubci] = 1;
            }
            if (this.RndE == 13) {
                this.Elem14[pubci] = 1;
            }
            if (this.RndE == 9) {
                this.Elem14[pubci] = 1;
            }
            if (this.RndE == 6) {
                this.Elem14[pubci] = 1;
            }
            if (this.RndE == 0) {
                this.Elem14[pubci] = 1;
            }
            if (this.RndE == 27) {
                this.Elem14[pubci] = -1;
            }
            if (this.RndE == 22) {
                this.Elem14[pubci] = -1;
            }
            if (this.RndE == 8) {
                this.Elem14[pubci] = -1;
            }
            if (this.RndE == 2) {
                this.Elem14[pubci] = -1;
            }
            if (this.RndE == 1) {
                this.Elem14[pubci] = -1;
            }
            this.PrintPosition();
        } while (++pubci < 25);
    }
    
    public void NextElem14() {
        this.jSP = 87;
        this.WriteElem14();
        this.CopyLevels();
    }
    
    public void CopyLevels() {
        int n = 0;
        do {
            this.Elem1[n] = this.Elem2[n];
            this.Elem2[n] = this.Elem3[n];
            this.Elem3[n] = this.Elem4[n];
            this.Elem4[n] = this.Elem5[n];
            this.Elem5[n] = this.Elem6[n];
            this.Elem7[n] = this.Elem8[n];
            this.Elem9[n] = this.Elem10[n];
            this.Elem11[n] = this.Elem12[n];
            this.Elem12[n] = this.Elem13[n];
        } while (++n < 25);
    }
    
    public void RecycleLevels() {
        int n = 0;
        do {
            this.Elem14[n] = this.Elem1[n];
            this.Elem1[n] = this.Elem2[n];
            this.Elem2[n] = this.Elem3[n];
            this.Elem3[n] = this.Elem4[n];
            this.Elem4[n] = this.Elem5[n];
            this.Elem5[n] = this.Elem6[n];
            this.Elem6[n] = this.Elem7[n];
            this.Elem7[n] = this.Elem8[n];
            this.Elem8[n] = this.Elem9[n];
            this.Elem9[n] = this.Elem10[n];
            this.Elem10[n] = this.Elem11[n];
            this.Elem11[n] = this.Elem12[n];
            this.Elem12[n] = this.Elem13[n];
        } while (++n < 25);
        int n2 = 0;
        do {
            this.RndE = (int)(40.0 * Math.random());
            if (this.RndE == 39) {
                this.Elem13[n2] = 9;
            }
            if (this.RndE == 32) {
                this.Elem13[n2] = 0;
            }
            if (this.RndE == 28) {
                this.Elem13[n2] = 0;
            }
            if (this.RndE == 23) {
                this.Elem13[n2] = 0;
                if (this.NCounter > 1000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 17) {
                this.Elem13[n2] = 0;
            }
            if (this.RndE == 11) {
                this.Elem13[n2] = 0;
                if (this.NCounter > 1000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 36) {
                this.Elem13[n2] = 8;
            }
            if (this.RndE == 14) {
                this.Elem13[n2] = 8;
            }
            if (this.RndE == 34) {
                this.Elem13[n2] = 7;
            }
            if (this.RndE == 21) {
                this.Elem13[n2] = 7;
            }
            if (this.RndE == 7) {
                this.Elem13[n2] = 7;
            }
            if (this.RndE == 19) {
                this.Elem13[n2] = 25;
                if (this.NCounter > 2000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 5) {
                this.Elem13[n2] = 25;
            }
            if (this.RndE == 35) {
                this.Elem13[n2] = 25;
                if (this.NCounter > 2000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 37) {
                this.Elem13[n2] = -25;
            }
            if (this.RndE == 30) {
                this.Elem13[n2] = -25;
            }
            if (this.RndE == 3) {
                this.Elem13[n2] = -25;
            }
            if (this.RndE == 38) {
                this.Elem13[n2] = 10;
            }
            if (this.RndE == 29) {
                this.Elem13[n2] = 10;
                if (this.NCounter > 5000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 15) {
                this.Elem13[n2] = 10;
            }
            if (this.RndE == 33) {
                this.Elem13[n2] = -10;
                if (this.NCounter > 5000) {
                    this.Elem13[n2] = 0;
                }
            }
            if (this.RndE == 20) {
                this.Elem13[n2] = -10;
            }
            if (this.RndE == 4) {
                this.Elem13[n2] = -10;
            }
            if (this.RndE == 40) {
                this.Elem13[n2] = 5;
            }
            if (this.RndE == 26) {
                this.Elem13[n2] = 5;
                if (this.NCounter > 3000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 16) {
                this.Elem13[n2] = 5;
                if (this.NCounter > 3000) {
                    this.Elem13[n2] = 7;
                }
            }
            if (this.RndE == 10) {
                this.Elem13[n2] = 5;
            }
            if (this.RndE == 31) {
                this.Elem13[n2] = -5;
            }
            if (this.RndE == 25) {
                this.Elem13[n2] = -5;
                if (this.NCounter > 5000) {
                    this.Elem13[n2] = 0;
                }
            }
            if (this.RndE == 12) {
                this.Elem13[n2] = -5;
                if (this.NCounter > 5000) {
                    this.Elem13[n2] = 0;
                }
            }
            if (this.RndE == 18) {
                this.Elem13[n2] = -5;
            }
            if (this.RndE == 24) {
                this.Elem13[n2] = 1;
            }
            if (this.RndE == 13) {
                this.Elem13[n2] = 1;
            }
            if (this.RndE == 9) {
                this.Elem13[n2] = 1;
            }
            if (this.RndE == 6) {
                this.Elem13[n2] = 1;
            }
            if (this.RndE == 0) {
                this.Elem13[n2] = 1;
            }
            if (this.RndE == 27) {
                this.Elem13[n2] = -1;
            }
            if (this.RndE == 22) {
                this.Elem13[n2] = -1;
            }
            if (this.RndE == 8) {
                this.Elem13[n2] = -1;
            }
            if (this.RndE == 2) {
                this.Elem13[n2] = -1;
            }
            if (this.RndE == 1) {
                this.Elem13[n2] = -1;
            }
        } while (++n2 < 25);
        this.NewLevels();
    }
    
    public void ClearPosition() {
        final Graphics graphics = this.getGraphics();
        this.Le[0] = this.xFa - 3;
        this.Lf[0] = this.yFa - 17;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(this.Gray3);
        graphics.fillPolygon(this.Le, this.Lf, 6);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.Le, this.Lf, 6);
    }
    
    public void PrintLevel1Position() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        String string = "" + this.Elem14[this.Mesto - 2];
        this.CounterRight();
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.setColor(Color.black);
        if (this.Elem14[this.Mesto - 2] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Mesto - 2] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Mesto - 2] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Mesto - 2] == 9) {
            string = "T";
        }
        graphics.drawString(string, this.xFa - 21, 347);
        graphics.setColor(Color.yellow);
        if (this.Elem14[this.Mesto - 2] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Mesto - 2] == 7) {
            graphics.setColor(Color.white);
        }
        if (this.Elem14[this.Mesto - 2] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Mesto - 2] == 8) {
            graphics.setColor(Color.orange);
        }
        if (this.Elem14[this.Mesto - 2] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Mesto - 2] == 0) {
            graphics.setColor(Color.red);
        }
        if (this.Elem14[this.Mesto - 2] == 9) {
            string = "T";
        }
        if (this.Elem14[this.Mesto - 2] == 9) {
            graphics.setColor(Color.magenta);
        }
        graphics.drawString(string, this.xFa - 22, 346);
    }
    
    public void paint(final Graphics graphics) {
        if (this.PaintTrig == 1) {
            this.hours = new Date().getHours();
            graphics.setColor(Color.black);
            graphics.drawString("Refresh", 611, 346);
            graphics.setColor(Color.yellow);
            graphics.drawString("Refresh", 610, 345);
        }
        if (this.PaintTrig == 0) {
            graphics.setFont(new Font("Arial", 1, 10));
            graphics.drawString("Please WAIT. Loading ...", 40, 10);
            this.frun();
            this.PrText();
            this.SetAllPosition();
            this.PaintTrig = 1;
        }
    }
    
    public Applet3() {
        this.gi = 0;
        this.Fx = new int[6];
        this.Fy = new int[6];
        this.Fa = new int[4];
        this.Fb = new int[4];
        this.Le = new int[6];
        this.Lf = new int[6];
        this.GlCount1 = 0;
        this.StepCS = "Zero Step! Click |Next|";
        this.BoxSym = new int[25];
        this.Elem14 = new int[25];
        this.Elem13 = new int[25];
        this.Elem12 = new int[25];
        this.Elem11 = new int[25];
        this.Elem10 = new int[25];
        this.Elem9 = new int[25];
        this.Elem8 = new int[25];
        this.Elem7 = new int[25];
        this.Elem6 = new int[25];
        this.Elem5 = new int[25];
        this.Elem4 = new int[25];
        this.Elem3 = new int[25];
        this.Elem2 = new int[25];
        this.Elem1 = new int[25];
        this.OldMesto = 1000;
        this.NumLevel = 0;
        this.NewP1 = 0;
        this.NewP2 = 0;
        this.NewP3 = 0;
        this.NewP4 = 0;
        this.NewP5 = 0;
        this.RefreshTg = 0;
        this.PaintTrig = 0;
        this.LocI = 0;
        this.iSP = 95;
        this.jSP = 87;
        this.NCounter = 0;
        this.Gray1 = new Color(10855845);
        this.Gray2 = new Color(8355711);
        this.Gray3 = new Color(9342606);
        this.Gray4 = new Color(8816262);
        this.Red1 = new Color(14822985);
        this.Green1 = new Color(8454016);
        this.Blue1 = new Color(12319212);
        this.Blue2 = new Color(14207998);
        this.Lime = new Color(9892783);
    }
    
    public void PrintLevel1PositionLeft() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        String string = "" + this.Elem14[this.Mesto];
        this.CounterLeft();
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.setColor(Color.black);
        if (this.Elem14[this.Mesto] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Mesto] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Mesto] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Mesto] == 9) {
            string = "T";
        }
        graphics.drawString(string, this.xFa + 17, 347);
        graphics.setColor(Color.yellow);
        if (this.Elem14[this.Mesto] == 7) {
            string = "B";
        }
        if (this.Elem14[this.Mesto] == 7) {
            graphics.setColor(Color.white);
        }
        if (this.Elem14[this.Mesto] == 8) {
            string = "P";
        }
        if (this.Elem14[this.Mesto] == 8) {
            graphics.setColor(Color.orange);
        }
        if (this.Elem14[this.Mesto] == 0) {
            string = "Z";
        }
        if (this.Elem14[this.Mesto] == 0) {
            graphics.setColor(Color.red);
        }
        if (this.Elem14[this.Mesto] == 9) {
            string = "T";
        }
        if (this.Elem14[this.Mesto] == 9) {
            graphics.setColor(Color.magenta);
        }
        graphics.drawString(string, this.xFa + 18, 346);
    }
    
    public void PrintMarker() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setColor(Color.yellow);
        if (this.Mesto == 1) {
            this.xFa = 95;
            this.yFa = 347;
            this.SetWinWhiteLeft();
            this.ClearPosition();
            graphics.drawString("X", 97, 346);
            this.LocI = 1;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 2) {
            this.xFa = 115;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 117, 346);
            this.LocI = 2;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 3) {
            this.xFa = 135;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 137, 346);
            this.LocI = 3;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 4) {
            this.xFa = 155;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 157, 346);
            this.LocI = 4;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 5) {
            this.xFa = 175;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 177, 346);
            this.LocI = 5;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 6) {
            this.xFa = 195;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 197, 346);
            this.LocI = 6;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 7) {
            this.xFa = 215;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 217, 346);
            this.LocI = 7;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 8) {
            this.xFa = 235;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 237, 346);
            this.LocI = 8;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 9) {
            this.xFa = 255;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 257, 346);
            this.LocI = 9;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 10) {
            this.xFa = 275;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 277, 346);
            this.LocI = 10;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 11) {
            this.xFa = 295;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 297, 346);
            this.LocI = 11;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 12) {
            this.xFa = 315;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 317, 346);
            this.LocI = 12;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 13) {
            this.xFa = 335;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 337, 346);
            this.LocI = 13;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 14) {
            this.xFa = 355;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 357, 346);
            this.LocI = 14;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 15) {
            this.xFa = 375;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 377, 346);
            this.LocI = 15;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 16) {
            this.xFa = 395;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 397, 346);
            this.LocI = 16;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 17) {
            this.xFa = 415;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 417, 346);
            this.LocI = 17;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 18) {
            this.xFa = 435;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 437, 346);
            this.LocI = 18;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 19) {
            this.xFa = 455;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 457, 346);
            this.LocI = 19;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 20) {
            this.xFa = 475;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 477, 346);
            this.LocI = 20;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 21) {
            this.xFa = 495;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 497, 346);
            this.LocI = 21;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 22) {
            this.xFa = 515;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 517, 346);
            this.LocI = 22;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 23) {
            this.xFa = 535;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 537, 346);
            this.LocI = 23;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 24) {
            this.xFa = 555;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 557, 346);
            this.LocI = 24;
            this.OldMesto = this.Mesto;
        }
        if (this.Mesto == 25) {
            this.xFa = 575;
            this.yFa = 347;
            this.SetWinWhiteRight();
            this.ClearPosition();
            graphics.drawString("X", 577, 346);
            this.LocI = 25;
            this.OldMesto = this.Mesto;
        }
        if (this.RefreshTg == 0) {
            this.DepositCounter();
        }
    }
    
    public void DepositCounter() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        final int n = this.Elem14[this.LocI - 1];
        if (this.hours <= 18) {
            graphics.setColor(Color.white);
        }
        if (this.hours > 18) {
            graphics.setColor(Color.green);
        }
        this.Fa[0] = 600;
        this.Fb[0] = 192;
        this.Fa[1] = this.Fa[0];
        this.Fb[1] = this.Fb[0] + 15;
        this.Fa[2] = this.Fa[0] + 60;
        this.Fb[2] = this.Fb[0] + 15;
        this.Fa[3] = this.Fa[0] + 60;
        this.Fb[3] = this.Fb[0];
        graphics.setColor(this.Gray4);
        graphics.fillPolygon(this.Fa, this.Fb, 4);
        this.Mesto = this.LocI;
        this.RndPay();
        final String string = "" + this.NCounter;
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.drawString(string, 608, 204);
        graphics.setColor(Color.yellow);
        graphics.drawString(string, 607, 203);
    }
    
    public void ScrollCounter() {
        this.NewP5 = this.NewP4;
        this.NewP4 = this.NewP3;
        this.NewP3 = this.NewP2;
        this.NewP2 = this.NewP1;
        this.NewP1 = this.NewP;
        final Graphics graphics = this.getGraphics();
        graphics.setFont(new Font("Arial", 1, 10));
        this.Le[0] = 594;
        this.Lf[0] = 70;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 100;
        this.Le[3] = this.Le[0] + 66;
        this.Lf[3] = this.Lf[0] + 100;
        this.Le[4] = this.Le[0] + 68;
        this.Lf[4] = this.Lf[0] + 98;
        this.Le[5] = this.Le[0] + 68;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(this.Gray4);
        graphics.fillPolygon(this.Le, this.Lf, 6);
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        final String string = "" + this.NewP5;
        graphics.setColor(Color.black);
        graphics.drawString(string, 612, 86);
        graphics.setColor(this.Blue2);
        graphics.drawString(string, 611, 85);
        final String string2 = "" + this.NewP4;
        graphics.setColor(Color.black);
        graphics.drawString(string2, 612, 106);
        graphics.setColor(this.Blue2);
        graphics.drawString(string2, 611, 105);
        final String string3 = "" + this.NewP3;
        graphics.setColor(Color.black);
        graphics.drawString(string3, 612, 126);
        graphics.setColor(this.Blue2);
        graphics.drawString(string3, 611, 125);
        final String string4 = "" + this.NewP2;
        graphics.setColor(Color.black);
        graphics.drawString(string4, 612, 146);
        graphics.setColor(this.Blue2);
        graphics.drawString(string4, 611, 145);
        final String string5 = "" + this.NewP1;
        graphics.setColor(Color.black);
        graphics.drawString(string5, 612, 166);
        graphics.setColor(this.Blue2);
        graphics.drawString(string5, 611, 165);
    }
    
    public void RndPay() {
        final int nCounter = this.NCounter;
        this.NCounter += this.Elem14[this.Mesto - 1];
        if (this.Elem14[this.Mesto - 1] == 9) {
            this.NCounter -= this.Elem14[this.Mesto - 1];
            if (this.NCounter < 1000) {
                this.RndE = (int)(3000.0 * Math.random());
            }
            if (this.NCounter > 1000) {
                this.RndE = (int)(2000.0 * Math.random());
            }
            if (this.NCounter > 2000) {
                this.RndE = (int)(1000.0 * Math.random());
            }
            this.NCounter += this.RndE;
        }
        if (this.Elem14[this.Mesto - 1] == 8) {
            this.NCounter -= this.Elem14[this.Mesto - 1];
            if (this.NCounter < 1000) {
                this.RndE = (int)(500.0 * Math.random());
            }
            if (this.NCounter > 1000) {
                this.RndE = (int)(1000.0 * Math.random());
            }
            final int n = (int)(1.0 * Math.random());
            if (n == 0) {
                this.NCounter += this.RndE;
            }
            if (n == 1) {
                this.NCounter -= this.RndE;
            }
        }
        if (this.Elem14[this.Mesto - 1] == 7) {
            this.NCounter -= this.Elem14[this.Mesto - 1];
            if (this.NCounter < 100) {
                this.RndE = (int)(1.0 * Math.random());
            }
            if (this.NCounter > 500) {
                this.RndE = (int)(5.0 * Math.random());
            }
            if (this.NCounter > 1000) {
                this.RndE = (int)(9.0 * Math.random());
            }
            if (this.RndE == 0) {
                this.NCounter -= 100;
            }
            if (this.RndE == 1) {
                this.NCounter -= 200;
            }
            if (this.RndE == 2) {
                this.NCounter -= 300;
            }
            if (this.RndE == 3) {
                this.NCounter -= 400;
            }
            if (this.RndE == 4) {
                this.NCounter -= 500;
            }
            if (this.RndE == 5) {
                this.NCounter -= 600;
            }
            if (this.RndE == 6) {
                this.NCounter -= 700;
            }
            if (this.RndE == 7) {
                this.NCounter -= 800;
            }
            if (this.RndE == 8) {
                this.NCounter -= 900;
            }
            if (this.RndE == 9) {
                this.NCounter -= 1000;
            }
        }
        if (this.Elem14[this.Mesto - 1] == 0) {
            this.NCounter = 0;
        }
        this.NewP = this.NCounter - nCounter;
        this.ScrollCounter();
    }
    
    public void ClearDn() {
        final Graphics graphics = this.getGraphics();
        this.Fa[0] = 150;
        this.Fb[0] = 352;
        this.Fa[1] = this.Fa[0];
        this.Fb[1] = this.Fb[0] + 20;
        this.Fa[2] = this.Fa[0] + 400;
        this.Fb[2] = this.Fb[0] + 20;
        this.Fa[3] = this.Fa[0] + 400;
        this.Fb[3] = this.Fb[0];
        graphics.setColor(this.Gray2);
        graphics.fillPolygon(this.Fa, this.Fb, 4);
        this.Fa[0] = 25;
        this.Fb[0] = 4;
        this.Fa[1] = this.Fa[0];
        this.Fb[1] = this.Fb[0] + 15;
        this.Fa[2] = this.Fa[0] + 580;
        this.Fb[2] = this.Fb[0] + 15;
        this.Fa[3] = this.Fa[0] + 580;
        this.Fb[3] = this.Fb[0];
        graphics.setColor(this.Gray1);
        graphics.fillPolygon(this.Fa, this.Fb, 4);
    }
    
    public void frun() {
        final Graphics graphics = this.getGraphics();
        if (this.gi == 0) {
            this.Fx[0] = 10;
            this.Fy[0] = 0;
            this.Fx[1] = this.Fx[0] - 10;
            this.Fy[1] = this.Fy[0] + 10;
            this.Fx[2] = this.Fx[0] - 10;
            this.Fy[2] = this.Fy[0] + 400;
            this.Fx[3] = this.Fx[0] + 690;
            this.Fy[3] = this.Fy[0] + 400;
            this.Fx[4] = this.Fx[0] + 700;
            this.Fy[4] = this.Fy[0] + 390;
            this.Fx[5] = this.Fx[0] + 700;
            this.Fy[5] = this.Fy[0];
            graphics.setColor(this.Gray1);
            graphics.fillPolygon(this.Fx, this.Fy, 6);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.Fx, this.Fy, 6);
            this.Fa[0] = 20;
            this.Fb[0] = 20;
            this.Fa[1] = this.Fa[0];
            this.Fb[1] = this.Fb[0] + 360;
            this.Fa[2] = this.Fa[0] + 670;
            this.Fb[2] = this.Fb[0] + 360;
            this.Fa[3] = this.Fa[0] + 670;
            this.Fb[3] = this.Fb[0];
            graphics.setColor(this.Gray2);
            graphics.fillPolygon(this.Fa, this.Fb, 4);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.Fa, this.Fb, 4);
            graphics.setColor(Color.black);
            graphics.drawLine(0, 400, 20, 380);
            graphics.setColor(Color.black);
            graphics.drawLine(10, 0, 20, 20);
            graphics.setColor(Color.black);
            graphics.drawLine(0, 10, 20, 20);
            graphics.setColor(Color.black);
            graphics.drawLine(690, 20, 710, 0);
            graphics.setColor(Color.black);
            graphics.drawLine(690, 380, 700, 400);
            graphics.setColor(Color.black);
            graphics.drawLine(690, 380, 710, 390);
            int eeii = 0;
            do {
                this.eeii = eeii;
                this.eeii *= 20;
                this.Le[0] = 50;
                this.Lf[0] = 50 + this.eeii;
                this.Le[1] = this.Le[0] - 2;
                this.Lf[1] = this.Lf[0] + 2;
                this.Le[2] = this.Le[0] - 2;
                this.Lf[2] = this.Lf[0] + 20;
                this.Le[3] = this.Le[0] + 36;
                this.Lf[3] = this.Lf[0] + 20;
                this.Le[4] = this.Le[0] + 38;
                this.Lf[4] = this.Lf[0] + 18;
                this.Le[5] = this.Le[0] + 38;
                this.Lf[5] = this.Lf[0];
                graphics.setColor(this.Gray4);
                graphics.fillPolygon(this.Le, this.Lf, 6);
                graphics.setColor(Color.black);
                graphics.drawPolygon(this.Le, this.Lf, 6);
                int iiee = 0;
                do {
                    this.iiee = iiee;
                    this.iiee *= 20;
                    this.Le[0] = 92 + this.iiee;
                    this.Lf[0] = 50 + this.eeii;
                    this.Le[1] = this.Le[0] - 2;
                    this.Lf[1] = this.Lf[0] + 2;
                    this.Le[2] = this.Le[0] - 2;
                    this.Lf[2] = this.Lf[0] + 20;
                    this.Le[3] = this.Le[0] + 16;
                    this.Lf[3] = this.Lf[0] + 20;
                    this.Le[4] = this.Le[0] + 18;
                    this.Lf[4] = this.Lf[0] + 18;
                    this.Le[5] = this.Le[0] + 18;
                    this.Lf[5] = this.Lf[0];
                    graphics.setColor(this.Gray3);
                    graphics.fillPolygon(this.Le, this.Lf, 6);
                    graphics.setColor(Color.black);
                    graphics.drawPolygon(this.Le, this.Lf, 6);
                } while (++iiee < 25);
            } while (++eeii < 15);
            this.Le[0] = 594;
            this.Lf[0] = 50;
            this.Le[1] = this.Le[0] - 2;
            this.Lf[1] = this.Lf[0] + 2;
            this.Le[2] = this.Le[0] - 2;
            this.Lf[2] = this.Lf[0] + 20;
            this.Le[3] = this.Le[0] + 66;
            this.Lf[3] = this.Lf[0] + 20;
            this.Le[4] = this.Le[0] + 68;
            this.Lf[4] = this.Lf[0] + 18;
            this.Le[5] = this.Le[0] + 68;
            this.Lf[5] = this.Lf[0];
            graphics.setColor(this.Gray4);
            graphics.fillPolygon(this.Le, this.Lf, 6);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.Le, this.Lf, 6);
            this.Le[0] = 594;
            this.Lf[0] = 70;
            this.Le[1] = this.Le[0] - 2;
            this.Lf[1] = this.Lf[0] + 2;
            this.Le[2] = this.Le[0] - 2;
            this.Lf[2] = this.Lf[0] + 100;
            this.Le[3] = this.Le[0] + 66;
            this.Lf[3] = this.Lf[0] + 100;
            this.Le[4] = this.Le[0] + 68;
            this.Lf[4] = this.Lf[0] + 98;
            this.Le[5] = this.Le[0] + 68;
            this.Lf[5] = this.Lf[0];
            graphics.setColor(this.Gray4);
            graphics.fillPolygon(this.Le, this.Lf, 6);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.Le, this.Lf, 6);
            int eeii2 = 0;
            do {
                this.eeii = eeii2;
                this.eeii *= 20;
                this.Le[0] = 594;
                this.Lf[0] = 170 + this.eeii;
                this.Le[1] = this.Le[0] - 2;
                this.Lf[1] = this.Lf[0] + 2;
                this.Le[2] = this.Le[0] - 2;
                this.Lf[2] = this.Lf[0] + 20;
                this.Le[3] = this.Le[0] + 66;
                this.Lf[3] = this.Lf[0] + 20;
                this.Le[4] = this.Le[0] + 68;
                this.Lf[4] = this.Lf[0] + 18;
                this.Le[5] = this.Le[0] + 68;
                this.Lf[5] = this.Lf[0];
                graphics.setColor(this.Gray4);
                graphics.fillPolygon(this.Le, this.Lf, 6);
                graphics.setColor(Color.black);
                graphics.drawPolygon(this.Le, this.Lf, 6);
                this.hours = new Date().getHours();
                graphics.setColor(Color.black);
                graphics.drawString("NEXT", 611, 306);
                graphics.drawString("X", 621, 326);
                graphics.drawString("Refresh", 611, 346);
                graphics.setColor(Color.yellow);
                graphics.drawString("NEXT", 610, 305);
                graphics.drawString("X", 620, 325);
                graphics.drawString("Refresh", 610, 345);
            } while (++eeii2 < 9);
        }
    }
    
    public void HiddenLevels() {
        final Graphics graphics = this.getGraphics();
        this.RndE = (int)(14.0 * Math.random());
        for (int i = 0; i < this.RndE; ++i) {
            final int n = i * 20;
            int n2 = 0;
            do {
                this.Le[0] = 92 + n2 * 20;
                this.Lf[0] = 70 + n;
                this.Le[1] = this.Le[0] - 2;
                this.Lf[1] = this.Lf[0] + 2;
                this.Le[2] = this.Le[0] - 2;
                this.Lf[2] = this.Lf[0] + 20;
                this.Le[3] = this.Le[0] + 16;
                this.Lf[3] = this.Lf[0] + 20;
                this.Le[4] = this.Le[0] + 18;
                this.Lf[4] = this.Lf[0] + 18;
                this.Le[5] = this.Le[0] + 18;
                this.Lf[5] = this.Lf[0];
                graphics.setColor(Color.orange);
                graphics.fillPolygon(this.Le, this.Lf, 6);
                graphics.setColor(Color.black);
                graphics.drawPolygon(this.Le, this.Lf, 6);
            } while (++n2 < 25);
        }
    }
    
    public void StepCounter() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        if (this.StepC > 0) {
            --this.StepC;
        }
        graphics.setColor(Color.orange);
        if (this.StepC == 0) {
            graphics.drawString(this.StepCS, 320, 365);
        }
        final String string = "" + this.StepC;
        this.xFa = 620;
        this.yFa = 278;
        this.ClearPosition();
        graphics.setColor(Color.black);
        graphics.drawString(string, 623, 278);
        graphics.setColor(Color.orange);
        graphics.drawString(string, 622, 277);
    }
    
    public void RefreshWindow() {
        this.frun();
        this.PrText();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(new Font("Arial", 1, 10));
        this.jSP = 87;
        this.hours = new Date().getHours();
        int n = 0;
        do {
            String string = "" + this.Elem13[n];
            if (this.Elem13[n] == 7) {
                string = "B";
            }
            if (this.Elem13[n] == 8) {
                string = "P";
            }
            if (this.Elem13[n] == 0) {
                string = "Z";
            }
            if (this.Elem13[n] == 9) {
                string = "T";
            }
            String string2 = "" + this.Elem12[n];
            if (this.Elem12[n] == 7) {
                string2 = "B";
            }
            if (this.Elem12[n] == 8) {
                string2 = "P";
            }
            if (this.Elem12[n] == 0) {
                string2 = "Z";
            }
            if (this.Elem12[n] == 9) {
                string2 = "T";
            }
            String string3 = "" + this.Elem11[n];
            if (this.Elem11[n] == 7) {
                string3 = "B";
            }
            if (this.Elem11[n] == 8) {
                string3 = "P";
            }
            if (this.Elem11[n] == 0) {
                string3 = "Z";
            }
            if (this.Elem11[n] == 9) {
                string3 = "T";
            }
            String string4 = "" + this.Elem10[n];
            if (this.Elem10[n] == 7) {
                string4 = "B";
            }
            if (this.Elem10[n] == 8) {
                string4 = "P";
            }
            if (this.Elem10[n] == 0) {
                string4 = "Z";
            }
            if (this.Elem10[n] == 9) {
                string4 = "T";
            }
            String string5 = "" + this.Elem9[n];
            if (this.Elem9[n] == 7) {
                string5 = "B";
            }
            if (this.Elem9[n] == 8) {
                string5 = "P";
            }
            if (this.Elem9[n] == 0) {
                string5 = "Z";
            }
            if (this.Elem9[n] == 9) {
                string5 = "T";
            }
            String string6 = "" + this.Elem8[n];
            if (this.Elem8[n] == 7) {
                string6 = "B";
            }
            if (this.Elem8[n] == 8) {
                string6 = "P";
            }
            if (this.Elem8[n] == 0) {
                string6 = "Z";
            }
            if (this.Elem8[n] == 9) {
                string6 = "T";
            }
            String string7 = "" + this.Elem7[n];
            if (this.Elem7[n] == 7) {
                string7 = "B";
            }
            if (this.Elem7[n] == 8) {
                string7 = "P";
            }
            if (this.Elem7[n] == 0) {
                string7 = "Z";
            }
            if (this.Elem7[n] == 9) {
                string7 = "T";
            }
            String string8 = "" + this.Elem6[n];
            if (this.Elem6[n] == 7) {
                string8 = "B";
            }
            if (this.Elem6[n] == 8) {
                string8 = "P";
            }
            if (this.Elem6[n] == 0) {
                string8 = "Z";
            }
            if (this.Elem6[n] == 9) {
                string8 = "T";
            }
            String string9 = "" + this.Elem5[n];
            if (this.Elem5[n] == 7) {
                string9 = "B";
            }
            if (this.Elem5[n] == 8) {
                string9 = "P";
            }
            if (this.Elem5[n] == 0) {
                string9 = "Z";
            }
            if (this.Elem5[n] == 9) {
                string9 = "T";
            }
            String string10 = "" + this.Elem4[n];
            if (this.Elem4[n] == 7) {
                string10 = "B";
            }
            if (this.Elem4[n] == 8) {
                string10 = "P";
            }
            if (this.Elem4[n] == 0) {
                string10 = "Z";
            }
            if (this.Elem4[n] == 9) {
                string10 = "T";
            }
            String string11 = "" + this.Elem3[n];
            if (this.Elem3[n] == 7) {
                string11 = "B";
            }
            if (this.Elem3[n] == 8) {
                string11 = "P";
            }
            if (this.Elem3[n] == 0) {
                string11 = "Z";
            }
            if (this.Elem3[n] == 9) {
                string11 = "T";
            }
            String string12 = "" + this.Elem2[n];
            if (this.Elem2[n] == 7) {
                string12 = "B";
            }
            if (this.Elem2[n] == 8) {
                string12 = "P";
            }
            if (this.Elem2[n] == 0) {
                string12 = "Z";
            }
            if (this.Elem2[n] == 9) {
                string12 = "T";
            }
            String string13 = "" + this.Elem1[n];
            if (this.Elem1[n] == 7) {
                string13 = "B";
            }
            if (this.Elem1[n] == 8) {
                string13 = "P";
            }
            if (this.Elem1[n] == 0) {
                string13 = "Z";
            }
            if (this.Elem1[n] == 9) {
                string13 = "T";
            }
            String string14 = "" + this.Elem14[n];
            if (this.Elem14[n] == 7) {
                string14 = "B";
            }
            if (this.Elem14[n] == 8) {
                string14 = "P";
            }
            if (this.Elem14[n] == 0) {
                string14 = "Z";
            }
            if (this.Elem14[n] == 9) {
                string14 = "T";
            }
            if (this.iSP > 577) {
                this.iSP = 95;
            }
            if (this.jSP > 360) {
                this.jSP = 87;
            }
            graphics.setColor(Color.black);
            graphics.drawString(string, this.iSP + 1, this.jSP);
            graphics.drawString(string2, this.iSP + 1, this.jSP + 20);
            graphics.drawString(string3, this.iSP + 1, this.jSP + 40);
            graphics.drawString(string4, this.iSP + 1, this.jSP + 60);
            graphics.drawString(string5, this.iSP + 1, this.jSP + 80);
            graphics.drawString(string6, this.iSP + 1, this.jSP + 100);
            graphics.drawString(string7, this.iSP + 1, this.jSP + 120);
            graphics.drawString(string8, this.iSP + 1, this.jSP + 140);
            graphics.drawString(string9, this.iSP + 1, this.jSP + 160);
            graphics.drawString(string10, this.iSP + 1, this.jSP + 180);
            graphics.drawString(string11, this.iSP + 1, this.jSP + 200);
            graphics.drawString(string12, this.iSP + 1, this.jSP + 220);
            graphics.drawString(string13, this.iSP + 1, this.jSP + 240);
            graphics.drawString(string14, this.iSP + 1, this.jSP + 260);
            graphics.setColor(Color.yellow);
            if (this.Elem13[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem13[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem13[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem13[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string, this.iSP, this.jSP - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem12[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem12[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem12[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem12[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string2, this.iSP, this.jSP + 20 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem11[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem11[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem11[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem11[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string3, this.iSP, this.jSP + 40 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem10[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem10[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem10[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem10[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string4, this.iSP, this.jSP + 60 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem9[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem9[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem9[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem9[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string5, this.iSP, this.jSP + 80 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem8[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem8[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem8[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem8[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string6, this.iSP, this.jSP + 100 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem7[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem7[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem7[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem7[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string7, this.iSP, this.jSP + 120 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem6[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem6[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem6[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem6[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string8, this.iSP, this.jSP + 140 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem5[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem5[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem5[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem5[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string9, this.iSP, this.jSP + 160 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem4[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem4[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem4[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem4[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string10, this.iSP, this.jSP + 180 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem3[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem3[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem3[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem3[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string11, this.iSP, this.jSP + 200 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem2[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem2[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem2[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem2[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string12, this.iSP, this.jSP + 220 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem1[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem1[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem1[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem1[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string13, this.iSP, this.jSP + 240 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem14[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem14[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem14[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem14[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string14, this.iSP, this.jSP + 260 - 1);
            this.iSP += 20;
        } while (++n < 25);
        this.Mesto = this.OldMesto;
        this.RefreshTg = 1;
        this.PrintMarker();
        this.RefreshTg = 0;
        final String string15 = "" + this.NewP5;
        graphics.setColor(Color.black);
        graphics.drawString(string15, 612, 86);
        graphics.setColor(this.Blue2);
        graphics.drawString(string15, 611, 85);
        final String string16 = "" + this.NewP4;
        graphics.setColor(Color.black);
        graphics.drawString(string16, 612, 106);
        graphics.setColor(this.Blue2);
        graphics.drawString(string16, 611, 105);
        final String string17 = "" + this.NewP3;
        graphics.setColor(Color.black);
        graphics.drawString(string17, 612, 126);
        graphics.setColor(this.Blue2);
        graphics.drawString(string17, 611, 125);
        final String string18 = "" + this.NewP2;
        graphics.setColor(Color.black);
        graphics.drawString(string18, 612, 146);
        graphics.setColor(this.Blue2);
        graphics.drawString(string18, 611, 145);
        final String string19 = "" + this.NewP1;
        graphics.setColor(Color.black);
        graphics.drawString(string19, 612, 166);
        graphics.setColor(this.Blue2);
        graphics.drawString(string19, 611, 165);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        if (n > 593 && n < 660 && n2 > 290 && n2 < 306) {
            this.StepC = 4;
            this.RePrint();
            final String s = "3";
            this.xFa = 620;
            this.yFa = 278;
            this.ClearPosition();
            graphics.setColor(Color.black);
            graphics.drawString(s, 623, 278);
            graphics.setColor(Color.orange);
            graphics.drawString(s, 622, 277);
        }
        if (n > 592 && n < 662 && n2 > 330 && n2 < 349) {
            this.RefreshWindow();
        }
        this.PrintSet();
        return true;
    }
    
    public void SetWinWhiteRight() {
        final Graphics graphics = this.getGraphics();
        this.Le[0] = this.xFa - 5 - 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
    }
    
    public void SetWinBlack() {
        final Graphics graphics = this.getGraphics();
        this.Le[0] = this.xFa - 5 - 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.black);
        graphics.drawPolygon(this.Le, this.Lf, 6);
    }
    
    public void SetAllPosition() {
        this.getGraphics();
        int n = 0;
        do {
            this.WriteElem14();
            this.PrintAllPosition();
            this.jSP += 20;
            if (this.jSP > 360) {
                this.jSP = 87;
            }
        } while (++n < 14);
    }
    
    public void PrintAllPosition() {
        int n = 0;
        do {
            if (this.GlCount1 == 0) {
                this.Elem13[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 1) {
                this.Elem12[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 2) {
                this.Elem11[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 3) {
                this.Elem10[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 4) {
                this.Elem9[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 5) {
                this.Elem8[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 6) {
                this.Elem7[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 7) {
                this.Elem6[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 8) {
                this.Elem5[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 9) {
                this.Elem4[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 10) {
                this.Elem3[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 11) {
                this.Elem2[n] = this.Elem14[n];
            }
            if (this.GlCount1 == 12) {
                this.Elem1[n] = this.Elem14[n];
            }
        } while (++n < 25);
        ++this.GlCount1;
    }
    
    public void CounterRight() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        this.Fa[0] = 600;
        this.Fb[0] = 192;
        this.Fa[1] = this.Fa[0];
        this.Fb[1] = this.Fb[0] + 15;
        this.Fa[2] = this.Fa[0] + 60;
        this.Fb[2] = this.Fb[0] + 15;
        this.Fa[3] = this.Fa[0] + 60;
        this.Fb[3] = this.Fb[0];
        graphics.setColor(this.Gray4);
        graphics.fillPolygon(this.Fa, this.Fb, 4);
        this.RndPay();
        final String string = "" + this.NCounter;
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.drawString(string, 608, 204);
        graphics.setColor(Color.yellow);
        graphics.drawString(string, 607, 203);
    }
    
    public void init() {
        this.setBackground(new Color(6250640));
        this.StepC = 3;
        this.RndE = (int)(400.0 * Math.random());
        this.NCounter = this.RndE;
    }
    
    public void RePrint() {
        this.ClearFullScreen();
        this.RecycleLevels();
        this.jSP = 87;
        int n = 0;
        do {
            final Graphics graphics = this.getGraphics();
            this.hours = new Date().getHours();
            graphics.setFont(new Font("Arial", 1, 10));
            String string = "" + this.Elem13[n];
            if (this.Elem13[n] == 7) {
                string = "B";
            }
            if (this.Elem13[n] == 8) {
                string = "P";
            }
            if (this.Elem13[n] == 0) {
                string = "Z";
            }
            if (this.Elem13[n] == 9) {
                string = "T";
            }
            String string2 = "" + this.Elem12[n];
            if (this.Elem12[n] == 7) {
                string2 = "B";
            }
            if (this.Elem12[n] == 8) {
                string2 = "P";
            }
            if (this.Elem12[n] == 0) {
                string2 = "Z";
            }
            if (this.Elem12[n] == 9) {
                string2 = "T";
            }
            String string3 = "" + this.Elem11[n];
            if (this.Elem11[n] == 7) {
                string3 = "B";
            }
            if (this.Elem11[n] == 8) {
                string3 = "P";
            }
            if (this.Elem11[n] == 0) {
                string3 = "Z";
            }
            if (this.Elem11[n] == 9) {
                string3 = "T";
            }
            String string4 = "" + this.Elem10[n];
            if (this.Elem10[n] == 7) {
                string4 = "B";
            }
            if (this.Elem10[n] == 8) {
                string4 = "P";
            }
            if (this.Elem10[n] == 0) {
                string4 = "Z";
            }
            if (this.Elem10[n] == 9) {
                string4 = "T";
            }
            String string5 = "" + this.Elem9[n];
            if (this.Elem9[n] == 7) {
                string5 = "B";
            }
            if (this.Elem9[n] == 8) {
                string5 = "P";
            }
            if (this.Elem9[n] == 0) {
                string5 = "Z";
            }
            if (this.Elem9[n] == 9) {
                string5 = "T";
            }
            String string6 = "" + this.Elem8[n];
            if (this.Elem8[n] == 7) {
                string6 = "B";
            }
            if (this.Elem8[n] == 8) {
                string6 = "P";
            }
            if (this.Elem8[n] == 0) {
                string6 = "Z";
            }
            if (this.Elem8[n] == 9) {
                string6 = "T";
            }
            String string7 = "" + this.Elem7[n];
            if (this.Elem7[n] == 7) {
                string7 = "B";
            }
            if (this.Elem7[n] == 8) {
                string7 = "P";
            }
            if (this.Elem7[n] == 0) {
                string7 = "Z";
            }
            if (this.Elem7[n] == 9) {
                string7 = "T";
            }
            String string8 = "" + this.Elem6[n];
            if (this.Elem6[n] == 7) {
                string8 = "B";
            }
            if (this.Elem6[n] == 8) {
                string8 = "P";
            }
            if (this.Elem6[n] == 0) {
                string8 = "Z";
            }
            if (this.Elem6[n] == 9) {
                string8 = "T";
            }
            String string9 = "" + this.Elem5[n];
            if (this.Elem5[n] == 7) {
                string9 = "B";
            }
            if (this.Elem5[n] == 8) {
                string9 = "P";
            }
            if (this.Elem5[n] == 0) {
                string9 = "Z";
            }
            if (this.Elem5[n] == 9) {
                string9 = "T";
            }
            String string10 = "" + this.Elem4[n];
            if (this.Elem4[n] == 7) {
                string10 = "B";
            }
            if (this.Elem4[n] == 8) {
                string10 = "P";
            }
            if (this.Elem4[n] == 0) {
                string10 = "Z";
            }
            if (this.Elem4[n] == 9) {
                string10 = "T";
            }
            String string11 = "" + this.Elem3[n];
            if (this.Elem3[n] == 7) {
                string11 = "B";
            }
            if (this.Elem3[n] == 8) {
                string11 = "P";
            }
            if (this.Elem3[n] == 0) {
                string11 = "Z";
            }
            if (this.Elem3[n] == 9) {
                string11 = "T";
            }
            String string12 = "" + this.Elem2[n];
            if (this.Elem2[n] == 7) {
                string12 = "B";
            }
            if (this.Elem2[n] == 8) {
                string12 = "P";
            }
            if (this.Elem2[n] == 0) {
                string12 = "Z";
            }
            if (this.Elem2[n] == 9) {
                string12 = "T";
            }
            String string13 = "" + this.Elem1[n];
            if (this.Elem1[n] == 7) {
                string13 = "B";
            }
            if (this.Elem1[n] == 8) {
                string13 = "P";
            }
            if (this.Elem1[n] == 0) {
                string13 = "Z";
            }
            if (this.Elem1[n] == 9) {
                string13 = "T";
            }
            String string14 = "" + this.Elem14[n];
            if (this.Elem14[n] == 7) {
                string14 = "B";
            }
            if (this.Elem14[n] == 8) {
                string14 = "P";
            }
            if (this.Elem14[n] == 0) {
                string14 = "Z";
            }
            if (this.Elem14[n] == 9) {
                string14 = "T";
            }
            if (this.iSP > 577) {
                this.iSP = 95;
            }
            if (this.jSP > 360) {
                this.jSP = 87;
            }
            graphics.setColor(Color.black);
            graphics.drawString(string, this.iSP + 1, this.jSP);
            graphics.drawString(string2, this.iSP + 1, this.jSP + 20);
            graphics.drawString(string3, this.iSP + 1, this.jSP + 40);
            graphics.drawString(string4, this.iSP + 1, this.jSP + 60);
            graphics.drawString(string5, this.iSP + 1, this.jSP + 80);
            graphics.drawString(string6, this.iSP + 1, this.jSP + 100);
            graphics.drawString(string7, this.iSP + 1, this.jSP + 120);
            graphics.drawString(string8, this.iSP + 1, this.jSP + 140);
            graphics.drawString(string9, this.iSP + 1, this.jSP + 160);
            graphics.drawString(string10, this.iSP + 1, this.jSP + 180);
            graphics.drawString(string11, this.iSP + 1, this.jSP + 200);
            graphics.drawString(string12, this.iSP + 1, this.jSP + 220);
            graphics.drawString(string13, this.iSP + 1, this.jSP + 240);
            graphics.drawString(string14, this.iSP + 1, this.jSP + 260);
            graphics.setColor(Color.yellow);
            if (this.Elem13[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem13[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem13[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem13[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string, this.iSP, this.jSP - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem12[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem12[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem12[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem12[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string2, this.iSP, this.jSP + 20 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem11[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem11[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem11[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem11[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string3, this.iSP, this.jSP + 40 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem10[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem10[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem10[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem10[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string4, this.iSP, this.jSP + 60 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem9[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem9[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem9[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem9[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string5, this.iSP, this.jSP + 80 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem8[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem8[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem8[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem8[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string6, this.iSP, this.jSP + 100 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem7[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem7[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem7[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem7[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string7, this.iSP, this.jSP + 120 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem6[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem6[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem6[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem6[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string8, this.iSP, this.jSP + 140 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem5[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem5[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem5[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem5[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string9, this.iSP, this.jSP + 160 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem4[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem4[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem4[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem4[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string10, this.iSP, this.jSP + 180 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem3[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem3[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem3[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem3[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string11, this.iSP, this.jSP + 200 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem2[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem2[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem2[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem2[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string12, this.iSP, this.jSP + 220 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem1[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem1[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem1[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem1[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string13, this.iSP, this.jSP + 240 - 1);
            graphics.setColor(Color.yellow);
            if (this.Elem14[n] == 7) {
                graphics.setColor(Color.white);
            }
            if (this.Elem14[n] == 8) {
                graphics.setColor(Color.orange);
            }
            if (this.Elem14[n] == 0) {
                graphics.setColor(Color.red);
            }
            if (this.Elem14[n] == 9) {
                graphics.setColor(Color.magenta);
            }
            graphics.drawString(string14, this.iSP, this.jSP + 260 - 1);
            this.iSP += 20;
        } while (++n < 25);
        this.HiddenLevels();
        this.Mesto = this.OldMesto;
        this.PrintMarker();
    }
    
    public void PrintMarkerLevo() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        graphics.setColor(Color.yellow);
        if (this.Mesto == 1) {
            this.LocI = 1;
            this.xFa = 115;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 95;
            this.yFa = 347;
            this.SetWinWhiteLeft();
            this.ClearPosition();
            graphics.drawString("X", 97, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 2) {
            this.LocI = 2;
            this.xFa = 135;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 115;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 117, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 3) {
            this.LocI = 3;
            this.xFa = 155;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 135;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 137, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 4) {
            this.LocI = 4;
            this.xFa = 175;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 155;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 157, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 5) {
            this.LocI = 5;
            this.xFa = 195;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 175;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 177, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 6) {
            this.LocI = 6;
            this.xFa = 215;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 195;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 197, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 7) {
            this.LocI = 7;
            this.xFa = 235;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 215;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 217, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 8) {
            this.LocI = 8;
            this.xFa = 255;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 235;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 237, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 9) {
            this.LocI = 9;
            this.xFa = 275;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 255;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 257, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 10) {
            this.LocI = 10;
            this.xFa = 295;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 275;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 277, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 11) {
            this.LocI = 11;
            this.xFa = 315;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 295;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 297, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 12) {
            this.LocI = 12;
            this.xFa = 335;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 315;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 317, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 13) {
            this.LocI = 13;
            this.xFa = 355;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 335;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 337, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 14) {
            this.LocI = 14;
            this.xFa = 375;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 355;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 357, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 15) {
            this.LocI = 15;
            this.xFa = 395;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 375;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 377, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 16) {
            this.LocI = 16;
            this.xFa = 415;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 395;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 397, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 17) {
            this.LocI = 17;
            this.xFa = 435;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 415;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 417, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 18) {
            this.LocI = 18;
            this.xFa = 455;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 435;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 437, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 19) {
            this.LocI = 19;
            this.xFa = 475;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 455;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 457, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 20) {
            this.LocI = 20;
            this.xFa = 495;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 475;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 477, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 21) {
            this.LocI = 21;
            this.xFa = 515;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 495;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 497, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 22) {
            this.LocI = 22;
            this.xFa = 535;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 515;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 517, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 23) {
            this.LocI = 23;
            this.xFa = 555;
            this.yFa = 347;
            this.SetWinBlack();
            this.ClearPosition();
            this.xFa = 535;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 537, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
        if (this.Mesto == 24) {
            this.LocI = 24;
            this.xFa = 575;
            this.yFa = 347;
            this.ClearPosition();
            this.xFa = 555;
            this.yFa = 347;
            this.SetWinWhite();
            this.ClearPosition();
            graphics.drawString("X", 557, 346);
            this.OldMesto = this.Mesto;
            this.PrintLevel1PositionLeft();
        }
    }
    
    public void PrText() {
        final Graphics graphics = this.getGraphics();
        this.hours = new Date().getHours();
        this.NewLevels();
        graphics.setFont(new Font("Arial", 1, 10));
        graphics.setColor(Color.black);
        graphics.drawString("Level", 56, 64);
        graphics.setColor(Color.yellow);
        graphics.drawString("Level", 55, 63);
        graphics.setColor(Color.black);
        graphics.drawString("Counter", 606, 184);
        graphics.setColor(Color.yellow);
        graphics.drawString("Counter", 605, 183);
        final String string = "" + this.NCounter;
        graphics.setColor(Color.black);
        graphics.drawString(string, 608, 204);
        graphics.setColor(Color.yellow);
        graphics.drawString(string, 607, 203);
    }
    
    public void SetWinWhiteLeft() {
        final Graphics graphics = this.getGraphics();
        this.Le[0] = this.xFa - 5 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
        this.Le[0] = this.xFa - 5 + 20 + 2;
        this.Lf[0] = this.yFa - 16 - 1;
        this.Le[1] = this.Le[0] - 2;
        this.Lf[1] = this.Lf[0] + 2;
        this.Le[2] = this.Le[0] - 2;
        this.Lf[2] = this.Lf[0] + 20;
        this.Le[3] = this.Le[0] + 16;
        this.Lf[3] = this.Lf[0] + 20;
        this.Le[4] = this.Le[0] + 18;
        this.Lf[4] = this.Lf[0] + 18;
        this.Le[5] = this.Le[0] + 18;
        this.Lf[5] = this.Lf[0];
        graphics.setColor(Color.white);
        graphics.drawPolygon(this.Le, this.Lf, 6);
    }
}
