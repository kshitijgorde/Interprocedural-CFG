// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.util.Stack;

class MudStack extends Stack
{
    Attribute curr_colour;
    static DisplayInterface curr_display;
    DisplayInterface def_display;
    int depth;
    static final boolean debug = false;
    static final boolean sdebug = false;
    int insnoop;
    boolean shieldok;
    
    public Attribute getColour() {
        return this.curr_colour;
    }
    
    public Object pop() {
        if (this.empty()) {
            System.err.println("Mudstack, pop called while empty,restoring defaults.");
            this.curr_colour = DoCodes.getAttribute(0);
            MudStack.curr_display = this.def_display;
            return null;
        }
        final Object ob = super.pop();
        if (ob instanceof CatchHolder) {
            super.push(ob);
            return ob;
        }
        if (ob instanceof Attribute) {
            this.curr_colour = (Attribute)ob;
        }
        else if (!(ob instanceof CatchHolder)) {
            if (ob instanceof SnoopHolder) {
                --this.insnoop;
            }
            else if (ob instanceof DisplayInterface) {
                if (MudStack.curr_display instanceof HealthPanel) {
                    if (this.shieldok) {
                        ((HealthPanel)MudStack.curr_display).update();
                    }
                }
                else if (MudStack.curr_display instanceof StamCatcher) {
                    if (this.shieldok) {
                        ((StamCatcher)MudStack.curr_display).update();
                    }
                }
                else if (MudStack.curr_display instanceof StamMaxCatcher && this.shieldok) {
                    ((StamMaxCatcher)MudStack.curr_display).update();
                }
                MudStack.curr_display = (DisplayInterface)ob;
            }
        }
        if (!this.empty() && this.peek() instanceof autopopper) {
            final Object nob = super.pop();
            if (nob instanceof shieldstopper) {
                this.shieldok = true;
            }
        }
        return ob;
    }
    
    public void dothrow() {
        while (!this.empty()) {
            final Object ob = super.pop();
            if (ob instanceof CatchHolder) {
                this.curr_colour = ((CatchHolder)ob).getColour();
                MudStack.curr_display = ((CatchHolder)ob).getDisplay();
                return;
            }
        }
        System.err.println("Throw failed to find matching catch, restoring defaults.");
        this.curr_colour = DoCodes.getAttribute(0);
        MudStack.curr_display = this.def_display;
    }
    
    MudStack(final DisplayInterface d) {
        this.insnoop = 0;
        this.shieldok = true;
        this.depth = 0;
        this.curr_colour = Attribute._defaultat;
        MudStack.curr_display = d;
        this.def_display = d;
    }
    
    public void mudPush(final Attribute a) {
        this.push(this.curr_colour);
        this.curr_colour = a;
    }
    
    public void mudPush(int a) {
        if (a == 0) {
            while (!this.empty()) {
                super.pop();
            }
            this.curr_colour = DoCodes.getAttribute(0);
            MudStack.curr_display = this.def_display;
            return;
        }
        if (a < 1 || a > 209) {
            System.err.println("Passed invalid integer to mudpush: " + a);
            a = 0;
        }
        this.push(this.curr_colour);
        this.curr_colour = DoCodes.getAttribute(a);
    }
    
    public void mudPush(final DisplayInterface d) {
        this.push(MudStack.curr_display);
        MudStack.curr_display = d;
    }
    
    public void mudPush(final SnoopHolder sh) {
        ++this.insnoop;
        this.push(sh);
    }
    
    public void mudPush(final autopopper ap) {
        if (ap instanceof shieldstopper) {
            this.shieldok = false;
        }
        this.push(ap);
    }
    
    public void docatch() {
        final CatchHolder ch = new CatchHolder(this.curr_colour, MudStack.curr_display);
        this.push(ch);
    }
    
    public Object push(final Object o) {
        return super.push(o);
    }
}
