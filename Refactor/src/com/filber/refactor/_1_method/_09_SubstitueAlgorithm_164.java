package com.filber.refactor._1_method;

import java.util.Arrays;
import java.util.List;

public class _09_SubstitueAlgorithm_164 {

	String badFoundPerson(String[] people){
		for (int i = 0; i < people.length; i++) {
			if ("Don".equals(people[i])) {
				return "Don";
			}
			if ("John".equals(people[i])) {
				return "John";
			}
			if ("Kent".equals(people[i])) {
				return "Kent";
			}
		}
		return "";
	}
	
	String goodFoundPerson(String[] people){
		List<String> names = Arrays.asList(new String[]{"Don","John","Kent"});
		for (int i = 0; i < people.length; i++) {
			if (names.contains(people[i])) {
				return people[i];
			}
		}
		return "";
	}
}
