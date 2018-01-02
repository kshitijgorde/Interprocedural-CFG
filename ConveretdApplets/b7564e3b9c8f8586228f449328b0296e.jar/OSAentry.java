import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class OSAentry
{
    public int L;
    public int j;
    public int g;
    public int Ljg;
    public Color color;
    public double x;
    public double y;
    public double z;
    public double R;
    public double G;
    public double B;
    double[][] xyz_imatrix;
    double monitorGamma;
    
    OSAentry(final int l, final int j, final int g, final double x, final double y, final double z) {
        this.xyz_imatrix = new double[][] { { 2.028515, -1.136533, 0.108017 }, { -0.9510597, 2.210624, -0.259564 }, { -0.3067103, 0.03540462, 1.271306 } };
        this.monitorGamma = 2.3;
        final double[] array = new double[3];
        this.L = l;
        this.j = j;
        this.g = g;
        this.Ljg = l * 10000 + this.j * 100 + this.g;
        array[0] = (this.x = x);
        array[1] = (this.y = y);
        array[2] = (this.z = z);
        this.R = 0.0;
        this.G = 0.0;
        this.B = 0.0;
        for (int i = 0; i < 3; ++i) {
            this.R += array[i] * this.xyz_imatrix[0][i];
            this.G += array[i] * this.xyz_imatrix[1][i];
            this.B += array[i] * this.xyz_imatrix[2][i];
        }
        this.R /= 100.0;
        this.G /= 100.0;
        this.B /= 100.0;
        this.R = Math.pow(this.R, 1.0 / this.monitorGamma);
        this.G = Math.pow(this.G, 1.0 / this.monitorGamma);
        this.B = Math.pow(this.B, 1.0 / this.monitorGamma);
        if (this.B < 0.0) {
            System.out.print("negative blue! ");
            System.out.print("L=" + this.L + ",j=" + this.j + ",g=" + this.g);
            System.out.println(" R=" + this.R + ",G=" + this.G + ",B=" + this.B);
            this.B = 0.0;
        }
        final Double n = new Double(this.R);
        final Double n2 = new Double(this.G);
        final Double n3 = new Double(this.B);
        try {
            this.color = new Color((float)(Object)n, (float)(Object)n2, (float)(Object)n3);
        }
        catch (Exception ex) {
            System.out.print("L=" + this.L + ",j=" + this.j + ",g=" + this.g);
            System.out.println(" R=" + this.R + ",G=" + this.G + ",B=" + this.B);
        }
    }
}
