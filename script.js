$(document).ready(function($)
{
    var data = firebase.database().ref();
    var users = data.child("Users");
    
    users.on("value", function(snap)
    {
        $("#myTable tbody").empty();
        $("#myTable tbody").append("<tr><th>Client ID</th><th>Proximity</th><th>Details on driver</th></tr>");
        
        snap.forEach(function(child)
        {
            if (child.child("Active").val() == true)
            {
                $("#myTable tbody").append("<tr><td><a href=\"clientPage.html\">" + child.child("ClientID").val() + "</a></td><td>" + child.child("Proximity").val() + " Miles away</td><td>" + child.child("Details").val() + "</td></tr>");
            }
        });
    });
    
    $(window).on("resize", function()
    {
        $("body").width($(window).innerWidth());
        $("body").height($(window).innerHeight());
    }).resize();
});