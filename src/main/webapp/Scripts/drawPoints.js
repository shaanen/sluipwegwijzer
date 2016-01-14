/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var canvas;

function createCanvas(){
    
    canvas = document.getElementById("swwCanvas");
    canvas.setAttribute('width', 800 * getScale());
    canvas.setAttribute('height', 400 * getScale());
}

function getPointsToDraw(){
    var pointsToDraw = [];
    
    $.ajax({
        url: 'http://localhost:8080/sluipwegwijzer/api/sluipwegwijzerApi/currentLocations', //voer url in van backend
        dataType: 'application/json; charset=utf8',
        complete: function(data){
            pointsToDraw = JSON.parse(data.responseText);
            drawPoints(pointsToDraw);
            setTimeout(getPointsToDraw, 30000);
        }
    });
    
}

function drawPoints(pointsToDraw){

    document.getElementById("Names").innerHTML = '<p>LEGENDA</p>';
    var ctx = canvas.getContext('2d');
    ctx.clearRect(0,0,800,400);
    var img = document.getElementById("Sluipwegwijzer");
    ctx.drawImage(img, 0, 0, 800, 330);
    
    for (var i = 0; i < pointsToDraw.length; i++){
        ctx.fillStyle = pointsToDraw[i].rgbcolor;
        ctx.fillRect(pointsToDraw[i].x, pointsToDraw[i].y, 10, 10);
        // naam toevoegen zijkant, kleur misschien?
        var Name = document.createElement("p");
        Name.innerHTML = pointsToDraw[i].name;
        Name.style.color = pointsToDraw[i].rgbcolor;
        document.getElementById("Names").appendChild(Name);
    }
}