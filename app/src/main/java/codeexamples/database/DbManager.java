package codeexamples.database;

import static codeexamples.jooq.Tables.*;
import static org.jooq.impl.DSL.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Optional;
import org.flywaydb.core.Flyway;
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

  public static void migrateDb(final String url, final String user, final String password) {
    final Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
    flyway.migrate();
  }
}
