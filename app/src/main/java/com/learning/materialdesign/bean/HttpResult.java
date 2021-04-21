package com.learning.materialdesign.bean;


import com.google.gson.annotations.SerializedName;


/**
 * 这里用的别人提供的api,比较乱，不过我们可以参考一下，我们一般开发api返回的response都有个固定的规则，也就是外面包裹着一些固定的额外信息
 * 需要先处理一下才是我们所需要的数据 即我们这边的T
 * @param <T>
 */
public class HttpResult<T> {


    /**
     * coderYJ : 简书关注coderYJ 欢迎加QQ群讨论277030213
     * money : 给我打赏 https://blog.csdn.net/simplyou/article/details/113424779?spm=1001.2014.3001.5501
     * course : 微信小程序课程 https://www.bilibili.com/video/BV1ia4y1H794
     * www.itjava.cn : API文档 www.itjava.cn
     * idea : idea 激活 https://www.jianshu.com/p/28f4e9a6c7c8
     * code : 200
     * msg : 请求成功
     * data : {"total":250,"limit":25,"page":1,"subject":[{"m_id":"6786002","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p1454261925.jpg","title":"触不可及","director":"奥利维·那卡什 Olivier Nakache / 艾力克·托兰达 Eric Toledano","score":"9.2","quote":"满满温情的高雅喜剧。","order_num":25},{"m_id":"1851857","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p462657443.jpg","title":"蝙蝠侠：黑暗骑士","director":"克里斯托弗·诺兰 Christopher Nolan","score":"9.2","quote":"无尽的黑暗。","order_num":26},{"m_id":"1293172","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p452089833.jpg","title":"末代皇帝","director":"贝纳尔多·贝托鲁奇 Bernardo Bertolucci","score":"9.3","quote":"\u201c不要跟我比惨，我比你更惨\u201d再适合这部电影不过了。","order_num":27},{"m_id":"20495023","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2614500706.jpg","title":"寻梦环游记","director":"李·昂克里奇 Lee Unkrich / 阿德里安·莫利纳 Adrian Molina","score":"9.1","quote":"死亡不是真的逝去，遗忘才是永恒的消亡。","order_num":28},{"m_id":"1292365","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p2524040532.jpg","title":"活着","director":"张艺谋 Yimou Zhang","score":"9.3","quote":"张艺谋最好的电影。","order_num":29},{"m_id":"30170448","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2555295759.jpg","title":"何以为家","director":"娜丁·拉巴基 Nadine Labaki","score":"9.1","quote":"凝视卑弱生命，用电影改变命运。","order_num":30},{"m_id":"1300267","img":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1963126880.jpg","title":"乱世佳人","director":"维克多·弗莱明 Victor Fleming / 乔治·库克 George Cukor","score":"9.3","quote":"Tomorrow is another day.","order_num":31},{"m_id":"1295038","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2614949805.jpg","title":"哈利·波特与魔法石","director":"Chris Columbus","score":"9.1","quote":"童话世界的开端。","order_num":32},{"m_id":"1291552","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p691932373.jpg","title":"指环王3：王者无敌","director":"彼得·杰克逊 Peter Jackson","score":"9.2","quote":"史诗的终章。","order_num":33},{"m_id":"26387939","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2401676338.jpg","title":"摔跤吧！爸爸","director":"涅提·蒂瓦里 Nitesh Tiwari","score":"9.0","quote":"你不是在为你一个人战斗，你要让千千万万的女性看到女生并不是只能相夫教子。","order_num":34},{"m_id":"2129039","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p485887754.jpg","title":"飞屋环游记","director":"彼特·道格特 Pete Docter / 鲍勃·彼德森 Bob Peterson","score":"9.0","quote":"最后那些最无聊的事情，才是最值得怀念的。","order_num":35},{"m_id":"21937452","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2118532944.jpg","title":"素媛","director":"李濬益 Jun-ik Lee","score":"9.3","quote":"受过伤害的人总是笑得最开心，因为他们不愿意让身边的人承受一样的痛苦。","order_num":36},{"m_id":"1929463","img":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1784592701.jpg","title":"少年派的奇幻漂流","director":"李安 Ang Lee","score":"9.1","quote":"瑰丽壮观、无人能及的冒险之旅。","order_num":37},{"m_id":"1293182","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p2173577632.jpg","title":"十二怒汉","director":"Sidney Lumet","score":"9.4","quote":"1957年的理想主义。","order_num":38},{"m_id":"1308807","img":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2174346180.jpg","title":"哈尔的移动城堡","director":"宫崎骏 Hayao Miyazaki","score":"9.1","quote":"带着心爱的人在天空飞翔。","order_num":39},{"m_id":"1291858","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2553104888.jpg","title":"鬼子来了","director":"姜文 Wen Jiang","score":"9.3","quote":"对敌人的仁慈，就是对自己残忍。","order_num":40},{"m_id":"1299398","img":"https://img2.doubanio.com/view/photo/s_ratio_poster/public/p2561721372.jpg","title":"大话西游之月光宝盒","director":"刘镇伟 Jeffrey Lau","score":"9.0","quote":"旷古烁今。","order_num":41},{"m_id":"1291583","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1446261379.jpg","title":"天空之城","director":"宫崎骏 Hayao Miyazaki","score":"9.1","quote":"对天空的追逐，永不停止。","order_num":42},{"m_id":"26752088","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2561305376.jpg","title":"我不是药神","director":"文牧野 Muye Wen","score":"9.0","quote":"对我们国家而言，这样的电影多一部是一部。","order_num":43},{"m_id":"1293839","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2189265085.jpg","title":"罗马假日","director":"威廉·惠勒 William Wyler","score":"9.0","quote":"爱情哪怕只有一天。","order_num":44},{"m_id":"1298624","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2550757929.jpg","title":"闻香识女人","director":"马丁·布莱斯 Martin Brest","score":"9.1","quote":"史上最美的探戈。","order_num":45},{"m_id":"21937445","img":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2158166535.jpg","title":"辩护人","director":"杨宇硕 Woo-seok Yang","score":"9.2","quote":"电影的现实意义大过电影本身。","order_num":46},{"m_id":"1291828","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2559577569.jpg","title":"天堂电影院","director":"朱塞佩·托纳多雷 Giuseppe Tornatore","score":"9.2","quote":"那些吻戏，那些青春，都在影院的黑暗里被泪水冲刷得无比清晰。","order_num":47},{"m_id":"1305487","img":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p453924541.jpg","title":"猫鼠游戏","director":"史蒂文·斯皮尔伯格 Steven Spielberg","score":"9.0","quote":"骗子大师和执著警探的你追我跑故事。","order_num":48},{"m_id":"1418019","img":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2184505167.jpg","title":"大闹天宫","director":"万籁鸣 Laiming Wan / 唐澄 Cheng Tang","score":"9.4","quote":"经典之作，历久弥新。","order_num":49}]}
     */

    public String coderYJ;
    public String money;
    public String course;
    @SerializedName("www.itjava.cn")
    public String _$WwwItjavaCn237; // FIXME check this code
    public String idea;
    public int code;
    public String msg;
    public T data;
}
