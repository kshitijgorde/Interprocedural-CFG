// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import com.stonewall.cornerstone.db.mysql.ConnectionFactory;

public final class DbSessionFactory
{
    public static DbSession getConnection() throws DbException {
        DbSession result = Transaction.getSession();
        if (result == null) {
            result = ConnectionFactory.getInstance().getConnection();
        }
        return result;
    }
}
