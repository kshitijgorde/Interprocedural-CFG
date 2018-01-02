// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public interface Guid extends StdCallLibrary
{
    public static class GUID extends Structure
    {
        public int Data1;
        public short Data2;
        public short Data3;
        public byte[] Data4;
        
        public GUID() {
            this.Data4 = new byte[8];
        }
        
        public GUID(final Pointer memory) {
            this.Data4 = new byte[8];
            this.useMemory(memory);
            this.read();
        }
        
        public static class ByReference extends GUID implements Structure.ByReference
        {
            public ByReference() {
            }
            
            public ByReference(final Pointer memory) {
                super(memory);
            }
        }
    }
}
