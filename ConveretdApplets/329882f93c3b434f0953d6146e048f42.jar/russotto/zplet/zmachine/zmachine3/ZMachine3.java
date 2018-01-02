// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine3;

import russotto.zplet.zmachine.ZInstruction;
import russotto.zplet.screenmodel.ZWindow;
import russotto.zplet.screenmodel.ZStatus;
import russotto.zplet.screenmodel.ZScreen;
import russotto.zplet.zmachine.ZMachine;

public class ZMachine3 extends ZMachine
{
    public ZMachine3(final ZScreen zScreen, final ZStatus zStatus, final byte[] array) {
        super(zScreen, zStatus, array);
        super.header = new ZHeader3(array);
        super.objects = new ZObjectTree3(this);
        super.zd = new ZDictionary3(this);
        super.globals = super.header.global_table();
        (super.window = new ZWindow[2])[0] = new ZWindow(zScreen);
        (super.window[1] = new ZWindow(zScreen)).set_transcripting(false);
        super.current_window = super.window[0];
        super.zi = new ZInstruction(this);
    }
    
    public void update_status_line() {
        final ZHeader3 zHeader3 = (ZHeader3)super.header;
        super.status_redirect = true;
        super.status_location = "";
        this.print_string(super.objects.short_name_addr(this.get_variable((short)16)));
        super.status_redirect = false;
        if (zHeader3.time_game()) {
            super.status_line.update_time_line(super.status_location, this.get_variable((short)17), this.get_variable((short)18));
        }
        else {
            super.status_line.update_score_line(super.status_location, this.get_variable((short)17), this.get_variable((short)18));
        }
        super.status_location = null;
    }
    
    public int string_address(final short n) {
        return (n & 0xFFFF) << 1;
    }
    
    public int routine_address(final short n) {
        return (n & 0xFFFF) << 1;
    }
    
    public void restart() {
        super.restart();
        super.window[0].moveto(0, 0);
        super.window[1].moveto(0, 0);
        super.window[0].resize(super.screen.getchars(), super.screen.getlines());
        super.window[1].resize(0, 0);
        super.window[0].movecursor(0, super.window[0].getHeight() - 1);
    }
    
    public void set_header_flags() {
        final ZHeader3 zHeader3 = (ZHeader3)super.header;
        super.set_header_flags();
        zHeader3.set_status_unavailable(false);
        zHeader3.set_splitting_available(true);
        zHeader3.set_variable_default(false);
    }
}
