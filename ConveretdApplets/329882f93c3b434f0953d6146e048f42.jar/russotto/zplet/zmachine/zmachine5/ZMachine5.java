// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine5;

import russotto.zplet.screenmodel.ZWindow;
import russotto.zplet.screenmodel.ZStatus;
import russotto.zplet.screenmodel.ZScreen;
import russotto.zplet.zmachine.state.ZState;
import russotto.zplet.zmachine.ZMachine;

public class ZMachine5 extends ZMachine
{
    public short argcount;
    ZState undo_state;
    
    public ZMachine5(final ZScreen zScreen, final byte[] array) {
        super(zScreen, null, array);
        this.undo_state = null;
        super.header = new ZHeader5(array);
        super.objects = new ZObjectTree5(this);
        super.zd = new ZDictionary5(this);
        super.globals = super.header.global_table();
        (super.window = new ZWindow[2])[0] = new ZWindow(zScreen);
        (super.window[1] = new ZWindow(zScreen)).setscroll(false);
        super.window[1].setwrapmode(false);
        super.window[1].set_transcripting(false);
        super.current_window = super.window[0];
        super.zi = new ZInstruction5(this);
        this.argcount = 0;
    }
    
    public void update_status_line() {
    }
    
    public int string_address(final short n) {
        return (n & 0xFFFF) << 2;
    }
    
    public int routine_address(final short n) {
        return (n & 0xFFFF) << 2;
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
        final ZHeader5 zHeader5 = (ZHeader5)super.header;
        super.set_header_flags();
        zHeader5.set_revision(0, 0);
        zHeader5.set_colors_available(true);
        zHeader5.set_bold_available(true);
        zHeader5.set_italic_available(true);
        zHeader5.set_fixed_font_available(true);
        zHeader5.set_timed_input_available(false);
        zHeader5.set_graphics_font_available(false);
        zHeader5.set_undo_available(true);
        zHeader5.set_mouse_available(false);
        zHeader5.set_sound_available(false);
        zHeader5.set_interpreter_number(6);
        zHeader5.set_interpreter_version(74);
        zHeader5.set_screen_height_lines(super.screen.getlines());
        zHeader5.set_screen_width_characters(super.screen.getchars());
        zHeader5.set_screen_height_units(super.screen.getlines());
        zHeader5.set_screen_width_units(super.screen.getchars());
        zHeader5.set_font_height_units(1);
        zHeader5.set_font_width_units(1);
        zHeader5.set_default_background_color(super.screen.getZBackground());
        zHeader5.set_default_foreground_color(super.screen.getZForeground());
    }
    
    int restore_undo() {
        if (this.undo_state != null) {
            this.undo_state.header.set_transcripting(super.header.transcripting());
            this.undo_state.restore_saved();
            this.set_header_flags();
            return 2;
        }
        return 0;
    }
    
    int save_undo() {
        if (this.undo_state == null) {
            this.undo_state = new ZState(this);
        }
        this.undo_state.save_current();
        return 1;
    }
}
