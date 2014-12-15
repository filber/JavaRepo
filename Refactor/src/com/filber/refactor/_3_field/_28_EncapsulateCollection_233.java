package com.filber.refactor._3_field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 封装集合
 */
public class _28_EncapsulateCollection_233 {

    class Course {
        private String name;
        private boolean isAdvanced;

        Course(String name, boolean isAdvanced) {
            this.name = name;
            this.isAdvanced = isAdvanced;
        }

        public boolean isAdvanced() {
            return isAdvanced;
        }

        public String getName() {
            return name;
        }
    }

    //------------------------------------------------------------------
    class BadCase {
        class Person {
            Set<Course> courses;

            public Set<Course> getCourses() {
                return courses;
            }

            public void setCourses(Set<Course> courses) {
                this.courses = courses;
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        class Person {
            Set<Course> courses = new HashSet<Course>();

            //追加担任HideDelegate角色的函数提供给Client间接访问集合.
            public boolean addCourse(Course course) {
                return courses.add(course);
            }

            public boolean removeCourse(Course course) {
                return courses.remove(course);
            }

            public boolean addAllCourses(Set<Course> courses) {
                return this.courses.addAll(courses);
            }

            public boolean removeAllCourses(Set<Course> courses) {
                return this.courses.removeAll(courses);
            }

            public Set<Course> getCourses() {
                return (Set<Course>) Collections.unmodifiableCollection(courses);//返回的集合是不可变对象
            }
        }
    }

}
