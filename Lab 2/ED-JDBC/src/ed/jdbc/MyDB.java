package ed.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class MyDB {

    public MyDB() {

    }

    public void createMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE MYUSER ( "
                    + " UserId CHAR(6) CONSTRAINT PK_CUSTOMER PRIMARY KEY, " + " Name CHAR(30), Password CHAR(6), Email CHAR(30), " + " Phone CHAR(10), Address CHAR(60), "
                    + " SecQn CHAR(60), SecAns CHAR(60))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void dropMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE MYUSER");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }



    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void addRecords(ArrayList<Myuser> myUsers) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for
            (Myuser myuser : myUsers) {
                pStmnt.setString(1, myuser.getUserid());
                pStmnt.setString(2, myuser.getName());
                pStmnt.setString(3, myuser.getPassword());
                pStmnt.setString(4, myuser.getEmail());
                pStmnt.setString(5, myuser.getPhone());
                pStmnt.setString(6, myuser.getAddress());
                pStmnt.setString(7, myuser.getSecQn());
                pStmnt.setString(8, myuser.getSecAns());
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch  (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {

                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEX) {

                }
            }
        }
    }

    public boolean createRecord(Myuser myuser)  {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, myuser.getUserid());
            pStmnt.setString(2, myuser.getName());
            pStmnt.setString(3, myuser.getPassword());
            pStmnt.setString(4, myuser.getEmail());
            pStmnt.setString(5, myuser.getPhone());
            pStmnt.setString(6, myuser.getAddress());
            pStmnt.setString(7, myuser.getSecQn());
            pStmnt.setString(8, myuser.getSecAns());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                throw new SQLException("Cannot insert records!");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally  {
            System.out.println("User Created");
            return true;
        }
    }

    public Myuser getRecord(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "SELECT * FROM APP.MYUSER WHERE USERID=(?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userId);

            ResultSet resultSet = pStmnt.executeQuery();

            if (resultSet.next() == false) {
                System.out.println("Result set is empty");
                return null;
            } else {
                String userID = resultSet.getString("USERID");
                String name = resultSet.getString("NAME");
                String password = resultSet.getString("PASSWORD");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                String address = resultSet.getString("ADDRESS");
                String secqn = resultSet.getString("SECQN");
                String secans = resultSet.getString("SECANS");

                Myuser user = new Myuser(userID, name, password, email, phone, address, secqn, secans);
                return user;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateRecord(Myuser myuser) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement
                    = "UPDATE MYUSER SET " +
                    "NAME=(?)," +
                    "PASSWORD=(?)," +
                    "EMAIL=(?)," +
                    "PHONE=(?)," +
                    "ADDRESS=(?)," +
                    "SECQN=(?)," +
                    "SECANS=(?)" +
                    "WHERE USERID=(?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, myuser.getName());
            pStmnt.setString(2, myuser.getPassword());
            pStmnt.setString(3, myuser.getEmail());
            pStmnt.setString(4, myuser.getPhone());
            pStmnt.setString(5, myuser.getAddress());
            pStmnt.setString(6, myuser.getSecQn());
            pStmnt.setString(7, myuser.getSecAns());
            pStmnt.setString(8, myuser.getUserid());

            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                System.out.println("User id does not exist");
            }else {
                System.out.println("User Id:" + myuser.getUserid() + " has been updated");
                return true;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteRecord(String userId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String sql
                    = "DELETE FROM MYUSER WHERE USERID=(?)";

            pStmnt = cnnct.prepareStatement(sql);
            pStmnt.setString(1, userId);

            pStmnt.executeUpdate();
            System.out.println("Record deleted successfully");
            return true;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}