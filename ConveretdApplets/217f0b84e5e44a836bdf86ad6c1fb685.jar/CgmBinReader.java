import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.io.DataInput;

// 
// Decompiled by Procyon v0.5.30
// 

public class CgmBinReader extends CgmReader implements Runnable
{
    int paramlen;
    int intprec;
    int indexprec;
    int colorprec;
    int colorindexprec;
    boolean VdcIsInt;
    int vdcrealprec;
    int vdcrealtype;
    int vdcintprec;
    static final int enumprec = 2;
    boolean paramContinued;
    
    CgmBinReader(final DataInput in, final CgmContext cgmContext) {
        this.intprec = 2;
        this.indexprec = 2;
        this.colorprec = 1;
        this.colorindexprec = 1;
        this.VdcIsInt = true;
        this.vdcrealprec = 4;
        this.vdcrealtype = 16;
        this.vdcintprec = 2;
        this.paramContinued = false;
        super.in = in;
        super.cgm = cgmContext;
        super.ContextOrPicture = cgmContext;
        (super.ReaderThread = new Thread(this, "Reader-thread")).start();
    }
    
    private final byte readByte() throws IOException {
        if (this.paramlen == 0) {
            if (!this.paramContinued) {
                return 0;
            }
            this.paramlen = this.readParamLen();
        }
        --this.paramlen;
        return super.in.readByte();
    }
    
    private final void readClass3(final int n) throws IOException {
        switch (n) {
            case 1: {
                this.vdcintprec = this.readPrec();
            }
            case 2: {
                this.readInt(2);
                final int int1 = this.readInt(this.intprec);
                this.vdcrealtype = this.readInt(this.intprec);
                this.vdcrealprec = int1 + this.vdcrealtype >> 3;
            }
            default: {}
        }
    }
    
    protected double readColorComp() throws Exception {
        return this.readInt(this.colorprec);
    }
    
    final void readElement() throws IOException {
        try {
            while (!super.cgm.FinishedLoading) {
                final int unsignedShort = super.in.readUnsignedShort();
                this.paramlen = (unsignedShort & 0x1F);
                final int n = unsignedShort >> 5;
                final int n2 = n & 0x7F;
                final int n3 = n >> 7 & 0xF;
                if (this.paramlen == 31) {
                    this.paramlen = this.readParamLen();
                }
                final int n4 = this.paramlen & 0x1;
                switch (n3) {
                    case 0: {
                        this.readMetaFileDelim(n2);
                        break;
                    }
                    case 1: {
                        this.readMetaFileDescr(n2);
                        break;
                    }
                    case 2: {
                        this.readMetaFilePictDescr(n2);
                        break;
                    }
                    case 3: {
                        this.readClass3(n2);
                        break;
                    }
                    case 4: {
                        this.readGraphicPrimitives(n2);
                        break;
                    }
                    case 5: {
                        this.readGraphicsAttributes(n2);
                        break;
                    }
                }
                this.paramlen += n4;
                if (this.paramlen > 0) {
                    this.skip();
                }
            }
        }
        catch (EOFException ex) {
            if (super.cgm.currpic != null) {
                super.cgm.endMF();
            }
        }
    }
    
    private final double readFloat() throws IOException {
        if (this.paramContinued && this.paramlen == 0) {
            this.paramlen = this.readParamLen();
        }
        if (this.paramlen >= this.vdcrealprec) {
            this.paramlen -= this.vdcrealprec;
            switch (this.vdcrealtype) {
                case 23: {
                    return super.in.readFloat();
                }
                case 52: {
                    return super.in.readDouble();
                }
                case 16: {
                    return super.in.readInt() / 65536.0;
                }
                case 32: {
                    return (super.in.readLong() >> 8) / 1.6777216E7;
                }
                default: {
                    System.out.println("Unsupported floating point format");
                    System.exit(1);
                    break;
                }
            }
        }
        this.skip();
        return 0.0;
    }
    
