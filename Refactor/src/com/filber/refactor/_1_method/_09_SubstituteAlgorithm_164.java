package com.filber.refactor._1_method;

import java.util.Arrays;
import java.util.List;

/**
 * 替换算法
 */
public class _09_SubstituteAlgorithm_164 {

	String badFoundPerson(String[] people){
        //一个需要通读整个代码段的算法.
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
    //-------------------------------------------------------------------------------------------------
	String goodFoundPerson(String[] people){
        //一个更直白明了的算法.
		List<String> names = Arrays.asList(new String[]{"Don","John","Kent"});
		for (int i = 0; i < people.length; i++) {
			if (names.contains(people[i])) {
				return people[i];
			}
		}
		return "";
	}
}
