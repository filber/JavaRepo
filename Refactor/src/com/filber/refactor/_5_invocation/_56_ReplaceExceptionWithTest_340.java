package com.filber.refactor._5_invocation;

import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Administrator on 2014/12/9.
 */
public class _56_ReplaceExceptionWithTest_340 {
    private Stack<MyResource> available;
    private Stack<MyResource> allocated;

    static class MyResource extends Resource{
        @Override
        public String getName() {
            return null;
        }
        @Override
        public URL getURL() {
            return null;
        }
        @Override
        public URL getCodeSourceURL() {
            return null;
        }
        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }
        @Override
        public int getContentLength() throws IOException {
            return 0;
        }
    }

    public Resource oldGetResource(){
        MyResource resource;
        try{
            resource = available.pop();
            allocated.push(resource);
            return resource;
        }catch (EmptyStackException e){
            resource=new MyResource();
            allocated.push(resource);
            return resource;
        }
    }

    //-------------------------------------------------------------------------------------------------

    public Resource newGetResource(){
        MyResource resource;
        if (available.isEmpty()) resource = new MyResource();
        else resource = available.pop();
        //Consolidate Duplicate Conditional Fragments
        allocated.push(resource);
        return resource;
    }
}
