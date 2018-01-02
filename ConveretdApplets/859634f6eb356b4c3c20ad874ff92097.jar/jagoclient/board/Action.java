// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.xml.XmlWriter;
import java.util.Vector;
import rene.util.parser.StringParser;
import java.io.PrintWriter;
import rene.util.list.ListElement;
import rene.util.list.ListClass;

public class Action
{
    String Type;
    ListClass Arguments;
    
    public Action(final String type) {
        this.Type = type;
        this.Arguments = new ListClass();
    }
    
    public Action(final String type, final String s) {
        this.Type = type;
        this.Arguments = new ListClass();
        this.addargument(s);
    }
    
    public void addargument(final String s) {
        this.Arguments.append(new ListElement(s));
    }
    
    public void toggleargument(final String s) {
        for (ListElement listElement = this.Arguments.first(); listElement != null; listElement = listElement.next()) {
            if (((String)listElement.content()).equals(s)) {
                this.Arguments.remove(listElement);
                return;
            }
        }
        this.Arguments.append(new ListElement(s));
    }
    
    public void print(final PrintWriter printWriter) {
        printWriter.println();
        printWriter.print(this.Type);
        for (ListElement listElement = this.Arguments.first(); listElement != null; listElement = listElement.next()) {
            printWriter.print("[");
            final Vector wrapwords = new StringParser((String)listElement.content()).wrapwords(60);
            for (int i = 0; i < wrapwords.size(); ++i) {
                String substring = wrapwords.elementAt(i);
                if (i > 0) {
                    printWriter.println();
                }
                for (int j = substring.indexOf(93); j >= 0; j = substring.indexOf(93)) {
                    if (j > 0) {
                        printWriter.print(substring.substring(0, j));
                    }
                    printWriter.print("\\]");
                    substring = substring.substring(j + 1);
                }
                printWriter.print(substring);
            }
            printWriter.print("]");
        }
    }
    
    public void print(final XmlWriter xmlWriter, final int n, final int n2) {
        if (this.Type.equals("C")) {
            xmlWriter.startTagNewLine("Comment");
            this.printTextArgument(xmlWriter);
            xmlWriter.endTagNewLine("Comment");
            return;
        }
        if (!this.Type.equals("GN") && !this.Type.equals("AP") && !this.Type.equals("FF") && !this.Type.equals("GM") && !this.Type.equals("N") && !this.Type.equals("SZ") && !this.Type.equals("PB") && !this.Type.equals("BR") && !this.Type.equals("PW") && !this.Type.equals("WR") && !this.Type.equals("HA") && !this.Type.equals("KM") && !this.Type.equals("RE") && !this.Type.equals("DT") && !this.Type.equals("TM") && !this.Type.equals("US") && !this.Type.equals("CP")) {
            if (this.Type.equals("B")) {
                xmlWriter.printTagNewLine("Black", "number", String.valueOf(n2), this.getXMLMove(n));
                return;
            }
            if (this.Type.equals("W")) {
                xmlWriter.printTagNewLine("White", "number", String.valueOf(n2), this.getXMLMove(n));
                return;
            }
            if (this.Type.equals("AB")) {
                this.printAllFields(xmlWriter, n, "AddBlack");
                return;
            }
            if (this.Type.equals("AW")) {
                this.printAllFields(xmlWriter, n, "AddWhite");
                return;
            }
            if (this.Type.equals("AE")) {
                this.printAllFields(xmlWriter, n, "Delete");
                return;
            }
            if (this.Type.equals("MA")) {
                this.printAllFields(xmlWriter, n, "Mark");
                return;
            }
            if (this.Type.equals("M")) {
                this.printAllFields(xmlWriter, n, "Mark");
                return;
            }
            if (this.Type.equals("SQ")) {
                this.printAllFields(xmlWriter, n, "Mark", "type", "square");
                return;
            }
            if (this.Type.equals("CR")) {
                this.printAllFields(xmlWriter, n, "Mark", "type", "circle");
                return;
            }
            if (this.Type.equals("TR")) {
                this.printAllFields(xmlWriter, n, "Mark", "type", "triangle");
                return;
            }
            if (this.Type.equals("TB")) {
                this.printAllFields(xmlWriter, n, "Mark", "territory", "black");
                return;
            }
            if (this.Type.equals("TW")) {
                this.printAllFields(xmlWriter, n, "Mark", "territory", "white");
                return;
            }
            if (this.Type.equals("LB")) {
                this.printAllSpecialFields(xmlWriter, n, "Mark", "label");
                return;
            }
            if (this.Type.equals("BL")) {
                xmlWriter.printTagNewLine("BlackTimeLeft", this.argument());
                return;
            }
            if (this.Type.equals("WL")) {
                xmlWriter.printTagNewLine("WhiteTimeLeft", this.argument());
                return;
            }
            xmlWriter.startTag("SGF", "type", this.Type);
            ListElement listElement = this.Arguments.first();
            while (listElement != null) {
                xmlWriter.startTag("Arg");
                final Vector wrapwords = new StringParser((String)listElement.content()).wrapwords(60);
                for (int i = 0; i < wrapwords.size(); ++i) {
                    final String s = wrapwords.elementAt(i);
                    if (i > 0) {
                        xmlWriter.println();
                    }
                    xmlWriter.print(s);
                }
                listElement = listElement.next();
                xmlWriter.endTag("Arg");
            }
            xmlWriter.endTagNewLine("SGF");
        }
    }
    
    public void printAllFields(final XmlWriter xmlWriter, final int n, final String s) {
        for (ListElement listElement = this.Arguments.first(); listElement != null; listElement = listElement.next()) {
            final String s2 = (String)listElement.content();
            xmlWriter.printTagNewLine(s, this.getXMLMove(listElement, n));
        }
    }
    
    public void printAllFields(final XmlWriter xmlWriter, final int n, final String s, final String s2, final String s3) {
        for (ListElement listElement = this.Arguments.first(); listElement != null; listElement = listElement.next()) {
            final String s4 = (String)listElement.content();
            xmlWriter.printTagNewLine(s, s2, s3, this.getXMLMove(listElement, n));
        }
    }
    
    public void printAllSpecialFields(final XmlWriter xmlWriter, final int n, final String s, final String s2) {
        for (ListElement listElement = this.Arguments.first(); listElement != null; listElement = listElement.next()) {
            final StringParser stringParser = new StringParser((String)listElement.content());
            stringParser.parseword(':');
            stringParser.skip(":");
            xmlWriter.printTagNewLine(s, s2, stringParser.parseword(), this.getXMLMove(listElement, n));
        }
    }
    
    public String getXMLMove(final ListElement listElement, final int n) {
        if (listElement == null) {
            return "";
        }
        final String s = (String)listElement.content();
        if (s == null) {
            return "";
        }
        final int i = Field.i(s);
        final int j = Field.j(s);
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return "";
        }
        return Field.coordinate(Field.i(s), Field.j(s), n);
    }
    
    public String getXMLMove(final int n) {
        return this.getXMLMove(this.Arguments.first(), n);
    }
    
    public void printTextArgument(final XmlWriter xmlWriter) {
        final ListElement first = this.Arguments.first();
        if (first == null) {
            return;
        }
        xmlWriter.printParagraphs((String)first.content(), 60);
    }
    
    public void type(final String type) {
        this.Type = type;
    }
    
    public String type() {
        return this.Type;
    }
    
    public ListElement arguments() {
        return this.Arguments.first();
    }
    
    public String argument() {
        if (this.arguments() == null) {
            return "";
        }
        return (String)this.arguments().content();
    }
}
