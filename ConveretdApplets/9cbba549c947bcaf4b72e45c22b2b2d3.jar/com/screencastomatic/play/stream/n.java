// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.io.DataInputStream;
import java.io.InputStream;
import javax.imageio.ImageReader;

public class n
{
    private ImageReader a;
    private InputStream b;
    private DataInputStream c;
    
    public n(final InputStream b, final String s) {
        this.b = b;
        this.c = new DataInputStream(b);
        ImageIO.setUseCache(false);
        final Iterator<ImageReader> imageReadersBySuffix = ImageIO.getImageReadersBySuffix(s);
        if (!imageReadersBySuffix.hasNext()) {
            throw new IllegalStateException("No readers found for type: " + s);
        }
        this.a = imageReadersBySuffix.next();
    }
    
    public BufferedImage a() {
        final int int1 = this.c.readInt();
        final byte[] array = new byte[int1];
        for (int i = 0; i < int1; i += this.b.read(array, i, int1 - i)) {}
        this.a.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(array)));
        return this.a.read(0);
    }
    
    public void b() {
        final int int1 = this.c.readInt();
        if (int1 != this.b.skip(int1)) {
            throw new IOException("Failed to skip next image.");
        }
    }
}
