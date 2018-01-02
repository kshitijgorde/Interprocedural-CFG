// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import edu.wise.exceptions.InvalidRegressionException;
import edu.wise.stats.Correl;
import VisualNumerics.math.Statistics;
import edu.wise.exceptions.DataNotFoundException;
import edu.wise.utils.FormatUtils;

public class CorrelData
{
    private static Data data;
    private static FormatUtils fu;
    private String[] names;
    private static final boolean DEBUG = false;
    private static Cor_app ca;
    
    static {
        CorrelData.fu = new FormatUtils();
    }
    
    public CorrelData(final Cor_app ca) {
        this(2, 10, ca);
        CorrelData.ca = ca;
    }
    
    public CorrelData(final int n, final int n2, final Cor_app ca) {
        this.names = new String[2];
        CorrelData.data = new Data(n, n2);
        this.names = new String[n];
        for (int i = 0; i < n; ++i) {
            this.names[i] = "var" + i;
        }
        CorrelData.ca = ca;
    }
    
    public CorrelData(final int n, final int n2, final String[] names, final Cor_app ca) {
        this(n, n2, ca);
        this.names = new String[names.length];
        this.names = names;
        CorrelData.ca = ca;
    }
    
    public CorrelData(final double[] array, final double[] array2, final String[] names, final Cor_app ca) {
        this.names = new String[2];
        CorrelData.ca = ca;
        CorrelData.data = new Data(array, array2);
        this.names = new String[names.length];
        this.names = names;
        listDataLong();
    }
    
    private static void listData() {
        System.out.println("begin data");
        for (int i = 0; i < N(); ++i) {
            try {
                System.out.println(String.valueOf(getXi(i)) + "\t" + getYi(i));
            }
            catch (DataNotFoundException ex) {
                System.out.println("here");
            }
        }
        System.out.println("end data.\n");
    }
    
    private static void listDataLong() {
        System.out.println("title CORREL DATA DOUBLE CHECK. \ndata list free /x y. \nbegin data");
        for (int i = 0; i < N(); ++i) {
            try {
                System.out.println(String.valueOf(getXi(i)) + "\t" + getYi(i));
            }
            catch (DataNotFoundException ex) {
                System.out.println("here");
            }
        }
        System.out.println("end data.\nREGRESSION\n/STATISTICS COEFF OUTS R ANOVA\n/DEPENDENT y\n/METHOD=ENTER x  .\nfreq var= x y\n\t/for=not\n\t/stat all.\n");
    }
    
    public String toString2() {
        return this.toString2(3);
    }
    
    public String toString2(final int n) {
        final double[] array = new double[2];
        Statistics.linearFit(getXArr(), getYArr());
        String string = "";
        try {
            string = String.valueOf(string) + "" + string.concat(String.valueOf(this.getXName()) + ":" + '\n') + string.concat("     Mean = " + FormatUtils.rounder_str(Statistics.average(getXArr()), n) + " sd = " + FormatUtils.rounder_str(Statistics.standardDeviation(getXArr()), n)) + string.concat(String.valueOf('\n') + this.getYName() + ":" + '\n') + string.concat("     Mean = " + FormatUtils.rounder_str(Statistics.average(getYArr()), n) + " sd = " + FormatUtils.rounder_str(Statistics.standardDeviation(getYArr()), n)) + string.concat("") + string.concat("\n\nCorrelation Statistics:") + string.concat("\n     r = " + FormatUtils.rounder_str(Correl.r(getXArr(), getYArr()), n)) + "     " + "t(" + (N() - 2) + ") = " + FormatUtils.rounder_str(t(), n) + "     p = " + FormatUtils.rounder_str(tToP(), n);
        }
        catch (InvalidRegressionException ex) {}
        return string;
    }
    
    public static int dfPred() {
        return 1;
    }
    
    public static int dfErr() throws InvalidRegressionException {
        if (N() < 3) {
            throw new InvalidRegressionException("dfErr based on N of " + N());
        }
        return N() - 2;
    }
    
    public static double dfTot() {
        return N() - 1;
    }
    
    public static double MSPred() throws InvalidRegressionException {
        if (dfPred() < 1) {
            throw new InvalidRegressionException("dfPred=" + dfPred());
        }
        return SSPred() / dfPred();
    }
    
    public static double MSTot() throws InvalidRegressionException {
        return MSPred() + MSErr();
    }
    
    public static double MSErr() throws InvalidRegressionException {
        return SSErr() / dfErr();
    }
    
    public static double F() throws InvalidRegressionException {
        return MSPred() / MSErr();
    }
    
    public static double P() throws InvalidRegressionException {
        return 1.0 - Statistics.FCdf(F(), dfPred(), dfErr());
    }
    
    public static double tToP() throws InvalidRegressionException {
        return Correl.tToP(t(), N(), 2);
    }
    
    public static int N() {
        return CorrelData.data.getXArr().length;
    }
    
    public static int k() {
        return 1;
    }
    
    public static double xbar() {
        return Statistics.average(CorrelData.data.getXArr());
    }
    
    public static double Ybar() {
        return Statistics.average(CorrelData.data.getYArr());
    }
    
