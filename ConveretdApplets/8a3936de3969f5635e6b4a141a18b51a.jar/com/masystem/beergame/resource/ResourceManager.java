// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.resource;

import java.util.WeakHashMap;
import com.masystem.beergame.ui.graphics.GraphicsTools;
import java.awt.image.BufferedImage;
import java.util.Map;

public final class ResourceManager
{
    private static Map<String, Object> resources;
    
    public static void addResource(final String s, final Object o) {
        ResourceManager.resources.put(s, o);
    }
    
    public static Object getResource(final String s) {
        return ResourceManager.resources.get(s);
    }
    
    public static BufferedImage getOptimizedImage(final String s) {
        final String string = s + "@optimized";
        BufferedImage loadOptimizedImage;
        if ((loadOptimizedImage = ResourceManager.resources.get(string)) == null && (loadOptimizedImage = GraphicsTools.loadOptimizedImage(s)) != null) {
            addResource(string, loadOptimizedImage);
        }
        return loadOptimizedImage;
    }
    
    static {
        ResourceManager.resources = new WeakHashMap<String, Object>();
    }
}
