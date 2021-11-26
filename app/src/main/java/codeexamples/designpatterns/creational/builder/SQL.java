package codeexamples.designpatterns.creational.builder;

public class SQL {
  private String select;
  private String from;
  private int limit;

  private SQL(final String select, final String from, final int limit) {
    this.select = select;
    this.from = from;
    this.limit = limit;
  }

  public String getQuery() {
    return "SELECT " + select + " FROM " + from + " LIMIT " + limit;
  }

  public static class SQLBuilder {
    private String select;
    private String from;
    private int limit;

    public SQLBuilder() {
      this.select = "*";
      this.from = "defaultTable";
      this.limit = 20;
    }

    public SQL build() {
      return new SQL(this.select, this.from, this.limit);
    }

    public SQLBuilder select(final String select) {
      this.select = select;
      return this;
    }

    public SQLBuilder from(final String from) {
      this.from = from;
      return this;
    }

    public SQLBuilder limit(final int limit) {
      this.limit = limit;
      return this;
    }
  }
}
