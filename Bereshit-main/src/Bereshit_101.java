//import simulation.Moon;
/**
 * This class represents the basic flight controller of the Bereshit space craft.
 * @author Lama-Shadi
 *
 */
public class Bereshit_101 {
	public static final double WEIGHT_EMP = 165; // kg
	public static final double WEIGHT_FULE = 420; // kg
	public static final double WEIGHT_FULL = WEIGHT_EMP + WEIGHT_FULE; // kg
	public static final double MAIN_ENG_F = 430; // N
	public static final double SECOND_ENG_F = 25; // N
	public static final double MAIN_BURN = 0.15; //liter per sec, 12 liter per m'
	public static final double SECOND_BURN = 0.009; //liter per sec 0.6 liter per m'
	public static final double ALL_BURN = MAIN_BURN + 8*SECOND_BURN;
	public static PID pid;

	public static double accMax(double weight) {
		return acc(weight, true,8);
	}
	public static double acc(double weight, boolean main, int seconds) {
		double t = 0;
		if(main) {t += MAIN_ENG_F;}
		t += seconds*SECOND_ENG_F;
		double ans = t/weight;
		return ans;
	}
	public static void main(String[] args) {
		System.out.println("Start :-(");
		Decide d=new Decide();
		d.setNN(0.7);
		double weight = WEIGHT_EMP + d.getFuel();
		while(d.getHight()>0) {
			if(d.getTime()%10==0 || d.getHight()<100) {
				System.out.println("  Time:"+d.getTime()+","+"  Horizontal Speed:"+d.getHs()+","+"  Vertical Speed:"+d.getVs()+","+"  Hight:"+d.getHight()+","+"  Fuel:"+d.getFuel());
			}
			pid = new PID(0, 0, 1);
			pid.compute(0,0,true);
			d.Make_Decision();
			double ang_rad = Math.toRadians(d.getAng());
			double h_acc = Math.sin(ang_rad)*d.getAcc();
			double v_acc = Math.cos(ang_rad)*d.getAcc();
			double vacc = Moon.getAcc(d.getHs());
			d.setTime(d.getTime()+d.getDt());
			double dw = d.getDt()*ALL_BURN*d.getNN();
			if(d.getFuel()>0) {
				d.setFuel(d.getFuel()-dw);
				weight = WEIGHT_EMP + d.getFuel();
				d.setAcc(d.getNN()* accMax(weight));
			}
			else {
				d.setAcc(0);
			}
			v_acc -= vacc;
			if(d.getHs()>0) {d.setHs(d.getHs()-h_acc*d.getDt());}
			d.setDist(d.getDist()-d.getHs()*d.getDt());
			d.setVs(d.getVs()-v_acc*d.getDt());
			d.setHight(d.getHight()-d.getDt()*d.getVs());
		}
	}
}