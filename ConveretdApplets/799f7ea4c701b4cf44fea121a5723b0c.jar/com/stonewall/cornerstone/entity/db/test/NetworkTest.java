// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import com.stonewall.cornerstone.entity.Network;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class NetworkTest extends TestCase
{
    public void testFetchById() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Network network = new NetworkDbAccess().fetchById("24BPT3A2");
            assertNotNull((Object)network);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void _testInsert() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Network network = new Network();
            network.setName("cam");
            network.setParentId("175");
            network.insert();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void _testUpdate() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Network network = new NetworkDbAccess().fetchById("175");
            network.setName("cam2");
            network.update();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void _testDelete() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Network network = new NetworkDbAccess().fetchById("338");
            network.delete();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
