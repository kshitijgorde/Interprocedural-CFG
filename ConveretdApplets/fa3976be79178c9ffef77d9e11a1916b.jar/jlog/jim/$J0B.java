// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.io.InputStream;
import java.awt.Dimension;
import jlog.awt.image.$A6;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Color;
import java.awt.Polygon;
import jlog.awt.$V_;
import jlog.$Y_B.$Z_B;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import jlog.io.$P4;
import java.io.IOException;
import jlog.$BI.$M4;
import jlog.util.$XG.$GI;
import java.util.Dictionary;
import java.net.URL;
import jlog.util.$XG.$DJ;
import java.util.Hashtable;
import jlog.$H4;

class $J0B implements $H4, $U9B
{
    private static final boolean debug = false;
    private Hashtable $XWC;
    public static final String $K0B = "create";
    
    void $AXC(final $BBC $bbc) {
        this.$XWC.remove($bbc.$VPC());
    }
    
    $BBC $DXC(final $DJ $dj, URL url, final $H0B $h0B, final Dictionary dictionary) throws IOException, $U5B {
        final String $ek = $dj.$EK("NAME");
        if ($ek == null) {
            throw new $U5B("missing name within map-TAG");
        }
        final String $ek2 = $dj.$EK("DESCRIPTION");
        final String $ek3 = $dj.$EK("TARGET");
        final String $ek4 = $dj.$EK("RUBRIK");
        final String $ek5 = $dj.$EK("MAP_VARS");
        url = new URL(url, "#" + $ek);
        final $BBC $bbc = new $BBC(url, $h0B, $h0B.$XKB);
        final String $ek6 = $dj.$EK("MAP_SCALE_DEFAULT");
        if ($ek6 != null) {
            $bbc.$NPC = Float.valueOf($ek6);
        }
        final String $ek7 = $dj.$EK("MAP_XOR_COLOR");
        if ($ek7 != null) {
            $bbc.$XOC = new $GI($ek7);
        }
        final String $ek8 = $dj.$EK("ZOOM_IMAGE_SRC");
        if ($ek8 != null) {
            try {
                $bbc.$PPC.$JT(new URL($bbc.$VPC(), $ek8));
            }
            catch (Exception ex) {
                $M4.print(ex);
            }
        }
        final String $ek9 = $dj.$EK("STARTCARD_ATTRIBUTE");
        if ($ek9 != null) {
            $bbc.$YMC = $ek9;
        }
        if ($ek5 != null) {
            $bbc.vars.$Q($ek5);
        }
        if ($ek4 != null) {
            this.$KXC($bbc, $ek4);
        }
        if ($ek3 != null) {
            $bbc.$MPC = $ek3;
        }
        if ($ek2 != null) {
            $bbc.setDescription($ek2);
        }
        $dj.getNextTag("/MAP");
        this.$LXC(new $DJ($dj.$DK()), $bbc);
        if ($bbc != null) {
            dictionary.put(url, $bbc);
            $h0B.$EHC(String.valueOf($h0B.$TEC.getString("Please wait a moment")) + "...");
            return $bbc;
        }
        throw new $U5B("no valid map");
    }
    
    private void $EXC(final $DJ $dj, final URL url, final Dictionary dictionary) throws MalformedURLException {
        final Object[] array = new Object[4];
        final String $ek = $dj.$EK("SRC");
        final String $ek2 = $dj.$EK("USEMAP");
        if ($ek == null || $ek2 == null) {
            return;
        }
        array[0] = $P4.$SXC(url, $ek);
        array[1] = $P4.$SXC(url, $ek2);
        final int $ek3 = $dj.$EK("WIDTH", -1);
        final int $ek4 = $dj.$EK("HEIGHT", -1);
        array[2] = new Integer($ek3);
        array[3] = new Integer($ek4);
        if ($ek3 < 0 || $ek4 < 0) {
            $M4.print("Warning: missing dimensions WIDTH= HEIGHT= for image " + $ek);
        }
        dictionary.put(array[1], array);
    }
    
    void $KXC(final $BBC $bbc, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|", false);
        while (stringTokenizer.hasMoreElements()) {
            final String s2 = (String)stringTokenizer.nextElement();
            try {
                final $F8B $qxc = this.$QXC(s2, $bbc);
                if ($qxc == null) {
                    continue;
                }
                $bbc.$JIC($qxc);
            }
            catch ($U5B $u5B) {
                $M4.print("MapStore: read attribute failed. line=" + s2.substring(0, 20), $u5B);
            }
        }
    }
    
