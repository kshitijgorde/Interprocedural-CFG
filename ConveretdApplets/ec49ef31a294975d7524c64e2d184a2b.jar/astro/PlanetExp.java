// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class PlanetExp
{
    static final PlanetExpP0[] MercuryLambda;
    static final PlanetExpP0[] MercuryBeta;
    static final PlanetExpP0[] MercuryR;
    static final PlanetExpP0[] VenusL0;
    static final PlanetExpP0[] VenusL1;
    static final PlanetExpP0[] VenusQ;
    static final PlanetExpP1 VenusP;
    static final PlanetExpP0[] MarsL0;
    static final PlanetExpP0[] MarsL1;
    static final PlanetExpP0[] MarsQ;
    static final PlanetExpP1 MarsP;
    static final PlanetExpP0[] JupiterN;
    static final PlanetExpP0[] JupiterB;
    static final PlanetExpP0[] JupiterQ;
    static final PlanetExpP2 JupiterP;
    static final PlanetExpP0[] SaturnN;
    static final PlanetExpP0[] SaturnB;
    static final PlanetExpP0[] SaturnQ;
    static final PlanetExpP2 SaturnP;
    static final PlanetExpP0[] UranusLambda;
    static final PlanetExpP0[] UranusBeta;
    static final PlanetExpP0[] UranusR;
    static final PlanetExpP0[] NeptuneLambda;
    static final PlanetExpP0[] NeptuneBeta;
    static final PlanetExpP0[] NeptuneR;
    static final PlanetExpP0[] PlutoLambda;
    static final PlanetExpP0[] PlutoBeta;
    static final PlanetExpP0[] PlutoR;
    static final PlanetExpP0[] SunLambda;
    static final PlanetExpP0[] SunQ;
    
    private static Xyz getPosExp0(final double n) {
        double n2 = 279.0358 + 360.00769 * n + (1.9159 - 5.0E-5 * n) * UdMath.udsin(356.531 + 359.991 * n);
        for (int i = 0; i < PlanetExp.SunLambda.length; ++i) {
            n2 += PlanetExp.SunLambda[i].a * UdMath.udsin(PlanetExp.SunLambda[i].b + PlanetExp.SunLambda[i].c * n);
        }
        final double deg2rad = UdMath.deg2rad(UdMath.degmal(n2 + 0.0057));
        final double n3 = 0.0;
        double n4 = (-0.007261 + 2.0E-7 * n) * UdMath.udcos(356.53 + 359.991 * n) + 3.0E-5;
        for (int j = 0; j < PlanetExp.SunQ.length; ++j) {
            n4 += PlanetExp.SunQ[j].a * UdMath.udcos(PlanetExp.SunQ[j].b + PlanetExp.SunQ[j].b * n);
        }
        final double pow = Math.pow(10.0, n4);
        return new Xyz(-pow * Math.cos(n3) * Math.cos(deg2rad), -pow * Math.cos(n3) * Math.sin(deg2rad), -pow * Math.sin(n3));
    }
    
    private static Xyz getPosExp1(final int n, final double n2) {
        PlanetExpP0[] array = null;
        PlanetExpP0[] array2 = null;
        PlanetExpP0[] array3 = null;
        PlanetExpP1 planetExpP1 = null;
        switch (n) {
            case 2: {
                array = PlanetExp.VenusL0;
                array2 = PlanetExp.VenusL1;
                array3 = PlanetExp.VenusQ;
                planetExpP1 = PlanetExp.VenusP;
                break;
            }
            case 4: {
                array = PlanetExp.MarsL0;
                array2 = PlanetExp.MarsL1;
                array3 = PlanetExp.MarsQ;
                planetExpP1 = PlanetExp.MarsP;
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
        double n3 = (planetExpP1.L6 + planetExpP1.L7 * n2) * UdMath.udsin(planetExpP1.L8 + planetExpP1.L9 * n2);
        for (int i = 0; i < array2.length; ++i) {
            n3 += array2[i].a * UdMath.udsin(array2[i].b + array2[i].c * n2);
        }
        double n4 = planetExpP1.L1 + planetExpP1.L2 * n2 + planetExpP1.L3 * UdMath.udsin(planetExpP1.L4 + planetExpP1.L5 * n2 + 2.0 * n3);
        for (int j = 0; j < array.length; ++j) {
            n4 += array[j].a * UdMath.udsin(array[j].b + array[j].c * n2);
        }
        final double deg2rad = UdMath.deg2rad(UdMath.degmal(n4 + n3));
        final double asin = Math.asin(planetExpP1.B1 * UdMath.udsin(planetExpP1.B2 + planetExpP1.B3 * n2 + n3));
        double n5 = (planetExpP1.q1 + planetExpP1.q2 * n2) * UdMath.udcos(planetExpP1.q3 + planetExpP1.q4 * n2) + planetExpP1.q5;
        for (int k = 0; k < array3.length; ++k) {
            n5 += array3[k].a * UdMath.udcos(array3[k].b + array3[k].c * n2);
        }
        final double pow = Math.pow(10.0, n5);
        return new Xyz(pow * Math.cos(asin) * Math.cos(deg2rad), pow * Math.cos(asin) * Math.sin(deg2rad), pow * Math.sin(asin));
    }
    
    private static Xyz getPosExp2(final int n, final double n2) {
        PlanetExpP0[] array = null;
        PlanetExpP0[] array2 = null;
        PlanetExpP0[] array3 = null;
        PlanetExpP2 planetExpP2 = null;
        double n3 = 0.0;
        double n4 = 0.0;
        switch (n) {
            case 5: {
                array = PlanetExp.JupiterN;
                array2 = PlanetExp.JupiterB;
                array3 = PlanetExp.JupiterQ;
                planetExpP2 = PlanetExp.JupiterP;
                n3 = 341.5208 + 30.34907 * n2 + ((0.035 + 2.8E-4 * n2) * UdMath.udsin(245.94 - 30.349 * n2) + 4.0E-4) - (0.0019 + 2.0E-5 * n2) * UdMath.udsin(162.78 + 0.38 * n2);
                n4 = (1.32E-4 + 1.1E-6 * n2) * UdMath.udcos(245.93 - 30.349 * n2);
                break;
            }
            case 6: {
                array = PlanetExp.SaturnN;
                array2 = PlanetExp.SaturnB;
                array3 = PlanetExp.SaturnQ;
                planetExpP2 = PlanetExp.SaturnP;
                n3 = 12.3042 + 12.22117 * n2 + ((0.0934 + 7.5E-4 * n2) * UdMath.udsin(250.29 + 12.221 * n2) + 8.0E-4) + (0.0057 + 5.0E-5 * n2) * UdMath.udsin(265.8 - 11.81 * n2) + (0.0049 + 4.0E-5 * n2) * UdMath.udsin(162.7 + 0.38 * n2) + (0.0019 + 2.0E-5 * n2) * UdMath.udsin(262.0 + 24.44 * n2);
                n4 = (3.54E-4 + 2.8E-6 * n2) * UdMath.udcos(70.28 + 12.22 * n2) + 1.83E-4 + (2.1E-5 + 2.0E-7 * n2) * UdMath.udcos(265.8 - 11.81 * n2);
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
        for (int i = 0; i < array.length; ++i) {
            n3 += array[i].a * UdMath.udsin(array[i].b + array[i].c * n2);
        }
        final double n5 = n3 + planetExpP2.f1 * UdMath.udsin(n3) + planetExpP2.f2 * UdMath.udsin(2.0 * n3) + planetExpP2.f3 * UdMath.udsin(3.0 * n3) + planetExpP2.f4 * UdMath.udsin(4.0 * n3);
        final double deg2rad = UdMath.deg2rad(UdMath.degmal(n5 + planetExpP2.V1 * UdMath.udsin(2.0 * n5 + planetExpP2.V2) + planetExpP2.L1 + planetExpP2.L2 * n2));
        double n6 = Math.asin(planetExpP2.B1 * UdMath.udsin(n5 + planetExpP2.B2)) + UdMath.deg2rad((planetExpP2.B3 + planetExpP2.B4 * n2) * UdMath.udsin(n5 + planetExpP2.B5));
        for (int j = 0; j < array2.length; ++j) {
            n6 += array2[j].a * UdMath.udsin(array2[j].b + array2[j].c * n2);
        }
        for (int k = 0; k < array3.length; ++k) {
            n4 += array3[k].a * UdMath.udcos(array3[k].b + array3[k].c * n2);
        }
        final double n7 = Math.pow(10.0, n4) * planetExpP2.r1 / (1.0 + planetExpP2.r2 * UdMath.udcos(n5));
        return new Xyz(n7 * Math.cos(n6) * Math.cos(deg2rad), n7 * Math.cos(n6) * Math.sin(deg2rad), n7 * Math.sin(n6));
    }
    
    private static Xyz getPosExp3(final int n, final double n2) {
        PlanetExpP0[] array = null;
        PlanetExpP0[] array2 = null;
        PlanetExpP0[] array3 = null;
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        switch (n) {
            case 1: {
                array = PlanetExp.MercuryLambda;
                array2 = PlanetExp.MercuryBeta;
                array3 = PlanetExp.MercuryR;
                n3 = 252.2502 + 149474.0714 * n2 + (23.4405 + 0.0023 * n2) * UdMath.udcos(149472.5153 * n2 + 84.7947) + (2.9818 + 6.0E-4 * n2) * UdMath.udcos(298945.031 * n2 + 259.589);
                n4 = (6.7057 + 0.0017 * n2) * UdMath.udcos(149472.886 * n2 + 113.919) + (1.4396 + 5.0E-4 * n2) * UdMath.udcos(0.37 * n2 + 119.12) + (1.3643 + 5.0E-4 * n2) * UdMath.udcos(298945.4 * n2 + 288.71);
                n5 = 0.395283 + 2.0E-6 * n2 + (0.078341 + 8.0E-6 * n2) * UdMath.udcos(149472.515 * n2 + 354.795) + (0.007955 + 2.0E-6 * n2) * UdMath.udcos(298945.03 * n2 + 169.59);
                break;
            }
            case 7: {
                array = PlanetExp.UranusLambda;
                array2 = PlanetExp.UranusBeta;
                array3 = PlanetExp.UranusR;
                n3 = 313.33676 + 428.7288 * n2 + 3.20671 * n2 * UdMath.udcos(705.15539 * n2 + 114.0274) + 2.69325 * n2 * UdMath.udcos(597.77389 * n2 + 317.7651) + 1.5E-4 * n2 * UdMath.udcos(3798.6 * n2 + 313.4);
                n4 = -0.02997 + 1.78488 * n2 * UdMath.udcos(507.52281 * n2 + 188.32394) + 0.56518 * n2 * UdMath.udcos(892.2869 * n2 + 354.9571) + 3.6E-4 * n2 * UdMath.udcos(1526.5 * n2 + 263.0);
                n5 = 19.203034 + 0.042617 * n2 + 0.361949 * n2 * UdMath.udcos(440.702 * n2 + 19.879) + 0.166685 * n2 * UdMath.udcos(702.024 * n2 + 307.419);
                break;
            }
            case 8: {
                array = PlanetExp.NeptuneLambda;
                array2 = PlanetExp.NeptuneBeta;
                array3 = PlanetExp.NeptuneR;
                n3 = -55.13323 + 219.93503 * n2 + 0.04403 * n2 * UdMath.udcos(684.128 * n2 + 332.797) + 0.02928 * n2 * UdMath.udcos(904.371 * n2 + 342.114);
                n4 = 0.01725;
                n5 = 30.073033 + 0.009784 * n2 * UdMath.udcos(515.2 * n2 + 195.7);
                break;
            }
            case 9: {
                array = PlanetExp.PlutoLambda;
                array2 = PlanetExp.PlutoBeta;
                array3 = PlanetExp.PlutoR;
                n3 = 241.82574 + 179.09519 * n2;
                n4 = -2.30285;
                n5 = 38.662489 + 0.007619 * n2 * UdMath.udcos(1425.9 * n2 + 31.0) + 0.002543 * n2 * UdMath.udcos(2196.1 * n2 + 199.5);
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
        for (int i = 0; i < array.length; ++i) {
            n3 += array[i].a * UdMath.udcos(array[i].b * n2 + array[i].c);
        }
        final double deg2rad = UdMath.deg2rad(UdMath.degmal(n3));
        for (int j = 0; j < array2.length; ++j) {
            n4 += array2[j].a * UdMath.udcos(array2[j].b * n2 + array2[j].c);
        }
        final double deg2rad2 = UdMath.deg2rad(n4);
        for (int k = 0; k < array3.length; ++k) {
            n5 += array3[k].a * UdMath.udcos(array3[k].b * n2 + array3[k].c);
        }
        return new Xyz(n5 * Math.cos(deg2rad2) * Math.cos(deg2rad), n5 * Math.cos(deg2rad2) * Math.sin(deg2rad), n5 * Math.sin(deg2rad2));
    }
    
    public static Xyz getPos(final int n, final ATime aTime) {
        switch (n) {
            case 3: {
                return getPosExp0(aTime.getT());
            }
            case 2:
            case 4: {
                return getPosExp1(n, aTime.getT());
            }
            case 5:
            case 6: {
                return getPosExp2(n, aTime.getT());
            }
            case 1:
            case 7:
            case 8:
            case 9: {
                return getPosExp3(n, aTime.getT2());
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        MercuryLambda = new PlanetExpP0[] { new PlanetExpP0(0.5258, 448417.55, 74.38), new PlanetExpP0(0.1796, 298945.77, 137.84), new PlanetExpP0(0.1061, 597890.1, 249.2), new PlanetExpP0(0.085, 149473.3, 143.0), new PlanetExpP0(0.076, 448418.3, 312.6), new PlanetExpP0(0.0256, 597890.8, 127.4), new PlanetExpP0(0.023, 747362.6, 64.0), new PlanetExpP0(0.0081, 747363.0, 302.0), new PlanetExpP0(0.0069, 1.0, 148.0), new PlanetExpP0(0.0052, 896835.0, 239.0), new PlanetExpP0(0.0023, 896836.0, 117.0), new PlanetExpP0(0.0019, 6356.0, 85.0), new PlanetExpP0(0.0011, 1046308.0, 54.0) };
        MercuryBeta = new PlanetExpP0[] { new PlanetExpP0(0.3123, 448417.92, 103.51), new PlanetExpP0(0.0753, 597890.4, 278.3), new PlanetExpP0(0.0367, 149472.1, 55.7), new PlanetExpP0(0.0187, 747362.9, 93.1), new PlanetExpP0(0.005, 298945.0, 230.0), new PlanetExpP0(0.0047, 896835.0, 268.0), new PlanetExpP0(0.0028, 448419.0, 342.0), new PlanetExpP0(0.0023, 298946.0, 347.0), new PlanetExpP0(0.002, 597891.0, 157.0), new PlanetExpP0(0.0012, 1046308.0, 83.0), new PlanetExpP0(9.0E-4, 747364.0, 331.0), new PlanetExpP0(9.0E-4, 448717.0, 45.0) };
        MercuryR = new PlanetExpP0[] { new PlanetExpP0(0.001214, 448417.55, 344.38), new PlanetExpP0(2.18E-4, 597890.1, 159.2), new PlanetExpP0(4.2E-5, 747363.0, 334.0), new PlanetExpP0(6.0E-6, 896835.0, 149.0) };
        VenusL0 = new PlanetExpP0[] { new PlanetExpP0(-0.0048, 248.6, -19.34), new PlanetExpP0(-4.0E-4, 198.0, 720.0) };
        VenusL1 = new PlanetExpP0[] { new PlanetExpP0(0.0033, 357.9, 1170.35), new PlanetExpP0(0.0031, 242.3, 450.37), new PlanetExpP0(0.002, 273.5, 675.55), new PlanetExpP0(0.0014, 31.1, 225.18) };
        VenusQ = new PlanetExpP0[] { new PlanetExpP0(-1.5E-5, 357.9, 1170.35), new PlanetExpP0(1.0E-5, 62.3, 450.37), new PlanetExpP0(-8.0E-6, 93.0, 675.6) };
        VenusP = new PlanetExpP1(310.1735, 585.19212, -0.0503, 107.44, 1170.37, 0.7775, -5.0E-5, 178.954, 585.178, 0.05922, 233.72, 585.183, -0.002947, 2.1E-7, 178.954, 585.178, -0.140658);
        MarsL0 = new PlanetExpP0[] { new PlanetExpP0(-0.0048, 248.6, -19.34), new PlanetExpP0(-4.0E-4, 198.0, 720.0) };
        MarsL1 = new PlanetExpP0[] { new PlanetExpP0(0.6225, 187.54, 382.797), new PlanetExpP0(0.0503, 101.31, 574.196), new PlanetExpP0(0.0146, 62.31, 0.198), new PlanetExpP0(0.0071, 71.8, 161.05), new PlanetExpP0(0.0061, 230.2, 130.71), new PlanetExpP0(0.0046, 15.1, 765.59), new PlanetExpP0(0.0045, 147.5, 322.11), new PlanetExpP0(0.0039, 279.3, -22.81), new PlanetExpP0(0.0024, 207.7, 168.59), new PlanetExpP0(0.002, 140.1, 145.78), new PlanetExpP0(0.0018, 224.7, 10.98), new PlanetExpP0(0.0014, 221.8, -45.62), new PlanetExpP0(0.001, 91.4, -30.34), new PlanetExpP0(9.0E-4, 268.0, 100.4) };
        MarsQ = new PlanetExpP0[] { new PlanetExpP0(-0.002825, 187.54, 382.797), new PlanetExpP0(-2.49E-4, 101.31, 574.196), new PlanetExpP0(-2.4E-5, 15.1, 765.59), new PlanetExpP0(2.3E-5, 251.7, 161.05), new PlanetExpP0(2.2E-5, 327.6, 322.11), new PlanetExpP0(1.7E-5, 50.2, 130.71), new PlanetExpP0(7.0E-6, 27.0, 168.6), new PlanetExpP0(6.0E-6, 320.0, 145.8) };
        MarsP = new PlanetExpP1(249.3542, 191.41696, -0.0149, 40.01, 382.819, 10.6886, 1.0E-4, 273.768, 191.399, 0.03227, 200.0, 191.409, -0.040421, -3.9E-7, 273.768, 191.399, 0.183844);
        JupiterN = new PlanetExpP0[] { new PlanetExpP0(0.3323, 162.78, 0.385), new PlanetExpP0(0.0541, 38.46, -36.256), new PlanetExpP0(0.0447, 293.42, -29.941), new PlanetExpP0(0.0342, 44.5, -5.907), new PlanetExpP0(0.023, 201.25, -24.035), new PlanetExpP0(0.0222, 109.99, -18.128), new PlanetExpP0(-0.0048, 248.6, -19.34), new PlanetExpP0(0.0047, 184.6, -11.81), new PlanetExpP0(0.0045, 150.1, -54.38), new PlanetExpP0(0.0042, 130.7, -42.16), new PlanetExpP0(0.0039, 7.6, 6.31), new PlanetExpP0(0.0031, 163.2, 12.22), new PlanetExpP0(0.0031, 145.6, 0.77), new PlanetExpP0(0.0024, 191.3, -0.23), new PlanetExpP0(0.0019, 148.4, 24.44), new PlanetExpP0(0.0017, 197.9, -29.941), new PlanetExpP0(0.001, 307.9, 36.66), new PlanetExpP0(0.001, 252.6, -72.51), new PlanetExpP0(0.001, 269.0, -60.29), new PlanetExpP0(0.001, 278.7, -29.53), new PlanetExpP0(8.0E-4, 52.0, -66.6), new PlanetExpP0(8.0E-4, 24.0, -35.8), new PlanetExpP0(5.0E-4, 356.0, -5.5) };
        JupiterB = new PlanetExpP0[] { new PlanetExpP0(0.001, 291.9, -29.94), new PlanetExpP0(3.0E-4, 196.0, -24.0) };
        JupiterQ = new PlanetExpP0[] { new PlanetExpP0(2.3E-4, 38.47, -36.256), new PlanetExpP0(1.68E-4, 293.36, -29.941), new PlanetExpP0(7.4E-5, 200.5, -24.03), new PlanetExpP0(5.5E-5, 110.0, -18.13), new PlanetExpP0(3.8E-5, 39.3, -5.91), new PlanetExpP0(2.4E-5, 150.9, -54.38), new PlanetExpP0(2.3E-5, 336.4, 0.41), new PlanetExpP0(1.9E-5, 131.7, -42.16), new PlanetExpP0(9.0E-6, 180.0, -11.8), new PlanetExpP0(7.0E-6, 277.0, -60.3), new PlanetExpP0(6.0E-6, 330.0, 24.4), new PlanetExpP0(6.0E-6, 53.0, -66.6), new PlanetExpP0(6.0E-6, 188.0, 6.3), new PlanetExpP0(6.0E-6, 251.0, -72.5), new PlanetExpP0(6.0E-6, 198.0, -29.9), new PlanetExpP0(5.0E-6, 353.5, 12.22) };
        JupiterP = new PlanetExpP2(13.6526, 0.01396, 0.0075, 5.94, 5.528, 0.1666, 0.007, 3.0E-4, 0.022889, 272.975, 0.0128, 1.0E-4, 35.52, 5.190688, 0.048254);
        SaturnN = new PlanetExpP0[] { new PlanetExpP0(0.8081, 342.74, 0.385), new PlanetExpP0(0.19, 3.57, -11.813), new PlanetExpP0(0.1173, 224.52, -5.907), new PlanetExpP0(0.0093, 176.6, 6.31), new PlanetExpP0(0.0089, 218.5, -36.26), new PlanetExpP0(0.008, 10.4, -0.23), new PlanetExpP0(0.0078, 56.8, 0.63), new PlanetExpP0(0.0074, 325.4, 0.77), new PlanetExpP0(0.0073, 209.4, -24.03), new PlanetExpP0(0.0064, 202.0, -11.59), new PlanetExpP0(-0.0048, 248.6, -19.34), new PlanetExpP0(0.0034, 105.2, -30.35), new PlanetExpP0(0.0034, 23.6, -15.87), new PlanetExpP0(0.0025, 348.4, -11.41), new PlanetExpP0(0.0022, 102.5, -7.94), new PlanetExpP0(0.0021, 53.5, -3.65), new PlanetExpP0(0.002, 220.4, -18.13), new PlanetExpP0(0.0018, 326.7, -54.38), new PlanetExpP0(0.0017, 173.0, -5.5), new PlanetExpP0(0.0014, 165.5, -5.91), new PlanetExpP0(0.0013, 307.9, -42.16) };
        SaturnB = new PlanetExpP0[] { new PlanetExpP0(0.0024, 3.9, -11.81), new PlanetExpP0(8.0E-4, 269.0, -5.9), new PlanetExpP0(5.0E-4, 135.0, -30.3) };
        SaturnQ = new PlanetExpP0[] { new PlanetExpP0(7.01E-4, 3.43, -11.813), new PlanetExpP0(3.78E-4, 110.54, -18.128), new PlanetExpP0(2.44E-4, 219.13, -5.907), new PlanetExpP0(1.14E-4, 158.22, 0.383), new PlanetExpP0(6.4E-5, 218.1, -36.26), new PlanetExpP0(4.2E-5, 215.8, -24.03), new PlanetExpP0(2.4E-5, 201.8, -11.59), new PlanetExpP0(2.4E-5, 1.3, 6.31), new PlanetExpP0(1.9E-5, 307.7, 12.22), new PlanetExpP0(1.5E-5, 326.3, -54.38), new PlanetExpP0(1.0E-5, 311.1, -42.16), new PlanetExpP0(1.0E-5, 83.2, 24.44), new PlanetExpP0(9.0E-6, 348.0, -11.4), new PlanetExpP0(8.0E-6, 129.0, -30.3), new PlanetExpP0(6.0E-6, 295.0, -29.9), new PlanetExpP0(6.0E-6, 148.0, -48.5), new PlanetExpP0(6.0E-6, 103.0, -7.9), new PlanetExpP0(5.0E-6, 318.0, 24.4), new PlanetExpP0(5.0E-6, 24.0, -15.9) };
        SaturnP = new PlanetExpP2(91.856, 0.01396, 0.0272, 135.53, 6.4215, 0.2248, 0.0109, 6.0E-4, 0.043519, 337.763, 0.0286, 2.3E-4, 77.06, 9.508863, 0.056061);
        UranusLambda = new PlanetExpP0[] { new PlanetExpP0(5.35857, 460.61987, 48.85031), new PlanetExpP0(0.58964, 919.0429, 188.3245), new PlanetExpP0(0.12397, 1065.1192, 354.5935), new PlanetExpP0(0.01475, 2608.702, 351.028), new PlanetExpP0(9.0E-4, 1968.3, 247.7), new PlanetExpP0(3.6E-4, 5647.4, 10.4), new PlanetExpP0(1.7E-4, 2356.6, 183.6), new PlanetExpP0(1.7E-4, 2873.2, 321.9), new PlanetExpP0(1.4E-4, 3157.9, 308.1) };
        UranusBeta = new PlanetExpP0[] { new PlanetExpP0(1.15483, 419.91739, 128.15303), new PlanetExpP0(0.67756, 652.9504, 273.6644), new PlanetExpP0(0.1349, 998.0302, 83.3517), new PlanetExpP0(2.5E-4, 3030.9, 194.2) };
        UranusR = new PlanetExpP0[] { new PlanetExpP0(0.90579, 408.729, 320.313), new PlanetExpP0(0.06271, 799.95, 67.99), new PlanetExpP0(0.004897, 2613.7, 80.4), new PlanetExpP0(6.56E-4, 1527.0, 202.0), new PlanetExpP0(2.23E-4, 2120.0, 321.0), new PlanetExpP0(2.05E-4, 3104.0, 37.0), new PlanetExpP0(1.2E-4, 5652.0, 100.0) };
        NeptuneLambda = new PlanetExpP0[] { new PlanetExpP0(0.9745, 221.3904, 167.7269), new PlanetExpP0(0.01344, 986.281, 50.826), new PlanetExpP0(0.00945, 2815.89, 0.09), new PlanetExpP0(0.00235, 2266.5, 309.35), new PlanetExpP0(0.00225, 2279.43, 127.61), new PlanetExpP0(2.3E-4, 5851.6, 19.2) };
        NeptuneBeta = new PlanetExpP0[] { new PlanetExpP0(1.76958, 218.87906, 83.11018), new PlanetExpP0(0.01366, 447.128, 338.864), new PlanetExpP0(1.5E-4, 1107.1, 224.7), new PlanetExpP0(1.5E-4, 2596.7, 187.5), new PlanetExpP0(1.2E-4, 3035.0, 243.9) };
        NeptuneR = new PlanetExpP0[] { new PlanetExpP0(0.260457, 222.371, 79.994), new PlanetExpP0(0.004944, 2815.4, 90.1), new PlanetExpP0(0.003364, 524.0, 308.1), new PlanetExpP0(0.002579, 1025.1, 104.0), new PlanetExpP0(1.2E-4, 5845.0, 111.0) };
        PlutoLambda = new PlanetExpP0[] { new PlanetExpP0(15.81087, 246.556453, 298.348019), new PlanetExpP0(1.18379, 551.3471, 351.67676), new PlanetExpP0(0.07886, 941.622, 41.989), new PlanetExpP0(0.00861, 2836.46, 60.35), new PlanetExpP0(0.0059, 1306.75, 112.91), new PlanetExpP0(0.00145, 2488.14, 19.01), new PlanetExpP0(2.2E-4, 5861.8, 77.9), new PlanetExpP0(1.3E-4, 3288.8, 293.0) };
        PlutoBeta = new PlanetExpP0[] { new PlanetExpP0(17.0455, 172.554318, 42.574982), new PlanetExpP0(2.4531, 415.6063, 66.1535), new PlanetExpP0(0.26775, 713.1227, 105.084), new PlanetExpP0(0.01855, 1089.202, 146.66), new PlanetExpP0(0.00119, 2658.22, 293.06), new PlanetExpP0(9.8E-4, 3055.6, 18.8), new PlanetExpP0(9.0E-4, 1532.6, 213.7), new PlanetExpP0(4.2E-4, 2342.3, 254.2) };
        PlutoR = new PlanetExpP0[] { new PlanetExpP0(8.670489, 181.3383, 198.4973), new PlanetExpP0(0.333884, 475.963, 228.717), new PlanetExpP0(0.008426, 909.8, 252.9), new PlanetExpP0(0.004902, 2831.6, 149.4), new PlanetExpP0(0.001188, 1748.0, 114.1), new PlanetExpP0(3.9E-4, 3188.0, 15.0), new PlanetExpP0(1.16E-4, 5860.0, 169.0) };
        SunLambda = new PlanetExpP0[] { new PlanetExpP0(0.02, 353.06, 719.981), new PlanetExpP0(-0.0048, 248.64, -19.341), new PlanetExpP0(0.002, 285.0, 329.64), new PlanetExpP0(0.0018, 334.2, -4452.67), new PlanetExpP0(0.0018, 293.7, -0.2), new PlanetExpP0(0.0015, 242.4, 450.37), new PlanetExpP0(0.0013, 211.1, 225.18), new PlanetExpP0(8.0E-4, 208.0, 659.29), new PlanetExpP0(7.0E-4, 53.5, 90.38), new PlanetExpP0(7.0E-4, 12.1, -30.35), new PlanetExpP0(6.0E-4, 239.1, 337.18), new PlanetExpP0(5.0E-4, 10.1, -1.5), new PlanetExpP0(5.0E-4, 99.1, -22.81), new PlanetExpP0(4.0E-4, 264.8, 315.56), new PlanetExpP0(4.0E-4, 233.8, 299.3), new PlanetExpP0(-4.0E-4, 198.1, 720.02), new PlanetExpP0(3.0E-4, 349.6, 1079.97), new PlanetExpP0(3.0E-4, 241.2, -44.43) };
        SunQ = new PlanetExpP0[] { new PlanetExpP0(-9.1E-5, 353.1, 719.98), new PlanetExpP0(1.3E-5, 205.8, 4452.67), new PlanetExpP0(7.0E-6, 62.0, 450.4), new PlanetExpP0(7.0E-6, 105.0, 329.6) };
    }
}
