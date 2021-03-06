package com.andy.kotlinandroid.net

import com.andy.kotlin.gank.util.GLog
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber

/**
 * ApiCallBack
 * @author andyqtchen <br/>
 * 网络回调
 * 创建日期：2017/6/5 11:34
 */
abstract class ApiCallBack<M> : Subscriber<M>() {
    abstract fun onSuccess(model: M)
    abstract fun onFailure(code: Int,msg: String?)
    abstract fun onFinish()

    override fun onCompleted() {
        onFinish()
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onError(e: Throwable?) {
        GLog.e(e)
        if (e is HttpException) {
            val httpException = e
            val code = httpException.code()
            var msg = httpException.message
            GLog.d("code = " + code)
            if (code == 554) {
                msg = "网络不给力"
            } else if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试"
            }
            onFailure(code,msg)
        } else {
            onFailure(0, e.toString())
        }
        onFinish()
    }
}