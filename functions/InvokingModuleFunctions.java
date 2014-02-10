import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.basex.BaseXXQDataSource;
import com.xqj2.XQConnection2;

/**
 * InvokingModuleFunctions.java
 * Invokes XQuery Functions from Java "as if" they were regular Java methods.
 *
 * This topic was presented at XML Prague 2012.
 *
 * BUILDING BRIDGES FROM JAVA TO XQUERY
 * ---------------------------------------------------------------------------
 * Paper Submission:
 *   http://www.xmlprague.cz/2012/files/xmlprague-2012-proceedings.pdf#page=197
 *
 * Video Presentation:
 *   http://tinyurl.com/building-bridges-video
 *
 * INSTRUCTIONS
 * ---------------------------------------------------------------------------
 * 1. Run "basexclient" and install "example.xqm" into BaseX, e.g.
 *   REPO INSTALL /fullPathTo/src/net/xqj/basex/examples/functions/example.xqm
 *
 * 2. Compile Java source files and run:
 *   net.xqj.basex.examples.functions.InvokingModuleFunctions
**/

public class InvokingModuleFunctions
{
  public static void main(String[] args) throws XQException
  {
    if(args.length != 4)
    {
      System.out.println(
        "usage: java net.xqj.basex.examples.functions.InvokingModuleFunctions "+
        "host port user password");

      return;
    }

    XQDataSource ds = new BaseXXQDataSource();

    ds.setProperty("serverName", args[0]);
    ds.setProperty("port", args[1]);
    ds.setProperty("user", args[2]);
    ds.setProperty("password", args[3]);

    XQConnection2 xqc = (XQConnection2)ds.getConnection();

    Example example =
      xqc.createModuleProxy(
        "http://www.example.com",
        null,
        Example.class
      );

    boolean contains =
      example.containsAnyOf("abc", "bc", "xy");
    System.out.println("contains = " + contains);

    int totalWords =
      example.wordCount("The quick brown fox jumps over the lazy dog");
    System.out.println("totalWords = " + totalWords);

    float product =
      example.multiply(3f, 4f);
    System.out.println("product = " + product);
  }
}