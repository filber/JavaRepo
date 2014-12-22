package com.filber.refactor._6_hierarchy;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * 以委托取代继承体系
 */
public class _67_ReplaceInheritanceWithDelegation_377 {
    //子类只使用了超类接口中的一部分,或是根本不需要继承而来的数据.

    class InheritanceStack<E> extends Vector<E> {
        public E push(E item) {
            super.addElement(item);
            return item;
        }

        public synchronized E pop() {
            E obj;
            int len = super.size();
            obj = peek();
            super.removeElementAt(len - 1);
            return obj;
        }

        public synchronized E peek() {
            int len = super.size();
            if (len == 0)
                throw new EmptyStackException();
            return super.elementAt(len - 1);
        }
    }

    //------------------------------------------------------------------
    class DelegationStack<E> {
        private Vector<E> vector = new Vector<E>();

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
