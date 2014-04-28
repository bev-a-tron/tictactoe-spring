$(document).ready(function(){

    function changeTextToX(){
        $(this).text("x");
    };

    $("td").click(changeTextToX);

});