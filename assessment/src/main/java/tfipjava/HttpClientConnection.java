package tfipjava;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClientConnection{
public static void main(String[] args) throws UnknownHostException, IOException {
    Socket sock=new Socket("localhost",12345);
    try{
        OutputStream osos=sock.getOutputStream();
        InputStream inps=sock.getInputStream();
        BufferedOutputStream bos=new BufferedOutputStream(osos);
        DataOutputStream dos=new DataOutputStream(bos);
        BufferedInputStream binps= new BufferedInputStream(inps);
        DataInputStream dinps= new DataInputStream(binps);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String OutputToServer="empty";
        while (!OutputToServer.equals("exit"))

{ 
OutputToServer=br.readLine();
dos.writeUTF (OutputToServer);
dos.flush();
String abc=new String(dinps.readUTF());
System.out.println(abc);
}
sock.close();

    }
    catch (Exception exc)
    {
        System.out.println("client fail");
        sock.close();
    }
}
    
}
