import java.awt.Event;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class SpielCanvas extends Canvas
{
    int feldbreite;
    int feldhoehe;
    int ikonenbreite;
    int ikonenhoehe;
    int balkenbreite;
    int balkenhoehe;
    int unbalkenbreite;
    int balkenx;
    int balkeny;
    int punktex;
    int punktey;
    int unpunktex;
    int unpunktey;
    int unpunktebreite;
    int unpunktehoehe;
    int loeschpunkteabstand;
    int startzeit;
    int wartezeit;
    int dropzeit;
    int nachspielzeit;
    int istschleifezeit;
    int loeschzeit;
    int abbruchzeit;
    int lr;
    int lu;
    int lo;
    int ro;
    int ru;
    int wg;
    int sk;
    int hell;
    int oben;
    int unten;
    int links;
    int rechts;
    int[][] ikonenausgang;
    int eingang;
    int ausgang;
    int[][] delta;
    int deltax;
    int deltay;
    int[] anderes;
    int[][][] groesze;
    int breite;
    int hoehe;
    int[][][][] teil;
    int[][] feld;
    int teilx;
    int teily;
    int teild;
    int teiln;
    int unteilx;
    int unteily;
    int unteild;
    int unteiln;
    int zeit;
    int punkte;
    int teilchen;
    int zuletztloeschpunkte;
    double_ applet;
    boolean catched;
    
    SpielCanvas(final double_ applet) {
        this.feldbreite = 20;
        this.feldhoehe = 10;
        this.balkenhoehe = 16;
        this.loeschpunkteabstand = 100;
        this.startzeit = 4000;
        this.wartezeit = 100;
        this.dropzeit = this.startzeit - 200;
        this.nachspielzeit = 2000;
        this.istschleifezeit = 25;
        this.loeschzeit = 100;
        this.abbruchzeit = -1;
        this.lu = 1;
        this.lo = 2;
        this.ro = 3;
        this.ru = 4;
        this.wg = 5;
        this.sk = 6;
        this.hell = 6;
        this.unten = 1;
        this.links = 2;
        this.rechts = 3;
        this.ikonenausgang = new int[][] { { this.oben, this.rechts }, { this.unten, this.rechts }, { this.links, this.unten }, { this.links, this.oben }, { this.links, this.rechts }, { this.oben, this.unten } };
        this.ausgang = 1;
        this.delta = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
        this.deltay = 1;
        this.anderes = new int[] { this.unten, this.oben, this.rechts, this.links };
        this.groesze = new int[][][] { { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } }, { { 1, 2 }, { 2, 1 } }, { { 2, 1 }, { 1, 2 } }, { { 2, 1 }, { 1, 2 } }, { { 2, 1 }, { 1, 2 }, { 2, 1 }, { 1, 2 } }, { { 2, 1 }, { 1, 2 }, { 2, 1 }, { 1, 2 } }, { { 2, 1 }, { 1, 2 }, { 2, 1 }, { 1, 2 } }, { { 2, 2 } }, { { 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 } }, { { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 } }, { { 2, 2 }, { 2, 2 }, { 2, 2 }, { 2, 2 } } };
        this.hoehe = 1;
        this.teil = new int[][][][] { { { { this.lo, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ro, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ru, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lu, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.sk, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.wg, this.lr, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.sk, this.sk, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.wg, this.lr, this.lr }, { this.wg, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lu, this.lr, this.lr }, { this.ro, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lo, this.ru, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.lr, this.lr }, { this.ru, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ro, this.lu, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.lr, this.lr }, { this.ro, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ro, this.ru, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lu, this.lr, this.lr }, { this.ru, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lo, this.lu, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.lr, this.lr }, { this.wg, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ro, this.sk, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.wg, this.lr, this.lr }, { this.ru, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.sk, this.lu, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lu, this.lr, this.lr }, { this.wg, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lo, this.sk, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.wg, this.lr, this.lr }, { this.ro, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.sk, this.ru, this.lr }, { this.lr, this.lr, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.lu, this.lr }, { this.ro, this.ru, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.ru, this.lr }, { this.ro, this.lu, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lu, this.lo, this.lr }, { this.ro, this.ru, this.lr }, { this.lr, this.lr, this.lr } }, { { this.ro, this.lu, this.lr }, { this.lo, this.ru, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lo, this.lu, this.lr }, { this.ru, this.ro, this.lr }, { this.lr, this.lr, this.lr } } }, { { { this.lo, this.sk, this.lu }, { this.wg, this.lr, this.lr }, { this.ro, this.lr, this.lr } }, { { this.lo, this.lr, this.lr }, { this.wg, this.lr, this.lr }, { this.ro, this.sk, this.ru } }, { { this.lr, this.lr, this.lu }, { this.lr, this.lr, this.wg }, { this.ro, this.sk, this.ru } }, { { this.lo, this.sk, this.lu }, { this.lr, this.lr, this.wg }, { this.lr, this.lr, this.ru } } }, { { { this.lo, this.sk, this.lr }, { this.wg, this.lr, this.lr }, { this.lr, this.lr, this.lr } }, { { this.wg, this.lr, this.lr }, { this.ro, this.sk, this.lr }, { this.lr, this.lr, this.lr } }, { { this.lr, this.wg, this.lr }, { this.sk, this.ru, this.lr }, { this.lr, this.lr, this.lr } }, { { this.sk, this.lu, this.lr }, { this.lr, this.wg, this.lr }, { this.lr, this.lr, this.lr } } } };
        this.feld = new int[this.feldbreite][this.feldhoehe];
        this.applet = applet;
        this.punkte = applet.ohnepunkte;
        this.ikonenbreite = applet.bild[this.lr].getWidth(null);
        this.ikonenhoehe = applet.bild[this.lr].getHeight(null);
        applet.appletbreite = this.feldbreite * this.ikonenbreite;
        applet.applethoehe = this.feldhoehe * this.ikonenhoehe + this.balkenhoehe + applet.doublebildhoehe + applet.doublebildvspace * 2;
        this.balkenx = 0;
        this.balkeny = this.feldhoehe * this.ikonenhoehe;
        this.balkenbreite = applet.appletbreite;
        this.unbalkenbreite = this.balkenbreite * this.wartezeit / this.startzeit;
        applet.doublebildx = (applet.appletbreite - applet.doublebildbreite) / 2;
        applet.doublebildmaxy = applet.applethoehe - applet.doublebildvspace - applet.doublebildhoehe;
        this.unpunktex = applet.doublebildx + 140;
        this.unpunktey = applet.doublebildmaxy + 15;
        this.unpunktebreite = applet.fontmetrics.stringWidth(applet.maxpunkte + applet.unloeschbarstring);
        this.unpunktehoehe = applet.fonthoehe;
        this.punktex = this.unpunktex;
        this.punktey = this.unpunktey + applet.fontaufstieg;
        this.setFont(applet.pearlfont);
        this.setForeground(applet.gelb);
        this.setBackground(applet.schwarz);
    }
    
    public void paint(final Graphics graphics) {
        this.FeldZeichnen(graphics);
        this.TeilZeichnen(graphics);
        this.BalkenZeichnen(graphics);
        this.applet.DoublebildZeichnen(graphics, this.applet.doublebildmaxy);
        this.PunkteZeichnen(graphics);
    }
    
    void FeldZeichnen(final Graphics graphics) {
        for (int i = 0; i < this.feldhoehe; ++i) {
            for (int j = 0; j < this.feldbreite; ++j) {
                graphics.drawImage(this.applet.bild[this.feld[j][i]], j * this.ikonenbreite, i * this.ikonenhoehe, null);
            }
        }
    }
    
    void TeilZeichnen(final Graphics graphics) {
        for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
            for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                final int n;
                if ((n = this.teil[this.teiln][this.teild][j][i]) != this.lr) {
                    graphics.drawImage(this.applet.bild[n + this.hell], (this.teilx + j) * this.ikonenbreite, (this.teily + i) * this.ikonenhoehe, null);
                }
            }
        }
    }
    
    void TeilUnzeichnen(final Graphics graphics) {
        for (int i = 0; i < this.groesze[this.unteiln][this.unteild][this.hoehe]; ++i) {
            for (int j = 0; j < this.groesze[this.unteiln][this.unteild][this.breite]; ++j) {
                if (this.teil[this.unteiln][this.unteild][j][i] != this.lr) {
                    graphics.drawImage(this.applet.bild[this.feld[this.unteilx + j][this.unteily + i]], (this.unteilx + j) * this.ikonenbreite, (this.unteily + i) * this.ikonenhoehe, null);
                }
            }
        }
    }
    
    void IkoneUnzeichnen(final Graphics graphics) {
        graphics.drawImage(this.applet.bild[this.lr], this.unteilx * this.ikonenbreite, this.unteily * this.ikonenhoehe, null);
    }
    
    void BalkenZeichnen(final Graphics graphics) {
        graphics.fillRect(this.balkenx, this.balkeny, this.balkenbreite * this.zeit / this.startzeit, this.balkenhoehe);
    }
    
    void BalkenUnzeichnen(final Graphics graphics) {
        graphics.clearRect(this.balkenx + this.balkenbreite * this.zeit / this.startzeit, this.balkeny, this.unbalkenbreite, this.balkenhoehe);
    }
    
    void PunkteZeichnen(final Graphics graphics) {
        if (this.punkte >= this.zuletztloeschpunkte + this.loeschpunkteabstand) {
            graphics.drawString(this.punkte + this.applet.punktestring, this.punktex, this.punktey);
            return;
        }
        graphics.drawString(this.punkte + this.applet.unloeschbarstring, this.punktex, this.punktey);
    }
    
    void PunkteUnzeichnen(final Graphics graphics) {
        graphics.clearRect(this.unpunktex, this.unpunktey, this.unpunktebreite, this.unpunktehoehe);
    }
    
    void NeuesFeld() {
        for (int i = 0; i < this.feldhoehe; ++i) {
            for (int j = 0; j < this.feldbreite; ++j) {
                this.feld[j][i] = this.lr;
            }
        }
        this.teilchen = 0;
    }
    
    void NeuesTeil() {
        this.teiln = this.applet.Zufall(this.teil.length);
        this.teild = this.applet.Zufall(this.teil[this.teiln].length);
        this.teilx = this.applet.Zufall(this.feldbreite - this.groesze[this.teiln][this.teild][this.breite] + 1);
        this.teily = this.applet.Zufall(this.feldhoehe - this.groesze[this.teiln][this.teild][this.hoehe] + 1);
        this.zeit = this.startzeit;
    }
    
    void BewegeTeil(final int teilx, final int teily) {
        this.unteiln = this.teiln;
        this.unteild = this.teild;
        this.unteilx = this.teilx;
        this.unteily = this.teily;
        if (teilx <= 0) {
            this.teilx = 0;
        }
        else if (teilx + this.groesze[this.teiln][this.teild][this.breite] >= this.feldbreite) {
            this.teilx = this.feldbreite - this.groesze[this.teiln][this.teild][this.breite];
        }
        else {
            this.teilx = teilx;
        }
        if (teily <= 0) {
            this.teily = 0;
        }
        else if (teily + this.groesze[this.teiln][this.teild][this.hoehe] >= this.feldhoehe) {
            this.teily = this.feldhoehe - this.groesze[this.teiln][this.teild][this.hoehe];
        }
        else {
            this.teily = teily;
        }
        if (this.teilx != this.unteilx || this.teily != this.unteily) {
            this.TeilUnzeichnen(this.getGraphics());
            this.TeilZeichnen(this.getGraphics());
        }
    }
    
    void DrehTeil() {
        this.unteiln = this.teiln;
        this.unteild = this.teild;
        this.unteilx = this.teilx;
        this.unteily = this.teily;
        final int teild = ++this.teild % this.teil[this.teiln].length;
        this.teild = teild;
        if (teild != this.unteild) {
            if (this.teilx + this.groesze[this.teiln][this.teild][this.breite] >= this.feldbreite) {
                this.teilx = this.feldbreite - this.groesze[this.teiln][this.teild][this.breite];
            }
            if (this.teily + this.groesze[this.teiln][this.teild][this.hoehe] >= this.feldhoehe) {
                this.teily = this.feldhoehe - this.groesze[this.teiln][this.teild][this.hoehe];
            }
            this.TeilUnzeichnen(this.getGraphics());
            this.TeilZeichnen(this.getGraphics());
        }
    }
    
    boolean GehtEs() {
        for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
            for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                if (this.teil[this.teiln][this.teild][j][i] != this.lr && this.feld[this.teilx + j][this.teily + i] != this.lr) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void IstSchleife() {
        int j = 0;
        int n = 0;
        int i = 0;
    Label_0096:
        for (i = (n = (j = 0)); j < this.groesze[this.unteiln][this.unteild][this.hoehe]; ++j) {
            for (i = 0; i < this.groesze[this.unteiln][this.unteild][this.breite]; ++i) {
                if ((n = this.teil[this.unteiln][this.unteild][i][j]) != this.lr) {
                    break Label_0096;
                }
            }
        }
        final int n2;
        int unteilx = n2 = i + this.unteilx;
        final int n3;
        int unteily = n3 = j + this.unteily;
        int n4 = this.ikonenausgang[n - this.lu][this.eingang];
        do {
            unteilx += this.delta[n4][this.deltax];
            unteily += this.delta[n4][this.deltay];
            final int n5;
            if (unteilx < 0 || unteilx >= this.feldbreite || unteily < 0 || unteily >= this.feldhoehe || (n5 = this.feld[unteilx][unteily]) == this.lr) {
                return;
            }
            if (this.anderes[this.ikonenausgang[n5 - this.lu][this.eingang]] == n4) {
                n4 = this.ikonenausgang[n5 - this.lu][this.ausgang];
            }
            else {
                if (this.anderes[this.ikonenausgang[n5 - this.lu][this.ausgang]] != n4) {
                    return;
                }
                n4 = this.ikonenausgang[n5 - this.lu][this.eingang];
            }
        } while (unteilx != n2 || unteily != n3);
        do {
            unteilx += this.delta[n4][this.deltax];
            unteily += this.delta[n4][this.deltay];
            final int n6 = this.feld[unteilx][unteily];
            this.feld[unteilx][unteily] = this.lr;
            if (++this.punkte > this.applet.maxpunkte) {
                this.punkte = this.applet.maxpunkte;
            }
            --this.teilchen;
            this.PunkteUnzeichnen(this.getGraphics());
            this.PunkteZeichnen(this.getGraphics());
            this.unteilx = unteilx;
            this.unteily = unteily;
            this.IkoneUnzeichnen(this.getGraphics());
            try {
                synchronized (this.applet.object) {
                    this.applet.object.wait(this.istschleifezeit);
                }
                // monitorexit(this.applet.object)
            }
            catch (InterruptedException ex) {}
            if (this.anderes[this.ikonenausgang[n6 - this.lu][this.eingang]] == n4) {
                n4 = this.ikonenausgang[n6 - this.lu][this.ausgang];
            }
            else {
                if (this.anderes[this.ikonenausgang[n6 - this.lu][this.ausgang]] != n4) {
                    continue;
                }
                n4 = this.ikonenausgang[n6 - this.lu][this.eingang];
            }
        } while (unteilx != n2 || unteily != n3);
        if (this.teilchen == 0) {
            if ((this.punkte *= 2) > this.applet.maxpunkte) {
                this.punkte = this.applet.maxpunkte;
            }
            this.PunkteUnzeichnen(this.getGraphics());
            this.PunkteZeichnen(this.getGraphics());
        }
    }
    
    void SetzTeil() {
        this.applet.thread.suspend();
        if (this.GehtEs()) {
            for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
                for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                    final int n;
                    if ((n = this.teil[this.teiln][this.teild][j][i]) != this.lr) {
                        this.feld[this.teilx + j][this.teily + i] = n;
                        ++this.teilchen;
                    }
                }
            }
            this.unteiln = this.teiln;
            this.unteild = this.teild;
            this.unteilx = this.teilx;
            this.unteily = this.teily;
            this.TeilUnzeichnen(this.getGraphics());
            this.IstSchleife();
            this.NeuesTeil();
            this.TeilZeichnen(this.getGraphics());
            this.BalkenZeichnen(this.getGraphics());
            synchronized (this.applet.object) {
                this.catched = true;
                this.applet.object.notifyAll();
            }
            // monitorexit(this.applet.object)
        }
        this.applet.thread.resume();
    }
    
    boolean GehtLoeschen() {
        if (this.punkte < this.zuletztloeschpunkte + this.loeschpunkteabstand) {
            return false;
        }
        for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
            for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                if (this.teil[this.teiln][this.teild][j][i] != this.feld[this.teilx + j][this.teily + i]) {
                    return false;
                }
            }
        }
        this.zuletztloeschpunkte = this.punkte;
        return true;
    }
    
    void LoeschTeil() {
        this.applet.thread.suspend();
        if (this.GehtLoeschen()) {
            this.PunkteUnzeichnen(this.getGraphics());
            this.PunkteZeichnen(this.getGraphics());
            for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
                for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                    if (this.teil[this.teiln][this.teild][j][i] != this.lr) {
                        this.feld[this.teilx + j][this.teily + i] = this.lr;
                        --this.teilchen;
                        this.unteilx = this.teilx + j;
                        this.unteily = this.teily + i;
                        this.IkoneUnzeichnen(this.getGraphics());
                        try {
                            synchronized (this.applet.object) {
                                this.applet.object.wait(this.loeschzeit);
                            }
                            // monitorexit(this.applet.object)
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            this.NeuesTeil();
            this.TeilZeichnen(this.getGraphics());
            this.BalkenZeichnen(this.getGraphics());
            synchronized (this.applet.object) {
                this.catched = true;
                this.applet.object.notifyAll();
            }
            // monitorexit(this.applet.object)
        }
        this.applet.thread.resume();
    }
    
    void AutoDrop() {
        if (this.GehtEs()) {
            this.PunkteUnzeichnen(this.getGraphics());
            this.PunkteZeichnen(this.getGraphics());
            for (int i = 0; i < this.groesze[this.teiln][this.teild][this.hoehe]; ++i) {
                for (int j = 0; j < this.groesze[this.teiln][this.teild][this.breite]; ++j) {
                    final int n;
                    if ((n = this.teil[this.teiln][this.teild][j][i]) != this.lr) {
                        this.feld[this.teilx + j][this.teily + i] = n;
                        ++this.teilchen;
                    }
                }
            }
            this.unteiln = this.teiln;
            this.unteild = this.teild;
            this.unteilx = this.teilx;
            this.unteily = this.teily;
            this.TeilUnzeichnen(this.getGraphics());
            this.IstSchleife();
            this.NeuesTeil();
            this.TeilZeichnen(this.getGraphics());
            this.BalkenZeichnen(this.getGraphics());
        }
    }
    
    void Spiel() {
        this.punkte = 0;
        this.zuletztloeschpunkte = -this.loeschpunkteabstand;
        this.NeuesFeld();
        this.NeuesTeil();
        this.applet.cardlayout.show(this.applet, "Spiel");
        this.requestFocus();
        while (this.zeit > 0) {
            while (this.zeit > 0) {
                this.catched = false;
                try {
                    synchronized (this.applet.object) {
                        this.applet.object.wait(this.wartezeit);
                    }
                    // monitorexit(this.applet.object)
                }
                catch (InterruptedException ex) {}
                if (!this.catched) {
                    this.zeit -= this.wartezeit;
                    this.BalkenUnzeichnen(this.getGraphics());
                }
            }
            try {
                synchronized (this.applet.object) {
                    this.applet.object.wait(this.wartezeit);
                }
                // monitorexit(this.applet.object)
            }
            catch (InterruptedException ex2) {}
            if (this.zeit == 0) {
                this.AutoDrop();
            }
        }
        try {
            synchronized (this.applet.object) {
                this.applet.object.wait(this.nachspielzeit);
            }
            // monitorexit(this.applet.object)
        }
        catch (InterruptedException ex3) {}
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.zeit > 0) {
            this.BewegeTeil(n / this.ikonenbreite, n2 / this.ikonenhoehe);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.zeit > 0) {
            if (event.modifiers == 4) {
                this.DrehTeil();
            }
            if (this.zeit <= this.dropzeit) {
                if (event.modifiers == 0) {
                    this.SetzTeil();
                }
                else if (event.modifiers == 8) {
                    this.LoeschTeil();
                }
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.zeit > 0) {
            if (n == 10) {
                this.DrehTeil();
            }
            else if (n == 1006) {
                this.BewegeTeil(this.teilx - 1, this.teily);
            }
            else if (n == 1007) {
                this.BewegeTeil(this.teilx + 1, this.teily);
            }
            else if (n == 1004) {
                this.BewegeTeil(this.teilx, this.teily - 1);
            }
            else if (n == 1005) {
                this.BewegeTeil(this.teilx, this.teily + 1);
            }
            else if (n == 97) {
                this.zeit = this.abbruchzeit;
            }
            if (this.zeit <= this.dropzeit) {
                if (n == 32) {
                    this.SetzTeil();
                }
                else if (n == 101) {
                    this.LoeschTeil();
                }
            }
        }
        return true;
    }
}
