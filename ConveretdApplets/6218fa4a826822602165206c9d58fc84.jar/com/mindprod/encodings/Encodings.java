// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.encodings;

import com.mindprod.common13.HybridJ;
import java.util.Iterator;
import java.util.SortedMap;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.security.AccessControlException;
import com.mindprod.fastcat.FastCat;
import java.nio.charset.Charset;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JApplet;

public final class Encodings extends JApplet
{
    private static final int APPLET_HEIGHT = 720;
    private static final int APPLET_WIDTH = 180;
    private static final int MAX_LINE_LENGTH;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1996-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2007-05-16";
    private static final String TITLE_STRING = "Encodings";
    private static final String VERSION_STRING = "1.6";
    
    public void destroy() {
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final SortedMap<String, Charset> sm = Charset.availableCharsets();
        final int size = sm.size();
        final FastCat sb = new FastCat(size * 2 + 8);
        sb.append("Encodings 1.6\nSUPPORTED\nENCODINGS");
        sb.append("\n------\n");
        try {
            final String defaultEncoding = System.getProperty("file.encoding");
            final String canonicalName = Charset.forName(defaultEncoding).name();
            sb.append("default:\n");
            sb.append(defaultEncoding);
            sb.append("\nofficially:\n");
            sb.append(canonicalName);
        }
        catch (AccessControlException e) {
            sb.append("No permission\ngranted\nto view\nthe default\nencoding");
        }
        sb.append("\n------\n");
        for (final String key : sm.keySet()) {
            sb.append(key);
            sb.append('\n');
        }
        final JTextArea encodingList = new JTextArea(sb.toString(), size, Encodings.MAX_LINE_LENGTH);
        encodingList.setMargin(new Insets(2, 2, 2, 0));
        final JScrollPane sp = new JScrollPane(encodingList, 20, 31);
        this.add(sp);
        this.validate();
        this.setVisible(true);
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Encodings(), "Enc", 180, 720);
    }
    
    static {
        MAX_LINE_LENGTH = "x-windows-iso2022jp".length();
    }
}
