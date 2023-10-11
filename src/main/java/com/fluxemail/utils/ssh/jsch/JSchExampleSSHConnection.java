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
        System.setProperty("JSCH_DEBUG", "true");

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
}
