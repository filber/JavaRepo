package com.filber.refactor._3_field;

public class _22_ReplaceArrayWithObject_211 {
	
	class ArrayExample{
		void test(){
			String[] row = new String[3];
			row[0]="liverpool";	//Team
			row[1]="15";				//Win
			row[2]="3";					//Loose
			
			//Client Code
			String name = row[0];
			int wins = Integer.valueOf(row[1]);
			int looses = Integer.valueOf(row[2]);
		}
	}
	
	static class ObjectExample{
		static class Performance{
			String name;
			int wins;
			int looses;
			public String getName() {
				return name;
			}
			public int getWins() {
				return wins;
			}
			public int getLooses() {
				return looses;
			}
			public Performance(String name, int wins, int looses) {
				this.name = name;
				this.wins = wins;
				this.looses = looses;
			}
		}
		
		void test(){
			Performance performance = new Performance("liverpool", 15, 3);
			//Client Code
			String name = performance.getName();
			int wins = performance.getWins();
			int looses = performance.getLooses();
		}
	}
}
