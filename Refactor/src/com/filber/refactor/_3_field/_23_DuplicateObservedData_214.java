package com.filber.refactor._3_field;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Observable;
import java.util.Observer;

public class _23_DuplicateObservedData_214 {
	//展示层与逻辑层混在一起
	static public class IntervalWindow extends Frame{
		TextField _startField;
		TextField _endField;
		TextField _lengthField;
		class SynFocus extends FocusAdapter{
			public void focusLost(FocusEvent e) {
				Object source = e.getSource();
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
		void StartFieldFocusLost(FocusEvent e){
			if (isNotInteger(_startField.getText())) {
				_startField.setText("0");
			}
			if (isNotInteger(_endField.getText())) {
				_endField.setText("0");
			}
			calculateLength();
		}
		void EndFieldFocusLost(FocusEvent e){
			if (isNotInteger(_startField.getText())) {
				_startField.setText("0");
			}
			if (isNotInteger(_endField.getText())) {
				_endField.setText("0");
			}
			calculateLength();
		}
		void LengthFieldFocusLost(FocusEvent e){
			if (isNotInteger(_startField.getText())) {
				_startField.setText("0");
			}
			if (isNotInteger(_lengthField.getText())) {
				_lengthField.setText("0");
			}
			calculateEnd();
		}
		void calculateLength(){
			int start = Integer.parseInt(_startField.getText());
			int end = Integer.parseInt(_endField.getText());
			int length = end - start;
			_lengthField.setText(String.valueOf(length));
		}
		void calculateEnd(){
			int start = Integer.parseInt(_startField.getText());
			int length = Integer.parseInt(_lengthField.getText());
			int end = start + length;
			_endField.setText(String.valueOf(end));
		}
	}

    static public class ObserverIntervalWindow extends Frame{
        FieldObservable fieldObservable;
        TextField _startField;
        TextField _endField;
        TextField _lengthField;

        public ObserverIntervalWindow() throws HeadlessException {
            fieldObservable = new FieldObservable(_startField,_endField,_lengthField);
            StartFieldObserver startFieldObserver = new StartFieldObserver(_startField);
            fieldObservable.addObserver(startFieldObserver);
            EndFieldObserver endFieldObserver = new EndFieldObserver(_endField);
            fieldObservable.addObserver(endFieldObserver);
            LengthFieldObserver lengthFieldObserver = new LengthFieldObserver(_lengthField);
            fieldObservable.addObserver(lengthFieldObserver);
        }

        static class FieldObservable extends  Observable{
            final TextField _startField;
            final TextField _endField;
            final TextField _lengthField;
            FieldObservable(TextField _startField, TextField _endField, TextField _lengthField) {
                this._startField = _startField;
                this._endField = _endField;
                this._lengthField = _lengthField;
            }
            public void setStart(int start){
                _startField.setText(String.valueOf(start));
            }
            public void setEnd(int end){
                _endField.setText(String.valueOf(end));
            }
            public void setLength(int length){
                _lengthField.setText(String.valueOf(length));
            }
            public int getStart() {
                return Integer.parseInt(_startField.getText());
            }
            public int getEnd() {
                return Integer.parseInt(_endField.getText());
            }
            public int getLength() {
                return Integer.parseInt(_lengthField.getText());
            }
        }

        static class StartFieldObserver implements Observer{
            StartFieldObserver(Object source) {
                this.source = source;
            }
            Object source;
            @Override
            public void update(Observable o, Object arg) {
                if (source == arg) {
                    FieldObservable observable = (FieldObservable)o;
                    int start = observable.getStart();
                    int end = observable.getEnd();
                    int length = end - start;
                    observable.setLength(length);
                }
            }
        }
        static class LengthFieldObserver implements Observer{
            LengthFieldObserver(Object source) {
                this.source = source;
            }
            Object source;
            @Override
            public void update(Observable o, Object arg) {
                if (source == arg) {
                    FieldObservable observable = (FieldObservable)o;
                    int start = observable.getStart();
                    int end = observable.getEnd();
                    int length = end - start;
                    observable.setLength(length);
                }
            }
        }
        static class EndFieldObserver implements Observer{
            EndFieldObserver(Object source) {
                this.source = source;
            }
            Object source;
            @Override
            public void update(Observable o, Object arg) {
                if (source == arg) {
                    FieldObservable observable = (FieldObservable)o;
                    int start = observable.getStart();
                    int length = observable.getLength();
                    int end = start + length;
                    observable.setEnd(end);
                }
            }
        }

        class SynFocus extends FocusAdapter{
            public void focusLost(FocusEvent e) {
                fieldObservable.notifyObservers(e.getSource());
            }
        }
    }

    static boolean isNotInteger(String text){
		if(text.isEmpty()){
			return true;
		}
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isDigit(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}
