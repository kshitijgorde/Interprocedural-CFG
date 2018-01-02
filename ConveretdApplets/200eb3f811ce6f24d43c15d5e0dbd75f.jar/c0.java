// 
// Decompiled by Procyon v0.5.30
// 

public class c0 implements cx, ay
{
    public void a(final cw cw, final c1 c1) {
        if (ay.a.i()) {
            ay.a.g("SessionStatusCallback:received update on session_status");
        }
        if (!cw.g()) {
            if (ay.a.g()) {
                ay.a.d(cw.i.as() + " SessionStatusCallback:received empty object");
            }
            return;
        }
        a(cw.i, cw);
    }
    
    public static void a(final ax ax, final a3 a3) {
        final String g = ax.aj().g("SESSION_STATUS");
        boolean b = false;
        if (!a3.f()) {
            ax.a(8, a3.t());
            if (ay.a.g()) {
                ay.a.d("SessionStatusCallback:set session state to STATUS_SERVER_CLOSE because of invalid status object: " + a3.t());
            }
            b = true;
        }
        else if (a3.b(g) >= 0 && (a3.b(0, g) == null || a3.b(0, g) != 0)) {
            ax.a(8, a3.e(ax.aj().g("SESSION_STATUS_MSG")));
            if (ay.a.g()) {
                ay.a.d("SessionStatusCallback:set session state to STATUS_SERVER_CLOSE because of notifying session_status update");
            }
            b = true;
        }
        if (b) {
            final Boolean c;
            if ((c = ax.aj().c("OBSERVE_SESSION_STATUS")) != null && c) {
                if (ay.a.g()) {
                    ay.a.d(ax.as() + " closing session because of STATUS_SERVER_CLOSE and OBSERVE_SESSION_STATUS");
                }
                ax.a(false);
            }
        }
        else if (ay.a.i()) {
            ay.a.g("SessionStatusCallback:session status is OK");
        }
    }
}
