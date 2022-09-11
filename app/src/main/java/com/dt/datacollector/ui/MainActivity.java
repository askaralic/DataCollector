package com.dt.datacollector.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.dt.datacollector.R;
import com.dt.datacollector.di.ActivityModule;
import com.dt.datacollector.di.AppComponent;
import com.dt.datacollector.di.DaggerActivityComponent;
import com.dt.datacollector.model.Persons;
import com.dt.datacollector.presenter.MainPresenter;
import com.dt.datacollector.utils.CSVParser;
import com.dt.datacollector.utils.GenUtils;
import com.dt.datacollector.utils.imagepicker.RxFileConverters;
import com.dt.datacollector.utils.imagepicker.RxFilePicker;
import com.dt.datacollector.utils.imagepicker.Sources;
import com.dt.datacollector.view.IMainView;

import org.joda.time.DateTime;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import com.commons.utils.FileUtils;
import com.commons.utils.TimeUtils;
import com.commons.utils.ToastUtils;
import com.commons.views.TypeFacedButton;
import com.commons.views.TypeFacedEditText;
import com.commons.views.TypeFacedTextView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 7/31/2019
 * Project : BaseApplication
 */
public class MainActivity extends BaseActivity implements IMainView {

    @Inject
    MainPresenter mPresenter;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnSubmit)
    TypeFacedButton btnSubmit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnClear)
    TypeFacedButton btnClear;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnProofOfIdentity)
    TypeFacedButton btnProofOfIdentity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txtDateOfBirth)
    TypeFacedTextView txtDateOfBirth;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtFamilyId)
    TypeFacedEditText edtFamilyId;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtFullName)
    TypeFacedEditText edtFullName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtFatherName)
    TypeFacedEditText edtFatherName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtMotherName)
    TypeFacedEditText edtMotherName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtSpouseName)
    TypeFacedEditText edtSpouseName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtBloodGroup)
    TypeFacedEditText edtBloodGroup;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtMemberStatus)
    TypeFacedEditText edtMemberStatus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtPrimaryMobile)
    TypeFacedEditText edtPrimaryMobile;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtAlternativeMobile)
    TypeFacedEditText edtAlternativeMobile;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtAnbiyam)
    TypeFacedEditText edtAnbiyam;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtHouseNumber)
    TypeFacedEditText edtHouseNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtEBNumber)
    TypeFacedEditText edtEBNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtWaterContractNumber)
    TypeFacedEditText edtWaterContractNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtMaritalStatus)
    TypeFacedEditText edtMaritalStatus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txtDateOfMarriage)
    TypeFacedTextView txtDateOfMarriage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtEducation)
    TypeFacedEditText edtEducation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtSocietyName)
    TypeFacedEditText edtSocietyName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtOccupation)
    TypeFacedEditText edtOccupation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtCompanyName)
    TypeFacedEditText edtCompanyName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtWorkingPlace)
    TypeFacedEditText edtWorkingPlace;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtWorkingCountry)
    TypeFacedEditText edtWorkingCountry;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtAdharNumber)
    TypeFacedEditText edtAdharNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.imgTakenProofOfIdentity)
    ImageView imgTakenProofOfIdentity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.layoutDOB)
    LinearLayout layoutDOB;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.layoutDateOfMarriage)
    LinearLayout layoutDateOfMarriage;



    private final CompositeDisposable disposables = new CompositeDisposable();
    private File selectedProofOfIdentity;
    DateTime selectedDateOfBirth;
    DateTime selectedDateOfMarriage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initViewsAndListeners() {
        layoutDOB.setOnClickListener(view -> showDOBPicker());
        layoutDateOfMarriage.setOnClickListener(view -> showDateOfMarriagePicker());
        btnSubmit.setOnClickListener(view -> {
            String fileName = "ExportedPersonsList";
            if(selectedProofOfIdentity == null){
                showMessage("Please upload proof of identity");
                return;
            }
            if(selectedDateOfBirth == null){
                showMessage("Please select date of birth");
                return;
            }
            if(selectedDateOfMarriage == null){
                showMessage("Please select date of marriage");
                return;
            }
            disposables.add(Observable.fromCallable(() -> {
                        CSVParser.saveEmployeeToCSVFile(fileName,getPerson(),MainActivity.this);
                        return true;
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((result) -> {
                        showMessage("Your information is saved successfully");
                        clearFields();
                    }));

        });
        btnProofOfIdentity.setOnClickListener(view -> {
            if(checkStoragePermission()) {
                disposables.add(RxFilePicker.with(getSupportFragmentManager()).requestImage(Sources.CAMERA)
                        .flatMap(uri -> RxFileConverters.uriToFile(MainActivity.this, uri, FileUtils.createTempFile(MainActivity.this,"","jpg"))).subscribe(file -> new Handler().post(() -> {
                            selectedProofOfIdentity= file;
                            Bitmap myBitmap = BitmapFactory.decodeFile(selectedProofOfIdentity.getAbsolutePath());
                            imgTakenProofOfIdentity.setImageBitmap(myBitmap);

                        }), Throwable::printStackTrace));
            }
        });
        btnClear.setOnClickListener(view -> clearFields());
    }

    private void clearFields() {
        edtFamilyId.setText("");
        edtFullName.setText("");
        edtFatherName.setText("");
        edtMotherName.setText("");
        edtSpouseName.setText("");
        edtBloodGroup.setText("");
        selectedDateOfBirth = null;
        edtMemberStatus.setText("");
        edtPrimaryMobile.setText("");
        edtAlternativeMobile.setText("");
        edtAnbiyam.setText("");
        edtHouseNumber.setText("");
        edtEBNumber.setText("");
        edtWaterContractNumber.setText("");
        edtMaritalStatus.setText("");
        selectedDateOfMarriage = null;
        edtEducation.setText("");
        edtSocietyName.setText("");
        edtOccupation.setText("");
        edtCompanyName.setText("");
        edtWorkingPlace.setText("");
        edtWorkingCountry.setText("");
        selectedProofOfIdentity = null;
        edtAdharNumber.getText().toString();
    }
    private boolean checkStoragePermission() {
        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
            return false;
        } else {
            return true;
        }
    }
    private List<Persons> getPerson() {
        List<Persons> personsList = new ArrayList<>();

        personsList.add(new Persons(
                edtFamilyId.getText().toString(),
                edtFullName.getText().toString(),
                edtFatherName.getText().toString(),
                edtMotherName.getText().toString(),
                edtSpouseName.getText().toString(),
                edtBloodGroup.getText().toString(),
                selectedDateOfBirth,
                edtMemberStatus.getText().toString(),
                edtPrimaryMobile.getText().toString(),
                edtAlternativeMobile.getText().toString(),
                edtAnbiyam.getText().toString(),
                edtHouseNumber.getText().toString(),
                edtEBNumber.getText().toString(),
                edtWaterContractNumber.getText().toString(),
                edtMaritalStatus.getText().toString(),
                selectedDateOfMarriage,
                edtEducation.getText().toString(),
                edtSocietyName.getText().toString(),
                edtOccupation.getText().toString(),
                edtCompanyName.getText().toString(),
                edtWorkingPlace.getText().toString(),
                edtWorkingCountry.getText().toString(),
                selectedProofOfIdentity,
                edtAdharNumber.getText().toString()
        ));
        return personsList;
    }
    private void showDOBPicker() {
        GenUtils.showDatePicker(MainActivity.this, new DateTime().minusYears(100),new DateTime(),selectedDateOfBirth, selectedDate -> {
            if (selectedDate.isBefore(new DateTime())) {
                selectedDateOfBirth = selectedDate;
                txtDateOfBirth.setText(TimeUtils.dateFmt.print(selectedDate));
            } else {
                ToastUtils.showAlert(MainActivity.this, getString(R.string.message_invalid_dob), TimeUtils.dateTimeFmt.print(selectedDateOfBirth), null);
            }
        });
    }
    private void showDateOfMarriagePicker() {
        GenUtils.showDatePicker(MainActivity.this, new DateTime().minusYears(100),new DateTime(),selectedDateOfMarriage, selectedDate -> {
            if (selectedDate.isBefore(new DateTime())) {
                selectedDateOfMarriage = selectedDate;
                txtDateOfMarriage.setText(TimeUtils.dateFmt.print(selectedDate));
            } else {
                ToastUtils.showAlert(MainActivity.this, getString(R.string.message_invalid_dob), TimeUtils.dateTimeFmt.print(selectedDateOfMarriage), null);
            }
        });
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectActivity(AppComponent appComponent) {
        DaggerActivityComponent.builder().appComponent(component).activityModule(new ActivityModule(this)).build().inject(this);
    }

}
