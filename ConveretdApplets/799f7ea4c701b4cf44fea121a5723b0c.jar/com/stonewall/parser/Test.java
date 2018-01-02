// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import org.jdom.Document;
import java.io.Reader;
import java.io.FileReader;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;

public class Test
{
    static Format pretty;
    static XMLOutputter printer;
    
    static {
        Test.pretty = Format.getPrettyFormat();
        Test.printer = new XMLOutputter(Test.pretty);
    }
    
    public static void main(final String[] args) throws Exception {
        init();
        final Reader main = new FileReader(args[0]);
        basic(new Parser(), main, args[1]);
    }
    
    public static void init() {
    }
    
    public static void basic(final Parser p, final Reader main, final String file) throws Exception {
        final Document d = parse(p, main, file);
        Parser.log.info("\n" + Test.printer.outputString(d));
    }
    
    public static void benchmark(final Parser p, final Reader main, final String file) throws Exception {
        final Document d = parse(p, main, file);
        Parser.log.info("\n" + Test.printer.outputString(d));
        for (int i = 0; i < 20; ++i) {
            parse(p, main, file);
        }
    }
    
    static Document parse(final Parser p, final Reader main, final String file) throws Exception {
        p.init(main);
        Parser.log.info("parsing: " + file);
        final Reader reader = new FileReader(file);
        final Document result = p.parse(reader);
        Parser.log.info("xpath performance (hit ratio)=" + p.pathStore().hitRatio());
        return result;
    }
}
