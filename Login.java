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
    String sql = "SELECT [UserID] FROM [AppUsers] WHERE [UserName] = '" + userName + "' " ;

    try {
        Connection conn = getConnection(); 
        Statement stmt = conn.createStatement(); 
        ResultSet data = stmt.executeQuery(sql);
        
        userId = data.getInt(1);        
    } catch (SQLException ex) {
        handleExceptions(ex);
    }
    finally {
        closeQuietly(data);
        closeQuietly(stmt);
        closeQuietly(conn);
    }
    
    return userId;
        }
} 
