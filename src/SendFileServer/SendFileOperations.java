package SendFileServer;


/**
* SendFileServer/SendFileOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from SendFileServer.idl
* mi�rcoles 10 de octubre de 2018 11:20:25 AM CDT
*/

public interface SendFileOperations 
{
  byte[] download (String ID);
  void shutdown ();
} // interface SendFileOperations