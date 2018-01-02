// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc.adapter;

import java.io.InputStream;
import org.apache.activemq.util.ByteArrayOutputStream;
import org.apache.activemq.store.jdbc.TransactionContext;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.SQLException;
import javax.jms.JMSException;
import java.sql.Connection;

public class BlobJDBCAdapter extends DefaultJDBCAdapter
{
    public void doAddMessage(final Connection c, final long seq, final String messageID, final String destinationName, final byte[] data) throws SQLException, JMSException {
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            s = c.prepareStatement(this.statements.getAddMessageStatement());
            s.setLong(1, seq);
            s.setString(2, destinationName);
            s.setString(3, messageID);
            s.setString(4, " ");
            if (s.executeUpdate() != 1) {
                throw new JMSException("Failed to broker message: " + messageID + " in container.");
            }
            s.close();
            s = c.prepareStatement(this.statements.getFindMessageStatement());
            s.setLong(1, seq);
            rs = s.executeQuery();
            if (!rs.next()) {
                throw new JMSException("Failed to broker message: " + messageID + " in container.");
            }
            final Blob blob = rs.getBlob(1);
            final OutputStream stream = blob.setBinaryStream(data.length);
            stream.write(data);
            stream.close();
            s.close();
            s = c.prepareStatement(this.statements.getUpdateMessageStatement());
            s.setBlob(1, blob);
            s.setLong(2, seq);
        }
        catch (IOException e) {
            throw (SQLException)new SQLException("BLOB could not be updated: " + e).initCause(e);
        }
        finally {
            try {
                rs.close();
            }
            catch (Throwable t) {}
            try {
                s.close();
            }
            catch (Throwable t2) {}
        }
    }
    
    public byte[] doGetMessage(final TransactionContext c, final long seq) throws SQLException {
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindMessageStatement());
            s.setLong(1, seq);
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            final Blob blob = rs.getBlob(1);
            final InputStream is = blob.getBinaryStream();
            final ByteArrayOutputStream os = new ByteArrayOutputStream((int)blob.length());
            int ch;
            while ((ch = is.read()) >= 0) {
                os.write(ch);
            }
            is.close();
            os.close();
            return os.toByteArray();
        }
        catch (IOException e) {
            throw (SQLException)new SQLException("BLOB could not be updated: " + e).initCause(e);
        }
        finally {
            try {
                rs.close();
            }
            catch (Throwable t) {}
            try {
                s.close();
            }
            catch (Throwable t2) {}
        }
    }
}
