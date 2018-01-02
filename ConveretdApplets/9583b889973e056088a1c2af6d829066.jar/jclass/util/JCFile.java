// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.awt.Component;
import java.net.URL;
import java.applet.Applet;

public class JCFile
{
    public static URL createURL(final Applet applet, String string) {
        if (string.indexOf(":") == -1) {
            try {
                return new URL(applet.getDocumentBase(), string);
            }
            catch (Exception ex) {
                string = "file:" + System.getProperty("user.dir") + "/" + string;
            }
        }
        try {
            return new URL(string);
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public static JCVector read(final Component component, final String s, final char c, final boolean b) {
        final JCVector jcVector = new JCVector();
        final Vector lines = readLines((component instanceof Applet) ? ((Applet)component) : null, s);
        int n = 0;
        for (int i = 0; i < lines.size(); ++i) {
            if (lines.elementAt(i) != null) {
                jcVector.setElementAt(n++, JCUtilConverter.toVector(component, lines.elementAt(i), c, b));
            }
        }
        return jcVector;
    }
    
    public static JCVector readCSV(final Component component, final String s) {
        final JCVector jcVector = new JCVector();
        final Vector lines = readLines((component instanceof Applet) ? ((Applet)component) : null, s);
        int n = 0;
        for (int i = 0; i < lines.size(); ++i) {
            if (lines.elementAt(i) != null) {
                jcVector.setElementAt(n++, ConvertUtil.toVectorFromCSV(component, lines.elementAt(i)));
            }
        }
        return jcVector;
    }
    
    public static Vector readLines(final Applet applet, final String s) {
        return readLines(applet, s, 0, Integer.MAX_VALUE);
    }
    
    public static Vector readLines(final Applet applet, final String s, final int n, final int n2) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(createURL(applet, s).openStream()));
            final Vector<String> vector = new Vector<String>();
            String line;
            for (int n3 = 0; (line = dataInputStream.readLine()) != null && n3 <= n2; ++n3) {
                if (n3 >= n) {
                    vector.addElement(line);
                }
            }
            return vector;
        }
        catch (Throwable t) {
            System.out.println("Error opening file '" + s + "'");
            return null;
        }
    }
    
    public static String read(final Applet applet, final String s) {
        String string = "";
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(createURL(applet, s).openStream()));
            for (String s2 = dataInputStream.readLine(); s2 != null; s2 = dataInputStream.readLine()) {
                string = String.valueOf(string) + s2;
            }
            return string;
        }
        catch (Exception ex) {
            System.out.println("Error opening file '" + s + "'");
            return null;
        }
    }
}
