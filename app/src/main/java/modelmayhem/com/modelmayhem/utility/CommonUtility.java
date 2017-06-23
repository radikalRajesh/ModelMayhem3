package modelmayhem.com.modelmayhem.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc

/**
 * The Class CommonUtility.
 */
public class CommonUtility {


    private final static int MAIL_SEND = 2;

    public static boolean checkConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isAvailable() && ni.isConnected();
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
//			view.measure(MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }



    public static boolean CheckDates(String startDate, String endDate) {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dfDate.parse(endDate));
            c.add(Calendar.DATE, 1);  // number of days to add
            endDate = dfDate.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean b = false;
        try {
            if (dfDate.parse(endDate).before(dfDate.parse(startDate))) {
                System.out.println("is smaller");
                b = true;//If start date is before end date
            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                System.out.println("is equal");
                b = true;//If two dates are equal
            } else {
                System.out.println("is diff");
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }


    public static int getDateDifference(String fromDate, String toDate) {
        try {
            Date date1 = null, date2 = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            date1 = df.parse(toDate);
            date2 = df.parse(fromDate);
            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
//            System.out.println(diffDays);
            return (int) diffDays + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    public static String getTomorrowDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }








    public static String convertIntoTicketAmOrPm(String time) {
        String t1 = null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");//"KK:mm a"
        try {
            t1 = outputFormat.format(inputFormat.parse(time));
//            System.out.println(outputFormat.format(inputFormat.parse(time)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t1;
    }



    public static String getTodayDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        return inputFormat.format(cal.getTime());
    }


    public static void sendEmail(String emailId, String subject, String body, Activity activity) {
        Intent mailer = new Intent(Intent.ACTION_SEND);
        // mailer.setType("vnd.android.cursor.item/email");
        mailer.setType("message/rfc822");
        mailer.putExtra(Intent.EXTRA_EMAIL, new String[]{emailId});
        mailer.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailer.putExtra(Intent.EXTRA_TEXT, body);
        activity.startActivityForResult(mailer, MAIL_SEND);
    }

    public static String convertDateToScocialNetworkingFormat(String dateposted) {
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sBuffer = new StringBuffer("");
        Date d1 = null;

        try {
            d1 = formatter.parse(dateposted);
            Calendar c = Calendar.getInstance();
            c.setTime(d1);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(new Date());
            // in milliseconds
            long diff = c1.getTimeInMillis() - c.getTimeInMillis();
            System.out.println("diff--->" + diff);
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays > 0)
                sBuffer.append(diffDays).append(" days ");
            else if (diffHours > 0 && diffDays <= 0)
                sBuffer.append(diffHours).append(" hours ");
            else if (diffMinutes > 0 && diffHours <= 0)
                sBuffer.append(diffMinutes).append(" mins ");
            else if (diffSeconds >= 0 && diffMinutes <= 0)
                sBuffer.append(diffSeconds + " secs");

            System.out.println("@@date--->" + sBuffer);

            return sBuffer.toString() + " ago";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static double distanceBetweenTwoLatLong(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        Location from_location = new Location("locationA");
        from_location.setLatitude(fromLatitude);
        from_location.setLongitude(fromLongitude);
        Location to_locations = new Location("locationB");
        to_locations.setLatitude(toLatitude);
        to_locations.setLongitude(toLongitude);
        return (double) from_location.distanceTo(to_locations);
    }

    /**
     * Validate email.
     *
     * @param emailID the email id
     * @return true, if successful
     */
    public static boolean validateEmail(String emailID) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(emailID);
        return matcher.matches();
    }

    /**
     * Validate email.
     *
     * @param emailID the email id
     * @return true, if successful
     */
    public static boolean validateGovernmentEmail(String emailID) {
        if (!validateEmail(emailID)) {
            String lastfix = emailID.substring(emailID.lastIndexOf(".") + 1);
            System.out.println("last prefix==>" + lastfix);
            if (lastfix.equals("mil") || lastfix.equals("gov"))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    /**
     * Validate password.
     *
     * @param password the password
     * @return true, if successful
     */
    public static boolean validatePassword(String password) {
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[[~!@#$%^&*()_+-={}}|\"'?/:<,>;}]]).{6,10})";
//        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d)(?!.*(AND|NOT)).*[a-z].*";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Validate password withour special charater.
     *
     * @param password the password
     * @return true, if successful
     */
    public static boolean validatePasswordWithourSpecialCharater(String password) {
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?!.*(AND|NOT)).*[a-z].*";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(password);
        return !matcher.matches();
    }

    /**
     * Show keyboard.
     *
     * @param et       the et
     * @param activity the activity
     */
    public static void showKeyboard(EditText et, Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scale picture.
     *
     * @param width             the width
     * @param height            the height
     * @param mCurrentPhotoPath the m current photo path
     * @return the bitmap
     */
    public static Bitmap scalePicture(float width, float height, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = (int) width;
        int targetH = (int) height;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        return BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
    }

    /**
     * Gets the resized bitmap.
     *
     * @param bm        the bm
     * @param newHeight the new height
     * @param newWidth  the new width
     * @return the resized bitmap
     */
    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }

    // decodes image and scales it to reduce memory consumption

    /**
     * Decode file.
     *
     * @param width  the width
     * @param height the height
     * @param f      the f
     * @return the bitmap
     */
    public static Bitmap decodeFile(int width, int height, File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			/*
             * // Find the correct scale value. It should be the power of 2. int
			 * REQUIRED_Height = (int)height; int REQUIRED_Widtht = (int)width;;
			 * int scale = 0; int width_tmp = o.outWidth, height_tmp =
			 * o.outHeight;
			 */

			/*
             * while (true) { if (width_tmp < REQUIRED_Widtht || height_tmp <
			 * REQUIRED_Height) break; width_tmp /= 2; height_tmp /= 2; scale *=
			 * 2; }
			 */

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = calculateInSampleSize(o, width, height);
            // System.out.println("Scale: " + scale);
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            // System.out.println("--- exception "+e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calculate in sample size.
     *
     * @param options   the options
     * @param reqWidth  the req width
     * @param reqHeight the req height
     * @return the int
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    /**
     * Utility function for creating a scaled version of an existing bitmap.
     *
     * @param unscaledBitmap Bitmap to scale
     * @param dstWidth       Wanted width of destination bitmap
     * @param dstHeight      Wanted height of destination bitmap
     * @param scalingLogic   Logic to use to avoid image stretching
     * @return New scaled bitmap object
     */
    public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(), dstRect.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    /**
     * Calculate src rect.
     *
     * @param srcWidth     the src width
     * @param srcHeight    the src height
     * @param dstWidth     the dst width
     * @param dstHeight    the dst height
     * @param scalingLogic the scaling logic
     * @return the rect
     */
    private static Rect calculateSrcRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.CROP) {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                final int srcRectWidth = (int) (srcHeight * dstAspect);
                final int srcRectLeft = (srcWidth - srcRectWidth) / 2;
                return new Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth, srcHeight);
            } else {
                final int srcRectHeight = (int) (srcWidth / dstAspect);
                final int scrRectTop = (srcHeight - srcRectHeight) / 2;
                return new Rect(0, scrRectTop, srcWidth, scrRectTop + srcRectHeight);
            }
        } else {
            return new Rect(0, 0, srcWidth, srcHeight);
        }
    }

    /**
     * Calculate dst rect.
     *
     * @param srcWidth     the src width
     * @param srcHeight    the src height
     * @param dstWidth     the dst width
     * @param dstHeight    the dst height
     * @param scalingLogic the scaling logic
     * @return the rect
     */
    private static Rect calculateDstRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return new Rect(0, 0, dstWidth, (int) (dstWidth / srcAspect));
            } else {
                return new Rect(0, 0, (int) (dstHeight * srcAspect), dstHeight);
            }
        } else {
            return new Rect(0, 0, dstWidth, dstHeight);
        }
    }

