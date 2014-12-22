package com.filber.refactor._5_invocation;

import sun.misc.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 以测试取代异常
 */
public class _56_ReplaceExceptionWithTest_340 {
    //异常只应该用于异常的,罕见的行为.
    //对于意料之内的错误,应用条件测试予以避免.

    private Stack<MyResource> available;
    private Stack<MyResource> allocated;

    static class MyResource extends Resource {
        public String getName() {
            return null;
        }

        public URL getURL() {
            return null;
        }

        public URL getCodeSourceURL() {
            return null;
        }

        public InputStream getInputStream() throws IOException {
            return null;
        }

        public int getContentLength() throws IOException {
            return 0;
        }
    }

    //------------------------------------------------------------------
    class BadCase {
        public Resource getResource() {
            MyResource resource;
            try {
                resource = available.pop();
                allocated.push(resource);
                return resource;
            } catch (EmptyStackException e) {
                resource = new MyResource();
                allocated.push(resource);
                return resource;
            }
        }
    }

    //-------------------------------------------------------------------------------------------------
    class GoodCase {
        public Resource getResource() {
            MyResource resource;
            if (available.isEmpty()) resource = new MyResource();
            else resource = available.pop();

            //Consolidate Duplicate Conditional Fragments
            allocated.push(resource);
            return resource;
        }
    }
}
