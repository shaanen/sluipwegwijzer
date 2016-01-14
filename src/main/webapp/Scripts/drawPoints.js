/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var canvas;

function createCanvas(){
    if (canvas){
        document.body.removeChild(canvas);
    }
    canvas = document.createElement('canvas');
    canvas.setAttribute('width', 800 * getScale());
    canvas.setArrtibute('height', 330 * getScale());
    canvas.setAttribute('style', 'position: absolute; x:0; y:0');
    document.body.appendChild(canvas);
}

function getPointsToDraw(){
    
    var pointsToDraw = [];
    
    $.ajax({
        url: 'http://PLACEHOLDER', //voer url in van backend
        dataType: 'application/json; charset=utf8',
        complete: function(data){
            pointsToDraw = JSON.parse(data);
        }
    });
    
    drawPoints(pointsToDraw);
}

function drawPoints(pointsToDraw){
    
    createCanvas();
    var ctx = canvas.getContext('2d');
    
    for (var i = 0; i < pointsToDraw.length; i++){
        ctx.fillRect(pointsToDraw[i].x, pointsToDraw[i].y, 5, 5);
        // naam toevoegen zijkant, kleur misschien?
    }
}