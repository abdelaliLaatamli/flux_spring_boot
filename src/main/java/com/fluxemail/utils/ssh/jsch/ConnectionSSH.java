package com.fluxemail.utils.ssh.jsch;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Getter;

public abstract class ConnectionSSH {

    protected String host;
    protected int port;
    protected String username;
    @Getter
    protected Session session;

    public ConnectionSSH(String host , int port , String username){
        this.host = host ;
        this.port = port;
        this.username= username;

    }
    public abstract void connect() throws JSchException;

    public void reconnect() throws JSchException {
        if(this.session == null)
            this.connect();
    }

    public void disconnect(){
        this.session.disconnect();
        System.out.printf("Disconnected from server %s\n",this.host );
    }

}