    public static double sd_x() {
        return Statistics.standardDeviation(CorrelData.data.getXArr());
    }
    
    public static double sd_y() {
        return Statistics.standardDeviation(CorrelData.data.getYArr());
    }
    
    public static double t() throws InvalidRegressionException {
        return Correl.tVal(r(), N());
    }
    
    public static double slope() {
        return Statistics.linearFit(CorrelData.data.getXArr(), CorrelData.data.getYArr())[1];
    }
    
    public static double intercept() {
        return Statistics.linearFit(CorrelData.data.getXArr(), CorrelData.data.getYArr())[0];
    }
    
    public static double r() throws InvalidRegressionException {
        return Correl.r(getXArr(), getYArr());
    }
    
    public static double rSqr() throws InvalidRegressionException {
        return Correl.rSqr(getXArr(), getYArr());
    }
    
    public static double rAdj() throws InvalidRegressionException {
        return Correl.rAdj(rSqr(), N(), k());
    }
    
    public static double stErrOfPred() throws InvalidRegressionException {
        return Math.sqrt(SSErr() / dfErr());
    }
    
    public static double SSPred() throws InvalidRegressionException {
        if (N() <= 2) {
            throw new InvalidRegressionException("CorrelData.SSPred");
        }
        final double[] ypredArr = getYpredArr();
        final double ybar = Ybar();
        double n = 0.0;
        for (int i = 0; i < ypredArr.length; ++i) {
            n += Math.pow(ypredArr[i] - ybar, 2.0);
        }
        return n;
    }
    
    public static double SSErr() throws InvalidRegressionException {
        if (N() <= 2) {
            throw new InvalidRegressionException("CorrelData.SSErr");
        }
        final double[] ypredArr = getYpredArr();
        double n = 0.0;
        for (int i = 0; i < ypredArr.length; ++i) {
            try {
                n += Math.pow(getYi(i) - ypredArr[i], 2.0);
            }
            catch (DataNotFoundException ex) {}
        }
        return n;
    }
    
    public static double SSTot() throws InvalidRegressionException {
        if (N() <= 2) {
            throw new InvalidRegressionException("CorrelData.SSErr");
        }
        final double ybar = Ybar();
        double n = 0.0;
        for (int i = 0; i < N(); ++i) {
            try {
                n += Math.pow(getYi(i) - ybar, 2.0);
            }
            catch (DataNotFoundException ex) {}
        }
        return n;
    }
    
    public static double[] getXArr() {
        return CorrelData.data.getXArr();
    }
    
    public static double[] getYArr() {
        return CorrelData.data.getYArr();
    }
    
    public static double getXi(final int n) throws DataNotFoundException {
        return CorrelData.data.getXi(n);
    }
    
    public static double getYi(final int n) throws DataNotFoundException {
        return CorrelData.data.getYi(n);
    }
    
    public static double[] getYbarArr() {
        return CorrelData.data.getYbarArr();
    }
    
    public static double getYbar() throws DataNotFoundException {
        try {
            return CorrelData.data.getYbarArr()[0];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("CorrelData.getYbar failed");
        }
    }
    
    public static double[] getYpredArr() {
        return CorrelData.data.getYpredArr();
    }
    
    public static double get_val(final int n, final int n2) throws DataNotFoundException {
        return getYi(n2);
    }
    
    public String getXName() {
        return this.getVarName(0);
    }
    
    public String getYName() {
        return this.getVarName(1);
    }
    
    public String getVarName(final int n) {
        try {
            return this.names[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return "var" + (char)(65 + n);
        }
    }
    
    public static void set_val(final int n, final int n2, final double n3) throws DataNotFoundException {
        if (n == 0) {
            setXi(n2, n3);
        }
        else {
            setYi(n2, n3);
        }
        update();
    }
    
    public static void setXArr(final double[] xArr) {
        CorrelData.data.setXArr(xArr);
    }
    
    public static void setYArr(final double[] yArr) {
        CorrelData.data.setYArr(yArr);
    }
    
    public static void setXi(final int n, final double n2) {
        CorrelData.data.setXi(n, n2);
    }
    
    public static void setYi(final int n, final double n2) {
        CorrelData.data.setYi(n, n2);
    }
    
    public static void setYpredArr(final double[] ypredArr) {
        CorrelData.data.setYpredArr(ypredArr);
    }
    
    public static void setRegType(final int regType) {
        CorrelData.data.setRegType(regType);
    }
    
    public static void update() {
        Data.update();
    }
    
    public static void update(final Data data) {
        CorrelData.data = data;
        Cor_app.update();
    }
    
    public void setXName(final int n, final String s) {
        this.setNames(n, s);
    }
    
    public void setYName(final int n, final String s) {
        this.setNames(n, s);
    }
    
    public void setNames(final int n, final String s) {
        try {
            this.names[n] = s;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("names out of bounds i = " + n);
        }
    }
    
    public static void addCase(final double n, final double n2) {
        CorrelData.data.addCase(n, n2);
    }
    
    public static void delPt(final int n) {
        CorrelData.data.delPt(n);
    }
    
    public static void changePt(final double n, final double n2, final int n3) {
        CorrelData.data.changePt(n, n2, n3);
    }
}
