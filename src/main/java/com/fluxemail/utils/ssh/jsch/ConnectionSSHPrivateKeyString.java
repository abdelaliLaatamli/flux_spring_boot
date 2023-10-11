package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class ConnectionSSHPrivateKeyString extends ConnectionSSH{

    private String sshKeyString;

    public ConnectionSSHPrivateKeyString(String host, int port, String username , String sshKeyString) {
        super(host, port, username);
        this.sshKeyString = sshKeyString;
    }

    /**
     * @throws JSchException
     */
    @Override
    public void connect() throws JSchException {

        JSch jsch = new JSch();
        byte[] privateKeyBytes = this.sshKeyString.getBytes();
        jsch.addIdentity(this.username, privateKeyBytes, null, null);
        // Establish an SSH session
        this.session= jsch.getSession(this.username, this.host, this.port);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.connect();

        System.out.printf("Connected to server %s\n",this.host );
    }
}
