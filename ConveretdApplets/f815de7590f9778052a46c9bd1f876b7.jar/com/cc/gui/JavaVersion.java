// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class JavaVersion
{
    public static void setAntialias(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public static Image bytesToImage(final ByteArrayInputStream byteArrayInputStream) {
        try {
            return ImageIO.read(byteArrayInputStream);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
