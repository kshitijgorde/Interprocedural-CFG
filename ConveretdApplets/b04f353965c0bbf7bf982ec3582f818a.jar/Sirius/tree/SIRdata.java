// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.util.StringTokenizer;

public class SIRdata
{
    private static int i;
    private static StringTokenizer st;
    
    public static String verifystr(final String s, final String s2) {
        String s3;
        if (s == null || s.equals("")) {
            s3 = s2;
        }
        else {
            s3 = s;
        }
        return s3;
    }
    
    public static Font verifyfnt(String s, final String s2, final String s3) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        SIRdata.st = new StringTokenizer(verifystr(s, s2), s3);
        Font font;
        try {
            String nextToken;
            if (SIRdata.st.hasMoreTokens()) {
                nextToken = SIRdata.st.nextToken();
            }
            else {
                nextToken = "Courier";
            }
            String upperCase;
            if (SIRdata.st.hasMoreTokens()) {
                upperCase = SIRdata.st.nextToken().toUpperCase();
            }
            else {
                upperCase = "N";
            }
            int int1;
            if (SIRdata.st.hasMoreTokens()) {
                int1 = Integer.parseInt(SIRdata.st.nextToken());
            }
            else {
                int1 = 12;
            }
            if (upperCase.equals("B")) {
                font = new Font(nextToken, 1, int1);
            }
            else if (upperCase.equals("I")) {
                font = new Font(nextToken, 2, int1);
            }
            else if (upperCase.equals("BI") || upperCase.equals("IB")) {
                font = new Font(nextToken, 3, int1);
            }
            else {
                font = new Font(nextToken, 0, int1);
            }
        }
        catch (Exception ex) {
            font = new Font("Courier", 0, 12);
        }
        return font;
    }
    
    public static Color verifyclr(String s, final String s2, final String s3) {
        if (s == null || s.equals("")) {
            s = s2;
        }
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        SIRdata.st = new StringTokenizer(verifystr(s, s2), s3);
        Color color;
        try {
            int abs;
            if (SIRdata.st.hasMoreTokens()) {
                abs = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs = 0;
            }
            int abs2;
            if (SIRdata.st.hasMoreTokens()) {
                abs2 = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs2 = 0;
            }
            int abs3;
            if (SIRdata.st.hasMoreTokens()) {
                abs3 = Math.abs(Integer.parseInt(SIRdata.st.nextToken()));
            }
            else {
                abs3 = 0;
            }
            color = new Color(abs, abs2, abs3);
        }
        catch (Exception ex) {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public static boolean verifybool(String substring, final String s) {
        boolean b = false;
        if (substring == null || substring.length() < 1) {
            substring = s;
        }
        substring = substring.toLowerCase().substring(0, 1);
        if (substring.equals("t")) {
            b = true;
        }
        return b;
    }
    
    public static int verifyint(String s) {
        int n;
        for (s += "  ", n = 0; n < s.length() & s.substring(n, n + 1).equals(" "); ++n) {}
        int n2;
        for (s = s.substring(n, s.length()), n2 = 0; n2 < s.length() & !s.substring(n2, n2 + 1).equals(" "); ++n2) {}
        s = s.substring(0, n2);
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = 0;
        }
        return int1;
    }
    
    public static Vector getsubmenu(final Vector vector, final String s) {
        final Enumeration<Treenode> elements = vector.elements();
        final Vector<Tree> vector2 = new Vector<Tree>();
        int n = 0;
        while (elements.hasMoreElements()) {
            final Treenode treenode = elements.nextElement();
            final String nodeid = treenode.getNodeid();
            if (treenode.getParentid().equals(s)) {
                final Vector getsubmenu = getsubmenu(vector, nodeid);
                final Treenode treenode2 = vector.elementAt(n);
                treenode2.setTreenode(getsubmenu);
                vector.setElementAt(treenode2, n);
                vector2.addElement(new Tree(n));
            }
            ++n;
        }
        return vector2;
    }
    
    public static Image getimage(final String s, final Vector vector) {
        final Enumeration<Treeimage> elements = vector.elements();
        Image image = null;
        for (boolean b = false; elements.hasMoreElements() && !b; b = true) {
            final Treeimage treeimage = elements.nextElement();
            if (s.equals(treeimage.getImagename())) {
                image = treeimage.getImage();
            }
        }
        return image;
    }
}
