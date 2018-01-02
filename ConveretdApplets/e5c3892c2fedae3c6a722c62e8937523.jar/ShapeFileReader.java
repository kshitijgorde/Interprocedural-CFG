import java.awt.geom.GeneralPath;
import java.nio.ByteOrder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.io.File;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShapeFileReader
{
    private static final int NULL = 0;
    private static final int POINT = 1;
    private static final int MULTIPOINT = 8;
    private static final int POLYLINE = 3;
    private static final int POLYGON = 5;
    private static final int HEADER_SIZE = 100;
    private FileChannel shapeChannel;
    private FileChannel indexChannel;
    private FileInputStream shapeStream;
    private FileInputStream indexStream;
    private Rectangle2D extent;
    private int numRecords;
    
    public void open(final String s) throws IOException {
        this.shapeStream = new FileInputStream(new File(s + ".shp"));
        this.shapeChannel = this.shapeStream.getChannel();
        this.indexStream = new FileInputStream(new File(s + ".shx"));
        this.indexChannel = this.indexStream.getChannel();
        final ByteBuffer allocate = ByteBuffer.allocate(100);
        if (this.indexChannel.read(allocate, 0L) != 100) {
            throw new IOException("Error reading header from " + s + ".shx");
        }
        allocate.order(ByteOrder.BIG_ENDIAN);
        this.numRecords = (allocate.getInt(24) * 2 - 100) / 8;
        final ByteBuffer allocate2 = ByteBuffer.allocate(100);
        if (this.shapeChannel.read(allocate2, 0L) != 100) {
            throw new IOException("Error reading header from " + s + ".shp");
        }
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        this.extent = new Rectangle2D.Double(allocate2.getDouble(36), allocate2.getDouble(44), allocate2.getDouble(52) - allocate2.getDouble(36), allocate2.getDouble(60) - allocate2.getDouble(44));
    }
    
    public int getRecordCount() {
        return this.numRecords;
    }
    
    public void close() throws IOException {
        if (this.shapeChannel != null) {
            this.shapeChannel.close();
        }
        if (this.shapeStream != null) {
            this.shapeStream.close();
        }
        if (this.indexChannel != null) {
            this.indexChannel.close();
        }
        if (this.indexStream != null) {
            this.indexStream.close();
        }
        this.shapeChannel = null;
        this.shapeStream = null;
        this.indexChannel = null;
        this.indexStream = null;
    }
    
    private int getRecordLength(final int n) throws IOException {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        if (this.shapeChannel.read(allocate, n) != 8) {
            throw new IOException("Error reading record length");
        }
        allocate.order(ByteOrder.BIG_ENDIAN);
        return allocate.getInt(4) * 2;
    }
    
    private int getRecordPosition(final int n) throws IOException {
        final int n2 = 100 + n * 8;
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        if (this.indexChannel.read(allocate, n2) != 8) {
            throw new IOException();
        }
        allocate.order(ByteOrder.BIG_ENDIAN);
        return allocate.getInt(0) * 2;
    }
    
    private void getPoint(final ByteBuffer byteBuffer, final int n, final double[] array) {
        array[0] = byteBuffer.getDouble(n);
        array[1] = byteBuffer.getDouble(n + 8);
    }
    
    public Rectangle2D getBoundingBox(final int n) throws IOException {
        final int recordPosition = this.getRecordPosition(n);
        int recordLength = this.getRecordLength(recordPosition);
        if (recordLength > 36) {
            recordLength = 36;
        }
        final ByteBuffer allocate = ByteBuffer.allocate(recordLength);
        if (this.shapeChannel.read(allocate, recordPosition + 8) != recordLength) {
            throw new IOException();
        }
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        Rectangle2D rectangle2D = null;
        switch (allocate.getInt(0)) {
            case 0: {
                break;
            }
            case 1: {
                final double[] array = new double[2];
                this.getPoint(allocate, 4, array);
                rectangle2D = new Rectangle2D.Double(array[0], array[1], 0.1, 0.1);
                break;
            }
            default: {
                final double double1 = allocate.getDouble(4);
                final double double2 = allocate.getDouble(12);
                double n2 = allocate.getDouble(20) - double1;
                if (n2 < 0.1) {
                    n2 = 0.1;
                }
                double n3 = allocate.getDouble(28) - double2;
                if (n3 < 0.1) {
                    n3 = 0.1;
                }
                rectangle2D = new Rectangle2D.Double(double1, double2, n2, n3);
                break;
            }
        }
        return rectangle2D;
    }
    
    public GeneralPath getShape(final int n, final Rectangle2D rectangle2D) throws IOException {
        final int recordPosition = this.getRecordPosition(n);
        final int recordLength = this.getRecordLength(recordPosition);
        final ByteBuffer allocate = ByteBuffer.allocate(recordLength);
        if (this.shapeChannel.read(allocate, recordPosition + 8) != recordLength) {
            throw new IOException();
        }
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        final int int1 = allocate.getInt(0);
        if (int1 == 0) {
            return null;
        }
        GeneralPath generalPath = null;
        final double[] array = new double[2];
        final double[] array2 = new double[2];
        int n2 = 0;
        switch (int1) {
            case 0: {
                return null;
            }
            case 1: {
                this.getPoint(allocate, 4, array);
                if (rectangle2D.contains(array[0], array[1])) {
                    generalPath = new GeneralPath();
                    generalPath.moveTo((float)array[0], (float)array[1]);
                }
                return generalPath;
            }
            case 8: {
                final int int2 = allocate.getInt(32);
                GeneralPath generalPath2 = new GeneralPath();
                for (int i = 0; i < int2; ++i) {
                    this.getPoint(allocate, 40 + 16 * i, array);
                    if (rectangle2D.contains(array[0], array[1])) {
                        generalPath2.moveTo((float)array[0], (float)array[1]);
                        n2 = 1;
                    }
                }
                if (n2 == 0) {
                    generalPath2 = null;
                }
                return generalPath2;
            }
            case 3: {
                final int int3 = allocate.getInt(36);
                GeneralPath generalPath3 = new GeneralPath();
                final int[] array3 = new int[int3 + 1];
                for (int j = 0; j < int3; ++j) {
                    array3[j] = allocate.getInt(44 + j * 4);
                }
                array3[int3] = (recordLength - 44 - int3 * 4) / 16;
                final int n3 = 44 + int3 * 4;
                for (int k = 0; k < int3; ++k) {
                    int n4 = 0;
                    int n5 = 0;
                    int l = n3 + array3[k] * 16;
                    this.getPoint(allocate, l, array);
                    l += 16;
                    if (rectangle2D.contains(array[0], array[1])) {
                        generalPath3.moveTo((float)array[0], (float)array[1]);
                        n2 = 1;
                    }
                    else {
                        n4 = 1;
                        n5 = 1;
                        array2[0] = array[0];
                        array2[1] = array[1];
                    }
                    while (l < n3 + array3[k + 1] * 16) {
                        this.getPoint(allocate, l, array);
                        if (rectangle2D.contains(array[0], array[1])) {
                            if (n5 != 0) {
                                generalPath3.moveTo((float)array2[0], (float)array2[1]);
                                n5 = 0;
                            }
                            n4 = 0;
                            generalPath3.lineTo((float)array[0], (float)array[1]);
                            n2 = 1;
                        }
                        else if (n4 == 0) {
                            generalPath3.lineTo((float)array[0], (float)array[1]);
                            n4 = 1;
                            n5 = 0;
                        }
                        else {
                            n5 = 1;
                            array2[0] = array[0];
                            array2[1] = array[1];
                        }
                        l += 16;
                    }
                }
                if (n2 == 0) {
                    generalPath3 = null;
                }
                return generalPath3;
            }
            case 5: {
                final int int4 = allocate.getInt(36);
                GeneralPath generalPath4 = new GeneralPath();
                boolean b = false;
                final int[] array4 = new int[int4 + 1];
                for (int n6 = 0; n6 < int4; ++n6) {
                    array4[n6] = allocate.getInt(44 + n6 * 4);
                }
                array4[int4] = (recordLength - 44 - int4 * 4) / 16;
                final int n7 = 44 + int4 * 4;
                for (int n8 = 0; n8 < int4; ++n8) {
                    int n9 = 0;
                    int n10 = 0;
                    int n11 = n7 + array4[n8] * 16;
                    this.getPoint(allocate, n11, array);
                    n11 += 16;
                    if (rectangle2D.contains(array[0], array[1])) {
                        generalPath4.moveTo((float)array[0], (float)array[1]);
                        n2 = 1;
                    }
                    else {
                        b = true;
                        n9 = 1;
                        n10 = 1;
                        array2[0] = array[0];
                        array2[1] = array[1];
                    }
                    while (n11 < n7 + array4[n8 + 1] * 16) {
                        this.getPoint(allocate, n11, array);
                        if (rectangle2D.contains(array[0], array[1])) {
                            if (n10 != 0) {
                                generalPath4.moveTo((float)array2[0], (float)array2[1]);
                                n10 = 0;
                            }
                            n9 = 0;
                            generalPath4.lineTo((float)array[0], (float)array[1]);
                            n2 = 1;
                        }
                        else if (n9 == 0) {
                            generalPath4.lineTo((float)array[0], (float)array[1]);
                            n9 = 1;
                            n10 = 0;
                        }
                        else {
                            b = true;
                            n10 = 1;
                            array2[0] = array[0];
                            array2[1] = array[1];
                        }
                        n11 += 16;
                    }
                }
                if (!b) {
                    generalPath4.closePath();
                }
                if (n2 == 0) {
                    generalPath4 = null;
                }
                return generalPath4;
            }
            default: {
                throw new RuntimeException("Unknown shape type");
            }
        }
    }
    
    public Rectangle2D getExtent() {
        return this.extent;
    }
}
