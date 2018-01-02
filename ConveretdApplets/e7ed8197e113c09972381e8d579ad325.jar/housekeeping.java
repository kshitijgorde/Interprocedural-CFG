// 
// Decompiled by Procyon v0.5.30
// 

class housekeeping extends Thread
{
    private groupboard gb;
    private int count;
    
    housekeeping(final groupboard gb) {
        this.gb = gb;
        this.count = 0;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
            if (this.gb.need_to_print) {
                this.gb.need_to_print = false;
                this.gb.actual_print();
            }
            if (null != this.gb.gui && null != this.gb.gui.draw_data) {
                final draw_panel draw_data = this.gb.gui.draw_data;
                if (null != draw_data.last_name && System.currentTimeMillis() - draw_data.last_name_time > 1000L) {
                    draw_data.remove_display_user();
                }
            }
            if (null != this.gb.games) {
                ++this.count;
                if (this.count <= 1000) {
                    continue;
                }
                this.gb.games.list_games();
                this.count = 0;
            }
        }
    }
}
