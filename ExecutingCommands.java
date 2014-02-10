/*
 * Copyright (c) 2014 xqj.net. All rights reserved.
 */

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQException;

import java.io.PrintWriter;

import net.xqj.basex.BaseXXQDataSource;

/**
 * Application which executes proprietary against BaseX
 * Some BaseX Commands have Output, this is sent to stdout.
**/

public class ExecutingCommands
{
  public static void main(String[] args) throws XQException
  {
    if(args.length != 4)
    {
      System.out.println(
        "usage: java ExecutingCommands "+
        "host port user password");
      return;
    }

    XQDataSource ds = new BaseXXQDataSource();

    ds.setLogWriter(new PrintWriter(System.out, true));

    ds.setProperty("serverName", args[0]);
    ds.setProperty("port", args[1]);
    ds.setProperty("user", args[2]);
    ds.setProperty("password", args[3]);

    XQConnection xqc = ds.getConnection();
    XQExpression xqe = xqc.createExpression();

    xqe.executeCommand("SHOW DATABASES");
    xqe.executeCommand("SHOW SESSIONS");
    xqe.executeCommand("SHOW USERS");
    xqe.executeCommand("SHOW EVENTS");

    xqc.close();
  }
}
