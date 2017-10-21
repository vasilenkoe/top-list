<%--
  Created by IntelliJ IDEA.
  User: Evjen
  Date: 20.10.2017
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.1/css/tabulator.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/bootstrap/3/css/bootstrap.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-8 col-md-offset-2">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Choose date range for top list movies</h3>
            </div>
            <div class="panel-body">
                <div class="input-group">
                    <input type="text" id="range" name="daterange" class="form-control" placeholder="Choose range">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="getTopMovies()">Go!</button>
                    </span>
                </div>
                <div id="table"></div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tabulator/3.3.1/js/tabulator.min.js"></script>
<script src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<script src="/js/scripts.js"></script>
</body>
</html>
