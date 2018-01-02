import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class RAMLReader
{
    String rawData;
    String rawGroup;
    String rawBlock;
    Vector groupVector;
    int id;
    
    public RAMLReader(final NewsPro newsPro, final String s) {
        final Vector<TextBlock> vector = new Vector<TextBlock>();
        this.groupVector = new Vector();
        final Group group = new Group("Unregistered", newsPro);
        vector.addElement(new TextBlock("Free Applet", "Get your free applet at <link=\"http://www.realapplets.com\" target=\"_blank\">RealApplets.com </link> !", newsPro, group));
        group.setNew(false);
        vector.addElement(new TextBlock("Registration", "Registration removes these comments.", newsPro, group));
        group.addBlocks(vector);
        this.groupVector.addElement(group);
        this.rawData = this.readRawData(String.valueOf(String.valueOf(newsPro.getCodeBase())) + "/" + s);
        while (this.rawData.length() > 0) {
            this.rawGroup = this.extractPart("<group>", "</group>", this.rawData, 1).trim();
            this.rawData = this.rawData.trim();
            final String trim = this.extractPart("<title>", "</title>", this.rawGroup, 2).trim();
            final Vector<TextBlock> vector2 = new Vector<TextBlock>();
            this.id = 0;
            final Group group2 = new Group(trim, newsPro);
            while (this.rawGroup.length() > 0) {
                ++this.id;
                this.rawBlock = this.extractPart("<block>", "</block>", this.rawGroup, 2).trim();
                final TextBlock textBlock = new TextBlock(this.extractPart("<title>", "</title>", this.rawBlock, 3).trim(), this.extractPart("<body>", "</body>", this.rawBlock, 3).trim(), newsPro, group2);
                group2.setNew(false);
                vector2.addElement(textBlock);
            }
            group2.addBlocks(vector2);
            this.groupVector.addElement(group2);
        }
        this.rawData = "";
        this.rawGroup = "";
        this.rawBlock = "";
    }
    
    private String extractPart(final String s, final String s2, String substring, final int n) {
        final int index = this.getIndex(s, substring);
        final int index2 = this.getIndex(s2, substring);
        final int length = s.length();
        final int length2 = s2.length();
        final String substring2 = substring.substring(index + length, index2);
        substring = substring.substring(index2 + length2, substring.length());
        if (n == 1) {
            this.rawData = substring;
        }
        else if (n == 2) {
            this.rawGroup = substring;
        }
        else if (n == 3) {
            this.rawBlock = substring;
        }
        return substring2;
    }
    
    public Vector getAllData() {
        return this.groupVector;
    }
    
    private int getIndex(final String s, final String s2) {
        return s2.toUpperCase().indexOf(s.toUpperCase());
    }
    
    private String readRawData(final String s) {
        int i = 0;
        String string = "";
        try {
            final InputStream openStream = new URL(s).openStream();
            while (i != -1) {
                i = openStream.read();
                if (i != -1) {
                    string = String.valueOf(string) + (char)i;
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Corrupted data file or file is missing");
        }
        return string;
    }
}
