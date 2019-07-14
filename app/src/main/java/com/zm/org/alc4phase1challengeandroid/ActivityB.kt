package com.zm.org.alc4phase1challengeandroid

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_b.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.net.http.SslError
import android.util.Log
import android.view.View
import android.webkit.SslErrorHandler
import android.content.Intent
import android.view.MenuItem


class ActivityB : AppCompatActivity() {


    private val aboutAlcUrl = "https://andela.com/alc/"
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        handleToolBar()



        initializeWebView()

    }

    private fun handleToolBar() {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.about_alc)

    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {

            onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {
        aboutAlcWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Log.d("Failure Url :", failingUrl)
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                Log.d("Ssl Error:", handler.toString() + "error:" + error)
                handler.proceed()
            }
        }
        aboutAlcWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    webViewProgressBar.visibility = View.GONE
                    aboutAlcWebView.visibility = View.VISIBLE

                } else {
                    if (View.GONE == webViewProgressBar.visibility) {
                        webViewProgressBar.visibility = View.VISIBLE
                    }
                    webViewProgressBar.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

        }

        aboutAlcWebView.settings.javaScriptEnabled = true

        if (Build.VERSION.SDK_INT >= 19) {
            aboutAlcWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }


        aboutAlcWebView.loadUrl(aboutAlcUrl)
    }
}
