package jhj.com.library.bean;

/**
 * Created by jhj on 17-7-27.
 */

public class LatLonBean {
    private double wgLat;
    private double wgLon;

    public LatLonBean(double wgLat, double wgLon) {
        setWgLat(wgLat);
        setWgLon(wgLon);
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }
}
