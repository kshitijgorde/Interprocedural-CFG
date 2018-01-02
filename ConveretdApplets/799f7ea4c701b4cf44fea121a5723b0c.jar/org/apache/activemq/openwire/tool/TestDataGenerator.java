// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

public class TestDataGenerator
{
    private int stringCounter;
    private boolean boolCounter;
    private byte byteCounter;
    private char charCounter;
    private short shortCounter;
    private int intCounter;
    private long longCounter;
    
    public TestDataGenerator() {
        this.charCounter = 'a';
    }
    
    public String createByte() {
        final StringBuilder append = new StringBuilder().append("(byte) ");
        final byte byteCounter = (byte)(this.byteCounter + 1);
        this.byteCounter = byteCounter;
        return append.append(byteCounter).toString();
    }
    
    public String createChar() {
        final StringBuilder append = new StringBuilder().append("'");
        final char charCounter = this.charCounter;
        this.charCounter = (char)(charCounter + '\u0001');
        return append.append(charCounter).append("'").toString();
    }
    
    public String createShort() {
        final StringBuilder append = new StringBuilder().append("(short) ");
        final short shortCounter = (short)(this.shortCounter + 1);
        this.shortCounter = shortCounter;
        return append.append(shortCounter).toString();
    }
    
    public int createInt() {
        return ++this.intCounter;
    }
    
    public long createLong() {
        return ++this.longCounter;
    }
    
    public String createString(final String property) {
        return property + ":" + ++this.stringCounter;
    }
    
    public boolean createBool() {
        return this.boolCounter = !this.boolCounter;
    }
    
    public String createByteArray(final String property) {
        return "\"" + this.createString(property) + "\".getBytes()";
    }
}
