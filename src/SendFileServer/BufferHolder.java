package SendFileServer;


/**
* SendFileServer/BufferHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SendFileServer.idl
* mi�rcoles 10 de octubre de 2018 11:20:24 AM CDT
*/

public final class BufferHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public BufferHolder ()
  {
  }

  public BufferHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SendFileServer.BufferHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SendFileServer.BufferHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SendFileServer.BufferHelper.type ();
  }

}
