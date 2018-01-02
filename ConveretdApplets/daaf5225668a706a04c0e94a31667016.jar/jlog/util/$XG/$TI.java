// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$XG;

import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.Enumeration;
import java.io.IOException;
import java.io.Writer;
import java.util.Dictionary;

public class $TI
{
    public String $UI;
    public int width;
    public int height;
    public Dictionary $VI;
    public Dictionary $WI;
    public String $XI;
    
    void $AJ(final Writer writer, final String s) throws IOException {
        final $AI $ai = new $AI("/applet");
        if (s != null) {
            writer.write(s);
        }
        $ai.output(writer);
        writer.write(10);
    }
    
    void $YI(final Writer writer, final String s) throws IOException {
        final $AI $ai = new $AI("applet");
        if (this.$UI != null) {
            $ai.put("code", this.$UI);
        }
        $ai.put("width", this.width);
        $ai.put("height", this.height);
        $ai.$GG(this.$VI);
        if (s != null) {
            writer.write(s);
        }
        $ai.output(writer);
        writer.write("\n");
    }
    
    void $ZI(final Writer writer, final String s) throws IOException {
        if (this.$WI == null) {
            return;
        }
        final $AI $ai = new $AI("param");
        final Enumeration<String> keys = this.$WI.keys();
        while (keys.hasMoreElements()) {
            final String nextElement = keys.nextElement();
            final Object value = this.$WI.get(nextElement);
            $ai.put("name", nextElement);
            $ai.put("value", value);
            if (s != null) {
                writer.write(s);
            }
            $ai.output(writer);
            writer.write("\n");
        }
    }
    
    public $TI(final String $ui, final int width, final int height, final Dictionary $vi, final Dictionary $wi) {
        this.$XI = null;
        this.$UI = $ui;
        this.width = width;
        this.height = height;
        this.$VI = $vi;
        this.$WI = $wi;
    }
    
    public void write(final OutputStream outputStream) throws IOException {
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        this.$YI(outputStreamWriter, null);
        this.$ZI(outputStreamWriter, "\t");
        if (this.$XI != null) {
            outputStreamWriter.write(this.$XI);
            outputStreamWriter.write(10);
        }
        this.$AJ(outputStreamWriter, null);
        outputStreamWriter.flush();
    }
}
