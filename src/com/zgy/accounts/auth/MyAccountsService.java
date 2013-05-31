package com.zgy.accounts.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 在系统设置里的账户和同步中添加账户时会开启此Service
 * 
 * @Description:
 * @author: zhuanggy
 * @see:
 * @since:
 * @copyright © 35.com
 * @Date:2013-4-28
 */
public class MyAccountsService extends Service {

	private static final String TAG = "MyAccountsService";

	private MyAccountAuthenticator mAccountAuthenticator;

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, "MyAccountsService on bind!!");
		IBinder ret = null;
		if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
			if (mAccountAuthenticator == null) {
				mAccountAuthenticator = new MyAccountAuthenticator(MyAccountsService.this);
			}
		}
		ret = mAccountAuthenticator.getIBinder();
		return ret;
	}

}
