package com.restaurant.arrifqiaziz.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CommonUtil {

    static Gson gson;

    static {
        if (gson == null)
            gson = new GsonBuilder()
                    .setExclusionStrategies()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showSnack(Activity context, String message) {
        Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }

    public static void formatDateMountDay(String string, String eDate, String eDay) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatToView = new SimpleDateFormat(" dd, yyyy");

        Date convertedDate = new Date();
        try {
            convertedDate = formatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String bulan = DialogUtils.convertBulan(convertedDate.getMonth());
        String tgl = formatToView.format(convertedDate.getTime());
        eDate = (bulan + tgl);
        eDay = (convertHari(convertedDate.getDay()));
    }

    public static void setImage(Context context, String url, ImageView imageView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5.f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

//        Glide.with(context).load(Constans.WEB_URL_IMAGE + url).placeholder(circularProgressDrawable).into(imageView);
    }

    public static void dialogTanggal(Context context, final EditText etDate) {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDate.setText(dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public static void dialogTanggal(Context context, final EditText etDate, int max) {
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.add(Calendar.DATE, max);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                etDate.setText(dateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMaxDate(newCalendar.getTimeInMillis());
    }

    public static String generalFormatDate(String dateAsal, String formatAsal, String formatTujuan) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatAsal);
        SimpleDateFormat formatToView = new SimpleDateFormat(formatTujuan);

        Date convertedDate = new Date();
        try {
            convertedDate = formatter.parse(dateAsal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String tglKlaim = formatToView.format(convertedDate);

        return tglKlaim;
    }

    public static String formatDate(String string, String toFormate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        SimpleDateFormat formatToView = new SimpleDateFormat(toFormate);

        Date convertedDate = new Date();
        try {
            convertedDate = formatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String tglKlaim = formatToView.format(convertedDate);

        return tglKlaim;
    }

    public static String stringToDate(String tanggal) {
        SimpleDateFormat fromString = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fromString.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat fromDate = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        String dateTime = fromDate.format(date);
        return dateTime;
    }

    public static String stringToDateSlash(String tanggal) {
        SimpleDateFormat fromString = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        Date date = null;
        try {
            date = fromString.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat fromDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateTime = fromDate.format(date);
        return dateTime;
    }

    public static String stringToDateSlash2(String tanggal) {
        SimpleDateFormat fromString = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fromString.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat fromDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateTime = fromDate.format(date);
        return dateTime;
    }

    public static String stringToDate2(String tanggal) {
        SimpleDateFormat fromString = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fromString.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat fromDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateTime = fromDate.format(date);
        return dateTime;
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = df.format(date);
        return stringDate;
    }

    public static String getHariFromDate(Date tanggal) {
        DateFormat format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        String day = format.format(tanggal);
        switch (day) {
            case "Sunday":
                day = "MINGGU";
                break;
            case "Monday":
                day = "SENIN";
                break;
            case "Tuesday":
                day = "SELASA";
                break;
            case "Wednesday":
                day = "RABU";
                break;
            case "Thursday":
                day = "KAMIS";
                break;
            case "Friday":
                day = "JUMAT";
                break;
        }
        return day;
    }

    public static void dialogTanggalFormat(Context context, final EditText etDate, final String format) {
        final DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        dateFormat.setTimeZone(tz);
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDate.setText(dateFormat.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public static String getCurrentDate(String fmt) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        SimpleDateFormat format = new SimpleDateFormat(fmt);
//        Date date = new Date();
        return format.format(cal.getTime());
    }

    public static void dialogShow(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton("OK", onClickListener);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    public static String convertTanggalToHari(int num) {
        String hari = "";
        switch (num) {
            case 1:
                hari = "Senin";
                break;
            case 2:
                hari = "Selasa";
                break;
            case 3:
                hari = "Rabu";
                break;
            case 4:
                hari = "Kamis";
                break;
            case 5:
                hari = "Jumat";
                break;
            case 6:
                hari = "Sabtu";
                break;
            case 0:
                hari = "Minggu";
                break;
        }
        return hari;
    }

    public static String convertHari(int num) {
        String hari = "";
        switch (num) {
            case 1:
                hari = "SENIN";
                break;
            case 2:
                hari = "SELASA";
                break;
            case 3:
                hari = "RABU";
                break;
            case 4:
                hari = "KAMIS";
                break;
            case 5:
                hari = "JUMAT";
                break;
            case 6:
                hari = "SABTU";
                break;
            case 0:
                hari = "MINGGU";
                break;
        }
        return hari;
    }


    public static JSONObject convertStringToJson(String[] parameter, String[] value) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < parameter.length; i++) {
            try {
                jsonObject.put(parameter[i], value[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static boolean validateEmptyEntries(ArrayList<View> fields) {
        boolean check = true;
        for (int i = 0; i < fields.size(); i++) {
            EditText currentField = (EditText) fields.get(i);
            if (currentField.getText().toString().length() <= 0) {
                currentField.setError("Please fill out this field");
//                showToast("Please fill out this field");
                check = false;
            }
        }
        return check;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean verifyPermission(Context activity, String permissionCode) {
        if (ContextCompat.checkSelfPermission(activity, permissionCode) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String getFormattedPriceIndonesia(Double price) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String result = kursIndonesia.format(price);
        return result;
    }

    public static List<Date> getDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static void dialogArray(Context context, final String[] items, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                .setTitle("Organization Type")
                .setItems(items, clickListener);

        Dialog dialog = builder.create();
        dialog.show();
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void dialogMultipleArray(Context context, final String[] items, boolean[] checkedItem, DialogInterface.OnMultiChoiceClickListener multiChoiceClickListener, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).
//                .setTitle("Organization Type")
        setMultiChoiceItems(items, checkedItem, multiChoiceClickListener)
                .setPositiveButton("PILIH", clickListener);

        Dialog dialog = builder.create();
        dialog.show();
    }

    public static boolean[] isCheckedDialog(String[] items, String value) {
        final boolean[] checkedItem = new boolean[items.length];
        String[] separated = value.split(",");
        for (int i = 0; i < separated.length; i++) {
            for (int j = 0; j < items.length; j++) {
                if (separated[i].contains(items[j])) {
                    checkedItem[j] = true;
                }
            }
        }

        for (int i = 0; i < checkedItem.length; i++) {
            Log.d("CheckedDialog", "isCheckedDialog: " + checkedItem[i] + " " + items[i]);
        }

        return checkedItem;
    }


    public static Bitmap getFacebookProfilePicture(String userID) {
        URL imageURL = null;
        try {
            imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static void toggleNotification(Context context, boolean value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("SHOW_NOTIFICATION", value);
        editor.apply();
    }

    public static boolean getNotificationState(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("SHOW_NOTIFICATION", true);
    }

    public static void vibrate(Context context, long millis) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(millis);
        }
    }

}
