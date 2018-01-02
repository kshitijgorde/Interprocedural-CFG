// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.awt.Image;
import java.applet.Applet;

public class PTVFile
{
    static final int MASK_ALLOW_LOCALVIEW = 2;
    static final int MASK_CRYPT_SIMPLE = 1;
    static final int VERSION = 1;
    protected int bitFlags;
    byte[] currentDomain;
    String currentDomainString;
    protected int dirEntrySize;
    protected int dirHeaderSize;
    protected int dirOffset;
    byte[] domainKey;
    String fileName;
    byte[] fixedKey;
    static final String fixedKeyString = "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57";
    protected byte[] fullImageBuffer;
    protected boolean hasPreview;
    HttpGetReader hgr;
    protected int nCols;
    protected int nRows;
    protected int nTiles;
    protected int pHeight;
    protected int pWidth;
    protected DirectoryEntry previewData;
    protected ptviewer ptv;
    protected DirectoryEntry[] tileData;
    protected boolean usePartialGet;
    protected boolean usingMSVM;
    
    public PTVFile(final ptviewer ptv, final String fileName) {
        this.hgr = null;
        this.ptv = ptv;
        this.fileName = fileName;
        this.fullImageBuffer = null;
        this.usingMSVM = (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") >= 0);
        if (this.usingMSVM) {
            System.out.println("PTViewer: running in the Microsoft VM");
            this.hgr = new HttpGetReader(ptv);
        }
        this.fixedKey = new byte["rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57".length()];
        final char[] ca = "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57".toCharArray();
        for (int k = 0; k < "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57".length(); ++k) {
            this.fixedKey[k] = (byte)ca[k];
        }
        this.loadHeaderData();
    }
    
    protected void computePitchAngle() {
        for (int k = 0; k < this.nTiles; ++k) {
            final int y = this.pWidth / 4 - this.pHeight / 2 + this.tileData[k].yPosInPano + this.tileData[k].height / 2;
            final double t = 90.0 - 180.0 * y / (this.pWidth / 2);
            this.tileData[k].pitch = t;
        }
    }
    
    protected void computeYawAngle() {
        for (int k = 0; k < this.nTiles; ++k) {
            double p = 360.0 * (this.tileData[k].xPosInPano + this.tileData[k].width / 2) / this.pWidth;
            if (p > 360.0) {
                p -= 360.0;
            }
            p -= 180.0;
            this.tileData[k].yaw = p;
        }
    }
    
    protected byte[] cryptByteArray(final byte[] s, final byte[] key) {
        final byte[] retVal = new byte[s.length];
        for (int k = 0; k < key.length; ++k) {
            retVal[k] = (byte)(s[k] ^ key[k]);
        }
        return retVal;
    }
    
    protected byte[] cryptComputeFixedKey(int lKey) {
        final int lFixed = this.fixedKey.length;
        final byte[] retVal = new byte[lKey];
        int j = 0;
        while (lKey > lFixed) {
            for (int k = 0; k < lFixed; ++k, ++j) {
                retVal[j] = this.fixedKey[k];
            }
            lKey -= lFixed;
        }
        for (int k = 0; k < lKey; ++k, ++j) {
            retVal[j] = this.fixedKey[k];
        }
        return retVal;
    }
    
    protected byte[] cryptCreateDomainKey(final String domain) {
        final byte[] retVal = new byte[domain.length() + 2];
        final short i = this.cryptHashString(domain);
        this.cryptShort2Str(i, retVal);
        final char[] ba = domain.toCharArray();
        for (int k = 0; k < ba.length; ++k) {
            retVal[k + 2] = (byte)ba[k];
        }
        return retVal;
    }
    
    protected short cryptHashString(final String s) {
        int retVal = 0;
        int a = 19;
        final int b = 17;
        for (int i = 0; i < s.length(); ++i) {
            retVal = retVal * a + s.charAt(i);
            retVal &= 0x3FFF;
            a *= b;
            a &= 0x3FFF;
        }
        return (short)(retVal & 0xFFFF);
    }
    
    protected void cryptImage(final byte[] img, final byte[] key) {
        int nBytes = key.length;
        if (img.length < nBytes) {
            nBytes = img.length;
        }
        for (int k = 0; k < nBytes; ++k) {
            final int n = k;
            img[n] ^= key[k];
        }
    }
    
    protected byte[] cryptResizeKey(final byte[] orgKey, int lKey) {
        final int lOrg = orgKey.length;
        final byte[] retVal = new byte[lKey];
        int j = 0;
        while (lKey > lOrg) {
            for (int k = 0; k < lOrg; ++k, ++j) {
                retVal[j] = orgKey[k];
            }
            lKey -= lOrg;
        }
        for (int k = 0; k < lKey; ++k, ++j) {
            retVal[j] = orgKey[k];
        }
        return retVal;
    }
    
    protected void cryptShort2Str(final short i, final byte[] ba) {
        final byte bl = (byte)(i & 0xFF);
        final byte bh = (byte)((i & 0xFF00) / 256);
        ba[0] = bh;
        ba[1] = bl;
    }
    
    protected byte[] doPartialGet(final int start, final int nBytes) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        ino360both/PTVFile.usingMSVM:Z
        //     4: ifeq            21
        //     7: aload_0         /* this */
        //     8: getfield        ino360both/PTVFile.hgr:Lino360both/HttpGetReader;
        //    11: aload_0         /* this */
        //    12: getfield        ino360both/PTVFile.fileName:Ljava/lang/String;
        //    15: iload_1         /* start */
        //    16: iload_2         /* nBytes */
        //    17: invokevirtual   ino360both/HttpGetReader.doPartialGet:(Ljava/lang/String;II)[B
        //    20: areturn        
        //    21: new             Ljava/net/URL;
        //    24: dup            
        //    25: aload_0         /* this */
        //    26: getfield        ino360both/PTVFile.ptv:Lino360both/ptviewer;
        //    29: invokevirtual   ino360both/ptviewer.getDocumentBase:()Ljava/net/URL;
        //    32: aload_0         /* this */
        //    33: getfield        ino360both/PTVFile.fileName:Ljava/lang/String;
        //    36: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //    39: astore          url
        //    41: aload           url
        //    43: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    46: checkcast       Ljava/net/HttpURLConnection;
        //    49: astore          Connection
        //    51: new             Ljava/lang/StringBuffer;
        //    54: dup            
        //    55: invokespecial   java/lang/StringBuffer.<init>:()V
        //    58: ldc             "bytes="
        //    60: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    63: iload_1         /* start */
        //    64: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //    67: ldc             "-"
        //    69: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    72: iload_1         /* start */
        //    73: iload_2         /* nBytes */
        //    74: iadd           
        //    75: iconst_1       
        //    76: isub           
        //    77: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //    80: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    83: astore_3        /* sRange */
        //    84: aload           Connection
        //    86: ldc             "Range"
        //    88: aload_3         /* sRange */
        //    89: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    92: aload           Connection
        //    94: invokevirtual   java/net/HttpURLConnection.connect:()V
        //    97: aload           Connection
        //    99: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   102: istore          responseCode
        //   104: aload           Connection
        //   106: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   109: astore          input
        //   111: iload_2         /* nBytes */
        //   112: newarray        B
        //   114: astore          buffer
        //   116: iload           responseCode
        //   118: sipush          206
        //   121: if_icmpeq       152
        //   124: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   127: new             Ljava/lang/StringBuffer;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuffer.<init>:()V
        //   134: ldc             "PTViewer: unexpected response code: "
        //   136: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   139: iload           responseCode
        //   141: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   144: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   147: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   150: aconst_null    
        //   151: areturn        
        //   152: new             Ljava/io/ByteArrayOutputStream;
        //   155: dup            
        //   156: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   159: astore          ba
        //   161: sipush          4096
        //   164: newarray        B
        //   166: astore          tmpBuf
        //   168: goto            182
        //   171: aload           ba
        //   173: aload           tmpBuf
        //   175: iconst_0       
        //   176: iload           10
        //   178: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   181: nop            
        //   182: aload           input
        //   184: aload           tmpBuf
        //   186: invokevirtual   java/io/InputStream.read:([B)I
        //   189: dup            
        //   190: istore          tmpLen
        //   192: ifge            171
        //   195: aload           ba
        //   197: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   200: aload           ba
        //   202: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   205: astore          buffer
        //   207: aload           buffer
        //   209: arraylength    
        //   210: istore          len
        //   212: iload           len
        //   214: iload_2         /* nBytes */
        //   215: if_icmpeq       255
        //   218: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   221: new             Ljava/lang/StringBuffer;
        //   224: dup            
        //   225: invokespecial   java/lang/StringBuffer.<init>:()V
        //   228: ldc             "PTViewer: number of returned bytes does not match. Requested: "
        //   230: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   233: iload_2         /* nBytes */
        //   234: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   237: ldc             "   Returned: "
        //   239: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   242: iload           len
        //   244: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   247: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   250: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   253: aconst_null    
        //   254: areturn        
        //   255: aload           buffer
        //   257: areturn        
        //   258: astore          ex
        //   260: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   263: aload           ex
        //   265: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   268: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   271: aconst_null    
        //   272: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name          Signature
        //  -----  ------  ----  ------------  -------------------------------
        //  0      273     0     this          Lino360both/PTVFile;
        //  0      273     1     start         I
        //  0      273     2     nBytes        I
        //  84     189     3     sRange        Ljava/lang/String;
        //  116    157     4     buffer        [B
        //  104    169     5     responseCode  I
        //  41     217     6     url           Ljava/net/URL;
        //  51     207     7     Connection    Ljava/net/HttpURLConnection;
        //  111    147     8     input         Ljava/io/InputStream;
        //  161    97      9     ba            Ljava/io/ByteArrayOutputStream;
        //  192    66      10    tmpLen        I
        //  168    90      11    tmpBuf        [B
        //  212    46      12    len           I
        //  260    13      6     ex            Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     151    258    273    Ljava/lang/Exception;
        //  152    254    258    273    Ljava/lang/Exception;
        //  255    257    258    273    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void extractDirectory(byte[] buf) {
        int off = this.dirOffset;
        this.nRows = this.extractInt2(buf, off);
        off += 2;
        this.nCols = this.extractInt2(buf, off);
        off += 2;
        final int itmp = this.extractInt2(buf, off);
        this.hasPreview = (itmp != 0);
        this.nTiles = this.nRows * this.nCols;
        this.tileData = new DirectoryEntry[this.nTiles];
        if (this.hasPreview) {
            this.previewData = new DirectoryEntry();
        }
        else {
            this.previewData = null;
        }
        int neededBytes = this.dirOffset + this.dirHeaderSize;
        if (this.hasPreview) {
            neededBytes += this.dirEntrySize;
        }
        neededBytes += this.dirEntrySize * this.nTiles;
        if (neededBytes > buf.length && this.usePartialGet) {
            buf = this.doPartialGet(0, neededBytes + 100);
            goto Label_0169;
        }
        off = this.dirOffset + this.dirHeaderSize;
        if (this.hasPreview) {
            this.extractDirectoryEntry(buf, off, this.previewData);
            off += this.dirEntrySize;
        }
        for (int k = 0; k < this.nTiles; ++k) {
            this.extractDirectoryEntry(buf, off, this.tileData[k] = new DirectoryEntry());
            off += this.dirEntrySize;
        }
    }
    
