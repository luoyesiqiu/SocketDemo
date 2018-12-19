import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;

public class MyClient {
    private static final String TAG = "[client]";



    public static void main(String[] args) throws Exception {

        Socket socket=new Socket("localhost",6523);
        OutputStream outputStream=socket.getOutputStream();
        Random rd=new Random();
        while(true) {
            outputStream.write(Protocol.FLAG[0]);
            outputStream.write(Protocol.FLAG[1]);
            outputStream.write(Protocol.FLAG[2]);
            int n=rd.nextInt(100);
            int len=n*255;

            byte[] len_parts=new byte[4];
            len_parts[0]=(byte)(len&0xff);
            len_parts[1]=(byte)((len&0xff00)>>8);
            len_parts[2]=(byte)((len&0xff0000)>>16);
            len_parts[3]=(byte)((len&0xff000000)>>24);
            outputStream.write(len_parts);
            System.out.println(TAG+"消息长度："+len);
            for(int i=0;i<n;i++){
                for(int j=0;j<255;j++){
                    outputStream.write((byte)j);
                }
            }


        }
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
