package tcp;

public class Protocol {
    //用来表示键值
    public static final  byte[] MSG_KEY_FLAG ={(byte)0xaf,(byte)0xa0,(byte)0xa1};
    //用来表示主要消息
    public static final  byte[] MSG_MAIN_FLAG ={(byte)0xdd,(byte)0xd0,(byte)0xd1};
}
