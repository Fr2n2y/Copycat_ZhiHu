package com.example.zy.zhihu.Bean;

import java.util.List;

/**
 * Created by zy' on 2016/4/26.
 */
public class Story {
    /**
     * body : <div class="main-wrap content-wrap">--------html文档
     * image_source : 《我爱你妈》
     * title : 瞎扯 · 如何正确地吐槽
     * image : http://pic3.zhimg.com/469fac37115f97006cda02e62be4ebc6.jpg
     * share_url : http://daily.zhihu.com/story/8186759
     * js : []
     * ga_prefix : 042206
     * section : {"thumbnail":"http://pic1.zhimg.com/474563dcf9b21ebaa39d7c05ef94a594.jpg","id":2,"name":"瞎扯"}
     * images : ["http://pic2.zhimg.com/5af03099bf9b3470249fa68f1600a5c9.jpg"]
     * type : 0
     * id : 8186759
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    /**
     * thumbnail : http://pic1.zhimg.com/474563dcf9b21ebaa39d7c05ef94a594.jpg
     * id : 2
     * name : 瞎扯
     */

    private SectionBean section;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public SectionBean getSection() {
        return section;
    }

    public void setSection(SectionBean section) {
        this.section = section;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionBean {
        private String thumbnail;
        private int id;
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