    protected void extractDirectoryEntry(final byte[] buf, final int offset, final DirectoryEntry de) {
        de.nRow = this.extractInt2(buf, offset);
        de.nCol = this.extractInt2(buf, offset + 2);
        de.width = this.extractInt4(buf, offset + 4);
        de.height = this.extractInt4(buf, offset + 8);
        de.xPosInPano = this.extractInt4(buf, offset + 12);
        de.yPosInPano = this.extractInt4(buf, offset + 16);
        de.offset = this.extractInt4(buf, offset + 20);
        de.size = this.extractInt4(buf, offset + 24);
        de.wDeg = 1.0 * de.width * 360.0 / this.pWidth;
        de.hDeg = 1.0 * de.height * 360.0 / this.pWidth;
    }
    
    protected void extractHeaderData(final byte[] buf) {
        if (buf[0] == 80 && buf[1] == 86) {
            System.out.println("PTViewer: This is not a valid .ptv file");
            this.ptv.fatal = true;
            this.ptv.repaint();
        }
        final int vers = this.extractInt2(buf, 2);
        if (vers != 1) {
            System.out.println("PTViewer: unknown version for .ptv file");
            this.ptv.fatal = true;
            this.ptv.repaint();
        }
        this.pWidth = this.extractInt4(buf, 4);
        this.pHeight = this.extractInt4(buf, 8);
        this.dirHeaderSize = this.extractInt4(buf, 12);
        this.dirEntrySize = this.extractInt4(buf, 16);
        this.dirOffset = this.extractInt4(buf, 20);
        this.bitFlags = this.extractInt4(buf, 24);
        this.domainKey = null;
        this.currentDomainString = "";
        if ((this.bitFlags & 0x1) != 0x0) {
            final int lKey = this.extractInt2(buf, 28);
            if ((this.bitFlags & 0x2) != 0x0) {
                final byte[] tmp = new byte[lKey];
                for (int k = 0; k < lKey; ++k) {
                    tmp[k] = buf[30 + k];
                }
                final byte[] key = this.cryptComputeFixedKey(lKey);
                this.domainKey = this.cryptByteArray(tmp, key);
            }
            else {
                this.domainKey = new byte[] { 3, 4, 5, 6, 7, 8, 9 };
            }
            this.domainKey = this.cryptResizeKey(this.domainKey, 2000);
            int nCh = lKey;
            if (this.ptv.getDocumentBase().toString().length() < nCh - 2) {
                nCh = this.ptv.getDocumentBase().toString().length();
            }
            this.currentDomainString = this.ptv.getDocumentBase().toString().substring(0, nCh - 2);
            this.currentDomainString = this.currentDomainString.toLowerCase();
            this.currentDomain = this.cryptCreateDomainKey(this.currentDomainString);
            this.currentDomain = this.cryptResizeKey(this.currentDomain, 2000);
        }
    }
    
