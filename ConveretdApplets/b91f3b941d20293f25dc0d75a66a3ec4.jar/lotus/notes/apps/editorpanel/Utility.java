// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Color;
import java.util.Vector;
import netscape.security.PrivilegeManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Image;

public class Utility
{
    private static final boolean DEBUG = false;
    private static Image brokenImg;
    private static int[] pix;
    public static boolean bNeedsPriv;
    
    public static Image createBKImage(final Component component) {
        if (Utility.brokenImg == null) {
            Utility.pix = new int[729];
            final int n = 0;
            final int n2 = -10263709;
            final int n3 = 16777215;
            final int n4 = n + addBits(n2, 9, n);
            final int n5 = n4 + addBits(n3, 3, n4);
            final int n6 = n5 + addBits(n2, 2, n5);
            final int n7 = n6 + addBits(n3, 13, n6);
            final int n8 = n7 + addBits(n2, 2, n7);
            final int n9 = n8 + addBits(n3, 2, n8);
            final int n10 = n9 + addBits(n2, 2, n9);
            final int n11 = n10 + addBits(n3, 2, n10);
            final int n12 = n11 + addBits(n2, 1, n11);
            final int n13 = n12 + addBits(n3, 3, n12);
            final int n14 = n13 + addBits(n2, 2, n13);
            final int n15 = n14 + addBits(n3, 13, n14);
            final int n16 = n15 + addBits(n2, 8, n15);
            final int n17 = n16 + addBits(n3, 3, n16);
            final int n18 = n17 + addBits(n2, 3, n17);
            final int n19 = n18 + addBits(n3, 13, n18);
            final int n20 = n19 + addBits(-3276545, 4, n19);
            final int n21 = n20 + addBits(n2, 1, n20);
            final int n22 = n21 + addBits(-12800, 1, n21);
            final int n23 = n22 + addBits(n3, 5, n22);
            final int n24 = n23 + addBits(-65536, 3, n23);
            final int n25 = n24 + addBits(n3, 13, n24);
            final int n26 = n25 + addBits(-3276545, 4, n25);
            final int n27 = n26 + addBits(n2, 1, n26);
            final int n28 = n27 + addBits(n3, 6, n27);
            final int n29 = n28 + addBits(-65536, 3, n28);
            final int n30 = n29 + addBits(n3, 13, n29);
            final int n31 = n30 + addBits(-3276545, 4, n30);
            final int n32 = n31 + addBits(n2, 1, n31);
            final int n33 = n32 + addBits(n3, 5, n32);
            final int n34 = n33 + addBits(-65536, 4, n33);
            final int n35 = n34 + addBits(n3, 13, n34);
            final int n36 = n35 + addBits(-3276545, 4, n35);
            final int n37 = n36 + addBits(n3, 4, n36);
            final int n38 = n37 + addBits(-12800, 1, n37);
            final int n39 = n38 + addBits(n2, 1, n38);
            final int n40 = n39 + addBits(-65536, 4, n39);
            final int n41 = n40 + addBits(n3, 13, n40);
            final int n42 = n41 + addBits(n2, 3, n41);
            final int n43 = n42 + addBits(n3, 5, n42);
            final int n44 = n43 + addBits(n2, 6, n43);
            final int n45 = n44 + addBits(n3, 13, n44);
            final int n46 = n45 + addBits(n2, 2, n45);
            final int n47 = n46 + addBits(n3, 6, n46);
            final int n48 = n47 + addBits(n2, 2, n47);
            final int n49 = n48 + addBits(n3, 2, n48);
            final int n50 = n49 + addBits(n2, 2, n49);
            final int n51 = n50 + addBits(n3, 13, n50);
            final int n52 = n51 + addBits(n2, 2, n51);
            final int n53 = n52 + addBits(n3, 4, n52);
            final int n54 = n53 + addBits(n2, 8, n53);
            final int n55 = n54 + addBits(n3, 222, n54);
            Utility.brokenImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(27, 27, Utility.pix, 0, 27));
            if (Utility.brokenImg != null) {
                final MediaTracker mediaTracker = new MediaTracker(component);
                mediaTracker.addImage(Utility.brokenImg, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (Exception ex) {
                    System.out.println("Problem loading image");
                }
            }
            else {
                System.out.println("error loading broken image");
            }
        }
        return Utility.brokenImg;
    }
    
