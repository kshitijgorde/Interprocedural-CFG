// 
// Decompiled by Procyon v0.5.30
// 

final class rp_gt implements rp_dA
{
    public final void a(final rp_gh rp_gh, final String s) {
        try {
            final long long1 = Long.parseLong(s);
            if (rp_gh.a() == -1L) {
                rp_gh.a(long1);
            }
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Illegal cookie max-age attribute");
        }
    }
}
