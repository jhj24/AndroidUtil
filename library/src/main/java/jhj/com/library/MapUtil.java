package jhj.com.library;

import java.math.BigDecimal;

/**
 * 地图工具
 * wgs84/gps84 原始GPS卫星坐标，GPS、谷歌地图卫星
 * gcj02 谷歌地图、腾讯地图、高德地图
 * bd09 百度地图
 * Created by zhangfan on 2016/12/1 0001.
 */

public class MapUtil {
    private final static double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两点间距离
     *
     * @param lat1 lat1
     * @param lng1 lat1
     * @param lat2 lat1
     * @param lng2 lat1
     * @return double 距离 单位公里,精确到米
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = new BigDecimal(s).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        return s;
    }


    /**
     * 各地图API坐标系统比较与转换;
     * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系,
     * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）;
     * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。
     * 谷歌中国地图和搜搜中国地图和高德地图采用的是GCJ02地理坐标系;  搜搜地图被腾讯收购了现在叫腾讯地图
     * BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系;
     * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
     */

    public static final String BAIDU_LBS_TYPE = "bd09ll";

    private static double pi = 3.1415926535897932384626;
    private static double a = 6378245.0;
    private static double ee = 0.00669342162296594323;

    /**
     * WGS84 to GCJ02
     *
     * @param lat lat
     * @param lon lon
     * @return Gps
     */
    public static Gps gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }

    /**
     * GCJ-02 to WGS84
     */
    public static Gps gcj_To_Gps84(double lat, double lon) {
        Gps gps = transform(lat, lon);
        double lontitude = lon * 2 - gps.getWgLon();
        double latitude = lat * 2 - gps.getWgLat();
        return new Gps(latitude, lontitude);
    }

    /**
     * GCJ-02 To BD-09
     *
     * @param gg_lat gg_lat
     * @param gg_lon gg_lat
     * @return Gps
     */
    public static Gps gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new Gps(bd_lat, bd_lon);
    }

    /**
     * BD-09 To GCJ-02
     * @param bd_lat bd_lat
     * @param bd_lon bd_lat
     * @return Gps
     */
    public static Gps bd09_To_Gcj02(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new Gps(gg_lat, gg_lon);
    }

    /**
     * BD09 To WGS84
     *
     * @param bd_lat bd_lat
     * @param bd_lon bd_lat
     * @return Gps
     */
    public static Gps bd09_To_Gps84(double bd_lat, double bd_lon) {

        Gps gcj02 = bd09_To_Gcj02(bd_lat, bd_lon);
        return gcj_To_Gps84(gcj02.getWgLat(),
                gcj02.getWgLon());

    }

    /**
     * WGS84 To BD09
     *
     * @param gps_lat gps_lat
     * @param gps_lon gps_lat
     * @return GPS
     */
    public static Gps Gps84_To_bd09(double gps_lat, double gps_lon) {
        Gps gcj02 = gps84_To_Gcj02(gps_lat, gps_lon);
        if (gcj02 == null)
            return null;
        return gcj02_To_Bd09(gcj02.getWgLat(), gcj02.getWgLon());
    }

    private static boolean outOfChina(double lat, double lon) {
        return lon < 72.004 || lon > 137.8347 || lat < 0.8293 || lat > 55.8271;
    }

    private static Gps transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new Gps(lat, lon);
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

}
