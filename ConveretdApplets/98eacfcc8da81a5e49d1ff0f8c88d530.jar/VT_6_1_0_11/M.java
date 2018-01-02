// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.net.MalformedURLException;
import com.hw.client.util.a;
import java.net.URL;
import java.applet.Applet;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.filechooser.FileFilter;
import com.wimba.clients.vboard.k;
import javax.swing.JFileChooser;
import java.io.File;

public class M
{
    private l a;
    private cY b;
    private String c;
    private String d;
    private String e;
    private File f;
    
    public M(final l a, final cY b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final String c) {
        this.c = c;
    }
    
    public final void b(final String e) {
        this.e = e;
    }
    
    public final void c(final String d) {
        this.d = d;
    }
    
    protected final void a() {
        if (this.e == null && (this.d == null || this.c == null)) {
            return;
        }
        if (this.b.n().q() != 3) {
            return;
        }
        final JFileChooser fileChooser = new JFileChooser(this.f);
        final k fileFilter = new k("mp3", "Audio (mp3)");
        final k k = new k("wav", "Audio (wav)");
        fileChooser.addChoosableFileFilter(new k("spx", "Audio (spx)"));
        fileChooser.addChoosableFileFilter(k);
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(fileFilter);
        if (fileChooser.showSaveDialog(this.a) != 0) {
            return;
        }
        this.f = fileChooser.getCurrentDirectory();
        File selectedFile = fileChooser.getSelectedFile();
        final String lowerCase;
        final String s = ((lowerCase = fileChooser.getFileFilter().getDescription().toLowerCase()).indexOf("spx") > 0) ? "spx" : ((lowerCase.indexOf("wav") > 0) ? "wav" : "mp3");
        if (!selectedFile.getName().toLowerCase().endsWith("." + s)) {
            selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + "." + s);
        }
        if (selectedFile.exists() && 0 != JOptionPane.showConfirmDialog(this.a, this.d("export_confirm_override_msg"), this.d("export_confirm_override_title"), 0)) {
            return;
        }
        URL url;
        try {
            String s2 = "?";
            if (this.e != null && this.e.length() > 0) {
                s2 = s2 + "nid=" + this.e;
            }
            else if (this.c.length() > 0 && this.d.length() > 0) {
                s2 = s2 + "rid=" + this.d + "&mid=" + this.c;
            }
            url = new URL(this.a.g, ca.a(this.a.t) + "/audio" + (s2 + "&format=" + s));
        }
        catch (MalformedURLException ex2) {
            final MalformedURLException ex = ex2;
            com.hw.client.util.a.b(ex2.getMessage(), ex);
            JOptionPane.showMessageDialog(this.a, ex.getMessage());
            return;
        }
        catch (IOException ex4) {
            final IOException ex3 = ex4;
            com.hw.client.util.a.b(ex4.getMessage(), ex3);
            JOptionPane.showMessageDialog(this.a, ex3.getMessage());
            return;
        }
        this.a.b().a(url, selectedFile);
    }
    
    private String d(final String s) {
        return this.a.e(s);
    }
    
    public M() {
    }
    
    public static float a(final float[] array, final float[] array2, final float[] array3, final int n) {
        float n2 = array2[0];
        if (array2[0] == 0.0f) {
            for (int i = 0; i < n; ++i) {
                array3[i] = 0.0f;
            }
            return 0.0f;
        }
        for (int j = 0; j < n; ++j) {
            float n3 = -array2[j + 1];
            for (int k = 0; k < j; ++k) {
                n3 -= array[k] * array2[j - k];
            }
            final float n4 = array3[j] = n3 / n2;
            array[j] = n4;
            int l;
            for (l = 0; l < j / 2; ++l) {
                final float n5 = array[l];
                final int n6 = l;
                array[n6] += n4 * array[j - 1 - l];
                final int n7 = j - 1 - l;
                array[n7] += n4 * n5;
            }
            if (j % 2 != 0) {
                final int n8 = l;
                array[n8] += array[l] * n4;
            }
            n2 *= (float)(1.0 - n4 * n4);
        }
        return n2;
    }
    
    public static void a(final float[] array, final float[] array2, int n, final int n2) {
        while (n-- > 0) {
            int i = n;
            float n3 = 0.0f;
            while (i < n2) {
                n3 += array[i] * array[i - n];
                ++i;
            }
            array2[n] = n3;
        }
    }
}
