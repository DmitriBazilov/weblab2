package com.dmitri.ifmo_web_lab_2;

import com.dmitri.ifmo_web_lab_2.entity.Table;
import com.dmitri.ifmo_web_lab_2.entity.TableRow;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {

    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getDispatcherType().name().equals("FORWARD")) {
            resp.sendError(403, "You are not welcome!");
            return;
        }
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        long currentTime = System.currentTimeMillis();
        float x = Float.parseFloat(request.getParameter("x"));
        float y = Float.parseFloat(request.getParameter("y"));
        float r = Float.parseFloat(request.getParameter("r"));
        long offset = Long.parseLong(request.getParameter("offset"));
        LocalDateTime clientTime = LocalDateTime.now().plusHours(offset);

        boolean hit = checkCircle(x, y, r) || checkRectangle(x, y, r) || checkTriangle(x, y, r);

        double scriptWorkingTime = System.currentTimeMillis() - currentTime;
    
        TableRow newRow = new TableRow(x, y, r, hit, clientTime.format(DATE_FORMAT), scriptWorkingTime);
        List<TableRow> sessionTable = (ArrayList<TableRow>) request.getSession().getAttribute("table");
        sessionTable.add(newRow);

        PrintWriter out = response.getWriter();
        out.print(new JSONObject(newRow));
        out.close();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private boolean checkTriangle(float x, float y, float r) {
        return x <= 0 && y <= 0 && (Math.abs(x) + Math.abs(y)) <= r / 2;
    }

    private boolean checkCircle(float x, float y, float r) {
        return x >= 0 && y >= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
    }

    private boolean checkRectangle(float x, float y, float r) {
        return x <= 0 && y >= 0 && Math.abs(x) <= r && y <= r / 2;
    }
}
