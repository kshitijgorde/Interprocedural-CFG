// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

import java.io.Reader;
import java.util.List;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import java.io.InputStreamReader;
import com.stonewall.parser.Dictionary;
import java.util.ArrayList;
import org.xmodel.log.Log;

public class Test
{
    public Interpreter interpreter;
    static Log log;
    
    static {
        Test.log = Log.getLog(Interpreter.class);
    }
    
    public Test() {
        this.interpreter = new Interpreter();
    }
    
    public void run() throws Exception {
        this.run(new String[0]);
    }
    
    public void run(final String[] args) throws Exception {
        final String line = "my dog has fleas";
        final List<String> tokens = new ArrayList<String>();
        String[] split;
        for (int length = (split = line.split("[\\s]+")).length, i = 0; i < length; ++i) {
            final String t = split[i];
            tokens.add(t);
        }
        final Dictionary<String> keywords = new Dictionary<String>();
        keywords.put("my", "dog");
        String string = "";
        if (args.length == 0) {
            Test.log.info("parsing stdin...");
            final Reader r = new InputStreamReader(System.in);
            this.interpreter.setTokens(tokens);
            this.interpreter.setKeywords(keywords);
            final Document model = new Document(new Element("model"));
            Element root = model.getRootElement();
            Element child = new Element("person");
            root.addContent((Content)child);
            root = child;
            root.addContent((Content)new Element("name").setText("jeff"));
            root.addContent((Content)new Element("age").setText("43"));
            child = new Element("phone");
            root.addContent((Content)child);
            root = child;
            root.addContent((Content)new Element("npa").setText("919"));
            root.addContent((Content)new Element("nxx").setText("881"));
            root.addContent((Content)new Element("number").setText("9592"));
            this.interpreter.set(model);
            string = this.interpreter.execute(r);
        }
        else {
            Test.log.info("parsing:\n" + args[0]);
            string = this.interpreter.execute(args[0]);
        }
        Test.log.info("\nRESULT:\n" + string + "\n");
    }
    
    public static void main(final String[] args) throws Exception {
        new Test().run(args);
    }
}
