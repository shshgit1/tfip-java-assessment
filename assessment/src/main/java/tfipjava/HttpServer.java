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
    String n="/index.html";
    String[] moreFiles;
    public HttpServer(Socket sock,String FileDirectory,String[] moreFiles)
    {
        this.sock=sock;
        this.FDirectory=FileDirectory;
        this.moreFiles=moreFiles;
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
                dos.writeUTF("directory does not exist");
                dos.flush();
serverSocket.close();
}

String inputFromClient=dinps.readUTF();
Scanner scanForGet=new Scanner(inputFromClient);
String s=scanForGet.next();
String afterGet=scanForGet.next().trim();
if (!s.equals("GET"))
{
    dos.writeUTF("HTTP/1.1 405 Method Not Allowed\r\n\r\n <method name> not supported\r\n");
    dos.flush();
}
else if (s.equals("GET"))
{
    File filecheck=new File(s+afterGet);
if (!filecheck.exists())
{
    dos.writeUTF("HTTP/1.1 404 Not Found\r\n\r\n <resource name> not found\r\n");
    dos.flush();
    serverSocket.close();
}
else{
    if (afterGet.equals("/"))
        {
File fileChecker=new File(n);
if (fileChecker.exists())
            {   
            String str=new String("HTTP/1.1 200 OK\r\n\r\n <resource contents as bytes>");
            dos.writeBytes(str);
            dos.flush();
        serverSocket.close();       
  }
  }
        else
        {
            File filechecker =new File(afterGet);
            if (filechecker.exists() && afterGet.toLowerCase().contains("png"))
            {
                dos.writeUTF("HTTP/1.1 200 OK\r\n\r\n <resopurce contents as bytes>");
                dos.flush();
                serverSocket.close();
            }
                    }
}
}
        }
        catch(Exception e)
        {
e.printStackTrace();
        }
     
    }
    
}

