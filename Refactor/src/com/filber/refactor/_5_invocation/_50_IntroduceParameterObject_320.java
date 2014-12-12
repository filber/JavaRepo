package com.filber.refactor._5_invocation;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2014/12/8.
 */
public class _50_IntroduceParameterObject_320 {
    static class BadCase{
        static class Entry{
            private double value;
            private Date chargeDate;
            Entry(double value, Date chargeDate) {
                this.value = value;
                this.chargeDate = chargeDate;
            }
            public double getValue() {
                return value;
            }
            public Date getChargeDate() {
                return chargeDate;
            }
        }
        static class Account{
            private List<Entry> entries;
            public double getFlowBetween(Date start,Date end){
                double result = 0.0;
                Iterator<Entry> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Entry entry = iterator.next();
                    if (entry.getChargeDate().equals(start)||entry.getChargeDate().equals(end)||
                            (entry.getChargeDate().after(start)&&entry.getChargeDate().before(end))){
                        result += entry.getValue();
                    }
                }
                return result;
            }
        }
    }
    static class GoodCase{
        static class Entry{
            private double value;
            private Date chargeDate;
            Entry(double value, Date chargeDate) {
                this.value = value;
                this.chargeDate = chargeDate;
            }
            public double getValue() {
                return value;
            }
            public Date getChargeDate() {
                return chargeDate;
            }
        }
        static class DateRange{
            private final Date start;
            private final Date end;
            DateRange(Date start, Date end) {
                this.start = start;
                this.end = end;
            }
            public boolean includes(Date date){
                if (date.equals(start)||date.equals(end)||(date.after(start)&&date.before(end))){
                    return true;
                } else return false;
            }
        }
        static class Account{
            private List<Entry> entries;
            public double getFlowBetween(DateRange range){
                double result = 0.0;
                Iterator<Entry> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Entry entry = iterator.next();
                    if (range.includes(entry.getChargeDate())){
                        result += entry.getValue();
                    }
                }
                return result;
            }
        }
    }
}
