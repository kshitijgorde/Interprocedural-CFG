// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.debug;

import org.yecht.DefaultYAMLParser;
import java.io.InputStream;
import org.yecht.TokenScanner;
import org.yecht.ErrorHandler;
import org.yecht.NodeHandler;
import org.yecht.NullNodeHandler;
import org.yecht.IoStrRead;
import org.yecht.Pointer;
import org.yecht.Parser;
import org.yecht.YAML;
import java.io.FileInputStream;

public class ScannerOutput
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
        final Parser parser = Parser.newParser();
        parser.str(Pointer.create(input, 0), read, null);
        parser.handler(new NullNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(true);
        parser.taguriExpansion(true);
        final DefaultYAMLParser.yyInput s = TokenScanner.createScanner(parser);
        int tok = -1;
        Object lval = null;
        int indent = 0;
        while (tok != 0) {
            s.advance();
            tok = s.token();
            if (tok == 266) {
                for (int i = 0; i < indent; ++i) {
                    System.out.print(" ");
                }
                ++indent;
            }
            else if (tok == 268) {
                --indent;
                for (int i = 0; i < indent; ++i) {
                    System.out.print(" ");
                }
            }
            else {
                for (int i = 0; i < indent; ++i) {
                    System.out.print(" ");
                }
            }
            final Object lval2 = s.value();
            System.out.print("tok: " + TokenScanner.tnames[tok]);
            if (lval != lval2) {
                System.out.print(" lval: " + lval2);
                lval = lval2;
            }
            System.out.println();
        }
    }
}
