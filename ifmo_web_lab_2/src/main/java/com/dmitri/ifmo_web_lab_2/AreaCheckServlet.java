package com.dmitri.ifmo_web_lab_2;

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

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {

    private DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        long currentTime = System.currentTimeMillis();
        Float x = Float.parseFloat(request.getParameter("x"));
        Float y = Float.parseFloat(request.getParameter("y"));
        Float r = Float.parseFloat(request.getParameter("r"));
        Long offset = Long.parseLong(request.getParameter("offset"));
        LocalDateTime clientTime = LocalDateTime.now().plusHours(offset);

        boolean hit = checkCircle(x, y, r) || checkRectangle(x, y, r) || checkTriangle(x, y, r);

        double scriptWorkingTime = System.currentTimeMillis() - currentTime;

        TableRow newRow = new TableRow(x, y, r, hit, clientTime.format(DATE_FORMAT), scriptWorkingTime);
        ArrayList<TableRow> sessionTable = (ArrayList<TableRow>) request.getSession().getAttribute("table");
        sessionTable.add(newRow);

        PrintWriter out = response.getWriter();
        out.print(new JSONObject(newRow));
        out.close();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private boolean checkTriangle(float x, float y, float r) {
        return x <= 0 && y >= 0 && (Math.abs(x) + y) <= r / 2;
    }

    private boolean checkCircle(float x, float y, float r) {
        return x >= 0 && y >= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2));
    }

    private boolean checkRectangle(float x, float y, float r) {
        return x <= 0 && y <= 0 && Math.abs(x) <= r && Math.abs(y) <= r / 2;
    }
}
