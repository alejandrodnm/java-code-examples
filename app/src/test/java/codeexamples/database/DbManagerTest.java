package codeexamples.database;

import static codeexamples.jooq.Tables.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class DbManagerTest {

  @Container
  /**
   * Making it static will reuse the same container for all the tests, in this case `createDb`
   * should be made static and decorated with `BeforeAll`
   */
  private PostgreSQLContainer postgreSQLContainer =
      new PostgreSQLContainer("postgres:12.4")
          .withDatabaseName("db_test")
          .withUsername("db_admin")
          .withPassword("db_password");

  private static DbManager dbManager;

  @BeforeEach
  public void createDb() {

    DbManager.migrateDb(
        postgreSQLContainer.getJdbcUrl(),
        postgreSQLContainer.getUsername(),
        postgreSQLContainer.getPassword());

    dbManager =
        new DbManager(
            postgreSQLContainer.getJdbcUrl(),
            postgreSQLContainer.getUsername(),
            postgreSQLContainer.getPassword(),
            postgreSQLContainer.getDriverClassName());
  }

  @Test
  public void addUser() throws SQLException, IOException {
    dbManager.insertUser("ainara");
    final var record = dbManager.getUser("ainara");
    assertTrue(record.isPresent());
    assertEquals("ainara", record.get().get(USERS.NAME));
  }
}