    /**
     * Calculate sample size.
     *
     * @param srcWidth     the src width
     * @param srcHeight    the src height
     * @param dstWidth     the dst width
     * @param dstHeight    the dst height
     * @param scalingLogic the scaling logic
     * @return the int
     */
    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float) srcWidth / (float) srcHeight;
            final float dstAspect = (float) dstWidth / (float) dstHeight;

            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }

    /**
     * ScalingLogic defines how scaling should be carried out if source and
     * destination image has different aspect ratio.
     * <p/>
     * CROP: Scales the image the minimum amount while making sure that at least
     * one of the two dimensions fit inside the requested destination area.
     * Parts of the source image will be cropped to realize this.
     * <p/>
     * FIT: Scales the image the minimum amount while making sure both
     * dimensions fit inside the requested destination area. The resulting
     * destination dimensions might be adjusted to a smaller size than
     * requested.
     */
    public enum ScalingLogic {

        /**
         * The crop.
         */
        CROP,
        /**
         * The fit.
         */
        FIT
    }

    // decodes image and scales it to reduce memory consumption

    /**
     * Decode file.
     *
     * @param width  the width
     * @param height the height
     * @param path   the path
     * @return the bitmap
     */
    public static Bitmap decodeFile(int width, int height, String path) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            // System.out.println("Scale:  width_tmp  " + width_tmp +
            // " height_tmp "+ height_tmp);

            while (true) {
                if (width_tmp < width || height_tmp < height)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            // System.out.println("Scale: " + scale);
            return BitmapFactory.decodeFile(path, o2);
        } catch (Exception e) {
            // System.out.println("--- exception "+e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * This method converts device specific pixels to density independent
     * pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    /**
     * Hide keyboard.
     *
     * @param activity the activity
     */
    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Service.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
