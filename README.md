************************************************************************************QuickChat Java Application************************************************************************************

The QuickChat app works through a series of interactive GUI windows built using Java Swing, with data stored being managed by a MySQL database. Upon launch, the user is presented with a login window where they may either log in or navigate to a registration page. During registration, form fields are validated against strict username, password, and phone number requirements to ensure data integrity. Upon login, the user is redirected to the messenger interface, where they can enter and send messages. The messages are stored in the `messages` table of the `quickchat_db` database along with a timestamp and the username of the sender. Each time the user logs in, their previously sent messages are retrieved from the database and displayed, providing message persistence across sessions. The interface includes navigational elements such as a "Quit" button to return to the login screen, and a "Show Recently Sent Messages" button which opens a placeholder frame with a message regarding future enhancements. The app structure prioritizes user experience, input validation, and modularity, with JUnit tests written to ensure important backend functionality such as database connection.
_______________________________________________________________________________________________________________________________________________________________________________________________________________________
Telusko (2019). How to connect Java with MySQL Database. [YouTube video]. Available at: https://www.youtube.com/watch?v=J4o3gGfE8aQ [Accessed 16 May 2025].

Bro Code (2021). Java GUI Tutorial: Make a GUI in Java. [YouTube video]. Available at: https://www.youtube.com/watch?v=Kmgo00avvEw [Accessed 16 May 2025].

Java Guides (2020). JUnit Tutorial for Beginners. [YouTube video]. Available at: https://www.youtube.com/watch?v=oD7ETkOYqWg [Accessed 16 May 2025].

???? Websites & Articles
GeeksforGeeks (2023). Java Swing Tutorial. Available at: https://www.geeksforgeeks.org/java-swing/ [Accessed 16 May 2025].

Baeldung (2022). Guide to JDBC with MySQL. https://www.baeldung.com/java-jdbc [Accessed 16 May 2025].

Stack Overflow (2021). Password validation using regex in Java. https://stackoverflow.com/questions/3802192 [Accessed 16 May 2025].

JUnit Documentation (2024). JUnit 4 Quick Start. https://junit.org/junit4/ [Accessed 16 May 2025].
