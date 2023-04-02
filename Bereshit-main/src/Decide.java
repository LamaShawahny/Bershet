public class Decide {

    double vs = 24.8;
    double hs = 932;
    double dist = 181*1000;
    double ang = 58.3;
    double hight = 13748;
    double time = 0;
    double dt = 1;
    double acc=0;
    double fuel = 121;
    double NN = 0.7;

    public Decide(){}

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public void setAng(double ang) {
        this.ang = ang;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public void setHs(double hs) {
        this.hs = hs;
    }

    public void setNN(double NN) {
        this.NN = NN;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setVs(double vs) {
        this.vs = vs;
    }

    public double getAcc() {
        return acc;
    }

    public double getAng() {
        return ang;
    }

    public double getDist() {
        return dist;
    }

    public double getDt() {
        return dt;
    }

    public double getFuel() {
        return fuel;
    }

    public double getHight() {
        return hight;
    }

    public double getHs() {
        return hs;
    }

    public double getNN() {
        return NN;
    }

    public double getTime() {
        return time;
    }

    public double getVs() {
        return vs;
    }

    public void Make_Decision(){
        if(this.hight>2191.5) {
            if(vs >25) {NN+=0.0029999999;}
            if(vs <20) {NN-=0.0020003078;}
            if(vs<25 && vs>20){NN-=0.0000041;}
        }
        else {
            if(this.ang>3) {this.ang-=2.99;}
            else {this.ang =1.2;}
            this.NN=0.5;
            if(this.hs<2) {this.hs=0;}
            if(this.hight<155) {
                this.NN=0.999;
                if(this.vs<4.8) {this.NN=0.6;}
            }
        }
        if(this.hight<4.9) {
            this.NN=0.01 ;
        }
    }
}
