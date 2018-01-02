// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import java.io.PrintStream;

public class Baseline implements Cloneable
{
    boolean Male;
    double Age;
    static final double maximumage = 250.0;
    double Height;
    static final double maximumheight = 400.0;
    double Weight;
    double Bfp;
    double RMR;
    double PAL;
    double CarbInPercent;
    double Sodium;
    double delta_E;
    double dECW;
    double Glycogen;
    boolean bfpcalc;
    static final double maximumbfp = 60.0;
    boolean rmrcalc;
    
    public Baseline(final boolean male, final double age, double height, double weight, double bfp, final double rmr, double pal) {
        this.Male = true;
        this.Age = 23.0;
        this.Height = 180.0;
        this.Weight = 70.0;
        this.Bfp = 18.0;
        this.RMR = 1708.0;
        this.PAL = 1.4;
        this.CarbInPercent = 50.0;
        this.Sodium = 4000.0;
        this.delta_E = 0.0;
        this.dECW = 0.0;
        this.Glycogen = 0.5;
        this.bfpcalc = true;
        this.rmrcalc = true;
        if (height < 0.0) {
            height = 0.1;
        }
        if (weight < 0.0) {
            weight = 0.1;
        }
        if (bfp < 0.0) {
            bfp = 0.0;
        }
        if (bfp > 100.0) {
            bfp = 100.0;
        }
        if (pal < 1.0) {
            pal = 1.0;
        }
        this.Male = male;
        this.Age = age;
        this.Height = height;
        this.Weight = weight;
        this.bfpcalc = false;
        this.Bfp = bfp;
        this.rmrcalc = false;
        this.RMR = rmr;
        this.PAL = pal;
    }
    
    public Baseline() {
        this.Male = true;
        this.Age = 23.0;
        this.Height = 180.0;
        this.Weight = 70.0;
        this.Bfp = 18.0;
        this.RMR = 1708.0;
        this.PAL = 1.4;
        this.CarbInPercent = 50.0;
        this.Sodium = 4000.0;
        this.delta_E = 0.0;
        this.dECW = 0.0;
        this.Glycogen = 0.5;
        this.bfpcalc = true;
        this.rmrcalc = true;
    }
    
    public void setMale(final boolean male) {
        this.Male = male;
    }
    
    public boolean getMale() {
        return this.Male;
    }
    
    public void setAge(final double age) {
        if (age > 0.0 && age <= 250.0) {
            this.Age = age;
        }
    }
    
    public double getAge() {
        return this.Age;
    }
    
    public void setHeight(final double height) {
        if (height > 0.0 && height <= 400.0) {
            this.Height = height;
        }
    }
    
    public double getNewAct(final Intervention intervention) {
        return intervention.getAct(this);
    }
    
    public double getHeight_cms() {
        return this.Height;
    }
    
    public void setWeight_kgs(final double weight) {
        if (weight > 0.0) {
            this.Weight = weight;
        }
    }
    
    public double getWeight_kgs() {
        return this.Weight;
    }
    
    public void setBfp(final double bfp) {
        if (bfp >= 0.0 && bfp <= 100.0 && !this.bfpcalc) {
            this.Bfp = bfp;
        }
    }
    
    public double getBfp() {
        if (this.bfpcalc) {
            if (this.Male) {
                this.Bfp = 0.14 * this.Age + 37.31 * Math.log(this.getBMI()) - 103.94;
            }
            else {
                this.Bfp = 0.14 * this.Age + 39.96 * Math.log(this.getBMI()) - 102.01;
            }
            if (this.Bfp > 60.0) {
                this.Bfp = 60.0;
            }
            if (this.Bfp < 0.0) {
                this.Bfp = 0.0;
            }
        }
        return this.Bfp;
    }
    
    public void setCarbInPercent(final double carbInPercent) {
        if (carbInPercent >= 0.0 && carbInPercent <= 100.0) {
            this.CarbInPercent = carbInPercent;
        }
    }
    
    public double getCarbInPercent() {
        return this.CarbInPercent;
    }
    
    public double getK() {
        return 0.76 * this.getMaintCals() - this.delta_E - 22.0 * this.getLeanWt() - 3.2 * this.getFatWt() - this.getActParam() * this.Weight;
    }
    
