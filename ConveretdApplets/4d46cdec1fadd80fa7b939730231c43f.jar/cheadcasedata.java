import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.IndexColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cheadcasedata
{
    private final int MAX_HEADCASE_EXPRESSIONS = 10;
    private final float HEADCASE_SCALEFACTOR = 0.0036303f;
    private final boolean READ_FROM_FILE = false;
    public float m_fAdjustedScaleFactor;
    public int m_nWidth;
    public int m_nHeight;
    public int m_nChar1;
    public int m_nChar2;
    public int m_nVersion;
    public int m_nNumberOfPointsForBase;
    public int m_nNumberOfTrianglesForBase;
    public int m_nNumberOfExpressions;
    public byte[] m_pFileInMemory;
    public int m_nFileInMemoryOffset;
    public int m_nBytesRead;
    public cheadcaseexpression m_LiveExpression;
    public cheadcase3dvertex[] pTriangles;
    cheadcaseexpression[] m_pArrayExpression;
    public IndexColorModel m_MainIndexColorModel;
    public byte[][] m_MainPaletteTable;
    public byte[] m_MainPixels;
    public byte[] m_DibPixels;
    int m_nTriangles;
    ctriangle[] m_vTriangles;
    
    void AdjustExpressions() {
        for (int i = 1; i < this.m_nNumberOfExpressions; ++i) {
            for (int j = 0; j < this.m_pArrayExpression[i].m_nNumberOfPoints; ++j) {
                final cheadcase3dvertex cheadcase3dvertex = this.m_pArrayExpression[i].m_p3DPoints[j];
                cheadcase3dvertex.x -= this.m_pArrayExpression[0].m_p3DPoints[this.m_pArrayExpression[i].m_p3DPoints[j].index].x;
                final cheadcase3dvertex cheadcase3dvertex2 = this.m_pArrayExpression[i].m_p3DPoints[j];
                cheadcase3dvertex2.y -= this.m_pArrayExpression[0].m_p3DPoints[this.m_pArrayExpression[i].m_p3DPoints[j].index].y;
                final cheadcase3dvertex cheadcase3dvertex3 = this.m_pArrayExpression[i].m_p3DPoints[j];
                cheadcase3dvertex3.z -= this.m_pArrayExpression[0].m_p3DPoints[this.m_pArrayExpression[i].m_p3DPoints[j].index].z;
            }
        }
    }
    
    void DrawTriangles() {
        for (int i = 0; i < this.m_nTriangles; ++i) {
            this.DrawAffineTriangle(this.m_vTriangles[i]);
        }
    }
    
    public boolean LoadIntoMemory(final String s) {
        return true;
    }
    
    public boolean Load(final String s, final int nWidth, final int nHeight) {
        this.m_nWidth = nWidth;
        this.m_nHeight = nHeight;
        int n;
        if (nWidth < nHeight) {
            n = nWidth;
        }
        else {
            n = nHeight;
        }
        this.m_pFileInMemory = new byte[70000];
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {}
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openConnection().getInputStream());
            int i;
            do {
                i = dataInputStream.read(this.m_pFileInMemory, this.m_nBytesRead, 100);
                this.m_nBytesRead += i;
            } while (i != -1);
            dataInputStream.close();
        }
        catch (IOException ex2) {
            return false;
        }
        this.m_nFileInMemoryOffset = 0;
        this.m_nChar1 = this.ScanNextNumber();
        this.m_nChar2 = this.ScanNextNumber();
        this.m_nVersion = this.ScanNextNumber();
        this.m_nNumberOfExpressions = this.ScanNextNumber();
        this.m_nNumberOfPointsForBase = this.ScanNextNumber();
        this.m_pArrayExpression[0] = new cheadcaseexpression();
        this.m_pArrayExpression[0].m_nNumberOfPoints = this.m_nNumberOfPointsForBase;
        this.m_pArrayExpression[0].m_p3DPoints = new cheadcase3dvertex[this.m_nNumberOfPointsForBase];
        this.m_LiveExpression.m_p3DPoints = new cheadcase3dvertex[this.m_nNumberOfPointsForBase];
        for (int j = 0; j < this.m_nNumberOfPointsForBase; ++j) {
            this.m_pArrayExpression[0].m_p3DPoints[j] = new cheadcase3dvertex();
            this.m_pArrayExpression[0].m_p3DPoints[j].index = j;
            final int scanNextNumber = this.ScanNextNumber();
            final int scanNextNumber2 = this.ScanNextNumber();
            final int scanNextNumber3 = this.ScanNextNumber();
            this.m_pArrayExpression[0].m_p3DPoints[j].x = scanNextNumber;
            this.m_pArrayExpression[0].m_p3DPoints[j].y = 255 - scanNextNumber2;
            this.m_pArrayExpression[0].m_p3DPoints[j].z = scanNextNumber3;
        }
        final cheadcase3dvertex[] array = new cheadcase3dvertex[this.m_nNumberOfPointsForBase];
        for (int k = 1; k < this.m_nNumberOfExpressions; ++k) {
            this.m_pArrayExpression[k] = new cheadcaseexpression();
            this.m_pArrayExpression[k].m_nNumberOfPoints = 0;
            for (int l = 0; l < this.m_nNumberOfPointsForBase; ++l) {
                final int scanNextNumber4 = this.ScanNextNumber();
                final int scanNextNumber5 = this.ScanNextNumber();
                final int scanNextNumber6 = this.ScanNextNumber();
                final int y = 255 - scanNextNumber5;
                if (scanNextNumber4 != this.m_pArrayExpression[0].m_p3DPoints[l].x || y != this.m_pArrayExpression[0].m_p3DPoints[l].y || scanNextNumber6 != this.m_pArrayExpression[0].m_p3DPoints[l].z) {
                    array[this.m_pArrayExpression[k].m_nNumberOfPoints] = new cheadcase3dvertex();
                    array[this.m_pArrayExpression[k].m_nNumberOfPoints].index = l;
                    array[this.m_pArrayExpression[k].m_nNumberOfPoints].x = scanNextNumber4;
                    array[this.m_pArrayExpression[k].m_nNumberOfPoints].y = y;
                    array[this.m_pArrayExpression[k].m_nNumberOfPoints].z = scanNextNumber6;
                    final cheadcaseexpression cheadcaseexpression = this.m_pArrayExpression[k];
                    ++cheadcaseexpression.m_nNumberOfPoints;
                }
            }
            this.m_pArrayExpression[k].m_p3DPoints = new cheadcase3dvertex[this.m_pArrayExpression[k].m_nNumberOfPoints];
            for (int n2 = 0; n2 < this.m_pArrayExpression[k].m_nNumberOfPoints; ++n2) {
                this.m_pArrayExpression[k].m_p3DPoints[n2] = new cheadcase3dvertex();
                this.m_pArrayExpression[k].m_p3DPoints[n2].index = array[n2].index;
                this.m_pArrayExpression[k].m_p3DPoints[n2].x = array[n2].x;
                this.m_pArrayExpression[k].m_p3DPoints[n2].y = array[n2].y;
                this.m_pArrayExpression[k].m_p3DPoints[n2].z = array[n2].z;
            }
        }
        this.m_nNumberOfTrianglesForBase = this.ScanNextNumber();
        this.pTriangles = new cheadcase3dvertex[this.m_nNumberOfTrianglesForBase];
        for (int index = 0; index < this.m_nNumberOfTrianglesForBase; ++index) {
            final int scanNextNumber7 = this.ScanNextNumber();
            final int scanNextNumber8 = this.ScanNextNumber();
            final int scanNextNumber9 = this.ScanNextNumber();
            this.pTriangles[index] = new cheadcase3dvertex();
            this.pTriangles[index].index = index;
            this.pTriangles[index].x = scanNextNumber7;
            this.pTriangles[index].y = scanNextNumber8;
            this.pTriangles[index].z = scanNextNumber9;
        }
        for (int n3 = 0; n3 < this.m_nNumberOfTrianglesForBase; ++n3) {
            final float n4 = (float)((this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n3].x].z + this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n3].y].z + this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n3].z].z) / 3.0);
            for (int n5 = 0; n5 < n3; ++n5) {
                if (n4 < (float)((this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n5].x].z + this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n5].y].z + this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n5].z].z) / 3.0)) {
                    final int index2 = this.pTriangles[n3].index;
                    this.pTriangles[n3].index = this.pTriangles[n5].index;
                    this.pTriangles[n5].index = index2;
                    final int x = this.pTriangles[n3].x;
                    this.pTriangles[n3].x = this.pTriangles[n5].x;
                    this.pTriangles[n5].x = x;
                    final int y2 = this.pTriangles[n3].y;
                    this.pTriangles[n3].y = this.pTriangles[n5].y;
                    this.pTriangles[n5].y = y2;
                    final int z = this.pTriangles[n3].z;
                    this.pTriangles[n3].z = this.pTriangles[n5].z;
                    this.pTriangles[n5].z = z;
                }
            }
        }
        this.m_fAdjustedScaleFactor = 1.0f;
        this.m_fAdjustedScaleFactor = n / 256.0f * 0.85f;
        for (int n6 = 0; n6 < this.m_pArrayExpression[0].m_nNumberOfPoints; ++n6) {
            final cheadcase3dvertex cheadcase3dvertex = this.m_pArrayExpression[0].m_p3DPoints[n6];
            cheadcase3dvertex.x -= 128;
            final cheadcase3dvertex cheadcase3dvertex2 = this.m_pArrayExpression[0].m_p3DPoints[n6];
            cheadcase3dvertex2.y -= 128;
            final cheadcase3dvertex cheadcase3dvertex3 = this.m_pArrayExpression[0].m_p3DPoints[n6];
            cheadcase3dvertex3.x *= (int)this.m_fAdjustedScaleFactor;
            final cheadcase3dvertex cheadcase3dvertex4 = this.m_pArrayExpression[0].m_p3DPoints[n6];
            cheadcase3dvertex4.y *= (int)this.m_fAdjustedScaleFactor;
            final cheadcase3dvertex cheadcase3dvertex5 = this.m_pArrayExpression[0].m_p3DPoints[n6];
            cheadcase3dvertex5.z *= (int)this.m_fAdjustedScaleFactor;
        }
        for (int n7 = 1; n7 < this.m_nNumberOfExpressions; ++n7) {
            for (int n8 = 0; n8 < this.m_pArrayExpression[n7].m_nNumberOfPoints; ++n8) {
                final cheadcase3dvertex cheadcase3dvertex6 = this.m_pArrayExpression[n7].m_p3DPoints[n8];
                cheadcase3dvertex6.x -= 128;
                final cheadcase3dvertex cheadcase3dvertex7 = this.m_pArrayExpression[n7].m_p3DPoints[n8];
                cheadcase3dvertex7.y -= 128;
                final cheadcase3dvertex cheadcase3dvertex8 = this.m_pArrayExpression[n7].m_p3DPoints[n8];
                cheadcase3dvertex8.x *= (int)this.m_fAdjustedScaleFactor;
                final cheadcase3dvertex cheadcase3dvertex9 = this.m_pArrayExpression[n7].m_p3DPoints[n8];
                cheadcase3dvertex9.y *= (int)this.m_fAdjustedScaleFactor;
                final cheadcase3dvertex cheadcase3dvertex10 = this.m_pArrayExpression[n7].m_p3DPoints[n8];
                cheadcase3dvertex10.z *= (int)this.m_fAdjustedScaleFactor;
            }
        }
        this.m_MainPaletteTable = new byte[3][256];
        int n9 = 0;
        do {
            this.m_MainPaletteTable[0][n9] = (byte)this.ScanNextNumber();
            this.m_MainPaletteTable[1][n9] = (byte)this.ScanNextNumber();
            this.m_MainPaletteTable[2][n9] = (byte)this.ScanNextNumber();
        } while (++n9 < 256);
        this.m_MainIndexColorModel = new IndexColorModel(8, 256, this.m_MainPaletteTable[0], this.m_MainPaletteTable[1], this.m_MainPaletteTable[2]);
        this.m_MainPixels = new byte[nWidth * nHeight];
        this.m_DibPixels = new byte[65536];
        int n10 = 255;
        do {
            int n11 = 0;
            do {
                this.m_DibPixels[n11 * 256 + n10] = (byte)this.ScanNextNumber();
            } while (++n11 < 256);
        } while (--n10 >= 0);
        this.m_nTriangles = this.m_nNumberOfTrianglesForBase;
        for (int n12 = 0; n12 < this.m_nNumberOfTrianglesForBase; ++n12) {
            this.m_vTriangles[n12] = new ctriangle();
            this.m_vTriangles[n12].v[0] = new cvertex();
            this.m_vTriangles[n12].v[1] = new cvertex();
            this.m_vTriangles[n12].v[2] = new cvertex();
            this.m_vTriangles[n12].v[0].u = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].x].x / this.m_fAdjustedScaleFactor + 128.0f;
            this.m_vTriangles[n12].v[0].v = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].x].y / this.m_fAdjustedScaleFactor + 128.0f;
            this.m_vTriangles[n12].v[1].u = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].y].x / this.m_fAdjustedScaleFactor + 128.0f;
            this.m_vTriangles[n12].v[1].v = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].y].y / this.m_fAdjustedScaleFactor + 128.0f;
            this.m_vTriangles[n12].v[2].u = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].z].x / this.m_fAdjustedScaleFactor + 128.0f;
            this.m_vTriangles[n12].v[2].v = this.m_pArrayExpression[0].m_p3DPoints[this.pTriangles[n12].z].y / this.m_fAdjustedScaleFactor + 128.0f;
        }
        this.AdjustExpressions();
        for (int n13 = 0; n13 < this.m_pArrayExpression[0].m_nNumberOfPoints; ++n13) {
            this.m_LiveExpression.m_p3DPoints[n13] = new cheadcase3dvertex();
        }
        return true;
    }
    
    public int ScanNextNumber() {
        int n = this.m_pFileInMemory[this.m_nFileInMemoryOffset++];
        if (n < 0) {
            n += 256;
        }
        return n;
    }
    
    void ResetLiveExpression() {
        for (int i = 0; i < this.m_pArrayExpression[0].m_nNumberOfPoints; ++i) {
            this.m_LiveExpression.m_p3DPoints[i].index = this.m_pArrayExpression[0].m_p3DPoints[i].index;
            this.m_LiveExpression.m_p3DPoints[i].x = this.m_pArrayExpression[0].m_p3DPoints[i].x;
            this.m_LiveExpression.m_p3DPoints[i].y = this.m_pArrayExpression[0].m_p3DPoints[i].y;
            this.m_LiveExpression.m_p3DPoints[i].z = this.m_pArrayExpression[0].m_p3DPoints[i].z;
        }
    }
    
    public cheadcasedata() {
        this.m_pArrayExpression = new cheadcaseexpression[10];
        this.m_LiveExpression = new cheadcaseexpression();
        this.m_vTriangles = new ctriangle[180];
    }
    
    void DrawAffineScanline(int n, int n2, int n3, int n4, int n5, int n6, final int n7) {
        if (n2 < n) {
            final int n8 = n;
            n = n2;
            n2 = n8;
            final int n9 = n3;
            n3 = n4;
            n4 = n9;
            final int n10 = n5;
            n5 = n6;
            n6 = n10;
        }
        int n11 = n2 - n;
        int n12 = 0;
        int n13 = 0;
        if (n11 > 0) {
            n12 = (n4 - n3 << 16) / n11;
            n13 = (n6 - n5 << 16) / n11;
        }
        int n14 = -n7 * this.m_nWidth + n + this.m_nWidth * (this.m_nHeight / 2) + this.m_nWidth / 2;
        n3 <<= 16;
        n5 <<= 16;
        while (n11-- > 0) {
            this.m_MainPixels[n14++] = this.m_DibPixels[(n3 >> 16 << 8) + (n5 >> 16)];
            n3 += n12;
            n5 += n13;
        }
    }
    
    public int ScanNextStringNumber() {
        int n = 0;
        byte b;
        do {
            b = this.m_pFileInMemory[this.m_nFileInMemoryOffset++];
        } while (b < 48 || b > 57);
        do {
            n = n * 10 + (b - 48);
            b = this.m_pFileInMemory[this.m_nFileInMemoryOffset++];
        } while (b >= 48 && b <= 57);
        return n;
    }
    
    public void CHeadCaseData_CleanUp() {
    }
    
    public boolean LoadHCFile(final String s) {
        return true;
    }
    
    void DrawAffineTriangle(final ctriangle ctriangle) {
        int n = 0;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        float n5 = 0.0f;
        float n6 = 0.0f;
        float n7 = 0.0f;
        int n8 = 1;
        do {
            if (ctriangle.v[n8].sy < ctriangle.v[n].sy) {
                n = n8;
            }
        } while (++n8 < 3);
        int n9 = n + 1;
        int n10 = n - 1;
        if (n9 > 2) {
            n9 = 0;
        }
        if (n10 < 0) {
            n10 = 2;
        }
        int n11 = (int)ctriangle.v[n].sy;
        float n12;
        float sx = n12 = ctriangle.v[n].sx;
        float n13;
        float u = n13 = ctriangle.v[n].u;
        float n14;
        float v = n14 = ctriangle.v[n].v;
        int n15 = (int)(ctriangle.v[n9].sy - ctriangle.v[n].sy);
        int n16 = (int)(ctriangle.v[n10].sy - ctriangle.v[n].sy);
        if (n15 > 0) {
            n2 = (ctriangle.v[n9].sx - ctriangle.v[n].sx) / n15;
            n4 = (ctriangle.v[n9].u - ctriangle.v[n].u) / n15;
            n6 = (ctriangle.v[n9].v - ctriangle.v[n].v) / n15;
        }
        if (n16 > 0) {
            n3 = (ctriangle.v[n10].sx - ctriangle.v[n].sx) / n16;
            n5 = (ctriangle.v[n10].u - ctriangle.v[n].u) / n16;
            n7 = (ctriangle.v[n10].v - ctriangle.v[n].v) / n16;
        }
        while (n15 > 0 && n16 > 0) {
            this.DrawAffineScanline((int)sx, (int)n12, (int)u, (int)n13, (int)v, (int)n14, n11);
            ++n11;
            --n15;
            --n16;
            sx += n2;
            n12 += n3;
            u += n4;
            n13 += n5;
            v += n6;
            n14 += n7;
        }
        if (n15 <= 0) {
            int n17 = n9 + 1;
            if (n17 > 2) {
                n17 = 0;
            }
            n15 = (int)(ctriangle.v[n17].sy - ctriangle.v[n9].sy);
            if (n15 > 0) {
                n2 = (ctriangle.v[n17].sx - ctriangle.v[n9].sx) / n15;
                n4 = (ctriangle.v[n17].u - ctriangle.v[n9].u) / n15;
                n6 = (ctriangle.v[n17].v - ctriangle.v[n9].v) / n15;
            }
            sx = ctriangle.v[n9].sx;
            u = ctriangle.v[n9].u;
            v = ctriangle.v[n9].v;
        }
        if (n16 <= 0) {
            int n18 = n10 - 1;
            if (n18 < 0) {
                n18 = 2;
            }
            n16 = (int)(ctriangle.v[n18].sy - ctriangle.v[n10].sy);
            if (n16 > 0) {
                n3 = (ctriangle.v[n18].sx - ctriangle.v[n10].sx) / n16;
                n5 = (ctriangle.v[n18].u - ctriangle.v[n10].u) / n16;
                n7 = (ctriangle.v[n18].v - ctriangle.v[n10].v) / n16;
            }
            n12 = ctriangle.v[n10].sx;
            n13 = ctriangle.v[n10].u;
            n14 = ctriangle.v[n10].v;
        }
        while (n15 > 0 && n16 > 0) {
            this.DrawAffineScanline((int)sx, (int)n12, (int)u, (int)n13, (int)v, (int)n14, n11);
            ++n11;
            --n15;
            --n16;
            sx += n2;
            n12 += n3;
            u += n4;
            n13 += n5;
            v += n6;
            n14 += n7;
        }
    }
}
