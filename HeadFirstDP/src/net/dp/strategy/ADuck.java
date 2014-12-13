package net.dp.strategy;

import net.dp.strategy.fly.FlyNoWay;
import net.dp.strategy.fly.FlyWithWings;
import net.dp.strategy.quack.MuteQuack;
import net.dp.strategy.quack.Squeak;

public class ADuck extends Duck {
    //在子类构造器中完成对父类中Strategy的初始化.
	public ADuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Squeak();
	}

	public void display() {
		System.out.println("I'm a duck model.");
	}
    //封装了Strategy的变化从而避免具体的Strategy被暴露在Client面前.
	public void changeBehavior() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new MuteQuack());
	}

	public static void main(String[] args) {
		ADuck aDuck = new ADuck();
		aDuck.display();
		aDuck.swim();
		aDuck.performFly();
		aDuck.performQuack();
        //Runtime Changes Strategy.
		aDuck.changeBehavior();
		aDuck.performFly();
		aDuck.performQuack();
	}
}
