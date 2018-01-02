// 
// Decompiled by Procyon v0.5.30
// 

final class rp_gx implements rp_dA
{
    public final void a(final rp_gh rp_gh, final String s) {
        try {
            rp_gh.a(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Illegal cookie version attribute");
        }
    }
}
