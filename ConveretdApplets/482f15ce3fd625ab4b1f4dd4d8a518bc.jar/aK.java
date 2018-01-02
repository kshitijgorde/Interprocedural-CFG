import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aK
{
    public static Font a;
    
    public static final boolean a(final String s) {
        return s.equalsIgnoreCase("www.davieboy.net") || s.equalsIgnoreCase("www.nescafeweb.com");
    }
    
    public static final boolean b(final String s) {
        return !s.equalsIgnoreCase("") && !s.equalsIgnoreCase("localhost") && !s.startsWith("192.168.") && !s.equalsIgnoreCase("www.arcadescript.com") && !s.equalsIgnoreCase("dev.arcadescript.com") && !s.equalsIgnoreCase("demo.arcadescript.com") && !s.equalsIgnoreCase("www.everyvideogame.com") && !s.equalsIgnoreCase("www.retrouprising.com") && !s.equalsIgnoreCase("the.www.retrouprising.com") && !s.equalsIgnoreCase("www.nesforever.com") && !s.equalsIgnoreCase("www.excessively.net") && !s.equalsIgnoreCase("excessively.net") && !s.equalsIgnoreCase("www.arcadecow.com") && !s.equalsIgnoreCase("www.playnes.net") && !s.equalsIgnoreCase("www.thesmartass.info") && !s.equalsIgnoreCase("www.1980-games.com") && !s.equalsIgnoreCase("www.arcadegangsta.com") && !s.equalsIgnoreCase("www.arcadethugz.com") && !s.equalsIgnoreCase("www.arcademaniacs.com") && !s.equalsIgnoreCase("www.musicworld3d.com") && !s.equalsIgnoreCase("www.musicworld3d.net") && !s.equalsIgnoreCase("musicworld3d.com") && !s.equalsIgnoreCase("musicworld3d.net") && !s.equalsIgnoreCase("www.gamehuddle.com") && !s.equalsIgnoreCase("flashgamesite.com") && !s.equalsIgnoreCase("www.flashgamesite.com") && !s.equalsIgnoreCase("8-bitarmy.com") && !s.equalsIgnoreCase("www.8-bitarmy.com") && !s.equalsIgnoreCase("nesonline.kryptonware.com") && !s.equalsIgnoreCase("www.game24hrs.com") && !s.equalsIgnoreCase("www.iplay.com.br") && !s.equalsIgnoreCase("www.nespear.com") && !s.equalsIgnoreCase("nespear.com") && !s.equalsIgnoreCase("www.daviddn.com") && !s.equalsIgnoreCase("www.daviddn.net") && !s.equalsIgnoreCase("madd74.com") && !s.equalsIgnoreCase("www.madd74.com") && !a(s);
    }
    
    public static final String a(final int n) {
        if (n < 60) {
            return n + " secs";
        }
        if (n < 3600) {
            return n / 60 + " mins";
        }
        return "over 1 hour";
    }
    
    public static final void a(final Graphics graphics, final int n, final int n2) {
        try {
            int n3 = n2;
            int n4 = n;
            graphics.setColor(new Color(50, 128, 50));
            graphics.fillPolygon(new int[] { n4 + 0, n4 - 11, n4 + 0 }, new int[] { n3 + 0, n3 + 11, n3 + 22 }, 3);
            graphics.setColor(new Color(50, 200, 50));
            n3 += 3;
            --n4;
            graphics.fillPolygon(new int[] { n4 + 0, n4 - 8, n4 + 0 }, new int[] { n3 + 0, n3 + 8, n3 + 16 }, 3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void b(final Graphics graphics, final int n, final int n2) {
        try {
            int n3 = n2;
            int n4 = n;
            graphics.setColor(new Color(50, 128, 50));
            graphics.fillPolygon(new int[] { n4 + 0, n4 + 11, n4 + 0 }, new int[] { n3 + 0, n3 + 11, n3 + 22 }, 3);
            graphics.setColor(new Color(50, 200, 50));
            n3 += 3;
            ++n4;
            graphics.fillPolygon(new int[] { n4 + 0, n4 + 8, n4 + 0 }, new int[] { n3 + 0, n3 + 8, n3 + 16 }, 3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void c(final Graphics graphics, final int n, final int n2) {
        try {
            int n3 = n;
            int n4 = n2;
            graphics.setColor(new Color(50, 128, 50));
            graphics.fillPolygon(new int[] { n3 + 0, n3 + 11, n3 + 22 }, new int[] { n4 + 0, n4 - 11, n4 + 0 }, 3);
            graphics.setColor(new Color(50, 200, 50));
            n3 += 3;
            --n4;
            graphics.fillPolygon(new int[] { n3 + 0, n3 + 8, n3 + 16 }, new int[] { n4 + 0, n4 - 8, n4 + 0 }, 3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void d(final Graphics graphics, final int n, final int n2) {
        try {
            int n3 = n;
            int n4 = n2;
            graphics.setColor(new Color(50, 128, 50));
            graphics.fillPolygon(new int[] { n3 + 0, n3 + 11, n3 + 22 }, new int[] { n4 + 0, n4 + 11, n4 + 0 }, 3);
            graphics.setColor(new Color(50, 200, 50));
            n3 += 3;
            ++n4;
            graphics.fillPolygon(new int[] { n3 + 0, n3 + 8, n3 + 16 }, new int[] { n4 + 0, n4 + 8, n4 + 0 }, 3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void a(final Graphics graphics, final String s, final Font font, final int n) {
        a(graphics, s, font, 0, n);
    }
    
    public static final void a(final Graphics graphics, final String s, final Font font, final int n, final int n2) {
        if (s == null || s.equals("")) {
            return;
        }
        final int n3 = (int)new TextLayout(s, font, new FontRenderContext(null, false, false)).getBounds().getWidth();
        graphics.setFont(font);
        graphics.drawString(s, (255 - n3) / 2 + n, n2);
    }
    
    public static final boolean a() {
        final SecurityManager securityManager;
        if ((securityManager = System.getSecurityManager()) != null && securityManager.getClass().getName().startsWith("com.ms.security.StandardSecurityManager")) {
            return false;
        }
        double double1 = 0.0;
        try {
            double1 = Double.parseDouble(System.getProperty("java.specification.version"));
        }
        catch (SecurityException ex) {}
        catch (Exception ex2) {}
        return double1 > 1.2;
    }
    
    public static final void a(final Graphics graphics, final String s, final int n, final int n2, final Color color) {
        a(graphics, s, n, n2, color, 1);
    }
    
    public static final void a(final Graphics graphics, final String s, final int n, final int n2, final Color color, final int n3) {
        a(graphics, s, n, n2, color, n3, new Color(0, 0, 0), aK.a);
    }
    
    public static final void a(final Graphics graphics, final String s, final int n, final int n2, final Color color, final int n3, final Color color2, final Font font) {
        graphics.setColor(color2);
        graphics.setFont(font);
        graphics.drawString(s, n + n3, n2 + n3);
        graphics.drawString(s, n, n2 + n3);
        graphics.drawString(s, n - n3, n2 + n3);
        graphics.drawString(s, n + n3, n2);
        graphics.drawString(s, n - n3, n2);
        graphics.drawString(s, n + n3, n2 - n3);
        graphics.drawString(s, n, n2 - n3);
        graphics.drawString(s, n - n3, n2 - n3);
        if (color == null) {
            graphics.setColor(new Color(255, 255, 255));
        }
        else {
            graphics.setColor(color);
        }
        graphics.drawString(s, n, n2);
    }
    
    public static final void a(final OutputStream outputStream, final int n) throws IOException {
        final int n2 = n & -1;
        outputStream.write(n2 >> 0 & 0xFF);
        outputStream.write(n2 >> 8 & 0xFF);
        outputStream.write(n2 >> 16 & 0xFF);
        outputStream.write(n2 >> 24 & 0xFF);
    }
    
    public static final void b(final OutputStream outputStream, final int n) throws IOException {
        final int n2 = n & 0xFFFF;
        outputStream.write(n2 >> 0 & 0xFF);
        outputStream.write(n2 >> 8 & 0xFF);
    }
    
    public static final void c(final OutputStream outputStream, final int n) throws IOException {
        outputStream.write((n & 0xFF) >> 0 & 0xFF);
    }
    
    public static final int a(final InputStream inputStream) throws IOException {
        final byte[] array = new byte[2];
        if (inputStream.read(array, 0, 2) < 2) {
            throw new IOException("Out of Input");
        }
        final int[] array2 = new int[2];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] + 256 & 0xFF);
        }
        return array2[0] + (array2[1] << 8);
    }
    
    public static final int b(final InputStream inputStream) throws IOException {
        final byte[] array = new byte[4];
        if (inputStream.read(array, 0, 4) < 4) {
            throw new IOException("Out of Input");
        }
        final int[] array2 = new int[4];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] + 256 & 0xFF);
        }
        return array2[0] + (array2[1] << 8) + (array2[2] << 16) + (array2[3] << 24);
    }
    
    public static final int c(final InputStream inputStream) throws IOException {
        final byte[] array = { 0 };
        if (inputStream.read(array, 0, 1) < 1) {
            throw new IOException("Out of Input");
        }
        return array[0] + 256 & 0xFF;
    }
    
    public static final String a(final String s) {
        if (s == null || s.trim().equals("")) {
            return "";
        }
        String s2;
        for (s2 = s; s2.indexOf("\\") >= 0; s2 = s2.substring(s2.indexOf("\\") + 1)) {}
        while (s2.indexOf("/") >= 0) {
            s2 = s2.substring(s2.indexOf("/") + 1);
        }
        while (s2.indexOf("?id=") >= 0) {
            s2 = s2.substring(s2.indexOf("?id=") + 4);
        }
        if ((s2.indexOf(".tmp") >= 0 & s2.indexOf("nes-") == 0) && (s2 = s2.substring(4)).indexOf("-") >= 0) {
            s2 = s2.substring(0, s2.indexOf("-"));
        }
        while (s2.toUpperCase().indexOf(".NES") >= 0) {
            s2 = s2.substring(0, s2.indexOf("."));
        }
        return s2;
    }
    
    public static final String a(final int n, final int n2) {
        String s;
        for (s = Integer.toHexString(n).toUpperCase(); s.length() < n2; s = "0" + s) {}
        return s;
    }
    
    public static final int a(String substring) {
        final String s = "0123456789ABCDEF";
        int n = 0;
        int n2 = 1;
        while (substring.length() > 0) {
            final String upperCase = substring.substring(substring.length() - 1).toUpperCase();
            if (s.indexOf(upperCase) < 0) {
                return 0;
            }
            n += n2 * s.indexOf(upperCase);
            n2 *= 16;
            substring = substring.substring(0, substring.length() - 1);
        }
        return n;
    }
    
    public static final int a(final int n, final int n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    public static final byte[] a(final byte[] array) {
        if (array == null) {
            return new byte[0];
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
            final GZIPOutputStream gzipOutputStream;
            (gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)).write(array, 0, array.length);
            gzipOutputStream.finish();
            gzipOutputStream.close();
            try {
                byteArrayOutputStream.close();
            }
            catch (IOException ex2) {
                return null;
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static final byte[] b(final byte[] array) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            final byte[] array2 = new byte[2048];
            while (gzipInputStream.available() != 0) {
                final int read;
                if ((read = gzipInputStream.read(array2, 0, array2.length)) > 0) {
                    byteArrayOutputStream.write(array2, 0, read);
                }
            }
            gzipInputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static final byte[] c(final byte[] input) {
        final Inflater inflater;
        (inflater = new Inflater()).setInput(input);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(input.length);
        final byte[] array = new byte[8192];
        while (!inflater.finished()) {
            try {
                byteArrayOutputStream.write(array, 0, inflater.inflate(array));
                continue;
            }
            catch (DataFormatException ex) {
                return null;
            }
            break;
        }
        try {
            byteArrayOutputStream.close();
        }
        catch (IOException ex2) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static final int a() {
        final GregorianCalendar gregorianCalendar;
        (gregorianCalendar = new GregorianCalendar()).get(1);
        gregorianCalendar.get(2);
        return 0;
    }
    
    public static final String[] a(final String s, final char c) {
        if (s == null) {
            return null;
        }
        int n = 1;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++n;
            }
        }
        final String[] array = new String[n];
        int n3 = 0;
        for (int j = 0; j < s.length(); ++j) {
            if (s.charAt(j) == c) {
                array[n3++] = s.substring(n2, j);
                n2 = j + 1;
            }
        }
        array[n3] = s.substring(n2);
        return array;
    }
    
    static {
        new Font("Dialog", 1, 13);
        aK.a = new Font("Dialog", 0, 9);
    }
}