    private static int addBits(final int n, final int n2, final int n3) {
        for (int i = 0; i < n2; ++i) {
            Utility.pix[i + n3] = n;
        }
        return n2;
    }
    
    public static URL makeURL(final URL url, final String s) {
        URL url2 = null;
        try {
            final String file = url.getFile();
            final URL url3 = new URL(file.substring(1, file.length()));
            final int port = url.getPort();
            final int port2 = url3.getPort();
            final String string = url.getProtocol() + "://";
            String s2;
            if (file.indexOf("domjava") != -1) {
                s2 = string + ((port != -1) ? (url.getHost() + ":" + port + url.getFile()) : (url.getHost() + url.getFile()));
            }
            else {
                s2 = string + ((port != -1) ? (url.getHost() + ":" + port) : url.getHost()) + "/" + ((port2 != -1) ? (url3.getProtocol() + "://" + url3.getHost() + ":" + port2) : (url3.getProtocol() + "://" + url3.getHost()));
            }
            url2 = new URL(s2 + s);
        }
        catch (Exception ex) {
            try {
                if (url != null) {
                    url2 = new URL(url, s);
                }
                else {
                    url2 = new URL(s);
                }
            }
            catch (MalformedURLException ex2) {
                if (url != null) {
                    System.out.println("Malformed URL when appending " + s);
                    return url2;
                }
                System.out.println("Malformed URL when creating from " + s);
            }
        }
        return url2;
    }
    
    public static Image loadImage(final String s, final Component component, final boolean b) {
        return loadImage(null, s, component, b);
    }
    
    public static Image loadImage(final URL url, final String s, final Component component, final boolean b) {
        if (Utility.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        Image image;
        try {
            image = waitForImage(makeURL(url, s), component, b);
        }
        catch (Exception ex2) {
            image = null;
        }
        if (image == null) {
            if (Utility.brokenImg == null) {
                createBKImage(component);
            }
            image = Utility.brokenImg;
        }
        return image;
    }
    
    public static Vector loadImages(final URL url, final String s, final String[] array, final Component component, final boolean b) {
        if (Utility.bNeedsPriv) {
            try {
                PrivilegeManager.enablePrivilege("UniversalBrowserRead");
                PrivilegeManager.enablePrivilege("UniversalConnect");
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        final Vector<Image> vector = new Vector<Image>(array.length);
        for (int i = 0; i < array.length; ++i) {
            try {
                vector.addElement(waitForImage(makeURL(url, s + array[i]), component, b));
            }
            catch (Exception ex2) {
                if (Utility.brokenImg == null) {
                    createBKImage(component);
                }
                vector.addElement(Utility.brokenImg);
            }
        }
        return vector;
    }
    
    public static Image waitForImage(final URL url, final Component component, final boolean b) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        mediaTracker.addImage(image, 0);
        try {
            if (b) {
                mediaTracker.waitForID(0);
            }
            else {
                mediaTracker.waitForID(0, 10L);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Problem: " + url);
        }
        if (mediaTracker.isErrorID(0)) {
            if (Utility.brokenImg == null) {
                createBKImage(component);
            }
            image = Utility.brokenImg;
        }
        return image;
    }
    
    public static Color decodeColor(final String s) {
        Color color = null;
        if (s != null) {
            try {
                int n;
                if (s.charAt(0) == '#') {
                    n = Integer.parseInt(s.substring(1, s.length()), 16);
                }
                else {
                    n = Integer.parseInt(s, 16);
                }
                color = new Color(n);
            }
            catch (Exception ex) {
                System.out.println("Invalid Color input.  Must be of form #RRGGBB");
            }
        }
        return color;
    }
    
    public static String stringToUnicode(final String s) {
        String s2;
        try {
            int n;
            if (s.startsWith("0x")) {
                n = Integer.parseInt(s.substring(2), 16);
            }
            else {
                n = Integer.parseInt(s);
            }
            if (n < 32) {
                return null;
            }
            s2 = new String(new char[] { (char)n });
        }
        catch (Exception ex) {
            return null;
        }
        return s2;
    }
    
    static {
        Utility.brokenImg = null;
        Utility.bNeedsPriv = false;
    }
}