    public double getPAL() {
        return this.PAL;
    }
    
    public void setPAL(final double pal) {
        if (pal >= 1.0 && pal <= 3.0) {
            this.PAL = pal;
        }
    }
    
    public void setRMR(final double rmr) {
        if (rmr > 0.0 && !this.rmrcalc) {
            this.RMR = rmr;
        }
    }
    
    public double getRMR() {
        if (this.rmrcalc) {
            if (this.Male) {
                this.RMR = 9.99 * this.Weight + 625.0 * this.Height / 100.0 - 4.92 * this.Age + 5.0;
            }
            else {
                this.RMR = 9.99 * this.Weight + 625.0 * this.Height / 100.0 - 4.92 * this.Age - 161.0;
            }
        }
        return this.RMR;
    }
    
    public double getnewRMR(final double n, final double n2) {
        double n3;
        if (this.Male) {
            n3 = 9.99 * n + 625.0 * this.Height / 100.0 - 4.92 * (this.Age + n2 / 365.0) + 5.0;
        }
        else {
            n3 = 9.99 * n + 625.0 * this.Height / 100.0 - 4.92 * (this.Age + n2 / 365.0) - 161.0;
        }
        return n3;
    }
    
    public double getMaintCals() {
        return this.PAL * this.getRMR();
    }
    
    public double getActParam() {
        return (0.9 * this.getRMR() * this.getPAL() - this.getRMR()) / this.getWeight_kgs();
    }
    
    public double getActExpend() {
        return this.getTEE() - this.getRMR();
    }
    
    public double getTEE() {
        return this.PAL * this.getRMR();
    }
    
    public double getFatWt() {
        return this.Weight * this.getBfp() / 100.0;
    }
    
    public double getLeanWt() {
        return this.Weight - this.getFatWt();
    }
    
    public double getBMI() {
        return this.Weight / Math.pow(this.Height / 100.0, 2.0);
    }
    
    public double getnewBMI(final double n) {
        return n / Math.pow(this.Height / 100.0, 2.0);
    }
    
    public double getECW() {
        double n;
        if (this.Male) {
            n = 0.025 * this.Age + 9.57 * this.Height + 0.191 * this.Weight - 12.4;
        }
        else {
            n = -4.0 + 5.98 * this.Height + 0.167 * this.Weight;
        }
        return n;
    }
    
    public double getnewECW(final double n, final double n2) {
        double n3;
        if (this.Male) {
            n3 = 0.025 * (this.Age + n / 365.0) + 9.57 * this.Height + 0.191 * n2 - 12.4;
        }
        else {
            n3 = -4.0 + 5.98 * this.Height + 0.167 * n2;
        }
        return n3;
    }
    
    public void setSodium(final double sodium) {
        if (sodium >= 0.0 && sodium < 50000.0) {
            this.Sodium = sodium;
        }
    }
    
    public double getSodium() {
        return this.Sodium;
    }
    
    public double proportionalSodium(final double n) {
        return this.getSodium() * n / this.getMaintCals();
    }
    
    public double getCarbsIn() {
        return this.CarbInPercent / 100.0 * this.getMaintCals();
    }
    
    public void setcalculatedBfp(final boolean bfpcalc) {
        this.Bfp = this.getBfp();
        this.bfpcalc = bfpcalc;
    }
    
    public void setcalculatedRMR(final boolean rmrcalc) {
        this.RMR = this.getRMR();
        this.rmrcalc = rmrcalc;
    }
    
    public double getdECW() {
        return this.dECW;
    }
    
    public void setdECW(final double decw) {
        this.dECW = decw;
    }
    
    public double getGlycogen() {
        return this.Glycogen;
    }
    
    public void setGlycogen(final double glycogen) {
        if (glycogen >= 0.0) {
            this.Glycogen = glycogen;
        }
    }
    
    public double getgH2O(final double n) {
        return 3.7 * (n - this.Glycogen);
    }
    
    public double gettherm() {
        return 0.13999999999999999 * this.getTEE();
    }
    
    public double[] getbc() {
        return new double[] { this.Weight * this.Bfp / 100.0, this.Weight * (100.0 - this.Bfp) / 100.0, this.dECW };
    }
    
