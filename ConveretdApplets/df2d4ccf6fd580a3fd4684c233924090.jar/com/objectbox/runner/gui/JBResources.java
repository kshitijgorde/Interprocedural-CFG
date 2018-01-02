// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.io.InputStream;
import com.objectbox.runner.util.JBLogger;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.awt.Image;
import java.awt.Frame;

public final class JBResources
{
    static Frame dummy;
    
    static {
        JBResources.dummy = new Frame();
    }
    
    public Image getImageResource(final String s) {
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            final DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
            if (resourceAsStream != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        byteArrayOutputStream.write(dataInputStream.readByte());
                    }
                    catch (IOException ex) {
                        break;
                    }
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                dataInputStream.close();
                final Image image = Toolkit.getDefaultToolkit().createImage(byteArray);
                final MediaTracker mediaTracker = new MediaTracker(JBResources.dummy);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                return image;
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception i getImageResourec: " + t);
        }
        return null;
    }
}
