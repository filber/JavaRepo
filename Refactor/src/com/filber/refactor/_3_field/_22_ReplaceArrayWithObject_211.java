package com.filber.refactor._3_field;

/**
 * 以对象取代数值
 */
public class _22_ReplaceArrayWithObject_211 {
    static class BadCase {
        void test() {
            String[] row = new String[3];
            row[0] = "liverpool";    //Team
            row[1] = "15";                //Win
            row[2] = "3";                    //Loose

            String name = row[0];
            int wins = Integer.valueOf(row[1]);
            int looses = Integer.valueOf(row[2]);
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class GoodCase {
        void test() {
            Performance performance = new Performance("liverpool", 15, 3);
            String name = performance.getName();
            int wins = performance.getWins();
            int looses = performance.getLooses();
        }

        static class Performance {
            private final String name;
            private final int wins;
            private final int looses;

            public Performance(String name, int wins, int looses) {
                this.name = name;
                this.wins = wins;
                this.looses = looses;
            }

            public String getName() {
                return name;
            }

            public int getWins() {
                return wins;
            }

            public int getLooses() {
                return looses;
            }
        }
    }
}
