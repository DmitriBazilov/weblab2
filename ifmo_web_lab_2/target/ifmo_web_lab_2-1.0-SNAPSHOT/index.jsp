<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab2</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link rel="stylesheet" href="style.css">
    <script src="plot.js"></script>
    <script src="script.js"></script>
    <script src="submit.js"></script>
</head>
<body onload="init()">
<header class="header">
    <span class="header-text">P32102</span>
    <span class="header-text">Жданок Прокопий</span>
    <span class="header-text">18459</span>
</header>

<div class="main">
    
    <div class="graph">
        <svg id="svg-graph" width="300px" height="300px" xmlns="https://www.w3.org/2000/svg">
            <!-- Координатные оси -->
            <line x1="0" x2="300" y1="150" y2="150"></line>
            <line x1="150" x2="150" y1="0" y2="300"></line>
            <!--Стрелки-->
            <polygon points="150,0 145,15 155,15" stroke="black"></polygon>
            <polygon points="300,150 285,145 285,155" stroke="black"></polygon>
            <!--Треугольник-->
            <polygon points="100,150 150,150 150,200"></polygon>
            <!--Четверть круга-->
            <path d="M150,50 A100,100 90 0,1 250,150 L 150,150 Z"></path>
            <!--Прямоугольник-->
            <polygon points="50,100 50,150 150,150 150,100"></polygon>

            <!-- Подписи к осям -->
            <text x="285" y="135">X</text>
            <text x="160" y="15">Y</text>
            <!-- Метки для значений R на оси X -->
            <line x1="50" x2="50" y1="143" y2="157"></line>
            <line x1="100" x2="100" y1="143" y2="157"></line>
            <line x1="200" x2="200" y1="143" y2="157"></line>
            <line x1="250" x2="250" y1="143" y2="157"></line>
            <!-- Метки для значений R на оси Y -->
            <line x1="143" x2="157" y1="50" y2="50"></line>
            <line x1="143" x2="157" y1="100" y2="100"></line>
            <line x1="143" x2="157" y1="200" y2="200"></line>
            <line x1="143" x2="157" y1="250" y2="250"></line>
            <!-- Значения R на оси X -->
            <text class="-r-g" x="40" y="138">-R</text>
            <text class="-r2-g" x="85" y="138">-R/2</text>
            <text class="r2-g" x="190" y="138">R/2</text>
            <text class="r-g" x="245" y="138">R</text>
            <!-- Значения R на оси Y -->
            <text class="r-g" x="162" y="54">R</text>
            <text class="r2-g" x="162" y="104">R/2</text>
            <text class="-r2-g" x="162" y="204">-R/2</text>
            <text class="-r-g" x="162" y="254">-R</text>
        </svg>
    </div>

    <form id="formSend" class="formSend">
        <div class="input">

            <div id="form-x">
                <span>Выберите X:</span>
                <div class="form-input">
                    <input type="checkbox" id="x-neg-5" name="input-x" value="-5">
                    <label for="x-neg-5">-5</label>
                    <input type="checkbox" id="x-neg-4" name="input-x" value="-4">
                    <label for="x-neg-4">-4</label>
                    <input type="checkbox" id="x-neg-3" name="input-x" value="-3">
                    <label for="x-neg-3">-3</label>
                    <input type="checkbox" id="x-neg-2" name="input-x" value="-2">
                    <label for="x-neg-2">-2</label>
                    <input type="checkbox" id="x-neg-1" name="input-x" value="-1">
                    <label for="x-neg-1">-1</label>
                    <input type="checkbox" id="x-0" name="input-x" value="0">
                    <label for="x-0">0</label>
                    <input type="checkbox" id="x-1" name="input-x" value="1">
                    <label for="x-1">1</label>
                    <input type="checkbox" id="x-2" name="input-x" value="2">
                    <label for="x-2">2</label>
                    <input type="checkbox" id="x-3" name="input-x" value="3">
                    <label for="x-3">3</label> 
                </div>
            </div>

            <div id="form-y">
                <span> Введите Y:</span>
                <input type="text" id="y" class="form-input" placeholder="Введите Y">
            </div>

            <div id="form-r">
                <span>Выберите R:</span>
                <div id="form-r-input" class="form-input form-r-input">
                    <input type="checkbox" id="r-1" name="input-r" value="1">
                    <label for="r-1">1</label>
                    <input type="checkbox" id="r-2" name="input-r" value="2">
                    <label for="r-2">2</label>
                    <input type="checkbox" id="r-3" name="input-r" value="3">
                    <label for="r-3">3</label>
                    <input type="checkbox" id="r-4" name="input-r" value="4">
                    <label for="r-4">4</label>
                    <input type="checkbox" id="r-5" name="input-r" value="5">
                    <label for="r-5">5</label>
                </div>
            </div>

                
            <div class="buttons">
                <button type="button" onclick="submitForm()" class="btn" id="btnSend">submit</button>
                <button type="reset" class="btn" id="btnReset">reset</button>
            </div>

        </div>
    </form>
</div>

<div style="margin-top:30px;">
    <span id="alert" class="alert"></span>
</div>
<div class="table-container">
    <button type="button" id="clearButton" onclick="cleanTable()">Очистить таблицу</button> 

    <table class="table">

        <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Дата проверки</th>
                <th>Время исполнения</th>
                <th>Результат</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>

</html>