    public void setdeltaE(final double delta_E) {
        this.delta_E = delta_E;
    }
    
    public double getdeltaE() {
        return this.delta_E;
    }
    
    public double getnewWeight(final double n, final double n2, final double n3, final double n4) {
        return n + n2 + this.getgH2O(n3) + n4;
    }
    
    public double getnewWeight(final BodyModel bodyModel) {
        return bodyModel.getFat() + bodyModel.getLean() + this.getgH2O(bodyModel.getGlyc()) + bodyModel.getDecw();
    }
    
    public double getstableWeight(final double n, final double n2, final double n3) {
        return n + n2 + this.getgH2O(this.glyce(n3)) + this.decwe(n3);
    }
    
    public double decwe(final double n) {
        return ((this.getSodium() / this.getMaintCals() + 4000.0 * this.getCarbInPercent() / (100.0 * this.getCarbsIn())) * n - (this.getSodium() + 4000.0)) / 3000.0;
    }
    
    public double glyce(final double n) {
        return this.getGlycogen() * Math.sqrt(this.getCarbInPercent() / 100.0 * n / this.getCarbsIn());
    }
    
    public BodyModel getBodyState() {
        return new BodyModel(this);
    }
    
    public double getnewTEE(final BodyModel bodyModel, final DailyParams dailyParams) {
        return bodyModel.getTEE(this, dailyParams);
    }
    
    public Intervention makebaseintervention(final double n) {
        return new Intervention((int)n, this.getTEE(), this.CarbInPercent, this.PAL, this.Sodium);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public void printdetailed(final PrintStream printStream) {
        printStream.println("Baseline report:");
        printStream.println("    Male=" + this.Male);
        printStream.println("    Age=" + this.Age);
        printStream.println("    Height (cm)=" + this.Height);
        printStream.println("    Weight (kg)=" + this.Weight);
        printStream.println("    Fat (kg)=" + this.getFatWt());
        printStream.println("    Percent Body Fat=" + this.Bfp);
        printStream.println("    RMR (kcal/day)=" + this.RMR);
        printStream.println("    PAL=" + this.PAL);
        printStream.println("    ActParam=" + this.getActParam());
        printStream.println("    TEE (kcal/day)=" + this.getTEE());
        printStream.println("    Sodium (mg/day)=" + this.Sodium);
        printStream.println("    Carb Percent=" + this.getCarbInPercent());
        printStream.println("    Glycogen (kg)=" + this.Glycogen);
        printStream.println("    therm (kcal/day)= " + this.gettherm());
        printStream.println("    K (kcal/day)=" + this.getK());
    }
    
    public void printdetailed() {
        this.printdetailed(System.out);
    }
    
    public void print() {
        this.print(System.out);
    }
    
    public void print(final PrintStream printStream) {
        printStream.println("Baseline report:");
        printStream.println("    Male=" + this.Male);
        printStream.println("    Age=" + this.Age);
        printStream.println("    Height (cm)=" + this.Height);
        printStream.println("    Weight (kg)=" + this.Weight);
        printStream.println("    Fat (kg)=" + this.getFatWt());
        printStream.println("    Percent Body Fat=" + this.Bfp);
        printStream.println("    RMR (kcal/day)=" + this.RMR);
        printStream.println("    PAL=" + this.PAL);
        printStream.println("    TEE (kcal/day)=" + this.getTEE());
        printStream.println("    Sodium (mg/day)=" + this.Sodium);
        printStream.println("    Carb Percent=" + this.getCarbInPercent());
    }
    
    public void check() {
        System.out.println("Check values vs. calls:");
        System.out.println("    Male:" + (this.Male == this.getMale()));
        System.out.println("    Age:" + (this.Age == this.getAge()));
        System.out.println("    Height:" + (this.Height == this.getHeight_cms()));
        System.out.println("    Weight:" + (this.Weight == this.getWeight_kgs()));
        System.out.println("    Bfp:" + (this.Bfp == this.getBfp()));
        System.out.println("    RMR:" + (this.RMR == this.getRMR()));
        System.out.println("    PAL:" + (this.PAL == this.getPAL()));
    }
}
