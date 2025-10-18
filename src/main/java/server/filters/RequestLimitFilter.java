package server.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebFilter(urlPatterns = "/*")
public class RequestLimitFilter extends HttpFilter {
    private int requestsWindow = 10000;
    private HashMap<String, ArrayList<Long>> requestsMap = new HashMap<>();
    private int requestLimit = 15;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String reqIP = req.getRemoteAddr();
        long time = System.currentTimeMillis();
        ArrayList<Long> list = requestsMap.get(reqIP);
        if (list != null) {
            list.add(time);
        } else {
            ArrayList<Long> newList = new ArrayList<>();
            newList.add(time);
            requestsMap.put(reqIP, newList);
        }
        list = requestsMap.get(reqIP);
        ArrayList<Long> newList = new ArrayList<>();
        for (Long t : list) {
            if (time - t < requestsWindow) {
                newList.add(t);
            }
        }
        requestsMap.put(reqIP, newList);
        if (newList.size() > requestLimit) {
            res.setStatus(418);
            return;
        }

        chain.doFilter(req, res);
    }
}
