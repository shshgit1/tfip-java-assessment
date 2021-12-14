package tfipjava;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HttpClientConnection implements Runnable{
    Socket sock;
    
    String FDirectory="";
    String n="/index.html";
    String moreFiles;
    public HttpClientConnection(Socket sock,String FileDirectory,String moreFiles)
    {
        this.sock=sock;
        this.FDirectory=FileDirectory;
        this.moreFiles=moreFiles;
    }
public static void main(String[] args) throws UnknownHostException, IOException {
}
@Override
public void run()
    {
        PrintWriter out = null;
        try{
            out = new PrintWriter(sock.getOutputStream(), true);
            InputStream inps=sock.getInputStream();
            OutputStream osos=sock.getOutputStream();
            BufferedInputStream binps= new BufferedInputStream(inps);
            DataInputStream dinps= new DataInputStream(binps);
            BufferedOutputStream bos=new BufferedOutputStream(osos);
            DataOutputStream dos=new DataOutputStream(bos);
            File f=new File(FDirectory);
            if(!f.exists())
            {
                out.println("directory does not exist");
                out.flush();
sock.close();
}

String inputFromClient=dinps.readUTF();
System.out.println(inputFromClient);
Scanner scanForGet=new Scanner(inputFromClient);
String s=scanForGet.next();
String afterGet=scanForGet.next().trim();
if (!s.equals("GET"))
{
    out.println("HTTP/1.1 405 Method Not Allowed\r\n\r\n <method name> not supported\r\n");
    out.flush();
}
else if (s.equals("GET"))
{
    File filecheck=new File(s+afterGet);
if (!filecheck.exists())
{
    out.println("HTTP/1.1 404 Not Found\r\n\r\n <resource name> not found\r\n");
    out.flush();
    sock.close();
}
else{
    if (afterGet.equals("/"))
        {
File fileChecker=new File(n);
if (fileChecker.exists())
            {   
            String str=new String("HTTP/1.1 200 OK\r\n\r\n <resource contents as bytes>");
            out.println(str.getBytes());
           out.flush();
            sock.close();       
  }
  }
        else
        {
            File filechecker =new File(afterGet);
            if (filechecker.exists() && afterGet.toLowerCase().contains("png"))
            {
                out.println("HTTP/1.1 200 OK\r\n\r\n <resopurce contents as bytes>");
                out.flush();
                sock.close();
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
