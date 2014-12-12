package com.filber.refactor._6_hierarchy;

import java.util.EmptyStackException;
import java.util.Vector;

public class _67_ReplaceInheritanceWithDelegation_377 {

    //    class MyStack<E> extends Vector<E>{
    class MyStack<E>{
        //        private Vector<E> vector= this;
        private Vector<E> vector= new Vector<E>();

        public E push(E item) {
            vector.addElement(item);
            return item;
        }

        public synchronized E pop() {
            E obj;
            int len = vector.size();
            obj = peek();
            vector.removeElementAt(len - 1);
            return obj;
        }

        public synchronized E peek() {
            int len = vector.size();
            if (len == 0)
                throw new EmptyStackException();
            return vector.elementAt(len - 1);
        }
    }

}
