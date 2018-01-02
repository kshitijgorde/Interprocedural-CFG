// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;
import com.stonewall.parser.Test;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.Element;
import java.io.FileReader;
import java.io.File;
import org.jdom.Document;
import java.io.StringReader;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.parser.Function;
import com.stonewall.parser.Parser;

public class TextParser extends Parser implements IParser
{
    private String name;
    
    public TextParser() {
        this.dispatcher().register("id", new Identity());
    }
    
    public TextParser(final Loader loader, final String main, final String name) {
        this();
        this.classloader(loader);
        this.name = name;
        try {
            final InputStream is = loader.getResourceAsStream(main);
            this.init(new InputStreamReader(is));
        }
        catch (Exception e) {
            TextParser.log.error(this, e);
        }
    }
    
    @Override
    public IModelObject parse(final DeviceOperation op) throws Exception {
        final String text = op.getRawResponse();
        final StringReader reader = new StringReader(text);
        final Document doc = this.parse(reader);
        final IModelObject model = this.convert(doc.detachRootElement());
        return model.getFirstChild(this.name);
    }
    
    @Override
    public IModelObject parse(final File file) throws Exception {
        final FileReader reader = new FileReader(file);
        final Document doc = this.parse(reader);
        final IModelObject model = this.convert(doc.detachRootElement());
        return model.getFirstChild(this.name);
    }
    
    private IModelObject convert(final Element e) throws Exception {
        final XMLOutputter printer = new XMLOutputter(Format.getCompactFormat());
        final String content = printer.outputString(e);
        final ModelBuilder builder = new ModelBuilder();
        return builder.buildModel(content);
    }
    
    public static void main(final String[] args) throws Exception {
        Test.init();
        final Parser p = new TextParser();
        if (args.length == 0) {
            final com.stonewall.parser.interpreter.Test t = new com.stonewall.parser.interpreter.Test();
            t.interpreter.attach(p.dispatcher());
            t.run();
            return;
        }
        final String sw = args[0];
        final String hw = args[1];
        final String mainspec = args[2];
        final String path = args[3];
        Parser.log.info("Parsing:\nsoftware = " + sw + "\nhardware = " + hw + "\nmain (spec) = " + mainspec + "\npath (input)= " + path);
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
        DSP.init();
        final Loader loader = new Loader(sw, hw);
        p.classloader(loader);
        final InputStream istr = loader.getResourceAsStream(mainspec);
        final Reader main = new InputStreamReader(istr);
        Test.basic(p, main, path);
    }
}
