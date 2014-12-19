package com.filber.refactor._5_invocation;

/**
 * 以明确函数取代参数
 */
public class _47_ReplaceParameterWithExplicitMethod_310 {
    private int height;
    private int width;

    public void setValue(String name,int value){
        if ("height".equals(name)){
            height = value;
        } else if ("width".equals(name)){
            width = value;
        } else{
            assert false:"Should Never reach here.";
        }
    }

    //-------------------------------------------------------------------------------------------------

    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}
