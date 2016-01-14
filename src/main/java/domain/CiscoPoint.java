/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Daan
 */
public class CiscoPoint {

    private double x;
    private double y;
    private final String locatedDateTime;

    public CiscoPoint(double x, double y, String locatedDateTime) {
        this.x = x;
        this.y = y;
        this.locatedDateTime = locatedDateTime;
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
    
    @Override
    public String toString(){
        return "X : " + x + ", Y : " + y + ", Date located : " +  locatedDateTime;
    }
}
