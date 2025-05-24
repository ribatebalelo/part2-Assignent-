package chatapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class ChatAppTest {

    private Connection conn;

    @Before
    public void setUp() throws Exception {
        conn = DBConnection.getConnection();
        // Clean up if the test user already exists
        PreparedStatement cleanup = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        cleanup.setString(1, "test_");
        cleanup.executeUpdate();
    }

    @Test
    public void testUserRegistrationAndLogin() throws Exception {
        // Insert user
        String username = "test_";
        String password = "Pass@123";
        String phone = "+27821234567";

        PreparedStatement insertStmt = conn.prepareStatement(
            "INSERT INTO users (username, password, phone) VALUES (?, ?, ?)"
        );
        insertStmt.setString(1, username);
        insertStmt.setString(2, password);
        insertStmt.setString(3, phone);
        int rowsInserted = insertStmt.executeUpdate();
        assertEquals(1, rowsInserted);

        // Try logging in
        PreparedStatement loginStmt = conn.prepareStatement(
            "SELECT * FROM users WHERE username = ? AND password = ?"
        );
        loginStmt.setString(1, username);
        loginStmt.setString(2, password);
        ResultSet rs = loginStmt.executeQuery();
        assertTrue("Login should succeed for inserted user", rs.next());

        // Check that the retrieved data matches
        assertEquals(username, rs.getString("username"));
        assertEquals(password, rs.getString("password"));
        assertEquals(phone, rs.getString("phone"));
    }

    @After
    public void tearDown() throws Exception {
        // Clean up test user after test
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        stmt.setString(1, "test_");
        stmt.executeUpdate();
        conn.close();
    }
}
