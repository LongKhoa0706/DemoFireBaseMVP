package com.longkhoa.myapplication.Model;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.longkhoa.myapplication.Main3Activity;
import com.longkhoa.myapplication.MainActivity;
import com.longkhoa.myapplication.Presenter.PresenterImDangNhap;
import com.longkhoa.myapplication.View.ViewXuLyDangNhap;

public class PresenterXuLyDangNhap implements PresenterImDangNhap {
    ViewXuLyDangNhap viewXuLyDangNhap;
    private FirebaseAuth firebaseAuth;

    public PresenterXuLyDangNhap(ViewXuLyDangNhap viewXuLyDangNhap) {
        this.viewXuLyDangNhap = viewXuLyDangNhap;
    }

    @Override
    public void ktDangNhap(String tk, String mk) {
        if (tk.length() == 0 || mk.length() == 0) {
            viewXuLyDangNhap.dangNhapThatBai();
        } else {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(tk, mk)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                viewXuLyDangNhap.dangNhapThanhCong();
                            } else {
                                viewXuLyDangNhap.checkTaiKhoan();
                            }
                        }
                    });
        }
    }
}
