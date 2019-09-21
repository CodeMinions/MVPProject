package me.codeminions.mvpproject.mvp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import me.codeminions.mvpproject.R;
import me.codeminions.mvpproject.mvp.app.BaseActivity;
import me.codeminions.mvpproject.mvp.presenter.UserPresenter;
import me.codeminions.mvpproject.mvp.view.ILoginView;

public class MainActivity extends BaseActivity implements ILoginView {

    private EditText edName, edPwd;
    private ProgressBar loadBar;
    private Button  btnLogin;

    private UserPresenter presenter = new UserPresenter(this);

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        edName = findViewById(R.id.edit_phone);
        edPwd = findViewById(R.id.edit_password);
        loadBar = findViewById(R.id.load);
        btnLogin = findViewById(R.id.btn_submit);
        TextView txtClean = findViewById(R.id.txt_go_register);
        txtClean.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.clean();
            }
        });
    }

    public void onClickLogin(View v){
        showLoading();
        presenter.login(getUserName(), getUserPwd());
    }

    @Override
    public String getUserName() {
        return edName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return edPwd.getText().toString();
    }

    @Override
    public void showLoading() {
        loadBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cleanUserName() {
        edName.setText("");
    }

    @Override
    public void cleanUserPwd() {
        edPwd.setText("");
    }
}
