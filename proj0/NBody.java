public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int n = in.readInt();
	    double res = in.readDouble();
        return res;
    }
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int n = in.readInt();
        double radius = in.readDouble();
        Planet[] res = new Planet[n];
        for (int i = 0; i < n; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            res[i] = p;
        }
        return res;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        int n = allPlanets.length;
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");

        for (Planet p : allPlanets){
            p.draw();
        }
        for (double i = 0; i < T; i += dt){
            StdDraw.enableDoubleBuffering();
            double xForces[] = new double[n];
            double yForces[] = new double[n];
            for (int j = 0; j < n; j++) {

                xForces[j] = allPlanets[j].calcNetForceExertedByX(allPlanets);
                yForces[j] = allPlanets[j].calcNetForceExertedByY(allPlanets);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");

            for (int j = 0; j < n; j++){
                allPlanets[j].update(dt, xForces[j], yForces[j]);
                allPlanets[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
