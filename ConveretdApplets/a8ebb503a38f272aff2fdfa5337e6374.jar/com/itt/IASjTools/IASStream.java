// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

import java.net.URISyntaxException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;

public interface IASStream
{
    void openImage(final String p0, final String p1, final String p2, final boolean p3, final File p4) throws MalformedURLException, IOException, URISyntaxException;
    
    void cancelStream();
    
    void setWindow(final int p0, final int p1, final int p2, final int p3, final int p4, final int[] p5, final int p6, final float[] p7, final float[] p8, final double p9, final boolean p10) throws Exception;
    
    Object getRawData();
}
