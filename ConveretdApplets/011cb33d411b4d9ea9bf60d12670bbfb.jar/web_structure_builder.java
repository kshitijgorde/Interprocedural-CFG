import java.util.StringTokenizer;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class web_structure_builder
{
    public Vector web_list;
    public Vector web_tree;
    public int max_level;
    boolean continu;
    static final String seperator_string = "%";
    static final String level_string = "#";
    static final String dont_publish_string = "|";
    
    web_structure_builder(final StringTokenizer stringTokenizer) {
        this.continu = true;
        this.web_tree = new Vector();
        this.web_list = new Vector();
        this.max_level = 0;
        this.build_web_structures(this.web_tree, 0, stringTokenizer.nextToken(), stringTokenizer);
    }
    
    String build_web_structures(final Vector vector, final int n, String s, final StringTokenizer stringTokenizer) {
        int n2 = 0;
        this.record_max_level(n);
        while (this.continu) {
            final int find_level = this.find_level(s);
            if (find_level == n) {
                ++n2;
                this.add_entry(vector, find_level, s.substring(s.lastIndexOf("#") + 1));
                if (!stringTokenizer.hasMoreTokens()) {
                    this.continu = false;
                    return null;
                }
                s = stringTokenizer.nextToken();
            }
            else {
                if (find_level <= n) {
                    return s;
                }
                if (n2 == 0) {
                    System.out.println("heirarchy file format error");
                    return null;
                }
                final tree_entry tree_entry = vector.elementAt(n2 - 1);
                tree_entry.children = new Vector();
                s = this.build_web_structures(tree_entry.children, n + 1, s, stringTokenizer);
            }
        }
        return null;
    }
    
    void record_max_level(final int max_level) {
        if (max_level > this.max_level) {
            this.max_level = max_level;
        }
    }
    
    int find_level(final String s) {
        int n = 0;
        String string = "#";
        for (int i = 0; i < s.length(); ++i) {
            if (!s.startsWith(string)) {
                return n;
            }
            string += "#";
            ++n;
        }
        return 0;
    }
    
    void add_entry(final Vector vector, final int n, String substring) {
        boolean b = true;
        if (substring.startsWith("|")) {
            b = false;
            substring = substring.substring(1);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(substring, "%");
        if (stringTokenizer.countTokens() > 1) {
            final tree_entry tree_entry = new tree_entry(stringTokenizer.nextToken(), stringTokenizer.nextToken(), n, b);
            vector.addElement(tree_entry);
            this.web_list.addElement(tree_entry);
            return;
        }
        System.out.println("incorrect format");
    }
    
    void restrict_tree(final Vector vector, final int n) {
        for (int i = 0; i < vector.size(); ++i) {
            final tree_entry tree_entry = vector.elementAt(i);
            if (tree_entry.level > n) {
                tree_entry.in_use = false;
            }
            else {
                tree_entry.in_use = true;
                this.web_list.addElement(tree_entry);
            }
            if (tree_entry.children != null) {
                this.restrict_tree(tree_entry.children, n);
            }
        }
    }
}
