package com.kmmoonlight.evilcomic.net;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rxhttp.RxHttp;

public class NetWorkTask {

    private static Observer<String> generateObserver(Callback callback, int requestId) {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                callback.onResult(s, requestId);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e.getMessage(), requestId);
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public static void QueryMainPageData(Callback callback, int requestId) {
        RxHttp.get("/")
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(generateObserver(callback, requestId));
    }

    public static void QueryComicIndex(String bookUrl,Callback callback, int requestId) {
        RxHttp.get(bookUrl)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(generateObserver(callback, requestId));
    }

    public static void QueryComicChapter(String chapterUrl,Callback callback, int requestId) {
        RxHttp.get(chapterUrl)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(generateObserver(callback, requestId));
    }

    public static void SearchComic(String keyword,Callback callback, int requestId) {
        RxHttp.get("/search")
                .add("keyword", keyword)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(generateObserver(callback, requestId));
    }

    public static void GetComicList(int page,Callback callback, int requestId) {
        RxHttp.get("/booklist")
                .add("page", String.valueOf(page))
                .add("tag", "全部")
                .add("area", -1)
                .add("end", 0)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(generateObserver(callback, requestId));
    }
}
