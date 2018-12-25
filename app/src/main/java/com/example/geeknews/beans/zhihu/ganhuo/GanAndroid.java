package com.example.geeknews.beans.zhihu.ganhuo;

import java.util.List;

/**
 * Created by 马明祥 on 2018/12/25.
 */

public class GanAndroid {

    /**
     * error : false
     * results : [{"_id":"5c1216aa9d21223f60727cbf","createdAt":"2018-12-13T08:22:02.158Z","desc":"360开源的移动端可视化性能监控平台，为移动端APP提供性能监控与管理，可以迅速发现和定位各类APP性能和使用问题，帮助APP不断的提升用户体验。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58eds38pj30rr0s0751"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/Qihoo360/ArgusAPM","used":true,"who":"lijinshanmx"},{"_id":"5c1216fa9d21223f60727cc0","createdAt":"2018-12-13T08:23:22.185Z","desc":"同学，你的系统Toast可能需要修复一下！Fix Your Android Toast!","publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/Dovar66/DToast","used":true,"who":"lijinshanmx"},{"_id":"5c12178e9d21223f5c4439c9","createdAt":"2018-12-13T08:25:50.978Z","desc":"用于在Android中的ViewPager中创建书籍和卡片翻转动画的库.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58eg162og309c0gikjl","https://ww1.sinaimg.cn/large/0073sXn7gy1fy58ei4rvsg309c0giqv5","https://ww1.sinaimg.cn/large/0073sXn7gy1fy58ejvz9hg309c0gie81"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/wajahatkarim3/EasyFlipViewPager","used":true,"who":"lijinshanmx"},{"_id":"5c1218469d21223f5a2bae98","createdAt":"2018-12-13T08:28:54.298Z","desc":"一个安卓浮动操作按钮的安卓库.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58epwnwyg30a00k0e84"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/imtuann/FloatingActionButtonExpandable","used":true,"who":"lijinshanmx"},{"_id":"5c12186b9d21223f57f13472","createdAt":"2018-12-13T08:29:31.354Z","desc":"FilePicker 是一个小巧快速的文件选择器框架，以快速集成、高自定义化和可配置化为目标不断前进~","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58ev5w2wj31hc0u0n5s"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/rosuH/AndroidFilePicker","used":true,"who":"lijinshanmx"},{"_id":"5c1218bf9d21223f5c4439cb","createdAt":"2018-12-13T08:30:55.766Z","desc":"一个卡片式层叠效果的LayoutManager。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58f6v38xg30b40mub2e"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/amyu/StackCardLayoutManager","used":true,"who":"lijinshanmx"},{"_id":"5c1218f89d21223f57f13473","createdAt":"2018-12-13T08:31:52.593Z","desc":"Android实现了Oleg Frolov的切换动画.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58fqo8tyg30b40go1k4"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/bitvale/Switcher","used":true,"who":"lijinshanmx"},{"_id":"5c1219519d21223f5a2bae99","createdAt":"2018-12-13T08:33:21.695Z","desc":"一个滑动手势来关闭Activity的库.","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58frd0qug306b0dcwnz"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/thefuntasty/hauler","used":true,"who":"lijinshanmx"},{"_id":"5c1219b39d21223f5a2bae9a","createdAt":"2018-12-13T08:34:59.776Z","desc":"RFlutter Alert是Flutter实现的可定制且易于使用的警报/弹出对话库。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58fspd8qj30u01o0q4h","https://ww1.sinaimg.cn/large/0073sXn7gy1fy58ft07gwj30u01o0409","https://ww1.sinaimg.cn/large/0073sXn7gy1fy58fthfd3j30u01o0q4p","https://ww1.sinaimg.cn/large/0073sXn7gy1fy58ftrzlxj30u01o0tag"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/RatelHub/rflutter_alert","used":true,"who":"lijinshanmx"},{"_id":"5c1219dd9d21223f57f13475","createdAt":"2018-12-13T08:35:41.654Z","desc":"Android库可以创建可自定义的浮动动画Toast。","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58fy9rmog307i0dckjn"],"publishedAt":"2018-12-13T00:00:00.0Z","source":"chrome","type":"Android","url":"https://github.com/hariprasanths/FloatingToast","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5c1216aa9d21223f60727cbf
         * createdAt : 2018-12-13T08:22:02.158Z
         * desc : 360开源的移动端可视化性能监控平台，为移动端APP提供性能监控与管理，可以迅速发现和定位各类APP性能和使用问题，帮助APP不断的提升用户体验。
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7gy1fy58eds38pj30rr0s0751"]
         * publishedAt : 2018-12-13T00:00:00.0Z
         * source : chrome
         * type : Android
         * url : https://github.com/Qihoo360/ArgusAPM
         * used : true
         * who : lijinshanmx
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
