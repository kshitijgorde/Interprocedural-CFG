import java.util.Vector;
import java.io.EOFException;
import java.awt.Color;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.StreamTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class CgmAsciiReader extends CgmReader implements Runnable
{
    StreamTokenizer tokenizer;
    NoCrInputStream nin;
    boolean isComment;
    int ctSize;
    
    CgmAsciiReader(final DataInputStream in, final CgmContext cgmContext) {
        this.isComment = false;
        this.ctSize = 256;
        super.in = in;
        super.cgm = cgmContext;
        super.ContextOrPicture = cgmContext;
        this.nin = new NoCrInputStream(in);
        (this.tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(this.nin)))).resetSyntax();
        this.tokenizer.wordChars(97, 122);
        this.tokenizer.wordChars(65, 90);
        this.tokenizer.whitespaceChars(0, 32);
        this.tokenizer.whitespaceChars(40, 41);
        this.tokenizer.whitespaceChars(44, 44);
        this.tokenizer.quoteChar(39);
        this.tokenizer.quoteChar(34);
        this.tokenizer.parseNumbers();
        (super.ReaderThread = new Thread(this, "Reader-thread")).start();
    }
    
    private final int nextToken() throws IOException {
        int i;
        for (i = this.tokenizer.nextToken(); i == 37; i = this.tokenizer.nextToken()) {
            this.readComment();
        }
        return i;
    }
    
    private final void notifyUnknown(final String s) throws IOException {
        System.out.println("Unknown command " + s);
        this.skip();
    }
    
    protected double readColorComp() throws Exception {
        return this.readFloat();
    }
    
    private final void readComment() throws IOException {
        while (this.tokenizer.nextToken() != 37) {}
    }
    
    final void readElement() throws IOException {
        try {
            while (!super.cgm.FinishedLoading) {
                if (this.nextToken() != -3) {
                    System.out.println("Command expected, line " + this.nin.lineno);
                    System.exit(1);
                }
                final String upperCase = this.tokenizer.sval.toUpperCase();
                try {
                    switch (upperCase.length()) {
                        case 4: {
                            if (upperCase.equals("LINE")) {
                                super.cgm.currpic.polygon(this.readPoints(false), false);
                                break;
                            }
                            if (upperCase.equals("TEXT")) {
                                super.cgm.currpic.text(false, 0.0, 0.0, this.readFloat(), this.readFloat(), this.readKeyword("1FINAL", 0) == 1, this.readString());
                                break;
                            }
                            if (upperCase.equals("RECT")) {
                                super.cgm.currpic.rectangle(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat());
                                break;
                            }
                            break;
                        }
                        case 5: {
                            if (upperCase.equals("BEGMF")) {
                                super.cgm.beginMF(this.readString());
                                super.ContextOrPicture = super.cgm;
                                break;
                            }
                            if (upperCase.equals("ENDMF")) {
                                super.cgm.endMF();
                                break;
                            }
                            this.notifyUnknown(upperCase);
                            break;
                        }
                        case 6: {
                            if (upperCase.equals("BEGPIC")) {
                                super.cgm.beginPic(this.readString());
                                super.ContextOrPicture = super.cgm.currpic;
                                break;
                            }
                            if (upperCase.equals("ENDPIC")) {
                                super.ContextOrPicture = super.cgm;
                                break;
                            }
                            if (upperCase.equals("VDCEXT")) {
                                super.ContextOrPicture.vdcExt(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat());
                                break;
                            }
                            if (upperCase.equals("MARKER")) {
                                super.cgm.currpic.marker(this.readPoints(false));
                                break;
                            }
                            if (upperCase.equals("CIRCLE")) {
                                final double float1 = this.readFloat();
                                final double float2 = this.readFloat();
                                final double float3 = this.readFloat();
                                super.cgm.currpic.ellipse(float1, float2, float1, float2 + float3, float1 + float3, float2);
                                break;
                            }
                            if (upperCase.equals("3PT")) {
                                super.cgm.currpic.circarc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), -1);
                                break;
                            }
                            if (upperCase.equals("ARCCTR")) {
                                super.cgm.currpic.circarc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), -1);
                                break;
                            }
                            break;
                        }
                        case 7: {
                            if (upperCase.equals("POLYGON")) {
                                super.cgm.currpic.polygon(this.readPoints(false), true);
                                break;
                            }
                            if (upperCase.equals("ELLIPSE")) {
                                super.cgm.currpic.ellipse(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat());
                                break;
                            }
                            if (upperCase.equals("CHARORI")) {
                                final double angle = CgmReader.toAngle(this.readFloat(), this.readFloat());
                                final double angle2 = CgmReader.toAngle(this.readFloat(), this.readFloat());
                                super.ContextOrPicture.CharOri = angle2;
                                super.ContextOrPicture.CharSlant = 1.0 / Math.tan(angle2 - angle);
                                break;
                            }
                            if (upperCase.equals("EDGEVIS")) {
                                super.ContextOrPicture.EdgesVisible = (this.readKeyword("1ON", 0) == 1);
                                break;
                            }
                            break;
                        }
                        case 8: {
                            if (upperCase.equals("LINETYPE")) {
                                super.ContextOrPicture.LineType = this.readKeyword("1SOLID", 0);
                                break;
                            }
                            if (upperCase.equals("LINECOLR")) {
                                super.ContextOrPicture.LineColor = this.readColor();
                                break;
                            }
                            if (upperCase.equals("TEXTCOLR")) {
                                super.ContextOrPicture.TextColor = this.readColor();
                                break;
                            }
                            if (upperCase.equals("INTSTYLE")) {
                                super.ContextOrPicture.InteriorStyle = this.readKeyword("1SOLID2PATTERN3HATCH", 0);
                                break;
                            }
                            if (upperCase.equals("FILLCOLR")) {
                                super.ContextOrPicture.FillColor = this.readColor();
                                break;
                            }
                            if (upperCase.equals("ELLIPARC")) {
                                super.cgm.currpic.elliparc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), -1);
                                break;
                            }
                            if (upperCase.equals("TEXTPREC")) {
                                break;
                            }
                            if (upperCase.equals("TEXTPATH")) {
                                super.ContextOrPicture.TextPath = this.readKeyword("0RIGHT1LEFT2UP", 3);
                                break;
                            }
                            if (upperCase.equals("PATINDEX")) {
                                break;
                            }
                            if (upperCase.equals("EDGETYPE")) {
                                super.ContextOrPicture.EdgeType = this.readKeyword("1SOLID", 0);
                                break;
                            }
                            if (upperCase.equals("EDGECOLR")) {
                                super.ContextOrPicture.EdgeColor = this.readColor();
                                break;
                            }
                            if (upperCase.equals("APNDTEXT")) {
                                super.cgm.currpic.text(true, 0.0, 0.0, 0.0, 0.0, this.readKeyword("1FINAL", 0) == 1, this.readString());
                                break;
                            }
                            if (upperCase.equals("FONTLIST")) {
                                while (this.nextToken() == 39) {
                                    super.cgm.addFont(this.tokenizer.sval);
                                }
                                break;
                            }
                            if (upperCase.equals("BACKCOLR")) {
                                super.ContextOrPicture.BackColor = this.readColor(0);
                                break;
                            }
                            if (upperCase.equals("COLRMODE")) {
                                super.ColorMode = this.readKeyword("2INDEXED", 0);
                                break;
                            }
                            break;
                        }
                        case 9: {
                            if (upperCase.equals("DISJTLINE")) {
                                super.cgm.currpic.disjtLine(this.readPoints(false));
                                break;
                            }
                            if (upperCase.equals("RESTRTEXT")) {
                                super.cgm.currpic.text(false, this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readKeyword("1FINAL", 0) == 1, this.readString());
                                break;
                            }
                            if (upperCase.equals("POLGONSET")) {
                                super.cgm.currpic.polygon(this.readPoints(true), true);
                                break;
                            }
                            if (upperCase.equals("CELLARRAY")) {
                                final double float4 = this.readFloat();
                                final double float5 = this.readFloat();
                                final double float6 = this.readFloat();
                                final double float7 = this.readFloat();
                                this.readFloat();
                                this.readFloat();
                                final int n = this.readInt() + 1;
                                final int int1 = this.readInt();
                                final double n2 = this.readInt();
                                this.readInt();
                                this.readInt();
                                final int n3 = n * int1;
                                final int[] array = new int[n3];
                                for (int i = 0; i < n3; ++i) {
                                    array[i] = this.readColor(2).getRGB();
                                }
                                super.cgm.currpic.cellArray(float4, float5, float6, float7, array, n, int1);
                                break;
                            }
                            if (upperCase.equals("LINEWIDTH")) {
                                super.ContextOrPicture.LineWidth = this.readFloat();
                                break;
                            }
                            if (upperCase.equals("TEXTINDEX")) {
                                break;
                            }
                            if (upperCase.equals("CHAREXPAN")) {
                                super.ContextOrPicture.CharacterExpansion = this.readFloat();
                                break;
                            }
                            if (upperCase.equals("CHARSPACE")) {
                                super.ContextOrPicture.CharacterSpacing = this.readFloat();
                                break;
                            }
                            if (upperCase.equals("TEXTALIGN")) {
                                super.ContextOrPicture.TextAlignHor = this.readKeyword("1LEFT2CENTRE3RIGHT", 0);
                                super.ContextOrPicture.TextAlignVert = this.readKeyword("0TOP1CAP2HALF3BASE4BOTTOM", 0);
                                break;
                            }
                            if (upperCase.equals("EDGEWIDTH")) {
                                super.ContextOrPicture.EdgeWidth = this.readFloat();
                                break;
                            }
                            if (upperCase.equals("COLRTABLE")) {
                                int int2 = this.readInt();
                                while (true) {
                                    if (int2 > this.ctSize) {
                                        this.ctSize += 256;
                                        final Color[] colorTable = new Color[this.ctSize + 1];
                                        System.arraycopy(super.ColorTable, 0, colorTable, 0, this.ctSize - 255);
                                        super.ColorTable = colorTable;
                                    }
                                    final Color color = this.readColor(0);
                                    if (color == null) {
                                        break;
                                    }
                                    try {
                                        super.ColorTable[int2] = color;
                                    }
                                    catch (ArrayIndexOutOfBoundsException ex2) {
                                        System.out.println("ColorTable Array out of bounds: " + int2);
                                    }
                                    ++int2;
                                }
                                super.ColorMode = 2;
                                break;
                            }
                            break;
                        }
                        case 10: {
                            if (upperCase.equals("CHARHEIGHT")) {
                                super.ContextOrPicture.CharacterHeight = Math.abs(this.readFloat() * super.ContextOrPicture.ay);
                                break;
                            }
                            if (upperCase.equals("MARKERTYPE")) {
                                super.ContextOrPicture.MarkerType = this.readInt();
                                break;
                            }
                            if (upperCase.equals("MARKERSIZE")) {
                                super.ContextOrPicture.MarkerSize = this.readFloat();
                                break;
                            }
                            if (upperCase.equals("MARKERCOLR")) {
                                super.ContextOrPicture.MarkerColor = this.readColor();
                                break;
                            }
                            if (upperCase.equals("HATCHINDEX")) {
                                super.ContextOrPicture.HatchIndex = this.readInt();
                                break;
                            }
                            break;
                        }
                        case 11: {
                            if (upperCase.equals("ARC3PTCLOSE")) {
                                super.cgm.currpic.circarc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readKeyword("1PIE", 0));
                                break;
                            }
                            if (upperCase.equals("ARCCTRCLOSE")) {
                                super.cgm.currpic.circarc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readKeyword("1PIE", 0));
                                break;
                            }
                            break;
                        }
                        case 12: {
                            if (upperCase.equals("MAXCOLRINDEX")) {
                                final int int3 = this.readInt();
                                if (int3 > 256) {
                                    super.ColorTable = new Color[int3 + 1];
                                    break;
                                }
                                break;
                            }
                            else {
                                if (upperCase.equals("COLRVALUEEXT")) {
                                    super.colr1 = (int)this.readFloat();
                                    super.colg1 = (int)this.readFloat();
                                    super.colb1 = (int)this.readFloat();
                                    super.colr2 = 255.0 / (this.readFloat() - super.colr1);
                                    super.colg2 = 255.0 / (this.readFloat() - super.colg1);
                                    super.colb2 = 255.0 / (this.readFloat() - super.colb1);
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        case 13: {
                            if (upperCase.equals("LINEWIDTHMODE")) {
                                super.ContextOrPicture.LineWidthMode = this.readKeyword("1ABS", 0);
                                break;
                            }
                            if (upperCase.equals("EDGEWIDTHMODE")) {
                                super.ContextOrPicture.EdgeWidthMode = this.readKeyword("1ABS", 0);
                                break;
                            }
                            if (upperCase.equals("ELLIPARCCLOSE")) {
                                super.cgm.currpic.elliparc(this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readFloat(), this.readKeyword("1PIE", 0));
                                break;
                            }
                            if (upperCase.equals("TEXTFONTINDEX")) {
                                super.ContextOrPicture.FontIndex = Math.max(1, this.readInt());
                                break;
                            }
                            break;
                        }
                        case 14: {
                            if (upperCase.equals("MARKERSIZEMODE")) {
                                super.ContextOrPicture.MarkerSizeMode = this.readKeyword("1ABS", 0);
                                break;
                            }
                            break;
                        }
                    }
                    this.skip();
                }
                catch (EOFException ex3) {
                    if (super.cgm.currpic != null) {
                        super.cgm.endMF();
                        break;
                    }
                    break;
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        catch (EOFException ex4) {
            if (super.cgm.currpic != null) {
                super.cgm.endMF();
            }
        }
    }
    
    private final double readFloat() throws IOException, Exception {
        if (this.nextToken() == -2) {
            return this.tokenizer.nval;
        }
        throw new Exception("cgmVA: Number expected, line " + this.nin.lineno);
    }
    
    protected final int readInt() throws IOException, Exception {
        if (this.nextToken() == -2) {
            return (int)this.tokenizer.nval;
        }
        throw new Exception("cgmVA: Integer expected, line " + this.nin.lineno);
    }
    
    private final int readKeyword(final String s, final int n) {
        try {
            switch (this.nextToken()) {
                case -3: {
                    return Character.digit(s.charAt(s.indexOf(this.tokenizer.sval.toUpperCase()) - 1), 10);
                }
                case -2: {
                    return (int)this.tokenizer.nval;
                }
                default: {
                    return n;
                }
            }
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private final double[] readPoints(final boolean b) throws IOException {
        final Vector<double[]> vector = new Vector<double[]>(5, 5);
        int n = 100;
        int i = 0;
        int n2 = 0;
        double[] array = null;
        while (true) {
            if (n == 100) {
                array = new double[100];
                vector.addElement(array);
                n = 0;
            }
            final int nextToken = this.nextToken();
            if (nextToken == 59 || nextToken == 47) {
                break;
            }
            if (nextToken != -2) {
                continue;
            }
            if (b && n2++ == 3) {
                n2 = 0;
            }
            else {
                array[n] = this.tokenizer.nval;
                ++n;
                ++i;
            }
        }
        final double[] array2 = new double[i];
        for (int n3 = 0; i > 0; i -= 100, ++n3) {
            System.arraycopy(vector.elementAt(n3), 0, array2, n3 * 100, Math.min(100, i));
        }
        return array2;
    }
    
    private final String readString() throws IOException, Exception {
        final int nextToken = this.nextToken();
        if (nextToken == 39 || nextToken == 34) {
            return this.tokenizer.sval;
        }
        throw new Exception("cgmVA: String expected, line " + this.nin.lineno);
    }
    
    private final void skip() throws IOException {
        while (this.tokenizer.ttype != 59 && this.tokenizer.ttype != 47) {
            this.nextToken();
        }
    }
}
