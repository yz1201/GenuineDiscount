package com.example.tyz1201.mytablayout.util;

import android.app.Activity;

/**
 * Created by tyz1201 on 2016/4/20.
 */
public class InterfaceSchdule {

    public static void createThread(final Activity activity, final BrandInflateHandler brandInflateHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                brandInflateHandler.openNewThread();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        brandInflateHandler.initUIThread();
                    }
                });
            }
        }).start();
    }

}
