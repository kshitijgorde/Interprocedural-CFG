// 
// Decompiled by Procyon v0.5.30
// 

public class public
{
    static float Oa;
    static float Pa;
    static float Qa;
    static float Ra;
    static float Sa;
    
    public static float f(final float pa, final float sa, final float ra) {
        public.Pa = pa;
        public.Sa = sa;
        public.Ra = ra;
        return public.Pa * public.Sa * public.Ra;
    }
    
    public static float a(final float oa, final float sa, final float ra) {
        public.Oa = oa;
        public.Sa = sa;
        public.Ra = ra;
        return public.Oa / (public.Sa * public.Ra);
    }
    
    public static float g(final float oa, final float pa, final float sa) {
        public.Oa = oa;
        public.Pa = pa;
        public.Sa = sa;
        return public.Oa / (public.Sa * public.Pa);
    }
}
