// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$XG;

import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

public class $IK extends $TI
{
    Vector $JK;
    Vector $KK;
    
    void $LK(final Writer writer) throws IOException {
        writer.write("<SCRIPT LANGUAGE=\"JavaScript\"><!--\n\tvar _info = navigator.userAgent;\n\tvar _ns = false;\n\tvar _ie = _info.indexOf(\"MSIE\") > 0 && _info.indexOf(\"Win\") > 0\n && _info.indexOf(\"Windows 3.1\") < 0;\n//--></SCRIPT>\n");
        writer.write("<COMMENT><SCRIPT LANGUAGE=\"JavaScript1.1\"><!--\n\tvar _ns = (navigator.appName.indexOf(\"Netscape\") >= 0 && ((_info.indexOf(\"Win\") > 0 && _info.indexOf(\"Win16\") < 0 && java.lang.System.getProperty(\"os.version\").indexOf(\"3.5\") < 0) || _info.indexOf(\"Sun\") > 0));\n//--></SCRIPT></COMMENT>\n");
    }
    
    void $MK(final Writer writer) throws IOException {
        writer.write("\n\n<SCRIPT LANGUAGE=\"JavaScript\"><!--\n\tif (_ie == true) document.writeln('<OBJECT classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\" width=" + super.width + " height=" + super.height + " codebase=\"http://java.sun.com/products/plugin/1.1/jinstall-11-win32.cab#Version=1,1,0,0\"");
        if (super.$VI != null) {
            final Enumeration<String> keys = super.$VI.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (this.$KK.contains(s.toUpperCase())) {
                    writer.write(" " + s.toUpperCase() + "=" + super.$VI.get(s));
                }
            }
        }
        writer.write(">");
        writer.write("<NOEMBED><XMP>');\n");
        writer.write("\telse if (_ns == true) document.writeln('");
    }
    
    void $NK(final Writer writer) throws IOException {
        writer.write("<EMBED " + $AI.$FI("type", "application/x-java-applet;version=1.1") + $AI.$FI("width", String.valueOf(super.width)) + $AI.$FI("height", String.valueOf(super.height)) + $AI.$FI("code", super.$UI) + $AI.$FI("pluginspage", "http://java.sun.com/products/plugin/1.1/plugin-install.html"));
        if (super.$VI != null) {
            final Enumeration<String> keys = super.$VI.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                writer.write(" " + s + "=\"" + (String)super.$VI.get(s) + "\"");
            }
        }
        if (super.$WI != null) {
            final Enumeration<String> keys2 = super.$WI.keys();
            while (keys2.hasMoreElements()) {
                final String s2 = keys2.nextElement();
                writer.write(" " + s2 + "=\"" + (String)super.$WI.get(s2) + "\"");
            }
        }
        writer.write(">");
    }
    
    void $OK(final Writer writer) throws IOException {
        if (super.$VI == null || (super.$VI.get("code") == null && super.$VI.get("CODE") == null)) {
            writer.write("\t<PARAM NAME=\"code\" VALUE=\"" + super.$UI + "\">\n");
        }
        if (super.$VI == null || (super.$VI.get("type") == null && super.$VI.get("TYPE") == null)) {
            writer.write("\t<PARAM NAME=\"type\" VALUE=\"application/x-java-applet;version=1.1\">\n");
        }
        if (super.$VI != null) {
            final Enumeration<String> keys = super.$VI.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (this.$JK.contains(s.toLowerCase())) {
                    writer.write("\t<PARAM NAME=\"");
                    writer.write(String.valueOf(s.toLowerCase()) + "\" VALUE=\"" + super.$VI.get(s) + "\">\n");
                }
            }
        }
    }
    
    public $IK(final String s, final int n, final int n2, final Dictionary dictionary, final Dictionary dictionary2) {
        super(s, n, n2, dictionary, dictionary2);
        (this.$JK = new Vector()).addElement("archive");
        this.$JK.addElement("code");
        this.$JK.addElement("codebase");
        this.$JK.addElement("object");
        (this.$KK = new Vector()).addElement("ALIGN");
        this.$KK.addElement("HEIGHT");
        this.$KK.addElement("HSPACE");
        this.$KK.addElement("NAME");
        this.$KK.addElement("TITLE");
        this.$KK.addElement("VSPACE");
        this.$KK.addElement("WIDTH");
    }
    
    public void write(final OutputStream outputStream) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        this.$LK(outputStreamWriter);
        this.$MK(outputStreamWriter);
        this.$NK(outputStreamWriter);
        outputStreamWriter.write("<NOEMBED><XMP>');\n//--></SCRIPT>\n");
        this.$YI(outputStreamWriter, null);
        outputStreamWriter.write("</XMP>\n");
        this.$ZI(outputStreamWriter, "\t");
        this.$OK(outputStreamWriter);
        if (super.$XI != null) {
            outputStreamWriter.write(super.$XI);
            outputStreamWriter.write(10);
        }
        this.$AJ(outputStreamWriter, null);
        outputStreamWriter.write("</NOEMBED></EMBED></OBJECT>\n");
        outputStreamWriter.flush();
    }
}
