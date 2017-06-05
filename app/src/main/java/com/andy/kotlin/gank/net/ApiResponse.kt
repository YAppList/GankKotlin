package com.andy.kotlin.gank.net

/**
 * todo 类名

 * @author andyqtchen <br></br>
 * *         todo 实现的主要功能。
 * *         创建日期：2017/6/5 13:27
 */
class ApiResponse<T> {
    var isError: Boolean = false
    var results: List<T>? = null

    override fun toString(): String {
        return "ApiResponse{" +
                "error=" + isError +
                ", results=" + results +
                '}'
    }
}
