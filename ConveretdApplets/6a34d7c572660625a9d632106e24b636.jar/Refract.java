// 
// Decompiled by Procyon v0.5.30
// 

class Refract
{
    static double k0;
    static double thet;
    static double n;
    static double Kr;
    static double conver;
    static double Ks;
    static double g;
    static double L;
    static double C;
    static double Cg;
    static double xkh0;
    static double cth;
    static double dxkh;
    static double th;
    static double ch;
    static double xkh;
    static double xkhzero;
    static double k;
    static double f;
    static double fprime;
    static final double G = 9.817;
    
    double groupVelocity(final double n, final double n2, final double n3) {
        Refract.n = 0.5 * (1.0 + 2.0 * n3 * n2 / Hyper.sinh(2.0 * n3 * n2));
        Refract.L = 6.283185307179586 / n3;
        Refract.C = Refract.L / n;
        return Refract.Cg = Refract.n * Refract.C;
    }
    
    float groupVelocity(final float n, final float n2, final float n3) {
        Refract.n = 0.5 * (1.0 + 2.0 * n3 * n2 / Hyper.sinh(2.0 * n3 * n2));
        Refract.L = 6.283185307179586 / n3;
        Refract.C = Refract.L / n;
        Refract.Cg = Refract.n * Refract.C;
        return (float)Refract.Cg;
    }
    
    double refractionCoef(final double n, final double n2) {
        return Refract.Kr = Math.sqrt(Math.cos(Refract.conver * n) / Math.cos(Refract.conver * n2));
    }
    
    float refractionCoef(final float n, final float n2) {
        Refract.Kr = Math.sqrt(Math.cos(Refract.conver * n) / Math.cos(Refract.conver * n2));
        return (float)Refract.Kr;
    }
    
    double shoalingCoef(final double n, final double n2) {
        return Refract.Ks = Math.sqrt(9.817 * n / (12.566370614359172 * n2));
    }
    
    float shoalingCoef(final float n, final float n2) {
        Refract.Ks = Math.sqrt(9.817 * n / (12.566370614359172 * n2));
        return (float)Refract.Ks;
    }
    
    double theta(final double n, final double n2, final double n3) {
        Refract.k0 = Math.pow(6.283185307179586 / n3, 2.0) / 9.817;
        Refract.conver = 0.017453292519943295;
        return Refract.thet = Math.asin(Refract.k0 * Math.sin(Refract.conver * n) / n2) / Refract.conver;
    }
    
    float theta(final float n, final float n2, final float n3) {
        Refract.k0 = Math.pow(6.283185307179586 / n3, 2.0) / 9.817;
        Refract.conver = 0.017453292519943295;
        Refract.thet = Math.asin(Refract.k0 * Math.sin(Refract.conver * n) / n2) / Refract.conver;
        return (float)Refract.thet;
    }
    
    double waveNumber(final double n, final double n2) {
        Refract.xkh0 = Math.pow(6.283185307179586 / n2, 2.0) * n / 9.817;
        Refract.cth = Hyper.cotanh(Math.pow(Refract.xkh0, 0.75));
        Refract.xkh = Refract.xkh0 * Math.pow(Refract.cth, 0.666);
        Refract.xkhzero = Refract.xkh / n;
        do {
            Refract.th = Hyper.tanh(Refract.xkh);
            Refract.ch = Hyper.cosh(Refract.xkh);
            Refract.f = Refract.xkh0 - Refract.xkh * Refract.th;
            Refract.fprime = -Refract.xkh * Math.pow(1.0 / Refract.ch, 2.0) - Refract.th;
            Refract.dxkh = -Refract.f / Refract.fprime;
            Refract.xkh += Refract.dxkh;
        } while (Math.abs(Refract.dxkh / Refract.xkh) > 1.0E-8);
        return Refract.k = Refract.xkh / n;
    }
    
    float waveNumber(final float n, final float n2) {
        Refract.xkh0 = Math.pow(6.283185307179586 / n2, 2.0) * n / 9.817;
        Refract.cth = Hyper.cotanh(Math.pow(Refract.xkh0, 0.75));
        Refract.xkh = Refract.xkh0 * Math.pow(Refract.cth, 0.666);
        Refract.xkhzero = Refract.xkh / n;
        do {
            Refract.th = Hyper.tanh(Refract.xkh);
            Refract.ch = Hyper.cosh(Refract.xkh);
            Refract.f = Refract.xkh0 - Refract.xkh * Refract.th;
            Refract.fprime = -Refract.xkh * Math.pow(1.0 / Refract.ch, 2.0) - Refract.th;
            Refract.dxkh = -Refract.f / Refract.fprime;
            Refract.xkh += Refract.dxkh;
        } while (Math.abs(Refract.dxkh / Refract.xkh) > 1.0E-8);
        Refract.k = Refract.xkh / n;
        return (float)Refract.k;
    }
}
