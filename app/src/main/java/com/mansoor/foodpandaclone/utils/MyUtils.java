package com.mansoor.foodpandaclone.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.mansoor.foodpandaclone.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.BuildConfig;


public class MyUtils {

    Context mContext;

    public MyUtils(Context context){
        this.mContext = context;
    }

    public boolean isNetworkAvailable() {

        final ConnectivityManager connectivityManager = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static String toCSV(ArrayList<Integer> array) {
        String result = "";

        if (array.size() > 0) {
            StringBuilder sb = new StringBuilder();

            for (int s : array) {
                sb.append(s).append(",");
            }

            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
    }
    public void showToast(String toastString){
        Toast.makeText(mContext,toastString, Toast.LENGTH_SHORT).show();
    }
    public void showInfoToast(String toastString){
        //Toasty.info(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,toastString, Toast.LENGTH_SHORT).show();
    }
    public void showWarningToast(String toastString){
        //Toasty.warning(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,toastString, Toast.LENGTH_SHORT).show();
    }
    public void showErrorToast(String toastString){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,toastString, Toast.LENGTH_SHORT).show();
    }
    public void showNoRecordFoundToast(){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,mContext.getString(R.string.no_record_found), Toast.LENGTH_SHORT).show();
    }
    public void showNoNetworkToast(){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,mContext.getString(R.string.no_network), Toast.LENGTH_SHORT).show();
    }
    public void showNoFieldEmptyToast(){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,mContext.getString(R.string.no_network), Toast.LENGTH_SHORT).show();
    }
    public void showNoLocationEnabledToast(){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,mContext.getString(R.string.no_loc), Toast.LENGTH_SHORT).show();
    }
    public void showNoNeRecordFoundToast(){
        //Toasty.error(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,mContext.getString(R.string.no_record_found), Toast.LENGTH_SHORT).show();
    }
    public void showSuccessToast(String toastString){
        //Toasty.success(mContext, toastString, Toast.LENGTH_SHORT, true).show();
        Toast.makeText(mContext,toastString, Toast.LENGTH_SHORT).show();
    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
       // System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);

        return formattedDate;
    }
    public String getCurrentMonth(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getCurrentYear(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getCurrentMontName(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());
        return month_name;
    }

    public float getDeviceWidthDP(){
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return dpWidth;
    }
    public Drawable getResourceDrawableFromName(String resourceName){
        Drawable drawable = null;
        Resources resources = mContext.getResources();
        try {
            showErrorToast(resourceName);
            final int resourceId = resources.getIdentifier(resourceName, "drawable",
                    mContext.getPackageName());
            showInfoToast("id "+resourceId);
            drawable =  resources.getDrawable(resourceId);
        }
        catch (Exception e){
            showInfoToast("id "+e.getMessage());
            e.getMessage();
            final int resourceId = resources.getIdentifier("ic_generic_application", "drawable",
                    mContext.getPackageName());
            drawable =  resources.getDrawable(resourceId);
        }
        return drawable;
    }
    public int getResourceIDFromName(String resourceName){
        int drawableID = 0;
        Resources resources = mContext.getResources();
        try {
           // showErrorToast(resourceName);
            final int resourceId = resources.getIdentifier(resourceName, "drawable",
                    mContext.getPackageName());
            drawableID = resourceId;
           // showInfoToast("id "+resourceId);
            //drawable =  resources.getDrawable(resourceId);
        }
        catch (Exception e){
           // showInfoToast("id "+e.getMessage());
            e.getMessage();
//            final int resourceId = resources.getIdentifier("ic_generic_application", "drawable",
//                    mContext.getPackageName());
//            drawable =  resources.getDrawable(resourceId);
        }
        return drawableID;
    }
    public String getCurrentFormattedDate(){
        Date c = Calendar.getInstance().getTime();
        // System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public String getCurrentTime(){
        Date c = Calendar.getInstance().getTime();
        // System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String formattedTime = df.format(c);

        return formattedTime;
    }

    public int getCurrentHour(){

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour;
    }
    public long convertLongToMB(long fileSize){

        //long l = 9999999990L;
        long MEGABYTE = 1024L * 1024L;
        long b = fileSize / MEGABYTE;
        return b;
    }

    public int getCurrentMinutes(){

        int hour = Calendar.getInstance().get(Calendar.MINUTE);
        return hour;
    }

    public String printKeyHash() {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = mContext.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = mContext.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", mContext.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }


    public String getTime(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm aa", cal).toString();
        return date;
    }

    public String getFormatedTime(long time) throws ParseException {

        String old = getTime(time);
        SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
        String time24 = outFormat.format(inFormat.parse(old));
        return  time24;
    }




    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String convertDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = "";
        dateTime = dateFormat.format(date);


        return dateTime;
    }

    public Date convertStringToDate(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public Date convertToDateDate(String input){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        Date date = null;
        try {
            date = format.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String convertToDateShowFormat(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = null;
        try {
            date = format.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f2 = new SimpleDateFormat("MM-dd-yyyy");
        String x = f2.format(date);
        return x;
    }

    public String convertToDateShow(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f2 = new SimpleDateFormat("MM-dd-yyyy");
        String x = f2.format(date);
        return x;
    }
    public String getFormatedTime(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = null;
        try {
            date = format.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f2 = new SimpleDateFormat("hh:mm:ss");
        String x = f2.format(date);
        return x;
    }


    public long getTimeDifference(String oldTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c = Calendar.getInstance();
        Date dateOne = null,dateTwo = null;
        long timeDiff =0;
        try {
            String formattedDate = df.format(c.getTime());
            dateOne = df.parse(oldTime);
            dateTwo = df.parse(formattedDate);
            timeDiff = Math.abs(dateOne.getTime()-dateTwo.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeDiff;
    }

    public String convertMillisecondsToTime(long time) {
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = time / daysInMilli;

        long elapsedHours = time / hoursInMilli;

        long elapsedMinutes = time / minutesInMilli;

        long elapsedSeconds = time / secondsInMilli;


        String date = elapsedDays+":"+elapsedHours+":"+elapsedMinutes+":"+elapsedSeconds;
        return date;
    }
    public File convertImageToFIle(Bitmap bitmap, String name){
        final int min = 20;
        final int max = 1000;
        final int random = new Random().nextInt((max - min) + 1) + min;
        File filesDir = mContext.getFilesDir();
        File imageFile = new File(filesDir, name+random + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            // Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
        return imageFile;
    }
    public String convertBitmapTo64ByteString(Bitmap bitmap) {
        String encoded = "";
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
             encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        catch (Exception e){
            e.getMessage();
        }
        return encoded;
    }
    public Bitmap convert64ByteStringToBitmap(String base64) {
        String encoded = "";
        byte[] imageBytes = {};
        Bitmap decodedImage = null;
        try {
            imageBytes = Base64.decode(base64, Base64.DEFAULT);
            decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
        catch (Exception e){
            e.getMessage();
        }
        return decodedImage;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        Bitmap resizedBitmap = null;
        try {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);
            // "RECREATE" THE NEW BITMAP
            resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);
            bm.recycle();
        }
        catch (Exception e){

        }

        return resizedBitmap;
    }

    public Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(returnedBitmap);
            //Get the view's background
            Drawable bgDrawable =view.getBackground();
            if (bgDrawable!=null) {
                //has background drawable, then draw it on the canvas
                bgDrawable.draw(canvas);
            }   else{
                //does not have background drawable, then draw white_cursor background on the canvas
                canvas.drawColor(Color.WHITE);
            }
            // draw the view on the canvas
            view.draw(canvas);
        }
        catch (Exception e){

            Log.v("exception",e.getMessage());
        }

        //Bind a canvas to it

        //return the bitmap
        return returnedBitmap;
    }

    public String getLocationAddress(double latitude, double longitude){
        Geocoder geocoder;
        List<Address> addresses;
        String address = "";
        try {
            geocoder = new Geocoder(mContext, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0);
        }
        catch (Exception e){

        }
        return address;
    }

    @SuppressLint("LongLogTag")
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    @SuppressLint("SoonBlockedPrivateApi") Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
    }

    @SuppressLint("LongLogTag")
    public static boolean colorPickerValue(NumberPicker numberPicker, View view, int color){
        if(view instanceof EditText){
            try{
                @SuppressLint("SoonBlockedPrivateApi") Field selectorWheelPaintField = numberPicker.getClass()
                        .getDeclaredField("mSelectorWheelPaint");
                selectorWheelPaintField.setAccessible(true);
                ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                ((EditText)view).setTextColor(color);
                numberPicker.invalidate();
                return true;
            }
            catch(NoSuchFieldException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalAccessException e){
                Log.w("setNumberPickerTextColor", e);
            }
            catch(IllegalArgumentException e){
                Log.w("setNumberPickerTextColor", e);
            }
        }
        return false;

    }

    public String currentDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = null;
        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                currentDay = "SUN";
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                currentDay = "MON";
                break;
            case Calendar.TUESDAY:
                currentDay = "TUE";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "WED";
                break;
            case Calendar.THURSDAY:
                currentDay = "THU";
                break;
            case Calendar.FRIDAY:
                currentDay = "FRI";
                break;
            case Calendar.SATURDAY:
                currentDay = "SAT";
                break;
        }

        return currentDay;
    }
//    public  String dayOfWeek(int seconds){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(seconds*1000L);
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//        String currentDay = null;
//        switch (dayOfWeek) {
//            case Calendar.SUNDAY:
//                // Current day is Sunday
//                currentDay = mContext.getString(R.string.sunday);
//                break;
//            case Calendar.MONDAY:
//                // Current day is Monday
//                currentDay = mContext.getString(R.string.monday);
//                break;
//            case Calendar.TUESDAY:
//                currentDay = mContext.getString(R.string.tuesday);
//                break;
//            case Calendar.WEDNESDAY:
//                currentDay = mContext.getString(R.string.wednesday);;
//                break;
//            case Calendar.THURSDAY:
//                currentDay = mContext.getString(R.string.thursday);;
//                break;
//            case Calendar.FRIDAY:
//                currentDay = mContext.getString(R.string.friday);;
//                break;
//            case Calendar.SATURDAY:
//                currentDay = mContext.getString(R.string.saturday);;
//                break;
//        }
//
//        return currentDay;
//
//    }
public void OnGPS() {
    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
    builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });
    final AlertDialog alertDialog = builder.create();
    alertDialog.show();
}
    public double meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f/ Math.PI);
        float a1 = lat_a / pk;
        float a2 = lng_a / pk;
        float b1 = lat_b / pk;
        float b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000 * tt/1000;
    }


    public Date convertStringToTime(String input){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(input);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public  boolean isLocationEnabled() {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(mContext.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }
    public String getLocationCountry(double latitude, double longitude){
        Geocoder geocoder;
        List<Address> addresses;
        String address = "";
        String country = "";
        try {
            geocoder = new Geocoder(mContext, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0);
            country=addresses.get(0).getCountryName();
        }
        catch (Exception e){

        }
        return country;
    }

    public String getLocationCity(double latitude, double longitude){
        Geocoder geocoder;
        List<Address> addresses;
        String address = "";
        String city = "";
        try {
            geocoder = new Geocoder(mContext, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0);
           city= addresses.get(0).getLocality();
        }
        catch (Exception e){

        }
        return city;
    }

    public static  void setStatusBarColor(Activity activity){
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.new_app_bg_start));
        }
    }
