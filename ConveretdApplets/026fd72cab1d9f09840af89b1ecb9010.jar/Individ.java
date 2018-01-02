import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class Individ
{
    String GenCode;
    int AnzSegmente;
    int SumLaenge;
    int SumBeweger;
    public int xPos;
    public int yPos;
    int Winkel;
    int Alter;
    int Energie;
    public Segment[] seg;
    int i;
    int j;
    int maxSegmente;
    int minSegmente;
    int GeneProSegment;
    int maxAlter;
    
    public void newCode() {
        final Random random = new Random();
        this.GenCode = "";
        this.i = 0;
        while (this.i < this.minSegmente + Math.abs(random.nextInt() % (this.maxSegmente - this.minSegmente))) {
            if (this.i == 0) {
                this.GenCode += "00";
            }
            else {
                final int abs = Math.abs(random.nextInt() % this.i);
                if (abs < 10) {
                    this.GenCode += "0";
                }
                this.GenCode += abs;
            }
            this.j = 0;
            while (this.j < 3) {
                this.GenCode += Math.abs(random.nextInt() % 10);
                ++this.j;
            }
            ++this.i;
        }
    }
    
    public void init(final int n, final int n2) {
        final Random random = new Random();
        this.Winkel = Math.abs(random.nextInt() % 360);
        this.maxAlter = 2000 + random.nextInt() % 1000;
        this.AnzSegmente = this.GenCode.length() / this.GeneProSegment;
        this.SumLaenge = 0;
        this.SumBeweger = 0;
        this.seg = new Segment[this.AnzSegmente];
        this.i = 0;
        while (this.i < this.AnzSegmente) {
            this.seg[this.i] = new Segment();
            this.seg[this.i].Vorgaenger = Integer.valueOf(this.GenCode.substring(this.i * this.GeneProSegment, this.i * this.GeneProSegment + 2));
            this.getPositions(this.i);
            this.seg[this.i].Laenge = Integer.valueOf(this.GenCode.substring(this.GeneProSegment * this.i + 2, this.GeneProSegment * this.i + 3));
            this.seg[this.i].UrWinkel = Integer.valueOf(this.GenCode.substring(this.GeneProSegment * this.i + 3, this.GeneProSegment * this.i + 4)) * 36;
            this.seg[this.i].AnzMov = Integer.valueOf(this.GenCode.substring(this.GeneProSegment * this.i + 4, this.GeneProSegment * this.i + 5));
            this.seg[this.i].init(this.Winkel);
            this.SumLaenge += this.seg[this.i].Laenge;
            if (this.seg[this.i].AnzMov > 0) {
                this.SumBeweger += this.seg[this.i].Laenge;
            }
            ++this.i;
        }
        this.xPos = Math.abs(random.nextInt()) % n;
        this.yPos = Math.abs(random.nextInt()) % n2;
        this.Alter = -5;
        this.Energie = 1000;
        this.moveIndiv(n, n2);
    }
    
    public void moveIndiv(final int n, final int n2) {
        int n3 = this.SumBeweger / 20 + this.SumLaenge / 20;
        if (n3 <= 0) {
            n3 = 1;
        }
        this.Energie -= n3;
        if (this.Energie < 0 || this.Alter >= this.maxAlter) {
            this.AnzSegmente = 0;
        }
        this.i = 0;
        while (this.i < this.AnzSegmente) {
            this.seg[this.i].moveSegment(this.Winkel);
            this.getPositions(this.i);
            this.xPos += (this.seg[this.i].xPosAnf - this.seg[this.i].xPosEnd) / 6;
            this.yPos += (this.seg[this.i].yPosAnf - this.seg[this.i].yPosEnd) / 6;
            ++this.i;
        }
        if (this.xPos < 0) {
            this.xPos = n - 1;
        }
        if (this.yPos < 0) {
            this.yPos = n2 - 1;
        }
        if (this.xPos > n) {
            this.xPos = 1;
        }
        if (this.yPos > n2) {
            this.yPos = 1;
        }
    }
    
    void getPositions(final int n) {
        if (n == 0) {
            this.seg[n].xPosAnf = 0;
            this.seg[n].yPosAnf = 0;
            return;
        }
        if (this.seg[n].Vorgaenger < n) {
            this.seg[n].xPosAnf = this.seg[this.seg[n].Vorgaenger].xPosEnd;
            this.seg[n].yPosAnf = this.seg[this.seg[n].Vorgaenger].yPosEnd;
            return;
        }
        this.seg[n].xPosAnf = this.seg[n - 1].xPosEnd;
        this.seg[n].yPosAnf = this.seg[n - 1].yPosEnd;
    }
    
    public Individ() {
        this.maxSegmente = 10;
        this.minSegmente = 5;
        this.GeneProSegment = 5;
    }
}
