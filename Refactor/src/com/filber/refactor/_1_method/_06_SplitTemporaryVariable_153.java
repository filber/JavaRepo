package com.filber.refactor._1_method;

/**
 * 分解临时变量
 */
public class _06_SplitTemporaryVariable_153 {
    //场景:第一个力先作用,一段时间之后,第二个力开始作用(两个力同时作用)
	double _primaryForce;
	double _mass;
	double _secondaryForce;
    // 第一个力发生到第二个力发生之间的时间间隔
	int _delay;
	
	double badGetDistanceTravelled(int time) {
		double result;
		double acc=_primaryForce/_mass;
		int primaryTime = Math.min(time, _delay);
		result = 0.5*acc*primaryTime*primaryTime;
		int secondaryTime = time - _delay;
		if (secondaryTime > 0) {
			double primaryVel = acc*_delay;
			//此处对于acc发生了重复赋值行为,导致语义混淆
			acc = (_primaryForce+_secondaryForce)/_mass;
			result += primaryVel*secondaryTime + 0.5*acc*secondaryTime*secondaryTime;
		}
		return result;
	}

    //-------------------------------------------------------------------------------------------------
    //第一次:引入secondaryAcc将Temp分离
	double firstGoodGetDistanceTravelled(int time) {
		double result;
		//使用了primaryAcc
		double primaryAcc=_primaryForce/_mass;
		int primaryTime = Math.min(time, _delay);
		result = 0.5*primaryAcc*primaryTime*primaryTime;
		int secondaryTime = time - _delay;
		if (secondaryTime > 0) {
			double primaryVel = primaryAcc*_delay;
			//使用了secondaryAcc
			double secondaryAcc = (_primaryForce+_secondaryForce)/_mass;
			result += primaryVel*secondaryTime + 0.5*secondaryAcc*secondaryTime*secondaryTime;
		}
		return result;
	}

    //-------------------------------------------------------------------------------------------------
	//第二次:ExtractMethod将distance的计算提炼出来.
	private double getDistanceOnce(double initVel,int time,double force,double mass){
		double acc = force/mass;
		return initVel*time + 0.5*acc*time*time;
	}
	double secondGoodGetDistanceTravelled(int time) {
		double result = getDistanceOnce(0, Math.min(time, _delay), _primaryForce,_mass);
		int secondaryTime = time - _delay;
		if (secondaryTime > 0) {
			double primaryVel = (_primaryForce/_mass)*_delay;
			result += getDistanceOnce(primaryVel, secondaryTime, _primaryForce+_secondaryForce,_mass);
		}
		return result;
	}
}
