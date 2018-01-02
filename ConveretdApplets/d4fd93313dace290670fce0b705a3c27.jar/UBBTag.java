// 
// Decompiled by Procyon v0.5.30
// 

class UBBTag
{
    public String tag;
    public String[][] attribute;
    public String text;
    public static final int NAME = 0;
    public static final int VALUE = 1;
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(1024);
        sb.append(">>>>>\nTag:   ");
        sb.append(this.tag);
        for (int i = 0; i < this.attribute.length; ++i) {
            sb.append("\nAttr:  ");
            sb.append(this.attribute[i][0]);
            sb.append("=");
            sb.append(this.attribute[i][1]);
        }
        sb.append("\nText:  ");
        sb.append(this.text);
        sb.append("\n<<<<<");
        return sb.toString();
    }
}
