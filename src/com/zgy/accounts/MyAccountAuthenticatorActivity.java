package com.zgy.accounts;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zgy.accounts.R;

/**
 * 添加账户页
 * 
 * @Description:
 * @author: zhuanggy
 * @see:
 * @since:
 * @copyright © 35.com
 * @Date:2013-4-28
 */
public class MyAccountAuthenticatorActivity extends AccountAuthenticatorActivity {

	private Button btnAdd;
	private EditText editName;
	private EditText editPwd;
	private String mName;
	private String mPwd;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.new_account);

		setTitle("添加账户");
		
		btnAdd = (Button) findViewById(R.id.new_account_done);
		editName = (EditText) findViewById(R.id.new_account_username);
		editPwd = (EditText) findViewById(R.id.new_account_password);

		final Activity self = this;
		btnAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 不允许为空
				if (!TextUtils.isEmpty(editName.getText()) && !TextUtils.isEmpty(editPwd.getText())) {
					mName = editName.getText().toString();
					mPwd = editPwd.getText().toString();

					// 在此遍历当前应用的所有账户，排除同名、
					boolean exist = false;
					Account[] accounts = AccountManager.get(MyAccountAuthenticatorActivity.this).getAccountsByType(getString(R.string.accoynt_type));
					for (Account a : accounts) {
						if (a.name.equals(mName)) {
							exist = true;
						}
					}
					if (exist) {
						Toast.makeText(MyAccountAuthenticatorActivity.this, "此用户已存在！", Toast.LENGTH_SHORT).show();
					} else {
						// 在此弹出进度窗，开启线程验证账户密码，

						// 密码验证完成后，发一个Handler消息。截获后添加账户
						Account account = new Account(mName, getString(R.string.accoynt_type));
						Bundle userdata = new Bundle();
						// userdata.putString("SERVER", server.getText().toString());
						AccountManager am = AccountManager.get(self);
						if (am.addAccountExplicitly(account, mName, userdata)) {
							Bundle result = new Bundle();
							// 还可添加其他参数，如token、password等
							result.putString(AccountManager.KEY_ACCOUNT_NAME, mName);
							result.putString(AccountManager.KEY_ACCOUNT_TYPE, getString(R.string.accoynt_type));
							setAccountAuthenticatorResult(result);
							finish();
						}
					}
				} else {
					Toast.makeText(MyAccountAuthenticatorActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
