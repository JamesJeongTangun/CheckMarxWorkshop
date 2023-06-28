import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import cnt.Security.*;

import java.lang.StringBuffer;
import java.sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

// Class Declaration
class Login
{
    public int getUserId(HttpServletRequest request) 
        throws ServletException, IOException {
    int userId = 0;
    
    String userName = request.getParameter("UserName");
    String sqlStoredProc = "{call getUserId (?, ?)}";

    try {
        Connection conn = getConnection(); 
        CallableStatement stmt = conn.prepareCall(sqlStoredProc); 
        
        stmt.setString(1, userName);         
        stmt.registerOutParameter(2, java.sql.Types.INTEGER);

        stmt.execute();
        userId = stmt.getInt(2);
    } catch (SQLException ex) {
        handleExceptions(ex);
    }
    finally {
        closeQuietly(stmt);
        closeQuietly(conn);
    }
    
    return userId;
}
} 
