package com.mydao.deploy.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import com.mydao.deploy.model.JavaWebLinux;
import com.mydao.deploy.util.RemoteShellTool;

/**
 * Created by wucao on 16-5-30.
 */
public class LogThread implements Runnable {


    private JavaWebLinux linux;
    private BufferedReader reader;
    private WebSocketSession session;
    private RemoteShellTool tool;
    public LogThread(JavaWebLinux linux, WebSocketSession session) throws UnsupportedEncodingException {
        this.linux = linux;
        this.session = session;
        tool = new RemoteShellTool(linux.getIp(), linux.getLoginname(),
        		linux.getPassword(), "UTF-8");
    }

    public void close() throws IOException {
            tool.getConn().close();
    }

    @Override
    public void run() {
        String line;
        try {
                if (tool.login()) {
                	ch.ethz.ssh2.Session lsession = tool.getConn().openSession(); // 打开一个会话
                	lsession.execCommand("tail -f -n 500 "+linux.getPath()+"/logs/catalina.out");
                	BufferedReader reader = new BufferedReader(new InputStreamReader(lsession.getStdout(),"UTF-8"));
                	while((line = reader.readLine()) != null) {
                        // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                		session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(line) + "<br>"));
                    }
        			lsession.close();
                }
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
