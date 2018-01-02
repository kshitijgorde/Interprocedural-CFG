import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.TextArea;
import java.math.BigInteger;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ecm extends Applet implements Runnable
{
    boolean onlyFactoring;
    static final int actionTextNbr = 1;
    static final int actionTextCurve = 2;
    static final int actionBtnCurve = 3;
    static final int actionTextFactor = 4;
    static final int actionBtnFactor = 5;
    static final int TYP_AURIF = 100000000;
    static final int TYP_TABLE = 150000000;
    static final int TYP_SIQS = 200000000;
    static final int TYP_LEHMAN = 250000000;
    static final int TYP_EC = 300000000;
    int digitsInGroup;
    final BigInteger[] SS;
    final BigInteger[] PD;
    final int[] Exp;
    final int[] Typ;
    final BigInteger[] PD1;
    final int[] Exp1;
    final int[] Typ1;
    String inputStr;
    boolean foundByLehman;
    boolean performLehman;
    static final BigInteger BigInt0;
    static final BigInteger BigInt1;
    static final BigInteger BigInt2;
    static final BigInteger BigInt3;
    static final int PWmax = 32;
    static final int Qmax = 30241;
    static final int LEVELmax = 11;
    static final int NLen = 1200;
    final int[] aiIndx;
    final int[] aiF;
    static final int[] aiP;
    static final int[] aiQ;
    static final int[] aiG;
    final int[] aiInv;
    static final int[] aiNP;
    static final int[] aiNQ;
    static final int[] aiT;
    final long[] biTmp;
    final long[] biExp;
    final long[] biN;
    final long[] biR;
    final long[] biS;
    final long[] biT;
    final long[] biU;
    final long[] biV;
    final long[] biW;
    final long[][] aiJS;
    final long[][] aiJW;
    final long[][] aiJX;
    final long[][] aiJ0;
    final long[][] aiJ1;
    final long[][] aiJ2;
    final long[][] aiJ00;
    final long[][] aiJ01;
    int NumberLength;
    int NbrFactors;
    int NbrFactors1;
    int EC;
    final long[] CalcAuxGcdU;
    final long[] CalcAuxGcdV;
    final long[] CalcAuxGcdT;
    final long[] CalcBigNbr;
    long[] TestNbr;
    final long[] TestNbr2;
    final long[] GcdAccumulated;
    final long[] Gamma;
    final long[] Delta;
    final long[] AurifQ;
    final BigInteger[] Factores;
    long[] fieldAA;
    long[] fieldTX;
    long[] fieldTZ;
    long[] fieldUX;
    long[] fieldUZ;
    long[] fieldAux1;
    long[] fieldAux2;
    long[] fieldAux3;
    long[] fieldAux4;
    int NroFact;
    int DegreeAurif;
    static final long DosALa32 = 4294967296L;
    static final long DosALa31 = 2147483648L;
    static final long DosALa31_1 = 2147483647L;
    static final long DosALa63 = Long.MIN_VALUE;
    static final double dDosALa31 = 2.147483648E9;
    static final double dDosALa62 = 4.6116860184273879E18;
    static final long Mi = 1000000000L;
    double dN;
    final long[] BigNbr1;
    final int[] SmallPrime;
    final long[] MontgomeryMultR1;
    final long[] MontgomeryMultR2;
    final long[] MontgomeryMultAfterInv;
    long MontgomeryMultN;
    final double[] ProbArray;
    TextArea upperTextArea;
    TextArea lowerTextArea;
    Label labelStatus;
    Label labelBottom;
    Label labelTop;
    TextField textNumber;
    TextField textCurve;
    TextField textFactor;
    Button btnCurve;
    Button btnFactor;
    BigInteger NumberToFactor;
    String StringToLabel;
    StringBuffer outputStr;
    boolean batchFinished;
    boolean batchPrime;
    volatile Thread calcThread;
    String textAreaContents;
    long OldTimeElapsed;
    long Old;
    long yieldFreq;
    boolean TerminateThread;
    int NextEC;
    BigInteger InputFactor;
    int StepECM;
    int indexM;
    int maxIndexM;
    int NbrPrimes;
    int indexPrimes;
    BigInteger Quad1;
    BigInteger Quad2;
    BigInteger Quad3;
    BigInteger Quad4;
    boolean Computing3Squares;
    long polynomialsSieved;
    long trialDivisions;
    long smoothsFound;
    long totalPartials;
    long partialsFound;
    long primeModMult;
    long lModularMult;
    long ValuesSieved;
    static final int[] limits;
    static final String[] expressionText;
    static final int ADD = 6;
    static final int DUP = 5;
    
    BigInteger Fibonacci(final int n) {
        if (this.onlyFactoring) {
            BigInteger bigInt1 = ecm.BigInt1;
            BigInteger bigInt2 = ecm.BigInt0;
            for (int i = 1; i <= n; ++i) {
                final BigInteger add = bigInt1.add(bigInt2);
                bigInt1 = bigInt2;
                bigInt2 = add;
            }
            return bigInt2;
        }
        return null;
    }
    
    public String getStringsFromBothPanes() {
        if (this.onlyFactoring) {
            final String text = this.lowerTextArea.getText();
            String s = "<HTML><TITLE>Elliptic Curve Method factorization</TITLE><BODY><H2>Elliptic Curve Method factorization</H2><P><I>Written by Dario Alejandro Alpern (alpertron@hotmail.com)</I><P><PRE>" + this.upperTextArea.getText() + "<HR><P>" + text;
            if (text.indexOf("complete") < 0) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.OldTimeElapsed >= 0L) {
                    this.OldTimeElapsed += currentTimeMillis - this.Old;
                    this.Old = currentTimeMillis;
                    s = s + "<HR><P>Elapsed time: " + this.GetDHMS(this.OldTimeElapsed / 1000L) + ((this.lModularMult >= 0L) ? ("\n" + this.lModularMult + " modular multiplications have been done") : "");
                }
            }
            return s + "</PRE><BODY></HTML>";
        }
        return null;
    }
    
    int ProbabilisticPrimeTest(final BigInteger bigInteger) {
        this.lowerTextArea.setText("Starting Rabin probabilistic prime check routine.");
        this.BigNbrToBigInt(bigInteger);
        final int lowestSetBit = bigInteger.subtract(ecm.BigInt1).getLowestSetBit();
        this.GetMontgomeryParms();
        long n = 1L;
        for (int n2 = bigInteger.bitLength() / 2, i = 0; i < n2; ++i) {
            if (n < 3L) {
                ++n;
            }
            else {
                Label_0061: {
                    break Label_0061;
                    long n3 = 0L;
                    do {
                        if (n % n3 != 0L) {
                            n3 += 2L;
                        }
                        else {
                            n += 2L;
                            n3 = 3L;
                        }
                    } while (n3 * n3 <= n);
                }
            }
            this.lowerTextArea.setText("Rabin probabilistic prime check routine\n\nBase used: " + n + " (" + i * 100 / n2 + "%)");
            System.arraycopy(this.MontgomeryMultR1, 0, this.biN, 0, this.NumberLength);
            int n4 = this.NumberLength - 1;
            long n5 = 1073741824L;
            for (int j = this.NumberLength * 31; j > lowestSetBit; --j) {
                this.MontgomeryMult(this.biN, this.biN, this.biT);
                if ((this.TestNbr[n4] & n5) != 0x0L) {
                    this.MultBigNbrByLongModN(this.biT, n, this.biT);
                }
                System.arraycopy(this.biT, 0, this.biN, 0, this.NumberLength);
                n5 /= 2L;
                if (n5 == 0L) {
                    --n4;
                    n5 = 1073741824L;
                }
            }
            int n6;
            for (n6 = 0; n6 < this.NumberLength && this.biN[n6] == this.MontgomeryMultR1[n6]; ++n6) {}
            if (n6 != this.NumberLength) {
                int k;
                for (k = 0; k < lowestSetBit; ++k) {
                    this.AddBigNbr(this.biN, this.MontgomeryMultR1, this.biT);
                    int n7;
                    for (n7 = 0; n7 < this.NumberLength && this.biT[n7] == this.TestNbr[n7]; ++n7) {}
                    if (n7 == this.NumberLength) {
                        break;
                    }
                    this.MontgomeryMult(this.biN, this.biN, this.biT);
                    System.arraycopy(this.biT, 0, this.biN, 0, this.NumberLength);
                }
                if (k == lowestSetBit) {
                    int n8;
                    for (n8 = 0; n8 < this.NumberLength && this.biN[n8] == this.MontgomeryMultR1[n8]; ++n8) {}
                    if (n8 < this.NumberLength) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public int setState(final String s) {
        if (this.onlyFactoring) {
            int i = 0;
            final int length = s.length();
            int index = s.indexOf(44, i);
            if (index == -1) {
                index = length;
            }
            this.NumberToFactor = ecm.BigInt1;
            this.NbrFactors = 0;
            int n = 0;
            do {
                this.Exp[n] = (this.Typ[n] = 0);
            } while (++n < 400);
            while (i < index) {
                int index2 = s.indexOf(120, i);
                if (index2 == -1) {
                    index2 = index;
                }
                int index3 = s.indexOf(94, i);
                int index4 = s.indexOf(40, i);
                if (index4 > index2 || index4 == -1) {
                    index4 = index2;
                }
                if (index3 > index4 || index3 == -1) {
                    index3 = index4;
                }
                final int n2 = index2 + 1;
                switch (s.charAt(index2 - 1)) {
                    case ')': {
                        this.Typ[this.NbrFactors] = Integer.parseInt(s.substring(index4 + 1, index2 - 1));
                        break;
                    }
                    default: {
                        this.Typ[this.NbrFactors] = 0;
                        break;
                    }
                }
                this.PD[this.NbrFactors] = new BigInteger(s.substring(i, index3));
                if (index3 == index4) {
                    this.Exp[this.NbrFactors] = 1;
                }
                else {
                    this.Exp[this.NbrFactors] = Integer.parseInt(s.substring(index3 + 1, index4));
                }
                this.NumberToFactor = this.NumberToFactor.multiply(this.PD[this.NbrFactors].pow(this.Exp[this.NbrFactors]));
                ++this.NbrFactors;
                i = n2;
            }
            this.lModularMult = -1L;
            if (index < length) {
                final int index5 = s.indexOf(43, index);
                if (index5 > 0) {
                    this.OldTimeElapsed = Long.parseLong(s.substring(index + 1, index5));
                    final int index6 = s.indexOf(43, index5 + 1);
                    if (index6 > 0) {
                        this.lModularMult = Long.parseLong(s.substring(index5 + 1, index6));
                        this.digitsInGroup = Integer.parseInt(s.substring(index6 + 1));
                    }
                    else {
                        this.lModularMult = Long.parseLong(s.substring(index5 + 1));
                    }
                }
                else {
                    this.OldTimeElapsed = Long.parseLong(s.substring(index + 1));
                }
            }
            else {
                this.OldTimeElapsed = -1L;
            }
            if (this.NumberToFactor.compareTo(ecm.BigInt1) > 0) {
                this.textNumber.setText(this.NumberToFactor.toString());
                (this.calcThread = new Thread(this)).start();
            }
            return this.digitsInGroup;
        }
        return 0;
    }
    
    public String getState() {
        if (this.onlyFactoring) {
            String s = "";
            if (this.calcThread != null) {
                this.TerminateThread = true;
                try {
                    this.calcThread.join();
                }
                catch (InterruptedException ex) {}
            }
            for (int i = 0; i < (this.Computing3Squares ? this.NbrFactors1 : this.NbrFactors); ++i) {
                if (i != 0) {
                    s += "x";
                }
                s += this.PD[i].toString();
                if (this.Exp[i] != 1) {
                    s = s + "^" + this.Exp[i];
                }
                if (this.Typ[i] != 0) {
                    s = s + "(" + this.Typ[i] + ")";
                }
            }
            if (this.OldTimeElapsed >= 0L) {
                s = s + "," + this.OldTimeElapsed;
            }
            if (this.lModularMult >= 0L) {
                s = s + "+" + this.lModularMult;
            }
            return s + "+" + this.digitsInGroup;
        }
        return null;
    }
    
    void ShowUpperPane() {
        this.textAreaContents = "";
        this.StringToLabel = "";
        this.insertBigNbr(this.NumberToFactor);
        if (this.NbrFactors == 1 && this.Exp[0] == 1) {
            if (this.Typ[0] > 0) {
                this.addStringToLabel("is composite");
            }
            else if (this.Typ[0] < 0) {
                this.addStringToLabel("is unknown");
            }
            else {
                this.addStringToLabel("is prime");
            }
        }
        else {
            this.addStringToLabel("=");
            for (int i = 0; i < this.NbrFactors; ++i) {
                if (i != 0) {
                    this.addStringToLabel("x");
                }
                this.insertBigNbr(this.PD[i]);
                if (this.Exp[i] != 1) {
                    this.addStringToLabel("^");
                    this.addStringToLabel("" + this.Exp[i]);
                }
                if (this.Typ[i] > 0) {
                    if ((this.digitsInGroup & 0x800) == 0x800) {
                        if (this.Typ[i] == 100000000) {
                            this.addStringToLabel("(Aurifeuille)");
                        }
                        else if (this.Typ[i] / 50000000 * 50000000 == 100000000) {
                            this.addStringToLabel("(Aurif - Composite)");
                        }
                        else if (this.Typ[i] == 150000000) {
                            this.addStringToLabel("(Table)");
                        }
                        else if (this.Typ[i] / 50000000 * 50000000 == 150000000) {
                            this.addStringToLabel("(Table - Composite)");
                        }
                        else if (this.Typ[i] == 200000000) {
                            this.addStringToLabel("(SIQS)");
                        }
                        else if (this.Typ[i] / 50000000 * 50000000 == 200000000) {
                            this.addStringToLabel("(SIQS - Composite)");
                        }
                        else if (this.Typ[i] == 250000000) {
                            this.addStringToLabel("(Lehman)");
                        }
                        else if (this.Typ[i] / 50000000 * 50000000 == 250000000) {
                            this.addStringToLabel("(Lehman - Composite)");
                        }
                        else if (this.Typ[i] > 300000000) {
                            this.addStringToLabel("(Curve " + (this.Typ[i] - 300000000) + ")");
                        }
                        else {
                            this.addStringToLabel("(Composite)");
                        }
                    }
                    else if (this.Typ[i] < 300000000 && this.Typ[i] != 250000000 && this.Typ[i] != 200000000 && this.Typ[i] != 100000000 && this.Typ[i] != 150000000) {
                        this.addStringToLabel("(Composite)");
                    }
                }
                else if (this.Typ[i] < 0) {
                    this.addStringToLabel("(Unknown)");
                }
            }
        }
        this.upperTextArea.setText(this.textAreaContents + this.StringToLabel);
    }
    
    void Cunningham(final BigInteger bigInteger, final int n, final BigInteger bigInteger2, final BigInteger bigInteger3) {
        if (this.onlyFactoring) {
            final int intValue = bigInteger2.intValue();
            this.NroFact = 1;
            this.Factores[0] = bigInteger3;
            int n2 = n;
            final BigInteger add = bigInteger.pow(n2).add(bigInteger2);
            this.InsertFactor(add);
            if ((this.digitsInGroup & 0x1000) == 0x0 && add.bitLength() > 60) {
                try {
                    this.lowerTextArea.setText("Requesting known primitive factors from Web server.");
                    final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new URL(this.getDocumentBase(), "factors.pl?base=" + bigInteger.intValue() + "&expon=" + n + "&type=" + ((intValue == 1) ? "p" : "m")).openStream()));
                    final String line = dataInputStream.readLine();
                    dataInputStream.close();
                    if (line.length() > 0) {
                        int i = 0;
                        do {
                            final int index = line.indexOf("*", i);
                            if (index > 0) {
                                this.InsertFactor(new BigInteger(line.substring(i, index)));
                            }
                            else {
                                this.InsertFactor(new BigInteger(line.substring(i)));
                            }
                            i = index + 1;
                        } while (i > 0);
                    }
                }
                catch (Exception ex) {}
            }
            while (n2 % 2 == 0 && intValue == -1) {
                n2 /= 2;
                this.InsertFactor(bigInteger.pow(n2).add(ecm.BigInt1));
                this.InsertAurifFactors(bigInteger, n2, 1);
            }
            for (int n3 = 1; n3 * n3 <= n; ++n3) {
                if (n % n3 == 0) {
                    if (n3 % 2 != 0) {
                        final BigInteger gcd = bigInteger.pow(n / n3).add(bigInteger2).gcd(bigInteger3);
                        this.InsertFactor(gcd);
                        this.InsertFactor(bigInteger3.divide(gcd));
                        this.InsertAurifFactors(bigInteger, n / n3, intValue);
                    }
                    if (n / n3 % 2 != 0) {
                        final BigInteger gcd2 = bigInteger.pow(n3).add(bigInteger2).gcd(bigInteger3);
                        this.InsertFactor(gcd2);
                        this.InsertFactor(bigInteger3.divide(gcd2));
                        this.InsertAurifFactors(bigInteger, n3, intValue);
                    }
                }
            }
            this.SortFactors();
        }
    }
    
    public static void main(final String[] array) {
        final ecm ecm = new ecm();
        ecm.init();
        final int n = 344;
        final BigInteger divide = new BigInteger("10").pow(n).divide(BigInteger.valueOf(9L));
        ecm.BigNbrToBigInt(divide);
        System.out.println("Initial number = 10^" + n + " / 9");
        System.out.println("NumberLength = " + ecm.NumberLength);
        System.out.println("TestNbr[0] = " + ecm.TestNbr[0]);
        System.out.println("TestNbr[1] = " + ecm.TestNbr[1]);
        System.out.println(ecm.BigIntToBigNbr(ecm.TestNbr));
        ecm.TerminateThread = false;
        ecm.MultBigNbrByLong(ecm.TestNbr, -7L, ecm.TestNbr);
        ecm.MultBigNbrByLong(ecm.TestNbr, -7L, ecm.TestNbr);
        ecm.DivBigNbrByLong(ecm.TestNbr, -7L, ecm.TestNbr);
        ecm.DivBigNbrByLong(ecm.TestNbr, -7L, ecm.TestNbr);
        System.out.println(ecm.BigIntToBigNbr(ecm.TestNbr).toString());
        ecm.BigNbrToBigInt(divide);
        ecm.LongToBigNbr(-7L, ecm.MontgomeryMultR2);
        ecm.MultBigNbr(ecm.MontgomeryMultR2, ecm.TestNbr, ecm.MontgomeryMultR1);
        ecm.MultBigNbr(ecm.MontgomeryMultR1, ecm.MontgomeryMultR2, ecm.TestNbr);
        System.out.println(ecm.BigIntToBigNbr(ecm.TestNbr).toString());
    }
    
    void insertBigNbr(final BigInteger bigInteger) {
        final int n = this.digitsInGroup & 0x3FF;
        final String string = bigInteger.toString();
        int i = (string.length() + n - 1) % n + 1;
        this.addStringToLabel(string.substring(0, i));
        while (i < string.length()) {
            this.addStringToLabel(string.substring(i, i + n));
            i += n;
        }
    }
    
    void SubtractBigNbr(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        long n = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n = (n >> 31) + array[i] - array2[i];
            array3[i] = (n & 0x7FFFFFFFL);
        }
    }
    
    void NormalizeJS(final int n, final int n2, final int n3, final int n4) {
        for (int i = n2; i < n; ++i) {
            if (!this.BigNbrIsZero(this.aiJS[i])) {
                for (int j = 0; j < this.NumberLength; ++j) {
                    this.biT[j] = this.aiJS[i][j];
                }
                for (int k = 1; k < n4; ++k) {
                    this.SubtractBigNbrModN(this.aiJS[i - k * n3], this.biT, this.aiJS[i - k * n3]);
                }
                for (int l = 0; l < this.NumberLength; ++l) {
                    this.aiJS[i][l] = 0L;
                }
            }
        }
    }
    
    void MultBigNbrByLong(final long[] array, final long n, final long[] array2) {
        final int numberLength = this.NumberLength;
        final long n2 = 2147483647L;
        long n3 = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n3 = (n3 >> 31) + n * array[i];
            array2[i] = (n3 & n2);
        }
    }
    
    int Totient(final int n) {
        if (this.onlyFactoring) {
            int n2 = n;
            int n3 = n;
            if (n2 % 2 == 0) {
                n3 /= 2;
                do {
                    n2 /= 2;
                } while (n2 % 2 == 0);
            }
            if (n2 % 3 == 0) {
                n3 = n3 * 2 / 3;
                do {
                    n2 /= 3;
                } while (n2 % 3 == 0);
            }
            for (int n4 = 5; n4 * n4 <= n2; n4 += 2) {
                if (n4 % 3 != 0 && n2 % n4 == 0) {
                    n3 = n3 * (n4 - 1) / n4;
                    do {
                        n2 /= n4;
                    } while (n2 % n4 == 0);
                }
            }
            if (n2 > 1) {
                n3 = n3 * (n2 - 1) / n2;
            }
            return n3;
        }
        return 0;
    }
    
    long modInv(final long n, final long n2) {
        long n3 = 1L;
        long n4 = n;
        long n5 = 0L;
        long n9;
        for (long n6 = n2; n6 != 0L; n6 = n9) {
            final long n7 = n4 / n6;
            final long n8 = n3 - n5 * n7;
            n9 = n4 - n6 * n7;
            n3 = n5;
            n4 = n6;
            n5 = n8;
        }
        return (n3 + n2) % n2;
    }
    
    void coladd(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n, final int n2) {
        if (n == n2) {
            return;
        }
        final int n3 = 1 << 31 - (n & 0x1F);
        final int n4 = 1 << 31 - (n2 & 0x1F);
        final int[] array5 = (n >= 32) ? array3 : array4;
        final int[] array6 = (n2 >= 32) ? array3 : array4;
        for (int i = array2.length - 1; i >= 0; --i) {
            if ((array5[i] & n3) != 0x0) {
                final int[] array7 = array6;
                final int n5 = i;
                array7[n5] ^= n4;
            }
        }
        final int[] array8 = (n >= 32) ? array : array2;
        final int[] array9 = (n2 >= 32) ? array : array2;
        for (int j = array2.length - 1; j >= 0; --j) {
            if ((array8[j] & n3) != 0x0) {
                final int[] array10 = array9;
                final int n6 = j;
                array10[n6] ^= n4;
            }
        }
    }
    
    static int Cos(final int n) {
        switch (n % 8) {
            case 0: {
                return 1;
            }
            case 4: {
                return -1;
            }
            default: {
                return 0;
            }
        }
    }
    
    void MontgomeryMult(final long[] array, final long[] array2, final long[] array3) {
        final long n = 2147483647L;
        final int n2 = (int)this.MontgomeryMultN;
        final long[] testNbr = this.TestNbr;
        final int numberLength = this.NumberLength;
        if (this.TerminateThread) {
            throw new ArithmeticException();
        }
        if (this.lModularMult >= 0L) {
            ++this.lModularMult;
            if (this.lModularMult % this.yieldFreq == 0L) {
                Thread.yield();
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.OldTimeElapsed >= 0L && this.OldTimeElapsed / 1000L != (this.OldTimeElapsed + currentTimeMillis - this.Old) / 1000L) {
                    this.OldTimeElapsed += currentTimeMillis - this.Old;
                    this.Old = currentTimeMillis;
                    String s = null;
                    switch (this.StepECM) {
                        case 1: {
                            s = "Step 1: " + this.indexPrimes * 100 / this.NbrPrimes + "%";
                            break;
                        }
                        case 2: {
                            s = "Step 2: " + ((this.maxIndexM == 0) ? 0 : (this.indexM * 100 / this.maxIndexM)) + "%";
                            break;
                        }
                        default: {
                            s = "";
                            break;
                        }
                    }
                    this.labelStatus.setText("Time elapsed: " + this.GetDHMS(this.OldTimeElapsed / 1000L) + "    mod mult: " + ((this.lModularMult >= 0L) ? ("" + this.lModularMult) : "I don't know") + "   " + s);
                }
            }
        }
        final long n3 = testNbr[0];
        final long n4 = testNbr[1];
        final long n5 = array2[0];
        final long n6 = array2[1];
        switch (numberLength) {
            case 2: {
                long n8;
                long n7 = n8 = 0L;
                int n9 = 0;
                do {
                    final long n11;
                    final long n10 = (n11 = array[n9]) * n5 + n8;
                    final long n12 = (int)n10 * n2 & n;
                    final long n13;
                    n8 = ((n13 = (n12 * n3 + n10 >>> 31) + n12 * n4 + n11 * n6 + n7) & 0x7FFFFFFFL);
                    n7 = n13 >>> 31;
                } while (++n9 < 2);
                if (n7 > n4 || (n7 == n4 && n8 >= n3)) {
                    final long n14;
                    n8 = ((n14 = n8 - n3) & n);
                    n7 = ((n14 >> 31) + n7 - n4 & n);
                }
                array3[0] = n8;
                array3[1] = n7;
            }
            case 3: {
                long n17;
                long n16;
                long n15 = n16 = (n17 = 0L);
                final long n18 = testNbr[2];
                final long n19 = array2[2];
                int n20 = 0;
                do {
                    final long n22;
                    final long n21 = (n22 = array[n20]) * n5 + n16;
                    final long n23 = (int)n21 * n2 & n;
                    final long n24;
                    n16 = ((n24 = (n23 * n3 + n21 >>> 31) + n23 * n4 + n22 * n6 + n15) & 0x7FFFFFFFL);
                    final long n25;
                    n15 = ((n25 = (n24 >>> 31) + n23 * n18 + n22 * n19 + n17) & 0x7FFFFFFFL);
                    n17 = n25 >>> 31;
                } while (++n20 < 3);
                if (n17 > n18 || (n17 == n18 && (n15 > n4 || (n15 == n4 && n16 >= n3)))) {
                    final long n26;
                    n16 = ((n26 = n16 - n3) & n);
                    final long n27;
                    n15 = ((n27 = (n26 >> 31) + n15 - n4) & n);
                    n17 = ((n27 >> 31) + n17 - n18 & n);
                }
                array3[0] = n16;
                array3[1] = n15;
                array3[2] = n17;
            }
            case 4: {
                long n31;
                long n30;
                long n29;
                long n28 = n29 = (n30 = (n31 = 0L));
                final long n32 = testNbr[2];
                final long n33 = testNbr[3];
                final long n34 = array2[2];
                final long n35 = array2[3];
                int n36 = 0;
                do {
                    final long n38;
                    final long n37 = (n38 = array[n36]) * n5 + n29;
                    final long n39 = (int)n37 * n2 & n;
                    final long n40;
                    n29 = ((n40 = (n39 * n3 + n37 >>> 31) + n39 * n4 + n38 * n6 + n28) & 0x7FFFFFFFL);
                    final long n41;
                    n28 = ((n41 = (n40 >>> 31) + n39 * n32 + n38 * n34 + n30) & 0x7FFFFFFFL);
                    final long n42;
                    n30 = ((n42 = (n41 >>> 31) + n39 * n33 + n38 * n35 + n31) & 0x7FFFFFFFL);
                    n31 = n42 >>> 31;
                } while (++n36 < 4);
                if (n31 > n33 || (n31 == n33 && (n30 > n32 || (n30 == n32 && (n28 > n4 || (n28 == n4 && n29 >= n3)))))) {
                    final long n43;
                    n29 = ((n43 = n29 - n3) & n);
                    final long n44;
                    n28 = ((n44 = (n43 >> 31) + n28 - n4) & n);
                    final long n45;
                    n30 = ((n45 = (n44 >> 31) + n30 - n32) & n);
                    n31 = ((n45 >> 31) + n31 - n33 & n);
                }
                array3[0] = n29;
                array3[1] = n28;
                array3[2] = n30;
                array3[3] = n31;
            }
            case 5: {
                long n50;
                long n49;
                long n48;
                long n47;
                long n46 = n47 = (n48 = (n49 = (n50 = 0L)));
                final long n51 = testNbr[2];
                final long n52 = testNbr[3];
                final long n53 = testNbr[4];
                final long n54 = array2[2];
                final long n55 = array2[3];
                final long n56 = array2[4];
                int n57 = 0;
                do {
                    final long n59;
                    final long n58 = (n59 = array[n57]) * n5 + n47;
                    final long n60 = (int)n58 * n2 & n;
                    final long n61;
                    n47 = ((n61 = (n60 * n3 + n58 >>> 31) + n60 * n4 + n59 * n6 + n46) & 0x7FFFFFFFL);
                    final long n62;
                    n46 = ((n62 = (n61 >>> 31) + n60 * n51 + n59 * n54 + n48) & 0x7FFFFFFFL);
                    final long n63;
                    n48 = ((n63 = (n62 >>> 31) + n60 * n52 + n59 * n55 + n49) & 0x7FFFFFFFL);
                    final long n64;
                    n49 = ((n64 = (n63 >>> 31) + n60 * n53 + n59 * n56 + n50) & 0x7FFFFFFFL);
                    n50 = n64 >>> 31;
                } while (++n57 < 5);
                if (n50 > n53 || (n50 == n53 && (n49 > n52 || (n49 == n52 && (n48 > n51 || (n48 == n51 && (n46 > n4 || (n46 == n4 && n47 >= n3)))))))) {
                    final long n65;
                    n47 = ((n65 = n47 - n3) & n);
                    final long n66;
                    n46 = ((n66 = (n65 >> 31) + n46 - n4) & n);
                    final long n67;
                    n48 = ((n67 = (n66 >> 31) + n48 - n51) & n);
                    final long n68;
                    n49 = ((n68 = (n67 >> 31) + n49 - n52) & n);
                    n50 = ((n68 >> 31) + n50 - n53 & n);
                }
                array3[0] = n47;
                array3[1] = n46;
                array3[2] = n48;
                array3[3] = n49;
                array3[4] = n50;
            }
            case 6: {
                long n74;
                long n73;
                long n72;
                long n71;
                long n70;
                long n69 = n70 = (n71 = (n72 = (n73 = (n74 = 0L))));
                final long n75 = testNbr[2];
                final long n76 = testNbr[3];
                final long n77 = testNbr[4];
                final long n78 = testNbr[5];
                final long n79 = array2[2];
                final long n80 = array2[3];
                final long n81 = array2[4];
                final long n82 = array2[5];
                int n83 = 0;
                do {
                    final long n85;
                    final long n84 = (n85 = array[n83]) * n5 + n70;
                    final long n86 = (int)n84 * n2 & n;
                    final long n87;
                    n70 = ((n87 = (n86 * n3 + n84 >>> 31) + n86 * n4 + n85 * n6 + n69) & 0x7FFFFFFFL);
                    final long n88;
                    n69 = ((n88 = (n87 >>> 31) + n86 * n75 + n85 * n79 + n71) & 0x7FFFFFFFL);
                    final long n89;
                    n71 = ((n89 = (n88 >>> 31) + n86 * n76 + n85 * n80 + n72) & 0x7FFFFFFFL);
                    final long n90;
                    n72 = ((n90 = (n89 >>> 31) + n86 * n77 + n85 * n81 + n73) & 0x7FFFFFFFL);
                    final long n91;
                    n73 = ((n91 = (n90 >>> 31) + n86 * n78 + n85 * n82 + n74) & 0x7FFFFFFFL);
                    n74 = n91 >>> 31;
                } while (++n83 < 6);
                if (n74 > n78 || (n74 == n78 && (n73 > n77 || (n73 == n77 && (n72 > n76 || (n72 == n76 && (n71 > n75 || (n71 == n75 && (n69 > n4 || (n69 == n4 && n70 >= n3)))))))))) {
                    final long n92;
                    n70 = ((n92 = n70 - n3) & n);
                    final long n93;
                    n69 = ((n93 = (n92 >> 31) + n69 - n4) & n);
                    final long n94;
                    n71 = ((n94 = (n93 >> 31) + n71 - n75) & n);
                    final long n95;
                    n72 = ((n95 = (n94 >> 31) + n72 - n76) & n);
                    final long n96;
                    n73 = ((n96 = (n95 >> 31) + n73 - n77) & n);
                    n74 = ((n96 >> 31) + n74 - n78 & n);
                }
                array3[0] = n70;
                array3[1] = n69;
                array3[2] = n71;
                array3[3] = n72;
                array3[4] = n73;
                array3[5] = n74;
            }
            case 7: {
                long n103;
                long n102;
                long n101;
                long n100;
                long n99;
                long n98;
                long n97 = n98 = (n99 = (n100 = (n101 = (n102 = (n103 = 0L)))));
                final long n104 = testNbr[2];
                final long n105 = testNbr[3];
                final long n106 = testNbr[4];
                final long n107 = testNbr[5];
                final long n108 = testNbr[6];
                final long n109 = array2[2];
                final long n110 = array2[3];
                final long n111 = array2[4];
                final long n112 = array2[5];
                final long n113 = array2[6];
                int n114 = 0;
                do {
                    final long n116;
                    final long n115 = (n116 = array[n114]) * n5 + n98;
                    final long n117 = (int)n115 * n2 & n;
                    final long n118;
                    n98 = ((n118 = (n117 * n3 + n115 >>> 31) + n117 * n4 + n116 * n6 + n97) & 0x7FFFFFFFL);
                    final long n119;
                    n97 = ((n119 = (n118 >>> 31) + n117 * n104 + n116 * n109 + n99) & 0x7FFFFFFFL);
                    final long n120;
                    n99 = ((n120 = (n119 >>> 31) + n117 * n105 + n116 * n110 + n100) & 0x7FFFFFFFL);
                    final long n121;
                    n100 = ((n121 = (n120 >>> 31) + n117 * n106 + n116 * n111 + n101) & 0x7FFFFFFFL);
                    final long n122;
                    n101 = ((n122 = (n121 >>> 31) + n117 * n107 + n116 * n112 + n102) & 0x7FFFFFFFL);
                    final long n123;
                    n102 = ((n123 = (n122 >>> 31) + n117 * n108 + n116 * n113 + n103) & 0x7FFFFFFFL);
                    n103 = n123 >>> 31;
                } while (++n114 < 7);
                if (n103 > n108 || (n103 == n108 && (n102 > n107 || (n102 == n107 && (n101 > n106 || (n101 == n106 && (n100 > n105 || (n100 == n105 && (n99 > n104 || (n99 == n104 && (n97 > n4 || (n97 == n4 && n98 >= n3)))))))))))) {
                    final long n124;
                    n98 = ((n124 = n98 - n3) & n);
                    final long n125;
                    n97 = ((n125 = (n124 >> 31) + n97 - n4) & n);
                    final long n126;
                    n99 = ((n126 = (n125 >> 31) + n99 - n104) & n);
                    final long n127;
                    n100 = ((n127 = (n126 >> 31) + n100 - n105) & n);
                    final long n128;
                    n101 = ((n128 = (n127 >> 31) + n101 - n106) & n);
                    final long n129;
                    n102 = ((n129 = (n128 >> 31) + n102 - n107) & n);
                    n103 = ((n129 >> 31) + n103 - n108 & n);
                }
                array3[0] = n98;
                array3[1] = n97;
                array3[2] = n99;
                array3[3] = n100;
                array3[4] = n101;
                array3[5] = n102;
                array3[6] = n103;
            }
            case 8: {
                long n137;
                long n136;
                long n135;
                long n134;
                long n133;
                long n132;
                long n131;
                long n130 = n131 = (n132 = (n133 = (n134 = (n135 = (n136 = (n137 = 0L))))));
                final long n138 = testNbr[2];
                final long n139 = testNbr[3];
                final long n140 = testNbr[4];
                final long n141 = testNbr[5];
                final long n142 = testNbr[6];
                final long n143 = testNbr[7];
                final long n144 = array2[2];
                final long n145 = array2[3];
                final long n146 = array2[4];
                final long n147 = array2[5];
                final long n148 = array2[6];
                final long n149 = array2[7];
                int n150 = 0;
                do {
                    final long n152;
                    final long n151 = (n152 = array[n150]) * n5 + n131;
                    final long n153 = (int)n151 * n2 & n;
                    final long n154;
                    n131 = ((n154 = (n153 * n3 + n151 >>> 31) + n153 * n4 + n152 * n6 + n130) & 0x7FFFFFFFL);
                    final long n155;
                    n130 = ((n155 = (n154 >>> 31) + n153 * n138 + n152 * n144 + n132) & 0x7FFFFFFFL);
                    final long n156;
                    n132 = ((n156 = (n155 >>> 31) + n153 * n139 + n152 * n145 + n133) & 0x7FFFFFFFL);
                    final long n157;
                    n133 = ((n157 = (n156 >>> 31) + n153 * n140 + n152 * n146 + n134) & 0x7FFFFFFFL);
                    final long n158;
                    n134 = ((n158 = (n157 >>> 31) + n153 * n141 + n152 * n147 + n135) & 0x7FFFFFFFL);
                    final long n159;
                    n135 = ((n159 = (n158 >>> 31) + n153 * n142 + n152 * n148 + n136) & 0x7FFFFFFFL);
                    final long n160;
                    n136 = ((n160 = (n159 >>> 31) + n153 * n143 + n152 * n149 + n137) & 0x7FFFFFFFL);
                    n137 = n160 >>> 31;
                } while (++n150 < 8);
                if (n137 > n143 || (n137 == n143 && (n136 > n142 || (n136 == n142 && (n135 > n141 || (n135 == n141 && (n134 > n140 || (n134 == n140 && (n133 > n139 || (n133 == n139 && (n132 > n138 || (n132 == n138 && (n130 > n4 || (n130 == n4 && n131 >= n3)))))))))))))) {
                    final long n161;
                    n131 = ((n161 = n131 - n3) & n);
                    final long n162;
                    n130 = ((n162 = (n161 >> 31) + n130 - n4) & n);
                    final long n163;
                    n132 = ((n163 = (n162 >> 31) + n132 - n138) & n);
                    final long n164;
                    n133 = ((n164 = (n163 >> 31) + n133 - n139) & n);
                    final long n165;
                    n134 = ((n165 = (n164 >> 31) + n134 - n140) & n);
                    final long n166;
                    n135 = ((n166 = (n165 >> 31) + n135 - n141) & n);
                    final long n167;
                    n136 = ((n167 = (n166 >> 31) + n136 - n142) & n);
                    n137 = ((n167 >> 31) + n137 - n143 & n);
                }
                array3[0] = n131;
                array3[1] = n130;
                array3[2] = n132;
                array3[3] = n133;
                array3[4] = n134;
                array3[5] = n135;
                array3[6] = n136;
                array3[7] = n137;
            }
            case 9: {
                long n176;
                long n175;
                long n174;
                long n173;
                long n172;
                long n171;
                long n170;
                long n169;
                long n168 = n169 = (n170 = (n171 = (n172 = (n173 = (n174 = (n175 = (n176 = 0L)))))));
                final long n177 = testNbr[2];
                final long n178 = testNbr[3];
                final long n179 = testNbr[4];
                final long n180 = testNbr[5];
                final long n181 = testNbr[6];
                final long n182 = testNbr[7];
                final long n183 = testNbr[8];
                final long n184 = array2[2];
                final long n185 = array2[3];
                final long n186 = array2[4];
                final long n187 = array2[5];
                final long n188 = array2[6];
                final long n189 = array2[7];
                final long n190 = array2[8];
                int n191 = 0;
                do {
                    final long n193;
                    final long n192 = (n193 = array[n191]) * n5 + n169;
                    final long n194 = (int)n192 * n2 & n;
                    final long n195;
                    n169 = ((n195 = (n194 * n3 + n192 >>> 31) + n194 * n4 + n193 * n6 + n168) & 0x7FFFFFFFL);
                    final long n196;
                    n168 = ((n196 = (n195 >>> 31) + n194 * n177 + n193 * n184 + n170) & 0x7FFFFFFFL);
                    final long n197;
                    n170 = ((n197 = (n196 >>> 31) + n194 * n178 + n193 * n185 + n171) & 0x7FFFFFFFL);
                    final long n198;
                    n171 = ((n198 = (n197 >>> 31) + n194 * n179 + n193 * n186 + n172) & 0x7FFFFFFFL);
                    final long n199;
                    n172 = ((n199 = (n198 >>> 31) + n194 * n180 + n193 * n187 + n173) & 0x7FFFFFFFL);
                    final long n200;
                    n173 = ((n200 = (n199 >>> 31) + n194 * n181 + n193 * n188 + n174) & 0x7FFFFFFFL);
                    final long n201;
                    n174 = ((n201 = (n200 >>> 31) + n194 * n182 + n193 * n189 + n175) & 0x7FFFFFFFL);
                    final long n202;
                    n175 = ((n202 = (n201 >>> 31) + n194 * n183 + n193 * n190 + n176) & 0x7FFFFFFFL);
                    n176 = n202 >>> 31;
                } while (++n191 < 9);
                if (n176 > n183 || (n176 == n183 && (n175 > n182 || (n175 == n182 && (n174 > n181 || (n174 == n181 && (n173 > n180 || (n173 == n180 && (n172 > n179 || (n172 == n179 && (n171 > n178 || (n171 == n178 && (n170 > n177 || (n170 == n177 && (n168 > n4 || (n168 == n4 && n169 >= n3)))))))))))))))) {
                    final long n203;
                    n169 = ((n203 = n169 - n3) & n);
                    final long n204;
                    n168 = ((n204 = (n203 >> 31) + n168 - n4) & n);
                    final long n205;
                    n170 = ((n205 = (n204 >> 31) + n170 - n177) & n);
                    final long n206;
                    n171 = ((n206 = (n205 >> 31) + n171 - n178) & n);
                    final long n207;
                    n172 = ((n207 = (n206 >> 31) + n172 - n179) & n);
                    final long n208;
                    n173 = ((n208 = (n207 >> 31) + n173 - n180) & n);
                    final long n209;
                    n174 = ((n209 = (n208 >> 31) + n174 - n181) & n);
                    final long n210;
                    n175 = ((n210 = (n209 >> 31) + n175 - n182) & n);
                    n176 = ((n210 >> 31) + n176 - n183 & n);
                }
                array3[0] = n169;
                array3[1] = n168;
                array3[2] = n170;
                array3[3] = n171;
                array3[4] = n172;
                array3[5] = n173;
                array3[6] = n174;
                array3[7] = n175;
                array3[8] = n176;
            }
            case 10: {
                long n220;
                long n219;
                long n218;
                long n217;
                long n216;
                long n215;
                long n214;
                long n213;
                long n212;
                long n211 = n212 = (n213 = (n214 = (n215 = (n216 = (n217 = (n218 = (n219 = (n220 = 0L))))))));
                final long n221 = testNbr[2];
                final long n222 = testNbr[3];
                final long n223 = testNbr[4];
                final long n224 = testNbr[5];
                final long n225 = testNbr[6];
                final long n226 = testNbr[7];
                final long n227 = testNbr[8];
                final long n228 = testNbr[9];
                final long n229 = array2[2];
                final long n230 = array2[3];
                final long n231 = array2[4];
                final long n232 = array2[5];
                final long n233 = array2[6];
                final long n234 = array2[7];
                final long n235 = array2[8];
                final long n236 = array2[9];
                int n237 = 0;
                do {
                    final long n239;
                    final long n238 = (n239 = array[n237]) * n5 + n212;
                    final long n240 = (int)n238 * n2 & n;
                    final long n241;
                    n212 = ((n241 = (n240 * n3 + n238 >>> 31) + n240 * n4 + n239 * n6 + n211) & 0x7FFFFFFFL);
                    final long n242;
                    n211 = ((n242 = (n241 >>> 31) + n240 * n221 + n239 * n229 + n213) & 0x7FFFFFFFL);
                    final long n243;
                    n213 = ((n243 = (n242 >>> 31) + n240 * n222 + n239 * n230 + n214) & 0x7FFFFFFFL);
                    final long n244;
                    n214 = ((n244 = (n243 >>> 31) + n240 * n223 + n239 * n231 + n215) & 0x7FFFFFFFL);
                    final long n245;
                    n215 = ((n245 = (n244 >>> 31) + n240 * n224 + n239 * n232 + n216) & 0x7FFFFFFFL);
                    final long n246;
                    n216 = ((n246 = (n245 >>> 31) + n240 * n225 + n239 * n233 + n217) & 0x7FFFFFFFL);
                    final long n247;
                    n217 = ((n247 = (n246 >>> 31) + n240 * n226 + n239 * n234 + n218) & 0x7FFFFFFFL);
                    final long n248;
                    n218 = ((n248 = (n247 >>> 31) + n240 * n227 + n239 * n235 + n219) & 0x7FFFFFFFL);
                    final long n249;
                    n219 = ((n249 = (n248 >>> 31) + n240 * n228 + n239 * n236 + n220) & 0x7FFFFFFFL);
                    n220 = n249 >>> 31;
                } while (++n237 < 10);
                if (n220 > n228 || (n220 == n228 && (n219 > n227 || (n219 == n227 && (n218 > n226 || (n218 == n226 && (n217 > n225 || (n217 == n225 && (n216 > n224 || (n216 == n224 && (n215 > n223 || (n215 == n223 && (n214 > n222 || (n214 == n222 && (n213 > n221 || (n213 == n221 && (n211 > n4 || (n211 == n4 && n212 >= n3)))))))))))))))))) {
                    final long n250;
                    n212 = ((n250 = n212 - n3) & n);
                    final long n251;
                    n211 = ((n251 = (n250 >> 31) + n211 - n4) & n);
                    final long n252;
                    n213 = ((n252 = (n251 >> 31) + n213 - n221) & n);
                    final long n253;
                    n214 = ((n253 = (n252 >> 31) + n214 - n222) & n);
                    final long n254;
                    n215 = ((n254 = (n253 >> 31) + n215 - n223) & n);
                    final long n255;
                    n216 = ((n255 = (n254 >> 31) + n216 - n224) & n);
                    final long n256;
                    n217 = ((n256 = (n255 >> 31) + n217 - n225) & n);
                    final long n257;
                    n218 = ((n257 = (n256 >> 31) + n218 - n226) & n);
                    final long n258;
                    n219 = ((n258 = (n257 >> 31) + n219 - n227) & n);
                    n220 = ((n258 >> 31) + n220 - n228 & n);
                }
                array3[0] = n212;
                array3[1] = n211;
                array3[2] = n213;
                array3[3] = n214;
                array3[4] = n215;
                array3[5] = n216;
                array3[6] = n217;
                array3[7] = n218;
                array3[8] = n219;
                array3[9] = n220;
            }
            case 11: {
                long n269;
                long n268;
                long n267;
                long n266;
                long n265;
                long n264;
                long n263;
                long n262;
                long n261;
                long n260;
                long n259 = n260 = (n261 = (n262 = (n263 = (n264 = (n265 = (n266 = (n267 = (n268 = (n269 = 0L)))))))));
                final long n270 = testNbr[2];
                final long n271 = testNbr[3];
                final long n272 = testNbr[4];
                final long n273 = testNbr[5];
                final long n274 = testNbr[6];
                final long n275 = testNbr[7];
                final long n276 = testNbr[8];
                final long n277 = testNbr[9];
                final long n278 = testNbr[10];
                final long n279 = array2[2];
                final long n280 = array2[3];
                final long n281 = array2[4];
                final long n282 = array2[5];
                final long n283 = array2[6];
                final long n284 = array2[7];
                final long n285 = array2[8];
                final long n286 = array2[9];
                final long n287 = array2[10];
                int n288 = 0;
                do {
                    final long n290;
                    final long n289 = (n290 = array[n288]) * n5 + n260;
                    final long n291 = (int)n289 * n2 & n;
                    final long n292;
                    n260 = ((n292 = (n291 * n3 + n289 >>> 31) + n291 * n4 + n290 * n6 + n259) & 0x7FFFFFFFL);
                    final long n293;
                    n259 = ((n293 = (n292 >>> 31) + n291 * n270 + n290 * n279 + n261) & 0x7FFFFFFFL);
                    final long n294;
                    n261 = ((n294 = (n293 >>> 31) + n291 * n271 + n290 * n280 + n262) & 0x7FFFFFFFL);
                    final long n295;
                    n262 = ((n295 = (n294 >>> 31) + n291 * n272 + n290 * n281 + n263) & 0x7FFFFFFFL);
                    final long n296;
                    n263 = ((n296 = (n295 >>> 31) + n291 * n273 + n290 * n282 + n264) & 0x7FFFFFFFL);
                    final long n297;
                    n264 = ((n297 = (n296 >>> 31) + n291 * n274 + n290 * n283 + n265) & 0x7FFFFFFFL);
                    final long n298;
                    n265 = ((n298 = (n297 >>> 31) + n291 * n275 + n290 * n284 + n266) & 0x7FFFFFFFL);
                    final long n299;
                    n266 = ((n299 = (n298 >>> 31) + n291 * n276 + n290 * n285 + n267) & 0x7FFFFFFFL);
                    final long n300;
                    n267 = ((n300 = (n299 >>> 31) + n291 * n277 + n290 * n286 + n268) & 0x7FFFFFFFL);
                    final long n301;
                    n268 = ((n301 = (n300 >>> 31) + n291 * n278 + n290 * n287 + n269) & 0x7FFFFFFFL);
                    n269 = n301 >>> 31;
                } while (++n288 < 11);
                if (n269 > n278 || (n269 == n278 && (n268 > n277 || (n268 == n277 && (n267 > n276 || (n267 == n276 && (n266 > n275 || (n266 == n275 && (n265 > n274 || (n265 == n274 && (n264 > n273 || (n264 == n273 && (n263 > n272 || (n263 == n272 && (n262 > n271 || (n262 == n271 && (n261 > n270 || (n261 == n270 && (n259 > n4 || (n259 == n4 && n260 >= n3)))))))))))))))))))) {
                    final long n302;
                    n260 = ((n302 = n260 - n3) & n);
                    final long n303;
                    n259 = ((n303 = (n302 >> 31) + n259 - n4) & n);
                    final long n304;
                    n261 = ((n304 = (n303 >> 31) + n261 - n270) & n);
                    final long n305;
                    n262 = ((n305 = (n304 >> 31) + n262 - n271) & n);
                    final long n306;
                    n263 = ((n306 = (n305 >> 31) + n263 - n272) & n);
                    final long n307;
                    n264 = ((n307 = (n306 >> 31) + n264 - n273) & n);
                    final long n308;
                    n265 = ((n308 = (n307 >> 31) + n265 - n274) & n);
                    final long n309;
                    n266 = ((n309 = (n308 >> 31) + n266 - n275) & n);
                    final long n310;
                    n267 = ((n310 = (n309 >> 31) + n267 - n276) & n);
                    final long n311;
                    n268 = ((n311 = (n310 >> 31) + n268 - n277) & n);
                    n269 = ((n311 >> 31) + n269 - n278 & n);
                }
                array3[0] = n260;
                array3[1] = n259;
                array3[2] = n261;
                array3[3] = n262;
                array3[4] = n263;
                array3[5] = n264;
                array3[6] = n265;
                array3[7] = n266;
                array3[8] = n267;
                array3[9] = n268;
                array3[10] = n269;
            }
            default: {
                long n322;
                long n321;
                long n320;
                long n319;
                long n318;
                long n317;
                long n316;
                long n315;
                long n314;
                long n313;
                long n312 = n313 = (n314 = (n315 = (n316 = (n317 = (n318 = (n319 = (n320 = (n321 = (n322 = 0L)))))))));
                final long n323 = testNbr[2];
                final long n324 = testNbr[3];
                final long n325 = testNbr[4];
                final long n326 = testNbr[5];
                final long n327 = testNbr[6];
                final long n328 = testNbr[7];
                final long n329 = testNbr[8];
                final long n330 = testNbr[9];
                final long n331 = testNbr[10];
                final long n332 = array2[2];
                final long n333 = array2[3];
                final long n334 = array2[4];
                final long n335 = array2[5];
                final long n336 = array2[6];
                final long n337 = array2[7];
                final long n338 = array2[8];
                final long n339 = array2[9];
                final long n340 = array2[10];
                for (int i = 11; i < numberLength; ++i) {
                    array3[i] = 0L;
                }
                int n341 = 0;
                do {
                    final long n343;
                    final long n342 = (n343 = array[n341]) * n5 + n313;
                    final long n344 = (int)n342 * n2 & n;
                    final long n345;
                    n313 = ((n345 = (n344 * n3 + n342 >>> 31) + n344 * n4 + n343 * n6 + n312) & 0x7FFFFFFFL);
                    final long n346;
                    n312 = ((n346 = (n345 >>> 31) + n344 * n323 + n343 * n332 + n314) & 0x7FFFFFFFL);
                    final long n347;
                    n314 = ((n347 = (n346 >>> 31) + n344 * n324 + n343 * n333 + n315) & 0x7FFFFFFFL);
                    final long n348;
                    n315 = ((n348 = (n347 >>> 31) + n344 * n325 + n343 * n334 + n316) & 0x7FFFFFFFL);
                    final long n349;
                    n316 = ((n349 = (n348 >>> 31) + n344 * n326 + n343 * n335 + n317) & 0x7FFFFFFFL);
                    final long n350;
                    n317 = ((n350 = (n349 >>> 31) + n344 * n327 + n343 * n336 + n318) & 0x7FFFFFFFL);
                    final long n351;
                    n318 = ((n351 = (n350 >>> 31) + n344 * n328 + n343 * n337 + n319) & 0x7FFFFFFFL);
                    final long n352;
                    n319 = ((n352 = (n351 >>> 31) + n344 * n329 + n343 * n338 + n320) & 0x7FFFFFFFL);
                    final long n353;
                    n320 = ((n353 = (n352 >>> 31) + n344 * n330 + n343 * n339 + n321) & 0x7FFFFFFFL);
                    final long n354;
                    n321 = ((n354 = (n353 >>> 31) + n344 * n331 + n343 * n340 + n322) & 0x7FFFFFFFL);
                    long n355;
                    n322 = ((n355 = (n354 >>> 31) + n344 * testNbr[11] + n343 * array2[11] + array3[11]) & 0x7FFFFFFFL);
                    int j;
                    for (j = 12; j < numberLength; ++j) {
                        array3[j - 1] = ((n355 = (n355 >>> 31) + n344 * testNbr[j] + n343 * array2[j] + array3[j]) & 0x7FFFFFFFL);
                    }
                    array3[j - 1] = n355 >>> 31;
                } while (++n341 < numberLength);
                array3[0] = n313;
                array3[1] = n312;
                array3[2] = n314;
                array3[3] = n315;
                array3[4] = n316;
                array3[5] = n317;
                array3[6] = n318;
                array3[7] = n319;
                array3[8] = n320;
                array3[9] = n321;
                array3[10] = n322;
                int n356;
                for (n356 = numberLength - 1; n356 >= 0 && array3[n356] == testNbr[n356]; --n356) {}
                if (n356 < 0 || (n356 >= 0 && array3[n356] >= testNbr[n356])) {
                    long n357 = 0L;
                    for (int k = 0; k < numberLength; ++k) {
                        array3[k] = ((n357 = (n357 >> 31) + array3[k] - testNbr[k]) & n);
                    }
                }
            }
        }
    }
    
    void MatrTranspMult(final int[] array, final int[] array2, final int[] array3) {
        final int length = array.length;
        int n = 1;
        int n2 = 31;
        do {
            int n3 = 0;
            for (int i = 0; i < length; ++i) {
                if ((array[i] & n) != 0x0) {
                    n3 ^= array2[i];
                }
            }
            array3[n2] = n3;
            n *= 2;
        } while (--n2 >= 0);
    }
    
    int Moebius(final int n) {
        if (this.onlyFactoring) {
            int n2 = 1;
            int n3 = n;
            if (n3 % 2 == 0) {
                n2 = -n2;
                n3 /= 2;
                if (n3 % 2 == 0) {
                    return 0;
                }
            }
            if (n3 % 3 == 0) {
                n2 = -n2;
                n3 /= 3;
                if (n3 % 3 == 0) {
                    return 0;
                }
            }
            for (int n4 = 5; n4 * n4 <= n3; n4 += 2) {
                if (n4 % 3 != 0) {
                    while (n3 % n4 == 0) {
                        n2 = -n2;
                        n3 /= n4;
                        if (n3 % n4 == 0) {
                            return 0;
                        }
                    }
                }
            }
            if (n3 > 1) {
                n2 = -n2;
            }
            return n2;
        }
        return 0;
    }
    
    BigInteger fnECM(final BigInteger bigInteger, final int n) {
        final long[] array = new long[1200];
        final long[] array2 = new long[1200];
        final long[] array3 = new long[1200];
        final long[] fieldAA = new long[1200];
        final long[] array4 = new long[1200];
        final long[] array5 = new long[1200];
        final long[] array6 = new long[1200];
        final long[] array7 = new long[1200];
        final long[] array8 = new long[1200];
        final long[] array9 = new long[1200];
        final long[] fieldTX = new long[1200];
        this.fieldTX = fieldTX;
        final long[] fieldTZ = new long[1200];
        this.fieldTZ = fieldTZ;
        final long[] fieldUX = new long[1200];
        this.fieldUX = fieldUX;
        final long[] fieldUZ = new long[1200];
        this.fieldUZ = fieldUZ;
        final long[] array10 = new long[1200];
        final long[] array11 = new long[1200];
        final long[] array12 = new long[1200];
        final long[] array13 = new long[1200];
        final long[] array14 = new long[1200];
        final long[] array15 = new long[1200];
        final long[] array16 = new long[1200];
        final long[] array17 = new long[1200];
        final long[] array18 = new long[1200];
        final long[] fieldAux1 = new long[1200];
        this.fieldAux1 = fieldAux1;
        final long[] fieldAux2 = new long[1200];
        this.fieldAux2 = fieldAux2;
        final long[] fieldAux3 = new long[1200];
        this.fieldAux3 = fieldAux3;
        this.fieldAux4 = new long[1200];
        final long[] array19 = new long[1200];
        final long[] array20 = new long[1200];
        final long[][] array21 = new long[480][1200];
        final byte[] array22 = new byte[23100];
        final byte[] array23 = new byte[2310];
        final int[] array24 = new int[480];
        int n2 = 0;
        this.fieldAA = fieldAA;
        this.BigNbrToBigInt(bigInteger);
        this.GetMontgomeryParms();
        for (int i = 0; i < this.NumberLength; ++i) {
            final long[] array25 = array7;
            final int n3 = i;
            final long[] array26 = array4;
            final int n4 = i;
            final long[] array27 = array5;
            final int n5 = i;
            final long[] array28 = array13;
            final int n6 = i;
            final long[] array29 = array14;
            final int n7 = i;
            final long[] array30 = array6;
            final int n8 = i;
            final long n9 = 0L;
            array29[n7] = (array30[n8] = n9);
            array27[n5] = (array28[n6] = n9);
            array25[n3] = (array26[n4] = n9);
        }
        this.textAreaContents = "";
        this.StringToLabel = "Factoring ";
        this.insertBigNbr(bigInteger);
        this.addStringToLabel("(" + bigInteger.toString().length() + " digits)");
        --this.EC;
        this.SmallPrime[0] = 2;
        long n10 = 3L;
        this.indexM = 1;
        this.indexM = 1;
        while (this.indexM < this.SmallPrime.length) {
            this.SmallPrime[this.indexM] = (int)n10;
            Label_0449: {
                break Label_0449;
                long n11 = 0L;
                do {
                    if (n10 % n11 != 0L) {
                        n11 += 2L;
                    }
                    else {
                        n10 += 2L;
                        n11 = 3L;
                    }
                } while (n11 * n11 <= n10);
            }
            ++this.indexM;
        }
        this.foundByLehman = false;
    Label_3859_Outer:
        while (true) {
            if (this.NextEC > 0) {
                this.EC = this.NextEC;
                this.NextEC = -1;
                if (this.EC >= 200000000) {
                    return ecm.BigInt1;
                }
            }
            else {
                ++this.EC;
                final BigInteger lehman = Lehman(this.NumberToFactor, this.EC);
                if (!lehman.equals(ecm.BigInt1)) {
                    this.foundByLehman = true;
                    return lehman;
                }
                final long n12 = bigInteger.toString().length();
                if (n12 > 30L && n12 <= 90L && (this.digitsInGroup & 0x400) == 0x0) {
                    final int n13 = ecm.limits[((int)n12 - 31) / 5];
                    if ((this.EC % 50000000 > n13 && n2 == 0) || (this.EC % 50000000 == n13 && n2 == 1)) {
                        this.EC += 200000000;
                        return ecm.BigInt1;
                    }
                }
            }
            n2 = 1;
            this.Typ[n] = this.EC;
            long n14 = 2000L;
            long n15 = 200000L;
            long n16 = 45L;
            long n17 = this.EC;
            this.NbrPrimes = 303;
            if (this.EC > 25) {
                if (this.EC < 326) {
                    n14 = 50000L;
                    n15 = 5000000L;
                    n16 = 224L;
                    n17 = this.EC - 24;
                    this.NbrPrimes = 5133;
                }
                else if (this.EC < 2000) {
                    n14 = 1000000L;
                    n15 = 100000000L;
                    n16 = 1001L;
                    n17 = this.EC - 299;
                    this.NbrPrimes = 78498;
                }
                else {
                    n14 = 11000000L;
                    n15 = 1100000000L;
                    n16 = 3316L;
                    n17 = this.EC - 1900;
                    this.NbrPrimes = 726517;
                }
            }
            final String string = this.textAreaContents + this.StringToLabel + "\nLimit (B1=" + n14 + "; B2=" + n15 + ")    Curve ";
            String string2 = "Digits in factor:   ";
            String s = "Probability:        ";
            int n18 = 0;
            do {
                string2 = string2 + "    >= " + (n18 * 5 + 15);
                final int n19 = (int)Math.round(100.0 * (1.0 - Math.exp(-(n14 * n17) / this.ProbArray[n18])));
                if (n19 == 100) {
                    s += "    100% ";
                }
                else if (n19 >= 10) {
                    s = s + "     " + n19 + "% ";
                }
                else {
                    s = s + "      " + n19 + "% ";
                }
            } while (++n18 < 6);
            this.lowerTextArea.setText(string + this.EC + "\n" + string2 + "\n" + s);
            this.LongToBigNbr(2 * (this.EC + 1), fieldAux1);
            this.LongToBigNbr(3 * (this.EC + 1) * (this.EC + 1) - 1, fieldAux2);
            this.ModInvBigNbr(fieldAux2, fieldAux2, this.TestNbr);
            this.MultBigNbrModN(fieldAux1, fieldAux2, fieldAux3);
            this.MultBigNbrModN(fieldAux3, this.MontgomeryMultR1, array);
            this.MontgomeryMult(array, array, array2);
            this.MontgomeryMult(array2, array, array3);
            this.SubtractBigNbrModN(array3, array, fieldAux1);
            this.MultBigNbrByLongModN(array2, 9L, fieldAux2);
            this.SubtractBigNbrModN(fieldAux2, this.MontgomeryMultR1, fieldAux2);
            this.MontgomeryMult(fieldAux1, fieldAux2, fieldAux3);
            if (!this.BigNbrIsZero(fieldAux3)) {
                this.MultBigNbrByLongModN(array, 4L, array18);
                this.MultBigNbrByLongModN(array2, 6L, fieldAux1);
                this.SubtractBigNbrModN(this.MontgomeryMultR1, fieldAux1, fieldAux1);
                this.MontgomeryMult(array2, array2, fieldAux2);
                this.MultBigNbrByLongModN(fieldAux2, 3L, fieldAux2);
                this.SubtractBigNbrModN(fieldAux1, fieldAux2, fieldAux1);
                this.MultBigNbrByLongModN(array3, 4L, fieldAux2);
                this.ModInvBigNbr(fieldAux2, fieldAux2, this.TestNbr);
                this.MontgomeryMult(fieldAux2, this.MontgomeryMultAfterInv, fieldAux3);
                this.MontgomeryMult(fieldAux1, fieldAux3, array);
                this.AddBigNbrModN(array, this.MontgomeryMultR2, fieldAux1);
                this.LongToBigNbr(4L, fieldAux2);
                this.ModInvBigNbr(fieldAux2, fieldAux3, this.TestNbr);
                this.MultBigNbrModN(fieldAux3, this.MontgomeryMultR1, fieldAux2);
                this.MontgomeryMult(fieldAux1, fieldAux2, fieldAA);
                this.MultBigNbrByLongModN(array2, 3L, fieldAux1);
                this.AddBigNbrModN(fieldAux1, this.MontgomeryMultR1, array17);
                System.arraycopy(array17, 0, array19, 0, this.NumberLength);
                System.arraycopy(array18, 0, array20, 0, this.NumberLength);
                System.arraycopy(this.MontgomeryMultR1, 0, this.GcdAccumulated, 0, this.NumberLength);
                int n20 = 0;
            Label_3859:
                while (true) {
                    do {
                        this.indexPrimes = 0;
                        this.StepECM = 1;
                        for (int n21 = 1; n21 <= n14; n21 <<= 1) {
                            this.duplicate(array17, array18, array17, array18);
                        }
                        for (int n22 = 3; n22 <= n14; n22 *= 3) {
                            this.duplicate(array11, array12, array17, array18);
                            this.add3(array17, array18, array17, array18, array11, array12, array17, array18);
                        }
                        if (n20 == 0) {
                            this.MontgomeryMult(this.GcdAccumulated, array18, fieldAux1);
                            System.arraycopy(fieldAux1, 0, this.GcdAccumulated, 0, this.NumberLength);
                        }
                        else {
                            this.GcdBigNbr(array18, this.TestNbr, array6);
                            if (!this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                break Label_3859;
                            }
                        }
                        this.indexM = 1;
                        long n23;
                        do {
                            ++this.indexPrimes;
                            long n24;
                            for (n23 = (n24 = this.SmallPrime[this.indexM]); n24 <= n14; n24 *= n23) {
                                this.prac((int)n23, array17, array18, array11, array12, array13, array14);
                            }
                            ++this.indexM;
                            if (n20 == 0) {
                                this.MontgomeryMult(this.GcdAccumulated, array18, fieldAux1);
                                System.arraycopy(fieldAux1, 0, this.GcdAccumulated, 0, this.NumberLength);
                            }
                            else {
                                this.GcdBigNbr(array18, this.TestNbr, array6);
                                if (this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                    continue Label_3859_Outer;
                                }
                                break Label_3859;
                            }
                        } while (this.SmallPrime[this.indexM - 1] <= n16);
                        long n25 = n23 + 2L;
                        int n26 = (int)n25;
                        int n27 = 0;
                        do {
                            array23[n27] = (byte)((n26 % 3 == 0 || n26 % 5 == 0 || n26 % 7 == 0 || n26 % 11 == 0) ? 1 : 0);
                            n26 += 2;
                        } while (++n27 < 2310);
                        do {
                            GenerateSieve((int)n25, array22, array23, this.SmallPrime);
                            int n28 = 0;
                            do {
                                if (array22[n28] == 0) {
                                    if (n25 + 2 * n28 > n14) {
                                        break;
                                    }
                                    ++this.indexPrimes;
                                    this.prac((int)(n25 + 2 * n28), array17, array18, array11, array12, array13, array14);
                                    if (n20 == 0) {
                                        this.MontgomeryMult(this.GcdAccumulated, array18, fieldAux1);
                                        System.arraycopy(fieldAux1, 0, this.GcdAccumulated, 0, this.NumberLength);
                                    }
                                    else {
                                        this.GcdBigNbr(array18, this.TestNbr, array6);
                                        if (this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                            continue Label_3859_Outer;
                                        }
                                        break Label_3859;
                                    }
                                }
                            } while (++n28 < 23100);
                            n25 += 46200L;
                        } while (n25 < n14);
                        if (n20 != 0) {
                            continue Label_3859_Outer;
                        }
                        if (this.BigNbrIsZero(this.GcdAccumulated)) {
                            System.arraycopy(array19, 0, array17, 0, this.NumberLength);
                            System.arraycopy(array20, 0, array18, 0, this.NumberLength);
                            continue Label_3859_Outer;
                        }
                        this.GcdBigNbr(this.GcdAccumulated, this.TestNbr, array6);
                        if (this.BigNbrAreEqual(array6, this.BigNbr1)) {
                            break;
                        }
                        if (!this.BigNbrAreEqual(array6, this.TestNbr)) {
                            this.lowerTextArea.setText("");
                            this.StepECM = 0;
                            return this.BigIntToBigNbr(array6);
                        }
                        continue Label_3859_Outer;
                    } while (++n20 < 2);
                    this.StepECM = 2;
                    int n29 = 0;
                    int j = 1;
                    do {
                        if (j % 3 == 0 || j % 5 == 0 || j % 7 == 0 || j % 11 == 0) {
                            array23[j / 2] = 1;
                        }
                        else {
                            array23[array24[n29++] = j / 2] = 0;
                        }
                        j += 2;
                    } while (j < 2310);
                    System.arraycopy(array23, 0, array23, 1155, 1155);
                    System.arraycopy(array17, 0, array19, 0, this.NumberLength);
                    System.arraycopy(array18, 0, array20, 0, this.NumberLength);
                    int n30 = 0;
                    do {
                        System.arraycopy(this.MontgomeryMultR1, 0, this.GcdAccumulated, 0, this.NumberLength);
                        System.arraycopy(array17, 0, fieldUX, 0, this.NumberLength);
                        System.arraycopy(array18, 0, fieldUZ, 0, this.NumberLength);
                        this.ModInvBigNbr(array18, fieldAux2, this.TestNbr);
                        this.MontgomeryMult(fieldAux2, this.MontgomeryMultAfterInv, fieldAux1);
                        this.MontgomeryMult(fieldAux1, array17, array21[0]);
                        int n31 = 0;
                        this.AddBigNbrModN(array17, array18, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array11);
                        this.SubtractBigNbrModN(array17, array18, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array12);
                        this.MontgomeryMult(array11, array12, fieldTX);
                        this.SubtractBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAA, fieldAux2);
                        this.AddBigNbrModN(fieldAux2, array12, fieldAux3);
                        this.MontgomeryMult(fieldAux1, fieldAux3, fieldTZ);
                        this.SubtractBigNbrModN(array17, array18, fieldAux1);
                        this.AddBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                        this.MontgomeryMult(fieldAux1, fieldAux2, array11);
                        this.AddBigNbrModN(array17, array18, fieldAux1);
                        this.SubtractBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                        this.MontgomeryMult(fieldAux1, fieldAux2, array12);
                        this.AddBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                        this.MontgomeryMult(fieldAux2, fieldUZ, array17);
                        this.SubtractBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                        this.MontgomeryMult(fieldAux2, fieldUX, array18);
                        int k = 5;
                        do {
                            System.arraycopy(array17, 0, array15, 0, this.NumberLength);
                            System.arraycopy(array18, 0, array16, 0, this.NumberLength);
                            this.SubtractBigNbrModN(array17, array18, fieldAux1);
                            this.AddBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                            this.MontgomeryMult(fieldAux1, fieldAux2, array11);
                            this.AddBigNbrModN(array17, array18, fieldAux1);
                            this.SubtractBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                            this.MontgomeryMult(fieldAux1, fieldAux2, array12);
                            this.AddBigNbrModN(array11, array12, fieldAux1);
                            this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                            this.MontgomeryMult(fieldAux2, fieldUZ, array17);
                            this.SubtractBigNbrModN(array11, array12, fieldAux1);
                            this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                            this.MontgomeryMult(fieldAux2, fieldUX, array18);
                            if (n30 == 0) {
                                this.MontgomeryMult(this.GcdAccumulated, fieldAux1, fieldAux2);
                                System.arraycopy(fieldAux2, 0, this.GcdAccumulated, 0, this.NumberLength);
                            }
                            else {
                                this.GcdBigNbr(fieldAux1, this.TestNbr, array6);
                                if (!this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                    continue Label_3859;
                                }
                            }
                            if (k == 1155) {
                                System.arraycopy(array17, 0, array4, 0, this.NumberLength);
                                System.arraycopy(array18, 0, array5, 0, this.NumberLength);
                            }
                            if (k % 3 != 0 && k % 5 != 0 && k % 7 != 0 && k % 11 != 0) {
                                ++n31;
                                this.ModInvBigNbr(array18, fieldAux2, this.TestNbr);
                                this.MontgomeryMult(fieldAux2, this.MontgomeryMultAfterInv, fieldAux1);
                                this.MontgomeryMult(fieldAux1, array17, array21[n31]);
                            }
                            System.arraycopy(array15, 0, fieldUX, 0, this.NumberLength);
                            System.arraycopy(array16, 0, fieldUZ, 0, this.NumberLength);
                            k += 2;
                        } while (k < 2310);
                        this.AddBigNbrModN(array4, array5, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array11);
                        this.SubtractBigNbrModN(array4, array5, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array12);
                        this.MontgomeryMult(array11, array12, array17);
                        this.SubtractBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAA, fieldAux2);
                        this.AddBigNbrModN(fieldAux2, array12, fieldAux3);
                        this.MontgomeryMult(fieldAux1, fieldAux3, array18);
                        System.arraycopy(array17, 0, fieldUX, 0, this.NumberLength);
                        System.arraycopy(array18, 0, fieldUZ, 0, this.NumberLength);
                        this.AddBigNbrModN(array17, array18, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array11);
                        this.SubtractBigNbrModN(array17, array18, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, array12);
                        this.MontgomeryMult(array11, array12, fieldTX);
                        this.SubtractBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAA, fieldAux2);
                        this.AddBigNbrModN(fieldAux2, array12, fieldAux3);
                        this.MontgomeryMult(fieldAux1, fieldAux3, fieldTZ);
                        this.SubtractBigNbrModN(array17, array18, fieldAux1);
                        this.AddBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                        this.MontgomeryMult(fieldAux1, fieldAux2, array11);
                        this.AddBigNbrModN(array17, array18, fieldAux1);
                        this.SubtractBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                        this.MontgomeryMult(fieldAux1, fieldAux2, array12);
                        this.AddBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                        this.MontgomeryMult(fieldAux2, fieldUZ, array17);
                        this.SubtractBigNbrModN(array11, array12, fieldAux1);
                        this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                        this.MontgomeryMult(fieldAux2, fieldUX, array18);
                        final int n32 = (int)(n14 / 4620L);
                        this.maxIndexM = (int)(n15 / 4620L);
                        this.indexM = 0;
                        while (this.indexM <= this.maxIndexM) {
                            if (this.indexM >= n32) {
                                if (this.indexM == 0) {
                                    this.ModInvBigNbr(fieldUZ, fieldAux2, this.TestNbr);
                                    this.MontgomeryMult(fieldAux2, this.MontgomeryMultAfterInv, fieldAux3);
                                    this.MontgomeryMult(fieldUX, fieldAux3, fieldAux1);
                                }
                                else {
                                    this.ModInvBigNbr(array18, fieldAux2, this.TestNbr);
                                    this.MontgomeryMult(fieldAux2, this.MontgomeryMultAfterInv, fieldAux3);
                                    this.MontgomeryMult(array17, fieldAux3, fieldAux1);
                                }
                                if (this.indexM % 10 == 0 || this.indexM == n32) {
                                    GenerateSieve(this.indexM / 10 * 46200 + 1, array22, array23, this.SmallPrime);
                                }
                                final int n33 = 1155 + this.indexM % 10 * 2310;
                                int n34 = 0;
                                do {
                                    final int n35 = array24[n34];
                                    if (array22[n33 + n35] == 0 || array22[n33 - 1 - n35] == 0) {
                                        this.SubtractBigNbrModN(fieldAux1, array21[n34], array7);
                                        this.MontgomeryMult(this.GcdAccumulated, array7, fieldAux2);
                                        System.arraycopy(fieldAux2, 0, this.GcdAccumulated, 0, this.NumberLength);
                                    }
                                } while (++n34 < 480);
                                if (n30 != 0) {
                                    this.GcdBigNbr(this.GcdAccumulated, this.TestNbr, array6);
                                    if (!this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                        continue Label_3859;
                                    }
                                }
                            }
                            if (this.indexM != 0) {
                                System.arraycopy(array17, 0, array15, 0, this.NumberLength);
                                System.arraycopy(array18, 0, array16, 0, this.NumberLength);
                                this.SubtractBigNbrModN(array17, array18, fieldAux1);
                                this.AddBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                                this.MontgomeryMult(fieldAux1, fieldAux2, array11);
                                this.AddBigNbrModN(array17, array18, fieldAux1);
                                this.SubtractBigNbrModN(fieldTX, fieldTZ, fieldAux2);
                                this.MontgomeryMult(fieldAux1, fieldAux2, array12);
                                this.AddBigNbrModN(array11, array12, fieldAux1);
                                this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                                this.MontgomeryMult(fieldAux2, fieldUZ, array17);
                                this.SubtractBigNbrModN(array11, array12, fieldAux1);
                                this.MontgomeryMult(fieldAux1, fieldAux1, fieldAux2);
                                this.MontgomeryMult(fieldAux2, fieldUX, array18);
                                System.arraycopy(array15, 0, fieldUX, 0, this.NumberLength);
                                System.arraycopy(array16, 0, fieldUZ, 0, this.NumberLength);
                            }
                            ++this.indexM;
                        }
                        if (n30 == 0) {
                            if (this.BigNbrIsZero(this.GcdAccumulated)) {
                                System.arraycopy(array19, 0, array17, 0, this.NumberLength);
                                System.arraycopy(array20, 0, array18, 0, this.NumberLength);
                            }
                            else {
                                this.GcdBigNbr(this.GcdAccumulated, this.TestNbr, array6);
                                if (!this.BigNbrAreEqual(array6, this.TestNbr) && !this.BigNbrAreEqual(array6, this.BigNbr1)) {
                                    continue Label_3859;
                                }
                                break;
                            }
                        }
                    } while (++n30 < 2);
                    break;
                }
                this.performLehman = true;
            }
        }
    }
    
    boolean LinearAlgebraPhase(final int n, final int[][] array, final long[] array2, final long[] array3, final long[] array4, final long[] array5, final int[] array6, final long[][] array7) {
        final int[] blockLanczos = this.BlockLanczos(array);
        int i = 1;
        do {
            this.LongToBigNbr(1L, array3);
            this.LongToBigNbr(1L, array4);
            for (int j = array.length - 1; j >= 0; --j) {
                array6[j] = 0;
            }
            for (int k = array.length - 1; k >= 0; --k) {
                if ((blockLanczos[k] & i) != 0x0) {
                    --this.NumberLength;
                    this.MultBigNbrModN(array7[k], array4, array5);
                    ++this.NumberLength;
                    for (int l = 0; l < this.NumberLength; ++l) {
                        array4[l] = array5[l];
                    }
                    final int[] array8 = array[k];
                    --this.NumberLength;
                    for (int n2 = array8.length - 1; n2 >= 0; --n2) {
                        final int n3 = array8[n2];
                        array6[n3] ^= 0x1;
                        if (array6[array8[n2]] == 0) {
                            if (array8[n2] == 0) {
                                this.SubtractBigNbr(this.TestNbr, array3, array3);
                            }
                            else {
                                this.MultBigNbrByLongModN(array3, array2[array8[n2]], array3);
                            }
                        }
                    }
                    ++this.NumberLength;
                }
            }
            for (int n4 = n - 1; n4 >= 0 && array6[n4] == 0; --n4) {}
            this.SubtractBigNbrModN(array4, array3, array4);
            this.GcdBigNbr(array4, this.TestNbr2, array3);
            int n5 = 0;
            if (array3[0] == 1L) {
                for (n5 = 1; n5 < this.NumberLength && array3[n5] == 0L; ++n5) {}
            }
            if (n5 < this.NumberLength) {
                int n6;
                for (n6 = 0; n6 < this.NumberLength && array3[n6] == this.TestNbr2[n6]; ++n6) {}
                if (n6 < this.NumberLength) {
                    return true;
                }
            }
            i *= 2;
        } while (i != 0);
        return false;
    }
    
    int EraseSingletons(final int[][] array, final long[][] array2, final int[] array3) {
        for (int i = array.length - 1; i >= 0; --i) {
            array3[i] = 0;
        }
        for (int j = array.length - 1; j >= 0; --j) {
            final int[] array4 = array[j];
            for (int k = array4.length - 1; k >= 0; --k) {
                final int n = array4[k];
                ++array3[n];
            }
        }
        int n2 = 0;
        for (int l = 0; l < array.length; ++l) {
            final int[] array5 = array[l];
            int n3;
            for (n3 = array5.length - 1; n3 >= 0; --n3) {
                if (array3[array5[n3]] == 1) {
                    ++n2;
                    break;
                }
            }
            if (n3 < 0) {
                array[l - n2] = array[l];
                array2[l - n2] = array2[l];
            }
        }
        return n2;
    }
    
    void layout(final String s, final boolean editable) {
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        (this.labelTop = new Label(s)).reshape(10, 10, 570, 14);
        this.labelTop.setFont(new Font("Courier", 0, 12));
        this.labelTop.setAlignment(1);
        this.add(this.labelTop);
        (this.textNumber = new TextField(1000)).reshape(10, 30, 570, 30);
        this.textNumber.setEditable(editable);
        this.add(this.textNumber);
        (this.upperTextArea = new TextArea("", 7, 75, 1)).reshape(10, 70, 570, 125);
        this.upperTextArea.setEditable(false);
        this.upperTextArea.setFont(new Font("Courier", 0, 12));
        this.add(this.upperTextArea);
        (this.lowerTextArea = new TextArea("", 6, 75, 1)).reshape(10, 205, 570, 100);
        this.lowerTextArea.setEditable(false);
        this.lowerTextArea.setFont(new Font("Courier", 0, 12));
        this.add(this.lowerTextArea);
        (this.labelStatus = new Label("")).reshape(10, 315, 570, 14);
        this.labelStatus.setFont(new Font("Courier", 0, 12));
        this.add(this.labelStatus);
        (this.textCurve = new TextField(10)).reshape(10, 335, 80, 30);
        this.add(this.textCurve);
        (this.btnCurve = new Button("New curve")).reshape(100, 335, 80, 30);
        this.btnCurve.setFont(new Font("Courier", 0, 12));
        this.add(this.btnCurve);
        (this.textFactor = new TextField(200)).reshape(250, 335, 240, 30);
        this.add(this.textFactor);
        (this.btnFactor = new Button("Factor")).reshape(500, 335, 70, 30);
        this.btnFactor.setFont(new Font("Courier", 0, 12));
        this.add(this.btnFactor);
        (this.labelBottom = new Label("Written by Dario Alejandro Alpern. Last updated December 31st, 2007")).reshape(10, 370, 570, 14);
        this.labelBottom.setFont(new Font("Courier", 0, 12));
        this.labelBottom.setAlignment(1);
        this.add(this.labelBottom);
        if (this.onlyFactoring) {
            this.textNumber.addActionListener(new Command(1, this));
            this.textCurve.addActionListener(new Command(2, this));
            this.btnCurve.addActionListener(new Command(3, this));
            this.textFactor.addActionListener(new Command(4, this));
            this.btnFactor.addActionListener(new Command(5, this));
        }
        this.validate();
        this.textNumber.requestFocus();
        this.ProbArray[0] = 50000.0;
        this.ProbArray[1] = 990000.0;
        this.ProbArray[2] = 1.5E7;
        this.ProbArray[3] = 1.75E8;
        this.ProbArray[4] = 1.8E9;
        this.ProbArray[5] = 1.53E10;
    }
    
    void addStringToLabel(final String s) {
        if (s.length() + 1 + this.StringToLabel.length() >= 79) {
            this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
            this.StringToLabel = s + " ";
            return;
        }
        this.StringToLabel = this.StringToLabel + s + " ";
    }
    
    void GetMontgomeryParms() {
        final int numberLength = this.NumberLength;
        this.dN = this.TestNbr[numberLength - 1];
        if (numberLength > 1) {
            this.dN += this.TestNbr[numberLength - 2] / 2.147483648E9;
        }
        if (numberLength > 2) {
            this.dN += this.TestNbr[numberLength - 3] / 4.6116860184273879E18;
        }
        final int n2;
        final int n = n2 = (int)this.TestNbr[0];
        final int n3 = n2 * (2 - n * n2);
        final int n4 = n3 * (2 - n * n3);
        final int n5 = n4 * (2 - n * n4);
        this.MontgomeryMultN = (-(n5 * (2 - n * n5)) & Integer.MAX_VALUE);
        int i = numberLength;
        this.MontgomeryMultR1[i] = 1L;
        do {
            this.MontgomeryMultR1[--i] = 0L;
        } while (i > 0);
        this.AdjustModN(this.MontgomeryMultR1);
        this.MultBigNbrModN(this.MontgomeryMultR1, this.MontgomeryMultR1, this.MontgomeryMultR2);
        this.MontgomeryMult(this.MontgomeryMultR2, this.MontgomeryMultR2, this.MontgomeryMultAfterInv);
        this.AddBigNbrModN(this.MontgomeryMultR1, this.MontgomeryMultR1, this.MontgomeryMultR2);
    }
    
    BigInteger FactoringSIQS(final BigInteger bigInteger) {
        int n = 0;
        int n2 = 0;
        final int[] array = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23 };
        final double[] array2 = new double[array.length];
        long n3 = 0L;
        int n4 = 0;
        long n5 = 0L;
        long n6 = 0L;
        long n7 = 0L;
        long n8 = 0L;
        long n9 = 0L;
        long n10 = 0L;
        long n11 = 0L;
        int n12 = 0;
        this.ValuesSieved = 0L;
        final double log = Math.log(bigInteger.doubleValue());
        final int n13 = (int)Math.exp(Math.sqrt(log * Math.log(log)) * 0.318);
        int n14 = (int)Math.exp(8.5 + 0.015 * log);
        if (n14 > 30000) {
            n14 = 30000;
        }
        final int n15 = bigInteger.bitLength() / 28 + 1;
        final long[] array3 = new long[n13 + 3];
        final long[] array4 = new long[n13 + 3];
        final byte[] array5 = new byte[n13 + 3];
        final long[] array6 = new long[n13 + 3];
        final int[][] array7 = new int[n15][n13 + 3];
        final int[] array8 = new int[n13 + 3];
        final int[] array9 = new int[n13 + 3];
        final long[] array10 = new long[n15];
        final int[] array11 = new int[n15];
        final int[] array12 = new int[n15];
        final int[] array13 = new int[200];
        final int n16 = (1 << n15 - 1) - 1;
        this.BigNbrToBigInt(bigInteger);
        this.TestNbr[this.NumberLength] = 0L;
        for (int i = this.NumberLength; i >= 0; --i) {
            this.TestNbr2[i] = this.TestNbr[i];
        }
        ++this.NumberLength;
        final int[] array14 = new int[1024];
        for (int j = array14.length - 1; j >= 0; --j) {
            array14[j] = -1;
        }
        this.textAreaContents = "";
        this.StringToLabel = "Factoring ";
        this.insertBigNbr(bigInteger);
        this.addStringToLabel("(" + bigInteger.toString().length() + " digits)");
        final String string = this.textAreaContents + this.StringToLabel + "\n\nSIQS parameters: " + n13 + " primes, sieve limit: " + n14;
        this.lowerTextArea.setText(string + "\nSearching for Knuth-Schroeppel multiplier...");
        double n17 = -10.0;
        array3[0] = 1L;
        array3[1] = 2L;
        final int intValue = bigInteger.and(BigInteger.valueOf(7L)).intValue();
        for (int k = 0; k < array.length; ++k) {
            final int n18 = intValue * array[k] % 8;
            array2[k] = 0.34657359;
            if (n18 == 1) {
                final double[] array15 = array2;
                final int n19 = k;
                array15[n19] *= 4.0;
            }
            if (n18 == 5) {
                final double[] array16 = array2;
                final int n20 = k;
                array16[n20] *= 2.0;
            }
            final double[] array17 = array2;
            final int n21 = k;
            array17[n21] -= Math.log(array[k]) / 2.0;
        }
        long n22 = 3L;
        while (n22 < 10000L) {
            final int n23 = (int)this.modPow((int)this.RemDivBigNbrByLong(this.TestNbr, n22), (n22 - 1L) / 2L, n22);
            final double n24 = n22;
            final double n25 = Math.log(n24) / n24;
            for (int l = 0; l < array.length; ++l) {
                if (array[l] == n22) {
                    final double[] array18 = array2;
                    final int n26 = l;
                    array18[n26] += n25;
                }
                else if (n23 * (int)this.modPow(array[l], (n22 - 1L) / 2L, n22) % n22 == 1L) {
                    final double[] array19 = array2;
                    final int n27 = l;
                    array19[n27] += 2.0 * n25;
                }
            }
            Label_0756: {
                break Label_0756;
                long n28 = 0L;
                do {
                    if (n22 % n28 != 0L) {
                        n28 += 2L;
                    }
                    else {
                        n22 += 2L;
                        n28 = 3L;
                    }
                } while (n28 * n28 <= n22);
            }
        }
        int n29 = 1;
        for (int n30 = 0; n30 < array.length; ++n30) {
            if (array2[n30] > n17) {
                n17 = array2[n30];
                n29 = array[n30];
            }
        }
        this.MultBigNbrByLong(this.TestNbr2, n29, this.TestNbr);
        if (this.TestNbr[this.NumberLength - 1] != 0L || this.TestNbr[this.NumberLength - 2] > 1000000000L) {
            this.TestNbr[this.NumberLength] = 0L;
            ++this.NumberLength;
        }
        final int[][] array20 = new int[n13 * 8][this.NumberLength + 2];
        this.dN = this.TestNbr[this.NumberLength - 2];
        if (this.NumberLength > 1) {
            this.dN += this.TestNbr[this.NumberLength - 3] / 2.147483648E9;
        }
        if (this.NumberLength > 2) {
            this.dN += this.TestNbr[this.NumberLength - 4] / 4.6116860184273879E18;
        }
        final int[][] array21 = new int[(int)(n13 * 1.05 + 40.0)][];
        final long[][] array22 = new long[array21.length][];
        final int[] array23 = new int[array21.length];
        array4[1] = (bigInteger.testBit(0) ? 1 : 0);
        switch ((int)this.TestNbr[0] & 0x7) {
            case 1: {
                array5[1] = 4;
                break;
            }
            case 5: {
                array5[1] = 2;
                break;
            }
            default: {
                array5[1] = 1;
                break;
            }
        }
        int n31;
        if (n29 != 1) {
            array3[2] = n29;
            array5[2] = (byte)Math.round(Math.log(n29) / Math.log(2.0));
            array4[2] = 0L;
            n31 = 3;
        }
        else {
            n31 = 2;
        }
        long n32 = 3L;
        while (n31 < n13) {
            if (this.modPow((int)this.RemDivBigNbrByLong(this.TestNbr, n32), (n32 - 1L) / 2L, n32) == 1L) {
                array3[n31] = n32;
                final int n33 = (int)this.RemDivBigNbrByLong(this.TestNbr, n32);
                long modPow;
                if (n32 % 4L == 3L) {
                    modPow = this.modPow(n33, (n32 + 1L) / 4L, n32);
                }
                else if (n32 % 8L == 5L) {
                    final long modPow2 = this.modPow(n33 * 2, (n32 - 5L) / 8L, n32);
                    modPow = (2 * n33 * modPow2 % n32 * modPow2 - 1L) % n32 * n33 % n32 * modPow2 % n32;
                }
                else {
                    long n34 = n32 - 1L;
                    long n35 = 0L;
                    long n36 = 1L;
                    do {
                        ++n35;
                        n34 /= 2L;
                        n36 *= 2L;
                    } while ((n34 & 0x1L) == 0x0L);
                    final long n37 = n36 / 2L;
                    long n38 = 1L;
                    long modPow3;
                    do {
                        ++n38;
                        modPow3 = this.modPow(n38, n34, n32);
                    } while (this.modPow(modPow3, n37, n32) == 1L);
                    long n39 = modPow3;
                    final long modPow4 = this.modPow(n33, (n34 - 1L) / 2L, n32);
                    long n40 = n33 * modPow4 % n32;
                    long n46;
                    for (long n41 = n40 * modPow4 % n32; n41 != 1L; n41 = n46) {
                        long n42 = 0L;
                        for (long n43 = n41; n43 != 1L; n43 = n43 * n43 % n32, ++n42) {}
                        final long modPow5 = this.modPow(n39, 1 << (int)(n35 - n42 - 1L), n32);
                        final long n44 = modPow5 * modPow5 % n32;
                        n35 = n42;
                        final long n45 = n40 * modPow5 % n32;
                        n46 = n41 * n44 % n32;
                        n39 = n44;
                        n40 = n45;
                    }
                    modPow = n40;
                }
                array4[n31] = modPow;
                array5[n31] = (byte)Math.round(Math.log(n32) / Math.log(2.0));
                ++n31;
            }
            Label_1639: {
                break Label_1639;
                long n47 = 0L;
                do {
                    if (n32 % n47 != 0L) {
                        n47 += 2L;
                    }
                    else {
                        n32 += 2L;
                        n47 = 3L;
                    }
                } while (n47 * n47 <= n32);
            }
        }
        final long n48 = n32;
        final byte[] array24 = new byte[(2 * n14 + 1 > (int)n48) ? (2 * n14 + 5000) : ((int)n48 + 5000)];
        final double doubleValue = bigInteger.doubleValue();
        final String string2 = string + "\nMultiplier: " + n29 + ", factor base: " + n48;
        this.lowerTextArea.setText(string2);
        int n49 = 2;
        int n50;
        for (n50 = 2; n50 < n13; ++n50) {
            n49 *= (int)array3[n50];
            if (n49 > 2 * n14) {
                break;
            }
        }
        final int n51 = n50 + 1;
        final byte b = array5[n50 + 1];
        final byte b2 = (byte)(Math.log(Math.sqrt(doubleValue) * n14 / (n48 * 64L) / array3[n50 + 1]) / Math.log(2.0) + 1.0);
        int n53;
        int n52;
        for (n52 = (n53 = (int)(Math.log(doubleValue) / 3.0)); n53 < n13 && array3[n53] * 2L <= n14; ++n53) {}
        int n54;
        for (n54 = n53; n54 < n13 && array3[n54] <= 2 * n14; ++n54) {}
        final int n55 = n13 - 4;
        final long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            int n56 = 1;
            long n57;
            int n58;
            for (n57 = (long)Math.pow(Math.sqrt(2.0 * doubleValue) / n14, 1.0f / n15), n58 = 2; array3[n58] <= n57; ++n58) {}
            int n59 = n13 / n15 / n15 / 2;
            if (n13 < 500) {
                n59 *= 2;
            }
            final int n60 = n58 - n59 / 2;
            for (int n61 = 0; n61 < n15; ++n61) {
                int n62;
                int n63;
                do {
                    n3 = (1141592621L * n3 + 321435L & 0xFFFFFFFFL);
                    for (n63 = (int)((n3 * n59 >> 32) + n60), n62 = 0; n62 < n61 && array11[n62] != n63 && array11[n62] != n63 + 1; ++n62) {}
                } while (n62 < n61);
                array10[n61] = array3[n63];
                array11[n61] = n63;
            }
            this.LongToBigNbr(array10[0], this.biS);
            for (int n64 = 1; n64 < n15; ++n64) {
                this.MultBigNbrByLong(this.biS, array10[n64], this.biS);
            }
            for (int n65 = 0; n65 < n15; ++n65) {
                long n66 = 1L;
                final long n67 = array10[n65];
                for (int n68 = 0; n68 < n15; ++n68) {
                    if (n65 != n68) {
                        n66 = n66 * array10[n68] % n67;
                    }
                }
                array12[n65] = (int)n66;
                long n69 = array4[array11[n65]] * this.modInv(n66, n67) % n67;
                if (n69 > n67 / 2L) {
                    n69 = n67 - n69;
                }
                this.DivBigNbrByLong(this.biS, n67, this.biR);
                this.MultBigNbrByLong(this.biR, n69, this.aiJS[n65]);
            }
            for (int n70 = 0; n70 < this.NumberLength; ++n70) {
                this.biN[n70] = this.aiJS[0][n70];
            }
            for (int n71 = 1; n71 < n15; ++n71) {
                this.AddBigNbr(this.biN, this.aiJS[n71], this.biN);
            }
            for (int n72 = 1; n72 < n13; ++n72) {
                long n73 = 1L;
                final long n74 = array3[n72];
                for (int n75 = 0; n75 < n15; ++n75) {
                    n73 = n73 * array10[n75] % n74;
                }
                array6[n72] = this.modInv(n73, n74);
                for (int n76 = 0; n76 < n15; ++n76) {
                    array7[n76][n72] = (int)(this.RemDivBigNbrByLong(this.aiJS[n76], n74) * 2L * array6[n72] % n74);
                }
                array8[n72] = (int)((n14 + array6[n72] * (n74 + array4[n72] - this.RemDivBigNbrByLong(this.biN, n74))) % n74 + 100L * n74);
                array9[n72] = (int)(2L * array6[n72] * (n74 - array4[n72]) % n74);
            }
            do {
                if (this.onlyFactoring) {
                    ++this.polynomialsSieved;
                }
                long n77;
                int n78;
                for (n77 = n56, n78 = 0; (n77 & 0x1L) == 0x0L; n77 /= 2L, ++n78) {}
                final boolean b3;
                if (b3 = ((n77 & 0x2L) != 0x0L)) {
                    this.AddBigNbr(this.biN, this.aiJS[n78], this.biN);
                    this.AddBigNbr(this.biN, this.aiJS[n78], this.biN);
                }
                else {
                    this.SubtractBigNbr(this.biN, this.aiJS[n78], this.biN);
                    this.SubtractBigNbr(this.biN, this.aiJS[n78], this.biN);
                }
                final int[] array25 = array7[n78];
                for (int n79 = 0; n79 < n15; ++n79) {
                    final int n80 = array11[n79];
                    final long n81 = array3[n80];
                    final long remDivBigNbrByLong = this.RemDivBigNbrByLong(this.TestNbr, n81 * n81);
                    final long remDivBigNbrByLong2 = this.RemDivBigNbrByLong(this.biN, n81 * n81);
                    array8[n80] = (int)(((remDivBigNbrByLong - remDivBigNbrByLong2 * remDivBigNbrByLong2) / n81 * this.modInv(array12[n79] * remDivBigNbrByLong2 % n81, n81) % n81 + n81 + n14) % n81 + 100L * n81);
                    array9[n80] = -1;
                }
                final int n82 = 2 * n14;
                final int n83 = b3 ? (-array25[1]) : array25[1];
                final int[] array26 = array8;
                final int n84 = 1;
                final int n85 = array26[n84] + n83;
                array26[n84] = n85;
                if (n85 % 2 == 0) {
                    array24[0] = (byte)(array5[1] - b2);
                    array24[1] = (byte)(-b2);
                }
                else {
                    array24[0] = (byte)(-b2);
                    array24[1] = (byte)(array5[1] - b2);
                }
                int n86 = 2;
                int n87 = 2;
                while (true) {
                    final int n88 = (int)array3[n87];
                    int n89 = n86 * n88;
                    if (n82 + 1 < n89) {
                        n89 = n82 + 1;
                    }
                    int n90;
                    for (n90 = n86; n90 * 2 <= n89; n90 *= 2) {
                        System.arraycopy(array24, 0, array24, n90, n90);
                    }
                    System.arraycopy(array24, 0, array24, n90, n89 - n90);
                    if (n89 == n82 + 1) {
                        break;
                    }
                    final byte b4 = array5[n87];
                    final int n91 = b3 ? (-array25[n87]) : array25[n87];
                    final int[] array27 = array8;
                    final int n92 = n87;
                    final int n93 = array27[n92] + n91;
                    array27[n92] = n93;
                    for (int n94 = n93 % n88; n94 < n89; n94 += n88) {
                        final byte[] array28 = array24;
                        final int n95 = n94;
                        array28[n95] += b4;
                    }
                    if (n88 != n29) {
                        for (int n96 = (array8[n87] + array9[n87]) % n88; n96 < n89; n96 += n88) {
                            final byte[] array29 = array24;
                            final int n97 = n96;
                            array29[n97] += b4;
                        }
                    }
                    ++n87;
                    n86 *= n88;
                }
                while (n87 < n51) {
                    final int n98 = (int)array3[n87];
                    final int n99 = b3 ? (-array25[n87]) : array25[n87];
                    final int[] array30 = array8;
                    final int n100 = n87;
                    array30[n100] += n99;
                    ++n87;
                }
                final int n101 = (int)array3[n87 - 1];
                final int n102 = array8[n87 - 1];
                final int n103 = (n102 + array9[n87 - 1]) % n101;
                while (n87 < n52) {
                    final int n104 = (int)array3[n87];
                    final int n105 = b3 ? (-array25[n87]) : array25[n87];
                    final int n106 = n104 + n104;
                    final int n107 = n106 + n104;
                    final int n108 = n107 + n104;
                    final int[] array31 = array8;
                    final int n109 = n87;
                    final int n110 = array31[n109] + n105;
                    array31[n109] = n110;
                    int n111 = n110 % n104;
                    final int n112 = (n111 + array9[n87]) % n104;
                    int n113;
                    if ((n113 = n112 - n111) < 0) {
                        n111 = n112;
                        n113 = -n113;
                    }
                    final int n114 = n113 + n104;
                    final int n115 = n114 + n104;
                    final int n116 = n115 + n104;
                    final byte b5 = array5[n87];
                    int n117 = n82 / n108 * n108 + n111;
                    do {
                        final byte[] array32 = array24;
                        final int n118 = n117;
                        array32[n118] += b5;
                        final byte[] array33 = array24;
                        final int n119 = n117 + n104;
                        array33[n119] += b5;
                        final byte[] array34 = array24;
                        final int n120 = n117 + n106;
                        array34[n120] += b5;
                        final byte[] array35 = array24;
                        final int n121 = n117 + n107;
                        array35[n121] += b5;
                        final byte[] array36 = array24;
                        final int n122 = n117 + n113;
                        array36[n122] += b5;
                        final byte[] array37 = array24;
                        final int n123 = n117 + n114;
                        array37[n123] += b5;
                        final byte[] array38 = array24;
                        final int n124 = n117 + n115;
                        array38[n124] += b5;
                        final byte[] array39 = array24;
                        final int n125 = n117 + n116;
                        array39[n125] += b5;
                    } while ((n117 -= n108) >= 0);
                    ++n87;
                }
                while (n87 < n53) {
                    final int n126 = (int)array3[n87];
                    final int n127 = b3 ? (-array25[n87]) : array25[n87];
                    final int n128 = n82 - 4 * n126;
                    final int n129 = n126 + n126;
                    final int n130 = n129 + n126;
                    final int n131 = n129 + n129;
                    final byte b6 = array5[n87];
                    final int[] array40 = array8;
                    final int n132 = n87;
                    final int n133 = array40[n132] + n127;
                    array40[n132] = n133;
                    int n134;
                    for (n134 = n133 % n126; n134 <= n128; n134 += n131) {
                        final byte[] array41 = array24;
                        final int n135 = n134;
                        array41[n135] += b6;
                        final byte[] array42 = array24;
                        final int n136 = n134 + n126;
                        array42[n136] += b6;
                        final byte[] array43 = array24;
                        final int n137 = n134 + n129;
                        array43[n137] += b6;
                        final byte[] array44 = array24;
                        final int n138 = n134 + n130;
                        array44[n138] += b6;
                    }
                    while (n134 <= n82) {
                        final byte[] array45 = array24;
                        final int n139 = n134;
                        array45[n139] += b6;
                        n134 += n126;
                    }
                    if (array9[n87] >= 0) {
                        int n140;
                        for (n140 = (n134 + array9[n87]) % n126; n140 <= n128; n140 += n131) {
                            final byte[] array46 = array24;
                            final int n141 = n140;
                            array46[n141] += b6;
                            final byte[] array47 = array24;
                            final int n142 = n140 + n126;
                            array47[n142] += b6;
                            final byte[] array48 = array24;
                            final int n143 = n140 + n129;
                            array48[n143] += b6;
                            final byte[] array49 = array24;
                            final int n144 = n140 + n130;
                            array49[n144] += b6;
                        }
                        while (n140 <= n82) {
                            final byte[] array50 = array24;
                            final int n145 = n140;
                            array50[n145] += b6;
                            n140 += n126;
                        }
                    }
                    ++n87;
                }
                while (n87 < n54) {
                    final int n146 = (int)array3[n87];
                    final byte b7 = array5[n87];
                    final int n147 = b3 ? (-array25[n87]) : array25[n87];
                    final int[] array51 = array8;
                    final int n148 = n87;
                    final int n149 = array51[n148] + n147;
                    array51[n148] = n149;
                    int n150;
                    for (n150 = n149 % n146; n150 <= n82; n150 += n146) {
                        final byte[] array52 = array24;
                        final int n151 = n150;
                        array52[n151] += b7;
                    }
                    for (int n152 = (n150 + array9[n87]) % n146; n152 <= n82; n152 += n146) {
                        final byte[] array53 = array24;
                        final int n153 = n152;
                        array53[n153] += b7;
                    }
                    ++n87;
                }
                if (b3) {
                    while (n87 < n55) {
                        final byte b8 = array5[n87];
                        final int n154 = (int)array3[n87];
                        final int n155 = -array25[n87];
                        final int[] array54 = array8;
                        final int n156 = n87;
                        final int n157 = array54[n156] + n155;
                        array54[n156] = n157;
                        final int n158;
                        if ((n158 = n157 % n154) < n82) {
                            final byte[] array55 = array24;
                            final int n159 = n158;
                            array55[n159] += b8;
                        }
                        final int n160;
                        if ((n160 = (n158 + array9[n87]) % n154) < n82) {
                            final byte[] array56 = array24;
                            final int n161 = n160;
                            array56[n161] += b8;
                        }
                        final int n162 = (int)array3[++n87];
                        final int n163 = -array25[n87];
                        final int[] array57 = array8;
                        final int n164 = n87;
                        final int n165 = array57[n164] + n163;
                        array57[n164] = n165;
                        final int n166;
                        if ((n166 = n165 % n162) < n82) {
                            final byte[] array58 = array24;
                            final int n167 = n166;
                            array58[n167] += b8;
                        }
                        final int n168;
                        if ((n168 = (n166 + array9[n87]) % n162) < n82) {
                            final byte[] array59 = array24;
                            final int n169 = n168;
                            array59[n169] += b8;
                        }
                        final int n170 = (int)array3[++n87];
                        final int n171 = -array25[n87];
                        final int[] array60 = array8;
                        final int n172 = n87;
                        final int n173 = array60[n172] + n171;
                        array60[n172] = n173;
                        final int n174;
                        if ((n174 = n173 % n170) < n82) {
                            final byte[] array61 = array24;
                            final int n175 = n174;
                            array61[n175] += b8;
                        }
                        final int n176;
                        if ((n176 = (n174 + array9[n87]) % n170) < n82) {
                            final byte[] array62 = array24;
                            final int n177 = n176;
                            array62[n177] += b8;
                        }
                        final int n178 = (int)array3[++n87];
                        final int n179 = -array25[n87];
                        final int[] array63 = array8;
                        final int n180 = n87;
                        final int n181 = array63[n180] + n179;
                        array63[n180] = n181;
                        final int n182;
                        if ((n182 = n181 % n178) < n82) {
                            final byte[] array64 = array24;
                            final int n183 = n182;
                            array64[n183] += b8;
                        }
                        final int n184;
                        if ((n184 = (n182 + array9[n87]) % n178) < n82) {
                            final byte[] array65 = array24;
                            final int n185 = n184;
                            array65[n185] += b8;
                        }
                        ++n87;
                    }
                    while (n87 < n13) {
                        final byte b9 = array5[n87];
                        final int n186 = (int)array3[n87];
                        final int n187 = -array25[n87];
                        final int[] array66 = array8;
                        final int n188 = n87;
                        final int n189 = array66[n188] + n187;
                        array66[n188] = n189;
                        final int n190;
                        if ((n190 = n189 % n186) < n82) {
                            final byte[] array67 = array24;
                            final int n191 = n190;
                            array67[n191] += b9;
                        }
                        final int n192;
                        if ((n192 = (n190 + array9[n87]) % n186) < n82) {
                            final byte[] array68 = array24;
                            final int n193 = n192;
                            array68[n193] += b9;
                        }
                        ++n87;
                    }
                }
                else {
                    while (n87 < n55) {
                        final byte b10 = array5[n87];
                        final int n194 = (int)array3[n87];
                        final int n195 = array25[n87];
                        final int[] array69 = array8;
                        final int n196 = n87;
                        final int n197 = array69[n196] + n195;
                        array69[n196] = n197;
                        final int n198;
                        if ((n198 = n197 % n194) < n82) {
                            final byte[] array70 = array24;
                            final int n199 = n198;
                            array70[n199] += b10;
                        }
                        final int n200;
                        if ((n200 = (n198 + array9[n87]) % n194) < n82) {
                            final byte[] array71 = array24;
                            final int n201 = n200;
                            array71[n201] += b10;
                        }
                        final int n202 = (int)array3[++n87];
                        final int n203 = array25[n87];
                        final int[] array72 = array8;
                        final int n204 = n87;
                        final int n205 = array72[n204] + n203;
                        array72[n204] = n205;
                        final int n206;
                        if ((n206 = n205 % n202) < n82) {
                            final byte[] array73 = array24;
                            final int n207 = n206;
                            array73[n207] += b10;
                        }
                        final int n208;
                        if ((n208 = (n206 + array9[n87]) % n202) < n82) {
                            final byte[] array74 = array24;
                            final int n209 = n208;
                            array74[n209] += b10;
                        }
                        final int n210 = (int)array3[++n87];
                        final int n211 = array25[n87];
                        final int[] array75 = array8;
                        final int n212 = n87;
                        final int n213 = array75[n212] + n211;
                        array75[n212] = n213;
                        final int n214;
                        if ((n214 = n213 % n210) < n82) {
                            final byte[] array76 = array24;
                            final int n215 = n214;
                            array76[n215] += b10;
                        }
                        final int n216;
                        if ((n216 = (n214 + array9[n87]) % n210) < n82) {
                            final byte[] array77 = array24;
                            final int n217 = n216;
                            array77[n217] += b10;
                        }
                        final int n218 = (int)array3[++n87];
                        final int n219 = array25[n87];
                        final int[] array78 = array8;
                        final int n220 = n87;
                        final int n221 = array78[n220] + n219;
                        array78[n220] = n221;
                        final int n222;
                        if ((n222 = n221 % n218) < n82) {
                            final byte[] array79 = array24;
                            final int n223 = n222;
                            array79[n223] += b10;
                        }
                        final int n224;
                        if ((n224 = (n222 + array9[n87]) % n218) < n82) {
                            final byte[] array80 = array24;
                            final int n225 = n224;
                            array80[n225] += b10;
                        }
                        ++n87;
                    }
                    while (n87 < n13) {
                        final byte b11 = array5[n87];
                        final int n226 = (int)array3[n87];
                        final int[] array81 = array8;
                        final int n227 = n87;
                        final int n228 = array81[n227] + array25[n87];
                        array81[n227] = n228;
                        final int n229;
                        if ((n229 = n228 % n226) < n82) {
                            final byte[] array82 = array24;
                            final int n230 = n229;
                            array82[n230] += b11;
                        }
                        int n231;
                        if ((n231 = n229 + array9[n87]) > n226) {
                            n231 -= n226;
                        }
                        if (n231 < n82) {
                            final byte[] array83 = array24;
                            final int n232 = n231;
                            array83[n232] += b11;
                        }
                        ++n87;
                    }
                }
                this.ValuesSieved += 2 * n14;
                int n233 = 2 * n14 + 1;
                do {
                    if (array24[--n233] >= 0 && array24[n233] < 64 && (array24[n233] >= b || (n233 - n102) % n101 == 0 || (n233 - n103) % n101 == 0)) {
                        ++this.trialDivisions;
                        this.MultBigNbrByLong(this.biS, n233 - n14, this.biT);
                        this.AddBigNbr(this.biT, this.biN, this.biT);
                        this.MultBigNbr(this.biT, this.biT, this.biR);
                        this.SubtractBigNbr(this.biR, this.TestNbr, this.biR);
                        for (int n234 = 0; n234 < this.NumberLength; ++n234) {
                            this.biT[n234] = this.biR[n234];
                        }
                        if (n29 > 1) {
                            while (this.RemDivBigNbrByLong(this.biR, n29 * n29) == 0L) {
                                this.DivBigNbrByLong(this.biR, n29 * n29, this.biR);
                            }
                        }
                        final int numberLength = this.NumberLength;
                        boolean b12 = true;
                        if (this.biR[this.NumberLength - 1] >= 1073741824L) {
                            b12 = false;
                            this.ChSignBigNbr(this.biR);
                        }
                        for (int n235 = 0; n235 < n15; ++n235) {
                            this.DivBigNbrByLong(this.biR, array10[n235], this.biR);
                            if (this.biR[this.NumberLength - 1] == 0L && this.biR[this.NumberLength - 2] < 1073741824L) {
                                --this.NumberLength;
                            }
                        }
                        switch (this.NumberLength) {
                            case 7: {
                                n11 = this.biR[6];
                            }
                            case 6: {
                                n10 = this.biR[5];
                            }
                            case 5: {
                                n9 = this.biR[4];
                            }
                            case 4: {
                                n8 = this.biR[3];
                            }
                            case 3: {
                                n7 = this.biR[2];
                            }
                            case 1:
                            case 2: {
                                n6 = this.biR[1];
                                n5 = this.biR[0];
                                break;
                            }
                        }
                        int n236 = 0;
                        long n237;
                        if (this.NumberLength <= 2) {
                            n237 = (n6 << 31 | n5);
                            for (int n238 = 1; n238 < n13; ++n238) {
                                for (long n239 = array3[n238]; n237 % n239 == 0L; n237 /= n239) {}
                            }
                        }
                        else {
                            n237 = 0L;
                            for (int n240 = 1; n240 < n13; ++n240) {
                                while (true) {
                                    final long n241 = array3[n240];
                                    long n242 = 0L;
                                    switch (this.NumberLength) {
                                        case 7: {
                                            n242 = (n11 + (n242 << 31)) % n241;
                                        }
                                        case 6: {
                                            n242 = (n10 + (n242 << 31)) % n241;
                                        }
                                        case 5: {
                                            n242 = (n9 + (n242 << 31)) % n241;
                                        }
                                        case 4: {
                                            n242 = (n8 + (n242 << 31)) % n241;
                                        }
                                        case 3: {
                                            n242 = (n5 + ((n6 + ((n7 + (n242 << 31)) % n241 << 31)) % n241 << 31)) % n241;
                                            break;
                                        }
                                    }
                                    if (n242 != 0L) {
                                        break;
                                    }
                                    switch (this.NumberLength) {
                                        case 7: {
                                            final long n243 = n11 + (n242 << 31);
                                            n242 = n243 % n241;
                                            n11 = n243 / n241;
                                        }
                                        case 6: {
                                            final long n244 = n10 + (n242 << 31);
                                            n242 = n244 % n241;
                                            n10 = n244 / n241;
                                        }
                                        case 5: {
                                            final long n245 = n9 + (n242 << 31);
                                            n242 = n245 % n241;
                                            n9 = n245 / n241;
                                        }
                                        case 4: {
                                            final long n246 = n8 + (n242 << 31);
                                            n242 = n246 % n241;
                                            n8 = n246 / n241;
                                        }
                                        case 3: {
                                            final long n247 = n7 + (n242 << 31);
                                            final long n248 = n247 % n241;
                                            n7 = n247 / n241;
                                            n237 = n6 + (n248 << 31);
                                            n6 = n237 / n241;
                                            n5 = (n5 + (n237 % n241 << 31)) / n241;
                                            break;
                                        }
                                    }
                                    switch (this.NumberLength) {
                                        case 7: {
                                            n12 = ((n11 == 0L && n10 < 1073741824L) ? 1 : 0);
                                            break;
                                        }
                                        case 6: {
                                            n12 = ((n10 == 0L && n9 < 1073741824L) ? 1 : 0);
                                            break;
                                        }
                                        case 5: {
                                            n12 = ((n9 == 0L && n8 < 1073741824L) ? 1 : 0);
                                            break;
                                        }
                                        case 4: {
                                            n12 = ((n8 == 0L && n7 < 1073741824L) ? 1 : 0);
                                            break;
                                        }
                                        case 3: {
                                            n12 = ((n7 == 0L && n6 < 1073741824L) ? 1 : 0);
                                            break;
                                        }
                                    }
                                    if (n12 == 0) {
                                        continue;
                                    }
                                    --this.NumberLength;
                                    if (this.NumberLength == 2) {
                                        n237 = (n6 << 31 | n5);
                                        int n249 = (int)Math.floor(Math.sqrt(n237));
                                        while (n240 < n13) {
                                            long n250;
                                            for (n250 = array3[n240]; n237 % n250 == 0L; n237 /= n250, n249 = (int)Math.floor(Math.sqrt(n237))) {}
                                            if (n250 > n249) {
                                                n240 = n13 - 1;
                                                if (n237 <= array3[n240]) {
                                                    n237 = 1L;
                                                    break;
                                                }
                                                break;
                                            }
                                            else {
                                                ++n240;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        final int numberLength2 = this.NumberLength;
                        this.NumberLength = numberLength;
                        if (numberLength2 == 2 && n237 == 1L) {
                            if (!b12) {
                                array13[n236++] = 0;
                            }
                            this.LongToBigNbr(1L, this.biR);
                            while (this.RemDivBigNbrByLong(this.biT, 9L) == 0L) {
                                this.DivBigNbrByLong(this.biT, 9L, this.biT);
                                if (this.RemDivBigNbrByLong(this.TestNbr, 3L) == 0L) {
                                    this.DivBigNbrByLong(this.biU, 3L, this.biU);
                                }
                                else {
                                    this.MultBigNbrByLong(this.biR, 3L, this.biR);
                                }
                            }
                            int n251 = 5;
                            while (true) {
                                if (this.RemDivBigNbrByLong(this.biT, n251 * n251) != 0L) {
                                    n251 += 2;
                                    while (this.RemDivBigNbrByLong(this.biR, n251 * n251) == 0L) {
                                        this.DivBigNbrByLong(this.biR, n251 * n251, this.biR);
                                        if (this.RemDivBigNbrByLong(this.TestNbr, n251) == 0L) {
                                            this.DivBigNbrByLong(this.biU, n251, this.biU);
                                        }
                                        else {
                                            this.MultBigNbrByLong(this.biR, n251, this.biR);
                                        }
                                    }
                                    n251 += 4;
                                    if (n251 >= 100) {
                                        break;
                                    }
                                    continue;
                                }
                                else {
                                    this.DivBigNbrByLong(this.biT, n251 * n251, this.biT);
                                    if (this.RemDivBigNbrByLong(this.TestNbr, n251) == 0L) {
                                        this.DivBigNbrByLong(this.biU, n251, this.biU);
                                    }
                                    else {
                                        this.MultBigNbrByLong(this.biR, n251, this.biR);
                                    }
                                }
                            }
                            this.MultBigNbrByLong(this.biS, n233 - n14, this.biU);
                            this.AddBigNbr(this.biU, this.biN, this.biU);
                            for (int n252 = 1; n252 < n13; ++n252) {
                                int n253 = 0;
                                final long n254 = array3[n252];
                                while (this.RemDivBigNbrByLong(this.biT, n254) == 0L) {
                                    this.DivBigNbrByLong(this.biT, n254, this.biT);
                                    n253 = 1 - n253;
                                    if (n253 == 0) {
                                        if (this.RemDivBigNbrByLong(this.TestNbr, n254) == 0L) {
                                            this.DivBigNbrByLong(this.biU, n254, this.biU);
                                        }
                                        else {
                                            this.MultBigNbrByLong(this.biR, n254, this.biR);
                                        }
                                    }
                                }
                                if (n253 != 0) {
                                    array13[n236++] = n252;
                                }
                            }
                            if (!this.InsertNewRelation(this.biR, this.biT, this.biU, n236, array21, array13, n2, array22)) {
                                continue;
                            }
                            ++this.smoothsFound;
                            ++n2;
                            this.ShowSIQSStatus(n2, array21.length, currentTimeMillis);
                            if (n2 != array21.length) {
                                continue;
                            }
                            final int eraseSingletons = this.EraseSingletons(array21, array22, array23);
                            if (eraseSingletons != 0) {
                                this.lowerTextArea.setText(string2 + "\n" + eraseSingletons + " singletons discarded");
                                n2 -= eraseSingletons;
                            }
                            else {
                                this.lowerTextArea.setText(string2 + "\nSolving congruence matrix using Block Lanczos algorithm");
                                if (this.LinearAlgebraPhase(n13, array21, array3, this.biT, this.biR, this.biU, array23, array22)) {
                                    return this.BigIntToBigNbr(this.biT);
                                }
                                this.lowerTextArea.setText(string2 + "\nLinear dependences were found. Discarding 50 congruences...");
                                n2 -= 50;
                            }
                        }
                        else {
                            if (numberLength2 != 2 || n237 >= 64L * n48) {
                                continue;
                            }
                            ++this.totalPartials;
                            if (!b12) {
                                array13[n236++] = 0;
                            }
                            int eraseSingletons2 = array14[(int)(n237 & 0x7FEL) / 2];
                            int n255 = -1;
                            while (eraseSingletons2 >= 0) {
                                if ((int)n237 == array20[eraseSingletons2][0]) {
                                    for (int n256 = 0; n256 < this.NumberLength; ++n256) {
                                        this.biV[n256] = array20[eraseSingletons2][n256 + 2];
                                    }
                                    this.MultBigNbr(this.biV, this.biV, this.biT);
                                    this.SubtractBigNbr(this.biT, this.TestNbr, this.biT);
                                    final long n257 = array20[eraseSingletons2][0];
                                    final long remDivBigNbrByLong3 = this.RemDivBigNbrByLong(this.TestNbr, n257);
                                    long n258 = n257;
                                    long n259 = remDivBigNbrByLong3;
                                    while (n258 != 0L) {
                                        final long n260 = n259 % n258;
                                        n259 = n258;
                                        n258 = n260;
                                    }
                                    this.LongToBigNbr(n257, this.biR);
                                    if (n257 < 0L) {
                                        this.AddBigNbr(this.biR, this.TestNbr, this.biR);
                                    }
                                    if (n259 < 0L) {
                                        n259 = -n259;
                                    }
                                    if (n259 != 1L) {
                                        this.DivBigNbrByLong(this.TestNbr, (n259 < 0L) ? (-n259) : n259, this.biU);
                                        this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                    }
                                    if (n29 > 1) {
                                        while (this.RemDivBigNbrByLong(this.biT, n29 * n29) == 0L) {
                                            this.DivBigNbrByLong(this.biT, n29 * n29, this.biT);
                                            --this.NumberLength;
                                            this.MultBigNbrByLongModN(this.biR, n29, this.biR);
                                            ++this.NumberLength;
                                            if (this.RemDivBigNbrByLong(this.TestNbr, n29) == 0L) {
                                                this.DivBigNbrByLong(this.TestNbr, n29, this.biU);
                                                this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                            }
                                        }
                                    }
                                    int n261 = 0;
                                    for (int n262 = 1; n262 < n13; ++n262) {
                                        final long n263 = array3[n262];
                                        int n264 = 0;
                                        while (this.RemDivBigNbrByLong(this.biT, n263) == 0L) {
                                            n264 = 1 - n264;
                                            this.DivBigNbrByLong(this.biT, n263, this.biT);
                                            if (n264 == 0) {
                                                --this.NumberLength;
                                                this.MultBigNbrByLongModN(this.biR, n263, this.biR);
                                                ++this.NumberLength;
                                                if (this.RemDivBigNbrByLong(this.TestNbr, n263) != 0L) {
                                                    continue;
                                                }
                                                this.DivBigNbrByLong(this.TestNbr, n263, this.biU);
                                                this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                            }
                                        }
                                        if (n264 != 0) {
                                            array13[n261++] = n262;
                                        }
                                    }
                                    this.MultBigNbrByLong(this.biS, n233 - n14, this.biT);
                                    this.AddBigNbr(this.biT, this.biN, this.biW);
                                    this.MultBigNbr(this.biW, this.biW, this.biT);
                                    this.SubtractBigNbr(this.biT, this.TestNbr, this.biT);
                                    if (n29 > 1) {
                                        while (this.RemDivBigNbrByLong(this.biT, n29 * n29) == 0L) {
                                            this.DivBigNbrByLong(this.biT, n29 * n29, this.biT);
                                            --this.NumberLength;
                                            this.MultBigNbrByLongModN(this.biR, n29, this.biR);
                                            ++this.NumberLength;
                                            if (this.RemDivBigNbrByLong(this.TestNbr, n29) == 0L) {
                                                this.DivBigNbrByLong(this.TestNbr, n29, this.biU);
                                                this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                            }
                                        }
                                    }
                                    for (int n265 = 1; n265 < n13; ++n265) {
                                        int n266 = 0;
                                        final long n267 = array3[n265];
                                        while (this.RemDivBigNbrByLong(this.biT, n267) == 0L) {
                                            n266 = 1 - n266;
                                            this.DivBigNbrByLong(this.biT, n267, this.biT);
                                            if (n266 == 0) {
                                                --this.NumberLength;
                                                this.MultBigNbrByLongModN(this.biR, n267, this.biR);
                                                ++this.NumberLength;
                                                if (this.RemDivBigNbrByLong(this.TestNbr, n267) != 0L) {
                                                    continue;
                                                }
                                                this.DivBigNbrByLong(this.TestNbr, n267, this.biU);
                                                this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                            }
                                        }
                                        if (n266 != 0) {
                                            int n268;
                                            for (n268 = 0; n268 < n261 && n265 > array13[n268]; ++n268) {}
                                            if (n268 < n261 && n265 == array13[n268]) {
                                                final long n269 = array3[array13[n268]];
                                                --this.NumberLength;
                                                this.MultBigNbrByLongModN(this.biR, n269, this.biR);
                                                ++this.NumberLength;
                                                if (this.RemDivBigNbrByLong(this.TestNbr, n269) == 0L) {
                                                    this.DivBigNbrByLong(this.TestNbr, n269, this.biU);
                                                    this.AddBigNbrModN(this.biR, this.biU, this.biR);
                                                }
                                                for (int n270 = n268 + 1; n270 < n261; ++n270) {
                                                    array13[n270 - 1] = array13[n270];
                                                }
                                                --n261;
                                            }
                                            else {
                                                for (int n271 = n261; n271 > n268; --n271) {
                                                    array13[n271] = array13[n271 - 1];
                                                }
                                                array13[n268] = n265;
                                                ++n261;
                                            }
                                        }
                                    }
                                    if ((this.biV[this.NumberLength - 1] & 0x40000000L) != 0x0L) {
                                        this.AddBigNbr(this.biV, this.TestNbr, this.biV);
                                    }
                                    if ((this.biW[this.NumberLength - 1] & 0x40000000L) != 0x0L) {
                                        this.AddBigNbr(this.biW, this.TestNbr, this.biW);
                                    }
                                    --this.NumberLength;
                                    this.MultBigNbrModN(this.biV, this.biW, this.biU);
                                    ++this.NumberLength;
                                    if (!this.InsertNewRelation(this.biR, this.biT, this.biU, n261, array21, array13, n2, array22)) {
                                        eraseSingletons2 = -2;
                                        break;
                                    }
                                    ++this.partialsFound;
                                    ++n2;
                                    this.ShowSIQSStatus(n2, array21.length, currentTimeMillis);
                                    if (n2 != array21.length) {
                                        break;
                                    }
                                    eraseSingletons2 = this.EraseSingletons(array21, array22, array23);
                                    if (eraseSingletons2 != 0) {
                                        this.lowerTextArea.setText(string2 + "\n" + eraseSingletons2 + " singletons discarded");
                                        n2 -= eraseSingletons2;
                                        break;
                                    }
                                    this.lowerTextArea.setText(string2 + "\nSolving congruence matrix using Block Lanczos algorithm");
                                    if (this.LinearAlgebraPhase(n13, array21, array3, this.biT, this.biR, this.biU, array23, array22)) {
                                        return this.BigIntToBigNbr(this.biT);
                                    }
                                    this.lowerTextArea.setText(string2 + "\nLinear dependences were found. Discarding 50 congruences...");
                                    n2 -= 50;
                                    break;
                                }
                                else {
                                    n255 = eraseSingletons2;
                                    eraseSingletons2 = array20[eraseSingletons2][1];
                                }
                            }
                            if (eraseSingletons2 != -1 || n4 >= array20.length) {
                                continue;
                            }
                            if (n255 >= 0) {
                                array20[n255][1] = n4;
                            }
                            else {
                                array14[(int)(n237 & 0x7FEL) / 2] = n4;
                            }
                            array20[n4][0] = (int)n237;
                            array20[n4][1] = -1;
                            this.MultBigNbrByLong(this.biS, n233 - n14, this.biT);
                            this.AddBigNbr(this.biT, this.biN, this.biT);
                            for (int n272 = 0; n272 < this.NumberLength; ++n272) {
                                array20[n4][n272 + 2] = (int)this.biT[n272];
                            }
                            ++n4;
                        }
                    }
                } while (n233 > 0);
            } while (++n56 < n16);
            ++n;
        }
    }
    
    int[] BlockLanczos(final int[][] array) {
        final int length = array.length;
        final int[] array2 = new int[32];
        final int[] array3 = new int[32];
        final int[] array4 = new int[32];
        int[] array5 = new int[32];
        int[] array6 = new int[32];
        int[] array7 = new int[32];
        int[] array8 = new int[32];
        int[] array9 = new int[32];
        int[] array10 = new int[32];
        int[] array11 = new int[32];
        int[] array12 = new int[32];
        final int[] array13 = new int[length];
        final int[] array14 = new int[32];
        final int[] array15 = new int[64];
        int[] array16 = new int[length];
        int[] array17 = new int[length];
        int[] array18 = new int[length];
        final int[] array19 = new int[length];
        int[] array20 = new int[length];
        final int[] array21 = new int[32];
        int[] array22 = new int[32];
        int n = 0;
        int n2 = 0;
        int n3 = -1;
        long n4 = 123456789L;
        for (int i = array19.length - 1; i >= 0; --i) {
            array19[i] = (int)n4;
            final long n5 = (n4 * 62089911L + 54325442L) % 2147483647L;
            final int[] array23 = array19;
            final int n6 = i;
            array23[n6] += (int)(n5 * 6543265L);
            n4 = (n5 * 62089911L + 54325442L) % 2147483647L;
        }
        this.MultiplyAByMatrix(array, array19, array20, array16);
        this.MatrTranspMult(array16, array16, array8);
        while (true) {
            final int n7 = n3;
            ++n2;
            this.MultiplyAByMatrix(array, array16, array20, array13);
            this.MatrTranspMult(array16, array13, array11);
            int n8;
            for (n8 = array11.length - 1; n8 >= 0 && array11[n8] == 0; --n8) {}
            if (n8 < 0) {
                break;
            }
            final int[] array24 = array7;
            array7 = array6;
            array6 = array5;
            array5 = array24;
            int n9 = 1;
            int n10 = 31;
            do {
                array2[n10] = array11[n10];
                array5[n10] = n9;
                n9 *= 2;
            } while (--n10 >= 0);
            int n11 = 31;
            int n12 = 31;
            int j = 1;
            do {
                if ((n7 & j) != 0x0) {
                    array3[n11] = n12;
                    array4[n11] = j;
                    --n11;
                }
                --n12;
                j *= 2;
            } while (j != 0);
            int n13 = 31;
            int k = 1;
            do {
                if ((n7 & k) == 0x0) {
                    array3[n11] = n13;
                    array4[n11] = k;
                    --n11;
                }
                --n13;
                k *= 2;
            } while (k != 0);
            n3 = 0;
            int n14 = 0;
            do {
                final int n15 = array3[n14];
                int n16;
                int n17;
                for (n16 = array4[n14], n17 = n14; n17 < 32 && (array2[array3[n17]] & n16) == 0x0; ++n17) {}
                if (n17 < 32) {
                    final int n18 = array3[n17];
                    final int n19 = array5[n18];
                    array5[n18] = array5[n15];
                    array5[n15] = n19;
                    final int n20 = array2[n18];
                    array2[n18] = array2[n15];
                    array2[n15] = n20;
                    n3 |= n16;
                    ++n;
                    int n21 = 31;
                    do {
                        if (n21 != n15 && (array2[n21] & n16) != 0x0) {
                            final int[] array25 = array5;
                            final int n22 = n21;
                            array25[n22] ^= n19;
                            final int[] array26 = array2;
                            final int n23 = n21;
                            array26[n23] ^= n20;
                        }
                    } while (--n21 >= 0);
                }
                else {
                    int n24;
                    for (n24 = n14; n24 < 32 && (array5[array3[n24]] & n16) == 0x0; ++n24) {}
                    final int n25 = array3[n24];
                    final int n26 = array5[n25];
                    array5[n25] = array5[n15];
                    array5[n15] = n26;
                    final int n27 = array2[n25];
                    array2[n25] = array2[n15];
                    array2[n15] = n27;
                    int n28 = 31;
                    do {
                        if ((array5[n28] & n16) != 0x0) {
                            final int[] array27 = array5;
                            final int n29 = n28;
                            array27[n29] ^= n26;
                            final int[] array28 = array2;
                            final int n30 = n28;
                            array28[n30] ^= n27;
                        }
                    } while (--n28 >= 0);
                }
            } while (++n14 < 32);
            if (n7 != -1) {
                this.MatrixMultiplication(array12, array6, array22);
                int n31 = 31;
                int l = 1;
                do {
                    final int[] array29 = array22;
                    final int n32 = n31;
                    array29[n32] ^= l;
                    --n31;
                    l *= 2;
                } while (l != 0);
                this.MatrixMultiplication(array7, array22, array21);
                this.MatrixMultiplication(array21, array14, array4);
                this.MatrMultBySSt(array4, n3, array4);
            }
            this.MatrixMultiplication(array6, array11, array3);
            this.MatrMultBySSt(array3, n3, array3);
            this.MatrTranspMult(array13, array13, array21);
            this.MatrMultBySSt(array21, n3, array21);
            this.MatrixAddition(array21, array11, array14);
            this.MatrixMultiplication(array5, array14, array2);
            int n33 = 31;
            int n34 = 1;
            do {
                final int[] array30 = array2;
                final int n35 = n33;
                array30[n35] ^= n34;
                --n33;
                n34 *= 2;
            } while (n34 != 0);
            this.MatrixMultiplication(array5, array8, array21);
            this.MatrixMultAdd(array16, array21, array19);
            this.MatrMultBySSt(array13, n3, array20);
            this.MatrixMultAdd(array16, array2, array20);
            this.MatrixMultAdd(array17, array3, array20);
            if (n7 != -1) {
                this.MatrixMultAdd(array18, array4, array20);
            }
            if (n2 > 3) {
                this.MatrTranspMult(array2, array8, array21);
                this.MatrTranspMult(array3, array9, array22);
                this.MatrixAddition(array21, array22, array22);
                if (n7 != -1) {
                    this.MatrTranspMult(array4, array10, array21);
                    this.MatrixAddition(array21, array22, array22);
                }
            }
            else if (n2 == 1) {
                this.MatrTranspMult(array20, array16, array22);
            }
            else {
                this.MatrTranspMult(array20, array17, array22);
            }
            final int[] array31 = array18;
            array18 = array17;
            array17 = array16;
            array16 = array20;
            array20 = array31;
            final int[] array32 = array10;
            array10 = array9;
            array9 = array8;
            array8 = array22;
            array22 = array32;
            final int[] array33 = array12;
            array12 = array11;
            array11 = array33;
        }
        for (int n36 = array.length - 1; n36 >= 0; --n36) {
            array17[n36] = (array18[n36] = 0);
        }
        for (int n37 = array.length - 1; n37 >= 0; --n37) {
            final int[] array34 = array[n37];
            for (int n38 = array34.length - 1; n38 >= 0; --n38) {
                final int[] array35 = array17;
                final int n39 = array34[n38];
                array35[n39] ^= array19[n37];
                final int[] array36 = array18;
                final int n40 = array34[n38];
                array36[n40] ^= array16[n37];
            }
        }
        int n41 = 64;
        int n42 = 0;
        while (n42 < n41) {
            for (int n43 = n42; n43 < n41; ++n43) {
                final int[] array37 = (n43 >= 32) ? array17 : array18;
                final int n44 = 1 << 31 - (n43 & 0x1F);
                array15[n43] = -1;
                for (int n45 = 0; n45 < array17.length; ++n45) {
                    if ((array37[n45] & n44) != 0x0) {
                        array15[n43] = n45;
                        break;
                    }
                }
            }
            for (int n46 = n42; n46 < n41; ++n46) {
                if (array15[n46] < 0) {
                    this.colexchange(array19, array16, array17, array18, n42, n46);
                    array15[n46] = array15[n42];
                    array15[n42] = -1;
                    ++n42;
                }
            }
            if (n42 == n41) {
                break;
            }
            int n47 = array15[n42];
            int n48 = n42;
            for (int n49 = n42 + 1; n49 < n41; ++n49) {
                if (array15[n49] < n47) {
                    n47 = array15[n49];
                    n48 = n49;
                }
            }
            int n50 = 0;
            for (int n51 = n42; n51 < n41; ++n51) {
                if (array15[n51] == n47) {
                    ++n50;
                }
            }
            if (n50 > 1) {
                for (int n52 = n48 + 1; n52 < n41; ++n52) {
                    if (array15[n52] == n47) {
                        this.coladd(array19, array16, array17, array18, n48, n52);
                    }
                }
            }
            else {
                --n41;
                this.colexchange(array19, array16, array17, array18, n48, n41);
            }
        }
        for (int n53 = 0; n53 < n41; ++n53) {
            for (int n54 = n53; n54 < n41; ++n54) {
                final int[] array38 = (n54 >= 32) ? array19 : array16;
                final int n55 = 1 << 31 - (n54 & 0x1F);
                array15[n54] = -1;
                for (int n56 = 0; n56 < array17.length; ++n56) {
                    if ((array38[n56] & n55) != 0x0) {
                        array15[n54] = n56;
                        break;
                    }
                }
            }
            for (int n57 = n53; n57 < n41; ++n57) {
                if (array15[n57] < 0) {
                    --n41;
                    this.colexchange(array19, array16, array17, array18, n41, n57);
                    array15[n57] = array15[n41];
                    array15[n41] = -1;
                }
            }
            if (n53 == n41) {
                break;
            }
            int n58 = array15[n53];
            int n59 = n53;
            for (int n60 = n53 + 1; n60 < n41; ++n60) {
                if (array15[n60] < n58) {
                    n58 = array15[n60];
                    n59 = n60;
                }
            }
            int n61 = 0;
            for (int n62 = n53; n62 < n41; ++n62) {
                if (array15[n62] == n58) {
                    ++n61;
                }
            }
            if (n61 > 1) {
                for (int n63 = n59 + 1; n63 < n41; ++n63) {
                    if (array15[n63] == n58) {
                        this.coladd(array19, array16, array17, array18, n59, n63);
                    }
                }
            }
            else {
                this.colexchange(array19, array16, array17, array18, n59, n53);
            }
        }
        return array16;
    }
    
    void ShowSIQSStatus(final int n, final int n2, final long n3) {
        if (this.TerminateThread) {
            throw new ArithmeticException();
        }
        Thread.yield();
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.OldTimeElapsed >= 0L && this.OldTimeElapsed / 1000L != (this.OldTimeElapsed + currentTimeMillis - this.Old) / 1000L) {
            this.OldTimeElapsed += currentTimeMillis - this.Old;
            this.Old = currentTimeMillis;
            final long n4 = this.OldTimeElapsed / 1000L;
            if (currentTimeMillis - n3 > 5L && n > 10) {
                this.labelStatus.setText(this.GetDHMS(n4) + "     Congruences found: " + n + " (" + n * 100 / n2 + "%)   End sieve in " + this.GetDHMS((currentTimeMillis - n3) * (n2 - n) / n / 1000L));
                return;
            }
            this.labelStatus.setText(this.GetDHMS(n4) + "     Congruences found: " + n + " (" + n * 100 / n2 + "%)");
        }
    }
    
    void SubtractBigNbrModN(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        final long n = 2147483647L;
        long n2 = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n2 = (n2 >> 31) + array[i] - array2[i];
            array3[i] = (n2 & n);
        }
        if (n2 < 0L) {
            long n3 = 0L;
            for (int j = 0; j < numberLength; ++j) {
                n3 = (n3 >> 31) + array3[j] + this.TestNbr[j];
                array3[j] = (n3 & n);
            }
        }
    }
    
    void BigNbrModN(final long[] array, final int n, final long[] array2) {
        int i;
        for (i = 0; i < this.NumberLength; ++i) {
            array2[i] = array[i + n - this.NumberLength];
        }
        array2[i] = 0L;
        this.AdjustModN(array2);
        for (int j = n - this.NumberLength - 1; j >= 0; --j) {
            for (int k = this.NumberLength; k > 0; --k) {
                array2[k] = array2[k - 1];
            }
            array2[0] = array[j];
            this.AdjustModN(array2);
        }
    }
    
    String BigNbrToString(final long[] array) {
        boolean b = false;
        String s = "";
        if (array[this.NumberLength - 1] >= 1073741824L) {
            this.ChSignBigNbr(array);
            b = true;
        }
        System.arraycopy(array, 0, this.CalcBigNbr, 0, this.NumberLength);
        int i;
        do {
            long n = 0L;
            for (int j = this.NumberLength - 1; j >= 0; --j) {
                final long[] calcBigNbr = this.CalcBigNbr;
                final int n2 = j;
                calcBigNbr[n2] += n << 31;
                n = this.CalcBigNbr[j] % 1000000000L;
                final long[] calcBigNbr2 = this.CalcBigNbr;
                final int n3 = j;
                calcBigNbr2[n3] /= 1000000000L;
            }
            s = (n + 1000000000L + "").substring(1) + s;
            for (i = 0; i < this.NumberLength && this.CalcBigNbr[i] == 0L; ++i) {}
        } while (i < this.NumberLength);
        while (s.charAt(0) == '0' && s.length() > 1) {
            s = s.substring(1);
        }
        if (b) {
            this.ChSignBigNbr(array);
            s = "-" + s;
        }
        return s;
    }
    
    void BigNbrToBigInt(final BigInteger bigInteger) {
        final byte[] byteArray = bigInteger.toByteArray();
        this.NumberLength = (byteArray.length * 8 + 30) / 31;
        final long[] array = new long[this.NumberLength + 1];
        this.yieldFreq = 1000000 / (this.NumberLength * this.NumberLength);
        if (this.yieldFreq > 100000L) {
            this.yieldFreq = this.yieldFreq / 100000L * 100000L;
        }
        else if (this.yieldFreq > 10000L) {
            this.yieldFreq = this.yieldFreq / 10000L * 10000L;
        }
        else if (this.yieldFreq > 1000L) {
            this.yieldFreq = this.yieldFreq / 1000L * 1000L;
        }
        else if (this.yieldFreq > 100L) {
            this.yieldFreq = this.yieldFreq / 100L * 100L;
        }
        int n = 0;
        long n2 = 1L;
        long n3 = 0L;
        for (int i = byteArray.length - 1; i >= 0; --i) {
            n3 += n2 * ((byteArray[i] >= 0) ? byteArray[i] : (byteArray[i] + 256));
            n2 *= 256L;
            if (n2 == 4294967296L) {
                array[n++] = n3;
                n2 = 1L;
                n3 = 0L;
            }
        }
        array[n] = n3;
        this.Convert32To31Bits(array, this.TestNbr);
        if (this.TestNbr[this.NumberLength - 1] > 1000000000L) {
            this.TestNbr[this.NumberLength] = 0L;
            ++this.NumberLength;
        }
        this.TestNbr[this.NumberLength] = 0L;
    }
    
    void InsertFactor(final BigInteger bigInteger) {
        for (int i = this.NroFact - 1; i >= 0; --i) {
            this.Factores[this.NroFact] = this.Factores[i].gcd(bigInteger);
            if (!this.Factores[this.NroFact].equals(ecm.BigInt1) && !this.Factores[this.NroFact].equals(this.Factores[i])) {
                this.Factores[i] = this.Factores[i].divide(this.Factores[this.NroFact]);
                ++this.NroFact;
            }
        }
    }
    
    public void destroy() {
        this.TerminateThread = true;
    }
    
    public void setDigits(final int n) {
        if (this.onlyFactoring) {
            this.digitsInGroup = ((this.digitsInGroup & 0xFC00) | n);
            final String textAreaContents = this.textAreaContents;
            final String stringToLabel = this.StringToLabel;
            this.ShowUpperPane();
            this.textAreaContents = textAreaContents;
            this.StringToLabel = stringToLabel;
        }
    }
    
    void MatrMultBySSt(final int[] array, final int n, final int[] array2) {
        for (int i = array.length - 1; i >= 0; --i) {
            array2[i] = (n & array[i]);
        }
    }
    
    public String resultBatch() {
        if (this.batchFinished) {
            return this.outputStr.toString();
        }
        return "";
    }
    
    public void Verbose(final int n) {
        if (n == 0) {
            this.digitsInGroup &= 0xF7FF;
        }
        else {
            this.digitsInGroup |= 0x800;
        }
        final String textAreaContents = this.textAreaContents;
        final String stringToLabel = this.StringToLabel;
        this.ShowUpperPane();
        this.textAreaContents = textAreaContents;
        this.StringToLabel = stringToLabel;
    }
    
    void colexchange(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n, final int n2) {
        if (n == n2) {
            return;
        }
        final int n3 = 1 << 31 - (n & 0x1F);
        final int n4 = 1 << 31 - (n2 & 0x1F);
        final int[] array5 = (n >= 32) ? array3 : array4;
        final int[] array6 = (n2 >= 32) ? array3 : array4;
        for (int i = array2.length - 1; i >= 0; --i) {
            if ((array5[i] & n3) == 0x0 != ((array6[i] & n4) == 0x0)) {
                final int[] array7 = array5;
                final int n5 = i;
                array7[n5] ^= n3;
                final int[] array8 = array6;
                final int n6 = i;
                array8[n6] ^= n4;
            }
        }
        final int[] array9 = (n >= 32) ? array : array2;
        final int[] array10 = (n2 >= 32) ? array : array2;
        for (int j = array2.length - 1; j >= 0; --j) {
            if ((array9[j] & n3) == 0x0 != ((array10[j] & n4) == 0x0)) {
                final int[] array11 = array9;
                final int n7 = j;
                array11[n7] ^= n3;
                final int[] array12 = array10;
                final int n8 = j;
                array12[n8] ^= n4;
            }
        }
    }
    
    void BatchThread() {
        this.outputStr = new StringBuffer();
        final int length = this.inputStr.length();
        int i = 0;
        final BigInteger[] array = { null };
        this.textNumber.setEditable(false);
        this.labelTop.setText("Number to factor:");
        while (i < length) {
            int index = this.inputStr.indexOf(10, i);
            if (index < 0) {
                index = length;
            }
            final String trim = this.inputStr.substring(i, index).trim();
            i = index + 1;
            if (trim.length() == 0) {
                this.outputStr.append("\n");
            }
            else if (trim.charAt(0) == '#') {
                this.outputStr.append(trim + "\n");
            }
            else {
                int computeExpression;
                try {
                    computeExpression = expression.ComputeExpression(trim, 0, array);
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.outputStr.append(trim + ": Out of memory\n");
                    continue;
                }
                catch (ArithmeticException ex) {
                    continue;
                }
                this.NumberToFactor = array[0];
                if (computeExpression != 0) {
                    this.outputStr.append(trim + ": " + ecm.expressionText[-1 - computeExpression] + "\n");
                }
                else {
                    if (this.batchPrime) {
                        if (this.NumberToFactor.compareTo(ecm.BigInt3) <= 0) {
                            this.NbrFactors = 0;
                        }
                        else if (ecm.BigInt3.modPow(this.NumberToFactor.subtract(ecm.BigInt1), this.NumberToFactor).equals(ecm.BigInt1)) {
                            if (this.NumberToFactor.bitLength() < 34) {
                                final long longValue = this.NumberToFactor.longValue();
                                if (longValue % 2L == 0L) {
                                    this.NbrFactors = 1;
                                }
                                else {
                                    this.NbrFactors = 0;
                                    for (long n = 3L; n * n <= longValue; n += 2L) {
                                        if (longValue % n == 0L) {
                                            this.NbrFactors = 1;
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                this.textNumber.setText(this.NumberToFactor.toString());
                                this.startNewFactorization(true);
                                if (this.NbrFactors == 1 && this.Exp[0] == 1) {
                                    this.NbrFactors = 0;
                                }
                            }
                        }
                        else {
                            this.NbrFactors = 1;
                        }
                    }
                    else if (this.NumberToFactor.bitLength() < 34) {
                        long longValue2 = this.NumberToFactor.longValue();
                        int n2 = 0;
                        int nbrFactors = 0;
                        while (longValue2 % 2L == 0L) {
                            ++n2;
                            longValue2 /= 2L;
                        }
                        if (n2 > 0) {
                            this.PD[0] = ecm.BigInt2;
                            this.Exp[0] = n2;
                            ++nbrFactors;
                        }
                        for (long n3 = 3L; n3 * n3 <= longValue2; n3 += 2L) {
                            int n4 = 0;
                            while (longValue2 % n3 == 0L) {
                                ++n4;
                                longValue2 /= n3;
                            }
                            if (n4 > 0) {
                                this.PD[nbrFactors] = BigInteger.valueOf(n3);
                                this.Exp[nbrFactors] = n4;
                                ++nbrFactors;
                            }
                        }
                        if (longValue2 > 1L) {
                            this.PD[nbrFactors] = BigInteger.valueOf(longValue2);
                            this.Exp[nbrFactors] = 1;
                            ++nbrFactors;
                        }
                        this.NbrFactors = nbrFactors;
                    }
                    else {
                        this.textNumber.setText(this.NumberToFactor.toString());
                        this.startNewFactorization(true);
                    }
                    this.outputStr.append(this.NumberToFactor.toString());
                    if (this.batchPrime) {
                        if (this.NbrFactors == 0) {
                            this.outputStr.append(" is prime\n");
                        }
                        else {
                            this.outputStr.append(" is composite\n");
                        }
                    }
                    else {
                        this.outputStr.append(" = ");
                        for (int j = 0; j < this.NbrFactors; ++j) {
                            if (j > 0) {
                                this.outputStr.append(" * ");
                            }
                            this.outputStr.append(this.PD[j].toString());
                            if (this.Exp[j] > 1) {
                                this.outputStr.append("^");
                                this.outputStr.append(this.Exp[j]);
                            }
                        }
                        this.outputStr.append("\n");
                    }
                }
            }
        }
        this.batchFinished = true;
        this.onlyFactoring = true;
        this.textNumber.setEditable(true);
        this.textNumber.setText("");
        this.upperTextArea.setText("");
        this.lowerTextArea.setText("");
        this.labelStatus.setText("");
        this.labelTop.setText("Type number or numerical expression to factor here and press Return:");
    }
    
    void InsertNewFactor(final BigInteger bigInteger) {
        for (int i = this.NbrFactors - 1; i >= 0; --i) {
            this.PD[this.NbrFactors] = this.PD[i].gcd(bigInteger);
            if (!this.PD[this.NbrFactors].equals(ecm.BigInt1) && !this.PD[this.NbrFactors].equals(this.PD[i])) {
                int n = 0;
                while (this.PD[i].remainder(this.PD[this.NbrFactors]).signum() == 0) {
                    this.PD[i] = this.PD[i].divide(this.PD[this.NbrFactors]);
                    ++n;
                }
                this.Exp[this.NbrFactors] = this.Exp[i] * n;
                if (this.Typ[i] < 100000000) {
                    this.Typ[i] = -this.EC;
                    this.Typ[this.NbrFactors] = -300000000 - this.EC;
                }
                else if (this.Typ[i] < 150000000) {
                    this.Typ[this.NbrFactors] = -this.Typ[i];
                    this.Typ[i] = 100000000 - this.Typ[i];
                }
                else if (this.Typ[i] < 200000000) {
                    this.Typ[this.NbrFactors] = -this.Typ[i];
                    this.Typ[i] = 150000000 - this.Typ[i];
                }
                else if (this.Typ[i] < 250000000) {
                    this.Typ[this.NbrFactors] = -this.Typ[i];
                    this.Typ[i] = 200000000 - this.Typ[i];
                }
                else {
                    this.Typ[this.NbrFactors] = -this.Typ[i];
                    this.Typ[i] = 250000000 - this.Typ[i];
                }
                ++this.NbrFactors;
            }
        }
        this.SortFactorsInputNbr();
    }
    
    void startNewFactorization(final boolean b) {
        if (this.calcThread != null && this.batchFinished) {
            this.TerminateThread = true;
            try {
                this.calcThread.join();
            }
            catch (InterruptedException ex) {}
        }
        this.onlyFactoring = !b;
        final boolean b2 = false;
        this.EC = (b2 ? 1 : 0);
        this.NbrFactors = (b2 ? 1 : 0);
        final long n = (long)(b2 ? 1 : 0);
        this.OldTimeElapsed = n;
        this.lModularMult = n;
        if (b) {
            this.factorize();
            return;
        }
        (this.calcThread = new Thread(this)).start();
    }
    
    void AddBigNbr32(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        long n = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n = (n >> 32) + array[i] + array2[i];
            array3[i] = (n & 0xFFFFFFFFL);
        }
    }
    
    void ChSignBigNbr(final long[] array) {
        final int numberLength = this.NumberLength;
        long n = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n = (n >> 31) - array[i];
            array[i] = (n & 0x7FFFFFFFL);
        }
    }
    
    void MultBigNbr(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        final long n = 2147483647L;
        long n2 = 0L;
        for (int i = 0; i < numberLength; ++i) {
            long n3 = n2 & n;
            n2 >>>= 31;
            for (int j = 0; j <= i; ++j) {
                final long n4 = n3 + array[j] * array2[i - j];
                n2 += n4 >>> 31;
                n3 = (n4 & n);
            }
            array3[i] = n3;
        }
    }
    
    long GetSmallFactors(final BigInteger bigInteger, final BigInteger[] array, final int[] array2, final int[] array3, final int n) {
        boolean b = false;
        this.BigNbrToBigInt(bigInteger);
        this.NbrFactors = 0;
        int n2 = 0;
        do {
            array2[n2] = (array3[n2] = 0);
        } while (++n2 < 400);
        while ((this.TestNbr[0] & 0x1L) == 0x0L) {
            if (array2[this.NbrFactors] == 0) {
                array[this.NbrFactors] = ecm.BigInt2;
            }
            final int nbrFactors = this.NbrFactors;
            ++array2[nbrFactors];
            this.DivBigNbrByLong(this.TestNbr, 2L, this.TestNbr);
        }
        if (array2[this.NbrFactors] != 0) {
            ++this.NbrFactors;
        }
        while (this.RemDivBigNbrByLong(this.TestNbr, 3L) == 0L) {
            if (n == 1) {
                b = !b;
            }
            if (array2[this.NbrFactors] == 0) {
                array[this.NbrFactors] = ecm.BigInt3;
            }
            final int nbrFactors2 = this.NbrFactors;
            ++array2[nbrFactors2];
            this.DivBigNbrByLong(this.TestNbr, 3L, this.TestNbr);
        }
        if (b) {
            return -1L;
        }
        if (array2[this.NbrFactors] != 0) {
            ++this.NbrFactors;
        }
        long n3 = 5L;
        long n4 = this.TestNbr[0] + (this.TestNbr[1] << 31);
        if (n4 < 0L) {
            n4 = 21474836480000L;
        }
        else {
            for (int i = 2; i < this.NumberLength; ++i) {
                if (this.TestNbr[i] != 0L) {
                    n4 = 21474836480000L;
                    break;
                }
            }
        }
        while (n3 < 131072L) {
            if (n3 % 3L != 0L) {
                while (this.RemDivBigNbrByLong(this.TestNbr, n3) == 0L) {
                    if (n == 1 && n3 % 4L == 3L) {
                        b = !b;
                    }
                    if (array2[this.NbrFactors] == 0) {
                        array[this.NbrFactors] = BigInteger.valueOf(n3);
                    }
                    final int nbrFactors3 = this.NbrFactors;
                    ++array2[nbrFactors3];
                    this.DivBigNbrByLong(this.TestNbr, n3, this.TestNbr);
                    n4 = this.TestNbr[0] + (this.TestNbr[1] << 31);
                    if (n4 < 0L) {
                        n4 = 21474836480000L;
                    }
                    else {
                        for (int j = 2; j < this.NumberLength; ++j) {
                            if (this.TestNbr[j] != 0L) {
                                n4 = 21474836480000L;
                                break;
                            }
                        }
                    }
                }
                if (b) {
                    return -1L;
                }
                if (array2[this.NbrFactors] != 0) {
                    ++this.NbrFactors;
                }
            }
            n3 += 2L;
            if (n4 < n3 * n3 && n4 != 1L) {
                if (n == 1 && n4 % 4L == 3L) {
                    return -1L;
                }
                if (array2[this.NbrFactors] != 0) {
                    ++this.NbrFactors;
                }
                array[this.NbrFactors] = BigInteger.valueOf(n4);
                array2[this.NbrFactors] = 1;
                n4 = 1L;
                ++this.NbrFactors;
                break;
            }
        }
        return n4;
    }
    
    public void run() {
        if (!this.batchFinished) {
            this.BatchThread();
            return;
        }
        this.polynomialsSieved = 0L;
        this.trialDivisions = 0L;
        this.smoothsFound = 0L;
        this.totalPartials = 0L;
        this.partialsFound = 0L;
        this.factorize();
    }
    
    public void init() {
        if (this.onlyFactoring) {
            this.layout("Type number or numerical expression to factor here and press Return:", true);
        }
    }
    
    void ModInvBigNbr(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        final long[] biTmp = this.biTmp;
        final long[] calcAuxGcdU = this.CalcAuxGcdU;
        final long[] calcAuxGcdV = this.CalcAuxGcdV;
        final long[] calcAuxGcdT = this.CalcAuxGcdT;
        this.Convert31To32Bits(array, calcAuxGcdU);
        this.Convert31To32Bits(array3, calcAuxGcdV);
        System.arraycopy(calcAuxGcdV, 0, biTmp, 0, numberLength);
        final int n2;
        final int n = n2 = (int)biTmp[0];
        final int n3 = n2 * (2 - n * n2);
        final int n4 = n3 * (2 - n * n3);
        final int n5 = n4 * (2 - n * n4);
        final int n6 = n5 * (2 - n * n5);
        for (int i = numberLength - 1; i >= 0; --i) {
            calcAuxGcdT[i] = (array2[i] = 0L);
        }
        calcAuxGcdT[0] = 1L;
        int n7 = 0;
        while (true) {
            int n9;
            int n8 = n9 = 1;
            int n11;
            int n10 = n11 = 0;
            int n12 = (int)calcAuxGcdU[0];
            int n13 = (int)calcAuxGcdV[0];
            int n14 = 0;
            int n15 = 1;
            if (n13 == 0) {
                int n16;
                for (n16 = numberLength - 1; n16 >= 0 && calcAuxGcdV[n16] == 0L; --n16) {}
                if (n16 < 0) {
                    break;
                }
            }
            int n17 = 0;
        Block_4:
            while (true) {
                for (n17 = 0; (n13 & 0x1) == 0x0; n13 >>= 1, ++n7, ++n14, n15 *= 2, ++n17) {
                    if (n14 == 31) {
                        break Block_4;
                    }
                }
                n9 <<= n17;
                n11 <<= n17;
                if (n7 >= 0) {
                    n7 = -n7;
                    if ((n12 + n13 & 0x3) == 0x0) {
                        final int n18 = n10;
                        n10 += n9;
                        n9 = n18;
                        final int n19 = n8;
                        n8 += n11;
                        n11 = n19;
                        final int n20 = n13;
                        n13 += n12;
                        n12 = n20;
                    }
                    else {
                        final int n21 = n10;
                        n10 -= n9;
                        n9 = n21;
                        final int n22 = n8;
                        n8 -= n11;
                        n11 = n22;
                        final int n23 = n13;
                        n13 -= n12;
                        n12 = n23;
                    }
                }
                else if ((n12 + n13 & 0x3) == 0x0) {
                    n10 += n9;
                    n8 += n11;
                    n13 += n12;
                }
                else {
                    n10 -= n9;
                    n8 -= n11;
                    n13 -= n12;
                }
                --n7;
            }
            final long n24 = n9;
            final long n25 = n11;
            final long n26 = n10;
            final long n27 = n8;
            final int n28 = (int)array2[0];
            final int n29 = (int)calcAuxGcdT[0];
            ++n7;
            ++n17;
            final long n30 = n24 << n17;
            final long n31 = n25 << n17;
            final long n32 = (-(int)n30 * n29 - (int)n31 * n28) * n6;
            final long n33 = (-n10 * n29 - n8 * n28) * n6;
            long n37;
            long n36;
            long n35;
            long n34 = n35 = (n36 = (n37 = 0L));
            final int n38 = (int)(n30 >> 32);
            final int n39 = (int)(n31 >> 32);
            final int n40 = (int)(n26 >> 32);
            final int n41 = (int)(n27 >> 32);
            final int n42 = (int)(n32 >> 32);
            final int n43 = (int)(n33 >> 32);
            final long n44 = n30 & 0xFFFFFFFFL;
            final long n45 = n31 & 0xFFFFFFFFL;
            final long n46 = n26 & 0xFFFFFFFFL;
            final long n47 = n27 & 0xFFFFFFFFL;
            final long n48 = n32 & 0xFFFFFFFFL;
            final long n49 = n33 & 0xFFFFFFFFL;
            final int n50 = n38 * 6 + n39 * 2 + n42;
            final int n51 = n40 * 6 + n41 * 2 + n43;
            int j;
            for (j = 0; j < numberLength; ++j) {
                final long n53;
                final long n52 = n44 * (n53 = calcAuxGcdT[j]);
                final long n55;
                final long n54 = n45 * (n55 = array2[j]);
                final long n57;
                final long n56 = n48 * (n57 = biTmp[j]);
                final long n58 = (n52 & 0xFFFFFFFFL) + (n54 & 0xFFFFFFFFL) + (n56 & 0xFFFFFFFFL) + n36;
                final long n60;
                final long n59 = n44 * (n60 = calcAuxGcdU[j]);
                final long n62;
                final long n61 = n45 * (n62 = calcAuxGcdV[j]);
                final long n63 = (n59 & 0xFFFFFFFFL) + (n61 & 0xFFFFFFFFL) + n35;
                switch (n50) {
                    case -9: {
                        n36 = -n53 - n55 - n57;
                        n35 = -n60 - n62;
                        break;
                    }
                    case -8: {
                        n36 = -n53 - n55;
                        n35 = -n60 - n62;
                        break;
                    }
                    case -7: {
                        n36 = -n53 - n57;
                        n35 = -n60;
                        break;
                    }
                    case -6: {
                        n36 = -n53;
                        n35 = -n60;
                        break;
                    }
                    case -5: {
                        n36 = -n53 + n55 - n57;
                        n35 = -n60 + n62;
                        break;
                    }
                    case -4: {
                        n36 = -n53 + n55;
                        n35 = -n60 + n62;
                        break;
                    }
                    case -3: {
                        n36 = -n55 - n57;
                        n35 = -n62;
                        break;
                    }
                    case -2: {
                        n36 = -n55;
                        n35 = -n62;
                        break;
                    }
                    case -1: {
                        n36 = -n57;
                        n35 = 0L;
                        break;
                    }
                    case 0: {
                        n36 = 0L;
                        n35 = 0L;
                        break;
                    }
                    case 1: {
                        n36 = n55 - n57;
                        n35 = n62;
                        break;
                    }
                    case 2: {
                        n36 = n55;
                        n35 = n62;
                        break;
                    }
                    case 3: {
                        n36 = n53 - n55 - n57;
                        n35 = n60 - n62;
                        break;
                    }
                    case 4: {
                        n36 = n53 - n55;
                        n35 = n60 - n62;
                        break;
                    }
                    case 5: {
                        n36 = n53 - n57;
                        n35 = n60;
                        break;
                    }
                    case 6: {
                        n36 = n53;
                        n35 = n60;
                        break;
                    }
                    case 7: {
                        n36 = n53 + n55 - n57;
                        n35 = n60 + n62;
                        break;
                    }
                    case 8: {
                        n36 = n53 + n55;
                        n35 = n60 + n62;
                        break;
                    }
                }
                n36 += (n52 >>> 32) + (n54 >>> 32) + (n56 >>> 32) + (n58 >> 32);
                n35 += (n59 >>> 32) + (n61 >>> 32) + (n63 >> 32);
                if (j > 0) {
                    calcAuxGcdT[j - 1] = (n58 & 0xFFFFFFFFL);
                    calcAuxGcdU[j - 1] = (n63 & 0xFFFFFFFFL);
                }
                final long n64 = n46 * n53;
                final long n65 = n47 * n55;
                final long n66 = n49 * n57;
                final long n67 = (n64 & 0xFFFFFFFFL) + (n65 & 0xFFFFFFFFL) + (n66 & 0xFFFFFFFFL) + n37;
                final long n68 = n46 * n60;
                final long n69 = n47 * n62;
                final long n70 = (n68 & 0xFFFFFFFFL) + (n69 & 0xFFFFFFFFL) + n34;
                switch (n51) {
                    case -9: {
                        n37 = -n53 - n55 - n57;
                        n34 = -n60 - n62;
                        break;
                    }
                    case -8: {
                        n37 = -n53 - n55;
                        n34 = -n60 - n62;
                        break;
                    }
                    case -7: {
                        n37 = -n53 - n57;
                        n34 = -n60;
                        break;
                    }
                    case -6: {
                        n37 = -n53;
                        n34 = -n60;
                        break;
                    }
                    case -5: {
                        n37 = -n53 + n55 - n57;
                        n34 = -n60 + n62;
                        break;
                    }
                    case -4: {
                        n37 = -n53 + n55;
                        n34 = -n60 + n62;
                        break;
                    }
                    case -3: {
                        n37 = -n55 - n57;
                        n34 = -n62;
                        break;
                    }
                    case -2: {
                        n37 = -n55;
                        n34 = -n62;
                        break;
                    }
                    case -1: {
                        n37 = -n57;
                        n34 = 0L;
                        break;
                    }
                    case 0: {
                        n37 = 0L;
                        n34 = 0L;
                        break;
                    }
                    case 1: {
                        n37 = n55 - n57;
                        n34 = n62;
                        break;
                    }
                    case 2: {
                        n37 = n55;
                        n34 = n62;
                        break;
                    }
                    case 3: {
                        n37 = n53 - n55 - n57;
                        n34 = n60 - n62;
                        break;
                    }
                    case 4: {
                        n37 = n53 - n55;
                        n34 = n60 - n62;
                        break;
                    }
                    case 5: {
                        n37 = n53 - n57;
                        n34 = n60;
                        break;
                    }
                    case 6: {
                        n37 = n53;
                        n34 = n60;
                        break;
                    }
                    case 7: {
                        n37 = n53 + n55 - n57;
                        n34 = n60 + n62;
                        break;
                    }
                    case 8: {
                        n37 = n53 + n55;
                        n34 = n60 + n62;
                        break;
                    }
                }
                n37 += (n64 >>> 32) + (n65 >>> 32) + (n66 >>> 32) + (n67 >> 32);
                n34 += (n68 >>> 32) + (n69 >>> 32) + (n70 >> 32);
                if (j > 0) {
                    array2[j - 1] = (n67 & 0xFFFFFFFFL);
                    calcAuxGcdV[j - 1] = (n70 & 0xFFFFFFFFL);
                }
            }
            if ((int)calcAuxGcdU[j - 1] < 0) {
                n35 -= n44;
                n34 -= n46;
            }
            if ((int)calcAuxGcdV[j - 1] < 0) {
                n35 -= n45;
                n34 -= n47;
            }
            if ((int)calcAuxGcdT[j - 1] < 0) {
                n36 -= n44;
                n37 -= n46;
            }
            if ((int)array2[j - 1] < 0) {
                n36 -= n45;
                n37 -= n47;
            }
            calcAuxGcdU[j - 1] = (n35 & 0xFFFFFFFFL);
            calcAuxGcdV[j - 1] = (n34 & 0xFFFFFFFFL);
            calcAuxGcdT[j - 1] = (n36 & 0xFFFFFFFFL);
            array2[j - 1] = (n37 & 0xFFFFFFFFL);
        }
        if (calcAuxGcdU[0] != 1L) {
            this.SubtractBigNbr32(biTmp, calcAuxGcdT, calcAuxGcdT);
        }
        int n71;
        if ((int)calcAuxGcdT[n71 = numberLength - 1] < 0) {
            this.AddBigNbr32(biTmp, calcAuxGcdT, calcAuxGcdT);
        }
        while (n71 >= 0 && biTmp[n71] == calcAuxGcdT[n71]) {
            --n71;
        }
        if (n71 < 0 || biTmp[n71] < calcAuxGcdT[n71]) {
            this.SubtractBigNbr32(calcAuxGcdT, biTmp, calcAuxGcdT);
        }
        this.Convert32To31Bits(calcAuxGcdT, array2);
    }
    
    void FactorLucas(final int n, final BigInteger bigInteger) {
        this.NroFact = 1;
        if (this.onlyFactoring) {
            this.InsertLucasFactor(n, this.Factores[0] = bigInteger);
            this.SortFactors();
        }
    }
    
    BigInteger Lucas(final int n) {
        if (this.onlyFactoring) {
            BigInteger value = BigInteger.valueOf(-1L);
            BigInteger bigInt2 = ecm.BigInt2;
            for (int i = 1; i <= n; ++i) {
                final BigInteger add = value.add(bigInt2);
                value = bigInt2;
                bigInt2 = add;
            }
            return bigInt2;
        }
        return null;
    }
    
    public void useCunnTable(final int n) {
        if (n == 1) {
            this.digitsInGroup &= 0xEFFF;
            return;
        }
        this.digitsInGroup |= 0x1000;
    }
    
    void MatrixMultiplication(final int[] array, final int[] array2, final int[] array3) {
        for (int length = array.length, i = 0; i < length; ++i) {
            int n = 0;
            for (int j = array[i], n2 = 0; j != 0; j *= 2, ++n2) {
                if (j < 0) {
                    n ^= array2[n2];
                }
            }
            array3[i] = n;
        }
    }
    
    boolean ComputeFourSquares(final BigInteger[] array, final int[] array2) {
        if (this.onlyFactoring) {
            this.Quad1 = ecm.BigInt1;
            this.Quad2 = ecm.BigInt0;
            this.Quad3 = ecm.BigInt0;
            this.Quad4 = ecm.BigInt0;
            for (int i = this.NbrFactors - 1; i >= 0; --i) {
                if (array2[i] % 2 != 0) {
                    final BigInteger bigInteger = array[i];
                    final BigInteger subtract = bigInteger.subtract(ecm.BigInt1);
                    BigInteger bigInteger2;
                    BigInteger bigInteger3;
                    BigInteger bigInteger4;
                    BigInteger bigInteger5;
                    if (bigInteger.equals(ecm.BigInt2)) {
                        bigInteger2 = ecm.BigInt1;
                        bigInteger3 = ecm.BigInt1;
                        bigInteger4 = ecm.BigInt0;
                        bigInteger5 = ecm.BigInt0;
                    }
                    else if (!bigInteger.testBit(1)) {
                        BigInteger bigInteger6 = ecm.BigInt1;
                        do {
                            bigInteger6 = bigInteger6.add(ecm.BigInt1);
                            bigInteger2 = bigInteger6.modPow(subtract.shiftRight(2), bigInteger);
                        } while (bigInteger2.equals(ecm.BigInt1) || bigInteger2.equals(subtract));
                        if (!bigInteger2.multiply(bigInteger2).mod(bigInteger).equals(subtract)) {
                            return false;
                        }
                        bigInteger3 = ecm.BigInt1;
                        while (true) {
                            final BigInteger divide = bigInteger2.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger3)).divide(bigInteger);
                            if (divide.equals(ecm.BigInt1)) {
                                bigInteger4 = ecm.BigInt0;
                                bigInteger5 = ecm.BigInt0;
                                break;
                            }
                            if (bigInteger.mod(divide).signum() == 0) {
                                return false;
                            }
                            BigInteger bigInteger7 = bigInteger2.mod(divide);
                            BigInteger bigInteger8 = bigInteger3.mod(divide);
                            if (bigInteger7.compareTo(divide.shiftRight(1)) > 0) {
                                bigInteger7 = bigInteger7.subtract(divide);
                            }
                            if (bigInteger8.compareTo(divide.shiftRight(1)) > 0) {
                                bigInteger8 = bigInteger8.subtract(divide);
                            }
                            final BigInteger divide2 = bigInteger2.multiply(bigInteger7).add(bigInteger3.multiply(bigInteger8)).divide(divide);
                            bigInteger3 = bigInteger2.multiply(bigInteger8).subtract(bigInteger3.multiply(bigInteger7)).divide(divide);
                            bigInteger2 = divide2;
                        }
                    }
                    else {
                        bigInteger2 = ecm.BigInt0;
                        do {
                            bigInteger2 = bigInteger2.add(ecm.BigInt1);
                        } while (ecm.BigInt1.negate().subtract(bigInteger2.multiply(bigInteger2)).modPow(subtract.shiftRight(1), bigInteger).compareTo(ecm.BigInt1) > 0);
                        bigInteger3 = ecm.BigInt1.negate().subtract(bigInteger2.multiply(bigInteger2)).modPow(bigInteger.add(ecm.BigInt1).shiftRight(2), bigInteger);
                        bigInteger4 = ecm.BigInt1;
                        bigInteger5 = ecm.BigInt0;
                        while (true) {
                            final BigInteger divide3 = bigInteger2.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger3)).add(bigInteger4.multiply(bigInteger4)).add(bigInteger5.multiply(bigInteger5)).divide(bigInteger);
                            if (divide3.equals(ecm.BigInt1)) {
                                break;
                            }
                            if (!divide3.testBit(0)) {
                                if (bigInteger2.add(bigInteger3).testBit(0)) {
                                    if (!bigInteger2.add(bigInteger4).testBit(0)) {
                                        final BigInteger bigInteger9 = bigInteger3;
                                        bigInteger3 = bigInteger4;
                                        bigInteger4 = bigInteger9;
                                    }
                                    else {
                                        final BigInteger bigInteger10 = bigInteger3;
                                        bigInteger3 = bigInteger5;
                                        bigInteger5 = bigInteger10;
                                    }
                                }
                                final BigInteger shiftRight = bigInteger2.add(bigInteger3).shiftRight(1);
                                final BigInteger shiftRight2 = bigInteger2.subtract(bigInteger3).shiftRight(1);
                                final BigInteger shiftRight3 = bigInteger4.add(bigInteger5).shiftRight(1);
                                bigInteger5 = bigInteger4.subtract(bigInteger5).shiftRight(1);
                                bigInteger4 = shiftRight3;
                                bigInteger3 = shiftRight2;
                                bigInteger2 = shiftRight;
                            }
                            else {
                                BigInteger bigInteger11 = bigInteger2.mod(divide3);
                                BigInteger bigInteger12 = bigInteger3.mod(divide3);
                                BigInteger bigInteger13 = bigInteger4.mod(divide3);
                                BigInteger bigInteger14 = bigInteger5.mod(divide3);
                                if (bigInteger11.compareTo(divide3.shiftRight(1)) > 0) {
                                    bigInteger11 = bigInteger11.subtract(divide3);
                                }
                                if (bigInteger12.compareTo(divide3.shiftRight(1)) > 0) {
                                    bigInteger12 = bigInteger12.subtract(divide3);
                                }
                                if (bigInteger13.compareTo(divide3.shiftRight(1)) > 0) {
                                    bigInteger13 = bigInteger13.subtract(divide3);
                                }
                                if (bigInteger14.compareTo(divide3.shiftRight(1)) > 0) {
                                    bigInteger14 = bigInteger14.subtract(divide3);
                                }
                                final BigInteger divide4 = bigInteger2.multiply(bigInteger11).add(bigInteger3.multiply(bigInteger12)).add(bigInteger4.multiply(bigInteger13)).add(bigInteger5.multiply(bigInteger14)).divide(divide3);
                                final BigInteger divide5 = bigInteger2.multiply(bigInteger12).subtract(bigInteger3.multiply(bigInteger11)).add(bigInteger4.multiply(bigInteger14)).subtract(bigInteger5.multiply(bigInteger13)).divide(divide3);
                                final BigInteger divide6 = bigInteger2.multiply(bigInteger13).subtract(bigInteger4.multiply(bigInteger11)).subtract(bigInteger3.multiply(bigInteger14)).add(bigInteger5.multiply(bigInteger12)).divide(divide3);
                                bigInteger5 = bigInteger2.multiply(bigInteger14).subtract(bigInteger5.multiply(bigInteger11)).add(bigInteger3.multiply(bigInteger13)).subtract(bigInteger4.multiply(bigInteger12)).divide(divide3);
                                bigInteger4 = divide6;
                                bigInteger3 = divide5;
                                bigInteger2 = divide4;
                            }
                        }
                    }
                    final BigInteger add = bigInteger2.multiply(this.Quad1).add(bigInteger3.multiply(this.Quad2)).add(bigInteger4.multiply(this.Quad3)).add(bigInteger5.multiply(this.Quad4));
                    final BigInteger subtract2 = bigInteger2.multiply(this.Quad2).subtract(bigInteger3.multiply(this.Quad1)).add(bigInteger4.multiply(this.Quad4)).subtract(bigInteger5.multiply(this.Quad3));
                    final BigInteger add2 = bigInteger2.multiply(this.Quad3).subtract(bigInteger4.multiply(this.Quad1)).subtract(bigInteger3.multiply(this.Quad4)).add(bigInteger5.multiply(this.Quad2));
                    this.Quad4 = bigInteger2.multiply(this.Quad4).subtract(bigInteger5.multiply(this.Quad1)).add(bigInteger3.multiply(this.Quad3)).subtract(bigInteger4.multiply(this.Quad2));
                    this.Quad3 = add2;
                    this.Quad2 = subtract2;
                    this.Quad1 = add;
                }
            }
            for (int j = 0; j < this.NbrFactors; ++j) {
                final BigInteger pow = array[j].pow(array2[j] / 2);
                this.Quad1 = this.Quad1.multiply(pow);
                this.Quad2 = this.Quad2.multiply(pow);
                this.Quad3 = this.Quad3.multiply(pow);
                this.Quad4 = this.Quad4.multiply(pow);
            }
            this.Quad1 = this.Quad1.abs();
            this.Quad2 = this.Quad2.abs();
            this.Quad3 = this.Quad3.abs();
            this.Quad4 = this.Quad4.abs();
            if (this.Quad1.compareTo(this.Quad2) < 0) {
                final BigInteger quad1 = this.Quad1;
                this.Quad1 = this.Quad2;
                this.Quad2 = quad1;
            }
            if (this.Quad1.compareTo(this.Quad3) < 0) {
                final BigInteger quad2 = this.Quad1;
                this.Quad1 = this.Quad3;
                this.Quad3 = quad2;
            }
            if (this.Quad1.compareTo(this.Quad4) < 0) {
                final BigInteger quad3 = this.Quad1;
                this.Quad1 = this.Quad4;
                this.Quad4 = quad3;
            }
            if (this.Quad2.compareTo(this.Quad3) < 0) {
                final BigInteger quad4 = this.Quad2;
                this.Quad2 = this.Quad3;
                this.Quad3 = quad4;
            }
            if (this.Quad2.compareTo(this.Quad4) < 0) {
                final BigInteger quad5 = this.Quad2;
                this.Quad2 = this.Quad4;
                this.Quad4 = quad5;
            }
            if (this.Quad3.compareTo(this.Quad4) < 0) {
                final BigInteger quad6 = this.Quad3;
                this.Quad3 = this.Quad4;
                this.Quad4 = quad6;
            }
        }
        return true;
    }
    
    void MultBigNbrModN(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        final long n = 2147483647L;
        int i = numberLength;
        do {
            array3[--i] = 0L;
        } while (i > 0);
        int j = numberLength;
        do {
            final long n2 = array[--j];
            int n3 = numberLength;
            do {
                array3[n3] = array3[n3 - 1];
            } while (--n3 > 0);
            array3[0] = 0L;
            long n4 = 0L;
            int k;
            for (k = 0; k < numberLength; ++k) {
                n4 = (n4 >>> 31) + n2 * array2[k] + array3[k];
                array3[k] = (n4 & n);
            }
            final int n5 = k;
            array3[n5] += n4 >>> 31;
            this.AdjustModN(array3);
        } while (j > 0);
    }
    
    int JacobiSymbol(final int n, int n2) {
        if (!this.onlyFactoring) {
            return 0;
        }
        int i = n;
        int n3 = n2;
        while (i != 0) {
            final int n4 = n3 % i;
            n3 = i;
            i = n4;
        }
        if (n3 > 1) {
            return 0;
        }
        int n5 = 1;
        while (n2 % 2 == 0) {
            n2 /= 2;
        }
        if (n2 % 3 == 0) {
            do {
                n5 = n5 * n % 3;
                n2 /= 3;
            } while (n2 % 3 == 0);
            n5 = (n5 + 1) % 3 - 1;
        }
        for (int n6 = 5; n6 * n6 <= n2; n6 += 2) {
            if (n6 % 3 != 0) {
                while (n2 % n6 == 0) {
                    n2 /= n6;
                    int n7 = (n5 + n6) % n6;
                    for (int j = (n6 - 1) / 2; j > 0; --j) {
                        n7 = n7 * n % n6;
                    }
                    n5 = (n7 + 1) % n6 - 1;
                }
            }
        }
        if (n2 > 1) {
            int n8 = (n5 + n2) % n2;
            for (int k = (n2 - 1) / 2; k > 0; --k) {
                n8 = n8 * n % n2;
            }
            n5 = (n8 + 1) % n2 - 1;
        }
        return n5;
    }
    
    int PowerCheck(final int n) {
        final int n2 = (this.PD[n].bitLength() - 1) / 17;
        final int[] array = { 2311, 4621, 9241, 11551, 18481, 25411, 32341, 34651, 43891, 50821 };
        int n3 = 1;
        int n4 = 1;
        int n5 = 1;
        int n6 = 1;
        int n7 = 1;
        for (int i = 0; i < array.length; ++i) {
            final long n8 = array[i];
            final long n9 = this.PD[n].mod(BigInteger.valueOf(n8)).intValue();
            if (n3 != 0 && this.modPow(n9, n8 / 2L, n8) > 1L) {
                n3 = 0;
            }
            if (n4 != 0 && this.modPow(n9, n8 / 3L, n8) > 1L) {
                n4 = 0;
            }
            if (n5 != 0 && this.modPow(n9, n8 / 5L, n8) > 1L) {
                n5 = 0;
            }
            if (n6 != 0 && this.modPow(n9, n8 / 7L, n8) > 1L) {
                n6 = 0;
            }
            if (n7 != 0 && this.modPow(n9, n8 / 11L, n8) > 1L) {
                n7 = 0;
            }
        }
        final boolean[] array2 = new boolean[n2 + 1];
        final boolean[] array3 = new boolean[2 * n2 + 3];
        for (int j = 2; j <= n2; ++j) {
            array2[j] = true;
        }
        for (int k = 2; k < array3.length; ++k) {
            array3[k] = true;
        }
        for (int n10 = 2; n10 * n10 < array3.length; ++n10) {
            for (int l = n10 * n10; l < array3.length; l += n10) {
                array3[l] = false;
            }
        }
        for (int n11 = 13; n11 < array3.length; ++n11) {
            if (array3[n11]) {
                int n12 = 0;
                for (int n13 = 2 * n11 + 1; n13 < array3.length; n13 += 2 * n11) {
                    if (array3[n13] && this.modPow(this.PD[n].mod(BigInteger.valueOf(n13)).longValue(), n13 / n11, n13) > 1L) {
                        for (int n14 = n11; n14 <= n2; n14 += n11) {
                            array2[n14] = false;
                        }
                        break;
                    }
                    if (++n12 > 10) {
                        break;
                    }
                }
            }
        }
        for (int n15 = n2; n15 >= 2; --n15) {
            if ((n15 % 2 != 0 || n3 != 0) && (n15 % 3 != 0 || n4 != 0) && (n15 % 5 != 0 || n5 != 0) && (n15 % 7 != 0 || n6 != 0) && (n15 % 11 != 0 || n7 != 0) && array2[n15]) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.OldTimeElapsed >= 0L && this.OldTimeElapsed / 1000L != (this.OldTimeElapsed + currentTimeMillis - this.Old) / 1000L) {
                    this.OldTimeElapsed += currentTimeMillis - this.Old;
                    this.Old = currentTimeMillis;
                    this.labelStatus.setText("Time elapsed: " + this.GetDHMS(this.OldTimeElapsed / 1000L) + "    Power exponent: " + n15);
                    Thread.yield();
                    if (this.TerminateThread) {
                        throw new ArithmeticException();
                    }
                }
                final int n16 = this.PD[n].bitLength() - 1;
                final double n17 = (n16 + Math.log(this.PD[n].shiftRight(n16 - 32).add(ecm.BigInt1).doubleValue()) / Math.log(2.0) - 32.0) / n15;
                BigInteger bigInteger;
                if (n17 < 32.0) {
                    bigInteger = BigInteger.valueOf((long)Math.exp(n17 * Math.log(2.0)));
                }
                else {
                    final int n18 = (int)Math.floor(n17) - 32;
                    bigInteger = BigInteger.valueOf((long)Math.exp((n17 - n18) * Math.log(2.0)) + 10L).shiftLeft(n18);
                }
                while (true) {
                    final BigInteger pow = bigInteger.pow(n15 - 1);
                    final BigInteger subtract = this.PD[n].subtract(bigInteger.multiply(pow));
                    if (subtract.signum() == 0) {
                        this.PD[n] = bigInteger;
                        final int[] exp = this.Exp;
                        exp[n] *= n15;
                        return 1;
                    }
                    final BigInteger subtract2 = subtract.add(ecm.BigInt1).divide(BigInteger.valueOf(n15).multiply(pow)).add(bigInteger).subtract(ecm.BigInt1);
                    if (bigInteger.compareTo(subtract2) <= 0) {
                        break;
                    }
                    bigInteger = subtract2;
                }
            }
        }
        return 0;
    }
    
    private boolean ProcessExponent(final int n) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.OldTimeElapsed >= 0L && this.OldTimeElapsed / 1000L != (this.OldTimeElapsed + currentTimeMillis - this.Old) / 1000L) {
            this.OldTimeElapsed += currentTimeMillis - this.Old;
            this.Old = currentTimeMillis;
            this.labelStatus.setText("Time elapsed: " + this.GetDHMS(this.OldTimeElapsed / 1000L) + "    Power +/- 1 exponent: " + n);
            Thread.yield();
            if (this.TerminateThread) {
                throw new ArithmeticException();
            }
        }
        final BigInteger add = this.NumberToFactor.add(ecm.BigInt1);
        final BigInteger subtract = this.NumberToFactor.subtract(ecm.BigInt1);
        final int n2 = add.bitLength() - 1;
        final double n3 = (n2 + Math.log(add.shiftRight(n2 - 32).add(ecm.BigInt1).doubleValue()) / Math.log(2.0) - 32.0) / n;
        BigInteger bigInteger;
        if (n3 < 32.0) {
            bigInteger = BigInteger.valueOf((long)Math.exp(n3 * Math.log(2.0)));
        }
        else {
            final int n4 = (int)Math.floor(n3) - 32;
            bigInteger = BigInteger.valueOf((long)Math.exp((n3 - n4) * Math.log(2.0)) + 10L).shiftLeft(n4);
        }
        final BigInteger bigInteger2 = bigInteger;
        while (true) {
            final BigInteger pow = bigInteger.pow(n - 1);
            final BigInteger subtract2 = add.subtract(bigInteger.multiply(pow));
            if (subtract2.signum() == 0) {
                this.Cunningham(bigInteger, n, ecm.BigInt1.negate(), this.PD[this.NbrFactors - 1]);
                return true;
            }
            final BigInteger subtract3 = subtract2.add(ecm.BigInt1).divide(BigInteger.valueOf(n).multiply(pow)).add(bigInteger).subtract(ecm.BigInt1);
            if (bigInteger.compareTo(subtract3) > 0) {
                bigInteger = subtract3;
            }
            else {
                BigInteger bigInteger3 = bigInteger2;
                while (true) {
                    final BigInteger pow2 = bigInteger3.pow(n - 1);
                    final BigInteger subtract4 = subtract.subtract(bigInteger3.multiply(pow2));
                    if (subtract4.signum() == 0) {
                        this.Cunningham(bigInteger3, n, ecm.BigInt1, this.PD[this.NbrFactors - 1]);
                        return true;
                    }
                    final BigInteger subtract5 = subtract4.add(ecm.BigInt1).divide(BigInteger.valueOf(n).multiply(pow2)).add(bigInteger3).subtract(ecm.BigInt1);
                    if (bigInteger3.compareTo(subtract5) <= 0) {
                        return false;
                    }
                    bigInteger3 = subtract5;
                }
            }
        }
    }
    
    final void LucasCheck() {
        if (!this.onlyFactoring) {
            return;
        }
        if (this.NumberToFactor.bitLength() > 32) {
            final int bitLength = this.NumberToFactor.bitLength();
            final double n = ((bitLength - 32) * Math.log(2.0) + Math.log(this.NumberToFactor.shiftRight(bitLength - 32).longValue())) / 0.481211825059603;
            if (n + 5.0E-6 - Math.floor(n + 5.0E-6) > 1.0E-5) {
                return;
            }
        }
        BigInteger value = BigInteger.valueOf(-1L);
        BigInteger bigInt2 = ecm.BigInt2;
        int n2 = 0;
        while (true) {
            final int compareTo = bigInt2.compareTo(this.NumberToFactor);
            if (compareTo == 0) {
                this.FactorLucas(n2, this.PD[this.NbrFactors - 1]);
                return;
            }
            if (compareTo > 0) {
                return;
            }
            final BigInteger add = value.add(bigInt2);
            value = bigInt2;
            bigInt2 = add;
            ++n2;
        }
    }
    
    static BigInteger Lehman(final BigInteger bigInteger, final int n) {
        final long[] array = { 3L, 19L, 23L, 571L, 5659L, 107287L, 199411L, 340831L, 332473075L, 303908791L, 96068509339L, 1870503675703L, 3678700564051L, 4626135339999L, 5310023542746835L, 156326468341437115L, 1662926210933060155L };
        final int[] array2 = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61 };
        final int[] array3 = new int[17];
        final int[] array4 = new int[17];
        BigInteger bigInteger2;
        int n2;
        BigInteger bigInteger3;
        if (!bigInteger.testBit(0)) {
            bigInteger2 = ecm.BigInt0;
            n2 = 1;
            bigInteger3 = ecm.BigInt1;
        }
        else if (n % 2 == 0) {
            bigInteger2 = ecm.BigInt1;
            n2 = 2;
            bigInteger3 = ecm.BigInt2;
        }
        else {
            bigInteger2 = BigInteger.valueOf(n).add(bigInteger).and(ecm.BigInt3);
            n2 = 4;
            bigInteger3 = BigInteger.valueOf(4L);
        }
        final BigInteger shiftLeft = bigInteger.multiply(BigInteger.valueOf(n)).shiftLeft(2);
        final int n3 = shiftLeft.bitLength() - 1;
        final double n4 = (n3 + Math.log(shiftLeft.shiftRight(n3 - 32).add(ecm.BigInt1).doubleValue()) / Math.log(2.0) - 32.0) / 2.0;
        BigInteger bigInteger4;
        if (n4 < 32.0) {
            bigInteger4 = BigInteger.valueOf((long)Math.exp(n4 * Math.log(2.0)));
        }
        else {
            final int n5 = (int)Math.floor(n4) - 32;
            bigInteger4 = BigInteger.valueOf((long)Math.exp((n4 - n5) * Math.log(2.0)) + 10L).shiftLeft(n5);
        }
        while (true) {
            final BigInteger subtract = shiftLeft.subtract(bigInteger4.multiply(bigInteger4));
            if (subtract.signum() == 0) {
                break;
            }
            final BigInteger subtract2 = subtract.add(ecm.BigInt1).divide(ecm.BigInt2.multiply(bigInteger4)).add(bigInteger4).subtract(ecm.BigInt1);
            if (bigInteger4.compareTo(subtract2) <= 0) {
                break;
            }
            bigInteger4 = subtract2;
        }
        BigInteger add;
        for (add = bigInteger4; !add.mod(bigInteger3).equals(bigInteger2) || add.multiply(add).compareTo(shiftLeft) < 0; add = add.add(ecm.BigInt1)) {}
        final BigInteger subtract3 = add.multiply(add).subtract(shiftLeft);
        int n6 = 0;
        do {
            final BigInteger value = BigInteger.valueOf(array2[n6]);
            array3[n6] = subtract3.mod(value).intValue();
            array4[n6] = bigInteger3.multiply(add.shiftLeft(1).add(bigInteger3)).mod(value).intValue();
        } while (++n6 < 17);
        int n7 = 0;
        do {
            int n8 = 0;
            while ((array[n8] & 1L << array3[n8]) != 0x0L && ++n8 < 17) {}
            if (n8 == 17) {
                final BigInteger add2 = add.add(BigInteger.valueOf(n2 * n7));
                final BigInteger subtract4 = add2.multiply(add2).subtract(shiftLeft);
                final int n9 = subtract4.bitLength() - 1;
                final double n10 = (n9 + Math.log(subtract4.shiftRight(n9 - 32).add(ecm.BigInt1).doubleValue()) / Math.log(2.0) - 32.0) / 2.0;
                BigInteger bigInteger5;
                if (n10 < 32.0) {
                    bigInteger5 = BigInteger.valueOf((long)Math.exp(n10 * Math.log(2.0)));
                }
                else {
                    final int n11 = (int)Math.floor(n10) - 32;
                    bigInteger5 = BigInteger.valueOf((long)Math.exp((n10 - n11) * Math.log(2.0)) + 10L).shiftLeft(n11);
                }
                while (true) {
                    final BigInteger subtract5 = subtract4.subtract(bigInteger5.multiply(bigInteger5));
                    if (subtract5.signum() == 0) {
                        bigInteger5 = bigInteger.gcd(add2.add(bigInteger5));
                        if (bigInteger5.compareTo(BigInteger.valueOf(10000L)) > 0) {
                            return bigInteger5;
                        }
                    }
                    final BigInteger subtract6 = subtract5.add(ecm.BigInt1).divide(ecm.BigInt2.multiply(bigInteger5)).add(bigInteger5).subtract(ecm.BigInt1);
                    if (bigInteger5.compareTo(subtract6) <= 0) {
                        break;
                    }
                    bigInteger5 = subtract6;
                }
            }
            int n12 = 0;
            do {
                array3[n12] = (array3[n12] + array4[n12]) % array2[n12];
                array4[n12] = (array4[n12] + 2 * n2 * n2) % array2[n12];
            } while (++n12 < 17);
        } while (++n7 < 10000);
        return ecm.BigInt1;
    }
    
    void JS_E(final int n, final int n2, final int n3, final int n4) {
        int n5;
        for (n5 = this.NumberLength - 1; n5 > 0 && this.biExp[n5] == 0L; --n5) {}
        if (n5 == 0 && this.biExp[0] == 1L) {
            return;
        }
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < this.NumberLength; ++j) {
                this.aiJW[i][j] = this.aiJS[i][j];
            }
        }
        long n6;
        for (n6 = 1073741824L; (this.biExp[n5] & n6) == 0x0L; n6 /= 2L) {}
        do {
            this.JS_2(n, n2, n3, n4);
            n6 /= 2L;
            if (n6 == 0L) {
                n6 = 1073741824L;
                --n5;
            }
            if ((this.biExp[n5] & n6) != 0x0L) {
                this.JS_JW(n, n2, n3, n4);
            }
        } while (n5 > 0 || n6 != 1L);
    }
    
    void add3(final long[] array, final long[] array2, final long[] array3, final long[] array4, final long[] array5, final long[] array6, final long[] array7, final long[] array8) {
        final long[] fieldTX = this.fieldTX;
        final long[] fieldTZ = this.fieldTZ;
        final long[] fieldUX = this.fieldUX;
        final long[] fieldUZ = this.fieldUZ;
        this.SubtractBigNbrModN(array3, array4, fieldUX);
        this.AddBigNbrModN(array5, array6, fieldUZ);
        this.MontgomeryMult(fieldUX, fieldUZ, fieldTZ);
        this.AddBigNbrModN(array3, array4, fieldUZ);
        this.SubtractBigNbrModN(array5, array6, fieldTX);
        this.MontgomeryMult(fieldTX, fieldUZ, fieldUX);
        this.AddBigNbrModN(fieldTZ, fieldUX, fieldTX);
        this.MontgomeryMult(fieldTX, fieldTX, fieldUZ);
        this.SubtractBigNbrModN(fieldTZ, fieldUX, fieldTX);
        this.MontgomeryMult(fieldTX, fieldTX, fieldUX);
        if (this.BigNbrAreEqual(array7, array)) {
            System.arraycopy(array7, 0, fieldTZ, 0, this.NumberLength);
            System.arraycopy(fieldUZ, 0, fieldTX, 0, this.NumberLength);
            this.MontgomeryMult(array8, fieldTX, fieldUZ);
            this.MontgomeryMult(fieldUX, fieldTZ, array2);
            System.arraycopy(fieldUZ, 0, array, 0, this.NumberLength);
            return;
        }
        this.MontgomeryMult(fieldUZ, array8, array);
        this.MontgomeryMult(array7, fieldUX, array2);
    }
    
    public String StartFactorExprBatch(final String inputStr, final int n) {
        this.batchFinished = false;
        this.inputStr = inputStr;
        this.batchPrime = (n == 1);
        (this.calcThread = new Thread(this)).start();
        return "";
    }
    
    void duplicate(final long[] array, final long[] array2, final long[] array3, final long[] array4) {
        final long[] fieldUZ = this.fieldUZ;
        final long[] fieldTX = this.fieldTX;
        final long[] fieldTZ = this.fieldTZ;
        this.AddBigNbrModN(array3, array4, fieldTZ);
        this.MontgomeryMult(fieldTZ, fieldTZ, fieldUZ);
        this.SubtractBigNbrModN(array3, array4, fieldTZ);
        this.MontgomeryMult(fieldTZ, fieldTZ, fieldTX);
        this.MontgomeryMult(fieldUZ, fieldTX, array);
        this.SubtractBigNbrModN(fieldUZ, fieldTX, fieldTZ);
        this.MontgomeryMult(this.fieldAA, fieldTZ, fieldUZ);
        this.AddBigNbrModN(fieldUZ, fieldTX, fieldUZ);
        this.MontgomeryMult(fieldTZ, fieldUZ, array2);
    }
    
    boolean BigNbrAreEqual(final long[] array, final long[] array2) {
        for (int i = 0; i < this.NumberLength; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    void InsertLucasFactor(final int n, final BigInteger bigInteger) {
        if (this.onlyFactoring) {
            final BigInteger bigInt0 = ecm.BigInt0;
            final BigInteger value = BigInteger.valueOf(5L);
            for (int n2 = 1; n2 * n2 <= n; ++n2) {
                if (n % n2 == 0) {
                    final BigInteger gcd = this.Lucas(n2).gcd(bigInteger);
                    this.InsertFactor(gcd);
                    if (n2 % 5 == 0) {
                        final BigInteger fibonacci = this.Fibonacci(n2);
                        this.InsertFactor(value.multiply(fibonacci).subtract(value).multiply(fibonacci).add(ecm.BigInt1));
                        this.InsertFactor(value.multiply(fibonacci).add(value).multiply(fibonacci).add(ecm.BigInt1));
                    }
                    else {
                        this.InsertFactor(gcd);
                        this.InsertFactor(bigInteger.divide(gcd));
                    }
                    final BigInteger gcd2 = this.Lucas(n / n2).gcd(bigInteger);
                    this.InsertFactor(gcd2);
                    if (n / n2 % 5 == 0) {
                        final BigInteger fibonacci2 = this.Fibonacci(n / n2);
                        this.InsertFactor(value.multiply(fibonacci2).subtract(value).multiply(fibonacci2).add(ecm.BigInt1));
                        this.InsertFactor(value.multiply(fibonacci2).add(value).multiply(fibonacci2).add(ecm.BigInt1));
                    }
                    else {
                        this.InsertFactor(gcd2);
                        this.InsertFactor(bigInteger.divide(gcd2));
                    }
                }
            }
        }
    }
    
    void GetAurifeuilleFactor(final int n, final BigInteger bigInteger) {
        if (this.onlyFactoring) {
            final BigInteger pow = bigInteger.pow(n);
            BigInteger bigInteger2;
            BigInteger add = bigInteger2 = ecm.BigInt1;
            int i;
            for (i = 1; i < this.DegreeAurif; ++i) {
                bigInteger2 = bigInteger2.multiply(pow).add(BigInteger.valueOf(this.Gamma[i]));
                add = add.multiply(pow).add(BigInteger.valueOf(this.Delta[i]));
            }
            final BigInteger add2 = bigInteger2.multiply(pow).add(BigInteger.valueOf(this.Gamma[i]));
            final BigInteger multiply = add.multiply(bigInteger.pow((n + 1) / 2));
            this.InsertFactor(add2.add(multiply));
            this.InsertFactor(add2.subtract(multiply));
        }
    }
    
    void MultiplyAByMatrix(final int[][] array, final int[] array2, final int[] array3, final int[] array4) {
        for (int i = array.length - 1; i >= 0; --i) {
            array3[i] = 0;
        }
        for (int j = array.length - 1; j >= 0; --j) {
            final int[] array5 = array[j];
            for (int k = array5.length - 1; k >= 0; --k) {
                final int n = array5[k];
                array3[n] ^= array2[j];
            }
        }
        for (int l = array.length - 1; l >= 0; --l) {
            int n2 = 0;
            final int[] array6 = array[l];
            for (int n3 = array6.length - 1; n3 >= 0; --n3) {
                n2 ^= array3[array6[n3]];
            }
            array4[l] = n2;
        }
    }
    
    static void GenerateSieve(final int n, final byte[] array, final byte[] array2, final int[] array3) {
        int i = 0;
        do {
            System.arraycopy(array2, 0, array, i, 2310);
            i += 2310;
        } while (i < 23100);
        int n2 = 5;
        int j = 13;
        do {
            if (n > j * j) {
                for (int k = n * ((j - 1) / 2) % j; k < 23100; k += j) {
                    array[k] = 1;
                }
            }
            else {
                final int n3 = j * j - n;
                if (n3 >= 46200) {
                    break;
                }
                for (int l = n3 / 2; l < 23100; l += j) {
                    array[l] = 1;
                }
            }
            j = array3[++n2];
        } while (j < 5000);
    }
    
    void Convert31To32Bits(final long[] array, final long[] array2) {
        int i = 0;
        while (true) {
            for (int j = -1; j < this.NumberLength; ++j) {
                final int n = i % 31;
                if (n == 0) {
                    ++j;
                }
                if (j == this.NumberLength) {
                    while (i < this.NumberLength) {
                        array2[i] = 0L;
                        ++i;
                    }
                    return;
                }
                if (j == this.NumberLength - 1) {
                    array2[i] = array[j] >> n;
                }
                else {
                    array2[i] = ((array[j] >> n | array[j + 1] << 31 - n) & 0xFFFFFFFFL);
                }
                ++i;
            }
            continue;
        }
    }
    
    void Convert32To31Bits(final long[] array, final long[] array2) {
        int n = 0;
        array[this.NumberLength] = 0L;
        for (int i = 0; i < this.NumberLength; ++i) {
            final int n2 = i % 32;
            if (n2 == 0) {
                array2[i] = (array[n] & 0x7FFFFFFFL);
            }
            else {
                array2[i] = ((array[n] >> 32 - n2 | array[n + 1] << n2) & 0x7FFFFFFFL);
                ++n;
            }
        }
    }
    
    void SubtractBigNbr32(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        long n = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n = (n >> 32) + array[i] - array2[i];
            array3[i] = (n & 0xFFFFFFFFL);
        }
    }
    
    void SortFactorsInputNbr() {
        for (int i = 0; i < this.NbrFactors - 1; ++i) {
            for (int j = i + 1; j < this.NbrFactors; ++j) {
                if (this.PD[i].compareTo(this.PD[j]) > 0) {
                    final BigInteger bigInteger = this.PD[i];
                    this.PD[i] = this.PD[j];
                    this.PD[j] = bigInteger;
                    final int n = this.Exp[i];
                    this.Exp[i] = this.Exp[j];
                    this.Exp[j] = n;
                    final int n2 = this.Typ[i];
                    this.Typ[i] = this.Typ[j];
                    this.Typ[j] = n2;
                }
            }
        }
    }
    
    BigInteger BigIntToBigNbr(final long[] array) {
        final long[] array2 = new long[this.NumberLength];
        this.Convert31To32Bits(array, array2);
        final int n = this.NumberLength * 4;
        final byte[] array3 = new byte[n];
        for (int i = 0; i < this.NumberLength; ++i) {
            final long n2 = array2[i];
            array3[n - 1 - 4 * i] = (byte)(n2 & 0xFFL);
            array3[n - 2 - 4 * i] = (byte)(n2 / 256L & 0xFFL);
            array3[n - 3 - 4 * i] = (byte)(n2 / 65536L & 0xFFL);
            array3[n - 4 - 4 * i] = (byte)(n2 / 16777216L & 0xFFL);
        }
        return new BigInteger(array3);
    }
    
    public int getFactors(final BigInteger bigInteger, final BigInteger[] array, final int[] array2) {
        this.layout("Number to factor:", false);
        this.textNumber.setText(bigInteger.toString());
        this.startNewFactorization(true);
        System.arraycopy(this.PD, 0, array, 0, this.NbrFactors);
        System.arraycopy(this.Exp, 0, array2, 0, this.NbrFactors);
        return this.NbrFactors;
    }
    
    void LongToBigNbr(final long n, final long[] array) {
        array[0] = (n & 0x7FFFFFFFL);
        array[1] = (n >> 31 & 0x7FFFFFFFL);
        for (int i = 2; i < this.NumberLength; ++i) {
            array[i] = ((n < 0L) ? 2147483647L : 0L);
        }
    }
    
    void GcdBigNbr(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        System.arraycopy(array, 0, this.CalcAuxGcdU, 0, numberLength);
        System.arraycopy(array2, 0, this.CalcAuxGcdV, 0, numberLength);
        int n;
        for (n = 0; n < numberLength && this.CalcAuxGcdU[n] == 0L; ++n) {}
        if (n == numberLength) {
            System.arraycopy(this.CalcAuxGcdV, 0, array3, 0, numberLength);
            return;
        }
        int n2;
        for (n2 = 0; n2 < numberLength && this.CalcAuxGcdV[n2] == 0L; ++n2) {}
        if (n2 == numberLength) {
            System.arraycopy(this.CalcAuxGcdU, 0, array3, 0, numberLength);
            return;
        }
        if (this.CalcAuxGcdU[numberLength - 1] >= 1073741824L) {
            this.ChSignBigNbr(this.CalcAuxGcdU);
        }
        if (this.CalcAuxGcdV[numberLength - 1] >= 1073741824L) {
            this.ChSignBigNbr(this.CalcAuxGcdV);
        }
        int i = 0;
        while ((this.CalcAuxGcdU[0] & 0x1L) == 0x0L && (this.CalcAuxGcdV[0] & 0x1L) == 0x0L) {
            ++i;
            this.DivBigNbrByLong(this.CalcAuxGcdU, 2L, this.CalcAuxGcdU);
            this.DivBigNbrByLong(this.CalcAuxGcdV, 2L, this.CalcAuxGcdV);
        }
        if ((this.CalcAuxGcdU[0] & 0x1L) == 0x1L) {
            System.arraycopy(this.CalcAuxGcdV, 0, this.CalcAuxGcdT, 0, numberLength);
            this.ChSignBigNbr(this.CalcAuxGcdT);
        }
        else {
            System.arraycopy(this.CalcAuxGcdU, 0, this.CalcAuxGcdT, 0, numberLength);
        }
        while (true) {
            if ((this.CalcAuxGcdT[0] & 0x1L) != 0x0L) {
                if (this.CalcAuxGcdT[numberLength - 1] < 1073741824L) {
                    System.arraycopy(this.CalcAuxGcdT, 0, this.CalcAuxGcdU, 0, numberLength);
                }
                else {
                    System.arraycopy(this.CalcAuxGcdT, 0, this.CalcAuxGcdV, 0, numberLength);
                    this.ChSignBigNbr(this.CalcAuxGcdV);
                }
                this.SubtractBigNbr(this.CalcAuxGcdU, this.CalcAuxGcdV, this.CalcAuxGcdT);
                int n3;
                for (n3 = 0; n3 < numberLength && this.CalcAuxGcdT[n3] == 0L; ++n3) {}
                if (n3 == numberLength) {
                    break;
                }
                continue;
            }
            else {
                this.DivBigNbrByLong(this.CalcAuxGcdT, 2L, this.CalcAuxGcdT);
            }
        }
        System.arraycopy(this.CalcAuxGcdU, 0, array3, 0, numberLength);
        while (i > 0) {
            this.AddBigNbr(array3, array3, array3);
            --i;
        }
    }
    
    void JS_2(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n2; ++i) {
            final int n5 = 2 * i % n;
            this.MontgomeryMult(this.aiJS[i], this.aiJS[i], this.biTmp);
            this.AddBigNbrModN(this.aiJX[n5], this.biTmp, this.aiJX[n5]);
            this.AddBigNbrModN(this.aiJS[i], this.aiJS[i], this.biT);
            for (int j = i + 1; j < n2; ++j) {
                final int n6 = (i + j) % n;
                this.MontgomeryMult(this.biT, this.aiJS[j], this.biTmp);
                this.AddBigNbrModN(this.aiJX[n6], this.biTmp, this.aiJX[n6]);
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < this.NumberLength; ++l) {
                this.aiJS[k][l] = this.aiJX[k][l];
                this.aiJX[k][l] = 0L;
            }
        }
        this.NormalizeJS(n, n2, n3, n4);
    }
    
    void SortFactors() {
        for (int i = 0; i < this.NroFact - 1; ++i) {
            for (int j = i + 1; j < this.NroFact; ++j) {
                if (this.Factores[i].compareTo(this.Factores[j]) > 0) {
                    final BigInteger bigInteger = this.Factores[i];
                    this.Factores[i] = this.Factores[j];
                    this.Factores[j] = bigInteger;
                }
            }
        }
        int k;
        for (k = 0; k < this.NroFact; ++k) {
            this.PD[this.NbrFactors + k - 1] = this.Factores[k];
            this.Exp[this.NbrFactors + k - 1] = 1;
            this.Typ[this.NbrFactors + k - 1] = -1;
        }
        this.NbrFactors += k - 1;
    }
    
    void NormalizeJW(final int n, final int n2, final int n3, final int n4) {
        for (int i = n2; i < n; ++i) {
            if (!this.BigNbrIsZero(this.aiJW[i])) {
                for (int j = 0; j < this.NumberLength; ++j) {
                    this.biT[j] = this.aiJW[i][j];
                }
                for (int k = 1; k < n4; ++k) {
                    this.SubtractBigNbrModN(this.aiJW[i - k * n3], this.biT, this.aiJW[i - k * n3]);
                }
                for (int l = 0; l < this.NumberLength; ++l) {
                    this.aiJW[i][l] = 0L;
                }
            }
        }
    }
    
    boolean InsertNewRelation(final long[] array, final long[] array2, final long[] array3, final int n, final int[][] array4, final int[] array5, final int n2, final long[][] array6) {
        if ((this.TestNbr[0] & 0x1L) == 0x0L) {
            this.DivBigNbrByLong(this.TestNbr, 2L, this.TestNbr);
            for (int i = 0; i < this.NumberLength; ++i) {
                array2[i] = 0L;
            }
            this.AddBigNbrModN(array, array2, array);
            --this.NumberLength;
            this.ModInvBigNbr(array, array2, this.TestNbr);
            ++this.NumberLength;
            this.MultBigNbrByLong(this.TestNbr, 2L, this.TestNbr);
        }
        else {
            this.ModInvBigNbr(array, array2, this.TestNbr);
        }
        if ((array3[this.NumberLength - 1] & 0x40000000L) != 0x0L) {
            this.AddBigNbr(array3, this.TestNbr, array3);
        }
        --this.NumberLength;
        this.MultBigNbrModN(array3, array2, array);
        ++this.NumberLength;
        int j;
        for (j = 0; j < n2; ++j) {
            if (n <= array4[j].length) {
                if (n == array4[j].length) {
                    int n3;
                    for (n3 = 0; n3 < n && array5[n3] == array4[j][n3]; ++n3) {}
                    if (n3 == n) {
                        return false;
                    }
                    if (array5[n3] > array4[j][n3]) {
                        continue;
                    }
                }
                for (int k = n2 - 1; k >= j; --k) {
                    array4[k + 1] = array4[k];
                    array6[k + 1] = array6[k];
                }
                break;
            }
        }
        array4[j] = new int[n];
        for (int l = 0; l < n; ++l) {
            array4[j][l] = array5[l];
        }
        array6[j] = new long[this.NumberLength];
        for (int n4 = 0; n4 < this.NumberLength; ++n4) {
            array6[j][n4] = array[n4];
        }
        return true;
    }
    
    void MatrixAddition(final int[] array, final int[] array2, final int[] array3) {
        for (int i = array.length - 1; i >= 0; --i) {
            array3[i] = (array[i] ^ array2[i]);
        }
    }
    
    String GetDHMS(final long n) {
        return n / 86400L + "d " + n % 86400L / 3600L + "h " + n % 3600L / 60L + "m " + n % 60L + "s";
    }
    
    void prac(final int n, final long[] array, final long[] array2, long[] array3, long[] array4, long[] array5, long[] array6) {
        long[] array7 = array;
        long[] array8 = array2;
        long[] fieldAux1 = this.fieldAux1;
        long[] fieldAux2 = this.fieldAux2;
        long[] fieldAux3 = this.fieldAux3;
        long[] fieldAux4 = this.fieldAux4;
        final double[] array9 = { 1.61803398875, 1.72360679775, 1.618347119656, 1.617914406529, 1.612429949509, 1.632839806089, 1.620181980807, 1.580178728295, 1.617214616534, 1.38196601125 };
        int lucas_cost = lucas_cost(n, array9[0]);
        int n2 = 0;
        int n3 = 1;
        do {
            final int lucas_cost2 = lucas_cost(n, array9[n3]);
            if (lucas_cost2 < lucas_cost) {
                lucas_cost = lucas_cost2;
                n2 = n3;
            }
        } while (++n3 < 10);
        final int n4 = (int)(n / array9[n2] + 0.5);
        int i = n - n4;
        int n5 = 2 * n4 - n;
        System.arraycopy(array7, 0, fieldAux1, 0, this.NumberLength);
        System.arraycopy(array8, 0, fieldAux2, 0, this.NumberLength);
        System.arraycopy(array7, 0, fieldAux3, 0, this.NumberLength);
        System.arraycopy(array8, 0, fieldAux4, 0, this.NumberLength);
        this.duplicate(array7, array8, array7, array8);
        while (i != n5) {
            if (i < n5) {
                final int n6 = i;
                i = n5;
                n5 = n6;
                final long[] array10 = array7;
                array7 = fieldAux1;
                fieldAux1 = array10;
                final long[] array11 = array8;
                array8 = fieldAux2;
                fieldAux2 = array11;
            }
            if (4 * i <= 5 * n5 && (i + n5) % 3 == 0) {
                final int n7 = (2 * i - n5) / 3;
                n5 = (2 * n5 - i) / 3;
                i = n7;
                this.add3(array3, array4, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
                this.add3(array5, array6, array3, array4, array7, array8, fieldAux1, fieldAux2);
                this.add3(fieldAux1, fieldAux2, fieldAux1, fieldAux2, array3, array4, array7, array8);
                final long[] array12 = array7;
                array7 = array5;
                array5 = array12;
                final long[] array13 = array8;
                array8 = array6;
                array6 = array13;
            }
            else if (4 * i <= 5 * n5 && (i - n5) % 6 == 0) {
                i = (i - n5) / 2;
                this.add3(fieldAux1, fieldAux2, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
                this.duplicate(array7, array8, array7, array8);
            }
            else if (i <= 4 * n5) {
                i -= n5;
                this.add3(array3, array4, fieldAux1, fieldAux2, array7, array8, fieldAux3, fieldAux4);
                final long[] array14 = fieldAux1;
                fieldAux1 = array3;
                array3 = fieldAux3;
                fieldAux3 = array14;
                final long[] array15 = fieldAux2;
                fieldAux2 = array4;
                array4 = fieldAux4;
                fieldAux4 = array15;
            }
            else if ((i + n5) % 2 == 0) {
                i = (i - n5) / 2;
                this.add3(fieldAux1, fieldAux2, fieldAux1, fieldAux2, array7, array8, fieldAux3, fieldAux4);
                this.duplicate(array7, array8, array7, array8);
            }
            else if (i % 2 == 0) {
                i /= 2;
                this.add3(fieldAux3, fieldAux4, fieldAux3, fieldAux4, array7, array8, fieldAux1, fieldAux2);
                this.duplicate(array7, array8, array7, array8);
            }
            else if (i % 3 == 0) {
                i = i / 3 - n5;
                this.duplicate(array3, array4, array7, array8);
                this.add3(array5, array6, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
                this.add3(array7, array8, array3, array4, array7, array8, array7, array8);
                this.add3(array3, array4, array3, array4, array5, array6, fieldAux3, fieldAux4);
                final long[] array16 = fieldAux3;
                fieldAux3 = fieldAux1;
                fieldAux1 = array3;
                array3 = array16;
                final long[] array17 = fieldAux4;
                fieldAux4 = fieldAux2;
                fieldAux2 = array4;
                array4 = array17;
            }
            else if ((i + n5) % 3 == 0) {
                i = (i - 2 * n5) / 3;
                this.add3(array3, array4, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
                this.add3(fieldAux1, fieldAux2, array3, array4, array7, array8, fieldAux1, fieldAux2);
                this.duplicate(array3, array4, array7, array8);
                this.add3(array7, array8, array7, array8, array3, array4, array7, array8);
            }
            else if ((i - n5) % 3 == 0) {
                i = (i - n5) / 3;
                this.add3(array3, array4, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
                this.add3(fieldAux3, fieldAux4, fieldAux3, fieldAux4, array7, array8, fieldAux1, fieldAux2);
                final long[] array18 = fieldAux1;
                fieldAux1 = array3;
                array3 = array18;
                final long[] array19 = fieldAux2;
                fieldAux2 = array4;
                array4 = array19;
                this.duplicate(array3, array4, array7, array8);
                this.add3(array7, array8, array7, array8, array3, array4, array7, array8);
            }
            else {
                if (n5 % 2 != 0) {
                    continue;
                }
                n5 /= 2;
                this.add3(fieldAux3, fieldAux4, fieldAux3, fieldAux4, fieldAux1, fieldAux2, array7, array8);
                this.duplicate(fieldAux1, fieldAux2, fieldAux1, fieldAux2);
            }
        }
        this.add3(array, array2, array7, array8, fieldAux1, fieldAux2, fieldAux3, fieldAux4);
    }
    
    void AddBigNbr(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        long n = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n = (n >> 31) + array[i] + array2[i];
            array3[i] = (n & 0x7FFFFFFFL);
        }
    }
    
    long BigNbrModLong(final long[] array, final long n) {
        long n2 = 0L;
        for (int i = this.NumberLength - 1; i >= 0; --i) {
            n2 = ((n2 << 31) + array[i]) % n;
        }
        return n2;
    }
    
    void DivBigNbrByLong(final long[] array, long n, final long[] array2) {
        boolean b = false;
        long n2 = 0L;
        if (n < 0L) {
            b = true;
            n = -n;
        }
        int i;
        if (array[i = this.NumberLength - 1] >= 1073741824L) {
            n2 = n - 1L;
        }
        while (i >= 0) {
            final long n3 = array[i] + (n2 << 31);
            n2 = n3 % n;
            array2[i] = n3 / n;
            --i;
        }
        if (b) {
            this.ChSignBigNbr(array2);
        }
    }
    
    long RemDivBigNbrByLong(final long[] array, long n) {
        long n2 = 0L;
        if (n < 0L) {
            n = -n;
        }
        int i;
        if (array[i = this.NumberLength - 1] >= 1073741824L) {
            n2 = n - 1L;
        }
        while (i >= 0) {
            n2 = (array[i] + (n2 << 31)) % n;
            --i;
        }
        return n2;
    }
    
    void InsertAurifFactors(final BigInteger bigInteger, int n, int n2) {
        if (bigInteger.compareTo(BigInteger.valueOf(386L)) <= 0) {
            final int intValue = bigInteger.intValue();
            if (n % 2 == 0 && n2 == -1) {
                do {
                    n /= 2;
                } while (n % 2 == 0);
                n2 = intValue % 4 - 2;
            }
            if (n % intValue == 0 && n / intValue % 2 != 0 && ((intValue % 4 != 1 && n2 == 1) || (intValue % 4 == 1 && n2 == -1))) {
                final int n3 = intValue;
                int n4;
                if (n3 % 4 == 1) {
                    n4 = n3;
                }
                else {
                    n4 = 2 * n3;
                }
                if (n3 % 4 == 3) {}
                if (n3 % 8 == 5) {}
                this.DegreeAurif = this.Totient(n4) / 2;
                for (int i = 1; i <= this.DegreeAurif; i += 2) {
                    this.AurifQ[i] = this.JacobiSymbol(n3, i);
                }
                for (int j = 2; j <= this.DegreeAurif; j += 2) {
                    int k = j;
                    int n5 = n4;
                    while (k != 0) {
                        final int n6 = n5 % k;
                        n5 = k;
                        k = n6;
                    }
                    this.AurifQ[j] = this.Moebius(n4 / n5) * this.Totient(n5) * Cos((n3 - 1) * j);
                }
                this.Gamma[0] = (this.Delta[0] = 1L);
                for (int l = 1; l <= this.DegreeAurif / 2; ++l) {
                    this.Gamma[l] = (this.Delta[l] = 0L);
                    for (int n7 = 0; n7 < l; ++n7) {
                        this.Gamma[l] = this.Gamma[l] + n3 * this.AurifQ[2 * l - 2 * n7 - 1] * this.Delta[n7] - this.AurifQ[2 * l - 2 * n7] * this.Gamma[n7];
                        this.Delta[l] = this.Delta[l] + this.AurifQ[2 * l + 1 - 2 * n7] * this.Gamma[n7] - this.AurifQ[2 * l - 2 * n7] * this.Delta[n7];
                    }
                    final long[] gamma = this.Gamma;
                    final int n8 = l;
                    gamma[n8] /= 2 * l;
                    this.Delta[l] = (this.Delta[l] + this.Gamma[l]) / (2 * l + 1);
                }
                for (int n9 = this.DegreeAurif / 2 + 1; n9 <= this.DegreeAurif; ++n9) {
                    this.Gamma[n9] = this.Gamma[this.DegreeAurif - n9];
                }
                for (int n10 = (this.DegreeAurif + 1) / 2; n10 < this.DegreeAurif; ++n10) {
                    this.Delta[n10] = this.Delta[this.DegreeAurif - n10 - 1];
                }
                for (int n11 = n / intValue, n12 = 1; n12 * n12 <= n11; n12 += 2) {
                    if (n11 % n12 == 0) {
                        this.GetAurifeuilleFactor(n12, bigInteger);
                        if (n11 != n12 * n12) {
                            this.GetAurifeuilleFactor(n11 / n12, bigInteger);
                        }
                    }
                }
            }
        }
    }
    
    void MatrixMultAdd(final int[] array, final int[] array2, final int[] array3) {
        for (int length = array.length, i = 0; i < length; ++i) {
            int n = array3[i];
            for (int j = array[i], n2 = 0; j != 0; j *= 2, ++n2) {
                if (j < 0) {
                    n ^= array2[n2];
                }
            }
            array3[i] = n;
        }
    }
    
    public ecm() {
        this.onlyFactoring = true;
        this.digitsInGroup = 6;
        this.SS = new BigInteger[4000];
        this.PD = new BigInteger[4000];
        this.Exp = new int[4000];
        this.Typ = new int[4000];
        this.PD1 = new BigInteger[4000];
        this.Exp1 = new int[4000];
        this.Typ1 = new int[4000];
        this.aiIndx = new int[30241];
        this.aiF = new int[30241];
        this.aiInv = new int[32];
        this.biTmp = new long[1200];
        this.biExp = new long[1200];
        this.biN = new long[1200];
        this.biR = new long[1200];
        this.biS = new long[1200];
        this.biT = new long[1200];
        this.biU = new long[1200];
        this.biV = new long[1200];
        this.biW = new long[1200];
        this.aiJS = new long[32][1200];
        this.aiJW = new long[32][1200];
        this.aiJX = new long[32][1200];
        this.aiJ0 = new long[32][1200];
        this.aiJ1 = new long[32][1200];
        this.aiJ2 = new long[32][1200];
        this.aiJ00 = new long[32][1200];
        this.aiJ01 = new long[32][1200];
        this.CalcAuxGcdU = new long[1200];
        this.CalcAuxGcdV = new long[1200];
        this.CalcAuxGcdT = new long[1200];
        this.CalcBigNbr = new long[1200];
        this.TestNbr = new long[1200];
        this.TestNbr2 = new long[1200];
        this.GcdAccumulated = new long[1200];
        this.Gamma = new long[386];
        this.Delta = new long[386];
        this.AurifQ = new long[386];
        this.Factores = new BigInteger[200];
        this.BigNbr1 = new long[1200];
        this.SmallPrime = new int[670];
        this.MontgomeryMultR1 = new long[1200];
        this.MontgomeryMultR2 = new long[1200];
        this.MontgomeryMultAfterInv = new long[1200];
        this.ProbArray = new double[6];
        this.batchFinished = true;
        this.batchPrime = false;
        this.TerminateThread = true;
        this.NextEC = -1;
        this.InputFactor = ecm.BigInt0;
    }
    
    int AprtCle(final BigInteger bigInteger) {
        String string = "";
        this.lowerTextArea.setText("Starting Prime Check routine.");
        this.BigNbrToBigInt(bigInteger);
        this.GetMontgomeryParms();
        if (!this.Computing3Squares) {
            this.textAreaContents = "";
            this.StringToLabel = "Testing primality of ";
            this.insertBigNbr(bigInteger);
            this.addStringToLabel("(" + bigInteger.toString().length() + " digits)");
            string = this.textAreaContents + this.StringToLabel + "\nAPRT-CLE progress: ";
        }
        for (int i = 0; i < this.NumberLength; ++i) {
            this.biS[i] = 0L;
            int n = 0;
            do {
                this.aiJX[n][i] = 0L;
            } while (++n < 32);
        }
        int n2 = 0;
        int k = 0;
    Label_0302:
        do {
            this.biS[0] = 2L;
            for (int j = 1; j < this.NumberLength; ++j) {
                this.biS[j] = 0L;
            }
            for (k = 0; k < ecm.aiNQ[n2]; ++k) {
                final int n3 = ecm.aiQ[k];
                int n4 = ecm.aiT[n2] * n3;
                do {
                    n4 /= n3;
                    this.MultBigNbrByLong(this.biS, n3, this.biS);
                } while (n4 % n3 == 0);
                if (this.CompareSquare(this.biS, this.TestNbr) > 0) {
                    break Label_0302;
                }
            }
        } while (++n2 < 11);
        if (n2 == 11) {
            return this.ProbabilisticPrimeTest(bigInteger);
        }
        int n5 = n2;
        int n6 = k;
        int n7 = ecm.aiT[n5];
        int n8 = ecm.aiNP[n5];
        Label_0336: {
            break Label_0336;
            int l = 0;
        Label_4076:
            do {
                final int n9 = ecm.aiP[l];
                int n11;
                int n10 = n11 = 0;
                final int n13;
                int n12 = n13 = (int)this.BigNbrModLong(this.TestNbr, n9 * n9);
                for (int n14 = n9 - 2; n14 > 0; --n14) {
                    n12 = n12 * n13 % (n9 * n9);
                }
                if (n9 > 2 && n12 != 1) {
                    n11 = 1;
                }
                while (true) {
                    for (int n15 = n10; n15 <= n6; ++n15) {
                        int n16 = ecm.aiQ[n15] - 1;
                        final int n17 = ecm.aiG[n15];
                        int n18 = 0;
                        while (n16 % n9 == 0) {
                            ++n18;
                            n16 /= n9;
                        }
                        final int n19 = ecm.aiQ[n15];
                        if (n18 != 0) {
                            if (!this.Computing3Squares) {
                                this.lowerTextArea.setText(string + "P = " + n9 + ",  Q = " + n19 + "  (" + (l * (n6 + 1) + n15) * 100 / (n8 * (n6 + 1)) + "%)");
                            }
                            int n20 = 1;
                            for (int n21 = 1; n21 < n18; ++n21) {
                                n20 *= n9;
                            }
                            final int n22 = (n9 - 1) * n20;
                            final int n23 = n9 * n20;
                            int n24 = 1;
                            for (int n25 = 1; n25 < n19; ++n25) {
                                n24 = n24 * n17 % n19;
                                this.aiIndx[n24] = n25;
                            }
                            int n26 = 1;
                            for (int n27 = 1; n27 <= n19 - 2; ++n27) {
                                n26 = n26 * n17 % n19;
                                this.aiF[n27] = this.aiIndx[(n19 + 1 - n26) % n19];
                            }
                            for (int n28 = 0; n28 < n23; ++n28) {
                                for (int n29 = 0; n29 < this.NumberLength; ++n29) {
                                    this.aiJ0[n28][n29] = (this.aiJ1[n28][n29] = 0L);
                                }
                            }
                            if (n9 > 2) {
                                this.JacobiSum(1, 1, n9, n23, n22, n20, n19);
                            }
                            else if (n18 != 1) {
                                this.JacobiSum(1, 1, n9, n23, n22, n20, n19);
                                for (int n30 = 0; n30 < n23; ++n30) {
                                    for (int n31 = 0; n31 < this.NumberLength; ++n31) {
                                        this.aiJW[n30][n31] = 0L;
                                    }
                                }
                                if (n18 != 2) {
                                    for (int n32 = 0; n32 < n20; ++n32) {
                                        for (int n33 = 0; n33 < this.NumberLength; ++n33) {
                                            this.aiJW[n32][n33] = this.aiJ0[n32][n33];
                                        }
                                    }
                                    this.JacobiSum(2, 1, n9, n23, n22, n20, n19);
                                    for (int n34 = 0; n34 < n20; ++n34) {
                                        for (int n35 = 0; n35 < this.NumberLength; ++n35) {
                                            this.aiJS[n34][n35] = this.aiJ0[n34][n35];
                                        }
                                    }
                                    this.JS_JW(n23, n22, n20, n9);
                                    this.NormalizeJS(n23, n22, n20, n9);
                                    for (int n36 = 0; n36 < n20; ++n36) {
                                        for (int n37 = 0; n37 < this.NumberLength; ++n37) {
                                            this.aiJ1[n36][n37] = this.aiJS[n36][n37];
                                        }
                                    }
                                    this.JacobiSum(3 << n18 - 3, 1 << n18 - 3, n9, n23, n22, n20, n19);
                                    for (int n38 = 0; n38 < this.NumberLength; ++n38) {
                                        for (int n39 = 0; n39 < n23; ++n39) {
                                            this.aiJW[n39][n38] = 0L;
                                        }
                                        for (int n40 = 0; n40 < n20; ++n40) {
                                            this.aiJS[n40][n38] = this.aiJ0[n40][n38];
                                        }
                                    }
                                    this.JS_2(n23, n22, n20, n9);
                                    this.NormalizeJS(n23, n22, n20, n9);
                                    for (int n41 = 0; n41 < n20; ++n41) {
                                        for (int n42 = 0; n42 < this.NumberLength; ++n42) {
                                            this.aiJ2[n41][n42] = this.aiJS[n41][n42];
                                        }
                                    }
                                }
                            }
                            for (int n43 = 0; n43 < this.NumberLength; ++n43) {
                                this.aiJ00[0][n43] = (this.aiJ01[0][n43] = this.MontgomeryMultR1[n43]);
                                for (int n44 = 1; n44 < n23; ++n44) {
                                    this.aiJ00[n44][n43] = (this.aiJ01[n44][n43] = 0L);
                                }
                            }
                            final int n45 = (int)this.BigNbrModLong(this.TestNbr, n23);
                            for (int n46 = 1; n46 < n23; ++n46) {
                                if (n46 % n9 != 0) {
                                    int n47 = 1;
                                    int n48 = n46;
                                    int n49 = 0;
                                    int n53;
                                    for (int n50 = n23; n50 != 0; n50 = n53) {
                                        final int n51 = n48 / n50;
                                        final int n52 = n47 - n49 * n51;
                                        n53 = n48 - n50 * n51;
                                        n47 = n49;
                                        n48 = n50;
                                        n49 = n52;
                                    }
                                    this.aiInv[n46] = (n47 + n23) % n23;
                                }
                                else {
                                    this.aiInv[n46] = 0;
                                }
                            }
                            if (n9 != 2) {
                                int n54 = 0;
                                do {
                                    for (int n55 = 1; n55 < n23; ++n55) {
                                        for (int n56 = 0; n56 < n23; ++n56) {
                                            for (int n57 = 0; n57 < this.NumberLength; ++n57) {
                                                this.aiJS[n56][n57] = this.aiJ0[n56][n57];
                                            }
                                        }
                                        if (n55 % n9 != 0) {
                                            if (n54 == 0) {
                                                this.LongToBigNbr(n55, this.biExp);
                                            }
                                            else {
                                                this.LongToBigNbr(n45 * n55 / n23, this.biExp);
                                                if (n45 * n55 / n23 == 0) {
                                                    continue;
                                                }
                                            }
                                            this.JS_E(n23, n22, n20, n9);
                                            for (int n58 = 0; n58 < n23; ++n58) {
                                                for (int n59 = 0; n59 < this.NumberLength; ++n59) {
                                                    this.aiJW[n58][n59] = 0L;
                                                }
                                            }
                                            final int n60 = this.aiInv[n55];
                                            for (int n61 = 0; n61 < n23; ++n61) {
                                                final int n62 = n61 * n60 % n23;
                                                this.AddBigNbrModN(this.aiJW[n62], this.aiJS[n61], this.aiJW[n62]);
                                            }
                                            this.NormalizeJW(n23, n22, n20, n9);
                                            if (n54 == 0) {
                                                for (int n63 = 0; n63 < n23; ++n63) {
                                                    for (int n64 = 0; n64 < this.NumberLength; ++n64) {
                                                        this.aiJS[n63][n64] = this.aiJ00[n63][n64];
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n65 = 0; n65 < n23; ++n65) {
                                                    for (int n66 = 0; n66 < this.NumberLength; ++n66) {
                                                        this.aiJS[n65][n66] = this.aiJ01[n65][n66];
                                                    }
                                                }
                                            }
                                            this.JS_JW(n23, n22, n20, n9);
                                            if (n54 == 0) {
                                                for (int n67 = 0; n67 < n23; ++n67) {
                                                    for (int n68 = 0; n68 < this.NumberLength; ++n68) {
                                                        this.aiJ00[n67][n68] = this.aiJS[n67][n68];
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n69 = 0; n69 < n23; ++n69) {
                                                    for (int n70 = 0; n70 < this.NumberLength; ++n70) {
                                                        this.aiJ01[n69][n70] = this.aiJS[n69][n70];
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } while (++n54 <= 1);
                            }
                            else if (n18 == 1) {
                                this.MultBigNbrByLongModN(this.MontgomeryMultR1, n19, this.aiJ00[0]);
                                for (int n71 = 0; n71 < this.NumberLength; ++n71) {
                                    this.aiJ01[0][n71] = this.MontgomeryMultR1[n71];
                                }
                            }
                            else if (n18 == 2) {
                                if (n45 == 1) {
                                    for (int n72 = 0; n72 < this.NumberLength; ++n72) {
                                        this.aiJ01[0][n72] = this.MontgomeryMultR1[n72];
                                    }
                                }
                                for (int n73 = 0; n73 < this.NumberLength; ++n73) {
                                    this.aiJS[0][n73] = this.aiJ0[0][n73];
                                    this.aiJS[1][n73] = this.aiJ0[1][n73];
                                }
                                this.JS_2(n23, n22, n20, n9);
                                if (n45 == 3) {
                                    for (int n74 = 0; n74 < this.NumberLength; ++n74) {
                                        this.aiJ01[0][n74] = this.aiJS[0][n74];
                                        this.aiJ01[1][n74] = this.aiJS[1][n74];
                                    }
                                }
                                this.MultBigNbrByLongModN(this.aiJS[0], n19, this.aiJ00[0]);
                                this.MultBigNbrByLongModN(this.aiJS[1], n19, this.aiJ00[1]);
                            }
                            else {
                                int n75 = 0;
                                do {
                                    for (int n76 = 1; n76 < n23; n76 += 2) {
                                        for (int n77 = 0; n77 <= n20; ++n77) {
                                            for (int n78 = 0; n78 < this.NumberLength; ++n78) {
                                                this.aiJS[n77][n78] = this.aiJ1[n77][n78];
                                            }
                                        }
                                        if (n76 % 8 != 5 && n76 % 8 != 7) {
                                            if (n75 == 0) {
                                                this.LongToBigNbr(n76, this.biExp);
                                            }
                                            else {
                                                this.LongToBigNbr(n45 * n76 / n23, this.biExp);
                                                if (n45 * n76 / n23 == 0) {
                                                    continue;
                                                }
                                            }
                                            this.JS_E(n23, n22, n20, n9);
                                            for (int n79 = 0; n79 < n23; ++n79) {
                                                for (int n80 = 0; n80 < this.NumberLength; ++n80) {
                                                    this.aiJW[n79][n80] = 0L;
                                                }
                                            }
                                            final int n81 = this.aiInv[n76];
                                            for (int n82 = 0; n82 < n23; ++n82) {
                                                final int n83 = n82 * n81 % n23;
                                                this.AddBigNbrModN(this.aiJW[n83], this.aiJS[n82], this.aiJW[n83]);
                                            }
                                            this.NormalizeJW(n23, n22, n20, n9);
                                            if (n75 == 0) {
                                                for (int n84 = 0; n84 < n23; ++n84) {
                                                    for (int n85 = 0; n85 < this.NumberLength; ++n85) {
                                                        this.aiJS[n84][n85] = this.aiJ00[n84][n85];
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n86 = 0; n86 < n23; ++n86) {
                                                    for (int n87 = 0; n87 < this.NumberLength; ++n87) {
                                                        this.aiJS[n86][n87] = this.aiJ01[n86][n87];
                                                    }
                                                }
                                            }
                                            this.NormalizeJS(n23, n22, n20, n9);
                                            this.JS_JW(n23, n22, n20, n9);
                                            if (n75 == 0) {
                                                for (int n88 = 0; n88 < n23; ++n88) {
                                                    for (int n89 = 0; n89 < this.NumberLength; ++n89) {
                                                        this.aiJ00[n88][n89] = this.aiJS[n88][n89];
                                                    }
                                                }
                                            }
                                            else {
                                                for (int n90 = 0; n90 < n23; ++n90) {
                                                    for (int n91 = 0; n91 < this.NumberLength; ++n91) {
                                                        this.aiJ01[n90][n91] = this.aiJS[n90][n91];
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (n75 != 0 && n45 % 8 != 1 && n45 % 8 != 3) {
                                        int n92;
                                        for (n92 = 0; n92 < n20; ++n92) {
                                            for (int n93 = 0; n93 < this.NumberLength; ++n93) {
                                                this.aiJW[n92][n93] = this.aiJ2[n92][n93];
                                                this.aiJS[n92][n93] = this.aiJ01[n92][n93];
                                            }
                                        }
                                        while (n92 < n23) {
                                            for (int n94 = 0; n94 < this.NumberLength; ++n94) {
                                                this.aiJW[n92][n94] = (this.aiJS[n92][n94] = 0L);
                                            }
                                            ++n92;
                                        }
                                        this.JS_JW(n23, n22, n20, n9);
                                        for (int n95 = 0; n95 < n20; ++n95) {
                                            for (int n96 = 0; n96 < this.NumberLength; ++n96) {
                                                this.aiJ01[n95][n96] = this.aiJS[n95][n96];
                                            }
                                        }
                                    }
                                } while (++n75 <= 1);
                            }
                            int n97;
                            for (n97 = 0; n97 < n22; ++n97) {
                                for (int n98 = 0; n98 < this.NumberLength; ++n98) {
                                    this.aiJS[n97][n98] = this.aiJ00[n97][n98];
                                }
                            }
                            while (n97 < n23) {
                                for (int n99 = 0; n99 < this.NumberLength; ++n99) {
                                    this.aiJS[n97][n99] = 0L;
                                }
                                ++n97;
                            }
                            this.DivBigNbrByLong(this.TestNbr, n23, this.biExp);
                            this.JS_E(n23, n22, n20, n9);
                            for (int n100 = 0; n100 < n23; ++n100) {
                                for (int n101 = 0; n101 < this.NumberLength; ++n101) {
                                    this.aiJW[n100][n101] = 0L;
                                }
                            }
                            for (int n102 = 0; n102 < n22; ++n102) {
                                for (int n103 = 0; n103 < n22; ++n103) {
                                    this.MontgomeryMult(this.aiJS[n102], this.aiJ01[n103], this.biTmp);
                                    this.AddBigNbrModN(this.biTmp, this.aiJW[(n102 + n103) % n23], this.aiJW[(n102 + n103) % n23]);
                                }
                            }
                            this.NormalizeJW(n23, n22, n20, n9);
                            int n104 = -1;
                            int n105 = 0;
                            for (int n106 = 0; n106 < n22; ++n106) {
                                if (!this.BigNbrIsZero(this.aiJW[n106])) {
                                    if (n104 == -1 && this.BigNbrAreEqual(this.aiJW[n106], this.MontgomeryMultR1)) {
                                        n104 = n106;
                                    }
                                    else {
                                        n104 = -2;
                                        this.AddBigNbrModN(this.aiJW[n106], this.MontgomeryMultR1, this.biTmp);
                                        if (this.BigNbrIsZero(this.biTmp)) {
                                            ++n105;
                                        }
                                    }
                                }
                            }
                            if (n104 < 0) {
                                if (n105 != n9 - 1) {
                                    return 1;
                                }
                                int n107;
                                for (n107 = 0; n107 < n20; ++n107) {
                                    this.AddBigNbrModN(this.aiJW[n107], this.MontgomeryMultR1, this.biTmp);
                                    if (this.BigNbrIsZero(this.biTmp)) {
                                        break;
                                    }
                                }
                                if (n107 == n20) {
                                    return 1;
                                }
                                for (int n108 = 1; n108 <= n9 - 2; ++n108) {
                                    this.AddBigNbrModN(this.aiJW[n107 + n108 * n20], this.MontgomeryMultR1, this.biTmp);
                                    if (!this.BigNbrIsZero(this.biTmp)) {
                                        return 1;
                                    }
                                }
                                n104 = n107 + n22;
                            }
                            if (n11 != 1 && n104 % n9 != 0) {
                                if (n9 != 2) {
                                    n11 = 1;
                                }
                                else if (n18 == 1) {
                                    if ((this.TestNbr[0] & 0x3L) == 0x1L) {
                                        n11 = 1;
                                    }
                                }
                                else {
                                    this.MultBigNbrByLongModN(this.MontgomeryMultR1, n19, this.biTmp);
                                    for (int n109 = 0; n109 < this.NumberLength; ++n109) {
                                        this.biR[n109] = this.biTmp[n109];
                                    }
                                    int n110;
                                    long n111;
                                    for (n110 = this.NumberLength - 1, n111 = 1073741824L; (this.TestNbr[n110] & n111) == 0x0L; --n110, n111 = 1073741824L) {
                                        n111 /= 2L;
                                        if (n111 == 0L) {}
                                    }
                                    do {
                                        n111 /= 2L;
                                        if (n111 == 0L) {
                                            --n110;
                                            n111 = 1073741824L;
                                        }
                                        this.MontgomeryMult(this.biR, this.biR, this.biT);
                                        for (int n112 = 0; n112 < this.NumberLength; ++n112) {
                                            this.biR[n112] = this.biT[n112];
                                        }
                                        if ((this.TestNbr[n110] & n111) != 0x0L) {
                                            this.MontgomeryMult(this.biR, this.biTmp, this.biT);
                                            for (int n113 = 0; n113 < this.NumberLength; ++n113) {
                                                this.biR[n113] = this.biT[n113];
                                            }
                                        }
                                    } while (n110 > 0 || n111 > 2L);
                                    this.AddBigNbrModN(this.biR, this.MontgomeryMultR1, this.biTmp);
                                    if (!this.BigNbrIsZero(this.biTmp)) {
                                        return 1;
                                    }
                                    n11 = 1;
                                }
                            }
                        }
                    }
                    if (n11 != 0) {
                        ++l;
                        continue Label_4076;
                    }
                    n10 = n6 + 1;
                    if (n6 < ecm.aiNQ[n5] - 1) {
                        ++n6;
                        final int n114 = ecm.aiQ[n6];
                        int n115 = n7 * n114;
                        do {
                            this.MultBigNbrByLong(this.biS, n114, this.biS);
                            n115 /= n114;
                        } while (n115 % n114 == 0);
                    }
                    else {
                        if (++n5 == 11) {
                            return this.ProbabilisticPrimeTest(bigInteger);
                        }
                        n7 = ecm.aiT[n5];
                        n8 = ecm.aiNP[n5];
                        this.biS[0] = 2L;
                        for (int n116 = 1; n116 < this.NumberLength; ++n116) {
                            this.biS[n116] = 0L;
                        }
                        for (int n117 = 0; n117 <= ecm.aiNQ[n5]; ++n117) {
                            final int n118 = ecm.aiQ[n117];
                            int n119 = n7 * n118;
                            do {
                                this.MultBigNbrByLong(this.biS, n118, this.biS);
                                n119 /= n118;
                            } while (n119 % n118 == 0);
                            if (this.CompareSquare(this.biS, this.TestNbr) > 0) {
                                n6 = n117;
                                break Label_0336;
                            }
                        }
                        return this.ProbabilisticPrimeTest(bigInteger);
                    }
                }
                l = 0;
            } while (l < n8);
        }
        final int numberLength = this.NumberLength;
        for (int n120 = 0; n120 < this.NumberLength; ++n120) {
            this.biN[n120] = this.TestNbr[n120];
            this.TestNbr[n120] = this.biS[n120];
            this.biR[n120] = 0L;
        }
        while (this.TestNbr[this.NumberLength - 1] == 0L) {
            --this.NumberLength;
        }
        this.dN = this.TestNbr[this.NumberLength - 1];
        if (this.NumberLength > 1) {
            this.dN += this.TestNbr[this.NumberLength - 2] / 2.147483648E9;
        }
        if (this.NumberLength > 2) {
            this.dN += this.TestNbr[this.NumberLength - 3] / 4.6116860184273879E18;
        }
        final int numberLength2 = this.NumberLength;
        final double dn = this.dN;
        this.MontgomeryMultR1[0] = 1L;
        for (int n121 = 1; n121 < this.NumberLength; ++n121) {
            this.MontgomeryMultR1[n121] = 0L;
        }
        this.biR[0] = 1L;
        this.BigNbrModN(this.biN, numberLength, this.biT);
        for (int n122 = 1; n122 <= n7; ++n122) {
            this.MultBigNbrModN(this.biR, this.biT, this.biTmp);
            int n123;
            for (n123 = this.NumberLength - 1; n123 > 0 && this.biTmp[n123] == 0L; --n123) {}
            if (n123 == 0 && this.biTmp[0] != 1L) {
                return 0;
            }
            while (this.biTmp[this.NumberLength - 1] == 0L) {
                --this.NumberLength;
            }
            for (int n124 = 0; n124 < this.NumberLength; ++n124) {
                this.TestNbr[n124] = this.biTmp[n124];
            }
            this.dN = this.TestNbr[this.NumberLength - 1];
            if (this.NumberLength > 1) {
                this.dN += this.TestNbr[this.NumberLength - 2] / 2.147483648E9;
            }
            if (this.NumberLength > 2) {
                this.dN += this.TestNbr[this.NumberLength - 3] / 4.6116860184273879E18;
            }
            int n125;
            for (n125 = this.NumberLength - 1; n125 > 0 && this.TestNbr[n125] == this.biTmp[n125]; --n125) {}
            if (this.TestNbr[n125] > this.biTmp[n125]) {
                this.BigNbrModN(this.biN, numberLength, this.biTmp);
                if (this.BigNbrIsZero(this.biTmp)) {
                    return 1;
                }
            }
            this.dN = dn;
            this.NumberLength = numberLength2;
            for (int n126 = 0; n126 < this.NumberLength; ++n126) {
                this.biR[n126] = this.TestNbr[n126];
                this.TestNbr[n126] = this.biS[n126];
            }
        }
        return 0;
    }
    
    int CompareSquare(final long[] array, final long[] array2) {
        int n;
        for (n = this.NumberLength - 1; n > 0 && array[n] == 0L; --n) {}
        final int n2 = this.NumberLength / 2;
        if (this.NumberLength % 2 == 0) {
            if (n >= n2) {
                return 1;
            }
            if (n < n2 - 1 || this.biS[n2 - 1] < 65536L) {
                return -1;
            }
        }
        else {
            if (n < n2) {
                return -1;
            }
            if (n > n2 || this.biS[n2] >= 65536L) {
                return 1;
            }
        }
        this.MultBigNbr(this.biS, this.biS, this.biTmp);
        this.SubtractBigNbr(this.biTmp, this.TestNbr, this.biTmp);
        if (this.BigNbrIsZero(this.biTmp)) {
            return 0;
        }
        if (this.biTmp[this.NumberLength - 1] >= 0L) {
            return 1;
        }
        return -1;
    }
    
    void JS_JW(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n2; ++j) {
                final int n5 = (i + j) % n;
                this.MontgomeryMult(this.aiJS[i], this.aiJW[j], this.biTmp);
                this.AddBigNbrModN(this.aiJX[n5], this.biTmp, this.aiJX[n5]);
            }
        }
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < this.NumberLength; ++l) {
                this.aiJS[k][l] = this.aiJX[k][l];
                this.aiJX[k][l] = 0L;
            }
        }
        this.NormalizeJS(n, n2, n3, n4);
    }
    
    final void FibonacciCheck() {
        if (!this.onlyFactoring) {
            return;
        }
        if (this.NumberToFactor.bitLength() > 32) {
            final int bitLength = this.NumberToFactor.bitLength();
            final double n = ((bitLength - 32) * Math.log(2.0) + Math.log(this.NumberToFactor.shiftRight(bitLength - 32).longValue()) + 0.80471895621705) / 0.481211825059603;
            if (n + 5.0E-6 - Math.floor(n + 5.0E-6) > 1.0E-5) {
                return;
            }
        }
        BigInteger bigInt1 = ecm.BigInt1;
        BigInteger bigInt2 = ecm.BigInt0;
        int n2 = 0;
        while (true) {
            final int compareTo = bigInt2.compareTo(this.NumberToFactor);
            if (compareTo == 0) {
                this.FactorFibonacci(n2, this.PD[this.NbrFactors - 1]);
                return;
            }
            if (compareTo > 0) {
                return;
            }
            final BigInteger add = bigInt1.add(bigInt2);
            bigInt1 = bigInt2;
            bigInt2 = add;
            ++n2;
        }
    }
    
    static {
        BigInt0 = BigInteger.valueOf(0L);
        BigInt1 = BigInteger.valueOf(1L);
        BigInt2 = BigInteger.valueOf(2L);
        BigInt3 = BigInteger.valueOf(3L);
        aiP = new int[] { 2, 3, 5, 7, 11, 13 };
        aiQ = new int[] { 2, 3, 5, 7, 13, 11, 31, 61, 19, 37, 181, 29, 43, 71, 127, 211, 421, 631, 41, 73, 281, 2521, 17, 113, 241, 337, 1009, 109, 271, 379, 433, 541, 757, 2161, 7561, 15121, 23, 67, 89, 199, 331, 397, 463, 617, 661, 881, 991, 1321, 2311, 2377, 2971, 3697, 4159, 4621, 8317, 9241, 16633, 18481, 23761, 101, 151, 401, 601, 701, 1051, 1201, 1801, 2801, 3301, 3851, 4201, 4951, 6301, 9901, 11551, 12601, 14851, 15401, 19801, 97, 353, 673, 2017, 3169, 3361, 5281, 7393, 21601, 30241, 53, 79, 131, 157, 313, 521, 547, 859, 911, 937, 1093, 1171, 1249, 1301, 1873, 1951, 2003, 2081, 41, 2731, 2861, 3121, 3433, 3511, 5851, 6007, 6553, 7151, 7723, 8009, 8191, 8581, 8737, 9829, 11701, 13729, 14561, 15601, 16381, 17551, 20021, 20593, 21841, 24571, 25741, 26209, 28081 };
        aiG = new int[] { 1, 2, 2, 3, 2, 2, 3, 2, 2, 2, 2, 2, 3, 7, 3, 2, 2, 3, 6, 5, 3, 17, 3, 3, 7, 10, 11, 6, 6, 2, 5, 2, 2, 23, 13, 11, 5, 2, 3, 3, 3, 5, 3, 3, 2, 3, 6, 13, 3, 5, 10, 5, 3, 2, 6, 13, 15, 13, 7, 2, 6, 3, 7, 2, 7, 11, 11, 3, 6, 2, 11, 6, 10, 2, 7, 11, 2, 6, 13, 5, 3, 5, 5, 7, 22, 7, 5, 7, 11, 2, 3, 2, 5, 10, 3, 2, 2, 17, 5, 5, 2, 7, 2, 10, 3, 5, 3, 7, 3, 2, 7, 5, 7, 2, 3, 10, 7, 3, 3, 17, 6, 5, 10, 6, 23, 6, 23, 2, 3, 3, 5, 11, 7, 6, 11, 19 };
        aiNP = new int[] { 2, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6 };
        aiNQ = new int[] { 5, 8, 11, 18, 22, 27, 36, 59, 79, 89, 136 };
        aiT = new int[] { 12, 60, 180, 1260, 2520, 5040, 15120, 166320, 831600, 1663200, 21621600 };
        limits = new int[] { 5, 8, 15, 25, 27, 32, 43, 70, 150, 300, 350, 600, 1500 };
        expressionText = new String[] { "Number too low (less than 2).", "Number too high (more than 10000 digits).", "Intermediate expression too high (more than 20000 digits).", "Non-integer division.", "Parentheses mismatch.", "Syntax error.", "Too many parentheses.", "Invalid parameter." };
    }
    
    public void switchSIQS(final int n) {
        if (n == 1) {
            this.digitsInGroup &= 0xFBFF;
            return;
        }
        this.digitsInGroup |= 0x400;
    }
    
    long modPow(final long n, long n2, final long n3) {
        long n4 = 1L;
        long n5 = n;
        while (n2 != 0L) {
            if ((n2 & 0x1L) == 0x1L) {
                n4 = n4 * n5 % n3;
            }
            n5 = n5 * n5 % n3;
            n2 /= 2L;
        }
        return n4;
    }
    
    void factorize() {
        final BigInteger[] array = { null };
        this.StepECM = 0;
        this.primeModMult = 0L;
        this.Computing3Squares = false;
        this.TerminateThread = false;
        this.Old = System.currentTimeMillis();
        if (this.onlyFactoring) {
            if (this.NbrFactors == 0) {
                this.lowerTextArea.setText("Computing input expression...");
                int computeExpression;
                try {
                    computeExpression = expression.ComputeExpression(this.textNumber.getText().trim(), 0, array);
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.lowerTextArea.setText("Out of memory.");
                    return;
                }
                catch (ArithmeticException ex) {
                    return;
                }
                this.NumberToFactor = array[0];
                if (computeExpression != 0) {
                    this.lowerTextArea.setText(ecm.expressionText[-1 - computeExpression]);
                    return;
                }
            }
        }
        else if (this.NbrFactors == 0) {
            this.NumberToFactor = new BigInteger(this.textNumber.getText().trim());
        }
        this.BigNbr1[0] = 1L;
        int n = 1;
        do {
            this.BigNbr1[n] = 0L;
        } while (++n < 1200);
        try {
            if (this.NbrFactors == 0) {
                this.lowerTextArea.setText("Searching for small factors (less than 131072).");
                if (this.GetSmallFactors(this.NumberToFactor, this.PD, this.Exp, this.Typ, 0) != 1L) {
                    this.PD[this.NbrFactors] = this.BigIntToBigNbr(this.TestNbr);
                    this.Exp[this.NbrFactors] = 1;
                    this.Typ[this.NbrFactors] = -1;
                    ++this.NbrFactors;
                    this.ShowUpperPane();
                    if (this.batchFinished || !this.batchPrime) {
                        this.lowerTextArea.setText("Searching for perfect power plus/minus 1.");
                        this.PowerPM1Check();
                        this.lowerTextArea.setText("Searching for Lucas number.");
                        this.LucasCheck();
                        this.lowerTextArea.setText("Searching for Fibonacci number.");
                        this.FibonacciCheck();
                    }
                }
            }
            this.performLehman = true;
            Label_0335: {
                break Label_0335;
                int i;
            Label_0648_Outer:
                do {
                Block_25:
                    while (true) {
                        Label_0663: {
                            break Label_0663;
                            int j = 0;
                            int aprtCle;
                            long lModularMult;
                            BigInteger bigInteger;
                            Block_30_Outer:Block_32_Outer:
                            while (j < this.NbrFactors) {
                                if (this.Typ[j] >= 0) {
                                    ++j;
                                    continue Label_0648_Outer;
                                }
                                this.lowerTextArea.setText("Searching for perfect power.");
                                if (this.PowerCheck(j) != 0) {
                                    this.SortFactorsInputNbr();
                                    break Label_0335;
                                }
                                if (this.PD[j].bitLength() <= 33) {
                                    aprtCle = 0;
                                }
                                else {
                                    this.lowerTextArea.setText("Before calling prime check routine.");
                                    lModularMult = this.lModularMult;
                                    aprtCle = this.AprtCle(this.PD[j]);
                                    this.primeModMult += this.lModularMult - lModularMult;
                                    if (!this.batchFinished && this.batchPrime) {
                                        this.NbrFactors = aprtCle;
                                        return;
                                    }
                                }
                                if (aprtCle == 0) {
                                    if (this.Typ[j] < -300000000) {
                                        this.Typ[j] = -this.Typ[j];
                                        break Label_0335;
                                    }
                                    if (this.Typ[j] < -250000000) {
                                        this.Typ[j] = 250000000;
                                        break Label_0335;
                                    }
                                    if (this.Typ[j] < -200000000) {
                                        this.Typ[j] = 200000000;
                                        break Label_0335;
                                    }
                                    if (this.Typ[j] < -100000000) {
                                        this.Typ[j] = 100000000;
                                        break Label_0335;
                                    }
                                    this.Typ[j] = 0;
                                    break Label_0335;
                                }
                                else {
                                    if (this.Typ[j] < -300000000) {
                                        this.Typ[j] = -300000000 - this.Typ[j];
                                        break Label_0335;
                                    }
                                    this.Typ[j] = -this.Typ[j];
                                    break Label_0335;
                                }
                                Label_0796: {
                                    this.Typ[i] = this.EC;
                                }
                                // iftrue(Label_0769:, !bigInteger.equals((Object)ecm.BigInt1))
                                // iftrue(Label_0815:, this.EC <= 0 || this.EC >= 300000000 || this.EC == 100000000 || this.EC == 200000000 || this.EC == 250000000)
                                // iftrue(Label_0796:, !this.foundByLehman)
                                while (true) {
                                Label_0769:
                                    while (true) {
                                    Block_31:
                                        while (true) {
                                            this.InsertNewFactor(bigInteger);
                                            break Label_0335;
                                            this.EC %= 50000000;
                                            bigInteger = this.fnECM(this.PD[i], i);
                                            break Block_31;
                                            this.Typ[i] = 250000000 + this.EC + 1;
                                            continue Block_30_Outer;
                                        }
                                        bigInteger = this.FactoringSIQS(this.PD[i]);
                                        break Label_0769;
                                        this.EC = this.Typ[i];
                                        continue Block_32_Outer;
                                    }
                                    continue;
                                }
                                Label_0815:
                                ++i;
                                continue Label_0648_Outer;
                            }
                        }
                        break Block_25;
                        this.ShowUpperPane();
                        int j = 0;
                        continue;
                    }
                    i = 0;
                } while (i < this.NbrFactors);
            }
            if (this.onlyFactoring) {
                this.textAreaContents = this.upperTextArea.getText() + "\n\n";
                this.StringToLabel = "";
                this.addStringToLabel("Number of divisors: ");
                BigInteger bigInteger2 = ecm.BigInt1;
                for (int k = 0; k < this.NbrFactors; ++k) {
                    bigInteger2 = bigInteger2.multiply(BigInteger.valueOf(this.Exp[k] + 1));
                }
                this.insertBigNbr(bigInteger2);
                this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n\n";
                this.StringToLabel = "";
                this.addStringToLabel("Sum of divisors: ");
                BigInteger bigInteger3 = ecm.BigInt1;
                for (int l = 0; l < this.NbrFactors; ++l) {
                    bigInteger3 = bigInteger3.multiply(this.PD[l].pow(this.Exp[l] + 1).subtract(ecm.BigInt1)).divide(this.PD[l].subtract(ecm.BigInt1));
                }
                this.insertBigNbr(bigInteger3);
                this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n\n";
                this.StringToLabel = "";
                this.addStringToLabel("Euler's Totient: ");
                BigInteger bigInteger4 = this.NumberToFactor;
                for (int n2 = 0; n2 < this.NbrFactors; ++n2) {
                    bigInteger4 = bigInteger4.multiply(this.PD[n2].subtract(ecm.BigInt1)).divide(this.PD[n2]);
                }
                this.insertBigNbr(bigInteger4);
                int n3 = 1;
                for (int n4 = 0; n4 < this.NbrFactors; ++n4) {
                    if (this.Exp[n4] == 1) {
                        n3 = -n3;
                    }
                    else {
                        n3 = 0;
                    }
                }
                this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n\nMoebius: " + n3;
                this.StringToLabel = "\n\nSum of squares: ";
                this.ComputeFourSquares(this.PD, this.Exp);
                this.NbrFactors1 = this.NbrFactors;
                Label_1750: {
                    if (this.Quad4.signum() != 0) {
                        final int lowestSetBit = this.NumberToFactor.getLowestSetBit();
                        if (lowestSetBit % 2 != 0 || !this.NumberToFactor.shiftRight(lowestSetBit).and(BigInteger.valueOf(7L)).equals(BigInteger.valueOf(7L))) {
                            this.Computing3Squares = true;
                            final int n5 = lowestSetBit / 2;
                            this.lowerTextArea.setText("Computing sum of three squares...");
                            BigInteger quad3 = ecm.BigInt1.shiftLeft(n5);
                            while (!this.TerminateThread) {
                                final long currentTimeMillis = System.currentTimeMillis();
                                if (this.OldTimeElapsed >= 0L && this.OldTimeElapsed / 1000L != (this.OldTimeElapsed + currentTimeMillis - this.Old) / 1000L) {
                                    this.OldTimeElapsed += currentTimeMillis - this.Old;
                                    this.Old = currentTimeMillis;
                                    this.labelStatus.setText("Time elapsed: " + this.GetDHMS(this.OldTimeElapsed / 1000L) + "    mod mult: " + ((this.lModularMult >= 0L) ? ("" + this.lModularMult) : "I don't know"));
                                }
                                final long getSmallFactors = this.GetSmallFactors(this.NumberToFactor.subtract(quad3.multiply(quad3)), this.PD1, this.Exp1, this.Typ1, 1);
                                Label_1653: {
                                    if (getSmallFactors >= 0L) {
                                        if (getSmallFactors == 1L) {
                                            this.ComputeFourSquares(this.PD1, this.Exp1);
                                        }
                                        else {
                                            if (this.TestNbr[0] % 4L == 3L) {
                                                break Label_1653;
                                            }
                                            this.PD1[this.NbrFactors] = this.BigIntToBigNbr(this.TestNbr);
                                            this.Exp1[this.NbrFactors] = 1;
                                            ++this.NbrFactors;
                                            if (!this.ComputeFourSquares(this.PD1, this.Exp1)) {
                                                break Label_1653;
                                            }
                                        }
                                        this.Quad3 = quad3;
                                        if (this.Quad1.compareTo(this.Quad3) < 0) {
                                            final BigInteger quad4 = this.Quad1;
                                            this.Quad1 = this.Quad3;
                                            this.Quad3 = quad4;
                                        }
                                        if (this.Quad2.compareTo(this.Quad3) < 0) {
                                            final BigInteger quad5 = this.Quad2;
                                            this.Quad2 = this.Quad3;
                                            this.Quad3 = quad5;
                                        }
                                        this.Computing3Squares = false;
                                        break Label_1750;
                                    }
                                }
                                quad3 = quad3.add(ecm.BigInt1.shiftLeft(n5));
                            }
                            throw new ArithmeticException();
                        }
                    }
                }
                this.NbrFactors = this.NbrFactors1;
                if (this.Quad4.signum() == 0) {
                    if (this.Quad3.signum() == 0) {
                        if (this.Quad2.signum() == 0) {
                            this.insertBigNbr(this.Quad1);
                            this.addStringToLabel("^2");
                        }
                        else {
                            this.textAreaContents = this.textAreaContents + this.StringToLabel + "a^2 + b^2\n";
                            this.StringToLabel = "a = ";
                            this.insertBigNbr(this.Quad1);
                            this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                            this.StringToLabel = "b = ";
                            this.insertBigNbr(this.Quad2);
                        }
                    }
                    else {
                        this.textAreaContents = this.textAreaContents + this.StringToLabel + "a^2 + b^2 + c^2\n";
                        this.StringToLabel = "a = ";
                        this.insertBigNbr(this.Quad1);
                        this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                        this.StringToLabel = "b = ";
                        this.insertBigNbr(this.Quad2);
                        this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                        this.StringToLabel = "c = ";
                        this.insertBigNbr(this.Quad3);
                    }
                }
                else {
                    this.textAreaContents = this.textAreaContents + this.StringToLabel + "a^2 + b^2 + c^2 + d^2\n";
                    this.StringToLabel = "a = ";
                    this.insertBigNbr(this.Quad1);
                    this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                    this.StringToLabel = "b = ";
                    this.insertBigNbr(this.Quad2);
                    this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                    this.StringToLabel = "c = ";
                    this.insertBigNbr(this.Quad3);
                    this.textAreaContents = this.textAreaContents + this.StringToLabel + "\n";
                    this.StringToLabel = "d = ";
                    this.insertBigNbr(this.Quad4);
                }
                this.upperTextArea.setText(this.textAreaContents + this.StringToLabel);
                final long currentTimeMillis2 = System.currentTimeMillis();
                if (this.OldTimeElapsed >= 0L) {
                    this.OldTimeElapsed += currentTimeMillis2 - this.Old;
                    final String getDHMS = this.GetDHMS(this.OldTimeElapsed / 1000L);
                    String text = "Factorization complete in " + getDHMS;
                    if (this.lModularMult >= 0L) {
                        text = text + "\nECM: " + (this.lModularMult - this.primeModMult) + " modular multiplications\nPrime checking: " + this.primeModMult + " modular multiplications";
                    }
                    if (this.smoothsFound > 0L) {
                        text = text + "\nSIQS: " + this.polynomialsSieved + " polynomials sieved\n      " + this.trialDivisions + " sets of trial divisions\n      " + this.smoothsFound + " smooth congruences found (1 out of every " + this.ValuesSieved / this.smoothsFound + " values)\n      " + this.totalPartials + " partial congruences found (1 out of every " + this.ValuesSieved / this.totalPartials + " values)\n      " + this.partialsFound + " useful partial congruences";
                    }
                    this.lowerTextArea.setText(text);
                    this.labelStatus.setText("Time elapsed: " + getDHMS + "    mod mult: " + ((this.lModularMult >= 0L) ? ("" + this.lModularMult) : "I don't know"));
                }
                else {
                    this.lowerTextArea.setText("Factorization complete");
                }
                this.NextEC = -1;
            }
        }
        catch (ArithmeticException ex2) {
            final long currentTimeMillis3 = System.currentTimeMillis();
            if (this.OldTimeElapsed >= 0L) {
                this.OldTimeElapsed += currentTimeMillis3 - this.Old;
            }
            System.gc();
            return;
        }
        System.gc();
    }
    
    final void PowerPM1Check() {
        if (this.onlyFactoring) {
            boolean b = false;
            boolean b2 = false;
            final int intValue = this.NumberToFactor.mod(BigInteger.valueOf(9L)).intValue();
            final int bitLength = this.NumberToFactor.bitLength();
            final double n = (bitLength - 32) * Math.log(2.0) + Math.log(this.NumberToFactor.shiftRight(bitLength - 32).longValue());
            final boolean[] array = new boolean[bitLength + 1];
            final boolean[] array2 = new boolean[2 * bitLength + 3];
            for (int i = 2; i <= bitLength; ++i) {
                array[i] = true;
            }
            for (int j = 2; j < array2.length; ++j) {
                array2[j] = true;
            }
            for (int n2 = 2; n2 * n2 < array2.length; ++n2) {
                for (int k = n2 * n2; k < array2.length; k += n2) {
                    array2[k] = false;
                }
            }
            for (int l = 2; l < array2.length; ++l) {
                if (array2[l]) {
                    final long n3 = l * l;
                    final int intValue2 = this.NumberToFactor.mod(BigInteger.valueOf(l)).intValue();
                    if (intValue2 == 1 && this.NumberToFactor.mod(BigInteger.valueOf(n3)).longValue() != 1L) {
                        b = true;
                    }
                    if (intValue2 == l - 1 && this.NumberToFactor.mod(BigInteger.valueOf(n3)).longValue() != n3 - 1L) {
                        b2 = true;
                    }
                    if (b2 && b) {
                        return;
                    }
                    if (array[l / 2]) {
                        if (intValue2 > (b ? 1 : 2) && intValue2 < (b2 ? (l - 1) : (l - 2))) {
                            for (int n4 = l / 2; n4 <= bitLength; n4 += l / 2) {
                                array[n4] = false;
                            }
                        }
                        else if (intValue2 == l - 2) {
                            for (int n5 = l - 1; n5 <= bitLength; n5 += l - 1) {
                                array[n5] = false;
                            }
                        }
                    }
                }
            }
            int n6 = 2;
            int n8;
            do {
                final double n7 = n / Math.log(n6) + 5.0E-6;
                n8 = (int)Math.floor(n7);
                if (n7 - n8 <= 1.0E-5 && (n8 % 3 != 0 || intValue <= 2 || intValue >= 7) && array[n8] && this.ProcessExponent(n8)) {
                    return;
                }
            } while (++n6 < 100);
            while (n8 >= 2) {
                if ((n8 % 3 != 0 || intValue <= 2 || intValue >= 7) && array[n8] && this.ProcessExponent(n8)) {
                    return;
                }
                --n8;
            }
        }
    }
    
    static int lucas_cost(final int n, final double n2) {
        final int n3 = (int)(n / n2 + 0.5);
        if (n3 >= n) {
            return 6 * n;
        }
        int i = n - n3;
        int n4 = 2 * n3 - n;
        int n5 = 11;
        while (i != n4) {
            if (i < n4) {
                final int n6 = i;
                i = n4;
                n4 = n6;
            }
            if (4 * i <= 5 * n4 && (i + n4) % 3 == 0) {
                final int n7 = (2 * i - n4) / 3;
                n4 = (2 * n4 - i) / 3;
                i = n7;
                n5 += 18;
            }
            else if (4 * i <= 5 * n4 && (i - n4) % 6 == 0) {
                i = (i - n4) / 2;
                n5 += 11;
            }
            else if (i <= 4 * n4) {
                i -= n4;
                n5 += 6;
            }
            else if ((i + n4) % 2 == 0) {
                i = (i - n4) / 2;
                n5 += 11;
            }
            else if (i % 2 == 0) {
                i /= 2;
                n5 += 11;
            }
            else if (i % 3 == 0) {
                i = i / 3 - n4;
                n5 += 23;
            }
            else if ((i + n4) % 3 == 0) {
                i = (i - 2 * n4) / 3;
                n5 += 23;
            }
            else if ((i - n4) % 3 == 0) {
                i = (i - n4) / 3;
                n5 += 23;
            }
            else {
                if (n4 % 2 != 0) {
                    continue;
                }
                n4 /= 2;
                n5 += 11;
            }
        }
        return n5;
    }
    
    void AddBigNbrModN(final long[] array, final long[] array2, final long[] array3) {
        final int numberLength = this.NumberLength;
        final long n = 2147483647L;
        long n2 = 0L;
        for (int i = 0; i < numberLength; ++i) {
            n2 = (n2 >> 31) + array[i] + array2[i] - this.TestNbr[i];
            array3[i] = (n2 & n);
        }
        if (n2 < 0L) {
            long n3 = 0L;
            for (int j = 0; j < numberLength; ++j) {
                n3 = (n3 >> 31) + array3[j] + this.TestNbr[j];
                array3[j] = (n3 & n);
            }
        }
    }
    
    void MultBigNbrByLongModN(final long[] array, final long n, final long[] array2) {
        final int numberLength = this.NumberLength;
        final long n2 = 2147483647L;
        long n3 = 0L;
        int i;
        for (i = 0; i < numberLength; ++i) {
            n3 = (n3 >>> 31) + n * array[i];
            array2[i] = (n3 & n2);
        }
        array2[i] = n3 >>> 31;
        this.AdjustModN(array2);
    }
    
    void AdjustModN(final long[] array) {
        final int numberLength = this.NumberLength;
        final long n = 2147483647L;
        double n2 = array[numberLength] * 2.147483648E9 + array[numberLength - 1];
        if (numberLength > 1) {
            n2 += array[numberLength - 2] / 2.147483648E9;
        }
        long n3 = (long)Math.ceil(n2 / this.dN) + 2L;
        if (n3 >= 4294967296L) {
            long n4 = 0L;
            for (int i = 0; i < numberLength; ++i) {
                final long n5 = array[i + 1] - (n3 >>> 31) * this.TestNbr[i] - n4;
                array[i + 1] = (n5 & n);
                n4 = n - n5 >>> 31;
            }
            n3 &= n;
        }
        long n6 = 0L;
        for (int j = 0; j < numberLength; ++j) {
            final long n7 = array[j] - n3 * this.TestNbr[j] - n6;
            array[j] = (n7 & n);
            n6 = n - n7 >>> 31;
        }
        final int n8 = numberLength;
        array[n8] -= n6;
        while ((array[numberLength] & n) != 0x0L) {
            long n9 = 0L;
            for (int k = 0; k < numberLength; ++k) {
                final long n10 = n9 + (array[k] + this.TestNbr[k]);
                array[k] = (n10 & n);
                n9 = n10 >> 31;
            }
            final int n11 = numberLength;
            array[n11] += n9;
        }
    }
    
    void JacobiSum(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < this.NumberLength; ++j) {
                this.aiJ0[i][j] = 0L;
            }
        }
        for (int k = 1; k <= n7 - 2; ++k) {
            final int n8 = (n * k + n2 * this.aiF[k]) % n4;
            if (n8 < n5) {
                this.AddBigNbrModN(this.aiJ0[n8], this.MontgomeryMultR1, this.aiJ0[n8]);
            }
            else {
                for (int l = 1; l < n3; ++l) {
                    this.SubtractBigNbrModN(this.aiJ0[n8 - l * n6], this.MontgomeryMultR1, this.aiJ0[n8 - l * n6]);
                }
            }
        }
    }
    
    boolean BigNbrIsZero(final long[] array) {
        for (int i = 0; i < this.NumberLength; ++i) {
            if (array[i] != 0L) {
                return false;
            }
        }
        return true;
    }
    
    void FactorFibonacci(final int n, final BigInteger bigInteger) {
        if (this.onlyFactoring) {
            this.NroFact = 1;
            this.Factores[0] = bigInteger;
            int n2;
            for (n2 = n; n2 % 2 == 0; n2 /= 2) {}
            for (int n3 = 1; n3 * n3 <= n2; n3 += 2) {
                if (n2 % n3 == 0) {
                    final BigInteger gcd = this.Fibonacci(n3).gcd(bigInteger);
                    this.InsertFactor(gcd);
                    this.InsertFactor(bigInteger.divide(gcd));
                    final BigInteger gcd2 = this.Fibonacci(n / n3).gcd(bigInteger);
                    this.InsertFactor(gcd2);
                    this.InsertFactor(bigInteger.divide(gcd2));
                }
            }
            int n4 = n;
            while (n4 % 2 == 0) {
                n4 /= 2;
                this.InsertLucasFactor(n4, bigInteger);
            }
            this.SortFactors();
        }
    }
    
    class Command implements ActionListener
    {
        int id;
        ecm appletEcm;
        
        public Command(final int id, final ecm appletEcm) {
            ecm.this.getClass();
            this.id = id;
            this.appletEcm = appletEcm;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            switch (this.id) {
                case 1: {
                    if (ecm.this.calcThread != null && ecm.this.calcThread.isAlive()) {
                        new AlertContinue(this.appletEcm);
                        return;
                    }
                    ecm.this.startNewFactorization(false);
                }
                case 2:
                case 3: {
                    int int1;
                    try {
                        int1 = Integer.parseInt(ecm.this.textCurve.getText());
                    }
                    catch (Exception ex) {
                        int1 = -1;
                    }
                    if (int1 >= 0 && int1 < 100000000) {
                        ecm.this.NextEC = int1;
                        if (ecm.this.calcThread != null) {
                            ecm.this.TerminateThread = true;
                            try {
                                ecm.this.calcThread.join();
                            }
                            catch (InterruptedException ex2) {}
                        }
                        if (ecm.this.NextEC == 0) {
                            ecm.this.NextEC = 200000000 + ecm.this.EC;
                        }
                        (ecm.this.calcThread = new Thread(this.appletEcm)).start();
                        return;
                    }
                    break;
                }
                case 4:
                case 5: {
                    try {
                        ecm.this.InputFactor = new BigInteger(ecm.this.textFactor.getText().trim());
                    }
                    catch (Exception ex3) {
                        ecm.this.InputFactor = ecm.BigInt0;
                    }
                    if (ecm.this.InputFactor.compareTo(ecm.BigInt1) > 0) {
                        if (ecm.this.calcThread != null) {
                            ecm.this.TerminateThread = true;
                            try {
                                ecm.this.calcThread.join();
                            }
                            catch (InterruptedException ex4) {}
                        }
                        ecm.this.InsertNewFactor(ecm.this.InputFactor);
                        ecm.this.NextEC = ecm.this.EC;
                        (ecm.this.calcThread = new Thread(this.appletEcm)).start();
                        return;
                    }
                    break;
                }
            }
        }
    }
}
