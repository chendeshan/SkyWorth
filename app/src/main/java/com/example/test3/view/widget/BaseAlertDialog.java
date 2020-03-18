package com.example.test3.view.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.example.test3.R;

public class BaseAlertDialog extends Dialog {
    BaseAlertDialog(Context context) {
        super(context, R.style.BorgDialog);
        setOwnerActivity((Activity) context);
    }

    private View mContentView;

    /**
     * Set the Dialog title from String
     *
     * @param title text
     */
    public void setTitle(CharSequence title) {
        if (title != null && mContentView != null) {
            ((TextView) mContentView.findViewById(R.id.base_dialog_title)).setText(title);
        }
    }

    /**
     * Set the Dialog message from String
     *
     * @param message text
     */
    public void setMessage(CharSequence message) {
        if (message != null && mContentView != null) {
            TextView messageText = (TextView) mContentView.findViewById(R.id.base_dialog_message);
            if (messageText != null) {
                messageText.setText(message);
            }
        }
    }

    /**
     * Get the Dialog button
     *
     * @param whichButton enum
     * @return TextView
     */
    public TextView getButton(int whichButton) {
        if (mContentView == null) {
            return null;
        }
        if (whichButton == DialogInterface.BUTTON_POSITIVE) {
            return (TextView) mContentView.findViewById(R.id.base_dialog_positive_button);
        } else if (whichButton == DialogInterface.BUTTON_NEGATIVE) {
            return (TextView) mContentView.findViewById(R.id.base_dialog_negative_button);
        } else {
            return null;
        }
    }

    /**
     * Set the Dialog positive button status
     *
     * @param enable boolean
     */
    public void setPositiveButtonEnable(boolean enable) {
        if (mContentView == null) {
            return;
        }

        TextView positiveButton = (TextView) mContentView.findViewById(R.id.base_dialog_positive_button);
        TextView negativeButton = (TextView) mContentView.findViewById(R.id.base_dialog_negative_button);

        if (positiveButton != null) {
            boolean negativeButtonExist = (negativeButton != null && negativeButton.getVisibility() == View.VISIBLE);

            int enableResId = (negativeButtonExist ? R.drawable.dialog_btn_background : R.drawable.dialog_btn_single_background);
            int disableResId = (negativeButtonExist ? R.drawable.dialog_btn_bg_disable : R.drawable.dialog_btn_single_bg_disable);

            positiveButton.setBackgroundResource(enable ? enableResId : disableResId);
            positiveButton.setEnabled(enable);
        }
    }

    /**
     * Set the Dialog negative button status
     *
     * @param enable boolean
     */
    public void setNegativeButtonEnable(boolean enable) {
        if (mContentView == null) {
            return;
        }

        TextView button = (TextView) mContentView.findViewById(R.id.base_dialog_negative_button);
        if (button != null) {
            button.setBackgroundResource(enable ? R.drawable.dialog_btn_background : R.drawable.dialog_btn_bg_disable);
            button.setEnabled(enable);
        }
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private final Context context;

        private String title;
        private Drawable icon;
        private String message;
        private int messageGravity;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;

        private Boolean cancelEnable = true;

        private DialogInterface.OnClickListener
                positiveButtonClickListener,
                negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
            this.messageGravity = Gravity.START;
        }

        /**
         * Set the Dialog message from String
         *
         * @param message text
         * @return Builder
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message text
         * @return Builder
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param gravity int
         * @return Builder
         */
        public Builder setMessageGravity(int gravity) {
            this.messageGravity = gravity;
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title text
         * @return Builder
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title text
         * @return Builder
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set the Dialog icon from resource
         *
         * @param icon resid
         * @return Builder
         */
        public Builder setIcon(int icon) {
            this.icon = context.getResources().getDrawable(icon);
            return this;
        }

        /**
         * Set the Dialog CancelEnable
         *
         * @param cancelEnable boolean
         * @return Builder
         */
        public Builder setCancelEnable(boolean cancelEnable) {
            this.cancelEnable = cancelEnable;
            return this;
        }

        /**
         * Set a custom content view for the Dialog.
         * If a message is set, the contentView is not
         * added to the Dialog...
         *
         * @param v view
         * @return Builder
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText text
         * @param listener           callback
         * @return Builder
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         *
         * @param positiveButtonText text
         * @param listener           callback
         * @return Builder
         */
        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         *
         * @param negativeButtonText button
         * @param listener           callback
         * @return Builder
         */
        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         *
         * @param negativeButtonText button
         * @param listener           callback
         * @return Builder
         */
        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * build the custom dialog
         */
        public BaseAlertDialog build() {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final BaseAlertDialog dialog = new BaseAlertDialog(context);

            View layout = inflater.inflate(R.layout.base_alert_dialog, null);
//            dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            TextView titleText = (TextView) layout.findViewById(R.id.base_dialog_title);
            titleText.setText(title);

            if (icon != null) {
                ((ImageView) layout.findViewById(R.id.base_dialog_icon)).setImageDrawable(icon);
            } else {
                layout.findViewById(R.id.base_dialog_icon).setVisibility(View.GONE);
            }

            TextView positiveButton = (TextView) layout.findViewById(R.id.base_dialog_positive_button);
            if (positiveButtonText != null) {
                positiveButton.setText(positiveButtonText);

                if (positiveButtonClickListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.base_dialog_positive_button).setVisibility(View.GONE);
            }

            // set the cancel button
            if (negativeButtonText != null) {
                TextView negativeButton = (TextView) layout.findViewById(R.id.base_dialog_negative_button);
                negativeButton.setText(negativeButtonText);

                if (negativeButtonClickListener != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.base_dialog_negative_button).setVisibility(View.GONE);

                // set single button resource
                positiveButton.setBackgroundResource(R.drawable.dialog_btn_single_background);
            }

            if (positiveButtonText != null && negativeButtonText != null) {
                layout.findViewById(R.id.base_dialog_sep_line).setVisibility(View.VISIBLE);
            } else {
                layout.findViewById(R.id.base_dialog_sep_line).setVisibility(View.GONE);
            }

            // set the content message
            if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((FrameLayout) layout.findViewById(R.id.base_dialog_content)).removeAllViews();
                ((FrameLayout) layout.findViewById(R.id.base_dialog_content)).addView(contentView, new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            } else if (message != null) {
                TextView messageText = (TextView) layout.findViewById(R.id.base_dialog_message);
                messageText.setText(message);
                messageText.setGravity(messageGravity);
            }

            //设置alert最小
//            float t = Settings.getFloat(Settings.PIXEL_PER_MM);
//            layout.setMinimumHeight((int) (t * 20));
//            layout.setMinimumWidth((int) (t * 50) + 10);

            dialog.setCancelable(cancelEnable);

            dialog.setContentView(layout);

            dialog.mContentView = layout;

            return dialog;
        }

    }
}
