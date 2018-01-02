import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class Segment
{
    int Vorgaenger;
    int Laenge;
    int UrWinkel;
    int AktWinkel;
    int AnzMov;
    int AktMov;
    public int xPosAnf;
    public int yPosAnf;
    public int xPosEnd;
    public int yPosEnd;
    int DrehRichtung;
    
    public void init(final int n) {
        new Random();
        this.AktWinkel = (this.UrWinkel + n) % 360;
        this.AktMov = 0;
        this.xPosEnd = this.xPosAnf + (int)(this.Laenge * Math.cos(3.141592653589793 * this.AktWinkel / 180.0));
        this.yPosEnd = this.yPosAnf + (int)(this.Laenge * Math.sin(3.141592653589793 * this.AktWinkel / 180.0));
    }
    
    public void moveSegment(final int n) {
        if (this.AnzMov > 0) {
            if ((this.Vorgaenger + this.Laenge) % 2 == 0) {
                this.AktMov = (this.AktMov + 1) % this.AnzMov;
            }
            else {
                if (this.DrehRichtung == 0) {
                    ++this.AktMov;
                }
                else {
                    --this.AktMov;
                }
                if (this.AktMov > this.AnzMov || this.AktMov < 0) {
                    this.DrehRichtung = (this.DrehRichtung + 1) % 2;
                }
            }
        }
        if ((this.Laenge + this.AnzMov) % 2 == 0) {
            this.AktWinkel = (this.UrWinkel + n + 8 * this.AktMov) % 360;
        }
        else {
            this.AktWinkel = (this.UrWinkel + n - 8 * this.AktMov) % 360;
        }
        this.xPosEnd = this.xPosAnf + (int)(this.Laenge * Math.cos(3.141592653589793 * this.AktWinkel / 180.0));
        this.yPosEnd = this.yPosAnf + (int)(this.Laenge * Math.sin(3.141592653589793 * this.AktWinkel / 180.0));
    }
}
