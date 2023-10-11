package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.*;
import java.io.InputStream;

public class JSchExampleSSHConnection {

    /**
     * JSch Example Tutorial
     * Java SSH Connection Program
     */
     public static void test_ssh() {
        String host="173.208.150.226";
        int port=2934;
        String user="abdelali";
        String password="!Vjjg3l1xm93f!";
        String command1="ls -ltr";

        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void test_ssk_key_file(){

        String host="45.56.122.235";
        int port=64322;
        String user="root";
        String command1="ls -ltr";
        JSch jsch = new JSch();
        try {

            jsch.addIdentity("src/main/resources/id_rsa_last");

            // Establish an SSH session
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no"); // Disable host key checking for simplicity (not recommended for production)
            session.connect();
            System.out.println("Connected");

            // Now you can execute commands or perform other SSH operations
            // For example, you can use session.openChannel() to open channels for remote commands or file transfers

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            // Disconnect the session when done
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test_ssh_key_string(){

        String host="45.79.147.143";
        int port=64322;
        String user="root";
//        String password="!Vjjg3l1xm93f!";
        String command1="ls -ltr";

        String privateKeyString = "___ private key ___";

        JSch jsch = new JSch();
        try {
            // Load the private key from a string
            byte[] privateKeyBytes = privateKeyString.getBytes();
            jsch.addIdentity(user, privateKeyBytes, null, null);

            // Establish an SSH session
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no"); // Disable host key checking for simplicity (not recommended for production)
            session.connect();

            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void ssh_out_class(){
        String host="173.208.150.226";
        int port=2934;
        String user="abdelali";
        String password="!Vjjg3l1xm93f!";
        String command1="ls -ltr";
        try {
            var aaa = new ConnectionSSHPassword(host , port , user , password );
            var aa = new SshHandler(aaa);
            String o = aa.exec(command1);
            System.out.println(o);
        }catch (Exception e){

        }


    }

    public static void ssh_key_string_class(){
        String host="wss280.pimhost.com";
        int port=64322;
        String user="root";
        String command1="ls -ltr";
        String ssh_key="-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIJKAIBAAKCAgEAgEl+ap+gr/t+DYSN6E1xq/gZuXnRdLvGbp23AZnzuW/eTjsj\n" +
                "9yzivT1iVXXT6pN2pVnsTus8BsIsYp1QAv6W2LC0yylf8Ws1ShtP2O+6szcqqvgb\n" +
                "5xprhx7rZwYARtuRhiF0gw55xerkFIjKUMpN42mStUUriOQQlaNZZhqp7kfnszNl\n" +
                "thbrxEH2dgwGOxZmbyLD3UoEWEg4M2wKqUFUvgg+1ktl0jV/zEmg/rwERfWy89ud\n" +
                "crpZG02AfxbiCQ1TsozvEYlw5BsK+pXeXlCyLxXb5hHiZMCZ4CdgSZzkxXCfNelF\n" +
                "QQWgmQy7KvqoQd4eMeuSDw8odCmhZX8qRv7heBiZ91EWKN7OnsxJKM+3Kr73ptQq\n" +
                "1u/CHyPMc4RFZfbQc0dsz/R+eoglVf92k7nq+CGcy/kYGIOeWylw9cFMIkIRGaLz\n" +
                "pCcz7B0pj4XtUDFnUwVFIuliXEZ6O0S2/lP11iAlR6eWxtRN/cLjQq+gHLpWRCoU\n" +
                "v0F1X0oEwLGpMngoGHvPCmejo8R1EDKNgZtUOpKynujllCosVjnHYG7qT3lpuPQq\n" +
                "tO2XXUjM4vjW9hKd3VqauxbLVPiQXSZca6IgbBNxXSZQYbxv38NSHVVzcqv48cUL\n" +
                "BBBPDrBU4P2UAfhWCZ2GUVfskel1eGq356Qw5d7jPm6kgFUGEOUgkS83vh0CAwEA\n" +
                "AQKCAgBH+vS3lsFufMoJg6qsAWgaeabVbwibWxDH2C5q85rRHXe6pWxUsBa438xg\n" +
                "7y/smrkjpgT1cv/HOwP0gRf31imFhACtTZXLu7+2yQRQUJKNJBMIwxXE4gobUrn6\n" +
                "oF9fYquQew0IzAJ/joBTFT2PfzOcugvbMehvcqfrjjAHHkbir+3NmrUjKlE3WVuU\n" +
                "eMK2PKdVDEihzXoKntsJ30FMHqAR0EaRrtR+0g3W7ikrRQuQxX+XXE5HpU+CBkZn\n" +
                "CMvjEFs5/+HUES4KjzHl0FloyhZwXO95c+d3aKnuH6njI+ko7zwl37E//oxR09bh\n" +
                "Eqt96cc/c1IMqRClvQYWEPgDxddz3+wNcCKnqd8nZki7Kb7cerNqeAH/oq+DiOPW\n" +
                "YnTTLm9omO/Bl3xfD621TFr4+z9ymeD8MopWpuvQh4PI/C4M3g5thXs7yCky6MWM\n" +
                "Hwkn1TCFoEBdm1hwpY7SfhYFwy4ha7lSBPq2N/o38S1Q19lhJsc5Y9MnXeNWbmub\n" +
                "UYdRhVmo5lBghphlk2yt45Wy56S9C0mmTkyz4u4ExUttZ8ohiuN1+2RAimjE2/1S\n" +
                "aeV7eNG2RRiqyuobzWM5IZ41em03M/GOLk84TE3EdEd8ExmMlPS1RKLIb4TOuVKD\n" +
                "OsYtbGA0S3HJltLyFb1pzXwnSHg06fx9lWLAFYWlt8H6kRFsgQKCAQEA3JECfkZO\n" +
                "6o6t1VnMbpWPAD0wmntROzM8lxuaomxkzkRNnTZGtIoIzVAM0uGAB7Sv8BdheaxR\n" +
                "y1Rdr/1AWE5w3Q9ZGPDPBlekW3tplnA+a5oXXuIx8i65tUqG50Y8yQhmzeKM/Gw0\n" +
                "bCTIqhxWXDfmE7MwYyZrpmX+7XapHa483wQyqJ6Sz+LbTk6fwAaA1++h4KHOAu3n\n" +
                "q9BJpQmDIpgF8aN7x/AZHAzTINDuNFdRTn9Ka3bi/TqrP7aH3eRNBUdQwdZvYC27\n" +
                "xkuCfFbo4cgjtjXO/fjXfFgBjESdr5vBhacNvmhh2zmrOlRlqwPeiqCcc48HXE6J\n" +
                "U1IDrEP2hzbz6QKCAQEAlOVp64MFq7AhC/yUB2StTkgj/xBZiOZIN5gKUrC52L85\n" +
                "y8tcujDTx5wUquxsrjoVjs/5/4A1NYdlrwAHDv7MryQlfHG2Kpml8z7H3QRU3kbn\n" +
                "74cEthu9+5gv6sMEly5XuMZ2ZiC4NGrtczA6O//5YsVFxQF2mjvRhUgLShxpGqLB\n" +
                "GDa9qecu6bNde0Y2f8hBz9fw1ui0YH54cDRkOT/QsaZR5i1v9+fVSGk9bTK4TIHT\n" +
                "90A/xzbkbXmhkJknGgfZ131XF+Kb97cH2v1uIiYd/K/vAAvRR1IYp6IfgwluoPty\n" +
                "pIMDj4w3BMp4rXMeQmtLxWCVEiqYr6AXJ2AxtN5cFQKCAQB+4H+BqeoNbUO7vasm\n" +
                "Vavr5EcIJjXFCyUoez2JfzbBnHLVI3qqsdh8Wjb2O7dNN3gZcSmmNyTaBM7bxJ91\n" +
                "7LFpyEFlYmiEanfmE+jaG/kcJoiZER7QBGbZTWSqtzGSJt4UQuT88zV4m/Q+PozD\n" +
                "AXi9Dk+gSWKVVoi9ioufwjHLy/tkGcuz//hNZFbqvBLzpCoHMKxz7OxeXjNu1zjy\n" +
                "9mY86OD0Uz3ub8YnU2gi/bOMpxZlGP9b0ZrezJ39Wy2fArYGutZYihZA7ADEi63s\n" +
                "6xb34p4OZE14fpttV68pbDx3KHB0flEELXk81X2iUVh7gAZfRwBh7d966WupdXLj\n" +
                "tw25AoIBABbft4nqsvBROoJw5vzLxqQECSOiC18woV+JB+z0IVje564bK7+eyKod\n" +
                "nD812uxZF/68jJdnxsTWbBSaWHtBQYiWcPvRLD8XI5texa16gN803WS6xUqEsIb6\n" +
                "NkJGe2oOQIOuWXoPBL1ov9m2Svv0swctby54rxO6pNFo4/MasjrZTfi+UBzN2zMj\n" +
                "zvSlMLIovZOEhQc+8JbyVuZF5i+1UgY4mtRVnqHjB6z8Heespwt51mphgGrGUAlR\n" +
                "ajSgkpU5J4onQz30HinMAHTwAf9VA75R+NF0/02JmygFi/nBdTFd75q+VcmzrLAb\n" +
                "urZKvRdPDHDiCYSk7pfsbCIscPZsQpECggEBAKMtJFumxvhWyjFoErlexe9pFwHj\n" +
                "Hps9Hf95dMy5zNF8WC5AWnJO1/J8ImSoxLwBGeaYcC6hVYhC0Ry7Tsd5+uXGvlrb\n" +
                "auXyEZh+TJtPU9JKudypH4hifzFvp2334iu+tOHMm/gTxAbXujylSVmKwU9nKnyG\n" +
                "+DeueSexFnAikChMHPq9s4jieMYTHfJN1iBNFn8ZwhYSDQTqOzPbVJeqcBYHIwYm\n" +
                "M+1YCXtASwYtqBSpr03krj1vqz5+MLTwfBO/NJ4tzw700X62s+ZRU+atQ01UFypL\n" +
                "rE5v8/aWfKLmzix37HwYk4KZAbJV01t4G5eOVEL3wkw+2TwicEDmWJ48EhI=\n" +
                "-----END RSA PRIVATE KEY-----";

        try {
            var aaa = new ConnectionSSHPrivateKeyString( host , port , user ,  ssh_key );
            var aa = new SshHandler(aaa);
            String o = aa.exec(command1);
            System.out.println(o);
        }catch (Exception e){
            System.out.println("eeeeee -------" + e.getMessage());
        }


    }

    public static void ssh_key_private_class(){
        String host="wss280.pimhost.com";
        int port=64322;
        String user="root";
        String command1="ls -ltr";
        String ssh_key="src/main/resources/id_rsa_last";

        try {
            var aaa = new ConnectionSSHPrivateKeyFile( host , port , user ,  ssh_key );
            var aa = new SshHandler(aaa);
            String o = aa.exec(command1);
            System.out.println(o);
        }catch (Exception e){
            System.out.println("eeeeee -------" + e.getMessage());
        }
    }

}
