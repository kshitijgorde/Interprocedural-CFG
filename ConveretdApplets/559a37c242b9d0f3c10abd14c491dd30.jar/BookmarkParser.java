import java.io.IOException;
import java.io.Reader;

// 
// Decompiled by Procyon v0.5.30
// 

public class BookmarkParser
{
    public static final int EOF = -1;
    public static final int UNKNOWN = 0;
    public static final int BOOKMARK = 1;
    public static final int FOLDER = 2;
    public static final int FOLDER_END = 3;
    public static final int SEPARATOR = 4;
    int type;
    TagGetter t;
    String name;
    String link;
    
    public BookmarkParser(final Reader reader) throws IOException {
        this.type = 0;
        this.t = new TagGetter(reader);
    }
    
    static void error(final String s) {
        System.out.println("*** " + s);
    }
    
    public String getLink() {
        return this.link;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getType() {
        return this.type;
    }
    
    boolean isBookmark(final String s) {
        return s.startsWith("<A HREF");
    }
    
    boolean isFolder(final String s) {
        return s.startsWith("<H3");
    }
    
    boolean isFolderEnd(final String s) {
        return s.equals("</DL>");
    }
    
    boolean isItem(final String s) {
        return s.equals("<DT>");
    }
    
    boolean isSeparator(final String s) {
        return s.equals("<HR>");
    }
    
    public int next() throws IOException {
        final String next = this.t.next();
        if (next == null) {
            return -1;
        }
        if (this.isItem(next)) {
            final String next2 = this.t.next();
            if (this.isFolder(next2)) {
                return this.nextFolder(next2);
            }
            if (this.isBookmark(next2)) {
                return this.nextBookmark(next2);
            }
            error("ITEM: Unknown type: " + next2);
            return 0;
        }
        else {
            if (this.isFolderEnd(next)) {
                return this.nextFolderEnd(next);
            }
            if (this.isSeparator(next)) {
                return this.nextSeparator(next);
            }
            return this.nextUnknown(next);
        }
    }
    
    int nextBookmark(final String s) throws IOException {
        final int n = s.indexOf(34) + 1;
        this.link = s.substring(n, s.indexOf(34, n));
        this.name = this.t.next();
        if (this.name.equals("</A>")) {
            this.name = "";
        }
        else {
            final String next = this.t.next();
            if (!next.equals("</A>")) {
                error("ITEM: Expected </A>, got " + next + ", bookmark name=" + this.name);
            }
        }
        return this.type = 1;
    }
    
    int nextFolder(final String s) throws IOException {
        this.name = this.t.next();
        this.link = null;
        final String next = this.t.next();
        if (!next.equals("</H3>")) {
            error("FOLDER_END: expected </H3>, got " + next);
        }
        return this.type = 2;
    }
    
    int nextFolderEnd(final String s) {
        return this.type = 3;
    }
    
    int nextSeparator(final String s) {
        return this.type = 4;
    }
    
    int nextUnknown(final String s) {
        return this.type = 0;
    }
    
    static void print(final String s) {
        System.out.println(s);
    }
}
