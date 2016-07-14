package com.mydao.deploy.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import com.mydao.deploy.model.JavaWebLinux;
import com.mydao.deploy.service.JavaWebLinuxService;
import com.mydao.deploy.util.QueryStringParser;
import com.mydao.deploy.util.RemoteShellTool;

/**
 * Created by wucao on 16-5-30.
 */

public class LogWebSocketHandle extends AbstractWebSocketHandler {

    private ConcurrentHashMap<String, LogThread> map = new ConcurrentHashMap<String, LogThread>();

    @Autowired
    private JavaWebLinuxService linuxService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String id = QueryStringParser.parse(session.getUri().getQuery()).get("id");
        JavaWebLinux linux = linuxService.selectById(Long.parseLong(id));
        LogThread thread = new LogThread(linux, session);
        threadPoolTaskExecutor.execute(thread);
        map.put(session.getId(), thread);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LogThread thread = map.get(session.getId());
        map.remove(session.getId());
        thread.close();

        System.out.println("Now Websocket Connection: " + map.size());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }
}