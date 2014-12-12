package com.filber.refactor._4_condition;

/**
 * Created by Administrator on 2014/12/3.
 */
public class _39_ReplaceConditionalWithPolymorphism_280{

    public class OldBird {
        public static final int EUROPEAN = 1;
        public static final int AFRICAN = 2;
        public static final int NORWEGIAN_BLUE = 3;
        private int type;
        private double voltage;
        private int numberOfCoconuts;
        private boolean isNailed;

        private double getBaseSpeed(){return 0;}

        private double getBaseSpeed(double voltage){return 0;}

        private double getLoadFactor(){return 0;}

        public double getWeight(){
            switch (type){
                case EUROPEAN:
                    return 1.5;
                case AFRICAN:
                    return 1.2;
                case NORWEGIAN_BLUE:
                    return 1.8;
            }
            throw new UnsupportedOperationException();
        }
        public double getSpeed(){
            switch (type){
                case EUROPEAN:
                    return getBaseSpeed();
                case AFRICAN:
                    return getBaseSpeed() - getLoadFactor()*numberOfCoconuts;
                case NORWEGIAN_BLUE:
                    return isNailed?0:getBaseSpeed(voltage);
            }
            throw new UnsupportedOperationException();
        }
    }
    //-------------------------------------------------------------------------------------------------
    static abstract class Bird{
        public static final int EUROPEAN = 1;
        public static final int AFRICAN = 2;
        public static final int NORWEGIAN_BLUE = 3;

        protected double getBaseSpeed(){return 0;}

        public static Bird createBird(int type){
            if (EUROPEAN==type){
                return new EuropeanBird();
            } else if(AFRICAN==type){
                return new AfricanBird();
            } else if(NORWEGIAN_BLUE==type){
                return new NorwegianBlueBird();
            }
            throw new IllegalArgumentException();
        }
        public abstract double getWeight();
        public abstract double getSpeed();
    }
    static class EuropeanBird extends Bird {
        @Override
        public double getWeight() {
            return 1.5;
        }
        @Override
        public double getSpeed() {
            return super.getBaseSpeed();
        }
    }
    static class AfricanBird extends Bird {
        private int numberOfCoconuts;
        private double getLoadFactor(){return 0;}
        @Override
        public double getWeight() {
            return 1.2;
        }
        @Override
        public double getSpeed() {
            return super.getBaseSpeed() - getLoadFactor()*numberOfCoconuts;
        }
    }
    static class NorwegianBlueBird extends Bird {
        private boolean isNailed;
        private double voltage;
        private double getBaseSpeed(double voltage){return 0;}
        @Override
        public double getWeight() {
            return 1.8;
        }
        @Override
        public double getSpeed() {
            return isNailed?0:getBaseSpeed(voltage);
        }
    }
}
