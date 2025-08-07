package org.example;

import java.util.HashMap;
import java.util.Map;

//사용자의 명령어에서 행동과 파라미터를 분리하는 클래스
public class Rq {

    private Map<String, String> paramMap;
    private String actionName;

    public Rq(String command) {
        paramMap = new HashMap<>();

        String[] commandBits = command.split("\\?");

        actionName = commandBits[0];
        String queryString = "";

        if (commandBits.length > 1) {
            queryString = commandBits[1];
        }

        String[] queryStringBits = queryString.split("&");

        for (String param : queryStringBits) {
            String[] paramBits = param.split("=");
            String key = paramBits[0];
            String value = null;

            if (paramBits.length > 1) {
                value = paramBits[1];
            }

            if (value == null) {
                continue;
            }

            paramMap.put(key, value);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key, String defaultValue) {
        return paramMap.getOrDefault(key, defaultValue);
    }

    public int getParamAsInt(String key, int defaultValue) {
        String value = getParam(key, null);
        if(value == null) {
            return defaultValue;
        }

        return Integer.parseInt(value);
    }
}