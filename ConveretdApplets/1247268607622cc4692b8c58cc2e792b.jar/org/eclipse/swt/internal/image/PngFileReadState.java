// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

class PngFileReadState
{
    boolean readIHDR;
    boolean readPLTE;
    boolean readIDAT;
    boolean readIEND;
    boolean readTRNS;
    boolean readPixelData;
}
