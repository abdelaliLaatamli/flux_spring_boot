package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class ConnectionSSHPrivateKeyFile extends ConnectionSSH {

    private String sshKeyFilePath;

    public ConnectionSSHPrivateKeyFile(String host, int port, String username , String sshPrivateKeyFilePath ) {
        super(host, port, username);
        this.sshKeyFilePath = sshPrivateKeyFilePath;
    }


    /**
     * @throws JSchException
     */
    @Override
    public void connect() throws JSchException {

        JSch jsch = new JSch();
//        jsch.addIdentity("src/main/resources/id_rsa_last");
        jsch.addIdentity(this.sshKeyFilePath);
        // Establish an SSH session
        this.session = jsch.getSession(this.username, this.host, this.port);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.connect();

        System.out.printf("Connected to server %s\n",this.host );
    }
}