    protected int extractInt2(final byte[] buf, final int offset) {
        return this.unsignedByte2Int(buf[offset]) + (this.unsignedByte2Int(buf[offset + 1]) << 8);
    }
    
    protected int extractInt4(final byte[] buf, final int offset) {
        return this.unsignedByte2Int(buf[offset]) + (this.unsignedByte2Int(buf[offset + 1]) << 8) + (this.unsignedByte2Int(buf[offset + 2]) << 16) + (this.unsignedByte2Int(buf[offset + 3]) << 24);
    }
    
    protected boolean isTileVisible(final int nTile) {
        boolean visible = true;
        double yawDist = Math.abs(this.ptv.yaw - this.tileData[nTile].yaw);
        if (yawDist > 180.0) {
            yawDist = 360.0 - yawDist;
        }
        final double pitchDist = Math.abs(this.ptv.pitch - this.tileData[nTile].pitch);
        if (yawDist > (this.ptv.hfov + this.tileData[nTile].wDeg) / 2.0) {
            visible = false;
        }
        if (visible && pitchDist > (this.ptv.math_fovy(this.ptv.hfov, this.ptv.vwidth, this.ptv.vheight) + this.tileData[nTile].hDeg) / 2.0) {
            visible = false;
            goto Label_0141;
        }
        return visible;
    }
    
