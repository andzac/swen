package com.rferl.SWEN.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ScanRFERL4MetaService {

    public static boolean checkUrlForRFLRL(List<String> urlList) {
        if (urlList.size() == 0) {
            throw new IllegalArgumentException("URL List is empty, please provide at least one URL");
        } else {
            for (String url : urlList) {
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements divTagTitle = doc.getElementsByAttributeValueContaining("class", "col-title");
                    System.out.println(divTagTitle.text());
                    Elements divTagPublishDate = doc.getElementsByAttributeValueContaining("class", "published");
                    System.out.println(divTagPublishDate.text());
                    Elements divTagPublisher = doc.getElementsByAttributeValueContaining("class", "links__item-link");
                    System.out.println(divTagPublisher.text());
                    Elements description = doc.select("span[class=caption]");
                    System.out.println(description.text());
                    for (Element desc : description
                    ) {
                        if (desc.text() != null) {
                            String fffText = String.format("img[alt='%s']", desc.text());
                            String imgSrc = doc.select(fffText).attr("src");
                            System.out.println(imgSrc + "\n\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    }
}
