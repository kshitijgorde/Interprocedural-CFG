// 
// Decompiled by Procyon v0.5.30
// 

class ZMachine3 extends ZMachine
{
    public ZMachine3(final ZScreen screen, final ZStatus status_line, final byte[] memory_image) {
        super(screen, status_line, memory_image);
        super.header = new ZHeader3(memory_image);
        super.objects = new ZObjectTree3(this);
        super.zd = new ZDictionary3(this);
        super.globals = super.header.global_table();
        (super.window = new ZWindow[2])[0] = new ZWindow(screen);
        (super.window[1] = new ZWindow(screen)).set_transcripting(false);
        super.current_window = super.window[0];
        super.zi = new ZInstruction(this);
    }
    
    void update_status_line() {
        final ZHeader3 header = (ZHeader3)super.header;
        super.status_redirect = true;
        super.status_location = "";
        this.print_string(super.objects.short_name_addr(this.get_variable((short)16)));
        super.status_redirect = false;
        if (header.time_game()) {
            super.status_line.update_time_line(super.status_location, this.get_variable((short)17), this.get_variable((short)18));
        }
        else {
            super.status_line.update_score_line(super.status_location, this.get_variable((short)17), this.get_variable((short)18));
        }
        super.status_location = null;
    }
    
    int string_address(final short addr) {
        return (addr & 0xFFFF) << 1;
    }
    
    int routine_address(final short addr) {
        return (addr & 0xFFFF) << 1;
    }
    
    void restart() {
        super.restart();
        super.window[0].moveto(0, 0);
        super.window[1].moveto(0, 0);
        super.window[0].resize(super.screen.getchars(), super.screen.getlines());
        super.window[1].resize(0, 0);
        super.window[0].movecursor(0, super.window[0].getHeight() - 1);
    }
    
    void set_header_flags() {
        final ZHeader3 header = (ZHeader3)super.header;
        super.set_header_flags();
        header.set_status_unavailable(false);
        header.set_splitting_available(true);
        header.set_variable_default(false);
    }
}
