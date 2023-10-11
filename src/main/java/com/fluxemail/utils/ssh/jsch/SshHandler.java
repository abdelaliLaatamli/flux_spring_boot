package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

import java.io.IOException;
import java.io.InputStream;

public class SshHandler {

    private ConnectionSSH connectionSSH;

    public SshHandler( ConnectionSSH connectionSSH ) throws JSchException {
        this.connectionSSH = connectionSSH;
        this.connectionSSH.connect();
    }

    public String exec(String command) throws JSchException, IOException {

        Channel channel=this.connectionSSH.getSession().openChannel("exec");
        ((ChannelExec)channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);

        InputStream in=channel.getInputStream();
        channel.connect();
        String resultat="";
        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
//                System.out.print(new String(tmp, 0, i));
                resultat=new String(tmp, 0, i);
            }
            if(channel.isClosed()){
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try{
                Thread.sleep(500);
            }catch(Exception ee){}
        }
        channel.disconnect();

        return resultat;
    }

    public String copyFile( String filePath ){
        return null;
    }


}
