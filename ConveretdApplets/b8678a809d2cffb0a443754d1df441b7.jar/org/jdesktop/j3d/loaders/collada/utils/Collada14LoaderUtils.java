// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.utils;

import java.awt.Dimension;
import com.sun.jimi.core.JimiReader;
import java.awt.image.ImageObserver;
import com.sun.jimi.core.Jimi;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Collada14LoaderUtils
{
    public static BufferedImage readImage(final URL url, final String path) {
        String str = url.toString();
        str = str.substring(0, str.lastIndexOf("/"));
        final String[] strs = path.split("/");
        String[] array;
        for (int length = (array = strs).length, i = 0; i < length; ++i) {
            final String string = array[i];
            if (string.equals("..")) {
                str = str.substring(0, str.lastIndexOf("/"));
            }
            else if (!string.equals(".")) {
                str = String.valueOf(str) + "/" + string;
            }
        }
        System.out.println("Trying to load image with ImageIO : " + str);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(str));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        if (image == null) {
            try {
                System.out.println("Trying to load image with jimi : " + str);
                final JimiReader reader = Jimi.createJimiReader(new URL(str));
                final Dimension d = reader.getSize();
                final BufferedImage bi = new BufferedImage(d.width, d.height, 2);
                bi.getGraphics().drawImage(reader.getImage(), 0, 0, null);
                image = bi;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
