package com.zgy.accounts.auth;

import com.zgy.accounts.MyAccountAuthenticatorActivity;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyAccountAuthenticator extends AbstractAccountAuthenticator {

	private static final String TAG = "MyAccountAuthenticator";
	private Context mContext;

	public MyAccountAuthenticator(Context context) {
		super(context);
		mContext = context;
	}

	/**
	 * 跳转到添加账户
	 */
	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
		Log.d(TAG, ".addAccount");
		Log.d(TAG, "accountType=" + accountType + " - " + "authTokenType=" + authTokenType);
		Bundle ret = new Bundle();
		Intent intent = new Intent(mContext, MyAccountAuthenticatorActivity.class);
		intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
		ret.putParcelable(AccountManager.KEY_INTENT, intent);
		return ret;
		// return null;
	}

	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {
		Log.d(TAG, ".confirmCredentials");
		return null;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
		Log.d(TAG, ".editProperties");
		return null;
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) throws NetworkErrorException {
		Log.d(TAG, ".getAuthToken");
		return null;
	}

	@Override
	public String getAuthTokenLabel(String authTokenType) {
		Log.d(TAG, ".getAuthTokenLabel");
		return null;
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
		Log.d(TAG, ".hasFeatures");
		return null;
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) {
		Log.d(TAG, ".updateCredentials");
		return null;
	}

}