    void $LXC(final $DJ $dj, final $BBC $bbc) throws IOException {
        long currentTimeMillis = 0L;
        final String string = $bbc.$VJC().$TEC.getString("create");
        while ($dj.getNextTag("AREA") != null) {
            final String $ek = $dj.$EK("SHAPE");
            if ($ek == null) {
                $M4.print("<MapStore: missing attribute SHAPE for area " + $dj + ">");
            }
            else {
                final String $ek2 = $dj.$EK("COORDS");
                if ($ek2 == null) {
                    $M4.print("<MapStore: missing attribute COORDS for area " + $dj + ">");
                }
                else {
                    final String $ek3 = $dj.$EK("HREF", "");
                    final String $ek4 = $dj.$EK("TARGET", "");
                    final String $ek5 = $dj.$EK("ALT", "");
                    final String $ek6 = $dj.$EK("ATTR", "");
                    final String $ek7 = $dj.$EK("DESCRIPTION", "");
                    final StringTokenizer stringTokenizer = new StringTokenizer($ek2, ", ", false);
                    final int[] array = new int[stringTokenizer.countTokens()];
                    int n = 0;
                    try {
                        while (stringTokenizer.hasMoreElements()) {
                            array[n++] = Integer.parseInt(stringTokenizer.nextToken());
                        }
                    }
                    catch (NumberFormatException ex) {
                        $M4.print("<MapStore: illegal coordvalues " + $ek2 + ">", ex);
                        continue;
                    }
                    $EIC $eic = null;
                    final String s = "";
                    try {
                        if ($ek.equals("RECT")) {
                            $eic = new $EIC(new $Z_B(array[0] / 10.0f, array[1] / 10.0f, (array[2] - array[0]) / 10.0f, (array[3] - array[1]) / 10.0f), $ek5, s, false);
                        }
                        else if ($ek.equals("CIRCLE")) {
                            $eic = new $EIC(new $Z_B((array[0] - array[2]) / 10.0f, (array[1] - array[2]) / 10.0f, 2 * array[2] / 10.0f, 2 * array[2] / 10.0f), $ek5, s, true);
                        }
                        else if ($ek.equals("POLYGON")) {
                            $eic = new $EIC($ek5, s, new $V_(array), 0.1f);
                        }
                        $eic.$XSC($ek3);
                        $eic.$IT($ek4);
                        $eic.setDescription($ek7);
                        $bbc.$DIC($eic, $ek6);
                    }
                    catch (RuntimeException ex2) {
                        $M4.print("<MapStore: error creating " + $dj + ">", ex2);
                        continue;
                    }
                    if (System.currentTimeMillis() - currentTimeMillis <= 333L) {
                        continue;
                    }
                    $bbc.$VJC().$EHC(String.valueOf(string) + " " + $eic.getName());
                    currentTimeMillis = System.currentTimeMillis();
                }
            }
        }
    }
    
