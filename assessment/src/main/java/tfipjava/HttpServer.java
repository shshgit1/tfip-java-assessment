package tfipjava;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable{
    Socket sock;
    ServerSocket serverSocket;
    public HttpServer(Socket sock)
    {
        this.sock=sock;
    }
   
    public void run()
    {
        try{InputStream inps=sock.getInputStream();
            OutputStream osos=sock.getOutputStream();
            BufferedInputStream binps= new BufferedInputStream(inps);
            DataInputStream dinps= new DataInputStream(binps);
            BufferedOutputStream bos=new BufferedOutputStream(osos);
            DataOutputStream dos=new DataOutputStream(bos);

        }
        catch(Exception e)
        {

        }

    }
    
}
