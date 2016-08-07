package com.example.zy.zhihu.Bean;

import java.util.List;

/**
 * Created by zy' on 2016/4/26.
 */
public class Before {

    /**
     * date : 20160516
     * stories : [{"images":["http://pic2.zhimg.com/f04c667f2a6b36d80bb44675989482f5.jpg"],"type":0,"id":8310101,"ga_prefix":"051622","title":"深夜惊奇 · 月入十万难不难"},{"images":["http://pic1.zhimg.com/027e1e60a53cebe7dea457178870d774.jpg"],"type":0,"id":8310664,"ga_prefix":"051621","title":"要说烧脑的时空穿越，这些电影都不应该错过"},{"images":["http://pic1.zhimg.com/cadf6924c4a94c639ecd440d24238fd4.jpg"],"type":0,"id":8305192,"ga_prefix":"051620","title":"为什么撸串让人越撸越开心，越撸越上瘾？"},{"title":"如何正确描写一位风云人物的一段著名婚外情？","ga_prefix":"051619","images":["http://pic2.zhimg.com/d787bacd9183080429da85284f69d69d.jpg"],"multipic":true,"type":0,"id":8290205},{"images":["http://pic1.zhimg.com/c2afc37319396596b2bbf56d2bb9bbe4.jpg"],"type":0,"id":8303151,"ga_prefix":"051618","title":"认识很多鱼之后，我再也没法好好吃鱼了"},{"images":["http://pic2.zhimg.com/4588a5e30211048a7942d125da06a485.jpg"],"type":0,"id":8309359,"ga_prefix":"051617","title":"「您好，我是卖保险的」一听就想挂电话，原因在此"},{"images":["http://pic4.zhimg.com/57e7d9a8a7cd6f7614fb1363abf671db.jpg"],"type":0,"id":8307351,"ga_prefix":"051616","title":"为什么你在夜晚看到满天的繁星会觉得幸福？"},{"title":"这支球队刚刚神奇夺冠，我在现场见证了他们很多次的胜利","ga_prefix":"051615","images":["http://pic1.zhimg.com/34268761d0976604dbca493d204128f8.jpg"],"multipic":true,"type":0,"id":8308768},{"title":"似乎 98.7654321% 的国人都在误解海胆，不信请看","ga_prefix":"051614","images":["http://pic3.zhimg.com/e0795407b02513d08a70a4aaecd9a5f2.jpg"],"multipic":true,"type":0,"id":8308587},{"images":["http://pic4.zhimg.com/9a4d3f50ab79fdbdfb5e25acafc7b5a7.jpg"],"type":0,"id":8309123,"ga_prefix":"051613","title":"这些华人科学家，发现了艾滋病毒新的弱点"},{"images":["http://pic4.zhimg.com/96db083b8d57a3962474e0475c02650b.jpg"],"type":0,"id":8308714,"ga_prefix":"051612","title":"医生得的是自己平时给别人看的病，是怎样一种感受？"},{"images":["http://pic3.zhimg.com/0815a84333d3f330f8e2b1e1b9ca027e.jpg"],"type":0,"id":8307478,"ga_prefix":"051611","title":"为什么有些「扶贫」反而帮了倒忙？"},{"images":["http://pic2.zhimg.com/d4bf74542a3b399d24e47a077ebe6955.jpg"],"type":0,"id":8307387,"ga_prefix":"051610","title":"都知道就业有性别歧视，那男女的工资到底相差多少？"},{"images":["http://pic1.zhimg.com/c95cda6ab153e2ac615900a3d9c46cf4.jpg"],"type":0,"id":8301543,"ga_prefix":"051609","title":"拉着你的心理学朋友聊感情问题，而他们已经开始翻白眼了"},{"title":"作为一个外国人，我去了中国的一百多个城市旅行，来分享点好玩的","ga_prefix":"051608","images":["http://pic4.zhimg.com/eb3d3552a66787fe99e50d537d6ec4a7.jpg"],"multipic":true,"type":0,"id":8307640},{"images":["http://pic3.zhimg.com/eb55edcc2abddfa9c2193a2446e06c46.jpg"],"type":0,"id":8307450,"ga_prefix":"051607","title":"一个不留神，手表就偷偷走快了"},{"images":["http://pic4.zhimg.com/4fdb300cc7502fc88775597bc407c13f.jpg"],"type":0,"id":8281977,"ga_prefix":"051607","title":"未来的银行网点可以长这样"},{"images":["http://pic2.zhimg.com/22ad39d6e6c9f312c763f655992b9625.jpg"],"type":0,"id":8297632,"ga_prefix":"051607","title":"那种小小的、慢慢的，但是看起来很实用的电动车，你会买吗？"},{"images":["http://pic4.zhimg.com/70c1dbcfd43da1ee660c982daaa508d3.jpg"],"type":0,"id":8307726,"ga_prefix":"051607","title":"读读日报 24 小时热门 TOP 5 · 戛纳大尺度女同电影"},{"images":["http://pic3.zhimg.com/d10566dd5ef2846297d925853defdcc6.jpg"],"type":0,"id":7783552,"ga_prefix":"051606","title":"瞎扯 · 女神和我的臭袜子"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/f04c667f2a6b36d80bb44675989482f5.jpg"]
     * type : 0
     * id : 8310101
     * ga_prefix : 051622
     * title : 深夜惊奇 · 月入十万难不难
     */

    private List<StoriesBean> stories;

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

}
