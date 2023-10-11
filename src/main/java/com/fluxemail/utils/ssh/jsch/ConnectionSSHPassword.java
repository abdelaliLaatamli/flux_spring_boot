package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionSSHPassword extends ConnectionSSH {

    private String password;

    public ConnectionSSHPassword( String host, int port , String username , String password ) {
        super( host, port , username );
        this.password = password;
    }


    /**
     */
    @Override
    public void connect() throws JSchException {

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();

        this.session=jsch.getSession(this.username, this.host , this.port);
        this.session.setPassword(this.password);
        this.session.setConfig(config);
        this.session.connect();

        System.out.printf("Connected to server %s\n",this.host );

    }
}
