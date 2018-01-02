// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import com.stonewall.cornerstone.entity.SiteTree;
import com.stonewall.cornerstone.entity.Site;
import com.stonewall.cornerstone.entity.db.SiteDbAccess;
import com.stonewall.cornerstone.component.Bootstrap;
import junit.framework.TestCase;

public class SiteTest extends TestCase
{
    public void testFetchById() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Site site = new SiteDbAccess().fetchById("123");
            assertNotNull((Object)site);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testFetchFullSiteTree() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final SiteTree tree = new SiteTree();
            assertNotNull((Object)tree.getRootNode().getId().equals("0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testInsert() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Site child = new Site();
            child.setName("cam");
            child.setParentId("122");
            child.insert();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testUpdate() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Site site = new SiteDbAccess().fetchById("122");
            site.setName("cam2");
            site.update();
            final Site newSite = new SiteDbAccess().fetchById("122");
            assertTrue(newSite.getName().equals("cam2"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testDelete() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final Site site = new SiteDbAccess().fetchById("126");
            site.delete();
            final Site newSite = new SiteDbAccess().fetchById("126");
            assertNull((Object)newSite);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
