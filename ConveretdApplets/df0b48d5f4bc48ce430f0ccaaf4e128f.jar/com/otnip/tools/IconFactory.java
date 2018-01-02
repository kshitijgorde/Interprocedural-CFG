// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.ArrayList;

public class IconFactory
{
    private static final String[] extentions;
    private static final ArrayList<String> classpaths;
    private static final HashMap<String, ImageIcon> icons;
    private static final HashMap<String, BufferedImage> images;
    
    public static void addClasspath(String classpath) {
        classpath = formatClasspath(classpath);
        if (!IconFactory.classpaths.contains(classpath)) {
            IconFactory.classpaths.add(classpath);
        }
    }
    
    @Deprecated
    public static ImageIcon get(String name) {
        if (name.length() > 0 && name.charAt(0) != '/') {
            name = "/" + name;
        }
        ImageIcon icon = IconFactory.icons.get(name);
        for (int classpathIndex = 0; icon == null && classpathIndex < IconFactory.classpaths.size(); ++classpathIndex) {
            final String classpath = IconFactory.classpaths.get(classpathIndex);
            try {
                URL url = IconFactory.class.getResource(classpath + name);
                String extention;
                for (int i = 0; url == null && i < IconFactory.extentions.length; url = IconFactory.class.getResource(classpath + name + extention), ++i) {
                    extention = IconFactory.extentions[i];
                }
                if (url != null) {
                    icon = new ImageIcon(url);
                    IconFactory.icons.put(name, icon);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return icon;
    }
    
    public static ImageIcon getIcon(String name) {
        if (name.length() > 0 && name.charAt(0) != '/') {
            name = "/" + name;
        }
        ImageIcon icon = IconFactory.icons.get(name);
        for (int classpathIndex = 0; icon == null && classpathIndex < IconFactory.classpaths.size(); ++classpathIndex) {
            final String classpath = IconFactory.classpaths.get(classpathIndex);
            try {
                URL url = IconFactory.class.getResource(classpath + name);
                String extention;
                for (int i = 0; url == null && i < IconFactory.extentions.length; url = IconFactory.class.getResource(classpath + name + extention), ++i) {
                    extention = IconFactory.extentions[i];
                }
                if (url != null) {
                    icon = new ImageIcon(url);
                    IconFactory.icons.put(name, icon);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return icon;
    }
    
    public static BufferedImage getImage(String name) {
        if (name.length() > 0 && name.charAt(0) != '/') {
            name = "/" + name;
        }
        BufferedImage image = IconFactory.images.get(name);
        for (int classpathIndex = 0; image == null && classpathIndex < IconFactory.classpaths.size(); ++classpathIndex) {
            final String classpath = IconFactory.classpaths.get(classpathIndex);
            try {
                URL url = IconFactory.class.getResource(classpath + name);
                String extention;
                for (int i = 0; url == null && i < IconFactory.extentions.length; url = IconFactory.class.getResource(classpath + name + extention), ++i) {
                    extention = IconFactory.extentions[i];
                }
                if (url != null) {
                    try {
                        image = ImageIO.read(url);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    IconFactory.images.put(name, image);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return image;
    }
    
    private static String formatClasspath(String classpath) {
        if (classpath == null) {
            classpath = "";
        }
        else if (classpath.length() > 0) {
            if (classpath.charAt(classpath.length() - 1) == '/') {
                classpath = classpath.substring(0, classpath.length() - 1);
            }
            if (classpath.length() > 0 && classpath.charAt(0) != '/') {
                classpath = "/" + classpath;
            }
        }
        return classpath;
    }
    
    private IconFactory() {
        throw new UnsupportedOperationException("Utility Class");
    }
    
    public static void main(final String[] args) {
        try {
            addClasspath("com/otnip/media");
            addClasspath("/com/otnip/media/");
            System.out.println(get("oTnip_logo_64"));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    static {
        extentions = new String[] { ".png", ".jpg", ".gif" };
        classpaths = new ArrayList<String>();
        icons = new HashMap<String, ImageIcon>();
        images = new HashMap<String, BufferedImage>();
    }
}
