package tfipjava;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer{
    ServerSocket serverSocket;
    Socket sock;
    String Fdirectory;
    String moreFiles;
    int port=3000;
    public HttpServer(int port,String FileDirectory,String moreFiles)
    {
        this.port=port;
        this.Fdirectory=FileDirectory;
        this.moreFiles=moreFiles;
    }

   public void startServer() throws IOException{

   ExecutorService threadPool = Executors.newFixedThreadPool(3);
   serverSocket = new ServerSocket(port);
   System.out.println("Server listening at port..." +port);
   try{
   while (true)
   {
       sock = serverSocket.accept();
HttpClientConnection serv=new HttpClientConnection(sock,Fdirectory,moreFiles);
threadPool.submit(serv);
   }
}
   finally
   {
       sock.close();  
       serverSocket.close();
   }
}

}

