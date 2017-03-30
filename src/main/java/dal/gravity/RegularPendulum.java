package dal.gravity;

/**
 * Represents a pendulum
 */
public class RegularPendulum extends AbstractPendulum {
    private double delta, iterations = 0;
    private double dissipation;
    private double lastTheta, lastVel, lastAccel;
    private GravityModel rpGravityModel;

    /**
     * Creates a new Pendulum instance 
     */
    public RegularPendulum (double inLength, double inMass, double inTheta0, GravityModel gravityModel,
		     double inDelta, double inDiss) {
	super (inLength, inMass, inTheta0, gravityModel);
	delta=inDelta;
	dissipation = inDiss;
	this.rpGravityModel = gravityModel;
	lastVel = 0;
	lastTheta = this.getMaxAngularDisplacement ();
	lastAccel = -(this.rpGravityModel.getGravitationalField () / this.getStringLength ())*Math.sin (lastTheta);
    }

    public RegularPendulum (double inLength, double inMass, double inTheta0, GravityModel gravityModel,
		     double inDelta) {
	this (inLength, inMass, inTheta0, gravityModel, inDelta, 0);
    }

    public void step () {
	iterations++;
	lastTheta = lastTheta + lastVel*delta;
	lastVel = lastVel + lastAccel*delta;
	lastAccel = - dissipation*lastVel - this.rpGravityModel.getGravitationalField () / this.getStringLength () * Math.sin (lastTheta);
    }

    public double getLastTheta () { return lastTheta; }
    public double getLastVelocity () { return lastVel; }
    public double getLastAcceleration () { return lastAccel; }
    public double getLastTime () { return iterations*delta; }
    public double getDissipationConstant () { return dissipation; }
        
    @Override
    public void setGravityModel(GravityModel gravityModel){
    	this.rpGravityModel = gravityModel;
    }	
}
