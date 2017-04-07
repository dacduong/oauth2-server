package jp.eisbahn.oauth2.server.play;

import jp.eisbahn.oauth2.server.models.Request;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

public class PlayRequestAdapter implements Request {

    private Http.Request request;

    public PlayRequestAdapter(Http.Request request) {
        super();
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        return request.getQueryString(name);
    }

    @Override
    public Map<String, String> getParameterMap() {
        Map<String, String[]> mapArr = request.queryString();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String[]> entry: mapArr.entrySet()) {
            String value = "";
            if (entry.getValue() != null && entry.getValue().length > 0) {
                value = entry.getValue()[0];
            }
            map.put(entry.getKey(), value);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }
}
