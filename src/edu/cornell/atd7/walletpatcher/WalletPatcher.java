package edu.cornell.atd7.walletpatcher;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;



public class WalletPatcher implements IXposedHookLoadPackage {
	public static final String MY_PACKAGE_NAME = WalletPatcher.class.getPackage()
			.getName();
	public static final String TAG = "EntropyWalletPatch";

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		if (lpparam.packageName.equals("com.google.android.apps.walletnfcrel")) {
			ClassLoader classLoader = lpparam.classLoader;

			XC_MethodReplacement truereplacer = new XC_MethodReplacement() {
				protected Object replaceHookedMethod(
						XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
						throws Throwable {
					return Boolean.valueOf("True");
					
				}
			};
			

			XC_MethodReplacement falsereplacer = new XC_MethodReplacement() {
				protected Object replaceHookedMethod(
						XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
						throws Throwable {
					return Boolean.valueOf("False");
					
				}
			};
/*			
			XC_MethodHook useragentprinter = new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					String result = (String) param.getResult();
					Log.i(TAG,result);
				}
				
			};
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.UserAgentFormatter",
					classLoader, "buildUserAgentString", String.class, int.class, String.class, String.class, String.class, String.class, String.class, useragentprinter);
*/



			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.DeviceCapabilityManagerImpl",
					classLoader, "checkWhitelistEntries", String.class, String[].class, truereplacer);


  			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.datamanager.local.UserInfoManagerImpl",
					classLoader, "isWalletAllowedForUserInCountry", truereplacer);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.security.EnvironmentProperty$1",
					classLoader, "checkDevice", XposedHelpers.findClass("com.google.android.apps.wallet.security.EnvironmentInfo",classLoader),
					falsereplacer);
			
/*
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.DeviceCapabilityManagerImpl",
					classLoader, "isWhitelisted", truereplacer);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.WalletRestrictionCheckerImpl",
					classLoader, "checkAnyRequiredFeatureEnabledOrCrash", java.util.Set.class,
					XC_MethodReplacement.DO_NOTHING);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.WalletRestrictionCheckerImpl",
					classLoader, "checkAllDeviceCapabilitiesExistOrCrash", java.util.Set.class,
					XC_MethodReplacement.DO_NOTHING);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.WalletRestrictionCheckerImpl",
					classLoader, "checkAllRestrictionsSatisfied", java.util.Set.class,
					truereplacer);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.WalletRestrictionCheckerImpl",
					classLoader, "checkAllRestrictionsSatisfiedOrFinishActivity", android.app.Activity.class,
					truereplacer);
			
			XposedHelpers.findAndHookMethod("com.google.android.apps.wallet.util.WalletRestrictionCheckerImpl",
					classLoader, "checkAllRestrictionsSatisfiedOrStopService", android.app.Service.class,
					truereplacer);
*/
		}

	}
	
}
