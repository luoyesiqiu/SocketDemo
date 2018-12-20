package tcp;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class MyClient {
    private static final String TAG = "[client]";

    private static int idx=1;

    public static void main(String[] args) throws Exception {

        Socket socket=new Socket(ConstantPool.REMOTE_IP,ConstantPool.REMOTE_PORT);
        OutputStream outputStream=socket.getOutputStream();
        Random rd=new Random();
        //随机发送键值或者主要消息
        while(true) {
            boolean mainMsg=rd.nextInt()%2==0;
            if(mainMsg){
                sendMainMsg(outputStream);
            }
            else{
                sendKeyMsg(outputStream);
            }

        }
    }

    /**
     * 发送主要消息
     * @param outputStream
     * @throws IOException
     */
    private static void sendMainMsg(OutputStream outputStream) throws  IOException{
        Random rd=new Random();
        outputStream.write(Protocol.MSG_MAIN_FLAG[0]);
        outputStream.write(Protocol.MSG_MAIN_FLAG[1]);
        outputStream.write(Protocol.MSG_MAIN_FLAG[2]);
        int n=rd.nextInt(100);
        int len=n*255;

        byte[] lenParts=new byte[4];
        lenParts[0]=(byte)(len&0xff);
        lenParts[1]=(byte)((len&0xff00)>>8);
        lenParts[2]=(byte)((len&0xff0000)>>16);
        lenParts[3]=(byte)((len&0xff000000)>>24);
        //写入消息长度
        outputStream.write(lenParts);
        System.out.println(TAG+"发送第"+(idx++)+"条消息，类型为主要消息，长度："+len);
        for(int i=0;i<n;i++){
            for(int j=0;j<255;j++){
                outputStream.write((byte)j);
            }
        }
    }

    /**
     * 发送键值
     * @param outputStream
     * @throws IOException
     */
    private static void sendKeyMsg(OutputStream outputStream) throws  IOException{
        Random rd=new Random();
        outputStream.write(Protocol.MSG_KEY_FLAG[0]);
        outputStream.write(Protocol.MSG_KEY_FLAG[1]);
        outputStream.write(Protocol.MSG_KEY_FLAG[2]);
        byte[] keys={0,1,8,16,24,32,48,64};
        //键值长度
        outputStream.write(1);
        //发送键值
        byte[] key={keys[rd.nextInt(8)]};
        outputStream.write(key);
        System.out.println(TAG+"发送第"+(idx++)+"条消息，类型为键值，长度："+1+",值："+key[0]);
    }

    private static void log(String log){
        System.out.println(TAG+log);
    }
}
