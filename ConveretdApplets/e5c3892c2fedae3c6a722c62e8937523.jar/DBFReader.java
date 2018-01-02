import java.nio.ByteOrder;
import java.io.IOException;
import java.io.File;
import java.nio.ByteBuffer;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DBFReader
{
    private FileChannel dbfChannel;
    private FileInputStream dbfStream;
    private ColumnInfo[] columnInfo;
    private int numRecords;
    private int headerLength;
    private int recordLength;
    private int numColumns;
    private ByteBuffer recordBuffer;
    private int recordBufferIndex;
    private final int FILE_INFO_SIZE = 32;
    private final int COLUMN_INFO_SIZE = 32;
    
    public void open(final String s) throws IOException {
        this.dbfStream = new FileInputStream(new File(s + ".dbf"));
        this.dbfChannel = this.dbfStream.getChannel();
        final ByteBuffer allocate = ByteBuffer.allocate(32);
        if (this.dbfChannel.read(allocate, 0L) != 32) {
            throw new IOException("Error reading " + s + ".dbf header");
        }
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        this.numRecords = allocate.getInt(4);
        this.headerLength = allocate.getShort(8);
        this.recordLength = allocate.getShort(10);
        this.numColumns = (this.headerLength - 32) / 32;
        this.recordBuffer = ByteBuffer.allocate(this.recordLength);
        this.recordBufferIndex = -1;
        final ByteBuffer allocate2 = ByteBuffer.allocate(this.headerLength - 32);
        if (this.dbfChannel.read(allocate2, 32L) != this.headerLength - 32) {
            throw new IOException("Error reading " + s + ".dbf column info");
        }
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        final byte[] array = new byte[11];
        this.columnInfo = new ColumnInfo[this.numColumns];
        for (int i = 0; i < this.numColumns; ++i) {
            final ColumnInfo columnInfo = new ColumnInfo();
            this.columnInfo[i] = columnInfo;
            final int n = 32 * i;
            columnInfo.type = (char)allocate2.get(n + 11);
            if (columnInfo.type == 'N' || columnInfo.type == 'F') {
                columnInfo.size = allocate2.get(n + 16);
                columnInfo.decimals = allocate2.get(n + 17);
            }
            else {
                columnInfo.size = allocate2.getShort(n + 16);
                columnInfo.decimals = 0;
            }
            if (i == 0) {
                columnInfo.offset = 1;
            }
            else {
                columnInfo.offset = this.columnInfo[i - 1].offset + this.columnInfo[i - 1].size;
            }
            for (int j = 0; j < array.length; ++j) {
                array[j] = allocate2.get(n + j);
            }
            columnInfo.name = new String(array).trim();
        }
    }
    
    public String[] getColumnNames() {
        final String[] array = new String[this.numColumns];
        for (int i = 0; i < this.numColumns; ++i) {
            array[i] = this.columnInfo[i].name;
        }
        return array;
    }
    
    public int getRecordCount() {
        return this.numRecords;
    }
    
    public void close() throws IOException {
        this.dbfChannel.close();
        this.dbfStream.close();
    }
    
    private int getRecordPosition(final int n) throws IOException {
        return n * this.recordLength + this.headerLength;
    }
    
    public int getColumnIndex(final String s) {
        int n = -1;
        for (int i = 0; i < this.numColumns; ++i) {
            if (s.equals(this.columnInfo[i].name)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public String readColumn(final int recordBufferIndex, final int n) throws IOException {
        if (this.recordBufferIndex != recordBufferIndex) {
            this.recordBuffer.clear();
            if (this.dbfChannel.read(this.recordBuffer, this.getRecordPosition(recordBufferIndex)) != this.recordLength) {
                throw new IOException("Error reading record at index " + recordBufferIndex);
            }
            this.recordBufferIndex = recordBufferIndex;
        }
        final int offset = this.columnInfo[n].offset;
        final int size = this.columnInfo[n].size;
        final byte[] array = new byte[size];
        for (int i = 0; i < size; ++i) {
            array[i] = this.recordBuffer.get(offset + i);
        }
        return new String(array, 0, size).trim();
    }
    
    public String[] readRecord(final int n) throws IOException {
        final String[] array = new String[this.numColumns];
        for (int i = 0; i < this.numColumns; ++i) {
            array[i] = this.readColumn(n, i);
        }
        return array;
    }
    
    class ColumnInfo
    {
        String name;
        int offset;
        int size;
        int decimals;
        char type;
    }
}