//    public static  void setGradientStatusBar(Activity activity){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            Drawable background = activity.getResources().getDrawable(R.drawable.gradient_toolbar);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
//            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
//            window.setBackgroundDrawable(background);
//        }
//    }

    public  int convertDPToPixel(int dpValue){
        float d = mContext.getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d);
        return margin;
    }

    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB));
    }

    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public String getWeekStartDate(){
        Calendar c1 = Calendar.getInstance();

        //first day of week
        c1.set(Calendar.DAY_OF_WEEK, c1.getFirstDayOfWeek());

        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
         String date = year1+"-"+month1+"-"+day1;

         return date;
    }

    public String getWeekEndDate(){
        Calendar c1 = Calendar.getInstance();

        //first day of week
        c1.set(Calendar.DAY_OF_WEEK, 7);

        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        String date = year1+"-"+month1+"-"+day1;

        return date;
    }

    public String getMonthStartDate(){
        Calendar c1 = Calendar.getInstance();
        //first day of week
        c1.set(Calendar.DAY_OF_MONTH, 1);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        DecimalFormat mFormat= new DecimalFormat("00");
        String date = year1+"-"+mFormat.format(Double.valueOf(month1))+"-"+mFormat.format(Double.valueOf(day1));
        return date;
    }
    public String getYesterdayDate(){
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //first day of week
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        if (day1==1){
            day1=30;
            if (month1>1)
            month1 = month1-1;
            else {
                month1 = 12;
                year1=year1-1;
            }
        }
        else {
            day1=day1-1;
        }
        DecimalFormat mFormat= new DecimalFormat("00");
        String date = year1+"-"+mFormat.format(Double.valueOf(month1))+"-"+mFormat.format(Double.valueOf(day1));
        return date;
    }

    public String getMonthEndDate(){
        Calendar c1 = Calendar.getInstance();
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        DecimalFormat mFormat= new DecimalFormat("00");
        String date = year1+"-"+mFormat.format(Double.valueOf(month1))+"-"+mFormat.format(Double.valueOf(day1));
       // String date = year1+"-"+month1+"-"+day1;
        return date;
    }
    public String getMonthName(){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());
       // String date = year1+"-"+month1+"-"+day1;
        return month_name;
    }
    public String getYear(){
        Calendar c1 = Calendar.getInstance();
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        DecimalFormat mFormat= new DecimalFormat("00");
        String date = ""+year1;
        // String date = year1+"-"+month1+"-"+day1;
        return date;
    }
    public String getDay(String input_date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = format.parse(input_date);
            System.out.println(date);

            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public String getSixMonthStartDate(){
        DecimalFormat mFormat= new DecimalFormat("00");
        Calendar c1 = Calendar.getInstance();
        //first day of week
        c1.add(Calendar.MONTH, -6);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH)+1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        String date = year1+"-"+mFormat.format(Double.valueOf(month1))+"-"+mFormat.format(Double.valueOf(day1));
        return date;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public Boolean isDateMoreOlderThan30Days(String olderDate){
        boolean isDateOver = false;
        Date older = convertStringToDate(olderDate);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDateString = df.format(c);
        Date currentDate = convertStringToDate(currentDateString);
        long diff = currentDate.getTime() - older.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (days>30) {
            isDateOver = true;
            System.out.println("30 months older than current date!");
        }
        return isDateOver;
    }


    public boolean isSpecialCharacterFound(String s) {
        Pattern p = Pattern.compile("[^A-Za-z0-9.]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();

        return b;
    }
}
