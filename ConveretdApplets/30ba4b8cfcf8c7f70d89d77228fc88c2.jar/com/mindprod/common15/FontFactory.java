// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common15;

import java.awt.Font;
import java.util.HashMap;

public class FontFactory
{
    private static final HashMap<String, String> otherTransforms;
    private static final HashMap<String, String> vistaTransforms;
    
    public static Font build(String fontFamilyName, final int style, final int size) {
        final String os = System.getProperty("os.name", "unknown");
        final String changedFontName = (os.equals("Windows Vista") || os.equals("Windows 7")) ? FontFactory.vistaTransforms.get(fontFamilyName) : FontFactory.otherTransforms.get(fontFamilyName);
        if (changedFontName != null) {
            fontFamilyName = changedFontName;
        }
        return new Font(fontFamilyName, style, size);
    }
    
    static {
        otherTransforms = new HashMap<String, String>(17);
        (vistaTransforms = new HashMap<String, String>(17)).put("Arrows", "Dialog");
        FontFactory.vistaTransforms.put("Dialog", "Segoe UI");
        FontFactory.vistaTransforms.put("DialogInput", "Consolas");
        FontFactory.vistaTransforms.put("Monospaced", "Consolas");
        FontFactory.vistaTransforms.put("SanSerif", "Arial");
        FontFactory.vistaTransforms.put("Serif", "Constantia");
        FontFactory.vistaTransforms.put("Unicode", "Dialog");
        FontFactory.otherTransforms.put("Arrows", "Dialog");
        FontFactory.otherTransforms.put("Unicode", "Dialog");
    }
}
