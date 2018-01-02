import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PTVFile
{
    static final int MASK_CRYPT_SIMPLE = 1;
    static final int MASK_ALLOW_LOCALVIEW = 2;
    static final String fixedKeyString = "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57";
    byte[] fixedKey;
    byte[] domainKey;
    String currentDomainString;
    byte[] currentDomain;
    static final int VERSION = 1;
    String fileName;
    protected ptviewer ptv;
    protected DirectoryEntry[] tileData;
    protected DirectoryEntry previewData;
    protected int nTiles;
    protected int pWidth;
    protected int pHeight;
    protected int dirHeaderSize;
    protected int dirEntrySize;
    protected int dirOffset;
    protected int bitFlags;
    protected int nRows;
    protected int nCols;
    protected boolean hasPreview;
    protected byte[] fullImageBuffer;
    protected boolean usePartialGet;
    protected boolean usingMSVM;
    HttpGetReader hgr;
    
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
        final char[] charArray = "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57".toCharArray();
        for (int i = 0; i < "rW.F)H8Yr6q4p2RA2F&G)8d9kKrE6B3Z9{ek}W2RwMfSg;yRuHw43956v:n57".length(); ++i) {
            this.fixedKey[i] = (byte)charArray[i];
        }
        this.loadHeaderData();
    }
    
    public Image loadPreviewImage() {
        byte[] doPartialGet = null;
        if (!this.hasPreview) {
            return null;
        }
        if (this.usePartialGet) {
            doPartialGet = this.doPartialGet(this.previewData.offset, this.previewData.size);
            if (doPartialGet == null) {
                this.usePartialGet = false;
                System.out.println("PTViewer: abandoned dynamic loading");
                this.loadWholeFile();
            }
        }
        if (!this.usePartialGet) {
            doPartialGet = new byte[this.previewData.size];
            System.arraycopy(this.fullImageBuffer, this.previewData.offset, doPartialGet, 0, this.previewData.size);
        }
        if ((this.bitFlags & 0x1) != 0x0) {
            if (this.currentDomainString.substring(0, 5).equalsIgnoreCase("file:")) {
                if ((this.bitFlags & 0x2) != 0x0) {
                    this.cryptImage(doPartialGet, this.domainKey);
                }
                else {
                    this.cryptImage(doPartialGet, this.currentDomain);
                }
            }
            else {
                this.cryptImage(doPartialGet, this.currentDomain);
            }
        }
        return this.ptv.bufferToImage(doPartialGet);
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
    
    protected void loadHeaderData() {
        if (this.ptv.getDocumentBase().toString().toLowerCase().startsWith("file:")) {
            this.usePartialGet = false;
            System.out.println("PTViewer: reading a local image file");
        }
        else {
            this.usePartialGet = true;
            final byte[] doPartialGet = this.doPartialGet(0, 2000);
            if (doPartialGet == null) {
                System.out.println("PTViewer: abandoned dynamic loading");
                this.usePartialGet = false;
            }
            else {
                this.extractHeaderData(doPartialGet);
                this.extractDirectory(doPartialGet);
            }
        }
        if (!this.usePartialGet) {
            this.loadWholeFile();
        }
    }
    
    protected byte[] doPartialGet(final int n, final int n2) {
        if (this.usingMSVM) {
            return this.hgr.doPartialGet(this.fileName, n, n2);
        }
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.ptv.getDocumentBase(), this.fileName).openConnection();
            httpURLConnection.setRequestProperty("Range", "bytes=" + n + "-" + (n + n2 - 1));
            httpURLConnection.connect();
            final int responseCode = httpURLConnection.getResponseCode();
            final InputStream inputStream = httpURLConnection.getInputStream();
            final byte[] array = new byte[n2];
            if (responseCode != 206) {
                System.out.println("PTViewer: unexpected response code: " + responseCode);
                return null;
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array2 = new byte[4096];
            int read;
            while ((read = inputStream.read(array2)) >= 0) {
                byteArrayOutputStream.write(array2, 0, read);
            }
            byteArrayOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final int length = byteArray.length;
            if (length != n2) {
                System.out.println("PTViewer: number of returned bytes does not match. Requested: " + n2 + "   Returned: " + length);
                return null;
            }
            return byteArray;
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    protected Image loadTileImage(final DirectoryEntry directoryEntry) {
        byte[] doPartialGet = null;
        if (this.usePartialGet) {
            doPartialGet = this.doPartialGet(directoryEntry.offset, directoryEntry.size);
            if (doPartialGet == null) {
                this.usePartialGet = false;
                System.out.println("PTViewer: abandoned dynamic loading");
                this.loadWholeFile();
            }
        }
        if (!this.usePartialGet) {
            doPartialGet = new byte[directoryEntry.size];
            System.arraycopy(this.fullImageBuffer, directoryEntry.offset, doPartialGet, 0, directoryEntry.size);
        }
        if ((this.bitFlags & 0x1) != 0x0) {
            if (this.currentDomainString.substring(0, 5).equalsIgnoreCase("file:")) {
                if ((this.bitFlags & 0x2) != 0x0) {
                    this.cryptImage(doPartialGet, this.domainKey);
                }
                else {
                    this.cryptImage(doPartialGet, this.currentDomain);
                }
            }
            else {
                this.cryptImage(doPartialGet, this.currentDomain);
            }
        }
        return this.ptv.bufferToImage(doPartialGet);
    }
    
    protected void loadTileInPTViewer(final DirectoryEntry directoryEntry) {
        if (directoryEntry.loaded) {
            return;
        }
        this.ptv.loadingROI = true;
        final Image loadTileImage = this.loadTileImage(directoryEntry);
        if (loadTileImage != null) {
            this.ptv.ptinsertImage(this.ptv.pdata, directoryEntry.xPosInPano, directoryEntry.yPosInPano, loadTileImage, (this.ptv.pheight + 99) / 100);
            if (this.ptv.hsready) {
                for (int i = 0; i < this.ptv.numhs; ++i) {
                    if ((this.ptv.hs_imode[i] & 0x4) > 0) {
                        final int n = (int)this.ptv.hs_up[i];
                        final int n2 = (int)this.ptv.hs_vp[i];
                        this.ptv.im_extractRect(this.ptv.pdata, (int)this.ptv.hs_xp[i] - n / 2, (int)this.ptv.hs_yp[i] - n2 / 2, (int[])this.ptv.hs_him[i], n, 0, n2, n, n2);
                    }
                }
            }
            directoryEntry.loaded = true;
        }
        this.ptv.loadingROI = false;
    }
    
    public void loadTiles() {
        int n = 0;
        this.computeYawAngle();
        this.computePitchAngle();
        boolean b;
        do {
            b = true;
            int n2 = -1;
            int n3 = -1;
            double n4 = 10000.0;
            double n5 = 10000.0;
            for (int i = 0; i < this.nTiles; ++i) {
                if (!this.tileData[i].loaded) {
                    b = false;
                    double abs = Math.abs(this.ptv.yaw - this.tileData[i].yaw);
                    if (abs > 180.0) {
                        abs = 360.0 - abs;
                    }
                    final double abs2 = Math.abs(this.ptv.pitch - this.tileData[i].pitch);
                    final double sqrt = Math.sqrt(abs * abs + abs2 * abs2);
                    if (this.isTileVisible(i)) {
                        if (sqrt < n4) {
                            n4 = sqrt;
                            n2 = i;
                        }
                    }
                    else if (sqrt < n5) {
                        n5 = sqrt;
                        n3 = i;
                    }
                }
            }
            int n6;
            if (n2 >= 0) {
                n6 = n2;
            }
            else {
                n6 = n3;
            }
            if (n6 >= 0) {
                this.loadTileInPTViewer(this.tileData[n6]);
                ++n;
                if (this.ptv.showToolbar) {
                    ((toolbar)this.ptv.tlbObj).setBarPerc(n * 100 / this.nTiles);
                }
                if (!this.isTileVisible(n6)) {
                    this.ptv.onlyPaintToolbar = true;
                }
                this.ptv.paintDone = false;
                this.ptv.forceBilIntepolator = true;
                this.ptv.repaint();
                for (int n7 = 0; !this.ptv.paintDone && n7 < 100; ++n7) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {}
                }
            }
        } while (!b);
        if (this.ptv.showToolbar) {
            ((toolbar)this.ptv.tlbObj).setBarPerc(0);
        }
        this.ptv.dirty = true;
        this.ptv.repaint();
    }
    
    protected boolean isTileVisible(final int n) {
        boolean b = true;
        double abs = Math.abs(this.ptv.yaw - this.tileData[n].yaw);
        if (abs > 180.0) {
            abs = 360.0 - abs;
        }
        final double abs2 = Math.abs(this.ptv.pitch - this.tileData[n].pitch);
        if (abs > (this.ptv.hfov + this.tileData[n].wDeg) / 2.0) {
            b = false;
        }
        if (b && abs2 > (this.ptv.math_fovy(this.ptv.hfov, this.ptv.vwidth, this.ptv.vheight) + this.tileData[n].hDeg) / 2.0) {
            b = false;
        }
        return b;
    }
    
    protected void computeYawAngle() {
        for (int i = 0; i < this.nTiles; ++i) {
            double n = 360.0 * (this.tileData[i].xPosInPano + this.tileData[i].width / 2) / this.pWidth;
            if (n > 360.0) {
                n -= 360.0;
            }
            this.tileData[i].yaw = n - 180.0;
        }
    }
    
    protected void computePitchAngle() {
        for (int i = 0; i < this.nTiles; ++i) {
            this.tileData[i].pitch = 90.0 - 180.0 * (this.pWidth / 4 - this.pHeight / 2 + this.tileData[i].yPosInPano + this.tileData[i].height / 2) / (this.pWidth / 2);
        }
    }
    
    protected void extractHeaderData(final byte[] array) {
        if (array[0] == 80 && array[1] == 86) {
            System.out.println("PTViewer: This is not a valid .ptv file");
            this.ptv.fatal = true;
            this.ptv.repaint();
        }
        if (this.extractInt2(array, 2) != 1) {
            System.out.println("PTViewer: unknown version for .ptv file");
            this.ptv.fatal = true;
            this.ptv.repaint();
        }
        this.pWidth = this.extractInt4(array, 4);
        this.pHeight = this.extractInt4(array, 8);
        this.dirHeaderSize = this.extractInt4(array, 12);
        this.dirEntrySize = this.extractInt4(array, 16);
        this.dirOffset = this.extractInt4(array, 20);
        this.bitFlags = this.extractInt4(array, 24);
        this.domainKey = null;
        this.currentDomainString = "";
        if ((this.bitFlags & 0x1) != 0x0) {
            final int int2 = this.extractInt2(array, 28);
            if ((this.bitFlags & 0x2) != 0x0) {
                final byte[] array2 = new byte[int2];
                for (int i = 0; i < int2; ++i) {
                    array2[i] = array[30 + i];
                }
                this.domainKey = this.cryptByteArray(array2, this.cryptComputeFixedKey(int2));
            }
            else {
                this.domainKey = new byte[] { 3, 4, 5, 6, 7, 8, 9 };
            }
            this.domainKey = this.cryptResizeKey(this.domainKey, 2000);
            int length = int2;
            if (this.ptv.getDocumentBase().toString().length() < length - 2) {
                length = this.ptv.getDocumentBase().toString().length();
            }
            this.currentDomainString = this.ptv.getDocumentBase().toString().substring(0, length - 2);
            this.currentDomainString = this.currentDomainString.toLowerCase();
            this.currentDomain = this.cryptCreateDomainKey(this.currentDomainString);
            this.currentDomain = this.cryptResizeKey(this.currentDomain, 2000);
        }
    }
    
    protected void extractDirectory(byte[] doPartialGet) {
        int dirOffset = this.dirOffset;
        this.nRows = this.extractInt2(doPartialGet, dirOffset);
        dirOffset += 2;
        this.nCols = this.extractInt2(doPartialGet, dirOffset);
        dirOffset += 2;
        this.hasPreview = (this.extractInt2(doPartialGet, dirOffset) != 0);
        this.nTiles = this.nRows * this.nCols;
        this.tileData = new DirectoryEntry[this.nTiles];
        if (this.hasPreview) {
            this.previewData = new DirectoryEntry();
        }
        else {
            this.previewData = null;
        }
        int n = this.dirOffset + this.dirHeaderSize;
        if (this.hasPreview) {
            n += this.dirEntrySize;
        }
        final int n2 = n + this.dirEntrySize * this.nTiles;
        if (n2 > doPartialGet.length && this.usePartialGet) {
            doPartialGet = this.doPartialGet(0, n2 + 100);
        }
        int n3 = this.dirOffset + this.dirHeaderSize;
        if (this.hasPreview) {
            this.extractDirectoryEntry(doPartialGet, n3, this.previewData);
            n3 += this.dirEntrySize;
        }
        for (int i = 0; i < this.nTiles; ++i) {
            this.extractDirectoryEntry(doPartialGet, n3, this.tileData[i] = new DirectoryEntry());
            n3 += this.dirEntrySize;
        }
    }
    
    protected void extractDirectoryEntry(final byte[] array, final int n, final DirectoryEntry directoryEntry) {
        directoryEntry.nRow = this.extractInt2(array, n);
        directoryEntry.nCol = this.extractInt2(array, n + 2);
        directoryEntry.width = this.extractInt4(array, n + 4);
        directoryEntry.height = this.extractInt4(array, n + 8);
        directoryEntry.xPosInPano = this.extractInt4(array, n + 12);
        directoryEntry.yPosInPano = this.extractInt4(array, n + 16);
        directoryEntry.offset = this.extractInt4(array, n + 20);
        directoryEntry.size = this.extractInt4(array, n + 24);
        directoryEntry.wDeg = 1.0 * directoryEntry.width * 360.0 / this.pWidth;
        directoryEntry.hDeg = 1.0 * directoryEntry.height * 360.0 / this.pWidth;
    }
    
    protected int extractInt2(final byte[] array, final int n) {
        return this.unsignedByte2Int(array[n]) + (this.unsignedByte2Int(array[n + 1]) << 8);
    }
    
    protected int extractInt4(final byte[] array, final int n) {
        return this.unsignedByte2Int(array[n]) + (this.unsignedByte2Int(array[n + 1]) << 8) + (this.unsignedByte2Int(array[n + 2]) << 16) + (this.unsignedByte2Int(array[n + 3]) << 24);
    }
    
    protected int unsignedByte2Int(final byte b) {
        return b & 0xFF;
    }
    
    protected byte[] cryptComputeFixedKey(int i) {
        final int length = this.fixedKey.length;
        final byte[] array = new byte[i];
        int n = 0;
        while (i > length) {
            for (int j = 0; j < length; ++j, ++n) {
                array[n] = this.fixedKey[j];
            }
            i -= length;
        }
        for (int k = 0; k < i; ++k, ++n) {
            array[n] = this.fixedKey[k];
        }
        return array;
    }
    
    protected byte[] cryptResizeKey(final byte[] array, int i) {
        final int length = array.length;
        final byte[] array2 = new byte[i];
        int n = 0;
        while (i > length) {
            for (int j = 0; j < length; ++j, ++n) {
                array2[n] = array[j];
            }
            i -= length;
        }
        for (int k = 0; k < i; ++k, ++n) {
            array2[n] = array[k];
        }
        return array2;
    }
    
    protected void cryptImage(final byte[] array, final byte[] array2) {
        int n = array2.length;
        if (array.length < n) {
            n = array.length;
        }
        for (int i = 0; i < n; ++i) {
            final int n2 = i;
            array[n2] ^= array2[i];
        }
    }
    
    protected byte[] cryptByteArray(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
        return array3;
    }
    
    protected short cryptHashString(final String s) {
        int n = 0;
        int n2 = 19;
        final int n3 = 17;
        for (int i = 0; i < s.length(); ++i) {
            n = (n * n2 + s.charAt(i) & 0x3FFF);
            n2 = (n2 * n3 & 0x3FFF);
        }
        return (short)(n & 0xFFFF);
    }
    
    protected void cryptShort2Str(final short n, final byte[] array) {
        final byte b = (byte)(n & 0xFF);
        array[0] = (byte)((n & 0xFF00) / 256);
        array[1] = b;
    }
    
    protected byte[] cryptCreateDomainKey(final String s) {
        final byte[] array = new byte[s.length() + 2];
        this.cryptShort2Str(this.cryptHashString(s), array);
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            array[i + 2] = (byte)charArray[i];
        }
        return array;
    }
    
    protected class DirectoryEntry
    {
        int nRow;
        int nCol;
        int width;
        int height;
        int xPosInPano;
        int yPosInPano;
        int offset;
        int size;
        boolean loaded;
        double yaw;
        double pitch;
        double wDeg;
        double hDeg;
        
        public DirectoryEntry() {
            this.loaded = false;
        }
    }
}
