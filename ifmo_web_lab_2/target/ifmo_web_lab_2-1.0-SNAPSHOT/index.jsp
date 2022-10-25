<%!
    String tableRowToHtml(TableRow tableRow) {
        if (tableRow == null) return "";
        return "<tr>" +
                "<td>" + tableRow.getX() + "</td>" +
                "<td>" + tableRow.getY() + "</td>" +
                "<td>" + tableRow.getR() + "</td>" +
                "<td>" + tableRow.getClientDate() + "</td>" +
                "<td>" + tableRow.getScriptWorkingTime() + " ms</td>" +
                "<td>" + tableRow.getHit() + "</td>" +
                "</tr>";
    }
%>

<%@ page import="com.dmitri.ifmo_web_lab_2.entity.TableRow" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dmitri Project</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script type="text/javascript" charset="UTF-8"
            src="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraphcore.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraph.css"/>
    <script src="js/graph.js"></script>
    <script src="js/submit.js"></script>
    <script src="js/validator.js"></script>
    <script src="js/tableWorker.js"></script>
</head>
<body>
<header class="header">
    <a href="https://github.com/DmitriBazilov">
        Bazilov Dmitri P3215 Вариант 1500
    </a>
</header>
<div class="menu">
    <div class="graph">
        <div id="jxgbox" class="jxgbox" style="width:500px; height:300px; margin-left:30px;"></div>
    </div>
    <form id="formSend" class="data_input">
        <div class="x_input">
            <span>Choose X</span><br>
            <input type="checkbox" id="X--1" name="X" value="-1">
            <label for="X--1">-1</label>
            <input type="checkbox" id="X--2" name="X" value="-2">
            <label for="X--2">-2</label>
            <input type="checkbox" id="X--3" name="X" value="-3">
            <label for="X--3">-3</label>
            <input type="checkbox" id="X-0" name="X" value="0">
            <label for="X-0">0</label>
            <input type="checkbox" id="X-1" name="X" value="1">
            <label for="X-1">1</label>
            <input type="checkbox" id="X-2" name="X" value="2">
            <label for="X-2">2</label>
            <input type="checkbox" id="X-3" name="X" value="3">
            <label for="X-3">3</label>
            <input type="checkbox" id="X-4" name="X" value="4">
            <label for="X-4">4</label>
            <input type="checkbox" id="X-5" name="X" value="5">
            <label for="X-5">5</label>
        </div>
        <div class="y_input">
            <input id="y_text" type="text" placeholder="Input Y">
        </div>
        <div class="r_input">
            <select id="r_select">
                <option value="" selected disabled hidden>Choose R</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <div class="buttons">
            <button class="btnSend" id="btnSend" type="button">submit</button>
            <button class="btnReset" id="btnReset" type="reset">reset</button>
        </div>
    </form>
</div>
<div id="alert"></div>
<div class="table">
    <button class="btnClean" id="btnClean" type="button">Clean Table</button>
    <table id=result_table>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Current time</th>
            <th>Execute time</th>
            <th>Result</th>
        </tr>
        <tbody id="table_body">
            <%
                ArrayList<TableRow> rows = (ArrayList<TableRow>) request.getSession().getAttribute("table");
                if (rows == null) rows = new ArrayList<>();
                for (TableRow tableRow: rows) {
                    out.print(tableRowToHtml(tableRow));
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
