package tfipjava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws IOException{
        
    
    Socket socket;
    int port=3000;
    ServerSocket serverSocket;  
    String directory="/static/";
    String[] input;
    String [] morefiles={};


    try{               
        if (args != null && args.length == 2)
       {     
        if (args[0].equals("--port")){

        port=Integer.valueOf(args[1]);
     
    }
        else if (args[0].equals("--docRoot"))
        {
            directory=args[1];
          
        }
            }

            if (args.length==4)
            {
                input = args;
                if(args[3].contains(":"))
                {
                String[] MoreFiles=input[3].split(":");
                morefiles=MoreFiles;
                
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("System.exit(1)");
        }

       
           ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening at port..." +port);
        try{
        while (true)
        {
            socket = serverSocket.accept();
HttpServer serv=new HttpServer(socket,directory,morefiles);
threadPool.submit(serv);
        }
    }
        finally
        {
            serverSocket.close();  
        }
}

}