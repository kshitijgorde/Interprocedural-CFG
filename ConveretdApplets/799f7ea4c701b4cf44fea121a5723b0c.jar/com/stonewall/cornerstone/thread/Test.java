// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.thread;

import java.lang.reflect.Method;
import com.stonewall.cornerstone.utility.Transaction;
import com.stonewall.cornerstone.security.User;
import junit.framework.TestCase;

public class Test extends TestCase
{
    public void testPool() {
        try {
            final ThreadPool pool = new ThreadPool("TEST", 50);
            final Method m = this.getClass().getDeclaredMethod("foo", Integer.TYPE);
            final ThreadDispatch dispatch = new ThreadDispatch(pool, m);
            User.setCurrent(new User("jortel"));
            final Transaction tr = new Transaction();
            tr.begin();
            for (int i = 0; i < 100; ++i) {
                System.out.println(String.valueOf(i) + "  SUBMIT");
                dispatch.submit(this, i);
                Thread.sleep(10L);
            }
            tr.commit();
            System.out.println(pool);
            Thread.currentThread().join();
        }
        catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
    
    void foo(final int i) {
        try {
            System.out.println(String.valueOf(i) + "  foo() running in thread" + Thread.currentThread());
            Thread.sleep(3000L);
        }
        catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
