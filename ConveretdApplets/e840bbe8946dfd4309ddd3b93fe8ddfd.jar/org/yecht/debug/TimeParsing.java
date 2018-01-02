// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.debug;

import java.io.InputStream;
import org.yecht.ErrorHandler;
import org.yecht.NodeHandler;
import org.yecht.NullNodeHandler;
import org.yecht.IoStrRead;
import org.yecht.Pointer;
import org.yecht.Parser;
import org.yecht.YAML;
import java.io.FileInputStream;

public class TimeParsing
{
    public static void main(final String[] args) throws Exception {
        final String filename = args[0];
        int len = 8000;
        int read = 0;
        int currRead = 0;
        final byte[] buffer = new byte[1024];
        byte[] input = new byte[len];
        final InputStream is = new FileInputStream(filename);
        while ((currRead = is.read(buffer, 0, 1024)) != -1) {
            if (read + currRead >= len) {
                len *= 2;
                input = YAML.realloc(input, len);
            }
            System.arraycopy(buffer, 0, input, read, currRead);
            read += currRead;
        }
        final int times = 10000;
        final long before = System.currentTimeMillis();
        for (int i = 0; i < times; ++i) {
            final Parser parser = Parser.newParser();
            parser.str(Pointer.create(input, 0), read, null);
            parser.handler(new NullNodeHandler());
            parser.errorHandler(null);
            parser.implicitTyping(true);
            parser.taguriExpansion(true);
            parser.parse();
        }
        final long after = System.currentTimeMillis();
        System.err.println("parsing " + filename + " " + times + " times took " + (after - before) + "ms");
    }
}
