// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.encoders;

import javax.imageio.stream.ImageOutputStream;
import javax.imageio.ImageWriteParam;
import java.util.Iterator;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriter;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;

public class SunJPEGEncoderAdapter implements ImageEncoder
{
    private float quality;
    
    public SunJPEGEncoderAdapter() {
        this.quality = 0.95f;
    }
    
    public float getQuality() {
        return this.quality;
    }
    
    public void setQuality(final float quality) {
        if (quality < 0.0f || quality > 1.0f) {
            throw new IllegalArgumentException("The 'quality' must be in the range 0.0f to 1.0f");
        }
        this.quality = quality;
    }
    
    public boolean isEncodingAlpha() {
        return false;
    }
    
    public void setEncodingAlpha(final boolean encodingAlpha) {
    }
    
    public byte[] encode(final BufferedImage bufferedImage) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.encode(bufferedImage, outputStream);
        return outputStream.toByteArray();
    }
    
    public void encode(final BufferedImage bufferedImage, final OutputStream outputStream) throws IOException {
        if (bufferedImage == null) {
            throw new IllegalArgumentException("Null 'image' argument.");
        }
        if (outputStream == null) {
            throw new IllegalArgumentException("Null 'outputStream' argument.");
        }
        final Iterator iterator = ImageIO.getImageWritersByFormatName("jpeg");
        final ImageWriter writer = iterator.next();
        final ImageWriteParam p = writer.getDefaultWriteParam();
        p.setCompressionMode(2);
        p.setCompressionQuality(this.quality);
        final ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(ios);
        writer.write(null, new IIOImage(bufferedImage, null, null), p);
        ios.flush();
        writer.dispose();
        ios.close();
    }
}
