// 
// Decompiled by Procyon v0.5.30
// 

package KJEcalculation;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class Calculation
{
    public String MHI;
    public String sCurrency;
    public String sScaleLabel3;
    public String sScaleLabel2;
    public String sScaleLabel1;
    public String MSG_YEARS_LBL;
    public String MSG_YEAR_LBL;
    public String MSG_MONTHS_LBL;
    public String MSG_MONTH_LBL;
    public String MSG_AND_LBL;
    public String[] sReportCols;
    public boolean PAYMENTS_AT_START;
    public String _sGraphUnits;
    public String _sMessage;
    protected StringBuffer sRepeating;
    protected int nRepeatingCount;
    public boolean bWithSchedule;
    public String _sTableHeader;
    public String _sTopRow;
    public String _sEvenRow;
    public String _sOddRow;
    public String _sTableFooter;
    public String _sRowFooter;
    public String _sCell;
    public String _sCellFooter;
    public String _sCellFormat;
    
    public Calculation() {
        this.MHI = "";
        this.sCurrency = "Dollars";
        this.sScaleLabel3 = "Billions of ";
        this.sScaleLabel2 = "Millions of ";
        this.sScaleLabel1 = "Thousands of ";
        this.MSG_YEARS_LBL = "years";
        this.MSG_YEAR_LBL = "year";
        this.MSG_MONTHS_LBL = "months";
        this.MSG_MONTH_LBL = "month";
        this.MSG_AND_LBL = "and";
        this.PAYMENTS_AT_START = false;
        this._sGraphUnits = "";
        this._sMessage = null;
        this.sRepeating = new StringBuffer();
        this.bWithSchedule = false;
        this._sTableHeader = "<table border=0 cellpadding=0 cellspacing=0 width=100%>";
        this._sTopRow = "<TR bgcolor=#CCCCCC>";
        this._sEvenRow = "<TR>";
        this._sOddRow = "<TR bgcolor=#CCCCCC>";
        this._sTableFooter = "</table>";
        this._sRowFooter = "</TR>";
        this._sCell = "";
        this._sCellFooter = "";
        this._sCellFormat = "</TD><TD>";
    }
    
    public static double APR(final double n, final double n2, final double n3, final double n4, final double n5) {
        return RATE(n, PMT(n3, n, n4 + n5), n4);
    }
    
    public static double APY_MONTH(final double n) {
        return FV_AMT(n, 12.0, 1.0) - 1.0;
    }
    
    public static double FV(final double n, final double n2, final double n3) {
        if (n == 0.0) {
            return n2 * n3;
        }
        return n3 / n * (Math.pow(1.0 + n, n2) - 1.0);
    }
    
    public static double FV(final double n, final int n2, final double n3) {
        if (n == 0.0) {
            return n2 * n3;
        }
        return n3 / n * (Math.pow(1.0 + n, n2) - 1.0);
    }
    
    public static double FV_AMT(final double n, final double n2, final double n3) {
        if (n == 0.0) {
            return n3;
        }
        return n3 / Math.pow(1.0 + n, -1.0 * n2);
    }
    
    public static double FV_BEGIN(final double n, final double n2, final double n3) {
        if (n == 0.0) {
            return n2 * n3;
        }
        return n3 / n * (Math.pow(1.0 + n, n2 + 1.0) - 1.0) - n3;
    }
    
    public String JavaScriptReport(final String s) throws CalculationException {
        this.bWithSchedule = true;
        this.recalculate();
        this.bWithSchedule = false;
        final String replace = replace("QQ_HEADER_QQ", this.MHI, replace("CURRENCY_LABEL", this.sCurrency, this.formatReport(replace("&QCPQ;", "&copy;", replace("&NBSP;", "&nbsp;", replace("&QQ", "&#", replace("<START>", "<HTML><HEAD>", this._sMessage)))))));
        final int index = replace.indexOf("**REPEATING GROUP**");
        if (index == -1) {
            return replace;
        }
        return String.valueOf(replace.substring(0, index)) + this.getRepeating() + replace.substring(index + 19);
    }
    
    public static double NPV_AMT(final double n, final double n2, final double n3) {
        return n3 / Math.pow(1.0 + n, n2);
    }
    
    public static double PERIODS(final double n, final double n2, final double n3) {
        double n4 = 3120.0;
        double n5 = 1560.0;
        for (int i = 1; i < 50; ++i) {
            final double pmt = PMT(n, n4, n3);
            if (pmt == n2) {
                return n4;
            }
            if (pmt < n2) {
                n4 -= n5;
            }
            else {
                n4 += n5;
            }
            n5 /= 2.0;
        }
        return n4;
    }
    
    public static double PERIODS_FV(final double n, final double n2, final double n3) {
        double n4 = 720.0;
        double n5 = 360.0;
        for (int i = 1; i < 50; ++i) {
            final double pmt = PMT(n, n4, NPV_AMT(n, n4, n3));
            if (pmt == n2) {
                return n4;
            }
            if (pmt < n2) {
                n4 -= n5;
            }
            else {
                n4 += n5;
            }
            n5 /= 2.0;
        }
        return n4;
    }
    
    public static double PMT(final double n, final double n2, final double n3) {
        if (n2 <= 0.0) {
            return n3;
        }
        if (n2 <= 1.0) {
            return n3 * (1.0 + n);
        }
        if (n == 0.0) {
            return n3 / n2;
        }
        return n3 * n / (1.0 - Math.pow(1.0 + n, n2 * -1.0));
    }
    
    public static double PMT_BEGIN(final double n, final double n2, final double n3) {
        if (n2 <= 1.0) {
            return n3;
        }
        double pmt = PMT(n, n2, n3);
        double n4 = pmt / 2.0;
        for (int i = 1; i < 50; ++i) {
            final double pmt2 = PMT(n, n2 - 1.0, n3 - pmt);
            if (pmt2 == pmt) {
                return pmt;
            }
            if (pmt2 < pmt) {
                pmt -= n4;
            }
            else {
                pmt += n4;
            }
            n4 /= 2.0;
        }
        return pmt;
    }
    
    public static double PV(final double n, final double n2, final double n3) {
        return NPV_AMT(n, n2, FV(n, n2, n3));
    }
    
    public static double RATE(final double n, final double n2, final double n3) {
        double n4 = 0.0;
        double n5 = 1.0;
        for (int i = 1; i < 50; ++i) {
            final double pmt = PMT(n4, n, n3);
            if (pmt == n2) {
                return n4;
            }
            if (pmt < n2) {
                n4 += n5;
            }
            else {
                n4 -= n5;
            }
            n5 /= 2.0;
        }
        return n4;
    }
    
    public static double ROR_MONTH(final double n) {
        return Math.pow(1.0 + n, 0.08333333333333333) - 1.0;
    }
    
    public static double ROR_PERIOD(final double n, final int n2) {
        return Math.pow(1.0 + n, 1.0 / n2) - 1.0;
    }
    
    public void addRepeat(final String s) {
        if (this.nRepeatingCount == 0) {
            this.sRepeating.append(this._sTableHeader);
            this.sRepeating.append(this._sTopRow);
        }
        else if (this.nRepeatingCount % 2 == 0) {
            this.sRepeating.append(this._sOddRow);
        }
        else {
            this.sRepeating.append(this._sEvenRow);
        }
        this.sRepeating.append(s);
        this.sRepeating.append(this._sRowFooter);
        ++this.nRepeatingCount;
    }
    
    protected abstract void calculate() throws CalculationException;
    
    protected abstract void clearCalculated();
    
    public abstract void clearInputed();
    
    protected abstract String formatReport(final String p0);
    
    public int getGraphUnits(final double n) {
        return this.getGraphUnits(n, 0.0);
    }
    
    public int getGraphUnits(final double n, final double n2) {
        int n3;
        if (n > 1.0E11 || n2 < -1.0E11) {
            n3 = 1000000000;
            this._sGraphUnits = this.sScaleLabel3;
        }
        else if (n > 1.0E8 || n2 < -1.0E8) {
            n3 = 1000000;
            this._sGraphUnits = this.sScaleLabel2;
        }
        else if (n > 100000.0 || n2 < -100000.0) {
            n3 = 1000;
            this._sGraphUnits = this.sScaleLabel1;
        }
        else {
            this._sGraphUnits = "";
            n3 = 1;
        }
        return n3;
    }
    
    public static String getMessage(final URL url) {
        final StringBuffer sb = new StringBuffer();
        try {
            final InputStream openStream = url.openStream();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(openStream)).readLine()) != null) {
                sb.append(String.valueOf(line) + "\n");
            }
            openStream.close();
        }
        catch (IOException ex) {
            return "<HTML><BODY>Financial Calculator Report not found<P>**REPEATING GROUP**</BODY></HTML>";
        }
        return sb.toString();
    }
    
    public String getRepeating() {
        final String string = this.sRepeating.append(this._sTableFooter).toString();
        this.sRepeating = new StringBuffer();
        return string;
    }
    
    public String getTermLabel(final int n, final boolean b) {
        final int n2 = n / 12;
        final int n3 = n % 12;
        return String.valueOf((b || n2 > 0) ? new StringBuffer(String.valueOf(n2)).append(" ").append((n2 == 1) ? this.MSG_YEAR_LBL : this.MSG_YEARS_LBL).toString() : "") + ((b || (n2 > 0 && n3 > 0)) ? (" " + this.MSG_AND_LBL + " ") : "") + ((b || n3 > 0) ? (String.valueOf(n3) + " " + ((n3 == 1) ? this.MSG_MONTH_LBL : this.MSG_MONTHS_LBL)) : "");
    }
    
    public void recalculate() throws CalculationException {
        this._sCellFormat = String.valueOf(this._sCellFooter) + "</TD><TD>" + this._sCell;
        this.nRepeatingCount = 0;
        this.clearCalculated();
        this.calculate();
    }
    
    public static String replace(final String s, String s2, final String s3) {
        int n = 0;
        if (s2.equals("-922,337,203,685,477,632.8%")) {
            s2 = "0%";
        }
        if (s2.equals("-92,233,720,368,547,760.08%")) {
            s2 = "0%";
        }
        final int length = s.length();
        int i = s3.indexOf(s);
        final StringBuffer sb = new StringBuffer();
        while (i != -1) {
            sb.append(s3.substring(n, i));
            sb.append(s2);
            n = i + length;
            i = s3.indexOf(s, n);
        }
        sb.append(s3.substring(n));
        return sb.toString();
    }
    
    public String sReportCol(final String s, final int n) {
        String s2;
        if (this.sReportCols == null) {
            s2 = s;
        }
        else if (n < 1 || n > this.sReportCols.length) {
            s2 = s;
        }
        else if (this.sReportCols[n - 1] == null) {
            s2 = s;
        }
        else {
            s2 = this.sReportCols[n - 1];
        }
        return s2;
    }
}
