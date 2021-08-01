public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public double G = 6.67 * Math.pow(10, -11);

    public Planet(double xp, double yp, double xv,
                  double yv, double m, String img){
        xxPos = xp;
        yyPos = yp;
        xxVel = xv;
        yyVel = yv;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt(
                (xxPos - p.xxPos) * (xxPos - p.xxPos) +
                (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G * mass * p.mass / (Math.pow(calcDistance(p), 2));
    }
    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - xxPos)
                / calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * (p.yyPos - yyPos)
                / calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] all){
        double res = 0;
        for (Planet planet : all) {
            if (planet.equals(this))
                continue;
            res += calcForceExertedByX(planet);
        }
        return res;
    }
    public double calcNetForceExertedByY(Planet[] all){
        double res = 0;
        for (Planet planet : all) {
            if (planet.equals(this))
                continue;
            res += calcForceExertedByY(planet);
        }
        return res;
    }
    public void update(double dt, double fX, double fY){
        double xxa = fX / mass;
        double yya = fY / mass;
        xxVel += dt * xxa;
        yyVel += dt * yya;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
