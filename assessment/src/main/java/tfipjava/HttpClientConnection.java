package tfipjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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

    @Override
    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try{       
            
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            File f=new File(FDirectory);
            if (!f.exists() || !f.isDirectory()){
            System.exit(1);
        }

while (true){
        String inputFromClient=in.readLine();
        System.out.println(inputFromClient);
        Scanner scanForGet=new Scanner(inputFromClient);
        String s=scanForGet.next();
        String afterGet=scanForGet.next().trim();
    if (!s.equals("GET")) {
    out.println("HTTP/1.1 405 Method Not Allowed\r\n\r\n <method name> not supported\r\n");
    out.flush();
    }
else if (s.equals("GET")){
    File filecheck=new File(s+afterGet);
    if (!filecheck.exists()){
        out.println("HTTP/1.1 404 Not Found\r\n\r\n <resource name> not found\r\n");
        out.flush();
        sock.close();
    }
    else{
        if (afterGet.equals("/")) {
            File fileChecker=new File(n);
            if (fileChecker.exists()){   
                String str=new String("HTTP/1.1 200 OK\r\n\r\n <resource contents as bytes>");
                out.println(str.getBytes());
                out.flush();
                sock.close();       
            }
        }
        else
        {
            File filechecker =new File(afterGet);
            if (filechecker.exists() && afterGet.toLowerCase().contains("png")){
                out.println("HTTP/1.1 200 OK\r\n\r\n <resopurce contents as bytes>");
                out.flush();
                sock.close();
            }
        }

    }
    }
       
        }
    }
        catch(Exception e)
        {
        System.exit(1);
        }
    }
    }
    
 
    
    

