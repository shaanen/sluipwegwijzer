/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var startX = 800;
var startY = 330;
var scale = 1;

function redraw(imgId){
    if (event.clientY !== 0){
            var endY = event.clientY;
            scale = endY / startY;
            var img = document.getElementById(imgId);

            img.width = startX * scale;
            img.height = startY * scale;
    }
    
}

function drawImage(imgId){
    img = document.getElementById(imgId);
    img.width = startX * scale;
    img.height = startY * scale;
}

function getScale(){
    return scale;
}

