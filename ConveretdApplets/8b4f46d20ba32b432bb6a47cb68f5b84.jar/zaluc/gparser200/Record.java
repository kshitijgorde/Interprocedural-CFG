// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Record
{
    public static final int VERSION_100 = 100;
    public static final int VERSION_200 = 200;
    public static final int VERSION = 0;
    public static final int PEOPLE_COUNT = 1;
    public static final int FAMILY_COUNT = 2;
    public static final int PERSON = 3;
    public static final int ID = 4;
    public static final int FIRST_NAME = 5;
    public static final int LAST_NAME = 6;
    public static final int TITLE = 7;
    public static final int NAME_SUFFIX = 8;
    public static final int DETAILS = 9;
    public static final int LIFE_DATES = 10;
    public static final int SEX = 11;
    public static final int FATHER = 12;
    public static final int MOTHER = 13;
    public static final int FAMILY_LINK = 14;
    public static final int FAMILY = 15;
    public static final int CHILD = 16;
    public static final int HIDE = 17;
    public static final int PASSWORD = 18;
    protected DataOutputStream outputStream;
    protected DataInputStream inputStream;
    
    public Record(final DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }
    
    public Record(final DataInputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public Record(final Record record) {
        this.inputStream = record.inputStream;
        this.outputStream = record.outputStream;
    }
    
    public void write(final int n) throws IOException {
        this.outputStream.writeByte(n);
    }
    
    public void write(final int n, final int n2) throws IOException {
        this.outputStream.writeByte(n);
        this.outputStream.writeShort(n2);
    }
    
    public void write(final int n, final String s) throws IOException {
        final StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); ++i) {
            sb.setCharAt(i, (char)(sb.charAt(i) ^ '\u00ff'));
        }
        this.outputStream.writeByte(n);
        this.outputStream.writeUTF(new String(sb));
    }
    
    public int readRecordType() throws IOException {
        return this.inputStream.readByte();
    }
    
    public int readIntValue() throws IOException {
        return this.inputStream.readShort();
    }
    
    public String readStrValue() throws IOException {
        final StringBuffer sb = new StringBuffer(this.inputStream.readUTF());
        for (int i = 0; i < sb.length(); ++i) {
            sb.setCharAt(i, (char)(sb.charAt(i) ^ '\u00ff'));
        }
        return new String(sb);
    }
}
