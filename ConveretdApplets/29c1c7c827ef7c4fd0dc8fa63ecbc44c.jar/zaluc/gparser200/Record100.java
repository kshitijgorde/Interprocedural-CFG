// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.io.IOException;

public class Record100 extends Record
{
    public Record100(final Record record) {
        super(record);
    }
    
    public void write(final int n, final int n2) throws IOException {
        super.outputStream.writeByte(n);
        super.outputStream.writeShort(n2);
    }
    
    public void write(final int n, final String s) throws IOException {
        super.outputStream.writeByte(n);
        super.outputStream.writeUTF(s);
    }
    
    public int readRecordType() throws IOException {
        return super.inputStream.readByte();
    }
    
    public int readIntValue() throws IOException {
        return super.inputStream.readShort();
    }
    
    public String readStrValue() throws IOException {
        return super.inputStream.readUTF();
    }
}
