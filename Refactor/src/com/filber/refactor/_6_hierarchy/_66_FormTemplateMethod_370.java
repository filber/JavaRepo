package com.filber.refactor._6_hierarchy;

/**
 * 塑造模板函数
 */
public class _66_FormTemplateMethod_370 {
    //函数以相同的顺序执行类似的操作,但各操作的细节不同

    class OldCustomer {
        public String statement(String name, int totalCharge, int frequentRenterPoints) {
            //head lines
            String result = "Rental Record for " + name + "\n";

            //footer lines
            result += "Amount owned is " + totalCharge + "\n";
            result += "You earned " + frequentRenterPoints + " frequent renter points.";
            return result;
        }

        public String htmlStatement(String name, int totalCharge, int frequentRenterPoints) {
            //head lines
            String result = "<H1>Rental Record for <EM>" + name + "</EM></H1><P>\n";

            //footer lines
            result += "<P>Amount owned is <EM>" + totalCharge + "</EM><P>\n";
            result += "You earned <EM>" + frequentRenterPoints + "</EM> frequent renter points.<P>";
            return result;
        }
    }

    //------------------------------------------------------------------

    interface Statement {
        String headerLines(String name);

        String footerLines(int totalCharge, int frequentRenterPoints);
    }

    static class TextStatement implements Statement {
        public String headerLines(String name) {
            return "Rental Record for " + name + "\n";
        }

        public String footerLines(int totalCharge, int frequentRenterPoints) {
            String result = "Amount owned is " + totalCharge + "\n";
            result += "You earned " + frequentRenterPoints + " frequent renter points.";
            return result;
        }
    }

    static class HtmlStatement implements Statement {
        public String headerLines(String name) {
            return "<H1>Rental Record for <EM>" + name + "</EM></H1><P>\n";
        }

        public String footerLines(int totalCharge, int frequentRenterPoints) {
            String result = "<P>Amount owned is <EM>" + totalCharge + "</EM><P>\n";
            result += "You earned <EM>" + frequentRenterPoints + "</EM> frequent renter points.<P>";
            return result;
        }
    }

    class Customer {
        private Statement statement;

        public void setStatement(Statement statement) {
            this.statement = statement;
        }

        public String printInfo(String name, int totalCharge, int frequentRenterPoints) {
            //head lines
            String result = statement.headerLines(name);

            //footer lines
            result += statement.footerLines(totalCharge, frequentRenterPoints);
            return result;
        }
    }
}