    $F8B $QXC(final String s, final $BBC $bbc) throws $U5B {
        $F8B $f8B = null;
        Color color = null;
        Color $xoc = null;
        URL $sxc = null;
        Image image = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        final String nextToken = this.nextToken(stringTokenizer, "name", s);
        final String nextToken2 = this.nextToken(stringTokenizer, "description", s);
        final String nextToken3 = this.nextToken(stringTokenizer, "id", s);
        while (stringTokenizer.hasMoreElements()) {
            String upperCase = (String)stringTokenizer.nextElement();
            String trim = "";
            final int index = upperCase.indexOf(61);
            if (index > 0) {
                trim = upperCase.substring(index + 1).trim();
                upperCase = upperCase.substring(0, index).trim().toUpperCase();
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(trim.toUpperCase(), ", ", false);
            if (upperCase.startsWith("$")) {
                $bbc.vars.put(upperCase.substring(1), trim);
            }
            else if (upperCase.equals("MARKER")) {
                n |= 0x1000;
                while (stringTokenizer2.hasMoreElements()) {
                    final String s2 = (String)stringTokenizer2.nextElement();
                    if (s2.equals("SELECTABLE")) {
                        n |= 0x1;
                    }
                    else if (s2.equals("SELECTED")) {
                        n |= 0x2;
                    }
                    else if (s2.equals("DEFAULT")) {
                        n |= 0x20;
                    }
                    else if (s2.equals("HIDENAME")) {
                        n |= 0x100;
                    }
                    else if (s2.equals("HIDELIST")) {
                        n |= 0x80;
                    }
                    else {
                        if (!s2.equals("HIDEMARKER")) {
                            continue;
                        }
                        n |= 0x200;
                    }
                }
            }
            else if (upperCase.equals("INDEX")) {
                n3 |= 0x1000;
                while (stringTokenizer2.hasMoreElements()) {
                    final String s3 = (String)stringTokenizer2.nextElement();
                    if (s3.equals("SELECTABLE")) {
                        n3 |= 0x1;
                    }
                    else if (s3.equals("INVERSE")) {
                        n3 |= 0x4;
                    }
                    else if (s3.equals("BLINK")) {
                        n3 |= 0x10;
                    }
                    else {
                        if (!s3.equals("LIST")) {
                            continue;
                        }
                        n3 |= 0x40;
                    }
                }
            }
            else if (upperCase.equals("MOUSE")) {
                n2 |= 0x1000;
                while (stringTokenizer2.hasMoreElements()) {
                    final String s4 = (String)stringTokenizer2.nextElement();
                    if (s4.equals("POPUP")) {
                        n2 |= 0x8;
                    }
                    else if (s4.equals("INVERSE")) {
                        n2 |= 0x4;
                    }
                    else {
                        if (!s4.equals("SELECTABLE")) {
                            continue;
                        }
                        n2 |= 0x1;
                    }
                }
            }
            else if (upperCase.equals("ICON")) {
                try {
                    $sxc = $P4.$SXC($bbc.$VPC(), trim);
                    image = $bbc.$VJC().createImage($sxc);
                }
                catch (Exception ex) {
                    $M4.print(ex);
                }
            }
            else if (upperCase.equals("MARKER_COLOR")) {
                color = new $GI(trim);
            }
            else {
                if (!upperCase.equals("XOR_COLOR")) {
                    continue;
                }
                $xoc = new $GI(trim);
            }
        }
        if (!nextToken.equals("-")) {
            $f8B = new $F8B(nextToken, nextToken3.equals("-") ? "" : nextToken3, nextToken2.equals("-") ? "" : nextToken2, color, $sxc, image);
            $f8B.$XOC = $xoc;
            if (n != 0) {
                $f8B.$JPC(n);
                if ((n & 0x2) != 0x0) {
                    $f8B.$TJC(true);
                }
            }
            if (n2 != 0) {
                $f8B.$KPC(n2);
            }
            if (n3 != 0) {
                $f8B.$SMC(n3);
            }
        }
        return $f8B;
    }
    
    public $BBC $UJC(final URL url, final $H0B $h0B) throws $A6, MalformedURLException, IOException, $U5B {
        $BBC $zwc = this.$XWC.get(url);
        if ($zwc == null) {
            URL url2;
            for (Enumeration keys = this.$XWC.keys(); $zwc == null && keys.hasMoreElements(); $zwc = ($BBC)this.$XWC.get(url2)) {
                url2 = keys.nextElement();
                if (new URL(url2.getProtocol(), url2.getHost(), url2.getPort(), url2.getFile()).equals(url)) {}
            }
        }
        if ($zwc == null) {
            $zwc = this.$ZWC(url, $h0B);
        }
        $zwc.$XPC($h0B);
        return $zwc;
    }
    
    public void $YWC(final $BBC $bbc) {
        this.$XWC.put($bbc.$VPC(), $bbc);
    }
    
    public $BBC $ZWC(final URL url, final $H0B $h0B) throws $A6, IOException, $U5B {
        this.$XWC.remove(url);
        return this.readMaps(url, $h0B);
    }
    
    $J0B() {
        this.$XWC = new Hashtable();
    }
    
    void destroy() {
        final Enumeration enumeration = this.getEnumeration();
        while (enumeration.hasMoreElements()) {
            enumeration.nextElement().destroy();
        }
        this.$XWC.clear();
    }
    
    public void finalize() {
    }
    
    public Enumeration getEnumeration() {
        return this.$XWC.elements();
    }
    
    String nextToken(final StringTokenizer stringTokenizer, final String s, final String s2) throws $U5B {
        try {
            return stringTokenizer.nextToken();
        }
        catch (Exception ex) {
            throw new $U5B("MapStore can not read " + s + "/ line=" + s2.substring(0, 20));
        }
    }
    
    private $BBC readMaps(final URL url, final $H0B $h0B) throws $A6, IOException, $U5B {
        final Hashtable<Object, $BBC> hashtable = (Hashtable<Object, $BBC>)new Hashtable<Object, Object>();
        final Hashtable<Object, Object> hashtable2 = new Hashtable<Object, Object>();
        final $BBC maps = this.readMaps(url, $h0B, hashtable, hashtable2);
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            final $BBC $bbc = hashtable.get(nextElement);
            final Object[] array = hashtable2.get(nextElement);
            if ($bbc != null && array != null) {
                $bbc.$QKC.$JT((URL)array[0]);
                final int intValue = (int)array[2];
                final int intValue2 = (int)array[3];
                if (intValue < 0 || intValue2 < 0) {
                    $bbc.setSize(null);
                }
                else {
                    $bbc.setSize(new Dimension(intValue, intValue2));
                }
            }
            $bbc.$IF(false);
            this.$YWC($bbc);
        }
        return maps;
    }
    
    $BBC readMaps(final URL url, final $H0B $h0B, final Dictionary dictionary, final Dictionary dictionary2) throws IOException, $U5B {
        $BBC $bbc = null;
        final InputStream inputStream = $h0B.getInputStream(url);
        try {
            final $DJ $dj = new $DJ(inputStream);
            while (true) {
                final String nextTag = $dj.getNextTag("MAP;IMG");
                if (nextTag == null) {
                    break;
                }
                if (nextTag.equals("MAP")) {
                    final $BBC $dxc = this.$DXC($dj, url, $h0B, dictionary);
                    if ($bbc != null) {
                        continue;
                    }
                    $bbc = $dxc;
                }
                else {
                    if (!nextTag.equals("IMG")) {
                        continue;
                    }
                    this.$EXC($dj, url, dictionary2);
                }
            }
        }
        finally {
            inputStream.close();
        }
        if ($bbc == null) {
            throw new $U5B("no valid mapfile: " + url);
        }
        return $bbc;
    }
}
