// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseFixer
{
    public static void main(final String[] args) {
        Connection conn = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Logger.log("Fixing database");
        try {
            conn = DBHelper.getConnection();
            s = conn.prepareStatement("select * from tunequeries order by id");
            r = s.executeQuery();
            while (r.next()) {
                final int id = r.getInt("id");
                System.out.println("Fixing record: " + id);
                String query = r.getString("query");
                if (query.length() > 300) {
                    System.out.println("Truncaing...");
                    query = query.substring(0, 300);
                }
                if (r.getInt("correct") == -2) {
                    continue;
                }
                ABCMatch match = null;
                final int correct = r.getInt("correct");
                if (correct == -1) {
                    final ABCFinder finder = new ABCFinder();
                    finder.setSearchString(query);
                    finder.findFromIndex();
                    match = finder.getPq().peek();
                }
                if (correct >= 0) {
                    final ABCFinder finder = new ABCFinder();
                    finder.setSearchString(query);
                    finder.findFromIndex();
                    for (int i = 0; i < correct; ++i) {
                        finder.getPq().poll();
                    }
                    match = finder.getPq().peek();
                }
                final PreparedStatement us = conn.prepareStatement("update tunequeries set tunepalid = ?, client = ?, ed = ?, normalEd = ? where id = ?");
                us.setString(1, match.getTunepalid());
                us.setString(2, "tunepal.org");
                us.setFloat(3, match.getEditDistance());
                us.setFloat(4, match.getEditDistance() / query.length());
                us.setInt(5, id);
                us.execute();
                us.close();
            }
        }
        catch (Exception e) {
            Logger.log("Could not update the database ");
            e.printStackTrace();
        }
        DBHelper.safeClose(conn, s, r);
        System.out.println("Finished!");
    }
}
