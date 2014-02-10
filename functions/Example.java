package net.xqj.basex.examples.functions;

/**
 * Java interface which represents the XQuery functions found in example.xqm
**/

public interface Example
{
  public boolean containsAnyOf(String arg, String... searchStrings);
  public int wordCount(String arg);
  public float multiply(float a, float b);
}
