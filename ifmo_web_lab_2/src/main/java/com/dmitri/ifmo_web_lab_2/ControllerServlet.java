package com.dmitri.ifmo_web_lab_2;

import com.dmitri.ifmo_web_lab_2.entity.Table;
import com.dmitri.ifmo_web_lab_2.entity.TableRow;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //createTableIfNeed(request);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if (checkGetTableRequest(request)) {
            Table table = (Table) request.getSession().getAttribute("table");
            ArrayList<TableRow> rows = table.getTableRows();
            out.print(new JSONArray(rows));
            out.close();
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (checkArgumentExists(request)) {
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //createTableIfNeed(request); //maybe need for "not browser" request
        if (checkCleanRequest(request)) {
            cleanTable(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private void createTableIfNeed(HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        if (currentSession.getAttribute("table") == null) {
            currentSession.setAttribute("table", new Table());
        }
    }

    private boolean checkCleanRequest(HttpServletRequest req) {
        return "true".equals(req.getParameter("clean"));
    }

    private void cleanTable(HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("table", new Table());
    }

    private boolean checkArgumentExists(HttpServletRequest request) {
        return request.getParameter("x") != null &&
                request.getParameter("y") != null &&
                request.getParameter("r") != null &&
                request.getParameter("offset") != null;
    }

    private boolean checkGetTableRequest(HttpServletRequest request) {
        return "true".equals(request.getParameter("getTable"));
    }
}
