// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.esial.util.c;
import java.awt.Event;
import com.diginet.digichat.network.v;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Label;
import com.diginet.digichat.awt.ah;

public class Lists extends ah
{
    private Label lblUsers;
    private Label lblGames;
    private Checkbox chkBuddies;
    private Checkbox[] chkRooms;
    private Checkbox[] chkUsers;
    private Checkbox[] chkGames;
    private CheckboxGroup grpRooms;
    private CheckboxGroup grpUsers;
    private CheckboxGroup grpGames;
    private h hUser;
    private ac acParent;
    
    private int getIndex(final Checkbox[] array) {
        final Checkbox current = array[0].getCheckboxGroup().getCurrent();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == current) {
                return i;
            }
        }
        return -1;
    }
    
    public void b() {
        boolean b = false;
        if (this.chkBuddies.isEnabled() && this.hUser.p != this.chkBuddies.getState()) {
            this.hUser.p = this.chkBuddies.getState();
            b = true;
            if (this.hUser.y != null) {
                this.hUser.y.updateButtons();
            }
        }
        this.hUser.setLists(b, this.getIndex(this.chkRooms), this.lblUsers.isEnabled() ? this.getIndex(this.chkUsers) : this.hUser.nIMList, this.lblGames.isEnabled() ? this.getIndex(this.chkGames) : this.hUser.nGames);
    }
    
    public void c() {
        if (this.hUser.fBuddies) {
            this.chkBuddies.enable();
            this.chkBuddies.setState(this.hUser.p);
        }
        else {
            this.chkBuddies.disable();
            this.chkBuddies.setState(false);
        }
        this.grpRooms.setCurrent(this.chkRooms[this.hUser.nRooms]);
        if (v.a(this.hUser.lPlus, 21)) {
            this.lblUsers.enable();
            this.chkUsers[0].enable(this.hUser.fEmbed);
            for (int i = 1; i < this.chkUsers.length; ++i) {
                this.chkUsers[i].enable();
            }
            this.grpUsers.setCurrent(this.chkUsers[this.hUser.nIMList]);
        }
        else {
            this.lblUsers.disable();
            this.grpUsers.setCurrent(null);
            for (int j = 0; j < this.chkUsers.length; ++j) {
                this.chkUsers[j].disable();
                this.chkUsers[j].setState(false);
            }
        }
        if (v.a(this.hUser.lPlus, 22)) {
            this.lblGames.enable();
            this.chkGames[0].enable(this.hUser.fEmbed);
            for (int k = 1; k < this.chkGames.length; ++k) {
                this.chkGames[k].enable();
            }
            this.grpGames.setCurrent(this.chkGames[this.hUser.nGames]);
        }
        else {
            this.lblGames.disable();
            this.grpGames.setCurrent(null);
            for (int l = 0; l < this.chkGames.length; ++l) {
                this.chkGames[l].disable();
                this.chkGames[l].setState(false);
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.chkBuddies) {
            this.acParent.enableBuddies(this.chkBuddies.getState());
        }
        return super.handleEvent(event);
    }
    
    public Lists(final h hUser, final ac acParent) {
        super(com.esial.util.c.a("Lists"), hUser);
        this.chkRooms = new Checkbox[3];
        this.chkUsers = new Checkbox[5];
        this.chkGames = new Checkbox[5];
        this.hUser = hUser;
        this.acParent = acParent;
        this.chkBuddies = new Checkbox(com.esial.util.c.a("Enable Buddies Tab"));
        this.grpRooms = new CheckboxGroup();
        this.chkRooms[0] = new Checkbox(com.esial.util.c.a("As Tab"), this.grpRooms, false);
        this.chkRooms[1] = new Checkbox(com.esial.util.c.a("As Menu"), this.grpRooms, false);
        this.chkRooms[2] = new Checkbox(com.esial.util.c.a("Not at all"), this.grpRooms, false);
        this.grpUsers = new CheckboxGroup();
        this.chkUsers[0] = new Checkbox(com.esial.util.c.a("Embedded"), this.grpUsers, false);
        this.chkUsers[1] = new Checkbox(com.esial.util.c.a("Separate"), this.grpUsers, false);
        this.chkUsers[2] = new Checkbox(com.esial.util.c.a("As Tab"), this.grpUsers, false);
        this.chkUsers[3] = new Checkbox(com.esial.util.c.a("In Users List"), this.grpUsers, false);
        this.chkUsers[4] = new Checkbox(com.esial.util.c.a("Not at all"), this.grpUsers, false);
        this.grpGames = new CheckboxGroup();
        this.chkGames[0] = new Checkbox(com.esial.util.c.a("Embedded"), this.grpGames, false);
        this.chkGames[1] = new Checkbox(com.esial.util.c.a("Separate"), this.grpGames, false);
        this.chkGames[2] = new Checkbox(com.esial.util.c.a("As Tab"), this.grpGames, false);
        this.chkGames[3] = new Checkbox(com.esial.util.c.a("As Menu"), this.grpGames, false);
        this.chkGames[4] = new Checkbox(com.esial.util.c.a("Not at all"), this.grpGames, false);
        if (!hUser.a9) {
            this.a("", this.chkBuddies);
        }
        this.a(com.esial.util.c.a("Rooms List"), this.chkRooms[0]);
        for (int i = 1; i < this.chkRooms.length; ++i) {
            this.a("", this.chkRooms[i]);
        }
        this.lblUsers = this.a(com.esial.util.c.a("Users List of 1:1"), this.chkUsers[0]);
        for (int j = 1; j < this.chkUsers.length; ++j) {
            this.a("", this.chkUsers[j]);
        }
        this.lblGames = this.a(com.esial.util.c.a("Games List"), this.chkGames[0]);
        for (int k = 1; k < this.chkGames.length; ++k) {
            this.a("", this.chkGames[k]);
        }
    }
}
