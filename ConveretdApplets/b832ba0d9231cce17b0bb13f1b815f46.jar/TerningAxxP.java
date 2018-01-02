import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class TerningAxxP
{
    Random kast;
    int terning1;
    int terning2;
    int terning3;
    int terning4;
    int terning5;
    int terning6;
    int terning7;
    int terning8;
    int terning9;
    int terning10;
    int valg1;
    int valg2;
    int valg3;
    int valg4;
    int valg5;
    int valg6;
    int kasteteller;
    int ubenyttet;
    int ubenytt1;
    int ubenytt2;
    int kastigjen;
    int count;
    int sumA1;
    int sumA2;
    int sumB1;
    int[] par;
    int[] topar;
    int[] trepar;
    int[] trelike;
    int[] firelike;
    int[] femlike;
    int[] litens;
    int[] stors;
    int[] fulls;
    int[] totre;
    int[] tretre;
    int[] fireto;
    int[] sjanse;
    int[] yatzy;
    
    public TerningAxxP() {
        this.kast = new Random();
        this.terning1 = 0;
        this.terning2 = 0;
        this.terning3 = 0;
        this.terning4 = 0;
        this.terning5 = 0;
        this.terning6 = 0;
        this.terning7 = 0;
        this.terning8 = 0;
        this.terning9 = 0;
        this.terning10 = 0;
        this.valg1 = 2;
        this.valg2 = 2;
        this.valg3 = 2;
        this.valg4 = 2;
        this.valg5 = 2;
        this.valg6 = 2;
        this.kasteteller = 0;
        this.ubenyttet = 0;
        this.ubenytt1 = 1;
        this.ubenytt2 = 2;
        this.kastigjen = 2;
        this.count = 0;
        this.sumA1 = 0;
        this.sumA2 = 0;
        this.sumB1 = 0;
        this.par = new int[7];
        this.topar = new int[7];
        this.trepar = new int[7];
        this.trelike = new int[7];
        this.firelike = new int[7];
        this.femlike = new int[7];
        this.litens = new int[7];
        this.stors = new int[7];
        this.fulls = new int[7];
        this.totre = new int[7];
        this.tretre = new int[7];
        this.fireto = new int[7];
        this.sjanse = new int[7];
        this.yatzy = new int[7];
    }
    
    public void Kaster() {
        if (this.valg1 % 2 == 0) {
            this.terning1 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
        if (this.valg2 % 2 == 0) {
            this.terning2 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
        if (this.valg3 % 2 == 0) {
            this.terning3 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
        if (this.valg4 % 2 == 0) {
            this.terning4 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
        if (this.valg5 % 2 == 0) {
            this.terning5 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
        if (this.valg6 % 2 == 0) {
            this.terning6 = Math.abs(this.kast.nextInt()) % 6 + 1;
        }
    }
    
    public void nullstill() {
        this.valg1 = 2;
        this.valg2 = 2;
        this.valg3 = 2;
        this.valg4 = 2;
        this.valg5 = 2;
        this.valg6 = 2;
        this.terning1 = 0;
        this.terning2 = 0;
        this.terning3 = 0;
        this.terning4 = 0;
        this.terning5 = 0;
        this.terning6 = 0;
        this.kasteteller = 0;
        this.kastigjen = 2;
    }
}
