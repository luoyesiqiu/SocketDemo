import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class MyClient {
    private static final String TAG = "client";

    public static void main(String[] args) throws Exception {

        Socket socket=new Socket("localhost",6523);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(socket.getOutputStream());
        while(true) {
            int randomInt=new Random().nextInt(1000000);
            byte[] willSendData=("生成一个随机数："+ randomInt).getBytes();
            log("写入协议版本：" + Protocol.VERSION);
            bufferedOutputStream.write(Protocol.VERSION);
            log("写入" + Protocol.FLAG_LEN + "个标记符");
            for (int i = 0; i < Protocol.FLAG_LEN; i++) {
                bufferedOutputStream.write(Protocol.FLAG);
            }
            log("写入消息体长度：" + willSendData.length);
            bufferedOutputStream.write(willSendData.length);
            log("写入消息体：");
            for (int i = 0; i < willSendData.length; i++) {
                log("写入：" + willSendData[i]);
                bufferedOutputStream.write(willSendData[i]);
            }
            bufferedOutputStream.flush();
        }
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
