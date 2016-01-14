/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Color;

/**
 *
 * @author Daan
 */
public class CiscoPoint {

    private double x;
    private double y;
    private final String locatedDateTime;
    private String name;
    private String rgbcolor;

    public CiscoPoint(double x, double y, String locatedDateTime) {
        this.x = x;
        this.y = y;
        this.locatedDateTime = locatedDateTime;
        this.name = null;
        Color c = new Color((int)(Math.random() * 0x1000000));
        this.rgbcolor = "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getLocatedDateTime() {
        return locatedDateTime;
    }
    
    public String getName(){
        return name;
    }
    
    public String getColor(){
        return rgbcolor;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setX(double X){
        this.x = X;
    }
    
    public void setY(double Y){
        this.y = Y;
    }
    
    @Override
    public String toString(){
        return "X : " + x + ", Y : " + y + ", Date located : " +  locatedDateTime;
    }
}
