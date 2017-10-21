/**
 * Created by Evjen on 20.10.2017.
 */
$(document).ready(function () {
    $("#table").tabulator({
        height: "311px",
        layout: "fitColumns",
        placeholder: "No Data Set",
        columns: [
            {title: "Rank", field: "position"},
            {title: "Name", field: "name"},
            {title: "Rating", field: "rating", align: "left", formatter: "star", formatterParams: {stars: 10}},
            {title: "Votes", field: "votes", align: "left", formatter: "progress", formatterParams: {max: 500000}},
            {title: "Year", field: "year", align: "center"}
        ]
    });

    $('input[name="daterange"]').daterangepicker(
        {
            locale: {
                format: " YYYY",
                viewMode: "years",
                minViewMode: "years"
            },
            startDate: '1917',
            endDate: '2017'
        });
});

function getTopMovies() {
    var range = $("#range").val().split('-');
    var from = range[0].trim();
    var to = range[1].trim();

    $.ajax({
        url: "getTopMovies",
        method: "GET",
        data: {from: from, to: to},
        success: function (data) {
            $("#table").tabulator("setData", data);
        }
    });
}

