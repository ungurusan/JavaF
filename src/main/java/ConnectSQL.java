import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alin on 10/13/2016.
 */
public class ConnectSQL {

    private Connection conn;
    private String sqlServer;

    public ConnectSQL(String sqlServer, String user, String pass, String database) throws SQLException, ClassNotFoundException {
        this.sqlServer = sqlServer;
        conn = DriverManager.getConnection("jdbc:sqlserver://" + sqlServer + ";user="+user + ";password=" + pass + ";database=" + database);
        System.out.println("Connected to: "+ sqlServer);
    }

    public List<List<String>> getQuery(String query) {

        Statement command;
        ResultSet answer;
        ResultSetMetaData metadata;
        List<List<String>> lista = new ArrayList<>();

        try {
            command = conn.createStatement();
            answer = command.executeQuery(query);
            metadata = answer.getMetaData();

            int columnCount = metadata.getColumnCount();

//            for (int i = 1; i <= columnCount; i++) {
//                List<String> column = new ArrayList<>();
//                column.add(metadata.getColumnName(i) + ", ");
//                lista.add(column);
//            }

            while (answer.next()) {
                List<String> row = new ArrayList<>();

                for (int i = 1; i <= columnCount; i++) {
                    row.add(metadata.getColumnName(i) + " = "+ answer.getString(i));
                }

                lista.add(row);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

        public void closeConnection() {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Disconnected from: " + sqlServer);
        }


    }

