package api;

import java.io.Serializable;

public class geolocation implements geo_location, Serializable {
  public double x,y,z;

  public geolocation(double x, double y, double z){
      this.x = x;
      this.y = y;
      this.z = z;
  }

  public geolocation(geo_location other){
      this.x = other.x();
      this.y = other.y();
      this.z = other.z();
  }
    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(geo_location g) {
        double dx = this.x() - g.x();
        double dy = this.y() - g.y();
        double dz = this.z() - g.z();
        double t = (dx*dx+dy*dy+dz*dz);
        return Math.sqrt(t);
    }
}
