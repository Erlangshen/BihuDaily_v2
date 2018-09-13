package com.erlangshen.mvp.model

class LatestData {

    /**
     * date : 20180809
     * stories : [{"images":["https://pic3.zhimg.com/v2-0e62df4df59434dbdc28d7bb9f6021ee.jpg"],"type":0,"id":9692675,"ga_prefix":"080916","title":"- 地球上现存最古老的生物是什么？\r\n- 很简单，大家都一样老"},{"images":["https://pic4.zhimg.com/v2-ad9959c76c927c7d8fff700f601ffb6b.jpg"],"type":0,"id":9692431,"ga_prefix":"080915","title":"生活中有哪些普遍存在、易被忽略而十分危险的儿童安全隐患？"},{"title":"一位已婚和尚，在京都开酒吧，还是 IT 公司老板","ga_prefix":"080913","images":["https://pic1.zhimg.com/v2-9369a3d7cf69e93e9d8e0dd8ab9515f4.jpg"],"multipic":true,"type":0,"id":9692494},{"images":["https://pic1.zhimg.com/v2-babce4e0bc888fe8a248991162792988.jpg"],"type":0,"id":9692533,"ga_prefix":"080912","title":"大误 · 吃一勺钫"},{"images":["https://pic4.zhimg.com/v2-6b0461bfd6e0cbb2e5756a5f3dc36a63.jpg"],"type":0,"id":9692479,"ga_prefix":"080910","title":"业内大佬手把手教你辨别瓷砖质量？大佬：不好意思我也看不出来"},{"images":["https://pic2.zhimg.com/v2-16a72344b6f57d00c9a50b7f15f7f34d.jpg"],"type":0,"id":9692401,"ga_prefix":"080909","title":"「流亡者」胖虎夺权后，大雄国凭借「蚊香旗」翻开了新篇章"},{"images":["https://pic2.zhimg.com/v2-156e408cc13c8ed1f05d1b96d96e3311.jpg"],"type":0,"id":9690431,"ga_prefix":"080908","title":"一种既能掩饰尴尬，又能拯救懒癌的「人类新语言」"},{"images":["https://pic2.zhimg.com/v2-c4b5640d0d3c8bc62f5d20ab57231811.jpg"],"type":0,"id":9692448,"ga_prefix":"080907","title":"如何判断一个公司值不值得待下去？"},{"images":["https://pic1.zhimg.com/v2-5ff6750065a485a1b78789251c98f560.jpg"],"type":0,"id":9692457,"ga_prefix":"080907","title":"别小瞧虚假广告，它们其实为你省了不少「冤枉钱」"},{"images":["https://pic4.zhimg.com/v2-8f906e5b18777a803c23e6eeab35d007.jpg"],"type":0,"id":9692597,"ga_prefix":"080907","title":"分期手续费到底该怎么算，银行永远不会跟你说实话"},{"images":["https://pic4.zhimg.com/v2-e9293bdf2f5312285057676fd8762c07.jpg"],"type":0,"id":9692451,"ga_prefix":"080906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-fd9afb4e263751e07129807ba4df781f.jpg","type":0,"id":9692595,"ga_prefix":"080821","title":"今晚点映 · 这里有嬉笑怒骂，柴米油盐，人间戏梦，滚滚红尘"},{"image":"https://pic4.zhimg.com/v2-f681f75861f8f9e24f65e5bf2be6fe5b.jpg","type":0,"id":9692494,"ga_prefix":"080913","title":"一位已婚和尚，在京都开酒吧，还是 IT 公司老板"},{"image":"https://pic4.zhimg.com/v2-e74993fb522dc8d350acf87ce7993c8f.jpg","type":0,"id":9692448,"ga_prefix":"080907","title":"如何判断一个公司值不值得待下去？"},{"image":"https://pic4.zhimg.com/v2-60c639837f71e6426ccef6a1b3d74fb3.jpg","type":0,"id":9692528,"ga_prefix":"080811","title":"请回答 2008：一切共同记忆以奥运开始，以奥运结束"},{"image":"https://pic4.zhimg.com/v2-657a8fcb6da39e357f2130f634eb01db.jpg","type":0,"id":9692442,"ga_prefix":"080713","title":"腾讯云硬盘出了个匪夷所思的 bug，导致用户数据完全丢失"}]
     */

    var date: String? = null
    var stories: MutableList<StoriesEntity>? = null
    var top_stories: MutableList<TopStoriesEntity>? = null

    class StoriesEntity {
        /**
         * images : ["https://pic3.zhimg.com/v2-0e62df4df59434dbdc28d7bb9f6021ee.jpg"]
         * type : 0
         * id : 9692675
         * ga_prefix : 080916
         * title : - 地球上现存最古老的生物是什么？
         * - 很简单，大家都一样老
         * multipic : true
         */

        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var isMultipic: Boolean = false
        var images: MutableList<String>? = null
        var isDate:Boolean= false
    }

    class TopStoriesEntity {
        /**
         * image : https://pic4.zhimg.com/v2-fd9afb4e263751e07129807ba4df781f.jpg
         * type : 0
         * id : 9692595
         * ga_prefix : 080821
         * title : 今晚点映 · 这里有嬉笑怒骂，柴米油盐，人间戏梦，滚滚红尘
         */

        var image: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
    }
}
