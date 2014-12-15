package com.filber.refactor._3_field;

/**
 * 以数据类取代记录
 */
public class _29_ReplaceRecordWithDataClass_242 {

    class BadCase {
        //"哑"数据对象
        public class BindCardInfo {
            private String bindId;//绑卡ID
            private String deleteFlag;////删除标识0正常 1删除

            public BindCardInfo(String bindId, String deleteFlag) {
                this.bindId = bindId;
                this.deleteFlag = deleteFlag;
            }

            public String getBindId() {
                return bindId;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }
        }
    }

    //------------------------------------------------------------------
    class GoodCase {
        public class BindCardInfo {
            public static final String DELETED = "1";

            private String bindId;//绑卡ID
            private String deleteFlag;////删除标识0正常 1删除

            public BindCardInfo(String bindId, String deleteFlag) {
                this.bindId = bindId;
                this.deleteFlag = deleteFlag;
            }

            public String getBindId() {
                return bindId;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            //通过重构进一步赋予额外的行为
            public boolean isDeleted() {
                return DELETED.equals(getDeleteFlag());
            }
        }
    }
}
