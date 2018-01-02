// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParamImage;
import netcharts.util.NFUtil;
import netcharts.util.NFParamException;
import java.util.Hashtable;
import java.net.URL;
import java.util.Vector;
import netcharts.util.NFParam;
import java.awt.Image;
import java.awt.Color;

public class NFPatternFill
{
    public int pattern;
    public Color fg;
    public Color bg;
    public Image image;
    
    public NFPatternFill() {
        this.pattern = 0;
        this.fg = null;
        this.bg = null;
        this.image = null;
    }
    
    public static void definePatternFillParam(final NFParam nfParam, final String s) {
        nfParam.defineVector(s, nfParam.defineTuple(s + "Tuple", getPatternFillParamTuple(nfParam, s)));
    }
    
    public static Vector getPatternFillParamTuple(final NFParam nfParam, final String s) {
        return getPatternFillParamTuple(new Vector(), nfParam, s);
    }
    
    public static Vector getPatternFillParamTuple(final Vector vector, final NFParam nfParam, final String s) {
        vector.addElement(nfParam.defineSymbol(s + "Type", getPatternFillSymbols(), new Integer(0)));
        vector.addElement(nfParam.defineColor(s + "FGColor", null));
        vector.addElement(nfParam.defineColor(s + "BGColor", null));
        vector.addElement(nfParam.defineImage(s + "Image", null));
        return vector;
    }
    
    public static Hashtable getPatternFillSymbols() {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("FSLASH", new Integer(1));
        hashtable.put("BSLASH", new Integer(2));
        hashtable.put("DGRID", new Integer(3));
        hashtable.put("HORIZONTAL", new Integer(4));
        hashtable.put("VERTICAL", new Integer(5));
        hashtable.put("GRID", new Integer(6));
        hashtable.put("GRADIENTVERTICAL", new Integer(7));
        hashtable.put("GRADIENTHORIZONTAL", new Integer(8));
        hashtable.put("GRADIENTFDIAG", new Integer(9));
        hashtable.put("GRADIENTBDIAG", new Integer(10));
        hashtable.put("IMAGE", new Integer(11));
        return hashtable;
    }
    
    public static NFPatternFill[] loadPatternFillParam(final NFParam nfParam, final String s) throws NFParamException {
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final NFPatternFill[] array = new NFPatternFill[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = loadPatternFill(vector.elementAt(i));
        }
        return array;
    }
    
    public static NFPatternFill loadPatternFill(final Vector vector) {
        return loadPatternFill(vector, 0);
    }
    
    public static NFPatternFill loadPatternFill(final Vector vector, final int n) {
        if (vector == null) {
            return null;
        }
        final NFPatternFill nfPatternFill = new NFPatternFill();
        nfPatternFill.pattern = NFUtil.getNumber(vector, n, 0);
        nfPatternFill.fg = NFUtil.getColor(vector, n + 1, null);
        nfPatternFill.bg = NFUtil.getColor(vector, n + 2, null);
        final NFParamImage nfParamImage = (NFParamImage)NFUtil.getObject(vector, n + 3, null);
        nfPatternFill.image = ((nfParamImage == null) ? null : nfParamImage.im);
        return nfPatternFill;
    }
}
