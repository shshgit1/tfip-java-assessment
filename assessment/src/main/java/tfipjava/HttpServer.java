package tfipjava;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class HttpServer implements Runnable{
    Socket sock;
    ServerSocket serverSocket;
    String FDirectory;
    public HttpServer(Socket sock,String FileDirectory)
    {
        this.sock=sock;
        this.FDirectory=FileDirectory;
    }
   
    
   public static void main(String[] args) {
       
   }
    public void run()
    {
        try{
            InputStream inps=sock.getInputStream();
            OutputStream osos=sock.getOutputStream();
            BufferedInputStream binps= new BufferedInputStream(inps);
            DataInputStream dinps= new DataInputStream(binps);
            BufferedOutputStream bos=new BufferedOutputStream(osos);
            DataOutputStream dos=new DataOutputStream(bos);
            File f=new File(FDirectory);

            if(!f.exists())
            {
System.out.println("directory does not exist");
serverSocket.close();
}

String inputFromClient=dinps.readUTF();
Scanner scanForGet=new Scanner(inputFromClient);
String s=scanForGet.next();
String afterGet=scanForGet.next().trim();
if (s.equals("GET"))
{
    File filecheck=new File(s+afterGet);
if (filecheck.exists())
{
    System.out.println("ok");
}

}
        }
        catch(Exception e)
        {

        }
        finally
        {
            
    }
    
}
}
