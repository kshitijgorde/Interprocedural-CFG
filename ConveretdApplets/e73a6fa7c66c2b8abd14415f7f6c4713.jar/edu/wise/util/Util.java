// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import edu.wise.utils.FormatUtils;
import edu.wise.stats.StatUtils;
import edu.wise.exceptions.OutOfRangeException;

public class Util
{
    private static final double INCREMENT = 0.01;
    private double n11;
    private double n12;
    private double n21;
    private double n22;
    private double n1;
    private double n2;
    private double N;
    private double M1;
    private double M2;
    private double S1;
    private double S2;
    private double p11;
    private double p12;
    private double p21;
    private double p22;
    private double z;
    private double z1;
    private double z2;
    private double pc1;
    private double pc2;
    private double pcT;
    private double u1;
    private double u2;
    private double uT;
    private double u11;
    private double u12;
    private double u21;
    private double u22;
    private double relWt;
    private double XL;
    private double XR;
    private double XS;
    private double XM;
    private double L;
    private double U;
    private double C;
    private double c1;
    private double c2;
    private double cl;
    private double cu;
    private double k1;
    private double k2;
    private double temp;
    private int k;
    public static final boolean DEBUG = false;
    
    public static void main(final String[] array) {
    }
    
    public Util() {
        this(0.0, -1.0, 1.0, 1.0, 50.0, 100.0, 1.0, -1.0, -1.0, 1.0);
    }
    
    public Util(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10) {
        this.n1 = 90.0;
        this.n2 = 10.0;
        this.M1 = 0.0;
        this.M2 = 2.0;
        this.S1 = 1.0;
        this.S2 = 1.0;
        this.u11 = 1.0;
        this.u12 = -1.0;
        this.u21 = -1.0;
        this.u22 = 1.0;
        this.relWt = 1.0;
        this.k = -999;
        this.setMeans(n, n2);
        try {
            this.setSds(n3, n4);
        }
        catch (OutOfRangeException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.setNs(n5, n6);
        }
        catch (OutOfRangeException ex2) {
            System.out.println(ex2.getMessage());
        }
        try {
            this.setUtil(n7, n8, n9, n10);
        }
        catch (OutOfRangeException ex3) {
            System.out.println(ex3.getMessage());
        }
    }
    
    public void calcCut() {
        this.XL = Math.log(this.n2 / this.n1 * (this.S1 / this.S2) * this.U);
        this.XR = Math.pow(this.M1 - this.M2, 2.0) + 2.0 * (this.S1 * this.S1 - this.S2 * this.S2) * this.XL;
        this.XS = this.S1 * this.S2 * Math.sqrt(this.XR);
        this.XM = this.M2 * Math.pow(this.S1, 2.0) - this.M1 * Math.pow(this.S2, 2.0);
        if (this.M1 != this.M2) {
            this.C = (2.0 * Math.pow(this.S1, 2.0) * Math.log(this.n2 / this.n1 * this.U) + Math.pow(this.M1, 2.0) - Math.pow(this.M2, 2.0)) / (2.0 * (this.M1 - this.M2));
        }
        else {
            this.C = Double.NaN;
        }
        if (this.S1 != this.S2) {
            this.c1 = (this.XM - this.XS) / (Math.pow(this.S1, 2.0) - Math.pow(this.S2, 2.0));
            this.c2 = (this.XM + this.XS) / (Math.pow(this.S1, 2.0) - Math.pow(this.S2, 2.0));
            this.cl = Math.min(this.c1, this.c2);
            this.cu = Math.max(this.c1, this.c2);
        }
        else {
            this.c1 = Double.NaN;
            this.c2 = Double.NaN;
        }
        switch (this.getState()) {
            case -2: {
                this.n11 = Double.NaN;
                this.n12 = Double.NaN;
                this.n21 = Double.NaN;
                this.n22 = Double.NaN;
                this.pc1 = Double.NaN;
                this.pc2 = Double.NaN;
                this.pcT = Double.NaN;
                this.u1 = Double.NaN;
                this.u2 = Double.NaN;
                this.uT = Double.NaN;
                this.k = -999;
                break;
            }
            case -1: {
                this.n11 = Double.NaN;
                this.n12 = Double.NaN;
                this.n21 = Double.NaN;
                this.n22 = Double.NaN;
                this.pc1 = Double.NaN;
                this.pc2 = Double.NaN;
                this.pcT = Double.NaN;
                this.u1 = Double.NaN;
                this.u2 = Double.NaN;
                this.uT = Double.NaN;
                this.k = -999;
                break;
            }
            case 0: {
                if (this.relWt > 1.0) {
                    this.n11 = 0.0;
                }
                else {
                    this.n11 = this.n1;
                }
                this.n21 = this.n1 - this.n11;
                if (this.relWt > 1.0) {
                    this.n22 = this.n2;
                }
                else {
                    this.n22 = 0.0;
                }
                this.n12 = this.n2 - this.n21;
                if (this.n11 > this.n22) {
                    this.k = 1;
                }
                else {
                    this.k = 2;
                }
                this.updatePandU();
                break;
            }
            case 1: {
                this.temp = StatUtils.normCdf(this.C, this.M1, this.S1);
                if (this.M1 < this.M2) {
                    this.n11 = this.temp * this.n1;
                }
                else {
                    this.n11 = (1.0 - this.temp) * this.n1;
                }
                this.n21 = this.n1 - this.n11;
                this.temp = StatUtils.normCdf(this.C, this.M2, this.S2);
                if (this.M1 < this.M2) {
                    this.n22 = (1.0 - this.temp) * this.n2;
                }
                else {
                    this.n22 = this.temp * this.n2;
                }
                this.n12 = this.n2 - this.n22;
                if (this.M1 < this.M2) {
                    this.k = 1;
                }
                else {
                    this.k = 2;
                }
                this.updatePandU();
                break;
            }
            case 2: {
                this.temp = 1.0 - (StatUtils.normCdf(this.cu, this.M1, this.S1) - StatUtils.normCdf(this.cl, this.M1, this.S1));
                if (this.c1 > this.c2) {
                    this.n11 = (1.0 - this.temp) * this.n1;
                }
                else {
                    this.n11 = this.temp * this.n1;
                }
                this.n21 = this.n1 - this.n11;
                this.temp = StatUtils.normCdf(this.cu, this.M2, this.S2) - StatUtils.normCdf(this.cl, this.M2, this.S2);
                if (this.c1 > this.c2) {
                    this.n22 = (1.0 - this.temp) * this.n2;
                }
                else {
                    this.n22 = this.temp * this.n2;
                }
                this.n12 = this.n2 - this.n22;
                if (this.c1 > this.c2) {
                    this.k = 1;
                }
                else {
                    this.k = 2;
                }
                this.updatePandU();
                break;
            }
            default: {
                System.out.println("An error occured in Util.classify()");
                break;
            }
        }
    }
    
