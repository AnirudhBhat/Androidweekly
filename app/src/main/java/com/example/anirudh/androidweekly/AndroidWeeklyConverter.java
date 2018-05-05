package com.example.anirudh.androidweekly;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class AndroidWeeklyConverter implements Converter {
    private static final String URL = "http://androidweekly.net/archive";
    private static ArrayList<String> androidWeeklyLinks = new ArrayList<>();
    private static final AndroidWeeklyConverter INSTANCE = new AndroidWeeklyConverter();
    @Override
    public ArrayList<String> convert(Object value) throws IOException {
        return parseAndroidWeeklyArchive();
    }


    public static final class Factory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                                Annotation[] annotations,
                                                                Retrofit retrofit) {
            return INSTANCE;
        }

    }


    public ArrayList<String> parseAndroidWeeklyArchive() {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            Elements linksContainingAnchorTag = doc.select("a");
            for (Element links : linksContainingAnchorTag) {
                if (links.attr("href").contains("/issues/issue")) {
                    androidWeeklyLinks.add(links.attr("href"));
                }
            }
            return androidWeeklyLinks;
        }
        return null;
    }
}
