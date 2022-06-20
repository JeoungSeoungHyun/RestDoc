package site.metacoding.restdoc.util.keystore;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class MediaTypeImpl extends MediaType {

    // mediatype을 사용할 때마다 쓰다가 실수하지 않도록 만들어놓기(사실 있음)
    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=utf-8";

    public MediaTypeImpl(MediaType other, Charset charset) {
        super(other, charset);
    }
}
