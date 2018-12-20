import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    private  final  static String TAG="[server]";
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(6523);
        Socket socket=serverSocket.accept();
        InputStream inputStream=socket.getInputStream();
        int idx=1;
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

                len+=(((inputStream.read()&0xff)<<0));
                len+=(((inputStream.read()&0xff)<<8));
                len+=(((inputStream.read()&0xff)<<16));
                len+=(((inputStream.read()&0xff)<<24));
                System.out.println(TAG+"接收第"+(idx++)+"条消息，长度："+len);
                for(int i=0;i<len;i++)
                {
                    inputStream.read();
                }
            }
        }
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
