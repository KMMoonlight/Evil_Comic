package com.kmmoonlight.evilcomic.net;

public interface Callback {

    void onResult(String result, int requestId);

    void onError(String error, int requestId);
}
