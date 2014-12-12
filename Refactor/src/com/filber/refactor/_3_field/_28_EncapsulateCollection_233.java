package com.filber.refactor._3_field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/12/2.
 */
public class _28_EncapsulateCollection_233 {

    static class Course{
        private String name;
        private boolean isAdvanced;
        Course(String name, boolean isAdvanced) {
            this.name = name;
            this.isAdvanced = isAdvanced;
        }
        public boolean isAdvanced() {
            return isAdvanced;
        }
    }

    static class OldPerson{
        Set<Course> courses;
        public Set<Course> getCourses() {
            return courses;
        }
        public void setCourses(Set<Course> courses) {
            this.courses = courses;
        }
    }

    static class NewPerson{
        Set<Course> courses = new HashSet<Course>();
        public boolean addCourse(Course course){
            return courses.add(course);
        }
        public boolean removeCourse(Course course){
            return courses.remove(course);
        }
        public boolean addAllCourses(Set<Course> courses){
            return this.courses.addAll(courses);
        }
        public boolean removeAllCourses(Set<Course> courses){
            return this.courses.removeAll(courses);
        }
        /**
         * 返回的集合是不可变对象
         */
        public Set<Course> getCourses(){
            return (Set<Course>) Collections.unmodifiableCollection(courses);
        }
    }
}
