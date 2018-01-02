// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.e;

import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Vector;
import a.a.a.a.b.j;
import java.util.ResourceBundle;
import a.a.a.a.b.c;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.StringTokenizer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Hashtable;

public class a
{
    private Hashtable a;
    private Hashtable do;
    private Hashtable if;
    
    public a() {
        this.a = new Hashtable();
    }
    
    public void a(final String s, final Object o) {
        if (o != null) {
            this.a.put(s, o);
        }
    }
    
    public void if() {
        if (this.a != null) {
            final Enumeration<String> keys = this.a.keys();
            while (keys.hasMoreElements()) {
                this.for(keys.nextElement());
            }
            this.a.clear();
            this.a = null;
        }
        if (this.do != null) {
            final Enumeration<String> keys2 = this.do.keys();
            while (keys2.hasMoreElements()) {
                this.for("images/button/" + keys2.nextElement());
            }
            this.do.clear();
            this.do = null;
        }
        if (this.if != null) {
            final Enumeration<String> keys3 = this.if.keys();
            while (keys3.hasMoreElements()) {
                this.for("cursor/" + keys3.nextElement());
            }
            this.if.clear();
            this.if = null;
        }
    }
    
    public void a(final Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof Image) {
            ((Image)o).flush();
        }
        else if (o instanceof AudioClip) {
            ((AudioClip)o).stop();
        }
    }
    
    protected Object char(final String s) {
        final Applet applet = (Applet)this.try("applet");
        if (applet == null) {
            return null;
        }
        String s2 = s;
        if (s.endsWith("initialView")) {
            s2 = "initialView";
        }
        else if (s.endsWith("autospin")) {
            s2 = "autospin";
        }
        if (s.equals("documentbase")) {
            return applet.getDocumentBase();
        }
        final String parameter = applet.getParameter(s2);
        if (parameter == null) {
            return null;
        }
        final String trim = parameter.trim();
        Object value;
        if (s.equals("toolbar")) {
            value = new Boolean(trim.equalsIgnoreCase("open"));
        }
        else if (s.equals("rotationcontrol")) {
            value = new Boolean(trim.equalsIgnoreCase("on"));
        }
        else if (s.equals("logopos")) {
            final int[] array = new int[2];
            final StringTokenizer stringTokenizer = new StringTokenizer(trim, ",");
            int i;
            try {
                for (i = 0; i < 2; ++i) {
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    array[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                }
            }
            catch (NumberFormatException ex) {
                return null;
            }
            if (i < 2) {
                return null;
            }
            value = new Point(array[0], array[1]);
        }
        else if (s.equals("toolbarbounds")) {
            final int[] array2 = new int[4];
            final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, ",");
            int j;
            try {
                for (j = 0; j < 4; ++j) {
                    if (!stringTokenizer2.hasMoreTokens()) {
                        break;
                    }
                    array2[j] = Integer.parseInt(stringTokenizer2.nextToken().trim());
                }
            }
            catch (NumberFormatException ex2) {
                return null;
            }
            if (j < 4) {
                return null;
            }
            value = new Rectangle(array2[0], array2[1], array2[2], array2[3]);
        }
        else {
            if (!s.equals("leftMargin") && !s.equals("topMargin") && !s.equals("displayWidth")) {
                if (!s.equals("displayHeight")) {
                    if (s.equals("showHotspots") || s.equals("foregroundFrame")) {
                        value = Boolean.valueOf(trim.trim().toLowerCase());
                        return value;
                    }
                    if (s.equals("backgroundColor")) {
                        final int[] array3 = { 255, 255, 255 };
                        final StringTokenizer stringTokenizer3 = new StringTokenizer(trim, ",");
                        int k;
                        try {
                            for (k = 0; k < 3; ++k) {
                                if (!stringTokenizer3.hasMoreTokens()) {
                                    break;
                                }
                                array3[k] = Integer.parseInt(stringTokenizer3.nextToken().trim());
                            }
                        }
                        catch (NumberFormatException ex3) {
                            return null;
                        }
                        if (k < 3) {
                            return null;
                        }
                        value = new Color(array3[0], array3[1], array3[2]);
                        return value;
                    }
                    else if (s.equals("hotspotColor")) {
                        final int[] array4 = { 255, 255, 255 };
                        final StringTokenizer stringTokenizer4 = new StringTokenizer(trim, ",");
                        int l;
                        try {
                            for (l = 0; l < 3; ++l) {
                                if (!stringTokenizer4.hasMoreTokens()) {
                                    break;
                                }
                                array4[l] = Integer.parseInt(stringTokenizer4.nextToken().trim());
                            }
                        }
                        catch (NumberFormatException ex4) {
                            return null;
                        }
                        if (l < 3) {
                            return null;
                        }
                        value = new Color(array4[0], array4[1], array4[2]);
                        return value;
                    }
                    else {
                        if (s.equals("enableZoomPastMax")) {
                            boolean b = false;
                            if (trim.trim().equalsIgnoreCase("on")) {
                                b = true;
                            }
                            value = new Boolean(b);
                            return value;
                        }
                        if (s.equals("d2.initialView")) {
                            final float[] array5 = { 0.0f, 0.0f, 1.0f, 1.0f };
                            final StringTokenizer stringTokenizer5 = new StringTokenizer(trim, ",");
                            try {
                                for (int n = 0; n < 4; ++n) {
                                    if (!stringTokenizer5.hasMoreTokens()) {
                                        break;
                                    }
                                    array5[n] = new Float(stringTokenizer5.nextToken().trim());
                                }
                            }
                            catch (NumberFormatException ex5) {
                                return null;
                            }
                            value = array5;
                            return value;
                        }
                        if (s.equals("pano.initialView")) {
                            final float[] array6 = new float[3];
                            final StringTokenizer stringTokenizer6 = new StringTokenizer(trim, ",");
                            try {
                                for (int n2 = 0; n2 < 3; ++n2) {
                                    if (!stringTokenizer6.hasMoreTokens()) {
                                        break;
                                    }
                                    array6[n2] = Float.valueOf(stringTokenizer6.nextToken().trim()) * 3.1415927f / 180.0f;
                                }
                            }
                            catch (NumberFormatException ex6) {
                                return null;
                            }
                            value = array6;
                            return value;
                        }
                        if (s.equals("url") || s.equals("file") || s.equals("verifi") || s.equals("backgroundImage")) {
                            value = trim;
                            return value;
                        }
                        if (s.endsWith("autospin")) {
                            float floatValue;
                            try {
                                floatValue = Float.valueOf(trim);
                            }
                            catch (NumberFormatException ex7) {
                                return null;
                            }
                            value = new Float(floatValue);
                            return value;
                        }
                        if (s.equals("minZoomAngle")) {
                            float n3;
                            try {
                                n3 = Float.valueOf(trim) * 3.1415927f / 180.0f;
                            }
                            catch (NumberFormatException ex8) {
                                return null;
                            }
                            value = new Float(n3);
                            return value;
                        }
                        if (!s.equals("antialias")) {
                            value = trim;
                            return value;
                        }
                        if (trim.equals("false")) {
                            value = new Boolean(false);
                            return value;
                        }
                        return null;
                    }
                }
            }
            int int1;
            try {
                int1 = Integer.parseInt(trim);
            }
            catch (NumberFormatException ex9) {
                return null;
            }
            value = new Integer(int1);
        }
        return value;
    }
    
    public Image new(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + s);
            if (resourceAsStream == null) {
                System.err.println("Image not found.");
                return null;
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = resourceAsStream.read()) >= 0) {
                byteArrayOutputStream.write(read);
            }
            return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Object try(final String s) {
        if (s.equals("statemachine")) {
            return this.a();
        }
        if (s.startsWith("command/state/")) {
            return this.case(s);
        }
        if (s.startsWith("command/action/")) {
            return this.int(s);
        }
        if (s.startsWith("resource/")) {
            if (s.startsWith("resource/image.")) {
                return this.new(this.for().getString(s.substring(9)));
            }
            return this.for().getString(s.substring(9));
        }
        else {
            if (s.startsWith("images/toolbar")) {
                return this.do("resource/image.toolbar");
            }
            if (s.startsWith("images/button/")) {
                return this.do().get(s.substring(14));
            }
            if (s.startsWith("cursor/")) {
                return this.int().get(s.substring(7));
            }
            if (s.equals("resource.bundle")) {
                return this.for();
            }
            if (s.startsWith("param/")) {
                return this.else(s.substring(6));
            }
            return this.a.get(s);
        }
    }
    
    protected c int(final String s) {
        c c = this.a.get(s);
        if (c == null) {
            c = new c(this, s.substring(15));
        }
        return c;
    }
    
    public boolean if(final String s) {
        return new Boolean(this.a(s));
    }
    
    protected Hashtable do() {
        if (this.do == null) {
            this.do = new Hashtable();
            final Image image = (Image)this.try("images/toolbar");
            if (image != null) {
                this.a(image);
                this.for("images/toolbar");
            }
        }
        return this.do;
    }
    
    protected Hashtable int() {
        if (this.if == null) {
            this.if = new Hashtable();
            this.new();
        }
        return this.if;
    }
    
    public Image do(final String s) {
        return (Image)this.try(s);
    }
    
    public int byte(final String s) {
        return Integer.parseInt(this.a(s));
    }
    
    protected Object else(final String s) {
        Object o = this.a.get("param/" + s);
        if (o == null) {
            o = this.char(s);
            if (o == null) {
                if (s.equals("toolbar") || s.equals("foregroundFrame") || s.equals("showHotspots") || s.equals("enableZoomPastMax")) {
                    o = new Boolean(false);
                }
                else if (s.equals("rotationcontrol")) {
                    o = new Boolean(true);
                }
                else if (s.equals("leftMargin")) {
                    o = new Integer(0);
                }
                else if (s.equals("topMargin")) {
                    o = new Integer(0);
                }
                else if (s.equals("backgroundColor")) {
                    o = new Color(255, 255, 255);
                }
                else if (s.equals("hotspotColor")) {
                    o = Color.red;
                }
                else if (s.equals("d2.initialView")) {
                    o = new float[] { 0.0f, 0.0f, 1.0f, 1.0f };
                }
                else if (!s.equals("pano.initialView")) {
                    if (s.equals("pano.autospin")) {
                        o = new Float(10.0f);
                    }
                    else if (s.equals("d3.autospin")) {
                        o = new Float(0.0f);
                    }
                    else if (s.equals("minZoomAngle")) {
                        o = new Float(0.08726647f);
                    }
                    else if (s.equals("antialias")) {
                        o = new Boolean(true);
                    }
                }
            }
            this.a("param/" + s, o);
        }
        return o;
    }
    
    protected ResourceBundle for() {
        ResourceBundle bundle = this.a.get("resource.bundle");
        if (bundle == null) {
            final Applet applet = (Applet)this.try("applet");
            if (applet == null) {
                return null;
            }
            bundle = ResourceBundle.getBundle("resource", applet.getLocale());
            this.a("resource.bundle", bundle);
        }
        return bundle;
    }
    
    protected j case(final String s) {
        j j = this.a.get(s);
        if (j == null) {
            j = new j(this, s.substring(14));
        }
        return j;
    }
    
    public a.a.a.a.c.a.a a() {
        a.a.a.a.c.a.a a = this.a.get("statemachine");
        if (a == null) {
            a = new a.a.a.a.c.a.a();
            this.a("statemachine", a);
        }
        return a;
    }
    
    public String a(final String s) {
        final String s2 = (String)this.try(s);
        if (s2 != null) {
            return s2.trim();
        }
        return null;
    }
    
    protected void a(final Image image) {
        final ResourceBundle resourceBundle = (ResourceBundle)this.try("resource.bundle");
        final StringTokenizer stringTokenizer = new StringTokenizer(resourceBundle.getString("button.images"), ",");
        final Vector vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken().trim());
        }
        final String[] array = new String[vector.size()];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (array[i].startsWith("toolbar")) {
                ++n;
            }
        }
        final int int1 = Integer.parseInt(resourceBundle.getString("button.toolbar.width"));
        final int int2 = Integer.parseInt(resourceBundle.getString("button.width"));
        final int int3 = Integer.parseInt(resourceBundle.getString("button.height"));
        final int n2 = int1 * n + (array.length - n) * int2;
        final int[] array2 = new int[n2 * int3];
        try {
            new PixelGrabber(image.getSource(), 0, 0, n2, int3, array2, 0, n2).grabPixels();
        }
        catch (Exception ex) {
            return;
        }
        int n3 = 0;
        for (int j = 0; j < array.length; ++j) {
            final int n4 = array[j].startsWith("toolbar") ? int1 : int2;
            final int[] array3 = new int[n4 * int3];
            for (int k = 0; k < int3; ++k) {
                for (int l = 0; l < n4; ++l) {
                    array3[k * n4 + l] = array2[k * n2 + n3 + l];
                }
            }
            this.do.put(array[j], Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n4, int3, array3, 0, n4)));
            n3 += n4;
        }
    }
    
    protected void new() {
        final StringTokenizer stringTokenizer = new StringTokenizer(((ResourceBundle)this.try("resource.bundle")).getString("cursors"), ":");
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",");
            final String trim = stringTokenizer2.nextToken().trim();
            final String trim2 = stringTokenizer2.nextToken().trim();
            int n = 0;
            if (trim2.equals("crosshair")) {
                n = 1;
            }
            else if (trim2.equals("hand")) {
                n = 12;
            }
            else if (trim2.equals("move")) {
                n = 13;
            }
            else if (trim2.equals("wait")) {
                n = 3;
            }
            this.if.put(trim, new Cursor(n));
        }
        this.if.put("default", new Cursor(0));
    }
    
    public void for(final String s) {
        this.a(this.try(s));
        if (s.startsWith("images/button/")) {
            this.do().remove(s.substring(14));
        }
        else if (s.startsWith("cursor/")) {
            this.int().remove(s.substring(7));
        }
        else {
            this.a.remove(s);
        }
    }
}
