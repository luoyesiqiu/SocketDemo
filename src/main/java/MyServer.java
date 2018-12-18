import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class MyServer {

    private  final  static String TAG="[server]";
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket=new ServerSocket(6523);
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        while (!socket.isClosed()) {
            int version = inputStream.read();
            if (version != Protocol.VERSION) {
                log("不正确的协议版本");
                continue;
            }
            for (int i = 0; i < Protocol.FLAG_LEN; i++) {
                if (inputStream.read() != Protocol.FLAG) {
                    log("消息格式错误");
                    return;
                }
            }
            int msgLength = inputStream.read();
            byte[] msgBody=new byte[msgLength];
            int data = inputStream.read(msgBody);

            log("来自客户端的消息:"+new String(msgBody));
        }
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
