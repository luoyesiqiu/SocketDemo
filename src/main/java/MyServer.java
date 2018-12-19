import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class MyServer {

    private  final  static String TAG="[server]";
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(6523);
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();

        while(true) {
            int len=0;
            byte[] header=new byte[3];
            for (int i = 0; i < 3; i++) {
                header[i] = (byte) inputStream.read();
            }
            if (header[0] == Protocol.FLAG[0]
                    && header[1] == Protocol.FLAG[1]
                    && header[2] == Protocol.FLAG[2]) {
                System.out.println(TAG+"检测到消息头！");

                len+=((byte)inputStream.read());
                len+=(((byte)inputStream.read())<<8);
                len+=(((byte)inputStream.read())<<16);
                len+=(((byte)inputStream.read())<<24);
                System.out.println(TAG+"消息长度:"+len);
            }
        }
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
