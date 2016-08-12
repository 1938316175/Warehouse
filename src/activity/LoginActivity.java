package activity;



import com.example.warehouse.R;
import com.example.warehouse.R.id;
import com.example.warehouse.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class LoginActivity extends Activity {

	private Button login_button;
	private Button register_button;
	private EditText account_et;
	private EditText password_et;
	private CheckBox password_see;
	private CheckBox password_save;
	
	private String str;
	
	public static final String FILENAME = "LoginAndRegister";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginorregister);

        login_button = (Button)findViewById(R.id.login);
        login_button.setOnClickListener(listener);
        register_button = (Button)findViewById(R.id.register);
        register_button.setOnClickListener(listener);
        account_et = (EditText)findViewById(R.id.acount);
        password_et = (EditText)findViewById(R.id.password);
        password_see = (CheckBox)findViewById(R.id.password_see);
        password_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password_see.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(password_see.isChecked()){
		               //����EditText������Ϊ�ɼ���
		               password_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
		            }else{
		               //��������Ϊ���ص�
		               password_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
		            }
			}
		});
        password_save = (CheckBox)findViewById(R.id.password_save);     
       
        SharedPreferences pref = getSharedPreferences(FILENAME, MODE_PRIVATE);
		Boolean save_query = pref.getBoolean("password_save_if", false);
		String account = pref.getString("account", "");
		String password = pref.getString("password", "");
		if(save_query){
			account_et.setText(account);
			password_et.setText(password);
			password_save.setChecked(true);
		}
    }

    OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login:

				if (!TextUtils.isEmpty(account_et.getText()) && !TextUtils.isEmpty(password_et.getText())) {
					if (query_register(account_et.getText().toString())) {
						//�Ѿ�ע�ᣬʵ�ֵ�¼���߼����
						Intent intent = new Intent(LoginActivity.this, AnythingActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(LoginActivity.this, "���˺�δע�ᣬ����ע����ٵ�¼��", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(LoginActivity.this, "�����˺������Ƿ��Ѿ���д����", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.register:
				if (!TextUtils.isEmpty(account_et.getText()) && !TextUtils.isEmpty(password_et.getText())) {
			//		Log.d("wangbin", "1 + " + account_et.getText().toString());
					if (!query_register(str = account_et.getText().toString())) {
						//ʵ��ע����߼�����
						SharedPreferences.Editor editor = getSharedPreferences(FILENAME,MODE_PRIVATE).edit();
						editor.putString("account", account_et.getText().toString());	
						editor.commit();
						
						Toast.makeText(LoginActivity.this, "ע��ɹ��������¼��������¼��ť��", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(LoginActivity.this, "���˺��Ѿ���ע�ᣬ��ֱ�ӵ�¼���ɣ�", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(LoginActivity.this, "ע��δ�ɹ��������˺������Ƿ���д��", Toast.LENGTH_SHORT).show();
				}
				
				break;
			}
			
		}
	};
	
	protected boolean query_register(String account) {
		SharedPreferences pref = getSharedPreferences(FILENAME, MODE_PRIVATE);
		String account_query = pref.getString("account", "nothing");
		Log.d("wangbin", account_query+",,"+account);
		if(account_query.equals(account)){
			Log.d("wangbin", account_query+",,"+account);
			return true;
		}
		else{
			return false;
		}
	}

	
	@Override
	protected void onPause() {
		super.onPause();
    	SharedPreferences.Editor editor = getSharedPreferences(FILENAME,MODE_PRIVATE).edit();
        //����Ƿ���Ҫ��������
        if(password_save.isChecked()){
            //����EditText������Ϊ�ɼ���
			editor.putBoolean("password_save_if", true);
			editor.putString("account", account_et.getText().toString());
			editor.putString("password", password_et.getText().toString());
			editor.commit();
         }
        else {
			SharedPreferences sharedPreferences = getSharedPreferences(FILENAME, 0);
			editor.putString("password", "");
		}
        
	}
	
}
