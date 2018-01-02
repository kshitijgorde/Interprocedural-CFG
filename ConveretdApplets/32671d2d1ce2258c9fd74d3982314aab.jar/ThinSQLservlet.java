import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.DriverManager;
import sun.misc.BASE64Decoder;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ThinSQLservlet extends HttpServlet
{
    public boolean DEBUG;
    
    public void init(final ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }
    
    public void destroy() {
    }
    
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String s = "";
            final Vector<String[]> vector = new Vector<String[]>();
            final Vector<Vector<String>> vector2 = new Vector<Vector<String>>();
            final ObjectInputStream objectInputStream = new ObjectInputStream((InputStream)((ServletRequest)httpServletRequest).getInputStream());
            final String[] array = (String[])objectInputStream.readObject();
            objectInputStream.close();
            final BASE64Decoder base64Decoder = new BASE64Decoder();
            final String s2 = new String(base64Decoder.decodeBuffer(array[0]));
            final String s3 = new String(base64Decoder.decodeBuffer(array[1]));
            final String s4 = new String(base64Decoder.decodeBuffer(array[2]));
            final String s5 = new String(base64Decoder.decodeBuffer(array[3]));
            final String s6 = new String(base64Decoder.decodeBuffer(array[4]));
            int n = 0;
            Statement statement = null;
            Connection connection = null;
            try {
                Class.forName(s2).newInstance();
                connection = DriverManager.getConnection(s3, s4, s5);
                connection.getMetaData();
                statement = connection.createStatement();
                statement.execute(s6);
                final int updateCount = statement.getUpdateCount();
                if (updateCount > 0) {
                    s = "Rows affected: " + updateCount;
                }
                else if (updateCount == 0) {
                    s = "Error, no rows affected";
                }
                else {
                    final ResultSet resultSet = statement.getResultSet();
                    final ResultSetMetaData metaData = resultSet.getMetaData();
                    final int columnCount = metaData.getColumnCount();
                    final String[] array2 = new String[columnCount];
                    final int[] array3 = new int[columnCount];
                    final int[] array4 = new int[columnCount];
                    final boolean[] array5 = new boolean[columnCount];
                    final boolean[] array6 = new boolean[columnCount];
                    final int[] array7 = new int[columnCount];
                    final boolean[] array8 = new boolean[columnCount];
                    final int[] array9 = new int[columnCount];
                    for (int i = 0; i < columnCount; ++i) {
                        array2[i] = metaData.getColumnLabel(i + 1);
                        array3[i] = metaData.getColumnDisplaySize(i + 1);
                        array4[i] = metaData.getColumnType(i + 1);
                        array5[i] = metaData.isAutoIncrement(i + 1);
                        array6[i] = metaData.isSigned(i + 1);
                        array7[i] = metaData.isNullable(i + 1);
                        array8[i] = metaData.isCurrency(i + 1);
                        array9[i] = metaData.getScale(i + 1);
                    }
                    vector.addElement(array2);
                    vector.addElement((String[])array3);
                    vector.addElement((String[])array4);
                    vector.addElement((String[])array5);
                    vector.addElement((String[])array6);
                    vector.addElement((String[])array7);
                    vector.addElement((String[])array8);
                    vector.addElement((String[])array9);
                    while (resultSet.next()) {
                        ++n;
                        final Vector<String> vector3 = new Vector<String>();
                        for (int j = 1; j <= columnCount; ++j) {
                            vector3.addElement(resultSet.getString(j));
                        }
                        vector2.addElement(vector3);
                    }
                    resultSet.close();
                    if (n == 0) {
                        s = "no rows selected";
                    }
                }
                statement.close();
                connection.close();
            }
            catch (SQLException ex) {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                s = String.valueOf(ex.toString()) + " SQL: " + s6;
            }
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream)((ServletResponse)httpServletResponse).getOutputStream());
            objectOutputStream.writeObject(s);
            objectOutputStream.writeObject(vector);
            objectOutputStream.writeObject(vector2);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
}
