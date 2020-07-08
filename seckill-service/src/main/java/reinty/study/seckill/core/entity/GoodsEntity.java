package reinty.study.seckill.core.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_goods")
public class GoodsEntity extends BaseAutoIdEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private int goodsType;
    /**
     * 编码
     */
    private String code;
    /**
     * 类目
     */
    private long categoryId;
    /**
     * 销售类型
     */
    private int saleType;
    /**
     * 上架／下架
     */
    private int shelfType;
    /**
     * 价格
     */
    private long price;
    /**
     * 会员价
     */
    private long vipPrice;
    /**
     * 划线价
     */
    private long linePrice;
    /**
     * 封面图
     */
    private String coverUrl;
    /**
     * 描述
     */
    private String description;
    /**
     * 扩展
     */
    private String conf;
    /**
     * 快递模版ID
     */
    private long expressTemplateId;
    /**
     * 操作人ID
     */
    private long opUserId;
    /**
     * 操作人名称
     */
    private String opUserName;
    /**
     * 状态
     */
    private int stat;
    /**
     * 商品列表小图
     */
    private String squareUrl;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 积分商城导航
     */
    private int scoreNavigation;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频封面图
     */
    private String posterUrl;

    /**
     * 是否支持加入购物车
     */
    private int shoppingCart;

    /**
     * 0 : 人工手动创建 1：批量自动创建
     */
    private int autoCreate;

    /**
     * 商品打标
     */
    private int mark;

    /**
     * 期次ID
     */
    private long periodId;

    /**
     * 期次名称
     */
    private String periodName;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "goods_type")
    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "sale_type")
    public int getSaleType() {
        return saleType;
    }

    public void setSaleType(int saleType) {
        this.saleType = saleType;
    }

    @Column(name = "shelf_type")
    public int getShelfType() {
        return shelfType;
    }

    public void setShelfType(int shelfType) {
        this.shelfType = shelfType;
    }

    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Column(name = "vip_price")
    public long getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(long vipPrice) {
        this.vipPrice = vipPrice;
    }

    @Column(name = "line_price")
    public long getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(long linePrice) {
        this.linePrice = linePrice;
    }

    @Column(name = "cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "conf")
    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    @Column(name = "express_template_id")
    public long getExpressTemplateId() {
        return expressTemplateId;
    }

    public void setExpressTemplateId(long expressTemplateId) {
        this.expressTemplateId = expressTemplateId;
    }

    @Column(name = "op_user_id")
    public long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(long opUserId) {
        this.opUserId = opUserId;
    }

    @Column(name = "op_user_name")
    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    @Column(name = "stat")
    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    @Column(name = "square_url")
    public String getSquareUrl() {
        return squareUrl;
    }

    public void setSquareUrl(String squareUrl) {
        this.squareUrl = squareUrl;
    }

    @Column(name = "channel")
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(name = "score_navigation")
    public int getScoreNavigation() {
        return scoreNavigation;
    }

    public void setScoreNavigation(int scoreNavigation) {
        this.scoreNavigation = scoreNavigation;
    }

    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(name = "video_name")
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Column(name = "poster_url")
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Column(name = "shopping_cart")
    public int getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(int shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Column(name = "auto_create")
    public int getAutoCreate() {
        return autoCreate;
    }

    public void setAutoCreate(int autoCreate) {
        this.autoCreate = autoCreate;
    }

    @Column(name = "mark")
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = this.mark | mark;
    }

    /**
     * 取消标位
     * @param mark 要取消的标位
     */
    public void cancelMark(int mark){
        this.mark = this.mark & ~mark;
    }

    @Column(name = "period_id")
    public long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(long periodId) {
        this.periodId = periodId;
    }

    @Column(name = "period_name")
    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
}
