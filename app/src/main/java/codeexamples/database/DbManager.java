package codeexamples.database;

import static codeexamples.jooq.Tables.*;
import static org.jooq.impl.DSL.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.Record1;
import org.jooq.SQLDialect;

public class DbManager {
  private final HikariDataSource dataSource;

  public DbManager(
      final String jdbcUrl,
      final String userName,
      final String password,
      final String driverClassName) {
    final HikariConfig poolConfig = new HikariConfig();
    poolConfig.setJdbcUrl(jdbcUrl);
    poolConfig.setUsername(userName);
    poolConfig.setPassword(password);
    poolConfig.setDriverClassName(driverClassName);
    poolConfig.setAutoCommit(false);
    poolConfig.setMaximumPoolSize(1);
    this.dataSource = new HikariDataSource(poolConfig);
  }

  public void insertUser(final String username) {
    using(this.dataSource, SQLDialect.POSTGRES)
        .transaction(
            config ->
                using(config)
                    .insertInto(table(name("users")))
                    .columns(field("name"))
                    .values("ainara")
                    .execute());
  }

  public Optional<Record1<String>> getUser(final String username) {
    return using(this.dataSource, SQLDialect.POSTGRES)
        .select(USERS.NAME)
        .from(USERS)
        .fetchOptional();
  }

  public void createDb() throws SQLException, IOException {
    try (final var connection = dataSource.getConnection();
        final var statement = prepareSchemaImportStatement(connection)) {
      statement.execute();
      connection.commit();
    }
  }

  private PreparedStatement prepareSchemaImportStatement(Connection connection)
      throws IOException, SQLException {
    return connection.prepareStatement(readSchema());
  }

  private String readSchema() throws IOException {
    final var file = getClass().getClassLoader().getResourceAsStream("schema.sql");
    if (file == null) {
      throw new RuntimeException("Cannot find resource file to create database: schema.sql");
    }
    try (file;
        final var fileReader =
            new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {
      return fileReader.lines().collect(Collectors.joining("\n"));
    }
  }
}
