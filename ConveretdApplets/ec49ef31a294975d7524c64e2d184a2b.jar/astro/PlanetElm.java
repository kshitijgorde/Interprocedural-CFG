// 
// Decompiled by Procyon v0.5.30
// 

package astro;

class PlanetElm
{
    double L;
    double node;
    double peri;
    double axis;
    double e;
    double incl;
    static final PlanetElmP1 MercuryE;
    static final PlanetElmP1 VenusE;
    static final PlanetElmP1 MarsE;
    static final PlanetElmP1 JupiterE;
    static final PlanetElmP1 SaturnE;
    static final PlanetElmP2 UranusE;
    static final PlanetElmP2 NeptuneE;
    static final PlanetElmP2 PlutoE;
    private static int[] perturbJup1;
    private static int[] perturbJup2;
    private static int[] perturbJup3;
    private static int[] perturbSat1;
    private static int[] perturbSat2;
    private static int[] perturbSat3;
    private static int[] perturbSat4;
    
    double perturbationElement(final double n, final double n2, final int[] array) {
        final int n3 = (int)(n / 30.0);
        final int n4 = n3 + 1;
        final int n5 = (int)(n2 / 30.0);
        final int n6 = n5 + 1;
        if (n3 >= 12 && n5 >= 12) {
            return array[n5 * 13 + n3];
        }
        if (n3 >= 12) {
            final double n7 = array[n5 * 13 + n3];
            return n7 + (array[n6 * 13 + n3] - n7) * (n2 / 30.0 - n5);
        }
        if (n5 >= 12) {
            final double n8 = array[n5 * 13 + n3];
            return n8 + (array[n5 * 13 + n4] - n8) * (n / 30.0 - n3);
        }
        final double n9 = array[n5 * 13 + n3];
        final double n10 = array[n5 * 13 + n4];
        final double n11 = array[n6 * 13 + n3];
        final double n12 = array[n6 * 13 + n4];
        final double n13 = n9 + (n11 - n9) * (n2 / 30.0 - n5);
        return n13 + (n10 + (n12 - n10) * (n2 / 30.0 - n5) - n13) * (n / 30.0 - n3);
    }
    
