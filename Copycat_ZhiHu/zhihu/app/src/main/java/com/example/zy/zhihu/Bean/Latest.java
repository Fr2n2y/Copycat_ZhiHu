package com.example.zy.zhihu.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zy' on 2016/4/26.
 */
public class Latest implements Serializable{

    /**
     * date : 20160426
     * stories : [{"images":["http://pic2.zhimg.com/b9701c0f2944f347efe882d270bb34f9.jpg"],"type":0,"id":8213334,"ga_prefix":"042616","title":"邻居大爷说我家装修噪音让他犯心脏病，这个钱可能真得赔"},{"images":["http://pic2.zhimg.com/5854c27bf79c38fb93cb5b8883079779.jpg"],"type":0,"id":8216764,"ga_prefix":"042615","title":"寶寶什么时候变成了宝宝？"},{"title":"挑个好日子，上天去看纯天然无污染的星空","ga_prefix":"042614","images":["http://pic3.zhimg.com/11286e4ba21d79c644302de8fe374896.jpg"],"multipic":true,"type":0,"id":8211408},{"images":["http://pic4.zhimg.com/6711a34139310e0c88002b5f687f97b3.jpg"],"type":0,"id":8215507,"ga_prefix":"042613","title":"「你应该保护好自己」，这句话不能随便说"},{"images":["http://pic1.zhimg.com/04d4eb29711e810f2f4813c6131f8f70.jpg"],"type":0,"id":8202834,"ga_prefix":"042612","title":"这种能长到 6-7 米长的「带鱼」，寿命什么的全是谜"},{"title":"家庭美食照片，没有专业的工具，做到这样也很棒","ga_prefix":"042611","images":["http://pic3.zhimg.com/d3a9a315e7ea265a2094a66f1238b9a6.jpg"],"multipic":true,"type":0,"id":8215631},{"images":["http://pic4.zhimg.com/9cdc8be8bdd897f50faa647242cabc17.jpg"],"type":0,"id":8196352,"ga_prefix":"042610","title":"青春疼痛片里喜欢惹事的总是男孩，实际上女孩攻击性更强"},{"images":["http://pic3.zhimg.com/cecdbb4a1573467c3ca314e46e72c5ba.jpg"],"type":0,"id":8211863,"ga_prefix":"042609","title":"听领事处秘书讲完小王的故事，再也不信美国签证谣言了"},{"images":["http://pic3.zhimg.com/eb8d9f5131c9da74e879c3d54841fe32.jpg"],"type":0,"id":8212751,"ga_prefix":"042608","title":"那条朋友圈刷屏的「血泪海鲜」报道，是怎么写出来的？"},{"images":["http://pic3.zhimg.com/90f381bb900928a7d734332e9284c94a.jpg"],"type":0,"id":8210736,"ga_prefix":"042607","title":"总觉得富人越来越富，穷人越来越穷？"},{"images":["http://pic4.zhimg.com/838f522004a15fe6d0be1968cced793b.jpg"],"type":0,"id":8213857,"ga_prefix":"042607","title":"要说「唐装」是唐朝人穿的衣服，这就很不科学了"},{"images":["http://pic3.zhimg.com/ed28b79264b595a346438b417b49ba2a.jpg"],"type":0,"id":8212713,"ga_prefix":"042607","title":"我想胸部变大，我想按摩治病，于是我走进了美容院"},{"images":["http://pic3.zhimg.com/cd025eba9537ba106b96708ad7c0967a.jpg"],"type":0,"id":8214394,"ga_prefix":"042607","title":"读读日报 24 小时热门 TOP 5 · 我妈登录了我的交友软件"},{"images":["http://pic1.zhimg.com/474563dcf9b21ebaa39d7c05ef94a594.jpg"],"type":0,"id":8209402,"ga_prefix":"042606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/d8533ee861e0b26d4154ee498ad69c92.jpg","type":0,"id":8202834,"ga_prefix":"042612","title":"这种能长到 6-7 米长的「带鱼」，寿命什么的全是谜"},{"image":"http://pic4.zhimg.com/94d900861bef1f3384caf33a3b0ed337.jpg","type":0,"id":8214394,"ga_prefix":"042607","title":"读读日报 24 小时热门 TOP 5 · 我妈登录了我的交友软件"},{"image":"http://pic3.zhimg.com/39b5b05337b8ff927f5cd21ec9ee43d2.jpg","type":0,"id":8212751,"ga_prefix":"042608","title":"那条朋友圈刷屏的「血泪海鲜」报道，是怎么写出来的？"},{"image":"http://pic3.zhimg.com/ac13dabc7ba2c3750003e2eff084c4da.jpg","type":0,"id":8211863,"ga_prefix":"042609","title":"听领事处秘书讲完小王的故事，再也不信美国签证谣言了"},{"image":"http://pic2.zhimg.com/cff2ae72b9232945d3ac16ceab419841.jpg","type":0,"id":8212613,"ga_prefix":"042518","title":"职人介绍所第 14 期：「全世界最好的职业」有多好呢？"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/b9701c0f2944f347efe882d270bb34f9.jpg"]
     * type : 0
     * id : 8213334
     * ga_prefix : 042616
     * title : 邻居大爷说我家装修噪音让他犯心脏病，这个钱可能真得赔
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic3.zhimg.com/d8533ee861e0b26d4154ee498ad69c92.jpg
     * type : 0
     * id : 8202834
     * ga_prefix : 042612
     * title : 这种能长到 6-7 米长的「带鱼」，寿命什么的全是谜
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class TopStoriesBean implements Serializable{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
