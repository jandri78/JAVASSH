package sshConnect;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
//import com.jcraft.jsch.UserInfo;

public class ConnectionSSH {

    private static final String usuario = "user";
    private static final String servidor = "127.0.0.1";
    private static final Integer puerto = 2422;
    private static final String clave = "passw";
 
    public static void main(String[] args) throws Exception{
        JSch jSSH = new JSch();
        Session session = jSSH.getSession(usuario, servidor, puerto);
        //UserInfo ui = new SesionUsuario();
        //session.setUserInfo(ui);
        session.setPassword(clave);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
 
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand("ls");
        channelExec.connect();
 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String linea = null;
        int index = 0;
 
        while ((linea = reader.readLine()) != null) {
            System.out.println(++index + " : " + linea);
        }
        channelExec.disconnect();
        session.disconnect();
    }
}