    private final void readGraphicPrimitives(final int n) throws IOException {
        final CgmPicture currpic = super.cgm.currpic;
        if (currpic == null) {
            System.out.println("Unsupported file format");
            System.exit(1);
        }
        switch (n) {
            case 1: {
                currpic.polygon(this.readPoints(false), false);
            }
            case 2: {
                currpic.disjtLine(this.readPoints(false));
            }
            case 3: {
                currpic.marker(this.readPoints(false));
            }
            case 4: {
                currpic.text(false, 0.0, 0.0, this.readVdc(), this.readVdc(), this.readInt(2) == 1, this.readString());
            }
            case 5: {
                currpic.text(false, this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readInt(2) == 1, this.readString());
            }
            case 6: {
                currpic.text(true, 0.0, 0.0, 0.0, 0.0, this.readInt(2) == 1, this.readString());
            }
            case 7: {
                currpic.polygon(this.readPoints(false), true);
            }
            case 8: {
                currpic.polygon(this.readPoints(true), true);
            }
            case 9: {
                final double vdc = this.readVdc();
                final double vdc2 = this.readVdc();
                final double vdc3 = this.readVdc();
                final double vdc4 = this.readVdc();
                this.readVdc();
                this.readVdc();
                final int n2 = this.readInt(this.intprec) + 1;
                final int int1 = this.readInt(this.intprec);
                final double n3 = this.readInt(2);
                this.readInt(2);
                this.readInt(2);
                final int n4 = n2 * int1;
                final int[] array = new int[n4];
                for (int i = 0; i < n4; ++i) {
                    array[i] = this.readColor(2).getRGB();
                }
                currpic.cellArray(vdc, vdc2, vdc3, vdc4, array, n2, int1);
            }
            case 10: {
                System.out.println("Generalized Drawing Primitive unsupported (proprietary format)");
            }
            case 11: {
                currpic.rectangle(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc());
            }
            case 12: {
                final double vdc5 = this.readVdc();
                final double vdc6 = this.readVdc();
                final double vdc7 = this.readVdc();
                currpic.ellipse(vdc5, vdc6, vdc5, vdc6 + vdc7, vdc5 + vdc7, vdc6);
            }
            case 13:
            case 14: {
                currpic.circarc(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readType());
            }
            case 15:
            case 16: {
                currpic.circarc(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readType());
            }
            case 17: {
                currpic.ellipse(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc());
            }
            case 18:
            case 19: {
                currpic.elliparc(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc(), this.readType());
            }
            case 21: {
                if (currpic.PrimList.size() > 0) {
                    ((CgmPrimitive)currpic.PrimList.lastElement()).setClosed();
                }
            }
            case 26: {
                currpic.bezier(this.readInt(2), this.readPoints(false));
            }
            default: {
                System.out.println("cgmVA: Unsupported Primitive " + n);
            }
        }
    }
    
