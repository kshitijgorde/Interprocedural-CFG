import symantec.itools.awt.TreeNode;

// 
// Decompiled by Procyon v0.5.30
// 

public class tocParser
{
    String checkLine;
    int level;
    TreeNode thisNode;
    
    public tocParser(final String checkLine) {
        this.checkLine = checkLine;
        String s;
        if (this.checkLine.charAt(0) == '\t') {
            int n = 0;
            char c = this.checkLine.charAt(0);
            this.level = 0;
            while (c == '\t') {
                ++this.level;
                ++n;
                c = this.checkLine.charAt(n);
            }
            s = this.checkLine.substring(n);
        }
        else {
            this.level = 0;
            s = this.checkLine;
        }
        if (s.startsWith("<")) {
            final int index = s.indexOf("\"");
            final String substring = s.substring(index + 1, s.indexOf("\"", index + 1));
            final int index2 = s.indexOf(">");
            (this.thisNode = new TreeNode(s.substring(index2 + 1, s.indexOf("<", index2 + 1)))).setDataObject((Object)substring);
            return;
        }
        if (s.length() > 1) {
            (this.thisNode = new TreeNode(s)).setDataObject((Object)null);
        }
    }
    
    public TreeNode getNode() {
        return this.thisNode;
    }
    
    public int getLevel() {
        return this.level;
    }
}