    private void perturbationJupiter(final double n) {
        final double n2 = (int)((n - 1721423.5) / 365.244 + 1.0) / 1000.0;
        final double n3 = (0.42 - 0.075 * n2 + 0.015 * n2 * n2 - 0.003 * n2 * n2 * n2) * UdMath.udsin((n2 - 0.62) * 360.0 / 0.925);
        final double n4 = 0.02 * UdMath.udsin((n2 + 0.1) * 360.0 / 0.925);
        final double n5 = 0.03 * UdMath.udsin((n2 + 0.36) * 360.0 / 0.925);
        final double degmal = UdMath.degmal(86.1 + 0.033459 * (n - 1721057.0));
        final double degmal2 = UdMath.degmal(89.1 + 0.04963 * (n - 1721057.0));
        final double n6 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbJup1) / 1000.0;
        final double n7 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbJup2) / 1000.0;
        final double n8 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbJup3) / 1000.0;
        double n9 = 2.58 + 0.1 * n2;
        if (n9 > 3.5) {
            n9 = 3.5;
        }
        if (n9 < 1.5) {
            n9 = 1.5;
        }
        this.L += n3 + n6;
        this.peri += (n4 + n7) / UdMath.udsin(n9);
        this.e = UdMath.udsin(n9 + n5 + n8);
    }
    
    void perturbationSaturn(final double n) {
        final double n2 = (int)((n - 1721423.5) / 365.244 + 1.0) / 1000.0;
        final double n3 = -0.5 + (0.88 - 0.0633 * n2 + 0.03 * n2 * n2 - 6.0E-4 * n2 * n2 * n2) * UdMath.udsin((n2 - 0.145) * 360.0 / 0.95);
        final double n4 = -0.5 + (0.1 - 0.005 * n2) * UdMath.udsin((n2 - 0.54) * 360.0 / 0.95);
        final double n5 = -0.5 + (0.1 - 0.005 * n2) * UdMath.udsin((n2 - 0.32) * 360.0 / 0.95);
        final double n6 = -0.05 + (0.004 - 5.0E-4 * n2) * UdMath.udsin((n2 - 0.35) * 360.0 / 0.95);
        final double degmal = UdMath.degmal(86.1 + 0.033459 * (n - 1721057.0));
        final double degmal2 = UdMath.degmal(89.1 + 0.04963 * (n - 1721057.0));
        final double n7 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbSat1) / 100.0;
        final double n8 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbSat2) / 100.0;
        final double n9 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbSat3) / 100.0;
        final double n10 = this.perturbationElement(degmal, degmal2, PlanetElm.perturbSat4) / 1000.0;
        double n11 = 3.56 - 0.175 * n2 - 0.005 * n2 * n2;
        if (n11 < 2.0) {
            n11 = 2.0;
        }
        this.L += n3 + n7;
        this.peri += (n4 + n8) / UdMath.udsin(n11);
        this.e = UdMath.udsin(n11 + n5 + n9);
        this.axis += n6 + n10;
    }
    
    private void getPlanetElm1(final int n, final double n2) {
        final double n3 = (n2 - 2415021.0) / 36525.0;
        final double n4 = n3 * n3;
        PlanetElmP1 planetElmP1 = null;
        switch (n) {
            case 1: {
                planetElmP1 = PlanetElm.MercuryE;
                break;
            }
            case 2: {
                planetElmP1 = PlanetElm.VenusE;
                break;
            }
            case 4: {
                planetElmP1 = PlanetElm.MarsE;
                break;
            }
            case 5: {
                planetElmP1 = PlanetElm.JupiterE;
                break;
            }
            case 6: {
                planetElmP1 = PlanetElm.SaturnE;
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
        this.L = UdMath.degmal(planetElmP1.L + planetElmP1.L1 * n3 + planetElmP1.L2 * n4 + planetElmP1.L3 * n3 * n4);
        this.node = UdMath.degmal(planetElmP1.node + planetElmP1.n1 * n3 + planetElmP1.n2 * n4 + planetElmP1.n3 * n3 * n4);
        this.peri = UdMath.degmal(planetElmP1.peri + planetElmP1.p1 * n3 + planetElmP1.p2 * n4 + planetElmP1.p3 * n3 * n4 - this.node);
        this.axis = planetElmP1.axis;
        this.e = UdMath.degmal(planetElmP1.e + planetElmP1.e1 * n3 + planetElmP1.e2 * n4 + planetElmP1.e3 * n3 * n4);
        this.incl = UdMath.degmal(planetElmP1.incl + planetElmP1.i1 * n3 + planetElmP1.i2 * n4 + planetElmP1.i3 * n3 * n4);
        switch (n) {
            case 5: {
                this.perturbationJupiter(n2);
                break;
            }
            case 6: {
                this.perturbationSaturn(n2);
                break;
            }
        }
    }
    
    private void getPlanetElm2(final int n, final double n2) {
        final double n3 = (n2 - 2451545.0) / 36525.0;
        final double n4 = n3 * n3;
        final double n5 = n3 * 36525.0;
        PlanetElmP2 planetElmP2 = null;
        switch (n) {
            case 7: {
                planetElmP2 = PlanetElm.UranusE;
                break;
            }
            case 8: {
                planetElmP2 = PlanetElm.NeptuneE;
                break;
            }
            case 9: {
                planetElmP2 = PlanetElm.PlutoE;
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
        this.L = UdMath.degmal(planetElmP2.L + planetElmP2.L1 * n5 + planetElmP2.L2 * n4);
        this.node = UdMath.degmal(planetElmP2.node + planetElmP2.n1 * n3 + planetElmP2.n2 * n4);
        this.peri = UdMath.degmal(planetElmP2.peri + planetElmP2.p1 * n3 + planetElmP2.p2 * n4 - this.node);
        this.axis = UdMath.degmal(planetElmP2.axis + planetElmP2.a1 * n3 + planetElmP2.a2 * n4);
        this.e = UdMath.degmal(planetElmP2.e + planetElmP2.e1 * n3 + planetElmP2.e2 * n4);
        this.incl = UdMath.degmal(planetElmP2.incl + planetElmP2.i1 * n3 + planetElmP2.i2 * n4);
    }
    
    private void getPlanetElmEarth(final double n) {
        final double n2 = (n - 2415021.0) / 36525.0;
        final double n3 = n2 * n2;
        this.L = 180.0 + UdMath.degmal(280.6824 + 36000.769325 * n2 + 7.22222E-4 * n3);
        this.peri = 180.0 + UdMath.degmal(281.2206 + 1.717697 * n2 + 4.83333E-4 * n3 + 2.77777E-6 * n2 * n3);
        this.node = 0.0;
        this.incl = 0.0;
        this.e = 0.0167498 - 4.258E-5 * n2 - 1.37E-7 * n3;
        this.axis = 1.00000129;
    }
    
    public PlanetElm(final int n, final ATime aTime) {
        switch (n) {
            case 3: {
                this.getPlanetElmEarth(aTime.getJd());
                break;
            }
            case 1:
            case 2:
            case 4:
            case 5:
            case 6: {
                this.getPlanetElm1(n, aTime.getJd());
                break;
            }
            case 7:
            case 8:
            case 9: {
                this.getPlanetElm2(n, aTime.getJd());
                break;
            }
            default: {
                throw new ArithmeticException();
            }
        }
    }
    
    public Xyz getPos() {
        final double n = this.e * 180.0 / 3.141592653589793;
        double n3;
        final double n2 = n3 = this.L - (this.peri + this.node);
        double n4;
        do {
            n4 = n3;
            n3 = n2 + n * UdMath.udsin(n4);
        } while (Math.abs(n3 - n4) > 5.729577951308233E-4);
        final double n5 = this.axis * (UdMath.udcos(n3) - this.e);
        final double n6 = this.axis * Math.sqrt(1.0 - this.e * this.e) * UdMath.udsin(n3);
        final double udsin = UdMath.udsin(this.peri);
        final double udcos = UdMath.udcos(this.peri);
        final double udsin2 = UdMath.udsin(this.node);
        final double udcos2 = UdMath.udcos(this.node);
        final double udsin3 = UdMath.udsin(this.incl);
        final double udcos3 = UdMath.udcos(this.incl);
        return new Xyz(n5 * (udcos2 * udcos - udsin2 * udcos3 * udsin) - n6 * (udcos2 * udsin + udsin2 * udcos3 * udcos), n5 * (udsin2 * udcos + udcos2 * udcos3 * udsin) - n6 * (udsin2 * udsin - udcos2 * udcos3 * udcos), n5 * (udsin3 * udsin) + n6 * (udsin3 * udcos));
    }
    
    static {
        MercuryE = new PlanetElmP1(182.27175, 149474.07244, 0.00201944, 0.0, 75.89717, 1.553469, 3.08639E-4, 0.0, 47.144736, 1.18476, 2.23194E-4, 0.0, 7.003014, 0.00173833, -1.55555E-5, 0.0, 0.20561494, 2.03E-5, -4.0E-8, 0.0, 0.3870984);
        VenusE = new PlanetElmP1(344.36936, 58519.2126, 9.8055E-4, 0.0, 130.14057, 1.3723, -0.0016472, 0.0, 75.7881, 0.91403, 4.189E-4, 0.0, 3.3936, 0.0012522, -4.333E-6, 0.0, 0.00681636, -5.384E-5, 1.26E-7, 0.0, 0.72333015);
        MarsE = new PlanetElmP1(294.26478, 19141.69625, 3.15028E-4, 0.0, 334.21833, 1.840394, 3.35917E-4, 0.0, 48.7867, 0.776944, -6.02778E-4, 0.0, 1.8503, -6.49028E-4, 2.625E-5, 0.0, 0.0933088, 9.5284E-5, -1.22E-7, 0.0, 1.5236781);
        JupiterE = new PlanetElmP1(238.132386, 3036.301986, 3.34683E-4, -1.64889E-6, 12.720972, 1.6099617, 0.00105627, -3.4333E-6, 99.443414, 1.01053, 3.52222E-4, -8.51111E-6, 1.308736, -0.00569611, 3.88889E-6, 0.0, 0.0483348, 1.6418E-4, -4.676E-7, -1.7E-9, 5.202805);
        SaturnE = new PlanetElmP1(266.597875, 1223.50988, 3.24542E-4, -5.83333E-7, 91.09821, 1.958416, 8.26361E-4, 4.61111E-6, 112.790414, 0.873195, -1.52181E-4, -5.30555E-6, 2.49252, -0.00391889, -1.54889E-5, 4.44444E-8, 0.05589231, -3.455E-4, -7.28E-7, 7.4E-10, 9.55474);
        UranusE = new PlanetElmP2(314.055005, 0.01176903644, 3.043E-4, 173.005159, 1.4863784, 2.145E-4, 74.005947, 0.5211258, 0.0013399, 19.2184461, -3.7E-8, 0.0, 0.0462959, -2.7337E-5, 7.9E-8, 0.773196, 7.744E-4, 3.75E-5);
        NeptuneE = new PlanetElmP2(304.348665, 0.00602007691, 3.093E-4, 48.123691, 1.4262678, 3.792E-4, 131.784057, 1.1022035, 2.6E-4, 30.1103869, -1.66E-7, 0.0, 0.00898809, 6.408E-6, -1.0E-9, 1.769952, -0.0093082, -7.1E-6);
        PlutoE = new PlanetElmP2(238.467028, 0.00401595755, -0.0090561, 224.14163, 1.3900789, 3.019E-4, 110.318223, 1.3506963, 4.014E-4, 39.5403429, 0.00313105, -3.792E-5, 0.24900535, 3.885E-5, -5.62E-7, 17.145104, -0.0054981, -3.84E-5);
        PlanetElm.perturbJup1 = new int[] { -20, -27, -44, -36, -20, 10, 21, 27, 33, 25, 18, 8, -20, -14, -25, -57, -75, -70, -55, -25, -15, -2, 8, 1, -4, -15, 5, -5, -21, -55, -67, -72, -55, -28, -13, 0, 7, 10, 5, 24, 21, 9, -11, -37, -57, -55, -37, -15, 3, 13, 18, 23, 27, 29, 27, 15, 4, -25, -45, -38, -22, -5, 10, 25, 30, 15, 27, 39, 33, 25, -5, -27, -34, -30, -19, -6, 20, 21, 7, 15, 25, 31, 24, 8, -11, -26, -32, -27, -19, -6, 16, -3, 3, 15, 23, 22, 15, 0, -15, -26, -29, -25, -20, -4, -15, -5, 3, 17, 22, 20, 11, 5, -11, -26, -27, -25, -16, -17, -4, 10, 20, 25, 31, 25, 24, 15, -6, -15, -18, -13, 0, 2, 13, 28, 39, 49, 48, 38, 33, 27, 13, -1, -2, -1, 0, 6, 23, 39, 49, 63, 53, 48, 41, 35, 17, 4, -26, -30, -30, -25, -9, 17, 31, 34, 34, 25, 22, 13, 6 };
        PlanetElm.perturbJup2 = new int[] { 4, 15, 30, 40, 40, 25, 6, 8, -27, -43, -43, -28, -5, -24, -9, 7, 10, 27, 30, 31, 17, -4, -29, -43, -40, -27, -31, -24, -25, -5, 14, 31, 43, 43, 19, -6, -29, -43, -32, -39, -29, -21, -13, -4, 19, 36, 52, 35, 15, -11, -30, -36, -31, -30, -24, -19, -13, 0, 20, 35, 46, 31, 9, -17, -30, -26, -28, -28, -20, -17, -15, 0, 24, 46, 45, 25, 0, -28, -10, -23, -27, -23, -21, -22, -14, 4, 29, 40, 37, 17, -5, 15, -9, -20, -22, -23, -27, -21, -13, 12, 31, 40, 33, 15, 29, 13, -10, -18, -22, -27, -30, -25, -11, 16, 36, 42, 31, 45, 28, 8, -10, -20, -28, -33, -33, -26, 9, 22, 45, 44, 41, 45, 19, 9, -9, -21, -34, -34, -34, -19, -4, 26, 42, 22, 36, 42, 25, 14, 0, -18, -27, -34, -32, -21, -7, 26, 0, 11, 26, 39, 36, 25, 8, -8, -26, -38, -38, -28, -2 };
        PlanetElm.perturbJup3 = new int[] { 41, 33, 19, 4, -13, -28, -37, -42, -27, -9, 16, 30, 44, 27, 33, 33, 23, 15, 3, -22, -36, -43, -25, -10, 14, 27, 13, 23, 32, 33, 27, 22, 8, -22, -37, -42, -27, -10, 12, -5, 10, 18, 23, 34, 32, 25, 5, -26, -45, -47, -26, -5, -17, -2, 10, 18, 26, 35, 37, 22, -4, -27, -44, -42, -27, -33, -15, -1, 7, 16, 22, 36, 35, 16, -7, -28, -40, -36, -44, -27, -12, -6, 4, 16, 32, 54, 31, 12, -10, -31, -43, -37, -37, -24, -12, -2, 7, 17, 30, 42, 24, 11, -15, -33, -31, -36, -35, -24, -13, -4, 7, 21, 35, 38, 20, 6, -15, -19, -32, -40, -31, -21, -18, -5, 12, 25, 38, 42, 26, -6, 11, -14, -30, -44, -33, -27, -13, -1, 15, 29, 42, 39, 18, 31, 13, -6, -22, -34, -29, -27, -27, 9, 15, 25, 40, 35, 40, 31, 18, 6, -15, -28, -38, -40, -29, -13, 15, 25, 40 };
        PlanetElm.perturbSat1 = new int[] { 57, 59, 57, 60, 56, 48, 42, 41, 41, 42, 46, 50, 55, 61, 64, 70, 73, 74, 66, 61, 57, 55, 55, 55, 56, 56, 58, 61, 65, 71, 76, 76, 72, 66, 63, 61, 60, 58, 56, 55, 55, 58, 63, 68, 74, 73, 71, 67, 63, 61, 57, 55, 52, 51, 51, 55, 61, 67, 70, 70, 67, 62, 58, 55, 53, 49, 48, 47, 48, 52, 58, 63, 65, 63, 60, 56, 52, 50, 48, 46, 44, 43, 45, 49, 54, 57, 58, 56, 53, 50, 48, 46, 44, 41, 40, 39, 40, 45, 48, 50, 51, 50, 48, 46, 44, 42, 39, 37, 36, 35, 36, 39, 43, 45, 46, 45, 44, 42, 40, 36, 34, 32, 31, 31, 33, 37, 39, 41, 42, 44, 42, 39, 37, 33, 30, 29, 29, 30, 32, 34, 37, 40, 44, 45, 45, 43, 39, 35, 30, 29, 30, 33, 35, 38, 42, 45, 55, 57, 61, 56, 49, 45, 42, 40, 42, 43, 46, 50, 54 };
        PlanetElm.perturbSat2 = new int[] { 33, 37, 44, 52, 60, 66, 67, 65, 57, 46, 37, 32, 31, 34, 40, 50, 60, 67, 70, 67, 60, 50, 40, 33, 29, 31, 36, 42, 50, 60, 68, 72, 68, 59, 47, 38, 34, 34, 37, 45, 48, 52, 57, 62, 65, 63, 55, 45, 40, 39, 42, 44, 54, 55, 54, 53, 54, 55, 54, 49, 45, 43, 44, 48, 54, 57, 60, 55, 51, 46, 45, 44, 46, 47, 48, 51, 55, 57, 57, 59, 56, 50, 43, 39, 39, 44, 49, 52, 55, 57, 57, 53, 54, 52, 49, 44, 40, 41, 45, 51, 55, 57, 54, 54, 46, 44, 45, 47, 47, 48, 48, 51, 55, 57, 55, 51, 47, 37, 35, 37, 45, 52, 57, 60, 59, 58, 56, 52, 45, 39, 31, 29, 33, 43, 55, 65, 69, 66, 60, 55, 48, 40, 34, 32, 30, 35, 45, 56, 68, 72, 69, 60, 52, 43, 36, 32, 33, 36, 43, 51, 59, 65, 68, 65, 57, 47, 38, 34, 31 };
        PlanetElm.perturbSat3 = new int[] { 51, 60, 66, 67, 62, 56, 46, 40, 34, 31, 37, 45, 53, 59, 66, 70, 67, 60, 51, 40, 33, 30, 33, 40, 50, 60, 60, 65, 67, 66, 59, 50, 38, 31, 30, 35, 43, 52, 59, 58, 59, 60, 59, 55, 49, 40, 36, 36, 43, 50, 55, 57, 55, 52, 50, 50, 49, 47, 45, 45, 45, 50, 55, 56, 55, 53, 48, 44, 42, 43, 46, 50, 53, 55, 56, 57, 55, 53, 51, 47, 41, 38, 40, 47, 55, 59, 61, 59, 56, 53, 51, 48, 42, 44, 42, 44, 48, 55, 58, 58, 55, 51, 50, 48, 45, 49, 50, 50, 50, 51, 53, 55, 54, 50, 45, 43, 45, 46, 52, 59, 62, 61, 56, 53, 50, 46, 42, 39, 38, 41, 45, 54, 65, 71, 71, 63, 53, 43, 39, 35, 34, 35, 42, 48, 55, 65, 71, 70, 63, 51, 40, 34, 31, 33, 38, 44, 51, 60, 66, 68, 65, 58, 46, 38, 33, 32, 37, 46, 54 };
        PlanetElm.perturbSat4 = new int[] { 83, 82, 80, 78, 75, 74, 73, 73, 75, 77, 79, 81, 83, 81, 82, 82, 81, 80, 77, 75, 72, 72, 75, 77, 80, 81, 77, 70, 77, 75, 75, 75, 70, 67, 65, 64, 65, 68, 70, 50, 51, 54, 58, 60, 61, 59, 56, 52, 49, 47, 47, 49, 30, 32, 34, 37, 40, 42, 42, 40, 36, 31, 30, 29, 30, 17, 18, 19, 20, 22, 24, 27, 26, 21, 19, 17, 15, 17, 13, 13, 12, 12, 14, 15, 17, 18, 17, 16, 15, 14, 13, 20, 19, 18, 17, 17, 18, 20, 21, 24, 24, 23, 21, 20, 31, 31, 32, 32, 31, 31, 32, 35, 37, 38, 36, 34, 32, 50, 50, 53, 53, 52, 51, 51, 52, 53, 53, 52, 50, 50, 68, 69, 71, 72, 72, 70, 69, 68, 68, 68, 70, 70, 67, 80, 80, 79, 80, 80, 79, 77, 76, 74, 76, 77, 80, 80, 83, 83, 80, 78, 75, 75, 76, 76, 76, 76, 79, 81, 83 };
    }
}