    public void updatePandU() {
        this.pc1 = this.n11 / this.n1;
        this.pc2 = this.n22 / this.n2;
        this.pcT = (this.n11 + this.n22) / (this.n1 + this.n2);
        this.u1 = this.n11 * this.u11 + this.n21 * this.u21;
        this.u2 = this.n12 * this.u12 + this.n22 * this.u22;
        this.uT = this.u1 + this.u2;
    }
    
    public void setM1(final double m1) {
        this.M1 = m1;
        this.calcCut();
    }
    
    public void setM2(final double m2) {
        this.M2 = m2;
        this.calcCut();
    }
    
    public void setMeans(final double m1, final double m2) {
        this.M1 = m1;
        this.M2 = m2;
        this.calcCut();
    }
    
    public void setS1(final double s1) throws OutOfRangeException {
        if (s1 > 0.0) {
            this.S1 = s1;
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n** Standard deviations must be greater than 0. (" + s1 + ", " + this.S2 + "). **");
    }
    
    public void setS2(final double s2) throws OutOfRangeException {
        if (s2 > 0.0) {
            this.S2 = s2;
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n** Standard deviations must be greater than 0. (" + this.S1 + ", " + s2 + "). **");
    }
    
    public void setSds(final double s1, final double s2) throws OutOfRangeException {
        if (s1 > 0.0 && s2 > 0.0) {
            this.S1 = s1;
            this.S2 = s2;
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n** Standard deviations must be greater than 0. (" + s1 + ", " + s2 + "). **");
    }
    
    public void setN1(final double n1) throws OutOfRangeException {
        if (n1 > 0.0) {
            this.n1 = n1;
            this.N = n1 + this.n2;
            this.setRelWt();
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n ** Input Error: Base Rates must be greater than 0. (" + n1 + ", " + this.n2 + "). **");
    }
    
    public void setN2(final double n2) throws OutOfRangeException {
        if (this.n1 > 0.0) {
            this.n2 = n2;
            this.N = this.n1 + n2;
            this.setRelWt();
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n ** Input Error: Base Rates must be greater than 0. (" + this.n1 + ", " + n2 + "). **");
    }
    
    public void setNs(final double n1, final double n2) throws OutOfRangeException {
        if (n1 > 0.0 && n2 > 0.0) {
            this.n1 = n1;
            this.n2 = n2;
            this.N = n1 + n2;
            this.setRelWt();
            this.calcCut();
            return;
        }
        throw new OutOfRangeException("\n ** Input Error: Base Rates must be greater than 0. (" + n1 + ", " + n2 + "). **");
    }
    
    public void setu11(final double u11) throws OutOfRangeException {
        this.u11 = u11;
        this.setU();
        this.calcCut();
    }
    
    public void setu12(final double u12) throws OutOfRangeException {
        this.u12 = u12;
        this.setU();
        this.calcCut();
    }
    
    public void setu21(final double u21) throws OutOfRangeException {
        this.u21 = u21;
        this.setU();
        this.calcCut();
    }
    
    public void setu22(final double u22) throws OutOfRangeException {
        this.u22 = u22;
        this.setU();
        this.calcCut();
    }
    
    public void setUtil(final double u11, final double u12, final double u13, final double u14) throws OutOfRangeException {
        if (u11 > u13 && u14 > u12) {
            this.u11 = u11;
            this.u12 = u12;
            this.u21 = u13;
            this.u22 = u14;
            this.setU();
            this.calcCut();
        }
        else {
            System.out.println("\n** Input Error: The Utility for a correct classification of an actual case **\n** must be larger than utility of an incorrect classification of that case. **");
        }
    }
    
    private void setU() {
        if (this.u11 - this.u21 != 0.0) {
            this.U = (this.u22 - this.u12) / (this.u11 - this.u21);
        }
        else {
            this.U = Double.NaN;
        }
        this.setRelWt();
    }
    
    private void setRelWt() {
        this.relWt = this.n2 / this.n1 * this.U;
    }
    
    public String toString() {
        return "M1 = " + this.M1 + " M2 = " + this.M2 + "\nSs = " + this.S1 + ", S2 = " + this.S2 + "\nn1 = " + this.n1 + ", n2 = " + this.n2 + "\nn11 = " + FormatUtils.rounder_str(this.n11, 2) + ", n12 = " + FormatUtils.rounder_str(this.n12, 3) + ", n21 = " + FormatUtils.rounder_str(this.n21, 3) + ", n22 = " + FormatUtils.rounder_str(this.n22, 3) + "\nu11 = " + this.u11 + ", u12 = " + this.u12 + ", u21 = " + this.u21 + ", u22 = " + this.u22 + "\n";
    }
    
    public int getState() {
        if (this.u11 <= this.u12 || this.u22 <= this.u21) {
            return -2;
        }
        if (this.S1 == this.S2 && this.M1 == this.M2 && this.relWt == 1.0) {
            return -1;
        }
        if (this.S1 == this.S2 && this.M1 == this.M2 && this.relWt != 1.0) {
            return 0;
        }
        if (this.S1 != this.S2 && this.XR < 0.0) {
            return 0;
        }
        if (this.S1 == this.S2 && this.M1 != this.M2) {
            return 1;
        }
        if (this.S1 != this.S2 && this.XR >= 0.0) {
            return 2;
        }
        System.out.println("*** Util.getState(failure)\n" + this.toString() + "***");
        System.out.println("Please report current parameters to Michael.Healy@cgu.edu.");
        return 2;
    }
    
    public double getn1() {
        return this.n1;
    }
    
    public double getn2() {
        return this.n2;
    }
    
    public double getn11() {
        return this.n11;
    }
    
    public double getn12() {
        return this.n12;
    }
    
    public double getn21() {
        return this.n21;
    }
    
    public double getn22() {
        return this.n22;
    }
    
    public double getu1() {
        return this.u1;
    }
    
    public double getu2() {
        return this.u2;
    }
    
    public double getu11() {
        return this.u11;
    }
    
    public double getu12() {
        return this.u12;
    }
    
    public double getu21() {
        return this.u21;
    }
    
    public double getu22() {
        return this.u22;
    }
    
    public double getuT() {
        return this.uT;
    }
    
    public double getU() {
        return this.U;
    }
    
    public double getpc1() {
        return this.pc1;
    }
    
    public double getpc2() {
        return this.pc2;
    }
    
    public double getpcT() {
        return this.pcT;
    }
    
    public double getM1() {
        return this.M1;
    }
    
    public double getM2() {
        return this.M2;
    }
    
    public double getS1() {
        return this.S1;
    }
    
    public double getS2() {
        return this.S2;
    }
    
    public double getC() {
        return this.C;
    }
    
    public double getcl() {
        return this.cl;
    }
    
    public double getcu() {
        return this.cu;
    }
    
    public int getGroup() {
        return this.k;
    }
}
