package dal.gravity;

public class GravityConstant implements GravityModel{
	private double gravity;
	public GravityConstant(double gravity){
		this.gravity = gravity;
	}
	
	public double getGravitationalField(){
		return this.gravity;
	}
}
