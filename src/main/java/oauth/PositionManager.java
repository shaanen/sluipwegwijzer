/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oauth;

import java.awt.Point;

/**
 *
 * @author Daan
 */
public class PositionManager {
    
    private double baseX;
    private double baseY;
    private Point basePointImage;
    private final double xFactor;
    private final double yFactor;
    
    public PositionManager(){
        //todo -- set base points after measurements have been done
        baseX = 215;
        baseY = 335;
        basePointImage = new Point(315,200);
        xFactor = 1.5588;
        yFactor = 1.5588;
    }
    
    public Point translateToImageXY(double wifiX, double wifiY){
        Point p = null;
        
        double xDiff = baseX - wifiX;
        double yDiff = baseY - wifiY;
        
        double imageXPoint = Math.abs(xDiff * xFactor);
        double imageYPoint = Math.abs(yDiff * yFactor);
        
        
        
        if (wifiX > baseX && wifiY > baseY){
            p = new Point(basePointImage.x + (int)imageXPoint, basePointImage.y + (int)imageYPoint);
        }
        
        if (wifiX < baseX && wifiY < baseY){
            p = new Point(basePointImage.x - (int)imageXPoint, basePointImage.y - (int)imageYPoint);
        }
        
        if (wifiX > baseX && wifiY < baseY){
            p = new Point(basePointImage.x + (int)imageXPoint, basePointImage.y - (int)imageYPoint);
        }
        
        if (wifiX < baseX && wifiY > baseY){
            p = new Point(basePointImage.x - (int)imageXPoint, basePointImage.y + (int)imageYPoint);
        }
        return p;
    }
    
    public Point validatePoint(Point p){
        
        Point fixedPoint = new Point();
        
        if (p.y < 10){
            if (p.x > 30 && p.x < 300){
                fixedPoint.y = 25;
            }
            
            if (p.x > 300 && p.x < 800){
                fixedPoint.y = 135;
            }
        }
        
        if (p.y > 330){
            if (p.x > 5 && p.x < 245){
                fixedPoint.y = 100;
            }
            
            if (p.x > 245 && p.x < 500){
                fixedPoint.y = 308;
            }
            
            if (p.x > 500 && p.x < 790){
                fixedPoint.y = 210;
            }
        }
        
        if (p.x < 0){
            if (p.y < 130){
                fixedPoint.x = 40;
            }
            
            if (p.y > 130 && p.y < 210){
                fixedPoint.x = 250;
            }
        }
        
        if (p.x < 800){
            
        }
        
        return fixedPoint;
    }
}
