// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GenFile extends RandomAccessFile
{
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
    public static final int ALIVE = 17;
    public static final int PASSWORD = 18;
    public static final int MPASSWORD = 19;
    
    public GenFile(final String s) throws IOException {
        super(s, "rw");
    }
    
    public void write(final int n, final int n2) throws IOException {
        this.writeByte(n);
        this.writeShort(n2);
    }
    
    public void write(final int n, final String s) throws IOException {
        this.writeByte(n);
        this.writeUTF(s);
    }
    
    public int readRecordType() throws IOException {
        return this.readByte();
    }
    
    public int readIntValue() throws IOException {
        return this.readShort();
    }
    
    public String readStrValue() throws IOException {
        return this.readUTF();
    }
    
    public void modifyRecord(final String s) throws IOException {
        this.writeUTF(s);
    }
}
