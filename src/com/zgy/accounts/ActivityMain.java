package com.zgy.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * *****************************************************
 * 
 * 
 * 热烈欢迎加入QQ开发群参与各种技术讨论: 88130145
 * 
 * 
 * *****************************************************
 * @Description:
 * @author: zhuanggy
 * @see:   
 * @since:      
 * @copyright © 35.com
 * @Date:2013-4-28
 */
public class ActivityMain extends Activity {

	private static final String TAG = "ActivityMain";
	private TextView textViewAccounts;
	private AccountManager mAccountManager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_accounts);

		textViewAccounts = (TextView) findViewById(R.id.manage_accounts_accountlist);
		mAccountManager = AccountManager.get(ActivityMain.this);

		Button newacc = (Button) findViewById(R.id.manage_accounts_newaccount);
		final Activity self = this;
		newacc.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 跳转添加账户页
				mAccountManager.addAccount(getString(R.string.accoynt_type), null, null, null, self, new AccountManagerCallback<Bundle>() {

					@Override
					public void run(AccountManagerFuture<Bundle> amfuture) {
						try {
							Log.d(TAG, amfuture.getResult().toString());
						} catch (Exception e) {
							Log.e(TAG, e.getMessage(), e);
						}
						listAccounts();
					}
				}, null);
			}
		});
		listAccounts();

	}

	private void listAccounts() {
		Account[] accounts = mAccountManager.getAccountsByType(getString(R.string.accoynt_type));

		textViewAccounts.setText("可以在系统设置-账户和同步中查看、添加、删除本应用的账户，也可以对单个账户进行设置，另外也可以控制同步操作\r\n\r\n\r\n本应用可以创建的账户类型为：account_type_zgy\r\n\r\n以下账户是本应用的账户:");
		for (Account account : accounts) {
			textViewAccounts.setText(textViewAccounts.getText().toString() + "\r\n\r\n" + account.name);
		}
	}

}
