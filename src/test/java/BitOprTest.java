import org.junit.Test;

public class BitOprTest {

    @Test
    public  void test(){
        final int n=129852;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        byte b1=(byte)(n&0xff);
        byte b2=(byte)((n&0xff00)>>8);
        byte b3=(byte)((n&0xff0000)>>16);
        byte b4=(byte)((n&0xff000000)>>24);

        System.out.println(b4+","+b3+","+b2+","+b1);
        System.out.println((b4&0xff)+","+(b3&0xff)+","+(b2&0xff)+","+(b1&0xff));
        int nn=0;
        nn+=((int)b1&0xff);
        nn+=(((int)b2&0xff)<<8);
        nn+=(((int)b3&0xff)<<16);
        nn+=(((int)b4&0xff)<<24);
        System.out.println(Integer.toBinaryString(nn));
        System.out.println(nn);

    }
}
