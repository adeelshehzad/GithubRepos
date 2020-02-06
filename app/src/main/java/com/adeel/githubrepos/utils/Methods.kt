package com.adeel.githubrepos.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.adeel.githubrepos.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//DataBinding Methods starts

/* DataBinding Method for loadingImage using Glide [Start]*/
fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 15f
        start()
    }
}

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(progressDrawable).error(R.drawable.ic_user_error)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

@BindingAdapter("android:avatarUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, getProgressDrawable(view.context))
}
/* DataBinding Method for loadingImage using Glide [End]*/

/* DataBinding Method for setting Repository name in bold concatenated with Owner Name [Start] */
@BindingAdapter(value = ["android:setRepoName", "android:setOwnerName"])
fun setFullRepoName(textView: TextView, repoName: String, ownerName: String) {
    val repoNameBoldSB = SpannableStringBuilder("$ownerName / $repoName")
    repoNameBoldSB.setSpan(
        StyleSpan(Typeface.BOLD),
        repoNameBoldSB.indexOf("/"),
        repoNameBoldSB.length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )

    textView.text = repoNameBoldSB
}
/* DataBinding Method for setting Repository name in bold concatenated with Owner Name [End] */

/* DataBinding Method for setting Repository language and color [Start] */
fun setRepoLanguage(textview: TextView, repoLanguage: String?) {
    val visibility = repoLanguage?.let {
        textview.text = repoLanguage
        View.VISIBLE
    } ?: View.GONE

    textview.visibility = visibility
}


@BindingAdapter(value = ["android:setRepoLanguage", "android:setLanguageColor"])
fun setLanguageColor(textview: TextView, repoLanguage: String?, languageColor: String?) {
    setRepoLanguage(textview, repoLanguage)
    languageColor?.let {
        val languageDrawable =
            textview.compoundDrawables[0] as GradientDrawable
        languageDrawable.setColor(Color.parseColor(it))
    }
}
/* DataBinding Method for setting Repository language and color [End] */

//DataBinding Methods end