    protected void loadHeaderData() {
        if (this.ptv.getDocumentBase().toString().toLowerCase().startsWith("file:")) {
            this.usePartialGet = false;
            System.out.println("PTViewer: reading a local image file");
        }
        else {
            this.usePartialGet = true;
            final byte[] buf = this.doPartialGet(0, 2000);
            if (buf == null) {
                System.out.println("PTViewer: abandoned dynamic loading");
                this.usePartialGet = false;
            }
            else {
                this.extractHeaderData(buf);
                this.extractDirectory(buf);
            }
        }
        if (!this.usePartialGet) {
            this.loadWholeFile();
        }
    }
    
    public Image loadPreviewImage() {
        byte[] buf = null;
        if (!this.hasPreview) {
            return null;
        }
        if (this.usePartialGet) {
            buf = this.doPartialGet(this.previewData.offset, this.previewData.size);
            if (buf == null) {
                this.usePartialGet = false;
                System.out.println("PTViewer: abandoned dynamic loading");
                this.loadWholeFile();
            }
        }
        if (!this.usePartialGet) {
            buf = new byte[this.previewData.size];
            System.arraycopy(this.fullImageBuffer, this.previewData.offset, buf, 0, this.previewData.size);
        }
        if ((this.bitFlags & 0x1) != 0x0) {
            if (this.currentDomainString.substring(0, 5).equalsIgnoreCase("file:")) {
                if ((this.bitFlags & 0x2) != 0x0) {
                    this.cryptImage(buf, this.domainKey);
                }
                else {
                    this.cryptImage(buf, this.currentDomain);
                }
            }
            else {
                this.cryptImage(buf, this.currentDomain);
            }
        }
        final Image img = this.ptv.bufferToImage(buf);
        return img;
    }
    
    protected Image loadTileImage(final DirectoryEntry de) {
        byte[] buf = null;
        if (this.usePartialGet) {
            buf = this.doPartialGet(de.offset, de.size);
            if (buf == null) {
                this.usePartialGet = false;
                System.out.println("PTViewer: abandoned dynamic loading");
                this.loadWholeFile();
            }
        }
        if (!this.usePartialGet) {
            buf = new byte[de.size];
            System.arraycopy(this.fullImageBuffer, de.offset, buf, 0, de.size);
        }
        if ((this.bitFlags & 0x1) != 0x0) {
            if (this.currentDomainString.substring(0, 5).equalsIgnoreCase("file:")) {
                if ((this.bitFlags & 0x2) != 0x0) {
                    this.cryptImage(buf, this.domainKey);
                }
                else {
                    this.cryptImage(buf, this.currentDomain);
                }
            }
            else {
                this.cryptImage(buf, this.currentDomain);
            }
        }
        final Image img = this.ptv.bufferToImage(buf);
        return img;
    }
    
