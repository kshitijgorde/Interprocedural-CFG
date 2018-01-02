// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

public class TransParser
{
    private ByteChain byteStore;
    private boolean isGood;
    public static final int HEADER_LEN = 5;
    private byte[] lengthShort;
    
    public TransParser() {
        this.isGood = true;
        this.byteStore = new ByteChain();
        this.lengthShort = new byte[2];
        this.isGood = true;
    }
    
    public void add(final byte[] array, final int n) {
        if (!this.isValid()) {
            return;
        }
        this.byteStore.add(array, 0, n);
        if (this.byteStore.isFull()) {
            System.err.println("err729938229.");
            this.isGood = false;
        }
    }
    
    public TransPack getNext() {
        if (!this.isValid()) {
            return null;
        }
        if (this.byteStore.isFull()) {
            System.err.println("err3722227773.");
            this.isGood = false;
            return null;
        }
        if (this.byteStore.length() < 5) {
            return null;
        }
        this.lengthShort[0] = this.byteStore.get(3);
        this.lengthShort[1] = this.byteStore.get(4);
        final short short1 = ByteUtil.getShort(this.lengthShort);
        if (short1 < 0) {
            System.err.println("err3734988772.");
            this.isGood = false;
            return null;
        }
        if (this.byteStore.length() < short1 + 5) {
            return null;
        }
        final TransPack transPack = new TransPack();
        transPack.ident[0] = this.byteStore.get(0);
        transPack.ident[1] = this.byteStore.get(1);
        transPack.type = this.byteStore.get(2);
        transPack.length = short1;
        transPack.isValid = true;
        transPack.data = this.byteStore.getBytes(5, transPack.length);
        this.byteStore.shiftLeft(5 + short1);
        if (transPack.data == null) {
            System.err.println("err374823913.");
            transPack.isValid = false;
            return transPack;
        }
        if (transPack.length != transPack.data.length) {
            System.err.println("err3782731," + transPack.length + " " + transPack.data.length);
            transPack.isValid = false;
        }
        return transPack;
    }
    
    public boolean isValid() {
        return this.isGood;
    }
    
    public int currentLength() {
        return this.byteStore.length();
    }
}
