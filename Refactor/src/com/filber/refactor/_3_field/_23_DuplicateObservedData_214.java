package com.filber.refactor._3_field;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 复制被监视数据
 */
public class _23_DuplicateObservedData_214 {

    static boolean isNotInteger(String text) {
        if (text.isEmpty()) {
            return true;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    //展示层与逻辑层混淆在一起
    static class BadCase {
        static public class IntervalWindow extends Frame {
            TextField _startField;
            TextField _endField;
            TextField _lengthField;

            class SynFocus extends FocusAdapter {
                public void focusLost(FocusEvent e) {
                    Object source = e.getSource();
                    //新增组件的逻辑控制复杂.
                    if (source == _startField) {
                        StartFieldFocusLost(e);
                    } else if (source == _endField) {
                        EndFieldFocusLost(e);
                    } else if (source == _endField) {
                        LengthFieldFocusLost(e);
                    } else {
                        super.focusLost(e);
                    }
                }
            }
            //代码重复性太高.
            void StartFieldFocusLost(FocusEvent e) {
                if (isNotInteger(_startField.getText())) {
                    _startField.setText("0");
                }
                if (isNotInteger(_endField.getText())) {
                    _endField.setText("0");
                }
                calculateLength();
            }

            void EndFieldFocusLost(FocusEvent e) {
                if (isNotInteger(_startField.getText())) {
                    _startField.setText("0");
                }
                if (isNotInteger(_endField.getText())) {
                    _endField.setText("0");
                }
                calculateLength();
            }

            void LengthFieldFocusLost(FocusEvent e) {
                if (isNotInteger(_startField.getText())) {
                    _startField.setText("0");
                }
                if (isNotInteger(_lengthField.getText())) {
                    _lengthField.setText("0");
                }
                calculateEnd();
            }

            void calculateLength() {
                int start = Integer.parseInt(_startField.getText());
                int end = Integer.parseInt(_endField.getText());
                int length = end - start;
                _lengthField.setText(String.valueOf(length));
            }

            void calculateEnd() {
                int start = Integer.parseInt(_startField.getText());
                int length = Integer.parseInt(_lengthField.getText());
                int end = start + length;
                _endField.setText(String.valueOf(end));
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    static class GoodCase {
        //观察者类型枚举
        public enum FieldType {
            StartFieldObserver,
            EndFieldObserver,
            LengthFieldObserver
        }

        //字段值对象(属性均为private final)
        public class FieldItem {
            private final FieldType type;
            private final FieldObserver observer;

            public FieldItem(FieldType type, FieldObserver observer) {
                this.type = type;
                this.observer = observer;
            }
        }

        //显示层剥离掉大部分的属性和行为.
        static public class ObserverIntervalWindow extends Frame {
            private FieldObservable fieldObservable;

            public ObserverIntervalWindow(FieldItem[] fieldItems) throws HeadlessException {
                fieldObservable = new FieldObservable(fieldItems);
            }

            class SynFocus extends FocusAdapter {
                public void focusLost(FocusEvent e) {
                    fieldObservable.notifyObservers(e.getSource());
                }
            }
        }

        //抽取出DomainClass,担当被观察者的角色.
        static class FieldObservable extends Observable {

            private final Map<FieldType, FieldObserver> fieldItemMap = new ConcurrentHashMap<FieldType, FieldObserver>();

            FieldObservable(FieldItem[] fieldItems) {
                for (FieldItem fieldItem : fieldItems) {
                    fieldItemMap.put(fieldItem.type, fieldItem.observer);
                    FieldObserver.attachFieldObserver(fieldItem.type, fieldItem.observer, this);
                }
            }

            public void setProperty(FieldType type, int value) {
                ((TextField) fieldItemMap.get(type).source).setText(String.valueOf(value));
            }

            public int getProperty(FieldType type) {
                return Integer.valueOf(((TextField) fieldItemMap.get(type).source).getText());
            }
        }

        //观察者抽象父类
        static abstract class FieldObserver implements Observer {
            private Object source;
            private FieldObservable observable;

            //注册观察者的静态工厂
            public static void attachFieldObserver(FieldType type, Object field, FieldObservable observable) {
                FieldObserver fieldObserver = null;
                try {
                    fieldObserver = (FieldObserver) Class.forName(type.toString()).newInstance();
                } catch (Exception e) {
                    //变更受查异常为非受查异常
                    throw new IllegalArgumentException("Failed To Initialize An Observer");
                }

                fieldObserver.source = field;
                observable.addObserver(fieldObserver);
            }

            //观察者被通知后调用该方法
            public void update(Observable o, Object arg) {
                if (source != arg) return;
                FieldObservable observable = (FieldObservable) o;
                calculate(observable);
            }

            //使用模板模式解决子类的差异性
            protected abstract void calculate(FieldObservable observable);
        }

        static class StartFieldObserver extends FieldObserver {
            protected void calculate(FieldObservable observable) {
                int start = observable.getProperty(FieldType.StartFieldObserver);
                int end = observable.getProperty(FieldType.EndFieldObserver);
                observable.setProperty(FieldType.LengthFieldObserver, end - start);
            }
        }

        static class LengthFieldObserver extends FieldObserver {
            protected void calculate(FieldObservable observable) {
                int start = observable.getProperty(FieldType.StartFieldObserver);
                int end = observable.getProperty(FieldType.EndFieldObserver);
                observable.setProperty(FieldType.LengthFieldObserver, end - start);
            }
        }

        static class EndFieldObserver extends FieldObserver {
            protected void calculate(FieldObservable observable) {
                int start = observable.getProperty(FieldType.StartFieldObserver);
                int length = observable.getProperty(FieldType.LengthFieldObserver);
                observable.setProperty(FieldType.EndFieldObserver, start + length);
            }
        }
    }
}
