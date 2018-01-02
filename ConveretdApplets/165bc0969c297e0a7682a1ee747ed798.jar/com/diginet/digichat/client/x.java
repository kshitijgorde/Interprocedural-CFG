// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.Game;
import java.awt.Container;
import java.awt.Frame;

public interface x
{
    void a(final bf p0);
    
    void a();
    
    Frame b();
    
    Container c();
    
    boolean isVisible();
    
    void d();
    
    boolean a(final bd p0);
    
    void b(final bo p0);
    
    void setVisible(final boolean p0);
    
    void a(final boolean p0);
    
    void setState(final int p0);
    
    void setRoom(final bo p0);
    
    void setGame(final Game p0);
    
    void updateList(final bd p0, final boolean p1);
    
    void updateOpt();
    
    void setPlus();
    
    void setAppear();
    
    void updateList();
    
    void updateMess();
    
    void updateTabs();
    
    void updateRooms();
    
    void updateGames();
    
    void updateButtons();
    
    void updateLocations();
    
    void closeBuddies();
    
    void setScrolls();
    
    void deleteRoom(final bo p0);
    
    void deleteGame(final Game p0);
}
