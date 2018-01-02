import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ak implements KeyListener
{
    private au a;
    private aE a;
    private int b;
    public int a;
    
    public ak(final au a, final aE a2) {
        this.b = 0;
        this.a = 0;
        this.a = a;
        this.a = a2;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        this.a.a("Keypress: " + keyCode);
        if (keyCode == 114) {
            ++this.b;
            if (this.b >= 8) {
                this.b = 0;
                System.out.println("NESCAFE WARM-REBOOT ENABLED");
                this.a.a.forceDebugMode = true;
                this.a.a.stop();
                this.a.a.run();
                return;
            }
        }
        else {
            this.b = 0;
        }
        final boolean m = this.a.a.m;
        if (this.a.b()) {
            if (this.a.a.a(keyEvent)) {
                this.a.b();
                if (!this.a.b()) {
                    this.a.a(false);
                }
            }
            return;
        }
        if (this.a.a.f && keyCode == 119) {
            this.a.a.a((byte[])null);
            return;
        }
        if (!this.a.a.f) {
            if (this.a.a.a(keyCode)) {
                this.a = 0;
                return;
            }
            if (this.a.b.a(keyCode)) {
                this.a = 0;
                return;
            }
        }
        if (keyCode == 27 && (this.a.c() || this.a.a() != 8)) {
            if (this.a.a.q) {
                this.a.a("Menu Access Disabled...", true);
                return;
            }
            this.a.a(new an(1, this.a));
        }
        else if (keyCode == 8) {
            if (this.a.a.f) {
                this.a.a("Time Shift Buffer disabled whilst playing a movie.", true);
                return;
            }
            if (this.a.a.e) {
                this.a.a("Time Shift Buffer disabled whilst recording a movie.", true);
                return;
            }
            if (this.a.a.n || !this.a.a.k) {
                this.a.a("Time Shift Buffer disabled...", true);
                return;
            }
            if (this.a.a.a.a < 5) {
                this.a.b("Time Shift Buffer empty, please wait " + (5 - this.a.a.a.a) * this.a.a.j + " seconds...");
                return;
            }
            this.a.a(new an(18, this.a, 0, true));
            this.a.a(true);
        }
        else {
            if (keyCode == 80 && this.a.a()) {
                final boolean b;
                if (b = !this.a.a.b) {
                    this.a.a("Game paused...", 86400, 16);
                }
                else {
                    this.a.a("Game resumed...", 6, 16);
                }
                this.a.a.a(b);
                return;
            }
            if (keyCode == 520) {
                if (this.a.a.a != null) {
                    this.a.a.a.b = !this.a.a.a.b;
                    this.a.b("Special Game Info for " + this.a.a.a.a + " : " + (this.a.a.a.b ? "Enabled" : "Disabled"));
                    return;
                }
                this.a.a("Special Game Info: None Available", true);
            }
            else {
                if (keyCode == 119 && !this.a.a.m) {
                    if (!this.a.a.canAppletSaveAnimGif() && !this.a.a.canAppletSaveState()) {
                        this.a.a("NESCafe prevented from saving Movies", true);
                        return;
                    }
                    if (!this.a.a.e) {
                        this.a.a("NESCafe prevented from saving Movies", true);
                        return;
                    }
                    if (this.a.a.f) {
                        this.a.a.a((byte[])null);
                        return;
                    }
                    if (!this.a.a.e) {
                        this.a.a(new an(22, this.a));
                        this.a.a(true);
                        return;
                    }
                    this.a.b("Finished Recording Movie...");
                    this.a.a.b();
                }
                else {
                    if (keyCode == 44) {
                        this.a.b("You are now controlling Player 1...");
                        this.a.a.d = 1;
                        return;
                    }
                    if (keyCode == 46) {
                        this.a.b("You are now controlling Player 2...");
                        this.a.a.d = 2;
                        return;
                    }
                    if (keyCode == 71) {
                        if (this.a.a.p) {
                            this.a.a("Game Genie changes are currently disabled.", true);
                            return;
                        }
                        if (this.a.a.f) {
                            this.a.a("Cannot change Game Genie settings when playing a movie.", true);
                            return;
                        }
                        if (this.a.a.e) {
                            this.a.a("Cannot change Game Genie settings when recording a movie.", true);
                            return;
                        }
                        this.a.a(new an(9, this.a, 0, true));
                        this.a.a(true);
                        return;
                    }
                    else {
                        if (keyCode == 73) {
                            this.a.a(new an(15, this.a));
                            this.a.a(true);
                            return;
                        }
                        if (keyCode == 81 && this.a.a() && !m) {
                            if (!this.a.a.f) {
                                this.a.a("Reseting has been disabled...", true);
                                return;
                            }
                            if (this.a.a.f) {
                                this.a.a("Cannot reset whilst playing a movie.", true);
                                return;
                            }
                            if (this.a.a.e) {
                                this.a.a("Cannot reset whilst recording a movie.", true);
                                return;
                            }
                            if (this.a.a.a.d) {
                                this.a.a(new an(5, this.a));
                                this.a.a(true);
                                return;
                            }
                        }
                        else if (keyCode == 82 && this.a.a() && !m) {
                            if (!this.a.a.f) {
                                this.a.a("Reseting has been disabled...", true);
                                return;
                            }
                            if (this.a.a.f) {
                                this.a.a("Cannot reset whilst playing a movie.", true);
                                return;
                            }
                            if (this.a.a.e) {
                                this.a.a("Cannot reset whilst recording a movie.", true);
                                return;
                            }
                            this.a.a(new an(4, this.a));
                            this.a.a(true);
                            return;
                        }
                        else if (keyCode == 67 && !this.a.a.equals("")) {
                            if (this.a.a.f) {
                                this.a.a("Cannot save states whilst playing a movie.", true);
                                return;
                            }
                            if (this.a.a.e) {
                                this.a.a("Cannot save states whilst recording a movie.", true);
                                return;
                            }
                            if (this.a.b) {
                                this.a.a("Cannot save states for IPS Patched Games.", true);
                                return;
                            }
                            if (aK.a(this.a.a.getCodeBase().getHost()) && !this.a.a.canAppletSaveState()) {
                                this.a.a("You need to be logged in to save states", true);
                                return;
                            }
                            if (!this.a.a.canAppletSaveState()) {
                                this.a.a("NESCafe prevented from saving States", true);
                                return;
                            }
                            this.a.a(new an(2, this.a, 0, true));
                            this.a.a(true);
                            return;
                        }
                    }
                }
                if (keyCode == 84) {
                    this.a.a(new an(24, this.a));
                    this.a.a(true);
                    return;
                }
                if (keyCode == 65) {
                    if (this.a.a.m) {
                        this.a.a("NESCafe cannot save images when running in Time Trial mode", true);
                        return;
                    }
                    if (aK.a(this.a.a.getCodeBase().getHost()) && !this.a.a.canAppletSaveState()) {
                        this.a.a("You need to be logged in to save screenshots", true);
                        return;
                    }
                    if (!this.a.a.canAppletSaveShot()) {
                        this.a.a("NESCafe prevented from saving Images", true);
                        return;
                    }
                    this.a.a(new an(6, this.a));
                    this.a.a(true);
                }
                else if (keyCode == 76 && !this.a.a.equals("") && !m) {
                    if (this.a.a.f) {
                        this.a.a("Cannot Rollback whilst playing a movie.", true);
                        return;
                    }
                    if (this.a.a.e) {
                        this.a.a("Cannot Rollback whilst recording a movie.", true);
                        return;
                    }
                    if (this.a.b) {
                        this.a.a("Option unavailable on IPS Patched Games.", true);
                        return;
                    }
                    if (aK.a(this.a.a.getCodeBase().getHost()) && !this.a.a.canAppletSaveState()) {
                        this.a.a("You need to be logged in to load your states", true);
                        return;
                    }
                    if (!this.a.a.canAppletLoadState()) {
                        return;
                    }
                    this.a.a(new an(3, this.a, 0, true));
                    this.a.a(true);
                }
                else if (keyCode == 83) {
                    if (this.a.a == null) {
                        return;
                    }
                    final boolean g;
                    if (g = this.a.a.g) {
                        this.a.b("Sound has been disabled...");
                    }
                    else {
                        this.a.b("Sound has been enabled...");
                    }
                    this.a.a.g = !g;
                }
                else {
                    ++this.a;
                    if (this.a > 3) {
                        this.a.b("Press ESC to enter menu and configure the controls...");
                    }
                }
            }
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        this.a.a.b(keyCode);
        this.a.b.b(keyCode);
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
}
