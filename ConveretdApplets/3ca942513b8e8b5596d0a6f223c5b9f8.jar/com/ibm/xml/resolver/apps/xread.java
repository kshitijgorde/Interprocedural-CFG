// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.apps;

import com.ibm.xml.resolver.CatalogManager;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.ibm.xml.resolver.Catalog;
import java.util.Date;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import com.ibm.xml.resolver.tools.ResolvingXMLReader;
import java.util.Vector;
import com.ibm.xml.resolver.helpers.Debug;

public class xread
{
    private static Debug debug;
    
    public static void main(final String[] array) throws FileNotFoundException, IOException {
        String s = null;
        final int n = 0;
        int maxMessages = 10;
        boolean b = true;
        boolean b2 = true;
        boolean b3 = n > 2;
        final boolean b4 = true;
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals("-c")) {
                ++i;
                vector.add(array[i]);
            }
            else if (array[i].equals("-w")) {
                b2 = false;
            }
            else if (array[i].equals("-v")) {
                b2 = true;
            }
            else if (array[i].equals("-n")) {
                b = false;
            }
            else if (array[i].equals("-N")) {
                b = true;
            }
            else if (array[i].equals("-d")) {
                ++i;
                final String s2 = array[i];
                try {
                    final int int1 = Integer.parseInt(s2);
                    if (int1 >= 0) {
                        xread.debug.setDebug(int1);
                        b3 = (int1 > 2);
                    }
                }
                catch (Exception ex3) {}
            }
            else if (array[i].equals("-E")) {
                ++i;
                final String s3 = array[i];
                try {
                    final int int2 = Integer.parseInt(s3);
                    if (int2 >= 0) {
                        maxMessages = int2;
                    }
                }
                catch (Exception ex4) {}
            }
            else {
                s = array[i];
            }
        }
        if (s == null) {
            System.out.println("Usage: org.apache.xml.resolver.apps.xread [opts] xmlfile");
            System.exit(1);
        }
        final ResolvingXMLReader resolvingXMLReader = new ResolvingXMLReader();
        try {
            resolvingXMLReader.setFeature("http://xml.org/sax/features/namespaces", b);
            resolvingXMLReader.setFeature("http://xml.org/sax/features/validation", b2);
        }
        catch (SAXException ex5) {}
        final Catalog catalog = resolvingXMLReader.getCatalog();
        for (int j = 0; j < vector.size(); ++j) {
            catalog.parseCatalog(vector.elementAt(j));
        }
        final XParseError errorHandler = new XParseError(b4, b3);
        errorHandler.setMaxMessages(maxMessages);
        resolvingXMLReader.setErrorHandler(errorHandler);
        final String s4 = b2 ? "validating" : "well-formed";
        final String s5 = b ? "namespace-aware" : "namespace-ignorant";
        if (maxMessages > 0) {
            System.out.println("Attempting " + s4 + ", " + s5 + " parse");
        }
        final Date date = new Date();
        try {
            resolvingXMLReader.parse(s);
        }
        catch (SAXException ex) {
            System.out.println("SAX Exception: " + ex);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        long n2 = new Date().getTime() - date.getTime();
        long n3 = 0L;
        long n4 = 0L;
        long n5 = 0L;
        if (n2 > 1000L) {
            n3 = n2 / 1000L;
            n2 %= 1000L;
        }
        if (n3 > 60L) {
            n4 = n3 / 60L;
            n3 %= 60L;
        }
        if (n4 > 60L) {
            n5 = n4 / 60L;
            n4 %= 60L;
        }
        if (maxMessages > 0) {
            System.out.print("Parse ");
            if (errorHandler.getFatalCount() > 0) {
                System.out.print("failed ");
            }
            else {
                System.out.print("succeeded ");
                System.out.print("(");
                if (n5 > 0L) {
                    System.out.print(n5 + ":");
                }
                if (n5 > 0L || n4 > 0L) {
                    System.out.print(n4 + ":");
                }
                System.out.print(n3 + "." + n2);
                System.out.print(") ");
            }
            System.out.print("with ");
            final int errorCount = errorHandler.getErrorCount();
            final int warningCount = errorHandler.getWarningCount();
            if (errorCount > 0) {
                System.out.print(errorCount + " error");
                System.out.print((errorCount > 1) ? "s" : "");
                System.out.print(" and ");
            }
            else {
                System.out.print("no errors and ");
            }
            if (warningCount > 0) {
                System.out.print(warningCount + " warning");
                System.out.print((warningCount > 1) ? "s" : "");
                System.out.print(".");
            }
            else {
                System.out.print("no warnings.");
            }
            System.out.println("");
        }
        if (errorHandler.getErrorCount() > 0) {
            System.exit(1);
        }
    }
    
    static {
        xread.debug = CatalogManager.getStaticManager().debug;
    }
}
