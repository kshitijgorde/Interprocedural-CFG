// 
// Decompiled by Procyon v0.5.30
// 

class ad_changer extends Thread
{
    private advert_area ad;
    
    ad_changer(final advert_area ad) {
        this.ad = ad;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(15000L);
                    this.ad.cycle_advert();
                }
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
}
