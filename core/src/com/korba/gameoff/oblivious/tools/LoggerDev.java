package com.korba.gameoff.oblivious.tools;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;

import java.lang.*;
import java.lang.StringBuilder;

public class LoggerDev {

    private static final String loggerTag = "LoggerDev";

    private Logger log;

    private Array<String> logArray;
    private long startTime;

    public LoggerDev() {
        log = new Logger(loggerTag);
    }

    private String getFPS() {
        String fps = "";
        startTime = TimeUtils.nanoTime();
        if (TimeUtils.nanoTime() - startTime > 1000000000) /* 1,000,000,000ns == one second */{
            fps = "fps: " + Gdx.graphics.getFramesPerSecond();
            startTime = TimeUtils.nanoTime();
        }

        return fps;
    }

    private void info(String message) {
        log.info(message);
        addToLogArray(message);
    }

    private void debug(String message) {
        log.debug(message);
        addToLogArray(message);
    }

    private void error(String message, Throwable exception) {
        log.error(message, exception);
        addToLogArray(message + ":" + exception.getMessage().substring(0, 50));
    }

    private void addToLogArray(String message) {
        if(logArray.size > 9)
            logArray.removeIndex(0);
        logArray.add(message);
    }

    private String getLatestLogs() {
        final java.lang.StringBuilder builder = new StringBuilder();
        builder.append(getFPS());
        for(String str : logArray)
            builder.append(System.lineSeparator()).append(str);

        return builder.toString();
    }


}