    private final void readGraphicsAttributes(final int n) throws IOException {
        switch (n) {
            case 2: {
                super.ContextOrPicture.LineType = this.readInt(2);
            }
            case 3: {
                super.ContextOrPicture.LineWidth = this.readVdc();
            }
            case 4: {
                super.ContextOrPicture.LineColor = this.readColor();
            }
            case 6: {
                super.ContextOrPicture.MarkerType = this.readInt(2);
            }
            case 7: {
                super.ContextOrPicture.MarkerSize = this.readVdc();
            }
            case 8: {
                super.ContextOrPicture.MarkerColor = this.readColor();
            }
            case 10: {
                super.ContextOrPicture.FontIndex = Math.max(1, this.readInt(this.indexprec));
            }
            case 11: {}
            case 12: {
                super.ContextOrPicture.CharacterExpansion = Math.max(0.5, this.readVdc());
            }
            case 13: {
                super.ContextOrPicture.CharacterSpacing = this.readVdc();
            }
            case 14: {
                super.ContextOrPicture.TextColor = this.readColor();
            }
            case 15: {
                super.ContextOrPicture.CharacterHeight = Math.abs(this.readVdc() * super.ContextOrPicture.ay);
            }
            case 16: {
                final double angle = CgmReader.toAngle(this.readVdc(), this.readVdc());
                final double angle2 = CgmReader.toAngle(this.readVdc(), this.readVdc());
                super.ContextOrPicture.CharOri = angle2;
                super.ContextOrPicture.CharSlant = 1.0 / Math.tan(angle2 - angle);
            }
            case 17: {
                super.ContextOrPicture.TextPath = this.readInt(2);
            }
            case 18: {
                super.ContextOrPicture.TextAlignHor = this.readInt(2);
                super.ContextOrPicture.TextAlignVert = this.readInt(2);
            }
            case 22: {
                super.ContextOrPicture.InteriorStyle = this.readInt(2);
                if (super.ContextOrPicture.InteriorStyle > 2) {
                    super.ContextOrPicture.InteriorStyle = 0;
                }
            }
            case 23: {
                super.ContextOrPicture.FillColor = this.readColor();
            }
            case 24: {
                super.ContextOrPicture.HatchIndex = this.readInt(this.indexprec);
                break;
            }
            case 30: {
                super.ContextOrPicture.EdgesVisible = (this.readInt(2) == 1);
                break;
            }
            case 28: {
                super.ContextOrPicture.EdgeWidth = this.readVdc();
                break;
            }
            case 27: {
                super.ContextOrPicture.EdgeType = this.readInt(2);
                break;
            }
            case 29: {
                super.ContextOrPicture.EdgeColor = this.readColor();
            }
            case 34: {
                int int1 = this.readInt(this.colorindexprec);
                while (this.paramlen > 0) {
                    final Color color = this.readColor(0);
                    try {
                        super.ColorTable[int1] = color;
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("ColorTable out of bounds: " + int1);
                    }
                    ++int1;
                }
                super.ColorMode = 2;
            }
            default: {
                System.out.println("Unknown attribute: " + n);
                break;
            }
        }
    }
    
    protected final int readInt() throws Exception {
        return this.readInt(this.colorindexprec);
    }
    
    private final int readInt(final int n) throws IOException {
        if (this.paramContinued && this.paramlen == 0) {
            this.paramlen = this.readParamLen();
        }
        int paramlen = this.paramlen;
        if (this.paramlen > n) {
            this.paramlen -= n;
            paramlen = n;
        }
        else {
            this.paramlen = 0;
        }
        switch (paramlen) {
            case 1: {
                return super.in.readUnsignedByte();
            }
            case 2: {
                return super.in.readShort();
            }
            case 3: {
                return super.in.readShort() << 8 + super.in.readUnsignedByte();
            }
            case 4: {
                return super.in.readInt();
            }
            default: {
                return 0;
            }
        }
    }
    
    private final void readMetaFileDelim(final int n) throws IOException {
        switch (n) {
            case 1: {
                super.cgm.beginMF(this.readString(this.paramlen));
            }
            case 5: {
                super.ContextOrPicture = super.cgm;
            }
            case 2: {
                super.cgm.endMF();
            }
            case 3: {
                super.cgm.beginPic(this.readString(this.paramlen));
                super.ContextOrPicture = super.cgm.currpic;
                break;
            }
        }
    }
    
