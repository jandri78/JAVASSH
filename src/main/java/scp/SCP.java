package scp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
//import com.jcraft.jsch.UserInfo;

public class SCP {

    private static final String usuario = "root";
    private static final String servidor = "146.250.193.162";
    private static final Integer puerto = 22;
    private static final String clave = "t3l1nt3l";
    public static String rfile ="hello.txt";
 
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
