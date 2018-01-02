// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

import java.util.Enumeration;
import com.tn.util.Array;
import java.io.IOException;
import java.util.Hashtable;
import com.tn.util.FileLoader;

public class MemoryFactory
{
    private FileLoader ivFileLoader;
    private Hashtable ivMemoryTable;
    
    public MemoryFactory(final FileLoader pFileLoader) {
        this.ivFileLoader = pFileLoader;
        this.ivMemoryTable = new Hashtable();
    }
    
    public Memory createRam(final int pSize) {
        final int[] bytes = new int[pSize];
        return new Memory(bytes, bytes);
    }
    
    public Memory createRom(final byte[] pRomData) {
        final Memory result = this.createRom(pRomData.length);
        final int[] rombytes = result.getBytesRead();
        for (int i = 0; i < pRomData.length; ++i) {
            rombytes[i] = (pRomData[i] & 0xFF);
        }
        return result;
    }
    
    public Memory createRom(final int pSize) {
        try {
            return this.createRom(pSize, null);
        }
        catch (IOException e) {
            throw new RuntimeException("IOException occured(?!): " + e);
        }
    }
    
    public Memory createRom(final int pSize, final String pFileName) throws IOException {
        final String tag = pSize + " " + pFileName;
        int[] bytesRead;
        if (pFileName != null) {
            if (this.ivMemoryTable.contains(tag)) {
                bytesRead = this.ivMemoryTable.get(pFileName);
            }
            else {
                bytesRead = Array.bytesToInts(this.ivFileLoader.readFile(pFileName, pSize));
                this.ivMemoryTable.put(tag, bytesRead);
            }
        }
        else {
            bytesRead = new int[pSize];
        }
        final String dummyTag = pSize + " DUMMY WRITE";
        int[] bytesWrite;
        if (this.ivMemoryTable.contains(dummyTag)) {
            bytesWrite = this.ivMemoryTable.get(dummyTag);
        }
        else {
            bytesWrite = new int[pSize];
            this.ivMemoryTable.put(dummyTag, bytesWrite);
        }
        return new Memory(bytesRead, bytesWrite);
    }
    
    @Override
    public String toString() {
        String result = "MemoryFactory " + super.toString();
        final Enumeration e = this.ivMemoryTable.keys();
        while (e.hasMoreElements()) {
            result = String.valueOf(result) + "\n  " + e.nextElement();
        }
        return result;
    }
}
