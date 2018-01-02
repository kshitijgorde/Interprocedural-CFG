// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ev implements Runnable
{
    private /* synthetic */ rp_bZ a;
    
    rp_ev(final rp_bZ a) {
        this.a = a;
    }
    
    public final void run() {
        while (true) {
            try {
                while (true) {
                    if (this.a.a.size() > 0) {
                        final rp_cK rp_cK;
                        (rp_cK = this.a.a.remove(0)).c = true;
                        try {
                            if (rp_cK.a) {
                                rp_cK.a = this.a.a(rp_cK.a, rp_cK.b, rp_cK.c, rp_cK.d);
                                rp_cK.c = !rp_cK.a.a();
                            }
                            else {
                                rp_cK.a = this.a.a(rp_cK.a);
                            }
                            rp_cK.c = false;
                        }
                        catch (Exception a) {
                            rp_cK.c = true;
                            rp_cK.a = a;
                        }
                        finally {
                            rp_cK.b = true;
                            if (!rp_cK.a && rp_cK.a == null) {
                                rp_cK.c = true;
                            }
                        }
                    }
                    else {
                        try {
                            Thread.sleep(50L);
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("Error in http request worker due to: " + ex.getMessage());
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
}
