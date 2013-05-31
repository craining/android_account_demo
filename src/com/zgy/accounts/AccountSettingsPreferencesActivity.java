package com.zgy.accounts;

import android.accounts.Account;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.zgy.accounts.R;

/**
 * 系统设置里，账户和同步里，点击账户，点击【账户设置】，跳转至此Activity
 * 
 * @Description:
 * @author: zhuanggy
 * @see:
 * @since:
 * @copyright © 35.com
 * @Date:2013-4-28
 */
public class AccountSettingsPreferencesActivity extends PreferenceActivity {

	private Account mAccount;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			if (b.containsKey("account")) {

				try {
					mAccount = (Account) b.get("account");
					if (mAccount != null) {
						Log.e("AccountSettingsPreferencesActivity", "account" + mAccount.toString());
						setTitle(mAccount.name);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		addPreferencesFromResource(R.xml.preferences_sources);

	}
}
