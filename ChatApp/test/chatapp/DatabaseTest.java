package chatapp;

import org.junit.Test;
import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    public void testDatabaseConnection() {
        Connection conn = null;
        try {
            conn = Database.connect();
            assertNotNull("Database connection should not be null", conn);
            assertFalse("Connection should not be closed", conn.isClosed());
        } catch (Exception e) {
            fail("Exception occurred during DB connection: " + e.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ignore) {}
        }
    }
}