    private final void readMetaFileDescr(final int n) throws IOException {
        switch (n) {
            case 3: {
                this.VdcIsInt = (this.readInt(2) == 0);
            }
            case 4: {
                this.intprec = this.readPrec();
            }
            case 1:
            case 5: {}
            case 6: {
                this.indexprec = this.readPrec();
            }
            case 7: {
                this.colorprec = this.readPrec();
            }
            case 8: {
                this.colorindexprec = this.readPrec();
            }
            case 9: {
                final int int1;
                if ((int1 = this.readInt(2)) > 256) {
                    super.ColorTable = new Color[int1 + 1];
                }
            }
            case 10: {
                super.colr1 = this.readInt(this.colorprec);
                super.colg1 = this.readInt(this.colorprec);
                super.colb1 = this.readInt(this.colorprec);
                super.colr2 = 255 / (this.readInt(this.colorprec) - super.colr1);
                super.colg2 = 255 / (this.readInt(this.colorprec) - super.colg1);
                super.colb2 = 255 / (this.readInt(this.colorprec) - super.colb1);
            }
            case 12: {
                this.paramlen = 0;
            }
            case 13: {
                while (this.paramlen > 0) {
                    super.cgm.addFont(this.readString(this.readByte()));
                }
            }
            default: {}
        }
    }
    
    private final void readMetaFilePictDescr(final int n) throws IOException {
        switch (n) {
            case 2: {
                switch (this.readInt(2)) {
                    case 0: {
                        super.ColorMode = 2;
                        return;
                    }
                    case 1: {
                        super.ColorMode = 0;
                        return;
                    }
                    default: {
                        System.out.println("Illegal colour mode");
                        System.exit(1);
                        return;
                    }
                }
                break;
            }
            case 3: {
                super.ContextOrPicture.LineWidthMode = this.readInt(2);
            }
            case 4: {
                super.ContextOrPicture.MarkerSizeMode = this.readInt(2);
            }
            case 5: {
                super.ContextOrPicture.EdgeWidthMode = this.readInt(2);
            }
            case 6: {
                super.ContextOrPicture.vdcExt(this.readVdc(), this.readVdc(), this.readVdc(), this.readVdc());
            }
            case 7: {
                super.ContextOrPicture.BackColor = this.readColor(0);
                break;
            }
        }
    }
    
    private final int readParamLen() throws IOException {
        final int unsignedShort = super.in.readUnsignedShort();
        final int n = unsignedShort & 0x7FFF;
        this.paramContinued = (unsignedShort != n);
        return n;
    }
    
    private final double[] readPoints(final boolean b) throws IOException {
        int n = 0;
        int n2 = this.VdcIsInt ? this.vdcintprec : this.vdcrealprec;
        if (b) {
            ++n2;
        }
        int n3 = this.paramlen / n2;
        double[] array = new double[n3];
        while (this.paramlen > 0) {
            while (this.paramlen > 0) {
                array[n++] = this.readVdc();
                array[n++] = this.readVdc();
                if (b) {
                    this.readInt(2);
                }
            }
            if (this.paramContinued) {
                this.paramlen = this.readParamLen();
                n3 += this.paramlen / n2;
                final double[] array2 = new double[n3];
                System.arraycopy(array, 0, array2, 0, n);
                array = array2;
            }
        }
        return array;
    }
    
    private final int readPrec() throws IOException {
        return this.readInt(this.paramlen) + 7 >> 3;
    }
    
    private final String readString() throws IOException {
        if (this.paramlen < 1) {
            return "cgmVA: Invalid string";
        }
        final int unsignedByte = super.in.readUnsignedByte();
        --this.paramlen;
        return this.readString(unsignedByte);
    }
    
    private final String readString(int paramlen) throws IOException {
        if (this.paramlen < paramlen) {
            System.out.println("Bad string");
            paramlen = this.paramlen;
        }
        this.paramlen -= paramlen;
        final byte[] array = new byte[paramlen];
        super.in.readFully(array, 0, paramlen);
        return new String(array);
    }
    
    private final int readType() throws IOException {
        return (this.paramlen == 0) ? -1 : this.readInt(2);
    }
    
    private final double readVdc() throws IOException {
        return this.VdcIsInt ? this.readInt(this.vdcintprec) : this.readFloat();
    }
    
    private final void skip() throws IOException {
        while (this.paramlen > 0) {
            super.in.skipBytes(this.paramlen);
            if (this.paramContinued) {
                this.paramlen = this.readParamLen();
                return;
            }
            this.paramlen = 0;
        }
    }
}
