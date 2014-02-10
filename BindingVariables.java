/*
 * Copyright (c) 2014 xqj.net. All rights reserved.
 */

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQException;

import javax.xml.namespace.QName;

import net.xqj.basex.BaseXXQDataSource;

/**
 * Basic Example showing how to bind Java values to XQuery external variables
**/
public class BindingVariables
{
  public static void main(String[] args) throws XQException
  {
    if(args.length != 4)
    {
      System.out.println(
        "usage: java net.xqj.basex.examples.BindingVariables "+
        "host port user password");
      return;
    }

    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", args[0]);
    ds.setProperty("port", args[1]);
    ds.setProperty("user", args[2]);
    ds.setProperty("password", args[3]);

    XQConnection xqc = ds.getConnection();

    XQPreparedExpression xqpe = xqc.prepareExpression(
      "declare variable $str as xs:string external;\n"+
      "declare variable $doc as document-node(element(e)) external;\n"+
      "fn:concat('String: ', $str),\n"+
      "fn:concat('Document: ', fn:serialize($doc))"
    );

    // ... Bind String ...
    xqpe.bindString(new QName("str"), "Hello World", null);

    // ... Bind Document ...
    xqpe.bindDocument(new QName("doc"), "<e>Hello World</e>", null, null);

    XQResultSequence rs = xqpe.executeQuery();

    while(rs.next())
      System.out.println(rs.getItemAsString(null));

    xqc.close();
  }
}