    protected void loadTileInPTViewer(final DirectoryEntry de) {
        if (de.loaded) {
            return;
        }
        this.ptv.loadingROI = true;
        Image r = null;
        r = this.loadTileImage(de);
        if (r != null) {
            this.ptv.ptinsertImage(this.ptv.pdata, de.xPosInPano, de.yPosInPano, r, (this.ptv.pheight + 99) / 100);
            if (this.ptv.hsready) {
                for (int k = 0; k < this.ptv.numhs; ++k) {
                    if ((this.ptv.hs_imode[k] & 0x4) > 0) {
                        final int w = (int)this.ptv.hs_up[k];
                        final int h = (int)this.ptv.hs_vp[k];
                        final int xp = (int)this.ptv.hs_xp[k] - w / 2;
                        final int yp = (int)this.ptv.hs_yp[k] - h / 2;
                        this.ptv.im_extractRect(this.ptv.pdata, xp, yp, (int[])this.ptv.hs_him[k], w, 0, h, w, h);
                    }
                }
            }
            de.loaded = true;
            r = null;
        }
        this.ptv.loadingROI = false;
    }
    
    public void loadTiles() {
        int nLoaded = 0;
        this.computeYawAngle();
        this.computePitchAngle();
        boolean done;
        do {
            done = true;
            int iVisible = -1;
            int iNotVisible = -1;
            double minDistVisible = 10000.0;
            double minDistNotVisible = 10000.0;
            for (int k = 0; k < this.nTiles; ++k) {
                if (!this.tileData[k].loaded) {
                    done = false;
                    double distX = Math.abs(this.ptv.yaw - this.tileData[k].yaw);
                    if (distX > 180.0) {
                        distX = 360.0 - distX;
                    }
                    final double distY = Math.abs(this.ptv.pitch - this.tileData[k].pitch);
                    final double dist = Math.sqrt(distX * distX + distY * distY);
                    if (this.isTileVisible(k)) {
                        if (dist < minDistVisible) {
                            minDistVisible = dist;
                            iVisible = k;
                        }
                    }
                    else if (dist < minDistNotVisible) {
                        minDistNotVisible = dist;
                        iNotVisible = k;
                    }
                }
            }
            int i;
            if (iVisible >= 0) {
                i = iVisible;
            }
            else {
                i = iNotVisible;
            }
            if (i >= 0) {
                this.loadTileInPTViewer(this.tileData[i]);
                ++nLoaded;
                if (this.ptv.showToolbar) {
                    ((toolbar)this.ptv.tlbObj).setBarPerc(nLoaded * 100 / this.nTiles);
                }
                if (!this.isTileVisible(i)) {
                    this.ptv.onlyPaintToolbar = true;
                }
                this.ptv.paintDone = false;
                this.ptv.forceBilIntepolator = true;
                this.ptv.repaint();
                for (int counter = 0; !this.ptv.paintDone && counter < 100; ++counter) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception _ex) {}
                }
            }
        } while (!done);
        if (this.ptv.showToolbar) {
            ((toolbar)this.ptv.tlbObj).setBarPerc(0);
        }
        this.ptv.dirty = true;
        this.ptv.repaint();
    }
    
    protected void loadWholeFile() {
        System.out.println("PTViewer: loading whole file");
        this.ptv.percent[0] = 0;
        this.fullImageBuffer = this.ptv.file_read(this.fileName, this.ptv.percent);
        if (this.fullImageBuffer == null) {
            System.out.println("PTViewer: unable to load panorama file: probably file not found");
            this.ptv.fatal = true;
            this.ptv.repaint();
        }
        this.extractHeaderData(this.fullImageBuffer);
        this.extractDirectory(this.fullImageBuffer);
    }
    
    protected int unsignedByte2Int(final byte b) {
        return b & 0xFF;
    }
    
    protected class DirectoryEntry
    {
        double hDeg;
        int height;
        boolean loaded;
        int nCol;
        int nRow;
        int offset;
        double pitch;
        int size;
        double wDeg;
        int width;
        int xPosInPano;
        int yPosInPano;
        double yaw;
        
        public DirectoryEntry() {
            this.loaded = false;
        }
    }
}
