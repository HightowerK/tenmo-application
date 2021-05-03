package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserDAOIntegrationTest {

    private static final long TEST_USER_ID = 5000;

    private static SingleConnectionDataSource dataSource;
    private JdbcUserDAO dao;

    @BeforeAll
    public static void setupDataSource() {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        dataSource.setAutoCommit(false);
    }

    @AfterAll
    public static void closeDataSource() throws SQLException {
        dataSource.destroy();
    }

    @Before
    public void setup() {
        String sqlInsertUser = "INSERT INTO users (user_id, username, password_hash) " +
                "VALUES (?, user, $2a$10$D8dTlNice6zeniNB6OIQieMHJEdiUHFqWyizA1ZXyPjXR0QK1pMLC)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sqlInsertUser, TEST_USER_ID);

        dao = new JdbcUserDAO(dataSource);
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

    @Test
    void findIdByUsername() {

    }

    @Test
    void findUserNameByAccountId() {
    }

    @Test
    void findAll() {

    }

    @Test
    void findByUsername() {
        String testUsername = "johnny";
        User user = getUser(TEST_USER_ID, testUsername, "$2a$10$D8dTlNice6zeniNB3OIQieMHJEdiUHFqWyizA1ZXyKlMR0QK1pMLC");
        dao.findByUsername(testUsername);

        User results = dao.findByUsername(testUsername);

        assertNotNull(results);
        assertEquals(testUsername, results.getUsername());
    }

    @Test
    void create() {
        User user = getUser(TEST_USER_ID, "johnny", "$2a$10$D8dTlNice6zeniNB3OIQieMHJEdiUHFqWyizA1ZXyKlMR0QK1pMLC");

        //dao.create(user);

    }

    @Test
    void findBalance() {
    }

    private User getUser(long userId, String username, String passwordHash) {
        User theUser = new User();
        theUser.setId(userId);
        theUser.setUsername(username);
        theUser.setPassword(passwordHash);
        return theUser;
    }

    private void assertUsersAreEqual(User expected, User actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
    }

}