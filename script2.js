$(document).ready(function($)
{
    var data = firebase.database().ref();
    var users = data.child("Users");
    
    $("#b1").click(function()
    {
        users.child("1178").child("Message").set("You're all good, drive safe!");
        users.child("1178").child("Active").set(false);
    });
    
    $("#b2").click(function()
    {
        users.child("1178").child("Message").set("I'm coming up to the window, please keep your hands on the wheel.");
        users.child("1178").child("Active").set(false);
    });
    
    $("#b3").click(function()
    {
        users.child("1178").child("Message").set("I'm going to be sending a speeding ticket, you may drive off.");
        users.child("1178").child("Active").set(false);
    });
    
    $("#b4").click(function()
    {
        users.child("1178").child("Message").set("You have a broken tail light, get it fixed.");
        users.child("1178").child("Active").set(false);
    });
    
    $("#b5").click(function()
    {
        users.child("1178").child("Message").set("You're all good, drive safe!");
        users.child("1178").child("Active").set(false);
    });
    
    $(window).on("resize", function()
    {
        $("body").width($(window).innerWidth());
        $("body").height($(window).innerHeight());
    }).resize();
});