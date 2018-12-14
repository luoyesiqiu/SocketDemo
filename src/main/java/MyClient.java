import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MyClient {
    private static final String TAG = "client";

    public static void main(String[] args) throws Exception {
        byte[] willSendData="你好啊".getBytes();
        Socket socket=new Socket("localhost",6523);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(socket.getOutputStream());
        while(true) {
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
