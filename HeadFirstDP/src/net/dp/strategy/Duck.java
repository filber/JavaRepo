package net.dp.strategy;

import net.dp.strategy.fly.FlyBehavior;
import net.dp.strategy.quack.QuackBehavior;

public abstract class Duck {
    //两个Strategy声明为protected,从而在子类中可以对其实例化.
	protected FlyBehavior flyBehavior;
	protected QuackBehavior quackBehavior;

	public void performFly(){
		flyBehavior.fly();
	}
	public void performQuack(){
		quackBehavior.quack();
	}

    //Abstract:每一个子类必然存在的行为,但每一个子类又不同.
    public abstract void display();
    //Default:每一个子类必然存在的行为,但每一个子类又大致相同.
	public void swim(){
		System.out.println("All ducks float, even decoys!");
	}

    //将Strategy的Setter声明为public,使得Client也可以Change Strategy at Runtime.
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
}
