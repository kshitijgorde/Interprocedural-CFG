// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.DBModel;

public final class a
{
    private final String a;
    private final String b;
    private final long c;
    private final String d;
    private final String e;
    private final String f;
    
    public static String a(final int n) {
        if (n == 1001) {
            return "Assignment Changed";
        }
        if (n == 1002) {
            return "Timed Out";
        }
        if (n == 1003) {
            return "Unassigned";
        }
        if (n == 1004) {
            return "Predictable";
        }
        if (n == 1005) {
            return "Unpredictable";
        }
        if (n == 1006) {
            return "Adherence";
        }
        if (n == 1007) {
            return "Silent Alarm";
        }
        return null;
    }
    
    public a(final String a, final String b, final long c, final String d, final String e, final String s) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = null;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("source=" + this.a + ",");
        sb.append("busTag=" + this.b + ",");
        sb.append("eventTime=" + this.c + ",");
        final String d;
        sb.append("getEventType=" + ((d = this.d).equals("jobchange") ? 1001 : (d.equals("timeout") ? 1002 : (d.equals("offjob") ? 1003 : (d.equals("predictable") ? 1004 : (d.equals("unpredictable") ? 1005 : (d.equals("adherence") ? 1006 : (d.equals("silentalarm") ? 1007 : 1000))))))) + ",");
        sb.append("job=" + this.e + ",");
        sb.append("message=" + this.f + ",");
        return sb.toString();
    }
}
