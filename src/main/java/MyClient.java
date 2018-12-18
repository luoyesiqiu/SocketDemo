import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class MyClient {
    private static final String TAG = "[client]";



    public static void main(String[] args) throws Exception {

        Socket socket=new Socket("localhost",6523);
        OutputStream outputStream=socket.getOutputStream();
        int idx=0;
        while(idx++<1) {
            int randomInt=new Random().nextInt(10000);
            byte[] willSendData=genRandomString(randomInt);
            log("写入消息体："+new String(willSendData));
            log("写入协议版本：" + Protocol.VERSION);
            outputStream.write(Protocol.VERSION);
            log("写入" + Protocol.FLAG_LEN + "个标记符");
            for (int i = 0; i < Protocol.FLAG_LEN; i++) {
                outputStream.write(Protocol.FLAG);
            }
            log("写入消息体长度：" + willSendData.length);
            outputStream.write(willSendData.length);
            for(int i=0;i<willSendData.length;i++) {
                outputStream.write(willSendData[i]);

            }
            outputStream.flush();
        }
        outputStream.close();
        socket.close();
    }

    private static byte[] genRandomString(int len){
        StringBuilder builder=new StringBuilder();
        Random rd=new Random();
        for(int i=0;i<len;i++){
            builder.append((char)(rd.nextInt(57)+65));
        }
        return builder.toString().getBytes();
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
