// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class Comet
{
    private String strName;
    private double fT;
    private double fE;
    private double fQ;
    private double fPeri;
    private double fNode;
    private double fIncl;
    private double fEquinox;
    private ATime atimeEquinox;
    private Matrix mtxVC;
    private static final double TOLERANCE = 1.0E-12;
    private static final int MAXAPPROX = 80;
    
    public Comet(final String strName, final double ft, final double fe, final double fq, final double fPeri, final double fNode, final double fIncl, final double fEquinox) {
        this.strName = strName;
        this.fT = ft;
        this.fE = fe;
        this.fQ = fq;
        this.fPeri = fPeri;
        this.fNode = fNode;
        this.fIncl = fIncl;
        this.fEquinox = fEquinox;
        final int n = (int)Math.floor(fEquinox);
        final double n2 = (fEquinox - n) * 12.0;
        final int n3 = (int)Math.floor(n2);
        this.atimeEquinox = new ATime(n, n3, (n2 - n3) * 30.0, 0.0);
        this.mtxVC = Matrix.VectorConstant(fPeri, fNode, fIncl, this.atimeEquinox);
    }
    
    private Xyz CometStatusEllip(final double n) {
        if (this.fQ == 0.0) {
            throw new ArithmeticException();
        }
        final double n2 = this.fQ / (1.0 - this.fE);
        final double n3 = 0.01720209895 * (n - this.fT) / (Math.sqrt(n2) * n2);
        double n4 = n3 + this.fE * Math.sin(n3);
        int n5 = 80;
        if (this.fE < 0.6) {
            do {
                final double n6 = n4;
                n4 = n3 + this.fE * Math.sin(n6);
                if (Math.abs(n4 - n6) > 1.0E-12) {
                    continue;
                }
                break;
            } while (--n5 > 0);
        }
        else {
            double n7;
            do {
                final double n8 = n3 + this.fE * Math.sin(n4) - n4;
                final double n9 = 1.0 - this.fE * Math.cos(n4);
                if (Math.abs(n8) < 1.0E-12) {
                    break;
                }
                if (Math.abs(n9) < 1.0E-12) {
                    break;
                }
                n7 = n8 / n9;
                n4 += n7;
            } while (Math.abs(n7) > 1.0E-12 && --n5 > 0);
        }
        if (n5 == 0) {
            throw new ArithmeticException();
        }
        return new Xyz(n2 * (Math.cos(n4) - this.fE), n2 * Math.sqrt(1.0 - this.fE * this.fE) * Math.sin(n4), 0.0);
    }
    
    private Xyz CometStatusPara(final double n) {
        if (this.fQ == 0.0) {
            throw new ArithmeticException();
        }
        double n3;
        final double n2 = n3 = 0.01720209895 * (n - this.fT) / (Math.sqrt(2.0) * this.fQ * Math.sqrt(this.fQ));
        int n4 = 80;
        double n5;
        do {
            n5 = n3;
            final double n6 = n3 * n3;
            n3 = (n6 * n3 * 2.0 / 3.0 + n2) / (1.0 + n6);
        } while (Math.abs(n3 - n5) > 1.0E-12 && --n4 > 0);
        if (n4 == 0) {
            throw new ArithmeticException();
        }
        return new Xyz(this.fQ * (1.0 - n3 * n3), 2.0 * this.fQ * n3, 0.0);
    }
    
    private Xyz CometStatusNearPara(final double n) {
        if (this.fQ == 0.0) {
            throw new ArithmeticException();
        }
        final double sqrt = Math.sqrt((1.0 + 9.0 * this.fE) / 10.0);
        final double n2 = 5.0 * (1.0 - this.fE) / (1.0 + 9.0 * this.fE);
        double n5;
        double n4;
        double n3 = n4 = (n5 = 1.0);
        int n6 = 80;
        double n7;
        do {
            n7 = n4;
            final double n8 = n3 * sqrt * 0.01720209895 * (n - this.fT) / (Math.sqrt(2.0) * this.fQ * Math.sqrt(this.fQ));
            int n9 = 80;
            double n10;
            do {
                n10 = n5;
                final double n11 = n10 * n10;
                n5 = (n11 * n10 * 2.0 / 3.0 + n8) / (1.0 + n11);
            } while (Math.abs(n5 - n10) > 1.0E-12 && --n9 > 0);
            if (n9 == 0) {
                throw new ArithmeticException();
            }
            n4 = n2 * n5 * n5;
            n3 = (-0.003809524 * n4 - 0.017142857) * n4 * n4 + 1.0;
        } while (Math.abs(n4 - n7) > 1.0E-12 && --n6 > 0);
        if (n6 == 0) {
            throw new ArithmeticException();
        }
        final double n12 = ((0.12495238 * n4 + 0.21714286) * n4 + 0.4) * n4 + 1.0;
        final double n13 = ((0.00571429 * n4 + 0.2) * n4 - 1.0) * n4 + 1.0;
        final double n14 = Math.sqrt(5.0 * (1.0 + this.fE) / (1.0 + 9.0 * this.fE)) * n12 * n5;
        return new Xyz(this.fQ * n13 * (1.0 - n14 * n14), 2.0 * this.fQ * n13 * n14, 0.0);
    }
    
    public Xyz GetPos(final double n) {
        Xyz xyz;
        if (this.fE < 0.98) {
            xyz = this.CometStatusEllip(n);
        }
        else if (Math.abs(this.fE - 1.0) < 1.0E-12) {
            xyz = this.CometStatusPara(n);
        }
        else {
            xyz = this.CometStatusNearPara(n);
        }
        return xyz.Rotate(this.mtxVC).Rotate(Matrix.PrecMatrix(this.atimeEquinox.getJd(), 2451545.0));
    }
    
    public String getName() {
        return this.strName;
    }
    
    public double getT() {
        return this.fT;
    }
    
    public double getE() {
        return this.fE;
    }
    
    public double getQ() {
        return this.fQ;
    }
    
    public double getPeri() {
        return this.fPeri;
    }
    
    public double getNode() {
        return this.fNode;
    }
    
    public double getIncl() {
        return this.fIncl;
    }
    
    public double getEquinox() {
        return this.fEquinox;
    }
    
    public double getEquinoxJd() {
        return this.atimeEquinox.getJd();
    }
    
    public Matrix getVectorConstant() {
        return this.mtxVC;
    }
}
