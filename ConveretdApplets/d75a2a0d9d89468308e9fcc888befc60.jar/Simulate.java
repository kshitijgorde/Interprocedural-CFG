import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Simulate
{
    Moon[] luna;
    Moon[] showluna;
    Point temp;
    int counter;
    int display_count;
    int work;
    int firstmass;
    double inc;
    double init_energy;
    double display_num;
    boolean display_energy;
    boolean no_perspective;
    double energy;
    BVector polyIn;
    BVector poly;
    Point[] polyAccel;
    Point[] polyPosition;
    
    public Simulate(final Moon[] luna, final double inc, final int work, final boolean display_energy, final boolean no_perspective) {
        this.temp = new Point(0.0, 0.0, 0.0);
        this.luna = luna;
        this.counter = 0;
        this.work = work;
        this.inc = inc;
        this.display_energy = display_energy;
        this.no_perspective = no_perspective;
        this.poly = new BVector(Moon.dejit.length);
        this.polyIn = new BVector(Moon.dejit.length);
        this.polyAccel = new Point[Moon.dejit.length];
        this.polyPosition = new Point[Moon.dejit.length + 2];
        for (int i = 0; i < Moon.dejit.length; ++i) {
            this.polyAccel[i] = new Point(0.0, 0.0, 0.0);
            this.polyPosition[i + 2] = new Point(0.0, 0.0, 0.0);
        }
        this.polyPosition[0] = new Point(0.0, 0.0, 0.0);
        this.polyPosition[1] = new Point(0.0, 0.0, 0.0);
        for (int j = 0; j < luna.length; ++j) {
            for (int k = j + 1; k < luna.length; ++k) {
                if (luna[j].m > luna[k].m) {
                    final Moon moon = luna[j];
                    luna[j] = luna[k];
                    luna[k] = moon;
                }
            }
        }
        this.firstmass = 0;
        while (this.firstmass < luna.length && luna[this.firstmass].m == 0.0) {
            ++this.firstmass;
        }
        this.showluna = new Moon[luna.length];
        for (int l = 0; l < luna.length; ++l) {
            this.showluna[l] = luna[l];
        }
        this.getGoing();
    }
    
    private final void getGoing() {
        this.center();
        this.inc /= this.work * 65536.0 * 65536.0;
        for (int i = 0; i < this.luna.length; ++i) {
            this.luna[i].v.scale(-this.inc);
        }
        this.moveToNewPoint();
        for (int j = 0; j < this.luna.length; ++j) {
            final Moon moon = this.luna[j];
            moon.ov[moon.head].plusa(moon.ov[moon.head], 0.5 * this.inc * this.inc, moon.oa[moon.head]);
        }
        for (int k = 0; k < 9; ++k) {
            this.leapfrog();
        }
        for (int l = 0; l < 32; ++l) {
            this.DoubleStep();
        }
        for (int n = 0; n < this.luna.length; ++n) {
            final Moon moon2 = this.luna[n];
            if (this.counter > 20) {
                this.counter = 20;
            }
            for (int n2 = 0; n2 < this.counter; ++n2) {
                if (n2 < this.counter - 1) {
                    moon2.p.minus(moon2.p, moon2.ov[moon2.head + n2]);
                    moon2.nov[moon2.head + n2].copy(moon2.ov[moon2.head + this.counter - n2 - 2]);
                }
                moon2.nov[moon2.head + n2].scale(-1.0);
                moon2.noa[moon2.head + n2].copy(moon2.oa[moon2.head + this.counter - n2 - 1]);
            }
            final Point[] nov = moon2.nov;
            moon2.nov = moon2.ov;
            moon2.ov = nov;
            final Point[] noa = moon2.noa;
            moon2.noa = moon2.oa;
            moon2.oa = noa;
        }
        this.display_num = 0.0;
        this.init_energy = this.FindEnergy();
    }
    
    private final void center() {
        double n = 0.0;
        final Point point = new Point(0.0, 0.0, 0.0);
        final Point point2 = new Point(0.0, 0.0, 0.0);
        for (int i = 0; i < this.luna.length; ++i) {
            final Moon moon = this.luna[i];
            n += moon.m;
            point2.plusa(point2, moon.m, moon.v);
            point.plusa(point, moon.m, moon.p);
        }
        for (int j = 0; j < this.luna.length; ++j) {
            final Moon moon2 = this.luna[j];
            moon2.v.plusa(moon2.v, -1.0 / n, point2);
            moon2.p.plusa(moon2.p, -1.0 / n, point);
        }
    }
    
    private final void moveToNewPoint() {
        this.accel();
        ++this.counter;
        for (int i = 0; i < this.luna.length; ++i) {
            final Moon moon2;
            final Moon moon = moon2 = this.luna[i];
            if (--moon2.head < 0) {
                final Moon moon3 = moon;
                moon3.head += 20;
            }
            moon.ov[moon.head].copy(moon.v);
            moon.oa[moon.head].copy(moon.a);
        }
    }
    
    private final void step() {
        if ((this.counter & 0x7) == 0x0) {
            this.dejitter(1.0);
        }
        for (int i = 0; i < this.luna.length; ++i) {
            this.luna[i].step(this.inc, this.temp);
        }
    }
    
    private final void leapfrog() {
        for (int i = 0; i < this.luna.length; ++i) {
            final Moon moon = this.luna[i];
            moon.v.plusa(moon.ov[moon.head], this.inc * this.inc, moon.oa[moon.head]);
            moon.p.plus(moon.p, moon.v);
        }
        this.moveToNewPoint();
    }
    
    private final void DoubleStep() {
        while (this.counter < 20) {
            for (int i = 0; i < this.luna.length; ++i) {
                this.luna[i].step(this.inc, this.temp);
            }
            this.moveToNewPoint();
        }
        if (this.counter > 20) {
            this.counter = 20;
        }
        for (int j = 0; j < this.luna.length; ++j) {
            final Moon moon = this.luna[j];
            for (int k = 0; k < this.counter; k += 2) {
                moon.nov[moon.head + k / 2].plus(moon.ov[moon.head + k], moon.ov[moon.head + k + 1]);
                moon.noa[moon.head + k / 2].copy(moon.oa[moon.head + k]);
            }
            final Point[] nov = moon.nov;
            moon.nov = moon.ov;
            moon.ov = nov;
            final Point[] noa = moon.noa;
            moon.noa = moon.oa;
            moon.oa = noa;
        }
        this.counter /= 2;
        this.inc *= 2.0;
    }
    
    private final void dejitter(final double n) {
        for (int i = 0; i < this.luna.length; ++i) {
            final Moon moon = this.luna[i];
            this.temp.minus(moon.ov[moon.head], moon.ov[moon.head + 1]);
            this.temp.plusa(this.temp, -this.inc * this.inc, moon.oa[moon.head + 1]);
            final double dot = this.temp.dot(this.temp);
            final double dot2 = moon.oa[moon.head + 1].dot(moon.oa[moon.head + 1]);
            if (dot > dot2 * this.inc * this.inc / 256.0 && dot2 != 0.0) {
                moon.dejitter(n, this.inc, this.polyIn, this.poly, this.polyAccel, this.polyPosition);
            }
        }
    }
    
    public final void move() {
        for (int i = 0; i < this.work; ++i) {
            this.step();
            this.moveToNewPoint();
        }
    }
    
    private double FindEnergy() {
        double n = 0.0;
        for (int i = this.firstmass; i < this.luna.length; ++i) {
            final Moon moon = this.luna[i];
            double n2 = 0.0;
            for (int j = i + 1; j < this.luna.length; ++j) {
                final Moon moon2 = this.luna[j];
                this.temp.minus(moon2.p, moon.p);
                for (int k = 0; k < 4; ++k) {
                    this.temp.minus(this.temp, moon2.ov[moon2.head + k]);
                    this.temp.plus(this.temp, moon.ov[moon.head + k]);
                }
                n2 += moon2.m / Math.sqrt(this.temp.dot(this.temp));
            }
            moon.velocity(this.inc, this.temp);
            n += moon.m * (0.5 * moon.v.dot(moon.v) - n2);
        }
        return n;
    }
    
    private void accel() {
        for (int i = 0; i < this.luna.length; ++i) {
            this.luna[i].a.zero();
        }
        for (int j = 0; j < this.firstmass; ++j) {
            final Moon moon = this.luna[j];
            for (int k = this.firstmass; k < this.luna.length; ++k) {
                final Moon moon2 = this.luna[k];
                this.temp.minus(moon2.p, moon.p);
                final double dot = this.temp.dot(this.temp);
                moon.a.plusa(moon.a, moon2.m * (1.0 / (dot * Math.sqrt(dot))), this.temp);
            }
        }
        for (int l = this.firstmass; l < this.luna.length; ++l) {
            final Moon moon3 = this.luna[l];
            for (int n = l + 1; n < this.luna.length; ++n) {
                final Moon moon4 = this.luna[n];
                this.temp.minus(moon4.p, moon3.p);
                final double dot2 = this.temp.dot(this.temp);
                final double n2 = 1.0 / (dot2 * Math.sqrt(dot2));
                moon3.a.plusa(moon3.a, moon4.m * n2, this.temp);
                moon4.a.plusa(moon4.a, -moon3.m * n2, this.temp);
            }
        }
    }
    
    public void clear(final Graphics graphics) {
        for (int i = 0; i < this.luna.length; ++i) {
            final int screenr = this.luna[i].screenr;
            if (screenr > 2) {
                graphics.fillOval(this.luna[i].screenx, this.luna[i].screeny, screenr, screenr);
            }
            else {
                graphics.fillRect(this.luna[i].screenx, this.luna[i].screeny, this.luna[i].screenx + screenr, this.luna[i].screeny + screenr);
            }
        }
    }
    
    public void draw(final Graphics graphics, final Eye eye) {
        ++this.display_count;
        for (int i = 0; i < this.showluna.length; ++i) {
            final Moon moon = this.showluna[i];
            final Point peye = moon.peye;
            eye.map(peye, moon.p);
            if (this.no_perspective) {
                peye.z = -eye.m[3].z;
            }
            if (peye.z > moon.r) {
                int screenr = (int)(eye.magnification * moon.r / peye.z);
                if (screenr < 1) {
                    screenr = 1;
                }
                moon.screenr = screenr;
                moon.screenx = eye.mapx(peye) - screenr / 2;
                moon.screeny = eye.mapy(peye) - screenr / 2;
                if (moon.screenx + screenr < 0 || moon.screeny + screenr < 0 || moon.screenx > eye.centerx + eye.centerx || moon.screeny > eye.centery + eye.centery) {
                    final Moon moon2 = moon;
                    final Moon moon3 = moon;
                    final Moon moon4 = moon;
                    final boolean screenx = false;
                    moon4.screenr = (screenx ? 1 : 0);
                    moon3.screeny = (screenx ? 1 : 0);
                    moon2.screenx = (screenx ? 1 : 0);
                }
            }
            else {
                final Moon moon5 = moon;
                final Moon moon6 = moon;
                final Moon moon7 = moon;
                final boolean screenx2 = false;
                moon7.screenr = (screenx2 ? 1 : 0);
                moon6.screeny = (screenx2 ? 1 : 0);
                moon5.screenx = (screenx2 ? 1 : 0);
            }
        }
        int length = this.showluna.length;
        while (--length > 0) {
            int n = length + 1;
            while (--n > 0 && this.showluna[n].peye.z > this.showluna[n - 1].peye.z) {
                final Moon moon8 = this.showluna[n];
                this.showluna[n] = this.showluna[n - 1];
                this.showluna[n - 1] = moon8;
            }
        }
        if (this.display_energy) {
            graphics.setColor(Color.black);
            graphics.drawString(this.display_count - 1 + " " + this.display_num, 2, 12);
            if ((this.display_count & 0x3F) == 0x0) {
                this.display_num = (this.FindEnergy() - this.init_energy) / this.init_energy;
            }
            graphics.setColor(Color.white);
            graphics.drawString(this.display_count + " " + this.display_num, 2, 12);
        }
        for (int j = 0; j < this.showluna.length; ++j) {
            final int screenr2 = this.showluna[j].screenr;
            if (screenr2 > 2) {
                graphics.setColor(this.showluna[j].c);
                graphics.fillOval(this.showluna[j].screenx, this.showluna[j].screeny, screenr2, screenr2);
            }
            else if (screenr2 > 0) {
                graphics.setColor(this.showluna[j].c);
                graphics.fillRect(this.showluna[j].screenx, this.showluna[j].screeny, screenr2, screenr2);
            }
        }
    }
}
