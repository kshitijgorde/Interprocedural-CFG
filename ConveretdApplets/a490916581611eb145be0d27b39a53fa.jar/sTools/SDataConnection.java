// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

public class SDataConnection
{
    int series;
    SDataSource ds;
    SDataListener dl;
    String xStr;
    String yStr;
    Parser xparser;
    Parser yparser;
    String[] vars;
    
    public SDataConnection(final SDataSource ds, final SDataListener dl, final int series, final String s, final String s2) {
        this.ds = null;
        this.dl = null;
        this.xparser = null;
        this.yparser = null;
        this.vars = null;
        this.series = series;
        this.ds = ds;
        this.dl = dl;
        this.xStr = s;
        this.yStr = s2;
        this.vars = ds.getVarStrings();
        this.setXStr(s);
        this.setYStr(s2);
        dl.clearSeries(series);
    }
    
    public boolean setXStr(final String s) {
        this.xStr = s.trim();
        if (this.xStr.equals("") || this.xStr.equals("0")) {
            this.xparser = null;
            return true;
        }
        this.xparser = new Parser(this.vars.length);
        for (int i = 0; i < this.vars.length; ++i) {
            this.xparser.defineVariable(i + 1, this.vars[i]);
        }
        this.xparser.define(this.xStr);
        this.xparser.parse();
        if (this.xparser.getErrorCode() != 0) {
            System.out.println(String.valueOf("Failed to parse horizontal datasource): ").concat(String.valueOf(this.xparser)));
            System.out.println(String.valueOf(String.valueOf(String.valueOf("Parse error: ").concat(String.valueOf(this.xparser.getErrorString()))).concat(String.valueOf(" at function 1, position "))).concat(String.valueOf(this.xparser.getErrorPosition())));
            return false;
        }
        return true;
    }
    
    public boolean setYStr(final String s) {
        this.yStr = s.trim();
        if (this.yStr.equals("") || this.yStr.equals("0")) {
            this.yparser = null;
            return true;
        }
        this.yparser = new Parser(this.vars.length);
        for (int i = 0; i < this.vars.length; ++i) {
            this.yparser.defineVariable(i + 1, this.vars[i]);
        }
        this.yparser.define(this.yStr);
        this.yparser.parse();
        if (this.yparser.getErrorCode() != 0) {
            System.out.println(String.valueOf("Failed to parse vertical datasource: ").concat(String.valueOf(this.yparser)));
            System.out.println(String.valueOf(String.valueOf(String.valueOf("Parse error: ").concat(String.valueOf(this.yparser.getErrorString()))).concat(String.valueOf(" at function 1, position "))).concat(String.valueOf(this.yparser.getErrorPosition())));
            return false;
        }
        return true;
    }
    
    public void registerDatum() {
        final double[][] variables = this.ds.getVariables();
        if (variables == null) {
            return;
        }
        final int length = variables.length;
        if (length == 1) {
            double evaluate = 0.0;
            double evaluate2 = 0.0;
            if (this.xparser != null) {
                evaluate = this.xparser.evaluate(variables[0]);
            }
            if (this.yparser != null) {
                evaluate2 = this.yparser.evaluate(variables[0]);
            }
            this.dl.addDatum(this.series, evaluate, evaluate2);
            return;
        }
        final double[] array = new double[length];
        final double[] array2 = new double[length];
        for (int i = 0; i < length; ++i) {
            if (this.xparser != null) {
                array[i] = this.xparser.evaluate(variables[i]);
            }
            if (this.yparser != null) {
                array2[i] = this.yparser.evaluate(variables[i]);
            }
        }
        this.dl.addData(this.series, array, array2);
    }
    
    public void clearData() {
        this.dl.clearSeries(this.series);
    }
    
    public void deleteData() {
        this.dl.deleteSeries(this.series);
    }
    
    public final SDataSource getDataSource() {
        return this.ds;
    }
    
    public final SDataListener getDataListener() {
        return this.dl;
    }
}
