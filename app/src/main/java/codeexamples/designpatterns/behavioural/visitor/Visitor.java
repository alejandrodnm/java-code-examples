package codeexamples.designpatterns.behavioural.visitor;

public interface Visitor {
  void visit(final HtmlDocument p0);

  void visit(final MdDocument p0);

  public static class HtmlDocument {
    public void visit(final Visitor v) {
      v.visit(this);
    }
  }

  public static class MdDocument {
    public void visit(final Visitor v) {
      v.visit(this);
    }
  }

  public static class MyVisitor implements Visitor {
    @Override
    public void visit(final HtmlDocument d) {
      System.out.println("visited HtmlDocument");
    }

    @Override
    public void visit(final MdDocument d) {
      System.out.println("visited MdDocument");
    }
  }
}
