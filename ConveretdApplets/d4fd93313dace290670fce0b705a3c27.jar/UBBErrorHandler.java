// 
// Decompiled by Procyon v0.5.30
// 

public class UBBErrorHandler
{
    private String idText;
    private boolean doWarn;
    public static final int ERROR = 0;
    public static final int WARNING = 1;
    public static final int LOG = 2;
    
    public UBBErrorHandler(final String idText) {
        this.idText = idText;
    }
    
    public void reportWarnings(final boolean doWarn) {
        this.doWarn = doWarn;
    }
    
    public void notify(final String s, final int n, final String s2, final Exception ex) {
        if (s2 != null && (n != 1 || (n == 1 && this.doWarn))) {
            final StringBuffer sb = new StringBuffer(s2.length() + 256);
            sb.append('<');
            if (this.idText != null) {
                sb.append(this.idText);
            }
            if (s != null) {
                if (this.idText != null) {
                    sb.append(' ');
                }
                sb.append(s);
            }
            sb.append("> ");
            if (n != 2) {
                if (n == 1) {
                    sb.append("Warning! ");
                }
                else {
                    sb.append("Error - ");
                }
            }
            sb.append(s2);
            if (ex != null) {
                sb.append(" because of ");
                sb.append(ex);
            }
            System.out.println(sb.toString());
        }
    }
}
